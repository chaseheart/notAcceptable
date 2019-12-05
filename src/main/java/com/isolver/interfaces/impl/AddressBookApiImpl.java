package com.isolver.interfaces.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.isolver.common.constant.SysStatusCodeConst;
import com.isolver.common.util.Result;
import com.isolver.interfaces.AddressBookApi;
import com.isolver.service.UserService;

@RestController
public class AddressBookApiImpl implements AddressBookApi {

	@Autowired
	private UserService userService;

	/**
	 * 检索所用用户
	 * 
	 * @return
	 */
	@Override
	public Result<Object> searchUser() {
		return new Result<>(SysStatusCodeConst.SUCCESS, userService.findAll());
	}

	/**
	 * 根据用户名模糊检索用户
	 * 
	 * @param username 用户名
	 * @return
	 */
	@Override
	public Result<Object> searchUserLike(String username) {
		return new Result<>(SysStatusCodeConst.SUCCESS, userService.findUserLike(username));
	}
}
