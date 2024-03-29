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
		orgModal:false,
		personModal:false,
        loading: false,
        orgList : [],
        orgListD : [],
        departList : [],
        roleList:[],
		assignerList : [],
		formValidate : {
			assigner: [ {
				required : true,
				message : '请选择审批者' 
			} ]
		},
		
		// 组织模态框
		orgModalForm:{
			orgName:'',
			id:''
		},
		orgFormValidate:{
			orgName:[{
				required : true,
				message : '请输入组织名称',
				trigger : 'blur'
			}]
		},
		// 人员模态框
		personModalForm:{
			id:'',
			username:'',
			workId:'',
			depart:'',
			role:'',
			phone:'',
			email:'',
			calType:'0'
		},
		personFormValidate:{
			workId:[{
				required : true,
				message : '请输入人员工号',
				trigger : 'blur'
			}],
			username:[{
				required : true,
				message : '请输入人员姓名',
				trigger : 'blur'
			}],
			depart:[{
				required : true,
				message : '请选择部门',
//				trigger : 'change'
			}],
			role:[{
				required : true,
				message : '请选择角色',
//				trigger : 'change'
			}],
			calType:[{
				required : true,
				message : '请选择放假类型',
				trigger : 'change'
			}]
		},
		// 组织模态框
		departModalForm:{
			id:'',
			departName:'',
			org:''
		},
		departFormValidate:{
			departName:[{
				required : true,
				message : '请输入组织名称',
				trigger : 'blur'
			}],
			org:[{
				required : true,
				message : '请选择所属组织',
				trigger : 'change'
			}]
		},
		currentPage:'',
		orgModal:false,
		personModal:false,
		departModal:false,
		modal_delete_confirm :false,
		
		roleModal:false,
		roleModalForm:{
			roleName:''
		},
		roleModalTable:[ {
			type : 'selection',
			className : 'table-col-5',
		}, {
			title : 'ID',
			key : 'id',
			className : 'table-col-5'
		}, {
			title : '角色名称',
			key : 'roleName',
			className : 'table-col-5'
		}],
		roleModalTableData:[{
			id : '',
			roleName : ''
		}],
		roleSelection : '',
		roleCancelRow : '',
		
		username:''
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
			if($("#org").val() != ''){
				let orgData = JSON.parse($("#org").val());
				for ( let i in orgData) {
					let data = {
						value :  orgData[i].id,
						label : orgData[i].orgName
					};
					this.orgListD.push(data)
				}
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
        org_model_cancel () {
        	this.orgModal = false;
        },
        pserson_model_cancel () {
        	this.personModal = false;
        },
        depart_model_cancel () {
        	this.departModal = false;
        	this.departModalForm.org='';
        	this.departModalForm.departName='';
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
		},
		// ·模态框共同 eval(window.top.vm.showModal)('vm.#点击确认后执行的方法名#')
		// ·只适用iframe内
		showOrgModal: function(ok,id,orgName){
			if($.isEmptyObject(ok)){
				console.error('confirm模态框：方法名不存在');
				return false;
			}
			this.orgModal = true;
			this.confirm_fun = ok;
			this.orgModalForm.id=id;
			this.orgModalForm.orgName=orgName;
		},
		modal_confirm_org_ok: function(){
			this.orgModal = false;
			$("#main")[0].contentWindow.eval(this.confirm_fun)(this.orgModalForm.orgName,this.orgModalForm.id);
			this.confirm_fun = '';
		},
		// ·模态框共同 eval(window.top.vm.showModal)('vm.#点击确认后执行的方法名#')
		// ·只适用iframe内
		showDeaprtModal: function(ok,id ){
			if($.isEmptyObject(ok)){
				console.error('confirm模态框：方法名不存在');
				return false;
			}
			this.departModal = true;
			this.confirm_fun = ok;
			if(id!=null){
				this.departModalForm.id=id;
				this.searchOneDepart(id);
			}
		},
		modal_confirm_depart_ok: function(){
			this.$refs["departModalForm"].validate((valid)=>{
				if (valid) {
					this.departModal = false;
					var form={
							departName : this.departModalForm.departName,
							org : this.departModalForm.org,
							id : this.departModalForm.id
					} 
					this.departModalForm.departName='';
					this.departModalForm.org='';
					this.departModalForm.id='';
					$("#main")[0].contentWindow.eval(this.confirm_fun)(form);
					this.confirm_fun = '';
				}
			})
		},
	// ·模态框共同 eval(window.top.vm.showModal)('vm.#点击确认后执行的方法名#')
	// ·只适用iframe内
	showPersonModal: function(ok,id){
		if($.isEmptyObject(ok)){
			console.error('confirm模态框：方法名不存在');
			return false;
		}
		// 生成部门
		this.searchDepart();
		if(id!=null){
			this.serachPerson(id);
		}
		if(ok == 'vm.insertPerson'){
			this.$refs['personModalForm'].resetFields();
		}
		this.personModal = true;
		this.confirm_fun = ok;
	},
	modal_confirm_person_ok: function(){
		this.$refs["personModalForm"].validate((valid)=>{
			if (valid) {
				this.personModal = false;
				console.log(this.personModalForm);
				var data={
						id: this.personModalForm.id,
						calType: this.personModalForm.calType,
						depart: this.personModalForm.depart,
						email: this.personModalForm.email,
						phone: this.personModalForm.phone,
						role: this.personModalForm.role,
						username: this.personModalForm.username,
						workId: this.personModalForm.workId
				}
				$("#main")[0].contentWindow.eval(this.confirm_fun)(data);
				this.$refs['personModalForm'].resetFields();
				this.confirm_fun = '';
			}
		})
	},
	serachPerson:function(id){
		var that = this;
		var data = {
				id:id
		};
		var url = "/personManage/searchOne";
		var success = function(data) {
			ajaxStatus = true;
			console.log(data);
			let personModalForm={
					id:id,
					workId:data.data.workId,
					username:data.data.username,
					depart:data.data.departId,
					role:data.data.roleId,
					phone:data.data.phone,
					email:data.data.email,
					calType:data.data.vacationType?'1':'0' 
			}
			that.personModalForm=personModalForm;
		};
		var cache = false;
		var alone = true;
		post(url, data, success, cache, alone);
	},
	searchDepart:function(){
		var that = this;
		var data = {};
		var result={};
		var url = "/departManage/searchAll";
		var success = function(data) {
			ajaxStatus = true;
			console.log(data);
			var roleList=[];
			var departList=[];
			for(let i in data.data.depart){
				let depart = {
					value : data.data.depart[i].id,
					label : data.data.depart[i].departName
				}
				departList.push(depart);
			}
			for(let i in data.data.role){
				let role = {
					value : data.data.role[i].id,
					label : data.data.role[i].roleName
				}
				roleList.push(role);
			}
			that.departList=departList;
			that.roleList=roleList;
			result=data.data.depart;
		};
		var cache = false;
		var alone = true;
		post(url, data, success, cache, alone);
		return result;
	},
	// 检索部门详情
	searchOneDepart:function(id){
		var that = this;
		var data = {
			id:id
		};
		var url = "/departManage/searchOne";
		var success = function(data) {
			ajaxStatus = true;
			that.departModalForm.departName=data.data.departName;
			that.departModalForm.id=data.data.id;
			that.departModalForm.org=data.data.orgId;
		};
		var cache = false;
		var alone = true;
		post(url, data, success, cache, alone);
	},
	showStepRoleModal: function(ok,id){
		if($.isEmptyObject(ok)){
			console.error('confirm模态框：方法名不存在');
			return false;
		}
		// 生成部门
		
		this.roleModal = true;
		this.confirm_fun = ok;
	},
	checkAuthority : function(){
		let authority = $("#authority").val() != '' ? JSON.parse($(
		"#authority").val()) : '';
		// console.log(authority);
		$('.needCheck').each(function(){
			menuNo = 'ROLE_'+ $(this).attr('id').split('_')[1];
			if($.inArray(menuNo,authority) < 0){
				$(this).remove();
			}
		})
		$('.ivu-menu-submenu ').each(function(){
			if($(this).find('.ivu-menu-item').length == 0){
				$(this).remove();
			}
		})
		
	},
	fixArr : function(arr, idArr, stepId){
		for(let i in idArr){
			for(let j in arr){
				if(idArr[i].id == parseInt(arr[j].id) && stepId == parseInt(arr[j].stepId)){
					arr.splice(j, 1);
				}
			}
		}
		// 。删除去掉的角色
		var roleCancelRow = this.roleCancelRow;
		if(roleCancelRow != undefined){
			let cancelArr = [];
			roleCancelRow = cancelArr.concat(roleCancelRow);
		}
		for(let i in roleCancelRow){
			for(let j in arr){
				if(roleCancelRow[i].id == parseInt(arr[j].id) && stepId == parseInt(arr[j].stepId)){
					arr.splice(j, 1);
				}
			}
		}
		// add 新的角色id
		for(let i in idArr){
			arr.push({
				id : idArr[i].id,
				roleName : idArr[i].roleName,
				stepId : stepId
			});
		}
		return arr;
	},
	// 。角色步骤模态框确认
	modal_confirm_stepRole_ok : function(){
		var that = this;
		var vm = $("#main")[0].contentWindow.vm;
		var clickObj = vm.clickObj;
		if(clickObj == 'normalFlow'){
			vm.nFlow = this.fixArr(vm.nFlow, that.roleSelection, vm.clickNo);
		}else if(clickObj == 'reFlow'){
			vm.reFlow = this.fixArr(vm.reFlow, that.roleSelection, vm.clickNo);
		}else if(clickObj == 'peFlow'){
			vm.nPeFlow = this.fixArr(vm.nPeFlow, that.roleSelection, vm.clickNo);
		}
		that.roleModal = false;
		that.roleSelection = '';
		that.roleCancelRow = '';
	},
	// 。角色步骤模态框取消
	stepRole_model_cancel : function(){
		this.roleModal = false;
		this.roleSelection = '';
		this.roleCancelRow = '';
	},
	// checkbox勾选变化数组处理
	createArr : function(totalRole, selection, cancelRow){
		var arr = $.extend(true, [], totalRole);
		for(let j in selection){
			for(let i in arr){
				if(parseInt(arr[i].id) == selection[j].id){
					arr.splice(i,1);
					break;
				}
			}
		}
		if(cancelRow.length > 0 || cancelRow != undefined){
			arr = arr.concat(cancelRow);
		}
		return arr;
	},
	// checkbox勾选变化
	roleChange : function(selection){
		this.roleSelection = selection;
		var vm = $("#main")[0].contentWindow.vm;
		var clickObj = vm.clickObj;
		if(clickObj == 'normalFlow'){
			vm.attendanceRole = this.createArr(vm.attendanceRole, selection, this.roleCancelRow);
		}else if(clickObj == 'peFlow'){
			vm.pAttendanceRole = this.createArr(vm.pAttendanceRole, selection, this.roleCancelRow)
		}else if(clickObj == 'reFlow'){
			vm.reimbursementRole = this.createArr(vm.reimbursementRole, selection, this.roleCancelRow)
		}
	},
	// checkbox勾选取消
	roleCancel : function(selection, row){
		if(this.roleCancelRow == undefined){
			this.roleCancelRow = row;
		}else{
			let cancelArr = [];
			this.roleCancelRow = cancelArr.concat(this.roleCancelRow).concat(row);
		}
		
	},
	// checkbox勾选取消
	roleCancel : function(selection, row){
		if(this.roleCancelRow == undefined){
			this.roleCancelRow = row;
		}else{
			let cancelArr = [];
			this.roleCancelRow = cancelArr.concat(this.roleCancelRow).concat(row);
		}
		
	},
	roleAllCancel : function(selection){
		var list;
		var totalRoleArr;
		var cancelArr = [];
		var vm = $("#main")[0].contentWindow.vm;
		let stepId = vm.clickNo;
		var clickObj = vm.clickObj;
		if(clickObj == 'normalFlow'){
			totalRoleArr = vm.nFlow;
			list = vm.getItem(totalRoleArr, stepId);
		}else if(clickObj == 'peFlow'){
			totalRoleArr = vm.nPeFlow;
			list = vm.getItem(vm.nPeFlow, stepId);
		}else if(clickObj == 'reFlow'){
			totalRoleArr = vm.reFlow;
			list = vm.getItem(vm.reFlow, stepId);
		}
		for(let i in list){
			for(let j in totalRoleArr){
				if(totalRoleArr[j].id == list[i] && totalRoleArr[j].stepId ==stepId){
					cancelArr.push(totalRoleArr[j]);
				}
			}
		}
		this.roleCancelRow = cancelArr;
	}
	
},
created : function() {
	this.getOrg();
	this.username = $("#username").val();
},
mounted : function(){
	this.checkAuthority();
}
});
