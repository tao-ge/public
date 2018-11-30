<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
String rc = request.getContextPath(); 
request.setAttribute("path",rc);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script src="${path }/js/echarts.js"></script>
	<title>熔纤盘示意图</title>
</head>
<c:if test="${flag=='0'}">
<center>没有数据！</center>
</c:if>
<c:forEach items="${fgs}" var="f">
<center><div id="main${f.groupName}" style="width: 600px; height:${f.groupHeight}px; border:dotted 1px; margin:auto;"></div></center>
<script type="text/javascript">
 
        // 基于准备好的dom，初始化echarts实例
        var myChart${f.groupName} = echarts.init(document.getElementById('main${f.groupName}'));

        // 指定图表的配置项和数据
        var option${f.groupName} = {
        	title: {
                       text: '熔纤盘/${f.groupName}',
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
                left:30,top:80,
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
						    </c:otherwise></c:choose>
						    
			                name: '${pds.name}',x:${pds.x},y:${pds.y},value: '大碗大碗的大'},            	
	                    </c:forEach>     
                
					],
					links:[
		                    <c:forEach items="${f.linkDatas}" var="links">
							{source : '${links.source}', target : '${links.target}'},
							</c:forEach>
							{source : '乏味的阿瓦蒂', target : '啊行啊的'},
							]
				
            }]
			,
			color:['rgba(237,221,112,1)','rgba(255,255,255,1)', 'rgba(157,202,100,1)' ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart${f.groupName}.setOption(option${f.groupName});
/* 		myChart${f.groupName}.on('click', function (params) {
			window.open("routeSimpleList.htm?code="+params.value);
                 
                 
        }); */
    </script>
  </c:forEach>
