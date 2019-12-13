package com.isolver.controller.sys;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.isolver.common.config.AuthenticationFacade;
import com.isolver.common.constant.SysStatusCodeConst;
import com.isolver.common.util.Dateutil;
import com.isolver.common.util.JsonUtil;
import com.isolver.common.util.Result;
import com.isolver.dto.ServicePerformanceDto;
import com.isolver.dto.UserDto;
import com.isolver.entity.Holiday;
import com.isolver.entity.User;
import com.isolver.service.HolidayManageService;
import com.isolver.service.ServicePerformanceService;
import com.isolver.service.UserService;

/**
 * @author IS1907005
 * @date 2019/11/06
 * @class servicePerformanceController.java
 */

@RestController
@RequestMapping(value = "/servicePerformanceManage")
public class ServicePerformanceManageController {

	private static final Logger logger = LoggerFactory.getLogger(ServicePerformanceManageController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private ServicePerformanceService servicePerformanceService;
	@Autowired
	private HolidayManageService holidayManageService;

	/**
	 * 考勤初始化数据
	 * 
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView servicePerformanceIndex(String workId) throws JsonProcessingException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("system/servicePerformanceManage");
//		Long id = AuthenticationFacade.getUserId();
		User user = userService.getUserByWorkId(workId);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		int firstDay = cal.getMinimum(Calendar.DATE);
		int lastDay = cal.getActualMaximum(Calendar.DATE);
		Holiday holiday = holidayManageService.findOneMonth(user.getHolidayType(), cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1);
		Date dateStart = Dateutil.localDateToDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, firstDay);
		Date dateEnd = Dateutil.localDateToDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, lastDay);
		dateEnd = Dateutil.getEndTimeOfDate(dateEnd);
		Map<String, Object> dtoList = servicePerformanceService.findservicePerformance(user, dateStart, dateEnd);
		modelAndView.addObject("username", JsonUtil.getJsonArray(new UserDto(user)));
		modelAndView.addObject("servicePerformance", JsonUtil.getJsonArray(dtoList));
		modelAndView.addObject("holiday", JsonUtil.objectToJSON(holiday));
		modelAndView.addObject("month", lastDay);
		Map<String, Object> map = new HashMap<>();
		map = servicePerformanceService.findAllAppilication(dateStart, dateEnd, user);
		modelAndView.addObject("map", JsonUtil.objectToJSON(map));
		//
		return modelAndView;

	}

	/**
	 * 考勤实绩检索
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findAllServicePerformance", method = RequestMethod.POST)
	public Result<Object> findAllServicePerformance(Integer oaYear, Integer oaMonth) {
		try {
			Map<String, Object> map = new HashMap<>();
			Long id = AuthenticationFacade.getUserId();
			User user = userService.getUserById(id);
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.MONTH, oaMonth - 1);
			cal.set(Calendar.YEAR, oaYear);
			int firstDay = cal.getMinimum(Calendar.DATE);
			int lastDay = cal.getActualMaximum(Calendar.DATE);
			Date dateStart = Dateutil.localDateToDate(oaYear, oaMonth, firstDay);
			Date dateEnd = Dateutil.getEndTimeOfDate(Dateutil.localDateToDate(oaYear, oaMonth, lastDay));
			Map<String, Object> dtoList = servicePerformanceService.findservicePerformance(user, dateStart, dateEnd);
			map.put("servicePerformance", dtoList);
			Holiday holiday = holidayManageService.findOneMonth(user.getDeleteFlag(), oaYear, oaMonth);
			map.put("holiday", holiday);
			map.put("month", lastDay);
			map.put("app", servicePerformanceService.findAllAppilication(dateStart, dateEnd, user));
			return new Result<Object>(SysStatusCodeConst.SUCCESS, map);
		} catch (Exception e) {
			logger.error("提交申请失败", e);
			throw new RuntimeException(e);
		}

	}

	/**
	 * 考勤实绩检索
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findOneServicePerformance", method = RequestMethod.POST)
	public Result<Object> findOneServicePerformance(Date date) {
		try {
			Map<String, Object> map = new HashMap<>();
			Long id = AuthenticationFacade.getUserId();
			User user = userService.getUserById(id);
			// 出勤记录
			ServicePerformanceDto dto = servicePerformanceService.findServicePerformanceByDate(user, date);
			map.put("servicePerformance", dto);
			return new Result<Object>(SysStatusCodeConst.SUCCESS, map);
		} catch (Exception e) {
			logger.error("提交申请失败", e);
			throw new RuntimeException(e);
		}

	}

	/**
	 * 考勤实绩检索
	 * 
	 * @return
	 */
	@RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
	public void exportExcel() {
		System.err.print(1);
	}

}
