package com.admin.pay.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author luojing
 * @email lg932740579@163.com
 * @date 2018-07-18 10:12:15
 */
public class QcPayOrderDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer orderId;
	
	//这两个参数是微信网页授权的参数，用于获取openId
	private String wxCode;
	private String wxState;
	//订单号
	private String orderNo;
	//金额
	private String payPrice;
	//支付人，即创建人
	private String payUser;
	
	//支付人，支付类型
	private Integer payType;
	//手机号
	private Integer payPhone;
	
	//订单状态。0L:待支付，1：已支付，2，完成,3。失效
	private Integer orderState;
	//逻辑删除，1：删除，0：正常
	private Integer isDel;
	//
	private Date createTime;
	//
	private Date modTime;
	//备注
	private String orderRemark;
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	/**
	 * 设置：
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：
	 */
	public Integer getOrderId() {
		return orderId;
	}
	/**
	 * 设置：订单号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 获取：订单号
	 */
	public String getOrderNo() {
		return orderNo;
	}
	/**
	 * 设置：金额
	 */
	public void setPayPrice(String payPrice) {
		this.payPrice = payPrice;
	}
	/**
	 * 获取：金额
	 */
	public String getPayPrice() {
		return payPrice;
	}
	/**
	 * 设置：支付人，即创建人
	 */
	public void setPayUser(String payUser) {
		this.payUser = payUser;
	}
	/**
	 * 获取：支付人，即创建人
	 */
	public String getPayUser() {
		return payUser;
	}
	/**
	 * 设置：订单状态。0L:待支付，1：已支付，2，完成,3。失效
	 */
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	/**
	 * 获取：订单状态。0L:待支付，1：已支付，2，完成,3。失效
	 */
	public Integer getOrderState() {
		return orderState;
	}
	/**
	 * 设置：逻辑删除，1：删除，0：正常
	 */
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	/**
	 * 获取：逻辑删除，1：删除，0：正常
	 */
	public Integer getIsDel() {
		return isDel;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：
	 */
	public void setModTime(Date modTime) {
		this.modTime = modTime;
	}
	/**
	 * 获取：
	 */
	public Date getModTime() {
		return modTime;
	}
	/**
	 * 设置：备注
	 */
	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}
	/**
	 * 获取：备注
	 */
	public String getOrderRemark() {
		return orderRemark;
	}
	public String getWxCode() {
		return wxCode;
	}
	public void setWxCode(String wxCode) {
		this.wxCode = wxCode;
	}
	public String getWxState() {
		return wxState;
	}
	public void setWxState(String wxState) {
		this.wxState = wxState;
	}
	
	
}
