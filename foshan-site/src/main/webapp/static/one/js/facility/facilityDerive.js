/**
 * 设施状态查询js脚本
 */
$(function($){
			$("#btnSimpleQuery").on('click',submitQuery);//注册简单查询事件
    		$("#btnExportQuery").on('click',exportONU)//导出光路光交箱
    		$("#btnLightPath").on("click",rebuildDeviceRoute);
    		$('.tablelist tbody tr:odd').addClass('odd');
    	})    	
    	
//    	//分页浏览查询
//    	function pageQuery(){
//    		
//    		$("#pageNo").val($(this).attr("pages"));
//    		
//    		submitQuery();
//		}

    	//提交查询表单
    	function submitQuery()
    	{
			$("#SearchForm")[0].submit();
			layer.msg('数据查询中...', {icon: 16,time:0,id:'exportLayer'});
//    		var devId =  $("#devId").val();
//    		var data = "{"+"\"devId\":"+devId+",\"returnNum\":1}";
//    		$.ajax({
//				   type: "POST",
//				   url: path+'/deriveONU.htm',
//				   data:eval('(' + data + ')'),
//				   success: function(r){ 
//					   layer.closeAll();
//				   },
//				   beforeSend:function(r){					   
//					   layer.msg('数据查询中...', {icon: 16,time:0,id:'exportLayer'});
//				   }
//    		}); 
    	}
    	
    	//光路光交箱
    	function exportONU()
    	{
    		var devId =  $("#devId").val();
    		var data = "{"+"\"devId\":"+devId+"}";
    		$.ajax({
				   type: "POST",
				   url: path+'/facilityONU.htm',
				   data:eval('(' + data + ')'),
				   success: function(r){ 
					   layer.closeAll();
					   if(r.dt.filePath == 1){
						   alert("无数据!");
					   }else{
						   $("#exportDown").attr("href",path+r.dt.filePath)
						   layer.open(
									{
										type: 1,
					    				closeBtn: 2,
					    				title: ['提示', 'font-size:18px;'],
					    				area: ['300px', '200px'],
					    				shadeClose: true, //点击遮罩关闭
					    				content:$("#div_export")
							 		}
							);
					   }
					  
				   },
				   beforeSend:function(r){					   
					   layer.msg('数据生成中...', {icon: 16,time:0,id:'exportLayer'});
				   }
    		}); 
    	}
    	
    	function rebuildDeviceRoute()
    	{
    		var devId =  $("#devId").val();
    		$.ajax({
				   type: "POST",
				   url: path+'/rebuildDeviceRoutes.htm',
				   data:{devId:devId},
				   success: function(r){ 
					   layer.closeAll();
					   layer.msg('光路生成完毕。');
				   },
				   beforeSend:function(r){			
					   layer.load(2);
					   layer.msg('正在生成光路...', {icon: 16,time:0,id:'exportLayer'});
				   }
    		}); 
    	}
    	