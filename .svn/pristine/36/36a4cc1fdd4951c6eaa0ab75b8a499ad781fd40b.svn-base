package com.isolver.common.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import com.isolver.common.constant.SysStatusCodeConst;
import com.isolver.common.util.FlowRoleOptionException;
import com.isolver.common.util.Result;

@Controller
@RestControllerAdvice
/**
 * 全局异常捕捉
 * @author IS1907006
 *
 */
public class MyExceptionHandler{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MessageSourceHandler messageSourceHandler;
	
	/** 乐观锁 **/
	private static final String OPTIMISTICLOCKINGFAILUREEXCEPTION = "ObjectOptimisticLockingFailureException";
	
	/** 流程配置异常 **/
	private static final String FLOWERROR = "FlowException";
	
	/**
	 * 捕捉乐观锁异常
	 * @param e
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ExceptionHandler(ObjectOptimisticLockingFailureException.class)
	public Result<Object> handlerObjectOptimisticLockingFailureException(Exception e,HttpServletRequest request,HttpServletResponse response) throws IOException {
		logger.error(e.getMessage(), e);
		return new Result<>(SysStatusCodeConst.LOCKUP_VALID, messageSourceHandler.getMessage(OPTIMISTICLOCKINGFAILUREEXCEPTION), StringUtils.EMPTY);
	}
	
	/**
	 * 捕捉流程配置异常
	 * @param e
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ExceptionHandler(FlowRoleOptionException.class)
	public Result<Object> handlerFlowRoleOptionException(Exception e,HttpServletRequest request,HttpServletResponse response) throws IOException {
		logger.error(e.getMessage(), e);
		return new Result<>(SysStatusCodeConst.FLOWERROR, messageSourceHandler.getMessage(FLOWERROR), StringUtils.EMPTY);
	}
	
	/**
	 * 无权限异常
	 * @param e
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ExceptionHandler(AccessDeniedException.class)
	public ModelAndView handlerAccessDeniedException(Exception e,HttpServletRequest request,HttpServletResponse response) throws IOException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/noRole");
		logger.error(e.getMessage(), e);
		return modelAndView;
	}
	
	/**
	 * 所有异常捕捉
	 * @param e 异常
	 * @param request request
	 * @param response response
	 * @return
	 * @throws IOException
	 */
	@ExceptionHandler(Exception.class)
	public String handlerAll(Exception e,HttpServletRequest request,HttpServletResponse response) throws IOException {
		String statuscode = HttpStatus.INTERNAL_SERVER_ERROR.toString();
		logger.error(e.getMessage(), e);
		response.setStatus(500);
		response.sendRedirect("/error");
		return statuscode;
	}
}