<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
String rc = request.getContextPath(); 
request.setAttribute("path",rc);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<style type="text/css">
		.tablelist td{
			font-size:   15px; 
			color:   #000000; 
			border:solid 1px;
		}
		#lieMing{
			width:13%;
			font-weight:bold 
		}
		.tablelink{
			height: 21px;
            line-height: 21px;
            padding: 0 11px;
            background: #6495ED;
            border: 2px  solid;
            border-radius: 4px;
            color: #fff;
            display: inline-block;
            text-decoration: none;
            font-size: 12px;
            outline: none;
		}
		#cableSpan{
			text-align: center;
			display:block;
			font-size:20px;
			font-weight:bold;
		}
		#zhiSpan{
			text-align: center;
			display:block;
			font-size:20px;
			font-weight:bold;
		}
	</style>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script  type="text/javascript" src="${path }/js/jquery.js"></script>	
	<script  type="text/javascript" src="${path }/js/layer.js"></script>
	<script type="text/javascript"> path='${path }'; </script>
    <script src="${path }/js/echarts.js"></script>
    
	<title>熔纤盘示意图</title>
</head>
<c:if test="${flag=='0'}">
	<center>没有数据！</center>
</c:if>

<!-- 	position:fixed; :relative  absolute-->

<div id="tableDev"  style=" width:655px; height:400px; position:relative; right:-675px; ">
</div>
<div id="tableCable11"  style=" width:655px; height:200px;  position:relative; right:-675px; top:120px;">
	<table class="tablelist" id="tableCable">
		<span id="cableSpan" style=" display:none;">光缆段成端详情【分组编码：<span id="cableSide" style="color:red;"></span>】【分组名称：<span id="cableName" style="color:red;"></span>】</span>
		<thead id="cableThead" style="Display:none">
			<tr>
				<th width="12%">编码</th>
				<th width="58%">名称</th>
				<th width="15%">已成端</th>
				<th width="15%">未成端</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</div>
<div>
<div id="tableZhiCable1"  style=" width:655px; height:200px;  position:relative; right:-675px; top:500px;">
	<table class="tablelist" id="tableZhiCable">
		<span id="zhiSpan" style=" display:none;">光缆段直熔详情【分组编码：<span id="zhiSide" style="color:red;"></span>】【分组名称：<span id="zhiName" style="color:red;"></span>】</span>
		<thead id="zhiThead" style="Display:none">
			<tr>
				<th width="10%">A端编码</th>
				<th width="30%">A端名称</th>
				<th width="11%">直熔详情</th>
				<th width="10%">Z端编码</th>
				<th width="28%">Z端名称</th>
				<th width="12%">直熔详情</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>

</div>


<c:forEach items="${fgs}" var="f">
<center>
	<div>
		<div id="main${f.groupName}" style="width: 600px; height:${f.groupHeight}px;  border:solid 1px; left:-300px;top:-800px;">
		</div>
		<div style=" width:100px; position:relative; top:-790px; right:450px;">
			<a href="javascript:;" op="select"  dui="${f.groupId}" dside="${f.groupName }" dname="${f.groupNameG }"   class="tablelink">已成端光缆</a>
		</div>
		<div style=" width:100px; position:relative; top:-815px; right:250px;">
			<a href="javascript:;" op="zhiRong"  dui="${f.groupId}" dside="${f.groupName }" dname="${f.groupNameG }"  class="tablelink">直熔光缆</a>
		</div>
	</div>
</center>
<script type="text/javascript">
		
        // 基于准备好的dom，初始化echarts实例
        var myChart${f.groupName} = echarts.init(document.getElementById('main${f.groupName}'));

        // 指定图表的配置项和数据
        var option${f.groupName} = {
        	title: {
                       text: '${f.groupName.contains("ZRIN")?"直熔盘":"熔纤盘" || f.groupName.contains("ZROUT")?"直熔盘":"熔纤盘"}/${f.groupName}',
       				x:'left',
       				y:'top'
                   },
			backgroundColor:'#fff',
            tooltip: {},
            legend: {
			  x: 'right',
                data:[
                      <c:forEach items="${f.statusMap}" var="smp">
                      '${smp.value}',
                      </c:forEach>
                      ]
            
            },
			textStyle:{
			
				color:"#000"
			
			}
			,
           
           
            series: [{
                name:'端子',
                type:'graph',
                width:550, 
                left:30,top:70,
				nodeScaleRatio:0.6,
				layout:'none',
				symbol:"circle",
				symbolSize:35,
				label:
				{
					normal:{show:true}
				},				
				categories:[
				            
				            <c:forEach items="${f.statusMap}" var="smp">
	
		                  	   {
		     					 name:"${smp.value}",symbol:'rect'
		     					},
		                      </c:forEach>
				
				
				],
				focusNodeAdjacency:true,
				
				 itemStyle: {
						normal: {
							label: {
								show: true,
								textStyle: {
									color: '#333'
								}
							},
							nodeStyle : {
								brushType : 'both',
								borderColor : 'rgba(255,25,0,0.4)',
								borderWidth : 1
							},
							linkStyle: {
								type: 'curve'
							}
						},
						emphasis: {
							label: {
								show: true,
								textStyle: {
									color: '#333'
								}
							},
							nodeStyle : {
								//r: 30
							},
							linkStyle : {}
						}
					},
				
                lineStyle:
					{
						normal:
						{
							width:2,color:'#000',curveness:0.1
						}
					
					},
					
			
                data:[
                      
			            <c:forEach items="${f.portDatas}" var="pds">
			            {
			            	<c:choose>
								<c:when test="${not empty pds.category}">
									category:'${pds.category}',
								</c:when>
								<c:otherwise>
									itemStyle:{normal:{color: 'rgba(46,54,62,1)'},emphasis:{color: 'rgba(46,54,62,1)'}},label:{normal:{textStyle:{color:'#fff',fontSize:14}},emphasis:{textStyle:{color:'#fff',fontSize:14}}},
							    </c:otherwise>
							</c:choose>
						    
			                name: '${pds.name}',x:${pds.x},y:${pds.y},value: '${pds.v}'},            	
	                    </c:forEach>     
                
					],
					links:[
		                    <c:forEach items="${f.linkDatas}" var="links">
							{source : '${links.source}', target : '${links.target}'},
							</c:forEach>
							]
				
            }]
			,
			//			占用						未使用					空闲
			color:['rgba(237,221,112,1)','rgba(86,185,235,1)', 'rgba(157,202,100,1)' ]
        };
		
        // 使用刚指定的配置项和数据显示图表。
        myChart${f.groupName}.setOption(option${f.groupName});
		myChart${f.groupName}.on('click', function (params) {
			var code=params.value;
			var port01="<span style='color:red'>"+code+"</span>";
			$.ajax({
				type : "POST",
				url : path + "/queryJumperTerrace.htm?code="+code,
				success : function(r) {
					var table = $("#tableDev");
					$("#tableDev").html("");
					var result = eval(r);
					if (result.r = 1) {
						var img="";
						if(result.dt.lineImgUrl !=null){
							for(var i = 0;i<result.dt.lineImgUrl.length;i++){
								img+="<img src='"+path+result.dt.lineImgUrl[i].imgUrl+"'  height='150' width='150'/>"
							}
						}else{
							img="";
						}
						table.append($(
								"<span style='text-align: center;display:block;font-size:20px;font-weight:bold;color:#00000'>端口详情</span>"
									+"<table class='tablelist'  width='100%' height='80%' align='center'>"
										+"<tbody>"
											+"<tr><td id='lieMing'>端口编码</td><td>"+port01+"</td></tr>"
											+"<tr><td id='lieMing'>端口状态</td><td>"+result.dt.isOccup+"</td></tr>"
											+"<tr><td id='lieMing'>成端信息</td><td>"+result.dt.secName+"</td></tr>"
											+"<tr><td id='lieMing'>跳纤信息</td><td>"+result.dt.devName+"</td></tr>"
											+"<tr><td id='lieMing'>光路名称</td><td>"+result.dt.routeName+"</td></tr>"
											+"<tr><td id='lieMing'>文本路由</td><td>"+result.dt.routeText+"</td></tr>"
											+"<tr><td id='lieMing'>光路方案</td><td></td></tr>"
											+"<tr><td id='lieMing'>光路用途</td><td></td></tr>"
											+"<tr><td id='lieMing'>传输段</td><td></td></tr>"
											+"<tr><td id='lieMing'>传输系统</td><td>"+result.dt.srvName+"</td></tr>"
											+"<tr><td id='lieMing'>跳纤图片</td>"
											+"<td>"+img+"</td></tr>"
										+"</tbody>"
									+"</table>"
						))
					} else {
						layer.msg(eval(r).r_content, {
							icon : 1
						});
					}
				}
			});
        });
		//---------------------成端详情--------------------------
		$("a[op='select']").on("click", finCable);
		var devId=${devId};
		function finCable(){
// 			$("#cableThead").display = "";
			var side=$(this).attr("dside");
			var groupName=$(this).attr("dname");
			
			document.getElementById('cableThead').style.display='';
			document.getElementById('cableSpan').style.display='';
			document.getElementById("cableSide").innerText = side;//给span标签赋值
			document.getElementById("cableName").innerText = groupName;//给span标签赋值
			var groupId=$(this).attr("dui");
			$.ajax({
				type : "POST",
				url : path + "/queryFinCable.htm?groupId="+groupId,
				success : function(r) {
					var table = $("#tableCable");
					$("#tableCable tbody").html("");
					var result = eval(r);
					if (result.r = 1) {
						for(var i=0;i<result.dtList.length;i++){
							
							table.append($(
												"<tr><td>"+result.dtList[i].sectCode+"</td>"
												+"<td>"+result.dtList[i].secName+"</td>"
												+"<td>"+result.dtList[i].inused+"</td>"
												+"<td>"+result.dtList[i].notInused+"</td></tr>"
							))
						}
					} else {
						layer.msg(eval(r).r_content, {
							icon : 1
						});
					}
				}
			});
		}
		//----------------直熔详情------------------
		$("a[op='zhiRong']").on("click", finZhiCable);
		function finZhiCable(){
			var groupId=$(this).attr("dui");
			var side=$(this).attr("dside");
			var groupName=$(this).attr("dname");
			document.getElementById('zhiThead').style.display='';
			document.getElementById('zhiSpan').style.display='';
			document.getElementById("zhiSide").innerText = side;//给span标签赋值
			document.getElementById("zhiName").innerText = groupName;//给span标签赋值
			$.ajax({
				type : "POST",
				url : path + "/queryZhiCable.htm?groupId="+groupId,
				success : function(r) {
					var table = $("#tableZhiCable");
					$("#tableZhiCable tbody").html("");
					var result = eval(r);
					if (result.r = 1) {
						for(var i=0;i<result.dtList.length;i++){
							
							table.append($(
											"<tr><td>"+result.dtList[i].aSectCode+"</td>"
											+"<td>"+result.dtList[i].aSecName+"</td>"
											+"<td>"+result.dtList[i].investment+"</td>"
											+"<td>"+result.dtList[i].zSectCode+"</td>"
											+"<td>"+result.dtList[i].zSecName+"</td>"
											+"<td>"+result.dtList[i].zInvestment+"</td></tr>"
							))
						}
					} else {
						layer.msg(eval(r).r_content, {
							icon : 1
						});
					}
				}
			});
		}
    </script>
</c:forEach>

