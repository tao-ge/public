/**
 * 设施状态查询js脚本
 */
$(function($){
    		
    		$("#btnSimpleQuery").on('click',simpleQuery);//注册简单查询事件    		
    		
    		$("#btnClear").on('click',clearMoreSearchForm);
    		
    		$("#lazy").on('click',fangda);
    		

    		//$("a[op='ctrl']").on("click",displayAlarmCtrl);
    		
    		//$("a[op='del']").on("click",deleteData);//加载删除事件
    		//$("#hrefAdd").on('click',loadEdit);//加载添加事件 
    		
    		//$("a[op='modify']").on("click",loadEdit);
    		
    		$('.tablelist tbody tr:odd').addClass('odd');
    		//$("#btnDevAlaCtrlSave").on('click',submitAlarmCtrl);
    		
    	})    	    	
  
    	
    	
    	//提交查询表单
    	function submitQuery()
    	{
			var imgTypesList= new Array();
			if ($('#imgTypes1').is(':checked')) {
				imgTypesList[0] = $('#imgTypes1').val();
			}
			
			if ($('#imgTypes2').is(':checked')) {
				imgTypesList[1]= $('#imgTypes2').val();
			}
			
			if ($('#imgTypes3').is(':checked')) {
				imgTypesList[2]= $('#imgTypes3').val();
			}
			
//			if(imgTypes.length>0)
//				imgTypes = imgTypes.substring(0,imgTypes.length-1);
			
			$("#imgTypesList").val(imgTypesList);		
			
    		var devId = $("#devId").val();
    		
	
    		$("#moreSearchForm")[0].action=path+'/facilityImgList.htm?devId='+devId;    	
    		$("#moreSearchForm")[0].submit();
    	}
    	
    	//简单查询提交
    	function simpleQuery()
    	{
    		//$("#btnClear").click();
//    		clearMoreSearchForm();
//    		$("#devName").val($("#devNameSearch").val());
//    		$("#devCode").val($("#devCodeSearch").val());
//    		$("#startTime").val($("#startTimeSearch").val());
//    		$("#endTime").val($("#endTimeSearch").val());
    	
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
    	//点击放大缩小图片
    	function fangda(){
    		var width = $(this).width();
		    if(width==150)
		    {
		        $(this).width(450);
		        $(this).height(450);
		    }
		    else
		    {
		        $(this).width(150);
		        $(this).height(150);
		    }
    	}
    	