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
//import com.isolver.form.OutBusinessForm;
//import com.isolver.service.OrganizationService;
//import com.isolver.service.OutBusinessService;
//import com.isolver.service.PendingService;
//import com.isolver.service.ServicePerformanceService;
//import com.isolver.service.UserService;
//
//@RestController
//@RequestMapping(value = "/weixin")
//public class WechatOutBusinessController {
//
//	private static final Logger logger = LoggerFactory.getLogger(WechatOutBusinessController.class);
//
//	@Autowired
//	private UserService userService;
//
//	@Autowired
//	private OutBusinessService outBusinessService;
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
//	 * 外出公务申请 - 初始化
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/vacation/outBusinessApply/init", method = RequestMethod.POST)
//	public Result<Object> ApplicationInit(Long ruFlowId) {
//		try {
//			List<OrgDto> orgdto = organizationService.findAllOrg();
//			Map<String, Object> map = servicePerformanceService.getServicePerformaneAndOutBusinessForm(ruFlowId);
//			map.put("org", orgdto);
//			return new Result<>(SysStatusCodeConst.SUCCESS, map);
//		} catch (Exception e) {
//			logger.error("初始化失败", e);
//			throw new RuntimeException(e);
//		}
//	}
//
//	/**
//	 * 外出公务提交申请
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/outBuiness/application", method = RequestMethod.POST)
//	public Result<Object> Application(@ModelAttribute OutBusinessForm outBusinessForm, Long userId) {
//		try {
//			User user = userService.getUserById(userId);
//			outBusinessService.startFlow(outBusinessForm, user);
//			return new Result<>(SysStatusCodeConst.SUCCESS);
//		} catch (Exception e) {
//			logger.error("提交申请失败", e);
//			throw new RuntimeException(e);
//		}
//	}
//	
//	/**
//	 * 外出公务审批
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/outBuiness/a/dealApplication", method = RequestMethod.POST)
//	public Result<Object> dealApplication(String assigner, String ruFlow, Long userId) {
//		try {
//			User user = userService.getUserById(userId);
//			Long ruFlowId = Long.valueOf(ruFlow);
//			if (user.getRole().getLevel() != 3) {
//				Long assignerId = Long.valueOf(assigner);
//				User assignerUser = userService.getUserById(assignerId);
//
//				outBusinessService.nextStep(assignerUser, ruFlowId, user);
//			} else {
//				outBusinessService.nextStep(user, ruFlowId, user);
//			}
//			return new Result<>(SysStatusCodeConst.SUCCESS);
//		} catch (Exception e) {
//			logger.error("提交申请失败", e);
//			throw new RuntimeException(e);
//		}
//	}
//	/**
//	 * 外出公务提交再申请
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/outBuiness/reApplication", method = RequestMethod.POST)
//	public Result<Object> reApplication(@ModelAttribute OutBusinessForm outBusinessForm, Long userId, Long id) {
//		try {
//			User user = userService.getUserById(userId);
//			outBusinessService.restartFlow(outBusinessForm, user, id);
//			return new Result<>(SysStatusCodeConst.SUCCESS);
//		} catch (Exception e) {
//			logger.error("提交申请失败", e);
//			throw new RuntimeException(e);
//		}
//	}
//
//	/**
//	 * 外出公务驳回
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/outBusiness/rejectFlow", method = RequestMethod.POST)
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
//	 * 外出公务拒絕
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/outBusiness/refuseApply", method = RequestMethod.POST)
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
