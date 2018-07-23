package com.admin.pay.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import java.util.HashMap;
import java.util.Iterator;

import com.admin.pay.domain.QcPayOrderDO;
import com.admin.pay.service.QcPayOrderService;
import com.admin.utils.BaseResultModel;
import com.admin.utils.FileLog;
import com.admin.utils.PageUtils;
import com.admin.utils.Query;
import com.admin.utils.R;
import com.admin.utils.ResultCode;
import com.admin.utils.ali.pay.AlipayConfig;
import com.admin.utils.ali.pay.PayException;
import com.admin.utils.ali.pay.PayUtils;
import com.admin.wxapi.domain.PayRecordDO;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;

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

	@GetMapping()
	String QcPayOrder() {
		return "pay/qcPayOrder/qcPayOrder";
	}

	@ResponseBody
	@RequestMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<QcPayOrderDO> qcPayOrderList = qcPayOrderService.list(query);
		int total = qcPayOrderService.count(query);
		PageUtils pageUtils = new PageUtils(qcPayOrderList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	String add() {
		return "pay/qcPayOrder/add";
	}

	@GetMapping("/edit/{orderId}")
	String edit(@PathVariable("orderId") Integer orderId, Model model) {
		QcPayOrderDO qcPayOrder = qcPayOrderService.get(orderId);
		model.addAttribute("qcPayOrder", qcPayOrder);
		return "pay/qcPayOrder/edit";
	}

	/**
	 * 保存新单，并返回订单信息
	 */
	@ResponseBody
	@PostMapping("/addOrder")
	public BaseResultModel addNewOrder(QcPayOrderDO qcPayOrder) {
		try {
			String result=qcPayOrderService.addNewOrderAndSDKRSA(qcPayOrder);
			return BaseResultModel.success(result);
		}catch(AlipayApiException pay) {
			FileLog.errorLog(pay, "支付签名校验失败。");
			return BaseResultModel.Tailor(ResultCode.ERROR_CODE, "添加失败");
		} catch (Exception e) {
			FileLog.errorLog(e, "异常");
			return BaseResultModel.Tailor(ResultCode.ERROR_CODE, "添加订单失败");
		}
	}

	/**
	 * 获取支付订单信息
	 */
	@ResponseBody
	@PostMapping("/getPayOrder")
	public BaseResultModel getPayOrder(QcPayOrderDO qcPayOrder) {
		try {
			QcPayOrderDO norder = new QcPayOrderDO();
			norder = qcPayOrderService.getPayOrder(qcPayOrder);
			if (norder == null) {
				// 获取订单失败
				return BaseResultModel.Tailor(ResultCode.ERROR_CODE, "获取订单失败");
			}
			if (norder.getPayType() == 1) {
				return BaseResultModel.success(norder);
//				return BaseResultModel.success(PayUtils.getAliPayOrderInfos(norder));
			} else {
				return BaseResultModel.success(norder);
//				return BaseResultModel.success(PayUtils.getAliPayOrderInfos(norder));

			}
		} catch (PayException pe) {
			FileLog.errorLog(pe, "订单已超时。");
			return BaseResultModel.Tailor(ResultCode.ERROR_CODE, "订单已超时");
		} catch (Exception e) {
			FileLog.errorLog(e, "添加订单失败。");
			return BaseResultModel.Tailor(ResultCode.ERROR_CODE, "获取订单失败");
		}
	}
	/**
	 * 获取预支付订单信息
	 */
	@ResponseBody
	@PostMapping("/getPayPreOrder")
	public BaseResultModel getPayPreOrder(QcPayOrderDO qcPayOrder) {
		try {
			String result=qcPayOrderService.getPayOrderANDSDKRSA(qcPayOrder);
			if (result == null) {
				// 获取订单失败
				return BaseResultModel.Tailor(ResultCode.ERROR_CODE, "获取订单失败");
			}
			return BaseResultModel.success(result);
			
			
		} catch (PayException pe) {
			FileLog.errorLog(pe, "订单已超时。");
			return BaseResultModel.Tailor(ResultCode.ERROR_CODE, "订单已超时");
		} catch (Exception e) {
			FileLog.errorLog(e, "添加订单失败。");
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
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update(QcPayOrderDO qcPayOrder) {
		qcPayOrderService.update(qcPayOrder);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("pay:qcPayOrder:remove")
	public R remove(Integer orderId) {
		if (qcPayOrderService.remove(orderId) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Integer[] orderIds) {
		qcPayOrderService.batchRemove(orderIds);
		return R.ok();
	}

}
