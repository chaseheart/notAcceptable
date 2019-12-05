/**
 * 
 */
package com.isolver.common.util;

import java.io.Serializable;

/**
 * 返回结果集工具类
 * @author 游传松
 * 2018/12/12
 */
public class Result<T> implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	//返回状态code
	private Integer status;
	//返回数据
	private T data;
	//返回信息
	private String msg;
	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * 只返回数据，默认为成功status:200  msg:success
	 * @param data 结果数据
	 */
	public Result(T data) {
		this.status = 200;
		this.data = data;
		this.msg = "success";
	}
	/**
	 * 返回状态值
	 * @param status 状态code
	 */
	public Result(Integer status) {
		this.status = status;
		this.data = null;
		this.msg = null;
	}
	/**
	 * 返回状态和结果集
	 * @param status 状态code
	 * @param data 结果数据
	 */
	public Result(Integer status,T data) {
		this.status = status;
		this.data = data;
		this.msg = null;
	}
	/**
	 * 返回状态值和信息
	 * @param status 状态code
	 * @param msg 信息
	 */
	
	public Result(Integer status,String msg) {
		this.status = status;
		this.data = null;
		this.msg = msg;
	}
	
	/**
	 * 返回完整结果集
	 * @param status 状态code
	 * @param data 结果数据
	 * @param msg 信息
	 */
	public Result(Integer status,T data,String msg) {
		this.status = status;
		this.data = data;
		this.msg = msg;
	}
	
	
	public Result() {
		
	}

}
