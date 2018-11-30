/**
 * 设施管理js脚本
 */
$(function($){
    		
    		$("#btnSimpleQuery").on('click',simpleQuery);//注册简单查询事件
    		$("#btnDisplayMoreQuery").on('click',disPlayMoreQuery);//注册显示高级查询事件
    		$("#btnMoreQuery").on('click',moreQuery);//注册高级查询事情
    		$("#btnClear").on('click',clearMoreSearchForm);
    		$("a[op='view']").on("click",openWinMap);
    		$('.tablelist tbody tr:odd').addClass('odd');
    		
    	})    	
    	
    	//显示高级查询窗口
    	function disPlayMoreQuery()
    	{
    		
    		layer.open(
					{
	    				type: 1,
	    				closeBtn: 2,
	    				title: ['高级查询', 'font-size:18px;'],
	    				area: ['780px', '450px'],
	    				shadeClose: true, //点击遮罩关闭
	    				content:$("#div_moreSearch"),
			 		}
			);
    	}
    	
    	//提交查询表单
    	function submitQuery()
    	{
    		var devId = $("#devId").val();
    		var devName = $("#devName").val();
    		$("#moreSearchForm")[0].action=path+'/facilityInspectList.htm?devId='+devId+'&devName='+devName;
    		
    		$("#moreSearchForm")[0].submit();
    	}
    	
    	//简单查询提交
    	function simpleQuery()
    	{
    		//$("#btnClear").click();
    		clearMoreSearchForm();
    	
    		
    		$("#devCode").val($("#devCodeSearch").val());
//    		$("#inspectStatus").val($("#inspectStatusSearch").val());
    		$("#startTime").val($("#startTimeSearch").val());
    		$("#endTime").val($("#endTimeSearch").val());
    		$("#pageNo").val('1');
    		
    		submitQuery();
    	}
    	
    	//提交高级查询窗口
    	function moreQuery()
    	{
    		$("#pageNo").val('1');
    		
    		submitQuery();
    	}
    	
    	//分页浏览查询
    	function pageQuery(){
    		
    		$("#pageNo").val($(this).attr("pages"));
    		
    		submitQuery();
		}
    	
    	//清空更多查询条件
    	function clearMoreSearchForm()
    	{
    		$(':input','#moreSearchForm')    		
    		.val('') 
    		.removeAttr('checked') 
    		.removeAttr('selected');
            //.not(':button, :submit, :reset, :hidden') 
    	}
    	
    	/*
    	 * 打开地图窗口 
    	 */
		function openWinMap(){
			var lng = $(this).attr("lng");
	    	var lat = $(this).attr("lat");
	    	var banjing = 500;
		    if(lng == ''&& lat==''){
			    alert("无位置信息！");
			    return;
		    }
			ymPrompt.win({message:path+'/jsp/one/facility/showMap.jsp?plng='+lng+'&plat='+lat+'&pbanjing='+banjing,
					width:580,height:580,title:'位置信息',handler:null,iframe:true,btn:[['确定','yes'],['关闭','cancel']]});
		}