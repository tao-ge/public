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
	<script type="text/javascript"> path='${path }'; </script>
	<script type="text/javascript" src="${path}/js/select-ui.min.js"></script>
	<script  type="text/javascript" src="${path }/js/prompt/ymPrompt.js"></script>
	<link rel="stylesheet"  type="text/css" href="${path}/js/prompt/skin/ymPrompt.css" />
	<script type="text/javascript">
		$(function(){
    		$('.tablelist tbody tr:odd').addClass('odd');
    	})
	</script>
	<title>信息管理系统界面</title>
	<script type="text/javascript">
    	var path="${path}";
    	$(document).ready(function(e) {

    		$(".select3").uedSelect({
    			width : 152
    		});
    	});
    	
    </script>
</head>
<body>
    <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="index.htm">首页</a></li>
    <li><a href="index.htm">系统管理</a></li>
    <li><a href="organizitionList.htm">组织机构管理</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
      <div class="tools">
    	<ul class="toolbar">
    <!-- 	zhouyu 2/6 -->
    	<c:if test="${orgId==0}">
    	 <li ><a href="${path}/organizitionAdd.htm" target="rightFrame"><span><img src="${path}/images/t01.png" /></span>添加</a></li>
    	</c:if>
       
        <%-- <li><a href="${path}/organizitionDelete.htm" target="rightFrame"><span><img src="${path}/images/t03.png" /></span>删除</a></li> --%>
        </ul>
        
       <!--  <ul class="toolbar1"></ul> -->
        
          <div style="float:right;">
            <form id="searchform" name="searchform" action="${path}/organizitionList.htm" method="post">
            <input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
	    	机构名称：<input name="orgName" type="text" value="${orgName }" class="scinput1" />&nbsp;
	    	<!-- 更改name submit -searchSubmit -->
	    	<input name="searchSubmit" value="查询" class="submit" type="submit">
    	    <!-- <input name="search" type="submit" class="scbtn search" value="搜索"/> -->
    	    </form>
    	  </div>    
        
    	
    </div>
    
     <ul class="classlist">
    
	    <table class="tablelist">
	    	<thead>
	    	<tr>
	        <th>机构名称</th>
	        <th>机构地址</th>
	        <th>所属片区</th>
	        <th>联系电话</th>
	        <!-- <th>创建时间</th> -->
	        <th>操作</th>
	        </tr>
	        </thead>
	        <tbody>
	        <c:forEach items="${pb.list}" var="u">
		        <tr>
		        <td>${u.orgName}</td>
		        <td>${u.orgAddress}</td>
		        <td>${u.codeName1}</td>
		        <td>${u.contactPhone}</td>
		        <%-- <td>${u.createData}</td> --%>
		        <td><a href="${path}/organizitionUp.htm?orgId=${u.orgId}" class="tablelink">修改</a>     
		        
		       
		        <%-- <a href="${path}/organizitionDelete.htm?orgId=${u.orgId}" onclick='return confirm( "确定要删除吗? ")' class="tablelink"> 删除</a> --%>
		     <!--   zhouyu 2/6 -->
		       <c:if test="${orgId==0 && u.isDelete<=0}">
		    		 <a href="javascript:;" op="del" did="${u.orgId}" dt="${u.orgName}" isOp="${u.isSynchOpss}" class="tablelink"> 删除</a>
		    	</c:if>
		       <%-- <a href="javascript:;" op="del" did="${u.orgId}" dt="${u.orgName}"  class="tablelink"> 删除</a> --%>
		        
		        </td>
		        </tr> 
       		</c:forEach>
	        </tbody>
	    </table>
    </ul>
    
    <div class="clear"></div>
     
    <jsp:include page="/jsp/one/common/page.jsp" />    
	</div>
	<script type="text/javascript">
  	//提交查询表单
	function submitQuery()
	{
		$("#searchform")[0].submit();
	}

	//分页浏览查询
	function pageQuery(){
		
		$("#pageNo").val($(this).attr("pages"));
		
		submitQuery();
	}
	$(function(){
		$("a[op='del']").on("click",deleteData);
	})
	
	  /*
     * 删除数据
     */
function deleteData(){
    	
    	var dt=$(this).attr("dt");	
    	var did=$(this).attr("did");
    	var isOp=$(this).attr("isOp");
    	if(isOp == '1'){
    		layer.confirm('该组织机构已与光路调度项目同步，不可删除');
    	}else{
    		//询问框
        	layer.confirm('确认要删除"'+dt+'"？', { btn: ['确定','取消']  },  
        		function(){	
        			window.location.href = path+'/organizitionDelete.htm?orgId='+did;			   
        			
        	} , null);
    	}
    	
    }
    </script>
	
</body>
</html>