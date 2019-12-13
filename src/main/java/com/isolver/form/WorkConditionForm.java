package com.isolver.form;

/**
 * @author IS1907005
 * @date 2019/11/28
 * @class WorkConditionForm.java
 */
public class WorkConditionForm {
	private String workId = "";
	private String user = "";
	private String org = "";
	private String depart = "";
	private String date = "";

	/**
	 * @return the workId
	 */
	public String getWorkId() {
		return workId;
	}

	/**
	 * @param workId the workId to set
	 */
	public void setWorkId(String workId) {
		this.workId = workId;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the org
	 */
	public String getOrg() {
		return org;
	}

	/**
	 * @param org the org to set
	 */
	public void setOrg(String org) {
		this.org = org;
	}

	/**
	 * @return the depart
	 */
	public String getDepart() {
		return depart;
	}

	/**
	 * @param depart the depart to set
	 */
	public void setDepart(String depart) {
		this.depart = depart;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * WorkConditionForm.java
	 * @param workId
	 * @param user
	 * @param org
	 * @param depart
	 * @param date
	 * 2019/12/10
	 */
	public WorkConditionForm(String workId, String user, String org, String depart, String date) {
		super();
		this.workId = workId;
		this.user = user;
		this.org = org;
		this.depart = depart;
		this.date = date;
	}

	/**
	 * WorkConditionForm.java
	 * 2019/12/10
	 */
	public WorkConditionForm() {
		super();
	}

}
