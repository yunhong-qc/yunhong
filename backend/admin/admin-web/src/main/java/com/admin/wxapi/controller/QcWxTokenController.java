package com.admin.wxapi.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.wxapi.domain.QcWxTokenDO;
import com.admin.wxapi.service.QcWxTokenService;
import com.admin.common.utils.PageUtils;
import com.admin.common.utils.Query;
import com.admin.common.utils.R;

/**
 * 
 * 
 * @author tangjie
 * @email lg932740579@163.com
 * @date 2018-07-12 15:08:49
 */
 
@Controller
@RequestMapping("/wxapi/qcWxToken")
public class QcWxTokenController {
	@Autowired
	private QcWxTokenService qcWxTokenService;
	
	@GetMapping()
	@RequiresPermissions("wxapi:qcWxToken:qcWxToken")
	String QcWxToken(){
	    return "wxapi/qcWxToken/qcWxToken";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("wxapi:qcWxToken:qcWxToken")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<QcWxTokenDO> qcWxTokenList = qcWxTokenService.list(query);
		int total = qcWxTokenService.count(query);
		PageUtils pageUtils = new PageUtils(qcWxTokenList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("wxapi:qcWxToken:add")
	String add(){
	    return "wxapi/qcWxToken/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("wxapi:qcWxToken:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		QcWxTokenDO qcWxToken = qcWxTokenService.get(id);
		model.addAttribute("qcWxToken", qcWxToken);
	    return "wxapi/qcWxToken/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("wxapi:qcWxToken:add")
	public R save( QcWxTokenDO qcWxToken){
		if(qcWxTokenService.save(qcWxToken)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("wxapi:qcWxToken:edit")
	public R update( QcWxTokenDO qcWxToken){
		qcWxTokenService.update(qcWxToken);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("wxapi:qcWxToken:remove")
	public R remove( Integer id){
		if(qcWxTokenService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("wxapi:qcWxToken:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		qcWxTokenService.batchRemove(ids);
		return R.ok();
	}
	
}
