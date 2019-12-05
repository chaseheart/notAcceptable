package com.isolver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.isolver.common.config.AuthenticationFacade;
import com.isolver.entity.User;
import com.isolver.service.UserService;

@RestController
public class AddressBookPCController {

	private static final Logger logger = LoggerFactory.getLogger(AddressBookPCController.class);

	@Autowired
	private UserService userService;

	/**
	 * main初始化数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addressBook", method = RequestMethod.GET)
	public ModelAndView indexView2() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("addressBook");

		return modelAndView;

	}
}
