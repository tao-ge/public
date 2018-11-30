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
	<script  type="text/javascript" src="${path }/js/jquery.js"></script>	
	<script  type="text/javascript" src="${path }/js/layer.js"></script>
	<script  type="text/javascript" src="${path }/js/echarts.js"></script>
	<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
	<script  type="text/javascript" src="${path }/js/iam/deviceTotal.js"></script>
	<script type="text/javascript"> path='${path }'; </script>
	<title>设备安装统计</title>
 </head> 
 <body>
  <!-- 页面导航  -->
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="index.htm">首页</a></li>
	    <li><a href="index.htm">智能设备管理</a></li>
	    <li><a href="#">设备安装统计</a></li>
	    </ul>
    </div>
	     <div class="tools" >
    		<div class="simpleQuery" style="margin-right: 83%;">
					统计图类型 : <select name="" id="check" selected="selected" onchange="SwitchingFigure()">
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
			 		年份 : <input id="datePick" name="year" type="text" value="${year}" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy',isShowToday:false})" class="Wdate" style=" width:80px;"/>
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
			    	 汇聚区 : <select name="areaCode1" id="areaCode2" class="hjq" runat="server" selected="selected">
			     
			     	</select> 
			 		<input id="btnSimpleQuery" value="查询" class="submit" type="button">
			 	</form>
		 	</div>
 	</div>
    <div id="main" style="width: 600px; height: 400px;margin-left: 18%;"></div>  
 </div>
 	<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->  
 <script type="text/javascript">
 function start(){
 var myChart = echarts.init(document.getElementById('main'));
 var year = document.getElementById('datePick').value;
 var areaCode1 = document.getElementById('areadevCode').value;
 var areaCode2 = document.getElementById('areaCode2').value;
 var option = {
	 color: [ '#32CD32','#e5323e'],
     title : {
         text : '月安装业务点数量'
     },
     tooltip : {},
     legend : {
         data : [ '已安装数量','未安装数量' ]
     },
     xAxis : {
         data : []
     },
     yAxis : {},
     series : [ 
    	 {
	         name : '已安装数量',
	         type : 'bar',
	         data : []
     	},
     	 {
	         name : '未安装数量',
	         type : 'bar',
	         data : []
    	}
     ],
     
     toolbox : {  
         show : true,  
         feature : {  
             mark : {  
                 show : true  
             },  
             dataView : {  
                 show : true,  
                 readOnly : false  
             },  
             magicType : {  
                 show : true,  
                 type : [ 'line', 'bar' ]  
             },  
             restore : {  
                 show : true  
             },  
             saveAsImage : {  
                 show : true  
             }  
         }  
     } 
 }
	 var index = layer.msg('数据加载中...', {icon: 16});
	 $.ajax({  
	     url:path+"/getAllData.htm?areaCode1="+areaCode1+"&areaCode2="+areaCode2+"&year="+year,
	     type : "post",       
	     async : true,      //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）  
	     dataType : "json",   
	     success : function(result) {  
		     if (result != null) {
		     	var months = result.dt.monthsJson;
		     	var deviceMonths = result.dt.deviceMonthsJson;
		     	var deviceMonthsNo = result.dt.deviceMonthsNoJson;
		        for(var i=0;i<months.length;i++){   
		               option.series[0].data.push(deviceMonths[i]); 
		               option.series[1].data.push(deviceMonthsNo[i]); 
		               option.xAxis.data.push(months[i]);  
		        }        
		        layer.close(index);
		        myChart.setOption(option);  
		     }else {
		    	 layer.close(index);
		         //返回的数据为空时显示提示信息  
		         alert("图表请求数据为空,请稍后再试！");  
		     }                 
	    }
	})
 }
</script>
 </body>
 
</html>
