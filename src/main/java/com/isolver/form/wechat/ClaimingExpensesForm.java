package com.isolver.form.wechat;

import java.math.BigDecimal;
import java.util.List;

public class ClaimingExpensesForm {

	/** ·用户 **/
	private String userId;
	/** ·所属部门 **/
	private String departId;
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
	/** 。审批人 **/
	private String flowUser;
	/** 。文件Id **/
	private List<Long> photo;
	/** 。流程运行Id **/
	private String id;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the departId
	 */
	public String getDepartId() {
		return departId;
	}

	/**
	 * @param departId the departId to set
	 */
	public void setDepartId(String departId) {
		this.departId = departId;
	}

	/**
	 * @return the type
	 */
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
	 * @return the flowUser
	 */
	public String getFlowUser() {
		return flowUser;
	}

	/**
	 * @param flowUser the flowUser to set
	 */
	public void setFlowUser(String flowUser) {
		this.flowUser = flowUser;
	}

	/**
	 * @return the photo
	 */
	public List<Long> getPhoto() {
		return photo;
	}

	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(List<Long> photo) {
		this.photo = photo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

}
