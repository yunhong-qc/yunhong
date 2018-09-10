package com.admin.pack.service;

import com.admin.pack.domain.StudentInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 学生信息表
 * 
 * @author luojing
 * @email lg932740579@163.com
 * @date 2018-09-09 19:52:19
 */
public interface StudentInfoService {
	
	StudentInfoDO get(Integer id);

	List<Map<String,Object>> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentInfoDO studentInfo);
	
	int update(StudentInfoDO studentInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
