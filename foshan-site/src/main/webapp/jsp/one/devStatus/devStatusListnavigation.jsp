
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link href="${path}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/select.css" rel="stylesheet" type="text/css" />
<html>
<!-- 
设施详情公共导航
需要参数：devId  devName
 -->		
  			<div style="background-color: #E8E8E8;">
 			<div class="tools">
	 			
	 			<div class="simpleQuery">
				    <button id="btn" onclick="ShowHide()" value="1" style="color: red;">展开/收起</button>
			    </div>
			    <div class="simpleQuery" style="position: absolute;left: 5px;">
	 					<a>${devName}</a>
	 			</div>
			</div>
			</div>
		    <div class="itab">
			  	<ul> 
			    <li >
				    <a  href="${path}/querySwitchRecord.htm?devId=${devId}&devName=${devName}&areaCode1=${areacode}" 
				    ${!(flag eq 'switchRecord') ? "" : "class=\"selected\""}>开关锁记录</a>
				</li> 
			    <li>
				    <a href="${path}/querydevTimeNewStatu.htm?devId=${devId}&devName=${devName}"
			         ${!(flag eq 'devTimeNewStatus') ? "" : "class=\"selected\""}>定时上报记录</a>
			    </li> 
			     <li>
				    <a href="${path}/queryDeviceAlarm.htm?devId=${devId}&devName=${devName}"
			         ${!(flag eq 'deviceAlarm') ? "" : "class=\"selected\""}>报警记录</a>
			    </li> 
				</ul>
		    </div> 
    <body>
    <script type="text/javascript">
     function ShowHide(){
    	 var i= $("#btn").val();
    	 if(i%2==0){
    		 window.parent.baobao.rows='94%,*';
    		 $("#btn").val("1");
    	 }else{
    	 	window.parent.baobao.rows='60%,*';
    	 	$("#btn").val("0");
    	 }
     }
    </script>
    </body>
</html>