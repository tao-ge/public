<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% 
String rc = request.getContextPath(); 
request.setAttribute("path",rc);
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10"/>
	<meta http-equiv="x-ua-compatible" content="IE=10" >
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="${path}/css/style.css" rel="stylesheet" type="text/css" />
	<link href="${path}/css/select.css" rel="stylesheet" type="text/css" />
	<script  type="text/javascript" src="${path }/js/jquery.js"></script>	
	<script  type="text/javascript" src="${path }/js/layer.js"></script>
	<script type="text/javascript"> path='${path }'; </script>
	<script type="text/javascript" src="${path }/js/jquery.js"></script>
	<script type="text/javascript" src="${path}/js/select-ui.min.js"></script>
	<script  type="text/javascript" src="${path }/js/iam/hardwareUpgradList.js"></script>
	<script  type="text/javascript" src="${path }/js/prompt/ymPrompt.js"></script>
	<link rel="stylesheet"  type="text/css" href="${path}/js/prompt/skin/ymPrompt.css" />
	<title>设备远程更新</title>
<script type="text/javascript">
		$(document).ready(function(e) {
		
			$(".select3").uedSelect({
				width : 152
			});
		});
		$(function(){
    		$('.tablelist tbody tr:odd').addClass('odd');
    	})
</script>
</head>
<body>
    <!-- 页面导航  -->
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="index.htm">首页</a></li>
	    <li><a href="index.htm">智能设备管理</a></li>
	    <li><a href="hardwareUpgradList.htm">设备固件更新</a></li>
	    </ul>
    </div>
        
    <div class="rightinfo">
	    <!-- 操作按钮：新增、导入、导出、查询  -->
	    <div class="tools">
	    	<ul class="toolbar">
		        <li ><a id="upload" href="javascript:;"><span><img src="${path}/images/t01.png" /></span>上传</a></li>
		    </ul>
	        <div class="simpleQuery">
	           <form name="searchlistform" id="searchlistform">
		                                      版本号：<input name="hardVersion" id="hardVersion" value="${hue. hardVersion}"  type="text" />
		                                      备注：<input name="remark" id="remark" maxlength="500" value="${hue. remark}"  type="text" />
		            <input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
	                <input  id="btnSimpleQuery" value="查询" class="submit" type="button">
	           </form>
	        </div>  
	    </div>    
	    <!-- 查询数据列表 -->
	    <table class="tablelist">
		    	<thead>
			    	<tr style="white-space:nowrap;">
				        <th>上传日期</th>
				        <th>设施类型</th>
				        <th>版本号</th>
				        <th>文件crc</th>
				        <th>文件大小(/byte)</th>
				        <th>备注</th>
				        <th>操作</th>
			        </tr>
		        </thead>
		        <tbody>
			        <c:forEach items="${pb.list}" var="h">
				        <tr>
					        <td>
					        	<fmt:formatDate value="${h.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					        </td>
					        <td>
						        <c:if test="${h.hardType==01}">
						        	光交箱
						        </c:if>
						         <c:if test="${h.hardType==02}">
						        	机房
						        </c:if>
					        </td>
					        <td>${h.hardVersion}</td>
					        <td>${h.hardCrc}</td>
					        <td>${h.hardSize}</td>
					        <td>
					        <c:if test="${fn:length(h.remark)>20 }">
					        	${fn:substring(h.remark,0,20) }...
					        </c:if>
					        <c:if test="${fn:length(h.remark)<=20 }">
					        	${h.remark}
					        </c:if>
					        </td>
					        <td>
					        	<a href="${path}${h.hardUrl}" class="tablelink">下载</a>
					        </td>
				        </tr>
		       		</c:forEach>
		        </tbody>
		    </table>  
		    <div class="clear"></div>   
		    <jsp:include page="/jsp/one/common/page.jsp" />    
     </div>
     
     <!-- 选择文件 -->
     <div id="div_uploadFile" class="newlayer" style="display:none">
	 <form method="post" id="form_uploadFile" name="form_uploadFile" enctype="multipart/form-data" >
	 <ul class="forminfo">
	 	<li><label>选择文件：</label>
	 		<input type="file" id="hardFile" style="width: 345px;height:32px;border-top: solid 1px #1c91e3;border-left: solid 1px #1c91e3;border-right: solid 1px #1c91e3;border-bottom: solid 1px #1c91e3;" name="hardFile" class="deployFile" required="true"/>
	 	</li>
	 	<li><label>版本号：</label>
	 		<input type="text" id="hardVersion1" class="dfinput"  name="hardVersion1" maxlength='8' placeholder="版本号请输入整数或小数"/>
	 	</li>
	 	<li><label>文件crc值：</label>
	 		<input type="text" id="crc" class="dfinput"  name="crc" maxlength='8' placeholder="crc值请输入整数"/>
	 	</li>
	 	<li><label>硬件类型：</label>
		 	<div class="vocation">
		 		<select  class="select3" id="hardType" name="hardType">
	  				<option value="01" selected="selected">光交箱</option>
	  				<option value="02">机房</option>
				</select>
			</div>
	 	</li>
	 	<li><label>备注：</label>
	 		<input type="text" id="remark1" class="dfinput"  maxlength="500" name="remark1"/>
	 	</li>
	 </ul>
	 </form>
	</div>
</body>
</html>
