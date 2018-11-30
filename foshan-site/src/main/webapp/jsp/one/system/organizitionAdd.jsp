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
	<script  type="text/javascript" src="${path }/js/jquery.js"></script>
	<script type="text/javascript" src="${path}/js/select-ui.min.js"></script>
	
	<script type="text/javascript" src="${path}/js/layer.js"></script>
	<script type="text/javascript">
	$(function(){
		$('.tablelist tbody tr:odd').addClass('odd');
	})
	
	</script>
	<title>信息管理系统界面</title>
	<style type="text/css">
	.selectselect{
		font-style:normal;
		background:#fff;
		z-index:2;cursor:pointer;
		height:34px;
		font-size:12px; text-indent:5px;background:url(../images/inputbg.gif) repeat-x;_background:none;_border:none;
		border-width:1px;border-style:solid;border-color:#1c91e3 #1c91e3 #1c91e3 #1c91e3;
	}
	</style>
	<script type="text/javascript">
    	var path="${path}";
    	/* $(document).ready(function(e) {

    		$(".select3").uedSelect({
    			width : 152
    		});
    	}); */
    	
    	//全选/全不选
    	function selectAll(){
    	 $("input[name='checkAll']").each( function() {
    		  if($(this).prop("checked")==true) {
    		  	$("input[name='pages']").prop('checked', true);
    		  	return;
    		  } else {
    		 	$("input[name='pages']").prop('checked', false);
    		  	return;
    		  }
    	 });
     	}
    	
    	function checkValue(){
    		var orgName = $("#orgName").val();
    		var ip = $("#ip").val();
    		var prov = $("#prov").val();
    		var city = $("#city").val();
    		var area = $("#areas").val();
    		//心跳规则+电池阀值+电池告警间隔时间+门未关时间+门超时告警间隔+非法开门告警间隔+温度阀值+
    		//温度告警间隔+撞击阀值+撞击告警间隔+锁未关时间+锁未关告警间隔+非法撬锁告警间隔
    		var heartRate = $("#heartRate").val();
    		var batteryThd = $("#batteryThd").val();
    		var highTempShd = $("#highTempShd").val();
    		var tilt = $("#tilt").val();
    		var doorNotLockedint = $("#doorNotLocked").val();
    		var lockNotLockedint = $("#lockNotLocked").val();
    		var temAlarmIntimeint = $("#temAlarmIntime").val();
    		var unopendoorAlarmIntimeint = $("#unopendoorAlarmIntime").val();
    		var unopenlockAlarmIntimeint = $("#unopenlockAlarmIntime").val();
    		var tiltAlarmIntimeint = $("#tiltAlarmIntime").val();
    		var powerAlarmIntimeint = $("#powerAlarmIntime").val();
    		var unlockAlarmIntimeint = $("#unlockAlarmIntime").val();
    		var undoorAlarmIntimeint = $("#undoorAlarmIntime").val();
    		
    		if(orgName == null || orgName == ""){
    			alert("请输入机构名称！");
    			return false;
    		}
    		if(ip != null || ip != ""){
    			if(!checkIP(ip)){
    				alert("请输入合格的IP地址！");
    				return false;
    			}
    		}
    		
    		if(prov == null || prov == '' || city == null || city == '' || area == null || area == ''){
    			alert("请选择所属片区!");
    			return;
    		}
    		if(heartRate==null || heartRate==''){
    			alert("心跳频率不能为空!");
    			return;
    		}
    		if(batteryThd==null || batteryThd==''){
    			alert("电量阈值不能为空!");
    			return;
    		}
    		if(tilt==null || tilt==''){
    			alert("倾斜阈值不能为空!");
    			return;
    		}
    		if(highTempShd==null || highTempShd==''){
    			alert("温度阈值不能为空!");
    			return;
    		}
    		if(doorNotLockedint==null || doorNotLockedint==''){
    			alert("门未关阀值不能为空!");
    			return;
    		}
    		if(lockNotLockedint==null || lockNotLockedint==''){
    			alert("锁未关阀值不能为空!");
    			return;
    		}
    		if(temAlarmIntimeint==null || temAlarmIntimeint==''){
    			alert("温度告警时间间隔不能为空！");
    			return;
    		}
    		if(unopendoorAlarmIntimeint==null || unopendoorAlarmIntimeint==''){
    			alert("非法开门告警时间间隔不能为空!");
    			return;
    		}
    		if(unopenlockAlarmIntimeint==null || unopenlockAlarmIntimeint==''){
    			alert("非法开锁告警时间间隔不能为空!");
    			return;
    		}
    		if(tiltAlarmIntimeint==null || tiltAlarmIntimeint==''){
    			alert("倾斜震动告警时间间隔不能为空!");
    			return;
    		}
    		if(powerAlarmIntimeint==null || powerAlarmIntimeint==''){
    			alert("电量告警时间间隔不能为空!");
    			return;
    		}
    		if(unlockAlarmIntimeint==null || unlockAlarmIntimeint==''){
    			alert("锁未关告警时间间隔不能为空!");
    			return;
    		}
    		if(undoorAlarmIntimeint==null || undoorAlarmIntimeint==''){
    			alert("门未关告警时间间隔不能为空!");
    			return;
    		}
    		//----------------------------------------------------
    		if(heartRate<0 || heartRate>1440){
    			alert("请输入正确的心跳频率阀值!");
    			return;
    		}
    		if(doorNotLockedint<0 || doorNotLockedint>1440){
    			alert("请输入正确的门未关阀值!");
    			return;
    		}
    		if(lockNotLockedint<0 || lockNotLockedint>1440){
    			alert("请输入正确的锁未关阀值!");
    			return;
    		}
    		if(temAlarmIntimeint<0 || temAlarmIntimeint>65536){
    			alert("请输入正确的温度告警时间间隔！");
    			return;
    		}
    		if(unopendoorAlarmIntimeint<0 || unopendoorAlarmIntimeint>65536){
    			alert("请输入正确的非法开门告警时间间隔!");
    			return;
    		}
    		if(unopenlockAlarmIntimeint<0 || unopenlockAlarmIntimeint>65536){
    			alert("请输入正确的非法开锁告警时间间隔!");
    			return;
    		}
    		if(tiltAlarmIntimeint<0 || tiltAlarmIntimeint>65536){
    			alert("请输入正确的倾斜震动告警时间间隔!");
    			return;
    		}
    		if(powerAlarmIntimeint<0 || powerAlarmIntimeint>65536){
    			alert("请输入正确的电量告警时间间隔!");
    			return;
    		}
    		if(unlockAlarmIntimeint<0 || unlockAlarmIntimeint>65536){
    			alert("请输入正确的锁未关告警时间间隔!");
    			return;
    		}
    		if(undoorAlarmIntimeint<0 || undoorAlarmIntimeint>65536){
    			alert("请输入正确的门未关告警时间间隔!");
    			return;
    		}
    		
    		$("#searchform").submit();
    	}
    	
    	function checkIP(value){
    		var regexp = /^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/;
            var valid = regexp.test(value);
     
            if(!valid){//首先必须是 xxx.xxx.xxx.xxx 类型的数字，如果不是，返回false
                return false;
            }
             
            return value.split('.').every(function(num){
                //切割开来，每个都做对比，可以为0，可以小于等于255，但是不可以0开头的俩位数
                //只要有一个不符合就返回false
                if(num.length > 1 && num.charAt(0) === '0'){
                    //大于1位的，开头都不可以是‘0’
                    return false;
                }else if(parseInt(num , 10) > 255){
                    //大于255的不能通过
                    return false;
                }
                return true;
            });
    	}
    	
    	/* $(function(){
    		var prov = $('#prov');
    		if(!$.data(prov[0],'opt'))
    			{
		    		 $.ajax({url:path+"/ajax/getProv.htm?areaRank=1",dataType:"json",
		    			success:function(data)
		    			{
		    				$.data(prov[0],"opt",data);
		    				$.each(data,function(index,value){
		    					prov.append($("<option>").prop("value",data[index].name).append(data[index].value));
		    					changeProv();
		    				});
		    			}
		    		 });
    			}
    		else
    			{
    			$.each($.data(prov[0],'opt'),function(index,value){
    				prov.append($("<option>").prop("value",index).append(value));
    				changeProv();
				});
    			}
    	})*/
    
    	function changeProv(){
    		var provCd = $("#prov").val();
    		var city = $('#city');
     		city.html("");
    		if(provCd != ""){
		    		$.ajax({url:path+"/ajax/getCity.htm?areaRank=2&parentAreaCode="+provCd,dataType:"json",
		    		success:function(data)
		    		{
 		    			city.append("<option value='0'>==请选择市==</option>");
		    			$.each(data,function(index,value){
		    				city.append($("<option>").prop("value",data[index].name).append(data[index].value)).render;
		    				changeCity();
		    			});
		    		}
		    	});
    		}
    		
    	}
    	
    	function changeCity(){
    		var cityCd = $("#city").val();
    		var areas = $('#areas');
    		$('#areas').html("");
    		$('#areas').append("<option value='0'>==请选择区==</option>");
    		if(cityCd != ""){
		    		 $.ajax({url:path+"/ajax/getAreas.htm?areaRank=3&parentAreaCode="+cityCd,dataType:"json",
		    			success:function(data)
		    			{
		    				$.each(data,function(index,value){
		    					areas.append($("<option>").prop("value",data[index].name).append(data[index].value));
		    				});
		    			}
		    		 });
    		}
    	} 
    	//验证必须是数字  刘沧海  2017/10/20
    	function inforcheck(e) { 
    	    var re = /^\d+(?=\.{0,1}\d+$|$)/ 
    	    if (e.value != "") { 
    	        if (!re.test(e.value)) { 
    	            alert("请输入正确的数字"); 
    	            e.value = ""; 
    	            e.focus(); 
    	        } 
    	    } 
    	} 
    /* 	显示隐藏信息 */
    	function divShow(){
			document.getElementById("btnshow").style.display="block";
			document.getElementById("btnhref").innerHTML ="<img src='${path}/images/lockup.png'/> 关闭锁参数信息";
			document.getElementById("btnhref").href ="javascript:divhidden()";
		}
		function divhidden(){
			document.getElementById("btnshow").style.display="none";
			document.getElementById("btnhref").innerHTML ="<img src='${path}/images/lockdown.png'/> 录入锁参数信息";
			document.getElementById("btnhref").href ="javascript:divShow()"; 
		}
		/* 选择权限 */
		function selectP(f){
   		  if(document.getElementById(f).checked==true) {
   			  $("input[id="+f+"]").prop('checked', false);
   		  } else {
   			  $("input[id="+f+"]").prop('checked', true);
   		  }
		}
		
		function optimSet(){
			var url = path +"/organizitionInsert1.htm";
			var data = $('#searchform').serialize();
			$.post(url,data,function(r){
				if(r==0){
					alert("保存失败!",{icon:2});
				}else{
					var url = path +"/httpsConection.htm";
					$.post(url,data,function(r){
						if(r>0){
							alert("设置成功!");
						}else{
							alert("设置失败!");
						}
					},"json")
				}
			},"json")
		}
    </script>
</head>
<body>
    <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">系统管理</a></li>
    <li><a href="#">组织机构编辑</a></li>
    </ul>
    </div>

    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
    <form id="searchform" name="searchform" action="${path}/organizitionInsert.htm" method="post">
    <ul class="forminfo" id="addInfo">
    <li><label>机构名称:</label><input id="orgName" name="orgName" type="text" class="dfinput" /><font style="font-size: 25px" color=red>*</font></li>
    <li><label>机构地址:</label><input id="orgAddress" name="orgAddress" type="text" class="dfinput" /></li>
    <li><label>机构显示顺序:</label><input id="orders" name="orders" type="text" class="dfinput" onkeyup="value=value.replace(/[^\d]/g,'')"/></li>
    <li><label>联系电话:</label><input id="contactPhone" name="contactPhone" type="text" class="dfinput" /></li>
    <li><label>资管录入版本:</label><input placeholder="请输入数字!" id="inforEnterver" name="inforEnterver" type="text"  class="dfinput" onblur="inforcheck(this)"  onkeyup="this.value=this.value.replace(/[^0-9.]/g,'')"/></li>
    <li><label>所属片区:</label>
    	<select class="selectselect" name="prov" id="prov" runat="server" onchange="changeProv();">
	     <option value='0'>==请选择省==</option>
	     <c:forEach items="${provinceList}" var="pro">
	     	<option value="${pro.name }">${pro.value }</option>
	     </c:forEach>
	    </select>
    	<select class="selectselect" name="code1" id="city" runat="server" onchange="changeCity();">
    		<option value=''>==请选择市==</option> 
	    </select>
	    <select class="selectselect" name="code2" runat="server" id="areas">
	    	<option value=''>==请选择区==</option>
	    </select>
	    <font style="font-size: 25px" color=red>*</font>
    </li>
    <%-- <li><font style="font-size: 25px" color=red>*</font><label>所属平台:</label>
    <div class="vocation">
    	 <select name="devPlatform" id="devPlatform" class="select3">
               <c:forEach  items="${devPlatformList}" var="item" varStatus="status">
                  <option value="${item.key}" <c:if test="${item.key == facilityCon.devPlatform}">selected ="selected"</c:if>>${item.value}</option>
               </c:forEach>
		  </select> 
	</div>
    </li> --%>
     <li style="font-size: 10px"><label>所属平台：</label>
		<c:forEach items="${devPlatformList}" var="item" >
				<input type="checkbox" value="${item.valueCode}" name="devPlatform">${item.valueName}
		</c:forEach>
    </li>
   <%--  <li style="font-size: 10px"><label>角色：</label>
		<c:forEach items="${rList}" var="r" >
			<c:if test="${r.roleId!=40}">
				<input type="checkbox" value="${r.roleId}" name="roles">${r.roleName}
			</c:if>
		</c:forEach>
    </li> --%>
    <li><label>ip地址(锁):</label><input id="ip" name="ip" type="text" class="dfinput" /><font style="font-size: 25px" color=red>*</font></li>
     <li >
	    	<label>功能权限：</label>
	    	<table >
	    		<tr style="display: block;margin: 2px;">
	    			<td><input type="checkbox" name="checkAll" onclick="selectAll()">全选/全不选</td>
	    		</tr>
	    		<tr>
			    	<c:forEach items="${fristPages}" var="f">
			    		<c:if test="${f.childPages.size()!=0 && f.flag eq 1}">
					    		<td style="width: 100px;vertical-align: top;padding: 2px;" >
					    			<input name="" type="button" style="width: 99px;height: 20px;color: blue;background-color: #ABABAB" value="${f.pageName}" onclick="selectP('${f.pageId}');"/>
					    			<div style="height: 4px;">&nbsp;</div>
						    		    <c:forEach items="${f.childPages}"  var="child">
							    		    	<c:if test="${ child.flag  eq  1}">
							    		        	<input id="${f.pageId}" name="pages"  type="checkbox" value="${child.pageId}" >${child.pageName}
							    		    		<br>
							    		    	</c:if>
						    		    </c:forEach>
					    		 </td>
			    		</c:if>
			    	</c:forEach>
	    		</tr>
	    	</table>
	    </li>
	    <li><label>是否同步光调项目:</label>
		    <div class="vocation">
		    	<select class="selectselect" style="width: 50px;" name="isSynchOpss" id="isSynchOpss">
		    		<option value="0" selected = "selected">否</option>
		    		<option value="1">是</option>
		    	</select>
		    </div>
	    </li>
	<li class="title" style="background-color:#AAAAAA;font-family:宋体;text-align:center;margin:0 auto;">
		<div style="padding-top: 2px;padding-bottom: 2px">
	     	<a href="javascript:divShow();" id="btnhref" class="btn-slide">
	     	<font size=5><img src="${path}/images/lockdown.png" id="lockimg"/> 锁信息</font>
     		</a>
     	 </div>
    </li>
   <div>
   	&nbsp;
   </div>
    <div id="btnshow" style="display: none;">
    &nbsp;
    	<ul>
		    <li><label>服务器端口号(锁):</label><input id="port"name="port" type="text" class="dfinput" onkeyup="value=value.replace(/[^\d]/g,'')"/></li>
		    <li>
				    低温阈值(锁):<input id="lowTempThd"name="lowTempThd" type="text"  class="dfinput"  value="0" style="width: 10%;margin-left: 3.5%;" onkeyup="value=value.replace(/[^\-?\d]/g,'')"/>
				    温度告警时间间隔:<input id="temAlarmIntime"name="temAlarmIntime" type="text" value="240"  class="dfinput" style="width: 8.3%;margin-left: 3.5%;" onkeyup="value=value.replace(/[^\-?\d]/g,'')"/>
		    </li>
		    <li>
		    	高温阈值(锁):<input id="highTempShd" name="highTempShd" type="text" class="dfinput" value="0" style="width: 10%;margin-left: 3.5%;" onkeyup="value=value.replace(/[^\d]/g,'')"/>
				非法开门告警时间间隔:<input id="unopendoorAlarmIntime"name="unopendoorAlarmIntime" value="240" type="text"  class="dfinput" style="width: 8.3%;margin-left: 1.3%;" onkeyup="value=value.replace(/[^\-?\d]/g,'')"/>
		    </li>
		    <li>
		    	湿度阈值(锁):<input id="humidityShd" name="humidityShd" type="text" class="dfinput" value="0" style="width: 10%;margin-left: 3.5%;" onkeyup="value=value.replace(/[^\d]/g,'')"/>
				非法开锁告警时间间隔:<input id="unopenlockAlarmIntime"name="unopenlockAlarmIntime" value="240" type="text"  class="dfinput" style="width: 8.3%;margin-left: 1.3%;" onkeyup="value=value.replace(/[^\-?\d]/g,'')"/>
		    </li>
		    <li>
				    倾斜震动阈值(锁):<input id="tilt" name="tilt" type="text" class="dfinput" value="0" style="width: 10%;margin-left: 1.5%;" onkeyup="value=value.replace(/[^\d]/g,'')"/>
				    倾斜震动告警时间间隔:<input id="tiltAlarmIntime" name="tiltAlarmIntime" value="240" type="text" class="dfinput" style="width: 8.3%;margin-left: 1.2%;" onkeyup="value=value.replace(/[^\d]/g,'')"/>
			</li>
			<li>
				电量阈值(锁):<input id="batteryThd" name="batteryThd" type="text" class="dfinput" value="0"  style="width: 10%;margin-left: 3.5%;" onkeyup="value=value.replace(/[^\d]/g,'')"/>
				电量告警时间间隔:<input id="powerAlarmIntime" name="powerAlarmIntime" type="text" value="240" class="dfinput" style="width: 8.3%;margin-left: 3.5%;" onkeyup="value=value.replace(/[^\d]/g,'')"/>
			</li>
		    <li>
		    	锁未关阀值(锁):<input id="doorNotLocked" name="doorNotLocked" type="text"  value="240" class="dfinput" style="width: 10%;margin-left: 2.5%;" onkeyup="value=value.replace(/[^\d]/g,'')"/>
				锁未关告警时间间隔:<input id="unlockAlarmIntime" name="unlockAlarmIntime" type="text" value="240" class="dfinput" style="width: 8.3%;margin-left: 2.3%;" onkeyup="value=value.replace(/[^\d]/g,'')"/>
		    </li>
		    <li>
		  		  门未关阈值(锁):<input id="lockNotLocked" name="lockNotLocked" type="text"  value="240" class="dfinput" style="width: 10%;margin-left: 2.5%;" onkeyup="value=value.replace(/[^\d]/g,'')"/>
				 门未关告警时间间隔:<input id="undoorAlarmIntime" name="undoorAlarmIntime" type="text"  value="240" class="dfinput" style="width: 8.3%;margin-left: 2.3%;" onkeyup="value=value.replace(/[^\d]/g,'')"/>
		  	</li>
		    <li><label>采集频率(锁):</label><input placeholder="例如设置为2:13上报，计算方法为（2*60+13）" id="colFreq" name="colFreq" type="text"  class="dfinput" onkeyup="value=value.replace(/[^\d]/g,'')"/></li>
		    <li><label>心跳频率(锁):</label><input id="heartRate" name="heartRate" type="text" value="1440" class="dfinput" onkeyup="value=value.replace(/[^\d]/g,'')" /></li>
		    <li><label>手机软件版本:</label><input id="mobileVer" name="mobileVer" type="text" class="dfinput" /></li>
		    <li><label>智能锁版本(锁):</label><input id="devVer" name="devVer" type="text" class="dfinput" /></li>
		    <li><label>智能锁软件地址(锁):</label><input id="devVerUrl" name="devVerUrl" type="text" class="dfinput" /></li>
		    <li><label>MID(锁):</label><input id="mid" name="mid" type="text" class="dfinput" /></li>
		    <li><label>PID(锁):</label><input id="pid" name="pid" type="text" class="dfinput" /></li>
    	</ul>
     </div>
    <li><label>&nbsp;</label><input name="directFlg" type="checkbox" value="1" />保存并继续录入&nbsp;
    <input name="" type="button" class="btn" value="确认保存" onclick="checkValue();"/>
    <input name="" type="button" class="btn" value="设置参数" onclick="optimSet();"/></li>
    </ul>
    </form>
    </div>

    <script type="text/javascript">
    	
    </script>
</body>
</html>