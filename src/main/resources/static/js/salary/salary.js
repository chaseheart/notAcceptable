var vm = new Vue({
	el : '#app',
	data : {
		formItem : {
			date : ''
		},
		formValidate : {
			date : [ {
				required : true,
				type : "date",
				message : '请输入时间',
			} ],
		}

	},
	methods : {
		excelUp : function() {
			this.$refs["formItem"].validate((valid)=>{
			if (valid) {
				$("#excelUpdate").click();
			}})
		},
		excelChange : function() {
			var that = this;
			$("#excelUpdate").bind('change', function() {
				var formData = new FormData();
				var fileData = $("#excelUpdate").prop("files")[0];
				formData.append("excelFile", fileData);
				formData.append("date", that.formItem.date.getUTCFullYear() + '-' + (that.formItem.date.getMonth() + 1));
				var postUrl = SERVER_URL_HEAD_AJAX + "/excel/importSalary";
				$.ajax({
					type : "post",
					url : postUrl,
					dataType : "json",
					processData : false,
					contentType : false,
					data : formData,
					success : function(data) {
						if (data.status == 200) {
							console.log("success");
						}
					}
				});
				// 清空file
				$("#excelUpdate").val('');
			});
		},
	},
	created : function() {
		this.excelChange();
	}
});
