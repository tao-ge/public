<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<script  type="text/javascript" src="${path }/js/echarts.js"></script>
	<script  type="text/javascript" src="${path }/js/jquery.js"></script>	
	<script  type="text/javascript" src="${path }/js/echarts.js"></script>
	<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
	<script  type="text/javascript" src="${path }/js/iam/disinfoCount.js"></script>
	<script type="text/javascript"> path='${path }'; </script>
	<title>熔纤盘安装数量</title>
 </head> 
 <body>
 <!-- 页面导航  -->
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="index.htm">首页</a></li>
	    <li><a href="index.htm">智能设备管理</a></li>
	    <li><a href="#">熔纤盘安装数量</a></li>
	    </ul>
    </div>
 <div>
  	<div class="tools">
	    <div class="simpleQuery" style="margin-right: 83%;">
		 统计图类型 : <select name="" id="check" onchange="SwitchingFigure()">
		     <c:forEach items="${checkList }" var="v">
		     	<option value="${v.value }" ${v.value eq check?"selected":""}>${v.name }</option>
		     </c:forEach>
		  </select> 
	   </div>
	 </div>
	  <div style="width: 100%;height: 2px;background-color: blue;"></div> 
	  <div class="rightinfo">
		<div class="tools">
			<div class="simpleQuery" style="margin-right: 35%;">
		 	<form id="searchlistform" name="searchlistform">
		 		所属区 : <select class="areadevCode" id="areadevCode" name="areadevCode" runat="server" selected="selected">
				     			<option value="">全部</option>
				     			<c:forEach items="${areaList}" var="area" >
				     			<option value="${area.name}" 
				     				<c:if test="${area.name==areaCode1}">selected="selected"</c:if>	
				     			>
				     				${area.value}
				     			</option>
				     			</c:forEach>
				     		</select>
				 <input id="areaCode" type="hidden" value="${areaCode2}">
		     	汇聚区 : <select name="areaCode1" id="areaCode2" class="hjq" runat="server" selected="selected">
		     
		     	</select> 
		 		<input id="btnSimpleQuery" value="查询" class="submit" type="button">
		 	</form>
 		</div>
 	</div>
 	<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->  
    <div id="main" style="width: 600px; height: 400px;margin-left: 18%;" ></div>  
   </div>
 <script type="text/javascript">
 function start(){
 var myChart = echarts.init(document.getElementById('main'));
 var option = {
		    title : {
		        text: '熔纤盘安装总数',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        left: 'left',
		        data: [${data1}]
		    },
		    series : [
		        {
		            name: '访问来源',
		            type: 'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:[
		               ${data2}
		            ],
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ],
		    color: ['rgb(50,205,50)','rgb(255,0,0)']
		};
 	myChart.setOption(option);  
 }
 start();
</script>
 </body>
 
</html>
