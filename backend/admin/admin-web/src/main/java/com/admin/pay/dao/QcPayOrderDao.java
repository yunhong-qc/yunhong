package com.admin.pay.dao;

import com.admin.pay.domain.QcPayOrderDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author luojing
 * @email lg932740579@163.com
 * @date 2018-07-17 16:57:43
 */
@Mapper
public interface QcPayOrderDao {

	QcPayOrderDO get(Integer orderId);
	QcPayOrderDO getOrderByOrderNumber(String orderNo);
	
	List<QcPayOrderDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(QcPayOrderDO qcPayOrder);
	
	int update(QcPayOrderDO qcPayOrder);
	
	int remove(Integer order_id);
	
	int batchRemove(Integer[] orderIds);
	List<QcPayOrderDO> getOrderBy(QcPayOrderDO order);
	
}
