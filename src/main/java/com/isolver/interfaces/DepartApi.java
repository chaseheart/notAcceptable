package com.isolver.interfaces;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.isolver.common.util.Result;

@RequestMapping("/weixin")
public interface DepartApi {

	/**
	 *   获得部门
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getDepart", method = RequestMethod.POST)
	public Result<Object> getDepart(Long id);
}
