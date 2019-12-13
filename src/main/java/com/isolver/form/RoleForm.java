package com.isolver.form;

/**
 * ·角色保存
 * @author IS1907011
 *
 */
public class RoleForm {
	/** id **/
	private String id ;
	/**	角色名 **/
	private String roleName;
	/** version **/
	private String version;
	/** ·是否为人事  **/
	private String isManager;
	/**	权限 **/
	private String authority;
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
	 * @return the authority
	 */
	public String getAuthority() {
		return authority;
	}
	/**
	 * @param authority the authority to set
	 */
	public void setAuthority(String authority) {
		this.authority = authority;
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
	
	
}
