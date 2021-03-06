package com.admin.system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.admin.system.domain.RoleDO;

@Service
public interface RoleService {

	RoleDO get(Long id);

	List<RoleDO> list();

	int save(RoleDO role);

	int update(RoleDO role);

	int remove(Long id);

	List<RoleDO> list(String userId);

	int batchremove(Long[] ids);
}
