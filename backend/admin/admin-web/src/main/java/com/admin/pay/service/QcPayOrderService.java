package com.admin.pay.service;

import com.admin.pay.domain.QcPayOrderDO;
import com.admin.utils.ali.pay.PayException;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author luojing
 * @email lg932740579@163.com
 * @date 2018-07-17 16:57:43
 */
public interface QcPayOrderService {
	
	QcPayOrderDO get(Integer orderId);
	
	List<QcPayOrderDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(QcPayOrderDO qcPayOrder);
	
	int update(QcPayOrderDO qcPayOrder);
	
	int remove(Integer orderId);
	
	int batchRemove(Integer[] orderIds);
	/**
	 * 增加新订单
	 * @param order
	 * @return
	 * 2018年7月17日
	 * 作者：fengchase
	 */
	QcPayOrderDO addNewOrder(QcPayOrderDO order);
	QcPayOrderDO getPayOrder(QcPayOrderDO order) throws PayException;
	void alipayResultCallBack(Map<String, String> map);
	
}
