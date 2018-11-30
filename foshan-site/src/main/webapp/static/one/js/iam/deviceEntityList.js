/**
 * 中控器列表查询js脚本
 */
$(function($){
    		
    		$("#btnSimpleQuery").on('click',simpleQuery);//注册简单查询事件    		
    		
//    		$("#btnClear").on('click',clearMoreSearchForm);
    		
    		$("a[op='deviceDisinfo']").on('click',deviceDisinfo);
    		$("a[op='deviceDisinfoisOccup']").on('click',deviceDisinfoisOccup);
    		//$("a[op='ctrl']").on("click",displayAlarmCtrl);
    		
    		//$("a[op='del']").on("click",deleteData);//加载删除事件
    		//$("#hrefAdd").on('click',loadEdit);//加载添加事件 
    		
    		//$("a[op='modify']").on("click",loadEdit);
    		
    		$('.tablelist tbody tr:odd').addClass('odd');
    		//$("#btnDevAlaCtrlSave").on('click',submitAlarmCtrl);
    		
    	})    	    	
  
    	//端子占用情况
    	function deviceDisinfoisOccup(){
			var codId=$(this).attr("did");
			var route = "/queryDeviceEntityList.htm";
			window.location.href= path +"/queryDeviceDisinfoOccup.htm?route="+route+"&codId="+codId;
		}
    	
    	//端子日志详情
    	function deviceDisinfo(){
			var codId=$(this).attr("did");
			var route = "/queryDeviceEntityList.htm";
			window.location.href= path +"/queryDeviceDisinfo.htm?route="+route+"&codId="+codId;
		}
    	
    	//提交查询表单
    	function submitQuery()
    	{
			$("#moreSearchForm")[0].submit();
    	}
    	
    	//简单查询提交
    	function simpleQuery()
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
    	
    	
    	