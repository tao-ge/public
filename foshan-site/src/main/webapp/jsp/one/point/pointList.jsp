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

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<style type="text/css">
html, body {
	margin: 0;
	width: 100%;
	height: 100%;
	background: #ffffff;
}


#map {
	width: 100%;
	height: 100%;
}

#panel {
	position: absolute;
	top: 30px;
	left: 10px;
	z-index: 999;
	color: #fff;
}

#login {
	position: absolute;
	width: 300px;
	height: 40px;
	left: 50%;
	top: 50%;
	margin: -40px 0 0 -150px;
}

#login input[type=password] {
	width: 200px;
	height: 30px;
	padding: 3px;
	line-height: 30px;
	border: 1px solid #000;
}

#login input[type=submit] {
	width: 80px;
	height: 38px;
	display: inline-block;
	line-height: 38px;
}
</style>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=0a3ma3VXzWL0k8a0TgZ3AjllNDmtgFX5"></script>

<title>点位管理</title>

</head>
<body>
	<!-- 页面导航  -->
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">资源管理</a></li>
			<li><a href="#">点位管理</a></li>
		</ul>
	</div>

	<div class="rightinfo">
		<!-- 操作按钮：新增、导入、导出、查询  -->
		<div class="tools">
			<ul class="toolbar">
				<li class="click"><a id="facilityImport" href="javascript:;"><span><img
							src="${path}/images/t02.png" /></span>导入</a></li>
				<li class="click"><a id="changePoints" href="javascript:;"><span><img
							src="${path}/images/t02.png" /></span>转化</a></li>	
<!-- 				<li class="click"><a id="insertPoint" href="javascript:;"><span><img -->
<%-- 							src="${path}/images/t02.png" /></span>导入点位</a></li>	 --%>
			</ul>
		</div>
	</div>

	<div id="div_import" title="导入点位" class="newlayer"
		style="display: none">
		<form action="${path}/importPoint.htm" enctype="multipart/form-data"
			method="post" id="importForm" target="importFrame">
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

	<div id="map"></div>
	<script type="text/javascript">
		
		
		
		
		
	// 显示多有点位
	var map = new BMap.Map("map", {}); // 创建Map实例
	var baiduX=$("#baiduX").val();
	var baiduY=$("#baiduY").val();
	map.centerAndZoom(new BMap.Point(baiduX, baiduY), 15); // 初始化地图,设置中心点坐标和地图级别
	map.enableScrollWheelZoom(); //启用滚轮放大缩小
		
		$(function($){
			$("#changePoints").on('click',changePoints); //转化点位
			$("#insertPoint").on('click',insertPoint); //插入点位
			
			
			if (document.createElement('canvas').getContext) { // 判断当前浏览器是否支持绘制海量点
				var points = []; // 添加海量点数据
					$.ajax({
						type:"post",
						url:path+"/showPoints.htm",
						dataType:"json",
						success:function(data){
								for (var i = 0; i < data.length; i++) {
									//points.push(new BMap.Point(data[i].baiduX, data[i].baiduY));
									var point = new BMap.Point(data[i].baiduX,data[i].baiduY);
							
									var marker = new BMap.Marker(point,{
										  // 初始化圆形
												icon: new BMap.Symbol(BMap_Symbol_SHAPE_CIRCLE, {
											    scale: 4,
											    strokeWeight: 0.6,
											    rotation: 90,
											    fillColor: 'red',
											    fillOpacity: 0.8
												})
										  });
									var removeMarker = function(e,ee,marker){
										map.removeOverlay(marker);
									}
									var markerMenu=new BMap.ContextMenu();
									markerMenu.addItem(new BMap.MenuItem('删除',removeMarker.bind(marker)));
									var label = new BMap.Label(data[i].pointName,{offset:new BMap.Size(20,-10)});
									label.setTitle(data[i].pointName);
									label.setStyle({display:'none'});
									marker.setLabel(label);
									map.addOverlay(marker);
									marker.addContextMenu(markerMenu);
									marker.addEventListener("click", function(e){   
										var l = e.target.getLabel();
										var opts = {
												  width : 200,     // 信息窗口宽度
												  height: 100,     // 信息窗口高度
												  title : l.getTitle(), // 信息窗口标题
												  enableMessage:false,//设置允许信息窗发送短息
												}
										var infoWindow = new BMap.InfoWindow("经度："+e.point.lng+"     纬度："+e.point.lat, opts);  // 创建信息窗口对象 
										map.openInfoWindow(infoWindow, e.point); //开启信息窗口
									});
									
									
								}  
								/* var options = {
									size : BMAP_POINT_SIZE_SMALL,
									shape : BMAP_POINT_SHAPE_STAR,
									color : '#d340c3'
								} */
								
								
						}	
					});
			};
			
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
						var line = new BMap.Polyline(pipelines, {strokeColor:"blue", strokeWeight:2, strokeOpacity:0.5 }); //创建折线对象
						//line.setTitle(data.pipelineName);
						//var curve = new BMapLib.CurveLine(pipelines, {strokeColor:"blue", strokeWeight:3, strokeOpacity:0.5}); //创建弧线对象
						map.addOverlay(line); //添加到地图中
						//curve.enableEditing(); //开启编辑功能
					}  
					
				}
			});
		});
		
		
		function changePoints()
    	{
    		$.ajax({
    			type:"post",
				url:path+"/changePoints.htm",
				dataType:"json",
				success:function(data){
					if(data=="true"){
						alert("已经全部转化成百度坐标");
					}else{
						alert("部分数据异常，请检查！");
					}
				}
    		});
    		
    	}
		
		function insertPoint(){
			$.ajax({
    			type:"post",
				url:path+"/insertPoint.htm",
				dataType:"json",
				success:function(data){
					if(data=="yes"){
						alert("导入成功");
					}else{
						alert("导入失败");
					}
				}
    		});
		}
	</script>
	
</body>

</html>
