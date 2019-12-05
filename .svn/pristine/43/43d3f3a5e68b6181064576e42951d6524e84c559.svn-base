package com.isolver.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.isolver.common.util.JsonUtil;
import com.isolver.service.RoleService;

/**
 * @author IS1907005
 * @date 2019/11/29
 * @class OrgManageController.java
 */
@RestController
@RequestMapping(value = "/roleManage")
public class RoleManageController {

	@Autowired
	private RoleService roleService;

	/**
	 * main初始化数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView indexView(String id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("system/role");
		modelAndView.addObject("roledto", JsonUtil.objectToJSON(roleService.getAllRole()));
		return modelAndView;
	}
}
