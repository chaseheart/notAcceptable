package com.isolver.dto.wechat;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.isolver.common.util.Dateutil;

public class ApproveHistoryDto {

	/** 运行流程id **/
	private String ruFlowId;
	
	/** 流程名 **/
	private String name;
	
	/** 处理人 **/
	private String dealPerson;
	
	/** 操作 **/
	private String operate;
	
	/** 最新更新时间 **/
	private String updateEndTime;

	public String getRuFlowId() {
		return ruFlowId;
	}

	public void setRuFlowId(String ruFlowId) {
		this.ruFlowId = ruFlowId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDealPerson() {
		return dealPerson;
	}

	public void setDealPerson(String dealPerson) {
		this.dealPerson = dealPerson;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getUpdateEndTime() {
		return updateEndTime;
	}

	public void setUpdateEndTime(String updateEndTime) {
		this.updateEndTime = updateEndTime;
	}

	public ApproveHistoryDto() {
		
	}
	
	public ApproveHistoryDto(String ruFlowId, String dealPerson, String operate, String updateEndTime) {
		super();
		this.ruFlowId = ruFlowId;
		this.dealPerson = dealPerson;
		this.operate = operate;
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sm.parse(updateEndTime);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		this.updateEndTime = Dateutil.getDate(date);;
	}

	public ApproveHistoryDto(String ruFlowId, String name) {
		super();
		this.ruFlowId = ruFlowId;
		this.name = name;
	}
}
