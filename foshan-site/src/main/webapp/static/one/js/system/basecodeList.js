/**
 * 数据字典管理js脚本
 */
$(function($){
	$("#btnDisplayMoreQuery").on('click',disPlayMoreQuery);//注册显示高级查询事件
	$("#btnMoreQuery").on('click',moreQuery);//注册高级查询事情
})
//显示高级查询窗口
function disPlayMoreQuery()
{	
	layer.open(
			{
				type: 1,
				closeBtn: 2,
				title: ['高级查询', 'font-size:18px;'],
				area: ['500px', '500px'],
				shadeClose: true, //点击遮罩关闭
				content:$("#div_moreSearch"),
	 		}
	);
}
//提交高级查询窗口
function moreQuery()
{
	$("#pageNo").val('1');
	$("#moreSearchForm")[0].action=path+'/basecodeList.htm';
	$("#SearchForm")[0].submit();
}

