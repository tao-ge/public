/**
 *远程开锁js
 */
$(function($) {
	//$("input[op='del']").on("click",SwitchData);//开锁事件
	
	//$("#btnSearch").on('click',submitQuery);//查询确认
});


//提交查询表单
function submitQuery()
{
	//$("#searchlistform")[0].submit();
}


//分页浏览查询
function pageQuery(){
	$("#pageNo").val($(this).attr("pages"));
	submitQuery();
}




//删除
function SwitchData()
{
	//function kaisuo(){
		   
		   //document.getElementById("kaisuo").style.background = "green";
	$("#kaisuo").val('开锁中...');
		   
	//}
	//setTimeout("kaisuo()",10000);
	
	var dt=$(this).attr("dt");
	var imei = $(this).attr("rid");
	//var url = "http://192.168.0.211:8080/device-site/unlockInstruct.htm?did="+imei;
	//询问框
	if(dt!='2'){
		alert("锁未激活，不能用平台开锁！");
	}else{
		//alert(1);
		layer.confirm('确认要开锁吗？', {
		  btn: ['确定','取消'] //按钮
		},
	    function(){
			
		    document.getElementById("kaisuo").setAttribute("disabled",true);
			
			$.ajax({
				   type: "POST",
				   url: path+"/unlockInstruct.htm?did="+imei,
				   success: function(r){
					   alert(r);
					   
					   
					   $(this).parents("tr").remove();	
					   window.location.reload();
					   
					   //console.log(r);
					   //layer.msg(eval(r).r_content, {icon: 1});
					   //submitQuery();
				   }
					
				}); 
			layer.closeAll('dialog');
		}, null);
	}
}


function onSearchMore(){
	$("#moreSearchForm")[0].submit();
}


