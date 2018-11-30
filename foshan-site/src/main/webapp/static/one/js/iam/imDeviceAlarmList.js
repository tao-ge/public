/**
 */
$(function($){
    		$("a[op='ctrl']").on("click",dodealSign);//报警详情
    		$("#btnDevAlaCtrlSave").on("click",presentation);//提交处理
    		$("a[op='detail']").on("click",detail);//查看处理结果
    		$("#close").on("click",close);
    	})    	
    	//关闭
    	function close(){
			layer.close(layer.index);
		}
    	//查看处理结果
    	function detail(){
			var alaramCoent = $(this).attr("dt");
			$("#coent").val(alaramCoent);
			layer.open(
				{
					type: 1,
    				closeBtn: 2,
    				title: ['处理结果', 'font-size:10px;'],
    				area: ['380px', '180px'],
    				shadeClose: true, //点击遮罩关闭
    				content:$("#div_deviceAlarmCtrl1")
				}		
			);
			
		}
    	//提交显示窗口
    	function dodealSign(){
			var alarmId = $(this).attr("did");
			$("#alarmId").val(alarmId);
			layer.open(
					{
	    				type: 1,
	    				closeBtn: 2,
	    				title: ['报警处理', 'font-size:10px;'],
	    				area: ['380px', '180px'],
	    				shadeClose: true, //点击遮罩关闭
	    				content:$("#div_deviceAlarmCtrl")
			 		}
			);
			
		}
		//提交处理
		function presentation(){
			$.ajax({
				   type: "POST",
				   url: path+"/deviceAlarmPresentationSave.htm",
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
    	
    	//分页浏览查询
    	function pageQuery(){
    		
    		$("#pageNo").val($(this).attr("pages"));
    		
    		submitQuery();
		}
    
    	//提交查询表单
    	function submitQuery()
    	{
    		$("#moreSearchForm")[0].submit();
    	}
    	
    	