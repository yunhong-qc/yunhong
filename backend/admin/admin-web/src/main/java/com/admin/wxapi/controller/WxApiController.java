package com.admin.wxapi.controller;


import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.impl.util.json.XML;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.common.aspect.WebLogAspect;
import com.admin.utils.BaseResultModel;
import com.admin.utils.WxUtils;
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
	String add() {

		return prefix + "/bind";
	}
	
	@ApiOperation(value = "初始化", notes = "")
	@GetMapping("/initJsApi")
	@ResponseBody
	public BaseResultModel goReadCardAnniu2(HttpServletRequest request) {
		BaseResultModel bm=new BaseResultModel("100001","获取异常");
		try {
			JSONObject result= wxApiService.goReadCardAnniu2();
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
	@GetMapping("/wxServiceGet")
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
	@RequestMapping("/wxService")
	@ResponseBody
	public String wxServicePost(HttpServletRequest request) {
		try {
			InputStream is=request.getInputStream();
			if(is==null) {
				logger.error("未获取到任何xml信息");
			}
			String xml=IOUtils.toString(is);
			org.json.JSONObject j= org.json.XML.toJSONObject(xml);
			
			logger.debug("接收到微信消息。"+xml);
	        
		} catch (Exception e) {
			// TODO 打印输出日志
			e.printStackTrace();
		}
		return null;
	}


}
