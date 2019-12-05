package com.isolver.dto;

import org.apache.commons.lang3.StringUtils;

import com.isolver.common.util.Dateutil;
import com.isolver.entity.ServicePerformance;

/**
 * @author IS1907005
 * @date 2019/11/07
 * @class ServicePerformanceDto.java
 */
public class ServicePerformanceDto {
	/** ID **/
	private String id;
	/** 。考勤日期 **/
	private String day;
	/** 。考勤开始时间 **/
	private String oaStart;
	/** 。考勤结束时间 **/
	private String oaEnd;
	/** 。考勤开始最终时间 **/
	private String oaFinalStart;
	/** 。考勤结束最终时间 **/
	private String oaFinalEnd;
	/** 。版本 **/
	private String version;
	/** 。工作时间 **/
	private String hour;
	/** 。考勤状态 **/
	private String state;
	private Integer absence;

	/**
	 * @return the absence
	 */
	public Integer getAbsence() {
		return absence;
	}

	/**
	 * @param absence the absence to set
	 */
	public void setAbsence(Integer absence) {
		this.absence = absence;
	}

	/**
	 * @return the hour
	 */
	public String getHour() {
		return hour;
	}

	/**
	 * @param hour the hour to set
	 */
	public void setHour(String hour) {
		this.hour = hour;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

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
	 * @return the oaStart
	 */
	public String getOaStart() {
		return oaStart;
	}

	/**
	 * @param oaStart the oaStart to set
	 */
	public void setOaStart(String oaStart) {
		this.oaStart = oaStart;
	}

	/**
	 * @return the oaEnd
	 */
	public String getOaEnd() {
		return oaEnd;
	}

	/**
	 * @param oaEnd the oaEnd to set
	 */
	public void setOaEnd(String oaEnd) {
		this.oaEnd = oaEnd;
	}

	/**
	 * @return the oaFinalStart
	 */
	public String getOaFinalStart() {
		return oaFinalStart;
	}

	/**
	 * @param oaFinalStart the oaFinalStart to set
	 */
	public void setOaFinalStart(String oaFinalStart) {
		this.oaFinalStart = oaFinalStart;
	}

	/**
	 * @return the oaFinalEnd
	 */
	public String getOaFinalEnd() {
		return oaFinalEnd;
	}

	/**
	 * @param oaFinalEnd the oaFinalEnd to set
	 */
	public void setOaFinalEnd(String oaFinalEnd) {
		this.oaFinalEnd = oaFinalEnd;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	public ServicePerformanceDto() {
		super();
	}

	/**
	 * ServicePerformanceDto.java
	 * 
	 * @param id
	 * @param day
	 * @param oaStart
	 * @param oaEnd
	 * @param oaFinalStart
	 * @param oaFinalEnd
	 * @param version      2019/11/11
	 */
	public ServicePerformanceDto(String id, String day, String oaStart, String oaEnd, String oaFinalStart, String oaFinalEnd, String version) {
		super();
		this.id = id;
		this.day = day.toString();
		this.oaStart = StringUtils.isNotEmpty(oaStart) ? oaStart.toString() : "";
		this.oaEnd = StringUtils.isNotEmpty(oaEnd) ? oaEnd.toString() : "";
		this.oaFinalStart = StringUtils.isNotEmpty(oaFinalStart) ? oaFinalStart.toString() : "";
		this.oaFinalEnd = StringUtils.isNotEmpty(oaFinalEnd) ? oaFinalEnd.toString() : "";
		this.version = version.toString();
	}

	/**
	 * ServicePerformanceDto.java
	 * 
	 * @param id
	 * @param day
	 * @param oaStart
	 * @param oaEnd
	 * @param oaFinalStart
	 * @param oaFinalEnd
	 * @param version
	 * @param hour
	 * @param state
	 * @param absence      2019/11/11
	 */
	public ServicePerformanceDto(String id, String day, String oaStart, String oaEnd, String oaFinalStart, String oaFinalEnd, String version, String hour, String state) {
		super();
		this.id = id;
		this.day = day;
		this.oaStart = oaStart;
		this.oaEnd = oaEnd;
		this.oaFinalStart = oaFinalStart;
		this.oaFinalEnd = oaFinalEnd;
		this.version = version;
		this.hour = hour;
		this.state = state;
	}

	/**
	 * ServicePerformanceDto.java
	 * 
	 * @param id
	 * @param day
	 * @param oaStart
	 * @param oaEnd
	 * @param oaFinalStart
	 * @param oaFinalEnd
	 * @param version
	 * @param hour
	 * @param state
	 * @param absence      2019/11/21
	 */
	public ServicePerformanceDto(ServicePerformance servicePerformance) {
		super();
		this.id = servicePerformance.getId().toString();
		this.day = servicePerformance.getDay().toString();
		this.oaStart = servicePerformance.getOaStart() == null ? "":servicePerformance.getOaStart().toString();
		this.oaEnd = servicePerformance.getOaEnd() == null ? "":servicePerformance.getOaEnd().toString();
		this.oaFinalStart = servicePerformance.getOaFinalStart() == null ? "":servicePerformance.getOaFinalStart().toString();
		this.oaFinalEnd = servicePerformance.getOaFinalEnd() == null ? "":servicePerformance.getOaFinalEnd().toString();
	}

}
