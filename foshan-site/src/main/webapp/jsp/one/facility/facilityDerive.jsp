<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link href="${path}/css/tinyselect.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path }/js/jquery.js"></script>
<script type="text/javascript" src="${path}/js/select-ui.min.js"></script>
<script type="text/javascript" src="${path}/js/tinyselect.js"></script>
<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${path}/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="${path }/js/layer.js"></script>
<script type="text/javascript">
	path = '${path }';
</script>
<script  type="text/javascript" src="${path }/js/facility/facilityDerive.js?ch444d"></script>	
<title>信息管理系统界面</title>
<script type="text/javascript">
	var path = "${path}";
	$(document).ready(function(e) {

		$(".select3").uedSelect({
			width : 152
		});
	});
</script>
</head>
<body>

	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="#">首页</a></li>
	    <li><a href="#">资源台账管理</a></li>
	    <li><a href="#">设施管理</a></li>
	    <li><a href="#">设施详情</a></li>
	    </ul>
    </div>
	<div class="formbody">
	
	    <div id="usual1" class="usual"> 
    	<jsp:include page="/jsp/one/facility/facilityDetailNav.jsp" />  	
		<div class="tools" style="margin:5px;">
	    	<ul class="toolbar">
		    </ul>
		    <div class="simpleQuery">
	           <form method="post"	action="${path}/deriveONU.htm?returnNum=1&devId=${devId}" id="SearchForm" name="SearchForm">
	           		<input type="hidden" id="devId" value="${devId}" >
	           		<input type="hidden" id="returnNum" value="1" >
	               <!--  <input  id="btnSimpleQuery" value="查询" class="submit" type="button"> -->
	                <button type="button"  id="btnExportQuery">导出数据</button>
	                <button type="button"  id="btnLightPath">光路生成</button> 
	                 <c:if test="${returnNum!=null}"> 
	           			<a id="exportDowns" href="${filePath}"><button type="button"  id="btnExportQueryss"><font color="red">下载查询数据</font></button></a>
					</c:if> 
	           </form>
	        </div>
	    </div>    
	    <!-- 导出弹层下载 -->
		<div id="div_export" class="newlayer" style="display:none;">
		     <center> <a id="exportDown" href="" target="_blank" style="font-size:18px;margin-top:120px">数据生成成功，点此下载！</a></center>
	   	</div>
	    <!-- 查询数据列表 -->
	    <table class="tablelist">
		    	<thead>
			    	<tr>
			    		<th width="5%">序号<i class="sort"><img src="images/px.gif" /></i></th>
				        <th width="12%">设备名称</th>
				        <th width="12%">光缆段名称</th>
				        <th width="7%">纤芯数量</th>
				        <th width="7%">纤芯序号</th>
				        <th width="10%">端口编码</th>
				        <th width="7%">双芯绑定</th>
				        <th width="20%">光路文本路由</th>
						<th width="10%">Z端跳纤位置</th>
				        <th width="10%">Z端端口状态</th>
			        </tr>
		        </thead>
		        <tbody>
			        <c:forEach items="${csbs}" var="f" varStatus="status">
				        <tr>
				        	<td>${status.count}</td>
				         	<td>${f.devName}</td>
					        <td>${f.secName}</td>
					        <td>${f.fnum1}</td>
					        <td>${f.fnum2}</td>
					        <td>${f.port01}</td>
					        <td>${f.id}</td>
					        <td>${f.routeText}</td>
					        <td>${f.txPort}</td>
					        <td>${f.zcodeType}</td>
				        </tr>
		       		</c:forEach>
		        </tbody>
		    </table>  
		    <div class="clear"></div>   
		    
		   
     </div>
	</div>
</body>
</html>