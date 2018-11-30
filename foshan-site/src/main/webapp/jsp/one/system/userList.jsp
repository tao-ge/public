<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	<script type="text/javascript"> path='${path }'; </script>
	<script type="text/javascript" src="${path}/js/select-ui.min.js"></script>
	<script  type="text/javascript" src="${path }/js/prompt/ymPrompt.js"></script>
	<link rel="stylesheet"  type="text/css" href="${path}/js/prompt/skin/ymPrompt.css" />
	<title>信息管理系统界面</title>
	<script type="text/javascript">
		$(function(){
    		$('.tablelist tbody tr:odd').addClass('odd');
    	})
	</script>
</head>
<body>
    <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="index.htm">首页</a></li>
    <li><a href="index.htm">系统管理</a></li>
    <li><a href="userList.htm">用户管理</a></li>
    </ul>
    </div>
	
	<div class="rightinfo">
      <div class="tools">
    	<ul class="toolbar">
    		 <li ><a href="${path}/userAdd.htm" target="rightFrame"><span><img src="${path}/images/t01.png" /></span>添加</a></li>
        <%-- <li><a href="${path}/userDelete.htm" target="rightFrame"><span><img src="${path}/images/t03.png" /></span>删除</a></li> --%>
        </ul>
        
        
        <div style="float:right;">
            <form id="searchform" name="searchform" action="${path}/userList.htm" method="post">
            <input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
	    	<label>真实姓名：</label><input name="userName" type="text" class="scinput1" value="${userName}" />&nbsp;
	    	<!-- <input name="submit" value="查询" class="submit" type="submit"> -->
    	    <input name="search" type="submit" class="submit" value="查询"/>
    	    </form>
    	  </div>    
    
    </div>

    <ul class="classlist">
    
	    <table class="tablelist">
	    	<thead>
	    	<tr>
	        <th>登录用户名</th>
	        <th>真实姓名</th>
	        <th>性别</th>
	        <th>手机号码</th>
	        <th>联系电话</th>
	        <th>最后登录时间</th>
	        <th>是否有效</th>
	        <th>操作</th>
	        </tr>
	        </thead>
	        <tbody>
	        <c:forEach items="${pb.list}" var="u">
	        	<c:choose>
	        		<c:when test="${u.userCode!='admin'}">
				        <tr>
				        <td>${u.userCode}</td>
				        <td>${u.userName}</td>
				        <td>
				        <c:if test="${u.sex == '0'}">未知</c:if>
				        <c:if test="${u.sex == '1'}">男</c:if>
				        <c:if test="${u.sex == '2'}">女</c:if>
				        </td>
				        <td>${u.mobilePhone}</td>
				        <td>${u.contactPhone}</td>
				        <td>
				        	<fmt:formatDate value="${u.lastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss" />
				        </td>
					        <c:if test="${u.flag eq '1'}"> <td>有效</td> </c:if> 
					        <c:if test="${u.flag eq '0'}"> <td><a class="red">无效</a></td> </c:if>
				        <td><a href="${path}/userUp.htm?userId=${u.userId}" class="tablelink">修改</a> 
					         <c:if test="${u.flag eq '1'}">
					        	<c:if test="${u.userCode!=user.userCode}">		        		
					        		<c:if test="${role.roleId==41}">
					        			<a href="javascript:;" op="del" did="${u.userId}" dt="${u.userName}"  class="tablelink"> 删除</a>
					        		</c:if>	
					        	</c:if>
					         </c:if>
				        </td>
				        </tr> 
		      		 </c:when>
	        	</c:choose>
       		</c:forEach>
	        </tbody>
	    </table>
    </ul>
    
    <div class="clear"></div>
    <jsp:include page="/jsp/one/common/page.jsp" />
    
    </div>
    <script type="text/javascript">
    
  //分页浏览查询
	function pageQuery(){
		
		$("#pageNo").val($(this).attr("pages"));
		
		submitQuery();
	}

    $(function(){
    	$("a[op='del']").on("click",deleteData);
    })
  
  	//提交查询表单
	function submitQuery()
	{
		$("#searchform")[0].submit();
	}
    
    /*
     * 删除数据
     */
function deleteData(){
    	
    	var dt=$(this).attr("dt");	
    	var did=$(this).attr("did");
    	
    	//询问框
    	layer.confirm('确认要删除"'+dt+'"？', { btn: ['确定','取消']  },  
    		function(){	
    			
    			window.location.href = path+'/userDelete.htm?userId='+did;			   
    			
    	} , null);
    }

	
    </script>
</body>
</html>