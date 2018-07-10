package com.admin.wxapi.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.common.utils.PageUtils;
import com.admin.common.utils.Query;
import com.admin.common.utils.R;
import com.admin.wxapi.domain.PayRecordDO;
import com.admin.wxapi.service.PayRecordService;

/**
 * 支付记录
 * 
 * @author luojing
 * @email lg932740579@163.com
 * @date 2018-07-10 11:22:27
 */
 
@Controller
@RequestMapping("/wx/payRecord")
public class PayRecordController {
	@Autowired
	private PayRecordService payRecordService;
	
	@GetMapping()
	@RequiresPermissions("wx:payRecord:payRecord")
	String QcPayRecord(){
	    return "wx/payRecord/payRecord";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("wx:payRecord:payRecord")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PayRecordDO> qcPayRecordList = payRecordService.list(query);
		int total = payRecordService.count(query);
		PageUtils pageUtils = new PageUtils(qcPayRecordList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("wx:payRecord:add")
	String add(){
	    return "/wx/payRecord/add";
	}

	@GetMapping("/edit/{payId}")
	@RequiresPermissions("wx:payRecord:edit")
	String edit(@PathVariable("payId") Long payId,Model model){
		PayRecordDO payRecord = payRecordService.get(payId);
		model.addAttribute("payRecord", payRecord);
	    return "/wx/payRecord/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("wx:payRecord:add")
	public R save( PayRecordDO qcPayRecord){
		if(payRecordService.save(qcPayRecord)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("wx:payRecord:edit")
	public R update( PayRecordDO qcPayRecord){
		payRecordService.update(qcPayRecord);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("wx:payRecord:remove")
	public R remove( Long payId){
		if(payRecordService.remove(payId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("wx:payRecord:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] payIds){
		payRecordService.batchRemove(payIds);
		return R.ok();
	}
	
}
