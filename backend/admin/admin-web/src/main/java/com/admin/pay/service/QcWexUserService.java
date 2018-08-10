package com.admin.pay.service;

import com.admin.pay.domain.QcWexUserDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author luojing
 * @email lg932740579@163.com
 * @date 2018-08-02 09:54:57
 */
public interface QcWexUserService {
	
	QcWexUserDO get(Integer id);
	
	List<QcWexUserDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(QcWexUserDO qcWexUser);
	
	int update(QcWexUserDO qcWexUser);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	QcWexUserDO getByOpenId(String openId);
}
