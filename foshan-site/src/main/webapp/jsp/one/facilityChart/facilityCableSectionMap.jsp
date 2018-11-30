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
<title>拓扑图</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">

<style type="text/css">
		body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
		#l-map{height:100%;width:78%;float:left;border-right:2px solid #bcbcbc;}
		#r-result{height:100%;width:20%;float:left;}
		.button{
			background-color: #008CBA; 
		    border: none;
		    color: white;
		    padding: 15px 32px;
		    text-align: center;
		    text-decoration: none;
		    display: inline-block;
		    font-size: 8px;
		    border-radius: 12px;
		    buttonStyleName: myCustomButtonStyleName;
		}
		.myCustomButtonStyleName {
            color: #ffffff;
            cornerRadius: 12;
           background-color:red;
        }
        
        .sel_btn{
            height: 21px;
            line-height: 21px;
            padding: 0 11px;
            background: #02bafa;
            border: 1px #26bbdb solid;
            border-radius: 3px;
            /*color: #fff;*/
            display: inline-block;
            text-decoration: none;
            font-size: 15px;
            outline: none;
        }
        .ch_cls{
            background: #e4e4e4;
        }
</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=zKysWtyCEKznq9k2BYmcSaVoV4y0yGB7"></script>


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
var path = "${path}";
</script>
</head>
<body>
	<div class="place">
		<span>位置：</span>
	    <ul class="placeul">
	    <li><a href="#">首页</a></li>
	    <li><a href="#">资源台账管理</a></li>
	    <li><a href="#">设施管理</a></li>
	    <li><a href="#">拓扑图</a></li>
	    </ul>
	</div>
	<div class="formbody">
		<div id="usual1" class="usual">
<%-- 			<jsp:include page="/jsp/one/cable/cableDetailNav.jsp" />  --%>
		</div>
			<!-- 2017-9-26 刘沧海添加 -->
	</div>
	<div id="allmap"></div>
	
</body>
<script type="text/javascript">
	var path = "${path}";
	$(function() {
		// 百度地图API功能
		var baiduX = "${baiduX}";
		var baiduY = "${baiduY}";
		if(baiduX =="" || baiduY==""){
			alert("无设施信息！");
			window.close();//关闭之前窗口
			return;
		}
		var map = new BMap.Map("allmap");
		map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
		map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
		//添加比例尺控件
		var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
		var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
		var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL}); //右上角，仅包含平移和缩放按钮
		map.addControl(top_left_control);        
		map.addControl(top_left_navigation);     
		map.addControl(top_right_navigation); 
		
		var point = new BMap.Point(baiduX, baiduY);
		map.centerAndZoom(point, 15);
		// 编写自定义函数,创建标注
		function addMarker(point){
		  var marker = new BMap.Marker(point);
		  map.addOverlay(marker);
		}
	    <c:forEach items="${faList}" var="item" varStatus="status" >  
			var type = "${item.devType}";
			var typeName = null;
			var myIcon = null;
			if ("${item.devType}" == "01") {
				typeName = "光交箱";
				myIcon = new BMap.Icon(path+"/images/devType/icon_marka.png", new BMap.Size(34,49),{
				    anchor: new BMap.Size(15, 47)
				});
			} else if ("${item.devType}" == "02") {
				typeName = "光终端盒";
				myIcon = new BMap.Icon(path+"/images/devType/xiang_icon.png", new BMap.Size(34,49),{
				    anchor: new BMap.Size(15, 47)
				});
			} else if ("${item.devType}" == "03") {
				typeName = "光分纤箱";
				myIcon = new BMap.Icon(path+"/images/devType/xiang_icon.png", new BMap.Size(34,49),{
				    anchor: new BMap.Size(15, 47)
				});
			} else if ("${item.devType}" == "04") {
				typeName = "光缆接头";
				myIcon = new BMap.Icon(path+"/images/devType/xiang_icon.png", new BMap.Size(34,49),{
				    anchor: new BMap.Size(15, 47)
				});
			} else if ("${item.devType}" == "05") {
				typeName = "ODF";
				myIcon = new BMap.Icon(path+"/images/devType/xiang_icon.png", new BMap.Size(34,49),{
				    anchor: new BMap.Size(15, 47)
				});
			} else if ("${item.devType}" == "06") {
				typeName = "分光器";
				myIcon = new BMap.Icon(path+"/images/devType/icon_geo.png", new BMap.Size(34,49),{
				    anchor: new BMap.Size(15, 47)
				});
			} else if ("${item.devType}" == "07") {
				typeName = "PTN";
			} else if ("${item.devType}" == "20") {
				typeName = "机房";
				myIcon = new BMap.Icon(path+"/images/devType/jifang_icon.png", new BMap.Size(34,49),{
				    anchor: new BMap.Size(15, 47)
				});
			} else {
				typeName = "未知类型";
			}
	    
	        var lng = "${item.baiduX}";  
	        var lat = "${item.baiduY}";
	        if(lng!=""&&lat!=""){
	        	var point = new BMap.Point(lng, lat);
	        	var marker = new BMap.Marker(point,{icon:myIcon});

	  		  	var removeMarker = function(e,ee,marker){
					map.removeOverlay(marker);
				}
				var markerMenu=new BMap.ContextMenu();
				markerMenu.addItem(new BMap.MenuItem('删除',removeMarker.bind(marker)));

				var label = new BMap.Label("${item.devName}",{offset:new BMap.Size(15,-5)});
				label.setTitle("${item.devName}");
				
				marker.setLabel(label);
	        	marker.addEventListener("click", function(e){ 
	        		
	        		var point = new BMap.Point("${item.baiduX}","${item.baiduY}");
		        	var title = "";
					title = "设施名称：" + "${item.devName}" + "<br>设施类型："
							+ "${item.devTypeName}" + "<br>百度X：" + "${item.baiduX}"
							+ "<br>百度Y：" + "${item.baiduY}";
		        	var opts = {
	        			width: 250, // 信息窗口宽度
	        			height: 80, // 信息窗口高度
	        			title:"", // 信息窗口标题
	        			enableMessage: false,//设置允许信息窗发送短息
	        		}
		        	var infowindow = new BMap.InfoWindow(title, opts);
		        	map.openInfoWindow(infowindow, point);
				});
	        	marker.addContextMenu(markerMenu);
	  		  	map.addOverlay(marker);
	        } 
	    </c:forEach>
	    //光缆段连线
	    <c:forEach items="${cableList}" var="item" varStatus="status" >  
	    	var lng = "${item.afacility.baiduX}";  
		    var lat = "${item.afacility.baiduY}";
		    var areaCode1 = "${item.zfacility.baiduX}";  
		    var areaCode2 = "${item.zfacility.baiduY}";
		        
		    var a = new BMap.Point(lng, lat);
		    var b = new BMap.Point(areaCode1, areaCode2);
			//alert(lng+"=="+lat+"--"+areaCode1+"=="+areaCode2);

		    fiber = [ a, b ];

		   	var line = new BMap.Polyline(fiber, {
		    	strokeColor : "blue",
		    	strokeWeight : 4,
		    	strokeOpacity : 0
		    }); //创建折线对象
		    line.addEventListener("click",function(e){
 				var p = e.target;
 				if (p instanceof BMap.Polyline) {
 					var content="名称：    "+"${item.secName}" +"\n纤芯数:  "+"${item.fiberNum}"+"\n编码:    "+"${item.sectCode}";
 					alert(content);
//  					layer.alert(content, {
//  			            icon: 1,
//  			            skin: 'layer-ext-moon' //该皮肤由layer.seaning.com友情扩展。关于皮肤的扩展规则，去这里查阅
//  			        });
 				}
 			});
		    map.addOverlay(line);
    	</c:forEach>
//     	function loadEdit(devId,sectId)
//     	{
//     		var url='';
//     		var title = "";
//     		if(devId!="" && sectId!=""){
//     			if(sectId=="0"){
//     				title = "熔钎盘信息";
//     				url=path+"/fiberdiscChart.htm?devId="+devId;
//     			}else{
//     				title = "设施信息";
//     				url=path+"/wellInfoList.htm?devId="+devId+"&sectId="+sectId;
//     			}
    			 
//     		}else{
//     			return;
//     		}
    		
    		
    		
//     		var index = layer.open({
//     			  type: 2,
//     			  title: title,
//     			  content: url,
//     			  area: ['1000px', '520px'],
//     			  maxmin: true
//     			});
    		
//     			//layer.full(index);
//     	}
	});	
	
	
	
	
</script>

</html>