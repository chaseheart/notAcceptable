$(function() {
	$(".table").on("click","input[type='checkbox']", function() {
		if (!$(this).parent().parent().hasClass("ant-checkbox-wrapper-checked")) {
			$(this).parent().parent().addClass("ant-checkbox-wrapper-checked");
			$(this).parent().addClass("ant-checkbox-checked");
		}else{
			$(this).parent().parent().removeClass("ant-checkbox-wrapper-checked");
			$(this).parent().removeClass("ant-checkbox-checked");
		}
	})

	$(".dropdown-menu").on("click",".dropdown-item",function() {
		var val = $(this).text();
		var value = $(this).attr('value');
		$(this).parent().prev().children(".select-value").text(val);
		$(this).parent().prev().children(".select-value").attr('value',value);
		$(this).parent().prev().removeClass("place-holder");
		$(this).parent().prev().children(".right").children("i").removeClass("fa-rotate-180");
	});
	$(".select-btn").click(function() {
		if ($(this).children(".right").children("i").hasClass("fa-rotate-180")) {
			$(this).children(".right").children("i").removeClass("fa-rotate-180");
		} else {
			$(this).children(".right").children("i").addClass("fa-rotate-180");
		}
	});

	// 按鈕排序
	$(".sort-btn").click(function() {
		var lastSort = $(this).find(".sort.active");
		$(this).children(".sort").addClass("active");
		lastSort.removeClass("active");
	});

	// 表格头排序
	$(".table th").click(
			function() {
				var lastSort = $(this).children(".table-sort").find(
						".sort.active");
				if ($(this).children(".table-sort").css("display") == "none"
						&& lastSort.hasClass("fa-sort-asc")) {
					lastSort = $(this).children(".table-sort").find(
							".fa-sort-desc");
				}
				$(this).children(".table-sort").children(".sort").addClass(
						"active");
				lastSort.removeClass("active");
				$(".table thead tr th").find(".table-sort").hide();
				$(this).children(".table-sort").show();
			});

	// 关闭模态框
	$(".modal-box .close").click(function() {
		$(this).parent().parent().hide();
		$(".bg").hide();
	});
});

function tableDoSort() {

}