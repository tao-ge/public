<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% 
String rc = request.getContextPath(); 
request.setAttribute("path",rc);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="${path}/css/style.css" rel="stylesheet" type="text/css" />
	<link href="${path}/css/select.css" rel="stylesheet" type="text/css" />
	<script  type="text/javascript" src="${path }/js/jquery.js"></script>
	<script type="text/javascript" src="${path}/js/layer.js"></script>
	<script type="text/javascript"> path='${path }'; </script>
	<script  type="text/javascript" src="${path }/js/cable/errorRecCableList.js?"></script>
	<script type="text/javascript">
	</script>
	
	<title>纠错光缆管理</title>
</head>
<body  >
	<div class="place" >
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">资源台账管理</a></li>
    <li><a href="#">纠错光缆段管理</a></li>
    </ul>
    </div>
    	
    <div class="rightinfo" >
      <div class="tools">
		<ul class="toolbar">
			
	   </ul>
		<div class="simpleQuery">
		     <form >
			    	光缆段编码:<input name="cableCode_Search" id="cableCodeSearch" value="${cableList.secCode}"  type="text"/>
			    	光缆段名称:<input name="cableName_Search" id="cableNameSearch" value="${cableList.secName}"  type="text" />
			    	<%-- <input type="checkbox" size="5px"  style="vertical-align: middle;" id="checkedbox" ${cableList.check==1?"checked":false} name="checkedbox"> 
			    		<a id="states" >成端状态:</a>
			    		<select name="isTerminat1" id="isTerminat1">
								<option value="">全部</option>
								<c:forEach items="${CableTypeMap}" var="item"
									varStatus="status">
									<option value="${item.key}"
										<c:if test="${item.key == cableList.isTerminat}">selected ="selected"</c:if>>${item.value}</option>
								</c:forEach>
						</select>
					 --%>
		    		<input  id="btnSimpleQuery" value="查询" class="submit" type="button">&nbsp;&nbsp;
		    		<button id="btnqueryMore" name="btnqueryMore" type="button">高级查询</button>
		     </form>
		</div>
    </div>
</div>
	    <table class="tablelist" >
	    	<thead>
	    	<tr style="white-space:nowrap;">
	    	<th width="7%">光缆段编码</th>
	        <th width="16%">光缆段名称</th>
	        <th width="7%">A端设施编码</th>
	        <th width="12%">A端设施名称</th>
	        <th width="7%">Z端设施编码</th>
	        <th width="12%">Z端设施名称</th>
	        <th width="5%">纤芯数</th>
	        <th width="6%">光缆状态</th>
	        <th width="8%">A端成端</th>
	        <th width="8%">Z端成端</th>
	      <!--   <th>操作</th> -->
	        </tr>
	        </thead>
	        <tbody>
	        <c:forEach items="${pb.list}" var="f">
		        <tr>
		        <td>${f.secCode}</td>
		        <td>${f.secName}</td>
		        <td>${f.devCodeA}</td>
		        <td>${f.adevName}</td>
		        <td>${f.devCodeZ}</td>
		        <td>${f.zdevName}</td>
		        <td>${f.fiberNum}</td> 

				<c:choose>
					<c:when test="${f.devState eq '0'}"><td><font style="font-weight:bold;color:red">未核查</font></td></c:when>
					<c:when test="${f.devState eq '1'}"><td><font style="font-weight:bold;color:red">正常</font></td></c:when>
					<c:when test="${f.devState eq '2'}"><td><font style="font-weight:bold;color:red">新增</font></td></c:when>
					<c:when test="${f.devState eq '3'}"><td><font style="font-weight:bold;color:red">修改</font></td></c:when>
					<c:when test="${f.devState eq '4'}"><td><font style="font-weight:bold;color:red">资管删除</font></td></c:when>
					<c:when test="${f.devState eq '5'}"><td><font style="font-weight:bold;color:red">新增删除</font></td></c:when>
					<c:otherwise><td><font style="font-weight:bold;color:red">  </font></td></c:otherwise>
					</c:choose>
				<td>
					<c:choose>
						<c:when test="${f.inusedA!=null&&f.inusedA!=''}"><font style="font-weight:bold;color:blue">成端:${f.inusedA}<br></font></c:when>
					</c:choose>
					<c:choose>
						<c:when test="${f.investmentA!=null&&f.investmentA!=''}"><font style="font-weight:bold;color:blue">直熔:${f.investmentA} <br></font></c:when>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${f.inusedZ!=null&&f.inusedZ!=''}"><font style="font-weight:bold;color:blue">成端:${f.inusedZ}<br></font></c:when>
					</c:choose>
					<c:choose>
						<c:when test="${f.investmentZ!=null&&f.investmentZ!=''}"><font style="font-weight:bold;color:blue">直熔:${f.investmentZ} </font></c:when>
					</c:choose>
				<%-- </td> 
	        	<td><a href="javascript:;" op="chart" did="${f.devCodeA}" dt="${f.devCodeZ}" dts="${f.sectId}" class="tablelink" >成端详情</a>
	        	<a href="${path}/cableInfo.htm?sectId=${f.sectId}" class="tablelink" >详情</a>
	        		<a href="javascript:;" op="del" did="${f.sectId}" dt="${f.secName}" class="tablelink" >删除</a>
	        	</td> --%>
       		</c:forEach>
	        </tbody>
	    </table>
  <div class="clear" ></div>
  <jsp:include page="/jsp/one/common/page.jsp" />      
    
	<!-- 导出弹层下载 -->
	<div id="div_export" class="newlayer" style="display:none;">
	     <center> <a id="exportDown" href="" target="_blank" style="font-size:18px;margin-top:120px">数据生成成功，点此下载！</a></center>
   	</div>
   <!-- 	高级查询 -->
   	<div id="moreSearch" class="newlayer" style="display:none;">
	       <form  method="post" id="SearchForm" name="SearchForm" >
			   <table width="100%" border="1" class="table1" >
			   		<tr><td>所属区:</td>
				     <td><select class="areadevCode" id="areadevCode" name="areadevCode" runat="server" selected="selected">
			     		<option value="">全部</option>
			     			<c:forEach items="${areaList}" var="area" >
			     				<option value="${area.name}" 
			     				<c:if test="${area.name==areadevCode1}">selected="selected"</c:if>	
			     			>
			     				${area.value}
			     			</option>
			     			</c:forEach>
			     		</select>
			     		</td>
		     	
				    <td> 汇聚区:</td>
				    <td><select name="areaCode1" id="areaCode" class="hjq" runat="server" selected="selected"></select> 
				     	</td>
			     </tr>
				 <tr>
			       <td>光缆段名称：</td>
			       <td><input id="secName" name="secName" type="text"  value="${cableList.secName}" class="scinput1"/></td>			     
		        
			       <td>光缆编码：</td>
			       <td><input id="secCode" name="secCode" type="text"  value="${cableList.secCode}" class="scinput1"/></td>
			     </tr>			
			      
			     <tr>
				    <td> 设施名称:</td>
				    <td><input name="devName" id="devName" type="text" value="${cableList.devName}" class="scinput1"></input> 
				     	</td>
			    
				    <td> 设施编码:</td>
				    <td><input name="devCode" id="devCode" type="text" value="${cableList.devCode}"class="scinput1"></input> 
				     	</td>
			     </tr>
			      <tr>
			     
			     <tr>
			       <td colspan="4" align="center">
	                   <button type="button" id="btnMoreQuery">查询</button>&nbsp;&nbsp;
		        		<button type="button" id="btnExport">导出</button>&nbsp;&nbsp;
		        	 	<button type="button" name="btnClear" id="btnClear" >清空</button>
		        	 	 <input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
		        	 </td>
		         </tr>
		       </table>
        </form>
      </div>
</body>
</html>
