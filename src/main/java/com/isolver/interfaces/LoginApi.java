package com.isolver.interfaces;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.isolver.common.util.Result;
import com.isolver.form.wechat.LoginForm;
import com.isolver.form.wechat.PasswordForm;

@RequestMapping("/weixin")
public interface LoginApi {

	/**
	 * 登录
	 * 
	 * @param loginForm 登录表单
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Result<Object> login(@ModelAttribute LoginForm loginForm);
	
	/**
	 * 重设密码
	 * 
	 * @param passwordForm 密码表单
	 * @return
	 */
	@RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
	public Result<Object> resetPwd(@ModelAttribute PasswordForm passwordForm);
}
