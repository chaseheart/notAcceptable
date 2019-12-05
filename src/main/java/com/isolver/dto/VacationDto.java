package com.isolver.dto;

import com.isolver.common.util.Dateutil;
import com.isolver.entity.Vacation;

/**
 * @author IS1907005
 * @date 2019/11/21
 * @class VacationDto.java
 */
public class VacationDto {
	
	/** ·申请者工号  **/
	private String userWorkId;
	/** ·申请人姓名  **/
	private String username;
	
	private Integer vacationType;
	/** 。休假开始时间 **/
	private String vacationStart;
	/** 。休假结束时间 **/
	private String vacationEnd;
	/** 。实体单据有无 **/
	private Boolean hasDocument;
	/** 。请假事由 **/
	private String vacationContent;
	/** 。备注 **/
	private String vacationMark;

	/**
	 * @return the vacationType
	 */
	public Integer getVacationType() {
		return vacationType;
	}

	/**
	 * @param vacationType the vacationType to set
	 */
	public void setVacationType(Integer vacationType) {
		this.vacationType = vacationType;
	}

	/**
	 * @return the vacationStart
	 */
	public String getVacationStart() {
		return vacationStart;
	}

	/**
	 * @param vacationStart the vacationStart to set
	 */
	public void setVacationStart(String vacationStart) {
		this.vacationStart = vacationStart;
	}

	/**
	 * @return the vacationEnd
	 */
	public String getVacationEnd() {
		return vacationEnd;
	}

	/**
	 * @param vacationEnd the vacationEnd to set
	 */
	public void setVacationEnd(String vacationEnd) {
		this.vacationEnd = vacationEnd;
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
	 * @return the vacationContent
	 */
	public String getVacationContent() {
		return vacationContent;
	}

	/**
	 * @param vacationContent the vacationContent to set
	 */
	public void setVacationContent(String vacationContent) {
		this.vacationContent = vacationContent;
	}

	/**
	 * @return the vacationMark
	 */
	public String getVacationMark() {
		return vacationMark;
	}

	/**
	 * @param vacationMark the vacationMark to set
	 */
	public void setVacationMark(String vacationMark) {
		this.vacationMark = vacationMark;
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
	 * VacationDto.java
	 * 
	 * @param vacationType
	 * @param vacationStart
	 * @param vacationEnd
	 * @param hasDocument
	 * @param vacationContent
	 * @param vacationMark    2019/11/21
	 */
	public VacationDto(Vacation vacation) {
		super();
		this.userWorkId = vacation.getUser().getWorkId();
		this.username = vacation.getUser().getUsername();
		this.vacationType = vacation.getVacationType();
		this.vacationStart = Dateutil.getDateTime(vacation.getVacationStart());
		this.vacationEnd = Dateutil.getDateTime(vacation.getVacationEnd());
		this.hasDocument = vacation.getHasDocument();
		this.vacationContent = vacation.getVacationContent();
		this.vacationMark = vacation.getVacationMark();
	}
}
