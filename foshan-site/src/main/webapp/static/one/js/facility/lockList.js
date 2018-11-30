/**
 *智能锁管理js
 */
$(function($) {
	$("#btnSerchEx").on('click',showSearchMoreForm);//显示高级查询事件
	$("#hrefAdd").on("click",showAddForm);//显示新建窗口
	$("a[op='edit']").on("click",showEditForm);//显示修改窗口
	$("a[op='redeploying']").on("click",redeploying);//加载布防撤防
	$("a[op='del']").on("click",deleteData);//加载删除事件
	$("a[op='import']").on('click',showImportForm);//import excel
	$("#btnSearch").on('click',submitQuery);//查询确认
	$("#btnSearchMore").on('click',onSearchMore);//高级查询确认
	$("#btnClear").on('click',onClear);//高级查询清空
	$("#yanzheng").on('click',yanzheng);//高级查询清空
});

function redeploying()
{
	var tid=$(this).attr("tid");
	var cz = tid == '0'?"布防":"撤防";
	var dt=$(this).attr("dt");
	var regId = $(this).attr("rid");
	var did =  tid == '0'?"1":"0";
	var pal = $(this).attr("pal");
	var imei = $(this).attr("imei");
	alert(regId+"regId");
	alert(imei+"imei");
	//询问框
	layer.confirm('确认要对'+dt+'该设施'+cz+'？', {
	  btn: ['确定','取消'] //按钮
	}, function(){
		layer.closeAll('dialog');
		var url = path+"/updateRedeploying.htm";
		var data ={tid:did,regId:regId,pal:pal,imei:imei};
		$.post(url,data,function(r){
					layer.confirm(r.r_content, {
						  btn: ['确定'] //按钮
					},function(){
						submitQuery();
					});
		},"json");
	   
	}, null);
}



//提交查询表单
function submitQuery()
{
	$("#searchlistform")[0].submit();
}
/*
 * 选择程序文件
 */
function showImportForm()
{
	layer.open(
		{
			type: 1,
			closeBtn: 2,
			title: ['选择文件', 'font-size:18px;'],
			area: ['400px', '200px'],
			shadeClose: true,
			content:$("#div_import")
 		}
	);
}
function yanzheng(){
	//o.value就是你选择的文件的完整路径,然后你可以自己过滤  
	if($("#importExcel").val()==''){
		alert("请选择文件！");
		return false;
	}else{
	    if ($("#importExcel").val().indexOf('.bin') == -1) {  
			 alert("文件类型错误！")
			 return false;
	    }else{
	    	layer.msg('正在升级，请稍后...', {
	            icon: 16,
	            time: 55000 //2秒关闭（如果不配置，默认是3秒）
	          }, function(){
	              setTimeout(function(){
	            	  	layer.msg('升级成功！', {icon: 1,time:3000});
	            	  },
	            	  150);
	          }); 
	    }  
	}
}

//显示高级查询
function showSearchMoreForm(){
	layer.open(
			{
				type: 1,
				closeBtn: 2,
				title: ['高级查询', 'font-size:18px;'],
				area: ['600px', '400px'],
				shadeClose: true, //点击遮罩关闭
				content:$("#div_moreSearch"),
	 		}
	);
}

//分页浏览查询
function pageQuery(){
	$("#pageNo").val($(this).attr("pages"));
	submitQuery();
}

//显示新建窗口
function showAddForm() {
	var url = path + "/goLockAdd.htm"
	location.href =path + "/goLockAdd.htm";
	/*var index = layer.open({
		type : 2,
		content : url,
		area : [ '800px', '480px' ],
		maxmin : true
	});*/
}

//显示修改窗口
function showEditForm() {
	var regId = $(this).attr("rid");
	//alert(regId);
	var url = path + "/goLockEdit.htm?regId="+regId;
	location.href =url;
}


//删除
function deleteData()
{
	var dt=$(this).attr("dt");
	var regId = $(this).attr("rid");
	//询问框
	layer.confirm('确认要删除"'+dt+'"？', {
	  btn: ['确定','取消'] //按钮
	}, function(){
		$.ajax({
			
			   type: "POST",
			   url: path+"/delLockReg.htm?regId="+regId,
			   success: function(r){
				   layer.msg(eval(r).r_content, {icon: 1});
				   submitQuery();
			   }
			});   			
	   
	}, null);
}


function onSearchMore(){
	$("#moreSearchForm")[0].submit();
}

function onClear(){
	$("#devImei").val("");
	$("#devMac").val("");
	$("#devName").val("");
}

