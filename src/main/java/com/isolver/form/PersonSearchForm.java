package com.isolver.form;

/**
 * @author IS1907005
 * @date 2019/11/29
 * @class PersonSearchForm.java
 */
public class PersonSearchForm {

	/** 工号 **/
	private String workId;
	/** 姓名 **/
	private String username;
	/** 组织 **/
	private Long org;
	/** 部门 **/
	private Long depart;

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
	 * @return the org
	 */
	public Long getOrg() {
		return org;
	}

	/**
	 * @param org the org to set
	 */
	public void setOrg(Long org) {
		this.org = org;
	}

	/**
	 * @return the depart
	 */
	public Long getDepart() {
		return depart;
	}

	/**
	 * @param depart the depart to set
	 */
	public void setDepart(Long depart) {
		this.depart = depart;
	}

}
