/**
 * 设施状态查询js脚本
 */
$(function($){
    		
    		$("#btnSimpleQuery").on('click',simpleQuery);//注册简单查询事件
    		$("#btnDisplayMoreQuery").on('click',disPlayMoreQuery);//注册显示高级查询事件
    		$("#btnMoreQuery").on('click',moreQuery);//注册高级查询事情
    		$("#btnClear").on('click',clearMoreSearchForm);
    		//$("a[op='ctrl']").on("click",displayAlarmCtrl);
    		
    		//$("a[op='del']").on("click",deleteData);//加载删除事件
    		//$("#hrefAdd").on('click',loadEdit);//加载添加事件 
    		
    		//$("a[op='modify']").on("click",loadEdit);
    		
    		$('.tablelist tbody tr:odd').addClass('odd');
    		//$("#btnDevAlaCtrlSave").on('click',submitAlarmCtrl);
    		
    	})    	
    	
    	//显示高级查询窗口
    	function disPlayMoreQuery()
    	{
    		
    		layer.open(
					{
	    				type: 1,
	    				closeBtn: 2,
	    				title: ['高级查询', 'font-size:18px;'],
	    				area: ['500px', '510px'],
	    				shadeClose: true, //点击遮罩关闭
	    				content:$("#div_moreSearch"),
			 		}
			);
    	}
    	
    	//提交查询表单
    	function submitQuery()
    	{
    		$("#moreSearchForm")[0].action=path+'/devStatusList.htm';
    	
    		$("#moreSearchForm")[0].submit();
    	}
    	
    	//简单查询提交
    	function simpleQuery()
    	{
    		//$("#btnClear").click();
    		clearMoreSearchForm();
    		$("#fName").val($("#devNameSearch").val());
    		$("#devCode").val($("#devCodeSearch").val());
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
    	
    	
    	