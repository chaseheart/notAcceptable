package com.isolver.dto;

import java.util.List;

import com.isolver.entity.Organization;

/**
 * @author IS1907005
 * @date 2019/11/12
 * @class OrgDto.java
 */
public class OrgDto {
	private String id;
	private String orgName;
	private List<DepartDto> departDto;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the departDto
	 */
	public List<DepartDto> getDepartDto() {
		return departDto;
	}

	/**
	 * @param departDto the departDto to set
	 */
	public void setDepartDto(List<DepartDto> departDto) {
		this.departDto = departDto;
	}

	/**
	 * OrgDto.java
	 * 
	 * @param id
	 * @param orgName 2019/11/12
	 */
	public OrgDto(String id, String orgName) {
		super();
		this.id = id;
		this.orgName = orgName;
	}

	/**
	 * OrgDto.java
	 * 
	 * @param id
	 * @param orgName
	 * @param departDto 2019/11/12
	 */
	public OrgDto(Organization org, List<DepartDto> departDto) {
		super();
		this.id = org.getId().toString();
		this.orgName = org.getOrgName();
		this.departDto = departDto;
	}

}
