package com.isolver.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isolver.common.constant.SysStaticConst;
import com.isolver.common.util.Dateutil;
import com.isolver.common.util.ExcelUtil;
import com.isolver.dao.claimingExpenses.ClaimingExpensesRepository;
import com.isolver.dao.holidayManger.HolidayManagerRepository;
import com.isolver.dao.outBusiness.OutBusinessRepository;
import com.isolver.dao.ruFlow.RuFlowRepository;
import com.isolver.dao.servicePerformance.ServicePerformanceRepository;
import com.isolver.dao.unusualAttendance.UnusualAttendanceRepository;
import com.isolver.dao.user.UserRepository;
import com.isolver.dao.vacation.VacationRepository;
import com.isolver.dao.workOvertime.WorkOvertimeRepository;
import com.isolver.dto.ApplicationDto;
import com.isolver.dto.OutBusinessDto;
import com.isolver.dto.OverTimeDto;
import com.isolver.dto.ServicePerformanceDto;
import com.isolver.dto.UnusualPerformanceDto;
import com.isolver.dto.VacationDto;
import com.isolver.dto.WorkSearchDto;
import com.isolver.dto.wechat.WechatServicePerformanceDto;
import com.isolver.entity.Holiday;
import com.isolver.entity.OutBusiness;
import com.isolver.entity.RuFlow;
import com.isolver.entity.ServicePerformance;
import com.isolver.entity.UnusualAttendance;
import com.isolver.entity.User;
import com.isolver.entity.Vacation;
import com.isolver.entity.WorkOvertime;
import com.isolver.form.WorkConditionForm;

/**
 * @author IS1907005
 * @date 2019/11/07
 * @class servicePerformanceService.java
 */
@Service
public class ServicePerformanceService {

	@Autowired
	private ServicePerformanceRepository servicePerformanceRepository;

	@Autowired
	private VacationRepository vacationRepository;

	@Autowired
	private WorkOvertimeRepository workOvertimeRepository;
	@Autowired
	private UnusualAttendanceRepository unusualAttendanceRepository;
	@Autowired
	private OutBusinessRepository outBusinessRepository;

	@Autowired
	private RuFlowRepository ruFlowRepository;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private HolidayManagerRepository holidayManagerRepository;

	/**
	 * 根据用户、考勤开始日期、考勤结束日期，获得当月考勤记录
	 * 
	 * @param user          用户
	 * @param dateStart考勤开始
	 * @param dateEnd考勤结束
	 * @return
	 */
	public Map<String, ServicePerformanceDto> findservicePerformance(Long userId, Date dateStart, Date dateEnd) {
		Map<String, ServicePerformanceDto> map = new HashMap<>();
		List<ServicePerformanceDto> dtoList = servicePerformanceRepository.findServicePerformanceByUserAndDeleteFlag(userId, dateStart, dateEnd, SysStaticConst.NOTDELE);
		for (ServicePerformanceDto dto : dtoList) {
			switch (dto.getState()) {
			case "uo":
				if (StringUtils.isEmpty(dto.getHour())) {
					dto.setAbsence(8);
				} else {
					dto.setAbsence(new Integer(9) - Integer.parseInt(dto.getHour()));
				}
				break;
			case "u":
				if (StringUtils.isEmpty(dto.getHour())) {
					dto.setAbsence(8);
				} else {
					dto.setAbsence(new Integer(9) - Integer.parseInt(dto.getHour()));
				}

				break;

			default:
				break;
			}
			map.put(dto.getDay(), dto);
		}
		// 。根据日历，计算一天请假的check TODO

		return map;
	}

	/**
	 * 通过ID获得一条考勤记录
	 * 
	 * @param id
	 * @return
	 */
	public ServicePerformanceDto findOneServicePerformance(Long id) {
		return servicePerformanceRepository.findservicePerformanceById(id);
	}

	/**
	 * 通过日期获得一条考勤记录
	 * 
	 * @param id
	 * @return
	 */
	public ServicePerformanceDto findServicePerformanceByDate(User user, Date date) {
		return servicePerformanceRepository.findServicePerformanceByDate(user, date, SysStaticConst.NOTDELE);
	}

	/**
	 * 检索一个月内所有考勤记录
	 * 
	 * @param oaStart
	 * @param oaEnd
	 * @return
	 */
	public Map<String, Object> findAllAppilication(Date oaStart, Date oaEnd, User user) {
		Map<String, Object> map = new HashMap<>();
		// 。调休申请
		List<ApplicationDto> vaList = vacationRepository.findApplicationDtoByVacationStartBetween(new Timestamp(oaStart.getTime()), new Timestamp(oaEnd.getTime()), user);
		map.put("vacation", vaList);
		// 。加班申请
		List<ApplicationDto> ovList = workOvertimeRepository.findApplicationDtoByWorkOvertimeStartBetween(new Timestamp(oaStart.getTime()), new Timestamp(oaEnd.getTime()), user);
		map.put("workOvertime", ovList);
		// 。非正常申请
		List<ApplicationDto> uaList = unusualAttendanceRepository.findApplicationDtoByUnusualAttendanceDateBetween(new Timestamp(oaStart.getTime()), new Timestamp(oaEnd.getTime()), user);
		map.put("unusualAttendance", uaList);
		// 。外出办公
		List<ApplicationDto> obList = outBusinessRepository.findApplicationDtoByBetween(new Timestamp(oaStart.getTime()), new Timestamp(oaEnd.getTime()), user);
		map.put("outBusiness", obList);
		// 。报销
//		List<ApplicationDto> ceList = claimingExpensesRepository.findApplicationDtoByUnusualAttendanceDateBetween(new Timestamp(oaStart.getTime()), new Timestamp(oaEnd.getTime()), user);
//		map.put("claimingExpenses", ceList);

		return map;
	}

	/**
	 * 
	 * @param dtoList
	 * @param holiday
	 * @return
	 */
	public HSSFWorkbook exportExcel(Map<String, ServicePerformanceDto> dtoList, Holiday holiday) {
		// excel标题
		String[] title = { "日期", "考勤开始", "考勤结束", "出勤时间", "加班时间" };

		// sheet名
		String sheetName = "学生信息表";

		String[][] content = new String[dtoList.size()][];
		int i = 0;
		// mapd的遍历

		if (holiday != null) {

		}
		for (Entry<String, ServicePerformanceDto> entry : dtoList.entrySet()) {

			System.out.println(entry.getKey() + ":" + entry.getValue());
			content[i] = new String[title.length];
			content[i][0] = entry.getValue().getDay();
			content[i][1] = entry.getValue().getOaFinalStart();
			content[i][2] = entry.getValue().getOaFinalEnd();
			content[i][3] = entry.getValue().getHour();
			content[i][4] = entry.getValue().getState();
			i++;

		}
		// 创建HSSFWorkbook
		HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
		return wb;

	}

	/**
	 * 检索考勤情况和休假情况
	 * 
	 * @param ruFlowId
	 */
	public Map<String, Object> getServicePerformaneAndVacationForm(Long ruFlowId) {
		Map<String, Object> map = new HashMap<>();
		RuFlow ruFlow = ruFlowRepository.findOne(ruFlowId);
		Vacation vacation = vacationRepository.findByRuFlow(ruFlow);
		VacationDto vacationDto = new VacationDto(vacation);
		ServicePerformanceDto servicePerformanceDto = new ServicePerformanceDto(vacation.getServicePerformance());
		map.put("vacationDto", vacationDto);
		map.put("servicePerformanceDto", servicePerformanceDto);
		return map;
	}

	/**
	 * 检索考勤情况和加班情况
	 * 
	 * @param ruFlowId
	 */
	public Map<String, Object> getServicePerformaneAndOverTimeForm(Long ruFlowId) {
		Map<String, Object> map = new HashMap<>();
		RuFlow ruFlow = ruFlowRepository.findOne(ruFlowId);
		WorkOvertime workOvertime = workOvertimeRepository.findByRuFlow(ruFlow);
		OverTimeDto overTimeDto = new OverTimeDto(workOvertime);
		ServicePerformanceDto servicePerformanceDto = new ServicePerformanceDto(workOvertime.getServicePerformance());
		map.put("overTime", overTimeDto);
		map.put("servicePerformanceDto", servicePerformanceDto);
		return map;
	}

	/**
	 * 检索考勤情况和非正常情况
	 * 
	 * @param ruFlowId
	 */
	public Map<String, Object> getServicePerformaneAndUnusualAttendanceForm(Long ruFlowId) {
		Map<String, Object> map = new HashMap<>();
		RuFlow ruFlow = ruFlowRepository.findOne(ruFlowId);
		UnusualAttendance unusualAttendance = unusualAttendanceRepository.findByRuFlow(ruFlow);
		UnusualPerformanceDto unusualAttendanceDto = new UnusualPerformanceDto(unusualAttendance);
		ServicePerformanceDto servicePerformanceDto = new ServicePerformanceDto(unusualAttendance.getServicePerformance());
		map.put("unusualAttendance", unusualAttendanceDto);
		map.put("servicePerformanceDto", servicePerformanceDto);
		return map;
	}

	/**
	 * 检索考勤情况和外出情况
	 * 
	 * @param ruFlowId
	 */
	public Map<String, Object> getServicePerformaneAndOutBusinessForm(Long ruFlowId) {
		Map<String, Object> map = new HashMap<>();
		RuFlow ruFlow = ruFlowRepository.findOne(ruFlowId);
		List<OutBusiness> outBusiness = outBusinessRepository.findByRuFlow(ruFlow);
		List<OutBusinessDto> outBusinessDtoList = new ArrayList<>();
		for (OutBusiness o : outBusiness) {
			OutBusinessDto outBusinessDto = new OutBusinessDto(o);
			outBusinessDtoList.add(outBusinessDto);
		}
		map.put("outBusiness", outBusinessDtoList);
		return map;
	}

	/**
	 * 通过ID获得一条考勤记录 微信
	 * 
	 * @param id
	 * @return
	 */
	public WechatServicePerformanceDto findOneServicePerformanceByIdAndUser(Long id, User user) {
		return servicePerformanceRepository.findServicePerformanceById(user, id, SysStaticConst.NOTDELE);
	}

	/**
	 * 初期化检索
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<WorkSearchDto> getWorkSearch(Date startDate, Date endDate) {
		WorkConditionForm workConditionForm = new WorkConditionForm();
		return servicePerformanceRepository.getAllWorkSearchDto(startDate, endDate, workConditionForm);

	}

	/**
	 * 初期化检索
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<WorkSearchDto> getWorkSearchByCondition(WorkConditionForm workConditionForm) {

		Calendar cal = Calendar.getInstance();
		Integer year = Integer.parseInt(workConditionForm.getDate().split("-")[0]);
		Integer month = Integer.parseInt(workConditionForm.getDate().split("-")[1]);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.YEAR, year);
		int firstDay = cal.getMinimum(Calendar.DATE);
		int lastDay = cal.getActualMaximum(Calendar.DATE);
		Date dateStart = Dateutil.localDateToDate(year, month, firstDay);
		Date dateEnd = Dateutil.localDateToDate(year, month, lastDay);

		return servicePerformanceRepository.getAllWorkSearchDto(dateStart, dateEnd, workConditionForm);

	}

	/**
	 * 将excel中考勤数据写入数据库
	 * 
	 * @param result
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	public void insertServicePerformanceByExcel(List<Object> result, User insertUser) throws ParseException {

		
		for (int i = 1; i < result.size(); i++) {
			List<Object> list = (List<Object>) result.get(i);
			// 。工号
			String workId = list.get(1).toString();
			// 。考勤日期
			String attenceDay = list.get(5).toString();
			// 。考勤开始时间
			String startTime = list.get(9).toString();
			// 。考勤结束时间
			String endTime = list.get(10).toString();
			// 。用户
			User user = userRepository.findByWorkId(workId);
			// 。年
			String year = attenceDay.split("/")[0];
			// 。月
			String month = attenceDay.split("/")[1];
			// 。日
			String day = "\"" + attenceDay.split("/")[2] + "\"";
			// 。年历国家
			Boolean type = user.getHolidayType();
			// 。节假日
			Holiday holiday = holidayManagerRepository.findByYearAndMonthAndType(Integer.parseInt(year), Integer.parseInt(month), type);

			String restDay = holiday.getRestDay();
			// 。考勤日期
			Date date = Dateutil.stringToDate(attenceDay.replaceAll("/", "-"));
			Date oaStart = null;
			Date finalStart = null;
			if (!startTime.isEmpty()) {
				// 。 考勤开始时间
				oaStart = Dateutil.getDateByYearAndTime(attenceDay, startTime);
				// 。考勤最终开始时间
				finalStart = Dateutil.getStartTime(oaStart);
			}
			Date oaEnd = null;
			Date finalEnd = null;
			if (!endTime.isEmpty()) {
				// 。考勤结束时间
				oaEnd = Dateutil.getDateByYearAndTime(attenceDay, endTime);
				// 。考勤最终结束时间
				finalEnd = Dateutil.getEndTime(oaEnd);

			}
			ServicePerformance sp = servicePerformanceRepository.findByUserAndDay(user, date);
//当日有考勤数据，则覆盖
			if (sp != null) {
				ServicePerformance servicePerformance = new ServicePerformance();
				BeanUtils.copyProperties(sp, servicePerformance);
				servicePerformance.setOaStart(oaStart);
				servicePerformance.setOaEnd(oaEnd);
				servicePerformance.setOaFinalStart(finalStart);
				servicePerformance.setOaFinalEnd(finalEnd);
				// 不休假
				if (restDay.indexOf(day) != -1 && finalStart != null && finalEnd != null) {
					long from2 = finalStart.getTime();
					long to2 = finalEnd.getTime();
					int hours = (int) ((to2 - from2) / (1000 * 60 * 60));
					System.out.println("两个时间之间的小时差为：" + hours);
					servicePerformance.setAbsenceTime(9 - hours);
				}

				servicePerformance.setUpdateUserId(insertUser.getId());
				servicePerformance.setUpdateTime(Dateutil.getTimestamp());
				servicePerformanceRepository.saveAndFlush(servicePerformance);
			}
			// 否则新建
			else {
				ServicePerformance servicePerformance = new ServicePerformance();
				servicePerformance.setOaStart(oaStart);
				servicePerformance.setOaEnd(oaEnd);
				servicePerformance.setOaFinalStart(finalStart);
				servicePerformance.setOaFinalEnd(finalEnd);
				// 不休假
				if (restDay.indexOf(day) != -1 && finalStart != null && finalEnd != null) {
					long from2 = finalStart.getTime();
					long to2 = finalEnd.getTime();
					int hours = (int) ((to2 - from2) / (1000 * 60 * 60));
					System.out.println("两个时间之间的小时差为：" + hours);
					servicePerformance.setAbsenceTime(9 - hours);
				}
				servicePerformance.setUser(user);
				servicePerformance.setDay(date);
				servicePerformance.setInsertUserId(insertUser.getId());
				servicePerformance.setInsertTime(Dateutil.getTimestamp());
				servicePerformance.setUpdateUserId(insertUser.getId());
				servicePerformance.setUpdateTime(Dateutil.getTimestamp());
				servicePerformance.setDeleteFlag(SysStaticConst.NOTDELE);
				servicePerformanceRepository.saveAndFlush(servicePerformance);
			}
		}
	}
}
