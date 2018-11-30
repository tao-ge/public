/**
 * 井管理js脚本
 */
$(function($){
	$("#btnSimpleQuery").on('click',simpleQuery);//简单查询
	$("#btnDisplayMoreQuery").on('click',disPlayMoreQuery);//点击高级查询
	$("#btnMoreQuery").on('click',moreQuery);//高级查询
	$("#exportWell").on('click',exportWell);//按条件导出excel表格
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
	
	submitQuery();
}

////提交高级查询窗口
//function moreQuery()
//{
//	/*$("#pageNo").val('1');*/
//	$("#moreSearchForm")[0].action=path+'/queryWellList.htm';
//	$("#SearchForm")[0].submit();
//}
//提交查询表单
function submitQuery(){
	$("#moreSearchForm")[0].submit();
}

function pageQuery(){
	$("#pageNo").val($(this).attr("pages"));
	submitQuery();
}	

function simpleQuery(){
//	clearMoreSearchForm();
//	var code=document.getElementById("classCode").value;
	$("#wellName").val($("#well_name").val());
	$("#wellNumber").val($("#well_number").val());
	$("#wellState").val($("#well_state").val());
//	$("#valueName").val($("#valueName").val())
	$("#pageNo").val('1');
	submitQuery();
}

//清空更多查询条件
function clearMoreSearchForm()
{
	$(':input','#SearchForm')
	.val('') 
	.removeAttr('checked') 
	.removeAttr('selected');
    //.not(':button, :submit, :reset, :hidden') 
}

function exportWell(){
	var wellName =$("#wellName").val();//井名称
	var wellNumber =$("#wellNumber").val();//喷码
	var wellState =$("#wellState").val();//现场核查状态 0 未核查 1 与现场一致 2 新增 3修改 4未找到 5新增删除 6存疑
	var wellType =$("#wellType").val();//井类型 01井 02挂墙 03杆 04标石
	var wellKind =$("#wellKind").val();//井种类 1孤立 2直通 3三通 4四通 5五通 6六通 7七通 8七通以上
	var IsFormerbureau =$("#IsFormerbureau").val();//是否为局前井
	var times1 =$("#times1").val();//开始时间
	var times2 =$("#times2").val();//结束时间
	$.ajax({
	   type: "POST", 
	   url: path+'/exportWell.htm?wellName='+wellName+'&wellNumber='+wellNumber+'&wellState='+wellState+'&wellType='+wellType+'&wellKind='+wellKind+'&IsFormerbureau='+IsFormerbureau+'&times1='+times1+'&times2='+times2,
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
