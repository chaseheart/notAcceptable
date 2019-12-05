package com.isolver.interfaces;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.isolver.common.util.Result;

@RequestMapping("/weixin")
public interface UserApi {

	/**
	 * 获取用户详细信息
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public Result<Object> findUserDetail(Long userId);
	
	/**
	 * 通过部门和角色查找用户（复数）
	 * 
	 * @param departId
	 * @return
	 */
	@RequestMapping(value = "/findUserByDepartAndRole", method = RequestMethod.POST)
	public Result<Object> findUserByDepartAndRole(Long departId,Long userId, Long flowType);
}
