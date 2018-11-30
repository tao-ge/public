/**
 */
$(function($){
    		
    		$("#btnSimpleQuery").on('click',simpleQuery);//注册简单查询事件
    		$("#btnDisplayMoreQuery").on('click',disPlayMoreQuery);//注册显示高级查询事件
    		$("#btnMoreQuery").on('click',moreQuery);//注册高级查询事情
    		$("#upload").on('click',returnLast);//返回事件加载
    		$("a[op='check']").on('click',historyDetial);//历史记录
    	})    	
    	
    	//历史记录
    	function historyDetial(){
			var discId=$(this).attr("did");
			var url = path+"/historyDetialByDiscId.htm?discId="+discId;
			layer.open({
				type: 2,
				closeBtn:2,
				title: ['历史上报记录', 'font-size:18px;'],
				area: ['880px', '400px'],
				shadeClose: true, //点击遮罩关闭
				content:url
			});
		}
    	//返回
    	function returnLast(){
			var route1 = $("#route1").val();
			window.location.href = path+route1;
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
//    		var startTime= $("#startTime").val();
//    		var endTime= $("#startTime").val();
//    		var t1 = new Date(startTime);   
//    		var t2 = new Date(endTime);  
//    		var d1=new Date(Date.parse(t1)).getTime();
//    		var d2=new Date(Date.parse(t2)).getTime(); 
    		
    		$("#moreSearchForm")[0].submit();
    	}
    	
    	//简单查询提交
    	function simpleQuery()
    	{
    		//$("#btnClear").click();
    		clearMoreSearchForm();

    		$("#devId").val($("#devIds").val());
    		$("#startTime").val($("#rptTimeSta").val());
    		$("#endTime").val($("#rptTimeEnd").val());
    		$("#codId").val($("#codIdSerach").val());
    		$("#route").val($("#route1").val());
    		$("#flag").val($("#flag1").val());
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
    	
    	
    	
