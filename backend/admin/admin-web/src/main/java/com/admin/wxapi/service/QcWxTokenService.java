package com.admin.wxapi.service;

import com.admin.wxapi.domain.QcWxTokenDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author tangjie
 * @email lg932740579@163.com
 * @date 2018-07-12 15:08:49
 */
public interface QcWxTokenService {
	
	QcWxTokenDO get(Integer id);
	
	List<QcWxTokenDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(QcWxTokenDO qcWxToken);
	
	int update(QcWxTokenDO qcWxToken);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
