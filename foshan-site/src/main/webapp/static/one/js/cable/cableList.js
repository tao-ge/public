/**
 * 设施管理js脚本
 */
$(function($){
    		
    		$("#btnSimpleQuery").on('click',simpleQuery);//注册简单查询事件
    		$("#checkedbox").on('click',oncheckbox); //注册单选单击事件
    		$("#btnDisplayMoreQuery").on('click',disPlayMoreQuery);//注册显示高级查询事件
    		$("#btnMoreQuery").on('click',moreQuery);//注册高级查询事情
    		$("#btnClear").on('click',clearMoreSearchForm);
    		$("a[op='del']").on("click",deleteData);//加载删除事件
    		$("#hrefAdd").on('click',loadEdit);//加载添加事件 
    		$("a[op='modify']").on("click",loadEdit);
    		$("#btnExport").on('click',exportQuery);//导出光交箱光缆段
    		$("#btnSectState").on('click',exportSectState);//光缆段状态导出
//    		$("#btnExportQueryCablin").on('click',exportCablin);//导出机柜光缆段
//    		$("#btnXLSPortInfo").on('click',exportPortInfo);//导出纤芯占用详细
//    		$("#btnXLSPortStat").on('click',exportPortStat);//导出纤芯占用统计
    		$('.tablelist tbody tr:odd').addClass('odd');
    		$(".areadevCode").on("change",function(){
				changeArea($(this));
			})
			$(".areadevCode").change();
//    		$(".district").on("change",function(){
//				changeAreaSim($(this));
//			})
    		$("a[op='chart']").on("click",ChartData);//加载删除事件
    		
    		
    	})    	
    	
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
	    				content:$("#div_moreSearch"),
			 		}
			);
    	}
    	
    	//提交查询表单
    	function submitQuery()
    	{
//    		$("#moreSearchForm")[0].action=path+'/cableList.htm';
//			if ($("#checkedbox").is(':checked')) {
//				$("#check").val("1");
//			}else{
//				$("#check").val("0");
//			}
			
    		$("#moreSearchForm")[0].submit();
    	}

		//注册单选单击事件
		function oncheckbox(){
			if($(this).is(':checked')){
				//$("#states").style.color="red";
				$("#isTerminat1 option[value='']").remove(); 
			}
		}
    	
    	//简单查询提交
    	function simpleQuery()
    	{ 
//    		var s=$("#areaCode1").val();
//    		alert(s+"汇聚区");
    		//$("#btnClear").click();
    		clearMoreSearchForm();
    		$("#secName").val($("#cableNameSearch").val());
    		$("#secCode").val($("#cableCodeSearch").val());
    		if($("#areaCode").val()!=null && $("#areaCode").val()!=''){
    			$("#areaCode1").val($("#areaCode").val());
    		}else{
//    			alert($("#areadevCode").val());
    			$("#areaCode1").val($("#areadevCode").val());
    		}
    		
    		$("#devName").val($("#devNameSearch").val());
    		$("#devCode").val($("#devCodeSearch").val());
    		
    		var areadevCode1=document.getElementById("areadevCode").value;
    		
    		
    		$("#areadevCode1").val(areadevCode1);//存到隐藏域
    		$("#isTerminat").val($("#isTerminat1").val());
//    		//复选框选中赋值
//    		if ($("#checkedbox").is(":checked")) {
//    			var is = $('#isTerminat1').val();
//    			var check=$('#checkedbox').val(is);
//    			$("#check").val(check);
//    			//$("#checkedbox").attr("checked","checked");
//			}
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
    	
    	/*清空更多查询条件*/
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
    				   url: path+"/cableDelete.htm?sectId="+did,
    				
    				   success: function(r){
    					   layer.msg(eval(r).r_content, {icon: 1});
    					   submitQuery();
    				   }
    				});   			
    		   
    		}, null);
    	}
    	
    	function loadEdit()
    	{
    		var url='';
    		if($(this).attr("did"))
    			{
    			  url=path+"/cableUpdates.htm?cableId="+$(this).attr("did");
    			}
    		else
    			{
    			url=path+"/cableAdds.htm?"
    			}
    		
    		
    		var index = layer.open({
    			  type: 2,
    			  content: url,
    			  area: ['1000px', '700px'],
    			  maxmin: true
    			});
    		
    			//layer.full(index);
    	}
    	
    	function changeArea(districtObj){
    		var areaCode1=$("#areaCode1").val();
    		var  value = $(districtObj).val();
    		var areas = $(districtObj).parents("div").find("select.hjq");
    		
    		areas.children("option").remove();
    		areas.append($("<option>").prop("value",'').append("==请选择=="));
    		$.ajax({
    			type:"POST",
    			url: path+"/ajax/getAreas.htm?areaRank=4&parentAreaCode="+value,
    			dataType:"json",
    			success:function(data)
    			{
    				$.each(data,function(index,value){
    					 if(areaCode1==value.name)
    					{
    						 
    						 //回显
    						areas.append($("<option>").prop("value",data[index].name).prop("selected",true)
    						.append(data[index].value));
    						
        					areas.append($("<option>").prop("value",data[index].name).append(data[index].value));
        					//删除重复数据
        					var obj=document.getElementById("areaCode");
         					//index,要删除选项的序号，这里取当前选中选项的序号
         					var index=obj.selectedIndex;
         					obj.options.remove(index+1); 
    					}else{
    					 areas.append($("<option>").prop("value",data[index].name).append(data[index].value));
    					}
    				});
    			}
    		});
    		
    	}
    	
//    	function changeAreaSim(areadevCode){
//        	
//    		var  value = $(areadevCode).val();
//    		//var areas = $(".areaCode1");
//    		var areas = $(areadevCode).parents("div").find("select.hjq1");
//    		
//    		areas.children("option").remove();
//    		areas.append($("<option>").prop("value",'').append("==请选择=="));
//    		$.ajax({
//    			type:"POST",
//    			url: path+"/ajax/getAreas.htm?areaRank=4&parentAreaCode="+value,
//    			dataType:"json",
//    			success:function(data)
//    			{
//    				$.each(data,function(index,value){
//    					areas.append($("<option>").prop("value",data[index].name).append(data[index].value));
//    				});
//    			}
//    		});
//    	}
    	
    	//导出光缆段
    	function exportQuery()
    	{
    		var str=$("#areaCode").val(); 
    		var areadevCode=$("#areadevCode").val(); 
    		var secName=$("#secName").val(); 
    		var secCode=$("#secCode").val(); 
    		var sectState=$("#sectState").val(); 
    		var devCode=$("#devCode").val(); 
    		var devName=$("#devName").val(); 
    		var isTerminat=$("#isTerminat").val(); 
    		
    		var exportType = $("#exportSelect").val(); 
    		if(str==null || str==''){
    			alert("当前导出所属区数据！");
	    		$.ajax({
					   type: "POST",
//					   url: path+'/'+exportType+'.htm?areaCode1='+areadevCode+'&secName='+secName+'&secCode='+secCode+'&sectState='+sectState+'&devCode='+devCode+'&devName='+devName,
					   url: path+'/'+exportType+'.htm?areaCode1='+areadevCode,
					   data:'xls',
					   success: function(r){
						   layer.closeAll();
						   if(r.dt.filePath==1){
							   alert("无数据")
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
    		}else{
    			alert("当前导出汇聚区数据！");
	    		$.ajax({
					   type: "POST",
					   url: path+'/'+exportType+'.htm?areaCode1='+str,
					   data:'xls',
					   success: function(r){
						   layer.closeAll();
						   if(r.dt.filePath==1){
							   alert("无数据")
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
    	}
    	//导出机柜光缆段
    	function exportCablin(){
    		var str=$("#areaCode1").val(); 
    		$.ajax({
				   type: "POST",
				   url: path+'/cableExportCablin.htm?areaCode1='+str,
				   data:'xls',
				   success: function(r){
					   layer.closeAll();
					   if(r.dt.filePath==1){
						   alert("无数据")
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
    	
    	//导出纤芯占用详细(光交箱)
    	function exportPortInfo()
    	{
    		var str=$("#areaCode1").val();
    		$.ajax({ 
				   type: "POST",
				   url: path+'/exportPortInfo.htm?areaCode1='+str,
				   data:'xls',
				   success: function(r){
					   layer.closeAll();
					   if(r.dt.filePath==1){
						   alert("无数据")
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
    	
    	//导出纤芯占用详细(机柜)
    	function exportPortInfoCablin()
    	{
    		var str=$("#areaCode1").val();
    		$.ajax({ 
				   type: "POST",
				   url: path+'/exportPortInfoCablin.htm?areaCode1='+str,
				   data:'xls',
				   success: function(r){
					   layer.closeAll();
					   if(r.dt.filePath==1){
						   alert("无数据")
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
    	
    	//导出纤芯占用统计
    	function exportPortStat()
    	{
    		var str=$("#areaCode1").val();
    		$.ajax({ 
				   type: "POST",
				   url: path+'/exportPortStat.htm?areaCode1='+str,
				   data:'xls',
				   success: function(r){
					   layer.closeAll();
					   if(r.dt.filePath==1){
						   alert("无数据")
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
    	
    	/*
    	 * 加载熔钎盘数据
    	 */
    	function ChartData()
    	{
    		var sectId=$(this).attr("dts");
    		var devCodeA=$(this).attr("did");
    		var devCodeZ=$(this).attr("dt");
    		if(devCodeA==null && devCodeZ==null){
    			alert("两端无设施，不可查看设施！");
    			return false;
    		}
    		if(devCodeA!=null){
    			var urlA = "cableSectionChart.htm?devCode="+devCodeA+"&sectId="+sectId+"&state="+1;
    		}
    		if(devCodeZ!=null){
    			var urlZ = "cableSectionChart.htm?devCode="+devCodeZ+"&sectId="+sectId+"&state="+2;
    		}

    		//多窗口模式，层叠置顶
    		layer.open({
    		  type: 2 //此处以iframe举例
    		  ,title: 'A端设施信息'
    		  ,area: ['550px', '500px']
    		  ,shade: 0
    		  ,offset: [ //为了演示，随机坐标
    			  14
    			    ,2
    		  ]
    		  ,maxmin: true
    		  ,content: urlA
    		  ,zIndex: layer.zIndex //重点1
    		  ,success: function(layero){
    		    layer.setTop(layero); //重点2
    		  }
    		});
    		
    		layer.open({
      		  type: 2 //此处以iframe举例
      		  ,title: 'Z端设施信息'
      		  ,area: ['550px', '500px']
      		  ,shade: 0
      		  ,offset: [ //为了演示，随机坐标
      			 14
      		    ,570
      		  ]
      		  ,maxmin: true
      		  ,content: urlZ
      		  ,zIndex: layer.zIndex //重点1
      		  ,success: function(layero){
      		    layer.setTop(layero); //重点2
      		  }
      		});
    	}
    	
    	//按照光缆段状态导出
    	function exportSectState()
    	{
    		var str=$("#areaCode").val(); 
    		var areadevCode=$("#areadevCode").val(); 
    		var exportType = $("#isTerminat").val(); 
    		if(exportType=='0'){
    			exportType='moDuanSectionExport.htm?localState=0'//末端光缆
    		}if(exportType=='1'){
    			exportType='yiDuanSectionExport.htm?localState=1'//一端未成端
    		}if(exportType=='2'){
    			exportType='yiDuanSectionExport.htm?localState=2'//已成端
    		}if(exportType=='3'){
    			exportType='chongFuSectionExport.htm?localState=3'//重复光缆
    		}if(exportType=='4'){
    			exportType='yiDuanSectionExport.htm?localState=4'//两端均未成端
    		}if(exportType=='all'){
    			exportType='allSectionExport.htm?localState=all'//两端均未成端
    		}
    		if(str==null || str==''){
    			alert("当前导出所属区数据！");
	    		$.ajax({
					   type: "POST",
					   url: path+'/'+exportType+'&areaCode1='+areadevCode,
					   data:'xls',
					   success: function(r){
						   layer.closeAll();
						   if(r.dt.filePath==1){
							   alert("无数据")
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
    		}else{
    			alert("当前导出汇聚区数据！");
	    		$.ajax({
					   type: "POST",
					   url: path+'/'+exportType+'.htm?areaCode1='+str,
					   data:'xls',
					   success: function(r){
						   layer.closeAll();
						   if(r.dt.filePath==1){
							   alert("无数据")
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
    	}
    	
    	