// 日期选择初始化
$(function($) {
	// to translate the daterange picker, please copy the
	// "examples/daterange-fr.js" contents here before initialization
	var drops = "down";
	if ($("#accountExpiryDate").hasClass("dateUp")) {
		drops = "up";
	}
	$('#accountExpiryDate').daterangepicker({
		'applyClass' : 'btn-data-apply',
		'cancelClass' : 'btn-data-cancel',
		'drops' : drops,
		locale : {
			format : 'YYYY/MM/DD',
			daysOfWeek : [ "日", "一", "二", "三", "四", "五", "六" ],
			monthNames : [ "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月" ],
			applyLabel : '确定',
			cancelLabel : '取消',
		}
	}).prev().on(ace.click_event, function() {
		$(this).next().focus();
	});

	// 自定义时间限定
	// $("#definition").click(function() {
	// $('#definitionTimeDate').click();
	// });
	$('#definitionTimeDate').daterangepicker({
		'timePicker' : true,
		'timePicker24Hour' : true,
		'timePickerSeconds' : true,
		'applyClass' : 'btn-data-apply',
		'cancelClass' : 'btn-data-cancel',
		'drops' : drops,
		locale : {
			format : 'YYYY/MM/DD HH:mm:ss',
			daysOfWeek : [ "日", "一", "二", "三", "四", "五", "六" ],
			monthNames : [ "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月" ],
			applyLabel : '确定',
			cancelLabel : '取消',
		}
	}).prev().on(ace.click_event, function() {
		$(this).next().focus();
	});

	$('#effectiveTime').datetimepicker({
		format : 'YYYY/MM/DD HH:mm:ss',// use this option to display
		icons : {
			time : 'fa fa-clock-o',
			date : 'fa fa-calendar',
			up : 'fa fa-chevron-up',
			down : 'fa fa-chevron-down',
			previous : 'fa fa-chevron-left',
			next : 'fa fa-chevron-right',
			today : 'fa fa-arrows ',
			clear : 'fa fa-trash',
			close : 'fa fa-times'
		}
	}).next().on(ace.click_event, function() {
		$(this).prev().focus();
	});

	$('#ymdTime').datetimepicker({
		format : 'YYYY/MM/DD',// use this option to display
		icons : {
			time : 'fa fa-clock-o',
			date : 'fa fa-calendar',
			up : 'fa fa-chevron-up',
			down : 'fa fa-chevron-down',
			previous : 'fa fa-chevron-left',
			next : 'fa fa-chevron-right',
			today : 'fa fa-arrows ',
			clear : 'fa fa-trash',
			close : 'fa fa-times'
		}
	}).next().on(ace.click_event, function() {
		$(this).prev().focus();
	});
});

// 日期选择
function setTime(start, end) {
	$(function($) {
		var drops = "down";
		if ($("#accountExpiryDate").hasClass("dateUp")) {
			drops = "up";
		}
		$('#accountExpiryDate').daterangepicker({
			'startDate' : start,
			'endDate' : end,
			'drops' : drops,
			'applyClass' : 'btn-data-apply',
			'cancelClass' : 'btn-cancel',
			locale : {
				format : 'YYYY/MM/DD',
				daysOfWeek : [ "日", "一", "二", "三", "四", "五", "六" ],
				monthNames : [ "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月" ],
				applyLabel : '确定',
				cancelLabel : '取消',
			}
		}).prev().on(ace.click_event, function() {
			$(this).next().focus();
		});
	});
}