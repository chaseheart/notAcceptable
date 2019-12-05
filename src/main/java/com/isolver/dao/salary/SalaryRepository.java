/**
 * @author Dong
 * VocationRecordRepository.java
 * 2019/11/19
 */
package com.isolver.dao.salary;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isolver.dto.wechat.SalaryListDto;
import com.isolver.entity.Salary;
import com.isolver.entity.User;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {

	/**
	 * 获得所有休假情况
	 * 
	 * @param user
	 * @param date
	 * @param deleteFlag
	 * 
	 * @return WechatVacationRecordDto
	 */
	@Query(" SELECT new com.isolver.dto.wechat.SalaryListDto(a.id as id, a.date as date, a.version as version) FROM Salary a WHERE a.userId = :user and a.date <= :date and a.deleteFlag = :deleteFlag ORDER BY a.date DESC ")
	public List<SalaryListDto> getSalaryByUserAndDate(@Param("user") User user, @Param("date") Date date, @Param("deleteFlag") Boolean deleteFlag);

}
