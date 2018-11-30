<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String rc = request.getContextPath();
	request.setAttribute("path", rc);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<title>分光器地图</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">

<style type="text/css">
body, html, #map_canvas {
	width: 100%;
	height: 100%;
	overflow: hidden;
	margin: 0;
}

body {
	font-size: 12px;
	color: #000;
	font-family: "宋体";
}

#introduction {
	color: #434343;
	width: 200px;
	top: 130px;
	left: 50px;
	position: absolute;
	z-index: 1;
	border: 1px solid #bcbcbc;
}

.intro-title {
	font-weight: 800;
	background: #eaeaea;
	font-size: 14px;
	line-height: 14px;
	padding: 10px;
}

.intro-title a {
	text-decoration: none;
	background: url(../images/close.gif);
	height: 14px;
	width: 14px;
}

.intro-body {
	padding: 10px 10px 20px;
	background: #fff;
}

#intro-content {
	line-height: 1.8em;
}

.intro-button {
	margin-top: 20px;
	text-align: center;
}

.intro-button a {
	line-height: 33px;
	font-weight: 800;
	color: #fff;
	text-decoration: none;
	display: inline-block;
	background: url(http://developer.baidu.com/map/jsdemo/img/btn.png);
	height: 33px;
	width: 100px;
}

.intro-button a:hover {
	background: url(../images/btn-on.png);
}

.f-r {
	float: right;
}

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow: hidden;
}
/* Overlays */
.ui-widget-overlay {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
}

/* Component containers
		----------------------------------*/
.ui-widget {
	font-family: Verdana, Arial, sans-serif;
	font-size: 1.1em;
}

.ui-widget .ui-widget {
	font-size: 1em;
}

.ui-widget input, .ui-widget select, .ui-widget textarea, .ui-widget button
	{
	font-family: Verdana, Arial, sans-serif;
	font-size: 1em;
}

.ui-widget-content {
	border: 1px solid #aaaaaa;
	background: #ffffff 50% 50% repeat-x;
	color: #222222;
}

.ui-widget-content a {
	color: #222222;
}

.ui-widget-header a {
	color: #222222;
}

/* Interaction states
		----------------------------------*/
.ui-state-default, .ui-widget-content .ui-state-default,
	.ui-widget-header .ui-state-default {
	border: 1px solid #3399FF;
	background: #3399FF 50% 50% repeat-x;
	font-weight: normal;
	color: #5c9dc4;
}

.ui-state-default a, .ui-state-default a:link, .ui-state-default a:visited
	{
	color: #3399FF;
	text-decoration: none;
}

.ui-state-hover a, .ui-state-hover a:hover {
	color: #3399FF;
	text-decoration: none;
}

.ui-state-active, .ui-widget-content .ui-state-active, .ui-widget-header .ui-state-active
	{
	border: 1px solid #3399FF;
	background: #3399FF 50% 50% repeat-x;
	font-weight: normal;
	color: #3399FF;
}

.ui-state-active a, .ui-state-active a:link, .ui-state-active a:visited
	{
	color: #3399FF;
	text-decoration: none;
}

.ui-widget :active {
	outline: none;
}

.ui-slider {
	position: relative;
	text-align: left;
}

.ui-slider .ui-slider-handle {
	position: absolute;
	z-index: 2;
	width: 12px;
	height: 12px;
	cursor: e-resize;
}

.ui-slider .ui-slider-range {
	position: absolute;
	z-index: 1;
	font-size: .7em;
	display: block;
	border: 0;
	background-position: 0 0;
}

.ui-slider-horizontal {
	height: .8em;
}

.ui-slider-horizontal .ui-slider-handle {
	top: -.3em;
	margin-left: -.6em;
}

.ui-slider-horizontal .ui-slider-range {
	top: 0;
	height: 100%;
}

.ui-slider-horizontal .ui-slider-range-min {
	left: 0;
}

.ui-slider-horizontal .ui-slider-range-max {
	right: 0;
}

.ui-slider-vertical {
	width: .8em;
	height: 100px;
}

.ui-slider-vertical .ui-slider-handle {
	left: -.3em;
	margin-left: 0;
	margin-bottom: -.6em;
}

.ui-slider-vertical .ui-slider-range {
	left: 0;
	width: 100%;
}

.ui-slider-vertical .ui-slider-range-min {
	bottom: 0;
}

.ui-slider-vertical .ui-slider-range-max {
	top: 0;
}

#shuoming {
	text-decoration: none;
	color: #444;
	border-bottom: 1px dashed #444;
	cursor: help;
	position: absolute;
	top: 5px;
	right: 20px;
}
</style>
<%-- 	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZLT8TTF1xGqIslUb0t659pGWnduCdGVf"></script>
 --%>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=ZLT8TTF1xGqIslUb0t659pGWnduCdGVf"></script>
<script type="text/javascript"
	src="http://developer.baidu.com/map/jsdemo/data/points-sample-data.js"></script>

<link href="${path}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/select.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/tinyselect.css" rel="stylesheet" type="text/css" />


<script type="text/javascript" src="${path}/js/mapmove/initmap-Baidu.js"></script>
<script src="${path}/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="${path}/js/toast.js"></script>

</head>
<body>
	<div class="place">
		<span>位置：</span>
	    <ul class="placeul">
	    <li><a href="#">首页</a></li>
	    <li><a href="#">资源台账管理</a></li>
	    <li><a href="#">光缆段管理</a></li>
	    <li><a href="#">光缆段位置</a></li>
	    </ul>
	</div>
	<div class="rightinfo">
		<!-- 操作按钮：新增、导入、导出、查询  -->
		<div class="tools">
			<ul class="toolbar">
				<li class="click"><a id="changePoints" href="javascript:;"><span><img
							src="${path}/images/t02.png" /></span>转化</a></li>	
			</ul>
		</div>
		<div
			style="background-color: #CCCCCC; z-index: 9999; position: relative; height: auto; width: 100%;">
				<div id="usual1" class="usual"> 
					<jsp:include page="/jsp/one/cable/cableDetailNav.jsp" />
					<span id="spanMsg" style="color: Red;"></span> 
					<input name="baiduXA"id="baiduXA" value="${baiduXA}" type="text" style="display: none" />
					<input name="baiduYA"id="baiduYA" value="${baiduYA}" type="text" style="display: none" />
					<input name="baiduXZ"id="baiduXZ" value="${baiduXZ}" type="text" style="display: none" />
					<input name="baiduYZ"id="baiduYZ" value="${baiduYZ}" type="text" style="display: none" />
				</div>	
			</div>
		</div>
			<div id="map_canvas"></div>
</body>
<script type="text/javascript">
	var path = "${path}";
// 	var map = null;
//	function MyPolyLine(points, opts, content){
//		BMap.Polyline.call(points, opts);
//		this.content = content;
//	}
//	MyPolyLine.prototype = new BMap.Polyline();
//	MyPolyLine.prototype.setContent = function(content){
//		this.content = content;
//	}
//	MyPolyLine.prototype.getContent = function(){
//		return this.content;
//	}
	
	
	$(function() {
		$("#changePoints").on('click',changePoints); //转化点位
		var baiduXA = $("#baiduXA").val();
		var baiduYA = $("#baiduYA").val();
		var baiduXZ = $("#baiduXZ").val();
		var baiduYZ = $("#baiduYZ").val();
		map = new BMap.Map("map_canvas", {});
		if(baiduXA!=null && baiduYA!=null && baiduXA!="" && baiduYA!=""){
			
			var map = new BMap.Map("map_canvas",{minZoom:4,maxZoom:30}); // 创建Map实例,设置地图允许的最小/大级别
			map.centerAndZoom(new BMap.Point(baiduXA, baiduYA), 16);
		}
		if(baiduXZ!=null && baiduYZ!=null && baiduXZ!="" && baiduYZ!=""){
			var map = new BMap.Map("map_canvas",{minZoom:4,maxZoom:30}); // 创建Map实例,设置地图允许的最小/大级别
			map.centerAndZoom(new BMap.Point(baiduXZ, baiduYZ), 16);
		}
		if(baiduXA==null && baiduXZ==null && baiduYA==null && baiduYZ==null
		   && baiduXA=="" && baiduXZ =="" && baiduYA=="" && baiduYZ==""
		){
			var map = new BMap.Map("map_canvas",{minZoom:10,maxZoom:30});
			map.centerAndZoom(new BMap.Point(114.4, 23.09), 10);
			var content="两端站点无经纬度数据";
			alert(content);
		}
		
		map.enableScrollWheelZoom(); //启用滚轮放大缩小
		map.addControl(new BMap.NavigationControl());
		if (document.createElement('canvas').getContext) { // 
			var points = [];
			var sectId = ${sectId}
			$.ajax({
				type : "post",
				url : path + "/findCables.htm?sectId="+sectId,
				dataType : "json",
				success : function(data) {
						if(data.facilityA!=null  && data.facilityZ!=null ){
							if(data.facilityA.baiduX!=null && data.facilityA.baiduY!=null && data.facilityZ.baiduX!=null && data.facilityZ.baiduY!=null){
								var point =new BMap.Point(data.baiduXA,data.baiduYA);
								var point1 = new BMap.Point(data.baiduXZ,data.baiduYZ);
								//A端
									if(point!=null && point!="" ){
										var type = data.facilityA.devType;
										var typeName = null;
										if (type == "01") {
											typeName = "光交箱";
											var myIcon = new BMap.Icon(path+"/images/devType/icon_marka.png", new BMap.Size(34,49),{
											    anchor: new BMap.Size(15, 47)
											});
										} else if (type == "02") {
											typeName = "光终端盒";
											var myIcon = new BMap.Icon(path+"/images/devType/icon_geo.png", new BMap.Size(34,49),{
											    anchor: new BMap.Size(15, 47)
											});
										} else if (type == "03") {
											typeName = "光分纤箱";
											var myIcon = new BMap.Icon(path+"/images/devType/icon_geo.png", new BMap.Size(34,49),{
											    anchor: new BMap.Size(15, 47)
											});
										} else if (type == "04") {
											typeName = "光缆接头";
											var myIcon = new BMap.Icon(path+"/images/devType/icon_geo.png", new BMap.Size(34,49),{
											    anchor: new BMap.Size(15, 47)
											});
										} else if (type == "05") {
											typeName = "ODF";
											var myIcon = new BMap.Icon(path+"/images/devType/icon_geo.png", new BMap.Size(34,49),{
											    anchor: new BMap.Size(15, 47)
											});
										} else if (type == "06") {
											typeName = "分光器";
											var myIcon = new BMap.Icon(path+"/images/devType/icon_geo.png", new BMap.Size(34,49),{
											    anchor: new BMap.Size(15, 47)
											});
										} else if (type == "07") {
											typeName = "PTN";
											var myIcon = new BMap.Icon(path+"/images/devType/icon_geo.png", new BMap.Size(34,49),{
											    anchor: new BMap.Size(15, 47)
											});
										} else if (type == "10") {
											typeName = "杆";
											var myIcon = new BMap.Icon(path+"/images/devType/gan_icon.png", new BMap.Size(34,49),{
											    anchor: new BMap.Size(15, 47)
											});
										} else if (type == "11") {
											typeName = "井";
										} else if (type == "13") {
											typeName = "挂墙";
											var myIcon = new BMap.Icon(path+"/images/devType/guaqiang_icon.png", new BMap.Size(34,49),{
											    anchor: new BMap.Size(15, 47)
											});
										} else if (type == "14") {
											typeName = "标石";
											var myIcon = new BMap.Icon(path+"/images/devType/biaoshi_icon.png", new BMap.Size(34,49),{
											    anchor: new BMap.Size(15, 47)
											});
										} else if (type == "15") {
											typeName = "直埋";
											var myIcon = new BMap.Icon(path+"/images/devType/zhimai_icon.png", new BMap.Size(34,49),{
											    anchor: new BMap.Size(15, 47)
											});
										} else if (type == "20") {
											typeName = "机房";
											var myIcon = new BMap.Icon(path+"/images/devType/jifang_icon.png", new BMap.Size(34,49),{
											    anchor: new BMap.Size(15, 47)
											});
										} else {
											typeName = "未知类型";
										}
										
										var marker = new BMap.Marker(point,{icon:myIcon});  // 创建标注
										
										var label = new BMap.Label(data.facilityA.devName,{offset:new BMap.Size(20,-10)});
										
										var title = "";
										title = "设施名称：" + data.facilityA.devName + "<br>设施类型："
												+ typeName + "<br>经度：" + data.baiduXA
												+ "<br>纬度：" + data.baiduYA;
										label.setTitle(title);
										
										marker.setLabel(label);
										
										marker.addContextMenu(markerMenu);
										marker.addEventListener("click", function(e) {
											var l = e.target.getLabel();
											var opts = {
												width : 200, // 信息窗口宽度
												height : 100, // 信息窗口高度
												title : "", // 信息窗口标题
												enableMessage : false,//设置允许信息窗发送短息
											}
		
											var infoWindow = new BMap.InfoWindow(l.getTitle(),opts); // 创建信息窗口对象 
											map.openInfoWindow(infoWindow, e.point); //开启信息窗口
		
										});
										
										var removeMarker = function(e, ee, marker) {
											map.removeOverlay(marker);
										}
										var markerMenu = new BMap.ContextMenu();
										markerMenu.addItem(new BMap.MenuItem('删除', removeMarker
												.bind(marker)));
										map.addOverlay(marker);  
									}
									
									//Z端
									if(point1!=null && point1!=""){
										var type = data.facilityZ.devType;
										var typeName = null;
										if (type == "01") {
											typeName = "光交箱";
											var myIcon = new BMap.Icon(path+"/images/devType/icon_marka.png", new BMap.Size(34,49),{
											    anchor: new BMap.Size(15, 47)
											});
										} else if (type == "02") {
											typeName = "光终端盒";
										} else if (type == "03") {
											typeName = "光分纤箱";
										} else if (type == "04") {
											typeName = "光缆接头";
										} else if (type == "05") {
											typeName = "ODF";
										} else if (type == "06") {
											typeName = "分光器";
											var myIcon = new BMap.Icon(path+"/images/devType/icon_geo.png", new BMap.Size(34,49),{
											    anchor: new BMap.Size(15, 47)
											});
										} else if (type == "07") {
											typeName = "PTN";
										} else if (type == "10") {
											typeName = "杆";
											var myIcon = new BMap.Icon(path+"/images/devType/gan_icon.png", new BMap.Size(34,49),{
											    anchor: new BMap.Size(15, 47)
											});
										} else if (type == "11") {
											typeName = "井";
										} else if (type == "13") {
											typeName = "挂墙";
											var myIcon = new BMap.Icon(path+"/images/devType/guaqiang_icon.png", new BMap.Size(34,49),{
											    anchor: new BMap.Size(15, 47)
											});
										} else if (type == "14") {
											typeName = "标石";
											var myIcon = new BMap.Icon(path+"/images/devType/biaoshi_icon.png", new BMap.Size(34,49),{
											    anchor: new BMap.Size(15, 47)
											});
										} else if (type == "15") {
											typeName = "直埋";
											var myIcon = new BMap.Icon(path+"/images/devType/zhimai_icon.png", new BMap.Size(34,49),{
											    anchor: new BMap.Size(15, 47)
											});
										} else if (type == "20") {
											typeName = "机房";
											var myIcon = new BMap.Icon(path+"/images/devType/jifang_icon.png", new BMap.Size(34,49),{
											    anchor: new BMap.Size(15, 47)
											});
										} else {
											typeName = "未知类型";
										}
										
										var marker = new BMap.Marker(point1,{icon:myIcon});  // 创建标注
										var label = new BMap.Label(data.facilityZ.devName,{offset:new BMap.Size(20,-10)});
										
										var title = "";
										title = "设施名称：" + data.facilityZ.devName + "<br>设施类型："
												+ typeName + "<br>经度：" + data.baiduXZ
												+ "<br>纬度：" + data.baiduYZ;
										label.setTitle(title);
										
										marker.setLabel(label);
										
										marker.addContextMenu(markerMenu);
										marker.addEventListener("click", function(e) {
											var l = e.target.getLabel();
											
											var opts = {
												width : 200, // 信息窗口宽度
												height : 100, // 信息窗口高度
												title : "", // 信息窗口标题
												enableMessage : false,//设置允许信息窗发送短息
											}
		
											var infoWindow = new BMap.InfoWindow(l.getTitle(),opts); // 创建信息窗口对象 
											
											map.openInfoWindow(infoWindow, e.point); //开启信息窗口
		
										});
										var removeMarker = function(e, ee, marker) {
											map.removeOverlay(marker);
										}
										var markerMenu = new BMap.ContextMenu();
										markerMenu.addItem(new BMap.MenuItem('删除', removeMarker
												.bind(marker)));
										map.addOverlay(marker);  
									}
 						}	
						
						if(data.facilityA.baiduX!=null && data.facilityA.baiduY!=null && data.facilityZ.baiduX==null && data.facilityZ.baiduY==null){
							var point =new BMap.Point(data.baiduXA,data.baiduYA);
							var type = data.facilityA.devType;
							var typeName = null;
							if (type == "01") {
								typeName = "光交箱";
								var myIcon = new BMap.Icon(path+"/images/devType/icon_marka.png", new BMap.Size(34,49),{
								    anchor: new BMap.Size(15, 47)
								});
							} else if (type == "02") {
								typeName = "光终端盒";
							} else if (type == "03") {
								typeName = "光分纤箱";
							} else if (type == "04") {
								typeName = "光缆接头";
							} else if (type == "05") {
								typeName = "ODF";
							} else if (type == "06") {
								typeName = "分光器";
								var myIcon = new BMap.Icon(path+"/images/devType/icon_geo.png", new BMap.Size(34,49),{
								    anchor: new BMap.Size(15, 47)
								});
							} else if (type == "07") {
								typeName = "PTN";
							} else if (type == "10") {
								typeName = "杆";
								var myIcon = new BMap.Icon(path+"/images/devType/gan_icon.png", new BMap.Size(34,49),{
								    anchor: new BMap.Size(15, 47)
								});
							} else if (type == "11") {
								typeName = "井";
							} else if (type == "13") {
								typeName = "挂墙";
								var myIcon = new BMap.Icon(path+"/images/devType/guaqiang_icon.png", new BMap.Size(34,49),{
								    anchor: new BMap.Size(15, 47)
								});
							} else if (type == "14") {
								typeName = "标石";
								var myIcon = new BMap.Icon(path+"/images/devType/biaoshi_icon.png", new BMap.Size(34,49),{
								    anchor: new BMap.Size(15, 47)
								});
							} else if (type == "15") {
								typeName = "直埋";
								var myIcon = new BMap.Icon(path+"/images/devType/zhimai_icon.png", new BMap.Size(34,49),{
								    anchor: new BMap.Size(15, 47)
								});
							} else if (type == "20") {
								typeName = "机房";
								var myIcon = new BMap.Icon(path+"/images/devType/jifang_icon.png", new BMap.Size(34,49),{
								    anchor: new BMap.Size(15, 47)
								});
							} else {
								typeName = "未知类型";
							}
							
							var marker = new BMap.Marker(point,{icon:myIcon});  // 创建标注
							
							var label = new BMap.Label(data.facilityA.devName+"|||"+"Z端站点设施无经纬度数据",{offset:new BMap.Size(20,-10)});
							
							var title = "";
							title = "设施名称：" + data.facilityA.devName + "<br>设施类型："
									+ typeName + "<br>经度：" + data.baiduXA
									+ "<br>纬度：" + data.baiduYA;
							label.setTitle(title);
							
							marker.setLabel(label);
							
							marker.addContextMenu(markerMenu);
							marker.addEventListener("click", function(e) {
								var l = e.target.getLabel();
								var opts = {
									width : 200, // 信息窗口宽度
									height : 100, // 信息窗口高度
									title : "", // 信息窗口标题
									enableMessage : false,//设置允许信息窗发送短息
								}

								var infoWindow = new BMap.InfoWindow(l.getTitle(),opts); // 创建信息窗口对象 
								map.openInfoWindow(infoWindow, e.point); //开启信息窗口

							});
							
							var removeMarker = function(e, ee, marker) {
								map.removeOverlay(marker);
							}
							var markerMenu = new BMap.ContextMenu();
							markerMenu.addItem(new BMap.MenuItem('删除', removeMarker
									.bind(marker)));
							map.addOverlay(marker);  
						}
						
						
						if(data.facilityZ.baiduX!=null && data.facilityZ.baiduY!=null && data.facilityA.baiduX==null && data.facilityA.baiduY==null){
							var point1 = new BMap.Point(data.baiduXZ,data.baiduYZ);
							var type = data.facilityZ.devType;
							var typeName = null;
							if (type == "01") {
								typeName = "光交箱";
								var myIcon = new BMap.Icon(path+"/images/devType/icon_marka.png", new BMap.Size(34,49),{
								    anchor: new BMap.Size(15, 47)
								});
							} else if (type == "02") {
								typeName = "光终端盒";
							} else if (type == "03") {
								typeName = "光分纤箱";
							} else if (type == "04") {
								typeName = "光缆接头";
							} else if (type == "05") {
								typeName = "ODF";
							} else if (type == "06") {
								typeName = "分光器";
								var myIcon = new BMap.Icon(path+"/images/devType/icon_geo.png", new BMap.Size(34,49),{
								    anchor: new BMap.Size(15, 47)
								});
							} else if (type == "07") {
								typeName = "PTN";
							} else if (type == "10") {
								typeName = "杆";
								var myIcon = new BMap.Icon(path+"/images/devType/gan_icon.png", new BMap.Size(34,49),{
								    anchor: new BMap.Size(15, 47)
								});
							} else if (type == "11") {
								typeName = "井";
							} else if (type == "13") {
								typeName = "挂墙";
								var myIcon = new BMap.Icon(path+"/images/devType/guaqiang_icon.png", new BMap.Size(34,49),{
								    anchor: new BMap.Size(15, 47)
								});
							} else if (type == "14") {
								typeName = "标石";
								var myIcon = new BMap.Icon(path+"/images/devType/biaoshi_icon.png", new BMap.Size(34,49),{
								    anchor: new BMap.Size(15, 47)
								});
							} else if (type == "15") {
								typeName = "直埋";
								var myIcon = new BMap.Icon(path+"/images/devType/zhimai_icon.png", new BMap.Size(34,49),{
								    anchor: new BMap.Size(15, 47)
								});
							} else if (type == "20") {
								typeName = "机房";
								var myIcon = new BMap.Icon(path+"/images/devType/jifang_icon.png", new BMap.Size(34,49),{
								    anchor: new BMap.Size(15, 47)
								});
							} else {
								typeName = "未知类型";
							}
							
							var marker = new BMap.Marker(point1,{icon:myIcon});  // 创建标注
							var label = new BMap.Label(data.facilityZ.devName+"A端站点设施无经纬度数据",{offset:new BMap.Size(20,-10)});
							var title = "";
							title = "设施名称：" + data.facilityZ.devName + "<br>设施类型："
									+ typeName + "<br>经度：" + data.facilityZ.baiduX
									+ "<br>纬度：" + data.facilityZ.baiduY;
							label.setTitle(title);
							
							marker.setLabel(label);
							
							marker.addContextMenu(markerMenu);
							marker.addEventListener("click", function(e) {
								var l = e.target.getLabel();
								
								var opts = {
									width : 200, // 信息窗口宽度
									height : 100, // 信息窗口高度
									title : "", // 信息窗口标题
									enableMessage : false,//设置允许信息窗发送短息
								}

								var infoWindow = new BMap.InfoWindow(l.getTitle(),opts); // 创建信息窗口对象 
								
								map.openInfoWindow(infoWindow, e.point); //开启信息窗口

							});
							var removeMarker = function(e, ee, marker) {
								map.removeOverlay(marker);
							}
							var markerMenu = new BMap.ContextMenu();
							markerMenu.addItem(new BMap.MenuItem('删除', removeMarker
									.bind(marker)));
							map.addOverlay(marker);  
						}
						if(data.facilityA.baiduX==null && data.facilityA.baiduY==null && data.facilityZ.baiduX==null && data.facilityZ.baiduY==null ){
							var content ="两端站点无经纬度数据";
							alert(content);
						}
				}	//	
			
						if(data.facilityA!=null  && data.facilityZ==null ){
							if(data.facilityA.baiduX!=null && data.facilityA.baiduY!=null){
							var point = new BMap.Point(data.baiduXA, 
									data.baiduYA)
								var type = data.facilityA.devType;
								var typeName = null;
								if (type == "01") {
									typeName = "光交箱";
									var myIcon = new BMap.Icon(path+"/images/devType/icon_marka.png", new BMap.Size(34,49),{
									    anchor: new BMap.Size(15, 47)
									});
								} else if (type == "02") {
									typeName = "光终端盒";
								} else if (type == "03") {
									typeName = "光分纤箱";
								} else if (type == "04") {
									typeName = "光缆接头";
								} else if (type == "05") {
									typeName = "ODF";
								} else if (type == "06") {
									typeName = "分光器";
									var myIcon = new BMap.Icon(path+"/images/devType/icon_geo.png", new BMap.Size(34,49),{
									    anchor: new BMap.Size(15, 47)
									});
								} else if (type == "07") {
									typeName = "PTN";
								} else if (type == "10") {
									typeName = "杆";
									var myIcon = new BMap.Icon(path+"/images/devType/gan_icon.png", new BMap.Size(34,49),{
									    anchor: new BMap.Size(15, 47)
									});
								} else if (type == "11") {
									typeName = "井";
								} else if (type == "13") {
									typeName = "挂墙";
									var myIcon = new BMap.Icon(path+"/images/devType/guaqiang_icon.png", new BMap.Size(34,49),{
									    anchor: new BMap.Size(15, 47)
									});
								} else if (type == "14") {
									typeName = "标石";
									var myIcon = new BMap.Icon(path+"/images/devType/biaoshi_icon.png", new BMap.Size(34,49),{
									    anchor: new BMap.Size(15, 47)
									});
								} else if (type == "15") {
									typeName = "直埋";
									var myIcon = new BMap.Icon(path+"/images/devType/zhimai_icon.png", new BMap.Size(34,49),{
									    anchor: new BMap.Size(15, 47)
									});
								} else if (type == "20") {
									typeName = "机房";
									var myIcon = new BMap.Icon(path+"/images/devType/jifang_icon.png", new BMap.Size(34,49),{
									    anchor: new BMap.Size(15, 47)
									});
								} else {
									typeName = "未知类型";
								}
								
								var marker = new BMap.Marker(point,{icon:myIcon});  // 创建标注
								
								var label = new BMap.Label(data.facilityA.devName+"|||"+"Z端站点设施无经纬度数据",{offset:new BMap.Size(20,-10)});
								var title = "";
								title = "设施名称：" + data.facilityA.devName + "<br>设施类型："
										+ typeName + "<br>经度：" + data.facilityA.baiduX
										+ "<br>纬度：" + data.facilityA.baiduY;
								label.setTitle(title);
								
								marker.setLabel(label);
								
								marker.addContextMenu(markerMenu);
								marker.addEventListener("click", function(e) {
									var l = e.target.getLabel();
									var opts = {
										width : 200, // 信息窗口宽度
										height : 100, // 信息窗口高度
										title : "", // 信息窗口标题
										enableMessage : false,//设置允许信息窗发送短息
									}
	
									var infoWindow = new BMap.InfoWindow(l.getTitle(),opts); // 创建信息窗口对象 
									map.openInfoWindow(infoWindow, e.point); //开启信息窗口
	
								});
								
								var removeMarker = function(e, ee, marker) {
									map.removeOverlay(marker);
								}
								var markerMenu = new BMap.ContextMenu();
								markerMenu.addItem(new BMap.MenuItem('删除', removeMarker
										.bind(marker)));
								map.addOverlay(marker);  
							}else{
								var content="两端站点无经纬度数据";
								alert(content)
							}
						}
						if(data.facilityZ!=null  && data.facilityA==null ){
							if(data.facilityZ.baiduX!=null && data.facilityZ.baiduY!=null){
							var point1 = new BMap.Point(data.facilityZ.baiduX,
									data.facilityZ.baiduY);
								var type = data.facilityZ.devType;
								var typeName = null;
								if (type == "01") {
									typeName = "光交箱";
									var myIcon = new BMap.Icon(path+"/images/devType/icon_marka.png", new BMap.Size(34,49),{
									    anchor: new BMap.Size(15, 47)
									});
								} else if (type == "02") {
									typeName = "光终端盒";
								} else if (type == "03") {
									typeName = "光分纤箱";
								} else if (type == "04") {
									typeName = "光缆接头";
								} else if (type == "05") {
									typeName = "ODF";
								} else if (type == "06") {
									typeName = "分光器";
									var myIcon = new BMap.Icon(path+"/images/devType/icon_geo.png", new BMap.Size(34,49),{
									    anchor: new BMap.Size(15, 47)
									});
								} else if (type == "07") {
									typeName = "PTN";
								} else if (type == "10") {
									typeName = "杆";
									var myIcon = new BMap.Icon(path+"/images/devType/gan_icon.png", new BMap.Size(34,49),{
									    anchor: new BMap.Size(15, 47)
									});
								} else if (type == "11") {
									typeName = "井";
								} else if (type == "13") {
									typeName = "挂墙";
									var myIcon = new BMap.Icon(path+"/images/devType/guaqiang_icon.png", new BMap.Size(34,49),{
									    anchor: new BMap.Size(15, 47)
									});
								} else if (type == "14") {
									typeName = "标石";
									var myIcon = new BMap.Icon(path+"/images/devType/biaoshi_icon.png", new BMap.Size(34,49),{
									    anchor: new BMap.Size(15, 47)
									});
								} else if (type == "15") {
									typeName = "直埋";
									var myIcon = new BMap.Icon(path+"/images/devType/zhimai_icon.png", new BMap.Size(34,49),{
									    anchor: new BMap.Size(15, 47)
									});
								} else if (type == "20") {
									typeName = "机房";
									var myIcon = new BMap.Icon(path+"/images/devType/jifang_icon.png", new BMap.Size(34,49),{
									    anchor: new BMap.Size(15, 47)
									});
								} else {
									typeName = "未知类型";
								}
								
								var marker = new BMap.Marker(point1,{icon:myIcon});  // 创建标注
								var label = new BMap.Label(data.facilityZ.devName+"|||"+"A端站点设施无经纬度数据",{offset:new BMap.Size(20,-10)});
								
								var title = "";
								title = "设施名称：" + data.facilityZ.devName + "<br>设施类型："
										+ typeName + "<br>经度：" + data.facilityZ.baiduX
										+ "<br>纬度：" + data.facilityZ.baiduY;
								label.setTitle(title);
								
								marker.setLabel(label);
								
								marker.addContextMenu(markerMenu);
								marker.addEventListener("click", function(e) {
									var l = e.target.getLabel();
									
									var opts = {
										width : 200, // 信息窗口宽度
										height : 100, // 信息窗口高度
										title : "", // 信息窗口标题
										enableMessage : false,//设置允许信息窗发送短息
									}
	
									var infoWindow = new BMap.InfoWindow(l.getTitle(),opts); // 创建信息窗口对象 
									
									map.openInfoWindow(infoWindow, e.point); //开启信息窗口
	
								});
								var removeMarker = function(e, ee, marker) {
									map.removeOverlay(marker);
								}
								var markerMenu = new BMap.ContextMenu();
								markerMenu.addItem(new BMap.MenuItem('删除', removeMarker
										.bind(marker)));
								map.addOverlay(marker);  
							}else{
								var content="两端站点无经纬度数据";
								alert(content)
							}
						}
					
						if(data.facilityZ==null && data.facilityA==null){
							var content="两端站点无经纬度数据";
							alert(content)
						}
				}
			});
		};
		var sectId = ${sectId}
		$.ajax({
			type : "post",
			url : path + "/findFiberCable.htm?sectId="+sectId,
			dataType : "json",
			success : function(data) {
					var fiber = [];
					if(data.facilityA!=null){
						if(data.baiduXA!=null && data.baiduYA!=null){
							var a = new BMap.Point(data.baiduXA,
									data.baiduYA);
						}
					}
					if(data.facilityZ!=null){
						if(data.baiduXZ!=null && data.baiduYZ!=null){
							var b = new BMap.Point(data.baiduXZ, 
									data.baiduYZ);
						}
					}
					if(a!=null && b!=null){
						fiber = [ a, b ];
					
						var line = new BMap.Polyline(fiber, {
							strokeColor : "blue",
							strokeWeight : 5,
							strokeOpacity : 0.5
						}); //创建折线对象
						setLine(line,data);
					}
			}
		});	
		
		
		function setLine(line,data,sectId){
			line.addEventListener("click",function(e){
				var p = e.target;
				if (p instanceof BMap.Polyline) {
					
					var content="光缆段名称："+data.cb.secName
					alert(content);
				}
			});
			map.addOverlay(line); //添加到地图中
		}
	
	
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
});
</script>

</html>