<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String rc = request.getContextPath();
	request.setAttribute("path", rc);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
	table tr:nth-child(odd){background:#F0F8FF;}
	table td:nth-child(even)
</style>
<link href="${path}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/select.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/tinyselect.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path }/js/jquery.js"></script>
<script type="text/javascript" src="${path}/js/select-ui.min.js"></script>
<script type="text/javascript" src="${path}/js/tinyselect.js"></script>
<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${path}/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="${path }/js/layer.js"></script>
<script type="text/javascript">
	path = '${path }';
	$(function(){
		$('.tablelist tbody tr:odd').addClass('odd');
	})
</script>
<script type="text/javascript" >
/**
 *远程开锁高级查询js
 */
$(function($) {
	$("#btnSerchEx").on('click',showSearchMoreForm);//显示高级查询事件
	//高级查询确认
	$("#btnSearchMore").on('click',function(){
		var g ={
				devName : $('#mdevName').val(),
				validateSign : $('#mvalidateSign').val(),
				devImei : $('#mdevImei').val(),
				devMac : $('#mdevMac').val()
		};
		layer.close(layerId);
		myrefresh(g);
	});
	//查询
	$('#btnSearch').on('click',function(){
// 		添加点击查询弹出层事件
		layer.msg('查询中...', {icon: 1});
		var c = {
				devCode : $('#devCode').val(),
				fName : $('#fName').val(),
				devMac : $('#devMac').val(),
				validateSign : $('#validateSign').val()
		};
		myrefresh(c);
	});
$("#btnClear").on('click',onClear);//高级查询清空
});

var layerId ;
//显示高级查询
function showSearchMoreForm(){
	layerId=layer.open(
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
	/* submitQuery(); */
}

/* function onSearchMore(){
	$("#moreSearchForm")[0].submit();
} */
//fl修改清空
function onClear(){
	$(':input','#moreSearchForm')    		
	.val('') 
	.removeAttr('checked') 
	.removeAttr('selected');
	
}
</script>

<title>信息管理系统界面</title>
<script type="text/javascript">
	var path = "${path}";
	$(document).ready(function(e) {

		$(".select3").uedSelect({
			width : 152
		});
	});
</script>
	<script language="JavaScript">
	var  canfresh = true;
		function myrefresh(m)
		{
			if(m!=null){
				var fName = m.fName;
				var validateSign = m.validateSign;
				var devCode = m.devCode;
				var devMac = m.devMac;
			}
			if(m==null){
				var devName = $('#devName').val();
				var validateSign = $('#validateSign').val();
			}
		   if(canfresh)
		   {
			   $.ajax({url: path +"/queryOnlines.htm",
				   data:{"fName":fName,"validateSign":validateSign,"devCode":devCode,"devMac":devMac},
				   success:function(data){
						write(data);
				  	 	$('#pb').html('<div class="message">共<i class="blue">'+data.pb.rows+'</i>条记录，当前显示第&nbsp;<i class="blue">'+data.pb.pageNo+'</i>页 ,共 <i class="blue">'+data.pb.totalPages+'</i>页</div>');
			  	 		
				   },
				   failed:function(data){
// 					   var t = new Toast({message:'开锁发生异常!',context:$('#tbody'),top:300});
// 					   t.show();
					   layer.msg('开锁发生异常!', {icon: 2});
				   }})
			   
		   }
		}
		
		function write(data){
			
// 			  console.log(data);
			  var s = '';
			   　for(var index = 0;index<data.lockList.length;index++)
			   {
// 				   console.log(index);
				   var item  = data.lockList[index];
			       s +=  "<tr>" +
				    		"<td>"+index+1+"</td>"+
				    		"<td>"+item.devCode+"</td>"+
				    		"<td>"+item.devName+"</td>"+
				    		"<td>"+item.devImei+"</td>"+
				    		"<td>"+item.devMac+"</td>"+
				    		"<td>"+(item.validateSign=='0'?'未注册':(item.validateSign=='1'?'已注册':'已激活'))+"</td>"+
				    		'<td><input type="button"  did="'+item.devImei+'"  dt="'+item.validateSign+'"  onclick="unlock(\''+item.devImei+'\')" value="开锁"></td>'
			   			"</tr>"
			   }
			  	 $('#tbody').html(s);
			  	 
			  	var o = '<option id="all" value="">全部</option>' +
			  			'<option id="zero" value="0">未注册</option>' +
			  			'<option id="one" value="1">已注册</option>' +
			  			'<option id="two" value="2">已激活</option>';
				console.log(o);
				$('#validateSign').html(o);
				if(""==data.sign){
					$('#all').attr("selected",true);
				}
				if("0"==data.sign){
					$('#zero').attr("selected",true);
				}
				if("1"==data.sign){
					$('#one').attr("selected",true);
				}
				if("2"==data.sign){
					$('#two').attr("selected",true);
				}
		}
		
		function unlock(did){
			
			var $input  = $("input[did="+did+"]");
			layer.confirm('确认要开锁吗？', {
				  btn: ['确定','取消'] //按钮
				},
			    function(){
					canfresh = false;
					$input.prop("value",'开锁中...');
					$input.prop("disabled",true);
					$.ajax({
						   type: "POST",
						   url: path+"/unlockInstruct.htm?did="+did,
						   success: function(r){
// 							   alert(r);
							   $input.parents("tr").remove();	
							   canfresh = true;
// 							   var t = new Toast({message:r,context:$('#tbody'),top:300});
// 							   t.show();
							   layer.msg(r, {icon: 1});
						   }
						});  
					layer.closeAll('dialog');
				}, null);
			
		}
		myrefresh();
		window.setInterval(myrefresh,10000); //指定10秒刷新一次
	</script>
</head>
<body>

<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="index.htm">首页</a></li>
	    <li><a href="index.htm">智能化设备管理</a></li>
	    <li><a href="remoteUnlock.htm">远程开锁</a></li>
	    </ul>
    </div>	
    <div class="rightinfo">
		<div class="tools">
			<div>
				<form name="searchlistform" id="searchlistform" >
					设施编码：<input name="devCode" id="devCode" type="text" /> 
					设施名称：<input name="fName" id="fName" type="text" /> 
					MAC地址：<input name="devMac" id="devMac" type="text" /> 
					状态： <select name="validateSign" id="validateSign">
						</select>
					<button id="btnSearch" name="btnSearch" type="button" class="submit">查询</button>
<!-- 					<button id="btnSerchEx" name="btnSerchEx" type="button">高级查询</button> -->
					<button id="refresh" name="refresh" type="button" >刷新</button>
				</form>
			</div>
		</div>
		<table class="tablelist">
			<thead>
				<tr>
					<th>编号<i class="sort"><img src="images/px.gif" /></i></th>
					<th>设施编码</th>
					<th>设施名称</th>
					<th>锁IMEI</th>
					<th>锁MAC地址</th>
					<th>注册状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="tbody">
				<!--<c:if test="${dids !=''}">
					<c:forEach items="${LockList}" var="f">
						<tr id="remoteUnlockList">
							<td>${f.regId}</td>
							<td>${f.devImei}</td>
							<td>${f.devMac}</td>
							<td>${f.devName}</td>
							<td><c:choose>
									<c:when test="${f.validateSign=='0'}">
										未注册
									</c:when>
									<c:when test="${f.validateSign=='1'}">
										已注册
									</c:when>
									<c:otherwise>
										已激活
	       							</c:otherwise>
								</c:choose></td>
							<td>在线</td>
							<%-- <td><a href="javascript:;" id = "kaisuo" op="del" rid="${f.devImei}" dt="${f.validateSign}"
								class="tablelink" >   开锁</a>
							</td> --%>
							<td>
								<input type="button" id = "kaisuo" op="del" rid="${f.devImei}" dt="${f.validateSign}"
								class="tablelink" value="开锁">
							</td>
						</tr>
	
					</c:forEach>
					
				</c:if>-->
			</tbody>
		</table>

		<div id="pb" class="clear"></div>
		<%-- <jsp:include page="/jsp/one/common/page.jsp" /> --%>

	</div>
    
    <!-- 高级查询 -->
		<div id="div_moreSearch" class="newlayer" style="display: none;">
			<form  id="moreSearchForm" name="moreSearchForm">
				<table width="100%" border="1" class="table1">
					<tr>
						<td>被授权IMEI：</td>
						<td><input id="mdevImei" name="mdevImei" type="text" value="" /></td>
					</tr>
					<tr>
						<td>被授权蓝牙MAC地址：</td>
						<td><input id="mdevMac" name="mdevMac" type="text" value="" /></td>
					</tr>
					<tr>
						<td>设施：</td>
						<td><input id="mdevName" name="mdevName" type="text" value="" /></td>
					</tr>
					<tr>
						<td>状态：</td>
						<td><select name="mvalidateSign" id="mvalidateSign">
								<option value=''>全部</option>
								<option value='0'>未注册</option>
								<option value='1'>已注册</option>
								<option value='2'>已激活</option>
						</select></td>
						<td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<button type="button" name="btnSearchMore" id="btnSearchMore">查询</button>&nbsp;&nbsp;
							<button type="button" name="btnClear" id="btnClear">清空</button></td>
					</tr>
				</table>
			</form>
		</div>
    
    
</body>
<script type="text/javascript"">
// 	添加点击刷新弹出层事件
	$("#refresh").on("click",function(){
		layer.msg('刷新...', {icon: 1});
		myrefresh();
	})
</script>
</html>