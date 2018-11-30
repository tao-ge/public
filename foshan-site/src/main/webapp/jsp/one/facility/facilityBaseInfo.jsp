<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link href="${path}/css/tinyselect.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path }/js/jquery.js"></script>
<script type="text/javascript" src="${path}/js/select-ui.min.js"></script>
<script type="text/javascript" src="${path}/js/tinyselect.js"></script>
<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${path}/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="${path }/js/layer.js"></script>
<script  type="text/javascript" src="${path }/js/prompt/ymPrompt.js"></script>
<link rel="stylesheet"  type="text/css" href="${path}/js/prompt/skin/ymPrompt.css" />
<script type="text/javascript">
	path = '${path }';
</script>
<script type="text/javascript"
	src="${path }/js/facility/facilityEdit.js?ce"></script>
<title>信息管理系统界面</title>

<script type="text/javascript">
	var path = "${path}";
	$(document).ready(function(e) {

		$(".select3").uedSelect({
			width : 152
		});
		
		$("a[op='view']").on("click",openWinMap);
	});
	
	/*
	 * 打开地图窗口 
	 */
	function openWinMap(){
		var lng = $(this).attr("lng");
    	var lat = $(this).attr("lat");
    	var banjing = 500;
	    if(lng == ''&& lat==''){
		    alert("无位置信息！");
		    return;
	    }
		ymPrompt.win({message:'${path }/jsp/one/facility/showMap.jsp?plng='+lng+'&plat='+lat+'&pbanjing='+banjing,
				width:700,height:500,title:'位置信息',handler:null,iframe:true,btn:[['确定','yes'],['关闭','cancel']]});
	}
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
		<form name="editForm" id="editForm" method="post"
			action="${path}/facilitySave.htm">		
			<table id="newcss" class="tableDetail">
				<tbody >
					<tr>
					    <td >设施代码：</td>
						<td colspan="4">${facility.devCode}</td>
					</tr>
					<tr>
					    <td >设施名称：</td>
						<td colspan="4">${facility.devName}</td>
					</tr>
					<tr>

						<td class="odd" width="100px">设施类型：</td>
						<td width="400px">${facility.devTypeName}</td>
						<td class="odd"  width="100px">设施型号：</td>
						<td >${facility.devModel}</td>

					</tr>

					<tr>

						<td class="odd">竣工日期：</td>
						<td>${date}</td>
						<td class="odd">详情地址：</td>
						<td>${facility.devAddr}</td>

					</tr>
					<%-- <tr>

						<td class="odd">GPS经度：</td>
						<td>${facility.longitude}</td>
						<td class="odd">GPS纬度：</td>
						<td>${facility.latitude}</td>

					</tr> --%>
					<tr>

						<td class="odd">百度经度：</td>
						<td>${facility.baiduX}</td>
						<td class="odd">百度纬度：</td>
						<td>${facility.baiduY}</td>

					</tr>
					<tr>

						<td class="odd">谷歌经度：</td>
						<td>${facility.guGEX}</td>
						<td class="odd">谷歌纬度：</td>
						<td>${facility.guGEY}</td>

					</tr>
					<tr>

						<td class="odd">附近特征描述：</td>
						<td>${facility.devDesc}</td>



						<td class="odd">所属片区：</td>
						<td>
						    ${facility.areaName1}
						</td>
					</tr>
					<tr>

						<td class="odd">所属机房：</td>
						<td>${facility.room}</td>
						<%-- <td class="odd">所属站点：</td>
						<td>${facility.site}</td> --%>
						<td class="odd">设施状态：</td>
						<td>
						<c:choose>
							<c:when test="${facility.devState eq 0}">未核对</c:when>
							<c:when test="${facility.devState eq 1}">正常</c:when>
							<c:when test="${facility.devState eq 2}">新增</c:when>
							<c:when test="${facility.devState eq 3}">修改</c:when>
							<c:when test="${facility.devState eq 4}">资管删除</c:when>
							<c:when test="${facility.devState eq 5}">新增删除</c:when>
						</c:choose>
						</td>
					</tr>
					<tr>

						<td class="odd">所属工程：</td>
						<td>${facility.proIdName}</td>
						<td class="odd">所属设施</td>
						<td>${facility.pdevName}</td>

					</tr>

					<tr>

						<td class="odd">有效标志：</td>
						<td>${facility.flagName}</td>
						<td class="odd" >核实标志：</td>
						<td>${facility.surveyResultName}</td>

					</tr>
					<tr>
					
						<td class="odd">最后修改者：</td>
						<td>${userName}</td>
						<td class="odd" >最后修改时间：</td>
						<td>
						<fmt:formatDate value="${facility.lastModifyTime}" pattern="yyyy-MM-dd HH:mm:ss" />
						</td>
						
					</tr>
					<tr>
						<td class="odd">核查状态：</td>
						<td>${facility.checkTypeName}</td>
						<td class="odd" >核查人：</td>
						<td>${facility.checkUserName}</td>
					</tr>
					<tr>
					    <td >位置信息：</td>
						<td colspan="4"><a href="javascript:;" op="view" lng="${facility.baiduX}" lat="${facility.baiduY}"   class="tablelink">点击查看</a></td>
					</tr>
					
				</tbody>
			</table>

		</form>
	</div>
	</div>
</body>
</html>