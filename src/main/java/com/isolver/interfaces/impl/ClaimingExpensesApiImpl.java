package com.isolver.interfaces.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.isolver.common.constant.SysStatusCodeConst;
import com.isolver.common.util.Result;
import com.isolver.entity.User;
import com.isolver.form.wechat.ClaimingExpensesForm;
import com.isolver.interfaces.ClaimingExpensesApi;
import com.isolver.service.PendingService;
import com.isolver.service.UserService;
import com.isolver.service.wechat.ClamingExpensesService;

@RestController
public class ClaimingExpensesApiImpl implements ClaimingExpensesApi {

	@Autowired
	private ClamingExpensesService clamingExpensesService;

	@Autowired
	private PendingService pendingService;

	@Autowired
	private UserService userService;

	/**
	 * 报销申请初始化
	 * 
	 * @param userId 用户id
	 * @return
	 */
	@Override
	public Result<Object> claimingExpensesInit(Long userId) {
		return new Result<>(SysStatusCodeConst.SUCCESS, clamingExpensesService.init(userId));
	}

	/**
	 * 报销申请提交
	 * 
	 * @param claimingExpensesForm 报销表单
	 * @return
	 */
	@Override
	public Result<Object> insert(ClaimingExpensesForm claimingExpensesForm) {
		return clamingExpensesService.applyClamingExpenses(claimingExpensesForm);
	}

	/**
	 * 审批报销申请
	 * 
	 * @param assigner
	 * @param ruFlow
	 * @param userId
	 * @return
	 */
	@Override
	public Result<Object> agreeApplication(String assigner, Long ruFlow, Long userId) {
		return clamingExpensesService.agreeApplication(assigner, ruFlow, userId);
	}

	/**
	 * 驳回报销
	 * 
	 * @param ruFlow
	 * @param userId
	 * @return
	 */
	@Override
	public Result<Object> rejectFlow(Long ruFlow, Long userId) {
		User user = userService.getUserById(userId);
		pendingService.rejectApply(ruFlow, user);
		return new Result<>(SysStatusCodeConst.SUCCESS);
	}

	/**
	 * 拒絕报销
	 * 
	 * @param ruFlow
	 * @param userId
	 * @return
	 */
	@Override
	public Result<Object> refuseApply(Long ruFlow, Long userId) {
		User user = userService.getUserById(userId);
		pendingService.refuseApply(ruFlow, user);
		return new Result<>(SysStatusCodeConst.SUCCESS);
	}

	/**
	 * 获得历史报销列表
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public Result<Object> ruHistory(Long userId) {
		return new Result<>(SysStatusCodeConst.SUCCESS, clamingExpensesService.getCEHistory(userId));
	}

}
