/**
 * 设备历史状态js
 * @param $
 * @returns
 */
$(function($){
    $("#upload").on('click',returnLast);//返回事件加载
});

//提交查询表单
function submitQuery()
{
	$("#simpleQuery")[0].action = path+'/showHistoryStatus.htm';
	$("#simpleQuery")[0].submit();
}

//分页浏览查询
function pageQuery(){
	$("#pageNo").val($(this).attr("pages"));
	submitQuery();
}

//返回
function returnLast(){
	var devId = $("#devId").val();
	var curStatus = $("#curStatus").val();
	var devId = $("#devId").val();
	var codName = $("#codName").val();
	var route = "/queryDeviceAlarmList.htm";
	window.location.href = path+"/queryFacilityState.htm?codName="+codName+"&route="+route+"&aphanic="+"1"+"&devId="+devId+"&curStatus="+curStatus;
//	history.back(-1);
}