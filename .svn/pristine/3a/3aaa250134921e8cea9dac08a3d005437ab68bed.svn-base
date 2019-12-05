/**
 * 
 */
package com.isolver.common.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.isolver.common.util.UserWithSalt;

/**
 * springsecurity 获取工具
 * @author 陈昶宇
 * @createDate 2019/06/21 13:53:14
 * 
 */
@Component
public class AuthenticationFacade {
	private static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	/**
	 * 获取用户名
	 * @return
	 */
	public static String getUsername() {
		return getAuthentication().getName();
	}
	
	/**
	 * 获取用户id
	 * @return
	 */
	public static Long getUserId() {
		UserWithSalt user = (UserWithSalt)getAuthentication().getPrincipal();
		return user.getId();
	}
	
}
