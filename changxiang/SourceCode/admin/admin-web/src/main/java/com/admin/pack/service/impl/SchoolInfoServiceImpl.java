package com.admin.pack.service.impl;

import com.admin.pack.dao.SchoolInfoDao;
import com.admin.pack.domain.SchoolInfoDO;
import com.admin.pack.service.SchoolInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class SchoolInfoServiceImpl implements SchoolInfoService {
	@Autowired
	private SchoolInfoDao schoolInfoDao;
	
	@Override
	public SchoolInfoDO get(Integer id){
		return schoolInfoDao.get(id);
	}
	
	@Override
	public List<SchoolInfoDO> list(Map<String, Object> map){
		return schoolInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return schoolInfoDao.count(map);
	}
	
	@Override
	public int save(SchoolInfoDO schoolInfo){
		return schoolInfoDao.save(schoolInfo);
	}
	
	@Override
	public int update(SchoolInfoDO schoolInfo){
		return schoolInfoDao.update(schoolInfo);
	}
	
	@Override
	public int remove(Integer id){
		return schoolInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return schoolInfoDao.batchRemove(ids);
	}
	
}
