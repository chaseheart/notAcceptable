package com.isolver.dto;

/**
 * 一条考勤记录对应的申请数量
 * 
 * @author IS1907005
 * @date 2019/12/11
 * @class SPAppCountDto.java
 */
public class SPAppCountDto {

	/** 考勤记录表Id **/
	private String day;
	/** 休假申请次数 **/
	private String vacationApp;
	/** 非正常考勤申请次数 **/
	private String unusualAttendanceApp;
	/** 加班申请次数 **/
	private String workOvertimeApp;


	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * @return the vacationApp
	 */
	public String getVacationApp() {
		return vacationApp;
	}

	/**
	 * @param vacationApp the vacationApp to set
	 */
	public void setVacationApp(String vacationApp) {
		this.vacationApp = vacationApp;
	}

	/**
	 * @return the unusualAttendanceApp
	 */
	public String getUnusualAttendanceApp() {
		return unusualAttendanceApp;
	}

	/**
	 * @param unusualAttendanceApp the unusualAttendanceApp to set
	 */
	public void setUnusualAttendanceApp(String unusualAttendanceApp) {
		this.unusualAttendanceApp = unusualAttendanceApp;
	}

	/**
	 * @return the workOvertimeApp
	 */
	public String getWorkOvertimeApp() {
		return workOvertimeApp;
	}

	/**
	 * @param workOvertimeApp the workOvertimeApp to set
	 */
	public void setWorkOvertimeApp(String workOvertimeApp) {
		this.workOvertimeApp = workOvertimeApp;
	}

	/**
	 * SPAppCountDto.java
	 * 
	 * @param id
	 * @param vacationApp
	 * @param unusualAttendanceApp
	 * @param workOvertimeApp      2019/12/11
	 */
	public SPAppCountDto(String day, String vacationApp, String unusualAttendanceApp, String workOvertimeApp) {
		super();
		this.day = day;
		this.vacationApp = vacationApp;
		this.unusualAttendanceApp = unusualAttendanceApp;
		this.workOvertimeApp = workOvertimeApp;
	}

	/**
	 * SPAppCountDto.java 2019/12/11
	 */
	public SPAppCountDto() {
		super();
	}

}
