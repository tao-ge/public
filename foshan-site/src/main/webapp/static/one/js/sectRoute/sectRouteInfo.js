
var checkboxid;
$(function($){
	
	$('.tablelist tbody tr:odd').addClass('odd');
		
$('input:checkbox').click(function() {
	var a1;
	var glalu=1;
	var value = $(this).attr('value');
	
	checkboxid=$(this);
	var orgid=$(this).attr('orgid');
	var areac=$(this).attr('areac');
	$("#glgl").val(glalu);
	 $("#rodeid").val(value);
	 $("#orgid1").val(orgid);
	 $("#areac1").val(areac);
	 var bj=$("#bj").val();		
		if(value!=''&&value!=bj){
		
	displayAlarmCtrl();
	    }
})
$("#glsh").click(function() {
	var glalu=2;
	var glscet=$("#glscet").val();
	$("#glgl").val(glalu);
	var value = $(this).attr('value');
	var glorglan=1;
	if(value!=''&&value!=glscet){
		displayAlarmCtrl();
	}
	
})

$("#btnDevAlaCtrlSave").on('click',submitAlarmCtrl);
$("a[op='alertRoute']").on("click",alertRoutePort);
})


function alertRoutePort(){
	//layer.alert(routeText,{icon:1})
	var url=path+"/sectRouteTextandPort.htm?port="+$(this).attr("port")+"&routeText="+$(this).attr("text");
	
	var index = layer.open({
		  type: 2,
		  title:"端子数据/文本路由",
		  content: url,
		  area: ['1000px', '400px'],
		  maxmin: true
		});
}

function displayAlarmCtrl()
{	
	
	a1=layer.open(
				{
    				type: 1,
    				closeBtn: 2,
    				title: ['', 'font-size:18px;'],
    				area: ['600px', '350px'],
    				shadeClose: true, //点击遮罩关闭
    				content:$("#div_deviceAlarmCtrl"),
    				cancel: function () {
    	                $("#glsh").prop("checked",false);
    	               
    	                if(checkboxid!=undefined){
    	                checkboxid.prop("checked",false);
    	                }
    	               
    	            }
		 		}
		);    				
	
}
function submitAlarmCtrl(){
	
	var value = $("#rodeid").val();
	var values=$("#glsh").val();
	
	var glorglan=$("#glgl").val();
	
	var logtype=$("#logtype").val();
	
	var bj=$("#bj").val();
	var glscet=$("#glscet").val();
	var rd=$("#rd").val();
	var areac1=$("#areac1").val();
	
	var orgid1=$("#orgid1").val();
	var logcontent=$("#logcontent").val();
	
	if(glorglan==1){
	if(value!=''&&value!=bj){
	var index = layer.load(1, {shade: false});

	$.ajax({
		  type: "post",
		  url: path+'/updateisentering.htm?routeId='+value+'&orgId='+orgid1+'&logcontent='+logcontent+'&areaCode1='+areac1,		  
		  success: function(r){
			  
			   $("#bj").val(r.id);
			   layer.msg(eval(r).r_content, {icon: 1});
			   layer.close(index);
			   layer.close(a1); 
			   
		   }
	
	});
	document.getElementById("logcontent").value = "";
	  }
	 
	
	    }
	if(glorglan==2){
		if(values!=''&&values!=glscet){
		var index = layer.load(1, {shade: false});
		
		$.ajax({
			  type: "post",
			  url: path+'/insertopticalcables.htm?sectId='+values+'&orgId='+orgid1+'&logType='+logtype+'&logcontent='+logcontent+'&areaCode1='+areac1,		  
			  success: function(r){
				 
				   $("#glscet").val(r.id);
				   layer.msg(eval(r).r_content, {icon: 1});
				   layer.close(index);
				   layer.close(a1);
			   }
		
		});
		document.getElementById("logcontent").value = "";
		}
		
	}
	$("#"+value).css({ color: "red" });
}
	
