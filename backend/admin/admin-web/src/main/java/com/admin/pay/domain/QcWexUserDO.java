package com.admin.pay.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author luojing
 * @email lg932740579@163.com
 * @date 2018-08-02 09:54:57
 */
public class QcWexUserDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//昵称
	private String nickName;
	//获取的openid
	private String openId;
	//省份
	private String provice;
	//城市
	private String city;
	//最后更新时间，用于验证code是否过期
	private Date lastTime;
	//用户头像
	private String userHead;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：昵称
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * 获取：昵称
	 */
	public String getNickName() {
		return nickName;
	}
	/**
	 * 设置：获取的openid
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	/**
	 * 获取：获取的openid
	 */
	public String getOpenId() {
		return openId;
	}
	/**
	 * 设置：省份
	 */
	public void setProvice(String provice) {
		this.provice = provice;
	}
	/**
	 * 获取：省份
	 */
	public String getProvice() {
		return provice;
	}
	/**
	 * 设置：城市
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 获取：城市
	 */
	public String getCity() {
		return city;
	}
	/**
	 * 设置：最后更新时间，用于验证code是否过期
	 */
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	/**
	 * 获取：最后更新时间，用于验证code是否过期
	 */
	public Date getLastTime() {
		return lastTime;
	}
	public String getUserHead() {
		return userHead;
	}
	public void setUserHead(String userHead) {
		this.userHead = userHead;
	}
	
}
