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
	<script type="text/javascript" src="http://api.map.baidu.com/library/GeoUtils/1.2/src/GeoUtils_min.js"></script>
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

		<div style="width:100%;height:100%;border:1px solid gray" id="container"></div>
</body>

</html>
<script type="text/javascript">
	// 百度地图API功能114.30435900,22.93122600
	var map = new BMap.Map("container");
	var baiduX='114.30435900';
	var baiduY='22.93122600';
	map.centerAndZoom(new BMap.Point(baiduX, baiduY), 15);
	map.enableScrollWheelZoom();
	
	
	var apolygon_points1=new Array();
	var apolygon_points2=new Array();
	var apolygon_points3=new Array();
	var apolygon_points4=new Array();
	var apolygon_points5=new Array();
	var apolygon_points6=new Array();
	var apolygon_points7=new Array();
	var apolygon_points8=new Array();
	var apolygon_points9=new Array();
	var apolygon_points10=new Array();
	var apolygon_points11=new Array();
	var apolygon_points12=new Array();
	var apolygon_points13=new Array();
	var apolygon_points14=new Array();
	var apolygon_points15=new Array();
	var apolygon_points16=new Array();
	var apolygon_points17=new Array();
	var apolygon_points18=new Array();
	var apolygon_points19=new Array();
	var apolygon_points20=new Array();
	var apolygon_points21=new Array();
	var apolygon_points22=new Array();
	var apolygon_points23=new Array();
	var apolygon_points24=new Array();
	var apolygon_points25=new Array();
	var apolygon_points26=new Array();
	var apolygon_points27=new Array();
	var apolygon_points28=new Array();
	var apolygon_points29=new Array();
	var apolygon_points30=new Array();
	var apolygon_points31=new Array();
	var apolygon_points32=new Array();
	var apolygon_points33=new Array();
	var apolygon_points34=new Array();
	var apolygon_points35=new Array();
	var apolygon_points36=new Array();
	var apolygon_points37=new Array();
	var apolygon_points38=new Array();
	var apolygon_points39=new Array();
	var apolygon_points40=new Array();
	var apolygon_points41=new Array();
	var apolygon_points42=new Array();
	var apolygon_points43=new Array();
	var apolygon_points44=new Array();
	var apolygon_points45=new Array();
	var apolygon_points46=new Array();
	var apolygon_points47=new Array();
	
	
    <c:forEach items="${apolygon1}" var="item" varStatus="status" >  
    	apolygon_points1.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach> 
	
	<c:forEach items="${apolygon2}" var="item" varStatus="status" >  
		apolygon_points2.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach>
	
    <c:forEach items="${apolygon3}" var="item" varStatus="status" >  
		apolygon_points3.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach> 
	
	<c:forEach items="${apolygon4}" var="item" varStatus="status" >  
		apolygon_points4.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach>
	
	<c:forEach items="${apolygon5}" var="item" varStatus="status" >  
		apolygon_points5.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach> 
	
	<c:forEach items="${apolygon6}" var="item" varStatus="status" >  
		apolygon_points6.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach>
	
	<c:forEach items="${apolygon7}" var="item" varStatus="status" >  
		apolygon_points7.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach> 
	
	<c:forEach items="${apolygon8}" var="item" varStatus="status" >  
		apolygon_points8.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach>
	
	<c:forEach items="${apolygon9}" var="item" varStatus="status" >  
		apolygon_points9.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach> 

	<c:forEach items="${apolygon10}" var="item" varStatus="status" >  
		apolygon_points10.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach>
	
    <c:forEach items="${apolygon11}" var="item" varStatus="status" >  
		apolygon_points11.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach> 
	
	<c:forEach items="${apolygon12}" var="item" varStatus="status" >  
		apolygon_points12.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach>
	
	<c:forEach items="${apolygon13}" var="item" varStatus="status" >  
		apolygon_points13.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach> 
	
	<c:forEach items="${apolygon14}" var="item" varStatus="status" >  
		apolygon_points14.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach>
	
	<c:forEach items="${apolygon15}" var="item" varStatus="status" >  
		apolygon_points15.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach> 
	
	<c:forEach items="${apolygon16}" var="item" varStatus="status" >  
		apolygon_points16.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach>
	
	<c:forEach items="${apolygon17}" var="item" varStatus="status" >  
		apolygon_points17.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach> 
	
	<c:forEach items="${apolygon18}" var="item" varStatus="status" >  
		apolygon_points18.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach>
	
	<c:forEach items="${apolygon19}" var="item" varStatus="status" >  
		apolygon_points19.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach> 
	
	<c:forEach items="${apolygon20}" var="item" varStatus="status" >  
		apolygon_points20.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach>
	
	<c:forEach items="${apolygon21}" var="item" varStatus="status" >  
		apolygon_points21.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach> 
	
	<c:forEach items="${apolygon22}" var="item" varStatus="status" >  
		apolygon_points22.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach>
	
	<c:forEach items="${apolygon23}" var="item" varStatus="status" >  
		apolygon_points23.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach> 
	
	<c:forEach items="${apolygon24}" var="item" varStatus="status" >  
		apolygon_points24.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach>
	
	<c:forEach items="${apolygon25}" var="item" varStatus="status" >  
		apolygon_points25.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach> 
	
	<c:forEach items="${apolygon26}" var="item" varStatus="status" >  
		apolygon_points26.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach>
	
	<c:forEach items="${apolygon27}" var="item" varStatus="status" >  
		apolygon_points27.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach> 
	
	<c:forEach items="${apolygon28}" var="item" varStatus="status" >  
		apolygon_points28.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach>
	
	<c:forEach items="${apolygon29}" var="item" varStatus="status" >  
		apolygon_points29.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach> 
	
	<c:forEach items="${apolygon30}" var="item" varStatus="status" >  
		apolygon_points30.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach>
	
	<c:forEach items="${apolygon31}" var="item" varStatus="status" >  
		apolygon_points31.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach> 
	
	<c:forEach items="${apolygon32}" var="item" varStatus="status" >  
	apolygon_points32.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach>
	
	<c:forEach items="${apolygon33}" var="item" varStatus="status" >  
		apolygon_points33.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach> 
	
	<c:forEach items="${apolygon34}" var="item" varStatus="status" >  
		apolygon_points34.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach>
	
	<c:forEach items="${apolygon35}" var="item" varStatus="status" >  
		apolygon_points35.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach> 
	
	<c:forEach items="${apolygon36}" var="item" varStatus="status" >  
		apolygon_points36.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach>
	
	<c:forEach items="${apolygon37}" var="item" varStatus="status" >  
		apolygon_points37.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach> 
	
	<c:forEach items="${apolygon38}" var="item" varStatus="status" >  
		apolygon_points38.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach>
	
	<c:forEach items="${apolygon39}" var="item" varStatus="status" >  
		apolygon_points39.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach> 
	
	<c:forEach items="${apolygon40}" var="item" varStatus="status" >  
		apolygon_points40.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach>
	
	<c:forEach items="${apolygon41}" var="item" varStatus="status" >  
		apolygon_points41.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach> 
	
	<c:forEach items="${apolygon42}" var="item" varStatus="status" >  
		apolygon_points42.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach>
	
	<c:forEach items="${apolygon43}" var="item" varStatus="status" >  
		apolygon_points43.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach> 
	
	<c:forEach items="${apolygon44}" var="item" varStatus="status" >  
		apolygon_points44.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach>
	
	<c:forEach items="${apolygon45}" var="item" varStatus="status" >  
		apolygon_points45.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach> 
	
	<c:forEach items="${apolygon46}" var="item" varStatus="status" >  
		apolygon_points46.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach>
	
	<c:forEach items="${apolygon47}" var="item" varStatus="status" >  
		apolygon_points47.push(new BMap.Point(${item.lng},${item.lag}));
	</c:forEach> 
	
	
	var apolygon1 = new BMap.Polygon(apolygon_points1, {
	                             strokeColor: "red",
	                             strokeWeight: 2,
	                             strokeOpacity: 0.5,
	                             fillOpacity: 0.4
	                         });
	
	var apolygon2 = new BMap.Polygon(apolygon_points2, {
        strokeColor: "red",
        strokeWeight: 2,
        strokeOpacity: 0.5,
        fillOpacity: 0.4
    });
	
	var apolygon3 = new BMap.Polygon(apolygon_points3, {
        strokeColor: "red",
        strokeWeight: 2,
        strokeOpacity: 0.5,
        fillOpacity: 0.4
    });
	
	var apolygon4 = new BMap.Polygon(apolygon_points4, {
        strokeColor: "red",
        strokeWeight: 2,
        strokeOpacity: 0.5,
        fillOpacity: 0.4
    });
	
	var apolygon5 = new BMap.Polygon(apolygon_points5, {
        strokeColor: "red",
        strokeWeight: 2,
        strokeOpacity: 0.5,
        fillOpacity: 0.4
    });
	
	var apolygon6 = new BMap.Polygon(apolygon_points6, {
        strokeColor: "red",
        strokeWeight: 2,
        strokeOpacity: 0.5,
        fillOpacity: 0.4
    });
	
	var apolygon7 = new BMap.Polygon(apolygon_points7, {
        strokeColor: "red",
        strokeWeight: 2,
        strokeOpacity: 0.5,
        fillOpacity: 0.4
    });
	
	var apolygon8 = new BMap.Polygon(apolygon_points8, {
        strokeColor: "red",
        strokeWeight: 2,
        strokeOpacity: 0.5,
        fillOpacity: 0.4
    });
	
	var apolygon9 = new BMap.Polygon(apolygon_points9, {
        strokeColor: "red",
        strokeWeight: 2,
        strokeOpacity: 0.5,
        fillOpacity: 0.4
    });
	
	var apolygon10 = new BMap.Polygon(apolygon_points10, {
        strokeColor: "red",
        strokeWeight: 2,
        strokeOpacity: 0.5,
        fillOpacity: 0.4
    });
	
	var apolygon11 = new BMap.Polygon(apolygon_points11, {
        strokeColor: "red",
        strokeWeight: 2,
        strokeOpacity: 0.5,
        fillOpacity: 0.4
    });

	var apolygon12 = new BMap.Polygon(apolygon_points12, {
		strokeColor: "red",
		strokeWeight: 2,
		strokeOpacity: 0.5,
		fillOpacity: 0.4
	});
	
	var apolygon13 = new BMap.Polygon(apolygon_points13, {
		strokeColor: "red",
		strokeWeight: 2,
		strokeOpacity: 0.5,
		fillOpacity: 0.4
	});
	
	var apolygon14 = new BMap.Polygon(apolygon_points14, {
		strokeColor: "red",
		strokeWeight: 2,
		strokeOpacity: 0.5,
		fillOpacity: 0.4
	});
	
	var apolygon15 = new BMap.Polygon(apolygon_points15, {
		strokeColor: "red",
		strokeWeight: 2,
		strokeOpacity: 0.5,
		fillOpacity: 0.4
	});
	
	var apolygon16 = new BMap.Polygon(apolygon_points16, {
		strokeColor: "red",
		strokeWeight: 2,
		strokeOpacity: 0.5,
		fillOpacity: 0.4
	});
	
	var apolygon17 = new BMap.Polygon(apolygon_points17, {
		strokeColor: "red",
		strokeWeight: 2,
		strokeOpacity: 0.5,
		fillOpacity: 0.4
	});
	
	var apolygon18 = new BMap.Polygon(apolygon_points18, {
		strokeColor: "red",
		strokeWeight: 2,
		strokeOpacity: 0.5,
		fillOpacity: 0.4
	});
	
	var apolygon19 = new BMap.Polygon(apolygon_points19, {
		strokeColor: "red",
		strokeWeight: 2,
		strokeOpacity: 0.5,
		fillOpacity: 0.4
	});
	
	var apolygon20 = new BMap.Polygon(apolygon_points20, {
		strokeColor: "red",
		strokeWeight: 2,
		strokeOpacity: 0.5,
		fillOpacity: 0.4
	});
		
	var apolygon21 = new BMap.Polygon(apolygon_points21, {
	    strokeColor: "red",
	    strokeWeight: 2,
	    strokeOpacity: 0.5,
	    fillOpacity: 0.4
	});
	
	var apolygon22 = new BMap.Polygon(apolygon_points22, {
		strokeColor: "red",
		strokeWeight: 2,
		strokeOpacity: 0.5,
		fillOpacity: 0.4
	});
	
	var apolygon23 = new BMap.Polygon(apolygon_points23, {
		strokeColor: "red",
		strokeWeight: 2,
		strokeOpacity: 0.5,
		fillOpacity: 0.4
	});
	
	var apolygon24 = new BMap.Polygon(apolygon_points24, {
		strokeColor: "red",
		strokeWeight: 2,
		strokeOpacity: 0.5,
		fillOpacity: 0.4
	});
	
	var apolygon25 = new BMap.Polygon(apolygon_points25, {
		strokeColor: "red",
		strokeWeight: 2,
		strokeOpacity: 0.5,
		fillOpacity: 0.4
	});
	
	var apolygon26 = new BMap.Polygon(apolygon_points26, {
		strokeColor: "red",
		strokeWeight: 2,
		strokeOpacity: 0.5,
		fillOpacity: 0.4
	});
	
	var apolygon27 = new BMap.Polygon(apolygon_points27, {
		strokeColor: "red",
		strokeWeight: 2,
		strokeOpacity: 0.5,
		fillOpacity: 0.4
	});
	
	var apolygon28 = new BMap.Polygon(apolygon_points28, {
		strokeColor: "red",
		strokeWeight: 2,
		strokeOpacity: 0.5,
		fillOpacity: 0.4
	});
	
	var apolygon29 = new BMap.Polygon(apolygon_points29, {
		strokeColor: "red",
		strokeWeight: 2,
		strokeOpacity: 0.5,
		fillOpacity: 0.4
	});
	
	var apolygon30 = new BMap.Polygon(apolygon_points30, {
		strokeColor: "red",
		strokeWeight: 2,
		strokeOpacity: 0.5,
		fillOpacity: 0.4
	});
	
	var apolygon31 = new BMap.Polygon(apolygon_points31, {
	    strokeColor: "red",
	    strokeWeight: 2,
	    strokeOpacity: 0.5,
	    fillOpacity: 0.4
	});
	
	var apolygon32 = new BMap.Polygon(apolygon_points32, {
		strokeColor: "red",
		strokeWeight: 2,
		strokeOpacity: 0.5,
		fillOpacity: 0.4
	});
	
	var apolygon33 = new BMap.Polygon(apolygon_points33, {
		strokeColor: "red",
		strokeWeight: 2,
		strokeOpacity: 0.5,
		fillOpacity: 0.4
	});
	
	var apolygon34 = new BMap.Polygon(apolygon_points34, {
		strokeColor: "red",
		strokeWeight: 2,
		strokeOpacity: 0.5,
		fillOpacity: 0.4
	});
	
	var apolygon35 = new BMap.Polygon(apolygon_points35, {
		strokeColor: "red",
		strokeWeight: 2,
		strokeOpacity: 0.5,
		fillOpacity: 0.4
	});
	
	var apolygon36 = new BMap.Polygon(apolygon_points36, {
		strokeColor: "red",
		strokeWeight: 2,
		strokeOpacity: 0.5,
		fillOpacity: 0.4
	});
	
	var apolygon37 = new BMap.Polygon(apolygon_points37, {
		strokeColor: "red",
		strokeWeight: 2,
		strokeOpacity: 0.5,
		fillOpacity: 0.4
	});
	
	var apolygon38 = new BMap.Polygon(apolygon_points38, {
		strokeColor: "red",
		strokeWeight: 2,
		strokeOpacity: 0.5,
		fillOpacity: 0.4
	});
	
	var apolygon39 = new BMap.Polygon(apolygon_points39, {
		strokeColor: "red",
		strokeWeight: 2,
		strokeOpacity: 0.5,
		fillOpacity: 0.4
	});
	
	var apolygon40 = new BMap.Polygon(apolygon_points40, {
		strokeColor: "red",
		strokeWeight: 2,
		strokeOpacity: 0.5,
		fillOpacity: 0.4
	});
	
	var apolygon41 = new BMap.Polygon(apolygon_points41, {
	    strokeColor: "red",
	    strokeWeight: 2,
	    strokeOpacity: 0.5,
	    fillOpacity: 0.4
	});
	
	var apolygon42 = new BMap.Polygon(apolygon_points42, {
		strokeColor: "red",
		strokeWeight: 2,
		strokeOpacity: 0.5,
		fillOpacity: 0.4
	});
	
	var apolygon43 = new BMap.Polygon(apolygon_points43, {
		strokeColor: "red",
		strokeWeight: 2,
		strokeOpacity: 0.5,
		fillOpacity: 0.4
	});
	
	var apolygon44 = new BMap.Polygon(apolygon_points44, {
		strokeColor: "red",
		strokeWeight: 2,
		strokeOpacity: 0.5,
		fillOpacity: 0.4
	});
	
	var apolygon45 = new BMap.Polygon(apolygon_points45, {
		strokeColor: "red",
		strokeWeight: 2,
		strokeOpacity: 0.5,
		fillOpacity: 0.4
	});
	
	var apolygon46 = new BMap.Polygon(apolygon_points46, {
		strokeColor: "red",
		strokeWeight: 2,
		strokeOpacity: 0.5,
		fillOpacity: 0.4
	});
	
	var apolygon47 = new BMap.Polygon(apolygon_points47, {
		strokeColor: "red",
		strokeWeight: 2,
		strokeOpacity: 0.5,
		fillOpacity: 0.4
	});
	
	
	var result = function(lng,lag){
		var pt=new BMap.Point(lng,lag);
		if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon1)) {//如果点在区域内，返回true
			return 1;
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon2)){
		    return 2;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon3)){
		    return 3;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon4)){
		    return 4;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon5)){
		    return 5;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon6)){
		    return 6;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon7)){
		    return 7;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon8)){
		    return 8;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon9)){
		    return 9;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon10)){
		    return 10;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon11)) {//如果点在区域内，返回true
			return 11;
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon12)){
		    return 12;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon13)){
		    return 13;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon14)){
		    return 14;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon15)){
		    return 15;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon16)){
		    return 16;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon17)){
		    return 17;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon18)){
		    return 18;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon19)){
		    return 19;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon20)){
		    return 20;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon21)) {//如果点在区域内，返回true
			return 21;
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon22)){
		    return 22;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon23)){
		    return 23;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon24)){
		    return 24;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon25)){
		    return 25;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon26)){
		    return 26;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon27)){
		    return 27;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon28)){
		    return 28;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon29)){
		    return 29;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon30)){
		    return 30;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon31)) {//如果点在区域内，返回true
			return 31;
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon32)){
		    return 32;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon33)){
		    return 33;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon34)){
		    return 34;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon35)){
		    return 35;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon36)){
		    return 36;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon37)){
		    return 37;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon38)){
		    return 38;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon39)){
		    return 39;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon40)){
		    return 40;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon41)) {//如果点在区域内，返回true
			return 41;
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon42)){
		    return 42;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon43)){
		    return 43;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon44)){
		    return 44;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon45)){
		    return 45;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon46)){
		    return 46;   
		}else if (BMapLib.GeoUtils.isPointInPolygon(pt, apolygon47)){
		    return 47;   
		}else{
		    return 0;   
		}
	}
	
	var resultJSONStr ='[' ;
	
	<c:forEach items="${point}" var="item" varStatus="status" >  
		var values = "${item.b}";
		
		var aArr = values.split(",0 ");
		if(aArr!=null && aArr.length>0) {
			aArr[aArr.length-1] = aArr[aArr.length-1].substring(0, aArr[aArr.length-1].lastIndexOf(","));
		}
		var value = "";
		for(var i=0;i<aArr.length;i++){
			var zb1 = aArr[i].split(",");
			if(i==0){
				value = result(zb1[0],zb1[1]);
			}else{
				value = value + ',' +result(zb1[0],zb1[1]);
			}
		}
		var count = ${counts};
		var seq = ${status.count};
		if(count==seq){
			value = '{"id":'
				+ ${item.id}
				+' , "value":"'
				+ value
				+'"}'
		}else{
			value = '{"id":'
				+ ${item.id}
				+',"value":"'
				+ value
				+'"},'
		}
		
		resultJSONStr = resultJSONStr + value;
		value = "";
	</c:forEach>
	
	var resultValue =  resultJSONStr + ']';
	
	
	 $.ajax({
         url:path+"/insertStreet.htm",
         type:"post",
         data:{code:resultValue},
         success:function(data){
             console.log(data);
         }
     })
	
// 	$.ajax({
// 		type:"post",
// 		url:path+"/insertStreet.htm?code="+resultValue,
// 		dataType:"json",
// 		success:function(data){
// 			alert(1); 
			
// 		}
// 	})

	
</script>
