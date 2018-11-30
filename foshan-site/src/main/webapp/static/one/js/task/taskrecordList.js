var areaCode1;
$(function($){
	var areadevCode1=document.getElementById("areadevCode1").value;
	 areaCode1=document.getElementById("areaCode2").value;
	 $("#btnDisplayMoreQuery").on('click',disPlayMoreQuery);
	$("#areadevCode").val(areadevCode1);
	$("#btnMoreQuery").on('click',moreQuery);
	$(".areadevCode").on("change",function(){
		changeArea($(this));
	})
	$(".areadevCode").change();
	selectlogtype();
	$("#btnSimpleQuery").on('click',function(){
		$("#pageNo").val("1");
		
		var areadevCode1=document.getElementById("areadevCode").value;
		$("#areadevCode1").val(areadevCode1);//存到隐藏域
		
		$("#searchform")[0].submit();
	});
	$("#logtype").val($("#logtype1").val());
})


function changeArea(districtObj){
    		var areaCode=areaCode1;
    		var  value = $(districtObj).val();
    		
    		var areas = $(districtObj).parents("div").find("select.hjq");
    		
    		areas.children("option").remove();
    		areas.append($("<option>").prop("value",'').append("==请选择=="));
    		$.ajax({
    			type:"POST",
    			url: path+"/ajax/getAreas.htm?areaRank=4&parentAreaCode="+value,
    			dataType:"json",
    			success:function(data)
    			{
    				$.each(data,function(index,value){
    					 if(areaCode==value.name)
    					{
    						 
    						 //回显
    						areas.append($("<option>").prop("value",data[index].name).prop("selected",true)
    						.append(data[index].value));
    						
        					areas.append($("<option>").prop("value",data[index].name).append(data[index].value));
        					//删除重复数据
        					var obj=document.getElementById("areaCode");
         					//index,要删除选项的序号，这里取当前选中选项的序号
         					var index=obj.selectedIndex;
         					obj.options.remove(index+1); 
    					}else{
    					 areas.append($("<option>").prop("value",data[index].name).append(data[index].value));
    					}
    				});
    			}
    		});
    		
    	}

function selectlogtype(){
	$.ajax({
		  type: "post",
		  url: path+"/selectlogtype.htm",		  
		  cache: false,
		  async: false,
		  dataType: "json", 
		  success: function(data){
		
			  for(var i=0;i<data.length;i++){
				  $("#logtype").append('<option value="'+data[i].valuename+'">'+data[i].valuename+'</option>'); 
			  }
		  }
	});	
	
}
function pageQuery(){
	
	$("#pageNo").val($(this).attr("pages"));
	
	$("#searchform")[0].submit();
}

//显示高级查询窗口
function disPlayMoreQuery()
{
	
	layer.open(
			{
				type: 1,
				closeBtn: 2,
				title: ['高级查询', 'font-size:18px;'],
				area: ['500px', '400px'],
				shadeClose: true, //点击遮罩关闭
				content:$("#div_moreSearch")
	 		}
	);
}
//提交高级查询窗口
function moreQuery()
{
	$("#logcontent").val($("#logcontents").val());
$("#username").val($("#usernames").val());
	$("#startTime").val($("#startTimes").val());
	$("#endTime").val($("#endTimes").val());
	submitQuery();
}
function submitQuery()
{
	//$("#moreSearchForm")[0].action=path+'/routeList.htm';
	$("#searchform")[0].submit();   		
}