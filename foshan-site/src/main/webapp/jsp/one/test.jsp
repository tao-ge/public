<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
String rc = request.getContextPath(); 
request.setAttribute("path",rc);
%>
<!DOCTYPE html>
<html>
<head>
 
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10"/>
	<meta http-equiv="x-ua-compatible" content="IE=10" >
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="${path}/css/style.css" rel="stylesheet" type="text/css" />
	<link href="${path}/css/select.css" rel="stylesheet" type="text/css" />
	<script  type="text/javascript" src="${path }/js/jquery.js"></script>	
	<script  type="text/javascript" src="${path }/js/layer.js"></script>
	<script type="text/javascript"> path='${path }'; </script>
<%-- 	<script  type="text/javascript" src="${path }/js/facility/facilityList.js"></script> --%>
	<script  type="text/javascript" src="${path }/js/prompt/ymPrompt.js"></script>
	<link rel="stylesheet"  type="text/css" href="${path}/js/prompt/skin/ymPrompt.css" />
	<title>设施管理</title>

</head>
<body>
    <!-- 页面导航  -->
	<div class="place">
	    <input id="sendBtn" type="button" onclick="ajax01()" value="按钮"/>
    </div>
        
    
</body>
<script type="text/javascript">
	function ajax01(){
		$.ajax({
		   type: "POST", 
		   url: path+'/m/addGroupNew.htm',
		   data:{imei:"123",timestamp:"1526606911623",devType:"1",dto:{devId:376,side:'abcd',portNum:12,discNum:1,startIndex:2}},
		   dataType : "json",
		   success: function(data){
			   alert(JSON.stringify(data)); 
		   }
		}); 
	}
</script>
</html>
