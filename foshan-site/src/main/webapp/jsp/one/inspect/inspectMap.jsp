<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% 
String rc = request.getContextPath(); 
request.setAttribute("path",rc);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${path}/css/style.css" rel="stylesheet" type="text/css" />
		<link href="${path}/css/select.css" rel="stylesheet" type="text/css" />
		<script  type="text/javascript" src="${path }/js/jquery.js"></script>
		<script  type="text/javascript" src="${path }/js/layer.js"></script>	
		<script type="text/javascript"> path='${path }'; </script>
		<script  type="text/javascript" src="${path }/js/prompt/ymPrompt.js"></script>
		<link rel="stylesheet"  type="text/css" href="${path}/js/prompt/skin/ymPrompt.css" />
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=zKysWtyCEKznq9k2BYmcSaVoV4y0yGB7"></script>
		<title>巡检日志查询</title>
	
	</head>
	<body >
		<div id="div_devlist" class="newlayer">
					<div style="width:700px;height:500px;border:#ccc solid 1px;" id="dituContent">
						<p style="FONT-SIZE: 46px" align=center>无轨迹信息！ </p>
					</div>
		</div>
		<script type="text/javascript">
		    //创建和初始化地图函数：
		    function initMap(){
		    	var str =${str};
		    	if(str != null && str!='1'){
		    		createMap();//创建地图
			        setMapEvent();//设置地图事件
			        addMapControl();//向地图添加控件
			        addMarker();//向地图中添加marker
		    	}
		        
		    }
		    
		    //创建地图函数：
		    function createMap(){
		        var map = new BMap.Map("dituContent");//在百度地图容器中创建一个地图
		        
		        var markerArr =${str};
		        var json = markerArr[0];
		        var p0 = json.point.split("|")[0];
		        var p1 = json.point.split("|")[1];
		        var point = new BMap.Point(p0,p1);
		        //var point = new BMap.Point(114.075562,24.956022);//定义一个中心点坐标
		        map.centerAndZoom(point,16);//设定地图的中心点和坐标并将地图显示在地图容器中
		        window.map = map;//将map变量存储在全局
		    }
		    
		    //地图事件设置函数：
		    function setMapEvent(){
		        map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
		        map.enableScrollWheelZoom();//启用地图滚轮放大缩小
		        map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
		        map.enableKeyboard();//启用键盘上下左右键移动地图
		    }
		    
		    //地图控件添加函数：
		    function addMapControl(){
		        //向地图中添加缩放控件
			var ctrl_nav = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
			map.addControl(ctrl_nav);
		        //向地图中添加缩略图控件
			var ctrl_ove = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:0});
			map.addControl(ctrl_ove);
		        //向地图中添加比例尺控件
			var ctrl_sca = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
			map.addControl(ctrl_sca);
		    }
		   
		    
		 
		    //创建marker
		    function addMarker(){
		    	var markerArr =${str};
		    	var pointArr = new Array();
		        for(var i=0;i<markerArr.length;i++){
		            var json = markerArr[i];
		            var p0 = json.point.split("|")[0];
		            var p1 = json.point.split("|")[1];
		            var point = new BMap.Point(p0,p1);
		            pointArr[i] = point;
					var iconImg = createIcon(json.icon);
		            var marker = new BMap.Marker(point);
					//var iw = createInfoWindow(i);
					var label = new BMap.Label(json.title,{"offset":new BMap.Size(json.icon.lb-json.icon.x+10,-20)});
					marker.setLabel(label);
		           
		            label.setStyle({
		                        borderColor:"#808080",
		                        color:"#333",
		                        cursor:"pointer"
		            });
		            if(i==markerArr.length-1){
		            	marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
		            }
		            map.addOverlay(marker);
		        }
		        var polyline = new BMap.Polyline(pointArr,{strokeColor:"blue", strokeWeight:6, strokeOpacity:0.5});    
		        map.addOverlay(polyline);
		    }
		 
		    //创建一个Icon
		    function createIcon(json){
		        var icon = new BMap.Icon("http://app.baidu.com/map/images/us_mk_icon.png", new BMap.Size(json.w,json.h),{imageOffset: new BMap.Size(-json.l,-json.t),infoWindowOffset:new BMap.Size(json.lb+5,1),offset:new BMap.Size(json.x,json.h)})
		        return icon;
		    }
		
		    initMap();
		</script>
	</body>
</html>