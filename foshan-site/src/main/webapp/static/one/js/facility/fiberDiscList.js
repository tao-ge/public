
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
    				   url: path+"/sectDelete.htm?sectId="+did,
    				
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
    			  url=path+"/cableSectionLoadUpdate.htm?sectId="+$(this).attr("did");
    			}
    		else
    			{
    			url=path+"/cableSectionLoadAdd.htm?"
    			}
    		
    		
    		var index = layer.open({
    			  type: 2,
    			  content: url,
    			  area: ['600px', '600px'],
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
    	
    	

	//弹出一个页面层
	
   $(function () {
      	 $("#add_rxp").click(function () {
			layer.open({
			type: 1,
			closeBtn: 1,
			title: ['韶关始兴隘子镇光交箱', 'font-size:18px;'],
			area: ['600px', '360px'],
			shadeClose: true, //点击遮罩关闭
			content:$("#box1"),
			btn: ['保存'],
			 });
                 })
            })  
			
 	$(function () {
      	 $("#up_flp").click(function () {
			layer.open({
			type: 1,
			closeBtn: 1,
			title: ['韶关始兴隘子镇光交箱', 'font-size:18px;'],
			area: ['600px', '360px'],
			shadeClose: true, //点击遮罩关闭
			content:$("#box2"),
			 });
                })
            }) 
	$(function () {
      	 $("#up_rxp").click(function () {
			layer.open({
			type: 1,
			closeBtn: 1,
			fix: false,
			title: ['韶关始兴隘子镇光交箱', 'font-size:18px;'],
			area: ['600px', '360px'],
			shadeClose: true, //点击遮罩关闭
			content:$("#box3"),
			 });
                })
            })  
			
	$(function () {
      	 $("#query_rxp").click(function () {
			layer.open({
			type: 1,
			fix: false,
			closeBtn: 1,
			title: ['韶关始兴隘子镇光交箱', 'font-size:18px;'],
			area: ['600px', '600px'],
			shadeClose: true, //点击遮罩关闭
			content:$("#box4"),
			 });
                })
            })   
			
			
			 
	//关闭iframe
	
	
		$(function () {
		$('#closeIframe').click(function(){
		var val = $('#name').val();
		if(val === ''){
			parent.layer.msg('请填写标记');
			return;
		}
		parent.layer.msg('您将标记 [ ' +val + ' ] 成功传送给了父窗口');
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
			});	 
      	 
            })   
			
			
