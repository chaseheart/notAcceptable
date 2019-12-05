package com.isolver.dto.wechat;

/**
 * @author IS1907005
 * @date 2019/08/19
 * @class LoginDto.java
 */
public class LoginDto {
	/** 用户id **/
	private Long id;
	/** 用户名 **/
	private String userName;

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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param username the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * LoginDto.java
	 * 
	 * @param id
	 * @param username 2019/08/19
	 */
	public LoginDto(Long id, String userName) {
		super();
		this.id = id;
		this.userName = userName;
	}

}
