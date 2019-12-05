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
		} ],
		resultData : [],
		orgList : [],
		departList : [],
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
	},
	created : function() {
		this.getDepart();
	}
});