/**
 * 装维添加远程开锁申请js
 * @param $
 * @returns
 */
$(function($) {
	$("#btnClose").on("click", onClose);
	$("#btnSearch").on("click", onSearchDev);
	$("#hrefAddUser").on("click", showUserListForm);// 显示用户列表
	$("#hrefAddDev").on("click", showDevListForm);// 显示选择设施列表
	$("a[op='choose']").on("click", onChooseDev);// 选择设施确认
	$("a[op='select']").on("click", onChooseUser);// 选择用户确认
	$("#devSelect").on("dblclick", onDblClickSelect);// 双击设施列表
//	$("#remark").on("keyup",onValidate);//验证字数
	$(".areadevCode").on("change",function(){
		changeArea($(this));
	})
//	$(".areadevCode").onChange();
	// 表单验证
	$("#unlockApplyForm").Validform({
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
//			var req = 1;
//			$.ajax({
//				type : "POST",
//				async : false,
//				///验证该设施是否已经申请并在其申请时间内
//				url : path + "/validateRemoteUnlock.htm?devId="+$("#devId").val(),
//				success : function(r) {
//					if(r.r==0){
//						req = r.r;
//					}
//				}
//			});
//			if(req == 0){
//				alert("该设施已经申请远程开锁");
//				return false;
//			}
			
			//验证开始时间与结束时间非空
			var startTime = $("#startTime").val();
			var endTime = $("#endTime").val();
			if(startTime == null || startTime == ''){
				alert("请填写开始时间");
				return false;
			}
			if(endTime == null || endTime == ''){
				alert("请填写结束时间");
				return false;
			}
			
			//验证结束时间大于开始时间
			var start = new Date(Date.parse(startTime));
			var end = new Date(Date.parse(endTime));
			if(start>end){
				alert("结束时间需大于开始时间");
				return false;
			}
			
			if($("#applyUserName").val() == null || $("#applyUserName").val() == ''){
				alert("请选择申请人!");
				return false;
			}
//			//验证select里有没有option
//			var eduElement = document.getElementById("devSelect");
//			var optionElements = eduElement.getElementsByTagName("option");
//			if(optionElements.length==0){
//				alert("请选择设施");
//				return false;
//			}
			//验证是否选择设施
			var element = document.getElementById("devName");
			if(element.value==null || element.value==''){
				alert("请选择设施！");
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
					$("#applyUserName").val("");
					$("#applyTime").val("");
					$("#remark").val("");
				}, function() {
					location.href = path + "/remoteUnlockList.htm";
				});
			} else {
				layer.msg(eval(data).r_content, {
					icon : 1
				});
			}
		}
	});
});

function changeArea(districtObj){
	var areaCode2=$("#code").val();
	var  value = $(districtObj).val();
	
	$("#areaCode2").html("");
	$("#areaCode2").append("<option value=''>==请选择==</option>");
	$.ajax({
		type:"POST",
		url: path+"/ajax/getAreas.htm?areaRank=4&parentAreaCode="+value,
		dataType:"json",
		success:function(data)
		{
			$.each(data,function(index,value){
				 if(areaCode2==value.name)
				{
					 $("#areaCode2").append("<option value='"+data[index].name+"'' selected>"+data[index].value+"</option>");
				}else{
					//$("#areaCode2").append("<option value='"+data[index].name+"''>"+data[index].value+"</option>");
					$("#areaCode2").append($("<option>").prop("value",data[index].name).append(data[index].value));
				}
			});
		}
	});
	
	
}


//验证字数
function onValidate(){
	var length = $("#remark").val().length;
	if(length<500){
		var c = 500-length;
		$("#in").text("还可输入"+c+"字");
	}
}

function onClose() {
	history.back(-1);
}

function onSearchDev(){
	var content = $("#textSearch").val();
	
	var areaCode1 = $("#areaCode1").val();
	var areaCode2 = $("#areaCode2").val();

//	alert($("#code").val());
	$.ajax({
		type : "POST",
		url : path + "/queryListByContentAndBindCod.htm?content="+content+"&areaCode2="+areaCode2+"&areaCode1="+areaCode1,
		success : function(r) {
			var table = $("#tableDev");
			$("#tableDev tbody").html("");
			var result = eval(r);
			if (result.r = 1) {
				for(var i=0;i<result.dtList.length;i++){
					table.append($("<tr><td>"
							+result.dtList[i].devCode+"</td><td>"
							+result.dtList[i].devName+"</td><td>"
							+result.dtList[i].devTypeName+"</td>"
							+"<td><a href='javascript:;' op='choose' did='"
							+result.dtList[i].devId+"' dcd='"
							+result.dtList[i].devCode+"' dna='"
							+result.dtList[i].devName+"' doi='"
							+result.dtList[i].orgId+"' class='tablelink'>选择</a></td></tr>"))
				}
				$('.tablelist tbody tr:odd').addClass('odd');
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
var index=0;
function showDevListForm() {
	$("#tableDev tbody").html("");
	index=layer.open({
		type : 1,
		closeBtn : 2,
		title : [ '选择设施', 'font-size:18px;' ],
		area : [ '800px', '400px' ],
		shadeClose : true, // 点击遮罩关闭
		content : $("#div_devlist"),
	});
}

var devicesIds="";
function onChooseDev() {
	layer.close(index);
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
	$("#applyUser").val(userId);
	$("#applyUserName").val(userName);
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
