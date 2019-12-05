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
		} ],
		resultData : [],
		orgList : [],
		departList : [],
		lineNum : 0,
		pageable:{
			page:0,
			size:10
		}
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
			let orgData = $("#orgdto").val() != '' ? JSON.parse($("#orgdto")
					.val()) : ''
//			console.log(orgData);
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
//			console.log(departDto);
			let departList = [];
			for ( let n in departDto) {
				let data = {
					value : departDto[n].id,
					label : departDto[n].departName
				};
//				console.log(data);
				departList.push(data)
			}
			this.departList = departList;
		},
		searchPersonData : function() {
			var formItem = {
				workId : this.formItem.workId,
				username : this.formItem.username,
				org : this.formItem.org,
				depart : this.formItem.depart,
				page:this.pageable.page,
				size:this.pageable.size
			}
			console.log(formItem);
			var that = this;
			var url = "/personManage/searchPersonData";
			var success = function(data) {
				ajaxStatus = true;
				// that.getFromItem(data.data);
//				console.log(data.data);
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
							vacationType : data.data.content[i].vacationType ? '日本'
									: '中国'
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
		changeSize:function(size){
			this.pageable.size = size;
			this.searchPersonData();
		},
		changePage:function(page){
			this.pageable.page = page - 1;
			this.searchPersonData();
		}
	},
	created : function() {
		this.getOrg();
		if ($("#userList").val() != '') {
			this.getPersonData(JSON.parse($("#userList").val()))
		}
	}
});