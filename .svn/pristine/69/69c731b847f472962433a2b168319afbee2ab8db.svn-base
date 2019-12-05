var vm = new Vue({
	el : '#app',
	data : {
		resultTable : [ {
			title : 'ID',
			key : 'id',
			className : 'table-col-5'
		}, {
			title : '组织名称',
			key : 'orgName',
			className : 'table-col-10'
		} ],
		resultData : [],
		orgList : [],
		departList : [],
	},
	methods : {
		getOrg : function() {
			let orgData = $("#orgdto").val() != '' ? JSON.parse($("#orgdto")
					.val()) : ''
//			console.log(orgData);
			var resultData = [];
			var resultItem = {};
			if (orgData.length != 0) {
				for ( let i in orgData) {
					resultItem = {
						id : orgData[i].id,
						orgName : orgData[i].orgName,
					}
					resultData.push(resultItem);
				}
			}
			this.resultData = resultData;
		},
	},
	created : function() {
		this.getOrg();
	}
});