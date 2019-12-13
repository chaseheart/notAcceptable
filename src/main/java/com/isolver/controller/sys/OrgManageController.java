package com.isolver.controller.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.isolver.common.config.AuthenticationFacade;
import com.isolver.common.util.JsonUtil;
import com.isolver.common.util.Result;
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
	@PreAuthorize("hasRole('ROLE_3-3')")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView indexView(String id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("system/org");
		List<OrgDto> orgList = organizationService.findAllOrg();
		modelAndView.addObject("orgdto", JsonUtil.objectToJSON(orgList));
		return modelAndView;
	}

	@RequestMapping(value = "/setNewOrg", method = RequestMethod.POST)
	public Result<Object> setNewOrg(String orgName) {
		Long userId = AuthenticationFacade.getUserId();
		if (organizationService.setNewOrg(orgName, userId)) {
			return new Result<>("success");
		} else {
			return new Result<>("failure");
		}

	}

	@RequestMapping(value = "/updateOrg", method = RequestMethod.POST)
	public Result<Object> updateOrg(String orgName, String id) {
		Long userId = AuthenticationFacade.getUserId();
		if (organizationService.updateOrg(orgName, userId, id)) {
			return new Result<>("success");
		} else {
			return new Result<>("failure");
		}
	}
	
	/**
	 * 删除一条组织
	 * 
	 * @return
	 */
	@RequestMapping(value = "/deleteOrg", method = RequestMethod.POST)
	public Result<Object> deleteOrg(Long orgId){
		Long userId = AuthenticationFacade.getUserId();
		return organizationService.deleteOrg(orgId, userId);
	}
}
