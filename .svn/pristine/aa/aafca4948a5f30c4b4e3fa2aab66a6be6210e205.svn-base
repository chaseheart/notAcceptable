package com.isolver.interfaces;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.isolver.common.util.Result;

@RequestMapping("/weixin/salary")
public interface SalaryApi {

	/**
	 * 获得工资单列表
	 * 
	 * @param userId
	 * @param date
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Result<Object> getSalaryList(Long userId, String date);
	
	/**
	 * 删除工资单
	 * 
	 * @param id
	 * @param userId
	 * @param version
	 * @return resultList
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Object> deleteSalary(Long id, Long userId, Integer version);
	
	/**
	 * 获得工资详细
	 * 
	 * @param id
	 * @param userId
	 * @param version
	 * @return resultList
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public Result<Object> getSalaryDetail(Long id);
}
