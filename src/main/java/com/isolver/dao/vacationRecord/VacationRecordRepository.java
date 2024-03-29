/**
 * @author Dong
 * VocationRecordRepository.java
 * 2019/11/19
 */
package com.isolver.dao.vacationRecord;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isolver.dto.wechat.WechatVacationRecordDto;
import com.isolver.entity.User;
import com.isolver.entity.VacationRecord;

@Repository
public interface VacationRecordRepository extends JpaRepository<VacationRecord, Long> {

	/**
	 * 获得所有休假情况
	 * 
	 * @param user
	 * @param date
	 * @param deleteFlag
	 * 
	 * @return WechatVacationRecordDto
	 */
	@Query(" SELECT new com.isolver.dto.wechat.WechatVacationRecordDto(a.annualLeave as annualLeave, a.paidLeave as paidLeave, a.date as date) FROM VacationRecord a WHERE a.userId.id = :user and a.date <= :date and a.deleteFlag = :deleteFlag ORDER BY a.date DESC ")
	public List<WechatVacationRecordDto> getByUserAndDate(@Param("user") Long user, @Param("date") Date date, @Param("deleteFlag") Boolean deleteFlag);

	/**
	 * 获得所有休假情况
	 * 
	 * @param user
	 * @param date
	 * @param deleteFlag
	 * 
	 * @return WechatVacationRecordDto
	 */
	@Query(" SELECT new com.isolver.dto.wechat.WechatVacationRecordDto(a.annualLeave as annualLeave, a.paidLeave as paidLeave, a.date as date) FROM VacationRecord a WHERE a.userId.id = :user and a.date = :date and a.deleteFlag = :deleteFlag ")
	public WechatVacationRecordDto getVacationByDate(@Param("user") Long user, @Param("date") Date date, @Param("deleteFlag") Boolean deleteFlag);

	/**
	 * 检索当月调休年休
	 * 
	 * @param date
	 * @return
	 */
	public VacationRecord findByUserIdAndDate(User userId, Date date);
}
