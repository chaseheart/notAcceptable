var vm = new Vue({
	el : '#app',
	data : {
		formItem : {
			workId : '',
			user : '',
			date : '',
			status : '',
			time : '',
			projectId : '',
			content : '',
			org : '',
			depart : '',
			assigner : ''
		},
		disabled_flg:false,
		formValidate : {
			date : [ {
				required : true,
				type : 'date',
				message : '请选择加班日期',
				trigger : 'change'
			} ],
			time : [ {
				required : true,
				type : 'array',
				message : '请选择加班时间',
				trigger : 'change',
				fields : {
					0 : {
						type : "string",
						message : "请选择加班时间",
						required : true
					},
					1 : {
						type : "string",
						message : "请选择加班时间",
						required : true
					}
				}
			} ],
			projectId : [ {
				required : true,
				message : '请输入项目编号',
				trigger : 'blur'
			} ],
			content : [ {
				required : true,
				message : '请输入主要工作内容',
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
			let day = this.formItem.date;
			let status = '暂无打卡记录';
			if(servicePerformance!=''){
				day = new Date(servicePerformance.day);
				status= servicePerformance.oaStart + '~' + servicePerformance.oaEnd;
				$("#servicePerformanceId").val(servicePerformance.id);
			}
			let formItem = {}
			if($("#overTime").val() != undefined && $("#overTime").val() != ''){
				let overTime = JSON.parse($("#overTime").val());
				formItem = {
						workId : overTime.userWorkId,
						user : overTime.username,
						date : day,
						status : status,
						date : overTime.workOvertimeDate.toString(),
						time :[overTime.workOvertimeStart.split(' ')[1] , overTime.workOvertimeEnd.split(' ')[1]] ,
						projectId : overTime.projectNo,
						content : overTime.workContent
				}
			}else{
				formItem = {
						workId : userData.workId,
						user : userData.username,
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
				console.log(data);
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
		submitApplication:function(name){
			this.$refs[name].validate((valid)=>{
				if (valid) {
					var that = this;
					var formItem = {
							workId : this.formItem.workId,
							workOvertimeDate : this.formItem.date.toLocaleDateString(),
							timeStart : this.formItem.time[0],
							timeEnd : this.formItem.time[1],
							projectId : this.formItem.projectId,
							workContent : this.formItem.content,
							assigner : this.formItem.assigner,
							servicePerformance:$("#servicePerformanceId").val( ),
					}
					 var data = formItem;
					 let ruFlowId = $("#ruFlowId").val() == ''?0:$("#ruFlowId").val()
					 var url = "/workOvertime/application/"+$("#type").val()+"/"+ruFlowId ;
					 var success = function(data) { 
						 ajaxStatus=true;
						 if(data.status == 200){
							 window.top.vm.$Message.success('提交成功');
							 window.location="/servicePerformance/index";
						 }else if(data.status == 604){
								window.top.vm.$Message.error(data.data);
						 }
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
					var url = "/workOvertime/dealApplication";
					var success = function(data) { 
						ajaxStatus=true;         
						window.top.vm.$Message.success('提交申请成功');
						window.location="/pending/index";
					};
					var cache = false;
					var alone = true;
					post(url, data, success, cache, alone);
	             } else {
	            	 window.top.vm.$Message.error('提交申请失败');
	             }
			})
		}
	},
	created : function() {
		this.getOrg();
		var servicePerformance='';
		if($("#servicePerformance").val()!=undefined && $("#servicePerformance").val()!='') {
			servicePerformance = JSON.parse($("#servicePerformance").val())[0];
		}
		this.getFormItem(servicePerformance);
		let userData = JSON.parse($("#username").val())[0];
		if(userData.isEnd == true){
			$("#appDepart").remove();
		}
		//初次申请，没有后续申请者，不允许申请
		if(!(userData.isEnd == true && $("#type").val() == 'write')){
			this.getTypeAndHtml($("#type").val(),$("#html").val());
		}
	}
});