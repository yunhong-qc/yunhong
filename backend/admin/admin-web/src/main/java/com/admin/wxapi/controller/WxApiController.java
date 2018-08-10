package com.admin.wxapi.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.pay.domain.QcPayOrderDO;
import com.admin.pay.domain.QcWexUserDO;
import com.admin.pay.service.QcPayOrderService;
import com.admin.pay.service.QcWexUserService;
import com.admin.utils.BaseResultModel;
import com.admin.utils.DateUtils;
import com.admin.utils.FileLog;
import com.admin.utils.GetMacAddr;
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

	@Autowired
	private IWxApiService wxApiService;
	@Autowired
	private QcPayOrderService qcPayOrderService;
	@Autowired
	private QcWexUserService qcWexUserService;
	private String prefix = "wx";

	@GetMapping("/bindPage")
	String bindpage(HttpServletRequest request) {
		request.setAttribute("test", "54646");
		return prefix + "/bind";
	}

	@GetMapping("/bindMpos")
	String bindmpos() {
		FileLog.debugLog("12312321");
		FileLog.errorLog("7679879");
		return prefix + "/BleConnectsxlb";
	}

	@GetMapping("/authdev")
	String authendev(HttpServletRequest request) {
		String openid = "";
		HttpSession session = request.getSession();
		openid = (String) session.getAttribute(session.getId());
		if (StringUtils.isNullString(openid)) {
			openid=request.getParameter("random");
		}
		if (!StringUtils.isNullString(openid)) {
			/**
			 * 通过session获取openid
			 */
			List<QcPayOrderDO> lst = new ArrayList<QcPayOrderDO>();
			QcPayOrderDO par = new QcPayOrderDO();
			par.setPayUserPhone(openid);
			par.setOrderState(2);
			QcWexUserDO wx = qcWexUserService.getByOpenId(openid);
			lst = qcPayOrderService.getOrderBy(par);
			request.setAttribute("orders", lst);
			if (wx != null) {
				request.setAttribute("userhead", wx.getUserHead());
				request.setAttribute("nickname", wx.getNickName());
				request.setAttribute("openid", wx.getOpenId());
			}
		}
		return prefix + "/authendev";
	}

	@GetMapping("/pubBind")
	String pubBind(HttpServletRequest request) {
		try {
			/*String ip = GetMacAddr.getIpAddress(request);
			String mac = GetMacAddr.getMacAddress(ip);
			System.out.println("ip地址：" + ip + "mac地址：" + mac);*/
			String openid = "";
			String access_token = "";
			HttpSession session = request.getSession();
			openid = (String) session.getAttribute(session.getId());
			String code = request.getParameter("code");
			// state可以为任何含义，暂不使用
			// String state = request.getParameter("state");
			if (StringUtils.isNullString(openid)) {

				// 授权码、商品id
				System.out.println("code=" + code);
				/**
				 * 暂时控制。
				 */
				if (StringUtils.isNullString(code)) {
					return prefix + "/bindpage";
				}
				// 下面就到了获取openid,这个代表用户id.
				// 获取openID
				JSONObject openJson = ServiceUtil.getOpenId(code);
				openid = openJson.getString("openid");
				if(StringUtils.isNullString(openid)) {
					//没有获取到，后续在做处理
					return prefix + "/bindpage";
				}
				access_token = openJson.getString("access_token");
				// 保存openid到session
				session.setAttribute(session.getId(), openid);
			} 
			if (!StringUtils.isNullString(openid)) {
				// 保存openid
				request.setAttribute("openid", openid);
				QcWexUserDO qcWexUser = qcWexUserService.getByOpenId(openid);
				if (qcWexUser == null
						|| DateUtils.compareTwoTimeDifferMuch(qcWexUser.getLastTime(), new Date(), 3600 * 24 * 7)) {
					// 获取保存用户信息
					JSONObject userInfo = ServiceUtil.getUserInfo(access_token, openid, code, 0);
					if (userInfo != null) {
						qcWexUser = new QcWexUserDO();
						qcWexUser.setOpenId(openid);
						qcWexUser.setCity(userInfo.getString("city"));
						qcWexUser.setProvice(userInfo.getString("province"));
						qcWexUser.setNickName(userInfo.getString("nickname"));
						qcWexUser.setUserHead(userInfo.getString("headimgurl"));
						qcWexUserService.save(qcWexUser);
					}
				}
				request.setAttribute("nickname", qcWexUser.getNickName());
			}
		} catch (Exception e) {
			FileLog.errorLog(e, "获取微信参数失败。");
		}
		return prefix + "/bindpage";
	}

	@ApiOperation(value = "初始化", notes = "")
	@GetMapping("/initJsApi")
	@ResponseBody
	public BaseResultModel initJsApi(HttpServletRequest request) {
		BaseResultModel bm = new BaseResultModel("100001", "获取异常");
		try {
			String url = request.getParameter("url");
			JSONObject result = wxApiService.goReadCardAnniu2(url);
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
			WXBizMsgCrypt wx = WXBizMsgCrypt.getWxCrypt();
			// 从request中取得输入流
			InputStream inputStream = request.getInputStream();
			// 转为String
			String xml = StrXmlToMap.ISXmlToString(inputStream);
			// 解密
			String res = wx.decryptMsg(xml);
			return WxMessageUtil.responseMessage(res);
			// return wx.encryptMsg("已收到你的消息，马上前往支援。", DateUtils.format(new Date(),
			// DateUtils.DATE_TIME_STAMP), WxUtils.getRandomStr());
		} catch (Exception e) {
			// TODO 打印输出日志
			e.printStackTrace();
		}
		return null;
	}

}
