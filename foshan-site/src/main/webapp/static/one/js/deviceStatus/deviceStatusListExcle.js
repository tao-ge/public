/**
 * 设施状态查询js脚本
 */
$(function($){
 	$("a[op='ctrl']").on("click",displayAlarmCtrl);
 	$("#btnDevAlaCtrlSave").on('click',submitAlarmCtrl);
})
 	/**
	 * 显示报警处理页面
	 */
	function displayAlarmCtrl()
	{
		var dealSituation=$(this).attr("dealSituation");
		var did=$(this).attr("did");
		var dealSignName = $(this).attr("dealSignName");
			
		var disposeStatus = (dealSignName == '未处理') || 
		( dealSignName == '已处理' && window.confirm("报警已处理，是否再次处理") ) ? 0 : 1;
		   		
		if(disposeStatus == 0){
			$("#dealSituation").val(dealSituation);
    		$("#alarmProcessId").val(did);
    		layer.open(
					{
	    				type: 1,
	    				closeBtn: 2,
	    				title: ['报警处理', 'font-size:18px;'],
	    				area: ['600px', '230px'],
	    				shadeClose: true, //点击遮罩关闭
	    				content:$("#div_deviceAlarmCtrl"),
			 		}
			);    			
		}
		
		
	}
    	

	//提交报警处理数据
	function submitAlarmCtrl()
	{
		$.ajax({
			   type: "POST",
			   url: path+"/deviceAlarmCtrlSave.htm",
			   data:$("#form_deviceAlarmCtrl").serialize(),
			   success: function(r){
				   
				   var result=eval(r);
					  if(result.r=1)
						{
							layer.confirm('保存成功，选择下一步操作？', {
								  btn: ['继续编辑','返回查询列表'] //按钮
								}, function(){			 			
									layer.msg('进入数据编辑状态...', {icon: 1});
								}, function(){
									submitQuery();
									
		                        });
						}
						else
						{
							layer.msg(eval(data).r_content, {icon: 1});
						}
			   }
			});   
	}
    	
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
    	
    	