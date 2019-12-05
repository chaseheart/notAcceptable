//package com.isolver.controller.wechat;
//
//import java.util.List;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.isolver.common.constant.SysStatusCodeConst;
//import com.isolver.common.util.Result;
//import com.isolver.dto.OrgDto;
//import com.isolver.entity.User;
//import com.isolver.form.HolidayForm;
//import com.isolver.service.HolidayService;
//import com.isolver.service.OrganizationService;
//import com.isolver.service.PendingService;
//import com.isolver.service.ServicePerformanceService;
//import com.isolver.service.UserService;
//
//@RestController
//@RequestMapping(value = "/weixin")
//public class WechatVacationController {
//
//	private static final Logger logger = LoggerFactory.getLogger(WechatVacationController.class);
//
//	@Autowired
//	private UserService userService;
//
//	@Autowired
//	private HolidayService holidayService;
//
//	@Autowired
//	private ServicePerformanceService servicePerformanceService;
//
//	@Autowired
//	private OrganizationService organizationService;
//
//	@Autowired
//	private PendingService pendingService;
//
//	/**
//	 * 休假申请 - 初始化
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/vacation/init", method = RequestMethod.POST)
//	public Result<Object> ApplicationInit(Long ruFlowId) {
//		try {
//			List<OrgDto> orgdto = organizationService.findAllOrg();
//			Map<String, Object> map = servicePerformanceService.getServicePerformaneAndVacationForm(ruFlowId);
//			map.put("org", orgdto);
//			return new Result<>(SysStatusCodeConst.SUCCESS, map);
//		} catch (Exception e) {
//			logger.error("初始化失败", e);
//			throw new RuntimeException(e);
//		}
//	}
//
//	/**
//	 * 休假申请
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/vacation/application", method = RequestMethod.POST)
//	public Result<Object> Application(@ModelAttribute HolidayForm holidayForm, Long userId) {
//		try {
//			User user = userService.getUserById(userId);
//			holidayService.startFlow(holidayForm, user);
//			return new Result<>(SysStatusCodeConst.SUCCESS);
//		} catch (Exception e) {
//			logger.error("提交申请失败", e);
//			throw new RuntimeException(e);
//		}
//	}
//
//	/**
//	 * 休假审批
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/vacation/dealApplication", method = RequestMethod.POST)
//	public Result<Object> dealApplication(String assigner, String ruFlow, Long userId) {
//		try {
//			User user = userService.getUserById(userId);
//			Long ruFlowId = Long.valueOf(ruFlow);
//			if (user.getRole().getLevel() != 3) {
//				Long assignerId = Long.valueOf(assigner);
//				User assignerUser = userService.getUserById(assignerId);
//
//				holidayService.nextStep(assignerUser, ruFlowId, user);
//			} else {
//				holidayService.nextStep(user, ruFlowId, user);
//			}
//			return new Result<>(SysStatusCodeConst.SUCCESS);
//		} catch (Exception e) {
//			logger.error("提交申请失败", e);
//			throw new RuntimeException(e);
//		}
//	}
//
//	/**
//	 * 休假再申请
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/vacation/reApplication", method = RequestMethod.POST)
//	public Result<Object> reApplication(@ModelAttribute HolidayForm holidayForm, Long userId, Long id) {
//		try {
//			User user = userService.getUserById(userId);
//			holidayService.restartFlow(holidayForm, user, id);
//			return new Result<>(SysStatusCodeConst.SUCCESS);
//		} catch (Exception e) {
//			logger.error("提交申请失败", e);
//			throw new RuntimeException(e);
//		}
//	}
//
//	/**
//	 * 休假驳回
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/vacation/rejectFlow", method = RequestMethod.POST)
//	public Result<Object> rejectFlow(Long ruFlow, Long userId) {
//		try {
//			User user = userService.getUserById(userId);
//			pendingService.rejectApply(ruFlow, user);
//			return new Result<>(SysStatusCodeConst.SUCCESS);
//		} catch (NullPointerException e) {
//			return new Result<>(SysStatusCodeConst.SYSERROR);
//		} catch (Exception e) {
//			logger.error("驳回失败", e);
//			throw new RuntimeException(e);
//		}
//	}
//
//	/**
//	 * 休假拒絕
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/vacation/refuseApply", method = RequestMethod.POST)
//	public Result<Object> refuseApply(Long ruFlow, Long userId) {
//		try {
//			User user = userService.getUserById(userId);
//			pendingService.refuseApply(ruFlow, user);
//			return new Result<>(SysStatusCodeConst.SUCCESS);
//		} catch (NullPointerException e) {
//			return new Result<>(SysStatusCodeConst.SYSERROR);
//		} catch (Exception e) {
//			logger.error("拒絕失败", e);
//			throw new RuntimeException(e);
//		}
//	}
//}
