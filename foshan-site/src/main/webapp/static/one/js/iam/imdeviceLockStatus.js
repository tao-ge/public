/**
 */
$(function($){
    		$("#btnSimpleQuery").on('click',submitQuery);//注册简单查询事件
    	})    	
    	
    	//提交查询表单
    	function submitQuery()
    	{
    		
    		$("#moreSearchForm")[0].submit();
    	}
    	
    	//分页浏览查询
    	function pageQuery(){
    		
    		$("#pageNo").val($(this).attr("pages"));
    		
    		submitQuery();
		}
    	
    	
    	
    	
    	
