package com.admin.pack.service.impl;

import com.admin.common.utils.PageUtils;
import com.admin.common.utils.Query;
import com.admin.system.dao.UserDao;
import com.admin.system.dao.UserRoleDao;
import com.admin.system.domain.UserDO;
import com.admin.system.domain.UserRoleDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.admin.pack.dao.StudentInfoDao;
import com.admin.pack.domain.StudentInfoDO;
import com.admin.pack.service.StudentInfoService;

@Service
public class StudentInfoServiceImpl implements StudentInfoService {
	@Autowired
	private StudentInfoDao studentInfoDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private UserDao userDao;
	
	@Override
	public StudentInfoDO get(Integer id){
		return studentInfoDao.get(id);
	}

	@Override
	public PageUtils selectPage(Map<String, Object> map, UserDO user) {
		Query query = new Query(map);
		//查询列表数据
		//验证用户角色ID
		Long roleId = userRoleDao.listRoleId(user.getUserId()).get(0);
		/**
		 * 1	超级用户
		 * 60	业务组-普通用户
		 * 61	移动组-存费用户
		 * 62	普通管理员
		 * 63	移动组-宽带用户
		 * 64	业务组-组长
		 */
		List<Map<String,Object>> studentInfoList = null;
		int total = 0;
		if(roleId == 64){
			//查询所有组员
			Map<String,Object> userParam = new HashMap<>();
			userParam.put("deptId",user.getDeptId());
			List<UserDO> userList = userDao.list(userParam);
			StringBuilder sb = new StringBuilder();
			List<String> list = new ArrayList<>();
			for(UserDO u : userList){
				list.add(u.getUserId());
			}
			query.put("userIds",list);
			studentInfoList = studentInfoDao.list(query);
			total = studentInfoDao.count(query);
			return new PageUtils(studentInfoList, total);
		}else if(roleId == 60){
			query.put("userId",user.getUserId());
		}
		studentInfoList = studentInfoDao.list(query);
		total = studentInfoDao.count(query);
		return new PageUtils(studentInfoList, total);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentInfoDao.count(map);
	}
	
	@Override
	public int save(StudentInfoDO studentInfo){
		return studentInfoDao.save(studentInfo);
	}
	
	@Override
	public int update(StudentInfoDO studentInfo){
		return studentInfoDao.update(studentInfo);
	}
	
	@Override
	public int remove(Integer id){
		return studentInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return studentInfoDao.batchRemove(ids);
	}
	
}
