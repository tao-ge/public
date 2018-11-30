/**
 * 设施状态查询js脚本
 */
$(function($){
    		
    		$("#btnSimpleQuery").on('click',simpleQuery);//注册简单查询事件
    		$("#btnDisplayMoreQuery").on('click',disPlayMoreQuery);//注册显示高级查询事件
    		$("#btnMoreQuery").on('click',moreQuery);//注册高级查询事情
    		$("#btnClear").on('click',clearMoreSearchForm);
    		
    		$('.tablelist tbody tr:odd').addClass('odd');
    		//$("#btnDevAlaCtrlSave").on('click',submitAlarmCtrl);
    		$("#btnImport").on("click",exportdevTimeNewStatus);//导出数据
    		
    	})    	
    	//导出数据
    	function exportdevTimeNewStatus(){
		$.ajax({
			   type: "POST", 
			   url: path+'/exportdevTimeNewStatus.htm',
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
    		$("#moreSearchForm")[0].action=path+'/devTimeNewStatusList.htm';
    	
    		$("#moreSearchForm")[0].submit();
    	}
    	
    	//简单查询提交
    	function simpleQuery()
    	{
    		//$("#btnClear").click();
    		clearMoreSearchForm();
    		$("#fName").val($("#devNameSearch").val());
    		$("#devCode").val($("#devCodeSearch").val());
    		$("#doorStatus").val($("#doorStatusSearch").val());
    		$("#lockStatus").val($("#lockStatusSearch").val());
    	
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
    	
    	
    	