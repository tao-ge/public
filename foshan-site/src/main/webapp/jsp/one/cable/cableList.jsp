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
	<script  type="text/javascript" src="${path }/js/cable/cableList.js?iwyyy1"></script>
	<script type="text/javascript">
	$(function(){
		$('.tablelist tbody tr:odd').addClass('odd');
	})
	</script>
	
	<title>光缆管理</title>
</head>
<body  >
	<div class="place" >
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="index.htm">首页</a></li>
    <li><a href="index.htm">资源台账管理</a></li>
    <li><a href="cableList.htm">光缆段管理</a></li>
    </ul>
    </div>
    	
    <div class="rightinfo" >
      <div class="tools">
		<ul class="toolbar">
			
	   </ul>
		<div class="simpleQuery">
		     <form >
		     	
		      		所属区:<select class="areadevCode" id="areadevCode" name="areadevCode" runat="server" selected="selected">
		     			<option value="">全部${areadevCode1 }</option>
		     			<c:forEach items="${areaList}" var="area" >
		     			<option value="${area.name}" 
		     				<c:if test="${area.name==areadevCode1}">selected="selected"</c:if>	
		     			>
		     				${area.value}
		     			</option>
		     			</c:forEach>
		     		</select>
			     	汇聚区:<select name="areaCode1" id="areaCode" class="hjq" runat="server" selected="selected">
			     
			     	</select> 
			     	<%--  设施编码:<input name="dev_code" id="devCodeSearch" value="${cableList.devCode}"  type="text"/>
			     	设施名称:<input name="dev_name" id="devNameSearch" value="${cableList.devName}"  type="text"/>--%>
			    	光缆编码:<input name="cableCode_Search" id="cableCodeSearch" value="${cableList.secCode}"  type="text"/>
			    	光缆名称:<input name="cableName_Search" id="cableNameSearch" value="${cableList.secName}"  type="text" /> 
			    	<%-- <input type="checkbox" size="5px"  style="vertical-align: middle;" id="checkedbox" ${cableList.check==1?"checked":false} name="checkedbox">  --%>
			    		<a id="states" >成端状态:</a>
			    		<select name="isTerminat1" id="isTerminat1">
								<option value="all">全部</option>
								<c:forEach items="${CableTypeMap}" var="item"
									varStatus="status">
									<option value="${item.key}"
										<c:if test="${item.key == cableList.isTerminat}">selected ="selected"</c:if>>${item.value}</option>
								</c:forEach>
						</select>
						<%-- <a >光缆状态:</a>
			    		<select name="sectState1" id="sectState1">
								<option value="">全部</option>
								<c:forEach items="${SectSectionState}" var="item"
									varStatus="status">
									<option value="${item.key}"
										<c:if test="${item.key == cableList.sectState}">selected ="selected"</c:if>>${item.value}</option>
								</c:forEach>
						</select> --%>
					
		    		<input  id="btnSimpleQuery" value="查询" class="submit" type="button">&nbsp;&nbsp;
		    		<input  id="btnDisplayMoreQuery" value="高级查询" class="submit" type="button">&nbsp;&nbsp;
		    		
<!--		    		<button type="button" id="btnExportQueryCablin">导出机柜光缆段</button>
		    		<button type="button" id="btnXLSPortInfo">导出纤芯占用详细</button>
		    		<button type="button" id="btnXLSPortStat">导出纤芯占用统计</button>
-->
		     </form>
		</div>
    </div>
</div>
	    <table class="tablelist" >
	    	<thead>
	    	<tr style="white-space:nowrap;">
	    	<th width="8%">光缆段编码</th>
	        <th width="12%">光缆段名称</th>
	        <th width="8%">A端设施编码</th>
	        <th width="12%">A端设施名称</th>
<!-- 	        <th width="110px">A端成端开始位置</th> 2017-9-26  刘沧海改 -->
	        <th width="8%">Z端设施编码</th>
	        <th >Z端设施名称</th>
<!-- 	        <th width="110px">Z端成端开始位置</th> 2017-9-26  刘沧海改-->
	        <th width="5%">纤芯数</th>
<!-- 	        <th width="70px">纤芯序号</th> 2017-9-26  刘沧海改-->
	        <th width="6%">光缆状态</th>
	        <th width="6%">成端状态</th>
	        <th>操作</th>
	        </tr>
	        </thead>
	        <tbody>
	        <c:forEach items="${pb.list}" var="f">
		        <tr style="word-wrap:break-word;word-break:break-all;">
		        <td>${f.secCode}</td>
		        <td width="200px;">${f.secName}</td>
 		        <td>${f.devCodeA}</td> 
		        <td>${f.adevName}</td>
<%-- 		        <td>${f.acode}</td> --%>
 		        <td>${f.devCodeZ}</td> 
		        <td>${f.zdevName}</td>
<%-- 		        <td>${f.zcode}</td>  --%>
		        <td>${f.fiberNum}</td> 
<%-- 		        <td>${f.fiberNo}</td> --%>

				<c:choose>
					<c:when test="${f.sectState eq '1'}"><td><font style="font-weight:bold;color:red">与现场一致</font></td></c:when>
					<c:when test="${f.sectState eq '2'}"><td><font style="font-weight:bold;color:red">新增</font></td></c:when>
					<c:when test="${f.sectState eq '3'}"><td><font style="font-weight:bold;color:red">修改</font></td></c:when>
					<c:when test="${f.sectState eq '4'}"><td><font style="font-weight:bold;color:red">未找到</font></td></c:when>
					<c:when test="${f.sectState eq '5'}"><td><font style="font-weight:bold;color:red">新增删除</font></td></c:when>
					<c:otherwise><td><font style="font-weight:bold;color:red">  </font></td></c:otherwise>
					</c:choose>
				<td>${f.isTerminat1}</td> 
				<td>
				<c:if test="${f.devCodeA != null and f.devCodeZ != null}">
		        	<a href="javascript:;" op="chart" did="${f.devCodeA}" dt="${f.devCodeZ}" dts="${f.sectId}" class="tablelink" >成端详情</a>
				</c:if>
	        	<a href="${path}/cableInfo.htm?sectId=${f.sectId}" class="tablelink" >详情</a>
	        		<a href="javascript:;" op="del" did="${f.sectId}" dt="${f.secName}" class="tablelink" >删除</a>
	        	</td>
       		</c:forEach>
	        </tbody>
	    </table>
  <div class="clear" ></div>
  <jsp:include page="/jsp/one/common/page.jsp" />      
    
    <!-- 高级查询弹出层  -->
    <div id="div_moreSearch"   class="newlayer" style="display:none;">
			 <form action="${path}/cableList.htm" method="post" id="moreSearchForm" name="moreSearchForm">
			  <table width="100%" border="1"  class="table1">
			   <input type="hidden" id="areadevCode1" name="areadevCode1" /> 
			   <%-- <tr>
			       <td>所属片区：</td>
			       <td><label for="select"></label>
			         <select class="areadevCode" name="areadevCode1" id="areadevCode1" runat="server" selected="selected">
			           <option value="">全部</option>
	                         <c:forEach  items="${areaList}" var="item" varStatus="status">
	                            <option value="${item.name}" <c:if test="${item.name == areadevCode1}">selected ="selected"</c:if>>${item.value}</option>
	                         </c:forEach>
                      </select>
                   </td>
                   <td>汇聚区</td>
		 		<td>
		 		<select name="areaCode1" id="areaCode1" class="hjq" runat="server" selected="selected"> 
		 	
		 		</select>
		         </tr>   --%>
		         
			     <input name="areaCode1" id="areaCode1" class="hjq" type="hidden" value="${cableList.areaCode1}" />  
			     <input name="areaCode2" id="areaCode2" class="hjq" type="hidden" value="${cableList.areaCode2}" />  
					
			     <tr>
			       <td>光缆名称：</td>
			       <td><input name="secName" id="secName" type="text" value="${cableList.secName}" /></td>
			       <td>光缆编码：</td>
			       <td><input name="secCode" id="secCode" type="text" value="${cableList.secCode}"/></td>
		         </tr>
		         <tr>
		         
				 <td>光缆状态:</td>
			    		<td><select name="sectState" id="sectState">
								<option value="">全部</option>
								<c:forEach items="${SectSectionState}" var="item"
									varStatus="status">
									<option value="${item.key}"
										<c:if test="${item.key == cableList.sectState}">selected ="selected"</c:if>>${item.value}</option>
								</c:forEach>
						</select></td>
				</tr>	
		         
		         <tr>
			       <td>设施编码：</td>
			       <td><input name="devCode" id="devCode" type="text" value="${cableList.devCode}"/></td>
			       <td>设施名称：</td>
			       <td><input name="devName" id="devName" type="text" value="${cableList.devName}"/></td>
		         </tr>
		         
		         <tr>
			       <td>按成端段状态导出:</td>
			       <td><select name="isTerminat" id="isTerminat">
							<option value="all">全部</option>
							<c:forEach items="${CableTypeMap}" var="item"
								varStatus="status">
								<option value="${item.key}"
									<c:if test="${item.key == cableList.isTerminat}">selected ="selected"</c:if>>${item.value}</option>
							</c:forEach>
						</select><td/>
					<button type="button" id="btnSectState">导出</button>
		         </tr>
		         <tr>
		         	<td>按端子状态导出:</td>
		         	<td>
		         		<select id="exportSelect" name="exportSelect" selected="selected">
			     			<option value="cableExport">导出光交箱光缆段</option>
			     			<option value="cableExportCablin">导出机柜光缆段</option>
			     			<option value="exportPortInfo">导出纤芯占用详细(光交箱)</option>
			     			<option value="exportPortInfoCablin">导出纤芯占用详细(机柜)</option>
			     			<option value="exportPortStat">导出纤芯占用统计</option>
		     			</select>
		         	</td>
		         	<td>
		     			<button type="button" id="btnExport">导出</button>
		     		</td>
		         </tr>
		        
		    		
			       <td colspan="4" align="center">
			       		
		    			
                   		<input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
	                   <button type="button" id="btnMoreQuery">查询</button>&nbsp;&nbsp;
	                   <button type="button" name="btnClear" id="btnClear" >清空</button>
                   </td>
		         </tr>                                                   
		       </table>
             
        </form>
      </div>

	<!-- 导出弹层下载 -->
	<div id="div_export" class="newlayer" style="display:none;">
	     <center> <a id="exportDown" href="" target="_blank" style="font-size:18px;margin-top:120px">数据生成成功，点此下载！</a></center>
   	</div>
</body>
</html>
