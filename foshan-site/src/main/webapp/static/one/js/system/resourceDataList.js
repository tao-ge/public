/**
 * 资管数据管理
 */
var areaCodeh;
$(function($){
	var areaCodes1=document.getElementById("areaCodes1").value;
	areaCodeh=document.getElementById("areaCodeh").value;
	$("#areaCodes").val(areaCodes1);
	$("a[op='reGenerator']").on("click",reGenerator);//重新生成点击事件
	$("#btnReGeneratorSave").on("click",reGeneratorSave);//重新生成点击保存
//	$("#goGenerator").on("click",goGenerator);//生成数据点击事件
	$("#goGenerator").on("click",importData);//导入数据点击事件
	$(".areaCodes").on("change",function(){
		changeArea($(this));
	})
	$(".areaCodes").change();
	$("#district").on("change",function(){
		changeArea1($(this));
	});
	$(".district").change();
	$("#btnSimpleQuery").on('click',function(){//查询事件
		if($("#areaCode").val() == "" && $("#areaCodes").val() != ""){
			$("#areaq").val($("#areaCodes").val());
			$("#searchlistform")[0].submit();
		}
		var areaCodes1=document.getElementById("areaCodes").value;
		$("#areaCodes1").val(areaCodes1);//存到隐藏域
		$("#searchlistform")[0].submit();
	});
	$('.tablelist tbody tr:odd').addClass('odd');//隔行换色
})

function submitQuery(){
	$("#searchlistform")[0].submit();
}

function changeArea(districtObj){
	var areaCode=areaCodeh;
	var  value = $(districtObj).val();
	
	var areas = $(districtObj).parents("div").find("select.hjq");
	
	areas.children("option").remove();
	areas.append($("<option id='areaq'>").prop("value",'').append("==请选择=="));
	$.ajax({
		type:"POST",
		url: path+"/ajax/getAreas.htm?areaRank=4&parentAreaCode="+value,
		dataType:"json",
		success:function(data)
		{
			$.each(data,function(index,value){
				 if(areaCode==value.name)
				{
					 
					 //回显
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


//分页浏览查询
function pageQuery(){
	$("#pageNo").val($(this).attr("pages"));
	$("#searchlistform")[0].submit();
}

//导入数据点击事件
function importData(){
	layer.open({
	type:1,
	title:'导入数据',
	content:$('#div_area'),
	area:'400px',
	btn:['导入','取消'],
	yes:function(){
		var areaCode3 = $("#areaCode3").val();//所属区
		var district = $("#district").val();//汇聚区
//		alert(areaCode3);
//		alert(district);
		if(areaCode3==null)
		{
//			layer.alert("当前按照所属区导入",{icon:1});
			layer.msg("当前按照所属区导入",{icon:1});
			importResourceDataFile(district);
		}else if(areaCode3==''){
//			layer.alert("当前按照所属区导入",{icon:1});
			layer.msg("当前按照所属区导入",{icon:1});
			importResourceDataFile(district);
		}else{
//			layer.alert("当前按照汇聚区导入",{icon:1});
			layer.msg("当前按照汇聚区导入",{icon:1});
			importResourceDataFile(areaCode3);
		}
	}
})
}

/**
 * 导入文件
 * @returns
 */
function importResourceDataFile(areaCode) {
	var index = layer.load(2);
	var formData = new FormData();
    formData.append("importFile", document.getElementById("deployFile").files[0]);  
	$.ajax({
		   type: "POST",
		   url: path+"/importResourceDataFile.htm?areaCode="+areaCode,
		   /**
            *必须false才会自动加上正确的Content-Type
            */
		   contentType: false,
		   /**
            * 必须false才会避开jQuery对 formdata 的默认处理
            * XMLHttpRequest会对 formdata 进行正确的处理
            */
		   processData: false,
		   data: formData,
		   success: function(r){
			   layer.close(index);
			  if(r.r==1){//导入成功
				  layer.alert(r.r_content,function(index){
					  submitQuery();
				  });
//				  submitQuery();
			  }else if(r.r==2){//文件为空
				  layer.alert(r.r_content,function(index){
					  submitQuery();
				  });
//				  layer.alert(r.r_content,{icon:7});
//				  submitQuery();
			  }else if(r.r==3){//文件类型错误
				  layer.alert(r.r_content,function(index){
					  submitQuery();
				  });
//				  layer.alert(r.r_content,{icon:2});
//				  submitQuery();
			  }else if(r.r==4){//文件过大
				  layer.alert(r.r_content,function(index){
					  submitQuery();
				  });
//				  layer.alert(r.r_content,{icon:7});
//				  submitQuery();
			  }
			  else{
				  layer.alert(r.r_content,function(index){
					  submitQuery();
				  });
//				  layer.alert(r.r_content,{icon:2});
//				  submitQuery();
			  }
		   }
		});   
	
}

//function goGenerator(){
//	layer.open({
//		type:1,
//		title:'生成数据',
//		content:$('#div_area'),
//		area:'250px',
//		btn:['确定','取消'],
//		yes:function(){
//			var areaCode3 = $("#areaCode3").val();
//			var district = $("#district").val();
////			alert(areaCode3 + "="+ district);
//			if(areaCode3==null)
//			{
//				layer.alert("当前按照所属区生成",{icon:1});
//				goGenerData(district);
//			}else if(areaCode3==''){
//				layer.alert(" 系统光路全部重新生成",{icon:1});
//				goGenerData(district);
//			}else{
//				layer.alert("当前按照汇聚区生成",{icon:1});
//				goGenerData(areaCode3);
//			}
//		}
//	})
//}

/**
 * 生成数据
 * @returns
 */
function goGenerData(areaCode) {
	var index = layer.load(2);
	$.ajax({
		   type: "POST",
		   url: path+"/goGenerData.htm?areaCode="+areaCode,
		   success: function(r){
			  layer.close(index);
			   layer.alert(eval(r).dt.areaName+"-->已生成数据!", {icon: 1});
			  if(r.r>0)
				  submitQuery();
		   }
		});   
}

function reGeneratorSave(){
	$("#btnReGeneratorSave").attr("disabled","true");
	$.ajax({
		type : "POST",
		url : path + "/reGeneratorSave.htm",
		data : $("#form_reGenerator").serialize(),
		success : function(r) {
			if(r.r == 1){
				layer.msg(r.dt.areaName+'-->数据生成成功!', {icon: 1});
			}else{
				layer.msg(r.dt.areaName+'-->数据生成失败!', {icon: 1});
			}
			$("#btnReGeneratorSave").removeAttr("disabled");
			submitQuery();
		},
		beforeSend:function(r){					   
			   layer.msg('数据重新生成中...', {icon: 16,time:0,id:'exportLayer'});
		}
	});
}

function reGenerator(){
	var did = $(this).attr("did");
	var ver = $(this).attr("ver");
	$("#version").val(ver);
	$("#resId").val(did);
	layer.open({
		type : 1,
		closeBtn : 2,
		title : [ '重新生成', 'font-size:18px;' ],
		area : [ '600px', '400px' ],
		shadeClose : true, // 点击遮罩关闭
		content : $("#div_reGenerator"),
	});
}

function changeArea1(district){
	var  value = $(district).val();
	$("#areaCode3").children("option").remove();
	$("#areaCode3").append($("<option>").prop("value",'').append("==请选择=="));
	$.ajax({
		type:"POST",
		url: path+"/ajax/getAreas.htm?areaRank=4&parentAreaCode="+value,
		dataType:"json",
		success:function(data)
		{
			$.each(data,function(index,value){
				$("#areaCode3").append($("<option>").prop("value",data[index].name).append(data[index].value));
			});
		}
	});
}