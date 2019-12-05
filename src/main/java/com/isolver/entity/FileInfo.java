package com.isolver.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 文件信息
 * 
 * @author IS1907005
 * @date 2019/08/16
 * @class FileInfo.java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "file_info")
@NamedQuery(name = "FileInfo.findAll", query = "SELECT s FROM FileInfo s")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class FileInfo implements Serializable {
	/** 文件编号 **/
	private Long id;
	/** 文件名 **/
	private String fileName;
	/** 文件路径 **/
	private String filePath;
	/** 表名 **/
	private String tableName;
	/** 表id **/
	private String tableId;

	private Boolean deleteFlag;
	private Long insertUserId;
	private Timestamp insertTime;
	private Long updateUserId;
	private Timestamp updateTime;
	private int version;

	/**
	 * @return the id
	 */
	@Id
	@Column(name = "id")
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
	 * @return the fileName
	 */
	@Column(name = "fileName")
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the filePath
	 */
	@Column(name = "filePath")
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the tableName
	 */
	@Column(name = "table_name")
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return the tableId
	 */
	@Column(name = "table_id")
	public String getTableId() {
		return tableId;
	}

	/**
	 * @param tableId the tableId to set
	 */
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	@Column(name = "deleteFlag")
	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Column(name = "insertUserId")
	public Long getInsertUserId() {
		return insertUserId;
	}

	public void setInsertUserId(Long insertUserId) {
		this.insertUserId = insertUserId;
	}

	@Column(name = "insertTime")
	public Timestamp getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Timestamp insertTime) {
		this.insertTime = insertTime;
	}

	@Column(name = "updateUserId")
	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	@Column(name = "updateTime")
	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "version")
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
