/**
 * 光缆段管理 js脚本 fl 
 */
$(function($){
	$("#btnSerchEx").on("click",showSearchMoreForm);//显示按井查询
//	$("a[op='del']").on("click",deleteData);//加载删除事件
//	$("a[op='modify']").on("click",loadEdit);//加载修改事件
	$("#btnSearch").on("click",submitQuery);//表单查询
})
		
var index;
		//显示按井查询
    	function showSearchMoreForm(){
    		index=layer.open(
    				{
    					type: 1,
    					closeBtn: 2,
    					title: ['按井查询', 'font-size:18px;'],
    					area: ['600px', '400px'],
    					shadeClose: true, //点击遮罩关闭
    					content:$("#div_moreSearch"),
    		 		}
    		);
    		
    		$.ajax({
    			type : "POST",
    			url : path + "/queryWellAll.htm",
    			success : function(r) {
    				var table = $("#tableDev");
    				$("#tableDev tbody").html("");
    				var result = eval(r);
    				if (result.r = 1) {
    					for(var i=0;i<result.dtList.length;i++){
    						table.append($("<tr><td>"
    								+result.dtList[i].wellName+"</td>"
    								+"<td><a href='javascript:;' op='choose' wname='"+result.dtList[i].wellName+"' class='tablelink'>选择</a></td></tr>"))
    					}
    					$("a[op='choose']").on("click", onChooseDev);// 选择设施确认
    					$('.tablelist tbody tr:odd').addClass('odd');
    				} else {
    					layer.msg(eval(data).r_content, {
    						icon : 1
    					});
    				}
    			}
    		});
    	}

		//赋值
		function onChooseDev() {
			
			var wellName = $(this).attr("wname");
			$("#wellName").val(wellName);
//			var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
			layer.close(index);
		}
    	//提交查询表单
    	function submitQuery()
    	{
    		$("#searchlistform")[0].submit();
    	}

    	//分页浏览查询
    	function pageQuery(){

    		$("#pageNo").val($(this).attr("pages"));
    		
    		submitQuery();
		}
    	
    	
