var vm = new Vue({
	el : '#app',
	data : {
		resultTable : [ {
			title : 'ID',
			key : 'id',
			className : 'table-col-5'
		}, {
			title : '权限名称',
			key : 'roleName',
			className : 'table-col-5'
		}, {
			title : '等级',
			key : 'level',
			className : 'table-col-5'
		} ],
		resultData : [],
//		orgList : [],
//		departList : [],
	},
	methods : {
		getRole : function() {
			let roledto = $("#roledto").val() != '' ? JSON.parse($(
					"#roledto").val()) : ''
			var resultData = [];
			var resultItem = {};
			if (roledto.length != 0) {
				for ( let i in roledto) {
					resultItem = {
						id : roledto[i].id,
						roleName : roledto[i].roleName,
						level : roledto[i].level
					}
					resultData.push(resultItem);
				}
			}
			this.resultData = resultData;
		},
	},
	created : function() {
		this.getRole();
	}
});