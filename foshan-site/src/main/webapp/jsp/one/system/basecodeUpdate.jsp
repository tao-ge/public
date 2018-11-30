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
	<script  type="text/javascript" src="${path }/js/jquery.js"></script>
	<script type="text/javascript" src="${path}/js/select-ui.min.js"></script>
	<title>信息管理系统界面</title>
	<script type="text/javascript">
    	var path="${path}";
    	$(document).ready(function(e) {

    		$(".select3").uedSelect({
    			width : 152
    		});
    	});
    	

    	
    	function checkValue(){
    		var classCode = $("#classCode").val();
    		var className = $("#className").val();
    		var valueCode = $("#valueCode").val();
    		var valueName = $("#valueName").val();
    		
    		if(classCode == null || classCode == ""){
    			alert("请输入代码分类编码！");
    			return false;
    		}
    		if(className == null || className == ""){
    			alert("请输入代码分类名称！");
    			return false;
    		}
    		if(valueCode == null || valueCode == ""){
    			alert("请输入代码值！");
    			return false;
    		}
    		if(valueName == null || valueName == ""){
    			alert("请输入代码名称！");
    			return false;
    		}
    		
    		$("#addForm").submit();
    	}

$(function(){
			
    		/*判断 代码值是否可用（唯一）*/
        	$("#valueCode").blur(function(){	
        		
        		var valueCode = $("#valueCode").val();    		
        		var classCode = $("#classCode").val();
        		var codeId = '${basecode.codeId}';        		
        		 $.ajax({
        		      type: "post",
        		      url: "basecodeValueCodeVerify.htm",
        		      cache: false,
        			  data:{
        				  "valueCode":valueCode,
        				  "classCode":classCode,
        				  "codeId":codeId
        			  },
        			  async: true,
        		      dataType: "json",
        		      success: function(data){
        		    	  	
        		    	 if(data==1){
        		    	  	
        		    	  	$("#qrbc").removeAttr("disabled");
        		    	  	
        		    	 }else{
        		    		 $("#valueCode").val("");
        		    		alert("该代码值已被占用，请重新输入代码值！");
        		    		
        		    	  	$("#qrbc").attr("disabled","disabled");
        		    	  }
        		    	  	 	    	 
        		      }		 
        		}); 
        		 
        		 
        	});
        	
      })
    </script>
</head>
<body>
    <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">系统管理</a></li>
    <li><a href="#">数据字典编辑</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
    
    <form id="addForm" name="addForm" action="${path}/basecodeUpdate.htm" method="post">
    <input type="hidden" id="codeId" name="codeId" value="${basecode.codeId}">
    <ul class="forminfo">
    <li><label>代码分类编码</label><input id="classCode" name="classCode" type="text" class="dfinput" value="${basecode.classCode}" /><font style="font-size: 25px" color=red>*</font></li>
    <li><label>代码分类名称</label><input id="className" name="className" type="text" class="dfinput" value="${basecode.className}" /><font style="font-size: 25px" color=red>*</font></li>

    <li><label>代码值</label>
    
    <input id="valueCode" name="valueCode" type="text" class="dfinput" value="${basecode.valueCode}" />
    
    <font style="font-size: 25px" color=red>*</font></li>
    <li><label>代码名称</label><input id="valueName" name="valueName" type="text" class="dfinput" value="${basecode.valueName}" /><font style="font-size: 25px" color=red>*</font></li>
    <li><label>维护标志</label>
    <div class="vocation">
	    <select class="select3" name="managerSign" id="managerSign">
	    <c:if test="${basecode.managerSign == '1'}">
	    <option value='1' selected="selected">有效</option>
	    <option value='0' >无效</option>
	    </c:if>
	    <c:if test="${basecode.managerSign == '0'}">
	    <option value='1'>有效</option>
	    <option value='0' selected="selected">无效</option>
	    </c:if>
	    </select>
	</div>
    </li>
    <li>
    <input id="qrbc" name="" type="button" class="btn" value="确认保存" onclick="checkValue();"/></li>
    </ul>
    </form>
    
    </div>
    
</body>
</html>