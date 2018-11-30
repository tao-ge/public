var map;
var _9_points = [];
var _14_points = [];
var _15_markers = [];
var _15_lines = [];
var layerIndex;

function init(){
	map = new BMap.Map("map_canvas", {});
	map.centerAndZoom(areaName, 9);
	map.enableScrollWheelZoom(); //启用滚轮放大缩小
	map.addControl(new BMap.NavigationControl());
	map.addEventListener("zoomend", zoomEvent);
}

function zoomEvent(){
	if(this.getZoom()<=9){
		removeMarker(_14_points);
		if(_9_points.length == 0){
			lte9();
		}else{
			addMarkersOverLay(_9_points);
		}
	}else if(this.getZoom() > 9 && this.getZoom() <= 14){
		removeMarker(_9_points);
		//markersHide();
		overLayHide(_15_markers);
		overLayHide(_15_lines);
		if(_14_points.length == 0){
			lt14();
		}else{
			addMarkersOverLay(_14_points);
		}
	}else if(this.getZoom() >= 15){
		removeMarker(_14_points);
		if(_15_markers.length > 0){
			//markersShow();
			overLayShow(_15_markers);
		}else{
			lte15Marker();
		}
		
		if(_15_lines.length > 0){
			overLayShow(_15_lines);
		}else{
			lte15Line();
		}
	}
}

function lte9(){
	$.ajax({
		type : "post",
		url : path + "/findFacilitiesACode1ByOrgId.htm",
		dataType : "json",
		success : function(data) {
			if(data){
				var point = new BMap.Point(data[0].baiduX,data[0].baiduY);
				var marker = new BMap.Marker(point);
				map.addOverlay(marker);
				_9_points.push(marker);
				//addPointOverLay(_9_points);
			}
		}
    })
}

function lt14(){
	$.ajax({
		type : "post",
		url : path + "/findFacilitiesACode1ByOrgId.htm",
		dataType : "json",
		success : function(data) {
			if(data){
				for (var i = 0; i < data.length; i++) {
					//_14_points.push(new BMap.Point(data[i].baiduX,data[i].baiduY));
					var point = new BMap.Point(data[i].baiduX,data[i].baiduY);
					var marker = new BMap.Marker(point);
					map.addOverlay(marker);
					_14_points.push(marker);
				}
			}
		}
    });
}

function lte15Marker(){
	$.ajax({
		type : "post",
		url : path + "/findFacilitiesAreaCode1.htm?areaCode1="+areacode,
		dataType : "json",
		success : function(data) {
			for (var i = 0; i < data.length; i++) {
				markerOut(data[i])
			}
		}
	});
}

function lte15Line(){
	$.ajax({
		type : "post",
		url : path + "/findFiber2.htm?areaCode1="+areacode,
		dataType : "json",
		success : function(data) {
			for (var i = 0; i < data.length; i++) {
				var fiber = [];
				var a = new BMap.Point(data[i].a_baidu_x, data[i].a_baidu_y);
				var b = new BMap.Point(data[i].b_baidu_x, data[i].b_baidu_y);
				fiber = [a, b];
				var line = new BMap.Polyline(fiber, {
					strokeColor : "blue",
					strokeWeight : 5,
					strokeOpacity : 0.5
				}); //创建折线对象
				setLine(line,data[i]);
			}
		}
	});
}

function setLine(line,data){
	line.addEventListener("click",function(e){
		var p = e.target;
		if (p instanceof BMap.Polyline) {
			var content="名称："+data.sec_name+"\n纤芯数:"+data.fiber_num;
			alert(content);
		}
	});
	map.addOverlay(line); //添加到地图中
	_15_lines.push(line);
}

function markerOut(data){
	var point = new BMap.Point(data.baiduX, data.baiduY);
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
	
	//	marker.addContextMenu(markerMenu);
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
	_15_markers.push(marker);
}


function addMarkersOverLay(markers){
	for(var i=0; i<markers.length; i++){
		map.addOverlay(_14_points[i]);
	}
}

function overLayHide(arr){
	if(arr.length > 0){
		for(var i=0; i<arr.length; i++){
			arr[i].hide();
		}
	}
}

function overLayShow(arr){
	for(var i=0; i<arr.length; i++){
		arr[i].show();
	}
}

function removePoint(){
	var allOverlay = map.getOverlays();
	//alert(allOverlay.join(","))
    for (var i = 0; i < allOverlay.length; i++){
//    	alert(allOverlay[i].getLabel().content);
//    	for(var key in allOverlay[i]){
//    		alert(key + ":" + allOverlay[i][key]);
//    	}
//        if(allOverlay[i].toString()!="[object Marker]"){
//        	map.removeOverlay(allOverlay[i]);
//        }
    }
}

function removeMarker(arr){
    for (var i = 0; i < arr.length; i++){
        map.removeOverlay(arr[i]);
    }
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

function closeSearchForm()
{
	$("#tableFacility tbody").html("");
	$(':input','#mapSearchForm')    		
	.val('')
	.removeAttr('checked') 
	.removeAttr('selected');
	layer.close(layerIndex);
}

function coordinateLocation(data){
	//map.clearOverlays();
	removeMarker(_9_points);
	removeMarker(_14_points);
	//overLayHide(_15_markers);
	//overLayHide(_15_lines);
	//pointOut(data);
	map.centerAndZoom(new BMap.Point(data.baiduX, data.baiduY), 18);
	closeSearchForm();
}

function facilityDetail(id){
	layer.open({
		type : 2,
		closeBtn : 1,
		title : [ '详情', 'font-size:18px;' ],
		area : [ '980px', '500px' ],
		shadeClose : true, // 点击遮罩关闭
		content : path + "/facilityBaseInfo.htm?devId=" + id
	});
}

$(function(){
	init();
	
	$("#devSearchBtn").click(function(){
		$("#tableFacility tbody").html("");
		
		var areacode = $("#areadevCode").val();
		var areaCode1 = $("#areaCode1").val();
		var devType = $("#devType").val();
		var devName = $("#devName").val();
		var devCode = $("#devCode").val();
			
		$.ajax({
			   type: "POST", 
			   url: path + '/facility/findFacilitysToBdMap.htm',
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
						   $("#tableFacility tbody").html(tr);
					   }
				   }
			   }
			}); 
	});
});