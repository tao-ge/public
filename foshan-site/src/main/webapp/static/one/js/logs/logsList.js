/**
 * 设施管理js脚本
 */
$(function($){
    		
    		//$("#btnSimpleQuery").on('click',simpleQuery);//注册简单查询事件
    		
    		$('.tablelist tbody tr:odd').addClass('odd');
    		
    	})    	
    
    	
    	//提交查询表单
    	function submitQuery()
    	{
    		
    		$("#moreSearchForm")[0].submit();
    	}
    	
    	//简单查询提交
    	function simpleQuery()
    	{
    		//$("#btnClear").click();
    		clearMoreSearchForm();
    		
    		$("#startTime").val($("#startTimeSearch").val());
    		$("#endTime").val($("#endTimeSearch").val());
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
    	
  