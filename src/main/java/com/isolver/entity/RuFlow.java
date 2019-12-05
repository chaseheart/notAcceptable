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
 * @class RuFlow.java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "ruFlow")
@NamedQuery(name = "RuFlow.findAll", query = "SELECT r FROM RuFlow r")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class RuFlow implements Serializable {
	/** ID **/
	private Long id;
	/** 。流程ID **/
	private OaFlow flowId;
	/** act运行ID **/
	private String actRunFlowId;
	/** 。任务ID **/
	private Long taskId;// TODO 问ccy调用act task id
	/** 。状态 **/
	private Integer state;
	/** 。处理人 **/
	private User dealPeople;
	/** 。当前处理人 **/
	private User dealPeopleNow;

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
	 * @return the actId
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "flowId", nullable = false)
	public OaFlow getFlowId() {
		return flowId;
	}

	/**
	 * @param actId the actId to set
	 */
	public void setFlowId(OaFlow flowId) {
		this.flowId = flowId;
	}

	/**
	 * @return the taskId
	 */
	@Column(name = "taskId")
	public Long getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	/**
	 * @return the state
	 */
	@Column(name = "state", nullable = false)
	public Integer getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * @return the actRunFlowId
	 */
	@Column(name = "actRunFlowId", nullable = false)
	public String getActRunFlowId() {
		return actRunFlowId;
	}

	/**
	 * @param actRunFlowId the actRunFlowId to set
	 */
	public void setActRunFlowId(String actRunFlowId) {
		this.actRunFlowId = actRunFlowId;
	}

	/**
	 * @return the dealPeople
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dealPeople", nullable = false)
	public User getDealPeople() {
		return dealPeople;
	}

	/**
	 * @param dealPeople the dealPeople to set
	 */
	public void setDealPeople(User dealPeople) {
		this.dealPeople = dealPeople;
	}

	/**
	 * @return the dealPeopleNow
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dealPeopleNow", nullable = false)
	public User getDealPeopleNow() {
		return dealPeopleNow;
	}

	/**
	 * @param dealPeopleNow the dealPeopleNow to set
	 */
	public void setDealPeopleNow(User dealPeopleNow) {
		this.dealPeopleNow = dealPeopleNow;
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
