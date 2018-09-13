package com.admin.pack.controller;

import com.admin.pack.domain.ResultMap;
import com.admin.pack.domain.ResultUser;
import com.admin.system.domain.DeptDO;
import com.admin.system.domain.UserDO;
import com.admin.system.service.DeptService;
import com.admin.system.service.UserService;
import com.admin.utils.SMS.Constants;
import com.admin.utils.SMS.SendShortMessage;
import com.admin.utils.strv.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
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
	public ResultMap listSchoolInfo(){
		ResultMap rm=new ResultMap();
		try {

			Map<String, Object> map = new HashMap<>();
			map.put("parentId",0);
			map.put("isSchool",1);
			rm.setCode("000000");
			rm.setData(deptService.list(map)); 
			return rm;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ResultMap.getErrorJo();
	}

	/**
	  * @Description: 根据学校查询用户
	  * @Author: luojing
	  * @Date: 2018/9/10 18:05
	  */
	@ResponseBody
	@RequestMapping("/listSchoolInfo/{deptId}")
	public ResultMap listUser(@PathVariable("deptId") Long deptId){
		ResultMap rm=new ResultMap();
		try {
			//查询学校已经下级
			Map<String, Object> map = new HashMap<>();
			map.put("parentId",deptId);
			DeptDO dept = deptService.get(deptId);
			List<DeptDO> schList = deptService.list(map);
			schList.add(dept);
			List<ResultUser> list = new ArrayList<ResultUser>();
			for(DeptDO d:schList){
				Map<String,Object> userMap = new HashMap<>();
				userMap.put("deptId",d.getDeptId());
				List<UserDO> ul = userService.list(userMap);
				for(UserDO u : ul){
					ResultUser rs=new ResultUser();
					rs.setName(u.getName());
					rs.setUserId(u.getUserId());
					rs.setUsername(u.getUsername());
					list.add(rs);
				}
			}
			rm.setCode("000000");
			rm.setData(list);
			return rm;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ResultMap.getErrorJo();
		
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
	public ResultMap getShortCode(HttpServletRequest request,@RequestParam String phone,@RequestParam String ttcode){
		ResultMap rm=new ResultMap();
		try {
			HttpSession session= request.getSession();
			Long newTime=new Date().getTime();
			try {
				String stcode=(String) session.getAttribute("stcode");
				System.out.println(stcode+"--"+ttcode);
				if(StringUtils.isNullString(ttcode)) {
					return ResultMap.getErrorJo("请拖动滑块验证!");
				}
				if(!ttcode.equals(stcode)) {
					return ResultMap.getErrorJo("请拖动滑块验证!");
				}
				String lastTime=(String) session.getAttribute(Constants.CODETIMEKEY);
				if(lastTime!=null && lastTime!="") {
					long cha=(newTime-Long.parseLong(lastTime))/1000;
					if(cha<Constants.VCODTIMEOUT) {
						//未超时，不予获取
						rm.setCode("000002");
						rm.setMsg("请"+cha+"秒后再试");
						rm.setData(cha);
						return rm;
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			String vcode="";
			for(int i=0;i<Constants.VCODELENGTH;i++) {
				Random rms=new Random();
				vcode=vcode+rms.nextInt(10);
			}
			session.setAttribute(Constants.CODEKEY, vcode);
			session.setAttribute(Constants.CODETIMEKEY, new Date().getTime()+"");
			System.out.println(phone+"--"+vcode);
			if(!Constants.ISTEST) {
				HashMap<String, Object> result=SendShortMessage.sendMess(phone, vcode, "330663", "3");
				if("000000".equals(result.get("statusCode"))){
					//正常返回输出data包体信息（map）
					return ResultMap.getSuccessJo();
				}else{
					//异常返回输出错误码和错误信息
					logger.debug("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
					rm.setCode("000001");
					rm.setMsg(result.get("statusMsg").toString());
					return rm;
				}
			}
			return ResultMap.getSuccessJo();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ResultMap.getErrorJo();
	}
	@RequestMapping("/ch_index")
	public String toWebPage(HttpServletRequest request){
		try {
			String tcode="";
			for(int i=0;i<Constants.VCODELENGTH;i++) {
				Random rms=new Random();
				tcode=tcode+rms.nextInt(10);
			}
			request.setAttribute("tcode", tcode);
			request.getSession().setAttribute("stcode", tcode);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "pack/webpage/index";
	}
	

}
