var vm = new Vue({
	el : '#app',
	data : {
		formItem : {
			workId : '',
			user : '',
			type : '',
			status : '',
			date : '',
			time : '',
			bill : '',
			reason : '',
			content : '',
			org : '',
			depart : '',
			assigner : ''
		},
		disabled_flg:false,
		formValidate : {
			type : [ {
				required : true,
				message : '请选择请假类型',
				trigger : 'change'
			} ],
			date : [ {
				required : true,
				type : 'array',
				message : '请选择请假日期',
				trigger : 'change',
				fields : {
					type : "date",
					message : "请选择请假日期",
					required : true
				}
			} ],
			time : [ {
				required : true,
				type : 'array',
				message : '请选择请假时间',
				trigger : 'change',
				fields : {
					0 : {
						type : "string",
						message : "请选择请假时间",
						required : true
					},
					1 : {
						type : "string",
						message : "请选择请假时间",
						required : true
					}
				}
			} ],
			reason : [ {
				required : true,
				message : '请输入请假事由',
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
			let formItem ={};
			if($("#vacation").val() != undefined && $("#vacation").val() != ''){
				let vacation = JSON.parse($("#vacation").val());
				formItem = {
						workId : vacation.userWorkId,
						user : vacation.username,
						date : day,
						status : status,
						type : vacation.vacationType.toString(),
						time :[vacation.vacationStart.split(' ')[1] , vacation.vacationEnd.split(' ')[1]] ,
						bill : vacation.hasDocument ? true : '' ,
						reason : vacation.vacationContent ,
						content : vacation.vacationMark
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
							type : this.formItem.type,
							date : this.formItem.date.toLocaleDateString(),
							timeStart : this.formItem.time[0],
							timeEnd : this.formItem.time[1],
							bill : this.formItem.bill,
							reason : this.formItem.reason,
							content : this.formItem.content,
							assigner : this.formItem.assigner,
							servicePerformance:$("#servicePerformanceId").val( ),
					}
					 var data = formItem;
					 let ruFlowId = $("#ruFlowId").val() == ''?0:$("#ruFlowId").val()
					 var url = "/holiday/application/"+$("#type").val()+"/"+ruFlowId ;
					 var success = function(data) { 
						 ajaxStatus=true; 
						if(data.status == 200){
							 window.top.vm.$Message.success('提交申请成功');
							 window.location="/servicePerformance/index";
					 	}else if(data.status == 604){
							window.top.vm.$Message.error(data.data);
						}
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
					var url = "/holiday/dealApplication";
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
		let servicePerformance ='';
		if($("#servicePerformance").val()!=undefined && $("#servicePerformance").val()!=''){
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