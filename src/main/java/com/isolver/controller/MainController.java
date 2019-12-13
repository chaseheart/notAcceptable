package com.isolver.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.isolver.common.config.AuthenticationFacade;
import com.isolver.common.util.JsonUtil;
import com.isolver.dao.oaFlowStep.OaFlowStepRepository;
import com.isolver.dto.OrgDto;
import com.isolver.dto.UserDto;
import com.isolver.entity.OaFlowStep;
import com.isolver.entity.User;
import com.isolver.service.AuthorityService;
import com.isolver.service.OrganizationService;
import com.isolver.service.UserService;

@RestController
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private AuthorityService authorityService;
	
	/**
	 * main初始化数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView indexView2() {
		ModelAndView modelAndView = new ModelAndView("forward:/main");
		return modelAndView;

	}

	/**
	 * main初始化数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView indexView() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("main");
		Long id = AuthenticationFacade.getUserId();
		User user = userService.getUserById(id);
		List<OrgDto> orgdto = organizationService.findAllOrg();
		List<String> authority  = authorityService.getUserAuthority(user);
		// ·该角色是否有后续步骤
		List<Integer> rolelist = userService.getNextUser(user);
		if(rolelist.size() != 0) {
			modelAndView.addObject("orgdto", JsonUtil.getJsonArray(orgdto));
			modelAndView.addObject("userList", JsonUtil.getJsonArray(new UserDto(user)));
		}
		modelAndView.addObject("org", JsonUtil.getJsonArray(orgdto));
		modelAndView.addObject("authority", JsonUtil.getJsonArray(authority));
		modelAndView.addObject("username", user.getUsername());

		return modelAndView;

	}

}
