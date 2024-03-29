var vm = new Vue({
	el : '#app',
	data : {
		resultTable : [ {
			title : 'ID',
			key : 'id',
			className : 'table-col-5'
		}, {
			title : '部门名称',
			key : 'departName',
			className : 'table-col-5'
		}, {
			title : '所属',
			key : 'org',
			className : 'table-col-5'
		}, {
			title : '操作',
			key : 'action',
			align:'center',
			className : 'table-col-5',
			render: (h, params) => {
                return h('div', [
                    h('Button', {
                        props: {
                            type: 'primary',
                        },
                        style: {
                            marginRight: '5px'
                        },
                        on: {
                            click: () => {
                            	window.top.vm.departModal = true;
                            	eval(window.top.vm.showDeaprtModal)('vm.updateDepart',params.row.id);
                            }
                        }
                    }, '修改'),
                    h('Button', {
                        props: {
                            type: 'error',
                        },
                        on: {
                            click: () => {
// window.top.vm.modal_delete_confirm = true;
// window.top.vm.currentPage = 'department';
                            	this.currentId = params.row.id;
                            	eval(window.top.vm.showModal)('vm.deleteDepart')
// eval(window.top.vm.showDeleteConfirm)('vm.deleteDepart',params.row.id);
                            }
                        }
                    }, '删除')
                ]);
            }
		}  ],
		resultData : [],
		orgList : [],
		departList : [],
		currentId:''
	},
	methods : {
		getDepart : function() {
			let departdto = $("#departdto").val() != '' ? JSON.parse($(
					"#departdto").val()) : ''
			var resultData = [];
			var resultItem = {};
			if (departdto.length != 0) {
				for ( let i in departdto) {
					resultItem = {
						id : departdto[i].id,
						departName : departdto[i].departName,
						org : departdto[i].orgName
					}
					resultData.push(resultItem);
				}
			}
			this.resultData = resultData;
		},
		addNewDepart:function(){
			eval(window.top.vm.showDeaprtModal)('vm.insertDepart');
		},
		insertDepart:function(departModalForm){
			var that = this;
			var data = {
				departName : departModalForm.departName,
				org : departModalForm.org
			};
			var url = "/departManage/setNewDepart";
			var success = function(data) {
				ajaxStatus = true;
				if (data.status == '200') {
					window.top.vm.$Message.success('添加成功');
					window.location.reload();
				}else if (data.status == '400'){
					window.top.vm.$Message.error(data.msg);
				}
			};
			var cache = false;
			var alone = true;
			post(url, data, success, cache, alone);
		},
		updateDepart:function(formItem){
			console.log(formItem);
			var that = this;
			var data = {
				id:formItem.id,
				departName : formItem.departName,
				org : formItem.org
			};
			var url = "/departManage/updateDepart";
			var success = function(data) {
				ajaxStatus = true;
				if (data.status == '200') {
					window.top.vm.$Message.success('修改成功');
					window.location.reload();
				}else if (data.status == '400'){
					window.top.vm.$Message.error(data.msg);
				}
			};
			var cache = false;
			var alone = true;
			post(url, data, success, cache, alone);
		},
		deleteDepart:function(){
			var that = this;
			var data = {
				'departId' : currentId
			};
			var url = "/departManage/deleteDepart";
			var success = function(data) {
				ajaxStatus = true;
				if (data.status == 200) {
					window.top.vm.$Message.success('删除成功');
					window.location.reload();
				}else if (data.status == 700){
					window.top.vm.$Message.error('部门下存在人员，不能删除');
				}
			};
			var cache = false;
			var alone = true;
			post(url, data, success, cache, alone);
		}
	},
	created : function() {
		this.getDepart();
	}
});