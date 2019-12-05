var vm = new Vue({
	el : '#app',
	data : {
		resultTable : [ {
			title : '处理',
			key : 'id',
			className : 'table-col-3',
			render : function(h, params) {
				var functionList = [];
				var show = h('a', {
					attrs : {
						href : "/claimingExpenses/mine?ruFlowId=" + params.row.id + "&type=" +  params.row.writeState ,
					},
				}, params.row.id);
				functionList.push(show);
				return h('span', {

				}, functionList);
			}
		}, {
			title : '申请类型',
			key : 'type',
			className : 'table-col-5'
		}, {
			title : '状态',
			key : 'writeState',
			className : 'table-col-5'
		},{
			title : '状态',
			key : 'state',
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
				for ( let i in data) {
					resultData.push({
						id : data[i].appId,
						type : '报销',
						user : data[i].appUsername,
						date : data[i].appStartTime,
						writeState : this.getWriteState(data[i].state),
						lasuUpdateUser : data[i].dealPeople,
						lasuUpdateTime : data[i].appEndtTime,
						state : this.getStatus(data[i].state),
					});
				}
			
			this.resultData = resultData;
		},
		getStatus : function(status) {
			switch (status) {
			case "0":
				return "否认完了";
			case "1":
				return "再申请";
			case "4":
				return "确认完了";
			default:
				return "申请中";
			}
		},
		getWriteState : function(status) {
			if(status=='1'){
				return "writeAgain";
			}else{
				return "view";
			}
		}
		
	},
	created : function() {
		var data = JSON.parse($("#pendingDtoList").val() == '' ? '[]' : $("#pendingDtoList").val());
		this.getFromItem(data);

	}
});
