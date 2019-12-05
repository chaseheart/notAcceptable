package com.isolver.dto.wechat;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.isolver.common.util.Dateutil;

/**
 * @author IS1907005
 * @date 2019/11/21
 * @class PendingDto.java
 */
public class PendingDtoWechat {

	/** 。申请编号 **/
	private String appId;
	/** 最后处理日期 **/
	private String appEndTime;
	/** 申请类型 **/
	private String appName;
	/** 申请状态 **/
	private String appState;
	/** 申请开始时间 **/
	private String appStartTime;

	/**
	 * @return the appId
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * @param appId the appId to set
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}

	/**
	 * @return the appEndTime
	 */
	public String getAppEndTime() {
		return appEndTime;
	}

	/**
	 * @param appEndtTime the appEndTime to set
	 */
	public void setAppEndTime(String appEndTime) {
		this.appEndTime = appEndTime;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppState() {
		return appState;
	}

	public void setAppState(String appState) {
		this.appState = appState;
	}

	public String getAppStartTime() {
		return appStartTime;
	}

	public void setAppStartTime(String appStartTime) {
		this.appStartTime = appStartTime;
	}

	public PendingDtoWechat(String appId, String appEndTime, String appName, String appState, String appStartTime) {
		super();
		this.appId = appId;
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sm.parse(appEndTime);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		this.appEndTime = Dateutil.getDate(date);
		this.appName = appName;
		this.appState = appState;
		this.appStartTime = appStartTime;
	}

}
