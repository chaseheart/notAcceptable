package com.isolver.interfaces.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.isolver.common.constant.SysStatusCodeConst;
import com.isolver.common.util.Result;
import com.isolver.dto.SysUserDto;
import com.isolver.entity.User;
import com.isolver.interfaces.UserApi;
import com.isolver.service.UserService;

@RestController
public class UserApiImpl implements UserApi {
	
	@Autowired
	private UserService userService;

	/**
	 * 获取用户详细信息
	 * @param userId
	 * @return
	 */
	@Override
	public Result<Object> findUserDetail(Long userId) {
		return new Result<Object>(SysStatusCodeConst.SUCCESS, userService.findAllById(userId));
	}

	/**
	 * 通过部门和角色查找用户（复数）
	 * 
	 * @param departId
	 * @return
	 */
	@Override
	public Result<Object> findUserByDepartAndRole(Long departId, Long userId, Long flowType) {
		User user = userService.getUserById(userId);
		List<SysUserDto> userDto = userService.findUserByDepartAndRole(departId, user.getRole(), flowType);
		return new Result<Object>(SysStatusCodeConst.SUCCESS, userDto);
	}
	
}
