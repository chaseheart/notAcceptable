var vm = new Vue({
			el : '#app',
			data : {
				formUser : {
					pwd:'',
					name:''
				},
				ruleValidate : {
					name : [ {
						required : true,
						message : '用户名不能为空',
						trigger : 'blur'
					} ],
					pwd : [ {
						required : true,
						message : '密码不能为空',
						trigger : 'blur'
					} ]
				}
			},
			methods : {
				init : function(){
					if (window.top.$("#main").attr('id') != undefined) {
						window.top.location.replace("login");
					}
					if($("#loginFlag").val() == "true"){
						this.$Message.error('密码错误');
					}
				},
				login : function(){
					$.ajax({
			            url: '/login',
			            type: 'POST',
			            data: {
			            	name:this.formUser.name,
			            	pwd:this.formUser.pwd
			            },
			            success: function (res, status) {
			                window.location.href='/main';
			            },
			            error: function (res, status) {
			            	vm.$Message.error("账号密码错误");
			            }
			        });

				}
				
			},
			created : function() {
				this.init();
			}
		});