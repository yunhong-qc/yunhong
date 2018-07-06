package com.admin.utils;
/**
作者：fengchase
时间：2018年7月6日
文件：BaseResultModel.java
项目：admin-web
*/
public class BaseResultModel {
	private String code;
	private String msg;
	private Object data;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public BaseResultModel() {
		
	}
	public BaseResultModel(String code,String msg) {
		this.code=code;
		this.msg=msg;
	}

}

