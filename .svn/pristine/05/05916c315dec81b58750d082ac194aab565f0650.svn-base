package com.isolver.interfaces;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.isolver.common.util.Result;
import com.isolver.form.HolidayForm;

@RequestMapping("/weixin/vacation")
public interface VacationApi {
	/**
	 * 休假申请 - 初始化
	 * 
	 * @return
	 */
	@RequestMapping(value = "/init", method = RequestMethod.POST)
	public Result<Object> ApplicationInit(Long ruFlowId);
	
	/**
	 * 休假申请
	 * 
	 * @return
	 */
	@RequestMapping(value = "/application", method = RequestMethod.POST)
	public Result<Object> Application(@ModelAttribute HolidayForm holidayForm, Long userId);
	
	/**
	 * 休假审批
	 * 
	 * @return
	 */
	@RequestMapping(value = "/dealApplication", method = RequestMethod.POST)
	public Result<Object> dealApplication(String assigner, String ruFlow, Long userId);
	
	/**
	 * 休假再申请
	 * 
	 * @return
	 */
	@RequestMapping(value = "/reApplication", method = RequestMethod.POST)
	public Result<Object> reApplication(@ModelAttribute HolidayForm holidayForm, Long userId, Long id);


	/**
	 * 休假驳回
	 * 
	 * @return
	 */
	@RequestMapping(value = "/rejectFlow", method = RequestMethod.POST)
	public Result<Object> rejectFlow(Long ruFlow, Long userId);
	
	/**
	 * 休假拒絕
	 * 
	 * @return
	 */
	@RequestMapping(value = "/refuseApply", method = RequestMethod.POST)
	public Result<Object> refuseApply(Long ruFlow, Long userId);
}
