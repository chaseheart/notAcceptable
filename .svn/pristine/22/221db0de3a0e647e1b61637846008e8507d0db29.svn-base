package com.isolver.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.isolver.common.util.Dateutil;
import com.isolver.common.util.JsonUtil;
import com.isolver.common.util.Result;
import com.isolver.dto.OrgDto;
import com.isolver.dto.WorkSearchDto;
import com.isolver.form.WorkConditionForm;
import com.isolver.service.OrganizationService;
import com.isolver.service.ServicePerformanceService;

/**
 * 社内出勤记录
 * 
 * @author IS1907005
 * @date 2019/11/27
 * @class WorkSearchController.java
 */
@RestController
@RequestMapping(value = "/workSearch")
public class WorkSearchController {

	private static final Logger logger = LoggerFactory.getLogger(WorkSearchController.class);

	@Autowired
	private ServicePerformanceService servicePerformanceService;
	@Autowired
	private OrganizationService organizationService;

	/**
	 * 工作记录一览数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView indexView() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("workSearch");
		List<OrgDto> orgdto = organizationService.findAllOrg();
		modelAndView.addObject("orgdto", JsonUtil.getJsonArray(orgdto));
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		int firstDay = cal.getMinimum(Calendar.DATE);
		int lastDay = cal.getActualMaximum(Calendar.DATE);
		Date startDate = Dateutil.localDateToDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, firstDay);
		Date endDate = Dateutil.localDateToDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, lastDay);
		List<WorkSearchDto> dtoList = servicePerformanceService.getWorkSearch(startDate, endDate);
		modelAndView.addObject("workSearch", JsonUtil.objectToJSON(dtoList));
		modelAndView.addObject("date", cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1));
		return modelAndView;
	}

	/**
	 * main初始化数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/searchWork", method = RequestMethod.POST)
	public Result<Object> searchWork(@ModelAttribute WorkConditionForm WorkConditionForm) {
		try {
			List<WorkSearchDto> dtoList = servicePerformanceService.getWorkSearchByCondition(WorkConditionForm);
			return new Result<>(dtoList);
		} catch (Exception e) {
			logger.error("提交申请失败", e);
			throw new RuntimeException(e);
		}
	}
}
