package com.isolver.form;

/**
 * 
 * @author IS1907006
 *
 */
public class StepRoleForm {
	
	/** id **/ 
	private Long id;
	
	/** 。步骤id **/
	private Integer stepId;
	
	/** 。流程类型 **/
	private Long flowType;
	
	/** 。类型 **/
	private Integer type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStepId() {
		return stepId;
	}

	public void setStepId(Integer stepId) {
		this.stepId = stepId;
	}

	public Long getFlowType() {
		return flowType;
	}

	public void setFlowType(Long flowType) {
		this.flowType = flowType;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}
