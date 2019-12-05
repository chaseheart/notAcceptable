package com.isolver.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * @author IS1907005
 * @date 2019/12/04
 * @class ServiceExcelExportDto.java
 */
public class ServiceExcelExportDto extends BaseRowModel {

	/** 工号 **/
	@ExcelProperty(value = "工号", index = 0)
	private String workId;
	/** 姓名 **/
	@ExcelProperty(value = "姓名", index = 1)
	private String username;
	/** 部门 **/
	@ExcelProperty(value = "部门", index = 2)
	private String departName;
	/** 加班时间 **/
	@ExcelProperty(value = "加班时间[小时]", index = 3)
	private String overtimeHour;
	/** 非正常考勤 **/
	@ExcelProperty(value = "非正常考勤[次]", index = 4)
	private String unusualTime;
	/** 缺勤 **/
	@ExcelProperty(value = "缺勤[小时]", index = 5)
	private String lackHour;
	/** 事假 **/
	@ExcelProperty(value = "事假[天]", index = 6)
	private String plDay;
	/** 病假 **/
	@ExcelProperty(value = "病假[天]", index = 7)
	private String slDay;
	/** 实勤 **/
	@ExcelProperty(value = "实勤 [小时]", index = 8)
	private String performanceActual;
	/** 国际备注 **/
	@ExcelProperty(value = "参考日历", index = 9)
	private String national;
	/**
	 * @return the workId
	 */
	public String getWorkId() {
		return workId;
	}
	/**
	 * @param workId the workId to set
	 */
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the departName
	 */
	public String getDepartName() {
		return departName;
	}
	/**
	 * @param departName the departName to set
	 */
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	/**
	 * @return the overtimeHour
	 */
	public String getOvertimeHour() {
		return overtimeHour;
	}
	/**
	 * @param overtimeHour the overtimeHour to set
	 */
	public void setOvertimeHour(String overtimeHour) {
		this.overtimeHour = overtimeHour;
	}
	/**
	 * @return the unusualTime
	 */
	public String getUnusualTime() {
		return unusualTime;
	}
	/**
	 * @param unusualTime the unusualTime to set
	 */
	public void setUnusualTime(String unusualTime) {
		this.unusualTime = unusualTime;
	}
	/**
	 * @return the lackHour
	 */
	public String getLackHour() {
		return lackHour;
	}
	/**
	 * @param lackHour the lackHour to set
	 */
	public void setLackHour(String lackHour) {
		this.lackHour = lackHour;
	}
	/**
	 * @return the plDay
	 */
	public String getPlDay() {
		return plDay;
	}
	/**
	 * @param plDay the plDay to set
	 */
	public void setPlDay(String plDay) {
		this.plDay = plDay;
	}
	/**
	 * @return the slDay
	 */
	public String getSlDay() {
		return slDay;
	}
	/**
	 * @param slDay the slDay to set
	 */
	public void setSlDay(String slDay) {
		this.slDay = slDay;
	}
	/**
	 * @return the performanceActual
	 */
	public String getPerformanceActual() {
		return performanceActual;
	}
	/**
	 * @param performanceActual the performanceActual to set
	 */
	public void setPerformanceActual(String performanceActual) {
		this.performanceActual = performanceActual;
	}
	/**
	 * @return the national
	 */
	public String getNational() {
		return national;
	}
	/**
	 * @param national the national to set
	 */
	public void setNational(String national) {
		this.national = national;
	}

}
