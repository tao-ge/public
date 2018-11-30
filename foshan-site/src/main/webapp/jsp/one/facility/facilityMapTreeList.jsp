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
	<script  type="text/javascript" src="${path }/js/jquery.js"></script>
	<link rel="stylesheet" href="${path }/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css" />	
	<script type="text/javascript" src="${path }/js/ztree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript">
	  //<!--
		var zTree;
		var demoIframe;
		var setting = {
			view: {
				dblClickExpand: false,
				showLine: true,
				selectedMulti: false
			},
			data: {
				simpleData: {
					enable:true,
					idKey: "id",
					pIdKey: "pId",
					rootPId: ""
				}
			},
			callback: {
				beforeClick: function(treeId, treeNode) {
					var zTree = $.fn.zTree.getZTreeObj("tree");
					if (treeNode.isParent) {
						zTree.expandNode(treeNode);
						return false;
					} 
				}
			}
		};
		var zNodes =[];
		<c:forEach items="${areasServiceList}" var="f" varStatus="st">		
		zNodes.push({id:"${f.parentAreaCode}", 
			pId:"${f.parentParentAreaCode}", 
			name:"${f.parentAreaName}",
				children:[
					<c:forEach items="${f.areas}" var="c" varStatus="ct">
						{
							id:"${c.areaCode}",
							pId:"${f.parentAreaCode}",
							name:"${c.areaName}",
							open:true,
							url:"showSitesMap.htm?areaCode1=${c.areaCode}",
							target:"rightMapFrame",
							orgLevel : "${c.areaRank}"
						},
					</c:forEach>
				],
			open:true,
			url:"showSitesMap.htm?areaCode1=${f.parentAreaCode}",
			target:"rightMapFrame",
			orgLevel : "${f.parentAreaRank}"
			});
		</c:forEach>
		$(function(){
		
			var t = $("#tree");
			t = $.fn.zTree.init(t, setting, zNodes);
			var zTree = $.fn.zTree.getZTreeObj("tree");
		});
	
	  function extendAllNode(){
		  var treeObj = $.fn.zTree.getZTreeObj("tree");
		  treeObj.expandAll(true);
	  }
	  
	  function collapseAllNode(){
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		var treeNodes = treeObj.getNodes();
		
		for(var i = 0; i < treeNodes.length; i++){
		    treeNodes[i].open = false;
		}
		$.fn.zTree.init($("#tree"), setting, treeNodes);
	  }
	  </script>


</head>

<body>
	<a href="#" onclick="extendAllNode();">
			<font style="font-size: 12px;">展开全部</font>
			</a>|<a href="#" onclick="collapseAllNode();"><font style="font-size: 12px;">关闭全部</font></a>
		<ul id="tree" class="ztree" style="width: 260px; overflow: auto;"></ul>
		
		<a id="changePoints" href="javascript:;"><span><img
							src="${path}/images/t02.png" /></span>转化</a>
    
</body>
<script type="text/javascript">
		$(function($){
			$("#changePoints").on('click',changePoints); //转化点位
			
			function changePoints()
	    	{
				path = '${path }';
	    		$.ajax({
	    			type:"post",
					url:path+"/changeFacility.htm",
					dataType:"json",
					success:function(data){
						if(data=="true"){
							alert("已经全部转化成百度坐标");
						}else{
							alert(data+"条数据异常，请检查！");
						}
					}
	    		});
	    		
	    	}
		});
</script>
</html>