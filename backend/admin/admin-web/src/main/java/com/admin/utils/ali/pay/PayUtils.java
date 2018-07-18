package com.admin.utils.ali.pay;

import com.admin.pay.domain.QcPayOrderDO;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;

/**
作者：fengchase
时间：2018年7月18日
文件：PayUtils.java
项目：admin-web
*/
public class PayUtils {
	
	public static String getAliPayOrderInfos(QcPayOrderDO order) throws AlipayApiException {
		//实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
        		AlipayConfig.APPID,//appid
        		AlipayConfig.RSA_PRIVATE_KEY,//私钥
                "json", //格式，仅支持json
                AlipayConfig.CHARSET,//请求编码格式
                AlipayConfig.ALIPAY_PUBLIC_KEY,//应用公钥
                "RSA2");//签名算法类型，支持RSA2和RSA，推荐使用的是RSA2
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(order.getOrderRemark());
        model.setSubject("标题");
        model.setOutTradeNo(order.getOrderNo());
        model.setTimeoutExpress("30m");//一般用不到这个
        model.setTotalAmount(order.getPayPrice());//这个嘛就是钱喽
        model.setProductCode("QUICK_MSECURITY_PAY");//商家和支付宝签约的产品码，为固定值
        request.setBizModel(model);
        request.setNotifyUrl(AlipayConfig.notify_url);//外网异步回调地址，是需要外网能够访问到的
        //这里和普通的接口调用不同，使用的是sdkExecute
        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
        response.getCode();
        return response.getBody();
       
	}

}

