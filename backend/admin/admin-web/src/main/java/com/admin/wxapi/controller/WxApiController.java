package com.admin.wxapi.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.admin.system.service.DeptService;
import com.admin.utils.BaseResultModel;
import com.admin.utils.HttpUtils;
import com.admin.wxapi.service.IWxApiService;
import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.ApiOperation;

/**
 * 作者：fengchase 时间：2018年7月6日 文件：WxApiController.java 项目：admin-web
 */
@Controller
@RequestMapping("/wx/ble")
public class WxApiController {


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
	public BaseResultModel goReadCardAnniu2() {
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


}
