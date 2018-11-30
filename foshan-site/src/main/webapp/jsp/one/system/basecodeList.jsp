<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String rc = request.getContextPath();
	request.setAttribute("path", rc);
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
	<script type="text/javascript" src="${path}/js/select-ui.min.js"></script>
	<script  type="text/javascript" src="${path }/js/prompt/ymPrompt.js"></script>
	<script  type="text/javascript" src="${path }/js/system/basecodeList.js"></script>
	<link rel="stylesheet"  type="text/css" href="${path}/js/prompt/skin/ymPrompt.css" />
	<title>信息管理系统界面</title>
	<script type="text/javascript">
		$(function(){
			$('.tablelist tbody tr:odd').addClass('odd');
		})
    	var path="${path}";
    	$(document).ready(function(e) {

    		$(".select3").uedSelect({
    			width : 152
    		});
    	});
    </script>
    <script type="text/javascript">
    
	</script>
</head>
<body>
    <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="index.htm">首页</a></li>
    <li><a href="index.htm">系统管理</a></li>
    <li><a href="basecodeList.htm">数据字典管理</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
      <div class="tools">
    	<ul class="toolbar">
        <li ><a href="${path}/basecodeAdd.htm" target="rightFrame"><span><img src="${path}/images/t01.png" /></span>添加</a></li>
        <%-- <li><a href="${path}/basecodeDelete.htm" target="rightFrame"><span><img src="${path}/images/t03.png" /></span>删除</a></li> --%>
        </ul>
        <div class="simpleQuery">
        	<form name="moreSearchForm" action="${path}/basecodeList.htm" id="moreSearchForm"  method="post">
        		<input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
        		<input id="code" name="name" type="hidden" value="${code }">
<%--         		<input id="classCode" name="classCode" value="${basecode.classCode }" type="hidden"> --%>
	        	分类：<select name="classCode" id="classCode" selected="selected">
			    <option value='' >全部</option>
			    </select>
			    
		    	代码名称：
		    	<input id="valueName" name="valueName" type="text" value="${basecode.valueName }" class="scinput1" />
				<input id="btnSimpleQuery" name="" value="查询" class="submit" type="submit">
				<!-- <button id="btnDisplayMoreQuery" type="button">高级查询</button> -->
	    	    <!-- <input name="search" type="submit" class="scbtn search" value="搜索"/> -->
        	</form>
        </div>
    </div>
    
     <ul class="classlist">
    
	    <table class="tablelist">
	    	<thead>
	    	<tr>
	        <th>分类</th>
	        <th>代码名称</th>
	        <th>代码值</th>
	        <!-- <th>是否有效</th> -->
	        <th>操作</th>
	        </tr>
	        </thead>
	        <tbody>
	        <c:forEach items="${pb.list}" var="u">
	        
		        <tr>
		        <td>${u.className}</td>
		        <td>${u.valueName}</td>
		        <td>${u.valueCode}</td>
		        
		        <%-- <c:if test="${u.managerSign eq '1'}"> <td>有效</td> </c:if> 
		        <c:if test="${u.managerSign eq '0'}"> <td>无效</td> </c:if> --%>
		        
		        <td><a href="${path}/basecodeUp.htm?codeId=${u.codeId}" class="tablelink">修改</a> 
		         
		            <%-- <a href="${path}/basecodeDelete.htm?codeId=${u.codeId}" onclick='return confirm( "确定要删除吗? ")' class="tablelink"> 删除</a> --%>
		 	    <a href="javascript:;" op="del" did="${u.codeId}" dt="${u.className}-${u.valueName}"  class="tablelink"> 删除</a>
		           
		            </td>
		        </tr> 
		        
       		</c:forEach>
	        </tbody>
	    </table>
    </ul>
    
    <div class="clear"></div>
    <jsp:include page="/jsp/one/common/page.jsp" />
    </div>
    
    <script type="text/javascript">
    	var code=$("#code").val();
    	$(function(){
    		var classCode = $('#classCode');
    		if(!$.data(classCode[0],'opt'))
    			{
		    		 $.ajax({url:path+"/ajax/classcode.htm",dataType:"json",
		    			success:function(data)
		    			{
		    				$.data(classCode[0],"opt",data);
		    				$.each(data,function(index,value){
		    					//回显
		    					if(code==value.name){
		    						classCode.append($("<option>").prop("value",data[index].name).prop("selected",true)
		    								.append(data[index].value));
		    						
		    						classCode.append($("<option>").prop("value",data[index].name).append(data[index].value));
		    						//删除重复数据
		        					var obj=document.getElementById("classCode");
		         					//index,要删除选项的序号，这里取当前选中选项的序号
		         					var index=obj.selectedIndex;
		         					obj.options.remove(index+1); 
		    					}else{
		    						classCode.append($("<option>").prop("value",data[index].name).append(data[index].value));
		    					}
		    				});
		    			}
		    		 });
    			}
    		else
    			{
    			$.each($.data(classCode[0],'opt'),function(index,value){
    				classCode.append($("<option>").prop("value",index).append(value));
				});
    			}
    	})
    	
    	
    
    </script>
    <script type="text/javascript">
    
//     $("#btnSimpleQuery").on('click',simpleQuery)
	function submitQuery(){
		
		$("#moreSearchForm")[0].submit();
	}
	
	function simpleQuery(){
		
		
		clearMoreSearchForm();
		var code=document.getElementById("classCode").value;
		$("#classCode1").val(code);
		$("#valueName").val($("#valueName").val())
		$("#pageNo").val('1');
		submitQuery();
	}
	
	//清空更多查询条件
	function clearMoreSearchForm()
	{
		$(':input','#moreSearchForm')    		
		.val('') 
		.removeAttr('checked') 
		.removeAttr('selected');
        //.not(':button, :submit, :reset, :hidden') 
	}
	
	//分页浏览查询
	function pageQuery(){
		
		$("#pageNo").val($(this).attr("pages"));
		
		submitQuery();
	}	
    
    
		$(function(){
			$("a[op='del']").on("click",deleteData);
		})
		
		  /*
	     * 删除数据
	     */
	function deleteData(){
	    	
	    	var dt=$(this).attr("dt");	
	    	var did=$(this).attr("did");
	    	
	    	//询问框
	    	layer.confirm('确认要删除"'+dt+'"？', { btn: ['确定','取消']  },  
	    		function(){	
	    			
	    			window.location.href = path+'/basecodeDelete.htm?codeId='+did;			   
	    			
	    	} , null);
	    }
	</script>
	<!-- 其它页面元素 如：弹出层等-->
	 <!-- 高级查询 -->
	<div id="div_moreSearch" class="newlayer" style="display:none;">
	       <form  method="post" id="SearchForm" name="SearchForm" >
			   <table width="100%" border="1" class="table1" >
				 <tr>
			       <td>井名称：</td>
			       <td><input id="wallName1" name="wallName" type="text"  value="${well.wellName }" class="scinput1"/></td>			     
		         </tr>
			     <tr>
			       <td>喷码：</td>
			       <td><input id="wellNumber1" name="wellNumber" type="text"  value="${well.wellNumber}" class="scinput1"/></td>
			     </tr>			
			  	 <tr>
			       <td>是否局前井：</td>
			       <td>
				       <select name="IsFormerbureau" id="IsFormerbureau" >
				          <option value="">是否局前井</option>
	                      <c:forEach  items="${WellIsformerbureau}" var="item" varStatus="status">
	                            <option value="${item.key}" <c:if test="${item.key == well.isFormerbureau}">selected ="selected"</c:if>>${item.value}</option>
	                      </c:forEach>
			           </select>
		           </td>
		         </tr>
		          <tr>
			       <td>井种类：</td>
			       <td>
				       <select name="wellKind" id="wellKind" >
				          <option value="">井种类</option>
	                      <c:forEach  items="${WellKind}" var="item" varStatus="status">
	                            <option value="${item.key}" <c:if test="${item.key == well.wellKind}">selected ="selected"</c:if>>${item.value}</option>
	                      </c:forEach>
			           </select>
		           </td>
		         </tr>
		         <tr>
			       <td>现场核查状态：</td>
			       <td>
				       <select name="wellState" id="wellState" >
				          <option value="">现场核查状态</option>
	                      <c:forEach  items="${WellState}" var="item" varStatus="status">
	                            <option value="${item.key}" <c:if test="${item.key == well.wellState}">selected ="selected"</c:if>>${item.value}</option>
	                      </c:forEach>
			           </select>
		           </td>
		         </tr>
		          <tr>
			       <td>井类型：</td>
			       <td>
				       <select name="wellType" id="wellType" >
				          <option value="">井类型</option>
	                      <c:forEach  items="${WellType}" var="item" varStatus="status">
	                            <option value="${item.key}" <c:if test="${item.key == well.wellType}">selected ="selected"</c:if>>${item.value}</option>
	                      </c:forEach>
			           </select>
		           </td>
		         </tr>
			       <tr>
			       <td>开始时间：</td>
			       <td><input id="times1" name="times1" type="text"   onClick="WdatePicker().toDate()"  value="${well.times1}" /></td>
			     </tr> 
			      <tr>
			       <td>结束时间：</td>
			       <td><input id="times2" name="times2" type="text"   onClick="WdatePicker().toDate()"  value="${well.times2}" /></td>
			     </tr> 
			    
			     <tr>
			       <td colspan="1" align="center">
				       <input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
	                   <button type="button" id="btnMoreQuery">查询</button>&nbsp;&nbsp;
	                  <button type="button" name="btnClear" id="btnClear" >清空</button>
                   </td>
		         </tr>           
		         
		         <!-- <tr>
			       <td>   
		         <input id="btnQuery" name="btnQuery" value="查询" class="submit" type="submit"/>  
		         </td>
		         </tr> -->                                         
		       </table>
        </form>
      </div>
</body>
</html>