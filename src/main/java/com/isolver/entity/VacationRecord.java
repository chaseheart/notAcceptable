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

@SuppressWarnings("serial")
@Entity
@Table(name = "vacation_record")
@NamedQuery(name = "VacationRecord.findAll", query = "SELECT v FROM VacationRecord v")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class VacationRecord implements Serializable {

	/** ID **/
	private Long id;
	/** ·用户 **/
	private User userId;
	/** ·年休(天) **/
	private Integer annualLeave;
	/** ·调休(时) **/
	private Integer paidLeave;
	/** 已使用年休（未扣除） **/
	private Integer leave;
	/** ·日期 **/
	private Date date;

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
	 * @return the userId
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public User getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(User userId) {
		this.userId = userId;
	}

	/**
	 * @return the annualLeave
	 */
	@Column(name = "annual_leave", nullable = false)
	public Integer getAnnualLeave() {
		return annualLeave;
	}

	/**
	 * @param annualLeave the annualLeave to set
	 */
	public void setAnnualLeave(Integer annualLeave) {
		this.annualLeave = annualLeave;
	}

	/**
	 * @return the paidLeave
	 */
	@Column(name = "paid_leave", nullable = false)
	public Integer getPaidLeave() {
		return paidLeave;
	}

	/**
	 * @param paidLeave the paidLeave to set
	 */
	public void setPaidLeave(Integer paidLeave) {
		this.paidLeave = paidLeave;
	}

	/**
	 * @return the date
	 */
	@Column(name = "date", nullable = false)
	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the leave
	 */
	@Column(name = "leave")
	public Integer getLeave() {
		return leave;
	}

	/**
	 * @param leave the leave to set
	 */
	public void setLeave(Integer leave) {
		this.leave = leave;
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
