package com.isolver.interfaces.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.isolver.common.util.Result;
import com.isolver.form.wechat.LoginForm;
import com.isolver.form.wechat.PasswordForm;
import com.isolver.interfaces.LoginApi;
import com.isolver.service.wechat.LoginService;

@RestController
public class LoginApiImpl implements LoginApi {
	
	@Autowired
	private LoginService loginService;

	/**
	 * 登录
	 * 
	 * @param loginForm 登录表单
	 * @return
	 */
	@Override
	public Result<Object> login(LoginForm loginForm){
		return loginService.getByLoginIdAndPassword(loginForm);
	}
	
	/**
	 * 重设密码
	 * 
	 * @param passwordForm 密码表单
	 * @return
	 */
	@Override
	public Result<Object> resetPwd(PasswordForm passwordForm){
		return loginService.resetPwd(passwordForm);
	}
}
