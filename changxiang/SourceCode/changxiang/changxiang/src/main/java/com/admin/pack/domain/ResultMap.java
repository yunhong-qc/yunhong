package com.admin.pack.domain;
/**
作者：fengchase
时间：2018年9月11日
文件：ResultMap.java
项目：admin-web
*/
public class ResultMap {
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
	
	public static ResultMap getErrorJo() {
		ResultMap rm=new ResultMap();
		rm.setCode("000001");
		rm.setMsg("失败");
		return rm;
	}
	public static ResultMap getErrorJo(String msg) {
		ResultMap rm=new ResultMap();
		rm.setCode("000001");
		rm.setMsg(msg);
		return rm;
	}
	public static ResultMap getSuccessJo() {
		ResultMap rm=new ResultMap();
		rm.setCode("000000");
		rm.setMsg("成功");
		return rm;
	}
	
}

