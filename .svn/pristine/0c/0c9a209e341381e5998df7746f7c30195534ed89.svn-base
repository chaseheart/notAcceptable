package com.isolver.dto;

import com.isolver.common.util.Dateutil;
import com.isolver.entity.OutBusiness;

/**
 * @author IS1907005
 * @date 2019/11/28
 * @class OutBusinessDto.java
 */
public class OutBusinessDto {
	
	/** ·申请者工号  **/
	private String userWorkId;
	/** ·申请人姓名  **/
	private String username;
	
	/** 。外出公务事由 **/
	private String businessReason;
	/** 。外出公务地点 **/
	private String businessPlace;
	/** 。具体地址 **/
	private String placeDetail;
	/** 。联系人 **/
	private String contactName;
	/** 。联系人电话 **/
	private String contactPhone;
	/** 。外出公务类型 **/
	private Integer outType;
	/** 。外出公务开始时间 **/
	private String outBusinessStart;
	/** 。外出公务结束时间 **/
	private String outBusinessEnd;
	/** 。备注 **/
	private String content;

	/**
	 * @return the businessReason
	 */
	public String getBusinessReason() {
		return businessReason;
	}

	/**
	 * @param businessReason the businessReason to set
	 */
	public void setBusinessReason(String businessReason) {
		this.businessReason = businessReason;
	}

	/**
	 * @return the businessPlace
	 */
	public String getBusinessPlace() {
		return businessPlace;
	}

	/**
	 * @param businessPlace the businessPlace to set
	 */
	public void setBusinessPlace(String businessPlace) {
		this.businessPlace = businessPlace;
	}

	/**
	 * @return the placeDetail
	 */
	public String getPlaceDetail() {
		return placeDetail;
	}

	/**
	 * @param placeDetail the placeDetail to set
	 */
	public void setPlaceDetail(String placeDetail) {
		this.placeDetail = placeDetail;
	}

	/**
	 * @return the contactName
	 */
	public String getContactName() {
		return contactName;
	}

	/**
	 * @param contactName the contactName to set
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	/**
	 * @return the contactPhone
	 */
	public String getContactPhone() {
		return contactPhone;
	}

	/**
	 * @param contactPhone the contactPhone to set
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	/**
	 * @return the outType
	 */
	public Integer getOutType() {
		return outType;
	}

	/**
	 * @param outType the outType to set
	 */
	public void setOutType(Integer outType) {
		this.outType = outType;
	}

	/**
	 * @return the outBusinessStart
	 */
	public String getOutBusinessStart() {
		return outBusinessStart;
	}

	/**
	 * @param outBusinessStart the outBusinessStart to set
	 */
	public void setOutBusinessStart(String outBusinessStart) {
		this.outBusinessStart = outBusinessStart;
	}

	/**
	 * @return the outBusinessEnd
	 */
	public String getOutBusinessEnd() {
		return outBusinessEnd;
	}

	/**
	 * @param outBusinessEnd the outBusinessEnd to set
	 */
	public void setOutBusinessEnd(String outBusinessEnd) {
		this.outBusinessEnd = outBusinessEnd;
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
	 * OutBusinessDto.java
	 * 
	 * @param user
	 * @param businessReason
	 * @param businessPlace
	 * @param placeDetail
	 * @param contactName
	 * @param contactPhone
	 * @param outType
	 * @param outBusinessStart
	 * @param outBusinessEnd
	 * @param content          2019/11/28
	 */
	public OutBusinessDto(OutBusiness outBusiness) {
		super();
		this.userWorkId = outBusiness.getUser().getWorkId();
		this.username = outBusiness.getUser().getUsername();
		this.businessReason = outBusiness.getBusinessReason();
		this.businessPlace = outBusiness.getBusinessPlace();
		this.placeDetail = outBusiness.getPlaceDetail();
		this.contactName = outBusiness.getContactName();
		this.contactPhone = outBusiness.getContactPhone();
		this.outType = outBusiness.getOutType();
		this.outBusinessStart = outBusiness.getOutBusinessStart().toString();
		this.outBusinessEnd = outBusiness.getOutBusinessEnd().toString();
		this.content = outBusiness.getContent();
	}

}
