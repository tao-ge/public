/**
 */
$(function($){
    		
    		$("#btnSimpleQuery").on('click',simpleQuery);//注册简单查询事件
    		$("#btnDisplayMoreQuery").on('click',disPlayMoreQuery);//注册显示高级查询事件
    		$("#btnMoreQuery").on('click',moreQuery);//注册高级查询事情
    		$("#btnClear").on('click',clearMoreSearchForm);
    		$("a[op='del']").on("click",deleteData);//加载删除事件
//    		$("#hrefAdd").on('click',loadEdit);//加载添加事件
    		$("a[op='check']").on("click",check);//报警详情
    		$("a[op='deviceState']").on("click",facilityState);//上报数据日志
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
    				area: ['580px', '380px'],
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
	    				area: ['580px', '380px'],
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
    	
    	function facilityState(){
			var id = $(this).attr("did");
			var name = $(this).attr("dt");
			var curStatus='0';
			var aphanic = '1';
			var route = "/queryDeviceAlarmList.htm";
			window.location.href= path + "/queryFacilityState.htm?route="+route+"&aphanic="+aphanic+"&curStatus="+curStatus+"&codId="+id+"&codName="+name;
		}
    	
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
	    				content:$("#div_moreSearch")
			 		}
			);
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
    		$("#codName").val($("#codNameSearch").val());
    		$("#codCode").val($("#codCodeSearch").val());
    		$("#alarmTypes").val($("#alarm").val());
    		$("#dealSign").val($("#dealSignSerach").val());
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
    	 * 删除数据
    	 */
    	function deleteData()
    	{
    		var dt=$(this).attr("dt");
    		var did=$(this).attr("did");
    		//询问框
    		layer.confirm('确认要删除"'+dt+'"？', {
    		  btn: ['确定','取消'] //按钮
    		}, function(){
    			$.ajax({
    				   type: "POST",
    				   url: path+"/facilityDelete.htm?devId="+did,
    				
    				   success: function(r){
    					   layer.msg(eval(r).r_content, {icon: 1});
    					   submitQuery();
    				   }
    				});   			
    		   
    		}, null);
    	}
    	
    	//报警详情
    	function check(){
//    		var alarmId = $(this).attr("did");
//    		alert(alarmId)
    		location.href =path+"/queryDeviceAlarmInfo.htm?alarmId="+$(this).attr("did");
    	}
    	
    	