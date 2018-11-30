/**
 * 普查管理js脚本
 */
$(function($){
    		
    		$("#btnSimpleQuery").on('click',simpleQuery);//注册简单查询事件
    		$("#btnDisplayMoreQuery").on('click',disPlayMoreQuery);//注册显示高级查询事件
    		$("#btnMoreQuery").on('click',moreQuery);//注册高级查询事情
    		$("#btnClear").on('click',clearMoreSearchForm);
    		$("a[op='del']").on("click",deleteData);//加载删除事件
    		$("#hrefAdd").on('click',loadEdit);//加载添加事件
    		$("a[op='modify']").on("click",loadEdit);
    		$('.tablelist tbody tr:odd').addClass('odd');
    		$("#censusImport").on('click',showImportForm);//import excel
    		$("#importExcel").on("change",uploadFile);
    		$("#result *").on("dblclick",function(){$("#result").hide();});
    		$("#checkdata").on("click",checkdata);
    	})    	
    	
    	//显示高级查询窗口
    	function disPlayMoreQuery()
    	{
    		
    		layer.open(
					{
	    				type: 1,
	    				closeBtn: 2,
	    				title: ['高级查询', 'font-size:18px;'],
	    				area: ['560px', '450px'],
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
    			  area: ['1000px', '520px'],
    			  maxmin: true
    			});
    		
    			//layer.full(index);
    	}
    	/*
    	 * 普查数据导入
    	 */
    	function showImportForm()
    	{
    		$("#importExcel").click();
    		/*layer.open(
				{
    				type: 1,
    				closeBtn: 2,
    				title: ['普查导入', 'font-size:18px;'],
    				area: ['600px', '400px'],
    				shadeClose: true,
    				content:$("#div_import")
		 		}
			);*/
    		
    	}
    	
    	function uploadFile(){

   		 var fileObj = document.getElementById("importExcel").files; // js 获取文件对象
   		    var files = new Array();
   		    var FileController = "censusImport.htm";                    // 接收上传文件的后台地址            
   		    var form = new FormData();
   		    for(var i = 0;i<fileObj.length;i++)
   		    {
   		    		form.append("importExcel", fileObj[i]);   
   		    }
   		    form.append("flag",$("#flag").val());
   		    // 文件对象
   		    // XMLHttpRequest 对象
   		 $("#result tbody").html("");
   		    var xhr = new XMLHttpRequest();
   		    xhr.open("post", FileController, true);
   		    xhr.responseType ="json";
   		    $("#result").show();
   		    $("#progress").html("正在导入中...");
   		    xhr.onreadystatechange = function () {
   		    	if(this.readyState==4)
   		    		{
			   		    if(this.status==200||this.status==304)
			   		    {
			   		    	$("#progress").html();
			   		    	  var o = this.response;
			   		    	  if(!o){
			   		    		  $("#result tbody").append("<tr><td>导入发生错误，请重试！</td></tr>");
			   		    	  }
			   		    	  else if(o.length==0)
			   		    	{
			   		    		  $("#result tbody").append("<tr><td>导入成功！</td></tr>");
			   		    	}else{
			   		    		for(var index = 0;index<o.length;index++)
			   		    		$("#result tbody").append("<tr><td>"+(index + 1)+". &nbsp;&nbsp;&nbsp;&nbsp; " +o[index]+"</td></tr>");
			   		    	}
			   		     }
   		    		}
   		    };    
   		    xhr.upload.onprogress=function(e){
   		    	//$("#progesss").html(e.lengthComputable +"/" + e.loaded + "/" + e.total);
   		    }
   		    xhr.onerror = function(){
   		    	alert("导入数据发生错误。")
   		    }
   		    xhr.send(form);
   		    $("#importExcel").val("");
    	}
    	
    	function checkdata(){
    		$.ajax({
    			url:"checkImportData.htm",
    			type:"post",
    			dataType:"json",
    			success:function(data){
    				console.log(data);
    			},
    			error:function(e,textStatus){
    				
    			},
    			complete:function()
    			{
    				
    			}
    		})
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
    	
    	
