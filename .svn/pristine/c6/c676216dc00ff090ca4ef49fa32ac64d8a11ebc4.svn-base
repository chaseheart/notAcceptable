package com.isolver.service.wechat;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isolver.common.constant.SysStaticConst;
import com.isolver.common.constant.SysStatusCodeConst;
import com.isolver.common.util.Dateutil;
import com.isolver.common.util.Result;
import com.isolver.dao.claimingExpenses.ClaimingExpensesRepository;
import com.isolver.dao.department.DepartmentRepository;
import com.isolver.dao.fileInfo.FileInfoRepository;
import com.isolver.dao.hiFlow.HiFlowRepository;
import com.isolver.dao.oaFlow.OaFlowRepository;
import com.isolver.dao.oaFlowStep.OaFlowStepRepository;
import com.isolver.dao.ruFlow.RuFlowRepository;
import com.isolver.dao.user.UserRepository;
import com.isolver.dto.DepartDto;
import com.isolver.dto.SysUserDto;
import com.isolver.dto.wechat.ApproveHistoryDto;
import com.isolver.dto.wechat.ClaimingExpensesDto;
import com.isolver.entity.ClaimingExpenses;
import com.isolver.entity.Department;
import com.isolver.entity.FileInfo;
import com.isolver.entity.HiFlow;
import com.isolver.entity.OaFlow;
import com.isolver.entity.RuFlow;
import com.isolver.entity.User;
import com.isolver.form.wechat.ClaimingExpensesForm;
import com.isolver.service.ActivityService;
import com.isolver.service.PendingService;
import com.isolver.service.UserService;

@Service
public class ClamingExpensesService {
	@Autowired
	private UserService userService;

	@Autowired
	private ActivityService activityService;

	@Autowired
	private PendingService pendingService;

	@Autowired
	private FileService fileService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private ClaimingExpensesRepository claimingExpensesRepository;

	@Autowired
	private OaFlowRepository oaFlowRepository;

	@Autowired
	private RuFlowRepository ruFlowRepository;

	@Autowired
	private HiFlowRepository hiFlowRepository;

	@Autowired
	private FileInfoRepository fileInfoRepository;

	@Autowired
	private OaFlowStepRepository oaFlowStepRepository;

	/** 。流程类型 **/
	private final static Long FLOWTYPE = 2L;

	/**
	 * 报销申请初始化
	 * 
	 * @param userId 用户id
	 * @return
	 */
	public Map<String, Object> init(Long userId) {
		User user = userService.getUserById(userId);
		Map<String, Object> map = new HashMap<>();
		// 。检索所有部门
		List<DepartDto> departDto = departmentRepository.findAllByDeleteflag(SysStaticConst.NOTDELE);
		// 。检索上级用户
		List<SysUserDto> userDto = oaFlowStepRepository.getNextStepUserList(user.getRole().getLevel(), FLOWTYPE, null);
		map.put("depart", departDto);
		map.put("user", userDto);
		return map;
	}

	/**
	 * 申请/再申请报销
	 * 
	 * @param claimingExpensesForm
	 * @return
	 */
	public Result<Object> applyClamingExpenses(ClaimingExpensesForm claimingExpensesForm) {
		// 。报销记录id非空的场合下，更新
		if (StringUtils.isNotEmpty(claimingExpensesForm.getId())) {
			return updateClamingExpenses(claimingExpensesForm);
			// 。报销记录id为空的场合下，新增
		} else {
			return insertClamingExpenses(claimingExpensesForm);
		}
	}

	/**
	 * 新增报销记录
	 * 
	 * @param claimingExpensesForm
	 * @return
	 */
	public Result<Object> insertClamingExpenses(ClaimingExpensesForm claimingExpensesForm) {
		// ·申请用户
		// lzx change parseLong -> valueOf
		User user = userRepository.findOne(Long.valueOf(claimingExpensesForm.getUserId()));
		Department depart = departmentRepository.findOne(Long.parseLong(claimingExpensesForm.getDepartId()));

		// ·审批人
		User flowUser = userRepository.findOne(Long.parseLong(claimingExpensesForm.getFlowUser()));

		Timestamp presentTime = Dateutil.getTimestamp();

		// 流程
		OaFlow oaFlow = oaFlowRepository.findOne(2l);
		RuFlow ruFlow = insertOaFlow(user, flowUser, oaFlow, presentTime);
		insertHiFlow(user, flowUser, oaFlow, ruFlow, presentTime);

		// ·报销
		ClaimingExpenses claimingExpensesModal = insertClaimingExpenses(claimingExpensesForm, user, depart, ruFlow,
				presentTime);

		List<FileInfo> fileInfoList = new ArrayList<>();
		for (int i = 0; i < claimingExpensesForm.getPhoto().size(); i++) {
			Long fileId = claimingExpensesForm.getPhoto().get(i);
			FileInfo fileInfo = fileInfoRepository.findOne(fileId);
			FileInfo fileInfoEntity = new FileInfo();
			BeanUtils.copyProperties(fileInfo, fileInfoEntity);

			fileInfoEntity.setTableId(claimingExpensesModal.getId().toString());
			fileInfoEntity.setTableName("claiming_expenses");
			fileInfoList.add(fileInfoEntity);
		}

		fileInfoRepository.save(fileInfoList);
		fileInfoRepository.flush();

		return new Result<>(SysStatusCodeConst.SUCCESS);
	}

	/**
	 * 更新报销记录
	 * 
	 * @param claimingExpensesForm
	 * @return
	 */
	public Result<Object> updateClamingExpenses(ClaimingExpensesForm claimingExpensesForm) {
		ClaimingExpenses claimingExpensesModel = claimingExpensesRepository.findDataByRuFlowIdAndDeleteFlag(Long.parseLong(claimingExpensesForm.getId()), SysStaticConst.NOTDELE);
		RuFlow ruFlow = claimingExpensesModel.getRuFlow();
		// 。删除以前上传的图片
		fileService.deleteOldPic(claimingExpensesModel.getId().toString(), claimingExpensesForm.getPhoto());
		// ·申请用户
		User user = userRepository.findOne(Long.valueOf(claimingExpensesForm.getUserId()));
		// ·审批人
		User flowUser = userRepository.findOne(Long.parseLong(claimingExpensesForm.getFlowUser()));
		Timestamp presentTime = Dateutil.getTimestamp();

		// ·下一步
		pendingService.nextStep(flowUser, ruFlow.getId(), user);

		List<FileInfo> fileInfoList = new ArrayList<>();
		for (int i = 0; i < claimingExpensesForm.getPhoto().size(); i++) {
			Long fileId = claimingExpensesForm.getPhoto().get(i);
			FileInfo fileInfo = fileInfoRepository.findOne(fileId);
			FileInfo fileInfoEntity = new FileInfo();
			BeanUtils.copyProperties(fileInfo, fileInfoEntity);

			fileInfoEntity.setTableId(claimingExpensesModel.getId().toString());
			fileInfoEntity.setTableName("claiming_expenses");
			fileInfoEntity.setUpdateTime(presentTime);
			fileInfoEntity.setUpdateUserId(user.getId());
			fileInfoList.add(fileInfoEntity);
		}

		fileInfoRepository.save(fileInfoList);
		fileInfoRepository.flush();

		return new Result<>(SysStatusCodeConst.SUCCESS);
	}

	/**
	 * 审批报销申请
	 * 
	 * @param claimingExpensesForm
	 * @return
	 */
	public Result<Object> agreeApplication(String assigner, Long ruFlow, Long userId) {
		User assign = new User();
		// 。下步审批人
		if (!StringUtils.isBlank(assigner)) {
			assign = userService.getUserById(Long.valueOf(assigner));
		}
		// 。申请者
		User user = userService.getUserById(userId);
		pendingService.nextStep(assign, ruFlow, user);
		return new Result<>(SysStatusCodeConst.SUCCESS);
	}

	/**
	 * 保存到报销表
	 * 
	 * @param claimingExpensesForm
	 * @param user
	 * @param depart
	 * @param ruFlow
	 * @param presentTime
	 * 
	 * @return
	 */
	public ClaimingExpenses insertClaimingExpenses(ClaimingExpensesForm claimingExpensesForm, User user,
			Department depart, RuFlow ruFlow, Timestamp presentTime) {
		ClaimingExpenses entity = new ClaimingExpenses();
		entity.setUser(user);
		entity.setDepartment(depart);
		entity.setType(claimingExpensesForm.getType());
		entity.setExpensesType(claimingExpensesForm.getExpensesType());
		entity.setMoney(claimingExpensesForm.getMoney());
		entity.setProjectId(claimingExpensesForm.getProjectId());
		entity.setCustomerName(claimingExpensesForm.getCustomerName());
		entity.setExpenseCompany(claimingExpensesForm.getExpenseCompany());
		entity.setContent(claimingExpensesForm.getContent());
		entity.setRuFlow(ruFlow);

		entity.setDeleteFlag(SysStaticConst.NOTDELE);
		entity.setInsertUserId(Long.parseLong(claimingExpensesForm.getUserId()));
		entity.setInsertTime(presentTime);
		entity.setUpdateUserId(Long.parseLong(claimingExpensesForm.getUserId()));
		entity.setUpdateTime(entity.getInsertTime());
		entity.setVersion(0);
		ClaimingExpenses result = claimingExpensesRepository.saveAndFlush(entity);

		return result;
	}

	/**
	 * 保存到流程表
	 * 
	 * @param user
	 * @param flowUser
	 * @param oaFlow
	 * @param presentTime
	 * 
	 * @return
	 */
	public RuFlow insertOaFlow(User user, User flowUser, OaFlow oaFlow, Timestamp presentTime) {
		// ·当前流程
		RuFlow ruFlow = new RuFlow();
		// 。存进ru的act id
		String runFlowId = activityService.startActivity(oaFlow);
		ruFlow.setActRunFlowId(runFlowId);
		ruFlow.setFlowId(oaFlow);
		ruFlow.setState(2);

		ruFlow.setDealPeople(flowUser);
		ruFlow.setDealPeopleNow(user);
		ruFlow.setInsertUserId(user.getId());
		ruFlow.setInsertTime(presentTime);
		ruFlow.setUpdateUserId(user.getId());
		ruFlow.setUpdateTime(ruFlow.getInsertTime());
		ruFlow.setDeleteFlag(SysStaticConst.NOTDELE);
		ruFlow = ruFlowRepository.saveAndFlush(ruFlow);

		return ruFlow;
	}

	/**
	 * 保存到历史流程表
	 * 
	 * @param user
	 * @param flowUser
	 * @param oaFlow
	 * @param ruFlow
	 * @param presentTime
	 * 
	 * @return
	 */
	public void insertHiFlow(User user, User flowUser, OaFlow oaFlow, RuFlow ruFlow, Timestamp presentTime) {
		HiFlow hiFlow = new HiFlow();
		hiFlow.setActId(ruFlow.getActRunFlowId());
		hiFlow.setState(1);
		hiFlow.setApplication(user);
		hiFlow.setDealPeopleNow(user);
		hiFlow.setDealPeople(user);
		hiFlow.setRuFlowId(ruFlow);
		hiFlow.setInsertUserId(user.getId());
		hiFlow.setInsertTime(presentTime);
		hiFlow.setUpdateUserId(user.getId());
		hiFlow.setUpdateTime(hiFlow.getInsertTime());
		hiFlow.setDeleteFlag(SysStaticConst.NOTDELE);
		hiFlowRepository.saveAndFlush(hiFlow);
	}

//	/**
//	 * 下一步骤
//	 * 
//	 * @param assign   审批人
//	 * @param ruFlowId
//	 * @param user     提交用户
//	 */
//	public void nextStep(User assign, Long ruFlowId, User user) {
//		RuFlow ruFlow = ruFlowRepository.findOne(ruFlowId);
//		Timestamp tm = Dateutil.getTimestamp();
//		ClaimingExpenses claimingExpenses = claimingExpensesRepository.findByRuFlow(ruFlow);
//		ProcessEngineUtil.getProcessEngine().getTaskService().complete(ProcessEngineUtil.getProcessEngine()
//				.getTaskService().createTaskQuery().processInstanceId(ruFlow.getActRunFlowId()).singleResult().getId());
//		HiFlow hiFlow = new HiFlow();
//		hiFlow.setActId(ruFlow.getActRunFlowId());
//		hiFlow.setState(ruFlow.getState());
//		hiFlow.setApplication(claimingExpenses.getUser());
//		hiFlow.setDealPeople(ruFlow.getDealPeople());
//		hiFlow.setDealPeopleNow(ruFlow.getDealPeopleNow());
//		hiFlow.setRuFlowId(ruFlow);
//		hiFlow.setInsertUserId(user.getId());
//		hiFlow.setInsertTime(tm);
//		hiFlow.setUpdateUserId(user.getId());
//		hiFlow.setUpdateTime(tm);
//		hiFlow.setDeleteFlag(SysStaticConst.NOTDELE);
//		hiFlowRepository.saveAndFlush(hiFlow);
//
//		if (ruFlow.getState() == 4) {
//			ruFlow.setState(5);
//			ruFlow.setDealPeople(user);
//		} else {
//			ruFlow.setState(ruFlow.getState() + 1);
//			ruFlow.setDealPeople(assign);
//		}
//		ruFlow.setDealPeopleNow(user);
//		ruFlow.setUpdateUserId(user.getId());
//		ruFlow.setUpdateTime(tm);
//		ruFlow.setDeleteFlag(SysStaticConst.NOTDELE);
//		ruFlowRepository.saveAndFlush(ruFlow);
//	}

	/**
	 * 获得报销详情
	 * 
	 * @param ruFlow
	 * @return
	 */
	public ClaimingExpensesDto getOneClaimingExpensesDto(Long ruFlow) {
		return claimingExpensesRepository.findByRuFlowIdAndDeleteFlag(ruFlow, SysStaticConst.NOTDELE);
	}

	/**
	 * 获得历史报销列表
	 * 
	 * @return
	 */
	public List<ApproveHistoryDto> getCEHistory(Long userId) {
		return hiFlowRepository.approveHistory(userId, SysStaticConst.NOTDELE);
	}

//	/**
//	 * 获得报销详情 - 微信
//	 * 
//	 * @param ruFlow
//	 * @return
//	 */
//	public Map<String, Object> getOneClaimingExpensesDtoWechat(Long ruFlow, Role role) {
//		Map<String, Object> map = new HashMap<>();
//		List<UserDto> userDto = userRepository.findByRoleDeleteFlag(role, SysStaticConst.NOTDELE);
//		ClaimingExpensesDto claimingExpensesDto = claimingExpensesRepository.findByRuFlowIdAndDeleteFlag(ruFlow, SysStaticConst.NOTDELE);
//		map.put("user", userDto);
//		map.put("claimingExpenses", claimingExpensesDto);
//		return map;
//	}

}
