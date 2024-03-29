package com.isolver.dto;

/**
 * @author IS1907005
 * @date 2019/11/12
 * @class DepartOrgDto.java
 */
public class DepartOrgDto {

	/** 部门id */
	private Long id;
	/** 部门名称 */
	private String departName;
	/** 所属组织 */
	private String orgName;

	/**
	 * @return the id
	 */
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
	 * @return the departName
	 */
	public String getDepartName() {
		return departName;
	}

	/**
	 * @param departName the departName to set
	 */
	public void setDepartName(String departName) {
		this.departName = departName;
	}

	/**
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * DepartOrgDto.java
	 * 
	 * @param id
	 * @param departName
	 * @param orgName
	 * 
	 */
	public DepartOrgDto(String id, String departName, String orgName) {
		super();
		this.id = Long.valueOf(id);
		this.departName = departName;
		this.orgName = orgName;
	}
}
