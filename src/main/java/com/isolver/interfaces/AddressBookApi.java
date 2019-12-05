package com.isolver.interfaces;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.isolver.common.util.Result;

@RequestMapping(value = "/weixin/addressbook")
public interface AddressBookApi {

	/**
	 * 检索所用用户
	 * @return
	 */
	@RequestMapping(value = "/searchUser", method = RequestMethod.GET)
	public Result<Object> searchUser ();
	
	/**
	 * 根据用户名模糊检索用户
	 * @param username 用户名
	 * @return
	 */
	@RequestMapping(value = "/searchUserLike", method = RequestMethod.POST)
	public Result<Object> searchUserLike (String username);
}
