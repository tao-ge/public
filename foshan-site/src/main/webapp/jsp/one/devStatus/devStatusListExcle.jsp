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
	<script  type="text/javascript" src="${path }/js/jquery.js"></script>	
	<script  type="text/javascript" src="${path }/js/layer.js"></script>
	<script type="text/javascript"> path='${path }'; </script>
	<script type="text/javascript" src='${path }/js/deviceStatus/deviceStatusListExcle.js'> </script>
	<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
	<script  type="text/javascript" src="${path }/js/prompt/ymPrompt.js"></script>
	<link rel="stylesheet"  type="text/css" href="${path}/js/prompt/skin/ymPrompt.css" />
	<title>报警记录</title>
	<script type="text/javascript">
		$(function(){
    		$('.tablelist tbody tr:odd').addClass('odd');
    	})
	</script>
</head>
    <jsp:include page="/jsp/one/devStatus/devStatusListnavigation.jsp"/>  
    <div class="rightinfo">
	    <!-- 查询数据列表 -->
	    <table class="tablelist">
		    	<thead>
			    	<tr>
				        
				        <th>设施编码<!-- <i class="sort"><img src="images/px.gif" /></i> --></th>
				        <th>设施名称</th>
				        <th>报警时间</th>
				        <th>报警类型</th>
				        <th>报警内容</th>
				        <th>是否处理</th>				        
				        <th>处理情况</th>
				        <th>处理人</th>
				        <th>图片</th>
				        <th>操作</th>
			        </tr>
		        </thead>
		        <tbody>
			        <c:forEach items="${pb.list}" var="da">
				        <tr>
				        
				        <td>${da.devCode}</td>
				        <td>${da.fdevName}</td>
				        <td><fmt:formatDate value="${da.alarmTime}" pattern="yyyy-MM-dd HH:mm:ss" />
				        </td>
				        <td>
					        <c:choose>
					         	<c:when test="${da.alarmTypes eq '01'}">阈值告警</c:when>
					         	<c:when test="${da.alarmTypes eq '02'}">水浸告警</c:when>
					         	<c:when test="${da.alarmTypes eq '03'}">震动告警</c:when>
					         	<c:when test="${da.alarmTypes eq '04'}">门锁异常告警</c:when>
					         	<c:when test="${da.alarmTypes eq '05'}">门超时告警</c:when>
					         	<c:when test="${da.alarmTypes eq '06'}">电量告警</c:when>
					         	<c:when test="${da.alarmTypes eq '07'}">锁超时告警</c:when>
					         	<c:when test="${da.alarmTypes eq '8'}">非法撬锁</c:when>
					         	<c:when test="${da.alarmTypes eq '13'}">布防APP开锁</c:when>
					         	<c:when test="${da.alarmTypes eq '14'}">布防平台远程开锁</c:when>
					         	<c:when test="${da.alarmTypes eq '15'}">布防钥匙开锁</c:when>
					         	<c:when test="${da.alarmTypes eq '16'}">布防蓝牙开锁</c:when>
					         	<c:when test="${da.alarmTypes eq '17'}">通讯终端告警</c:when>
					         	<c:when test="${da.alarmTypes eq '18'}">备用钥匙告警</c:when>
					         	<c:when test="${da.alarmTypes == null or da.alarmTypes eq ''}">未知</c:when>
					        </c:choose>
						</td>
				        <td>${da.alarmContent}</td>
				        <td>${da.alarmContent}</td>
				        <td>${da.dealSignName}</td>
				        
				        <td>${da.dealSituation}</td>
				        <td>${da.userName}</td>
				        <td>
				        	<%-- <c:if test="${fn:contains(da.alarmContent,'强行进入,,')==true'}">
				        		<a href="javascript:;" op="pic" did="${da.alarmProcessId}" class="tablelink">查看</a>
				        	</c:if> --%>
				        	<c:if test="${da.isCheck=='1'}">
				        		<a href="javascript:;" op="pic" did="${da.alarmProcessId}" class="tablelink">查看</a>
				        	</c:if>
				        </td>
				        <td>
				        	<a href="javascript:;" op="ctrl" did="${da.alarmProcessId}" dealSignName="${da.dealSignName}"  dealSituation="${da.dealSituation}" class="tablelink">报警处理</a>
				        </td>
				        </tr>
		       		</c:forEach>
		        </tbody>
		    </table>  
     <div id="div_moreSearch" class="newlayer" style="display:none;">
		        <form action="${path}/queryDeviceAlarm.htm" method="post" id="moreSearchForm" name="moreSearchForm">
				 <input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
				 <input id="devId" name="devId" value="${devId}" type="hidden" />
				 <input id="devName" name="devName" value="${devName}" type="hidden" />
	        	</form>
     		</div> 
     <div class="clear"></div> 
     
     <!-- 报警处理 -->
      <div id="div_deviceAlarmCtrl" class="newlayer" style="display:none;">
	       <form action="" method="post" id="form_deviceAlarmCtrl" name="form_deviceAlarmCtrl">
			   <table width="100%" border="1" class="table1">
			    
			     <tr>
			       <td style="valign:top">处理内容：</td>
			       <td>
			          <textarea rows="5" style="width:450px; height:100px;" name="dealSituation" id="dealSituation" ></textarea>
				   
				      
		           </td>
		         </tr>
			     <tr>
			       <td colspan="2" align="center">
			           <input id="alarmProcessId" name="alarmProcessId" type="hidden" />
	                   <button type="button" id="btnDevAlaCtrlSave">提交处理</button>
                   </td>
		         </tr>                                                   
		       </table>
        </form>
      </div>
      
    <jsp:include page="/jsp/one/common/page.jsp" />
    </div>
     
</body>

</html>
