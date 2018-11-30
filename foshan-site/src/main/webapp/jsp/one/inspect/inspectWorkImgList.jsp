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
<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	path = '${path }';
</script>
<script type="text/javascript">
		$(function(){
    		$('.tablelist tbody tr:odd').addClass('odd');
    	})
	</script>

<title>信息管理系统界面</title>
<script type="text/javascript">
	var path = "${path}";
	$(document).ready(function(e) {
	});
</script>
</head>
<body>
<!-- 	<div class="place"> -->
<!-- 		<span>位置：</span> -->
<!-- 		<ul class="placeul"> -->
<!-- 			<li><a href="#">首页</a></li> -->
<!-- 			<li><a href="#">巡检管理</a></li> -->
<!-- 			<li><a href="#">巡检任务管理</a></li> -->
<!-- 			<li><a href="#">巡检图片</a></li> -->
<!-- 		</ul> -->
<!-- 	</div> -->
	<div class="rightinfo">

		<table class="tablelist">
			<thead>
				<tr>
<!-- 					<th>编号<i class="sort"><img src="images/px.gif" /></i></th> -->
					<th>设施编码</th>
					<th>设施名称</th>
					<th>描述</th>
					<th>巡检图片</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${inspectImg}" var="item">
					<tr>
						<td>${item.devCode}</td>
						<td>${item.devName}</td>
						<td>${item.imgDesc}</td>
						<td><a href="javascript:;" op="pic"  class="tablelink" value="${path}${item.imgUrl}">查看</a></td>
<%-- 						<td><img  class="lazy" src="${path}${item.imgUrl}"  height="150" width="150"/></td> --%>
						<div id="div_img" class="newlayer" style="display: none;">
							<img  class="lazy" id="img" src=""  height="300" width="300"/>
						</div>
					</tr>
					

				</c:forEach>
			</tbody>
		</table>

	</div>
	
	

</body>
<script type="text/javascript">
//$("a[op='pic']").on("click", showInspectImg);// 显示巡检图片页面
$("a[op='pic']").on("click",function(){
	var value = $(this).attr('value');
	showInspectImg(value);
})
function showInspectImg(value){
	var img = document.getElementById("img");
	img.setAttribute("src",value);
	layer.open(
			{
				type: 1,
				closeBtn: 2,
				title: ['巡检图片', 'font-size:18px;'],
				area: ['350px', '390px'],
				shadeClose: true, //点击遮罩关闭
				content:$("#div_img")
	 		}
	);
}
</script>
</html>
