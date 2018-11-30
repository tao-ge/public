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
	<script  type="text/javascript" src="${path }/js/iam/mobileSwitchList.js"></script>
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
 <jsp:include page="/jsp/one/iam/deviceNavigation.jsp"/> 
    <div class="rightinfo">
	    <!-- 查询数据列表 -->
	    
	    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th>序号<i class="sort"><img src="images/px.gif" /></i></th>
				        <th>设施名称</th>
				        <th>设施编号</th>
				        <th>开锁时间</th>
				        <th>关锁时间</th>
				        <th>开锁人</th>
				        <th>作业结果</th>
				        <th>作业时间</th>
				        <th>作业时长(/分钟)</th>
				        <th>最后一次上报时间</th>
				        <th>开锁类型</th>
			        </tr>
		        </thead>
		        <tbody>
			       <c:forEach items="${pb.list}" var="f" varStatus="status">
				        <td>${status.count}</td>
				        <td>${f.devCode}</td>
				        <td>${f.devName}</td>
				        <td>
						   <fmt:formatDate value="${f.openTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
				    	<td>
					    	<fmt:formatDate value="${f.closeTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
				    	</td>
				         <td>
				         	<c:choose>
				         		<c:when test="${f.userName ==null or f.userName eq ''}">未知</c:when>
				         		<c:otherwise>${f.userName}</c:otherwise>
				         	</c:choose>
				         </td>
				        <td>
				        	 <c:if test="${f.resultStatus eq '0'}">失败</c:if>
				        	 <c:if test="${f.resultStatus eq '1'}">成功</c:if>
				        </td>
				         <td>
				         	<fmt:formatDate value="${f.openTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				         </td>
				       <td>${f.workTime}</td>
				        <td>
						    	<fmt:formatDate value="${f.lastModifyTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
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
									机械开锁
								</c:when>
								<c:when test="${f.switchType=='5'}">
									关锁
								</c:when>
								<c:otherwise>
									未知开锁
	   							</c:otherwise>
							</c:choose>
				        </td>
				        </tr>
		       		</c:forEach>
		        </tbody>
		    </table> 
		    <div id="div_moreSearch" class="newlayer" style="display:none;">
		        <form action="${path}/queryimDeviceLockSwitch.htm" method="post" id="moreSearchForm" name="moreSearchForm">
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
