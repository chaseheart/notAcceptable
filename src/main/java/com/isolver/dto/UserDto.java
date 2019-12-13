package com.isolver.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isolver.entity.OaFlowStep;
import com.isolver.entity.User;
import com.isolver.service.UserService;

public class UserDto {

	/** ID **/
	private Long id;
	/** 。工号 **/
	private String workId;
	/** 。姓名 **/
	private String username;
	/** 。组织 **/
	private String org;
	/** 。部门 **/
	private String depart;
	/** 。部门 **/
	private Long departId;
	/** 。角色是否为末节点 **/
	private Boolean isEnd;
	/** 。角色 **/
	private Long roleId;
	/** 姓名首字母 **/
	private String firstChart;
	/** 电话 **/
	private String phone;
	/** 邮箱 **/
	private String email;
	/** 角色名称 **/
	private String roleName;
	/** 节假日类型 **/
	private Boolean vacationType;

	public UserDto() {

	}

	/**
	 * UserDto.java
	 * 
	 * @param id
	 * @param workId
	 * @param username
	 * @param org
	 * @param depart
	 * @param roleName
	 * @param phone
	 * @param email    2019/11/29
	 */
	public UserDto(String id, String workId, String username, String org, String depart, String roleName, Boolean vacationType) {
		super();
		this.id = Long.parseLong(id);
		this.workId = workId;
		this.username = username;
		this.org = org;
		this.depart = depart;
		this.roleName = roleName;
		this.vacationType = vacationType;
	}

	public UserDto(Long id, String username) {
		super();
		this.id = id;
		this.username = username;
	}
	/**
	 * UserDto.java
	 * 
	 * @param user 2019/11/07
	 */
	public UserDto(User user) {
		super();
		this.id = user.getId();
		this.workId = user.getWorkId();
		this.username = user.getUsername();
		this.org = user.getDepart().getOrg().getOrgName();
		this.depart = user.getDepart().getDepartName();
		this.departId = user.getDepart().getId();
		this.roleId=user.getRole().getId();
		this.phone = user.getPhone();
		this.email = user.getEmail();
		this.setVacationType(user.getHolidayType());
	}
	/**
	 * UserDto.java
	 * 
	 * @param user 2019/11/07
	 */
	public UserDto(User user, Boolean isEnd) {
		super();
		this.id = user.getId();
		this.workId = user.getWorkId();
		this.username = user.getUsername();
		this.org = user.getDepart().getOrg().getOrgName();
		this.depart = user.getDepart().getDepartName();
		this.departId = user.getDepart().getId();
		this.isEnd = isEnd ;
		this.roleId=user.getRole().getId();
		this.phone = user.getPhone();
		this.email = user.getEmail();
		this.setVacationType(user.getHolidayType());
	}

	/**
	 * UserDto.java
	 * 
	 * @param id
	 * @param workId
	 * @param username
	 * @param org
	 * @param depart
	 * @param roleName
	 * @param phone
	 * @param email    2019/11/29
	 */
	public UserDto(Long id, String workId, String username, String org, String depart, String roleName, Boolean vacationType) {
		super();
		this.id = id;
		this.workId = workId;
		this.username = username;
		this.org = org;
		this.depart = depart;
		this.roleName = roleName;
		this.vacationType = vacationType;
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
	 * @return the departId
	 */
	public Long getDepartId() {
		return departId;
	}

	/**
	 * @param departId the departId to set
	 */
	public void setDepartId(Long departId) {
		this.departId = departId;
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
	 * @return the roleId
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
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
	 * @return the isEnd
	 */
	public Boolean getIsEnd() {
		return isEnd;
	}

	/**
	 * @param isEnd the isEnd to set
	 */
	public void setIsEnd(Boolean isEnd) {
		this.isEnd = isEnd;
	}

	/**
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * @param userName
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 
	 * @return
	 */
	public String getFirstChart() {
		return firstChart;
	}

	/**
	 * 
	 * @param firstChart
	 */
	public void setFirstChart(String firstChart) {
		this.firstChart = firstChart;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the vacationType
	 */
	public Boolean getVacationType() {
		return vacationType;
	}

	/**
	 * @param vacationType the vacationType to set
	 */
	public void setVacationType(Boolean vacationType) {
		this.vacationType = vacationType;
	}

}
