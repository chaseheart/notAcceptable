//package com.isolver.controller.wechat;
//
//import java.util.List;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.isolver.common.constant.SysStatusCodeConst;
//import com.isolver.common.util.Result;
//import com.isolver.dto.wechat.PendingDtoWechat;
//import com.isolver.entity.ClaimingExpenses;
//import com.isolver.entity.User;
//import com.isolver.service.PendingService;
//import com.isolver.service.ServicePerformanceService;
//import com.isolver.service.UserService;
//import com.isolver.service.wechat.VerifyService;
//
//@RestController
//@RequestMapping(value = "/weixin")
//public class VerifyController {
//
//	private static final Logger logger = LoggerFactory.getLogger(VerifyController.class);
//
//	@Autowired
//	private UserService userService;
//
//	@Autowired
//	private VerifyService verifyService;
//
//	@Autowired
//	private ServicePerformanceService servicePerformanceService;
//
//	@Autowired
//	private PendingService pendingService;
//
////	/**
////	 * 我的申请详情-初始化
////	 * 
////	 * @param loginForm 登录表单
////	 * @return
////	 */
////	@RequestMapping(value = "/verify/my", method = RequestMethod.POST)
////	public Result<Object> verifyMyInit(Long userId) {
////		try {
////			User user = userService.getUserById(userId);
////			Map<String, Object> map = servicePerformanceService.findAllAppilication(dateStart, dateEnd, user);
////			return new Result<>(SysStatusCodeConst.SUCCESS, map);
////		} catch (NullPointerException e) {
////			return new Result<>(SysStatusCodeConst.SYSERROR);
////		} catch (Exception e) {
////			logger.error("初期化失败", e);
////			throw new RuntimeException(e);
////		}
////	}
//
//	/**
//	 * 我的审核详情-初始化
//	 * 
//	 * @param loginForm 登录表单
//	 * @return
//	 */
//	@RequestMapping(value = "/verify/examine", method = RequestMethod.POST)
//	public Result<Object> verifyMyInit(Long userId) {
//		try {
//			User user = userService.getUserById(userId);
//			Map<String, Object> map = pendingService.findAllPendingByUser(user, "%%", "%%");
//			return new Result<>(SysStatusCodeConst.SUCCESS, map);
//		} catch (NullPointerException e) {
//			return new Result<>(SysStatusCodeConst.SYSERROR);
//		} catch (Exception e) {
//			logger.error("初期化失败", e);
//			throw new RuntimeException(e);
//		}
//	}
//
//	/**
//	 * 审核我的申请-初始化
//	 * 
//	 * @param loginForm 登录表单
//	 * @return
//	 */
//	@RequestMapping(value = "/verify/my/init", method = RequestMethod.POST)
//	public Result<Object> verifyMineInit(Long userId) {
//		try {
//			User user = userService.getUserById(userId);
//			List<PendingDtoWechat> list = pendingService.findAllPendingByUserForWechat(user);
//			return new Result<>(SysStatusCodeConst.SUCCESS, list);
//		} catch (NullPointerException e) {
//			return new Result<>(SysStatusCodeConst.SYSERROR);
//		} catch (Exception e) {
//			logger.error("初期化失败", e);
//			throw new RuntimeException(e);
//		}
//	}
//
//	/**
//	 * 审核历史-初始化
//	 * 
//	 * @param loginForm 登录表单
//	 * @return
//	 */
//	@RequestMapping(value = "/verify/examineHistory", method = RequestMethod.POST)
//	public Result<Object> examineHistoryInit(Long userId) {
//		try {
//			Map<String, Object> map = pendingService.findAllPendingSPHistoryByUser(userId);
//			return new Result<>(SysStatusCodeConst.SUCCESS, map);
//		} catch (NullPointerException e) {
//			return new Result<>(SysStatusCodeConst.SYSERROR);
//		} catch (Exception e) {
//			logger.error("初期化失败", e);
//			throw new RuntimeException(e);
//		}
//	}
//
//	/**
//	 * 申请历史-初始化
//	 * 
//	 * @param loginForm 登录表单
//	 * @return
//	 */
//	@RequestMapping(value = "/verify/applyHistory", method = RequestMethod.POST)
//	public Result<Object> applyHistoryInit(Long userId) {
//		try {
//			User user = userService.getUserById(userId);
//
//			return new Result<>(SysStatusCodeConst.SUCCESS, verifyService.getApplyHistoryList(user));
//		} catch (NullPointerException e) {
//			return new Result<>(SysStatusCodeConst.SYSERROR);
//		} catch (Exception e) {
//			logger.error("初期化失败", e);
//			throw new RuntimeException(e);
//		}
//	}
//
//}
