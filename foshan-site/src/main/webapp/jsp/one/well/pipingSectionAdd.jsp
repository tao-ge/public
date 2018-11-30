<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>管道段编辑界面</title>
   <script type="text/javascript">
    	var path="${path}";
    	$(document).ready(function(e) {

    		$(".select3").uedSelect({
    			width : 152
    		});
    	});
		
    	function checkValue(){
    		var pipingName = $("#pipingName").val();
    		var pipingSectType = $("#pipingSectType").val();
    		var isImport = $("#isImport").val();
    		var phyLen = $("#phyLen").val();
    		var mapLen = $("#mapLen").val();
    		
    		if(pipingName == null || pipingName == ""){
    			alert("光缆端名称不能为空！");
    			return false;
    		} else {
    			// 重复check
    		}
    		
    		if(pipingSectType == null || pipingSectType == ""){
    			alert("请选择光缆状态！");
    			return false;
    		}
    		if(isImport == null || isImport == ""){
    			alert("请选择是否为资管导入！");
    			return false;
    		}
    		if(phyLen == null || phyLen == ""){
    			alert("物理长度不能为空！");
    			return false;
    		}
    		if(mapLen == null || mapLen == ""){
    			alert("地图长度不能为空！");
    			return false;
    		}
    		 
    		$("#addform").submit();
    	}
    	function userNameVerify(){
    		
    		var userCode = $("#userCode").val();
    		$.ajax({
  		      type: "post",
  		      url: "userNameVerify.htm",
  		      cache: false,
  			  data:{
  				  "userCode":userCode,  				  
  			  },
  			  async: true,
  		      dataType: "json",
  		      success: function(data){
  		    	if(data==1){
		    	  	$(".btn").removeAttr("disabled");
		    	 }else{
		    		 $("#userCode").val("");
		    		alert("登陆用户名重复，请重新输入！");
		    	  	$(".btn").attr("disabled","disabled");
		    	  }
  		      }
    		});
    	}
    	
    </script> 
</head>
<body>
    <div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">井资源</a></li>
			<li><a href="#">管道段编辑</a></li>
		</ul>
	</div>
	
	<div class="formbody">
    
    <div class="formtitle"><span>基本信息</span><font></div>
    
    <form id="addform" name="addform" action="${path}/pipingSectionInsert.htm" method="post">
    <ul class="forminfo">
     <li><label>管道段名称：</label><input id="pipingName" name="pipingName" type="text" class="dfinput" onBlur="userNameVerify()" value="${users.userCode}"/><font style="font-size: 25px" color=red>*</font></li>
     <li><label>管道段状态: </label>
    <div class="vocation">
	    <select class="select3" name="pipingSectType" id="pipingSectType" onchange="changeRank();">
	    <option value=''>=请选择=</option>
	    <c:forEach items="${typeList }" var="u">
	    <c:if test="${u.pipingSectType==0}">
	    <option value="${u.pipingSectType}">未审核</option>
	    </c:if>
	    <c:if test="${u.pipingSectType==1}">
	    <option value="${u.pipingSectType}">与现场一致</option>
	    </c:if>
	     <c:if test="${u.pipingSectType==2}">
	    <option value="${u.pipingSectType}">新增</option>
	    </c:if>
	    <c:if test="${u.pipingSectType==3}">
	    <option value="${u.pipingSectType}">修改</option>
	    </c:if>
	    <c:if test="${u.pipingSectType==4}">
	    <option value="${u.pipingSectType}">资管删除</option>
	    </c:if>
	    <c:if test="${u.pipingSectType==5}">
	    <option value="${u.pipingSectType}">新增删除</option>
	    </c:if>
	     <c:if test="${u.pipingSectType==6}">
	    <option value="${u.pipingSectType}">存疑</option>
	    </c:if>
	    </c:forEach>
	    </select>
	</div>
    </li>
    <li><label>是否资管导入：</label>
    <div class="vocation">
	    <select class="select3" name="isImport" id="isImport" onchange="changeRank();">
	    <option value=''>=请选择=</option>
	    <option value='0'>否</option>
	    <option value='1'>是</option>
	    </select>
	</div>
    </li> 
    <li><label>物理长度：</label><input id="phyLen" name="phyLen" type="text" class="dfinput" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" value=""/><font style="font-size: 25px" color=red>*</font></li> 
    <li><label>地图长度：</label><input id="mapLen" name="mapLen" type="text" class="dfinput" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" value=""/><font style="font-size: 25px" color=red>*</font></li> 
    <li><label>&nbsp;</label><input name="directFlg" type="checkbox" value="1" />保存并继续录入&nbsp;
    <input name="" type="button" class="btn" value="确认保存" onclick="checkValue();"/></li>
    </ul>
    </form>
    </div>
	
</body>
</html>