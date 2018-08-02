package com.admin.utils;

/**
 * @author Admin
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils{
	/**
	 * 是否是空已经空字符串
	 * @param str
	 * @return
	 * 2018年8月1日
	 * 作者：fengchase
	 */
	public static boolean isNullString(String str) {
		if(isEmpty(str) || str=="" || "".equals(str)) {
			return true;
		}
		return false;
	}
}
