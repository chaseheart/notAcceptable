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
			depart : '',
			assigner : ''
		},
		formValidate : {
			type : [ {
				required : true,
				message : '请选择费用分类',
				trigger : 'change'
			} ],
			depart : [ {
				required : true,
				message : '请选择所属部门',
			} ],
			price : [ {
				required : true,
				message : '请输入合计金额',
				trigger : 'blur'
			} ],
			expensesType : [ {
				required : true,
				message : '请选择费用分类',
			} ],
			projectId : [ {
				required : true,
				message : '请输入项目编号',
				trigger : 'blur'
			} ],
			customerName : [ {
				required : true,
				message : '请输入客户名称',
				trigger : 'blur'
			} ],
			expenseCompany : [ {
				required : true,
				message : '请输入费用承担方',
				trigger : 'blur'
			} ],
			assigner : [ {
				required : true,
				message : '请选择审批人'
			} ]
		},
		departList : [],
		assignerList : [],
	},
	methods : {
		getFormItem : function(servicePerformance) {
			let day = this.formItem.date;
			if($("#type").val() != "view"){
				let assigner = JSON.parse($("#assigners").val());
				for ( let i in assigner) {
					let data = {
						value : assigner[i].id,
						label : assigner[i].username
					};
					this.assignerList.push(data)
				}
			}else{
				$('#assigner_input').remove();
			}
			

			let depart = JSON.parse($("#depart").val());
			for ( let i in depart) {
				let data = {
					value : depart[i].id,
					label : depart[i].departName
				};
				this.departList.push(data)
			}
			
			
		},
		getTypeAndHtml : function( html){
				$("#iform").append(html);
		},
		submitApplication: function(){
			this.saveApplication()
		},
		saveApplication : function() {
			this.$refs['formItem'].validate((valid)=>{
				if (valid) {
					var file = saveImg();
					var fileIdList = [];
					for(var i in file.data){
						fileIdList.push(file.data[i].id);
					}
					var that = this;
					var data = {
							type : this.formItem.type,
							departId : this.formItem.depart,
							expensesType : this.formItem.expensesType,
							money : this.formItem.price,
							projectId : this.formItem.projectId,
							customerName : this.formItem.customerName,
							expenseCompany : this.formItem.expenseCompany,
							content : this.formItem.content,
							flowUser : this.formItem.assigner,
							photo : fileIdList
							
					}
					var url = "/claimingExpenses/application";

					var success = function(data) {
						ajaxStatus=true;         
						that.$Message.success('提交申请成功');
						window.location="/claimingExpenses/indexMine"
					};
					if ($("#type").val() == "writeAgain"){
						var url = "/claimingExpenses/applicationAgain/" + $("#ruFlowId").val();
					}
					var cache = false;
					var alone = true;
					post(url, data, success, cache, alone);
				} else {
				 	that.$Message.error('提交申请失败');
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
				window.top.vm.$Message.success('拒绝成功');
				window.location="/pending/claimingExpensesIndex"
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
				if($("#type").val() == "writeAgain"){
					window.top.vm.$Message.success('终止成功');
					window.location="/claimingExpenses/indexMine"
				}else{
					window.top.vm.$Message.success('驳回成功');
					window.location="/pending/claimingExpensesIndex"
				}
			};
			var cache = false;
			var alone = true;
			post(url, data, success, cache, alone);
		},
		agreeApplication:function(){
			var that = this;
			var formItem = {
					assigner : this.formItem.assigner,
					ruFlow:$("#ruFlowId").val()
			}
			var data = formItem;
			var url = "/claimingExpenses/dealApplication";
			var success = function(data) { 
				ajaxStatus=true;         
				that.$Message.success('提交申请成功');
				window.location="/pending/claimingExpensesIndex"
			};
			var cache = false;
			var alone = true;
			post(url, data, success, cache, alone);
		},
	    setDepart :function() {
			let userData = JSON.parse($("#username").val());
			
			var formItem = {
				workId : userData.workId,
				user : userData.username,
			}
	    	if($("#claimingExpenses").val()!=''){
				let claimingExpensesJson=JSON.parse($("#claimingExpenses").val());
				formItem  = {
						workId : userData.workId,
						user : userData.username,
						type : claimingExpensesJson.type,
						depart:claimingExpensesJson.departId,
						expensesType : parseInt(claimingExpensesJson.expensesType),
						price : claimingExpensesJson.money,
						projectId : claimingExpensesJson.projectId,
						customerName : claimingExpensesJson.customerName,
						expenseCompany : claimingExpensesJson.expenseCompany,
						content : claimingExpensesJson.content,
						
				}
				//this.formItem = formItem;
			}
			this.formItem = formItem;
	    },
	    setPicture : function(){
	    	if($("#picture").val()=='[]'||$("#picture").val()=='')
	    		return;
	    	let pictureList=JSON.parse($("#picture").val());
	    	for(let i in pictureList){
	    		console.log(pictureList[i]);
	    		$("ul.ace-thumbnails").append("<li style='display: inline-block;margin-left: 10px;position: relative;'>"
			+ "<img class='picGroup' width='150' height='150' src='"+pictureList[i].fileName + pictureList[i].filePath+"'></li>")
	    	}
	    },
	},
	created : function() {
		this.getFormItem();
		this.getTypeAndHtml($("#html").val());
		this.setPicture();
	},
	mounted(){
		this.setDepart();
	},
});