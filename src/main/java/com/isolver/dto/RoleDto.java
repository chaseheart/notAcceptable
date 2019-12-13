package com.isolver.dto;

import java.util.List;

import com.isolver.entity.Role;

public class RoleDto {
	/** ID **/
	private Long id;
	/** 。角色名 **/
	private String roleName;
	/** ·是否为人事部  **/
	private String isManager;
	/** version **/
	private String version;
	
	private List<AuthorityDto> authorityList;

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
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the authorityList
	 */
	public List<AuthorityDto> getAuthorityList() {
		return authorityList;
	}

	/**
	 * @param authorityList the authorityList to set
	 */
	public void setAuthorityList(List<AuthorityDto> authorityList) {
		this.authorityList = authorityList;
	}

	
	/**
	 * @return the isManager
	 */
	public String getIsManager() {
		return isManager;
	}

	/**
	 * @param isManager the isManager to set
	 */
	public void setIsManager(String isManager) {
		this.isManager = isManager;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @param id
	 * @param roleName
	 * @param authorityList
	 */
	public RoleDto(Role role, List<AuthorityDto> authorityList) {
		super();
		this.id = role.getId();
		this.roleName = role.getRoleName();
		this.isManager = role.getIsManager().toString();
		this.version = role.getVersion().toString();
		this.authorityList = authorityList;
	}
	
	
}
