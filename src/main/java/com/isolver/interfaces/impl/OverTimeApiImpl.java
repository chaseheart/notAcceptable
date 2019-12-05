package com.isolver.interfaces.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.isolver.common.constant.SysStatusCodeConst;
import com.isolver.common.util.Result;
import com.isolver.dto.OrgDto;
import com.isolver.entity.User;
import com.isolver.form.OverTimeForm;
import com.isolver.interfaces.OverTimeApi;
import com.isolver.service.OrganizationService;
import com.isolver.service.OverTimeService;
import com.isolver.service.PendingService;
import com.isolver.service.ServicePerformanceService;
import com.isolver.service.UserService;

@RestController
public class OverTimeApiImpl implements OverTimeApi {
	
	@Autowired
	private UserService userService;

	@Autowired
	private OverTimeService overTimeService;

	@Autowired
	private ServicePerformanceService servicePerformanceService;

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private PendingService pendingService;

	/**
	 * 加班申请 - 初始化
	 * @param ruFlowId
	 * @return
	 */
	@Override
	public Result<Object> ApplicationInit(Long ruFlowId) {
		List<OrgDto> orgdto = organizationService.findAllOrg();
		Map<String, Object> map = servicePerformanceService.getServicePerformaneAndOverTimeForm(ruFlowId);
		map.put("org", orgdto);
		return new Result<>(SysStatusCodeConst.SUCCESS, map);
	}

	/**
	 * 加班提交申请
	 * @param overTimeForm
	 * @param userId
	 * @return
	 */
	@Override
	public Result<Object> Application(OverTimeForm overTimeForm, Long userId) {
		User user = userService.getUserById(userId);
		overTimeService.startFlow(overTimeForm, user);
		return new Result<>(SysStatusCodeConst.SUCCESS);
	}

	/**
	 * 加班提交申请
	 * @param overTimeForm
	 * @param userId
	 * @param id
	 * @return
	 */
	@Override
	public Result<Object> reApplication(OverTimeForm overTimeForm, Long userId, Long id) {
		User user = userService.getUserById(userId);
		overTimeService.restartFlow(overTimeForm, user, id);
		return new Result<>(SysStatusCodeConst.SUCCESS);
	}

	/**
	 * 加班审批
	 * @param assigner
	 * @param ruFlow
	 * @param userId
	 * @return
	 */
	@Override
	public Result<Object> dealApplication(String assigner, String ruFlow, Long userId) {
		User user = userService.getUserById(userId);
		Long ruFlowId = Long.valueOf(ruFlow);
		if (user.getRole().getLevel() != 3) {
			Long assignerId = Long.valueOf(assigner);
			User assignerUser = userService.getUserById(assignerId);

			overTimeService.nextStep(assignerUser, ruFlowId, user);
		} else {
			overTimeService.nextStep(user, ruFlowId, user);
		}
		return new Result<>(SysStatusCodeConst.SUCCESS);
	}

	/**
	 * 加班驳回
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
	 * 加班拒絕
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
	
}
