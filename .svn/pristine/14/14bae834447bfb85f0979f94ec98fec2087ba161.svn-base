package com.isolver.service;

import java.sql.Timestamp;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isolver.common.constant.SysStaticConst;
import com.isolver.common.util.Dateutil;
import com.isolver.dao.oaFlow.OaFlowRepository;
import com.isolver.dao.ruFlow.RuFlowRepository;
import com.isolver.dao.servicePerformance.ServicePerformanceRepository;
import com.isolver.dao.user.UserRepository;
import com.isolver.dao.workOvertime.WorkOvertimeRepository;
import com.isolver.entity.OaFlow;
import com.isolver.entity.RuFlow;
import com.isolver.entity.ServicePerformance;
import com.isolver.entity.User;
import com.isolver.entity.WorkOvertime;
import com.isolver.form.OverTimeForm;

/**
 *· 加班
 * @author IS1907005
 * @date 2019/11/18
 * @class OverTimeService.java
 */
@Service
public class OverTimeService {
	@Autowired
	private OaFlowRepository oaFlowRepository;
	@Autowired
	private ServicePerformanceRepository servicePerformanceRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RuFlowRepository ruFlowRepository;
	@Autowired
	private WorkOvertimeRepository workOvertimeRepository;
	@Autowired
	/** ·流程处理  **/
	private PendingService pendingService;

	/**
	 * ·启动加班流程
	 * @param overTimeForm 申请表单
	 * @param user 申请者
	 */
	public void startFlow(OverTimeForm overTimeForm, User user) {

		OaFlow oaFlow = oaFlowRepository.findOne(1l);
		ServicePerformance servicePerformance = servicePerformanceRepository.findOne(Long.valueOf(overTimeForm.getServicePerformance()));
		User assigner = userRepository.findOne(Long.valueOf(overTimeForm.getAssigner()));
		Timestamp tm = Dateutil.getTimestamp();
		// ·流程启动 191203 yy
		RuFlow ruFlow = pendingService.startFlow(assigner, oaFlow, user);
		
		/** ·保存申请表单 **/
		WorkOvertime workOvertime = new WorkOvertime();
		workOvertime.setUser(user);
		workOvertime.setWorkOvertimeDate(Timestamp.valueOf(overTimeForm.getWorkOvertimeDate().replaceAll("/", "-") + " 00:00:00"));
		workOvertime.setWorkOvertimeStart(Timestamp.valueOf(overTimeForm.getWorkOvertimeDate().replaceAll("/", "-") + " " + overTimeForm.getTimeStart() + ":00"));
		workOvertime.setWorkOvertimeEnd(Timestamp.valueOf(overTimeForm.getWorkOvertimeDate().replaceAll("/", "-") + " " + overTimeForm.getTimeEnd() + ":00"));
		workOvertime.setProjectNo(overTimeForm.getProjectId());
		workOvertime.setWorkContent(overTimeForm.getWorkContent());
		workOvertime.setRuFlow(ruFlow);
		workOvertime.setInsertUserId(user.getId());
		workOvertime.setInsertTime(tm);
		workOvertime.setUpdateUserId(user.getId());
		workOvertime.setUpdateTime(tm);
		workOvertime.setDeleteFlag(SysStaticConst.NOTDELE);
		workOvertime.setServicePerformance(servicePerformance);
		workOvertimeRepository.saveAndFlush(workOvertime);
	}

	/**
	 * 下一步骤
	 * 
	 * @param assign   审批人
	 * @param ruFlowId
	 * @param user     提交用户
	 */
	public void nextStep(User assign, Long ruFlowId, User user) {
		pendingService.nextStep(assign, ruFlowId, user);
	}

	/**
	 * ·加班流程再申请
	 * @param overTimeForm 申请表单
	 * @param user 申请者
	 * @param ruFlowId 运行流程id
	 */
	public void restartFlow(OverTimeForm overTimeForm, User user, Long ruFlowId) {

		User assigner = userRepository.findOne(Long.valueOf(overTimeForm.getAssigner()));
		RuFlow ruFlowModel = ruFlowRepository.findOne(ruFlowId);
		Timestamp tm = Dateutil.getTimestamp();
		/** ·流程运行  **/
		pendingService.nextStep(assigner, ruFlowId, user);

		/** ·更新申请表单 **/
		WorkOvertime workOvertimeModel = workOvertimeRepository.findByRuFlow(ruFlowModel);
		WorkOvertime workOvertimeEntity = new WorkOvertime();
		BeanUtils.copyProperties(workOvertimeModel, workOvertimeEntity);
		workOvertimeEntity.setWorkOvertimeDate(Timestamp.valueOf(overTimeForm.getWorkOvertimeDate().replaceAll("/", "-") + " 00:00:00"));
		workOvertimeEntity.setWorkOvertimeStart(Timestamp.valueOf(overTimeForm.getWorkOvertimeDate().replaceAll("/", "-") + " " + overTimeForm.getTimeStart() + ":00"));
		workOvertimeEntity.setWorkOvertimeEnd(Timestamp.valueOf(overTimeForm.getWorkOvertimeDate().replaceAll("/", "-") + " " + overTimeForm.getTimeEnd() + ":00"));
		workOvertimeEntity.setProjectNo(overTimeForm.getProjectId());
		workOvertimeEntity.setWorkContent(overTimeForm.getWorkContent());
		workOvertimeEntity.setUpdateUserId(user.getId());
		workOvertimeEntity.setUpdateTime(tm);
		workOvertimeRepository.saveAndFlush(workOvertimeEntity);
	}
}
