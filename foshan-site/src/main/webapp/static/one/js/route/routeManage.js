/**
 * 设施状态查询js脚本
 */
var areadevCodedd;
$(function($){
    		$("#btnSimpleQuery").on('click',simpleQuery);//注册简单查询事件
    		 areadevCodedd=document.getElementById("areadevCode3").value;		
    		$("#btnDisplayMoreQuery").on('click',disPlayMoreQuery);//注册显示高级查询事件
    		$("#btnMoreQuery").on('click',moreQuery);//注册高级查询事情
    		$("#btnClear").on('click',clearMoreSearchForm); 
    		$("#rebuildAll").on('click',rebuildAll);//加载全部重新生成事件
    		$("a[op='rebuild']").on("click",rebuildRoute);//加载重新生成事件
    		$("a[op='del']").on("click",deleteData);//加载删除事件
    		$('.tablelist tbody tr:odd').addClass('odd');
    		$("#btnExportQuery").on('click',exportQuery);//注册高级查询事情
    		
			$("#devId").val("-1");
			//设施下拉列表初始化
			$("#seledevId").tinyselect({ dataUrl: path+"/json/facility/list.htm" , dataParser: dataParserA });
			//所属工程选中设置工程id
			$("#seledevId").on("change",function() {
				
				$("#devId").val($(this).val());
			});
			
			$(".areadevCode").on("change",function(){
				changeArea($(this));
			})
			
			
			$(".district").on("change",function(){
				changeAreaSim($(this));
			})
				$(".district").change();
			$(".areadevCode").change();
			
			$("#rebuildAnyPoint").on("click",function(){
				rebuildAnyPoint();
			});
		
			$(".areadevCode").on("change",function(){
				changeArea1($(this));
			})
			$(".areadevCode").change();
			
			$("#areaCode1").val($("#areah").val());
    		var areadevCode2=document.getElementById("areadevCode2").value;
    		$("#areadevCode").val(areadevCode2);
    	})    	
    	
    	//显示高级查询窗口
    	function disPlayMoreQuery()
    	{
    		
    		layer.open(
					{
	    				type: 1,
	    				closeBtn: 2,
	    				title: ['高级查询', 'font-size:18px;'],
	    				area: ['550px', '350px'],
	    				shadeClose: true, //点击遮罩关闭
	    				content:$("#div_moreSearch")
			 		}
			);
    	}
    	
    	//提交查询表单
    	function submitQuery()
    	{
    		//$("#moreSearchForm")[0].action=path+'/routeList.htm';
    		$("#moreSearchForm")[0].submit();    		
    	}
    	
    	//简单查询提交
    	function simpleQuery()
    	{
    		clearMoreSearchForm();
    		$("#devName").val($("#devNameSearch").val());
    		$("#routetext").val($("#routetextSearch").val());
    		$("#areadevCode1").val($("#areadevCode").val());
    		$("#areaCode1").val($("#areaCode").val());
    	
    		$("#pageNo").val('1');
    		
    		submitQuery();
    	}
    	
    	//提交高级查询窗口
    	function moreQuery()
    	{
    	
    		$("#pageNo").val('1');
    		$("#routetext").val($("#routetextgjSearch").val());
    		$("#startTime").val($("#startTime").val());
    		$("#endTime").val($("#endTime").val());
    		
    		submitQuery();
    	}
    	
    	//分页浏览查询
    	function pageQuery(){
    		$("#devName").val($("#devNameSearch").val());
    		$("#routetext").val($("#routetextSearch").val());
    		$("#areadevCode1").val($("#areadevCode").val());
    		$("#areaCode1").val($("#areaCode").val());
    		$("#pageNo").val($(this).attr("pages"));
    		$("#devId").val($("#devd").val());
    		$("#startTime").val($("#startTimes").val());
    		$("#endTime").val($("#endTimes").val());
   
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
    	
        /**
         * 绑定带查询的下拉列表框
         */
    	function dataParserA(data, selected) {
    		retval = [ { val: "-1" , text: "---" } ];

    		data.forEach(function(v){
    			if(selected == "-1" && v.val == 3)
    				v.selected = true;
    			retval.push(v); 
    		});

    		return retval;
    	}
    	
    	/*
    	 * 单个重新生成
    	 */
    	function rebuildRoute()
    	{
    		var dt=$(this).attr("dt");
    		var did=$(this).attr("did");
    		var orgId=$(this).attr("org");
    		//询问框
    		layer.confirm('确认要重新生成"'+dt+'"？', {
    		  btn: ['确定','取消'] //按钮
    		}, function(){
    			$.ajax({
    				   type: "POST",
    				   url: path+"/rebuildRoute.htm?code="+did+"&orgId="+orgId,
    				
    				   success: function(r){
    					   
    					   layer.msg(eval(r).r_content, {icon: 1});
    					   submitQuery();
    				   }
    				});   			
    		   
    		}, null);
    	}
    	
    	function rebuildAnyPoint(){
    		var code = $("#anyCode").val();
    		if(code==null||""==code.trim())
			{
    			layer.msg("请先输入有效的端子编码",{icon:2});
    			return;
			}
    		var index = layer.load(2);
    		$.ajax({
				   type: "POST",
				   url: path+"/singlePointGenOptPath.htm?code="+code,
				   success: function(r){
					  layer.close(index);
					  if(r.r>0){
						  $("#currentCode").val(code);
						  submitQuery();
					  }
					  else
						  layer.alert(r.r_content,{icon:2});
				   },
				   error:function(e){
					   layer.close(index);
					   layer.alert('发生错误:' + e.statusText,{icon:2});
				   }
				});   	
    	}
    	
    	/*
    	 * 全部重新生成
    	 */
    	function rebuildAll(){
    		
    		
    		//if(!getNowFormatDate()) return;
    		layer.open({
    			type:1,
    			title:'生成光路',
    			content:$('#div_area'),
    			area:'250px',
    			btn:['确定','取消'],
    			yes:function(){
    				var areaCode1 = $("#areaCode1").val();
    				var district = $("#district").val();
    				//alert(areaCode1 + "="+ district);
    				if(areaCode1==null)
    				{
    					layer.alert("当前按照所属区生成",{icon:1});
    					getRebuildAlls(district);
    				}else if(areaCode1==''){
    					layer.alert(" 系统光路全部重新生成",{icon:1});
    					getRebuildAlls(district);
    				}else{
    					layer.alert("当前按照汇聚区生成",{icon:1});
    					getRebuildAlls(areaCode1);
    				}
    			}
    		})
    		
    	}
    	
    	/**
    	 * 生成光路
    	 * @returns
    	 */
    	function getRebuildAlls(areaCode) {
    		var index = layer.load(2);
			$.ajax({
				   type: "POST",
				   url: path+"/rebuildAlls.htm?areaCode="+areaCode,
				   success: function(r){
					  layer.close(index);
					   layer.alert(eval(r).r_content, {icon: 1});
					  if(r.r>0)
						  submitQuery();
				   },
				   error:function(e){
					   layer.close(index);
					   layer.alert('发生错误:' + e.statusText,{icon:2});
				   }
				});   
    	}
    	
    	//判断时间
    	function getNowFormatDate() {
    		var kssjsw=new Date(); 
    		kssjsw='09:00:00';
    		var jssjsw=new Date(); 
    		jssjsw='12:00:00';
    		var kssjxw=new Date(); 
    		kssjxw='13:00:00';
    		var jssjxw=new Date(); 
    		jssjxw='18:00:00';
    	    var date = new Date();
    	    var seperator1 = "-";
    	    var seperator2 = ":";   	    
    	    var strDate = date.getDate();   	    
    	    var currentdate =
    	             date.getHours() + seperator2 + date.getMinutes()
    	            + seperator2 + date.getSeconds();
    	    if(currentdate>=kssjsw&&currentdate<=jssjsw ||currentdate>=kssjxw&&currentdate<=jssjxw ){
    	    	layer.alert("09:00:00-12:00:00,13:00:00-18:00:00时间内不允许生成数据",{icon:1});
    	    	return false;
    	    }else{
    	    	return true;
    	    }
    	  
    	}
    	function changeArea(districtObj){
    		var areaCode1=areadevCodedd;
    		
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
    	
    	function changeAreaSim(areadevCode){
    	
    		var  value = $(areadevCode).val();
    		//var areas = $(".areaCode1");
    		var areas = $(areadevCode).parents("div").find("select.hjq");
    		
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
  //二级汇聚区  	
    	function changeArea1(districtObj){
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
    				   url: path+"/routeDelete.htm?routeId="+did,
    				
    				   success: function(r){
    					   layer.msg(eval(r).r_content, {icon: 1});
    					   submitQuery();
    				   }
    				});   			
    		   
    		}, null);
    	}
    	
    	
    	
    	//提交查询表单
    	function exportQuery()
    	{
    		$.ajax({
				   type: "POST",
				   url: path+'/routeExport.htm',
				   data:$("#moreSearchForm").serialize(),
				   success: function(r){
					   layer.closeAll();
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
				   },
				   beforeSend:function(r){					   
					   layer.msg('数据生成中...', {icon: 16,time:0,id:'exportLayer'});
				   }
				}); 
    	}
    

