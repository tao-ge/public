/**
 * 设施管理js脚本
 */
$(function($){
    		
    		$("#btnSimpleQuery").on('click',simpleQuery);//注册简单查询事件
    		$('.tablelist tbody tr:odd').addClass('odd');
    		$("a[op='adevInfoEdit']").on("click",devInfoEdit);
    	})    	
    
    	function devInfoEdit() {
			var url=path+"/sectRouteInfo.htm?sectId="+$(this).attr("did")+"&routeType="+$(this).attr("dids");
				
			var index = layer.open({
  			  type: 2,
  			  content: url,
  			  area: ['1100px', '520px'],
  			  maxmin: true
  			});
		}
    	
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
    		
//    		alert(1);
    		alert($("#sec_name1").val());
//    		$("#adevName").val($("#adevName_Search").val());
//    		$("#devName").val($("#devName_Search").val());
//    		$("#secName").val($("#sec_name").val());
    		$("#sectStateall").val($("#sectState_all").val());
    		
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
    	
  