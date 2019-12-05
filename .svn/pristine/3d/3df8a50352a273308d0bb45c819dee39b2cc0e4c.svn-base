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
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author IS1907005
 * @date 2019/11/06
 * @class WorkOvertime.java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "workOvertime")
@NamedQuery(name = "WorkOvertime.findAll", query = "SELECT w FROM WorkOvertime w")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class WorkOvertime implements Serializable {
	/** ID **/
	private Long id;
	/** 。申请者 **/
	private User user;
	/** 。加班日期 **/
	private Date workOvertimeDate;
	/** 。加班开始时间 **/
	private Timestamp workOvertimeStart;
	/** 。加班加速时间 **/
	private Timestamp workOvertimeEnd;
	/** 。项目编号 **/
	private String projectNo;
	/** 。工作内容 **/
	private String workContent;
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
	 * @return the workOvertimeDate
	 */
	@Column(name = "workOvertimeDate", nullable = false)
	public Date getWorkOvertimeDate() {
		return workOvertimeDate;
	}

	/**
	 * @param workOvertimeDate the workOvertimeDate to set
	 */
	public void setWorkOvertimeDate(Date workOvertimeDate) {
		this.workOvertimeDate = workOvertimeDate;
	}

	/**
	 * @return the workOvertimeStart
	 */
	@Column(name = "workOvertimeStart", nullable = false)
	public Timestamp getWorkOvertimeStart() {
		return workOvertimeStart;
	}

	/**
	 * @param workOvertimeStart the workOvertimeStart to set
	 */
	public void setWorkOvertimeStart(Timestamp workOvertimeStart) {
		this.workOvertimeStart = workOvertimeStart;
	}

	/**
	 * @return the workOvertimeEnd
	 */
	@Column(name = "workOvertimeEnd", nullable = false)
	public Timestamp getWorkOvertimeEnd() {
		return workOvertimeEnd;
	}

	/**
	 * @param workOvertimeEnd the workOvertimeEnd to set
	 */
	public void setWorkOvertimeEnd(Timestamp workOvertimeEnd) {
		this.workOvertimeEnd = workOvertimeEnd;
	}

	/**
	 * @return the projectNo
	 */
	@Column(name = "projectNo", nullable = false)
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
	@Column(name = "workContent", nullable = false)
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
