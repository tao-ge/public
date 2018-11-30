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
    		
    		// 判断分类状态
    		var classcode = $("#classCode").val();
    		var className = $("#classCode").find("option:selected").text();
    		if(classcode == null || classcode == "" ){
    			$("#classCodeInput").val("");
    			$("#className").val("");
    			$('#classCodeInput').removeAttr("readonly");
    			$('#className').removeAttr("readonly");
    		} else {
    			$("#classCodeInput").val(classcode);
    			$("#className").val(className);
    			$('#classCodeInput').attr("readonly","readonly");
    			$('#className').attr("readonly","readonly")
    		}
    	});
    	
    	// 下拉列表change事件
    	function changeClasscode(){
    		var classcode = $("#classCode").val();
    		var className = $("#classCode").find("option:selected").text();
    		if(classcode == null || classcode == "" ){
    			$("#classCodeInput").val("");
    			$("#className").val("");
    			$('#classCodeInput').removeAttr("readonly");
    			$('#className').removeAttr("readonly");
    		} else {
    			$("#classCodeInput").val(classcode);
    			$("#className").val(className);
    			$('#classCodeInput').attr("readonly","readonly");
    			$('#className').attr("readonly","readonly")
    		}
    	}
    	
    	function checkValue(){
    		var classCodeInput = $("#classCodeInput").val();
    		var className = $("#className").val();
    		var valueCode = $("#valueCode").val();
    		var valueName = $("#valueName").val();
    		var classCode = $("#classCode").val();
    		
    		if(classCode == null || classCode == ""){
    			alert("请先选择分类");
    			return false;
    		}
    		if(classCodeInput == null || classCodeInput == ""){
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
    		
    		 $.ajax({
    		      type: "post",
    		      url: "basecodeValueCodeVerify.htm",
    		      cache: false,
    			  data:{
    				  "valueCode":valueCode,
    				  "classCode":classCode
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
    
    <form id="addForm" name="addForm" action="${path}/basecodeInsert.htm" method="post">
    <ul class="forminfo">
    <li><label>分类名</label>
    <div class="vocation">
	    <select class="select3" id="classCode" onchange="changeClasscode();">
	    <option value=''></option>
	    </select>
	</div>
    </li>
    <li><label>代码分类编码</label><input id="classCodeInput" name="classCode" type="text" class="dfinput" /><font style="font-size: 25px" color=red>*</font></li>
    <li><label>代码分类名称</label><input id="className" name="className" type="text" class="dfinput" /><font style="font-size: 25px" color=red>*</font></li>

    <li><label>代码值</label>
        
    <input id="valueCode" name="valueCode" type="text" class="dfinput" />
        
    <font  style="font-size: 25px" color=red>*</font></li> 
    
    <li><label>代码名称</label><input id="valueName" name="valueName" type="text" class="dfinput" /><font style="font-size: 25px" color=red>*</font></li>
    <li><label>维护标志</label>
    <div class="vocation">
	    <select class="select3" name="managerSign" id="classCode1">
	    <option value='1'>有效</option>
	    <option value='2'>无效</option>
	    </select>
	</div>
    </li>
    <li><label>&nbsp;</label><input name="directFlg" type="checkbox" value="1" />保存并继续录入&nbsp;
    <input id="qrbc" name="" type="button" class="btn" value="确认保存" onclick="checkValue();"/></li>
    </ul>
    </form>
    
    </div>
    
    <script type="text/javascript">
    	$(function(){
    		var classCode = $('#classCode');
    		if(!$.data(classCode[0],'opt'))
    			{
		    		 $.ajax({url:path+"/ajax/classcode.htm",dataType:"json",
		    			success:function(data)
		    			{
		    				$.data(classCode[0],"opt",data);
		    				$.each(data,function(index,value){
		    					classCode.append($("<option>").prop("value",data[index].name).append(data[index].value));
		    				});
		    			}
		    		 });
    			}
    		else
    			{
    			$.each($.data(classCode[0],'opt'),function(index,value){
    				classCode.append($("<option>").prop("value",index).append(value));
				});
    			}
    	})
    	
     </script>
    
</body>
</html>