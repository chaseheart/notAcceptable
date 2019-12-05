package com.isolver.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @class ClaimingExpenses.java
 * @apiNote 报销
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "claiming_expenses")
@NamedQuery(name = "ClaimingExpenses.findAll", query = "SELECT c FROM ClaimingExpenses c")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class ClaimingExpenses implements Serializable{
	/** ID **/
	private Long id;
	/** ·用户 **/
	private User user;
	/** ·所属部门 **/
	private Department department;
	/** ·申请类型 **/
	private String type;
	/** 。费用分类 **/
	private String expensesType;
	/** 。金额 **/
	private BigDecimal money;
	/** 。项目ID **/
	private String projectId;
	/** 。客户名称 **/
	private String customerName;
	/** 。承担方 **/
	private String expenseCompany;
	/** 。备注 **/
	private String content;
	/** 。ru外联表 **/
	private RuFlow ruFlow;

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
	 * @return the department
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department", nullable = false)
	public Department getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * @return the type
	 */
	@Column(name = "type", nullable = false)
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the expensesType
	 */
	@Column(name = "expensesType", nullable = false)
	public String getExpensesType() {
		return expensesType;
	}

	/**
	 * @param expensesType the expensesType to set
	 */
	public void setExpensesType(String expensesType) {
		this.expensesType = expensesType;
	}

	/**
	 * @return the money
	 */
	@Column(name = "money", nullable = false)
	public BigDecimal getMoney() {
		return money;
	}

	/**
	 * @param money the money to set
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	/**
	 * @return the projectId
	 */
	@Column(name = "projectId", nullable = false)
	public String getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return the customerName
	 */
	@Column(name = "customerName", nullable = false)
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the expenseCompany
	 */
	@Column(name = "expenseCompany", nullable = false)
	public String getExpenseCompany() {
		return expenseCompany;
	}

	/**
	 * @param expenseCompany the expenseCompany to set
	 */
	public void setExpenseCompany(String expenseCompany) {
		this.expenseCompany = expenseCompany;
	}

	/**
	 * @return the content
	 */
	@Column(name = "content", nullable = false)
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
