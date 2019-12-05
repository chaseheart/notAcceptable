var vm = new Vue({
	el : '#app',
	data : {
		formItem : {
			workId : '',
			user : '',
			status : '',
			date : '',
			bill : false,
			content : '',
			org : '',
			depart : '',
			assigner : ''
		},
		disabled_flg:false,
		formValidate : {
			type : [ {
				required : true,
				message : '请选择非正常考勤类型:',
				trigger : 'change'
			} ],
			date : [ {
				required : true,
				type : 'date',
				message : '请选择非正常考勤时间',
				trigger : 'change',
			} ],
			content : [ {
				required : true,
				message : '请输入非正常考勤原因',
				trigger : 'blur'
			} ],
			assigner: [ {
				required : true,
				message : '请选择审批者' 
			} ]
		},
		orgList : [],
		departList : [],
		assignerList : [],
		
		historyTable : [ {
			title : '处理者',
			key : 'username'
		}, {
			title : '处理结果',
			key : 'state'
		}, {
			title : '处理时间',
			key : 'lastUpdateTime'
		}],
		historyData : [],
	},
	methods : {
		getFormItem : function(servicePerformance) {
			let userData = JSON.parse($("#username").val())[0];
			if(userData.role==3){
				$("#appDepart").remove();
			}
			let day = this.formItem.date;
			let status = '暂无打卡记录';
			if(servicePerformance!=''){
				day = new Date(servicePerformance.day);
				status= servicePerformance.oaStart + '~' + servicePerformance.oaEnd;
				$("#servicePerformanceId").val(servicePerformance.id);
			}
			let formItem ={};
			if($("#unusualAttendance").val() != undefined && $("#unusualAttendance").val() != ''){
				let unusualAttendance = JSON.parse($("#unusualAttendance").val())[0];
				formItem = {
						workId : unusualAttendance.userWorkId,
						user : unusualAttendance.username,
						date : day,
						status : status,
						type : unusualAttendance.unusualAttendanceType.toString(),
						bill : unusualAttendance.hasDocument ,
						content : unusualAttendance.reason
				}
			}else{
				formItem = {
						workId : userData.workId,
						user : userData.username,
						type : '1',
						date : day,
						status : status
				}
			}
			this.formItem = formItem;
		},
		getOrg : function() {
			let orgData = $("#orgdto").val() != '' ? JSON.parse($("#orgdto").val()) : ''
			for ( let i in orgData) {
				let data = {
					value : i,
					label : orgData[i].orgName
				};
				this.orgList.push(data)
			}

		},
		getDepart : function(i) {
			let orgData = JSON.parse($("#orgdto").val());
			let departDto = orgData[i].departDto;
			let departList = [];
			for ( let n in departDto) {
				let data = {
					value : departDto[n].id,
					label : departDto[n].departName
				};
				departList.push(data)
			}
			this.departList = departList;
		},
		getLeader : function(i) {
			var that = this;
			var data = {
				'departId' : i
			};
			var url = "/user/findUserByDepartAndRole";
			var success = function(data) {
				ajaxStatus = true;
				var assignerList = [];
				if (data.status == '200') {
					if (data.data.length != 0) {
						for ( let i in data.data) {
							let assignerData = {
								value : data.data[i].id,
								label : data.data[i].username
							}
							assignerList.push(assignerData);
						}
					}
				}
				that.assignerList = assignerList;
			};
			var cache = false;
			var alone = true;
			post(url, data, success, cache, alone);

		},
		submitApplication : function(name){
			this.$refs['formItem'].validate((valid)=>{
				if (valid) {
					var that = this;
					var formItem = {
							date : this.formItem.date.toLocaleDateString(),
							type : this.formItem.type,
							hasDocument : this.formItem.bill,
							reason : this.formItem.content,
							assigner : this.formItem.assigner,
							servicePerformance:$("#servicePerformanceId").val( ),
					}
					var data = formItem;
					let ruFlowId = $("#ruFlowId").val() == ''?0:$("#ruFlowId").val()
					var url = "/unusualAttendance/application/"+$("#type").val()+"/"+ruFlowId ;
					var success = function(data) { 
						ajaxStatus=true;         
						window.top.vm.$Message.success('提交申请成功');
						window.location="/servicePerformance/index"
					};
					var cache = false;
					var alone = true;
					post(url, data, success, cache, alone);
				} else {
					window.top.vm.$Message.error('提交申请失败');
				}
			})
			
		},
		getServicePerformance : function(){
			var that = this;
			let userData = JSON.parse($("#username").val())[0];
			let day = '';
			let status = '暂无打卡记录';
			var data = {
					date : this.formItem.date.toLocaleDateString()
			};
			var url = "/servicePerformance/findOneServicePerformance";
			var success = function(data) {
				ajaxStatus=true;
				var servicePerformance = data.data.servicePerformance;
				if(servicePerformance != null && servicePerformance != undefined){
					that.getFormItem(servicePerformance);
				}else{
					that.getFormItem('');
				}
			};
			var cache = false;
			var alone = true;
			post(url, data, success, cache, alone);
		},
		getTypeAndHtml : function(type , html){
			//·加载审批人
			if(type ==''){
				$("#appDepart").remove();
			}else{
				$("#iform").append(html);
			}
			//·表单只读
			if(type =='view' || type ==''){
				this.disabled_flg =true;
				this.formValidate = {
						assigner : [ {
							required : true,
							message : '请选择审批人'
						} ]
				}
			}
		},
		/**
		 * 待处理申请
		 */
		agreeApplication : function(){
			this.$refs['formItem'].validate((valid)=>{
				if (valid) {
					var that = this;
					var formItem = {
							assigner : this.formItem.assigner,
							ruFlow:$("#ruFlowId").val()
					}
					var data = formItem;
					var url = "/unusualAttendance/dealApplication";
					var success = function(data) { 
						ajaxStatus=true;         
						window.top.vm.$Message.success('提交成功');
						window.location="/pending/index"
					};
					var cache = false;
					var alone = true;
					post(url, data, success, cache, alone);
	             } else {
	            	 window.top.vm.$Message.error('提交失败');
	             }
			})
		},
		// ·拒绝
		refuseApply : function(){
			eval(window.top.vm.showModal)('vm.refuseApplyOk')
		},
		refuseApplyOk : function(){
			var that = this;
			var formItem = {
					ruFlowId:$("#ruFlowId").val()
			}
			var data = formItem;
			var url = "/pending/refuseApply";
			var success = function(data) { 
				ajaxStatus=true;         
				if($("#type").val() == "writeAgain"){
					window.top.vm.$Message.success('终止成功');
					window.location="/servicePerformance/index"
				}else{
					window.top.vm.$Message.success('拒绝成功');
					window.location="/pending/index"
				}
			};
			var cache = false;
			var alone = true;
			post(url, data, success, cache, alone);
		},
		// ·驳回
		rejectApply : function(){
			eval(window.top.vm.showModal)('vm.rejectApplyOk')
		},
		rejectApplyOk : function(){
			var that = this;
			var formItem = {
					ruFlowId:$("#ruFlowId").val()
			}
			var data = formItem;
			var url = "/pending/rejectApply";
			var success = function(data) { 
				ajaxStatus=true;         
				window.top.vm.$Message.success('驳回成功');
				window.location="/pending/index"
			};
			var cache = false;
			var alone = true;
			post(url, data, success, cache, alone);
		}
	},
	created : function() {
		this.getOrg();
		var servicePerformance='';
		if($("#servicePerformance").val()!=undefined && $("#servicePerformance").val()!='') {
			servicePerformance = JSON.parse($("#servicePerformance").val())[0];
		}
		this.getFormItem(servicePerformance);
		this.getTypeAndHtml($("#type").val(),$("#html").val());
	}
});