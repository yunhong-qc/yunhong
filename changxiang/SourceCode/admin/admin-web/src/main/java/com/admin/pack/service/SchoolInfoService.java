package com.admin.pack.service;

import com.admin.pack.domain.SchoolInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 学校信息表
 * 
 * @author luojing
 * @email lg932740579@163.com
 * @date 2018-09-10 15:36:19
 */
public interface SchoolInfoService {
	
	SchoolInfoDO get(Integer id);
	
	List<SchoolInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SchoolInfoDO schoolInfo);
	
	int update(SchoolInfoDO schoolInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
