<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
String rc = request.getContextPath(); 
request.setAttribute("path",rc);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10"/>
	<meta http-equiv="x-ua-compatible" content="IE=10" >
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>${pmName }</title>
    <script type="text/javascript">
    	var path="${path}";
    </script>
</head>
	<frameset rows="88,*,31" cols="*" frameborder="no" border="0" framespacing="0">
		  <frame src="${path}/top.htm" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
		  <frameset cols="187,*" frameborder="no" border="0" framespacing="0">
			    <frame src="${path}/left.htm" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" title="leftFrame" />
			    <frame src="${path}/index.htm" name="rightFrame" id="rightFrame" title="rightFrame" />
		  </frameset>
		  <frame src="${path}/footer.htm" name="bottomFrame" scrolling="No" noresize="noresize" id="bottomFrame" title="bottomFrame" />
	</frameset>
<noframes><body></body></noframes>

</html>