var vm = new Vue({
	el : '#app',
	data : {
		formItem : {
			workId : '',
			username : '',
			org : '',
			depart : ''
		},
		resultTable : [ {
			title : 'ID',
			key : 'id',
			className : 'table-col-5'
		}, {
			title : '工号',
			key : 'workId',
			className : 'table-col-5'
		}, {
			title : '姓名',
			key : 'username',
			className : 'table-col-5'
		}, {
			title : '组织',
			key : 'org',
			className : 'table-col-5'
		}, {
			title : '部门',
			key : 'depart',
			className : 'table-col-5'
		}, {
			title : '角色',
			key : 'role',
			className : 'table-col-5'
		}, {
			title : '放假类型',
			key : 'vacationType',
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
                        			eval(window.top.vm.showPersonModal)('vm.updatePerson',params.row.id);
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
// window.top.vm.currentPage = 'person';
                            	this.currentId = params.row.id;
                            	eval(window.top.vm.showModal)('vm.deletePerson');
                            }
                        }
                    }, '删除')
                ]);
            }
		} ],
		resultData : [],
		orgList : [],
		departList : [],
		lineNum : 0,
		pageable : {
			page : 0,
			size : 10
		},
		currentId:''
	},
	methods : {
		getPersonData : function(data) {
			var resultData = [];
			var personData = {};
			for ( let i in data.content) {
				personData = {
					id : data.content[i].id,
					workId : data.content[i].workId,
					username : data.content[i].username,
					org : data.content[i].org,
					depart : data.content[i].depart,
					role : data.content[i].roleName,
					vacationType : data.content[i].vacationType ? '日本' : '中国'
				}
				resultData.push(personData);
			}
			this.resultData = resultData;
			this.lineNum = data.totalElements;
		},
		getOrg : function() {
			let orgData = $("#orgdto").val() != '' ? JSON.parse($("#orgdto").val()) : ''
			// console.log(orgData);
			for ( let i in orgData) {
				let data = {
					value : orgData[i].id,
					label : orgData[i].orgName
				};
				this.orgList.push(data)
			}
		},
		getDepart : function(id) {
			let orgData = JSON.parse($("#orgdto").val());
			var departDto;
			for ( let i in orgData) {
				if (orgData[i].id == id) {
					this.formItem.depart = '';
					departDto = orgData[i].departDto;
				}
			}
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
		// 检索用户信息
		searchPersonData : function() {
			var formItem = {
				workId : this.formItem.workId,
				username : this.formItem.username,
				org : this.formItem.org,
				depart : this.formItem.depart,
				page : this.pageable.page,
				size : this.pageable.size
			}
			console.log(formItem);
			var that = this;
			var url = "/personManage/searchPersonData";
			var success = function(data) {
				ajaxStatus = true;
				// that.getFromItem(data.data);
				// console.log(data.data);
				var resultData = [];
				var personData = {};
				if (data.data.content.length != 0) {
					for ( let i in data.data.content) {
						personData = {
							id : data.data.content[i].id,
							workId : data.data.content[i].workId,
							username : data.data.content[i].username,
							org : data.data.content[i].org,
							depart : data.data.content[i].depart,
							role : data.data.content[i].roleName,
							vacationType : data.data.content[i].vacationType ? '日本' : '中国'
						}
						resultData.push(personData);
					}
				}
				that.resultData = resultData;
				that.lineNum = data.data.count;
			};
			var cache = false;
			var alone = true;
			post(url, formItem, success, cache, alone);
		},
		updatePerson : function(personModalForm){
			var that = this;
			var data = {
				id : personModalForm.id,
				workId : personModalForm.workId,
				username : personModalForm.username,
				depart : personModalForm.depart,
				role : personModalForm.role,
				phone : personModalForm.phone,
				email :	personModalForm.email,
				calType : personModalForm.calType
			};
			var url = "/personManage/updatePerson";
			var success = function(data) {
				ajaxStatus = true;
				if (data.status == '200') {
					window.top.vm.$Message.success(data.msg);
					window.location.reload();
				}else{
					window.top.vm.$Message.error(data.msg);
				}
			};
			var cache = false;
			var alone = true;
			post(url, data, success, cache, alone);
			
		
		},
		changeSize : function(size) {
			this.pageable.size = size;
			this.searchPersonData();
		},
		changePage : function(page) {
			this.pageable.page = page - 1;
			this.searchPersonData();
		},
		// 上传excel
		excelUp : function() {
			$("#excelUpdate").click();
		},
		excelChange : function() {
			$("#excelUpdate").bind('change', function() {
				var formData = new FormData();
				var fileData = $("#excelUpdate").prop("files")[0];
				formData.append("excelFile", fileData);
				var postUrl = SERVER_URL_HEAD_AJAX + "/excel/importExcelUser";
				$.ajax({
					type : "post",
					url : postUrl,
					dataType : "json",
					processData : false,
					contentType : false,
					data : formData,
					success : function(data) {
						if (data.status == 200) {
							console.log(data.data);
							window.top.vm.$Message.success('用户导入操作成功');
							if(data.data.length!=0){
								var msg='';
								for(let i in data.data){
									msg += data.data[i] + "  ";
								}
								window.top.vm.$Message.error('导入失败：'+data.data.length+'条，序号：'+msg);
								window.location.reload();
							}

						}
					}
				});
				// 清空file
				$("#excelUpdate").val('');
			});
		},
		// 新增用户
		addNewPerson:function(){
			eval(window.top.vm.showPersonModal)('vm.insertPerson');
		},
		insertPerson:function(personModalForm){
			var that = this;
			console.log(personModalForm);
			var data = {
				workId : personModalForm.workId,
				username : personModalForm.username,
				depart : personModalForm.depart,
				role : personModalForm.role,
				phone : personModalForm.phone,
				email :	personModalForm.email,
				calType : personModalForm.calType
			};
			var url = "/personManage/setNewPerson";
			var success = function(data) {
				ajaxStatus = true;
				if (data.status == '200') {
					window.location.reload();
				}else{
					window.top.vm.$Message.error(data.msg);
				}
			};
			var cache = false;
			var alone = true;
			post(url, data, success, cache, alone);
		},
		// 删除用户
		deletePerson:function(){
			var that = this;
			var data = {
				'personId' : currentId
			};
			var url = "/personManage/deletePerson";
			var success = function(data) {
				ajaxStatus = true;
				if (data.status == 200) {
					window.top.vm.$Message.success('删除成功');
					window.location.reload();
				}
			};
			var cache = false;
			var alone = true;
			post(url, data, success, cache, alone);
		}
	},
	created : function() {
		this.getOrg();
		if ($("#userList").val() != '') {
			this.getPersonData(JSON.parse($("#userList").val()))
		}
		this.excelChange();
	}
});