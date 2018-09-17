package com.admin.wxapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.admin.wxapi.dao.QcWxTokenDao;
import com.admin.wxapi.domain.QcWxTokenDO;
import com.admin.wxapi.service.QcWxTokenService;



@Service
public class QcWxTokenServiceImpl implements QcWxTokenService {
	@Autowired
	private QcWxTokenDao qcWxTokenDao;
	
	@Override
	public QcWxTokenDO get(Integer id){
		return qcWxTokenDao.get(id);
	}
	
	@Override
	public List<QcWxTokenDO> list(Map<String, Object> map){
		return qcWxTokenDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return qcWxTokenDao.count(map);
	}
	
	@Override
	public int save(QcWxTokenDO qcWxToken){
		return qcWxTokenDao.save(qcWxToken);
	}
	
	@Override
	public int update(QcWxTokenDO qcWxToken){
		return qcWxTokenDao.update(qcWxToken);
	}
	
	@Override
	public int remove(Integer id){
		return qcWxTokenDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return qcWxTokenDao.batchRemove(ids);
	}
	
}
