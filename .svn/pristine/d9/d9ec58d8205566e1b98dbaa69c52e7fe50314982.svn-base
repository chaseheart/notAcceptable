package com.isolver.controller.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.isolver.common.util.JsonUtil;
import com.isolver.dto.OrgDto;
import com.isolver.service.OrganizationService;

/**
 * @author IS1907005
 * @date 2019/11/29
 * @class OrgManageController.java
 */
@RestController
@RequestMapping(value = "/orgManage")
public class OrgManageController {

	@Autowired
	private OrganizationService organizationService;

	/**
	 * main初始化数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView indexView(String id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("system/org");
		List<OrgDto> orgList = organizationService.findAllOrg();
		modelAndView.addObject("orgdto", JsonUtil.objectToJSON(orgList));
		return modelAndView;
	}
}
