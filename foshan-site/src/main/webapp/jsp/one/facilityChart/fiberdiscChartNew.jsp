<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	<link href="${path}/css/tinyselect.css" rel="stylesheet" type="text/css" />
	<script  type="text/javascript" src="${path }/js/jquery.js"></script>	
	<script type="text/javascript" src="${path}/js/select-ui.min.js"></script>
	<script type="text/javascript" src="${path}/js/tinyselect.js"></script>	
	<script  type="text/javascript" src="${path }/js/layer.js"></script>
	<script type="text/javascript"> path='${path}'; </script>
	<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
		$(function(){
    		$('.tablelist tbody tr:odd').addClass('odd');
    	}) 
	</script>
	<link href="${path}/css/DisInfoOccup.css" rel="stylesheet" type="text/css" />
	<title>熔纤盘</title>
</head>
<body>
    <!-- 页面导航  -->
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
		    <c:if test="${flag eq 0}">
		    	<li><a href="index.htm">熔纤盘</a></li>
		   		<li><a href="index.htm">熔纤盘详情</a></li>
		    </c:if>
		     <c:if test="${flag eq 1}">
		    	<li><a href="index.htm">只能设备管理</a></li>
		    	<li><a href="index.htm">差异数据统计</a></li>
		   		<li><a href="index.htm">详情</a></li>
		    </c:if> 
	    </ul>
    </div>
    <div class="rightinfo">
	    <!-- 操作按钮：新增、导入、导出、查询  -->
	    <div class="tools">
	   		 <ul class="toolbar">
	   		 			<li ><a id="upload" href="javascript:history.go(-1);"><span></span>返回</a></li>
	   		 		<c:if test="${flag eq 1}">
	   		 		</c:if>
		        	<img src="${path}/images/circle_yellow.png" style="width: 2.5%;height: 2.5%;vertical-align: middle;" ><a style="vertical-align: middle;">&nbsp;清查端子占用</a>
	          		<img src="${path}/images/circle_blue.png" style="width: 2.5%;height: 2.5%;vertical-align: middle;" ><a style="vertical-align: middle;">&nbsp;清查端子空闲</a>
	          		<img src="${path}/images/circle_blue_b_y.png" style="width: 2.5%;height: 2.5%;vertical-align: middle;" ><a style="vertical-align: middle;">&nbsp;中控器上报空闲</a>
	          		<img src="${path}/images/circle_yellow_b_b.png" style="width: 2.5%;height: 2.5%;vertical-align: middle;" ><a style="vertical-align: middle;">&nbsp;中控器上报占用</a>
	          		<img src="${path}/images/circle_red.png" style="width: 2.5%;height: 2.5%;vertical-align: middle;" ><a style="vertical-align: middle;">&nbsp;中控器未上报</a>
		 	</ul>
	        <div class="simpleQuery">
	           <form action="${path}/fiberdiscChart.htm" method="post" id="moreSearchForm" name="moreSearchForm">
	           		设施名称：<select name="devIds" id="devIds">
	                          <c:forEach  items="${facilityList}" var="item" >
	                             <option value="${item.devId}" ${item.devId == devId?"selected":""}>${item.devName}</option>
	                          </c:forEach>
					  </select> 
	           		<input  id="" value="查询" class="submit" onclick="Query()" type="button">
	           </form>
	        </div>  
	    </div>    
	    <!-- 查询数据列表 -->
	    	<div>
			  <table class="tablelist">
			     <thead>
					<tr>
						<th >分组编码</th>
						<th >熔纤盘编码</th>
						<th >是否绑定</th>
						<th >占用情况</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<c:forEach items="${groupList}" var="g" varStatus="ig">
							<tr style="background-color: #FF4040;">
								<td >${g.side}</td>
								<td></td>
								<td></td>
								<td></td>
								<c:forEach items="${g.discs}" var="d" varStatus="id">
									<tr>
										<td></td>
											<td>${d.discCode}</td>
											<td>${d.bind eq '0'?"否":d.bind eq '1'?"是":"未知"}</td>
												<td>	
													<c:forEach items="${d.ports}" var="p" varStatus="ip">
													 <c:if test="${ip.count <= d.ports.size()/2 }">
																<div style="display: inline;">
																	<c:if test="${p.occup eq '0' }">
																		<div class="lastReportDataListblue">
																			<c:if test="${ip.count <10}">
																				0${ip.count} 
																			</c:if>
																			<c:if test="${ip.count >=10}">
																				${ip.count} 
																			</c:if>
																		</div>
																	</c:if>
																	<c:if test="${p.occup eq '1' }">
																		<div class="lastReportDataListyellow" >
																			<c:if test="${ip.count <10}">
																				0${ip.count} 
																			</c:if>
																			<c:if test="${ip.count >=10}">
																				${ip.count} 
																			</c:if>
																		</div>
																	</c:if>
																	<c:if test="${p.occup eq '2' }">
																		<div class="lastReportDataListred" >
																			<c:if test="${ip.count <10}">
																				0${ip.count} 
																			</c:if>
																			<c:if test="${ip.count >=10}">
																				${ip.count} 
																			</c:if>
																		</div>
																	</c:if>
																	<c:if test="${p.occup eq '3' }">
																		<div class="lastReportDataListby" >
																			<c:if test="${ip.count <10}">
																				0${ip.count} 
																			</c:if>
																			<c:if test="${ip.count >=10}">
																				${ip.count} 
																			</c:if>
																		</div>
																	</c:if>
																	<c:if test="${p.occup eq '4' }">
																		<div class="lastReportDataListyb" >
																			<c:if test="${ip.count <10}">
																				0${ip.count} 
																			</c:if>
																			<c:if test="${ip.count >=10}">
																				${ip.count} 
																			</c:if>
																		</div>
																	</c:if>
															<div class="lastReportDataListyb" >
															</div>
																</div>
															</c:if>
															<c:if test="${ip.count == d.ports.size()/2 }">
																<br>&nbsp;&nbsp;
															</c:if>
															<c:if test="${ip.count > d.ports.size()/2 }">
																<div style="display: inline;">
																	<c:if test="${p.occup eq '0' }">
																		<div class="lastReportDataListblue">
																			<c:if test="${ip.count <10}">
																				0${ip.count} 
																			</c:if>
																			<c:if test="${ip.count >=10}">
																				${ip.count} 
																			</c:if>
																		</div>
																	</c:if>
																	<c:if test="${p.occup eq '1' }">
																		<div class="lastReportDataListyellow" >
																			<c:if test="${ip.count <10}">
																				0${ip.count} 
																			</c:if>
																			<c:if test="${ip.count >=10}">
																				${ip.count} 
																			</c:if>
																		</div>
																	</c:if>
																	<c:if test="${p.occup eq '2' }">
																		<div class="lastReportDataListred" >
																			<c:if test="${ip.count <10}">
																				0${ip.count} 
																			</c:if>
																			<c:if test="${ip.count >=10}">
																				${ip.count} 
																			</c:if>
																		</div>
																	</c:if>
																	<c:if test="${p.occup eq '3' }">
																		<div class="lastReportDataListby" >
																			<c:if test="${ip.count <10}">
																				0${ip.count} 
																			</c:if>
																			<c:if test="${ip.count >=10}">
																				${ip.count} 
																			</c:if>
																		</div>
																	</c:if>
																	<c:if test="${p.occup eq '4' }">
																		<div class="lastReportDataListyb" >
																			<c:if test="${ip.count <10}">
																				0${ip.count} 
																			</c:if>
																			<c:if test="${ip.count >=10}">
																				${ip.count} 
																			</c:if>
																		</div>
																	</c:if>
																	<div class="lastReportDataListyb" >
																	</div>
																</div>
															</c:if>
													</c:forEach>
												</td>
										</td>
									</tr>
								</c:forEach>
							</tr>
						</c:forEach>
					</tr>
				</tbody>
			  </table>
			</div>
    
</body>
<script type="text/javascript">
function Query(){
	var devId=$("#devIds").val();
	window.location.href= path+"/fiberdiscChart.htm?devId="+devId;
}
</script>
</html>