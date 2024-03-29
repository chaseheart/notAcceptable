//分页，页码导航,要求参数为一个对象
function createPageNav(opt) {
	opt = opt || {};
	var $container = opt.$container || null, // 必需，页码容器，请确保这个容器只用来存放页码导航
	pageCount = Number(opt.pageCount) || 0, // 必需，页码总数
	currentNum = Number(opt.currentNum) || 1, // 选填，当前页码
	maxCommonLen = Number(opt.maxCommonLen) || 1, // 选填，普通页码的最大个数
	dataCount = Number(opt.dataCount) || 0, // 选填，总条数
	pageSize = Number(opt.pageSize) || 0, // 每页条数
	pageName = opt.pageName || null,

	className = opt.className || "ant-pagination ant-table-pagination", // 选填，分页类型：pagination或pager等
	preText = opt.preText || "<", // 选填，上一页文字显示，适用于只有前后页按钮的情况
	nextText = opt.nextText || ">", // 选填，下一页文字，同上
	firstText = opt.firstText || "首页", lastText = opt.lastText || "末页",

	hasFirstBtn = opt.hasFirstBtn === false ? false : true, hasLastBtn = opt.hasLastBtn === false ? false
			: true, hasPreBtn = opt.hasPreBtn === false ? false : true, hasNextBtn = opt.hasNextBtn === false ? false
			: true, hasInput = opt.hasInput === false ? false : true, hasCommonPage = opt.hasCommonPage === false ? false
			: true, // 选填，是否存在普通页
	hasLineNum = opt.hasLineNum === true ? true : false, hasJump = opt.hasJump === false ? false
			: true,

	beforeFun = opt.beforeFun || null, // 选填，页码跳转前调用的函数，可通过返回false来阻止跳转，可接收目标页码参数
	afterFun = opt.afterFun || null, // 选填，页码跳转后调用的函数，可接收目标页码参数
	noPageFun = opt.noPageFun || null; // 选填，页码总数为0时调用的函数

	// 当前显示的最小页码，用于计算起始页码，直接容器,当前页，前，后，首，末，输入框
	var minNum = 1, changeLen, $parent, $currentPage, $preBtn, $nextBtn, $firstBtn, $lastBtn, $input;

	// 容器
	if (!$container || $container.length != 1) {
		console.log("分页容器不存在或不正确");
		return false;
	}
	// 总页数
	if (pageCount <= 0) {
		if (noPageFun)
			noPageFun();
		$container.html('');
		return false;
	}
	// 当前页
	if (currentNum < 1)
		currentNum = 1;
	else if (currentNum > pageCount)
		currentNum = pageCount;
	// 普通页码的最大个数，起始页算法限制，不能小于3
	if (maxCommonLen < 4)
		maxCommonLen = 4;
	// 跳转页响应长度，用于计算起始页码
	if (maxCommonLen >= 8)
		changeLen = 3;
	else if (maxCommonLen >= 5)
		changeLen = 2;
	else
		changeLen = 1;

	$container.hide();
	_initPageNav();
	$container.show();

	function _initPageNav() {
		var initStr = [];
		initStr.push('<nav><ul class="' + className
				+ '" onselectstart="return false">');

		// if(hasFirstBtn)initStr.push('<li class="first-page
		// ant-pagination-item page-item" value="1"><span>'+ firstText
		// +'</span></li>');
		if (hasPreBtn)
			initStr
					.push('<li class="ant-pagination-prev pre-page ant-pagination-item page-item"  value="'
							+ (currentNum - 1)
							+ '"><span>'
							+ preText
							+ '</span></li>');
		if (hasNextBtn)
			initStr
					.push('<li class="ant-pagination-next next-page ant-pagination-item page-item" value="'
							+ (currentNum + 1)
							+ '"><span>'
							+ nextText
							+ '</span></li>');
		// if(hasLastBtn) initStr.push('<li class="last-page ant-pagination-item
		// page-item" value="' + pageCount + '"><span>'+ lastText
		// +'</span></li>');

		if (hasInput)
			if (hasLineNum) {
				// initStr.push('<div class="input-page-div">共<span>'
				// + pageCount
				// + '</span>页，共<span>'+ dataCount +'</span>条记录 ');
				// initStr.push('<div class="input-page-div"><select
				// id="lineNum" class="ant-input"><option
				// value="5">5条/页</option>'
				// + '<option value="10">10条/页</option>'
				// + '<option value="15">15条/页</option></select>&nbsp;');

				initStr
						.push('<div class="dropdown-box input-page-div"><button class="select-btn ant-input" '
								+ 'data-toggle="dropdown"><span class="select-value">10条/页</span><span class="right"><i class="fa fa-angle-down"></i></span></button>'
								+ ' <div class="dropdown-menu"> <a class="dropdown-item" href="#">5条/页</a><a class="dropdown-item" '
								+ 'href="#">10条/页</a> <a class="dropdown-item" href="#">15条/页</a></div></div>');
			} else {
				initStr.push('<div class="input-page-div noLine">共<span>'
						+ pageCount + '</span>页，共<span>' + dataCount
						+ '</span>条记录 ');
			}
		initStr.push('</div>');

		if (hasJump)
			// initStr.push('<div class="input-page-div">转到 <input
			// class="ant-input" type="text" maxlength="6" value="' + currentNum
			// + '" /> 页<button type="button" class="ant-btn
			// ant-btn-primary">确定</button></div>');
			initStr
					.push('&nbsp;<div class="input-page-div skip">转到 <input class="ant-input" type="text" maxlength="6" value="'
							+ currentNum + '" /> 页</div>');
		initStr.push('</ul></nav>');

		$container.html(initStr.join(""));

		$("#lineNum").val(pageSize);

		// $('#lineNum').change(function(){
		// if(pageName == "AT000011"){
		// reSearch(1,$('#lineNum').val(),1);
		// }else{
		// research(1,$('#lineNum').val());
		// }
		// $("#lineNumRecord").val($('#lineNum').val());
		// })

		$('#pagination .dropdown-item').bind("click", function() {
			let pageValue = $(this).html().split("条")[0];
			research(1, pageValue);
			$("#pagination .select-value").html($(this).html());
		});

		// 初始化变量
		$parent = $container.children().children();
		if (hasFirstBtn)
			$firstBtn = $parent.children("li.first-page");
		if (hasPreBtn)
			$preBtn = $parent.children("li.pre-page");
		if (hasNextBtn)
			$nextBtn = $parent.children("li.next-page");
		if (hasLastBtn)
			$lastBtn = $parent.children("li.last-page");
		if (hasInput) {
			$input = $parent.find("div.skip>input");
			// $parent.find("div.input-page-div>button").click(function(){
			// _gotoPage($input.val());
			// });
			// 页码失去焦点时跳转
			$input.blur(function() {
				let inputValue = $input.val();
				// 判断是否是数字
				if (!isNaN(inputValue)) {
					// 页数小于等于0
					if (inputValue <= 0) {
						_gotoPage(1);
						$input.val(1);
					} else if (inputValue > pageCount) {
						_gotoPage(inputValue);
						$input.val(pageCount);
					} else {
						_gotoPage(inputValue);
					}

					// 非数字场合下设为1
				} else {
					_gotoPage(1);
					$input.val(1);
				}
			});
		}
		// 初始化功能按钮
		_buttonToggle(currentNum);
		// 生成普通页码
		if (hasCommonPage) {
			_createCommonPage(_computeStartNum(currentNum), currentNum);
		}
		// 绑定点击事件
		$parent.on("click", "li", function() {
			var $this = $(this);
			if ($this.is("li") && $this.attr("value")) {
				if (!$this.hasClass("disabled")
						&& !$this.hasClass("ant-pagination-item-active")) {
					_gotoPage($this.attr("value"));
				}
			}
		});
	}
	// 跳转到页码
	function _gotoPage(targetNum) {
		targetNum = _formatNum(targetNum);
		if (targetNum == 0 || targetNum == currentNum)
			return false;
		// 跳转前回调函数
		if (beforeFun && beforeFun(targetNum) === false)
			return false;
		// 修改值
		currentNum = targetNum;
		if (hasInput)
			$input.val(targetNum);
		if (hasPreBtn)
			$preBtn.attr("value", targetNum - 1);
		if (hasNextBtn)
			$nextBtn.attr("value", targetNum + 1);
		// 修改功能按钮的状态
		_buttonToggle(targetNum);
		// 计算起始页码
		if (hasCommonPage) {
			var starNum = _computeStartNum(targetNum);
			if (starNum == minNum) {// 要显示的页码是相同的
				$currentPage.removeClass("ant-pagination-item-active");
				$currentPage = $parent.children("li.commonPage").eq(
						targetNum - minNum).addClass(
						"ant-pagination-item-active");
			} else {// 需要刷新页码
				_createCommonPage(starNum, targetNum);
			}
		}
		// 跳转后回调函数
		if (afterFun)
			afterFun(targetNum);
	}
	// 整理目标页码的值
	function _formatNum(num) {
		num = Number(num);
		if (isNaN(num))
			num = 0;
		else if (num <= 0)
			num = 1;
		else if (num > pageCount)
			num = pageCount;
		return num;
	}
	// 功能按钮的开启与关闭
	function _buttonToggle(current) {
		if (current == 1) {
			if (hasFirstBtn)
				$firstBtn.addClass("disabled");
			if (hasPreBtn)
				$preBtn.addClass("disabled");
		} else {
			if (hasFirstBtn)
				$firstBtn.removeClass("disabled");
			if (hasPreBtn)
				$preBtn.removeClass("disabled");
		}

		if (current == pageCount) {
			if (hasNextBtn)
				$nextBtn.addClass("disabled");
			if (hasLastBtn)
				$lastBtn.addClass("disabled");
		} else {
			if (hasNextBtn)
				$nextBtn.removeClass("disabled");
			if (hasLastBtn)
				$lastBtn.removeClass("disabled");
		}
	}
	// 计算当前显示的起始页码
	function _computeStartNum(targetNum) {
		var startNum;
		if (pageCount <= maxCommonLen)
			startNum = 1;
		else {
			if ((targetNum - minNum) >= (maxCommonLen - changeLen)) {// 跳转到靠后的页码
				startNum = targetNum - changeLen;
				if ((startNum + maxCommonLen - 1) > pageCount)
					startNum = pageCount - (maxCommonLen - 1);// 边界修正
			} else if ((targetNum - minNum) <= (changeLen - 1)) {// 跳转到靠前的页码
				startNum = targetNum - (maxCommonLen - changeLen - 1);
				if (startNum <= 0)
					startNum = 1;// 边界修正
			} else {// 不用改变页码
				startNum = minNum;
			}
		}
		return startNum;
	}
	// 生成普通页码
	function _createCommonPage(startNum, activeNum) {
		var initStr = [];
		for (var i = 1, pageNum = startNum; i <= pageCount && i <= maxCommonLen; i++, pageNum++) {
			initStr
					.push('<li class="commonPage ant-pagination-item ant-pagination-item-"'
							+ i
							+ ' value="'
							+ pageNum
							+ '"><a href="javascript:">'
							+ pageNum
							+ '</a></li>');
		}
		$parent.hide();
		$parent.children("li.commonPage").remove();
		if (hasPreBtn)
			$preBtn.after(initStr.join(""));
		else if (hasFirstBtn)
			$firstBtn.after(initStr.join(""));
		else
			$parent.prepend(initStr.join(""));
		minNum = startNum;
		$currentPage = $parent.children("li.commonPage").eq(
				activeNum - startNum).addClass("ant-pagination-item-active");
		$parent.show();
	}
}