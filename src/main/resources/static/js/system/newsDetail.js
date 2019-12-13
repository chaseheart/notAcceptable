var vm = new Vue({
	el : '#app',
	data : {
		formItem : {
			title : '',
			type : '',
			introduction : '',
			content : ''
		},
		formValidate : {
			type : [ {
				required : true,
				message : '请选择通知类型',
			} ],
			title : [ {
				required : true,
				message : '请输入通知标题',
				trigger : 'blur'
			} ],
			content : [ {
				required : true,
				message : '请输入通知内容',
				trigger : 'blur'
			} ],
		},
		url:window.location.pathname,
		editFlag: false
	},
	methods : {
		editInit : function(){
			let data = $("#detail").val() != '' ? JSON.parse($("#detail").val()) : '';
			this.formItem.title= data.title;
			this.formItem.type= data.type;
			this.formItem.introduction= data.introduction;
			this.formItem.content= data.content;
			if(data.filePath != '' && data.filePath != null){
				$("#preview").show();
				$("#preview").attr("src",data.filePath);
				$("#cancelUp").prop("disabled",false);
			}
		},
		saveData : function(){
			this.$refs['formItem'].validate((valid)=>{
				var page = '新增通知';
				if(this.url.indexOf('Edit') != -1){
					page = '修改通知';
				}
				if (valid) {
					var that = this;
					var formData = new FormData();
					var fileData = $("#file0").prop("files")[0];
					formData.append("picFile", fileData);
					formData.append("content", that.formItem.content);
					formData.append("introduction", that.formItem.introduction);
					formData.append("type", that.formItem.type);
					formData.append("title", that.formItem.title);
					if(that.url.indexOf('View') == -1 && that.url.indexOf('Edit') == -1){
						formData.append("flag", true);
					}else{
						formData.append("flag", that.editFlag);
						formData.append("id", that.getQueryString('id'));
					}
					var postUrl = SERVER_URL_HEAD_AJAX+ "/newsManage/saveData";
					$.ajax({
						type : "post",
						url : postUrl,
						dataType : "json",
						processData : false,
						contentType : false,
						data : formData,
						success : function(data) {
							ajaxStatus = true;
							if(data.status == 200){
								window.top.vm.$Message.success( page + '成功');
								window.location = SERVER_URL_HEAD_AJAX + "/newsManage/index";
							}
						}
					});
				}else{
						window.top.vm.$Message.error(page + '失败');
				}
			});
		},
		// 。上传图片到页面
		upPic : function(){
			$("#file0").click();
			this.picFileChange();
		},
		// 
		picFileChange : function(){
			var that = this;
			$('#file0').bind('change', function(){
				let fileType = $("#file0").val().split(".")[1].toLowerCase();
				if(fileType != 'png' && fileType != 'jpg' && fileType != 'jpeg' && fileType != 'gif'){
					window.top.vm.$Message.error('图片类型不符');
					$('#file0').val('');
					return;
				}
				$("#preview").attr("src",URL.createObjectURL($(this)[0].files[0]));
				$("#cancelUp").prop("disabled",false);
				// 。判断预览图片是否可见
				if($("#preview").is(":hidden")){
					$("#preview").show();
				}
				that.editFlag = true;
			});
			loadPicZoom()
		},
		// 。取消上傳圖片
		deletePic : function(){
			if($("#preview").is(":visible")){
				this.editFlag = true;
			}
			$('#file0').val('');
			$("#cancelUp").prop("disabled",true);
			$("#preview").hide();
		},
		getQueryString : function(name){
		     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		     var r = window.location.search.substr(1).match(reg);
		     if(r!=null)return  unescape(r[2]); return null;
		}
	},
	created : function() {
		this.picFileChange();
		if(this.url.indexOf('Edit') != -1){
			this.editInit();
		}
		
	}
});

function loadPicZoom(){
	//图片放大
    $("#outerdiv").hide();
    
    $("img").mouseover(function(){
        $(this).css("cursor","pointer");
    });

    $("img").click(function(){
        var _this = $(this);//将当前的pimg元素作为_this传入函数
        imgShow("#outerdiv", "#bigimg", _this);
    });
   
}
function imgShow(outerdiv, bigimg, _this) {
	$('#app').css('filter','blur(1px)');
    var src = _this.attr("src");//获取当前点击的pimg元素中的src属性
    $("#outerdiv").attr("display", "block");
    $("#bigimg").attr("src", src);//设置#bigimg元素的src属性
    $("#outerdiv").fadeIn("fast");

    $("#outerdiv").click(function () {//再次点击淡出消失弹出层
        $(this).fadeOut("fast");
        $('#app').css('filter','blur(0px)');
    });
}