//var weekDay = [ "日", "一", "二", "三", "四", "五", "六" ];
var vm = new Vue({
	el : '#app',
	data : {
		openDrawer : false,
		formItem : {
			date : new Date()
		},
		approvalTable : [ {
			title : '申请号',
			key : 'id',
			className : 'table-col-5',
			render : function(h, params) {
				var functionList = [];
				var show = h('a', {
					attrs : {
						href : "/" + params.row.appType + "/mine?ruFlowId=" + params.row.id + "&type=" + params.row.model,
					},
				}, params.row.id);
				functionList.push(show);
				return h('span', {

				}, functionList);
			}
		}, {
			title : 'appType',
			key : 'appType',
			showPrise : false,
			showRentPrise : false,
			className : 'table-col-6'
		}, {
			title : 'model',
			key : 'model',
			showPrise : false,
			showRentPrise : false,
			className : 'table-col-6'
		}, {
			title : '类型',
			key : 'type',
			className : 'table-col-6'
		}, {
			title : '申请开始时间',
			key : 'startTime',
			className : 'table-col-6'
		}, {
			title : '申请中止时间',
			key : 'endTime',
			className : 'table-col-6'
		}, {
			title : '最终审批者',
			key : 'lastApproval',
			className : 'table-col-6'
		}, {
			title : '审批状态',
			key : 'approvalStatus',
			className : 'table-col-6'
		}],
		approvalData : [],
		resultTable : [ {
			title : 'id',
			key : 'id',
			className : 'table-col-1',
			render : function(h, params) {
				var functionList = [];
				var show = h('span', {
					props : {
						type : 'primary',
					},
					on : {
						click : function() {
						}
					}
				}, params.row.id);
				functionList.push(show);
				return h('span', {

				}, functionList);
			}
		}, {
			title : '考勤日期',
			key : 'date',
			render : function(h, params) {
				var functionList = [];
				var show = h('a', {
					props : {
						type : 'primary',
					},
					attrs : {
						href : '####',
					},
					on : {
						click : function() {
						}
					}
				}, params.row.date);
				var content = h("div", {
					slot : "content"
				}, [ h('a', {
					props : {
						type : 'primary',
					},
					attrs : {
						href : '/holiday/index?id=' + params.row.id,
					}
				}, '休假申请'), h('a', {
					props : {
						type : 'primary',
					},
					attrs : {
						href : '/workOvertime/index?id=' + params.row.id,
					}
				}, '加班申请'), h('a', {
					props : {
						type : 'primary',
					},
					attrs : {
						href : '/unusualAttendance/index?id=' + params.row.id,
					}
				}, '非正常考勤申请'), h('a', {
					props : {
						type : 'primary',
					},
					attrs : {
						href : '/outBusiness/index?id=' + params.row.id,
					}
				}, '外出公务申请') ])
				functionList.push(show);
				functionList.push(content);
				return h('Poptip', {
					props : {
						transfer : true,
						title : '考勤',
						size : 'small',
					},
				}, functionList);
			},
			className : 'table-col-3'
		}, {
			title : '星期',
			key : 'weeks',
			className : 'table-col-1'
		}, {
			title : '勤务开始时间',
			key : 'startTime',
			className : 'table-col-5'
		}, {
			title : '勤务结束时间',
			key : 'endTime',
			className : 'table-col-5'
		}, {
			title : '加班',
			key : 'overtime',
			className : 'table-col-1'
		}, {
			title : '非正常考勤情况',
			key : 'status',
			className : 'table-col-5'
		}, {
			title : '旷工',
			key : 'absenteeism',
			className : 'table-col-1'
		}, {
			title : '缺勤',
			key : 'absenceFromDuty',
			className : 'table-col-1'
		}, {
			title : '事假',
			key : 'absence',
			className : 'table-col-1'
		}, {
			title : '病假',
			key : 'sick',
			className : 'table-col-1'
		}, {
			title : '带薪假',
			key : 'paid',
			className : 'table-col-3'
		}, {
			title : '外出公务',
			key : 'out',
			className : 'table-col-3'
		}, {
			title : '打卡时间',
			key : 'clockIn',
			className : 'table-col-5'
		}, {
			title : '照合结果',
			key : 'result',
			className : 'table-col-3',
			render : function(h, params) {
				var functionList = [];
				if (params.row.result == "非") {
					var show = h('Tag', {
						props : {
							type : 'error',
						},
						on : {
							click : function() {
							}
						}
					}, "X");
					functionList.push(show);
					return h('span', {

					}, functionList);
				} else if (params.row.result == "加") {
					var show = h('Tag', {
						props : {
							type : 'primary',
						},
						on : {
							click : function() {
							}
						}
					}, params.row.result);
					functionList.push(show);
					return h('span', {

					}, functionList);
				} else if (params.row.result == "非加") {
					var t1 = params.row.result.split('加')[0];
					var t2 = params.row.result.split('非')[1];

					var show1 = h('Tag', {
						props : {
							type : 'error',
						},
						on : {
							click : function() {
							}
						}
					}, "X");
					var show2 = h('Tag', {
						props : {
							type : 'primary',
						},
						on : {
							click : function() {
							}
						}
					}, t2);
					functionList.push(show1);
					functionList.push(show2);
					return h('span', {

					}, functionList);
				}
			}
		} ],
		resultData : []
	},
	methods : {
		getFormItem : function() {
			let userData = JSON.parse($("#username").val())[0];
			let date = new Date();
			let formItem = {
				workId : userData.workId,
				user : userData.username,
				org : userData.org,
				depart : userData.depart,
				date : date.getFullYear().toString() + '-' + date.getMonth().toString(),
			}
			this.formItem = formItem;
			this.getApprovalData(JSON.parse($("#map").val()));
		},
		getApprovalData : function(appData) {
			// 。申请信息
			let unusualAttendance = appData['unusualAttendance'];
			let vacation = appData['vacation'];
			let workOvertime = appData['workOvertime'];
			let outBusiness = appData['outBusiness'];
			let claimingExpenses = appData['claimingExpenses'];
			// 申请数据
			let approvalData = [];
			if (unusualAttendance.length != 0 || workOvertime.length != 0 || vacation.length != 0 || outBusiness != 0 || claimingExpenses != 0) {
				// 非正常考勤数据
				for ( let i in unusualAttendance) {
					let data = {
						id : unusualAttendance[i].appId,
						type : this.getApplicationType("unusualAttendance", unusualAttendance[i].typeDetail),
						startTime : unusualAttendance[i].appStart,
						endTime : unusualAttendance[i].appEnd,
						lastApproval : unusualAttendance[i].assigner,
						approvalStatus : this.getStatus(unusualAttendance[i].state),
						remarks : unusualAttendance[i].content,
						appType :"unusualAttendance",
						model : unusualAttendance[i].state == "1"?"writeAgain":"view"
					}
					approvalData.push(data);

				}
				// 休假考勤数据
				for ( let i in vacation) {
					let data = {
						id : vacation[i].appId,
						type : this.getApplicationType("vacation", vacation[i].typeDetail),
						startTime : vacation[i].appStart,
						endTime : vacation[i].appEnd,
						lastApproval : vacation[i].assigner,
						approvalStatus : this.getStatus(vacation[i].state),
						remarks : vacation[i].content,
						appType : "holiday",
						model : vacation[i].state == "1"?"writeAgain":"view"
					}
					approvalData.push(data);

				}
				for ( let i in workOvertime) {
					let data = {
						id : workOvertime[i].appId,
						type : this.getApplicationType("workOvertime", workOvertime[i].typeDetail),
						startTime : workOvertime[i].appStart,
						endTime : workOvertime[i].appEnd,
						lastApproval : workOvertime[i].assigner,
						approvalStatus : this.getStatus(workOvertime[i].state),
						remarks : workOvertime[i].content,
						appType : "workOvertime",
						model : workOvertime[i].state == "1"?"writeAgain":"view"
					}
					approvalData.push(data);

				}
				for ( let i in outBusiness) {
					let data = {
						id : outBusiness[i].appId,
						type : this.getApplicationType("outBusiness", outBusiness[i].typeDetail),
						startTime : outBusiness[i].appStart,
						endTime : outBusiness[i].appEnd,
						lastApproval : outBusiness[i].assigner,
						approvalStatus : this.getStatus(outBusiness[i].state),
						remarks : outBusiness[i].content,
						appType : "outBusiness",
						model : outBusiness[i].state == "1"?"writeAgain":"view"
					}
					approvalData.push(data);

				}
				for ( let i in claimingExpenses) {
					let data = {
						id : claimingExpenses[i].appId,
						type : this.getApplicationType("claimingExpenses", claimingExpenses[i].typeDetail),
						lastApproval : claimingExpenses[i].assigner,
						approvalStatus : '',
						remarks : claimingExpenses[i].content
					}
					approvalData.push(data);

				}
			}
			this.approvalData = approvalData;
		},
		getDateWeek : function(date) {
			var day = new Date(Date.parse(date));
			var today = new Array('日', '一', '二', '三', '四', '五', '六');
			var week = today[day.getDay()];
			return week;
		},
		createTable : function(servicePerformance, holiday, monthDay, month) { // 。生成表格
			let resultData = [];
			if (holiday != undefined) {
				let restDay = JSON.parse('[' + holiday.restDay + ']');

				for (var i = 0; i < monthDay; i++) {

					// 。循环日期
					var keyDay = holiday.year + '-' + this.prefixInteger(holiday.month,2) + '-' + this.prefixInteger((i + 1),2);
					// 。状态
					if (servicePerformance != undefined && servicePerformance[keyDay] != undefined) {
						var state = servicePerformance[keyDay].state;
					} else {
						var state = '';
					}

					// 。当月有考勤记录：true,当月无考勤记录：false
					if (!$.isEmptyObject(servicePerformance)) {
						// 。当天有考勤记录：true,当天无考勤记录：false
						if (servicePerformance[keyDay] != undefined) {

							let oaStart = servicePerformance[keyDay].oaStart;
							let oaEnd = servicePerformance[keyDay].oaEnd;
							let oaFinalStart = servicePerformance[keyDay].oaFinalStart;
							let oaFinalEnd = servicePerformance[keyDay].oaFinalEnd;
							let result = '';
							switch (servicePerformance[keyDay].state) {
							case "n":
								break;
							case "o":
								result = '加';
								break;
							case "uo":
								result = '非加';
								break;
							case "u":
								result = '非';
								break;
							default:
								break;
							}
							if (this.getDateWeek(servicePerformance[keyDay].day) == '六' || this.getDateWeek(servicePerformance[keyDay].day) == '日') {
								result = '加';
							}
							resultData[i] = {
								id : servicePerformance[keyDay].id,
								date : servicePerformance[keyDay].day,
								weeks : this.getDateWeek(servicePerformance[keyDay].day),
								startTime : oaFinalStart.substring(0, oaFinalStart.length - 3),
								endTime : oaFinalEnd.substring(0, oaFinalEnd.length - 3),
								overtime : '',
								status : '',
								absenteeism : servicePerformance[keyDay].absence,
								absenceFromDuty : '',
								absence : '',
								sick : '',
								paid : '',
								out : '',
								clockIn : oaStart.substring(0, oaStart.length - 3) + ' - ' + oaEnd.substring(0, oaEnd.length - 3),
								result : result
							}
						} else {
							resultData[i] = {
								date : keyDay,
								weeks : this.getDateWeek(keyDay),
								startTime : '',
								endTime : '',
								overtime : '',
								status : '',
								absenteeism : 8,
								absenceFromDuty : '',
								absence : '',
								sick : '',
								paid : '',
								out : '',
								clockIn : '',
								result : ''
							}
						}
					} else {
						resultData[i] = {
							date : keyDay,
							weeks : this.getDateWeek(keyDay),
							startTime : '',
							endTime : '',
							overtime : '',
							status : '',
							absenteeism : '',
							absenceFromDuty : '',
							absence : '',
							sick : '',
							paid : '',
							out : '',
							clockIn : '',
							result : ''
						}
					}
					// 。判断休假变红
					let date = i + 1;
					if (jQuery.inArray(date < 10 ? '0' + date : date + '', restDay) != -1) {
						// red
						resultData[i]["cellClassName"] = {
							weeks : 'text-error'
						}
						resultData[i]["absenteeism"] = '';
					}
				}
			}

			this.resultData = resultData;
		},
		searchPerformance : function() {
			var that = this;
			var datePicker = $("#datePicker").find("input").val();
			var data = {
				'oaYear' : datePicker.split("-")[0],
				'oaMonth' : datePicker.split("-")[1]
			};
			var url = "/servicePerformance/findAllServicePerformance";
			var success = function(data) {
				ajaxStatus = true;
				// 。校验通过，跳转到［首页］
				if (data.status == '200') {
					that.createTable(data.data.servicePerformance, data.data.holiday, data.data.month, datePicker.split("-")[1]);
					console.log(data);
					that.getApprovalData(data.data.app);
				}
			};

			var cache = false;
			var alone = true;
			post(url, data, success, cache, alone);
		},
		exportExcel : function() {
			var that = this;
			var datePicker = $("#datePicker").find("input").val();
			var data = {
				'oaYear' : datePicker.split("-")[0],
				'oaMonth' : datePicker.split("-")[1]
			};
			var url = "/servicePerformance/exportExcel";
			var success = function(data) {
				ajaxStatus = true;
				// 。校验通过，跳转到［首页］
			};

			var cache = false;
			var alone = true;
			get(url, data, success, cache, alone);
		},
		getApplicationType : function(type, typeDetail) {
			if (type == "unusualAttendance") {
				switch (typeDetail) {
				case "1":
					return "非正常:" + "迟到/早退"
					break;
				case "2":
					return "非正常:" + "忘打卡"
					break;
				case "3":
					return "非正常:" + "忘带卡"
					break;
				case "4":
					return "非正常:" + "地铁故障"
					break;
				}
			} else if (type == "vacation") {
				switch (typeDetail) {
				case "1":
					return "休假:" + "事假"
					break;
				case "2":
					return "休假:" + "病假"
					break;
				case "3":
					return "休假:" + "产假"
					break;
				case "4":
					return "休假:" + "产检假"
					break;
				case "5":
					return "休假:" + "陪产假"
					break;
				case "6":
					return "休假:" + "丧假"
					break;
				case "7":
					return "休假:" + "婚假"
					break;
				}
			} else if (type == "workOvertime") {
				return "加班"
			} else if (type == "outBusiness") {
				return "外出公务"
			}
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
		/**
		 * 自动补零
		 * num 原数
		 * m 目标位数
		 */
		prefixInteger: function (num, m) {
		     return (Array(m).join(0) + num).slice(-m);
		}
	},
	created : function() {
		this.getFormItem();
		let servicePerformance = JSON.parse($("#servicePerformance").val() == '' ? '[]' : $("#servicePerformance").val());
		let holiday = JSON.parse($("#holiday").val());
		let month = parseInt($("#month").val());
		this.createTable(servicePerformance[0], holiday, month);

	}
});
