<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String rc = request.getContextPath();
	request.setAttribute("path", rc);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link href="${path}/css/style.css" rel="stylesheet" type="text/css" />
	<link href="${path}/css/select.css" rel="stylesheet" type="text/css" />
	<link href="${path}/css/tinyselect.css" rel="stylesheet" type="text/css" />
	<script  type="text/javascript" src="${path }/js/jquery.js"></script>
	<script  type="text/javascript" src="${path }/js/layer.js"></script>
	<script type="text/javascript"> path='${path }';	</script>
	<script type="text/javascript" src="${path}/js/tinyselect.js"></script>
	<script type="text/javascript" src="${path}/js/select-ui.min.js"></script>
	<script  type="text/javascript" src="${path }/js/prompt/ymPrompt.js"></script>
	<script  type="text/javascript" src="${path }/js/task/taskrecordlogList.js?dwdddFe"></script>
	<link rel="stylesheet"  type="text/css" href="${path}/js/prompt/skin/ymPrompt.css" />
	<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
	
<title>信息管理系统界面</title>
</head>
<body>
<script type="text/javascript">
		$(function(){
    		$('.tablelist tbody tr:odd').addClass('odd');
    	})
	</script>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">任务管理</a></li>
    <li><a href="#">任务记录</a></li>
    </ul>
    </div>

    <div class="rightinfo" id="show">
      <div class="tools">
    	<ul class="toolbar">
  
    	<c:if test="${record.roleId == 40}">		     
        <li ><a id="hrefAdd" href="javascript:;"><span><img src="${path}/images/t01.png" /></span>添加</a></li>
        </c:if>
        </ul>
        <div style="float:right;">
            <form id="searchform"  action="${path}/queryTaskRecordlogList.htm" method="post">
           <c:if test="${record.roleId == 40}">
            <label style="color:red">资管版本号：</label> 
           <input name="inforentervers" id="inforentervers"  value="${records.inforenterverys}" type="text"  onfocus=this.blur()  /> 
           </c:if>
            <label>任务类型：</label>
     <select name="tasktypecx" id="tasktypecx"> 
 	    	<option value="">全部</option>
 	    	</select>
								<input id="tasktypeid" value="${record.tasktypecx}" type="hidden"/>
               时间：<input onClick="WdatePicker()"  name="startTime" id="startTime_Search" value="${record.startTime}" type="text" />
	   ————<input onClick="WdatePicker()" name="endTime" id="endTime_Search" value="${record.endTime}" type="text" />
            <input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
	    	<c:if test="${record.roleId == 40}">		        	
	    	<label>被分配人：</label>
 	    	<select name="userallottee" id="userallottee"> 
 	    	<option value="" ></option> 
 	    	</select> </c:if>
 	    	<input id="uid" value="${record.userallottee }" type="hidden"/>
	    	<%-- <input name="userName" value="${record.userName }" type="text"  /> --%>&nbsp;
    	    <input id="btnSimpleQuery" name="" type="button" class="submit" value="查询"/>
    	    </form>
    	  </div> 
      </div>
	    <table class="tablelist">
	    	<thead id="glxs">
	    	<tr>
	        
	        <th  width="80px;">编号</th>
	        <th  width="100px;">类型</th>
	        <th width="100px;">设施</th>
	        <th  width="100px;">分配时间</th>
	        
	        <th  width="100px;">完成时间</th>
	        <th  width="100px;">版本号</th>
	        <th  width="100px;">分配人</th>
	      <th width="150px;">被分配人</th>
	        
	        <th width="80px;">工作记录</th>
	        <th width="80px;"  >备注</th>
	        <th width="80px;"  >是否完成</th>
	        <c:if test="${record.roleId != 40}">
	        <th  width="80px;">操作</th>
	        </c:if>
	        </tr>
	        </thead>
	        <tbody >
	       
	        <c:forEach items="${pb.list}" var="r" varStatus="a">
		       <tr>
	        <td>${r.taskrecordid }</td>
		        <td>
		        ${r.tasktype}
		        </td>
		        <td><a href="javascript:;" op="see" ids="${r.devIds}"
							class="tablelink"> 详细</a></td>		        
		         <td> <fmt:formatDate value="${r.allocatedtime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		         <td> <fmt:formatDate value="${r.finishtime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		        <td>${r.inforenterver }</td>
		        <td>${r.assigner }</td>
		       <td>${r.allottee }</td>
		        <td>${r.questlog }
		        	
		        </td>
		        <td>${r.remark }</td>
		         
		        <c:if test='${r.sign  == 0 || r.sign  == null ||"" }'>
		        		<td>未完成</td>
		        	</c:if>
		        	<c:if test="${r.sign  == 1}">
		        	<td style="color:red;">	完成</td>
		        	</c:if>
		        <c:if test="${record.roleId != 40}">
		        <td>		        
		        <a id="sh" href="javascript:find(${r.taskrecordid },'sh');"  class="tablelink">完成录入</a> <br/>	    		       
		        </td>
		         </c:if>
		        </tr> 
       		</c:forEach>
	        
	    </table>
   
    <div class="clear"></div> 
    <jsp:include page="/jsp/one/common/page.jsp" />
    </div>
    
<div id="div_deviceAlarmCtrl" class="newlayer" style="display:none;">
			<form action="" method="post" id="form_deviceAlarmCtrl" name="form_deviceAlarmCtrl">
			   <table width="100%" border="1" class="table1" id="zg">
			    
			     <tr>
			       <td style="valign:top">资管版本号：</td>
			       <td>
			          
				    <input id="inforenterverupdate" name="inforenterverupdate" type="text" />
				      
		           </td>
		         </tr>
			     <tr>
			       <td colspan="2" align="center">
			           
	                   <button type="button" id="btnDevAlaCtrlSave">提交处理</button>
                   </td>
		         </tr>                                                   
		       </table>
		       </form>
		       <form action="" method="post" id="form_deviceAlarmCtrlsh" name="form_deviceAlarmCtrl">
		       <table  id="sh" width="100%" border="1" class="table1" >
			    
			     <tr>
			       <td style="valign:top">工作记录：</td>
			       <td>
			          
				    <input id="questlog" name="questlog" type="text" />
				      
		           </td>
		         </tr>
		         <tr>
		         <td style="valign:top">完成时间：</td>
		         <td>
		         <input onClick="WdatePicker()" name="finishtime" id="finishtime"  type="text" />
		        </td>
		         </tr>
		         <tr>
		         <!--  <td style="valign:top">是否录入：</td>-->
		         <td>
		         <input  id="sign" name="sign" value="1" type= "hidden"/>
		        </td>
		         </tr>
		         <tr>
		         <td>
		          <input type="hidden" id="taskrecordid" name="taskrecordid" />
		          </td>
		          </tr>
			     <tr>
			       <td colspan="2" align="center">
			           
	                   <button type="button" id="btnDevAlaCtrlSavesh">提交处理</button>
                   </td>
		         </tr>                                                   
		       </table>
        </form>
        </div>
        <!-- 点击"详细",显示设施列表弹窗 -->
		<div id="div_devlist" class="newlayer" style="display: none;">
			<ul id="selectDev">
			</ul>
		</div>
</body>

</html>