package com.isolver.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author IS1907005
 * @date 2019/11/06
 * @class OaFlow.java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "menu")
@NamedQuery(name = "Menu.findAll", query = "SELECT o FROM Menu o")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Menu implements Serializable {
	/** ID **/
	private Long id;
	/** 菜单名 **/
	private String menuName;
	/** 菜单icon **/
	private String menuIcon;
	/** 菜单URL **/
	private String menuUrl;
	
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
	
	public Menu() {
		
	}
	
	public Menu(Long id) {
		super();
		this.id = id;
	}
	
	/**
	 * @return the id
	 */
	@Id
	@Column(name = "id", nullable = false)
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
	 * 
	 * @return
	 */
	@Column(name = "menu_name")
	public String getMenuName() {
		return menuName;
	}

	/**
	 * 
	 * @param menuName
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	/**
	 * 
	 * @return
	 */
	@Column(name = "menu_icon")
	public String getMenuIcon() {
		return menuIcon;
	}

	/**
	 * 
	 * @param menuIcon
	 */
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	/**
	 * 
	 * @return
	 */
	@Column(name = "menu_url")
	public String getMenuUrl() {
		return menuUrl;
	}

	/**
	 * 
	 * @param menuUrl
	 */
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

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
