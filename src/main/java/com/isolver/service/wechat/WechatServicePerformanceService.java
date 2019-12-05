package com.isolver.service.wechat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isolver.common.constant.SysStaticConst;
import com.isolver.common.util.Dateutil;
import com.isolver.dao.servicePerformance.ServicePerformanceRepository;
import com.isolver.dao.vacationRecord.VacationRecordRepository;
import com.isolver.dto.ServicePerformanceDto;
import com.isolver.dto.wechat.WechatServicePerformanceDto;
import com.isolver.dto.wechat.WechatVacationRecordDto;
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

	/**
	 * 根据用户、考勤开始日期、考勤结束日期，获得当月考勤记录
	 * 
	 * @param user          用户
	 * @param dateStart考勤开始
	 * @param dateEnd考勤结束
	 * @return
	 */
	public Map<String, Object> findServicePerformance(Long userId, Integer oaYear, Integer oaMonth) {
		Map<String, Object> result = new HashMap<>();
		Map<String, ServicePerformanceDto> map = new HashMap<>();
		List<String> dataList = new ArrayList<>();
		// 。考勤开始日期
		Date dateStart = Dateutil.getTheFirstDayOfMonth(oaYear, oaMonth);
		// 。考勤结束日期
		Date dateEnd = Dateutil.getTheLastDayOfMonth(oaYear, oaMonth);
		// 。检索当月考勤记录
		List<ServicePerformanceDto> dtoList = servicePerformanceRepository.findServicePerformanceByUserAndDeleteFlag(userId, dateStart, dateEnd, SysStaticConst.NOTDELE);
		for (ServicePerformanceDto dto : dtoList) {
			switch (dto.getState()) {
			case "n":
				break;
			case "o":
				break;

			case "uo":
				break;

			case "u":

				dto.setAbsence(new Integer(9) - Integer.parseInt(dto.getHour()));
				break;

			default:
				break;
			}
			map.put(dto.getDay(), dto);
			dataList.add(dto.getDay());
		}
		WechatVacationRecordDto vrDto = vacationRecordRepository.getVacationByDate(userId, dateStart, SysStaticConst.NOTDELE);
		// 。根据日历，计算一天请假的check TODO
		result.put("servicePerformance", map);
		result.put("date", dataList);
		result.put("vacation", vrDto);
		return result;
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

}
