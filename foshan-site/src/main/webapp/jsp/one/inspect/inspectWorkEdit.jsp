<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String rc = request.getContextPath();
	request.setAttribute("path", rc);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${path}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path }/js/jquery.js"></script>
<script type="text/javascript" src="${path}/js/select-ui.min.js"></script>
<script type="text/javascript" src="${path}/js/tinyselect.js"></script>
<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${path}/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="${path }/js/layer.js"></script>
<script type="text/javascript" src="${path }/js/deviceStatus/deviceStatusList.js?ces"></script>
<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>

<!-- <script type="text/javascript" src="http://api.map.baidu.com/api?key=&v=1.1&services=true"></script> -->
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=ZLT8TTF1xGqIslUb0t659pGWnduCdGVf"></script>
<script type="text/javascript">
	path = '${path }';
</script>
<script type="text/javascript" src="${path }/js/inspect/inspectWorkEdit.js"></script>
<title>信息管理系统界面</title>
<script type="text/javascript">
	var path = "${path}";
</script>
</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">巡检管理</a></li>
			<li><a href="#">巡检任务编辑</a></li>
		</ul>
	</div>
	<div class="formbody">
		<form name="workEditForm" id="workEditForm" action="${path}/saveWork.htm">
			<div class="formtitle">
				<span>基本信息</span>
			</div>

			<input name="workId" type="text" id="workId" value="${fa.workId}" style="display: none" /> 
			<input name="workerId" type="text" id="workerId" value="${fa.workerId}" style="display: none" /> 
			<input name="devIds" type="text" id="devIds" value="${fa.devIds}" style="display: none" />
			<table class="tableForm">
				<tbody>
					<tr>
						<td>巡检任务名称：</td>
						<td>
							<input name="workName" type="text" id="workName" value="${fa.workName }" 
							 type="text" datatype="*1-200"
							errormsg="设施名称至少1个字符,最多200个字符！"/><span
							style="color: red; display: inline">* 必填</span>
						</td>
					</tr>
					<tr>
						<td>选择用户：</td>
						<td><input name="userName" type="text" class="dfinput" id="userName" value="${fa.userName}" readonly="readonly" />&nbsp;&nbsp;<a id="hrefAddUser" href="javascript:;"><u>选择</u></a></td>
					</tr>
<!-- 					<tr> -->
<!-- 						<td>状态：</td> -->
<!-- 						<td><select name="workType" id="workType"> -->
<%-- 								<c:forEach items="${VaList}" var="item"> --%>
<%-- 									<option value="${item.value}" <c:if test="${item.value == sign}">selected ="selected"</c:if>>${item.name}</option> --%>
<%-- 								</c:forEach> --%>
<!-- 						</select></td> -->

<!-- 					</tr> -->
					<tr>
						<td>备注：</td>
						<td><input id="remark" name="remark" type="text" value="${fa.remark}" /></td>
					</tr>
					<tr>
						<td>设施列表：</td>
						<td id="td" rowspan="5" style="vertical-align: top;"><select  name="devSelect" id="devSelect" multiple="multiple" style="height: 150px; width: 348px; overflow-y: auto;">
								<c:forEach items="${DevList}" var="item">
									<option id="op" value="${item.value}" <c:if test="${item.value == sign}"></c:if>>${item.name}</option>
								</c:forEach>
						</select></td>
<!-- 						<p class="error">请至少选中一个体育项目</p> -->
<!--     					<p class="result"></p> -->
						<span id="hao"></span>
						<td style="float: left !important; position: absolute !important; left: 470px !important;"><a id="hrefAddDev" href="javascript:;"><u>添加<a style="color:red; display:block;">(双击所选设施删除)</a></u></a></td>
						<td style="float: left !important; position: absolute !important; left: 470px !important; top: 380px !important;"><a id="hrefQueryDev" href="javascript:;"><u>查询</br><a style="color:red; display:block;">(双击所选设施删除)</a></u></a></td>
					</tr>
					<tr>
						<td style="top: 495px; float: left; left: 110px; position: absolute;" class="odd"><label>&nbsp;</label><input name="btnSave" id="btnSave" type="button" class="btn" value="确认保存" />
							<button name="btnClose" id="btnClose" type="button" class="btn">取消</button></td>
					</tr>
					
			</table>
		</form>
		<div id="div_devlist" class="newlayer" style="width:800px;height:500px;display: none;">
		</div>

		<!-- 其它页面元素 如：弹出层等-->	
			<div id="div_devlist" class="newlayer" style="width:800px;height:500px;display: none;">
<!-- 				<div style="width:800px;height:500px;" id="dituContent"></div> -->
			</div>
		<!-- 设施列表 -->
		<div id="query_devList" class="newlayer" style="display: none;">
			<div class="tools">
				<div>
					名称或编号：<input name="textSearch" id="textSearch" type="text" />
					<button id="btnSearch" name="btnSearch" type="button" class="submit">查询</button>
				</div>
			</div>
			<!-- 查询数据列表 -->
			<table class="tablelist" id="tableDev">
				<thead>
					<tr>
						<th>设施代码<i class="sort"><img src="images/px.gif" /></i></th>
						<th>设施名称</th>
						<th>设施类型</th>
						<th>设施型号</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			<div class="clear"></div>
		</div>
		<!-- 用户列表 -->
		<div id="div_userlist" class="newlayer" style="display: none;">
			<!-- 查询数据列表 -->
			<table class="tablelist">
				<thead>
					<tr>
						<th>姓名</th>
						<th>性别</th>
						<th>手机号码</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pbUser}" var="f">
						<tr>
							<td>${f.userName}</td>
							<td>
								<c:if test="${f.sex=='0'}">
									未知
								</c:if>
								<c:if test="${f.sex=='1'}">
									男
								</c:if>
								<c:if test="${f.sex=='2'}">
									女
								</c:if>
							</td>
							<td>${f.mobilePhone}</td>
							<td><a href="javascript:;" op="select" dui="${f.userId}" dun="${f.userName}" class="tablelink">选择</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="clear"></div>
			
		</div>
	</div>
	<script type="text/javascript">
    //创建和初始化地图函数：
    function initMap(){
        createMap();//创建地图
        setMapEvent();//设置地图事件
        addMapControl();//向地图添加控件
        addMarker();//向地图中添加marker
    }
    
    //创建地图函数：
    function createMap(){
        var map = new BMap.Map("div_devlist");//在百度地图容器中创建一个地图
        var areaName="${areaName}";
//         alert(areaName);
        var markerArr =${str};
        var json = markerArr[0];
        var p0 = json.point.split("|")[0];
        var p1 = json.point.split("|")[1];
        var point = new BMap.Point(p0,p1);
        //var point = new BMap.Point(114.075562,24.956022);//定义一个中心点坐标
        map.centerAndZoom(point,15);//设定地图的中心点和坐标并将地图显示在地图容器中
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
	var ctrl_ove = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:1});
	map.addControl(ctrl_ove);
        //向地图中添加比例尺控件
	var ctrl_sca = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
	map.addControl(ctrl_sca);
    }
   
    
 
    //创建marker
    function addMarker(){
    	var markerArr =${str};
        for(var i=0;i<markerArr.length;i++){
            var json = markerArr[i];
            var p0 = json.point.split("|")[0];
            var p1 = json.point.split("|")[1];
            var point = new BMap.Point(p0,p1);
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
            addClickHandler(json.devId,json.content,label);
            map.addOverlay(marker);
        }
        function addClickHandler(devId,content,label){
        	label.addEventListener("click",function(e){
    		 	downDataInfoWindow(devId,content);
    			//openInfo(content,e)
    			}
    		);
    	}
    }
 
    //创建一个Icon
    function createIcon(json){
        var icon = new BMap.Icon("http://app.baidu.com/map/images/us_mk_icon.png", new BMap.Size(json.w,json.h),{imageOffset: new BMap.Size(-json.l,-json.t),infoWindowOffset:new BMap.Size(json.lb+5,1),offset:new BMap.Size(json.x,json.h)})
        return icon;
    }

    var devicesIds="";
    function downDataInfoWindow(devId,devName) {

     	$("#devId").val(devId);
     	$("#devName").val(devName);
    	var b=true;
    	if(devicesIds.length>0){
    		var idAry = devicesIds.split(",");
    		for(i=0;i<idAry.length;i++){
    			if(idAry[i]==devId){
    				b=false;
    			}
    		}
    	}
    	if(b){
    		$("<option></option>").val(devId).text(devName).appendTo($("#devSelect"));
    	}
    	updateDevIds();
    }
    
    function onDblClickSelect(){
        var selOpt = $("#devSelect option:selected");  
        selOpt.remove();  
    	updateDevIds();
    }

    function updateDevIds(){
    	devicesIds="";
    	var i=0;
    	var len = $("#devSelect option").length;
    	$("#devSelect option").each(function() {
    		devicesIds=devicesIds+$(this).val();
    		i++;
    		if(i!=len)
    			devicesIds=devicesIds+",";
    	});
    	$("#devIds").val(devicesIds);
    }
	
    initMap();
</script>

</body>
</html>
