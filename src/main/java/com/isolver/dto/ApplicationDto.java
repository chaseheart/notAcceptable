package com.isolver.dto;

/**
 * @author IS1907005
 * @date 2019/11/18
 * @class ApplicationDto.java
 */
public class ApplicationDto {

	/** 。申请ID **/
	private String appId;
	/** 。类型 **/
	private String typeDetail;
	/** 。申请开始时间 **/
	private String appStart;
	/** 。申请结束时间 **/
	private String appEnd;
	/** 。审批者 **/
	private String assigner;
	/** 。备注 **/
	private String content;
	/** 状态 **/
	private String state;

	/**
	 * ApplicationDto.java
	 * 
	 * @param appId
	 * @param typeDetail
	 * @param appStart
	 * @param appEnd
	 * @param assigner
	 * @param content    2019/11/18
	 */
	public ApplicationDto(String appId, String typeDetail, String appStart, String appEnd, String assigner, String content, String state) {
		super();
		this.appId = appId;
		this.typeDetail = typeDetail;
		this.appStart = appStart;
		this.appEnd = appEnd;
		this.assigner = assigner;
		this.content = content;
		this.state = state;
	}

	/**
	 * ApplicationDto.java
	 * 
	 * @param appId
	 * @param appStart
	 * @param appEnd
	 * @param assigner
	 * @param content  2019/11/19
	 */
	public ApplicationDto(String appId, String appStart, String appEnd, String assigner, String content, String state) {
		super();
		this.appId = appId;
		this.appStart = appStart;
		this.appEnd = appEnd;
		this.assigner = assigner;
		this.content = content;
		this.state = state;
	}

	/**
	 * ApplicationDto.java
	 * 
	 * @param appId
	 * @param appStart
	 * @param appEnd
	 * @param assigner
	 * @param content  2019/11/19
	 */
	public ApplicationDto(String appId, String appStart, String appEnd, String assigner, String content) {
		super();
		this.appId = appId;
		this.appStart = appStart;
		this.appEnd = appEnd;
		this.assigner = assigner;
		this.content = content;
	}

	/**
	 * ApplicationDto.java
	 * 
	 * @param appId
	 * @param appStart
	 * @param appEnd
	 * @param assigner
	 * @param content  2019/11/19
	 */
	public ApplicationDto(String appId, String typeDetail, String assigner, String content) {
		super();
		this.appId = appId;
		this.typeDetail = typeDetail;
		this.assigner = assigner;
		this.content = content;
	}

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
	 * @return the typeDetail
	 */
	public String getTypeDetail() {
		return typeDetail;
	}

	/**
	 * @param typeDetail the typeDetail to set
	 */
	public void setTypeDetail(String typeDetail) {
		this.typeDetail = typeDetail;
	}

	/**
	 * @return the appStart
	 */
	public String getAppStart() {
		return appStart;
	}

	/**
	 * @param appStart the appStart to set
	 */
	public void setAppStart(String appStart) {
		this.appStart = appStart;
	}

	/**
	 * @return the appEnd
	 */
	public String getAppEnd() {
		return appEnd;
	}

	/**
	 * @param appEnd the appEnd to set
	 */
	public void setAppEnd(String appEnd) {
		this.appEnd = appEnd;
	}

	/**
	 * @return the assigner
	 */
	public String getAssigner() {
		return assigner;
	}

	/**
	 * @param assigner the assigner to set
	 */
	public void setAssigner(String assigner) {
		this.assigner = assigner;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
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

}
