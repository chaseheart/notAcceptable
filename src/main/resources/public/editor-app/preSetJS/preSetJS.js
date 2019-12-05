$(document).ready(function(){
	getAllUser();
})

function getAllUser(){
	$.ajax({
		url:"/user/getUser",
		async:false,
		type:"GET",
		dataTYpe:"json",
		success:function(data){
//			console.log(data);
			for(var i=0;i<data.length;i++){
				$("#userModel").append("<option id='"+data[i].id+"'>"+data[i].name+"</option>");
			}
		}
	});
}

function getAllProcessByUserId(){
	console.log($("#userModel option:selected").attr("id"));
	var data = {"userId":$("#userModel option:selected").attr("id")};
	$.ajax({
		url:"/user/getAllProcessByUserId",
		async:false,
		type:"POST",
		dataType:"json",
		data:data,
		success:function(data){
			console.log(data);
			$("#processModel").html("");
			for(var i=0;i<data.length;i++){
				$("#processModel").append("<option id='"+data[i].processId+"'>"+data[i].processName+"</option>");
			}
		}
	});
}