package com.isolver.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isolver.common.config.AuthenticationFacade;
import com.isolver.common.constant.SysStaticConst;
import com.isolver.common.util.Dateutil;
import com.isolver.dao.oaFlowStep.OaFlowStepRepository;
import com.isolver.dao.role.RoleRepository;
import com.isolver.entity.OaFlow;
import com.isolver.entity.OaFlowStep;
import com.isolver.entity.Role;
import com.isolver.form.StepRoleForm;

/**
 * ·配置步骤角色
 * 
 * @date 2019/12/06
 * @author IS1907011
 *
 */
@Service
public class StepRoleService {

	@Autowired
	private OaFlowStepRepository oaFlowStepRepository;
	
	@Autowired
	private RoleRepository rolepRepository;
	
	/** 。勤务流程类型id **/
	private final static Long FLOWID = 1L;

	/** 。报销流程类型id **/
	private final static Long RFLOWID = 2L;

	public Map<String, Object> init() {
		Map<String, Object> initMap = new HashMap<>();
		// 。获取勤务流程步骤角色对应
		initMap.put("normalFlow", oaFlowStepRepository.getDataByFlowType(FLOWID, 0, SysStaticConst.NOTDELE));
		// 。获取人事勤务流程步骤角色对应
		initMap.put("normalPeFlow", oaFlowStepRepository.getDataByFlowType(FLOWID, 1, SysStaticConst.NOTDELE));
		// 。获取报销流程步骤角色对应
		initMap.put("reFlow", oaFlowStepRepository.getDataByFlowTypeForRe(RFLOWID, SysStaticConst.NOTDELE));
		// 。获取报销流程步骤角色对应
		initMap.put("allRole", rolepRepository.findByDeleteFlag(SysStaticConst.NOTDELE));
		return initMap;
	}
	
	/**
	 * 保存
	 * @param form
	 */
	public void saveData(List<StepRoleForm> form) {
		oaFlowStepRepository.deleteAll();
		List<OaFlowStep> resultList = new ArrayList<>();
		Long userId = AuthenticationFacade.getUserId();
		Timestamp tm = Dateutil.getTimestamp();
		for(StepRoleForm data : form) {
			OaFlowStep entity = new OaFlowStep();
			entity.setFlowState(data.getStepId());
			OaFlow oaFlow = new OaFlow();
			oaFlow.setId(data.getFlowType());
			oaFlow.setVersion(0);
			entity.setFlow(oaFlow);
			Role role = rolepRepository.findOne(data.getId());
			entity.setRoleId(role);
			entity.setType(data.getType());
			entity.setInsertTime(tm);
			entity.setUpdateTime(tm);
			entity.setInsertUserId(userId);
			entity.setUpdateUserId(userId);
			entity.setDeleteFlag(SysStaticConst.NOTDELE);
			resultList.add(entity);
		}
		oaFlowStepRepository.save(resultList);
	}

}
