/**
 * 智能锁管理js
 */
$(function($) {
	$("#hrefAdd").on("click", showAddForm);// 显示新建窗口
	$("a[op='edit']").on("click", showEditForm);// 显示修改窗口
	$("a[op='pic']").on("click", showInspectImg);// 显示巡检图片页面
	$("a[op='del']").on("click", deleteData);// 加载删除事件
	$("a[op='see']").on("click", showDevListForm);// 显示设施列表
	$("a[op='view']").on("click",openWinMap);
	$("#btnSearch").on('click', submitQuery);// 查询确认



});

// 提交查询表单
function submitQuery() {
	$("#searchlistform")[0].submit();
}


//分页浏览查询
function pageQuery(){
	$("#pageNo").val($(this).attr("pages"));
	submitQuery();
}

// 显示新建窗口
function showAddForm() {
	location.href = path + "/goWorkAdd.htm";
}

//显示巡检图片页面
function showInspectImg(){
	
	//刘沧海修改弹窗
	 var url = path + "/showInspectImg.htm?workId=" + $(this).attr("aid");
		var index = layer.open({
			  type: 2,
			  content: url,
			  title: ['巡检图片', 'font-size:18px;'],
			  area: ['1000px', '500px'],
			  maxmin: true
			});
	
//	var workId = $(this).attr("aid");
//	 url = path + "/showInspectImg.htm?workId=" + workId;
//	var index = layer.open({
//		  type: 2,
//		  content: url,
//		  area: ['700px', '500px'],
//		  maxmin: true
//		});
	
	
//	var url='';
//	if($(this).attr("lng")){
//		url=path+"/openInpectMap.htm?workId="+$(this).attr("lng");
//		var index = layer.open({
//			  type: 2,
//			  content: url,
//			  area: ['700px', '500px'],
//			  maxmin: true
//			});
//	}else{
//		alert('无设施信!');
//	}
}

// 显示修改窗口
function showEditForm() {
	var workId = $(this).attr("aid");
	var url = path + "/goWorkEdit.htm?workId=" + workId;
	location.href = url;
}

// 删除
function deleteData() {
	var workId = $(this).attr("aid");
	// 询问框
	layer.confirm('确认要删除这条巡检任务吗？', {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		$.ajax({
			type : "POST",
			url : path + "/delWork.htm?id=" + workId,

			success : function(r) {
				layer.msg(eval(r).r_content, {
					icon : 1
				});
				submitQuery();
			}
		});

	}, null);
}

function onSearchMore() {
	$("#moreSearchForm")[0].submit();
}

function showDevListForm(){
	var ids = $(this).attr("ids");
	$.ajax({
		type : "POST",
		url : path + "/queryDevlistByIdss.htm?ids="+ids,
		data : $("#form_accessCtrl").serialize(),
		success : function(r) {
			var result = eval(r);
			if (result.r = 1) {
				$("#selectDev").empty();
				for(var i=0;i<result.dtList.length;i++){
					var option = $("<li>").val(i).text(result.dtList[i].devName);
					$("#selectDev").append(option);
				}
				layer.open({
					type : 1,
					closeBtn : 2,
					title : [ '设施列表', 'font-size:18px;' ],
					area : [ 'auto', '400px' ],
					shadeClose : true, // 点击遮罩关闭
					content : $("#div_devlist"),
				});
			} else {
				layer.msg(eval(data).r_content, {
					icon : 1
				});
			}
		}
	});
}

function submitCtrlSave() {
	$.ajax({
		type : "POST",
		url : path + "/saveAccess.htm",
		data : $("#form_accessCtrl").serialize(),
		success : function(r) {
			var result = eval(r);
			if (result.r = 1) {
				layer.confirm('保存成功，选择下一步操作？', {
					btn : [ '继续编辑', '返回查询列表' ]
				// 按钮
				}, function() {
					layer.msg('进入数据编辑状态...', {
						icon : 1
					});
				}, function() {
					submitQuery();

				});
			} else {
				layer.msg(eval(data).r_content, {
					icon : 1
				});
			}
		}
	});
}

function openWinMap()
{
	var url='';
	if($(this).attr("lng")){
		url=path+"/openInpectMap.htm?workId="+$(this).attr("lng");
		var index = layer.open({
			  type: 2,
			  content: url,
			  area: ['700px', '500px'],
			  maxmin: true
			});
	}else{
		alert('无设施信!');
	}
	
		//layer.full(index);
}
