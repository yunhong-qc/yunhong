package com.admin.pack.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.admin.pack.domain.ResultMap;
import com.admin.pack.domain.StudentInfoDO;
import com.admin.pack.service.StudentInfoService;
import com.admin.utils.SMS.Constants;
import com.admin.utils.strv.StringUtils;
import com.admin.common.utils.PageUtils;
import com.admin.common.utils.Query;
import com.admin.common.utils.R;

/**
 * 学生信息表
 * 
 * @author luojing
 * @email lg932740579@163.com
 * @date 2018-09-09 19:52:19
 */
 
@Controller
@RequestMapping("/pack/studentInfo")
public class StudentInfoController {
	@Autowired
	private StudentInfoService studentInfoService;
	
	@GetMapping()
	@RequiresPermissions("pack:studentInfo:studentInfo")
	String StudentInfo(){
	    return "pack/studentInfo/studentInfo";
	}

	/**
	  * @Description: 业务员查看页面
	  * @Author: luojing
	  * @Date: 2018/9/9 21:13
	  */
	@GetMapping("/payRecord")
	@RequiresPermissions("pack:studentInfo:studentInfo")
	String payRecord(){
		return "pack/studentInfo/payRecord";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("pack:studentInfo:studentInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<Map<String,Object>> studentInfoList = studentInfoService.list(query);
		int total = studentInfoService.count(query);
		PageUtils pageUtils = new PageUtils(studentInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("pack:studentInfo:add")
	String add(){
	    return "pack/studentInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("pack:studentInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		StudentInfoDO studentInfo = studentInfoService.get(id);
		model.addAttribute("studentInfo", studentInfo);
	    return "pack/studentInfo/edit";
	}

	@GetMapping("/payEdit/{id}")
	@RequiresPermissions("pack:studentInfo:edit")
	String payEdit(@PathVariable("id") Integer id,Model model){
		StudentInfoDO studentInfo = studentInfoService.get(id);
		model.addAttribute("studentInfo", studentInfo);
		return "pack/studentInfo/payEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	public ResultMap save( StudentInfoDO studentInfo,HttpServletRequest request,String validCode,String ttcode){
		HttpSession session= request.getSession();
		String stcode=(String) session.getAttribute("stcode");
		System.out.println(stcode+"--"+ttcode);
		if(StringUtils.isNullString(ttcode)) {
			return ResultMap.getErrorJo("请拖动滑块验证!");
		}
		if(!ttcode.equals(stcode)) {
			return ResultMap.getErrorJo("请拖动滑块验证!");
		}
		String code=(String) session.getAttribute(Constants.CODEKEY);
		System.out.println(validCode+"--"+code);
		if(StringUtils.isNullString(validCode)) {
			return ResultMap.getErrorJo("验证码错误!");
		}
		if(Constants.ISTEST|| code==validCode || validCode.equals(code)) {
			//相同
			studentInfo.setIsSuccess(1);
			studentInfo.setIsPay(1);
			studentInfo.setCreateTime(new Date());
			if(studentInfoService.save(studentInfo)>0){
				return ResultMap.getSuccessJo();
			}
		}else {
			return ResultMap.getErrorJo("验证码错误!");
		}
		return ResultMap.getErrorJo();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("pack:studentInfo:edit")
	public R update( StudentInfoDO studentInfo){
		studentInfoService.update(studentInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("pack:studentInfo:remove")
	public R remove( Integer id){
		if(studentInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("pack:studentInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		studentInfoService.batchRemove(ids);
		return R.ok();
	}
	
}
