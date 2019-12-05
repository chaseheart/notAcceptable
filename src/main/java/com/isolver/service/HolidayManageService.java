package com.isolver.service;

import java.sql.Timestamp;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isolver.common.config.AuthenticationFacade;
import com.isolver.common.constant.SysStaticConst;
import com.isolver.common.util.Dateutil;
import com.isolver.dao.holidayManger.HolidayManagerRepository;
import com.isolver.entity.Holiday;

/**
 * @author IS1907005
 * @date 2019/11/08
 * @class HolidayService.java
 */
@Service
public class HolidayManageService {
	@Autowired
	private HolidayManagerRepository holidayManagerRepository;

	/**
	 * 检索一个月的休息情况
	 * 
	 * @param type
	 * @param year
	 * @param month
	 * @return
	 */
	public Holiday findOneMonth(Boolean type, Integer year, Integer month) {
		return holidayManagerRepository.findByYearAndMonthAndType(year, month, type);
	}

	public void saveHoliday(Integer year, Integer month, String restDay, Boolean type) {
		Holiday holiday = holidayManagerRepository.findByYearAndMonthAndType(year, month, type);
		restDay=restDay.replaceAll("&#34;", "\\\"");
		restDay=restDay.substring(0, restDay.length()-1);
		Timestamp tm = Dateutil.getTimestamp();
		if (holiday != null) {
			Holiday entity = new Holiday();
			BeanUtils.copyProperties(holiday, entity);
			entity.setRestDay(restDay);
			entity.setUpdateUserId(AuthenticationFacade.getUserId());
			entity.setUpdateTime(Dateutil.getTimestamp());
			holidayManagerRepository.saveAndFlush(entity);
		} else {
			holiday = new Holiday();
			holiday.setYear(year);
			holiday.setMonth(month);
			holiday.setType(type);
			holiday.setRestDay(restDay);
			holiday.setInsertUserId(AuthenticationFacade.getUserId());
			holiday.setInsertTime(tm);
			holiday.setUpdateUserId(AuthenticationFacade.getUserId());
			holiday.setUpdateTime(tm);
			holiday.setDeleteFlag(SysStaticConst.NOTDELE);
			holidayManagerRepository.saveAndFlush(holiday);
		}
	}
}
