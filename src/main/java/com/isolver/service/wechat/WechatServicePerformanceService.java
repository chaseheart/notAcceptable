package com.isolver.service.wechat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isolver.common.constant.SysStaticConst;
import com.isolver.common.util.Dateutil;
import com.isolver.dao.holidayManger.HolidayManagerRepository;
import com.isolver.dao.oaFlowStep.OaFlowStepRepository;
import com.isolver.dao.servicePerformance.ServicePerformanceRepository;
import com.isolver.dao.user.UserRepository;
import com.isolver.dao.vacationRecord.VacationRecordRepository;
import com.isolver.dto.ServicePerformanceDto;
import com.isolver.dto.wechat.WechatServicePerformanceDto;
import com.isolver.dto.wechat.WechatVacationRecordDto;
import com.isolver.entity.Holiday;
import com.isolver.entity.User;

/**
 * @author IS1907005
 * @date 2019/11/07
 * @class servicePerformanceService.java
 */
@Service
public class WechatServicePerformanceService {

	@Autowired
	private ServicePerformanceRepository servicePerformanceRepository;

	@Autowired
	private VacationRecordRepository vacationRecordRepository;

	@Autowired
	private HolidayManagerRepository holidayManagerRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OaFlowStepRepository oaFlowStepRepository;

	/**
	 * 根据用户、考勤开始日期、考勤结束日期，获得当月考勤记录
	 * 
	 * @param user          用户
	 * @param dateStart考勤开始
	 * @param dateEnd考勤结束
	 * @return
	 */
	public Map<String, Object> findServicePerformance(User user, Integer oaYear, Integer oaMonth) {
		Map<String, Object> result = new HashMap<>();
		Map<String, ServicePerformanceDto> map = new HashMap<>();
		List<String> dataList = new ArrayList<>();
		// 。考勤开始日期
		Date dateStart = Dateutil.getTheFirstDayOfMonth(oaYear, oaMonth);
		// 。考勤结束日期
		Date dateEnd = Dateutil.getTheLastDayOfMonth(oaYear, oaMonth);
		// 。检索当月考勤记录
		List<ServicePerformanceDto> dtoList = servicePerformanceRepository.findServicePerformanceByUserAndDeleteFlag(user , dateStart, dateEnd, SysStaticConst.NOTDELE);
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
			dataList.add(dto.getDay());
			map.put(dto.getDay(), dto);
		}
		// 。根据日历，计算一天请假的check TODO
		WechatVacationRecordDto vrDto = vacationRecordRepository.getVacationByDate(user.getId(), dateStart, SysStaticConst.NOTDELE);
		Holiday holiday = holidayManagerRepository.findByYearAndMonthAndType(oaYear, oaMonth, user.getHolidayType());
		
		List<Integer> roleStep = oaFlowStepRepository.getNextStepLevel(user.getRole(), 1L);
		if (roleStep.size() != 0) {
			result.put("role", false);
		} else {
			result.put("role", true);
		}
		
		result.put("servicePerformance", map);
		result.put("date", dataList);
		result.put("vacation", vrDto);
		result.put("holiday", holiday);
		return result;
	}

	/**
	 * 通过ID获得一条考勤记录 微信
	 * 
	 * @param id
	 * @return
	 */
	public Map<String, Object> findOneServicePerformanceByIdAndUser(Long id, User user) {
		Map<String, Object> result = new HashMap<>();
		WechatServicePerformanceDto spDto = servicePerformanceRepository.findServicePerformanceById(user, id, SysStaticConst.NOTDELE);
		result.put("servicePerformance", spDto);
		Holiday holiday = holidayManagerRepository.findByYearAndMonthAndType(Integer.parseInt(spDto.getDay().split("-")[0]), Integer.parseInt(spDto.getDay().split("-")[1]), user.getHolidayType());
		result.put("holiday", holiday);
		return result;
	}

}
