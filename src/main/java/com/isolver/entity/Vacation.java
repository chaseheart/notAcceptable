package com.isolver.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author IS1907005
 * @date 2019/11/06
 * @class Vacation.java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "vacation")
@NamedQuery(name = "Vacation.findAll", query = "SELECT v FROM Vacation v")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class Vacation implements Serializable {
	/** ID **/
	private Long id;
	/** 。申请者 **/
	private User user;
	/** 。休假类型 **/
	// 1事假 2病假 3产假 4产检假
	// 5陪产假 6丧假 7婚假
	private Integer vacationType;
	/** 。休假开始时间 **/
	private Timestamp vacationStart;
	/** 。休假结束时间 **/
	private Timestamp vacationEnd;
	/** 。实体单据有无 **/
	private Boolean hasDocument;
	/** 。请假事由 **/
	private String vacationContent;
	/** 。备注 **/
	private String vacationMark;
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
	 * @return the vacationType
	 */
	@Column(name = "vacationType", nullable = false)
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
	@Column(name = "vacationStart", nullable = false)
	public Timestamp getVacationStart() {
		return vacationStart;
	}

	/**
	 * @param vacationStart the vacationStart to set
	 */
	public void setVacationStart(Timestamp vacationStart) {
		this.vacationStart = vacationStart;
	}

	/**
	 * @return the vacationEnd
	 */
	@Column(name = "vacationEnd", nullable = false)
	public Timestamp getVacationEnd() {
		return vacationEnd;
	}

	/**
	 * @param vacationEnd the vacationEnd to set
	 */
	public void setVacationEnd(Timestamp vacationEnd) {
		this.vacationEnd = vacationEnd;
	}

	/**
	 * @return the hasDocument
	 */
	@Column(name = "hasDocument", nullable = false)
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
	@Column(name = "vacationContent", nullable = false)
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
	 * @return the vacationMrak
	 */
	@Column(name = "vacationMark", nullable = false)
	public String getVacationMark() {
		return vacationMark;
	}

	/**
	 * @param vacationMrak the vacationMrak to set
	 */
	public void setVacationMark(String vacationMark) {
		this.vacationMark = vacationMark;
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
