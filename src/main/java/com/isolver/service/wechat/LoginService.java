package com.isolver.service.wechat;

import java.sql.Timestamp;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isolver.common.constant.SysStaticConst;
import com.isolver.common.constant.SysStatusCodeConst;
import com.isolver.common.util.Dateutil;
import com.isolver.common.util.Encodeutil;
import com.isolver.common.util.Result;
import com.isolver.dao.user.UserRepository;
import com.isolver.dao.wechat.LoginRepository;
import com.isolver.dto.wechat.LoginDto;
import com.isolver.entity.User;
import com.isolver.form.wechat.LoginForm;
import com.isolver.form.wechat.PasswordForm;

@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private UserRepository userRepository;

	/**
	 * 登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public Result<Object> getByLoginIdAndPassword(LoginForm loginForm) {
		String loginId = loginForm.getLoginId();
		// 。登录flag
		String password = Encodeutil.MD5Util(loginForm.getLoginId() + loginForm.getPassword());
		LoginDto loginDto = loginRepository.getByLoginIdAndPassword(password, loginId, SysStaticConst.NOTDELE);
		if (loginDto == null) {
			return new Result<>(SysStatusCodeConst.NODATA);
		}
		return new Result<>(SysStatusCodeConst.SUCCESS, loginDto);
	}

	/**
	 * 修改密码
	 * 
	 * @param passwordForm passwordForm
	 * @return
	 */
	public Result<Object> resetPwd(PasswordForm passwordForm) {
		User user = userRepository.findOne(Long.parseLong(passwordForm.getId()));
		String password = Encodeutil.MD5Util(user.getWorkId() + passwordForm.getOriginPassword());
		if (password.equals(user.getPassword())) {
			Timestamp presentTime = Dateutil.getTimestamp();
			User entity = new User();
			BeanUtils.copyProperties(user, entity);
			password = Encodeutil.MD5Util(user.getWorkId() + passwordForm.getNewPassword());
			entity.setPassword(password);
			entity.setUpdateUserId(Long.parseLong(passwordForm.getId()));
			entity.setUpdateTime(presentTime);
			userRepository.save(entity);
			return new Result<>(SysStatusCodeConst.SUCCESS);
		}
		return new Result<>(SysStatusCodeConst.NODATA);
	}

}
