package com.admin.wxapi.controller;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.pay.domain.QcPayOrderDO;
import com.admin.pay.service.QcPayOrderService;
import com.admin.utils.BaseResultModel;
import com.admin.utils.FileLog;
import com.admin.utils.StringUtils;
import com.admin.utils.pay.wex.ServiceUtil;
import com.admin.utils.wx.StrXmlToMap;
import com.admin.utils.wx.WXBizMsgCrypt;
import com.admin.utils.wx.WxMessageUtil;
import com.admin.utils.wx.WxUtils;
import com.admin.wxapi.service.IWxApiService;
import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.ApiOperation;

/**
 * 作者：fengchase 时间：2018年7月6日 文件：WxApiController.java 项目：admin-web
 */
@Controller
@RequestMapping("/wx/ble")
public class WxApiController {

	private static final Logger logger = LoggerFactory.getLogger(WxApiController.class);
	@Autowired
	private IWxApiService wxApiService;
	@Autowired
	private QcPayOrderService qcPayOrderService;
	private String prefix = "wx";

	@GetMapping("/bindPage")
	String bindpage(HttpServletRequest request) {
		request.setAttribute("test", "54646");
		return prefix + "/bind";
	}
	@GetMapping("/bindMpos")
	String bindmpos() {
		return prefix + "/BleConnectsxlb";
	}
	@GetMapping("/pubBind")
	String bindmpos2(HttpServletRequest request) {
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
		 * 暂时控制。
		 */
		if(StringUtils.isNullString(code)) {
			return prefix + "/bindpage";
		}
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
		List<QcPayOrderDO> lst=new ArrayList<QcPayOrderDO>();
		if(!StringUtils.isNullString(openid)) {
			//不为空，获取用户订单数据
			QcPayOrderDO par=new QcPayOrderDO();
			par.setPayUserPhone(openid);
			lst=qcPayOrderService.getOrderBy(par);
		}
//		request.setAttribute("code", code);
//		request.setAttribute("state", state);
		request.setAttribute("openid", openid);
		request.setAttribute("nickname", userInfo.get("nickname"));
		request.setAttribute("headurl", userInfo.get("headimgurl"));
		request.setAttribute("orders", lst);
		return prefix + "/bindpage";
	}
	
	@ApiOperation(value = "初始化", notes = "")
	@GetMapping("/initJsApi")
	@ResponseBody
	public BaseResultModel initJsApi(HttpServletRequest request) {
		BaseResultModel bm=new BaseResultModel("100001","获取异常");
		try {
			String url=request.getParameter("url");
			JSONObject result= wxApiService.goReadCardAnniu2(url);
			bm.setCode("000000");
			bm.setMsg("获取成功");
			bm.setData(result);
		} catch (Exception e) {
			// TODO 打印输出日志
			e.printStackTrace();
		}

		return bm;
	}
	@ApiOperation(value = "微信对接服务器验证", notes = "")
	@GetMapping("/wxService")
	@ResponseBody
	public String wxServiceGet(HttpServletRequest request) {
		try {
			String msgSignature = request.getParameter("signature");
	        String msgTimestamp = request.getParameter("timestamp");
	        String msgNonce = request.getParameter("nonce");
	        String echostr = request.getParameter("echostr");
	     // 这里的 WXPublicConstants.TOKEN 填写你自己设置的Token就可以了
	        if (WxUtils.verifyUrl(msgSignature, msgTimestamp, msgNonce)) {
	            return echostr;
	        }
		} catch (Exception e) {
			// TODO 打印输出日志
			e.printStackTrace();
		}
		return null;
	}
	@ApiOperation(value = "接受微信发送的消息", notes = "")
	@PostMapping("/wxService")
	@ResponseBody
	public String wxServicePost(HttpServletRequest request) {
		try {
			WXBizMsgCrypt wx=WXBizMsgCrypt.getWxCrypt();
			// 从request中取得输入流  
	        InputStream inputStream = request.getInputStream();  
	        //转为String
	        String xml=StrXmlToMap.ISXmlToString(inputStream);
	        //解密
	        String res=wx.decryptMsg(xml);
	        return WxMessageUtil.responseMessage(res);
//			return wx.encryptMsg("已收到你的消息，马上前往支援。", DateUtils.format(new Date(), DateUtils.DATE_TIME_STAMP), WxUtils.getRandomStr());
		} catch (Exception e) {
			// TODO 打印输出日志
			e.printStackTrace();
		}
		return null;
	}


}
