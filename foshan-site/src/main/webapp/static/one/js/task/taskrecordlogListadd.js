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
	$(".areadevCode").on("change",function(){
		changeArea($(this));
	})
	$(".areadevCode").change();
	$("#inforenterver").attr("disabled", true); //设置为不可编辑
	
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
					$("#userName").val("");
					$("#remark").val("");
					$("#inforenterver").val("");

				}, function() {
					location.href = path + "/queryTaskRecordlogList.htm";
				});
			} else {
				layer.msg(eval(data).r_content, {
					icon : 1
				});
			}
		}
	});
	rwlx();
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
		url : path + "/queryListlogContent.htm?content="+content,
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
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
	parent.layer.close(index);
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
function changeArea(districtObj){

	var  value = $(districtObj).val();
	
	var areas = $(districtObj).parents("div").find("select.hjq");
	
	areas.children("option").remove();
	areas.append($("<option>").prop("value",'').append("==请选择=="));
	$.ajax({
		type:"POST",
		url: path+"/ajax/getAreas.htm?areaRank=4&parentAreaCode="+value,
		dataType:"json",
		success:function(data)
		{
			$.each(data,function(index,value){
				
				 areas.append($("<option>").prop("value",data[index].name).append(data[index].value));
				
			});
		}
	});
	
}
function rwlx(){
	$.ajax({
		  type: "post",
		  url: path+"/selecttasktype.htm",		  
		  cache: false,
		  async: false,
		  dataType: "json", 
		  success: function(data){
		
			  for(var i=0;i<data.length;i++){
				  $("#tasktypecx").append('<option value="'+data[i].valuename+'">'+data[i].valuename+'</option>'); 
			  }
		  }
	});	
	
}
