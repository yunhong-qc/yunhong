package com.admin.wxapi.controller;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
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

import com.admin.utils.BaseResultModel;
import com.admin.utils.FileLog;
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
	
	private String prefix = "wx";

	@GetMapping("/bindPage")
	String bindpage() {
		return prefix + "/bind";
	}
	@GetMapping("/bindMpos")
	String bindmpos() {
		return prefix + "/BleConnectsxlb";
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
