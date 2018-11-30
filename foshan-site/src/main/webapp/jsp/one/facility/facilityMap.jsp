<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
String rc = request.getContextPath(); 
request.setAttribute("path",rc);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>光纤传输网管理云平台</title>
    <script type="text/javascript">
    	var path="${path}";
    </script>
</head>
	<frameset rows="*" cols="*" frameborder="no" border="0" framespacing="0">
		  <frameset cols="187,*" frameborder="no" border="0" framespacing="0">
			    <frame src="${path}/facilityMapTreeList.htm" name="leftMapFrame" scrolling="auto" noresize="noresize" id="leftMapFrame" title="leftMapFrame" />
			    <frame src="${path}/showSitesMap.htm" name="rightMapFrame" id="rightMapFrame" title="rightMapFrame" />
		  </frameset>		 
	</frameset>
<noframes><body></body></noframes>

</html>