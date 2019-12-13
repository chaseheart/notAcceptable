package com.isolver.interfaces;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.isolver.common.util.Result;

@RequestMapping("/weixin/verify")
public interface VerifyApi {
	
	/**
	 * 取得个人所有报销申请
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/reimbursement/my", method = RequestMethod.POST)
	public Result<Object> verifyInit(Long userId);
	
	/**
	 * 报销审批已完成状态记录取得
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/reimbursement/history", method = RequestMethod.POST)
	public Result<Object> verifyHistoryInit(Long userId);
	
	/**
	 * 报销未办审批状态记录取得
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/reimbursement/examine", method = RequestMethod.POST)
	public Result<Object> verifyExamineInit(Long userId);
	
	/**
	 * 我的申请详情-初始化
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/reimbursement/init", method = RequestMethod.POST)
	public Result<Object> applyInit(Long id);
	
	/**
	 * 我的审核详情-初始化
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/reimbursement/examineInit", method = RequestMethod.POST)
	public Result<Object> examineInit(Long id, Long userId);
	
	/**
	 * 我的申请详情-修改初始化
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/reimbursement/editInit", method = RequestMethod.POST)
	public Result<Object> applyEditInit(Long id, Long userId);
	
	/**
	 * 根据用户检索所有考勤申请
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/examine", method = RequestMethod.POST)
	public Result<Object> verifyMyInit(Long userId);
	
	/**
	 * 根据用户检索所有考勤审批
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/my/init", method = RequestMethod.POST)
	public Result<Object> verifyMineInit(Long userId);
	
	/**
	 * 根据用户检索所有考勤审批历史
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/examineHistory", method = RequestMethod.POST)
	public Result<Object> examineHistoryInit(Long userId);
	
	/**
	 * 根据用户检索所有考勤申请历史
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/applyHistory", method = RequestMethod.POST)
	public Result<Object> applyHistoryInit(Long userId);
}
