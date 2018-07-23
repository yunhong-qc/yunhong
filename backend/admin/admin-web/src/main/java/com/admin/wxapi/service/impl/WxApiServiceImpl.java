package com.admin.wxapi.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Formatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.utils.FileLog;
import com.admin.utils.HttpUtils;
import com.admin.utils.wx.WxUtils;
import com.admin.wxapi.dao.QcWxTokenDao;
import com.admin.wxapi.domain.QcWxTokenDO;
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

	@Autowired
	public QcWxTokenDao qcWxTokenDao;
	
	@Override
	public JSONObject goReadCardAnniu2(String url) {

		try {
			url=java.net.URLDecoder.decode(url,"UTF-8");
			//从数据库获取token数据
			QcWxTokenDO tokenDo= qcWxTokenDao.getByAppId(WxUtils.appId);
			/**
			 * 开始验证token是否超时
			 */
			if(!WxUtils.validTokenOut(tokenDo)) {
				System.out.println("token超时..");
				//表示超时,需要重新获取token和ticket
				//1.重新获取token
				JSONObject tokenObject = WxUtils.geWxtAccessToken();
				System.out.println(tokenObject.toString());
				//2.重新获取ticket
				String ticket = this.getJsapiTicket(tokenObject.getString("access_token"));
				if(tokenDo!=null) {
					//更新
					tokenDo.setAcessToken(tokenObject.getString("access_token"));
					tokenDo.setTicket(ticket);
					tokenDo.setCreateTime(new Date());
					qcWxTokenDao.update(tokenDo);
				}else {
					//新增
					tokenDo=new QcWxTokenDO();
					tokenDo.setAcessToken(tokenObject.getString("access_token"));
					tokenDo.setOutTime(WxUtils.token_out_time);
					tokenDo.setAppid(WxUtils.appId);
					tokenDo.setCreateTime(new Date());
					tokenDo.setTicket(ticket);
					qcWxTokenDao.save(tokenDo);
				}
			}
			System.out.println("token:"+tokenDo.getAcessToken());
			System.out.println("ticket:"+tokenDo.getTicket());
			//获取调用微信jsapi的凭证
			JSONObject map = this.sign(tokenDo.getTicket(), url);
			map.put("app_id", WxUtils.appId);
			return map;
		} catch (Exception e) {
			// TODO 打印输出日志
			e.printStackTrace();
			
		}
		return null;
	}

	public String getJsapiTicket(String access_token) throws Exception {
		String getticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=";// 接口凭据

		String jsonData = HttpUtils.getReString(getticket_url + access_token + "&type=jsapi");
		JSONObject jsonObj = JSONObject.parseObject(jsonData);
		System.out.println("ticket:"+jsonObj.toString());
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
		FileLog.debugLog("jsapi_ticket:"+jsapi_ticket);
		FileLog.debugLog("nonce_str:"+nonce_str);
		FileLog.debugLog("timestamp:"+timestamp);
//		ret.put("url", url);
//		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("noncestr", nonce_str);
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

