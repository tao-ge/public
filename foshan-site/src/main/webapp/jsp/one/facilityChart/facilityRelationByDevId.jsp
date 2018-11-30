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
	  <script type="text/javascript" src="${path }/js/jquery.js"></script>
    <script src="${path }/js/echarts.js"></script>  
    <script type="text/javascript" src="${path }/js/layer.js"></script>
	<title>设施拓扑图</title>
</head>

<body style="overflow-x:hidden ;overflow-y:hidden ">
<center><div id="main" style="width: 100%; height:100%; border:dotted 1px; margin:auto;position: absolute;"></div></center>
 <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '设施关系示意图',
				subtext: '${userName}',
				x:'right',
				y:'bottom'
            },
			backgroundColor:'#fff',
			
			toolbox: {
               show : true,
               feature : {
            restore : {show: true},
            magicType: {show: true, type: ['force', 'chord']},
            saveAsImage : {show: true}
				}
			},
			
            tooltip: {},
            legend: {
			  x: 'left',
                data:['ODF','光交箱','光终端盒','光分纤箱','光缆接头']
            },
			textStyle:{
			
				color:"#000"
			
			}
			,
           
           
            series: [{
                name:'拓扑图',
                type:'graph',
				nodeScaleRatio:0.6,
				layout:'force',
				symbol:"rect",
				symbolSize:10,
				legendHoverLink:true,
				label:
				{
					normal:{show:true,position:'bottom'}
				},
				categories:[
					{
					 name:"ODF",symbol:'rect'
					},
					{
					 name:"光交箱",symbol:'rect'
					}	
					,
					{
					 name:"光终端盒",symbol:'rect'
					}	
					,
					{
					 name:"光分纤箱",symbol:'rect'
					}	
					,
					{
					 name:"光缆接头",symbol:'rect'
					}	
				
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
								brushType : 'both',
								borderColor : 'rgba(255,25,0,0.4)',
								borderWidth : 1
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
				force:
				{
					gravity:0.1,
					repulsion:600,
					edgeLength:80
				},
                roam:true,
				draggable:true,
				coordinateSystem:null,
				animationDuration: 1500,
                animationEasingUpdate: 'quinticInOut',
                data:[		
					<c:forEach items="${crNodes}" var="nodes"><c:choose>
							<c:when test="${nodes.devName==currNode}">
							{ category:'${nodes.devType}',name: '${nodes.devName}',value:'${nodes.devId}',code:'${nodes.devCode}',
								symbolSize:40,itemStyle:{normal:{color:'10ff00'}}},
							</c:when>
							<c:otherwise>
							{ category:'${nodes.devType}',name: '${nodes.devName}',value:'${nodes.devId}',code:'${nodes.devCode}'},
						    </c:otherwise></c:choose>
					</c:forEach>
       
					],
				links:[
                    <c:forEach items="${crLinks}" var="links">
					{source : '${links.devAName}', target : '${links.devZName}'},
					</c:forEach>
					]
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
		myChart.on('click', function (params) {
			
			
			if(params.dataType=='node')
				{
				 layer.open({
	   			  type: 2,
	   			  content: '${path}'+"/fiberdiscChart.htm?devId="+params.value,
	   			  area: ['650px', '500px'],
	   			  maxmin: true
	   			});
			}
        });
    </script>

</body>
</html>