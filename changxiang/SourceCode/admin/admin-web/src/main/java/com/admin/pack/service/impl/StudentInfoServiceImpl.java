package com.admin.pack.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.admin.pack.dao.StudentInfoDao;
import com.admin.pack.domain.StudentInfoDO;
import com.admin.pack.service.StudentInfoService;



@Service
public class StudentInfoServiceImpl implements StudentInfoService {
	@Autowired
	private StudentInfoDao studentInfoDao;
	
	@Override
	public StudentInfoDO get(Integer id){
		return studentInfoDao.get(id);
	}
	
	@Override
	public List<StudentInfoDO> list(Map<String, Object> map){
		return studentInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentInfoDao.count(map);
	}
	
	@Override
	public int save(StudentInfoDO studentInfo){
		return studentInfoDao.save(studentInfo);
	}
	
	@Override
	public int update(StudentInfoDO studentInfo){
		return studentInfoDao.update(studentInfo);
	}
	
	@Override
	public int remove(Integer id){
		return studentInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return studentInfoDao.batchRemove(ids);
	}
	
}
