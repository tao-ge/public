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
   
    <script type="text/javascript" src="${path }/js/layer.js"></script>
	<title>设施关系示意图</title>
</head>

<body >
<center><div id="main" style="height:650px"></div></center>
 	
	  <script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
	  
	   <script type="text/javascript">
        // 路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
        
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/force' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main')); 
                
                var option = {
		title : {
			text: '设施网络关系图',
			subtext: '${userName}',
			x:'right',
			y:'bottom'
		},
		tooltip : {
			trigger: 'item',
			formatter: '{a} : {b}'
		},
		toolbox: {
			show : true,
			feature : {
				restore : {show: true},
				
				saveAsImage : {show: true}
			}
		},
		legend: {
			x: 'left',
			   data:['ODF','光交箱','光终端盒','光分纤箱','光缆接头']
		  
		},
		series : [
			{
				type:'force',
				name : "设施关系",
				ribbonType: true,
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
				itemStyle: {
					normal: {
						label: {
							show: false,
							textStyle: {
								color: '#333'
							}
						},
						nodeStyle : {
							brushType : 'both',
							borderColor : 'rgba(255,215,0,0.4)',
							borderWidth : 1
						},
						linkStyle: {
							type: 'curve'
						}
					},
					emphasis: {
						label: {
							show: false
							// textStyle: null      // 默认使用全局文本样式，详见TEXTSTYLE
						},
						nodeStyle : {
							//r: 30
						},
						linkStyle : {}
					}
				},
				useWorker: false,
				minRadius : 3,
				maxRadius : 5,
				gravity: 1,
				scaling: 1.1,
				roam: 'move',
				nodes:[
						<c:forEach items="${crNodes}" var="nodes">
							
						{category:'${nodes.devType}', name: '${nodes.devName}',value:'${nodes.devId}',dataType:'node'},
						</c:forEach>
					
				],
				links:[
	                    <c:forEach items="${crLinks}" var="links">
						{source : '${links.devAName}', target : '${links.devZName}'},
						</c:forEach>
						]
			}
		]
};
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
                myChart.on('click', function (params) {
                	//console.log(params);
                	if(params.data.dataType=='node')
    				{
							 layer.open({
				   			  type: 2,
				   			  content: '${path}'+"/fiberdiscChart.htm?devId="+params.value,
				   			  area: ['650px', '500px'],
				   			  maxmin: true
				   			});
				}
                });
            }
        );
    </script>

</body>
</html>