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
    		$("a[op='view']").on("click",openWinMap);
    		$("a[op='modify']").on("click",loadEdit);
    		$("a[op='detail']").on("click",loadDetail);
    		$('.tablelist tbody tr:odd').addClass('odd');
    		$("a[op='pic']").on("click",showImages);//查看图片
    		$("#btnImport").on("click",exportmobileSwitch);//导出数据
    	})    	
    	
    	//导出数据
    	function exportmobileSwitch(){
		$.ajax({
			   type: "POST", 
			   url: path+'/exportmobileSwitch.htm',
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
    	//显示图片
    	function showImages(){
			var did=$(this).attr("did");
			$.ajax({
				   type: "POST",
				   url: path+"/querySwitchImage.htm?switchId="+did,
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
    		$("#startTime").val($("#startTimeSearch").val());
    		$("#endTime").val($("#endTimeSearch").val());
    		$("#fName").val($("#fName1").val());
    		$("#devCode").val($("#devCode1").val());
    		$("#userName").val($("#userName1").val());
    		$("#switchType").val($("#switchType1").val());
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
    			  area: ['1000px', '700px'],
    			  maxmin: true
    			});
    		
    			//layer.full(index);
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
		function openWinMap(){
			var lng = $(this).attr("lng");
	    	var lat = $(this).attr("lat");
	    	var banjing = 500;
		    if(lng == ''&& lat==''){
			    alert("无位置信息！");
			    return;
		    }
			ymPrompt.win({message:'/frms-site/jsp/one/facility/showMap.jsp?plng='+lng+'&plat='+lat+'&pbanjing='+banjing,
					width:580,height:580,title:'位置信息',handler:null,iframe:true,btn:[['确定','yes'],['关闭','cancel']]});
		}