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
   <script type="text/javascript" src="${path}/js/jquery.js"></script>
   <script type="text/javascript">
		$(function(){	
			//顶部导航切换
			$(".nav li a").click(function(){
				$(".nav li a.selected").removeClass("selected")
				$(this).addClass("selected");
			})	
		})	
	</script>
</head>
<body style="background:url(${path}/images/topbg.gif) repeat-x;">

    <div class="topleft">
    <c:if test="${ipValue==1 }">
    	<a  target="_parent"><img src="${path}/images/p1.png" title="系统首页" /></a>
    </c:if>
    <c:if test="${ipValue==0 }">
    	<a  target="_parent"><img src="${path}/images/p3.png" title="系统首页" /></a>
    </c:if>
    </div>
        
    <ul class="nav">
    <li><a href="index.htm" target="rightFrame" class="selected" ><img src="${path}/images/top1.png" title="桌面" /><h2>桌面</h2></a></li>
    <li><a href="javascript:history.back(-1)" target="rightFrame"><img src="${path}/images/top3.png" title="后退" /><h2>后退</h2></a></li>
    <li><a href="javascript:history.back(1)"  target="rightFrame"><img src="${path}/images/top2.png" title="前进" /><h2>前进</h2></a></li>
    
    <li><a href="javascript:window.parent.frames['rightFrame'].location.reload();" target="rightFrame"><img src="${path}/images/top4.png" title="修改密码" /><h2>刷新</h2></a></li>
    <li><a href="changePWD.htm"  target="rightFrame"><img src="${path}/images/top5.png" title="修改密码" /><h2>修改密码</h2></a></li>
    <li><a href="switchAccount.htm"  target="rightFrame"><img src="${path}/images/top4.png" title="切换账号" /><h2>切换账号</h2></a></li>
    
    <li>
    	<a href="downloadAPP.htm"  target="rightFrame">
    		<img src="${path}/images/top7.png" />
		    <h2>APP下载</h2> 
		</a>
    </li>
    
    <li><a href="#" onclick="javascript:if(confirm('您确定要退出${pmName}？')){parent.location.href='userLogout.htm';}"   ><img src="${path}/images/top6.png" title="退出" /><h2>退出</h2></a></li>
    </ul>
            
    <div class="topright">    
    <ul>
    <li><span><img src="${path}/images/help.png" title="帮助"  class="helpimg"/></span><a href="http://39.108.48.7:8080/#Contact%20us" target="_Blank">帮助</a></li>
    <li><a href="http://39.108.48.7:8080/#Partner%20&%20affiliates" target="_Blank">关于</a></li>
    
    </ul>
     
    <div class="user">
    <span>${sessionScope.platformUser.userName }</span>
    <i>报警：</i>
    <b>${dasCount.rows}</b>
    </div>    
    
    </div>

</body>
</html>
