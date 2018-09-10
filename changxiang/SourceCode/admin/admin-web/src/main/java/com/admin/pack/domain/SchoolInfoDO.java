package com.admin.pack.domain;

import java.io.Serializable;



/**
 * 学校信息表
 * 
 * @author luojing
 * @email lg932740579@163.com
 * @date 2018-09-10 15:36:19
 */
public class SchoolInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键ID
	private Integer id;
	//学校名
	private String stuName;
	//学校地址
	private String stuAddress;
	//备注
	private String remark;

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
	 * 设置：学校名
	 */
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	/**
	 * 获取：学校名
	 */
	public String getStuName() {
		return stuName;
	}
	/**
	 * 设置：学校地址
	 */
	public void setStuAddress(String stuAddress) {
		this.stuAddress = stuAddress;
	}
	/**
	 * 获取：学校地址
	 */
	public String getStuAddress() {
		return stuAddress;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
}
