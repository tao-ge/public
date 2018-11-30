/**
 * 报警查询js脚本
 */
$(function($){
    		
			window.parent.topFrame.location.reload();
    		$("#btnSimpleQuery").on('click',simpleQuery);//注册简单查询事件
    		$("#btnDisplayMoreQuery").on('click',disPlayMoreQuery);//注册显示高级查询事件
    		$("#btnMoreQuery").on('click',moreQuery);//注册高级查询事情
    		$("#btnClear").on('click',clearMoreSearchForm);
    		$("a[op='ctrl']").on("click",displayAlarmCtrl);
    		
    		//$("a[op='del']").on("click",deleteData);//加载删除事件
    		//$("#hrefAdd").on('click',loadEdit);//加载添加事件 
    		
    		//$("a[op='modify']").on("click",loadEdit);
    		
    		$('.tablelist tbody tr:odd').addClass('odd');
    		$("#btnDevAlaCtrlSave").on('click',submitAlarmCtrl);
    		$("a[op='pic']").on("click",showImages);//查看图片
    		$("#btnImport").on("click",exportdevAlarm);//导出
    	})    	
    	
    	//导出数据
    	function exportdevAlarm(){
		$.ajax({
			   type: "POST", 
			   url: path+'/exportdevAlarm.htm',
			   data:'xls',
			   success: function(r){ 
				   layer.closeAll();
				   if(r.dt.filePath==1){
					   layer.msg("无数据,请确保已生成数据！", {icon: 1});
				   }else{
					   $("#exportDown").attr("href",path+r.dt.filePath)
					   layer.open(
								{
									type: 1,
				    				closeBtn: 2,
				    				title: ['提示', 'font-size:18px;'],
				    				area: ['300px', '200px'],
				    				shadeClose: true, //点击遮罩关闭
				    				content:$("#div_exportAll")
						 		}
						);
				   }
			   },
			   beforeSend:function(r){					   
				   layer.msg('数据生成中...', {icon: 16,time:0,id:'exportLayer'});
			   }
			}); 
		}
    	//查看图片 fl 修改 ，2018年 2月12日
    	function showImages(){
			var did = $(this).attr("did");
			$.ajax({
				   type: "POST",
				   url: path+"/queryAlarmImage.htm?alarmProcessId="+did,
				   success: function(data){

					   if(data.image!=null){
						   var url = path+data.image.imageUrl;
//						   var req = CheckImgExists(url);
						   if(data.image.imageUrl != null || data.image.imageUrl != ''){
							   var img = document.getElementById("img");
							   img.setAttribute("src",url);
							   layer.open(
										{
											type: 1,
											closeBtn: 2,
											title: ['开锁图片', 'font-size:18px;'],
											area: ['430px', '400px'],
											shadeClose: true, //点击遮罩关闭
											content:$("#div_img")
								 		}
								);
						   }else{
							   layer.msg('无图片信息', {icon: 5});
						   }
					   }else{
						   layer.msg('无图片信息', {icon: 5});
					   }
				   }
				});
		}
    	
    	//显示高级查询窗口
    	function disPlayMoreQuery()
    	{
    		
    		layer.open(
					{
	    				type: 1,
	    				closeBtn: 2,
	    				title: ['高级查询', 'font-size:18px;'],
	    				area: ['500px', '350px'],
	    				shadeClose: true, //点击遮罩关闭
	    				content:$("#div_moreSearch"),
			 		}
			);
    	}
    	
    	//提交查询表单
    	function submitQuery()
    	{
    		$("#fName").val($("#devNameSearch").val());
    		$("#devCode").val($("#devCodeSearch").val());
    		$("#dealSign").val($("#dealSignSearch").val());
    		$("#alarmTimeStart").val($("#startTimeSearch").val());
    		$("#alarmTimeEnd").val($("#endTimeSearch").val());
    		$("#alarmTypes").val($("#alarmTypesSearch").val());
    		$("#moreSearchForm")[0].action=path+'/deviceAlarmList.htm';
    	
    		$("#moreSearchForm")[0].submit();
    	}
    	
    	//简单查询提交
    	function simpleQuery()
    	{
    		//$("#btnClear").click();
//    		clearMoreSearchForm();
    		
    		
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
    	
    	//验证图片是否存在
		function CheckImgExists(imgurl) {  
		    var ImgObj = new Image(); //判断图片是否存在  
		    ImgObj.src = imgurl;  
		    //没有图片，则返回-1  
		    if (ImgObj.fileSize > 0 || (ImgObj.width > 0 && ImgObj.height > 0)) {  
		        return true;  
		    } else {  
		        return false;
		    }  
		} 
    	
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
    	    				area: ['600px', '350px'],
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