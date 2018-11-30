<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String rc = request.getContextPath();
	request.setAttribute("path", rc);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link href="${path }/css/style.css" rel="stylesheet" type="text/css" />
<link href="${path }/css/facility.css" rel="stylesheet" type="text/css" />
<link href="${path }/css/select.css" rel="stylesheet" type="text/css" />
<link href="${path }/js/skin/layer.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript" src="${path}/js/jquery1.9.js"></script>

<script type="text/javascript" src="${path}/js/layer.js"></script>
<script type="text/javascript" src="${path}/js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="${path}/js/select-ui.min.js"></script>
 <script type="text/javascript">
	path = '${path }';
</script>
 <script type="text/javascript"
	src="${path }/js/facility/fiberDiscList.js?lgaaa"></script>
  <style type="text/css">
  	.box1{
		margin-top: 3%;
		margin-left: 3%;
		width: 95%;
		height: 100%;
		background: #FFF;
		display:none;
 	}
  </style>
  
<script type="text/javascript">
$(document).ready(function(e) {
    $(".select1").uedSelect({
		width : 345			  
	});
	$(".select2").uedSelect({
		width : 167  
	});
	$(".select3").uedSelect({
		width : 100
	});
});
</script>
 
</head>

<body>

	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="#">首页</a></li>
	    <li><a href="#">资源台账管理</a></li>
	    <li><a href="#">设施管理</a></li>
	    <li><a href="#">设施详情</a></li>
	    </ul>
    </div>
	<div class="formbody">
	
	    <div id="usual1" class="usual"> 
    	 <jsp:include page="/jsp/one/facility/facilityDetailNav.jsp" />  	
		 <div class="tools" style="margin:5px;">
	    	<!-- <ul class="toolbar">
		        <li ><a id="hrefAdd" href="javascript:;" dids="${devId}"><span><img src="${path}/images/t01.png" /></span>新建熔纤盘分组</a></li>
		        <li ><a id="hrefAdd" href="javascript:;" dids="${devId}"><span><img src="${path}/images/t01.png" /></span>批量生成容纤盘数据</a></li>
		    </ul>
		     -->
		    <div class="simpleQuery">
	           <form method="post"	action="${path}/sonFacilityList.htm?devId=${devId}" id="SearchForm" name="SearchForm">
	              <!--   <input  id="btnSimpleQuery" value="查询" class="submit" type="button"/>-->
	           </form>
	        </div>
	     </div>
	     
	     <c:forEach items="${fiberdiscGroup}" var="f">
			<div id="tab2" class="tabson tabson_1" style="margin-top: 0.2%;">
				<table class="tablelist">
					<thead>
							<tr style=" background-color:#ADFAFD;">
							<td class="flp_add1">
							${f.side}
							</td>
							<td class="flp_add2">
							<!--<a  id="up_flp"  style="color:blue;text-decoration:underline;" href="#">
							修改
							</a>
							</td>
							<td class="flp_add3">
							<!--<a href="#"   style="color:blue;text-decoration:underline;" onclick="if(confirm('您确定删除这条记录？')){location.href='/member/life/del_ppt/<?php echo $v->id;?>';}" >
							删除
							</a>-->
							</td>
							<td class="flp_add4">
							<!--<a id="up_rxp" style="color:blue;text-decoration:underline;" href="#">
							添加熔纤盘
							</a>-->
							</td>
							<td class="flp_add5">
						 	</td>
							</tr>
					</thead>
				</table>
			
				<table class="tablelist">
			    	<thead>
			    	<tr>
			       	<th  style="width:10%;">熔纤盘名称</th>
			        <th  style="width:20%;">熔纤盘编码</th>
			        <th  style="width:20%;">端子数量</th>
			        <th	 style="width:30%;">业务描述</th>
					<!--<th  style="width:20%;">操作</th>-->
			        </tr>
			        </thead>
			        <tbody>
			        	<c:forEach items="${f.discs}" var="g">
					        <tr>
					        <td>${g.discName}</td>
					        <td>${g.discCode}</td>
					        <td>${g.row}</td>
					        <td>${g.remark}</td>
							<!--<td><a id="query_rxp" href="#" class="tablelink">查看</a>     <a href="#" class="tablelink"> 删除</a></td>
					       --> </tr> 
						</c:forEach>					    	
			        </tbody>
			    </table>
		    </div>
    </c:forEach>
    
    <!--法兰盘分组新建-->
   	 
					<table class="box1" id="box1">
			
    			<tbody>
				 
			   <caption class="caption_tittle">
   						<span class="span_flp">
						法兰盘数据批量生成
						</span>
 			 </caption>
					
					 <tr class="layer_tr_1">
					<td class="layer_td_1" >分组编码：</td>
					<td class="layer_td_2" >
						<input class="layer_input1" id="name" />
					</td>
					 </tr> 
				
					<tr class="layer_tr_1">
						<td class="layer_td_1" >熔纤盘数量：</td>
					<td class="layer_td_2" >
							<input class="layer_input2"  id="name"/>*批量生产数量
					</td>
					 </tr> 
					<tr class="layer_tr_1">
					<td class="layer_td_1" >单盘端子数量：</td>
					<td class="layer_td_2" >
						<input class="layer_input2" id="name"/>*批量生产数量
					</td>
					 </tr> 
					 	<tr class="layer_tr_1">
					<td class="layer_td_1" ></td>
					<td class="layer_td_2" >
					<input type="checkbox"  value="" />保存并继续录入：
							<button id="closeIframe" class="closeIframe_button" >
								保存
						</button>
					</td>
					 </tr> 
				
				
				</tbody>
				</table>
  
  <!--分组编辑-->
  	<table class="box1" id="box2">
			
    			<tbody>
				 
			   <caption class="caption_tittle">
   					<span class="span_flp">
					
					法兰盘数据批量生成
					</span>

 			 </caption>
					
					 <tr class="layer_tr_1">
					<td class="layer_td_1" >分组编码：</td>
					<td class="layer_td_2" >
						<input class="layer_input1" />
					</td>
					 </tr> 
				
					 	<tr class="layer_tr_1">
					<td class="layer_td_1" ></td>
					<td class="layer_td_2" >
					<input type="checkbox"  value=""/>保存并继续录入：
							<button id="closeIframe" class="closeIframe_button" > 
						保存
						 
						</button>
					</td>
					 </tr> 
				</tbody>
				</table>
	<!--熔纤盘编辑-->
  	<table class="box1" id="box3">
			
    			<tbody>
				 
			   <caption class="caption_tittle">
   					<span class="span_flp">
					法兰盘编辑
					</span>

 				 </caption>
					
					 <tr class="layer_tr_1">
					<td class="layer_td_1" >所属分组：</td>
					<td class="layer_td_2" >
					M01
					</td>
					 </tr> 
					  <tr class="layer_tr_1">
					<td class="layer_td_1" >熔纤盘编码：</td>
					<td class="layer_td_2" >
						<input class="layer_input1" />
					</td>
					 </tr>
					  <tr class="layer_tr_1">
					<td class="layer_td_1" >熔纤盘名称：</td>
					<td class="layer_td_2" >
						<input class="layer_input1" />
					</td>
					 </tr>
					  <tr class="layer_tr_1">
					<td class="layer_td_1" >端子数量：</td>
					<td class="layer_td_2" >
						<input class="layer_input1" />
					</td>
					 </tr>
					<tr class="layer_tr_1">
					<td class="layer_td_1" >业务描述：</td>
					<td class="layer_td_2" >
						<input class="layer_input1" />
					</td>
					 </tr>
				
					 	<tr class="layer_tr_1">
					<td class="layer_td_1" ></td>
					<td class="layer_td_2" >
					<input type="checkbox"  value=""/>保存并继续录入：
						<button  class="closeIframe_button" >
						<a id="transmit"> 
						保&nbsp;&nbsp;存
						 </a>
						</button>
					</td>
					 </tr> 
				</tbody>
	</table>
	<!--查询熔纤盘信息-->
	<table class="box1" id="box4">
			
    	<tbody>
					<caption >
					</caption>
				 	<td class="caption_tittle">
   					<span class="span_flp">
					法兰盘信息
					</span>

 				 </td>
					
					 <tr class="layer_tr_1">
					<td class="layer_td_1" >编码：</td>
					<td class="layer_td_5" >
					A
					</td>
					 </tr> 
					  <tr class="layer_tr_1">
					<td class="layer_td_1" >名称：</td>
					<td class="layer_td_5" >
						A
					</td>
					 </tr>
					  <tr class="layer_tr_1">
					<td class="layer_td_1" >端子数量：</td>
					<td class="layer_td_5" >
						12
					</td>
					 </tr>
					  <tr class="layer_tr_1">
					<td class="layer_td_1" >业务描述：</td>
					<td class="layer_td_5" >
						养鸡场
					</td>
					 </tr>
					 
					 	<td class="caption_tittle">
   					<span class="span_flp">
					端子信息
					</span>
		</td>
				   <tr class="layer_tr_1">
					<td class="layer_td_3" ><img style="margin-bottom: 10px; margin-top:10px;width: 80%;" src="http://img.juimg.com/tuku/yulantu/140118/328300-14011R0240083.jpg" />
					</td>
					<td class="layer_td_4" >
						<h1>对端位置：Gj0006-A-1-12</h1>
						<h1>对端位置：Gj0006-A-1-12</h1>
						<h1>对端位置：Gj0006-A-1-12</h1>
						<h1>业务描述：养狗场</h1>
					</td>
					<td class="layer_td_3" >维护</td>
					
					</tr>
					 <tr class="layer_tr_1">
					<td class="layer_td_3" ><img style="margin-bottom: 10px; margin-top:10px;width: 80%;" src="http://img.juimg.com/tuku/yulantu/140118/328300-14011R0240083.jpg" />
					</td>
					<td class="layer_td_4" >
						<h1>对端位置：Gj0006-A-1-12</h1>
						<h1>对端位置：Gj0006-A-1-12</h1>
						<h1>对端位置：Gj0006-A-1-12</h1>
						<h1>业务描述：养狗场</h1>
						
					</td>
					<td class="layer_td_3" >维护</td>
					
					</tr>
					 <tr class="layer_tr_1">
					<td class="layer_td_3" ><img style="margin-bottom: 10px; margin-top:10px;width: 80%;" src="http://img.juimg.com/tuku/yulantu/140118/328300-14011R0240083.jpg" />
					</td>
					<td class="layer_td_4" >
						<h1>对端位置：Gj0006-A-1-12</h1>
						<h1>对端位置：Gj0006-A-1-12</h1>
						<h1>对端位置：Gj0006-A-1-12</h1>
						<h1>业务描述：养狗场</h1>
						
					</td>
					<td class="layer_td_3" >维护</td>
					
					</tr>
					 
		</tbody>
	</table>
    
    
	</div> 
 
	<script type="text/javascript"> 
      $("#usual1 ul").idTabs(); 
    </script>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
    
    
    
    
    
    </div>
</div>
</div>

</body>

</html>
