package com.isolver.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isolver.common.util.Dateutil;
import com.isolver.dao.oaFlow.OaFlowRepository;
import com.isolver.dao.outBusiness.OutBusinessRepository;
import com.isolver.dao.ruFlow.RuFlowRepository;
import com.isolver.dao.servicePerformance.ServicePerformanceRepository;
import com.isolver.dao.user.UserRepository;
import com.isolver.entity.OaFlow;
import com.isolver.entity.OutBusiness;
import com.isolver.entity.RuFlow;
import com.isolver.entity.ServicePerformance;
import com.isolver.entity.UnusualAttendance;
import com.isolver.entity.User;
import com.isolver.form.OutBusinessForm;

/**
 * @author IS1907005
 * @date 2019/11/19
 * @class OutBusinessService.java
 */
@Service
public class OutBusinessService {

	@Autowired
	private OaFlowRepository oaFlowRepository;
	@Autowired
	private ServicePerformanceRepository servicePerformanceRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RuFlowRepository ruFlowRepository;
	@Autowired
	private OutBusinessRepository outBusinessRepository;
	@Autowired
	/** ·流程处理  **/
	private PendingService pendingService;

	/**
	 * ·外出公务 录入并启动新流程
	 * @param outBusinessForm 申请表单
	 * @param user 申请者
	 */
	public void startFlow(OutBusinessForm outBusinessForm, User user) {

		OaFlow oaFlow = oaFlowRepository.findOne(1l);
		User assigner = userRepository.findOne(Long.valueOf(outBusinessForm.getAssigner()));
		Timestamp tm = Dateutil.getTimestamp();
		// ·流程启动 191203 yy
		RuFlow ruFlow = pendingService.startFlow(assigner, oaFlow, user);
		
		/** ·保存申请表单 **/
		// ·外出公务开始日期
		Integer startYear = Integer.parseInt(outBusinessForm.getOutBusinessStart().split("/")[0]);
		Integer startMonth = Integer.parseInt(outBusinessForm.getOutBusinessStart().split("/")[1]);
		Integer startDay = Integer.parseInt(outBusinessForm.getOutBusinessStart().split("/")[2]);
		Date dateStart = Dateutil.localDateToDate(startYear, startMonth, startDay);
		// ·外出公务结束日期
		Integer endYear = Integer.parseInt(outBusinessForm.getOutBusinessEnd().split("/")[0]);
		Integer endMonth = Integer.parseInt(outBusinessForm.getOutBusinessEnd().split("/")[1]);
		Integer endDay = Integer.parseInt(outBusinessForm.getOutBusinessEnd().split("/")[2]);
		Date dateEnd = Dateutil.localDateToDate(endYear, endMonth, endDay);

		List<ServicePerformance> splist = servicePerformanceRepository.findByUserAndDayBetween(user, dateStart, dateEnd);
		List<OutBusiness> oblist = new ArrayList<>();
		for (ServicePerformance sp : splist) {
			OutBusiness outBusiness = new OutBusiness();
			outBusiness.setUser(user);
			outBusiness.setBusinessReason(outBusinessForm.getBusinessReason());
			outBusiness.setBusinessPlace(outBusinessForm.getBusinessPlace());
			outBusiness.setPlaceDetail(outBusinessForm.getPlaceDetail());
			outBusiness.setContactName(outBusinessForm.getContactName());
			outBusiness.setContactPhone(outBusinessForm.getContactPhone());
			outBusiness.setOutType(outBusinessForm.getOutType());
			outBusiness.setOutBusinessStart(dateStart);
			outBusiness.setOutBusinessEnd(dateEnd);
			outBusiness.setContent(outBusinessForm.getContent());
			outBusiness.setRuFlow(ruFlow);
			outBusiness.setServicePerformance(sp);
			outBusiness.setInsertTime(tm);
			outBusiness.setInsertUserId(user.getId());
			outBusiness.setUpdateTime(outBusiness.getInsertTime());
			outBusiness.setUpdateUserId(user.getId());
			oblist.add(outBusiness);
		}
		outBusinessRepository.save(oblist);
		outBusinessRepository.flush();
	}

	/**
	 * 下一步骤
	 * 
	 * @param assign   审批人
	 * @param ruFlowId
	 * @param user     提交用户
	 */
	public void nextStep(User assign, Long ruFlowId, User user) {
		Boolean endFlg = pendingService.nextStep(assign, ruFlowId, user);
		//·流程正常结束，修改出勤的结算时间9点~18点
		if(endFlg) {
			RuFlow ruFlow = new RuFlow();
			ruFlow.setId(ruFlowId);
			ruFlow.setVersion(1);
			// ·申请表
			List<OutBusiness> outBusinessList = outBusinessRepository.findByRuFlow(ruFlow);
			// ·考勤表
			List<ServicePerformance> servicePerformanceList = new ArrayList<>();
			for(OutBusiness outBusiness : outBusinessList) {
				ServicePerformance servicePerformance = outBusiness.getServicePerformance();
				Timestamp tm = Dateutil.getTimestamp();
				Calendar t = Calendar.getInstance();
				// 9:00
				t.set(Calendar.HOUR_OF_DAY, 9);
				servicePerformance.setOaFinalStart(t.getTime());
				// 18:00
				t.set(Calendar.HOUR_OF_DAY, 18);
				servicePerformance.setOaFinalEnd(t.getTime());
				
				servicePerformance.setUpdateTime(tm);
				servicePerformance.setUpdateUserId(user.getId());
				servicePerformanceList.add(servicePerformance);
			}
			servicePerformanceRepository.save(servicePerformanceList);
			servicePerformanceRepository.flush();
		}
	}

	/**
	 * 外出公务 - 再申请
	 * 
	 * @param outBusinessForm 申请表单
	 * @param user 申请者
	 * @param ruFlowId 运行流程id
	 * 
	 */
	public void restartFlow(OutBusinessForm outBusinessForm, User user, Long ruFlowId) {


		User assigner = userRepository.findOne(Long.valueOf(outBusinessForm.getAssigner()));
		RuFlow ruFlowModel = ruFlowRepository.findOne(ruFlowId);
		Timestamp tm = Dateutil.getTimestamp();
		/** ·流程运行  **/
		pendingService.nextStep(assigner, ruFlowId, user);

		/** ·更新申请表单 **/

		// ·外出公务开始日期
		Integer startYear = Integer.parseInt(outBusinessForm.getOutBusinessStart().split("/")[0]);
		Integer startMonth = Integer.parseInt(outBusinessForm.getOutBusinessStart().split("/")[1]);
		Integer startDay = Integer.parseInt(outBusinessForm.getOutBusinessStart().split("/")[2]);
		Date dateStart = Dateutil.localDateToDate(startYear, startMonth, startDay);
		// ·外出公务结束日期
		Integer endYear = Integer.parseInt(outBusinessForm.getOutBusinessEnd().split("/")[0]);
		Integer endMonth = Integer.parseInt(outBusinessForm.getOutBusinessEnd().split("/")[1]);
		Integer endDay = Integer.parseInt(outBusinessForm.getOutBusinessEnd().split("/")[2]);
		Date dateEnd = Dateutil.localDateToDate(endYear, endMonth, endDay);

		List<OutBusiness> obFormList = outBusinessRepository.findByRuFlow(ruFlowModel);
		outBusinessRepository.delete(obFormList);

		List<ServicePerformance> splist = servicePerformanceRepository.findByUserAndDayBetween(user, dateStart, dateEnd);
		List<OutBusiness> oblist = new ArrayList<>();
		for (ServicePerformance sp : splist) {
			OutBusiness outBusiness = new OutBusiness();
			outBusiness.setUser(user);
			outBusiness.setBusinessReason(outBusinessForm.getBusinessReason());
			outBusiness.setBusinessPlace(outBusinessForm.getBusinessPlace());
			outBusiness.setPlaceDetail(outBusinessForm.getPlaceDetail());
			outBusiness.setContactName(outBusinessForm.getContactName());
			outBusiness.setContactPhone(outBusinessForm.getContactPhone());
			outBusiness.setOutType(outBusinessForm.getOutType());
			outBusiness.setOutBusinessStart(dateStart);
			outBusiness.setOutBusinessEnd(dateEnd);
			outBusiness.setContent(outBusinessForm.getContent());
			outBusiness.setRuFlow(ruFlowModel);
			outBusiness.setServicePerformance(sp);
			outBusiness.setInsertTime(tm);
			outBusiness.setInsertUserId(user.getId());
			outBusiness.setUpdateTime(outBusiness.getInsertTime());
			outBusiness.setUpdateUserId(user.getId());
			oblist.add(outBusiness);
		}
		outBusinessRepository.save(oblist);
		outBusinessRepository.flush();
	}
}
