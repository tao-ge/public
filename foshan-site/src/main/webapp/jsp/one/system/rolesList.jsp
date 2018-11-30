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
	<script  type="text/javascript" src="${path }/js/layer.js"></script>
	<script type="text/javascript" src="${path}/js/select-ui.min.js"></script>
	<script  type="text/javascript" src="${path }/js/prompt/ymPrompt.js"></script>
	<link rel="stylesheet"  type="text/css" href="${path}/js/prompt/skin/ymPrompt.css" />
	<script type="text/javascript"> path='${path }'; </script>
	<script type="text/javascript">
		$(function(){
    		$('.tablelist tbody tr:odd').addClass('odd');
    	})
	</script>
	<title>角色管理界面</title>
</head>
<body>
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
		    <li><a href="index.htm">首页</a></li>
		    <li><a href="index.htm">系统管理</a></li>
		    <li><a href="rolesList.htm">角色管理</a></li>
	    </ul>
    </div>
    
    <div class="rightinfo">
      <div class="tools">
    	<ul class="toolbar">
        <li ><a href="${path}/rolesAdd.htm" target="rightFrame"><span><img src="${path}/images/t01.png" /></span>添加</a></li>
        <%-- <li><a href="${path}/rolesDelete.htm" target="rightFrame"><span><img src="${path}/images/t03.png" /></span>删除</a></li> --%>
        </ul>
        
      </div>
    
    
    <ul class="classlist">
    
	    <table class="tablelist">
	    	<thead>
	    	<tr>
<!-- 	        <th><input name="" type="checkbox" value=""  class="all"/>全选</th> -->
	        <th>角色名称</th>
	        <th>备注</th>
	        <th>操作</th>
	        </tr>
	        </thead>
	        <tbody>
	        <c:forEach items="${rolesList}" var="r">
		        <tr>
<!-- 			        <td><input name="" type="checkbox" value="" /></td> -->
			        <td>${r.roleName}</td>
			        <td>${r.remark}</td>
			        <td><!-- <a href="#" class="tablelink">权限管理</a>   -->   
				        <a href="${path}/rolesUp.htm?roleId=${r.roleId}" class="tablelink">修改</a> 
					    <c:forEach items="${rauMap}" var="mp">
					    <c:if test="${mp.key == r.roleName }">
					        <c:if test="${r.roleId!='40'  && mp.value == 'yes'}">   
<%-- 					       		<a href="${path}/rolesDelete.htm?roleId=${r.roleId}"  onclick='return confirm( "确定要删除吗? ")'  class="tablelink"> 删除</a> --%>
					        	<a href="javascript:;" op="del" did="${r.roleId}" dt="${r.roleName}"  class="tablelink"> 删除</a>
					        </c:if>
					    </c:if>
					    </c:forEach>
			        </td>
		        </tr> 
	        
       		</c:forEach>
	        </tbody>
	    </table>
    
    </ul>
    </div>
    <div class="clear"></div>
    
</body>
<script type="text/javascript">

	$(function(){
		$("a[op='del']").on("click",deleteData);
	})
	
	/*
	 * 删除数据
	 */
	function deleteData(){
		
		var dt=$(this).attr("dt");	
		var did=$(this).attr("did");
		
		//询问框
		layer.confirm('确认要删除"'+dt+'"？', { btn: ['确定','取消']  },  
			function(){	
				window.location.href = path+'/rolesDelete.htm?roleId='+did;			   
				
		} , null);
	}

$(".all").click(function(){   
    if(this.checked){   
        $(".tablelist :checkbox").prop("checked", true);  
    }else{   
	$(".tablelist :checkbox").prop("checked", false);
    }   
});
</script>
</html>
