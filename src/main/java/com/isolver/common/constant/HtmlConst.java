package com.isolver.common.constant;

/**
 * @author IS1907005
 * @date 2019/11/21
 * @class HtmlConst.java
 */
public class HtmlConst {
	// 。申请
	public static final String APPLICATION = "write";
	// 。再申请
	public static final String APPLICATIONAGAIN = "writeAgain";
	// 。申请按钮
	public static final String SUBMMITBUTTON = "<i-button type='primary' @click='submitApplication(\"formItem\")'>申请</i-button>";
	// 。审批
	public static final String PENDING = "view";
	// 。审批按钮
	public static final String PENDINGBUTTON = "<div class='hidden-box-child'> <i-button type='primary' @click='agreeApplication()'>承认</i-button> <i-button type='error' @click='refuseApply()'>拒绝</i-button> <i-button @click='rejectApply()'>驳回</i-button> </div>";
	//。报销申请按钮
	public static final String SAVEAPPLICATION = "<i-button type='primary' @click='saveApplication'>申请</i-button>";
	// 。再申请按钮
	public static final String SUBMMITAGAINBUTTON = "<i-button type='primary' @click='submitApplication(\"formItem\")'>再申请</i-button> <i-button type='error' @click='refuseApply()'>终止</i-button>";
	
}
