var vm = new Vue({
	el : '#app',
	data : {
		// 。勤务(非管理部)流程勾选项
		nFlow : '',
		// 。勤务(管理部)流程勾选项
		nPeFlow : '',
		// 。报销)流程勾选项
		reFlow : '',
		// 。全角色
		allRole : '',
		// 。点击对象
		clickObj : '',
		// 。点击对象序号
		clickNo : '',
		// 。勤务(非管理部)流程剩余未勾选项
		attendanceRole : '',
		// 。勤务(管理部)流程剩余未勾选项
		pAttendanceRole : '',
		// 。报销流程剩余未勾选项
		reimbursementRole : ''
	},
	methods : {
		// 。获取对应流程步骤的已勾选角色
		getItem : function(list, stepId) {
			var result = [];
			for ( let i in list) {
				if (stepId == list[i].stepId) {
					result.push(parseInt(list[i].id));
				}
			}
			return result;
		},
		// 。获取角色是否被勾选
		getChecked : function(id, arr) {
			return jQuery.inArray(id, arr) != -1;
		},
		// 。获取当前步骤显示的所有角色
		createArr : function(allRole, surplusRoles, idArr){
			var copyArr = $.extend(true, [], allRole);
			var result = [];
			if(surplusRoles == undefined || surplusRoles.length == 0){
				for(let i in copyArr){
					if(jQuery.inArray(copyArr[i].id, idArr) != -1){
						result.push(copyArr[i]);
					}
				}
				return result;
			}
			for(let j in copyArr){
				for(let i in surplusRoles){
					if(copyArr[j].id == surplusRoles[i].id){
						result.push(copyArr[j]);
						break;
					}else if(jQuery.inArray(copyArr[j].id, idArr) != -1){
						result.push(copyArr[j]);
						break;
					}
				}
			}
			return result;
		},
		// 。配置点击
		stepRoleOperate : function(obj) {
			let formId = $(obj.currentTarget).parents(".form-box").attr("id");
			var data = [];
			var roleArr = this.allRole;
			if (formId == "normalFlow" || formId == "reFlow") {
				let holder = $(obj.currentTarget).parent();
				let stepId = holder.parent().find("p").index(holder) + 1;
				let list;
				let totalArr;
				if (formId == "normalFlow") {
					list = this.getItem(this.nFlow, stepId);
					this.clickObj = 'normalFlow';
					totalArr = this.createArr(roleArr, this.attendanceRole, list);
				} else {
					list = this.getItem(this.reFlow, stepId);
					this.clickObj = 'reFlow';
					totalArr = this.createArr(roleArr, this.reimbursementRole, list);
				}
				this.clickNo = stepId;
				for ( let i in totalArr) {
					let checked = this.getChecked(totalArr[i].id, list);
					data.push({
						id : totalArr[i].id,
						roleName : totalArr[i].roleName,
						_checked : checked
					});
				}
			} else if (formId == "peFlow") {
				let holder = $(obj.currentTarget).parent();
				let stepId = holder.parent().find("p").index(holder) + 2;
				let list = this.getItem(this.nPeFlow, stepId);
				let totalArr = this.createArr(roleArr, this.pAttendanceRole, list);
				for ( let i in totalArr) {
					let checked = this.getChecked(totalArr[i].id, list);
					data.push({
						id : totalArr[i].id,
						roleName : totalArr[i].roleName,
						_checked : checked
					});
				}
				this.clickObj = 'peFlow';
				this.clickNo = stepId;
			}
			window.top.vm.roleModalTableData = data;
			window.top.vm.roleModal = true;
		},
		// 。保存
		saveData : function() {
			var that = this;
			var dataArr = [];
			for ( let i in that.nFlow) {
				dataArr.push({
					id : that.nFlow[i].id,
					stepId : that.nFlow[i].stepId,
					flowType : 1,
					type:0
					
				});
			}
			for ( let i in that.nPeFlow) {
				dataArr.push({
					id : that.nPeFlow[i].id,
					stepId : parseInt(that.nPeFlow[i].stepId),
					flowType : 1,
					type:1
				});
			}
			for ( let i in that.reFlow) {
				dataArr.push({
					id : that.reFlow[i].id,
					stepId : that.reFlow[i].stepId,
					flowType : 2,
					type:0
				});
			}

			var url = "/stepRole/saveData";
			var success = function(data) {
				ajaxStatus = true;
				if (data.status == '200') {
					window.top.vm.$Message.success('提交成功');
					window.location.reload();
				}
			};
			var cache = false;
			var alone = true;
			var contentType = 'application/json;charset=utf-8';
			post(url, JSON.stringify(dataArr), success, cache, alone,
					contentType);
		},
		// 。删除已勾选的角色
		sliceArr : function(allRole, selectedRole) {
			var copyArr = $.extend(true, [], allRole);
			if (selectedRole == undefined || selectedRole.length == 0) {
				return copyArr;
			}
			for ( let i in selectedRole) {
				for ( let j in copyArr) {
					if (copyArr[j].id == selectedRole[i].id) {
						copyArr.splice(j,1);
						break;
					}
				}
			}
			return copyArr;
		},
		// 。重置
		revert : function(){
			window.location.reload();
		}
	},
	created : function() {
		let initData = $("#data").val() != '' ? JSON.parse($("#data").val()): '';
		this.nFlow = initData.normalFlow;
		this.nPeFlow = initData.normalPeFlow;
		this.reFlow = initData.reFlow;
		this.allRole = initData.allRole;
		var arr = this.sliceArr(this.allRole, this.nFlow);
		this.attendanceRole = arr;
		arr = this.sliceArr(this.allRole, this.nPeFlow);
		this.pAttendanceRole = arr;
		arr = this.sliceArr(this.allRole, this.reFlow);
		this.reimbursementRole = arr;
	}
});