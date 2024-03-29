package com.isolver.interfaces.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.isolver.common.constant.SysStatusCodeConst;
import com.isolver.common.util.Result;
import com.isolver.dto.wechat.SalaryListDto;
import com.isolver.entity.User;
import com.isolver.interfaces.SalaryApi;
import com.isolver.service.UserService;
import com.isolver.service.wechat.SalaryService;

@RestController
public class SalaryApiImpl implements SalaryApi {
	
	@Autowired
	private SalaryService salaryService;

	@Autowired
	private UserService userService;

	/**
	 * 获得工资单列表
	 * 
	 * @param userId
	 * @param date
	 * @return
	 */
	@Override
	public Result<Object> getSalaryList(Long userId) {
		User user = userService.getUserById(userId);
		List<SalaryListDto> resultList = salaryService.getRecord(user);
		return new Result<Object>(SysStatusCodeConst.SUCCESS, resultList);
	}

	/**
	 * 删除工资单
	 * 
	 * @param id
	 * @param userId
	 * @param version
	 * @return resultList
	 */
	@Override
	public Result<Object> deleteSalary(Long id, Long userId, Integer version) {
		return salaryService.delOneSalary(id, userId, version);
	}

	/**
	 * 获得工资详细
	 * 
	 * @param id
	 * @param userId
	 * @param version
	 * @return resultList
	 */
	@Override
	public Result<Object> getSalaryDetail(Long id) {
		return new Result<>(SysStatusCodeConst.SUCCESS, salaryService.getOneSalary(id));
	}
	

}
