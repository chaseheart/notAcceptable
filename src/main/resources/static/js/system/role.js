var vm = new Vue({
	el : '#app',
	data : {
		resultTable : [ {
			title : 'ID',
			key : 'id',
			className : 'table-col-5'
		}, {
			title : '角色名称',
			key : 'roleName',
			className : 'table-col-5'
		},{
			title : '操作',
			key : 'action',
			align:'center',
			className : 'table-col-2',
			render: (h, params) => {
				 return h('div', [
					 h('Button', {
                     props: {
                         type: 'primary',
                     },
                     on: {
                         click: () => {
                        	
                         }
                     }
                 	}, '修改'),
					 h('Button', {
	                        props: {
	                            type: 'error',
	                        },
	                        on: {
	                            click: () => {
	                            	eval(window.top.vm.showModal)('vm.deleteApplyOk');
	                            }
	                        }
	                    }, '删除')
	                  
				 ]);
			}
		}],
		menuTable:[{
			title :'ID',
			key : 'id',
			className : 'table-col-5'
		},{
			type : 'selection',
			className : 'table-col-2'
		}, {
			title : '菜单名称',
			key : 'menuName',
			className : 'table-col-5'
		}],
		disabled_flg:false,
		menuData : [],
		menuSelectData : [],
		resultData : [],
		formItem : {
			id:'',
			roleName:'',
			isManager:false,
			version:''
		},
		formValidate:{
			roleName:[
				{
					required : true,
					message : '请输入角色名称',
					trigger : 'blur'
				}
			]
		},
		indeterminate: true,
        checkAll: false,
//		orgList : [],
//		departList : [],
	},
	methods : {
		// ·角色列表
		getRole : function() {
			let roledto = $("#roledto").val() != '' ? JSON.parse($(
					"#roledto").val()) : ''
			this.showRole(roledto);
		},
		searchRole : function() {
			var that = this;
			var url = "/roleManage/getAllRole";
			var success = function(data) {
				ajaxStatus = true;
				if (data.status == '200') {
					that.showRole(data.data);
				}
			}
			var cache = false;
			var alone = true;
			get(url, success, cache, alone) 
			
		},
		showRole : function(roledto){
			var resultData = [];
			var resultItem = {};
			for ( let i in roledto) {
				resultItem = {
					id : roledto[i].id,
					roleName : roledto[i].roleName,
					level : roledto[i].level
				}
				resultData.push(resultItem);
			}
			this.resultData = resultData;
		},
		// ·菜单列表
		getMenu : function(){
			let menuList = $("#menuList").val() != '' ? JSON.parse($(
			"#menuList").val()) : '';
			let menuData = [];
			for ( let i in menuList) {
				let menuItem = {
					id : menuList[i].id,
					menuName : menuList[i].menuName
				}
				menuData.push(menuItem);
			}
			
			this.menuData = menuData;
			//·角色名
			this.formItem.roleName = '';
			//·角色id
			this.formItem.id = '';
			//·是否为人事
			this.formItem.isManager = false;
			//version
			this.formItem.version = '';
		},
		//·点击角色行加载角色信息和权限
		clickRow : function(selection){
			$('#doSave').prop('disabled',false);
			$('#doSave span').html('保存修改');
			var that = this;
			var data = {
				'roleId' : selection.id
			};
			var url = "/roleManage/searchRoleInfo";
			var success = function(data) {
				ajaxStatus = true;
				if (data.status == '200') {
					//·角色名
					that.formItem.roleName = data.data.roleName;
					//·管理部
					that.formItem.isManager = data.data.isManager == 'true'?true:false;
					//·角色id
					that.formItem.id= data.data.id;
					//version
					that.formItem.version = data.data.version;
					let authorityList = data.data.authorityList;
					let menuList = $("#menuList").val() != '' ? JSON.parse($(
						"#menuList").val()) : '';
					that.menuSelectData = [];
						let menuData = [];
						for ( let i in menuList) {
							let check = getItem(authorityList,{id:menuList[i].id})
							let menuItem = {
								id : menuList[i].id,
								menuName : menuList[i].menuName,
								_checked : check
							}
							menuData.push(menuItem);
							//·保存已选项
							if(check){
								that.menuSelectData.push(menuItem);
							}
						}
						that.menuData = menuData;
				}
			};
			var cache = false;
			var alone = true;
			post(url, data, success, cache, alone);
//			console.log(selection)
		},
		// ·勾选权限
		onselect: function (selection){
			this.menuSelectData = selection;
		},
		//·确认删除
		deleteApplyOk : function(){
			let that = this;
			let postData = {
					roleId :that.formItem.id,
					version : that.formItem.version
			}
			var url = "/roleManage/deleteRole";
			var success = function(data) {
				ajaxStatus = true;
				if(data.status == 200){
					that.$Message.success('删除角色成功');
				}else if(data.status == 600){
					that.$Message.error('保存失败，角色下含有用户！');
				}
				$('#doSave span').html('新增');
				// ·重新初始化
				that.searchRole();
				that.getMenu();
			}
			var cache = false;
			var alone = true;
			post(url, postData, success, cache, alone);
		},
		cancel :function(){
			$('#doSave span').text('新增');
			// ·重新初始化
			this.searchRole();
			this.getMenu();
		},
		//·保存
		saveRole :function(){
			let that = this;
			this.$refs['formItem'].validate((valid)=>{
				if (valid) {
					let ids = [];
					for(let i in that.menuSelectData){
						ids.push(that.menuSelectData[i].id)
					}
					let postData = {
							id :that.formItem.id,
							roleName: that.formItem.roleName,
							isManager : that.formItem.isManager.toString(),
							version : that.formItem.version,
							authority :ids.toString()
					}
					var url = "/roleManage/saveRole";
					var success = function(data) {
						ajaxStatus = true;
						if(data.status == 200){
							if(that.formItem.id == ''){
								that.$Message.success('新增角色成功');
							}else{
								that.$Message.success('保存角色成功');
							}
							$('#doSave span').html('新增');
							// ·重新初始化
							that.searchRole();
							that.getMenu();
						}else if(data.status == '603'){
							that.$Message.error('保存失败:角色名重复');
						}
					}
					var cache = false;
					var alone = true;
					post(url, postData, success, cache, alone);
				}
			})
		}
	},
	created : function() {
		this.getRole();
		this.getMenu();
	}
});

/**
 * use Array 'arr' to search 'obj' 
 * @param arr
 * @param obj
 * @returns
 */
function getItem(arr,obj){
	arrFor:for(var i = 0;i<arr.length; i++){
		for(var n in obj){
			//console.log(n);
			if(arr[i][n]!=obj[n]){
				continue arrFor;
			}
		}
		return true;
	}
	return false;
}