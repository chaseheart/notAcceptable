package com.isolver.service;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isolver.common.util.ProcessEngineUtil;
import com.isolver.entity.OaFlow;

/**
 * @author IS1907005
 * @date 2019/11/15
 * @class ActivityService.java
 */
@Service
public class ActivityService {

	@Autowired
	private ProcessEngine processEngine;

	public String startActivity(OaFlow oaFlow) {
		// activiti 启动
		RuntimeService runtimeService = processEngine.getRuntimeService();

		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(oaFlow.getActId());// act id
		String runFlowId = processInstance.getId();// 存进ru的act id
		// activiti下一步
		ProcessEngineUtil.getProcessEngine().getTaskService().complete(ProcessEngineUtil.getProcessEngine().getTaskService().createTaskQuery().processInstanceId(runFlowId).singleResult().getId());
		return runFlowId;
	}
}
