package com.admin.pay.controller;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import com.admin.pay.domain.QcPayOrderDO;
import com.admin.pay.domain.RequestHandler;
import com.admin.pay.service.QcPayOrderService;
import com.admin.utils.BaseResultModel;
import com.admin.utils.DateUtils;
import com.admin.utils.FileLog;
import com.admin.utils.PageUtils;
import com.admin.utils.Query;
import com.admin.utils.R;
import com.admin.utils.ResultCode;
import com.admin.utils.pay.ali.AlipayConfig;
import com.admin.utils.pay.ali.PayException;
import com.admin.utils.pay.wex.ServiceUtil;
import com.admin.utils.pay.wex.WeixinUtils;
import com.admin.utils.pay.wex.WxPayConfig;
import com.admin.utils.wx.StrXmlToMap;
import com.admin.utils.wx.WxUtils;
import com.admin.wxapi.domain.PayRecordDO;
import com.admin.wxapi.service.PayRecordService;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;

import groovy.transform.Synchronized;

/**
 * 
 * 
 * @author luojing
 * @email lg932740579@163.com
 * @date 2018-07-17 16:57:43
 */

@Controller
@RequestMapping("/pay")
public class QcPayOrderController {
	@Autowired
	private QcPayOrderService qcPayOrderService;
	@Autowired
	private PayRecordService PpayRecordService;

	@GetMapping()
	String QcPayOrder() {
		return "pay/qcPayOrder/qcPayOrder";
	}

	private String prefix = "wx";

	/**
	 * 保存新单，并返回订单信息
	 */
	@ResponseBody
	@PostMapping("/addOrder")
	synchronized public BaseResultModel addNewOrder(QcPayOrderDO qcPayOrder) {
		try {
			String result = qcPayOrderService.addNewOrderAndSDKRSA(qcPayOrder);
			return BaseResultModel.success(result);
		} catch (AlipayApiException pay) {
			FileLog.errorLog(pay, "支付签名校验失败。");
			return BaseResultModel.Tailor(ResultCode.ERROR_CODE, "添加失败");
		} catch (Exception e) {
			FileLog.errorLog(e, "异常");
			return BaseResultModel.Tailor(ResultCode.ERROR_CODE, "添加订单失败");
		}
	}

	/**
	 * 获取预支付订单信息
	 */
	@ResponseBody
	@PostMapping("/getPayPreOrder")
	public BaseResultModel getPayPreOrder(QcPayOrderDO qcPayOrder) {
		try {
			String result = qcPayOrderService.getPayOrderANDSDKRSA(qcPayOrder);
			if (result == null) {
				// 获取订单失败
				return BaseResultModel.Tailor(ResultCode.ERROR_CODE, "获取订单失败");
			}
			return BaseResultModel.success(result);

		} catch (PayException pe) {
			FileLog.errorLog(pe, "订单已超时。");
			return BaseResultModel.Tailor(ResultCode.ERROR_CODE, "订单已超时");
		} catch (Exception e) {
			FileLog.errorLog(e, "获取订单失败。");
			return BaseResultModel.Tailor(ResultCode.ERROR_CODE, "获取订单失败");
		}
	}

	/**
	 * 支付宝异步通知回调
	 */
	@RequestMapping(value = "/ali/callBack", method = RequestMethod.POST, consumes = "application/json", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String ali(HttpServletRequest request) {
		try {

			Map<String, String> params = new HashMap<String, String>();
			Map<String, String[]> requestParams = request.getParameterMap();
			for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				FileLog.debugLog("name:" + name + "    value:" + valueStr);
				// 乱码解决，这段代码在出现乱码时使用。
				// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
			// 切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
			boolean flag = true; // 校验公钥正确性防止意外
			try {
				flag = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, "utf-8", "RSA2");
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (flag) {
				/**
				 * 调用方法保存数据
				 */
				qcPayOrderService.alipayResultCallBack(params);
			}
			return "success";
		} catch (Exception e) {
			FileLog.errorLog(e, "支付宝回调出现异常");
			return "error";
		}
	}

	/**
	 * 微信回调
	 * 
	 * @param request
	 * @return 2018年7月23日 作者：fengchase
	 */
	@RequestMapping(value = "/wex/callBack")
	@ResponseBody
	public String wexCallBack(HttpServletRequest request, HttpServletResponse response) {
		try {
			InputStream inStream = request.getInputStream();
			ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inStream.read(buffer)) != -1) {
				outSteam.write(buffer, 0, len);
			}
			outSteam.close();
			inStream.close();
			String resultStr = new String(outSteam.toByteArray(), WxPayConfig.CHARTSET);
			Map<String, String> resultMap = StrXmlToMap.strXmltoMap(resultStr);

			System.out.println("微信回调结果:" + resultMap.toString());

			String result_code = resultMap.get("result_code");
			String orderNo = resultMap.get("out_trade_no");
			// String is_subscribe = resultMap.get("is_subscribe");
			// String transaction_id = resultMap.get("transaction_id");
			// String sign = resultMap.get("sign");
			// String time_end = resultMap.get("time_end");
			// String bank_type = resultMap.get("bank_type");
			String return_code = resultMap.get("return_code");

			QcPayOrderDO qo = qcPayOrderService.getOrderByOrderNumber(orderNo);
			// 签名验证
			// GenericValue userLogin = delegator.findOne("UserLogin",
			// UtilMisc.toMap("userLoginId", "admin"), false);
			PayRecordDO prd = new PayRecordDO();
			prd.setPayMoney(qo.getPayPrice());
			prd.setOrderNo(orderNo);
			prd.setPayType(qo.getPayType());
			prd.setPayDeviceNo(qo.getOrderNo());
			if (return_code.equals("SUCCESS")) {
				if (result_code.equals("SUCCESS")) {
					prd.setPayResult(0);
				} else {
					prd.setPayResult(1);
				}
				// 修改数据库支付状态
			} else {
				// 交易失败，支付失败
				prd.setPayResult(1);
			}
			// 保存交易记录
			PpayRecordService.save(prd);
			request.setAttribute("out_trade_no", orderNo);
			// 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		return RequestHandler.setXML("SUCCESS", "");
	}

	@RequestMapping("toPayPage")
	public String toPayPage(HttpServletRequest request) {
		/**
		 * 第一步：用户同意授权，根据参数，获取code
		 * 授权成功后返回的授权码，参考：http://mp.weixin.qq.com/wiki/17/c0f37d5704f0b64713d5d2c37b468d75.html#.E7.AC.AC.E4.B8.80.E6.AD.A5.EF.BC.9A.E7.94.A8.E6.88.B7.E5.90.8C.E6.84.8F.E6.8E.88.E6.9D.83.EF.BC.8C.E8.8E.B7.E5.8F.96code
		 */

		String code = request.getParameter("code");
		String state = request.getParameter("state");

		// state可以为任何含义，根据你前端需求，这里暂时叫商品id
		// 授权码、商品id
		System.out.println("code=" + code + ",state=" + state);

		/**
		 * 第二步：通过code换取网页授权access_token
		 * 根据授权码code获取access_token，参考：http://mp.weixin.qq.com/wiki/17/c0f37d5704f0b64713d5d2c37b468d75.html#.E7.AC.AC.E4.BA.8C.E6.AD.A5.EF.BC.9A.E9.80.9A.E8.BF.87code.E6.8D.A2.E5.8F.96.E7.BD.91.E9.A1.B5.E6.8E.88.E6.9D.83access_token
		 */
		// 下面就到了获取openid,这个代表用户id.
		// 获取openID
		JSONObject openJson = ServiceUtil.getOpenId(code);
		String openid = openJson.getString("openid");
		String access_token = openJson.getString("access_token");
		JSONObject userInfo=ServiceUtil.getUserInfo(access_token, openid);
		
//		request.setAttribute("code", code);
//		request.setAttribute("state", state);
		request.setAttribute("openid", openid);
		request.setAttribute("nickname", userInfo.get("nickname"));
		request.setAttribute("headurl", userInfo.get("headimgurl"));
		return "pay/wxPay";
	}

	/**
	 * 前往充值界面
	 * 
	 * @param request
	 * @return 2018年7月26日 作者：fengchase
	 */
	@RequestMapping("toCharnagePage")
	public String toCharnagePage(HttpServletRequest request) {
		/**
		 * 第一步：用户同意授权，根据参数，获取code
		 * 授权成功后返回的授权码，参考：http://mp.weixin.qq.com/wiki/17/c0f37d5704f0b64713d5d2c37b468d75.html#.E7.AC.AC.E4.B8.80.E6.AD.A5.EF.BC.9A.E7.94.A8.E6.88.B7.E5.90.8C.E6.84.8F.E6.8E.88.E6.9D.83.EF.BC.8C.E8.8E.B7.E5.8F.96code
		 */
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		request.setAttribute("code", code);
		request.setAttribute("state", state);
		return "pay/notifyOrder";
	}

}
