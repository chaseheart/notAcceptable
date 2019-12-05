var vm = new Vue({
	el : '#app',
	data : {
		formItem : {
			workId : '',
			username : ''
		},
		resultTable : [ {
			type : 'selection',
			render : function(h, params) {
				console.log();
			}

		}, {
			title : '处理',
			key : 'id',
			className : 'table-col-5',
			render : function(h, params) {
				var functionList = [];
				var show = h('a', {
					attrs : {
						href : "/" + params.row.appType + "/pending?ruFlowId=" + params.row.id + "&type=notslef",
					},
				}, params.row.id);
				functionList.push(show);
				return h('span', {

				}, functionList);
			}
		}, {
			title : 'appType',
			key : 'appType',
			showPrise : false,
			showRentPrise : false,
			type : 'hidden',
			className : 'table-col-5'
		}, {
			title : '申请类型',
			key : 'type',
			className : 'table-col-5'
		}, {
			title : '申请者',
			key : 'user',
			className : 'table-col-5'
		}, {
			title : '申请日期',
			key : 'date',
			className : 'table-col-5'
		}, {
			title : '最后处理者',
			key : 'lasuUpdateUser',
			className : 'table-col-5'
		}, {
			title : '最后处理日期',
			key : 'lasuUpdateTime',
			className : 'table-col-5'
		} ],
		resultData : []
	},
	methods : {
		getFromItem : function(data) {
			let resultData = [];
			for ( let key in data) {
				for ( let i in data[key]) {
					resultData.push({
						id : data[key][i].appId,
						appType : key,
						type : '报销',
						user : data[key][i].appUsername,
						date : data[key][i].appStartTime,
						lasuUpdateUser : data[key][i].dealPeople,
						lasuUpdateTime : data[key][i].appEndtTime
					});
				}
			}
			this.resultData = resultData;
		},
		searchClaimingExpenses : function() {
			var data = {
				workId : this.formItem.workId,
				username : this.formItem.username
			}
			console.log(data);
			var that = this;
			var url = "/claimingExpenses/getDataByCondition";
			var success = function(data) {
				ajaxStatus = true;
				that.getFromItem(data.data);
				console.log(data.data);
			};
			var cache = false;
			var alone = true;
			post(url, data, success, cache, alone);
		}
	},
	created : function() {
		var data = JSON.parse($("#map").val() == '' ? '[]' : $("#map").val());
		this.getFromItem(data);

	}
});
