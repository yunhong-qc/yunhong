package com.admin.wxapi.service;

import java.util.List;
import java.util.Map;

import com.admin.wxapi.domain.PayRecordDO;

/**
 * 支付记录
 * 
 * @author luojing
 * @email lg932740579@163.com
 * @date 2018-07-10 11:22:27
 */
public interface PayRecordService {
	
	PayRecordDO get(Long payId);
	
	List<PayRecordDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PayRecordDO qcPayRecord);
	
	int update(PayRecordDO qcPayRecord);
	
	int remove(Long payId);
	
	int batchRemove(Long[] payIds);
}
