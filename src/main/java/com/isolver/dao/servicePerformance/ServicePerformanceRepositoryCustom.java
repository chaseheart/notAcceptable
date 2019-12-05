package com.isolver.dao.servicePerformance;

import java.util.Date;
import java.util.List;

import com.isolver.dto.ServicePerformanceDto;
import com.isolver.dto.WorkSearchDto;
import com.isolver.dto.wechat.WechatServicePerformanceDto;
import com.isolver.entity.User;
import com.isolver.form.WorkConditionForm;

/**
 * @author IS1907005
 * @date 2019/11/08
 * @class ServicePerformanceRepositoryCustom.java
 */
public interface ServicePerformanceRepositoryCustom {
	/**
	 * 
	 * @param user
	 * @param monthStart
	 * @param monthEnd
	 * @param deleteFlag
	 * @return
	 */
	public List<ServicePerformanceDto> findServicePerformanceByUserAndDeleteFlag(Long userId, Date monthStart, Date monthEnd, Boolean deleteFlag);

	/**
	 * 
	 * @param id
	 * @param deleteFlag
	 * @return
	 */
	public WechatServicePerformanceDto findServicePerformanceById(User user, Long id, Boolean deleteFlag);

	/**
	 * 出勤记录一览
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<WorkSearchDto> getAllWorkSearchDto(Date startDate, Date endDate, WorkConditionForm workConditionForm);

}
