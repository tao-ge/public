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
	<!-- <meta http-equiv="refresh" content="5"/>每隔5秒，网页刷新一次 -->
	<title>设施实时监控</title>
    <script type="text/javascript">
    	var path="${path}";
    </script>
</head>
	<frameset rows="*" cols="*" frameborder="no" border="0" framespacing="0">
		  <frameset rows="94%,*" frameborder="no" border="0" framespacing="0" id="baobao">
			    <frame src="${path}/devStatusListEarth.htm" name="topMapFrame" scrolling="auto" noresize="noresize" id="topMapFrame" title="topMapFrame" />
			    <frame src="${path}/queryDeviceAlarm.htm" name="downMapFrame"  id="downMapFrame" title="downMapFrame"/>
		  </frameset>		 
	</frameset>
<noframes><body>
</body></noframes>
</html>
