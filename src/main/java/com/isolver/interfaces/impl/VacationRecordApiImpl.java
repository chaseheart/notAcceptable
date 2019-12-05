package com.isolver.interfaces.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.isolver.common.constant.SysStatusCodeConst;
import com.isolver.common.util.Result;
import com.isolver.interfaces.VacationRecordApi;
import com.isolver.service.wechat.VacationRecordService;

@RestController
public class VacationRecordApiImpl implements VacationRecordApi {

	@Autowired
	private VacationRecordService vacationRecordService;
	
	/**
	   *  休假查看
	 * @param userId 用户id
	 * @param date 时间
	 * @return
	 */
	@Override
	public Result<Object> getVacationRecord(Long userId, String date) {
		return new Result<Object>(SysStatusCodeConst.SUCCESS, vacationRecordService.getRecord(userId, date));
	}
	
}
