package com.isolver.dto;

/**
 * @author IS1907005
 * @date 2019/11/12
 * @class DepartDto.java
 */
public class DepartDto {
	private String id;
	private String departName;

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
	 * DepartDto.java
	 * @param id
	 * @param departName
	 * 2019/11/12
	 */
	public DepartDto(String id, String departName) {
		super();
		this.id = id;
		this.departName = departName;
	}
	
	
}
