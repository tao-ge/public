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
/* body, html, #map_canvas {
	width: 100%;
	height: 100%;
	overflow: hidden;
	margin: 0;
} */

body, html, #map_canvas {
	width: 100%;
	height: 100%;
	margin: 0;
}

#r-result {
	width: 100%;
}
</style>
<link href="${path}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=ZLT8TTF1xGqIslUb0t659pGWnduCdGVf"></script>
<script type="text/javascript"
	src="http://developer.baidu.com/map/jsdemo/data/points-sample-data.js"></script>
<script src="${path}/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="${path}/js/toast.js"></script>
<script type="text/javascript" src="${path}/js/layer.js"></script>
</head>
<body>
	<div style="background-color: #CCCCCC; z-index: 9999; position: relative; height: auto; width: 100%;">
		<span id="spanMsg" style="color: Red;"></span> 
		<input name="areacode" id="areacode" value="${areacode}" type="text" style="display: none" />
		<span id="spanMsg1" style="color: Red;"></span>
		<input name="areaName" id="areaName" value="${areaName}" type="text" style="display: none" />
	</div>
	
	<div id="map_canvas"></div>
	
	<div id="div_list" class="newlayer" style="display: none;">
		<form id="mapSearchForm" action="">
		<table width="100%" border="1" class="table1" id="layterTable">
			<tr>
				<td>所属片区：</td>
				<td>
					<label for="select"></label> 
					<select class="areadevCode" name="areadevCode" id="areadevCode" runat="server" selected="selected">
							<option value="">全部</option>
							<c:forEach items="${areaList}" var="item" varStatus="status">
								<option value="${item.name}"
									<c:if test="${item.name == facilityCon.areaCode}">selected ="selected"</c:if>>${item.value}</option>
							</c:forEach>
					</select>
				</td>
				<td>汇聚区</td>
				<td><select name="areaCode1" id="areaCode1" class="hjq" runat="server" selected="selected">

				</select> 
				<input name="areaCode2" id="areaCode2" type="hidden" value="${areaCode2}" />
			</tr>
			<tr>
				<td>设施类型：</td>
				<td>
					 <select  id="devType" name="devType" >						    
						     <option value="">全部</option>
	                        <c:forEach  items="${deviceTypeList}" var="item" varStatus="status">
	                            <option value="${item.name}" <c:if test="${item.name == facilityCon.devType}">selected ="selected"</c:if>>${item.value}</option>
	                         </c:forEach>
						</select>
				</td>
				<td>设施名称：</td>
			    <td><input id="devName" name="devName" type="text"/></td>
			</tr>
			<tr>
			   <td>设施编码：</td>
			   <td><input id="devCode" name="devCode" type="text"/></td>
		       <td align="right">
                   <button type="button" id="devSearchBtn">查询</button>&nbsp;&nbsp;
                   <button type="button" name="btnClear" id="btnClear" onclick="closeSearchForm()">关闭</button>
               </td>
	         </tr> 
		</table>
		</form>
			<!-- 查询数据列表 -->
			<table class="tablelist" id="tableFacility">
				<thead>
					<tr>
						<th>设施名称</th>
						<th>设施编码</th>
						<th>设施类型</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			<div class="clear"></div>   
		</div>
</body>
<script type="text/javascript">
	var path = "${path}";
	var map = null;
	var sign = 0;
	var layerIndex;
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
		
		$("input[name=devType]:checkbox").click(function(){
			var flag=true
			$("[name=devType]:checkbox").each(function(){
				if(!this.checked){
				}
			});
		});
		
		$(".areadevCode").on("change",function(){
			changeArea($(this));
		})
		
		$("#devSearchBtn").click(function(){
			$("#tableFacility tbody").html("");
			
			var areacode = $("#areadevCode").val();
			var areaCode1 = $("#areaCode1").val();
			var devType = $("#devType").val();
			var devName = $("#devName").val();
			var devCode = $("#devCode").val();
				
			$.ajax({
				   type: "POST", 
				   url: '${path}/facility/findFacilitysToBdMap.htm',
				   data:{areaCode:areacode,areaCode1:areaCode1,devType:devType,devName:devName,devCode:devCode},
				   dataType : "json",
				   success: function(data){
					   if(data){
						   for(var i=0;i<data.length; i++){
							   var obj = data[i];
							   var trTemplate = "<tr>";
							   if((i+1)%2==0){
								   trTemplate = "<tr style=\"background:#b6dcf7;\">"
							   }
							   trTemplate = trTemplate + "<td>#\{devName\}</td><td>#\{devCode\}</td><td>#\{devType\}</td>";
							   var tr = trTemplate.replace(/#\{devCode\}/g, obj.dev_code)
							   				.replace(/#\{devName\}/g, obj.devName)
							   				.replace(/#\{devType\}/g, obj.value_name);
							   tr = tr + "<td>";
							   if($.trim(obj.baiduX) != '' && $.trim(obj.baiduY) != ''){
								   var objstr = JSON.stringify(obj).replace(/\"/g,"'");
								   tr = tr + "<a href=\"javascript:void(0)\" onclick=\"coordinateLocation("+objstr+")\" x=\"#x\" y=\"#y\">定位</a>";								   			
							   }
							   tr = tr +  "&nbsp;&nbsp;<a href=\"javascript:void(0)\" onclick=\"facilityDetail('"+obj.dev_id+"')\">详细</a></td></tr>";
							   $("#tableFacility tbody").append(tr);
						   }
					   }
				   }
				}); 
		});		
	});
	
	function coordinateLocation(data){
		map.clearOverlays();
		pointOut(data);
		map.centerAndZoom(new BMap.Point(data.baiduX, data.baiduY), 14);
		closeSearchForm();
	}
	
	function closeSearchForm()
	{
		$("#tableFacility tbody").html("");
		$(':input','#mapSearchForm')    		
		.val('')
		.removeAttr('checked') 
		.removeAttr('selected');
		layer.close(layerIndex);
		
/* 		var areaName = $("#areaName").val();
		map.centerAndZoom(areaName, 15); */
	}
	
	function changeArea(districtObj){
		var areaCode1=$("#areaCode2").val();
		var  value = $(districtObj).val();
		var areas = $(districtObj).parents("#layterTable").find("select.hjq");
		
		areas.children("option").remove();
		areas.append($("<option>").prop("value",'').append("==请选择=="));
		$.ajax({
			type:"POST",
			url: "${path}/ajax/getAreas.htm?areaRank=4&parentAreaCode="+value,
			dataType:"json",
			success:function(data)
			{
				$.each(data,function(index,value){
					 if(areaCode1==value.name)
					{
						 
						 //回显
						areas.append($("<option>").prop("value",data[index].name).prop("selected",true)
						.append(data[index].value));
						
    					areas.append($("<option>").prop("value",data[index].name).append(data[index].value));
    					//删除重复数据
    					var obj=document.getElementById("areaCode");
     					//index,要删除选项的序号，这里取当前选中选项的序号
     					var index=obj.selectedIndex;
     					obj.options.remove(index+1); 
					}else{
					 areas.append($("<option>").prop("value",data[index].name).append(data[index].value));
					}
				});
			}
		});
	}
	
	
	function showSearchForm(btn){
		closeSearchForm();
		layerIndex = layer.open({
			type : 1,
			closeBtn : 2,
			title : [ '高级查询', 'font-size:18px;' ],
			area : [ '780px', '450px' ],
			shadeClose : true, // 点击遮罩关闭
			content : $("#div_list")
		});
	}
	
	function facilityDetail(id){
		layer.open({
			type : 2,
			closeBtn : 1,
			title : [ '详情', 'font-size:18px;' ],
			area : [ '980px', '500px' ],
			shadeClose : true, // 点击遮罩关闭
			content : "${path}/facilityBaseInfo.htm?devId=" + id
		});
	}
	
	function aa(sign,strs){
		
		var areacode = $("#areacode").val();
		var areaName = $("#areaName").val();
		map = new BMap.Map("map_canvas", {});
		alert("定位方式：" + areaName)
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
		
		// 定义一个控件类,即function
	    function ZoomControl() {
	        this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
	        this.defaultOffset = new BMap.Size(150, 10);
	    }
		ZoomControl.prototype = new BMap.Control();
		ZoomControl.prototype.initialize = function(map){
	      // 创建一个DOM元素
	      var div = document.createElement("div");
	      div.innerHTML = '<div id="r-result" style="background-color:#EBEBEB;" class="tools">'
	      		+ '<button id="btnSearch" name="topSearchBtn" type="button" class="submit" onclick="showSearchForm(this)">高级查询</button>&nbsp;&nbsp;'
	      		+ '</div>';
	
	      // 添加DOM元素到地图中
	      map.getContainer().appendChild(div);
	      // 将DOM元素返回
	      return div;
	    }
		var myZoomCtrl = new ZoomControl();
		map.addControl(myZoomCtrl);
		
// 		var u; // 定义地图缩放等级的变量
		map.addEventListener("zoomend", function(){
			if(this.getZoom()<=9){
				map.clearOverlays();
				if (document.createElement('canvas').getContext) {  // 判断当前浏览器是否支持绘制海量点
			        var points = [];  // 添加海量点数据
			        $.ajax({
						type : "post",
						url : path + "/findFacilitiesACode1ByOrgId.htm",
						dataType : "json",
						success : function(data) {
							if(data){
								points.push(new BMap.Point(data[0].baiduX,data[0].baiduY));
								var options = {
						            size: BMAP_POINT_SIZE_SMALL,
						            shape: BMAP_POINT_SHAPE_CIRCLE,
						            color: '#A52A2A'
						        }
								var pointCollection = new BMap.PointCollection(points, options);// 初始化PointCollection
					            map.addOverlay(pointCollection);  // 添加Overlay
							}
						}
			        })
			        
			    } else {
			        alert('请在chrome、safari、IE8+以上浏览器查看本示例');
			    }
			}else if(this.getZoom() > 9 && this.getZoom() < 14){
				map.clearOverlays();
				if (document.createElement('canvas').getContext) {  // 判断当前浏览器是否支持绘制海量点
			        var points = [];  // 添加海量点数据
			        $.ajax({
						type : "post",
						url : path + "/findFacilitiesACode1ByOrgId.htm",
						dataType : "json",
						success : function(data) {
							if(data){
								for (var i = 0; i < data.length; i++) {
									points.push(new BMap.Point(data[i].baiduX,data[i].baiduY));
								}
								var options = {
						            size: BMAP_POINT_SIZE_SMALL,
						            shape: BMAP_POINT_SHAPE_CIRCLE,
						            color: '#A52A2A'
						        }
								var pointCollection = new BMap.PointCollection(points, options);// 初始化PointCollection
					            map.addOverlay(pointCollection);  // 添加Overlay
							}
						}
			        })
			        
			    } else {
			        alert('请在chrome、safari、IE8+以上浏览器查看本示例');
			    }
			}else if(this.getZoom()>=15){
				map.clearOverlays();
				if (document.createElement('canvas').getContext) { // 
					var points = [];
					$.ajax({
						type : "post",
						url : path + "/findFacilitiesAreaCode1.htm?areaCode1="+areacode,
						dataType : "json",
						success : function(data) {
							for (var i = 0; i < data.length; i++) {
								pointOut(data[i])
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
			}
		
		});
	}
	
	function pointOut(data){
		var point = new BMap.Point(data.baiduX, data.baiduY);
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
/* 								var removeMarker = function(e, ee, marker) {
			map.removeOverlay(marker);
		}
*/							
		var type = data.devType;
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
		
		var label = new BMap.Label(data.devName,{offset:new BMap.Size(20,-10)});
		
		var title = "";
		title = "设施名称：" + data.devName + "<br>设施类型："
				+ typeName + "<br>百度X：" + data.baiduX
				+ "<br>百度Y：" + data.baiduY;
		label.setTitle(title);
			label.setStyle({
				display : 'none'
			});
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