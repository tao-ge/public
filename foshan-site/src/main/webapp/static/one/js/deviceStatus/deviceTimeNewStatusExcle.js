/**
 * 设施状态查询js脚本
 */
    	
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
