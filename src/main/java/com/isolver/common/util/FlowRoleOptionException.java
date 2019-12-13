package com.isolver.common.util;

import com.isolver.common.constant.SysStatusCodeConst;

/**
 * 流程配置异常
 * @author IS1907011
 *
 */
public class FlowRoleOptionException extends RuntimeException  {

	private static final long serialVersionUID = 1L;
	
	//异常信息
    private String message;
    
    //异常code
    private Integer code;
    
    public FlowRoleOptionException(String message) {
    	super(message);
    	this.setMessage(message);
    	this.code=SysStatusCodeConst.FLOWERROR;
    }

//    public FlowRoleOptionException(String message, Throwable cause) {
//        super(message, cause);
//    }
//    
//    public FlowRoleOptionException(Throwable cause) {
//        super(cause);
//    }
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
