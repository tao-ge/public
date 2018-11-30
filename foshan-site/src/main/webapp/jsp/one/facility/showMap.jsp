<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
String rc = request.getContextPath(); 
request.setAttribute("path",rc);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;}
#l-map{height:100%;width:78%;float:left;border-right:2px solid #bcbcbc;}
#r-result{height:100%;width:20%;float:left;}
</style>
<script src="${path}/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.4"></script>
<script type="text/javascript" src="http://developer.baidu.com/map/jsdemo/demo/convertor.js"></script>
<script type="text/javascript" src="${path}/js/prompt/ymPrompt.js"></script>
<link rel="stylesheet" id='skin' type="text/css" href="${path}/js/prompt/skin/ymPrompt.css" />
<meta http-equiv="Content-Type" content="text/html; charset=gbk"/>
<title>地图</title>
</head>
<body>
<div id="allmap"></div>
</body>
</html>
<script type="text/javascript">
	//GPS
 	function  getQueryString (name) {
        if (location.href.indexOf("?") == -1 || location.href.indexOf(name + '=') == -1) {
            return '';
        }
 
        var queryString = location.href.substring(location.href.indexOf("?") + 1);
        queryString = decodeURI(queryString);
 
        var parameters = queryString.split("&");
 
        var pos, paraName, paraValue;
        for (var i = 0; i < parameters.length; i++) {

            pos = parameters[i].indexOf('=');
            if (pos == -1) { continue; }
 
            paraName = parameters[i].substring(0, pos);
            paraValue = parameters[i].substring(pos + 1);
 
            if (paraName == name) {
                return unescape(paraValue.replace(/\+/g, " "));
            }
        }
        return '';
    }
	
	 //"lng":"120.305456","lat":"31.570037","zoom":"13"
	var xx = 120.305456;
	var yy = 31.570037;
	var jizPoint = new BMap.Point(xx,yy);
	
	if(getQueryString("plng")!=null && getQueryString("plng")!="" && getQueryString("plat")!=null && getQueryString("plat")!=""  )
	{
		jizPoint = new BMap.Point(getQueryString("plng"),getQueryString("plat"));
	}
	else
	{
		alert("无位置信息");
	
	}
	var bj=500;
	if(getQueryString("pbanjing")!="" && getQueryString("pbanjing")!=null)
	 {
		bj =getQueryString("pbanjing");
	 }
	
	// 百度地图API功能
	var map = new BMap.Map("allmap");
	var point = jizPoint;
	map.centerAndZoom(point, 20);
	map.enableScrollWheelZoom();
	var marker = new BMap.Marker(point);  // 创建标注
	map.enableScrollWheelZoom();
	map.enableContinuousZoom();
	map.addOverlay(marker);               // 将标注添加到地图中
// 	marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	
	
// 	var bm = new BMap.Map("allmap");
// 	bm.centerAndZoom(jizPoint, 18);
// 	map.enableScrollWheelZoom();
// 	bm.addControl(new BMap.NavigationControl());
	
// 	var removeMarker = function(e, ee, marker) {
// 		bm.removeOverlay(marker);
// 	}
// 	var myIcon = new BMap.Icon(path+"/images/devType/biaoshi_icon.png", new BMap.Size(34,49),{
// 	    anchor: new BMap.Size(15, 47)
// 	});
//     var marker = new BMap.Marker(bm,{icon:myIcon});
// 	bm.addOverlay(marker);
	
// 	var myGeo = new BMap.Geocoder();
// 	var marker;
// 	bm.enableScrollWheelZoom();
// 	bm.enableContinuousZoom();
	
// 	var address ;
// 	translateCallback = function (point){
// 	    var marker = new BMap.Marker(point);
// 	    bm.addOverlay(marker);
	
// 	    myGeo.getLocation(point, function(rs){
// 	    	var addComp = rs.addressComponents;
// 	    	address = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
// 	        var label = new BMap.Label(address,{offset:new BMap.Size(20,-10)});
// 	        marker.setLabel(label); 
// 	        bm.setCenter(point);
	
// 	    	var circle = new BMap.Circle(jizPoint,20);
// 	    	  circle.enableMassClear();
// 	          circle.setStrokeWeight(1);
// 	          circle.setStrokeStyle("solid");
// 	          circle.setFillColor("#0000FF");
// 	          circle.setFillOpacity(0.06);
// 	    	bm.addOverlay(circle);
// 	    });
	
// 	}
// 	setTimeout(function(){
// 	    BMap.Convertor.translate(jizPoint,0,translateCallback);
// 	}, 0);


</script>

