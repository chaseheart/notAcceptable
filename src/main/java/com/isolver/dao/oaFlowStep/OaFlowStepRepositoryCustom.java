package com.isolver.dao.oaFlowStep;

import java.util.List;

import com.isolver.dto.SysUserDto;
import com.isolver.entity.Role;

public interface OaFlowStepRepositoryCustom {
	
	/**
	 * ·检索申请审批人(下一步审批人)
	 * @param roleId 本人角色
	 * @param flowId 申请流程类型(流程id)
	 * @param departId 审批者部门id
	 * @return
	 */
	public List<SysUserDto> getNextStepUserList(Role role,Long flowId, Long departId);

	/**
	 * 查找本角色之后流程步骤的角色层级对应关系
	 * 取第一个即为下一步
	 * @param role	 本角色
	 * @param flowId 流程定义id
	 * @return
	 */
	public List<Integer> getNextStepLevel(Role role, Long flowId);
	
	
	/**
	 * 检索所有流程的已配置的步骤数
	 * @param flowId 流程类型
	 * @return
	 */
	public Integer getFlowStepCount(Long flowId);
}
