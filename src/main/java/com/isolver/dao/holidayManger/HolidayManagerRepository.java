package com.isolver.dao.holidayManger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isolver.entity.Holiday;

/**
 * @author IS1907005
 * @date 2019/11/08
 * @class HolidayRepository.java
 */
@Repository
public interface HolidayManagerRepository extends JpaRepository<Holiday, Long> {
	/**
	 * 根据年月日检索当天休假情况
	 * 
	 * @param year
	 * @param month
	 * @param type
	 * @return
	 */
	public Holiday findByYearAndMonthAndType(Integer year, Integer month, Boolean type);

}
