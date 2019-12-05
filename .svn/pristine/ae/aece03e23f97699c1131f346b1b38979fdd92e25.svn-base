$(function () {	
		var vm = new Vue({
			el: '#app',
			data:{
				user : {},
			},
			methods: {
				getUser: function () {
					$.ajax({
						type: "POST",
					    url: "getUser",
					    dataType: "json",
					    success: function(result){
					    	vm.user = result;
						}
					});
				},
				add:function(){
					dialogOpen({
						title: '流程图',
						url: "bpmn/show.shtml",
						//屏幕小 滚动条显示
						scroll : true,
						width: '80%',
						height: '80%',
						yes : function(iframeId) {
							
						},
					});  
		    	}
			},
			created: function(){
				this.getUser()
			}
		});						
	});