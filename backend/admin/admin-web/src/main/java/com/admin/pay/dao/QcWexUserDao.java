package com.admin.pay.dao;

import com.admin.pay.domain.QcWexUserDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author luojing
 * @email lg932740579@163.com
 * @date 2018-08-02 09:54:57
 */
@Mapper
public interface QcWexUserDao {

	QcWexUserDO get(Integer id);
	QcWexUserDO getByOpenId(String openId);
	
	List<QcWexUserDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(QcWexUserDO qcWexUser);
	
	int update(QcWexUserDO qcWexUser);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
