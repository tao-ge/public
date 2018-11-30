<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String rc = request.getContextPath();
	request.setAttribute("path", rc);
%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10" />
<meta http-equiv="x-ua-compatible" content="IE=10">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${path}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path }/js/jquery.js"></script>
<script type="text/javascript" src="${path }/js/layer.js"></script>
<script type="text/javascript">
	path = '${path }';
</script>
<script type="text/javascript"
	src="${path }/js/facility/facilityList.js"></script>
<script type="text/javascript" src="${path }/js/prompt/ymPrompt.js"></script>
<link rel="stylesheet" type="text/css"
	href="${path}/js/prompt/skin/ymPrompt.css" />
	
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=0a3ma3VXzWL0k8a0TgZ3AjllNDmtgFX5"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/library/CurveLine/1.5/src/CurveLine.min.js"></script>
	<style type="text/css">
html,body{
			width:100%;
			height:100%;
			margin:0;
			overflow:hidden;
			font-family:"微软雅黑";
		}
	</style>
	
	
<title>点位管理</title>

</head>
<body>
	<!-- 页面导航  -->
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">资源管理</a></li>
			<li><a href="#">管道管理</a></li>
		</ul>
	</div>

	<div class="rightinfo">
		<!-- 操作按钮：新增、导入、导出、查询  -->
		<div class="tools">
			<ul class="toolbar">
				<li class="click"><a id="facilityImport" href="javascript:;"><span><img
							src="${path}/images/t02.png" /></span>导入</a></li>

			</ul>
		</div>
	</div>

	<!-- 导入Excel -->
	<div id="div_import" title="导入管道" class="newlayer"
		style="display: none">
		<form action="${path}/importPipeline.htm"
			enctype="multipart/form-data" method="post" id="importForm"
			target="importFrame">
			<table class="table1">
				<tr>
					<td>选择设施数据文件：</td>
					<td><input type="file" name="importExcel" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="submit">导入</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div style="display: none;">
		<iframe id="importFrame" name="importFrame"></iframe>
	</div>
<input type="hidden" id="baiduX" value="${fixedPoint.baiduX}"/>
	
		
		<input type="hidden" id="baiduY" value="${fixedPoint.baiduY}"/>
		<div style="width:100%;height:100%;border:1px solid gray" id="container"></div>
</body>

</html>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("container");
	var baiduX=$("#baiduX").val();
	var baiduY=$("#baiduY").val();
	map.centerAndZoom(new BMap.Point(baiduX, baiduY), 15);
	map.enableScrollWheelZoom();
	
	$.ajax({
		type:"post",
		url:path+"/findPipeline.htm",
		dataType:"json",
		success:function(data){
			for (var i = 0; i < data.length; i++) {
				var pipelines=[];
				var a =new BMap.Point(data[i].abaiduX,data[i].abaiduY);
				var b =new BMap.Point(data[i].zbaiduX,data[i].zbaiduY);
				pipelines=[a,b];
				
				var line = new BMap.Polyline(pipelines, {strokeColor:"blue", strokeWeight:3, strokeOpacity:0.5 }); 
				//var curve = new BMapLib.CurveLine(pipelines, {strokeColor:"blue", strokeWeight:3, strokeOpacity:0.5}); //创建弧线对象
				map.addOverlay(line); //添加到地图中
				//curve.enableEditing(); //开启编辑功能
			}  
			
		}
	})

	
</script>
