package com.isolver.dao.hiFlow;

import java.util.List;

import com.isolver.dto.wechat.ApproveHistoryDto;

/**
 * @author IS1907005
 * @date 2019/11/08
 * @class ServicePerformanceRepositoryCustom.java
 */
public interface HiFlowRepositoryCustom {
	
	/**
	 * 检索报销审核历史
	 * 
	 * @param user
	 * @param deleteFlag
	 * @return
	 */
	public List<ApproveHistoryDto> approveHistory(Long dealPerson, Boolean deleteFlag);

	/**
	 * 检索非正常考勤审核历史
	 * 
	 * @param user
	 * @param deleteFlag
	 * @return
	 */
	public List<ApproveHistoryDto> approveHistoryUnusualAttendance(Long dealPerson, Boolean deleteFlag);

	/**
	 * 检索休假审核历史
	 * 
	 * @param user
	 * @param deleteFlag
	 * @return
	 */
	public List<ApproveHistoryDto> approveHistoryVacation(Long dealPerson, Boolean deleteFlag);

	/**
	 * 检索加班审核历史
	 * 
	 * @param user
	 * @param deleteFlag
	 * @return
	 */
	public List<ApproveHistoryDto> approveHistoryOvertime(Long dealPerson, Boolean deleteFlag);

	/**
	 * 检索外出公务审核历史
	 * 
	 * @param user
	 * @param deleteFlag
	 * @return
	 */
	public List<ApproveHistoryDto> approveHistoryOutBusiness(Long dealPerson, Boolean deleteFlag);

}
