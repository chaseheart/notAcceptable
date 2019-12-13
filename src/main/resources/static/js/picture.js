$(function() {

	$("#newPic").click(function() {
		$(this).parent().find('.upload_input').eq(0).click();
	})
	$(".fileInput").change(function() {
		change(this);
	})
	$("#saveImg").click(function() {
		saveImg();
	})

	/**
	 * 点击放大图片
	 */
	loadPicZoom();

})

var deleteNum = 0;

/**
 * 选择图片
 */
var fileNo = 0
function change(file) {

	var ext = file.value.substring(file.value.lastIndexOf(".") + 1).toLowerCase();

	// gif在IE浏览器暂时无法显示
	if (ext != 'jpg' && ext != 'jpeg' && ext != 'png') {
		return;
	}
	var html = createPicInput(fileNo);

	$(file).parent().parent().before(html);
	$(file).before(createnewPicInput(fileNo + 1));
	fileNo++;
	// 预览
	var pic = $(file).parent().parent().prev().children().eq(0);
	html5Reader(file, pic);
	$(".deletePic").bind("click", function() {
		console.log($(this).children('div').html());
		$(this).parent().remove();
		if ($(this).prev()[0].src == "http:") {
			deleteNum++;
		}
		$("#file" + $(this).children('div').html()).remove();
	});
	loadPicZoom();
}
// H5渲染
function html5Reader(file, pic) {
	var file = file.files[0];
	var reader = new FileReader();
	reader.readAsDataURL(file);
	reader.onload = function(e) {
		pic.attr("src", this.result);
	}
}
/**
 * 保存图片
 */
function saveImg() {
	var type = "file"; // 后台接收时需要的参数名称，自定义即可
	var fileId = "file"; // 即input的id，用来寻找值
	var goodsId = "goodsId";// 商品ID
	var existedPiId = "picId";// 商品ID
	var existedPic = new Array();
	var formData = new FormData();
	// 读入新上传的图片
	for (var i = 0; i < $(".picGroup").length; i++) {
		if ($(".picGroup")[i].src.substring(0, 5) == "http:") {
			formData.append(existedPiId, $(".picGroup")[i].src);
		} else {
			// formData.append(type, $(".picGroup")[i].src);
			formData.append(existedPiId, '');
		}
	}
	if ($(".picGroup").length == 0) {
		formData.append(existedPiId, "")
	}
	// 筛选已存在的图片
	for (var i = 0; i < fileNo; i++) {
		formData.append(type, $("#" + fileId + i)[0].files[0]);
	}
	var filedata;
	$.ajax({
		type : "POST", // 因为是传输文件，所以必须是post
		url : SERVER_URL_HEAD_AJAX +'/claimingExpenses/saveFiles', // 对应的后台处理类的地址
		data : formData,
		processData : false,
		contentType : false,
		async : false,
		success : function(data) {
			deleteNum = 0;
			// 修改成功
			filedata = data;
		}
	});
	return filedata;

}
/**
 * 创建隐藏文件上传
 */
function createPicInput(fileNo) {
	var html = "<li style='display: inline-block;margin-left: 10px;position: relative;'><img class='picGroup' width='150' height='150' src='' />"
			+ "  <a href='#' style=' top: 0; position: absolute;left: 130px;' class='deletePic'>" + "<div style='display:none;'>" + fileNo + "</div>" + " <i class='red'>X</i>" + "</a>" + " </li>";
	return html;
}
/**
 * 创建图片按钮
 */
function createnewPicInput(fileNo) {
	var html = '<input name="url" id="file' + fileNo + '" type="file" class="upload_input" onchange="change(this)"  style="display: none;" />';
	return html;
}


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