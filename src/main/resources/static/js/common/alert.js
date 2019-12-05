$(function() {        
    $.alerts = {         
    	// 重写confirm
        confirm: function(message, callback) {  
        	// confirm显示内容
            $.alerts._show(message, null, 'confirm', function(result) {  
                if( callback ) callback(result);  
            });  
        },  
          
        _show: function(msg, value, type, callback) {  
            
                    var _html = "";  
                    _html += '<div id="mb_box"></div><div id="mb_con"><span id="mb_tit"></span>';  
                    _html += '<div id="mb_msg">' + msg + '</div><div id="mb_btnbox">';  
                      _html += '<input class="btn-alert-no" id="mb_btn_no" type="button" value="取消" />';  
                      _html += '<input class="btn-alert-ok" id="mb_btn_ok" type="button" value="确定" />';  
                    _html += '</div></div>';  
                   
                    // 必须先将_html添加到body，再设置Css样式
                    $("body").append(_html); GenerateCss();  
           
                    $("#mb_btn_ok").click( function() {  
                        $.alerts._hide();  
                        if( callback ) callback(true);  
                    });  
                    $("#mb_btn_no").click( function() {  
                        $.alerts._hide();  
                        if( callback ) callback(false);  
                    });  
                    $("#mb_btn_no").focus();  
                    $("#mb_btn_ok, #mb_btn_no").keypress( function(e) {  
                        if( e.keyCode == 13 ) $("#mb_btn_ok").trigger('click');  
                        if( e.keyCode == 27 ) $("#mb_btn_no").trigger('click');  
                    });  
                
        },  
        _hide: function() {  
             $("#mb_box,#mb_con").remove();  
        }  
    }  
       
    myConfirm = function(message, callback) {  
        $.alerts.confirm(message, callback);  
    };  
           
   
      
      // 生成Css
  var GenerateCss = function () {  
   
    $("#mb_box").css({ width: '100%', height: '100%', zIndex: '99999', position: 'fixed',  
      filter: 'Alpha(opacity=60)', backgroundColor: 'black', top: '0', left: '0', opacity: '0.6'  
    });  
   
    $("#mb_con").css({ zIndex: '999999', width: '350px',height:'200px', position: 'fixed',  
      backgroundColor: 'White',  
    });  
   
    $("#mb_tit").css({ display: 'block', fontSize: '14px', color: '#444', padding: '10px 15px',  
      backgroundColor: '#fff', borderRadius: '15px 15px 0 0',  
      fontWeight: 'bold'  
    });  
   
    $("#mb_msg").css({ padding: '20px', lineHeight: '40px', textAlign:'center', 
      fontSize: '18px' ,color:'#4c4c4c' 
    });  
   
    $("#mb_ico").css({ display: 'block', position: 'absolute', right: '10px', top: '9px',  
      border: '1px solid Gray', width: '18px', height: '18px', textAlign: 'center',  
      lineHeight: '16px', cursor: 'pointer', borderRadius: '12px', fontFamily: '微软雅黑'  
    });  
   
    $("#mb_btnbox").css({ margin: '15px 0px 10px 0', textAlign: 'center' });  
    $("#mb_btn_ok,#mb_btn_no").css({ width: '80px', height: '30px', color: 'white', border: 'none', borderRadius:'4px'});  
    $("#mb_btn_ok").css({ backgroundColor: 'rgba(138, 185, 215, 1)' });  
    $("#mb_btn_no").css({ backgroundColor: 'gray', marginRight: '40px' });  
   
   
    // 右上角关闭按钮hover样式
    $("#mb_ico").hover(function () {  
      $(this).css({ backgroundColor: 'Red', color: 'White' });  
    }, function () {  
      $(this).css({ backgroundColor: '#DDD', color: 'black' });  
    });  
   
    var _widht = document.documentElement.clientWidth; // 屏幕宽
    var _height = document.documentElement.clientHeight; // 屏幕高
   
    var boxWidth = $("#mb_con").width();  
    var boxHeight = $("#mb_con").height();  
   
    // 让提示框居中
    $("#mb_con").css({ top: (_height - boxHeight) / 2 + "px", left: (_widht - boxWidth) / 2 + "px" });  
  }  
   
});

/**
 * 信息弹出
 * 需要手动 $("#alert-box .alert").remove();
 * @param msg 信息内容
 * @param msgType 信息类型
 * @returns
 */
function alertMsg(msg, msgType) {
	$("#alert-box")
			.append(
					'<div style="" class=" alert alert-'+ msgType+ '">'
							+ '<a class="close" data-dismiss="alert">&times;</a>'+ '<strong >'+ msg +'</strong>' + '</div>')
	$(".alert").bind("click", function() {
		$(this).remove();
	});
	//定时关闭
	setTimeout(function(){
		$("#alert-box .alert").remove();
	},2000);
}

