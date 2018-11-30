/**
 * 智能锁管理js
 */
$(function($) {
	
	
	//设施A下拉列表初始化
	$("#selectdevId").tinyselect({ dataUrl: path+"/json/facility/list.htm" , dataParser: dataParserA });

	//设施A选中设置id
	$("#selectdevId").on("change",function() {
		
		$("#devId").val($(this).val());
	});
	
	//$("#btnSave").on("click", onSave);
	$("#btnClose").on("click", onClose);
	$("#hrefAddDev").on("click", showDevListForm);// 显示选择设施列表
	$("a[op='choose']").on("click", onChooseDev);// 选择设施确认
	
	// 表单验证
	$("#lockregEditForm").Validform({
		btnSubmit : "#btnSave",
		ajaxPost : true,
		tiptype : function(msg, o, cssctl) {
			if (!o.obj.is("form")) {
				layer.tips(msg, '#' + o.obj.attr('id'));

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
					$("#regId").val("");
					$("#devId").val("");
					$("#devImei").val("");
					$("#devMac").val("");
					$("#devName").val("");
					$("#devCode").val("");
					$("#orgId").val("");
				}, function() {
					location.href = path + "/LockList.htm";
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
	$.ajax({
		type : "POST",
		url : path + "/saveLockReg.htm",
		data : {
			regId : $("#regId").val(),
			devId : $("#devId").val(),
			validateSign : $("#validateSign").val(),
			devImei : $("#devImei").val(),
			devMac : $("#devMac").val(),
			devName : $("#devName").val(),
			devCode : $("#devCode").val(),
			orgId : $("#orgId").val()
		},
		dataType : "json",
		success : function(data) {
			$("#regId").val("");
			$("#devId").val("");
			$("#devImei").val("");
			$("#devMac").val("");
			$("#devName").val("");
			$("#devCode").val("");
			$("#orgId").val("");
		}
	});

	
}

function onClose() {
	history.back(-1);
}

function showDevListForm() {
	layer.open({
		type : 1,
		closeBtn : 2,
		title : [ '选择设施', 'font-size:18px;' ],
		area : [ '600px', '400px' ],
		shadeClose : true, // 点击遮罩关闭
		content : $("#div_devlist"),
	});
}

function onChooseDev() {
	var devId = $(this).attr("did");
	var devCode = $(this).attr("dcd");
	var devName = $(this).attr("dna");
	var devOrgId = $(this).attr("doi");
	$("#devId").val(devId);
	$("#devCode").val(devCode);
	$("#devName").val(devName);
	$("#orgId").val(devOrgId);
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
	parent.layer.close(index);
}
/**
 * 绑定带查询的下拉列表框
 */
function dataParserA(data, selected) {
	retval = [ { val: "-1" , text: "---" } ];

	data.forEach(function(v){
		if(selected == "-1" && v.val == 3)
			v.selected = true;
		retval.push(v); 
	});

	return retval;
}