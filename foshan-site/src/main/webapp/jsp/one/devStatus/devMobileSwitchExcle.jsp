<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% 
String rc = request.getContextPath(); 
request.setAttribute("path",rc);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="${path}/css/style.css" rel="stylesheet" type="text/css" />
	<link href="${path}/css/select.css" rel="stylesheet" type="text/css" />
	<script  type="text/javascript" src="${path }/js/jquery.js"></script>	
	<script  type="text/javascript" src="${path }/js/layer.js"></script>
	<script type="text/javascript"> path='${path }'; </script>
	<script  type="text/javascript" src="${path }/js/facility/mobileSwitchList.js?cesa"></script>
	<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
	<script  type="text/javascript" src="${path }/js/prompt/ymPrompt.js"></script>
	<link rel="stylesheet"  type="text/css" href="${path}/js/prompt/skin/ymPrompt.css" />
	<title>开关锁记录</title>
	<script type="text/javascript">
		$(function(){
    		$('.tablelist tbody tr:odd').addClass('odd');
    	})
	</script>
</head>
<body>
 <jsp:include page="/jsp/one/devStatus/devStatusListnavigation.jsp"/>  
    <div class="rightinfo">
	    <!-- 查询数据列表 -->
	    
	    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th>序号<i class="sort"><img src="images/px.gif" /></i></th>
				        <th>设施编号</th>
				        <th>设施名称</th>
				        <th>操作类型</th>
				        <th>用户名称</th>
				        <th>手机号码</th>
				        <th>作业结果</th>
				        <th>作业时间</th>
				        <th>作业时长(/分钟)</th>
				        <th>图片</th>
			        </tr>
		        </thead>
		        <tbody>
			       <c:forEach items="${pb.list}" var="f" varStatus="status">
				        <tr ${f.switchType=='4'?'style="background-color:#eec0ca"':''}>
				        <td>${status.count}</td>
				        <td>${f.devCode}</td>
				        <td>${f.fdevName}</td>
				        <td>
					        <c:choose>
								<c:when test="${f.switchType=='0'}">
									蓝牙开锁
								</c:when>
								<c:when test="${f.switchType=='1'}">
									远程开锁
								</c:when>
								<c:when test="${f.switchType=='2'}">
									扫码开锁
								</c:when>
								<c:when test="${f.switchType=='3'}">
									小程序开锁
								</c:when>
								<c:when test="${f.switchType=='4'}">
									钥匙开锁
								</c:when>
								<c:when test="${f.switchType=='5'}">
									关锁
								</c:when>
								<c:otherwise>
									未知开锁
	   							</c:otherwise>
							</c:choose>
				        </td>
				         <td>${f.userName}</td>
				        <td>${f.mobilePhone}</td>
				        <td>
				        	<c:choose>
								<c:when test="${f.switchType=='5'}">
									作业结束
								</c:when>
								<c:otherwise>
									开始作业
       							</c:otherwise>
							</c:choose>
				        </td>
				         <td>
				         	<fmt:formatDate value="${f.colTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				         </td>
				       <td>${f.workTime}</td>
				        <td><a href="javascript:;" op="pic" did="${f.switchId}"  class="tablelink">查看</a></td>
				        </tr>
		       		</c:forEach>
		        </tbody>
		    </table> 
		    <div id="div_moreSearch" class="newlayer" style="display:none;">
		        <form action="${path}/querySwitchRecord.htm" method="post" id="moreSearchForm" name="moreSearchForm">
				 <input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
				 <input id="devId" name="devId" value="${devId}" type="hidden" />
				 <input id="devName" name="devName" value="${devName}" type="hidden" />
	        	</form>
     		</div> 
     <div class="clear"></div> 
    <jsp:include page="/jsp/one/common/page.jsp" />
    </div>
</body>

</html>
