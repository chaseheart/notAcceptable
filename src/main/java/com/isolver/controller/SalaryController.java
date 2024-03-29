package com.isolver.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author IS1907005
 * @date 2019/12/05
 * @class SalaryController.java
 */
@RestController
@RequestMapping(value = "/salary")
public class SalaryController {
	/**
	 * 薪资导入 初始化数据
	 * 
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_3-8')")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView indexView() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("salary");
		return modelAndView;
	}
}
