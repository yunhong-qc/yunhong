package com.admin.pack.domain;

import io.swagger.models.auth.In;

import java.io.Serializable;
import java.util.Date;



/**
 * 学生信息表
 * 
 * @author luojing
 * @email lg932740579@163.com
 * @date 2018-09-09 19:52:19
 */
public class StudentInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键ID
	private Integer id;
	//学生姓名
	private String name;
	//学生身份证
	private String cardNo;
	//电话
	private String telephone;
	//学校ID
	private Integer schoolId;
	//寝室编号
	private String dormId;
	//是否安装宽带：0是，1否
	private Integer isWb;
	//是否办理成功：0已办理，1未办理
	private Integer isSuccess;
	//业务员编号
	private Integer userId;
	//录入时间
	private Date createTime;
	//是否支付：0未支付，1支付
	private Integer isPay;
	//宽带是否办理成功：0已办理，1未办理，-1办理失败
	private Integer wbIsSuccess;
	//是否签订宽带协议：0已签，1未签
	private Integer isProtocol;
	//赠品类型
	private Integer zenpType;

	
	public Integer getZenType() {
		return zenpType;
	}
	public void setZenpType(Integer zenpType) {
		this.zenpType = zenpType;
	}
	/**
	 * 设置：主键ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键ID
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：学生姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：学生姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：学生身份证
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	/**
	 * 获取：学生身份证
	 */
	public String getCardNo() {
		return cardNo;
	}
	/**
	 * 设置：电话
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * 获取：电话
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * 设置：学校ID
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	/**
	 * 获取：学校ID
	 */
	public Integer getSchoolId() {
		return schoolId;
	}
	/**
	 * 设置：寝室编号
	 */
	public void setDormId(String dormId) {
		this.dormId = dormId;
	}
	/**
	 * 获取：寝室编号
	 */
	public String getDormId() {
		return dormId;
	}
	/**
	 * 设置：是否安装宽带：0是，1否
	 */
	public void setIsWb(Integer isWb) {
		this.isWb = isWb;
	}
	/**
	 * 获取：是否安装宽带：0是，1否
	 */
	public Integer getIsWb() {
		return isWb;
	}
	/**
	 * 设置：是否办理成功：0成功，1初始状态，2失败
	 */
	public void setIsSuccess(Integer isSuccess) {
		this.isSuccess = isSuccess;
	}
	/**
	 * 获取：是否办理成功：0成功，1初始状态，2失败
	 */
	public Integer getIsSuccess() {
		return isSuccess;
	}
	/**
	 * 设置：业务员编号
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：业务员编号
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 设置：录入时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：录入时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
	}

	public Integer getIsPay() {
		return isPay;
	}

	public Integer getWbIsSuccess(){
		return wbIsSuccess;
	}
	public void setWbIsSuccess(Integer wbIsSuccess){
		this.wbIsSuccess = wbIsSuccess;
	}

	public Integer getIsProtocol(){
		return isProtocol;
	}
	public void setIsProtocol(Integer isProtocol){
		this.isProtocol = isProtocol;
	}
}
