package com.isolver.controller.sys;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.isolver.common.util.JsonUtil;
import com.isolver.common.util.Result;
import com.isolver.dto.OrgDto;
import com.isolver.dto.UserDto;
import com.isolver.form.PageableForm;
import com.isolver.form.PersonSearchForm;
import com.isolver.service.OrganizationService;
import com.isolver.service.UserService;

/**
 * @author IS1907005
 * @date 2019/11/29
 * @class PersonManageController.java
 */
@RestController
@RequestMapping(value = "/personManage")
public class PersonManageController {

	private static final Logger logger = LoggerFactory.getLogger(PersonManageController.class);

	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private UserService userService;

	/**
	 * main初始化数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView indexView(String id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("system/person");
		List<OrgDto> orgList = organizationService.findAllOrg();
		Page<UserDto> userList = userService.findAllUser();
		modelAndView.addObject("orgdto", JsonUtil.objectToJSON(orgList));
		modelAndView.addObject("userList", JsonUtil.objectToJSON(userList));
		return modelAndView;
	}

	/**
	 * 检索用户数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/searchPersonData", method = RequestMethod.POST)
	public Result<Object> Application(@ModelAttribute PersonSearchForm personSearchForm, @ModelAttribute PageableForm pageableForm) {
		try {
			Map<String, Object> userList = userService.findAllUserByCondition(personSearchForm.getWorkId(), personSearchForm.getUsername(), personSearchForm.getOrg(), personSearchForm.getDepart(),
					pageableForm);
			return new Result<>(userList);
		} catch (Exception e) {
			logger.error("提交申请失败", e);
			throw new RuntimeException(e);
		}
	}
}
