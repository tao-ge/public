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
	
	</script>
<style type="text/css">
.lastReportDataList{
	text-align: left;
	vertical-align: middle;
	display: inline;
	font-size: 15px;
	margin: 2px;
	width: 250px;
	height: 250px;
}

</style>
	<title>设施管理</title>
</head>
<body>
	
    <div class="rightinfo">
    	<div class="tools">
	   		 <ul class="toolbar">
		        	<img src="${path}/images/circle_green.png" style="width: 5%;height: 5%;vertical-align: middle;" ><a style="vertical-align: middle;">&nbsp;占用</a>
	          		<img src="${path}/images/circle_blue.png" style="width: 5%;height: 5%;vertical-align: middle;" ><a style="vertical-align: middle;">&nbsp;成端</a>
	          		<img src="${path}/images/circle_gray.png" style="width: 5%;height: 5%;vertical-align: middle;" ><a style="vertical-align: middle;">&nbsp;未成端</a>
		 	</ul>
	    </div>
	    <!-- 查询数据列表 -->
	    <div style="width: 520px;">
	    	<span style="font-size: 20px;">设施名称：${chart.devName}</span>
	    	<span style="font-size: 20px;">设施编码：${chart.devCode}</span>
	    	<span style="font-size: 20px;color: green;">未成端纤芯：${chart.notoEnd}</span>
	    </div>
	    <div style="width: 520px;">
			  <table class="tablelist" >
			     <thead>
					<tr>
						<th width="15%">分组编码</th>
						<th width="13%">熔纤盘序号</th>
						<th width="20%">纤芯序号</th>
						<th width="30%">占用情况</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<c:forEach items="${chart.groupList}" var="g" varStatus="ig">
							<tr style="background-color: #FF4040;">
								<td >${g.side}</td>
								<td ></td>
								<td ></td>
								<td ></td>
								<c:forEach items="${g.discs}" var="d" varStatus="id">
									<tr >
										<td ></td>
										<td>${d.discRowno}</td>
										<td>${d.discFiberNum}</td>
										<td>	
											<c:forEach items="${d.ports}" var="p" varStatus="ip">
												<c:if test="${d.ports.size() <=6}">
													<div style="display: inline;">
														<c:if test="${p.fiberNum ==null}">
															<div class="lastReportDataList" style="background-image: url('images/circle_gray.png');background-size: 100% 100%;">
																<c:if test="${p.discColno <10}">
																	0${p.discColno} 
																</c:if>
																<c:if test="${p.discColno >=10}">
																	${p.discColno} 
																</c:if>
															</div>
														</c:if>
														<c:if test="${p.fiberNum !=null}">
															<c:if test="${p.isOccup eq '0' }">
																<div class="lastReportDataList" style="background-image: url('images/circle_blue.png');background-size: 100% 100%;">
																	<c:if test="${p.discColno <10}">
																		0${p.discColno} 
																	</c:if>
																	<c:if test="${p.discColno >=10}">
																		${p.discColno} 
																	</c:if>
																</div>
															</c:if>
															<c:if test="${p.isOccup eq '1' }">
																<div class="lastReportDataList" style="background-image: url('images/circle_green.png');background-size:100% 100%;">
																	<c:if test="${p.discColno <10}">
																		0${p.discColno} 
																	</c:if>
																	<c:if test="${p.discColno >=10}">
																		${p.discColno} 
																	</c:if>
																</div>
															</c:if>
														</c:if>
														<div class="lastReportDataList"></div>
													</div>
												</c:if>
												<c:if test="${d.ports.size()>6}">
												
													<c:if test="${p.discColno <= d.ports.size()/2 }">
														<c:if test="${p.fiberNum ==null}">
															<div class="lastReportDataList" style="background-image: url('images/circle_gray.png');background-size: 100% 100%;">
																<c:if test="${p.discColno <10}">
																	0${p.discColno} 
																</c:if>
																<c:if test="${p.discColno >=10}">
																	${p.discColno} 
																</c:if>
															</div>
														</c:if>
														<c:if test="${p.fiberNum !=null}">
															<div style="display: inline;">
																<c:if test="${p.isOccup eq '0' }">
																	<div class="lastReportDataList" style="background-image: url('images/circle_blue.png');background-size: 100% 100%;">
																		<c:if test="${p.discColno <10}">
																			0${p.discColno} 
																		</c:if>
																		<c:if test="${p.discColno >=10}">
																			${p.discColno} 
																		</c:if>
																	</div>
																</c:if>
																<c:if test="${p.isOccup eq '1' }">
																	<div class="lastReportDataList" style="background-image: url('images/circle_green.png');background-size:100% 100%;">
																		<c:if test="${p.discColno <10}">
																			0${p.discColno} 
																		</c:if>
																		<c:if test="${p.discColno >=10}">
																			${p.discColno} 
																		</c:if>
																	</div>
																</c:if>
																<div class="lastReportDataList"></div>
															</div>
														</c:if>
													</c:if>
													<c:if test="${p.discColno == d.ports.size()/2 }">
														<br>&nbsp;&nbsp;
													</c:if>
													<c:if test="${p.discColno > d.ports.size()/2 }">
														<div style="display: inline;">
															<c:if test="${p.fiberNum ==null}">
																<div class="lastReportDataList" style="background-image: url('images/circle_gray.png');background-size: 100% 100%;">
																	<c:if test="${p.discColno <10}">
																		0${p.discColno} 
																	</c:if>
																	<c:if test="${p.discColno >=10}">
																		${p.discColno} 
																	</c:if>
																</div>
															</c:if>
															<c:if test="${p.fiberNum !=null}">
																<c:if test="${p.isOccup eq '0' }">
																	<div class="lastReportDataList" style="background-image: url('images/circle_blue.png');background-size: 100% 100%;">
																		<c:if test="${p.discColno <10}">
																			0${p.discColno} 
																		</c:if>
																		<c:if test="${p.discColno >=10}">
																			${p.discColno} 
																		</c:if>
																	</div>
																</c:if>
																<c:if test="${p.isOccup eq '1' }">
																	<div class="lastReportDataList" style="background-image: url('images/circle_green.png');background-size:100% 100%;">
																		<c:if test="${p.discColno <10}">
																			0${p.discColno} 
																		</c:if>
																		<c:if test="${p.discColno >=10}">
																			${p.discColno} 
																		</c:if>
																	</div>
																</c:if>
															</c:if>
															<div class="lastReportDataList"></div>
														</div>
													</c:if>
												</c:if>
											</c:forEach>
										</td>
									</tr>
								</c:forEach>
							</tr>
						</c:forEach>
					</tr>
				</tbody>
			  </table>
			
		</div>	
    </div>
</body>
</html>