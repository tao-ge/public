/**
 * 智能锁授权管理js
 */
$(function($) {
	$("#btnClose").on("click", onClose);
	$("#btnSearch").on("click", onSearchDev);
	$("#hrefAddUser").on("click", showUserListForm);// 显示用户列表
	$("#hrefAddDev").on("click", showDevListForm);// 显示选择设施列表
	$("a[op='choose']").on("click", onChooseDev);// 选择设施确认
	$("a[op='select']").on("click", onChooseUser);// 选择用户确认
	$("#devSelect").on("dblclick", onDblClickSelect);// 双击设施列表
	
//	if($("#accessId").val()){
//		$("#validateTime").on("mouseout", validateTime);//鼠标移出
//		
//		function validateTime(){
//			var endTime = $("#validateTime").val();
//			var startTime = $("#accessTime").val();
////			alert("start:"+startTime+"--end:"+endTime);
//			if (startTime > endTime) { 
//				  alert("授权结束时间需大于开始时间");
//				  return false;
//			}
//		}
//	}
	
	
	// 表单验证
	$("#accessEditForm").Validform({
		btnSubmit : "#btnSave",
		ajaxPost : true,
		tiptype : function(msg, o, cssctl) {
			if (!o.obj.is("form")) {
				layer.tips(msg, '#' + o.obj.attr('id'));

			}
		},
		beforeCheck:function(curform){
			//在表单提交执行验证之前执行的函数，curform参数是当前表单对象。
			//这里明确return false的话将不会继续执行验证操作;  
			if($("#userName").val() == null || $("#userName").val() == ''){
				alert("用户不能为空,请选择用户!");
				return false;
			}
			//授权时间非空验证
			if($("#validateTime").val() == null || $("#validateTime").val() == ''){
				alert("请填写授权结束时间!");
				return false;
			}else{
				var endTime = $("#validateTime").val();
				var startTime = $("#accessTime").val();
				if (startTime > endTime) { 
					  alert("授权结束时间需大于开始时间");
					  return false;
				}
			}
			//验证select里有没有option
			var eduElement = document.getElementById("devSelect");
			var optionElements = eduElement.getElementsByTagName("option");
			if(optionElements.length==0){
				alert("请选择设施");
				return false;
			}
		},
		callback : function(data) {
			var result = eval(data);
			if (result.r = 1) {
				layer.confirm('保存成功，选择下一步操作？', {
					btn : [ '继续编辑', '返回查询列表' ]
				// 按钮
				}, function() {
					layer.msg('进入数据编辑状态...', {
						icon : 1
					});
					$("#accessId").val("");
					$("#devIds").val("");
					$("#userId").val("");
					$("#userName").val("");
					$("#remark").val("");

				}, function() {
					location.href = path + "/facilityaccesslist.htm";
				});
			} else {
				layer.msg(eval(data).r_content, {
					icon : 1
				});
			}
		}
	});
});

function onSave() {
}

function onClose() {
	history.back(-1);
}

function onSearchDev(){
	var content = $("#textSearch").val();
	if(content=="")
		return;
	$.ajax({
		type : "POST",
		url : path + "/queryListByContent.htm?content="+content,
		success : function(r) {
			var table = $("#tableDev");
			$("#tableDev tbody").html("");
			var result = eval(r);
			if (result.r = 1) {
				for(var i=0;i<result.dtList.length;i++){
					table.append($("<tr><td>"
							+result.dtList[i].devCode+"</td><td>"
							+result.dtList[i].devName+"</td><td>"
							+result.dtList[i].devTypeName+"</td><td>"
							+result.dtList[i].devModel+"</td><td><a href='javascript:;' op='choose' did='"
							+result.dtList[i].devId+"' dcd='"
							+result.dtList[i].devCode+"' dna='"
							+result.dtList[i].devName+"' doi='"
							+result.dtList[i].orgId+"' class='tablelink'>选择</a></td></tr>"))
				}
				$("a[op='choose']").on("click", onChooseDev);// 选择设施确认
			} else {
				layer.msg(eval(data).r_content, {
					icon : 1
				});
			}
		}
	});
}

function showUserListForm(){
	layer.open({
		type : 1,
		closeBtn : 2,
		title : [ '选择用户', 'font-size:18px;' ],
		area : [ '600px', '400px' ],
		shadeClose : true, // 点击遮罩关闭
		content : $("#div_userlist"),
	});
}

function showDevListForm() {
	$("#tableDev tbody").html("");
	layer.open({
		type : 1,
		closeBtn : 2,
		title : [ '选择设施', 'font-size:18px;' ],
		area : [ '600px', '400px' ],
		shadeClose : true, // 点击遮罩关闭
		content : $("#div_devlist"),
	});
}

var devicesIds="";
function onChooseDev() {
	var devId = $(this).attr("did");
	var devName = $(this).attr("dna");
	$("#devId").val(devId);
	$("#devName").val(devName);
	var b=true;
	if(devicesIds.length>0){
		var idAry = devicesIds.split(",");
		for(i=0;i<idAry.length;i++){
			if(idAry[i]==devId)
				b=false;
		}
	}
	if(b){
		$("<option></option>").val(devId).text(devName).appendTo($("#devSelect"));
	}
	updateDevIds();
}

function onChooseUser() {
	
	var userId = $(this).attr("dui");
	var userName = $(this).attr("dun");
	$("#userId").val(userId);
	$("#userName").val(userName);
	/*var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
*/	/*parent.layer.close(index);*/
	/*zhouyu 18/1/4*关闭窗口/
	 * 
	 */
	layer.close(layer.index);
}

function onDblClickSelect(){
    var selOpt = $("#devSelect option:selected");  
    selOpt.remove();  
	updateDevIds();
}

function updateDevIds(){
	devicesIds="";
	var i=0;
	var len = $("#devSelect option").length;
	$("#devSelect option").each(function() {
		devicesIds=devicesIds+$(this).val();
		i++;
		if(i!=len)
			devicesIds=devicesIds+",";
	});
	$("#devIds").val(devicesIds);
}
