var tasktypecxdd;
$(function($){
	$('.tablelist tbody tr:odd').addClass('odd');
	$("#addOrUpdate").hide();	
	$("#routeScheme").attr("disabled",true);
	$("#hrefAdd").on('click',addOrUpdate);	//添加按钮
	$("a[op='del']").on('click',deleteData);//删除
	$("a[op='delglan']").on('click',deleteDataglan);
	$("#fh").on('click',fanhui);
	$("#fhglan").on('click',fanhui);//点击返回按钮
	$("#devType").on("change",devtype);
	$("#tasktypecx").on("change",tasktypecxgg);
	$("#btnSimpleQuery").on('click',function(){
		$("#pageNo").val("1");
		$("#searchform")[0].submit();
	});
	
	$(".areadevCode").on('change',function(){
		changeArea($(this));
	});
	$(".areadevCode").change();
	
	$("#terminalinformation").on("change",function() {	
		var  value = $("#terminalinformation").val();	
		$.ajax({
			  type: "post",
			  url: path+"/selectdzh.htm?terminalinformation="+value,
			  cache: false,
			  async: true,
			  dataType: "json", //必须写 响应的json 值
			  success: function(data){
				  $("#routeName").val(data.routeName);
				  if(devType!='其他'){
				  $("#routeScheme").val(data.routeName);
				  }
			  },
              error:function(){
                 alert("该端子不存在光路信息！");
             }
		});
  
		   
	});	
	
	$("#routeName").keyup(function() {
		
		var devType = $("#devType").val();
		if(devType != '其他'){
			$("#routeScheme").val($(this).val());
		}	   
	});	
	var userName = $("#uid").val();
	 tasktypecxdd=$("#tasktypeid").val();
	selectTaskUser();	//查询全部 有任务记录的用户
	$("#userName").val(userName);
	$("#tasktype").on("change",tasktype);
	$("#tasktypecx").on("change",tasktypecx);
	rwlxtj();
	tasktype();//调用任务类型方法
	$("#s2").hide();
	$("#s4").hide();
	rwlx();
	$("#tasktypecx").val(tasktypecxdd);
	tasktypecx();
	
	
})
//任务类型选择
function tasktype(){
	var tasktype = $("#tasktype").val();
	if(tasktype == 'glu'){
		$("#glu").show();
		$("#glan").hide();
	}if(tasktype == 'glan'){
		$("#glan").show();
		$("#glu").hide();
	}
}
//查询任务类型选择
function tasktypecx(){
	
	var tasktypecx = $("#tasktypecx").val();
	if(tasktypecx == 'glu'){
		$("#glxs").show();
		$("#glsj").show();
		$("#glanxs").hide();
		$("#glansj").hide();
	}if(tasktypecx == 'glan'){
		$("#glxs").hide();
		$("#glsj").hide();
		$("#glanxs").show();
		$("#glansj").show();
	}
}
/*返回按钮*/
function fanhui(){
	
	$("#show").show();
	$("#addOrUpdate").hide();
	$("input").attr("disabled",false);
	$("select").attr("disabled",false);
	$("textarea").attr("disabled",false);
	$("#qrbc").show();
}

/*添加方法*/
function addOrUpdate(){
	$(".input").val("");
	$("select").val("");
	$("textarea").val("");
	$("#show").hide();
	$("#addOrUpdate").show();
	$("#taskId").val("");
	$("#showTu").html("");
	$("#csd").hide();
	
	$("#areaCode").val("44130203");
	$("#areadevCode").val("441302");
	
}
/*查找 赋值 显示*/
function find(kid,i){
	
	$.ajax({
		  type: "post",
		  url: path+"/selectByPrimaryKey.htm",
		  data:{
			"taskId":kid  
		  },
		  cache: false,
		  async: true,
		  dataType: "json", //必须写 响应的json 值
		  success: function(data){
			  $("#areadevCode").val(data.areaCode);
			  $("#areaCode").val(data.areaCode1);
			  $("#devType").val(data.devType);
			  $("#routeName").val(data.routeName);
		  	  $("#routeScheme").val(data.routeScheme);
		  	  $("#lebt").val(data.lebt);
		  	  $("#textRouting").val(data.textRouting);
		  	  $("#taskId").val(data.taskId);		  	  
		  	  $("#lightPathType").val(data.lightPathType);
		  	  $("#remark").val(data.remark);
		  	  $("#facilityterminal").val(data.facilityterminal);
		  	  $("#terminalinformation").val(data.terminalinformation);
		  	  $("#transmissionsystem").val(data.transmissionsystem);
		  	  $("#resourcetextrouting").val(data.resourcetextrouting);
		  	  $("#imgUrl").val(data.imgUrl);
		  	  
		  	  
		  	if(i=="find"){
		  		$("input").attr("disabled",true);
		  		$("textarea").attr("disabled",true);
		  		$("select").attr("disabled",true);
		  		$("#fh").attr("disabled",false);
		  		$("#qrbc").hide();
		  	}else{
		  		devtype();
		  		$("#tasktype").attr("disabled",true);
		  	}		  		  	
		  	$("#showTu").html("");
		  	$("#showTu").html("<img width=\"300px\" height=\"150px\" alt=\"图片\" src=\""+path+data.imgUrl+"\"/>");
		  	$("#show").hide();
			$("#addOrUpdate").show();
		  }
	});
}
/*查找 赋值 显示光缆*/
function findglan(kid,i){
	$("#tasktype").val(tasktypecxdd);
	$.ajax({
		  type: "post",
		  url: path+"/selectByPrimaryKeyglan.htm",
		  data:{
			"cableId":kid  
		  },
		  cache: false,
		  async: true,
		  dataType: "json", //必须写 响应的json 值
		  success: function(data){
			  $("#areadevCode").val(data.areaCode);
			  $("#areaCode").val(data.areaCode1);	
		  	  $("#cableId").val(data.cableId);		  	  
		  	  $("#remarkglan").val(data.remarkglan);
		  	  $("#fiberopticcablename").val(data.fiberopticcablename);
		  	  $("#finesome").val(data.finesome);
		  	  $("#contentmodification").val(data.contentmodification);

		  	if(i=="find"){
		  		$("input").attr("disabled",true);
		  		$("textarea").attr("disabled",true);
		  		$("select").attr("disabled",true);
		  		$("#fhglan").attr("disabled",false);
		  		$("#qrbcglan").hide();
		  	}else{
		  		$("#tasktype").attr("disabled",true);
		  		$("#qrbcglan").show();
		  	}
		  	
		  	$("#show").hide();
			$("#addOrUpdate").show();
			$("#glan").show();
			$("#glu").hide();
		  }
	});
}

/*预览本地图片*/
function getPhoto(node) {  
    var imgURL = "";  
    try{  
        var file = null;       
        if(node.files && node.files[0] ){  
            file = node.files[0];  
        }else if(node.files && node.files.item(0)) {
        	
            file = node.files.item(0);  
        }  
        
        try{  
            imgURL =  file.getAsDataURL();  
        }catch(e){  
            imgURL = window.URL.createObjectURL(file);  
        }  
    }catch(e){      	
        if (node.files && node.files[0]) {  
            var reader = new FileReader();  
            reader.onload = function (e) {  
                imgURL = e.target.result;  
            };  
            reader.readAsDataURL(node.files[0]);  
        }  
    }      
    $("#showTu").html("<img width=\"300px\" height=\"150px\" alt=\"图片\" src=\""+imgURL+"\"/>");  
     
}  

/*根据设备类型显示 传输段*/
function csdShow(devType){
	
	if(devType == 'PTN' || devType == 'PON'){
		$("#csd").show();
	}else{
		$("#csd").hide();
	}
}
/*换设备类型触发*/
function devtype(){
	var devType = $("#devType").val();
	csdShow(devType);
	
	if(devType == '其他'){
		$("#routeScheme").attr("disabled",false);
	}else{
		$("#routeScheme").attr("disabled",true);
		$("#routeScheme").val($("#routeName").val());
	}
}

function rwlx(){
	var arrayrwjl = new Array("glu","glan");
	for(var i=0;i<arrayrwjl.length;i++){
		if(i==0){
		  $("#tasktypecx").append('<option value="'+arrayrwjl[i]+'">光路任务</option>'); 
		}
		if(i==1){
			  $("#tasktypecx").append('<option value="'+arrayrwjl[i]+'">光缆任务</option>'); 
			}
	  }
	
}
function rwlxtj(){
	var arrayrwjl = new Array("glu","glan");
	for(var i=0;i<arrayrwjl.length;i++){
		if(i==0){
		  $("#tasktype").append('<option value="'+arrayrwjl[i]+'">光路任务</option>'); 
		}
		if(i==1){
			  $("#tasktype").append('<option value="'+arrayrwjl[i]+'">光缆任务</option>'); 
			}
	  }
	
}
/*查询全部 有任务记录的用户*/
function selectTaskUser(){
	$.ajax({
		  type: "post",
		  url: path+"/selectTaskUser.htm",		  
		  cache: false,
		  async: false,
		  dataType: "json", 
		  success: function(data){
			  
			  for(var i=0;i<data.length;i++){
				  $("#userName").append('<option value="'+data[i].userId+'">'+data[i].userName+'</option>'); 
			  }
		  }
	});	
	
}

/*提交非空 验证*/
function checkValueglu(){
    		var routeName = $("#routeName").val();
    		var routeScheme = $("#routeScheme").val();
    		var areadevCode = $("#areadevCode").val();
    		var areaCode = $("#areaCode").val();
    		var lightPathType = $("#lightPathType").val();
    		var facilityterminal= $("#facilityterminal").val();
    		var resourcetextrouting=$("#resourcetextrouting").val();
    		var Terminalinformation=$("#Terminalinformation").val();
    		var lebt=$("#lebt").val();
    		var transmissionsystem=$("#transmissionsystem").val();
    		if(areadevCode == null || areadevCode == ""){
    			alert("请选择所属区域！");
    			return false;
    		}
    		if(areaCode == null || areaCode == ""){
    			alert("请选择汇聚区！");
    			return false;
    		}
    		if(routeName == null || routeName == ""){
    			alert("请输入光路名称");
    			return false;
    		}
    		if(routeScheme == null || routeScheme == ""){
    			alert("请输入光路方案！");
    			return false;
    		}
    		if(lightPathType == null || lightPathType == ""){
    			alert("请输选择光路类型！");
    			return false;
    		} 
    		if(facilityterminal == null || facilityterminal == ""){
    			alert("请输设备端口号！");
    			return false;
    		}
    		if(resourcetextrouting == null || resourcetextrouting == ""){
    			alert("请输入资源文本路由！");
    			return false;
    		}
    		if(terminalinformation == null || terminalinformation == ""){
    			alert("请输入端子信息！");
    			return false;
    		}
    		if(transmissionsystem == null || transmissionsystem == ""){
    			alert("请输入传输系统！");
    			return false;
    		}
    		$("#routeScheme").attr("disabled",false);
    		$("#tasktype").removeAttr("disabled",true);
    		$("#form").submit();
}
function checkValueglan(){
	var fiberopticcablename = $("#fiberopticcablename").val();
	var finesome = $("#finesome").val();
	var areadevCode = $("#areadevCode").val();

	var areaCode = $("#areaCode").val();
	var contentmodification = $("#contentmodification").val();
	var patrn = /^(-)?\d+(\.\d+)?$/;
	if(areadevCode == null || areadevCode == ""){
		alert("请选择所属区域！");
		return false;
	}
	if(areaCode == null || areaCode == ""){
		alert("请选择汇聚区！");
		return false;
	}
	if(fiberopticcablename == null || fiberopticcablename == ""){
		alert("请输入光缆名称");
		return false;
	}
	if(finesome == null || finesome == ""){
		alert("请输入纤芯数！");
		return false;
	}
	if(contentmodification == null || contentmodification == ""){
		alert("请输入修改内容！");
		return false;
	} 
	if (patrn.exec(finesome) == null || finesome == "") {
    	alert("纤芯数只允许输入数字！");
        return false
    }
	$("#tasktype").removeAttr("disabled",true);
	$("#form").submit();
}

/*删除*/
function deleteData(){
	
	var dt=$(this).attr("dt");	
	var did=$(this).attr("did");
	
	//询问框
	layer.confirm('确认要删除"'+dt+'"？', { btn: ['确定','取消']  },  
		function(){	
			window.location.href = path+'/updateFlag.htm?taskId='+did;			   
	} , null);
}
function deleteDataglan(){
var value= tasktypecxdd;
	var dt=$(this).attr("dt");	
	var did=$(this).attr("did");
	
	//询问框
	layer.confirm('确认要删除"'+dt+'"？', { btn: ['确定','取消']  },  
		function(){	
			window.location.href = path+'/updateFlagglan.htm?cableId='+did+'&tasktype='+value;			   
	} , null);
}

/* 分页相关 */
function pageQuery(){
	$("#pageNo").val($(this).attr("pages"));
	
	$("#searchform")[0].submit();
}
function tasktypecxgg(){
	
	
	$("#searchform")[0].submit();
}

/*汇聚区*/
function changeArea(districtObj){
	var areaCode1=$("#areaCode1").val();
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
				 if(areaCode1==value.name)
				{	 //回显
					areas.append($("<option>").prop("value",data[index].name).prop("selected",true)
					.append(data[index].value));
					
					areas.append($("<option>").prop("value",data[index].name).append(data[index].value));
					//删除重复数据
					var obj=document.getElementById("areaCode");
 					//index,要删除选项的序号，这里取当前选中选项的序号
 					var index=obj.selectedIndex;
 					obj.options.remove(index+1); 
				}else{
				 areas.append($("<option>").prop("value",data[index].name).append(data[index].value));
				}
			});
		}
	});
}

