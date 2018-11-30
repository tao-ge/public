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
	<script  type="text/javascript" src="${path }/js/task/taskList.js?dwdFe"></script>
	<link rel="stylesheet"  type="text/css" href="${path}/js/prompt/skin/ymPrompt.css" />
	<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
	
<title>信息管理系统界面</title>
</head>
<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="index.htm">首页</a></li>
    <li><a href="index.htm">任务管理</a></li>
    <li><a href="queryTaskRecordList.htm">任务记录</a></li>
    </ul>
    </div>

    <div class="rightinfo" id="show">
      <div class="tools">
    	<ul class="toolbar">
        <li ><a id="hrefAdd" href="javascript:;"><span><img src="${path}/images/t01.png" /></span>添加</a></li>
        </ul>
        <div style="float:right;">
            <form id="searchform"  action="${path}/queryTaskRecordList.htm" method="post">
            <label>任务类型：</label>
 	    	<select id="tasktypecx" name="tasktypecx" >				
								</select>
								<input id="tasktypeid" value="${record.tasktypecx}" type="hidden"/>
               时间：<input onClick="WdatePicker()"  name="startTime" id="startTime_Search" value="${record.startTime}" type="text" />
	   ————<input onClick="WdatePicker()" name="endTime" id="endTime_Search" value="${record.endTime}" type="text" />
            <input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
	    	<label>用户名：</label>
 	    	<select name="userId" id="userName"> 
 	    	<option value="" ></option> 
 	    	</select> 
 	    	<input id="uid" value="${record.userId }" type="hidden"/>
	    	<%-- <input name="userName" value="${record.userName }" type="text"  /> --%>&nbsp;
    	    <input id="btnSimpleQuery" name="" type="button" class="submit" value="查询"/>
    	    </form>
    	  </div> 
      </div>
	    <table class="tablelist">
	    	<thead id="glxs">
	    	<tr>
	        
	        <th  width="80px;">用户名</th>
	        <th  width="100px;">设备类型</th>
	        <th width="100px;">光路名称</th>
	        <th  width="100px;">传输段</th>
	        
	        <th  width="100px;">传输系统</th>
	        <th  width="100px;">设备端口</th>
	        <th  width="100px;">端子信息</th>
	      <th width="150px;">资源文本路由</th>
	        
	        <th width="80px;">文本路由</th>
	        <th width="80px;"  >备注</th>
	        <th  width="80px;">时间</th>
	        <th  width="80px;">操作</th>
	        </tr>
	        </thead>
	        <tbody id="glsj">
	        <c:forEach items="${pb.list}" var="r" varStatus="a">
		        <tr>
	        <td>${r.userName }</td>
		        <td>${r.devType }</td>
		        <td>${r.routeName }</td>
		        <td>${r.lebt }</td>
		        <td>${r.transmissionsystem }</td>
		        <td>${r.facilityterminal }</td>
		        <td>${r.terminalinformation }</td>
		       <td>${r.resourcetextrouting }</td>
		        <td>${r.textRouting }
		        	<c:if test="${r.lightPathType == 1}">
		        		(单芯)
		        	</c:if>
		        	<c:if test="${r.lightPathType == 2}">
		        		(双芯)
		        	</c:if>
		        </td>
		        <td>${r.remark }</td>
		        <td> <fmt:formatDate value="${r.createTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		        <td>
		        <a id="find" href="javascript:find(${r.taskId },'find');" class="tablelink">查看详情</a><br/>  
		        <c:if test="${r.userId == user.userId }">
		        <a id="update" href="javascript:find(${r.taskId },'delete');" class="tablelink">修改</a> <br/>
		        <a href="javascript:;" op="del" did="${r.taskId}" dt="${r.userName }${r.routeName }"  class="tablelink"> 删除</a>
		        </c:if>
		        </td>
		        </tr> 
       		</c:forEach>
	        </tbody>
	        
	        <thead id="glanxs">
	    	<tr>
	        
	        <th  width="60px;">用户名</th>
	        <th width="60px;">光缆名称</th>
	        <th  width="60px;">纤芯数</th>
	        <th  width="60px;">修改内容</th>
	        <th  width="60px;">备注</th>
	        <th  width="135px;">时间</th>
	        <th  width="65px;">操作</th>
	        </tr>
	        </thead>
	        <tbody id="glansj">
	        <c:forEach items="${pb.list}" var="r" varStatus="a">
		        <tr>
		     
		        <td>${r.userName }</td>
		        <td>${r.fiberopticcablename }</td>
		        <td>${r.finesome }</td>
		       <td>${r.contentmodification }</td>
		        
		        <td>${r.remarkglan }</td>
		        <td> <fmt:formatDate value="${r.createTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		        <td>
		        <a id="find" href="javascript:findglan(${r.cableId },'find');" class="tablelink">查看详情</a><br/>  
		        <c:if test="${r.userId == user.userId }">
		        <a id="update" href="javascript:findglan(${r.cableId },'delete');" class="tablelink">修改</a> <br/>
		        <a href="javascript:;" op="delglan" did="${r.cableId}" dt="${r.userName }${r.fiberopticcablename }"  class="tablelink"> 删除</a>
		        </c:if>
		        </td>
		        </tr> 
       		</c:forEach>
	        </tbody>
	    </table>
   
    <div class="clear"></div> 
    <jsp:include page="/jsp/one/common/page.jsp" />
    </div>
    
     <div class="formbody" id="addOrUpdate">
		<form name="form" id="form" method="post" action="${path}/addTaskRecord.htm" enctype="multipart/form-data">
			<div class="formtitle">
				<span>任务基本信息</span>
			</div>
			
				<table class="tableForm">
				<tr>
				<td class="odd">任务类型：</td>
						<td >  <select id="tasktype" name="tasktype" >
								</select>	</td>
								
					</tr>	
				<tbody>
				<tr>
					<td>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp所属区:</td>
					<td><select class="areadevCode" id="areadevCode" name="areadevCode" runat="server" autocomplete="off" >

		     			   <option value="" id="s2">全部</option>
		     			<c:forEach items="${areaList}" var="area" >
		    			<option value="${area.name}" 
		     				<c:if test="${area.name==areadevCode1}">selected="selected"</c:if>	
		     			>
		     				${area.value}
		     			</option>	     			
		     			</c:forEach> 
		     		</select></td>
			     	<td>汇聚区:</td>
			     	<td><select name="areaCode1" id="areaCode" class="hjq" runat="server" selected="selected">
			     	</select></td>
			     			     	
				</tr>
				</tbody>
				<tbody id="glu">
				<tr>
					<td class="odd">设备类型：</td>
					<td><select id="devType" name="devType" >
						<option value="">==请选择==</option>
						<option value="PTN">PTN</option>
						<option value="PON">PON</option>
						<option value="PDH">PDH</option>
						<option value="SDH">SDH</option>
						<option value="其他">其他</option>
					</select></td>
					<td class="odd">光路类型：</td>
						<td >  <select id="lightPathType" name="lightPathType" >
						<option value="">==请选择==</option>
						<option value="1">单芯</option>
						<option value="2">双芯</option>
								</select>	</td>
								
					</tr>	
					<tr>
						<td class="odd">设备端口：</td>
						<td><input name="facilityterminal" type="text" id="facilityterminal" value="" class="input" /></td>
						<td class="odd">端子信息：</td>
						<td><input name="terminalinformation" type="text" id="terminalinformation" value="" class="input" /></td>
					</tr>
					<tr>
						<td class="odd">光路名称：</td>
						<td><input name="routeName" type="text" id="routeName" value="" class="input" /></td>
						
						<td class="odd">光路方案：</td>
						<td><input name="routeScheme" type="text" id="routeScheme" value="" class="input" /></td>
					</tr>
					<tr>						
						<td class="odd">传输系统：</td>
						<td><input name="transmissionsystem" type="text" id="transmissionsystem" value="" class="input" /></td>
					</tr>
					
					<tr id="csd">
						<td class="odd">传输段：</td>
						<td><input name="lebt" type="text" id="lebt" value="" class="input" /></td>
					</tr>
					
					<tr>
					<td class="odd">文本路由：</td>
						<td colspan="4">
						<textarea rows="2" cols="126" name="textRouting" id="textRouting" class="textarea"
						style="border:solid 1px #1c91e3; outline:none;resize:none;
						text-indent:10px;"  ></textarea>
						</td>
						</tr>
						
						<tr>
						<td class="odd">资源文本路由：</td>
						<td colspan="3">
						<textarea rows="2" cols="126" name="resourcetextrouting" id="resourcetextrouting" class="textarea"
						style="border:solid 1px #1c91e3; outline:none;resize:none;
						text-indent:10px;"  ></textarea>
						</td>
					</tr>
							
					<tr>
						<td class="odd">备注：</td>
						<td colspan="4"><input name="remark" type="text" id="remark" style="width:780px" value="" class="input" /></td>
					</tr>
					
					<tr>
						<td class="odd">光路图片：</td>
						<td><input name="fileImg" type="file" id="fileImg" onchange="getPhoto(this)" value="" class="input" /></td>
					</tr>
					<tr>	<td></td>
					<td>	<div style="width:300px;height:150px;" id="showTu">
					</div>
						<input type="hidden" id="imgUrl" name="imgUrl" class="input"/></td>
					</tr>
					
					<tr>
						<td style="text-align: center;" colspan="4">
						    <input type="hidden" id="taskId" name="taskId" value=""/>
							<input id="qrbc" name="" type="button" class="btn" value="确认保存" onclick="checkValueglu();"/>
							<input type="button" id="fh" value="返回" class="btn"/>
						</td>
					</tr>
				</tbody>
				<tbody id="glan">
				<tr>
						<td class="odd">光缆名称：</td>
						<td ><input name="fiberopticcablename" type="text" id="fiberopticcablename"  value="" class="input" /></td>					
						<td class="odd">纤芯数：</td>
						<td ><input name="finesome" type="text" id="finesome"  value="" class="input" /></td>
					</tr>
					<tr>
						<td class="odd">修改内容：</td>
						<td colspan="4"><input name="contentmodification" type="text" id="contentmodification" style="width:780px" value="" class="input" /></td>
					</tr>
					<tr>
						<td class="odd">备注：</td>
						<td colspan="4"><input name="remarkglan" type="text" id="remarkglan" style="width:780px" value="" class="input" /></td>
					</tr>
					<tr>
						<td style="text-align: center;" colspan="4">
						    <input type="hidden" id="cableId" name="cableId" value=""/>
						    
							<input id="qrbcglan" name="" type="button" class="btn" value="确认保存" onclick="checkValueglan();"/>
							
							<input type="button" id="fhglan" value="返回" class="btn"/>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

</body>

</html>