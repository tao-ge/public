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
	<script  type="text/javascript" src="${path }/js/layer.js"></script>	
	<script type="text/javascript"> path='${path }'; </script>
	<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${path }/js/sectRoute/sectRouteInfo.js?kooo2k"></script>
<title>信息管理系统界面</title>
</head>
<body>
	
	<div class="rightinfo">
		<h5 style="color:red">设施名称 ：${sectRouteList.devName} </h5>
		<h4 style="color:red">光缆总纤芯数 ：${sectRouteList.allFiberNo} </h4>
		<input type="text" style="width:800px"><a style="color:red">是否核查 </a><c:if test="${sectRoute.sectIdbz == 0}">
		<input type="radio" id="glsh" name="glsh"value="${sectRoute.sectId }">
		</c:if>
		<h4>-----------------------------------------------</h4>
		<c:forEach items="${sectRouteList.srgList}" var="f">
		<br/>
		<h3>分组编码 ：${f.side} &nbsp;&nbsp;&nbsp;&nbsp;盘数：${f.discNum} 
		<c:if test="${sectRoute.sectIdbz == 1}">
		<input type="radio" id="glsh" checked="checked" name="glsh"value="">
		</c:if>
		</h3>
		<input id="bj" name="bj" type="hidden"  />
		<input id="rd" name="rd"  type="hidden"  />
		<input id="glgl" name="glgl"  type="hidden"  />
		<input id="glscet" name="glscet"  type="hidden"  />
		<input id="logtype" name="logtype" value="${sectRoute.routeType }" type="hidden"  />
			<table class="tablelist">
				<thead>
					<c:forEach items="${f.srdList}" var="g">
						<tr>
							<th>盘(${g.discNum})</th>
							<c:forEach items="${g.srfList}" var="h">
								<c:if test="${h.isflag == 0}">
									<th>${h.fiberPort}</th>
								</c:if>
								<c:if test="${h.isflag > 0}">
									<th style="color:green"><%-- <a href="javascript:alertRoute('${h.routeText}','${h.fiberPort}');" class="tablelink">${h.fiberPort}</a> --%>
									<a href="javascript:;" op="alertRoute" text="${h.routeText}" port="${h.port01}" class="tablelink">${h.fiberPort}</a> 
									</th>
								</c:if>
							</c:forEach>
						</tr>
					</c:forEach>
				</thead>
				<tbody>
						
				</tbody>
			</table>
		</c:forEach>
		<br/>
			<table class="tablelist" >
				<thead>
					<tr>
					 
						<th>分组-盘数-端子</th>
						<th>文本路由</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${sectRouteList.routeList}" var="p">
						<tr id="${p.routeId }">     
						     
							<td>
					<c:if test="${p.isentering == 1}">
		        	<font  color="red">${p.sideDisc}</font>
		        	      </c:if>
		        	   <c:if test="${p.isentering == 0}">
		        		<font>${p.sideDisc}</font>
		        	   </c:if>
							</td>
							<td>
					<c:if test="${p.isentering == 1}">
		        	<font  color="red">${p.routeText}</font>
		        	      </c:if>
		        	   <c:if test="${p.isentering == 0}">
		        		<font>${p.routeText}</font>
		        	   </c:if>
							</td>
							<td>
						<c:if test="${p.isentering == 1}">
		        	<input type="checkbox" onclick="return false;"checked="checked" value="">
		        	      </c:if>
		        	   <c:if test="${p.isentering == 0}">
		        		<input type="checkbox" id="${p.routeId }" name="${p.routeId }" onclick='' 
		        		value="${p.routeId }" orgid="${p.orgid }" areac="${p.areacode1 }"> 
		        	   </c:if>							
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div id="div_deviceAlarmCtrl" class="newlayer" style="display:none;">
			<form action="" method="post" id="form_deviceAlarmCtrl" name="form_deviceAlarmCtrl">
			   <table width="100%" border="1" class="table1">
			    
			     <tr>
			       <td style="valign:top">处理内容：</td>
			       <td>
			          <textarea rows="5" style="width:450px;" name="logcontent" id="logcontent" ></textarea>
				   
				      
		           </td>
		         </tr>
			     <tr>
			       <td colspan="2" align="center">
			           <input id="alarmProcessId" name="alarmProcessId" type="hidden" />
			           <input id="rodeid" name="rodeid" type="hidden"  />
			             <input id="orgid1" name="orgid1" type="hidden"  />
			               <input id="areac1" name="areac1" type="hidden"  />
	                   <button type="button" id="btnDevAlaCtrlSave">提交处理</button>
                   </td>
		         </tr>                                                   
		       </table>
        </form>
        </div>
		<div class="clear"></div>
	</div>
</body>
</html>