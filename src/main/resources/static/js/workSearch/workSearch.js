var vm = new Vue({
	el : '#app',
	data : {
		formItem : {
			workId : '',
			user : '',
			org : '',
			depart : '',
			date : '',
			actualAttendance : ''
		},
		orgList : [],
		departList : [],
		resultTable : [ {
			title : '工号',
			key : 'id',
			className : 'table-col-3',
			render : function(h, params) {
				var functionList = [];
				var show = h('a', {
					props : {
						type : 'error',
					},
					attrs : {
						href : '/servicePerformanceManage/index?workId=' + params.row.id + '&date=' + $("#date").val(),
						'class' : 'link-error'
					},
					on : {
						click : function() {
						}
					}
				}, params.row.id);
				functionList.push(show);
				return h('span', {

				}, functionList);
			}
		}, {
			title : '姓名',
			key : 'username',
			className : 'table-col-1'
		}, {
			title : '部门',
			key : 'departName',
			className : 'table-col-3'
		}, {
			title : '加班[小时]',
			key : 'overtimeHour',
			className : 'table-col-3'
		}, {
			title : '非正常考勤[次]',
			key : 'unusualTime',
			className : 'table-col-3'
		}, {
			title : '缺勤[小时]',
			key : 'lackHour',
			className : 'table-col-1'
		}, {
			title : '事假[天]',
			key : 'plDay',
			className : 'table-col-1'
		}, {
			title : '病假[天]',
			key : 'slDay',
			className : 'table-col-1'
		}, {
			title : '带薪休假[天]',
			key : 'annualLeave',
			className : 'table-col-3'
		}, {
			title : '调休[小时]',
			key : 'paidLeave',
			className : 'table-col-1'
		}, {
			title : '实勤[小时]',
			key : 'actualAttendance',
			className : 'table-col-1'
		}, {
			title : '备注',
			key : 'content',
			className : 'table-col-1'
		} ],
		resultData : []
	},
	methods : {
		getFormItem : function(workData) {
			var resultData = [];
			for ( let i in workData) {
				var data = {
					id : workData[i].workId,
					username : workData[i].username,
					departName : workData[i].departName,
					overtimeHour : workData[i].overtimeHour,
					unusualTime : workData[i].unusualTime,
					lackHour : workData[i].lackHour,
					plDay : workData[i].plDay,
					slDay : workData[i].slDay,
					annualLeave : workData[i].annualLeave,
					paidLeave : workData[i].paidLeave,
					actualAttendance : workData[i].performanceActual,
					content : workData[i].content
				}
				resultData.push(data);
			}
			this.resultData = resultData;
		},
		getOrg : function() {
			let orgData = $("#orgdto").val() != '' ? JSON.parse($("#orgdto").val()) : ''
			for ( let i in orgData) {
				let data = {
					value : orgData[i].id,
					label : orgData[i].orgName
				};
				this.orgList.push(data)
			}

		},
		getDepart : function(id) {
			this.formItem.depart = '';
			let orgData = JSON.parse($("#orgdto").val());
			let departList = [];
			for ( let i in orgData) {
				if (orgData[i].id == id) {

					let departDto = orgData[i].departDto;
					for ( let n in departDto) {
						let data = {
							value : departDto[n].id,
							label : departDto[n].departName
						};
						console.log(data);
						departList.push(data)
					}

				}
			}
			this.departList = departList;
		},
		searchOA : function() {
			var that = this;
			$("#date").val(this.formItem.date.getFullYear() + '-' + (this.formItem.date.getMonth() + 1));
			let condition = {
				workId : this.formItem.workId,
				user : this.formItem.user,
				org : this.formItem.org,
				depart : this.formItem.depart,
				date : this.formItem.date.getFullYear() + '-' + (this.formItem.date.getMonth() + 1)
			}
			var url = "/workSearch/searchWork";
			var success = function(data) {
				ajaxStatus = true;
				var assignerList = [];
				if (data.status == '200') {
					that.getFormItem(data.data);
				}
				that.assignerList = assignerList;
			};
			var cache = false;
			var alone = true;
			post(url, condition, success, cache, alone);
		},
		excelUp : function() {
			$("#excelUpdate").click();
		},
		excelChange : function() {
			$("#excelUpdate").bind('change', function() {
				var formData = new FormData();
				var fileData = $("#excelUpdate").prop("files")[0];
				formData.append("excelFile", fileData);

				var postUrl = SERVER_URL_HEAD_AJAX + "/excel/importData";
				$.ajax({
					type : "post",
					url : postUrl,
					dataType : "json",
					processData : false,
					contentType : false,
					data : formData,
					success : function(data) {
						if (data.status == 200) {
							indow.top.vm.$Message.success('提交成功');
							window.top.vm.$Message.error('提交失败 ' + data.msg + '条');
						}
					}
				});
				// 清空file
				$("#excelUpdate").val('');
			});
		},
		excelDown : function() {
			let workId = this.formItem.workId == '' ? '0' : this.formItem.workId;
			let user = this.formItem.user == '' ? '0' : this.formItem.user;
			let org = this.formItem.org == '' ? '0' : this.formItem.org;
			let depart = this.formItem.depart == '' ? '0' : this.formItem.org;
			$("#workSearchForm").attr("action",
					"/excel/exportData/" + workId + '/' + user + '/' + org + '/' + depart + '/' + (this.formItem.date.getFullYear() + '-' + (this.formItem.date.getMonth() + 1)));
			$("#getTemplSubmit").click();
		},
		excelOverWorkDown : function() {
			$("#excelOverWorkDown").click();
		}
	},
	created : function() {
		this.getOrg();
		this.formItem.date = $("#date").val();
		if ($("#workSearch").val() != '') {
			var workData = JSON.parse($("#workSearch").val());
			this.getFormItem(workData);
		}
		this.excelChange();
	}
});