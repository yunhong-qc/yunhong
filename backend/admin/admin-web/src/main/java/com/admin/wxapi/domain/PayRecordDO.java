package com.admin.wxapi.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 支付记录
 * 
 * @author luojing
 * @email lg932740579@163.com
 * @date 2018-07-10 11:22:27
 */
public class PayRecordDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键ID
	private Long payId;
	//金额
	private String payMoney;
	//设备编号
	private String payDeviceNo;
	//用户编号
	private String payUserNo;
	//卡类型，根据具体卡类型来。
	private Integer payCardType;
	//支付类型：1，支付宝，2微信，3银联
	private Integer payType;
	//支付结果。1，失败，0成功
	private Integer payResult;
	//逻辑删除,1删除0正常
	private Integer isDel;
	//创建时间
	private Date createTime;

	/**
	 * 设置：主键ID
	 */
	public void setPayId(Long payId) {
		this.payId = payId;
	}
	/**
	 * 获取：主键ID
	 */
	public Long getPayId() {
		return payId;
	}
	/**
	 * 设置：金额
	 */
	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}
	/**
	 * 获取：金额
	 */
	public String getPayMoney() {
		return payMoney;
	}
	/**
	 * 设置：设备编号
	 */
	public void setPayDeviceNo(String payDeviceNo) {
		this.payDeviceNo = payDeviceNo;
	}
	/**
	 * 获取：设备编号
	 */
	public String getPayDeviceNo() {
		return payDeviceNo;
	}
	/**
	 * 设置：用户编号
	 */
	public void setPayUserNo(String payUserNo) {
		this.payUserNo = payUserNo;
	}
	/**
	 * 获取：用户编号
	 */
	public String getPayUserNo() {
		return payUserNo;
	}
	/**
	 * 设置：卡类型，根据具体卡类型来。
	 */
	public void setPayCardType(Integer payCardType) {
		this.payCardType = payCardType;
	}
	/**
	 * 获取：卡类型，根据具体卡类型来。
	 */
	public Integer getPayCardType() {
		return payCardType;
	}
	/**
	 * 设置：支付类型：1，支付宝，2微信，3银联
	 */
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	/**
	 * 获取：支付类型：1，支付宝，2微信，3银联
	 */
	public Integer getPayType() {
		return payType;
	}
	/**
	 * 设置：支付结果。1，失败，0成功
	 */
	public void setPayResult(Integer payResult) {
		this.payResult = payResult;
	}
	/**
	 * 获取：支付结果。1，失败，0成功
	 */
	public Integer getPayResult() {
		return payResult;
	}
	/**
	 * 设置：逻辑删除,1删除0正常
	 */
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	/**
	 * 获取：逻辑删除,1删除0正常
	 */
	public Integer getIsDel() {
		return isDel;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
}
