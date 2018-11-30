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
<script type="text/javascript" src="${path}/js/mapmove/initmap-Baidu.js"></script>
<script src="${path}/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="${path}/js/toast.js"></script>
</head>
<body>
	<div
		style="background-color: #CCCCCC; z-index: 9999; position: relative; height: auto; width: 100%;">
		<span id="spanMsg" style="color: Red;"></span> <input name="areacode"
			id="areacode" value="${areacode}" type="text" style="display: none" />
			<span id="spanMsg1" style="color: Red;"></span> <input name="areaName"
			id="areaName" value="${areaName}" type="text" style="display: none" />
	</div>
	<input id="search" type="text" onchange="" oninput="onSearch()" placeholder="请输入设施名称...">
<!-- 	<input style="cursor: pointer;" type="button" value="搜索"> -->
<!-- 	<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div> -->
	<select id="se" name="se" style="width:30%" onchange="sel(this.options[this.options.selectedIndex].value,this.options[this.options.selectedIndex].text)">
		<!-- 默认选择 -->
		<option id="aaa"  value="!" selected>==请选择==</option>
	</select>
	<div id="map_canvas"></div>
</body>
<script type="text/javascript">
	var path = "${path}";
	var map = null;
	var sign = 0;
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

	//点击按位置浏览,初始化地图		
	$(function() {
		sign = 0;
		var strs = "";
		aa(sign,strs);
	});
	
	
	function aa(sign,strs){
		
		var areacode = $("#areacode").val();
		var areaName = $("#areaName").val();
		map = new BMap.Map("map_canvas", {});
		//初始化地图
		if(sign == 0){
			map.centerAndZoom(areaName, 9);
		}
		//按位置浏览搜索栏搜索光交箱
		if(sign == 1){
			 var X = strs[0];
			 var Y = strs[1];
			map.centerAndZoom(new BMap.Point(X, Y), 20);
		}
		map.enableScrollWheelZoom(); //启用滚轮放大缩小
		map.addControl(new BMap.NavigationControl());
// 		var u; // 定义地图缩放等级的变量
		map.addEventListener("zoomend", function(){
			if(this.getZoom()>=15){
				map.clearOverlays();
				if (document.createElement('canvas').getContext) { // 
					var points = [];
					$.ajax({
						type : "post",
						url : path + "/findFacilitiesAreaCode1.htm?areaCode1="+areacode,
						dataType : "json",
						success : function(data) {
							for (var i = 0; i < data.length; i++) {
								var point = new BMap.Point(data[i].baiduX,
										data[i].baiduY);
		// 						var marker = new BMap.Marker(point, {
		// 							// 初始化圆形
		// 							icon : new BMap.Symbol(BMap_Symbol_SHAPE_CIRCLE, {
		// 								scale : 4,
		// 								strokeWeight : 0.6,
		// 								rotation : 90,
		// 								fillColor : 'red',
		// 								fillOpacity : 0.8
		// 							})
		// 						});
								var removeMarker = function(e, ee, marker) {
									map.removeOverlay(marker);
								}
							
								var type = data[i].devType;
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
								} else if (type == "07") {
									typeName = "PTN";
								} else if (type == "20") {
									typeName = "机房";
									var myIcon = new BMap.Icon(path+"/images/devType/jifang_icon.png", new BMap.Size(34,49),{
									    anchor: new BMap.Size(15, 47)
									});
								} else {
									typeName = "未知类型";
								}
								
								var marker = new BMap.Marker(point,{icon:myIcon});  // 创建标注
								map.addOverlay(marker);              // 将标注添加到地图中
								
								var label = new BMap.Label(data[i].devName,{offset:new BMap.Size(20,-10)});
								
								var title = "";
								title = "设施名称：" + data[i].devName + "<br>设施类型："
										+ typeName + "<br>百度X：" + data[i].baiduX
										+ "<br>百度Y：" + data[i].baiduY;
								label.setTitle(title);
		// 						label.setStyle({
		// 							display : 'none'
		// 						});
								marker.setLabel(label);
								
		// 						marker.addContextMenu(markerMenu);
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
							}
						}
					});
				};
		
				$.ajax({
					type : "post",
					url : path + "/findFiber.htm?areaCode1="+areacode,
					dataType : "json",
					success : function(data) {
						for (var i = 0; i < data.length; i++) {
							var fiber = [];
							var a = new BMap.Point(data[i].afacility.baiduX,
									data[i].afacility.baiduY);
							var b = new BMap.Point(data[i].zfacility.baiduX,
									data[i].zfacility.baiduY);
							fiber = [ a, b ];
							var line = new BMap.Polyline(fiber, {
								strokeColor : "blue",
								strokeWeight : 5,
								strokeOpacity : 0.5
							}); //创建折线对象
							
							setLine(line,data[i]);
							
						}
		
					}
				});
				
				function setLine(line,data){
					line.addEventListener("click",function(e){
						var p = e.target;
						if (p instanceof BMap.Polyline) {
							var acode=data.acode;
							if(acode==null || acode==""){
								acode="信息不全";
							}
							var zcode=data.zcode;
							if(zcode==null || zcode==""){
								zcode="信息不全";
							}
							var content="名称："+data.secName+"\n纤芯数:"+data.fiberNum+"\nA端成端起始端口："+acode+"\nZ端成端起始端口："+zcode;
							alert(content);
						}
					});
					map.addOverlay(line); //添加到地图中
				}
			}else{
				map.clearOverlays();
				if (document.createElement('canvas').getContext) {  // 判断当前浏览器是否支持绘制海量点
			        var points = [];  // 添加海量点数据
			        $.ajax({
						type : "post",
						url : path + "/findFacilitiesAreaCode1.htm",
						dataType : "json",
						success : function(data) {
							for (var i = 0; i < data.length; i++) {
								points.push(new BMap.Point(data[i].baiduX,data[i].baiduY));
							}
							var options = {
					            size: BMAP_POINT_SIZE_SMALL,
					            shape: BMAP_POINT_SHAPE_CIRCLE,
					            color: '#A52A2A'
					        }
					        var pointCollection = new BMap.PointCollection(points, options);// 初始化PointCollection
					        pointCollection.addEventListener('click', function (e) {
					        	var devName="";//名称
					        	var devType="";//设施类型
					        	var baiduX;
					        	var baiduY;
					        	for (var i = 0; i < data.length; i++) {
					        		if(data[i].baiduX==e.point.lng && data[i].baiduY==e.point.lat){
					        			devName=data[i].devName;
					        			baiduX=data[i].baiduX;
					        			baiduY=data[i].baiduY;
					        			if(data[i].devType=='01'){
					        				devType="光交箱";
					        			}else if(data[i].devType=='20'){
					        				devType="机房";
					        			}else if(data[i].devType=='05'){
					        				devType="机柜";
					        			}else if(data[i].devType=="02") {
					        				devType="光终端盒";
										}else if(data[i].devType=="03") {
											devType="光分纤箱";
										}else if(data[i].devType=="04") {
											devType="光缆接头";
										}else if(data[i].devType=="06") {
											devType="分光器";
										}else{
											devType="未知类型";
										}
					        			break;
					        		}
					        	}
					        	var point = new BMap.Point(e.point.lng, e.point.lat);
					        	var title = "";
								title = "设施名称：" + devName + "<br>设施类型："
										+ devType + "<br>百度X：" + baiduX
										+ "<br>百度Y：" + baiduY;
					        	var opts = {
				        			width: 250, // 信息窗口宽度
				        			height: 80, // 信息窗口高度
				        			title:"", // 信息窗口标题
				        			enableMessage: false,//设置允许信息窗发送短息
				        		}
					        	var infowindow = new BMap.InfoWindow(title, opts);
					        	map.openInfoWindow(infowindow, point);
				            });
				            map.addOverlay(pointCollection);  // 添加Overlay
						}
			        })
			        
			    } else {
			        alert('请在chrome、safari、IE8+以上浏览器查看本示例');
			    }
			}
		
		});
	}
	//搜索选中设施进入光缆段详情map—————————————————————————————bb——————————————————————————————————
	function bb(sign,strs){
		var devId=strs[2];
		map = new BMap.Map("map_canvas", {});
		
		if(sign == 1){
			 var X = strs[0];
			 var Y = strs[1];
			map.centerAndZoom(new BMap.Point(X, Y), 16);
		}
		map.enableScrollWheelZoom(); //启用滚轮放大缩小
		map.addControl(new BMap.NavigationControl());
		
		if (document.createElement('canvas').getContext) { // 
			var points = [];
			$.ajax({
				type : "post",
				url : path + "/findbyDevId.htm?devId="+devId,
				dataType : "json",
				success : function(data) {
					for (var i = 0; i < data.length; i++) {
						var point = new BMap.Point(data[i].baiduX,
								data[i].baiduY);
						var removeMarker = function(e, ee, marker) {
							map.removeOverlay(marker);
						}
						var type = data[i].devType;
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
							typeName = "机柜";
							var myIcon = new BMap.Icon(path+"/images/devType/xiang_icon.png", new BMap.Size(34,49),{
							    anchor: new BMap.Size(15, 47)
							});
						} else if (type == "06") {
							typeName = "分光器";
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
						map.addOverlay(marker);              // 将标注添加到地图中
						
						var label = new BMap.Label(data[i].devName,{offset:new BMap.Size(20,-10)});
						
						var title = "";
						title = "设施名称：" + data[i].devName + "<br>设施类型："
								+ typeName + "<br>百度X：" + data[i].baiduX
								+ "<br>百度Y：" + data[i].baiduY;
						label.setTitle(title);
						marker.setLabel(label);
						
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
					}
				}
			});
		};
		
		$.ajax({
			type : "post",
			url : path + "/findCDState.htm?devId="+devId,
			dataType : "json",
			success : function(data) {
				if(data.cableTwoCD !=null){	//两端都成端
					for(var i=0 ; i<data.cableTwoCD.length; i++){
						var fiber = [];
						var a = new BMap.Point(data.cableTwoCD[i].afacility.baiduX,
								data.cableTwoCD[i].afacility.baiduY);
						var b = new BMap.Point(data.cableTwoCD[i].zfacility.baiduX,
								data.cableTwoCD[i].zfacility.baiduY);
						fiber = [ a, b ];
						var line = new BMap.Polyline(fiber, {
							strokeColor : "blue",
							strokeWeight : 5,
							strokeOpacity : 0.5
						}); //创建折线对象
						
						line.addEventListener("click",function(e){
			 				var p = e.target;
			 				if (p instanceof BMap.Polyline) {
			 					var content="名称："+data.cableTwoCD[i].secName+"\n纤芯数:"+data.fiberNum+"\n编码:"+data.cableTwoCD[i].sectCode;
			 					alert(content);
			 				}
			 			});
						
						map.addOverlay(line); //添加到地图中
						
// 						setLine(line,data.cableTwoCD[i]);
					}
				}
				if(data.cableOneCD !=null){	//一端成端
					for(var i=0 ; i<data.cableOneCD.length; i++){
						var fiber = [];
						var a = new BMap.Point(data.cableOneCD[i].afacility.baiduX,
								data.cableOneCD[i].afacility.baiduY);
						var b = new BMap.Point(data.cableOneCD[i].zfacility.baiduX,
								data.cableOneCD[i].zfacility.baiduY);
						fiber = [ a, b ];
						var line = new BMap.Polyline(fiber, {
							strokeColor : "red",
							strokeWeight : 5,
							strokeOpacity : 0.5
						}); //创建折线对象
						
						line.addEventListener("click",function(e){
			 				var p = e.target;
			 				if (p instanceof BMap.Polyline) {
			 					var content="名称："+data.cableOneCD[i].secName+"\n纤芯数:"+data.fiberNum+"\n编码:"+data.cableOneCD[i].sectCode;
			 					alert(content);
			 				}
			 			});
						
						map.addOverlay(line); //添加到地图中
// 						setLine(line,data.cableOneCD[i]);
					}
				}
				if(data.cableZeroCD != null){	//两端都没有成端
					for(var i=0 ; i<data.cableZeroCD.length; i++){
						var fiber = [];
						var a = new BMap.Point(data.cableZeroCD[i].afacility.baiduX,
								data.cableZeroCD[i].afacility.baiduY);
						var b = new BMap.Point(data.cableZeroCD[i].zfacility.baiduX,
								data.cableZeroCD[i].zfacility.baiduY);
						fiber = [ a, b ];
						var line = new BMap.Polyline(fiber, {
							strokeColor : "yellow",
							strokeWeight : 5,
							strokeOpacity : 0.5
						}); //创建折线对象
						
						line.addEventListener("click",function(e){
			 				var p = e.target;
			 				if (p instanceof BMap.Polyline) {
			 					var content="名称："+data.cableZeroCD[i].secName+"\n纤芯数:"+data.fiberNum+"\n编码:"+data.cableZeroCD[i].sectCode;
			 					alert(content);
			 				}
			 			});
						
						map.addOverlay(line); //添加到地图中
// 						setLine(line,data.cableZeroCD[i]);
					}
				}
				

			}
		});
		
// 		function setLine(line,data){
// 			line.addEventListener("click",function(e){
// 				var p = e.target;
// 				if (p instanceof BMap.Polyline) {
// 					var acode=data.acode;
// 					if(acode==null || acode==""){
// 						acode="信息不全";
// 					}
// 					var zcode=data.zcode;
// 					if(zcode==null || zcode==""){
// 						zcode="信息不全";
// 					}
// 					var content="名称："+data.secName+"\n纤芯数:"+data.fiberNum+"\nA端成端起始端口："+acode+"\nZ端成端起始端口："+zcode;
// 					alert(content);
// 				}
// 			});
// 			map.addOverlay(line); //添加到地图中
// 		}
		
	}
			
	//按位置浏览搜索栏搜索光交箱
	function onSearch(){
		var searchName = $("#search").val();
		var areacode = $("#areacode").val();
		if(searchName==''){
			$('#se').html("<option id='aaa' style='display:none;'  selected>==请选择==</option>");
		}
		
		$.ajax({
			type : "post",
			url : path + "/onSearch.htm?searchName="+searchName+"&areaCode1="+areacode,
			dataType : "json",
			success : function(data) {
				var s = '';
				for(var index = 0;index<data.length;index++)
				   {
					   var item  = data[index];
				       s +=  "<option value='"+item.baiduX+","+item.baiduY+","+item.devId+"'>"+item.devName+"</option>"
				   }
					//模糊查询,添加下拉框
					$('#se').html("<option id='aaa' style='display:none;'  selected>==请选择==</option>");
				  	$('#se').append(s);
				  	 
			}
		});
	}
	
	//点击下拉框选项,跳转地图
	function sel(value,text){
		 var strs = value.split(",");
         var selectId = document.getElementById("se");  
		 selectId.options[0].text = text;//修改默认显示的名称
         selectId.options[0].selected=true;//多次点击同一个选项,onchange事件不触发.此处点击后选中默认选项,触发onchange  
         
         sign = 1;
         bb(sign,strs);
			
	}
</script>

</html>