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
	<script  type="text/javascript" src="${path }/js/jquery.js"></script>
	<script  type="text/javascript" src="${path }/js/index.js"></script>		
	<title>信息管理系统界面</title>
    <script type="text/javascript">
    	var path="${path}";
    </script>
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    </ul>
    </div>
    
    <div class="mainindex">
    
    
    <div class="welinfo">
    <span><img src="images/sun.png" alt="" /></span>
    <b>您好，欢迎使用${pmName }</b>
     <!-- (liuyou@ycthingnet.com) -->
<%--     <a href="mailto:liuyou@ycthingnet.com?subject=${userName}的反馈(光纤传输网管理云平台)">问题反馈</a> --%>
    </div>
    
    <div class="welinfo">
    <span><img src="images/time.png" alt="时间" /></span>
    <i>您上次登录的时间：<fmt:formatDate value="${lastLoginTime}" pattern="yyyy-MM-dd HH:mm" /></i> 
    </div>
    
    <div class="xline"></div>
    
    <ul class="iconlist">
    <c:if test="${fn:contains(pidString,'42')}">
    <li><a href="${path}/facilityList.htm"><img src="images/guanli.png" /></a><p><a href="${path}/facilityList.htm" style="color:#000">设施管理</a></p></li>
    </c:if>
    <c:if test="${fn:contains(pidString,'49')}">
    <li><a href="${path}/facilityaccesslist.htm?pb.pageSize=20"><img src="images/shouquan.png" /></a><p><a href="${path}/facilityaccesslist.htm?pb.pageSize=20"style="color:#000">用户授权管理</a></p></li>
    </c:if>
    <c:if test="${fn:contains(pidString,'44')}">
    <%-- <li><a href="${path}/facilityMap.htm"><img src="images/liulan.png" /></a><p><a href="${path}/facilityMap.htm"style="color:#000"style="color:#000">按位置浏览</a></p></li> --%>
    <li><a href="${path}/facilityBdMap.htm"><img src="images/liulan.png" /></a><p><a href="${path}/facilityBdMap.htm"style="color:#000"style="color:#000">按位置浏览</a></p></li>
    </c:if>
    <c:if test="${fn:contains(pidString,'46')}">
    <li><a href="${path}/facilityRelationSimple.htm"><img src="images/guanxitu.png" /></a><p><a href="${path}/facilityRelationSimple.htm"style="color:#000">设施关系图</a></p></li>
    </c:if>
    <c:if test="${fn:contains(pidString,'50')}">
    <li><a href="${path}/devNewStatusList.htm"><img src="images/zhuangtai.png" /></a><p><a href="${path}/devNewStatusList.htm"style="color:#000">设备当前状态</a></p></li>
   </c:if>
    <c:if test="${fn:contains(pidString,'80')}">
    <li><a href="${path}/deviceAlarmList.htm"><img src="images/baojing.png" /></a><p><a href="${path}/deviceAlarmList.htm"style="color:#000">报警记录</a></p></li> 
     </c:if>
         
    </ul>
    

    <div class="xline"></div>
    <div class="box"></div>

<table width="100%" border="1">
  <tr>
    <td>
    <div class="welinfo">
    <span><img src="images/dp.png" alt="提醒" /></span>
    <b>最新开锁记录</b>
    </div>    
    <ul class="infolist">
	    <c:if test="${empty msPb.list}">
        <li style="background: rgba(0, 0, 0, 0) url('');"><a></a></li>
        <li style="background: rgba(0, 0, 0, 0) url('');"><a></a></li>
        <li style="background: rgba(0, 0, 0, 0) url('');"><a></a></li>
 		</c:if>
 	
	    <c:forEach items="${msPb.list}" var="f" varStatus="status">
	    	<li style="width: 427px;"><a href="${path}/switchRecordList.htm">${f.fName}:<c:choose><c:when test="${f.oprTypes=='0'}">关闭</c:when><c:when test="${f.oprTypes=='1'}">开锁</c:when><c:when test="${f.oprTypes=='2'}">应急开锁</c:when><c:otherwise>其它</c:otherwise></c:choose>
				<c:choose><c:when test="${f.resultStatus=='0'}">(异常)</c:when><c:when test="${f.resultStatus=='1'}">(正常)</c:when><c:otherwise>(其它)</c:otherwise></c:choose>
				<fmt:formatDate value="${f.colTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
	    	  </a></li>
	    	  <c:if test="${status.last}" >    
	    		<c:if test="${status.count != 3}" >
	    		<c:set var="num" value="${3-(status.count % 3)}"></c:set>
					    			<c:forEach begin="1" end="${num}">
					    				<li style="background: rgba(0, 0, 0, 0) url('');"><a></a></li>
					    			</c:forEach>
				</c:if>
	    	</c:if>
	    </c:forEach>
    </ul>
    </td>
    <td>
    <div class="uimakerinfo"><b>最新报警信息</b></div>
    
    <ul class="infolist">
    	<c:if test="${empty dasPb.list}">
        <li style="background: rgba(0, 0, 0, 0) url('');"><a></a></li>
        <li style="background: rgba(0, 0, 0, 0) url('');"><a></a></li>
        <li style="background: rgba(0, 0, 0, 0) url('');"><a></a></li>
 		</c:if>
	    <c:forEach items="${dasPb.list}" var="f" varStatus="status">
	    	<li style="width: 427px;"><a href="${path}/deviceAlarmList.htm">${f.fName}:${f.alarmContent}<fmt:formatDate value="${f.alarmTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
	    	</a></li>    
	    	<c:if test="${status.last}" >
	    		<c:if test="${status.count != 3}" >
	    		<c:set var="num" value="${3-(status.count % 3)}"></c:set>
					    			<c:forEach begin="1" end="${num}">
					    				<li style="background: rgba(0, 0, 0, 0) url('');"><a></a></li>
					    			</c:forEach>
				</c:if>
	    	</c:if>
	    </c:forEach>
    </ul>    
    </td>
  </tr>
</table>
 
    <div class="xline"></div>
    
    </div>
    
    

</body>
</html>
