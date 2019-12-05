package com.isolver.dao.oaFlowStep;

import java.util.List;

import com.isolver.dto.SysUserDto;

public interface OaFlowStepRepositoryCustom {
	
	/**
	 * ·检索申请审批人(下一步审批人)
	 * @param level 本人角色层级
	 * @param flowId 申请流程类型(流程id)
	 * @param departId 审批者部门id
	 * @return
	 */
	public List<SysUserDto> getNextStepUserList(Integer level,Long flowId, Long departId);

}
