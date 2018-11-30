<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String rc = request.getContextPath(); 
request.setAttribute("path",rc);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <link href="${path}/css/style.css" rel="stylesheet" type="text/css" />
	<script  type="text/javascript" src="${path }/js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson .header").click(function(){
		var $parent = $(this).parent();
		$(".menuson>li.active").not($parent).removeClass("active open").find('.sub-menus').hide();
		
		$parent.addClass("active");
		if(!!$(this).next('.sub-menus').size()){
			if($parent.hasClass("open")){
				$parent.removeClass("open").find('.sub-menus').hide();
			}else{
				$parent.addClass("open").find('.sub-menus').show();	
			}
			
			
		}
	});
	
	// 三级菜单点击
	$('.sub-menus li').click(function(e) {
        $(".sub-menus li.active").removeClass("active")
		$(this).addClass("active");
    });
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('.menuson').slideUp();
		if($ul.is(':visible')){
			$(this).next('.menuson').slideUp();
		}else{
			$(this).next('.menuson').slideDown();
		}
	});
})	
</script>


</head>

<body style="background:#354f6f;color:#fff;">
	<div class="lefttop" ><span></span>工作台</div>
    
    <dl class="leftmenu"  >
        
    <%-- <dd>
    <div class="title">
    <span><img src="images/leftico01.png" /></span>资源台账管理
    </div>
    	<ul class="menuson">
	        <li><cite></cite><a href="${path}/facilityList.htm" target="rightFrame">设施管理</a><i></i></li>
	        <li><cite></cite><a href="${path}/cableList.htm" target="rightFrame">光缆管理</a><i></i></li>
	        <li><cite></cite><a href="${path}/projectList.htm" target="rightFrame">工程管理</a><i></i></li>
	 		<li><cite></cite><a href="#">按位置浏览</a><i></i></li>
	        <li><cite></cite><a href="facilityRelation.htm" target="rightFrame">拓扑图浏览</a><i></i></li>
	        <li><cite></cite><a href="facilityRelationSimple.htm"  target="rightFrame">设施关系图</a><i></i></li>

	        <li><cite></cite><a href="#">光路查询</a><i></i></li>
        </ul>    
    </dd>
    
   
    
    <dd>
    <div class="title">
    <span><img src="images/leftico02.png" /></span>监控管理
    </div>
    <ul class="menuson">
        <li><cite></cite><a href="${path}/LockList.htm?pb.pageSize=20" target="rightFrame">智能锁管理</a><i></i></li>
        <li><cite></cite><a href="${path}/facilityaccesslist.htm?pb.pageSize=20" target="rightFrame">智能锁授权管理</a><i></i></li>
        <li><cite></cite><a href="${path}/devNewStatusList.htm" target="rightFrame">设施最新状态</a><i></i></li>
        <li><cite></cite><a href="${path}/devStatusList.htm" target="rightFrame">设施监控数据</a><i></i></li>
        <li><cite></cite><a href="${path}/deviceAlarmList.htm"  target="rightFrame">报警管理</a><i></i></li>
        <li><cite></cite><a href="${path}/switchRecordList.htm?pb.pageSize=20"  target="rightFrame">终端开关锁记录</a><i></i></li>
        </ul>     
    </dd> 
    
    <dd>
    <div class="title">
    <span><img src="images/leftico02.png" /></span>巡检管理
    </div>
    <ul class="menuson">
        <li><cite></cite><a href="${path}/inspectList.htm" target="rightFrame">巡检日志管理</a><i></i></li>
        </ul>     
    </dd> 
    
    
    <dd><div class="title"><span><img src="images/leftico03.png" /></span>日志管理</div>
    <ul class="menuson">
        <li><cite></cite><a href="${path}/logsList.htm" target="rightFrame">平台操作日志</a><i></i></li>
        <li><cite></cite><a href="${path}/tmpsList.htm"  target="rightFrame">智能锁数据日志</a><i></i></li>
    </ul>    
    </dd>  
    
    
    
    
    <dd><div class="title"><span><img src="images/leftico01.png" /></span>系统管理</div>
    <ul class="menuson">
    	<li><cite></cite><a href="${path}/rolesList.htm?pb.pageSize=20" target="rightFrame">角色管理</a><i></i></li>
        <li><cite></cite><a href="${path}/pageList.htm?pb.pageSize=20" target="rightFrame">功能管理</a><i></i></li>
        <li><cite></cite><a href="${path}/organizitionList.htm?pb.pageSize=20" target="rightFrame">组织机构管理</a><i></i></li>
        <li><cite></cite><a href="${path}/basecodeList.htm?pb.pageSize=20" target="rightFrame">数据字典管理</a><i></i></li>
        <li><cite></cite><a href="${path}/userList.htm?pb.pageSize=20" target="rightFrame">用户管理</a><i></i></li>
    </ul><img src="${path}${parent.icon}" />
    </dd> --%>
    
     <c:forEach items="${parentList}" var="parent">
     	<dd>
     	    <div class="title"><span><img src="images/leftico01.png" /></span>${parent.pageName}</div>
		    <ul class="menuson">
		        <c:forEach items="${parent.childPages}" var="child">
		            <li><cite></cite><a href="${path}/${child.pageUrl}" target="rightFrame">${child.pageName}</a></li>
		        </c:forEach>
		    </ul>
		</dd>
     </c:forEach>
    
    </dl>
    
</body>
</html>
