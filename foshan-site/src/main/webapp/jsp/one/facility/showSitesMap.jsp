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
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
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
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZLT8TTF1xGqIslUb0t659pGWnduCdGVf"></script>	
	<script type="text/javascript" src="${path}/js/convertor.js"></script>
	<script src="${path}/js/jquery.js" type="text/javascript"></script>
	<script type="text/javascript" src="${path}/js/mapmove/initmap-Baidu.js"></script>
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
	var map = null;
	var path = "${path}";
	var centerCircle;
	$(function (){
		initBaiduMap(0, 0, 16);
		//initBaiduMap(23.09,114.40,18);
		serchLocation();		
	});
	
	function serchLocation(){
	    if (map) {
	        map.clearOverlays();
	    }	    
	    
	    var areacode = $("#areacode").val();
	    if(areacode==null || areacode=='')
	    	return;
	    
	    $("#spanMsg").html("正在加载");
	    $("#btnPlay").attr("disabled",true);
	    

	    	
		$.post(path+"/ajax/showSitesMap.htm?areaCode1="+areacode+"&&rnd="+Math.random(),
				function (data){
				    var dataList = data.list;
				    if(dataList!=null && dataList.length > 0){
						/*map = new BMap.Map("map_canvas");
						var point = new BMap.Point(dataList[0].longitude, dataList[0].latitude);
					    map.centerAndZoom(point, 18);
					    map.addControl(new BMap.ScaleControl());// 添加默认比例尺控件
					    map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
					    map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
					    map.addControl(new BMap.MapTypeControl());      //切换地图类型
					    map.enableInertialDragging();　// 开启惯性拖拽效果		
					    */
					    var point = new BMap.Point(dataList[0].longitude, dataList[0].latitude)
					    map.centerAndZoom(point, 16);
					    //范围
					    map.removeOverlay(centerCircle);									    
					    centerCircle = new BMap.Circle(point, 500);//为半径
					    centerCircle.enableMassClear();//允许覆盖物在map.clearOverlays方法中被清除
					    centerCircle.setStrokeWeight(1);//边线的宽度
					    centerCircle.setStrokeStyle("solid");//边线样式 ：实线/虚线 solid/dashed
					    centerCircle.setFillColor("#0000FF");//圆的填充颜色
					    centerCircle.setFillOpacity(0.06);//圆的填充透明度
				  	    map.addOverlay(centerCircle);
					    
					    var top_left_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_LEFT, type: BMAP_NAVIGATION_CONTROL_SMALL}); //右上角，仅包含平移和缩放按钮
					    map.addControl(top_left_navigation); 	
					    $.each(dataList,findLbsAndShowPoint);
					    

					    
						map.addEventListener("dragend", function showInfo(e){//鼠标拖拽后触发								
							   map.setCenter(e.point);
					    	   map.clearOverlays(); 
						   	    $("#spanMsg").html("正在加载");
							    $("#btnPlay").attr("disabled",true);
					    	   $.post(path+"/ajax/showDragendMap.htm?areaCode1="+areacode+"&&curLng="+e.point.lng+"&&curLat="+e.point.lat+"&&rnd="+Math.random(),
					   				function (data){
						    		   var dataList = data.list;
									    if(dataList!=null && dataList.length > 0){
									    	$.each(dataList,findLbsAndShowPoint);
									    }
									    //范围
									    map.removeOverlay(centerCircle);									    
									    centerCircle = new BMap.Circle(e.point, 500);//为半径
									    centerCircle.enableMassClear();//允许覆盖物在map.clearOverlays方法中被清除
									    centerCircle.setStrokeWeight(1);//边线的宽度
									    centerCircle.setStrokeStyle("solid");//边线样式 ：实线/虚线 solid/dashed
									    centerCircle.setFillColor("#0000FF");//圆的填充颜色
									    centerCircle.setFillOpacity(0.06);//圆的填充透明度
								  	    map.addOverlay(centerCircle);
									    
										$("#spanMsg").html("");
										$("#btnPlay").attr("disabled",false);
						    	    }
						   		 );	    	   
					    	});	
				    }else{
						new Toast({context:$('body'),message:'无位置信息！'}).show();
					}
					
					$("#spanMsg").html("");
					$("#btnPlay").attr("disabled",false);
				}
		);
		
	}
	
	
	var content;
	function findLbsAndShowPoint(i,f){
		if(f.longitude == null || map == null){
			return;
		}

	  	var point = new BMap.Point(f.longitude, f.latitude);
	  	
	    var index = 0;//坐标图片
	    if(f.surveyResult==0){//未普查
	    	index = 0;
	    }else if(f.surveyResult==1){//已普查
	    	index = 1;
	    }else{//无设施
	    	index = 2;
	    }	    	    	     
	    
	    var myIcon = new BMap.Icon("http://api.map.baidu.com/img/markers.png", new BMap.Size(23, 25), {  
	    	  
	        offset: new BMap.Size(10, 25),  
	      
	        imageOffset: new BMap.Size(0, 0 - index * 25)  
	      
	      });  
	  	
	    var marker = new BMap.Marker(point,{icon: myIcon});
	    
	    addressLabel = new BMap.Label(f.devName, {offset:new BMap.Size(20,-10)});
	    marker.setLabel(addressLabel); //添加百度label
	    


        map.addOverlay(marker);

      	var addressLabel;
      	var circle;
      	marker.addEventListener("mouseover", function(){
    	    //addressLabel = new BMap.Label(f.devName, {offset:new BMap.Size(20,-10)});
    	    //this.setLabel(addressLabel); //添加百度label
    	    
        	circle = new BMap.Circle(point, 500);//为半径
	  	  	circle.enableMassClear();//允许覆盖物在map.clearOverlays方法中被清除
	  	        circle.setStrokeWeight(1);//边线的宽度
	  	        circle.setStrokeStyle("solid");//边线样式 ：实线/虚线 solid/dashed
	  	        circle.setFillColor("#0000FF");//圆的填充颜色
	  	        circle.setFillOpacity(0.06);//圆的填充透明度
	  	    map.addOverlay(circle);
        });
        
        marker.addEventListener("mouseout", function(){
	  	    //map.removeOverlay(addressLabel);
	  	    map.removeOverlay(circle);
        });
        
        marker.addEventListener("click", function(){        	
        			var info = "";
        			if(f.devAddr==null)
        				f.devAddr = "无";

					info = "光交箱：<a target=\"_blank\" href=\""+path+"/facilityBaseInfo.htm?devId="+f.devId+"\" >"+f.devName+"</a><br/>" 
				      	  +"设施编码："+f.devCode+"<br/>" 
				      	  +"设施类型："+f.devTypeName+"<br/>"
				      	  +"设施型号："+f.devModel+"<br/>"
				      	  +"所属站点："+f.site+"<br/>"
				      	  +"详细地址："+f.devAddr+"<br/>"
				      	  +"普查结果："+f.surveyResultName+"<br/>";

					
			      	//创建信息窗口
		        	var infoWindow = new BMap.InfoWindow("<div style='font-size:12px;'>"+info+"</div>", {
		        		title: "",     //标题
		        		offset: new BMap.Size(-5,-25),
		        		enableMessage:false //是否启用发送到手机
		        	});
		        	map.openInfoWindow(infoWindow, point);        	
        });
	}
	
</script>
</html>

