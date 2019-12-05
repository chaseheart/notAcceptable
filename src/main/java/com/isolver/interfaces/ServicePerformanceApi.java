package com.isolver.interfaces;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.isolver.common.util.Result;

@RequestMapping("/weixin/servicePerformance")
public interface ServicePerformanceApi {
	
	/**
	 * 考勤一览初期化
	 * @param id
	 * @param oaYear
	 * @param oaMonth
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Result<Object> searchSP(Long id, Integer oaYear, Integer oaMonth);
	
	/**
	 * 申请初期化
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/init", method = RequestMethod.POST)
	public Result<Object> init(Long id);
	
	/**
	 * 考勤详情
	 * @param id
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public Result<Object> getDetail(Long id, Long user);
}
