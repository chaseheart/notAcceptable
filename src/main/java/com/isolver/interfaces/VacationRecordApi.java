package com.isolver.interfaces;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.isolver.common.util.Result;

@RequestMapping("/weixin/vacationRecord")
public interface VacationRecordApi {

	/**
	   *  休假查看
	 * @param userId 用户id
	 * @param date 时间
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Result<Object> getVacationRecord(Long userId, String date);
}
