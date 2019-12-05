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
		modal1: false,
		modal_confirm: false,
		confirm_fun:'',
        loading: false,
        orgList : [],
		departList : [],
		assignerList : [],
		formValidate : {
			assigner: [ {
				required : true,
				message : '请选择审批者' 
			} ]
		},
		
		orgModalForm:{
			orgName:''
		},
		orgFormValidation:{
			orgName:[{
				required : true,
				message : '请输入组织名称'
			}]
		},
		currentPage:'',
		orgModal:false,
		modal_delete_confirm :false
	},
	
	methods : {
		getOrg : function() {
			if($("#orgdto").val() != ''){
				let orgData = JSON.parse($("#orgdto").val());
				for ( let i in orgData) {
					let data = {
						value : i,
						label : orgData[i].orgName
					};
					this.orgList.push(data)
				}
			}else{
				this.formValidate.assigner = [];
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
		menuSelect : function(name) {
			/* this.$router.push(name); */
			$("#main").prop('src', name);
		},
        model_cancel () {
        	this.modal1 = false;
        },
        depart_model_cancel () {
        	this.orgModal = false;
        },
        modal_delete_confirm_cancel () {
        	this.modal_delete_confirm = false;
        },
        /**
		 * 待处理申请
		 */
        model_asyncOK : function(){
        	this.loading = true;
			$('.model_back').prop('disabled',true);
			if($("#orgdto").val() == ''){
				this.apply(this);
			}else{
				this.$refs['formItem'].validate((valid)=>{
					if (valid) {
						 this.apply(this);
					} else {
			        	 this.loading = false;
			             $('.ivu-modal-close,.model_back').prop('disabled',false)
			        	 that.$Message.error('提交申请失败');
			        }
				})
			}
			
		},
		apply : function(obj){
			let flowArray = [];
			$("#main").contents().find('.result-table tbody input:checked').each(function(){
				let flowId = $(this).parents('tr').children('td').eq(1).find('a').text();
				flowArray.push(flowId)
			});
			var that = obj;
			var formItem = {
					assigner : this.formItem.assigner,
					ruFlowIdArrayString : flowArray.toString()
			}
			var data = formItem;
			var url = "/pending/nextStepbenth";
			var success = function(data) { 
				ajaxStatus=true;
				if(data.status == 200){
					that.modal1 = false;
					that.loading = false;
					// ·调用子页面方法
					$("#main")[0].contentWindow.vm.searchOA();
		            $('.ivu-modal-close,.model_back').prop('disabled',false)
		            // ·错误信息
		            if(data.data.length > 0){
		            	for(var i in data.data){
		            		that.$Message.error(data.data[i]);
		            	}
		            }else{
		            	that.$Message.success('提交申请成功');
		            }
		            $("#main").contents().find('.bench-apply').prop('disabled',true)
				}
				
			};
			var cache = false;
			var alone = true;
			var async = true;
			postAsync(url, data, success, cache, alone, async);
         
		
		},
		// ·模态框共同 eval(window.top.vm.showModal)('vm.#点击确认后执行的方法名#')
		// ·只适用iframe内
		showModal: function(ok){
			if($.isEmptyObject(ok)){
				console.error('confirm模态框：方法名不存在');
				return false;
			}

			this.modal_confirm = true;
			this.confirm_fun = ok;
		},
		modal_confirm_ok: function(){
			this.modal_confirm = false;
			$("#main")[0].contentWindow.eval(this.confirm_fun)();
			this.confirm_fun = '';
		},
		modal_confirm_cancel: function(){
			this.modal_confirm = false;
			this.confirm_fun= '';
		}
	},
	created : function() {
		this.getOrg();
	},
});
