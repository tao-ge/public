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
	<title>角色管理界面</title>
</head>
<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="index.htm">首页</a></li>
    <li><a href="index.htm">系统管理</a></li>
    <li><a href="pageList.htm">功能管理</a></li>
    </ul>
    </div>
    
    
    <div class="rightinfo">
      <div class="tools">
    	<ul class="toolbar">
        <li ><a href="${path}/pageAdd.htm" target="rightFrame"><span><img src="${path}/images/t01.png" /></span>添加</a></li>
        <%-- <li><a href="${path}/pageDelete.htm" target="rightFrame"><span><img src="${path}/images/t03.png" /></span>删除</a></li> --%>
        </ul>
        <div style="float:right;">
            <form id="searchform"  action="${path}/pageList.htm" method="post">
 	    系统功能名称:	<input id="pageName" name="pageName" type="text" value="${pages.pageName}" />
 	           是否有效 :	<select id="flag" name="flag">
 	           <option value="">==选择==</option>
 	           <option value="0">无效</option>
 	           <option value="1">有效</option>
 	           </select>
 	         <input id="flags" name="flags" type="hidden" value="${pages.flag}" />
 	        <!--  添加分页 zhouyu 12/28 -->
 	          <input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
	    	<%-- <input name="userName" value="${record.userName }" type="text"  /> --%>&nbsp;
    	    <input id="btnSimpleQuery" name="" type="button" class="submit" value="查询"/>
    	    </form>
    	  </div>
        
      </div>
    
    
    <ul class="classlist">
    
	    <table class="tablelist">
	    	<thead>
	    	<tr>
<!-- 	        <th><input name="" type="checkbox" value="" class="all"  checked="checked"/></th> -->
	        <th>系统功能名称</th>
	        <th>功能级别</th>
	        <th>功能排序</th>
	        <th>功能描述</th>
	        <th>所属功能</th>
	        <th>是否有效</th>
	        <th>操作</th>
	        </tr>
	        </thead>
	        <tbody>
	        <c:forEach items="${pb.list}" var="r">
		        <tr>
<!-- 		        <td><input name="" type="checkbox" value="" /></td> -->
		        <td>${r.pageName}</td>
		        <td>
		        <c:if test="${r.pageRank == '1'}">一级</c:if>
		        <c:if test="${r.pageRank == '2'}">二级</c:if>
		        </td>
		        <td>${r.pageOrders}</td>
		        <td>${r.pageDesc}</td>
		        <td>${r.parentPageName}</td>
		        
		        <c:if test="${r.flag eq '1'}"><td>有效</td></c:if>
		        <c:if test="${r.flag eq '0'}"><td>无效</td></c:if>
		        
		        <td><!-- <a href="#" class="tablelink">权限管理</a> -->
		        <a href="${path}/pageUp.htm?pageId=${r.pageId}" class="tablelink">修改</a>  		        
		        <a href="javascript:;" op="del" did="${r.pageId}" dt="${r.pageName}"  class="tablelink"> 删除</a>        
		        </td>
		        
		        </tr> 
	        
       		</c:forEach>
	        </tbody>
	    </table>
    
    </ul>
   
    <div class="clear"></div>
    <jsp:include page="/jsp/one/common/page.jsp" />  
     </div>
</body>
<script type="text/javascript">

$(function(){
	
	$("a[op='del']").on("click",deleteData);
	$("#btnSimpleQuery").on('click',function(){
		var flag = $("#flag").val();
		var pageName = $("#pageName").val();
		if((flag!='' && flag!=null) || (pageName!='' && pageName !=null)){
			$("#pageNo").attr("value",1);
		}
		$("#searchform")[0].submit();
	});
	var p=document.getElementById('flags').value;
	document.getElementById("flag").value=p; 
})
$(".all").click(function(){   
    if(this.checked){   
        $(".tablelist :checkbox").prop("checked", true);  
    }else{   
	$(".tablelist :checkbox").prop("checked", false);
    }   
});

//提交查询表单
function submitQuery()
{
	$("#searchform")[0].submit();
}
/*
 * 分页查询
 */
function pageQuery(){
	
	$("#pageNo").val($(this).attr("pages"));
	
	submitQuery();
}

/*
 * 删除数据
 */
function deleteData(){
	var dt=$(this).attr("dt");	
	var did=$(this).attr("did");
	
	$.ajax({
		type:"POST",
		url:path+"/queryByxjname.htm?pageId="+did,
		success: function(r){
			if(r=="no"){
				layer.alert("下级使用该父级不能删除！");
				
			}
			if(r=="yes"){
				layer.confirm('确认要删除"'+dt+'"？', { btn: ['确定','取消']  },  
						function(){	
							
							window.location.href = path+'/pageDelete.htm?pageId='+did;			   
							
					} , null);
			}
		}
	})
	
	
}

</script>
</html>