package com.admin.pack.controller;

import com.admin.pack.domain.ResultMap;
import com.admin.system.domain.DeptDO;
import com.admin.system.domain.UserDO;
import com.admin.system.service.DeptService;
import com.admin.system.service.UserService;
import com.admin.utils.SMS.SendShortMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

/**
 * 学校信息表
 * 
 * @author luojing
 * @email lg932740579@163.com
 * @date 2018-09-10 15:36:19
 */
 
@Controller
@RequestMapping("/pack/common")
public class CommonController {

	@Autowired
	private DeptService deptService;
	@Autowired
	private UserService userService;

	/**
	  * @Description: 查询学校
	  * @Author: luojing
	  * @Date: 2018/9/10 17:47
	  */
	@ResponseBody
	@RequestMapping("/listSchoolInfo")
	public List<DeptDO> listSchoolInfo(){
		Map<String, Object> map = new HashMap<>();
		map.put("parentId",0);
		map.put("isSchool",1);
		return deptService.list(map);
	}

	/**
	  * @Description: 根据学校查询用户
	  * @Author: luojing
	  * @Date: 2018/9/10 18:05
	  */
	@ResponseBody
	@RequestMapping("/listSchoolInfo/{deptId}")
	public List<UserDO> listUser(@PathVariable("deptId") Long deptId){
		//查询学校已经下级
		Map<String, Object> map = new HashMap<>();
		map.put("parentId",deptId);
		DeptDO dept = deptService.get(deptId);
		List<DeptDO> schList = deptService.list(map);
		schList.add(dept);
		List<UserDO> list = new ArrayList<UserDO>();
		for(DeptDO d:schList){
			Map<String,Object> userMap = new HashMap<>();
			userMap.put("deptId",d.getDeptId());
			List<UserDO> ul = userService.list(userMap);
			for(UserDO u : ul){
				list.add(u);
			}
		}
		return list;
	}
	/**
	 * 获取验证码接口
	 * @param deptId
	 * @return
	 * 2018年9月11日
	 * 作者：fengchase
	 */
	@ResponseBody
	@RequestMapping("/getVcode")
	public ResultMap getShortCode(HttpServletRequest request,@RequestParam String phone){
		try {
			int len=6;
			String vcode="";
			for(int i=0;i<len;i++) {
				Random rm=new Random(10);
				vcode=vcode+rm.nextInt();
			}
			SendShortMessage.sendMess(phone, vcode, "1", "3");
			return ResultMap.getSuccessJo();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ResultMap.getErrorJo();
	}
	@RequestMapping("/toWebPage")
	public String toWebPage(HttpServletRequest request){
		try {
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "pack/webpage/index";
	}
	

}
