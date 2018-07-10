package com.admin.wxapi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.admin.wxapi.domain.PayRecordDO;

/**
 * 支付记录
 * @author luojing
 * @email lg932740579@163.com
 * @date 2018-07-10 11:22:27
 */
@Mapper
public interface PayRecordDao {

	PayRecordDO get(Long payId);
	
	List<PayRecordDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(PayRecordDO qcPayRecord);
	
	int update(PayRecordDO qcPayRecord);
	
	int remove(Long pay_id);
	
	int batchRemove(Long[] payIds);
}
