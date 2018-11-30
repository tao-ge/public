<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String rc = request.getContextPath();
	request.setAttribute("path", rc);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${path}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path }/js/jquery.js"></script>

<script type="text/javascript" src="${path }/js/layer.js"></script>
<script type="text/javascript">
	path = '${path }';
</script>
<script src="${path }/js/echarts.js"></script>
<title>光路示意图</title>

</head>
<body>

<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="#">首页</a></li>
	    <li><a href="#">资源台账管理</a></li>
	    <li><a href="#">设施管理</a></li>
	    <li><a href="#">光路查询</a>
	    <li><a href="#">光路详情</a>
	    </li>
	    </ul>
    </div>
	<div class="formbody">
	
	    <div id="usual1" class="usual"> 
    	  <div class="formtitle"><span>${rt.routeName}</span></div>
		    <div class="itab">
			  	<ul> 
			    <li>
				<a>光路信息</a></li> 
			  	</ul>
		    </div> 
 	
		<form name="editForm" id="editForm" method="post"
			action="">		
			<table class="tableDetail">
				<tbody>
					
					<tr>
					    <td class="odd" width="100px">A端设施：</td>
						<td width="400px">${rt.aotherName}</td>
					</tr>
					<tr>
						<td class="odd"  width="100px">Z端设施：</td>
						<td >${rt.zotherName}</td>
					</tr>
					<tr>
						<td class="odd">光路文本路由：</td>
						<td>${rt.routetext}</td>
						
					</tr>
					<tr>
						<td class="odd">光路示意图：</td>
						<td>
						<div id="main" style="width:800px; height:700px; border:dotted 1px;"></div>
						
						</td>
					</tr>
					<tr>
						<td class="odd">最后修改时间：</td>
						<td>
							<fmt:formatDate value="${rt.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
						</td>
					</tr>
					
					
				</tbody>
			</table>

		</form>
	</div>
	</div>
	
 <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '',
				subtext: '',
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
           
			textStyle:{
			
				color:"#000"
			
			}
			,
			series: [{
                name:'光交箱',
                type:'graph',
				nodeScaleRatio:0.6,
				layout:'force',
				symbol:"rect",
				symbolSize:10,
				legendHoverLink:true,
				width:780,height:700,
				label:
				{
					normal:{show:true,position:'bottom'}
				},				
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
				force:
				{
					gravity:0.1,
					repulsion:600,
					edgeLength:150
				},
                roam:true,
				draggable:true,
				coordinateSystem:null,
				animationDuration: 1500,
                animationEasingUpdate: 'quinticInOut',
                data:[		
						<c:forEach items="${rt.chartNodes}" var="node">						                  	
						{name: '${node.nodeName}',value:'${node.nodeName}'},
						</c:forEach>       
					  ]
				,
				links:[
	                    <c:forEach items="${rt.chartlinks}" var="link">
						{source : '${link.targetName}', target : '${link.sourceName}'},
						</c:forEach>
					  ]
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
		myChart.on('click', function (params) {
			
			/*alert(params.value);
			 layer.open({
   			  type: 2,
   			  content: '${path}'+"/fiberdiscChartTest.htm?sl=5",
   			  area: ['650px', '500px'],
   			  maxmin: true
   			});*/
        });
		
		
    </script>
</body>
</html>
