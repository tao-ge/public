/**
 * 设施管理js脚本
 */
$(function($){
			$("#btnSimpleQuery").on('click',simpleQuery);//注册简单查询事件
    		$("#btnClear").on('click',clearMoreSearchForm);
    		$("a[op='del']").on("click",deleteData);//加载删除事件
    		$("#hrefAdd").on('click',loadEdit);//加载添加事件
    		
    		$("a[op='modify']").on("click",loadEdit);
    		$("a[op='detail']").on("click",loadDetail);
    		$('.tablelist tbody tr:odd').addClass('odd');
    		
    	})    	
    	
    	//提交查询表单
    	function submitQuery()
    	{
    		
    		$("#SearchForm")[0].submit();
    	}
    	
    	//简单查询提交
    	function simpleQuery()
    	{

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
    				   url: path+"/sonDelete.htm?devId="+did,
    				
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
    			  url=path+"/sonFacilityLoadUpdate.htm?devId="+$(this).attr("did")+"&pdevId="+$(this).attr("dids");
    			}
    		else
    			{
    			url=path+"/sonFacilityLoadAdd.htm?devId="+$(this).attr("dids");
    			}
    		
    		
    		var index = layer.open({
    			  type: 2,
    			  content: url,
    			  area: ['1000px', '400px'],
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
    	
    	