package com.admin.pay.service;

import com.admin.pay.domain.QcPayOrderDO;
import com.admin.utils.pay.ali.PayException;
import com.alipay.api.AlipayApiException;

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
	String addNewOrderAndSDKRSA(QcPayOrderDO order) throws AlipayApiException;
	QcPayOrderDO getPayOrder(QcPayOrderDO order) throws PayException;
	String getPayOrderANDSDKRSA(QcPayOrderDO order) throws PayException,AlipayApiException;
	void alipayResultCallBack(Map<String, String> map);
	
}
