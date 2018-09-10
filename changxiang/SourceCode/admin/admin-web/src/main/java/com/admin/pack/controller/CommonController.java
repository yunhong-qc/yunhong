package com.admin.pack.controller;

import com.admin.system.domain.DeptDO;
import com.admin.system.domain.UserDO;
import com.admin.system.service.DeptService;
import com.admin.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
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
	@GetMapping("/listSchoolInfo")
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
	@GetMapping("/listSchoolInfo/{deptId}")
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

}
