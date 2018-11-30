/**
 * 设施管理js脚本
 */
$(function($){
    		
    		$("#btnSimpleQuery").on('click',simpleQuery);//注册简单查询事件
    		$("#btnDisplayMoreQuery").on('click',disPlayMoreQuery);//注册显示高级查询事件
    		$("#btnMoreQuery").on('click',moreQuery);//注册高级查询事情
    		$("#btnClear").on('click',clearMoreSearchForm);
    		$("a[op='del']").on("click",deleteData);//加载删除事件
    		$("#hrefAdd").on('click',loadEdit);//加载添加事件
    		$("#exportAll").on('click',exportAll);//导出
    		$("a[op='view']").on("click",openWinMap);
    		$("a[op='modify']").on("click",loadEdit);
    		$("a[op='detail']").on("click",loadDetail);
    		$('.tablelist tbody tr:odd').addClass('odd');
    		$("#facilityImport").on('click',showImportForm);//import excel
    		$("a[op='lock']").on("click",facilityLock);
    		$(".areadevCode").on("change",function(){
				changeArea($(this));
			})
			$(".areadevCode").change();
//    		$(".district").on("change",function(){
//				changeAreaSim($(this));
//			})
			$("#btnImport").on("click",exportFactily);
    	})    	
    	
    	//导出是否存在经纬度数据数据
    	function exportFactily(){
		var exist=$("#existLngLat").val();
		$.ajax({
			   type: "POST", 
			   url: path+'/exprotIstLngLat.htm?existLngLat='+exist,
			   data:'xls',
			   success: function(r){ 
				   layer.closeAll();
				   if(r.dt.filePath==1){
					   layer.msg("无数据,请确保已生成数据！", {icon: 1});
				   }else{
					   $("#exportDownFactily").attr("href",path+r.dt.filePath)
					   layer.open(
								{
									type: 1,
				    				closeBtn: 2,
				    				title: ['提示', 'font-size:18px;'],
				    				area: ['300px', '200px'],
				    				shadeClose: true, //点击遮罩关闭
				    				content:$("#div_exportFactily")
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
    		$("#devStateall").val($("#devState_all").val());//核查状态,0未核对,不是1是（1,2,3,4,5）
    		$("#devName").val($("#devNameSearch").val());
    		$("#devCode").val($("#devCodeSearch").val());
    		$("#devType").val($("#devTypeSearch").val());
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
    	
    	function facilityLock(){
    		var url=path+"/facilityLock.htm?devId="+$(this).attr("did");
    		var index = layer.open({
  			  type: 2,
  			  content: url,
  			  area: ['1000px', '520px'],
  			  maxmin: true
  			});
    	}
    	
    	function loadEdit()
    	{
    		var url='';
    		if($(this).attr("did"))
    			{
    			  url=path+"/facilityLoadUpdate.htm?devId="+$(this).attr("did");
    			}
    		else
    			{
    			url=path+"/facilityLoadAdd.htm?"
    			}
    		
    		
    		var index = layer.open({
    			  type: 2,
    			  content: url,
    			  area: ['1050px', '520px'],
    			  maxmin: true
    			});
    		
    			//layer.full(index);
    	}
    	/*
    	 * 设施数据导入
    	 */
    	function showImportForm()
    	{
    		
    		layer.open(
				{
    				type: 1,
    				closeBtn: 2,
    				title: ['设施导入', 'font-size:18px;'],
    				area: ['600px', '400px'],
    				shadeClose: true,
    				content:$("#div_import")
		 		}
			);
    		
    	}
    	/*
    	 * 加载详情页面
    	 */
    	function loadDetail()
    	{
    		layer.tab({
    			  area: ['600px', '300px'],
    			  tab: [{
    				  type: 2,
    			    title: 'TAB1', 
    			    content: path+"/facilityLoadAdd.htm?"
    			  }, {
    			    title: 'TAB2', 
    			    content: '内容2'
    			  }, {
    			    title: 'TAB3', 
    			    content: '内容3'
    			  }]
    			});   
    	}
    	
    	
    	/*
    	 * 打开地图窗口 
    	 */
//		function openWinMap(){
//			var lng = $(this).attr("lng");
//	    	var lat = $(this).attr("lat");
//	    	var banjing = 500;
//		    if(lng == ''&& lat==''){
//			    alert("无位置信息！");
//			    return;
//		    }
//			ymPrompt.win({message:path+'/jsp/one/facility/showMap.jsp?plng='+lng+'&plat='+lat+'&pbanjing='+banjing,
//					width:580,height:580,title:'位置信息',handler:null,iframe:true,btn:[['确定','yes'],['关闭','cancel']]});
//    			
//    		
//    			//layer.full(index);
//    	}
    	/*
    	 * 设施数据导入
    	 */
    	function showImportForm()
    	{
    		
    		layer.open(
				{
    				type: 1,
    				closeBtn: 2,
    				title: ['设施导入', 'font-size:18px;'],
    				area: ['600px', '400px'],
    				shadeClose: true,
    				content:$("#div_import")
		 		}
			);
    		
    	}
    	/*
    	 * 加载详情页面
    	 */
    	function loadDetail()
    	{
    		layer.tab({
    			  area: ['600px', '300px'],
    			  tab: [{
    				  type: 2,
    			    title: 'TAB1', 
    			    content: path+"/facilityLoadAdd.htm?"
    			  }, {
    			    title: 'TAB2', 
    			    content: '内容2'
    			  }, {
    			    title: 'TAB3', 
    			    content: '内容3'
    			  }]
    			});   
    	}
    	
    	
    	/*
    	 * 导出  刘沧海  2017/10/17
    	 */
    	function exportAll(){
    		
    		
    		//if(!getNowFormatDate()) return;
    		layer.open({
    			type:1,
    			title:'导出',
    			content:$('#div_area'),
    			area:'250px',
    			btn:['确定','取消'],
    			yes:function(){
    				var areaCode3 = $("#areaCode3").val();
    				var district = $("#district").val();
    				//alert(areaCode1 + "="+ district);
    				if(district=='' && (areaCode3==null || areaCode3==''))
    				{
    					layer.alert("导出全部",{icon:1});
    					getRebuildAlls(district);
    				}else if(areaCode3=='' ){
    					layer.alert("当前按照所属区导出",{icon:1});
    					getRebuildAlls(district);
    				}else{
    					layer.alert("当前按照汇聚区导出",{icon:1});
    					getRebuildAlls(areaCode3);
    				}
    			}
    		})
    		
    	}
    	
    	/**
    	 * 导出
    	 * @returns
    	 */
    	function getRebuildAlls(areaCode) {
    		$.ajax({
				   type: "POST", 
				   url: path+'/exportAll.htm?areaCode='+areaCode,
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
    	
    	
    	function changeAreaSim(areadevCode){
        	
    		var  value = $(areadevCode).val();
    		//var areas = $(".areaCode1");
    		var areas = $(areadevCode).parents("div").find("select.hjq1");
    		
    		areas.children("option").remove();
    		areas.append($("<option>").prop("value",'').append("==请选择=="));
    		$.ajax({
    			type:"POST",
    			url: path+"/ajax/getAreas.htm?areaRank=4&parentAreaCode="+value,
    			dataType:"json",
    			success:function(data)
    			{
    				$.each(data,function(index,value){
    					areas.append($("<option>").prop("value",data[index].name).append(data[index].value));
    				});
    			}
    		});
    	}
    	
    	/*
    	 * 打开地图窗口 
    	 */
		function openWinMap(){
			var lng = $(this).attr("lng");
	    	var lat = $(this).attr("lat");
	    	var banjing = 500;
		    if(lng == ''&& lat==''){
			    alert("无位置信息！");
			    return;
		    }
			ymPrompt.win({message:path+'/jsp/one/facility/showMap.jsp?plng='+lng+'&plat='+lat+'&pbanjing='+banjing,
					width:700,height:500,title:'位置信息',handler:null,iframe:true,btn:[['确定','yes'],['关闭','cancel']]});
		}
		
		function changeArea(districtObj){
    		var areaCode1=$("#areaCode2").val();
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
		
//		function changeArea(districtObj){
//			
//			
//    		
//    			var value = $(districtObj).val();
//    	
//    		var areas = $(districtObj).parents("div").find("select.hjq");
//    		areas.children("option").remove();
//    		areas.append($("<option>").prop("value",'').append("==请选择=="));
//    		$.ajax({
//    			type:"POST",
//    			url: path+"/ajax/getAreas.htm?areaRank=4&parentAreaCode="+value,
//    			dataType:"json",
//    			success:function(data)
//    			{
//    				$.each(data,function(index,value){
//   					 
//   					 areas.append($("<option>").prop("value",data[index].name).append(data[index].value));
//   					
//   				});
//    			}
//    		});
//    	}
