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
.tools div button {
    background: #a9ccff no-repeat;
    line-height: 22px;
    height: 22px;
    border: solid 1px #1c91e3;
    border-radius: 3px;
    behavior: url(js/pie.htc);
    cursor: pointer;
}

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
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10"/>
	<meta http-equiv="x-ua-compatible" content="IE=10" >
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="${path}/css/style.css" rel="stylesheet" type="text/css" />
	<link href="${path}/css/select.css" rel="stylesheet" type="text/css" />
	<script  type="text/javascript" src="${path }/js/jquery.js"></script>	
	<script  type="text/javascript" src="${path }/js/layer.js"></script>
	<script type="text/javascript"> path='${path }'; </script>
	<script  type="text/javascript" src="${path }/js/facility/facilityList.js"></script>
	<script  type="text/javascript" src="${path }/js/prompt/ymPrompt.js"></script>
	<link rel="stylesheet"  type="text/css" href="${path}/js/prompt/skin/ymPrompt.css" />
</head>
<body>

 <!-- 页面导航  -->
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
		    <li><a href="index.htm">首页</a></li>
		    <li><a href="index.htm">智能化设备管理</a></li>
		    <li><a href="devStatusList.htm">地图浏览</a></li>
	    </ul>
    </div>
	<div style="background-color: #E8E8E8;height:10px;">
	</div>
	 <div style="background-color: #E8E8E8;height: 45px;">
		 <div class="tools">
			 	<input name="areacode" id="areacode" value="${areacode}" type="hidden"/>
			 	<input name="baiduXY" id="baiduX"  type="hidden" />
			 	<input name="baiduXY" id="baiduY"  type="hidden" />
			 	<input name="centerName" id="centerName"  type="hidden" value="${centerName}" />
				<input name="areaName" id="areaName" value="${areaName}" type="text" style="border-bottom: solid 1px #1c91e3; background: url(../images/inputbg.gif) repeat-x;text-indent: 10px;width: 320px;height: 32px;line-height: 32px;border-top: solid 1px #1c91e3; border-left: solid 1px #1c91e3; border-right: solid 1px #1c91e3;margin-left: 1%" readonly="readonly"/>
			 	<button onclick="select()" style="padding-right: 10px;margin-right: 5px;border-radius: 3px;behavior: url(js/pie.htc);cursor: pointer;background: #a9ccff no-repeat;line-height: 33px;height: 33px;border: solid 1px #1c91e3;padding-left: 10px;">选择区域</button>
				<input name="devName" placeholder="设施名称" onkeyup="this.value=this.value.replace(/\s+/g,'')" id="devName" value="${devName}" type="text" style="border-bottom: solid 1px #1c91e3; background: url(../images/inputbg.gif) repeat-x;text-indent: 10px;width: 80px;height: 32px;line-height: 32px;border-top: solid 1px #1c91e3; border-left: solid 1px #1c91e3; border-right: solid 1px #1c91e3;"/>
				<input type="button" onclick="jihuo()" value="查询"  class="submit"/>
				<button onclick="flush()" style=" padding-right: 10px;margin-right: 5px;border-radius: 3px;behavior: url(js/pie.htc);cursor: pointer;background: #a9ccff no-repeat;line-height: 33px;height: 33px;border: solid 1px #1c91e3;padding-left: 10px;">刷新</button> 
				<!-- <button id="stop" onclick="Stopflush()"  style="position:absolute; margin-left:-450px;">停止自动刷新</button> -->
				<img id="img" src="${path}/images/devType/bj02.gif"  style="margin-left: 80%;margin-top: -38px">
				<img id="img1" src="${path}/images/devType/bj04.gif"  style="margin-left: 90%;margin-top: -38px">
		</div>
		<div></div>
	</div>
	<div id="map_canvas"></div>
	 <div id="div_areaSearch" class="newlayer" style="display: none;">
			<table class="tablelist">
			<thead>
				<tr>
					<th>区域名称</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${areaList}" var="f">
					<tr>
						<td>${f.parentAreaName}</td>
						<c:if test="${f.parentAreaName==areaName}">
							<td><input name="checkbox" type="checkbox" value="${f.codeAndName}" checked="checked" /></td>
						</c:if>
						<c:if test="${f.parentAreaName!=areaName}">
							<td><input name="checkbox" type="checkbox" value="${f.codeAndName}" /></td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="tools">
			 	<div class="simpleQuery">
					<a href="javascript:;" id="b1" type="button" ><button  class="tablelink">提交查询</button></a>
				</div>
			</div>
	</div> 
	<a id="fff" href="javascript:;"  target="downMapFrame"><li id="device">查询下方表格数据</li></a>
</body>
<script type="text/javascript">
	var path = "${path}";
	var map = null;
	var sign = 0;
	//刷新按钮
	function flush(){
		aa();
	}
	
	//提交赋值过程
	$("#b1").click(function(){
            //$('input:checkbox:checked') 等同于 $('input[type=checkbox]:checked')
            //意思是选择被选中的checkbox
            var valueCodes = new Array();
            var valueNames = new Array();
            $.each($('input:checkbox:checked'),function(){
                    var value=$(this).val();
                    strs=value.split(","); //字符分割 
                    valueCodes.push(strs[0]);
                  //如果为市辖区则把上级市拼接查询
                    if(strs[1] == '市辖区'){
                    	valueNames.push($("#centerName").val()+strs[1]);
                    }else{
                    	valueNames.push(strs[1]);
                    }
            });
			$("#areaName").val(valueNames);
			$("#areacode").val(valueCodes);
			layer.close(layer.index);
			aa();
        });
	
	
	function jihuo(){
		aa();
	}

	
	//显示设施列表
	function select(){
		layer.open({
			type : 1,
			closeBtn : 2,
			title : [ '所属区列表', 'font-size:18px;' ],
			area : [ '350px', '450px' ],
			shadeClose : true, // 点击遮罩关闭
			content : $("#div_areaSearch"),
		});
		
	}
	//点击按位置浏览,初始化地图	
	var j = 0;
	$(function() {
		//j = setInterval(aa,1000*60);//自动刷新页面
		aa();
	});
	
	
	function aa(){
		var devId = "";
		var devName = "";
		var areacode = $("#areacode").val();
		var areaName = $("#areaName").val();
		map = new BMap.Map("map_canvas", {});
		var centerName = $("#centerName").val();
		var devName = $("#devName").val();
 	//	$('#fff').attr('href',path+"/querydevTimeNewStatu.htm?devName="+devName+"&devId="+devId+"&areaCode1="+areacode); 
 	//	$("#device").trigger("click");
 		//$('#fff').attr('href',path+"/querySwitchRecord.htm?devName="+devName+"&devId="+devId+"&areaCode1="+areacode); 
 	//	$("#device").trigger("click");
 	//	$('#fff').attr('href',path+"/queryDeviceAlarm.htm?devName="+devName+"&devId="+devId+"&areaCode1="+areacode); 
 	//	$("#device").trigger("click"); 
		//初始化地图
		/* var point = new BMap.Point(114.412315,23.06995);
		map.centerAndZoom(point,9); */
		if(areaName != null){
			var p0 = areaName.split(",");
			if(p0.length>1){//超过两个，用市
				map.centerAndZoom(centerName, 10);
			}else{//只有一个，用当前名字
				map.centerAndZoom(areaName, 10);
			}
		}else {
			map.centerAndZoom(areaName, 9);
		}
		map.enableScrollWheelZoom(); //启用滚轮放大缩小
		map.addControl(new BMap.NavigationControl());
		if (document.createElement('canvas').getContext) { // 
			var points = [];
			$.ajax({
				type : "post",
				url : path + "/queryDeviceStateList.htm?devName="+devName+"&areaCode1="+areacode,
				dataType : "json",
				success : function(data) {
					var isStatus = data.bj;
					var isWorkStatus = data.gz;
					if(isStatus == '1'){//报警 红色
						$('#img').attr('src',path+"/images/devType/bj01.gif");
					}
					if(isStatus == '0'){//报警 红色
						$('#img').attr('src',path+"/images/devType/bj02.gif");
					}
					if(isWorkStatus == '1'){//工作 绿色 // 0 ，正常 蓝色
						$('#img1').attr('src',path+"/images/devType/bj03.gif");
					}
					if(isWorkStatus == '0'){//工作 绿色 // 0 ，正常 蓝色
						$('#img1').attr('src',path+"/images/devType/bj04.gif");
					} 
					var data = data.list;
					for (var i = 0; i < data.length; i++) {
						 var point = new BMap.Point(data[i].baiduX,
								data[i].baiduY);
						var state = data[i].workState;
						
						if(state == '03'){//报警 红色
							$('#img').attr('src',path+"/images/devType/bj01.gif");
						}
						if(state != '03'){//报警 红色
							$('#img').attr('src',path+"/images/devType/bj02.gif");
						}
						
						if(state == '02'){//工作 绿色 // 0 ，正常 蓝色
							$('#img1').attr('src',path+"/images/devType/bj03.gif");
						}
						if(state == '01'){//工作 绿色 // 0 ，正常 蓝色
							$('#img1').attr('src',path+"/images/devType/bj04.gif");
						} 
						
						var devId =  data[i].devId;
						var devName = data[i].devName;
						var typeName = null;
						if (state == "01") {
							typeName = "正常";
							var myIcon = new BMap.Icon(path+"/images/devType/xiang-zc.png", new BMap.Size(34,49),{
							    anchor: new BMap.Size(15, 47)
							});
						} else if (state == "02") {
							typeName = "工作中";
							var myIcon = new BMap.Icon(path+"/images/devType/xiang-gz.gif", new BMap.Size(34,49),{
							    anchor: new BMap.Size(15, 47)
							});
						} else if (state == "03") {
							typeName = "报警";
							var myIcon = new BMap.Icon(path+"/images/devType/xiang-bj.gif", new BMap.Size(34,49),{
							    anchor: new BMap.Size(15, 47)
							});
						} else {
							typeName = "未知类型";
						}
						
						var marker = new BMap.Marker(point,{icon:myIcon});  // 创建标注
						map.addOverlay(marker);              // 将标注添加到地图中
						
						var label = new BMap.Label(data[i].devName,{offset:new BMap.Size(20,-10)});
						
						var title = "";
						title = "设施名称：" + data[i].devName + 
								"<br>设施编码："+ data[i].devCode + 
								"<br>中控器工作状态："+ typeName + 
								"<br>百度X：" + data[i].baiduX +
								"<br>百度Y：" + data[i].baiduY;
								
						label.setTitle(title);
						marker.setLabel(label);
						 addClickHandler(devId,devName);
						 function addClickHandler(devId,devName){ //点击设施查询下方表格
				        	marker.addEventListener("click",function(e){
					        		$('#fff').attr('href',path+"/queryImDeviceAlarm.htm?devName="+devName+"&devId="+devId); 
									$("#device").trigger("click");
				    			}
				    		);
					    } 
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

	}
	//停止自动刷新
	function Stopflush(){
		$("#stop").attr("style","color:red;position:absolute; margin-left:-450px;");
		window.clearInterval(j);
	} 
	
</script>

</html>
