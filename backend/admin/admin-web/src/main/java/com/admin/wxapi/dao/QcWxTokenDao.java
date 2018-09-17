package com.admin.wxapi.dao;

import com.admin.wxapi.domain.QcWxTokenDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author tangjie
 * @email lg932740579@163.com
 * @date 2018-07-12 15:08:49
 */
@Mapper
public interface QcWxTokenDao {

	QcWxTokenDO get(Integer id);
	QcWxTokenDO getByAppId(String appId);
	
	List<QcWxTokenDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(QcWxTokenDO qcWxToken);
	
	int update(QcWxTokenDO qcWxToken);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
