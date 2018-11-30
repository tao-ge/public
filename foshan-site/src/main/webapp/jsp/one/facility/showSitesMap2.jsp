<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<% 
String rc = request.getContextPath(); 
request.setAttribute("path",rc);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>	
	<title>分光器地图</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">

	<style type="text/css">
		body, html,#map_canvas {width: 100%;height: 100%;overflow: hidden;margin:0;}
		
		body{font-size:12px;color:#000;font-family:"宋体";}
		#introduction{color:#434343;width:200px;top:130px;left:50px;position:absolute;z-index:1;border:1px solid #bcbcbc;}
		.intro-title{font-weight:800;background:#eaeaea;font-size:14px;line-height:14px;padding:10px;}
		.intro-title a{text-decoration:none;background:url(../images/close.gif);height:14px;width:14px;}
		.intro-body{padding:10px 10px 20px;background:#fff;}
		#intro-content{line-height:1.8em;}
		.intro-button {margin-top:20px;text-align:center;}
		.intro-button a{line-height:33px;font-weight:800;color:#fff;text-decoration:none;display:inline-block;background:url(http://developer.baidu.com/map/jsdemo/img/btn.png);height:33px;width:100px;}
		.intro-button a:hover{background:url(../images/btn-on.png);}
		.f-r{float:right;}
		
		body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
			overflow:hidden;
		}
		/* Overlays */
		.ui-widget-overlay { position: absolute; top: 0; left: 0; width: 100%; height: 100%; }
		 
		/* Component containers
		----------------------------------*/
		.ui-widget { font-family: Verdana,Arial,sans-serif; font-size: 1.1em; }
		.ui-widget .ui-widget { font-size: 1em; }
		.ui-widget input, .ui-widget select, .ui-widget textarea, .ui-widget button { font-family: Verdana,Arial,sans-serif; font-size: 1em; }
		.ui-widget-content { border: 1px solid #aaaaaa; background: #ffffff  50% 50% repeat-x; color: #222222; }
		.ui-widget-content a { color: #222222; }
		.ui-widget-header a { color: #222222; }
		 
		/* Interaction states
		----------------------------------*/
		.ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default { border: 1px solid #3399FF; background: #3399FF 50% 50% repeat-x; font-weight: normal; color: #5c9dc4; }
		.ui-state-default a, .ui-state-default a:link, .ui-state-default a:visited { color: #3399FF; text-decoration: none; }
		.ui-state-hover a, .ui-state-hover a:hover { color: #3399FF; text-decoration: none; }
		.ui-state-active, .ui-widget-content .ui-state-active, .ui-widget-header .ui-state-active { border: 1px solid #3399FF; background: #3399FF 50% 50% repeat-x; font-weight: normal; color: #3399FF; }
		.ui-state-active a, .ui-state-active a:link, .ui-state-active a:visited { color: #3399FF; text-decoration: none; }
		.ui-widget :active { outline: none; }
		 
		.ui-slider { position: relative; text-align: left;  }
		.ui-slider .ui-slider-handle { position: absolute; z-index: 2; width: 12px; height: 12px; cursor: e-resize; }
		.ui-slider .ui-slider-range { position: absolute; z-index: 1; font-size: .7em; display: block; border: 0; background-position: 0 0; }
		 
		.ui-slider-horizontal { height: .8em; }
		.ui-slider-horizontal .ui-slider-handle { top: -.3em; margin-left: -.6em; }
		.ui-slider-horizontal .ui-slider-range { top: 0; height: 100%; }
		.ui-slider-horizontal .ui-slider-range-min { left: 0; }
		.ui-slider-horizontal .ui-slider-range-max { right: 0; }
		 
		.ui-slider-vertical { width: .8em; height: 100px; }
		.ui-slider-vertical .ui-slider-handle { left: -.3em; margin-left: 0; margin-bottom: -.6em; }
		.ui-slider-vertical .ui-slider-range { left: 0; width: 100%; }
		.ui-slider-vertical .ui-slider-range-min { bottom: 0; }
		.ui-slider-vertical .ui-slider-range-max { top: 0; }
		
		#shuoming{text-decoration:none;color:#444;border-bottom:1px dashed #444;cursor:help;position:absolute;top:5px;right:20px;}
				
	</style>
<%-- 	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZLT8TTF1xGqIslUb0t659pGWnduCdGVf"></script>
 --%>	
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZLT8TTF1xGqIslUb0t659pGWnduCdGVf"></script>
	<script type="text/javascript" src="http://developer.baidu.com/map/jsdemo/data/points-sample-data.js"></script>	
	<script type="text/javascript" src="${path}/js/mapmove/initmap-Baidu.js"></script>
	<script src="${path}/js/jquery.js" type="text/javascript"></script>	
	<script type="text/javascript" src="${path}/js/toast.js"></script>	
</head>
<body>
<div style="background-color:#CCCCCC; z-index:9999; position:relative; height:auto; width:100%;">
		<span id="spanMsg" style="color:Red;"></span>  
		<input name="areacode" id="areacode"  value="${areacode}"   type="text" style="display:none"/>
</div>
<div id="map_canvas"></div>
</body>
<script type="text/javascript">
var path = "${path}";
var map = null;
$(function (){	
	//initBaiduMap(0, 0, 0);
	    var areacode = $("#areacode").val();
    if(areacode==null || areacode=='')
    	return;
	 				map = new BMap.Map("map_canvas", {}); 
				    map.centerAndZoom(new BMap.Point(105.000, 38.000), 5);     // 初始化地图,设置中心点坐标和地图级别
				    map.enableScrollWheelZoom();                        //启用滚轮放大缩小
				    if (document.createElement('canvas').getContext) {  // 
				    	$.post(path+"/ajax/showSitesMap2.htm?areaCode1="+areacode+"&&rnd="+Math.random(),
				    			function (data){
				    			    var dataList = data.list;
				    			    if(dataList!=null && dataList.length > 0){
				    			    	map.centerAndZoom(new BMap.Point(dataList[0].longitude, dataList[0].latitude), 5); 
				    			    	var points = [];  // 添加海量点数据
								        //alert(dataList[0].longitude);
								        for (var i = 0; i < dataList.length; i++) {
								          
								          points.push(new BMap.Point(dataList[i].longitude, dataList[i].longitude));
								        }
								        
								        var options = {
								            size: BMAP_POINT_SIZE_SMALL,
								            shape: BMAP_POINT_SHAPE_STAR,
								            color: '#d340c3'
								        }
								        var pointCollection = new BMap.PointCollection(points, options);  // 初始化PointCollection
								        alert(points[0].lng);
								        pointCollection.addEventListener('click', function (e) {
								          alert('单击点的坐标为：' + e.point.lng + ',' + e.point.lat);  // 监听点击事件
								        });
								        map.addOverlay(pointCollection);  // 添加Overlay
				    			    }
				    			}
				    	);
					}
				    	
	
	//serchLocation();
});
function serchLocation(){
    var areacode = $("#areacode").val();
    if(areacode==null || areacode=='')
    	return;
	
    var datalist=null;
	$.post(path+"/ajax/showSitesMap2.htm?areaCode1="+areacode+"&&rnd="+Math.random(),
			function (data){
			    var dataList = data.list;
			    if(dataList!=null && dataList.length > 0){
				    map = new BMap.Map("map_canvas", {}); 
				    map.centerAndZoom(new BMap.Point(dataList[0].longitude, dataList[0].latitude), 11);     // 初始化地图,设置中心点坐标和地图级别
				    map.enableScrollWheelZoom();                        //启用滚轮放大缩小
				    if (document.createElement('canvas').getContext) {  // 判断当前浏览器是否支持绘制海量点
				        var points = [];  // 添加海量点数据
				        //alert(dataList[0].longitude);
				        for (var i = 0; i < dataList.length; i++) {
				          
				          points.push(new BMap.Point(dataList[i].longitude, dataList[i].longitude));
				        }
				        var options = {
				            size: 6,
				            shape: 2,
				            color: '#d340c3'
				        }
				        var pointCollection = new BMap.PointCollection(points, options);  // 初始化PointCollection
				        pointCollection.addEventListener('click', function (e) {
				          alert('单击点的坐标为：' + e.point.lng + ',' + e.point.lat);  // 监听点击事件
				        });
				        map.addOverlay(pointCollection);  // 添加Overlay
				    } else {
				        alert('请在chrome、safari、IE8+以上浏览器查看本示例');
				    }
			    }else{
					new Toast({context:$('body'),message:'无位置信息！'}).show();
				}
	}
	);
	
}
  </script>
  
</html>

