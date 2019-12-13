package com.isolver.controller.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.isolver.common.constant.SysStatusCodeConst;
import com.isolver.common.util.JsonUtil;
import com.isolver.common.util.Result;
import com.isolver.form.StepRoleForm;
import com.isolver.service.StepRoleService;

/**
 * @author IS1907005
 * @date 2019/11/29
 * @class OrgManageController.java
 */
@RestController
@RequestMapping(value = "/stepRole")
public class StepRoleController {
	
	@Autowired
	private StepRoleService stepRoleService;

	/**
	 * 通知页面初期化
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_3-7')")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView indexView() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("system/stepRole");
		modelAndView.addObject("init", JsonUtil.objectToJSON(stepRoleService.init()));
		return modelAndView;
	}
	
	/**
	 * 保存
	 * @param form 
	 * @return
	 */
	@RequestMapping(value = "/saveData", method = RequestMethod.POST)
	public Result<Object> saveData(@RequestBody List<StepRoleForm> form) {
		stepRoleService.saveData(form);
		return new Result<>(SysStatusCodeConst.SUCCESS);
	}
	
}
