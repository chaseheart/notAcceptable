// check 时间
const validateDate = (rule, value, callback) => {
	vm.$refs['formItem'].fields.forEach((v,i)=>{
//		v.validateDisabled = true;
//		console.log(v.$vnode.componentOptions.propsData);
		if(i == 6 || i == 7){
			v.validateState = '';
			v.validateMessage = '';
		}
		
	});
	if (!value) {
	    return callback(new Error('请选择日期'));
	}
	var startDate,startYear,startMonth,endDate,endYear,endMonth;
	startDate = vm.formItem.startDate;
	endDate = vm.formItem.endDate;
	let yearMonth = getQueryString('date').slice(0, getQueryString('date').lastIndexOf('-'))
	if(vm.formItem.startDate != '' && vm.formItem.startDate == value){
		  startYear = startDate.getFullYear();
		  startMonth = startDate.getMonth() + 1;
		  if(yearMonth != (startYear + '-' + startMonth)){
				return callback(new Error('请选择正确的开始日期'));
		  }
	}
	if(vm.formItem.endDate != '' && vm.formItem.endDate == value){
    	 endYear = endDate.getFullYear();
    	 endMonth = endDate.getMonth() + 1;
    	 if(yearMonth != (endYear + '-' + endMonth)){
 			return callback(new Error('请选择正确的结束日期'));
 		}
	}
	if(vm.formItem.endDate != '' && vm.formItem.startDate != '' && startDate > endDate){
			 return callback(new Error('请选择正确的日期'));
	}
	callback();
};

var vm = new Vue({
	el : '#app',
	data : {
		formItem : {
			workId : '',
			user : '',
			reason : '',
			location : '',
			address : '',
			contacts : '',
			phone : '',
			type : '',
			startDate : '',
			endDate : '',
			content : '',
			assigner : ''
		},
		disabled_flg:false,
		showDialog:true,
		formValidate : {
			reason : [ {
				required : true,
				message : '请输入外出公务事由',
				trigger : 'blur'
			} ],
			location : [ {
				required : true,
				message : '请输入外出公务去向',
				trigger : 'blur'
			} ],
			type : [ {
				required : true,
				message : '请选择外出公务类型',
				trigger : 'change'
			} ],
			startDate : [ {
				required : true,
				type : 'date',
				// message : '请选择外出开始时间',
				validator: validateDate,
				trigger : 'change',
			} ],
			endDate : [ {
				required : true,
				type : 'date',
				//message : '请选择外出结束时间',
				validator: validateDate,
				trigger : 'change',
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
			if(userData.isEnd == true){
				$("#appDepart").remove();
			}
			// 初次申请，没有后续申请者，不允许申请
			if(!(userData.isEnd == true && $("#type").val() == 'write')){
				this.getTypeAndHtml($("#type").val(),$("#html").val());
			}
			let day = this.formItem.date;
// let status = '暂无打卡记录';
			if (servicePerformance != '') {
				day = new Date(servicePerformance.day);
// status = servicePerformance.oaStart + '~' + servicePerformance.oaEnd;
				$("#servicePerformanceId").val(servicePerformance.id);
			}
			let formItem ={};
			if( $("#outBusiness").val() != ''){
				let outBusiness = JSON.parse($("#outBusiness").val())[0];
				formItem = {
						workId : outBusiness.userWorkId,
						user : outBusiness.username,
						reason : outBusiness.businessReason,
						location : outBusiness.businessPlace,
						address : outBusiness.placeDetail,
						contacts : outBusiness.contactName,
						type : outBusiness.outType.toString(),
						startDate :  outBusiness.outBusinessStart,
						endDate : outBusiness.outBusinessEnd,
						content : outBusiness.content,
						assigner : ''
				}
			}else{
				formItem = {
					workId : userData.workId,
					user : userData.username,
					reason : '',
					location : '',
					address : '',
					contacts : '',
					type : '1',
					startDate : day,
					endDate : day,
					content : '',
					assigner : ''

				};
				
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
					var data = {
							businessReason : this.formItem.reason,
							businessPlace : this.formItem.location,
							placeDetail : this.formItem.address,
							contactName : this.formItem.contacts,
							contactPhone : this.formItem.phone,
							outType : this.formItem.type,
							outBusinessStart : this.formItem.startDate.toLocaleDateString(),
							outBusinessEnd : this.formItem.endDate.toLocaleDateString(),
							content : this.formItem.content,
							assigner : this.formItem.assigner
					}
					let ruFlowId = $("#ruFlowId").val() == ''?0:$("#ruFlowId").val()
					var url = "/outBusiness/application/"+$("#type").val()+"/"+ruFlowId;
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
					window.top.v.$Message.error('提交申请失败');
				}
			})
			
		},
		getTypeAndHtml : function(type , html){
			// ·加载审批人
			if(type ==''){
				$("#appDepart").remove();
			}else{
				$("#iform").append(html);
			}
			// ·表单只读
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
					var url = "/outBusiness/dealApplication";
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
		var servicePerformance = '';
		if ($("#servicePerformance").val() != undefined && $("#servicePerformance").val() != '') {
			servicePerformance = JSON.parse($("#servicePerformance").val())[0];
		}
		this.getFormItem(servicePerformance);
	}
});
function getQueryString(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}