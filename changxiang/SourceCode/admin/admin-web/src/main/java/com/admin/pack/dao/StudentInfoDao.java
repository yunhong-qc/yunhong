package com.admin.pack.dao;

import com.admin.pack.domain.StudentInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 学生信息表
 * @author luojing
 * @email lg932740579@163.com
 * @date 2018-09-09 19:52:19
 */
@Mapper
public interface StudentInfoDao {

	StudentInfoDO get(Integer id);
	
	List<StudentInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentInfoDO studentInfo);
	
	int update(StudentInfoDO studentInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
