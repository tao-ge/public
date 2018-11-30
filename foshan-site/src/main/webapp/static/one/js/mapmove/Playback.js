var DeviceID = 0;
var TimeZone = "";
var DeviceName = "";
var isShowLBS = 0; //是否显示LBS数据,0只显示GPS,1显示GPS+LBS
var speedLimit = 0;
var temp;
$(document).ready(function () {
	var title = "Playback:";
    DeviceName = $("#hidDeviceName").val();
    document.title = title + DeviceName;
    speedLimit = parseFloat($("#hidSpeedLimit").val());
    initLanguage();
    syncSize();
    initSlider();
    initmap();
    
    DeviceID = $("#hidDeviceID").val();
    TimeZone = $("#hidTimeZone").val();
});

function initLanguage() {
    $("#btnPlay").val("播放");
    $("#btnPause").val("下一个");
    $("#btnNext").val("暂停");
}

function initSlider() {
    $("#PlaySpeed").slider({
        min: 30,
        max: 1000,
        value: 200,
        step: 10,
        animate: true,
        change: function (event, ui) {
            forTimer = ui.value;
            var isPlay = document.getElementById("btnNext").disabled;
            //var isPlay = $("#btnNext").attr("disabled");  //这里获取的是字符串,""或underfind
            if (!isPlay) {
                if (forTime) {
                    clearInterval(forTime);
                    showHistoryMap();
                    forTime = setInterval(showHistoryMap, forTimer);
                }
            }
        }

    });
}

window.onresize = syncSize;

function syncSize() {
    var h = $(this).height() - 30;
    var w = $(this).width() - 0;
    $("#map_canvas").css("height", h + "px");
    $("#map_canvas").css("width", w + "px");
}


var forTimer = 200;
var queryStartDate = null;
var queryEndDate = null;
var isSerachFirst = true;
var index = 0;
var forTime = null;
var isSetZoom = true;
var isFirstShowHistory = true;
var lastMarker = null;
var allLocation = [];
var lastAllLocationLength = -1;
var lastLocationID = -1;
var allMarker = [];
var allPolyline = [];
var LocationID = 0;

function serchLocation() {

    var startDate = $("#txtStartDate").val();
    var endDate = $("#txtEndDate").val();
    if(startDate == "" || endDate == ""){
    	alert("请选择时间！");
    	return false;
    }
    var diff = DateDiff(startDate, endDate);
    if (diff <= 0) {
        alert('时间选择错误');
        return false;
    } else {
        if (DeviceID > 0) {
            deleteOverLays(allPolyline);
            deleteOverLays(allMarker);

            lastLocationID = -1;
            queryStartDate = startDate;
            queryEndDate = endDate;
            isSerachFirst = true;
           // isSetZoom = true;
            isFirstShowHistory = true;
            lastMarker = null;
            index = 0;
            
	    if (forTime) {
                clearInterval(forTime);
            }
            forTime = null;

            allLocation = [];
            lastAllLocationLength = -1;

            WebGetHistory();
        }
    }
}

function WebGetHistory() {
	var startDate = $("#txtStartDate").val();
	var endDate = $("#txtEndDate").val();
	var groupCode = $("#devGroupCode").val();

	var lastDevice = null;
    if (allLocation.length > 0) {
        lastDevice = allLocation[allLocation.length - 1];
    }
    allLocation.length = 0;
    index = 0;
    document.getElementById("btnPause").disabled = true;
    document.getElementById("btnNext").disabled = true;
    //上一次搜索,最后一条记录,用于添加停止点
    if (lastDevice) {
        allLocation.push(lastDevice);
        index = 1;
    }
    document.getElementById("spanMsg").innerHTML = "正在加载";
    document.getElementById("btnPlay").disabled = true;
    //CollectGarbage();
    $.ajax({
		url:"../../mobile/searchCarAdrJsonList.action",
		type: "post",          //使用post方法访问后台  
		dataType: "json",      //返回json格式的数据
		data:{
    		"txtStartDate":startDate,
    		"txtEndDate": endDate,
    		"devGroupCode":groupCode
    	},
		success: function(data){
    		//alert(data.d);
    		if(data.d != null && data.d != ""){
    			WebGetHistoryCallBack(data.d);
    		}else{
    			alert("暂无相关数据！");
    			document.getElementById("spanMsg").innerHTML = "";
    			document.getElementById("btnPlay").disabled = false;
    			document.getElementById("btnPause").disabled = true;
    		    document.getElementById("btnNext").disabled = true;
    		}
    		
		}   
	});
    
	//lng + 0.0065 lat + 0.0060
/*
var temp = "d:{devices:[{deviceUtcDate:\"2013-03-15 12:59:04\",latitude:\"31.518931\",longitude:\"120.313412\",baiduLat:\"31.524689\",baiduLng:\"120.319981\",speed:\"0.00\",course:\"0\",dataType:\"1\""+
	",IsStop:1,distance:\"320.865900000001\",stopTimeMinute:4,serverUtcTime:\"2013-03-15 13:03:45\"},{deviceUtcDate:\"2013-03-15 13:05:46\",latitude:\"31.519953\",longitude:\"120.313762\",baiduLat:\"31.525710\",baiduLng:\"120.320331\",speed:\"19.00\",course:\"349\",dataType:\"1"+
	"\",IsStop:0,distance:\"320.865900000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 13:05:56\"},{deviceUtcDate:\"2013-03-15 13:05:57\",latitude:\"31.520518\",longitude:\"120.313422"+
	"\",baiduLat:\"31.526275\",baiduLng:\"120.319991\",speed:\"21.00\",course:\"319\",dataType:\"1\",IsStop:0,distance:\"320.936600000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 13:06:05\"},"+
	"{deviceUtcDate:\"2013-03-15 13:06:06\",latitude:\"31.520926\",longitude:\"120.313040\",baiduLat:\"31.526684\",baiduLng:\"120.319610\",speed:\"21.00\",course:\"313\",dataType:\"1"+
	"\",IsStop:0,distance:\"320.994700000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 13:06:15\"},{deviceUtcDate:\"2013-03-15 13:06:16\",latitude:\"31.521333\",longitude:\"120.313150"+
	"\",baiduLat:\"31.527090\",baiduLng:\"120.319720\",speed:\"22.00\",course:\"37\",dataType:\"1\",IsStop:0,distance:\"321.041200000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 13:06:25\"},"+
	"{deviceUtcDate:\"2013-03-15 13:06:27\",latitude:\"31.521808\",longitude:\"120.313722\",baiduLat:\"31.527565\",baiduLng:\"120.320291\",speed:\"22.00\",course:\"54\",dataType:\"1"+
	"\",IsStop:0,distance:\"321.117000000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 13:06:35\"},{deviceUtcDate:\"2013-03-15 13:06:36\",latitude:\"31.522056\",longitude:\"120.314184"+
"\",baiduLat:\"31.527814\",baiduLng:\"120.320753\",speed:\"0.00\",course:\"0\",dataType:\"1\",IsStop:1,distance:\"321.168800000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 13:06:46\"},"+
	"{deviceUtcDate:\"2013-03-15 15:07:38\",latitude:\"31.566867\",longitude:\"120.326255\",baiduLat:\"31.572792\",baiduLng:\"120.332809\",speed:\"56.00\",course:\"325\",dataType:\"1"+
	"\",IsStop:0,distance:\"329.453500000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:07:53\"}],lastLocationID:2869,lastDeviceUtcDate:\"3/15/2013 3:07:38 PM\"}";

temp = "{devices:[{deviceUtcDate:\"2013-03-15 12:59:04\",latitude:\"31.518931\",longitude:\"120.313412\",baiduLat:\"31.524689\",baiduLng:\"120.319981\",speed:\"0.00\",course:\"0\",dataType:\"1\",IsStop:1,distance:\"320.865900000001\",stopTimeMinute:4,serverUtcTime:\"2013-03-15 13:03:45\"},{deviceUtcDate:\"2013-03-15 13:05:46\",latitude:\"31.519953\",longitude:\"120.313762\",baiduLat:\"31.525710\",baiduLng:\"120.320331\",speed:\"19.00\",course:\"349\",dataType:\"1\",IsStop:0,distance:\"320.865900000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 13:05:56\"},{deviceUtcDate:\"2013-03-15 13:05:57\",latitude:\"31.520518\",longitude:\"120.313422\",baiduLat:\"31.526275\",baiduLng:\"120.319991\",speed:\"21.00\",course:\"319\",dataType:\"1\",IsStop:0,distance:\"320.936600000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 13:06:05\"},{deviceUtcDate:\"2013-03-15 13:06:06\",latitude:\"31.520926\",longitude:\"120.313040\",baiduLat:\"31.526684\",baiduLng:\"120.319610\",speed:\"21.00\",course:\"313\",dataType:\"1\",IsStop:0,distance:\"320.994700000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 13:06:15\"},{deviceUtcDate:\"2013-03-15 13:06:16\",latitude:\"31.521333\",longitude:\"120.313150\",baiduLat:\"31.527090\",baiduLng:\"120.319720\",speed:\"22.00\",course:\"37\",dataType:\"1\",IsStop:0,distance:\"321.041200000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 13:06:25\"},{deviceUtcDate:\"2013-03-15 13:06:27\",latitude:\"31.521808\",longitude:\"120.313722\",baiduLat:\"31.527565\",baiduLng:\"120.320291\",speed:\"22.00\",course:\"54\",dataType:\"1\",IsStop:0,distance:\"321.117000000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 13:06:35\"},{deviceUtcDate:\"2013-03-15 13:06:36\",latitude:\"31.522056\",longitude:\"120.314184\",baiduLat:\"31.527814\",baiduLng:\"120.320753\",speed:\"0.00\",course:\"0\",dataType:\"1\",IsStop:1,distance:\"321.168800000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 13:06:46\"},{deviceUtcDate:\"2013-03-15 13:06:46\",latitude:\"31.522206\",longitude:\"120.314439\",baiduLat:\"31.527964\",baiduLng:\"120.321008\",speed:\"10.00\",course:\"41\",dataType:\"1\",IsStop:0,distance:\"321.198200000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 13:06:55\"},{deviceUtcDate:\"2013-03-15 13:06:57\",latitude:\"31.522009\",longitude:\"120.314809\",baiduLat:\"31.527767\",baiduLng:\"120.321378\",speed:\"24.00\",course:\"140\",dataType:\"1\",IsStop:0,distance:\"321.239600000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 13:07:05\"},{deviceUtcDate:\"2013-03-15 13:07:07\",latitude:\"31.521433\",longitude:\"120.315384\",baiduLat:\"31.527190\",baiduLng:\"120.321953\",speed:\"29.00\",course:\"141\",dataType:\"1\",IsStop:0,distance:\"321.323800000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 13:07:15\"},{deviceUtcDate:\"2013-03-15 13:07:17\",latitude:\"31.520826\",longitude:\"120.315899\",baiduLat:\"31.526584\",baiduLng:\"120.322468\",speed:\"26.00\",course:\"142\",dataType:\"1\",IsStop:0,distance:\"321.407200000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 13:07:25\"},{deviceUtcDate:\"2013-03-15 13:07:27\",latitude:\"31.520216\",longitude:\"120.316324\",baiduLat:\"31.525974\",baiduLng:\"120.322893\",speed:\"28.00\",course:\"150\",dataType:\"1\",IsStop:0,distance:\"321.486200000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 13:07:35\"},{deviceUtcDate:\"2013-03-15 13:07:36\",latitude:\"31.519711\",longitude:\"120.316662\",baiduLat:\"31.525469\",baiduLng:\"120.323231\",speed:\"19.00\",course:\"147\",dataType:\"1\",IsStop:0,distance:\"321.550900000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 13:07:45\"},{deviceUtcDate:\"2013-03-15 13:07:46\",latitude:\"31.519094\",longitude:\"120.317065\",baiduLat:\"31.524852\",baiduLng:\"120.323635\",speed:\"32.00\",course:\"144\",dataType:\"1\",IsStop:0,distance:\"321.629500000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 13:07:55\"},{deviceUtcDate:\"2013-03-15 13:07:57\",latitude:\"31.518234\",longitude:\"120.317657\",baiduLat:\"31.523992\",baiduLng:\"120.324226\",speed:\"34.00\",course:\"150\",dataType:\"1\",IsStop:0,distance:\"321.740500000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 13:08:05\"},{deviceUtcDate:\"2013-03-15 13:08:07\",latitude:\"31.517709\",longitude:\"120.318045\",baiduLat:\"31.523467\",baiduLng:\"120.324615\",speed:\"24.00\",course:\"141\",dataType:\"1\",IsStop:0,distance:\"321.809600000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 13:08:25\"},{deviceUtcDate:\"2013-03-15 13:08:27\",latitude:\"31.517486\",longitude:\"120.318480\",baiduLat:\"31.523244\",baiduLng:\"120.325050\",speed:\"15.00\",course:\"336\",dataType:\"1\",IsStop:0,distance:\"321.857800000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 13:08:45\"},{deviceUtcDate:\"2013-03-15 13:08:44\",latitude:\"31.517911\",longitude:\"120.318419\",baiduLat:\"31.523669\",baiduLng:\"120.324988\",speed:\"13.00\",course:\"28\",dataType:\"1\",IsStop:0,distance:\"321.905500000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 13:08:55\"},{deviceUtcDate:\"2013-03-15 13:08:55\",latitude:\"31.518273\",longitude:\"120.318719\",baiduLat:\"31.524030\",baiduLng:\"120.325288\",speed:\"14.00\",course:\"35\",dataType:\"1\",IsStop:0,distance:\"321.954800000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 13:09:05\"},{deviceUtcDate:\"2013-03-15 13:09:05\",latitude:\"31.518633\",longitude:\"120.319165\",baiduLat:\"31.524390\",baiduLng:\"120.325735\",speed:\"0.00\",course:\"0\",dataType:\"1\",IsStop:1,distance:\"322.013100000001\",stopTimeMinute:110,serverUtcTime:\"2013-03-15 14:59:58\"},{deviceUtcDate:\"2013-03-15 15:00:40\",latitude:\"31.524673\",longitude:\"120.312605\",baiduLat:\"31.530431\",baiduLng:\"120.319172\",speed:\"0.00\",course:\"0\",dataType:\"1\",IsStop:1,distance:\"322.929400000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:00:59\"},{deviceUtcDate:\"2013-03-15 15:01:00\",latitude:\"31.525299\",longitude:\"120.312022\",baiduLat:\"31.531057\",baiduLng:\"120.318589\",speed:\"45.00\",course:\"322\",dataType:\"1\",IsStop:0,distance:\"323.018500000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:01:09\"},{deviceUtcDate:\"2013-03-15 15:01:10\",latitude:\"31.526116\",longitude:\"120.311219\",baiduLat:\"31.531874\",baiduLng:\"120.317785\",speed:\"42.00\",course:\"320\",dataType:\"1\",IsStop:0,distance:\"323.137100000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:01:19\"},{deviceUtcDate:\"2013-03-15 15:01:19\",latitude:\"31.527204\",longitude:\"120.310164\",baiduLat:\"31.532962\",baiduLng:\"120.316730\",speed:\"0.00\",course:\"0\",dataType:\"1\",IsStop:1,distance:\"323.294300000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:01:32\"},{deviceUtcDate:\"2013-03-15 15:01:28\",latitude:\"31.528176\",longitude:\"120.309192\",baiduLat:\"31.533840\",baiduLng:\"120.315779\",speed:\"52.00\",course:\"321\",dataType:\"1\",IsStop:0,distance:\"323.437000000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:01:39\"},{deviceUtcDate:\"2013-03-15 15:01:38\",latitude:\"31.529193\",longitude:\"120.308197\",baiduLat:\"31.534857\",baiduLng:\"120.314784\",speed:\"55.00\",course:\"318\",dataType:\"1\",IsStop:0,distance:\"323.584400000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:01:49\"},{deviceUtcDate:\"2013-03-15 15:01:48\",latitude:\"31.530405\",longitude:\"120.306982\",baiduLat:\"31.536068\",baiduLng:\"120.313569\",speed:\"64.00\",course:\"321\",dataType:\"1\",IsStop:0,distance:\"323.761800000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:01:59\"},{deviceUtcDate:\"2013-03-15 15:01:59\",latitude:\"31.531333\",longitude:\"120.306155\",baiduLat:\"31.536997\",baiduLng:\"120.312742\",speed:\"22.00\",course:\"319\",dataType:\"1\",IsStop:0,distance:\"323.891500000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:02:09\"},{deviceUtcDate:\"2013-03-15 15:02:08\",latitude:\"31.531541\",longitude:\"120.306030\",baiduLat:\"31.537205\",baiduLng:\"120.312617\",speed:\"15.00\",course:\"5\",dataType:\"1\",IsStop:0,distance:\"323.917500000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:02:19\"},{deviceUtcDate:\"2013-03-15 15:02:18\",latitude:\"31.532143\",longitude:\"120.306650\",baiduLat:\"31.537807\",baiduLng:\"120.313237\",speed:\"43.00\",course:\"47\",dataType:\"1\",IsStop:0,distance:\"324.006600000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:02:29\"},{deviceUtcDate:\"2013-03-15 15:02:28\",latitude:\"31.532998\",longitude:\"120.307739\",baiduLat:\"31.538662\",baiduLng:\"120.314326\",speed:\"55.00\",course:\"45\",dataType:\"1\",IsStop:0,distance:\"324.147000000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:02:39\"},{deviceUtcDate:\"2013-03-15 15:02:38\",latitude:\"31.533983\",longitude:\"120.308985\",baiduLat:\"31.539648\",baiduLng:\"120.315575\",speed:\"56.00\",course:\"48\",dataType:\"1\",IsStop:0,distance:\"324.308300000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:02:49\"},{deviceUtcDate:\"2013-03-15 15:02:48\",latitude:\"31.534614\",longitude:\"120.309725\",baiduLat:\"31.540374\",baiduLng:\"120.316294\",speed:\"31.00\",course:\"44\",dataType:\"1\",IsStop:0,distance:\"324.405900000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:02:59\"},{deviceUtcDate:\"2013-03-15 15:02:58\",latitude:\"31.535458\",longitude:\"120.310740\",baiduLat:\"31.541217\",baiduLng:\"120.317309\",speed:\"56.00\",course:\"47\",dataType:\"1\",IsStop:0,distance:\"324.540400000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:03:09\"},{deviceUtcDate:\"2013-03-15 15:03:08\",latitude:\"31.536428\",longitude:\"120.312019\",baiduLat:\"31.542187\",baiduLng:\"120.318588\",speed:\"59.00\",course:\"48\",dataType:\"1\",IsStop:0,distance:\"324.702800000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:03:19\"},{deviceUtcDate:\"2013-03-15 15:03:18\",latitude:\"31.537351\",longitude:\"120.313285\",baiduLat:\"31.543110\",baiduLng:\"120.319854\",speed:\"57.00\",course:\"49\",dataType:\"1\",IsStop:0,distance:\"324.860900000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:03:29\"},{deviceUtcDate:\"2013-03-15 15:03:28\",latitude:\"31.538386\",longitude:\"120.314615\",baiduLat:\"31.544145\",baiduLng:\"120.321184\",speed:\"65.00\",course:\"48\",dataType:\"1\",IsStop:0,distance:\"325.031800000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:03:39\"},{deviceUtcDate:\"2013-03-15 15:03:38\",latitude:\"31.539534\",longitude:\"120.316129\",baiduLat:\"31.545294\",baiduLng:\"120.322698\",speed:\"69.00\",course:\"47\",dataType:\"1\",IsStop:0,distance:\"325.224000000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:03:49\"},{deviceUtcDate:\"2013-03-15 15:03:48\",latitude:\"31.540591\",longitude:\"120.317480\",baiduLat:\"31.546350\",baiduLng:\"120.324049\",speed:\"58.00\",course:\"47\",dataType:\"1\",IsStop:0,distance:\"325.398000000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:04:01\"},{deviceUtcDate:\"2013-03-15 15:03:59\",latitude:\"31.541703\",longitude:\"120.318914\",baiduLat:\"31.547462\",baiduLng:\"120.325483\",speed:\"64.00\",course:\"49\",dataType:\"1\",IsStop:0,distance:\"325.581900000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:04:09\"},{deviceUtcDate:\"2013-03-15 15:04:08\",latitude:\"31.542765\",longitude:\"120.320356\",baiduLat:\"31.548684\",baiduLng:\"120.326882\",speed:\"75.00\",course:\"48\",dataType:\"1\",IsStop:0,distance:\"325.760200000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:04:19\"},{deviceUtcDate:\"2013-03-15 15:04:19\",latitude:\"31.544142\",longitude:\"120.322180\",baiduLat:\"31.550060\",baiduLng:\"120.328712\",speed:\"0.00\",course:\"0\",dataType:\"1\",IsStop:1,distance:\"325.991100000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:04:32\"},{deviceUtcDate:\"2013-03-15 15:04:28\",latitude:\"31.545249\",longitude:\"120.323643\",baiduLat:\"31.551167\",baiduLng:\"120.330176\",speed:\"73.00\",course:\"46\",dataType:\"1\",IsStop:0,distance:\"326.176700000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:04:40\"},{deviceUtcDate:\"2013-03-15 15:04:38\",latitude:\"31.546416\",longitude:\"120.324998\",baiduLat:\"31.552333\",baiduLng:\"120.331531\",speed:\"59.00\",course:\"43\",dataType:\"1\",IsStop:0,distance:\"326.359400000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:04:49\"},{deviceUtcDate:\"2013-03-15 15:04:48\",latitude:\"31.547511\",longitude:\"120.326243\",baiduLat:\"31.553428\",baiduLng:\"120.332776\",speed:\"64.00\",course:\"44\",dataType:\"1\",IsStop:0,distance:\"326.529100000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:04:59\"},{deviceUtcDate:\"2013-03-15 15:04:58\",latitude:\"31.548739\",longitude:\"120.327586\",baiduLat:\"31.554657\",baiduLng:\"120.334119\",speed:\"68.00\",course:\"42\",dataType:\"1\",IsStop:0,distance:\"326.716000000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:05:09\"},{deviceUtcDate:\"2013-03-15 15:05:08\",latitude:\"31.550141\",longitude:\"120.329113\",baiduLat:\"31.556058\",baiduLng:\"120.335646\",speed:\"77.00\",course:\"43\",dataType:\"1\",IsStop:0,distance:\"326.928900000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:05:20\"},{deviceUtcDate:\"2013-03-15 15:05:18\",latitude:\"31.551476\",longitude:\"120.330787\",baiduLat:\"31.557581\",baiduLng:\"120.337271\",speed:\"74.00\",course:\"49\",dataType:\"1\",IsStop:0,distance:\"327.1439\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:05:29\"},{deviceUtcDate:\"2013-03-15 15:05:28\",latitude:\"31.552566\",longitude:\"120.332324\",baiduLat:\"31.558671\",baiduLng:\"120.338808\",speed:\"59.00\",course:\"50\",dataType:\"1\",IsStop:0,distance:\"327.333600000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:05:39\"},{deviceUtcDate:\"2013-03-15 15:05:38\",latitude:\"31.553255\",longitude:\"120.333631\",baiduLat:\"31.559363\",baiduLng:\"120.340124\",speed:\"51.00\",course:\"84\",dataType:\"1\",IsStop:0,distance:\"327.4793\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:05:49\"},{deviceUtcDate:\"2013-03-15 15:05:49\",latitude:\"31.552991\",longitude:\"120.335289\",baiduLat:\"31.559096\",baiduLng:\"120.341773\",speed:\"48.00\",course:\"74\",dataType:\"1\",IsStop:0,distance:\"327.639300000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:05:59\"},{deviceUtcDate:\"2013-03-15 15:05:58\",latitude:\"31.553773\",longitude:\"120.335764\",baiduLat:\"31.559881\",baiduLng:\"120.342258\",speed:\"46.00\",course:\"342\",dataType:\"1\",IsStop:0,distance:\"327.737300000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:06:11\"},{deviceUtcDate:\"2013-03-15 15:06:08\",latitude:\"31.554858\",longitude:\"120.334844\",baiduLat:\"31.560966\",baiduLng:\"120.341338\",speed:\"59.00\",course:\"316\",dataType:\"1\",IsStop:0,distance:\"327.886300000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:06:19\"},{deviceUtcDate:\"2013-03-15 15:06:18\",latitude:\"31.556285\",longitude:\"120.333821\",baiduLat:\"31.562393\",baiduLng:\"120.340314\",speed:\"69.00\",course:\"332\",dataType:\"1\",IsStop:0,distance:\"328.072400000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:06:29\"},{deviceUtcDate:\"2013-03-15 15:06:28\",latitude:\"31.557936\",longitude:\"120.332796\",baiduLat:\"31.564045\",baiduLng:\"120.339289\",speed:\"76.00\",course:\"330\",dataType:\"1\",IsStop:0,distance:\"328.280400000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:06:39\"},{deviceUtcDate:\"2013-03-15 15:06:39\",latitude:\"31.559541\",longitude:\"120.331669\",baiduLat:\"31.565650\",baiduLng:\"120.338163\",speed:\"60.00\",course:\"329\",dataType:\"1\",IsStop:0,distance:\"328.488600000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:06:49\"},{deviceUtcDate:\"2013-03-15 15:06:49\",latitude:\"31.560736\",longitude:\"120.330747\",baiduLat:\"31.566845\",baiduLng:\"120.337241\",speed:\"52.00\",course:\"324\",dataType:\"1\",IsStop:0,distance:\"328.647800000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:06:59\"},{deviceUtcDate:\"2013-03-15 15:06:58\",latitude:\"31.561728\",longitude:\"120.330027\",baiduLat:\"31.567836\",baiduLng:\"120.336521\",speed:\"52.00\",course:\"327\",dataType:\"1\",IsStop:0,distance:\"328.777600000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:07:09\"},{deviceUtcDate:\"2013-03-15 15:07:08\",latitude:\"31.562916\",longitude:\"120.329170\",baiduLat:\"31.568837\",baiduLng:\"120.335713\",speed:\"62.00\",course:\"327\",dataType:\"1\",IsStop:0,distance:\"328.933900000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:07:19\"},{deviceUtcDate:\"2013-03-15 15:07:19\",latitude:\"31.564486\",longitude:\"120.328060\",baiduLat:\"31.570410\",baiduLng:\"120.334614\",speed:\"0.00\",course:\"0\",dataType:\"1\",IsStop:1,distance:\"329.137900000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:07:32\"},{deviceUtcDate:\"2013-03-15 15:07:28\",latitude:\"31.565679\",longitude:\"120.327170\",baiduLat:\"31.571604\",baiduLng:\"120.333724\",speed:\"63.00\",course:\"326\",dataType:\"1\",IsStop:0,distance:\"329.295300000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:07:44\"},{deviceUtcDate:\"2013-03-15 15:07:38\",latitude:\"31.566867\",longitude:\"120.326255\",baiduLat:\"31.572792\",baiduLng:\"120.332809\",speed:\"56.00\",course:\"325\",dataType:\"1\",IsStop:0,distance:\"329.453500000001\",stopTimeMinute:0,serverUtcTime:\"2013-03-15 15:07:53\"}],lastLocationID:2869,lastDeviceUtcDate:\"3/15/2013 3:07:38 PM\"}";
*/
	//WebGetHistoryCallBack(temp);//自己添加
}


function WebGetHistoryCallBack(res) {
	var temp='' ;//"114.238785,30.612293,1336464358000,1;114.23857,30.612303,1336464377000,19;114.23769,30.612333,1336464387000,33;114.236565,30.612432,1336464397000,40;114.235535,30.612494,1336464407000,32;114.23493,30.612513,1336464417000,6;114.23471,30.612463,1336464427000,15;114.23374,30.612494,1336464437000,31;114.232216,30.612543,1336464447000,50;114.23074,30.612623,1336464457000,55;114.22919,30.612703,1336464468000,40;114.2283,30.612812,1336464478000,24;114.22779,30.613092,1336464488000,32;114.22685,30.613222,1336464499000,23;114.22604,30.613354,1336464509000,18;114.225815,30.613443,1336464519000,2;114.22576,30.613493,1336464531000,0;114.225464,30.613523,1336464561000,9;114.225334,30.613703,1336464571000,10;114.225334,30.614014,1336464581000,16;114.22537,30.614384,1336464591000,17;114.22537,30.614794,1336464641000,19;114.225494,30.615414,1336464651000,15;114.22556,30.615883,1336464661000,27;114.225624,30.616703,1336464671000,33;114.225426,30.617292,1336464681000,21;114.22462,30.617474,1336464691000,19;114.224144,30.617502,1336464701000,10;114.22359,30.617523,1336464711000,25;114.22289,30.617582,1336464721000,19;114.221985,30.617672,1336464731000,39;114.2209,30.617905,1336464741000,21;114.22073,30.618044,1336464751000,7;114.22074,30.618284,1336464761000,16;114.22093,30.618864,1336464771000,23;114.221,30.619503,1336464781000,27;114.22115,30.620203,1336464791000,18;114.22109,30.619894,1336464860000,25;114.220955,30.619225,1336464870000,24;114.22086,30.618584,1336464880000,22;114.22072,30.618155,1336464890000,2;114.22071,30.617994,1336464900000,7;114.22067,30.617905,1336464910000,9;114.22016,30.618034,1336464920000,17;114.21979,30.618093,1336464930000,0;114.21951,30.618063,1336464950000,19;114.21884,30.618174,1336464960000,27;114.21804,30.618303,1336464970000,25;114.21724,30.618355,1336464980000,24;114.21645,30.618454,1336464990000,31;114.21526,30.618574,1336465000000,46;114.21405,30.618685,1336465010000,36;114.21316,30.618744,1336465020000,27;114.21229,30.618864,1336465030000,34;114.21122,30.619024,1336465040000,30;114.21011,30.619154,1336465050000,45;114.2089,30.619385,1336465060000,31;114.2088,30.620424,1336465070000,47;114.20879,30.621544,1336465080000,50;114.20878,30.622753,1336465090000,45;114.20876,30.623884,1336465100000,52;114.20871,30.625275,1336465110000,57;114.2087,30.626675,1336465120000,51;114.20855,30.627064,1336465130000,14;114.20852,30.626244,1336465140000,38;114.20804,30.625763,1336465150000,14;114.20788,30.625494,1336465160000,13;114.20783,30.625763,1336465519000,17;114.208,30.625654,1336465678000,4;114.20841,30.625923,1336465688000,26;114.20848,30.626635,1336465698000,31;114.20859,30.626913,1336465708000,16;114.2086,30.626055,1336465718000,42;114.2086,30.624813,1336465728000,50;114.2086,30.623453,1336465738000,54;114.20861,30.621914,1336465748000,63;114.20862,30.620375,1336465758000,57;114.20864,30.619314,1336465768000,25;114.20864,30.618284,1336465778000,51;114.20865,30.617033,1336465788000,52;114.20865,30.615993,1336465798000,23;114.208664,30.615854,1336465878000,9;114.20871,30.615314,1336465888000,30;114.208664,30.614393,1336465898000,39;114.20846,30.613298,1336465908000,33;114.208374,30.612898,1336465918000,15;114.20876,30.612518,1336465928000,31;114.20857,30.611898,1336465938000,37;114.2086,30.610678,1336465948000,42;114.20859,30.609957,1336465958000,37;114.20854,30.609148,1336465968000,12;114.20856,30.609118,1336465985000,0;114.20859,30.609108,1336466127000,7;114.208595,30.608618,1336466137000,29;114.2086,30.607807,1336466147000,25;114.20803,30.607758,1336466157000,38;114.20677,30.608067,1336466167000,47;114.205605,30.608347,1336466177000,27;114.2051,30.608387,1336466217000,19;114.20449,30.607668,1336466227000,46;114.20383,30.606667,1336466237000,46;114.20316,30.605818,1336466247000,38;114.20277,30.605228,1336466257000,20;114.20252,30.604858,1336466267000,21;114.20214,30.604557,1336466277000,12;114.201546,30.604618,1336466287000,33;114.20039,30.605217,1336466297000,45;114.19941,30.60574,1336466307000,49;114.19844,30.60625,1336466317000,41;114.197464,30.60672,1336466327000,36;114.196465,30.60724,1336466337000,46;114.19518,30.60792,1336466347000,54;114.19377,30.60862,1336466357000,56;114.192444,30.60928,1336466367000,52;114.19122,30.60996,1336466377000,47;114.19071,30.61056,1336466387000,26;114.19094,30.61112,1336466397000,14;114.191345,30.612461,1336466414000,36;114.19154,30.61347,1336466424000,49;114.19178,30.61477,1336466434000,46;114.19184,30.61524,1336466444000,11;114.19167,30.61527,1336466454000,4;114.19158,30.61454,1336466464000,45;114.191414,30.61333,1336466474000,46;114.19133,30.61284,1336466478000,47;114.19103,30.6117,1336466488000,48;114.19071,30.61067,1336466498000,31;114.190605,30.61049,1336466588000,13;114.19069,30.60996,1336466598000,25;114.19158,30.6096,1336466607000,32;114.19267,30.60897,1336466617000,48;114.19404,30.60828,1336466627000,58;114.19558,30.60745,1336466637000,64;114.19718,30.60666,1336466647000,59;114.19834,30.60601,1336466657000,18;114.198784,30.60574,1336466687000,41;114.199814,30.605177,1336466697000,60;114.201416,30.604357,1336466707000,54;114.20241,30.603977,1336466717000,27;114.20303,30.603647,1336466727000,25;114.20354,30.603418,1336466737000,5;114.203835,30.603317,1336466747000,26;114.20463,30.602888,1336466757000,34;114.20555,30.602438,1336466767000,35;114.206215,30.602077,1336466777000,30;114.20715,30.601517,1336466787000,48;114.20835,30.600918,1336466797000,48;114.209496,30.600348,1336466807000,19;114.20954,30.600187,1336466837000,12;114.21005,30.599907,1336466847000,22;114.210815,30.599478,1336466857000,29;114.21185,30.598927,1336466867000,35;114.212944,30.598467,1336466877000,36;114.21417,30.597837,1336466887000,60;114.215675,30.597038,1336466897000,49;114.216736,30.596277,1336466907000,41;114.21777,30.595388,1336466917000,58;114.21842,30.594818,1336466927000,21;114.21832,30.593977,1336466937000,49;114.218056,30.593067,1336466947000,12;114.21802,30.592648,1336467006000,16;114.2181,30.592167,1336467016000,27;114.218895,30.591587,1336467026000,30;114.21942,30.591017,1336467036000,37;114.21995,30.589888,1336467046000,49;114.220146,30.588587,1336467056000,46;114.220795,30.587818,1336467066000,39;114.22179,30.587168,1336467076000,40;114.22279,30.586578,1336467086000,48;114.22401,30.585857,1336467096000,46;114.22478,30.585318,1336467106000,24;114.22553,30.584658,1336467116000,47;114.226715,30.583868,1336467126000,49;114.227806,30.583027,1336467136000,53;114.22884,30.582127,1336467146000,46;114.229454,30.581547,1336467156000,14;114.22958,30.581377,1336467166000,16;114.230255,30.580738,1336467176000,44;114.23145,30.579987,1336467186000,52;114.232765,30.579607,1336467196000,45;114.23405,30.579287,1336467206000,49;114.23527,30.579008,1336467216000,46;114.236694,30.578888,1336467226000,46;114.237816,30.578878,1336467236000,38;114.23902,30.578857,1336467246000,39;114.24033,30.578957,1336467256000,54;114.24216,30.579088,1336467266000,69;114.2439,30.579157,1336467276000,48;114.24558,30.579138,1336467286000,68;114.24768,30.578838,1336467296000,73;114.24959,30.578192,1336467306000,68;114.25086,30.57756,1336467316000,31;114.25144,30.577122,1336467326000,45;114.25255,30.57616,1336467336000,63;114.25415,30.57496,1336467346000,79;114.255714,30.574001,1336467356000,40;114.2562,30.573671,1336467366000,9;114.25641,30.57353,1336467386000,30;114.25756,30.572851,1336467396000,54;114.259094,30.57225,1336467406000,50;114.26019,30.57187,1336467416000,37;114.26124,30.57159,1336467426000,40;114.262505,30.571451,1336467436000,42;114.26369,30.571411,1336467446000,42;114.26528,30.571201,1336467456000,59;114.26713,30.570992,1336467466000,68;114.26882,30.57076,1336467476000,55;114.2702,30.57053,1336467486000,32;114.27075,30.57043,1336467496000,22;114.27166,30.570261,1336467506000,31;114.272675,30.570002,1336467515000,45;114.273705,30.569632,1336467525000,30;114.274445,30.5695,1336467535000,32;114.27558,30.569351,1336467545000,40;114.27654,30.569191,1336467555000,31;114.277,30.56909,1336467565000,13;114.27782,30.568472,1336467575000,38;114.27885,30.568312,1336467585000,24;114.27962,30.568193,1336467595000,31;114.27997,30.568153,1336467605000,0;114.27997,30.568142,1336467635000,5;114.28034,30.568113,1336467645000,19;114.28102,30.568052,1336467655000,20;114.28176,30.567972,1336467665000,23;114.28231,30.567923,1336467675000,21;114.28274,30.567873,1336467685000,12;114.28318,30.567812,1336467695000,11;114.28336,30.567793,1336467705000,10;114.28363,30.567772,1336467715000,2;114.28387,30.567753,1336467725000,12;114.28418,30.567722,1336467735000,10;114.28478,30.567663,1336467745000,14;114.28504,30.567633,1336467755000,8;114.28536,30.567623,1336467765000,10;114.28542,30.567732,1336467835000,14;114.28492,30.567762,1336467845000,4;114.28457,30.567783,1336467855000,16;114.28397,30.567823,1336467865000,17;114.28352,30.567842,1336467875000,16;114.28317,30.567892,1336467885000,5;114.28302,30.567883,1336467895000,11;114.282684,30.567923,1336467905000,14;114.28229,30.568003,1336467915000,12;114.28188,30.568073,1336467924000,24;114.28094,30.568172,1336467934000,27;114.28037,30.568213,1336467944000,8;";
   
	document.getElementById("spanMsg").innerHTML = "";
	document.getElementById("btnPlay").disabled = false;

    if (res != "") {
    	
        var json = eval("(" + res + ")");
        //alert(json);
        queryStartDate = json.lastDeviceUtcDate;

        LocationID = parseInt(json.lastLocationID);

        if (LocationID == lastLocationID) {
            clearInterval(forTime);
            document.getElementById("btnPause").disabled = true;
            document.getElementById("btnNext").disabled = true;
            document.getElementById("btnPlay").disabled = false;
            alert(playbackPage.playOver);
            return;
        }
        lastLocationID = LocationID;

        allLocation = json.devices;

    }

    if (allLocation.length == 0) {
        if (isSerachFirst) {
            alert("没找到");
        } else {
            clearInterval(forTime);
            document.getElementById("btnPlay").disabled = false;
            alert("播放完毕");
        }
    }
    isSerachFirst = false;
    if (allLocation.length > 0) {
        document.getElementById("btnPause").disabled = true;
        document.getElementById("btnNext").disabled = false;
        document.getElementById("btnPlay").disabled = true;
        lastAllLocationLength = allLocation.length;
        showHistoryAllMap();
    }
}

function changePlay(t) {
    if (t == 0) {
        //继续
        showHistoryMap();
        forTime = setInterval(showHistoryMap, forTimer);
        document.getElementById("btnPause").disabled = true;
        document.getElementById("btnNext").disabled = false;
        document.getElementById("btnPlay").disabled = true;
    } else if (t == 1) {
        //暂停
        clearInterval(forTime);
        document.getElementById("btnPause").disabled = false;
        document.getElementById("btnNext").disabled = true;
        document.getElementById("btnPlay").disabled = true;
    } else if (t == 2) {

    }
}

function DateDiff(start, end)  //返回分钟
{
    start = start.replace(/-/g, '/');
    end = end.replace(/-/g, '/');
    var a = new Date(start);
    a = a.getTime();
    var b = new Date(end);
    b = b.getTime();
    var ticksspan = b - a;
    return ticksspan / 60 / 1000;
}


//分钟转换到天小时分钟格式
function minuteToStr(minute) {

    var time = "";

    var day = parseInt(minute / 60 / 24);
    var hour = parseInt((minute / 60) - (day * 24));
    var minu = (minute) - (day * 24 * 60) - (hour * 60);
    if (day > 0) {
        time = day + allPage.day;
        time += hour + allPage.hour;
        time += minu + allPage.minute;
    } else if (hour > 0) {
        time = hour + allPage.hour;
        time += minu + allPage.minute;
    } else {
        time = minu + allPage.minute;
    }

    return time;

}


function GetPopupHtml(d, lat, lng) {

    var html = [];
    html.push("<b>" + DeviceName + "</b><br />");
    html.push(d.deviceUtcDate + "<br />");
    html.push(allPage.lat + ":" + lat + ",");
    html.push(allPage.lng + ":" + lng + "<br />");
    var courseName = GetCoureName(d.course);
    var stopTime = minuteToStr(d.stopTimeMinute);
    if (d.IsStop == 1) {
        html.push(allPage.stopTime + ":" + stopTime + "<br />");
       /* if((d.tempData!="" && d.tempData!=null) || (d.rhData!=""&&d.rhData!=null) )
		{
        	var tempData = (d.tempData==""?"-":d.tempData);
        	var rhData = (d.rhData==""?"-":d.rhData);
		 html.push("温度：" +tempData+"℃ &nbsp;&nbsp; 湿度："+rhData+"%RH" + "<br />");
		}*/
    } else {
        html.push(allPage.drection + ":" + courseName + ",");
        html.push(allPage.speed + ":" + d.speed + "" + allPage.speedKM + "<br />");
       /* if((d.tempData!="" && d.tempData!=null) || (d.rhData!=""&&d.rhData!=null) )
		{
        	var tempData = (d.tempData==""?"-":d.tempData);
        	var rhData = (d.rhData==""?"-":d.rhData);
		 html.push("温度：" +tempData+"℃ &nbsp;&nbsp; 湿度："+rhData+"%RH" + "<br />");
		}*/
    }
    html.push('&nbsp;&nbsp;');
    return html.join('');
}

function GetClkInfo(d, lat, lng) {

    var html = [];
    html.push("<b>" + DeviceName + "</b><br />");
    html.push(d.deviceUtcDate + "<br />");
    html.push(allPage.lat + ":" + lat + ",");
    html.push(allPage.lng + ":" + lng + "<br />");
    var stopTime = minuteToStr(d.stopTimeMinute);
    if (d.IsStop == 1) {
        html.push(allPage.startStopTime + ":" + d.deviceUtcDate + "<br />");
        html.push(allPage.endStopTime + ":" + d.serverUtcTime + "<br />");
        html.push(allPage.stopTime + ":" + stopTime+ "<br />");
      /*  if((d.tempData!="" && d.tempData!=null) || (d.rhData!=""&&d.rhData!=null) )
		{
        	var tempData = (d.tempData==""?"-":d.tempData);
        	var rhData = (d.rhData==""?"-":d.rhData);
		 html.push("温度：" +tempData+"℃ &nbsp;&nbsp; 湿度："+rhData+"%RH" + "<br />");
		}*/

    } else {
        var courseName = GetCoureName(d.course);
        html.push(allPage.drection + ":" + courseName + "," + allPage.speed + ":" + d.speed + allPage.speedKM +"<br/>");
      /*  if((d.tempData!="" && d.tempData!=null) || (d.rhData!=""&&d.rhData!=null) )
		{
        	var tempData = (d.tempData==""?"-":d.tempData);
        	var rhData = (d.rhData==""?"-":d.rhData);
		 html.push("<br />温度：" +tempData+"℃ &nbsp;&nbsp; 湿度："+rhData+"%RH" + "<br />");
		}*/
    }
    return html.join('');

}