package com.admin.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class HttpUtils {
	
	public static String CONTENTYPE_APPLICTIONJSON="application/json;charset=utf-8";
	
	public static String post(String url,JSONObject jsonParam) throws Exception {

        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient client = HttpClients.createDefault();
        String respContent = null;

        StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");//解决中文乱码问题    
        httpPost.addHeader("Content-type",CONTENTYPE_APPLICTIONJSON);  
        httpPost.setHeader("Accept", "application/json");  
        httpPost.setEntity(entity);

        HttpResponse resp = client.execute(httpPost);
        if(resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity he = resp.getEntity();
            respContent = EntityUtils.toString(he,"UTF-8");
        }
        return respContent;
    }
	public static String getReString(String url) throws Exception {
		
		HttpGet httpGet=new HttpGet(url);
		
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;
		
		
		HttpResponse resp = client.execute(httpGet);
		if(resp.getStatusLine().getStatusCode() == 200) {
			HttpEntity he = resp.getEntity();
			respContent = EntityUtils.toString(he,"UTF-8");
		}
		return respContent;
	}
	public static JSONObject getReObject(String url) throws Exception {
		JSONObject result=new JSONObject();
		HttpGet httpGet=new HttpGet(url);
		
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;
		
		
		HttpResponse resp = client.execute(httpGet);
		if(resp.getStatusLine().getStatusCode() == 200) {
			HttpEntity he = resp.getEntity();
			respContent = EntityUtils.toString(he,"UTF-8");
			result=JSONObject.parseObject(respContent);
		}
		
		return result;
	}
}
