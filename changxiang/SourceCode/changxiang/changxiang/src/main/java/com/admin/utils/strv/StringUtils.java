package com.admin.utils.strv;
/**
作者：fengchase
时间：2018年9月11日
文件：StringUtils.java
项目：changxiang
*/
public class StringUtils {
	public static boolean isNullString(String str) {
		if(str==null || str=="" || "".equals(str)) {
			return true;
		}
		return false;
	}

}

