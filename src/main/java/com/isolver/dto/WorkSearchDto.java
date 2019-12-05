package com.isolver.dto;

/**
 * 社内出勤记录
 * 
 * @author IS1907005
 * @date 2019/11/27
 * @class WorkSearchDto.java
 */
public class WorkSearchDto {
	/** 工号 **/
	private String workId;
	/** 姓名 **/
	private String username;
	/** 部门 **/
	private String departName;
	/** 加班时间 **/
	private String overtimeHour;
	/** 非正常考勤 **/
	private String unusualTime;
	/** 缺勤 **/
	private String lackHour;
	/** 事假 **/
	private String plDay;
	/** 病假 **/
	private String slDay;
	/** 剩余年休 **/
	private String annualLeave;
	/** 剩余调休 **/
	private String paidLeave;
	/** 实勤 **/
	private String performanceActual;

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
	 * @return the annualLeave
	 */
	public String getAnnualLeave() {
		return annualLeave;
	}

	/**
	 * @param annualLeave the annualLeave to set
	 */
	public void setAnnualLeave(String annualLeave) {
		this.annualLeave = annualLeave;
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
	 * @return the paidLeave
	 */
	public String getPaidLeave() {
		return paidLeave;
	}

	/**
	 * @param paidLeave the paidLeave to set
	 */
	public void setPaidLeave(String paidLeave) {
		this.paidLeave = paidLeave;
	}

 

	/**
	 * WorkSearchDto.java
	 * @param workId
	 * @param username
	 * @param departName
	 * @param overtimeHour
	 * @param unusualTime
	 * @param lackHour
	 * @param plDay
	 * @param slDay
	 * @param annualLeave
	 * @param paidLeave
	 * @param performanceActual
	 * 2019/11/28
	 */
	public WorkSearchDto(String workId, String username, String departName, String overtimeHour, String unusualTime, String lackHour, String plDay, String slDay, String annualLeave, String paidLeave,
			String performanceActual) {
		super();
		this.workId = workId;
		this.username = username;
		this.departName = departName;
		this.overtimeHour = overtimeHour;
		this.unusualTime = unusualTime;
		this.lackHour = lackHour;
		this.plDay = plDay;
		this.slDay = slDay;
		this.annualLeave = annualLeave;
		this.paidLeave = paidLeave;
		this.performanceActual = performanceActual;
	}

	/**
	 * WorkSearchDto.java 2019/11/28
	 */
	public WorkSearchDto() {
		super();
	}

}
