package com.isolver.dto;

import com.isolver.common.util.Dateutil;
import com.isolver.entity.WorkOvertime;

/**
 * @author IS1907005
 * @date 2019/11/25
 * @class OverTimeDto.java
 */
public class OverTimeDto {
	
	/** ·申请者工号  **/
	private String userWorkId;
	/** ·申请人姓名  **/
	private String username;

	/** 。加班日期 **/
	private String workOvertimeDate;
	/** 。加班开始时间 **/
	private String workOvertimeStart;
	/** 。加班加速时间 **/
	private String workOvertimeEnd;
	/** 。项目编号 **/
	private String projectNo;
	/** 。工作内容 **/
	private String workContent;

	/**
	 * @return the workOvertimeDate
	 */
	public String getWorkOvertimeDate() {
		return workOvertimeDate;
	}

	/**
	 * @param workOvertimeDate the workOvertimeDate to set
	 */
	public void setWorkOvertimeDate(String workOvertimeDate) {
		this.workOvertimeDate = workOvertimeDate;
	}

	/**
	 * @return the workOvertimeStart
	 */
	public String getWorkOvertimeStart() {
		return workOvertimeStart;
	}

	/**
	 * @param workOvertimeStart the workOvertimeStart to set
	 */
	public void setWorkOvertimeStart(String workOvertimeStart) {
		this.workOvertimeStart = workOvertimeStart;
	}

	/**
	 * @return the workOvertimeEnd
	 */
	public String getWorkOvertimeEnd() {
		return workOvertimeEnd;
	}

	/**
	 * @param workOvertimeEnd the workOvertimeEnd to set
	 */
	public void setWorkOvertimeEnd(String workOvertimeEnd) {
		this.workOvertimeEnd = workOvertimeEnd;
	}

	/**
	 * @return the projectNo
	 */
	public String getProjectNo() {
		return projectNo;
	}

	/**
	 * @param projectNo the projectNo to set
	 */
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	/**
	 * @return the workContent
	 */
	public String getWorkContent() {
		return workContent;
	}

	/**
	 * @param workContent the workContent to set
	 */
	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}

	
	/**
	 * @return the userWorkId
	 */
	public String getUserWorkId() {
		return userWorkId;
	}

	/**
	 * @param userWorkId the userWorkId to set
	 */
	public void setUserWorkId(String userWorkId) {
		this.userWorkId = userWorkId;
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
	 * OverTimeDto.java
	 * 
	 * @param workOvertimeDate
	 * @param workOvertimeStart
	 * @param workOvertimeEnd
	 * @param projectNo
	 * @param workContent       2019/11/25
	 */
	public OverTimeDto(WorkOvertime workOvertime) {
		super();
		this.userWorkId = workOvertime.getUser().getWorkId();
		this.username = workOvertime.getUser().getUsername();
		this.workOvertimeDate = Dateutil.getDate(workOvertime.getWorkOvertimeDate());
		this.workOvertimeStart = workOvertime.getWorkOvertimeStart().toString();
		this.workOvertimeEnd = workOvertime.getWorkOvertimeEnd().toString();
		this.projectNo = workOvertime.getProjectNo();
		this.workContent = workOvertime.getWorkContent();
	}

}
