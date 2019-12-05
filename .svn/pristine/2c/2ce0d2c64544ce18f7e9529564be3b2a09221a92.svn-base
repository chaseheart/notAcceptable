package com.isolver.interfaces.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.isolver.common.constant.SysStatusCodeConst;
import com.isolver.common.util.Result;
import com.isolver.dto.OrgDto;
import com.isolver.entity.User;
import com.isolver.form.UnusualAttendanceForm;
import com.isolver.interfaces.UnusualAttendanceApi;
import com.isolver.service.OrganizationService;
import com.isolver.service.PendingService;
import com.isolver.service.ServicePerformanceService;
import com.isolver.service.UnusualAttendanceService;
import com.isolver.service.UserService;

@RestController
public class UnusualAttendanceApiImpl implements UnusualAttendanceApi {
	
	@Autowired
	private UserService userService;

	@Autowired
	private UnusualAttendanceService unusualAttendanceService;

	@Autowired
	private ServicePerformanceService servicePerformanceService;

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private PendingService pendingService;

	/**
	 * 非正常考勤申请 - 初始化
	 * 
	 * @return
	 */
	@Override
	public Result<Object> applicationInit(Long ruFlowId) {
		List<OrgDto> orgdto = organizationService.findAllOrg();
		Map<String, Object> map = servicePerformanceService.getServicePerformaneAndUnusualAttendanceForm(ruFlowId);
		map.put("org", orgdto);
		return new Result<>(SysStatusCodeConst.SUCCESS, map);
	}

	/**
	 * 非正常提交申请
	 * 
	 * @return
	 */
	@Override
	public Result<Object> application(UnusualAttendanceForm unusualAttendanceForm, Long userId) {
		User user = userService.getUserById(userId);
		unusualAttendanceService.startFlow(unusualAttendanceForm, user);
		return new Result<>(SysStatusCodeConst.SUCCESS);
	}

	/**
	 * 非正常提交再申请
	 * 
	 * @return
	 */
	@Override
	public Result<Object> reApplication(UnusualAttendanceForm unusualAttendanceForm, Long userId, Long id) {
		User user = userService.getUserById(userId);
		unusualAttendanceService.restartFlow(unusualAttendanceForm, user, id);
		return new Result<>(SysStatusCodeConst.SUCCESS);
	}

	/**
	 * 非正常审批
	 * 
	 * @return
	 */
	@Override
	public Result<Object> dealApplication(String assigner, String ruFlow, Long userId) {
		User user = userService.getUserById(userId);
		Long ruFlowId = Long.valueOf(ruFlow);
		if (user.getRole().getLevel() != 3) {
			Long assignerId = Long.valueOf(assigner);
			User assignerUser = userService.getUserById(assignerId);

			unusualAttendanceService.nextStep(assignerUser, ruFlowId, user);
		} else {
			unusualAttendanceService.nextStep(user, ruFlowId, user);
		}
		return new Result<>(SysStatusCodeConst.SUCCESS);
	}

	/**
	 * 非正常驳回
	 * 
	 * @return
	 */
	@Override
	public Result<Object> rejectFlow(Long ruFlow, Long userId) {
		User user = userService.getUserById(userId);
		pendingService.rejectApply(ruFlow, user);
		return new Result<>(SysStatusCodeConst.SUCCESS);
	}

	/**
	 * 非正常审批拒絕
	 * 
	 * @return
	 */
	@Override
	public Result<Object> refuseApply(Long ruFlow, Long userId) {
		User user = userService.getUserById(userId);
		pendingService.refuseApply(ruFlow, user);
		return new Result<>(SysStatusCodeConst.SUCCESS);
	}
	
}
