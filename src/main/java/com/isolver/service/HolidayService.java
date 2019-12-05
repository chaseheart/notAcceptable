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
import com.isolver.dao.vacation.VacationRepository;
import com.isolver.entity.OaFlow;
import com.isolver.entity.RuFlow;
import com.isolver.entity.ServicePerformance;
import com.isolver.entity.User;
import com.isolver.entity.Vacation;
import com.isolver.form.HolidayForm;

/**
 * ·休假
 * @author IS1907005
 * @date 2019/11/14
 * @class HolidayService.java
 */
@Service
public class HolidayService {

	@Autowired
	private OaFlowRepository oaFlowRepository;
	@Autowired
	private ServicePerformanceRepository servicePerformanceRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RuFlowRepository ruFlowRepository;
	@Autowired
	private VacationRepository vacationRepository;

	@Autowired
	/** ·流程处理  **/
	private PendingService pendingService;

	/**
	 * ·休假申请录入并启动新流程
	 * 
	 * @param formInputList
	 * @param prevAssignJson 预处理人组
	 * @param assignId
	 * @param userId
	 */
	public void startFlow(HolidayForm holidayForm, User user) {

		OaFlow oaFlow = oaFlowRepository.findOne(1l);
		ServicePerformance servicePerformance = servicePerformanceRepository.findOne(Long.valueOf(holidayForm.getServicePerformance()));
		User assigner = userRepository.findOne(Long.valueOf(holidayForm.getAssigner()));
		Timestamp tm = Dateutil.getTimestamp();
		// ·流程启动 191203 yy
		RuFlow ruFlow = pendingService.startFlow(assigner, oaFlow, user);
		
		/** ·保存申请表单 **/
		Vacation vacation = new Vacation();
		vacation.setUser(user);
		vacation.setVacationType(Integer.parseInt(holidayForm.getType()));
		vacation.setVacationStart(Timestamp.valueOf(holidayForm.getDate().replaceAll("/", "-") + " " + holidayForm.getTimeStart() + ":00"));
		vacation.setVacationEnd(Timestamp.valueOf(holidayForm.getDate().replaceAll("/", "-") + " " + holidayForm.getTimeEnd() + ":00"));
		vacation.setHasDocument(holidayForm.getBill() != null ? true : false);
		vacation.setVacationContent(holidayForm.getReason());
		vacation.setVacationMark(holidayForm.getContent());
		vacation.setRuFlow(ruFlow);
		vacation.setInsertUserId(user.getId());
		vacation.setInsertTime(tm);
		vacation.setUpdateUserId(user.getId());
		vacation.setUpdateTime(tm);
		vacation.setDeleteFlag(SysStaticConst.NOTDELE);
		vacation.setServicePerformance(servicePerformance);
		vacationRepository.saveAndFlush(vacation);
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
	 * 休假 - 再申请
	 * 
	 * @param holidayForm 申请表单
	 * @param user 申请者
	 * @param ruFlowId 流程运行id
	 * 
	 */
	public void restartFlow(HolidayForm holidayForm, User user, Long ruFlowId) {

		User assigner = userRepository.findOne(Long.valueOf(holidayForm.getAssigner()));
		RuFlow ruFlowModel = ruFlowRepository.findOne(ruFlowId);
		Timestamp tm = Dateutil.getTimestamp();
		/** ·流程运行  **/
		pendingService.nextStep(assigner, ruFlowId, user);

		/** ·更新申请表单 **/
		Vacation vacationModal = vacationRepository.findByRuFlow(ruFlowModel);
		Vacation vacationEntity = new Vacation();
		BeanUtils.copyProperties(vacationModal, vacationEntity);
		vacationEntity.setVacationType(Integer.parseInt(holidayForm.getType()));
		vacationEntity.setVacationStart(Timestamp.valueOf(holidayForm.getDate().replaceAll("/", "-") + " " + holidayForm.getTimeStart() + ":00"));
		vacationEntity.setVacationEnd(Timestamp.valueOf(holidayForm.getDate().replaceAll("/", "-") + " " + holidayForm.getTimeEnd() + ":00"));
		vacationEntity.setHasDocument(holidayForm.getBill() != null ? true : false);
		vacationEntity.setVacationContent(holidayForm.getReason());
		vacationEntity.setVacationMark(holidayForm.getContent());
		vacationEntity.setUpdateUserId(user.getId());
		vacationEntity.setUpdateTime(tm);
		vacationRepository.saveAndFlush(vacationEntity);
	}
}
