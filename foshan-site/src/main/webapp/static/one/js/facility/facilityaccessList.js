/**
 * 智能锁管理js
 */
$(function($) {
	$("#btnSerchEx").on('click',showSearchMoreForm);//显示高级查询事件
	$("#hrefAdd").on("click", showAddForm);// 显示新建窗口
	$("#accExport").on("click", accExport);// 导出
	$("a[op='ctrl']").on("click", showControlForm);// 显示授权处理窗口
	$("a[op='edit']").on("click", showEditForm);// 显示修改窗口
	$("a[op='del']").on("click", deleteData);// 加载删除事件
	$("a[op='see']").on("click", showDevListForm);// 显示设施列表

	$("#btnSearch").on('click', submitQuery);// 查询确认
	$("#btnSearchMore").on('click',onSearchMore);//高级查询确认
	$("#btnClear").on('click',onClear);//高级查询清空

	$("#btnAccessCtrlSave").on('click', submitCtrlSave);// 查询确认
	
	$('.tablelist tbody tr:odd').addClass('odd');//隔行换色
});

//用高级查询进行提交查询
function submitQuery() {
	$("#userName").val($("#userNameSearch").val());
	$("#validateStatus").val($("#validateStatusSearch").val());
	$("#moreSearchForm")[0].submit();
//	$("#searchlistform")[0].submit();
}

// 显示高级查询
function showSearchMoreForm() {
	layer.open({
		type : 1,
		closeBtn : 2,
		title : [ '高级查询', 'font-size:18px;' ],
		area : [ '1000px', '520px' ],
		shadeClose : true, // 点击遮罩关闭
		content : $("#div_moreSearch"),
	});
}

//分页浏览查询
function pageQuery(){
	$("#pageNo").val($(this).attr("pages"));
	submitQuery();
}

// 显示新建窗口
function showAddForm() {
	location.href = path + "/goAccessAdd.htm";
}

// 显示修改窗口
function showEditForm() {
	var accessId = $(this).attr("aid");
	var url = path + "/goAccessEdit.htm?accessId=" + accessId;
	location.href = url;
}

// 删除
function deleteData() {
	var dt = $(this).attr("dt");
	var accessId = $(this).attr("aid");
	// 询问框
	layer.confirm('确认要删除"' + dt + '"的授权？', {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		$.ajax({
			type : "POST",
			url : path + "/delAccess.htm?id=" + accessId,

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

function onClear(){
	$("#devimei").val("");
	$("#mobile").val("");
	$("#devname").val("");
	$("#bdate").val("");
	$("#edate").val("");
	$("#bdateEnd").val("");
	$("#edateEnd").val("");
}

function showControlForm() {
	var accessId = $(this).attr("aid");
	var valTime = $(this).attr("vlt");
	var valStatus = $(this).attr("sta");
	var remark = $(this).attr("rmk");
	$("#accessId").val(accessId);
	$("#validateTime").val(valTime);
	var st = parseInt(valStatus);
	$("#validateStatusCtrl").val(st);
	$("#remark").val(remark);
	
	layer.open({
		type : 1,
		closeBtn : 2,
		title : [ '授权管理', 'font-size:18px;' ],
		area : [ '600px', '400px' ],
		shadeClose : true, // 点击遮罩关闭
		content : $("#div_accessCtrl"),
	});
}

function showDevListForm(){
	var ids = $(this).attr("ids");
	$.ajax({
		type : "POST",
		url : path + "/queryDevlistByIds.htm?ids="+ids,
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
//	var myDate = new Date("yyyy-MM-dd");
	var now = new Date();
	var year = now.getFullYear();    //年
	var month = now.getMonth() + 1;   //月
	var day = now.getDate();      //日
//	var hh = now.getHours();      //时
//	var mm = now.getMinutes();     //分
	var clock = year + "-";
	if (month < 10)
	clock += "0";
	clock += month + "-";
	if (day < 10)
	clock += "0";
	clock += day;
	var validateTime=$("#validateTime").val();
	if(validateTime == null || validateTime == ''){
		alert("请填写授权结束时间!");
		return false;
	}else{
		if (clock > validateTime) { 
			  alert("授权结束时间需大于当前时间！");
			  return false;
		}
	}
	
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

//提交查询表单
function accExport()
{
	$.ajax({
		   type: "POST",
		   url: path+'/accExport.htm',
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
