var bj;
$(function($){
	$("#addOrUpdate").hide();
	$("#btnSimpleQuery").on('click',function(){
	
		$("#pageNo").val("1");
		$("#searchform")[0].submit();
	});
	rwlx();
	selectTaskUser();
	var userallottee = $("#uid").val();

	$("#userallottee").val(userallottee);
	 tasktypecxdd=$("#tasktypeid").val();
	 $("#tasktypecx").val(tasktypecxdd);
	 $("#inforentervers").click(function() {
		 bj='zg';
		 displayAlarmCtrl(bj);
		});
	 $("#btnDevAlaCtrlSave").on('click',submitAlarmCtrl);
	 $("#btnDevAlaCtrlSavesh").on('click',submitAlarmCtrlsh);
	 $("#hrefAdd").on("click", showAddForm);// 显示新建窗口
	 $("a[op='see']").on("click", showDevListForm);// 显示设施列表
})

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
function pageQuery(){
	$("#pageNo").val($(this).attr("pages"));
	
	$("#searchform")[0].submit();
}
/*查询除了超级管理员外得所有人*/
function selectTaskUser(){
	$.ajax({
		  type: "post",
		  url: path+"/selectTasklogUser.htm",		  
		  cache: false,
		  async: false,
		  dataType: "json", 
		  success: function(data){
			  
			  for(var i=0;i<data.length;i++){
				  $("#userallottee").append('<option value="'+data[i].userName+'">'+data[i].userName+'</option>'); 
			  }
		  }
	});	
	
}

function displayAlarmCtrl(bj)
{	
if(bj=='sh'){
		
		$("#form_deviceAlarmCtrl").hide();
		$("#form_deviceAlarmCtrlsh").show();
	}
	if(bj=='zg'){
		$("#form_deviceAlarmCtrl").show();
		$("#form_deviceAlarmCtrlsh").hide();
	}
	a1=layer.open(
				{
    				type: 1,
    				closeBtn: 2,
    				title: ['', 'font-size:1px;'],
    				area: ['400px', '300px'],
    				shadeClose: true, //点击遮罩关闭
    				content:$("#div_deviceAlarmCtrl"),
    				
		 		}
		);    				

}
function submitAlarmCtrl(){
	var values=$("#inforentervers").val()
	var value=$("#inforenterverupdate").val();
	if(!isNaN(value)){
		
		}else{
			layer.alert('请输入是数字的版本号！');
			return false;
		}
	if(value<values){
		
		layer.alert('新版本号应大于原始版本号！');
		return false;
	}
	$.ajax({
		  type: "post",
		  url: path+'/updateversion.htm?inforenterver='+value,		  
		  success: function(r){
			  
			   $("#inforentervers").val(r.inforenterver);
			   layer.msg(eval(r).r_content, {icon: 1});
			  
			   layer.close(a1); 
			   
		   }
	
	});
}
function find(kid,i){
	  $("#taskrecordid").val(kid);
	bj='sh';
	if(i=='sh'){
		 displayAlarmCtrl(bj);
	}
}

function submitAlarmCtrlsh(){

	var questlog=$("#questlog").val()
	var finishtimes=$("#finishtime").val() 
	var taskrecordid=$("#taskrecordid").val()
	var sign=$("#sign").val()

	$.ajax({
		  type: "post",
		  url: path+'/updatesh.htm?questlog='+questlog+'&finishtimes='+finishtimes+'&sign='+sign+'&taskrecordid='+taskrecordid,		  
		  success: function(r){
			   layer.msg(eval(r).r_content, {icon: 1});
			   layer.close(a1); 
			   window.location.href = path+'/queryTaskRecordlogList.htm';
		   }
	
	});
}

function showAddForm() {
	location.href = path + "/taskrecordlogadd.htm";
}
function showDevListForm(){
	var ids = $(this).attr("ids");
	$.ajax({
		type : "POST",
		url : path + "/queryListsslb.htm?ids="+ids,
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
function pageQuery(){
	
	$("#pageNo").val($(this).attr("pages"));
	
	$("#searchform")[0].submit();
}