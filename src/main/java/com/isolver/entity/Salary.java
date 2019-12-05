package com.isolver.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @class Vacation.java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "salary")
@NamedQuery(name = "Salary.findAll", query = "SELECT s FROM Salary s")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class Salary implements Serializable {

	/** ID **/
	private Long id;
	/** 。用户 **/
	private User userId;
	/** 。日期 **/
	private Date date;
	/** 。基础工资 **/
	private BigDecimal salary;
	/** 。学历津贴 **/
	private BigDecimal education;
	/** 。服务年限津贴 **/
	private BigDecimal serviceYears;
	/** 。级别工资 **/
	private BigDecimal levelSalary;
	/** 。日语津贴 **/
	private BigDecimal japanese;
	/** 。保密津贴 **/
	private BigDecimal secret;
	/** 。附加项 **/
	private BigDecimal addSalary;
	/** 。欠勤扣发 **/
	private BigDecimal absenceFromDuty;
	/** 。事假扣发 **/
	private BigDecimal compassionateLeave;
	/** 。病假扣发 **/
	private BigDecimal sickLeave;
	/** 。法定假日加班 **/
	private BigDecimal holidayOvertime;
	/** 。加班费 **/
	private BigDecimal overtime;
	/** 。其他扣发 **/
	private BigDecimal otherWithholding;
	/** 。社保缴费 **/
	private BigDecimal socialSecurity;
	/** 。社保补缴费 **/
	private BigDecimal socialSecuritySupply;
	/** 。公积金缴费 **/
	private BigDecimal accumulationFund;
	/** 。公积金补缴费 **/
	private BigDecimal accumulationFundSupply;
	/** 。保守津贴 **/
	private BigDecimal conservative;
	/** 。绩效工资 **/
	private BigDecimal achievements;
	/** 。加班餐贴 **/
	private BigDecimal meals;
	/** 。所得税 **/
	private BigDecimal incomeTax;
	/** 。独生子女费 **/
	private BigDecimal onlyChild;
	/** 。其他 **/
	private BigDecimal others;
	/** 。摘要 **/
	private String content;

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
	 * @return the salary
	 */
	@Column(name = "salary", nullable = false)
	public BigDecimal getSalary() {
		return salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	/**
	 * @return the education
	 */
	@Column(name = "education", nullable = false)
	public BigDecimal getEducation() {
		return education;
	}

	/**
	 * @param education the education to set
	 */
	public void setEducation(BigDecimal education) {
		this.education = education;
	}

	/**
	 * @return the serviceYears
	 */
	@Column(name = "service_years", nullable = false)
	public BigDecimal getServiceYears() {
		return serviceYears;
	}

	/**
	 * @param serviceYears the serviceYears to set
	 */
	public void setServiceYears(BigDecimal serviceYears) {
		this.serviceYears = serviceYears;
	}

	/**
	 * @return the levelSalary
	 */
	@Column(name = "level_salary", nullable = false)
	public BigDecimal getLevelSalary() {
		return levelSalary;
	}

	/**
	 * @param levelSalary the levelSalary to set
	 */
	public void setLevelSalary(BigDecimal levelSalary) {
		this.levelSalary = levelSalary;
	}

	/**
	 * @return the japanese
	 */
	@Column(name = "japanese", nullable = false)
	public BigDecimal getJapanese() {
		return japanese;
	}

	/**
	 * @param japanese the japanese to set
	 */
	public void setJapanese(BigDecimal japanese) {
		this.japanese = japanese;
	}

	/**
	 * @return the secret
	 */
	@Column(name = "secret", nullable = false)
	public BigDecimal getSecret() {
		return secret;
	}

	/**
	 * @param secret the secret to set
	 */
	public void setSecret(BigDecimal secret) {
		this.secret = secret;
	}

	/**
	 * @return the addSalary
	 */
	@Column(name = "add_salary", nullable = false)
	public BigDecimal getAddSalary() {
		return addSalary;
	}

	/**
	 * @param addSalary the addSalary to set
	 */
	public void setAddSalary(BigDecimal addSalary) {
		this.addSalary = addSalary;
	}

	/**
	 * @return the absenceFromDuty
	 */
	@Column(name = "absence_from_duty", nullable = false)
	public BigDecimal getAbsenceFromDuty() {
		return absenceFromDuty;
	}

	/**
	 * @param absenceFromDuty the absenceFromDuty to set
	 */
	public void setAbsenceFromDuty(BigDecimal absenceFromDuty) {
		this.absenceFromDuty = absenceFromDuty;
	}

	/**
	 * @return the compassionateLeave
	 */
	@Column(name = "compassionate_leave", nullable = false)
	public BigDecimal getCompassionateLeave() {
		return compassionateLeave;
	}

	/**
	 * @param compassionateLeave the compassionateLeave to set
	 */
	public void setCompassionateLeave(BigDecimal compassionateLeave) {
		this.compassionateLeave = compassionateLeave;
	}

	/**
	 * @return the sickLeave
	 */
	@Column(name = "sick_leave", nullable = false)
	public BigDecimal getSickLeave() {
		return sickLeave;
	}

	/**
	 * @param sickLeave the sickLeave to set
	 */
	public void setSickLeave(BigDecimal sickLeave) {
		this.sickLeave = sickLeave;
	}

	/**
	 * @return the holidayOvertime
	 */
	@Column(name = "holiday_overtime", nullable = false)
	public BigDecimal getHolidayOvertime() {
		return holidayOvertime;
	}

	/**
	 * @param holidayOvertime the holidayOvertime to set
	 */
	public void setHolidayOvertime(BigDecimal holidayOvertime) {
		this.holidayOvertime = holidayOvertime;
	}

	/**
	 * @return the overtime
	 */
	@Column(name = "overtime", nullable = false)
	public BigDecimal getOvertime() {
		return overtime;
	}

	/**
	 * @param overtime the overtime to set
	 */
	public void setOvertime(BigDecimal overtime) {
		this.overtime = overtime;
	}

	/**
	 * @return the otherWithholding
	 */
	@Column(name = "other_withholding", nullable = false)
	public BigDecimal getOtherWithholding() {
		return otherWithholding;
	}

	/**
	 * @param otherWithholding the otherWithholding to set
	 */
	public void setOtherWithholding(BigDecimal otherWithholding) {
		this.otherWithholding = otherWithholding;
	}

	/**
	 * @return the socialSecurity
	 */
	@Column(name = "social_security", nullable = false)
	public BigDecimal getSocialSecurity() {
		return socialSecurity;
	}

	/**
	 * @param socialSecurity the socialSecurity to set
	 */
	public void setSocialSecurity(BigDecimal socialSecurity) {
		this.socialSecurity = socialSecurity;
	}

	/**
	 * @return the socialSecuritySupply
	 */
	@Column(name = "social_security_supply", nullable = false)
	public BigDecimal getSocialSecuritySupply() {
		return socialSecuritySupply;
	}

	/**
	 * @param socialSecuritySupply the socialSecuritySupply to set
	 */
	public void setSocialSecuritySupply(BigDecimal socialSecuritySupply) {
		this.socialSecuritySupply = socialSecuritySupply;
	}

	/**
	 * @return the accumulationFund
	 */
	@Column(name = "accumulation_fund", nullable = false)
	public BigDecimal getAccumulationFund() {
		return accumulationFund;
	}

	/**
	 * @param accumulationFund the accumulationFund to set
	 */
	public void setAccumulationFund(BigDecimal accumulationFund) {
		this.accumulationFund = accumulationFund;
	}

	/**
	 * @return the accumulationFundSupply
	 */
	@Column(name = "accumulation_fund_supply", nullable = false)
	public BigDecimal getAccumulationFundSupply() {
		return accumulationFundSupply;
	}

	/**
	 * @param accumulationFundSupply the accumulationFundSupply to set
	 */
	public void setAccumulationFundSupply(BigDecimal accumulationFundSupply) {
		this.accumulationFundSupply = accumulationFundSupply;
	}

	/**
	 * @return the conservative
	 */
	@Column(name = "conservative", nullable = false)
	public BigDecimal getConservative() {
		return conservative;
	}

	/**
	 * @param conservative the conservative to set
	 */
	public void setConservative(BigDecimal conservative) {
		this.conservative = conservative;
	}

	/**
	 * @return the achievements
	 */
	@Column(name = "achievements", nullable = false)
	public BigDecimal getAchievements() {
		return achievements;
	}

	/**
	 * @param achievements the achievements to set
	 */
	public void setAchievements(BigDecimal achievements) {
		this.achievements = achievements;
	}

	/**
	 * @return the meals
	 */
	@Column(name = "meals", nullable = false)
	public BigDecimal getMeals() {
		return meals;
	}

	/**
	 * @param meals the meals to set
	 */
	public void setMeals(BigDecimal meals) {
		this.meals = meals;
	}

	/**
	 * @return the incomeTax
	 */
	@Column(name = "income_tax", nullable = false)
	public BigDecimal getIncomeTax() {
		return incomeTax;
	}

	/**
	 * @param incomeTax the incomeTax to set
	 */
	public void setIncomeTax(BigDecimal incomeTax) {
		this.incomeTax = incomeTax;
	}

	/**
	 * @return the onlyChild
	 */
	@Column(name = "only_child", nullable = false)
	public BigDecimal getOnlyChild() {
		return onlyChild;
	}

	/**
	 * @param onlyChild the onlyChild to set
	 */
	public void setOnlyChild(BigDecimal onlyChild) {
		this.onlyChild = onlyChild;
	}

	/**
	 * @return the others
	 */
	@Column(name = "others", nullable = false)
	public BigDecimal getOthers() {
		return others;
	}

	/**
	 * @param others the others to set
	 */
	public void setOthers(BigDecimal others) {
		this.others = others;
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
