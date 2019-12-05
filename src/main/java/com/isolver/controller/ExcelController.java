package com.isolver.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.isolver.common.config.AuthenticationFacade;
import com.isolver.common.constant.ExcelConst;
import com.isolver.common.util.Dateutil;
import com.isolver.common.util.ExcelUtil;
import com.isolver.dto.ServicePerformanceDto;
import com.isolver.dto.WorkSearchDto;
import com.isolver.entity.Holiday;
import com.isolver.entity.User;
import com.isolver.form.WorkConditionForm;
import com.isolver.service.HolidayManageService;
import com.isolver.service.ServicePerformanceService;
import com.isolver.service.UserService;

@RestController
@RequestMapping(value = "/excel")
public class ExcelController {

	@Autowired
	private ServicePerformanceService servicePerformanceService;

	@Autowired
	private UserService userService;

	@Autowired
	private ExcelUtil excelUtil;
	@Autowired
	private HolidayManageService holidayManageService;

	/**
	 * 考勤数据上传
	 * 
	 * @param file
	 * @param id
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/importData")
	public void importExcelData(@RequestParam("excelFile") MultipartFile file) throws IOException {
//		readExcel.getExcelInfo(file);

		Long userId = AuthenticationFacade.getUserId();
		User user = userService.getUserById(userId);

		Sheet sheet = new Sheet(1, 0);
		List<Object> result = EasyExcelFactory.read(file.getInputStream(), sheet);
		servicePerformanceService.insertServicePerformanceByExcel(result, user);
		System.err.print(result);
	}

	/**
	 * 导出出勤时间
	 * 
	 * @param WorkConditionForm
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/exportData")
	@ResponseBody
	public void exportExcelData(@ModelAttribute WorkConditionForm WorkConditionForm, HttpServletResponse response) throws IOException {

		WorkConditionForm.setDate("2019-10");
		String fileName = ExcelConst.SERVICEPERFORMANCE;

		List<WorkSearchDto> dtoList = servicePerformanceService.getWorkSearchByCondition(WorkConditionForm);

		response.setContentType("application/x-xls");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
		ServletOutputStream out = response.getOutputStream();
		ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);

		Sheet sheet1 = new Sheet(1, 0, WorkSearchDto.class);
		sheet1.setSheetName(ExcelConst.SUM);

		writer.write(dtoList, sheet1);
		writer.finish();

		out.flush();
		out.close();
	}

	/**
	 * 导出加班 时间
	 * 
	 * @param WorkConditionForm
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/excelOverWorkDown")
	@ResponseBody
	public void excelOverWorkDown(@ModelAttribute WorkConditionForm WorkConditionForm, HttpServletResponse response) throws IOException {

		WorkConditionForm.setDate("2019-10");
		String fileName = ExcelConst.OVERTIME;

		response.setContentType("application/x-xls");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

		ServletOutputStream out = response.getOutputStream();
		ExcelWriter writer = EasyExcelFactory.getWriter(out);

		Sheet sheet1 = new Sheet(1, 0);
		sheet1.setSheetName(ExcelConst.SUM);

		Table table = new Table(1);

		table.setHead(ExcelUtil.createOvertimeListStringHead());
		writer.write1(new ArrayList<>(), sheet1, table);
		writer.finish();

		out.flush();
		out.close();
	}

	/**
	 * 个人考勤时间
	 * 
	 * @param WorkConditionForm
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/excelPersonalDown/{workId}/{oaYear}/{oaMonth}")
	@ResponseBody
	public void excelAttendenceDown(@PathVariable String workId, @PathVariable Integer oaYear, @PathVariable Integer oaMonth, HttpServletResponse response) throws IOException {

		String fileName = ExcelConst.OVERTIME;

		response.setContentType("application/xls");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");

		ServletOutputStream out = response.getOutputStream();

		User user = userService.findUserByworkId(workId);

		Calendar cal = Calendar.getInstance();
		cal.setTime(Dateutil.localDateToDate(oaYear, oaMonth + 1, 1));
		int firstDay = cal.getMinimum(Calendar.DATE);
		int lastDay = cal.getActualMaximum(Calendar.DATE);
		Date dateStart = Dateutil.localDateToDate(oaYear, oaMonth + 1, firstDay);
		Date dateEnd = Dateutil.getEndTimeOfDate(Dateutil.localDateToDate(oaYear, oaMonth + 1, lastDay));

		Holiday holiday = holidayManageService.findOneMonth(user.getHolidayType(), oaYear, oaMonth + 1);

		List<ServicePerformanceDto> dtoList = servicePerformanceService.findServicePerformanceList(user.getId(), dateStart, dateEnd);
		excelUtil.createAttendence(out, oaYear + "-" + (oaMonth + 1), user.getUsername(), user.getWorkId(), user.getDepart().getDepartName(), dtoList, holiday.getRestDay());

	}
}
