package com.admin.common.controller;

import org.springframework.stereotype.Controller;

import com.admin.system.domain.UserDO;
import com.admin.utils.ShiroUtils;

@Controller
public class BaseController {
	public UserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
}