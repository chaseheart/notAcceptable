package com.isolver.dto;

import com.isolver.common.util.Dateutil;
import com.isolver.entity.UnusualAttendance;

/**
 * @author IS1907005
 * @date 2019/11/25
 * @class UnusualPerformance.java
 */
public class UnusualPerformanceDto {
	
	/** ·申请者工号  **/
	private String userWorkId;
	/** ·申请人姓名  **/
	private String username;
	
	/** 。非正常考勤时间 **/
	private String unusualAttendanceDate;
	/** 。非正常考勤类型 **/
	private Integer unusualAttendanceType;
	/** 。实体单据有无 **/
	private Boolean hasDocument;
	/** 。非正常考勤原因 **/
	private String reason;

	/**
	 * @return the unusualAttendanceDate
	 */
	public String getUnusualAttendanceDate() {
		return unusualAttendanceDate;
	}

	/**
	 * @param unusualAttendanceDate the unusualAttendanceDate to set
	 */
	public void setUnusualAttendanceDate(String unusualAttendanceDate) {
		this.unusualAttendanceDate = unusualAttendanceDate;
	}

	/**
	 * @return the unusualAttendanceType
	 */
	public Integer getUnusualAttendanceType() {
		return unusualAttendanceType;
	}

	/**
	 * @param unusualAttendanceType the unusualAttendanceType to set
	 */
	public void setUnusualAttendanceType(Integer unusualAttendanceType) {
		this.unusualAttendanceType = unusualAttendanceType;
	}

	/**
	 * @return the hasDocument
	 */
	public Boolean getHasDocument() {
		return hasDocument;
	}

	/**
	 * @param hasDocument the hasDocument to set
	 */
	public void setHasDocument(Boolean hasDocument) {
		this.hasDocument = hasDocument;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
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
	 * UnusualPerformanceDto.java
	 * 
	 * @param user
	 * @param unusualAttendanceDate
	 * @param unusualAttendanceType
	 * @param hasDocument
	 * @param reason                2019/11/25
	 */
	public UnusualPerformanceDto(UnusualAttendance unusualAttendance) {
		super();
		this.userWorkId = unusualAttendance.getUser().getWorkId();
		this.username = unusualAttendance.getUser().getUsername();
		this.unusualAttendanceDate = Dateutil.getDate(unusualAttendance.getUnusualAttendanceDate());
		this.unusualAttendanceType = unusualAttendance.getUnusualAttendanceType();
		this.hasDocument = unusualAttendance.getHasDocument();
		this.reason = unusualAttendance.getReason();
	}

}
