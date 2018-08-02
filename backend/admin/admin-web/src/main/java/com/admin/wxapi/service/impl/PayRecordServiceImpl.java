package com.admin.wxapi.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.wxapi.dao.PayRecordDao;
import com.admin.wxapi.domain.PayRecordDO;
import com.admin.wxapi.service.PayRecordService;



@Service
public class PayRecordServiceImpl implements PayRecordService {
	@Autowired
	private PayRecordDao qcPayRecordDao;
	
	@Override
	public PayRecordDO get(Long payId){
		return qcPayRecordDao.get(payId);
	}
	
	@Override
	public List<PayRecordDO> list(Map<String, Object> map){
		return qcPayRecordDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return qcPayRecordDao.count(map);
	}
	
	@Override
	public int save(PayRecordDO qcPayRecord){
		qcPayRecord.setIsDel(0);
		qcPayRecord.setCreateTime(new Date());
		return qcPayRecordDao.save(qcPayRecord);
	}
	
	@Override
	public int update(PayRecordDO qcPayRecord){
		return qcPayRecordDao.update(qcPayRecord);
	}
	
	@Override
	public int remove(Long payId){
		return qcPayRecordDao.remove(payId);
	}
	
	@Override
	public int batchRemove(Long[] payIds){
		return qcPayRecordDao.batchRemove(payIds);
	}
	
}
