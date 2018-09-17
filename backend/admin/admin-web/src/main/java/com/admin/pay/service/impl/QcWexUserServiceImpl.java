package com.admin.pay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.admin.pay.dao.QcWexUserDao;
import com.admin.pay.domain.QcWexUserDO;
import com.admin.pay.service.QcWexUserService;



@Service
public class QcWexUserServiceImpl implements QcWexUserService {
	@Autowired
	private QcWexUserDao qcWexUserDao;
	
	@Override
	public QcWexUserDO get(Integer id){
		return qcWexUserDao.get(id);
	}
	
	@Override
	public List<QcWexUserDO> list(Map<String, Object> map){
		return qcWexUserDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return qcWexUserDao.count(map);
	}
	
	@Override
	public int save(QcWexUserDO qcWexUser){
		qcWexUser.setLastTime(new Date());
		QcWexUserDO qd=qcWexUserDao.getByOpenId(qcWexUser.getOpenId());
		if(qd!=null) {
			qcWexUser.setId(qd.getId());
			return qcWexUserDao.update(qcWexUser);
		}
		return qcWexUserDao.save(qcWexUser);
	}
	
	@Override
	public int update(QcWexUserDO qcWexUser){
		return qcWexUserDao.update(qcWexUser);
	}
	
	@Override
	public int remove(Integer id){
		return qcWexUserDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return qcWexUserDao.batchRemove(ids);
	}

	@Override
	public QcWexUserDO getByOpenId(String openId) {
		// TODO 此处为方法主题
		return qcWexUserDao.getByOpenId(openId);
	}
	
}
