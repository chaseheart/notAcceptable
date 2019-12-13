var vm = new Vue({
	el : '#app',
	data : {
		formItem:{
			content:'',
			type:''
		},
		resultTable : [ {
			title : 'ID',
			key : 'id',
			className : 'table-col-5'
		}, {
			title : '通知标题',
			key : 'title',
			className : 'table-col-5'
		}, {
			title : '通知类型', //1:新闻 2:通知 3:贴士
			key : 'type',
			className : 'table-col-5'
		}, {
			title : '操作',
			key : 'action',
			align:'center',
			className : 'table-col-5',
			render: (h, params) => {
                return h('div', [
                	h('Button', {
                        style: {
                            marginRight: '5px'
                        },
                        on: {
                            click: () => {
                            	window.location.href = "/newsManage/detailView?id=" + params.row.id;
                            }
                        }
                    }, '详情'),
                    h('Button', {
                        props: {
                            type: 'primary',
                        },
                        style: {
                            marginRight: '5px'
                        },
                        on: {
                            click: () => {
                            	window.location.href = "/newsManage/detailEdit?id=" + params.row.id;
                            }
                        }
                    }, '修改'),
                    h('Button', {
                        props: {
                            type: 'error',
                        },
                        on: {
                            click: () => {
                            	this.currentId = params.row.id;
                            	eval(window.top.vm.showModal)('vm.deleteNews');
                            }
                        }
                    }, '删除')
                ]);
            }
		}  ],
		resultData : [],
		orgList : [],
		departList : [],
		currentId:''
	},
	methods : {
		dealData : function(data){
			var resultData = [];
			var resultItem = {};
			if (data.length != 0) {
				for(let i in data){
					resultItem = {
							id : data[i].id,
							title : data[i].title,
							type : data[i].type
					};
					resultData.push(resultItem);
				}
			}
			return resultData;
		},
		// 。初期化
		getNotice : function(){
			let data = $("#data").val() != '' ? JSON.parse($("#data").val()) : '';
			// 。处理初期化数据
			this.resultData = this.dealData(data);
		},
		
		// 。检索 
		searchData : function(){
			var that = this;
			var data = {
				'content' : that.formItem.content,
				'type' : that.formItem.type
			};
			var url = "/newsManage/searchData";
			var success = function(data) {
				ajaxStatus = true;
				if(data.status == 200){
					that.resultData = that.dealData(data.data);
				}
			};
			var cache = false;
			var alone = true;
			post(url, data, success, cache, alone);
		},
		// 删除新闻
		deleteNews:function(){
			var that = this;
			var data = {
				'newsId' : currentId
			};
			var url = "/newsManage/deleteNews";
			var success = function(data) {
				ajaxStatus = true;
				if (data.status == 200) {
					window.top.vm.$Message.success('删除成功');
					window.location.reload();
				}
			};
			var cache = false;
			var alone = true;
			post(url, data, success, cache, alone);
		},
		addNotice : function(){
			window.location.href = "/newsManage/detail";
		}
	},
	created : function() {
		this.getNotice();
	}
});