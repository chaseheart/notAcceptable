package com.isolver.interfaces;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.isolver.common.util.Result;
import com.isolver.form.wechat.ClaimingExpensesForm;

@RequestMapping("/weixin/claimingExpenses")
public interface ClaimingExpensesApi {

	/**
	 * 报销申请初期化
	 * @param userId 用户id
	 * @return
	 */
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public Result<Object> claimingExpensesInit(Long userId);
	
	/**
	 * 报销申请提交
	 * @param claimingExpensesForm 报销表单
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Result<Object> insert(@ModelAttribute ClaimingExpensesForm claimingExpensesForm);
	
	/**
	 * 审批报销申请
	 * @param assigner
	 * @param ruFlow
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/dealApplication", method = RequestMethod.POST)
	public Result<Object> agreeApplication(String assigner, Long ruFlow, Long userId);
	
	/**
	 * 驳回报销
	 * @param ruFlow
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/rejectFlow", method = RequestMethod.POST)
	public Result<Object> rejectFlow(Long ruFlow, Long userId);
	
	/**
	 *  拒絕报销
	 * @param ruFlow
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/refuseApply", method = RequestMethod.POST)
	public Result<Object> refuseApply(Long ruFlow, Long userId);
	
	/**
	 * 获得历史报销列表
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/ruFlowHistory", method = RequestMethod.POST)
	public Result<Object> ruHistory(Long userId);
}
