package com.isolver.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author IS1907005
 * @date 2019/11/06
 * @class OutBusiness.java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "outBusiness")
@NamedQuery(name = "OutBusiness.findAll", query = "SELECT o FROM OutBusiness o")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class OutBusiness implements Serializable {
	/** ID **/
	private Long id;
	/** 。申请者 **/
	private User user;
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
	private Date outBusinessStart;
	/** 。外出公务结束时间 **/
	private Date outBusinessEnd;
	/** 。备注 **/
	private String content;
	/** 。ru外联表 **/
	private RuFlow ruFlow;
	/** 。考勤记录 **/
	private ServicePerformance servicePerformance;

	/**
	 * @return the id
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the user
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user", nullable = false)
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the businessReason
	 */
	@Column(name = "businessReason", nullable = false)
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
	@Column(name = "businessPlace", nullable = false)
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
	@Column(name = "placeDetail")
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
	@Column(name = "contactName")
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
	@Column(name = "contactPhone")
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
	@Column(name = "outType", nullable = false)
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
	@Column(name = "outBusinessStart", nullable = false)
	@Temporal(TemporalType.DATE)
	public Date getOutBusinessStart() {
		return outBusinessStart;
	}

	/**
	 * @param outBusinessStart the outBusinessStart to set
	 */
	public void setOutBusinessStart(Date outBusinessStart) {
		this.outBusinessStart = outBusinessStart;
	}

	/**
	 * @return the outBusinessEnd
	 */
	@Column(name = "outBusinessEnd", nullable = false)
	@Temporal(TemporalType.DATE)
	public Date getOutBusinessEnd() {
		return outBusinessEnd;
	}

	/**
	 * @param outBusinessEnd the outBusinessEnd to set
	 */
	public void setOutBusinessEnd(Date outBusinessEnd) {
		this.outBusinessEnd = outBusinessEnd;
	}

	/**
	 * @return the content
	 */
	@Column(name = "content")
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
	 * @return the ruFlow
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ruFlowId", nullable = false)
	public RuFlow getRuFlow() {
		return ruFlow;
	}

	/**
	 * @param ruFlow the ruFlow to set
	 */
	public void setRuFlow(RuFlow ruFlow) {
		this.ruFlow = ruFlow;
	}

	/**
	 * @return the servicePerformance
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "servicePerformance", nullable = false)
	public ServicePerformance getServicePerformance() {
		return servicePerformance;
	}

	/**
	 * @param servicePerformance the servicePerformance to set
	 */
	public void setServicePerformance(ServicePerformance servicePerformance) {
		this.servicePerformance = servicePerformance;
	}

	/** 。插入者 **/
	private Long insertUserId;
	/** 。插入时间 **/
	private Timestamp insertTime;
	/** 。更新者 **/
	private Long updateUserId;
	/** 。更新时间 **/
	private Timestamp updateTime;
	/** deleteFlag **/
	private Boolean deleteFlag;
	/** 。版本 **/
	private Integer version;

	/**
	 * @return the insertUserId
	 */
	@Column(name = "insertUserId")
	public Long getInsertUserId() {
		return insertUserId;
	}

	/**
	 * @param insertUserId the insertUserId to set
	 */
	public void setInsertUserId(Long insertUserId) {
		this.insertUserId = insertUserId;
	}

	/**
	 * @return the insertTime
	 */
	@Column(name = "insertTime")
	public Timestamp getInsertTime() {
		return insertTime;
	}

	/**
	 * @param insertTime the insertTime to set
	 */
	public void setInsertTime(Timestamp insertTime) {
		this.insertTime = insertTime;
	}

	/**
	 * @return the updateUserId
	 */
	@Column(name = "updateUserId")
	public Long getUpdateUserId() {
		return updateUserId;
	}

	/**
	 * @param updateUserId the updateUserId to set
	 */
	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	/**
	 * @return the updateTime
	 */
	@Column(name = "updateTime")
	public Timestamp getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the deleteFlag
	 */
	@Column(name = "deleteFlag")
	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	/**
	 * @param deleteFlag the deleteFlag to set
	 */
	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	/**
	 * @return the version
	 */
	@Version
	@Column(name = "version")
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}
}
