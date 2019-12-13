package com.isolver.dto;

import com.isolver.entity.Authority;
import com.isolver.entity.RoleAuthority;

public class AuthorityDto {
	/** ID **/
	private Long id;
	/** ·菜单名 **/
	private String menuName;
	/** ·菜单code  **/
	private String menuCode;
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
	 * @return the menuName
	 */
	public String getMenuName() {
		return menuName;
	}
	/**
	 * @param menuName the menuName to set
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	/**
	 * @return the menuCode
	 */
	public String getMenuCode() {
		return menuCode;
	}
	/**
	 * @param menuCode the menuCode to set
	 */
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	/**
	 * @param authority
	 */
	public AuthorityDto(Authority authority) {
		super();
		this.id = authority.getId();
		this.menuName = authority.getMenuName();
		this.menuCode = authority.getMenuCode();
	}
	
	/**
	 * 
	 * @param roleAuthority
	 */
	public AuthorityDto(RoleAuthority roleAuthority) {
		this.id = roleAuthority.getAuthority().getId();
		this.menuName = roleAuthority.getAuthority().getMenuName();
		this.menuCode = roleAuthority.getAuthority().getMenuCode();
	}
}
