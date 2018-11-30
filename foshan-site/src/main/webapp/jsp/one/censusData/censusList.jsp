<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<script  type="text/javascript" src="${path }/js/census/censusList.js"></script>
	<script  type="text/javascript" src="${path }/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet"  type="text/css" href="${path}/js/prompt/skin/ymPrompt.css" />
	<title>普查数据管理</title>
	<style type="text/css">
		#result th ,#result td{
			text-align:left;
		}
		#result td{
		color:red;
		}
	</style>
</head>
<body>
    <!-- 页面导航  -->
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="#">首页</a></li>
	    <li><a href="#">资源台账管理</a></li>
	    <li><a href="#">普查数据管理</a></li>
	    </ul>
    </div>
        
    <div class="rightinfo">
	    <!-- 操作按钮：新增、导入、导出、查询  -->
	    <div class="tools">
	    	<ul class="toolbar">
		        <li class="click" id="censusImport"><a  href="javascript:;" ><span><img src="${path}/images/t02.png" /></span>导入</a></li>
		        <li style="background:none;border:0;margin:0;padding:0;border-radius:0"><div><select name="flag" id="flag"><option value="0">全部重导</option><option value="1">部分替换</option></select><span id="tip" style="display:inline"></span></div></li>
		        <li id="checkdata"><a href="javascript:void(0)">数据校验</a></li>
		    </ul>
		 
	        <div class="simpleQuery">
	           <form>
	              设施编码：<input name="devCode_search"  id="devCodeSearch"  value="${flangePlate.devCode}"  type="text" />
	                <input  id="btnSimpleQuery" value="查询" class="submit" type="button">
	                <button id="btnDisplayMoreQuery" type="button">高级查询</button>
	           </form>
	        </div>  
	    </div>    
	    <!-- 查询数据列表 -->
	    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th><input name="" type="checkbox" value="" checked="checked"/></th>
				        <th>设备编码<i class="sort"><img src="images/px.gif" /></i></th>
				        <th>光缆段编号</th>
				        <th>AB面</th>
				        <th>法兰盘编号</th>
				        <th>端子号</th>
				        <th>端子顺序</th>
				        <th>备注</th>
				        <th>操作人</th>
				        <th>录入时间</th>
			        </tr>
		        </thead>
		        <tbody>
			        <c:forEach items="${pb.list}" var="f">
				        <tr>
				        <td><input name="" type="checkbox" value="" /></td>
				        <td>${f.devCode}</td>
				        <td>${f.cableCode}</td>
				        <td>${f.aorB}</td>
				        <td>${f.flangePlateId}</td>
				        <td>${f.terminal}</td>
				        <td>${f.terminalnum}</td>
				        <td>${f.remark}</td>
				        <td>${f.userId}</td>
				        <td><fmt:formatDate value="${f.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				        
				        <td>
				        </tr>
		       		</c:forEach>
		        </tbody>
		    </table>  
		     <table class="tablelist" id="result" style="display:none">
		    	<thead>
			    	<tr>
				        <th>处理结果：<span id="progress" style="display:inline"></span></th>
			        </tr>
		        </thead>
		        <tbody >
			       
		        </tbody>
		    </table>  
		    <div class="clear"></div>   
		    <jsp:include page="/jsp/one/common/page.jsp" />    
     </div>
    
    <!-- 其它页面元素 如：弹出层等-->
	 <!-- 高级查询 -->
	<div id="div_moreSearch" class="newlayer" style="display:none;">
	       <form action="${path}/censusList.htm" method="post" id="moreSearchForm" name="moreSearchForm">
			   <table width="100%" border="1" class="table1">
			     <tr>
			       <td>设施编码：</td>
			       <td><input id="devCode" name="devCode" type="text"  value="${flangePlate.devCode}" /></td>
		         </tr>
		         <tr>
					<td>开始时间：</td>
					<td><input id="startDate" name="startDate" type="text" onClick="WdatePicker()" value="" /></td>
				</tr>
				<tr>
					<td>结束时间：</td>
					<td><input id="endDate" name="endDate" type="text" onClick="WdatePicker()" value="" /></td>
				</tr>
				<tr>
					<td></td>
				</tr>
			    <tr>
			       <td colspan="4" align="center">
				       <input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
	                   <button type="button" id="btnMoreQuery">查询</button>&nbsp;&nbsp;
	                   <button type="button" name="btnClear" id="btnClear" >清空</button>
                   </td>
		         </tr>                                                   
		       </table>
        </form>
      </div>
<!-- 导入Excel -->
		<div id="div_import" title="导入普查数据" class="newlayer" style="display:none">
			<form action="${path}/censusImport.htm" enctype="multipart/form-data" method="post" id="importForm" target="importFrame">
				<table class="table1">
					<tr>
					    <td>下载模板：</td>
					    <td>
					        <span><a style="color:indigo;font-size:medium;font-family:fantasy" href="${path}/exportFacilityTemp.htm">设施模板文件.xls</a></span>
					    </td>
					</tr>
					<!-- 
					<tr>	     
						<td>下载类型id文件：</td>
					    <td>
					        <span><a href="exportType.htm">类型文件.xls</a></span>
					    </td>
				     </tr>
					-->
					<tr>
					    <td>选择普查数据文件：</td>
					    <td>
					        <input type="file" id="importExcel" name="importExcel" multiple />
					    </td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<button type="button" onclick ="UpladFile()">导入</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div style="display:none;"><iframe id="importFrame" name="importFrame"></iframe></div>

</body>
<script type="text/javascript">

</script>
</html>
