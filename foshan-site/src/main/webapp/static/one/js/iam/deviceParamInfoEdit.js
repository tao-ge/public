/**
 * @param $
 * @returns
 */
$(function($) {
	$("#btnClose").on("click", onClose);
	// 表单验证
	$("#addForm").Validform({
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
			var lowTempThd = $("#lowTempThd").val();
			var highTempShd = $("#highTempShd").val();
			if(lowTempThd == null || lowTempThd == ''){
				alert("请填写低温阈值");
				return false;
			}
			if(highTempShd == null || highTempShd == ''){
				alert("请填写高温阈值");
				return false;
			}
			var batteryThd = $("#batteryThd").val();
			if(batteryThd == null || batteryThd == ''){
				alert("请填写电压阈值");
				return false;
			}
			var tilt = $("#tilt").val();
			if(tilt == null || tilt == ''){
				alert("请填写倾斜度阈值");
				return false;
			}
			var dormantTime = $("#dormantTime").val();
			if(dormantTime == null || dormantTime == ''){
				alert("请填写休眠时间");
				return false;
			}
			var selfCheckingTime = $("#selfCheckingTime").val();
			if(selfCheckingTime == null || selfCheckingTime == ''){
				alert("请填写自动间隔时间");
				return false;
			}
			var reportTime = $("#reportTime").val();
			if(reportTime == null || reportTime == ''){
				alert("请填写上报间隔时间");
				return false;
			}
			var lockAbnorTime = $("#lockAbnorTime").val();
			if(lockAbnorTime == null || lockAbnorTime == ''){
				alert("请填写门锁异常报警时间");
				return false;
			}
			var pollTime = $("#pollTime").val();
			if(pollTime == null || pollTime == ''){
				alert("请填写轮询时间");
				return false;
			}
			var ip = $("#ip").val();
			if(ip == null || ip == ''){
				alert("请填写ip");
				return false;
			}
			var port = $("#port").val();
			if(port == null || port == ''){
				alert("请填写端口");
				return false;
			}
			var reg = /-[0-9]+(.[0-9]+)?|[0-9]+(.[0-9]+)?/; //数字
			if (!reg.test(lowTempThd)) {
				alert("低温阈值请填写数字");
				return false;
			}
			if (!reg.test(highTempShd)) {
				alert("高温阈值请填写数字");
				return false;
			}
			if (!reg.test(batteryThd)) {
				alert("电压阈值请填写数字");
				return false;
			}
			if (!reg.test(tilt)) {
				alert("倾斜度阈值请填写数字");
				return false;
			}else if(tilt>180 || tilt<0){
				alert("请填写正确的倾斜度阈值");
				return false;
			}
			if (!reg.test(dormantTime)) {
				alert("休眠时间请填写数字");
				return false;
			}
			if (!reg.test(selfCheckingTime)) {
				alert("自动间隔时间请填写数字");
				return false;
			}
			if (!reg.test(reportTime)) {
				alert("自动上报间隔时间请填写数字");
				return false;
			}
			if (!reg.test(lockAbnorTime)) {
				alert("门锁异常报警间隔时间请填写数字");
				return false;
			}
			if (!reg.test(pollTime)) {
				alert("轮询时间请填写数字");
				return false;
			}
			if (!reg.test(port)) {
				alert("端口请填写数字");
				return false;
			}
			var regip = /^(\d|[1-9]\d|1\d{2}|2[0-5][0-5])\.(\d|[1-9]\d|1\d{2}|2[0-5][0-5])\.(\d|[1-9]\d|1\d{2}|2[0-5][0-5])\.(\d|[1-9]\d|1\d{2}|2[0-5][0-5])$/;
			
			if (!regip.test(ip)) {
				alert("请填写正确的ip");
				return false;
			}
			
			//验证select里有没有option
//			var eduElement = document.getElementById("devSelect");
//			var optionElements = eduElement.getElementsByTagName("option");
//			if(optionElements.length==0){
//				alert("请选择设施");
//				return false;
//			}
		},
		callback : function(data) {
			if (data > 0) {
				layer.confirm('保存成功', {
					btn : ['确定']
				// 按钮
				}, function() {
					layer.msg('进入数据编辑状态...', {
						icon : 1
					});
					location.href = path + "/queryDeviceParamInfo.htm";
				});
			} else {
				layer.msg("操作失败!", {
					icon : 1
				});
			}
		}
	});
});



function onClose() {
	history.back(-1);
}

