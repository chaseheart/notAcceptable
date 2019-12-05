package com.isolver.interfaces.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.isolver.common.constant.SysStatusCodeConst;
import com.isolver.common.util.Result;
import com.isolver.dto.OrgDto;
import com.isolver.dto.ServicePerformanceDto;
import com.isolver.dto.wechat.WechatServicePerformanceDto;
import com.isolver.entity.User;
import com.isolver.interfaces.ServicePerformanceApi;
import com.isolver.service.OrganizationService;
import com.isolver.service.ServicePerformanceService;
import com.isolver.service.UserService;
import com.isolver.service.wechat.WechatServicePerformanceService;

@RestController
public class ServicePerformanceApiImpl implements ServicePerformanceApi {

	@Autowired
	private WechatServicePerformanceService servicePerformanceService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ServicePerformanceService servicePerformancePcService;
	
	@Autowired
	private OrganizationService organizationService;
	/**
	 * 根据用户、考勤开始日期、考勤结束日期，获得当月考勤记录
	 * 
	 * @param id      用户id
	 * @param oaYear  考勤开始日期
	 * @param oaMonth 考勤结束日期
	 * @return
	 */
	@Override
	public Result<Object> searchSP(Long id, Integer oaYear, Integer oaMonth) {
		return new Result<Object>(SysStatusCodeConst.SUCCESS,
				servicePerformanceService.findServicePerformance(id, oaYear, oaMonth));
	}

	/**
	 * 申请初期化
	 * @param id
	 * @return
	 */
	@Override
	public Result<Object> init(Long id) {
		List<OrgDto> orgdto = organizationService.findAllOrg();
		ServicePerformanceDto servicePerformanceDto = servicePerformancePcService.findOneServicePerformance(id);
		Map<String, Object> result = new HashMap<>();
		result.put("org", orgdto);
		result.put("servicePerformance", servicePerformanceDto);
		return new Result<Object>(SysStatusCodeConst.SUCCESS, result);
	}

	/**
	 * 考勤详情
	 * @param id
	 * @param user
	 * @return
	 */
	@Override
	public Result<Object> getDetail(Long id, Long user) {
		User userEntity = userService.getUserById(user);
		WechatServicePerformanceDto spDto = servicePerformanceService.findOneServicePerformanceByIdAndUser(id, userEntity);
		return new Result<Object>(SysStatusCodeConst.SUCCESS, spDto);
	}
}
