package com.isolver.interfaces;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.isolver.common.util.Result;
import com.isolver.form.OverTimeForm;

@RequestMapping("/weixin/overTime")
public interface OverTimeApi {

	/**
	 * 加班申请 - 初始化
	 * @param ruFlowId
	 * @return
	 */
	@RequestMapping(value = "/init", method = RequestMethod.POST)
	public Result<Object> ApplicationInit(Long ruFlowId, Long userId);
	
	/**
	 * 加班提交申请
	 * @param overTimeForm
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/application", method = RequestMethod.POST)
	public Result<Object> Application(@ModelAttribute OverTimeForm overTimeForm, Long userId);
	
	/**
	 * 加班提交申请
	 * @param overTimeForm
	 * @param userId
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/reApplication", method = RequestMethod.POST)
	public Result<Object> reApplication(@ModelAttribute OverTimeForm overTimeForm, Long userId, Long id);

	/**
	 * 加班审批
	 * @param assigner
	 * @param ruFlow
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/dealApplication", method = RequestMethod.POST)
	public Result<Object> dealApplication(String assigner, String ruFlow, Long userId);
	
	/**
	 * 加班驳回
	 * @param ruFlow
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/rejectFlow", method = RequestMethod.POST)
	public Result<Object> rejectFlow(Long ruFlow, Long userId);
	
	/**
	 * 加班拒絕
	 * @param ruFlow
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/refuseApply", method = RequestMethod.POST)
	public Result<Object> refuseApply(Long ruFlow, Long userId);
}
