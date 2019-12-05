//package com.isolver.controller.wechat;
//
//import java.util.List;
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
//import com.isolver.dto.wechat.SalaryListDto;
//import com.isolver.entity.Salary;
//import com.isolver.entity.User;
//import com.isolver.service.UserService;
//import com.isolver.service.wechat.SalaryService;
//
//@RestController
//@RequestMapping(value = "/weixin")
//public class WechatSalaryController {
//
//	private static final Logger logger = LoggerFactory.getLogger(WechatSalaryController.class);
//
//	@Autowired
//	private SalaryService salaryService;
//
//	@Autowired
//	private UserService userService;
//
//	/**
//	 * 获得工资单列表
//	 * 
//	 * @param userId
//	 * @param date
//	 * @return
//	 */
//	@RequestMapping(value = "/salary", method = RequestMethod.POST)
//	public Result<Object> getSalaryList(Long userId, String date) {
//		try {
//			User user = userService.getUserById(userId);
//
//			List<SalaryListDto> resultList = salaryService.getRecord(user, date);
//			return new Result<Object>(SysStatusCodeConst.SUCCESS, resultList);
//		} catch (NullPointerException e) {
//			return new Result<>(SysStatusCodeConst.SYSERROR);
//		} catch (Exception e) {
//			logger.error("取得失败", e);
//			throw new RuntimeException(e);
//		}
//	}
//
//	/**
//	 * 删除工资单
//	 * 
//	 * @param id
//	 * @param userId
//	 * @param version
//	 * @return resultList
//	 */
//	@RequestMapping(value = "/salary/delete", method = RequestMethod.POST)
//	public Result<Object> deleteSalary(Long id, Long userId, Integer version) {
//		try {
//			return salaryService.delOneSalary(id, userId, version);
//		} catch (NullPointerException e) {
//			return new Result<>(SysStatusCodeConst.SYSERROR);
//		} catch (Exception e) {
//			logger.error("删除失败", e);
//			throw new RuntimeException(e);
//		}
//	}
//
//	/**
//	 * 获得工资详细
//	 * 
//	 * @param id
//	 * @param userId
//	 * @param version
//	 * @return resultList
//	 */
//	@RequestMapping(value = "/salary/detail", method = RequestMethod.POST)
//	public Result<Object> getSalaryDetail(Long id) {
//		try {
//			Salary result = salaryService.getOneSalary(id);
//			return new Result<>(SysStatusCodeConst.SUCCESS, result);
//		} catch (NullPointerException e) {
//			return new Result<>(SysStatusCodeConst.SYSERROR);
//		} catch (Exception e) {
//			logger.error("取得失败", e);
//			throw new RuntimeException(e);
//		}
//	}
//}
