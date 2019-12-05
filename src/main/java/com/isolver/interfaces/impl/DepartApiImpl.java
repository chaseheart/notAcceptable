package com.isolver.interfaces.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.isolver.common.constant.SysStatusCodeConst;
import com.isolver.common.util.Result;
import com.isolver.interfaces.DepartApi;
import com.isolver.service.DepartmentService;

@RestController
public class DepartApiImpl implements DepartApi {
	
	@Autowired
	private DepartmentService departmentService;

	/**
	 *   获得部门
	 * @param id
	 * @return
	 */
	@Override
	public Result<Object> getDepart(Long id){
		return new Result<Object>(SysStatusCodeConst.SUCCESS, departmentService.getAimDepartList(id));
	}
	
}
