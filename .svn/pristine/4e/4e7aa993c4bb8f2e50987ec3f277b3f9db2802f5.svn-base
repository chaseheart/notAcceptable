package com.isolver.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.isolver.common.config.AuthenticationFacade;
import com.isolver.entity.User;
import com.isolver.service.ServicePerformanceService;
import com.isolver.service.UserService;

@RestController
@RequestMapping(value = "/excel")
public class ExcelController {

	@Autowired
	private ServicePerformanceService servicePerformanceService;

	@Autowired
	private UserService userService;

	/**
	 * 考勤数据上传
	 * 
	 * @param file
	 * @param id
	 * @throws IOException
	 * @throws ParseException 
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/importData")
	public void importExcelData(@RequestParam("excelFile") MultipartFile file) throws IOException, ParseException {
//		readExcel.getExcelInfo(file);

		Long userId = AuthenticationFacade.getUserId();
		User user = userService.getUserById(userId);

		Sheet sheet = new Sheet(1, 0);
		List<Object> result = EasyExcelFactory.read(file.getInputStream(), sheet);
		servicePerformanceService.insertServicePerformanceByExcel(result, user);
		System.err.print(result);
	}
}
