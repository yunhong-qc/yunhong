package com.admin.wxapi.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.admin.utils.HttpUtils;
import com.admin.wxapi.service.IWxApiService;
import com.alibaba.fastjson.JSONObject;

/**
作者：fengchase
时间：2018年7月6日
文件：WxApiServiceImpl.java
项目：admin-web
*/
@Service("wxApiService")
public class WxApiServiceImpl implements IWxApiService{

	@Override
	public JSONObject goReadCardAnniu2() {

		try {
			String appId = "wx3d7f7c26f2369785";// 应用id
			String appsecret = "c34fde54dfac1f9c2870ee8edf467ebf";// 应用秘钥
			// 1,获取access_token
			JSONObject params = new JSONObject();
			params.put("appid", appId);
			params.put("secret", appsecret);

			JSONObject tokenObject = HttpUtils
					.getReObject("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId
							+ "&secret=" + appsecret + "");
			// 2,获取调用微信jsapi的凭证
			String ticket = this.getJsapiTicket(tokenObject.getString("access_token"));
			JSONObject map = this.sign(ticket, "http://app.cdqckj.com/wx/ble/bindPage");
//			request.setAttribute("timestamp", map.get("timestamp"));
//			request.setAttribute("nonceStr", map.get("nonceStr"));
//			request.setAttribute("signature", map.get("signature"));
			map.put("appId", appId);
			return map;
		} catch (Exception e) {
			// TODO 打印输出日志
			e.printStackTrace();
			
		}
		return null;
	}
	
	private JSONObject geWxtAccessToken(String appId,String appsecret) {
		try {
			return HttpUtils
			.getReObject("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId
					+ "&secret=" + appsecret + "");
		} catch (Exception e) {
			// TODO 打印输出日志
			e.printStackTrace();
		}
		return new JSONObject();
	}

	public String getJsapiTicket(String access_token) throws Exception {
		String getticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=";// 接口凭据

		String jsonData = HttpUtils.getReString(getticket_url + access_token + "&type=jsapi");
		JSONObject jsonObj = JSONObject.parseObject(jsonData);
		String errcode = jsonObj.getString("errcode");
		String ticket = null;
		if (errcode.equals("0")) {
			ticket = jsonObj.getString("ticket");
		}
		return ticket;
	}

	/***
	 * 获取界面调用jsapi的所需参数
	 * 
	 * @param jsapi_ticket
	 *            凭据
	 * @param url
	 *            界面请求地址
	 * @return V型知识库 www.vxzsk.com
	 */
	public JSONObject sign(String jsapi_ticket, String url) {
		JSONObject ret = new JSONObject();
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		String string1;
		String signature = "";

		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
		System.out.println(string1);

		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		ret.put("url", url);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);

		return ret;
	}

	private String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	private String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}
}

