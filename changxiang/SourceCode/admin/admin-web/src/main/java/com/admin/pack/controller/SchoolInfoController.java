package com.admin.pack.controller;

import com.admin.common.utils.PageUtils;
import com.admin.common.utils.Query;
import com.admin.common.utils.R;
import com.admin.pack.domain.SchoolInfoDO;
import com.admin.pack.service.SchoolInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 学校信息表
 * 
 * @author luojing
 * @email lg932740579@163.com
 * @date 2018-09-10 15:36:19
 */
 
@Controller
@RequestMapping("/pack/schoolInfo")
public class SchoolInfoController {
	@Autowired
	private SchoolInfoService schoolInfoService;
	
	@GetMapping()
	@RequiresPermissions("pack:schoolInfo:schoolInfo")
	String SchoolInfo(){
	    return "pack/schoolInfo/schoolInfo";
	}

	@ResponseBody
	@GetMapping("/listSchoolInfo")
	public List<SchoolInfoDO> listSchoolInfo(){
		return schoolInfoService.list(null);
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("pack:schoolInfo:schoolInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SchoolInfoDO> schoolInfoList = schoolInfoService.list(query);
		int total = schoolInfoService.count(query);
		PageUtils pageUtils = new PageUtils(schoolInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("pack:schoolInfo:add")
	String add(){
	    return "pack/schoolInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("pack:schoolInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		SchoolInfoDO schoolInfo = schoolInfoService.get(id);
		model.addAttribute("schoolInfo", schoolInfo);
	    return "pack/schoolInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("pack:schoolInfo:add")
	public R save( SchoolInfoDO schoolInfo){
		if(schoolInfoService.save(schoolInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("pack:schoolInfo:edit")
	public R update( SchoolInfoDO schoolInfo){
		schoolInfoService.update(schoolInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("pack:schoolInfo:remove")
	public R remove( Integer id){
		if(schoolInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("pack:schoolInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		schoolInfoService.batchRemove(ids);
		return R.ok();
	}
	
}
