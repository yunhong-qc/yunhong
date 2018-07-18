package com.admin.pay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.admin.pay.dao.QcPayOrderDao;
import com.admin.pay.domain.QcPayOrderDO;
import com.admin.pay.service.QcPayOrderService;
import com.admin.utils.DateUtils;
import com.admin.utils.FileLog;
import com.admin.utils.ali.pay.PayException;
import com.admin.wxapi.dao.PayRecordDao;
import com.admin.wxapi.domain.PayRecordDO;



@Service
public class QcPayOrderServiceImpl implements QcPayOrderService {
	@Autowired
	private QcPayOrderDao qcPayOrderDao;
	@Autowired
	private PayRecordDao payRecordDao;
	
	@Override
	public QcPayOrderDO get(Integer orderId){
		return qcPayOrderDao.get(orderId);
	}
	
	@Override
	public List<QcPayOrderDO> list(Map<String, Object> map){
		return qcPayOrderDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return qcPayOrderDao.count(map);
	}
	
	@Override
	public int save(QcPayOrderDO qcPayOrder){
		return qcPayOrderDao.save(qcPayOrder);
	}
	
	@Override
	public int update(QcPayOrderDO qcPayOrder){
		return qcPayOrderDao.update(qcPayOrder);
	}
	
	@Override
	public int remove(Integer orderId){
		return qcPayOrderDao.remove(orderId);
	}
	
	@Override
	public int batchRemove(Integer[] orderIds){
		return qcPayOrderDao.batchRemove(orderIds);
	}

	/**
	 * 添加新订单，并返回订单信息
	 */
	@Override
	@Transactional
	public QcPayOrderDO addNewOrder(QcPayOrderDO order) {
		// TODO 此处为方法主题
		order.setIsDel(0);
		order.setCreateTime(new Date());
		order.setOrderState(0);//初始
		order.setOrderNo(DateUtils.getOrderNo());
		//获取订单号
		if(qcPayOrderDao.save(order)>0) {
			return order;
		}
		return null;
	}

	/**
	 * 获取支付订单，依据订单号等
	 * @throws PayException 
	 */
	@Override
	public QcPayOrderDO getPayOrder(QcPayOrderDO order) throws PayException {
		// TODO 此处为方法主题
		QcPayOrderDO resOrder=qcPayOrderDao.getOrderByOrderNumber(order.getOrderNo());
		if(resOrder==null) {
			return null;
		}
		if(DateUtils.compareTwoTimeDifferMuch(resOrder.getCreateTime(), new Date(), 3600)) {
			//未失效
			return resOrder;
		}else {
			FileLog.errorLog("订单已超时："+resOrder.getOrderNo());
			throw new PayException("订单已超时。");
		}
	}

	/**
	 * 支付宝的回调方法
	 */
	@Override
	public void alipayResultCallBack(Map<String, String> params) {
		// TODO 此处为方法主题
		Integer ordersId = Integer.parseInt(params.get("out_trade_no"));
		QcPayOrderDO order=qcPayOrderDao.get(ordersId);
		
		String tradeStatus = params.get("trade_status");
		PayRecordDO prd = new PayRecordDO();
		prd.setPayMoney(order.getPayPrice());
		prd.setCreateTime(order.getCreateTime());
		prd.setIsDel(0);
		prd.setPayType(1);
		// orders.setOrderState("1"); // 订单状态位已支付
		switch (tradeStatus) // 判断交易结果
		{
		case "TRADE_FINISHED": // 完成
			order.setOrderState(0);
			prd.setPayResult(0);
			break;
		case "TRADE_SUCCESS": // 完成
			prd.setPayResult(0);
			order.setOrderState(0);
			break;
		case "WAIT_BUYER_PAY": // 待支付
			prd.setPayResult(1);
			order.setOrderState(1);
			break;
		case "TRADE_CLOSED": // 交易关闭
			order.setOrderState(2);
			prd.setPayResult(2);
			break;
		default:
			break;
		}
		payRecordDao.save(prd);
		qcPayOrderDao.update(order);
	}
	
}
