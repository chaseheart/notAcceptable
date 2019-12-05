$(function() {
	$(".result-table table tbody").on('click','td',function() {
		if ($(this).hasClass("selected")) {
			$(this).removeClass("selected");
		} else if (!$(this).hasClass("last-month")) {
			$(this).addClass("selected");
		}
	})
})

var vm = new Vue({
	el : '#app',
	data : {
		formItem : {
			date : '',
			country : '1'
		},
		country : [ {
			value : '0',
			label : '中国'
		}, {
			value : '1',
			label : '日本'
		} ],
		formValidate : {
			date : [ {
				required : true,
				type : 'date',
				message : '请选择日期',
				trigger : 'change'
			} ] 
		},
		calendarHeader : [ {
			title : '周日',
			key : 'sun',
			align : 'center'
		}, {
			title : '周一',
			key : 'mon',
			align : 'center',
		}, {
			title : '周二',
			key : 'tue',
			align : 'center',
		}, {
			title : '周三',
			key : 'wed',
			align : 'center',
		}, {
			title : '周四',
			key : 'thu',
			align : 'center',
		}, {
			title : '周五',
			key : 'fri',
			align : 'center',
		}, {
			title : '周六',
			key : 'sat',
			align : 'center',
		} ],
		calendarData : []
	},
	methods : {
		getDateWeek : function(date) {
			var day = new Date(Date.parse(date));
			var today = new Array('7', '1', '2', '3', '4', '5', '6');
			var week = today[day.getDay()];
			return week;
		},
		createCalendar : function(year, month, monthDay, holiday) {
			var calendar = {
				sun : '',
				mon : '',
				tue : '',
				wed : '',
				thu : '',
				fri : '',
				sat : '',
				cellClassName : {
					sun : 'last-month',
					mon : 'last-month',
					tue : 'last-month',
					wed : 'last-month',
					thu : 'last-month',
					fri : 'last-month',
					sat : 'last-month'
				}
			}
			for (var i = 1; i <= monthDay; i++) {
				let weekday = this.getDateWeek(year + '/' + month + '/' + i);
				let isSat = false;
				if(holiday!=null){
					let restDay=JSON.parse('['+holiday.restDay+']');
					let flag=i<10?'0'+i:i+''
					switch (weekday) {
					case '7':
						calendar['sun'] = flag;
						if (restDay.includes(flag)) {
							calendar['cellClassName']['sun'] = "selected"
						} else {
							calendar['cellClassName']['sun'] = ""
						}
						break;
					case '1':
						calendar['mon'] = flag;
						if (restDay.includes(flag)) {
							calendar['cellClassName']['mon'] = "selected"
						} else {
							calendar['cellClassName']['mon'] = ""
						}
						break;
					case '2':
						calendar['tue'] = flag;
						if (restDay.includes(flag)) {
							calendar['cellClassName']['tue'] = "selected"
						} else {
							calendar['cellClassName']['tue'] = ""
						}
						break;
					case '3':
						calendar['wed'] = flag;
						if (restDay.includes(flag)) {
							calendar['cellClassName']['wed'] = "selected"
						} else {
							calendar['cellClassName']['wed'] = ""
						}
						break;
					case '4':
						calendar['thu'] = flag;
						if (restDay.includes(flag)) {
							calendar['cellClassName']['thu'] = "selected"
						} else {
							calendar['cellClassName']['thu'] = ""
						}
						break;
					case '5':
						calendar['fri'] = flag;
						if (restDay.includes(flag)) {
							calendar['cellClassName']['fri'] = "selected"
						} else {
							calendar['cellClassName']['fri'] = ""
						}
						break;
					case '6':
						calendar['sat'] = flag;
						if (restDay.includes(flag)) {
							calendar['cellClassName']['sat'] = "selected"
						} else {
							calendar['cellClassName']['sat'] = ""
						}
						isSat = true
						break;
					}
					if (isSat || i == monthDay) {
						isSat = false;
						this.calendarData.push(calendar);
						calendar = {
							sun : '',
							mon : '',
							tue : '',
							wed : '',
							thu : '',
							fri : '',
							sat : '',
							cellClassName : {
								sun : 'last-month',
								mon : 'last-month',
								tue : 'last-month',
								wed : 'last-month',
								thu : 'last-month',
								fri : 'last-month',
								sat : 'last-month'
							}
						}
					}
				}else{
					switch (weekday) {
					case '7':
						calendar['sun'] = i;
						calendar['cellClassName']['sun'] = "selected"
						break;
					case '1':
						calendar['mon'] = i;
						calendar['cellClassName']['mon'] = ""
						break;
					case '2':
						calendar['tue'] = i;
						calendar['cellClassName']['tue'] = ""
						break;
					case '3':
						calendar['wed'] = i;
						calendar['cellClassName']['wed'] = ""
						break;
					case '4':
						calendar['thu'] = i;
						calendar['cellClassName']['thu'] = ""
						break;
					case '5':
						calendar['fri'] = i;
						calendar['cellClassName']['fri'] = ""
						break;
					case '6':
						calendar['sat'] = i;
						calendar['cellClassName']['sat'] = "selected"
						isSat = true
						break;
					}
					if (isSat || i == monthDay) {
						isSat = false;
						this.calendarData.push(calendar);
						calendar = {
							sun : '',
							mon : '',
							tue : '',
							wed : '',
							thu : '',
							fri : '',
							sat : '',
							cellClassName : {
								sun : 'last-month',
								mon : 'last-month',
								tue : 'last-month',
								wed : 'last-month',
								thu : 'last-month',
								fri : 'last-month',
								sat : 'last-month'
							}
						}
					}
				}

			}
		},
		searchHoliday : function() {
			this.$refs['formItem'].validate((valid)=>{
				if (valid) {
					var that = this;
					var data =  {
							year : this.formItem.date.getFullYear(),
							month : this.formItem.date.getMonth(),
							country : this.formItem.country,
					};
					var url = "/holidayManage/searchHoliday";
					var success = function(data) { 
						ajaxStatus=true;   
						console.log(data);
						that.calendarData=[];
						that.createCalendar(that.formItem.date.getFullYear(),that.formItem.date.getMonth()+1,data.data.lastDay,data.data.holiday);
					};
					var cache = false;
					var alone = true;
					post(url, data, success, cache, alone);
				} else {
					that.$Message.error('检索失败');
				}
			})
		},
		saveHoliday : function() {
			var holiday="";
			$(".selected span").each(function(){
				if($(this).html().lenth==1){
					holiday+='"0'+$(this).html()+'",';
				}else{
					holiday+='"'+$(this).html()+'",';
					}
				
			})
			console.log(holiday);
			var that = this;
			var data = {
					year : this.formItem.date.getFullYear(),
					month : this.formItem.date.getMonth(),
					country : this.formItem.country,
					holiday : holiday
			};
			var url = "/holidayManage/saveHoliday";
			var success = function(data) { 
				ajaxStatus=true;   
				that.$Message.success('保存成功');
			};
			var cache = false;
			var alone = true;
			post(url, data, success, cache, alone);
		},
	},
	created : function() {
		var calenda = $("#holiday").val() != '' ? JSON.parse($("#holiday").val()) : '';
		this.formItem = {
			date : calenda.year + '-' + calenda.month,
			country : '0'
		};
		this.createCalendar(calenda.year, calenda.month, $("#month").val(), calenda);
	}
});