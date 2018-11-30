//普通:绿色,超速:红色,拉直线:蓝色
var map = null;
var popupmarkerWidth = 220;
var popupmarkerHeight = 70;
var zoomLev = 15;   //地图标尺500米

function initmap() {
    initBaiduMap(0, 0, 0);
}



function deleteOverLays(allOverlays) {
    if (map) {
        map.clearOverlays();
    }
    allOverlays.length = 0;
}

//提前绘制所有线路
function showHistoryAllMap() {

    var ArrayPolyPoints = new Array();
    var dashedPolyPoints = new Array();
    var lastDraw = true;
    for (var i = 0; i < allLocation.length; i++) {
        var isAdd = true;
        var latlng = new BMap.Point(allLocation[i].baiduLng, allLocation[i].baiduLat);
        var isDraw = true;
        if (i > 0) {
            isDraw = isGPSPolyline(allLocation[i - 1], allLocation[i], "Baidu");
        }

        if (!isDraw) {
            if (ArrayPolyPoints.length > 1) {
                var mypoly = new BMap.Polyline(ArrayPolyPoints, { strokeColor: "#00FF33", strokeWeight: 5, strokeOpacity: 1 });
                map.addOverlay(mypoly);
                allPolyline.push(mypoly);

                polyDashed(dashedPolyPoints);
                dashedPolyPoints.length = 0;
                var firstDashedLatLng = ArrayPolyPoints[ArrayPolyPoints.length - 1];
                dashedPolyPoints.push(firstDashedLatLng);
            }
            ArrayPolyPoints = [];
            isAdd = false;
        }

        //虚线
        if (!isDraw) {
            dashedPolyPoints.push(latlng);
        } else {
            if (i > 0) {
                polyDashed(dashedPolyPoints);
                if (lastDraw != isDraw) {
                    ArrayPolyPoints.length = 0;
                    if (dashedPolyPoints.length > 1) {
                        var firstSolidLatLng = dashedPolyPoints[dashedPolyPoints.length - 1];
                        ArrayPolyPoints.push(firstSolidLatLng);
                    }
                }
                dashedPolyPoints.length = 0;
            } else {
                dashedPolyPoints.push(latlng);
            }
        }
        lastDraw = isDraw;
        if (isAdd) {
            ArrayPolyPoints.push(latlng);
        }

    }
    if (ArrayPolyPoints.length > 1) {
        var mypoly = new BMap.Polyline(ArrayPolyPoints, { strokeColor: "#00FF33", strokeWeight: 6, strokeOpacity: 1 });
        map.addOverlay(mypoly);
        allPolyline.push(mypoly);
    }
    if (dashedPolyPoints.length > 0) {
        polyDashed(dashedPolyPoints);
        dashedPolyPoints.length = 0;
    }
    showHistoryMap();
    forTime = setInterval(showHistoryMap, forTimer);
}

//画虚线
function polyDashed(dashedPolyPoints) {
    if (dashedPolyPoints.length > 1) {
        var dashedPoly = new BMap.Polyline(dashedPolyPoints, { strokeColor: "#3333FF", strokeWeight: 5, strokeOpacity: 1 });
        dashedPoly.setStrokeStyle("dashed");
        map.addOverlay(dashedPoly);
        allPolyline.push(dashedPoly);
    }
}

var endIndex = 0;
var isDrawStart = false; //是否需要重新描起始点
function showHistoryMap() {
    //map操作
    var d = allLocation[index];
    //alert();
    if (d) {
        var latlng = new BMap.Point(d.baiduLng, d.baiduLat);
        if (isSetZoom) {
            map.centerAndZoom(latlng, zoomLev);
            isSetZoom = false;
        } else {
            var LatLngBounds = map.getBounds();
            var isMap = LatLngBounds.containsPoint(latlng);
            if (!isMap) {
                //panTo好像无效
                map.setCenter(latlng);
            }
        }
        /*
       lastLatlng.addEventListener("click", function(e){
    	   alert("ok");
    	   var marker = new BMap.Marker(lastLatlng);
    	   
       }); 
        */
       if( d.tempData== 1 )
		{

				var html = GetMarkerInfo(d);
                var lastLatlng = new BMap.Point(d.baiduLng, d.baiduLat);
			   var marker = new BMap.Marker(lastLatlng); 
			   map.addOverlay(marker);                     // 将标注添加到地图中
                addBaiduClkListener(marker, allLocation[index]);
                allMarker.push(marker);
		}
        
        
        if (lastMarker) {
            var html = GetMarkerInfo(d);
            var obj = { "position": latlng, "text": html };
            lastMarker.update(obj);
            endIndex = index; //有可能连续几个直线
            //停止显示地标
            if (allLocation[index - 1].IsStop == 1 && allLocation[index - 1].stopTimeMinute >= 10) {
                var lastLatlng = new BMap.Point(allLocation[index - 1].baiduLng, allLocation[index - 1].baiduLat);
                var stopIcon = "../images/icons/stoptr.png";
                var sIcon = new BMap.Icon(stopIcon, new BMap.Size(20, 38));
                var stopMarker = new BMap.Marker(lastLatlng, { icon: sIcon, title: allLocation[index - 1].deviceUtcDate });
                stopMarker.setOffset(new BMap.Size(0, -10))
                map.addOverlay(stopMarker);
                addBaiduClkListener(stopMarker, allLocation[index - 1]);
                allMarker.push(stopMarker)
            }

        }
        if (!isFirstShowHistory && !isDrawStart) {
            //超速
            if (speedLimit > 0) {
                if (d.speed > speedLimit) {
                    var lastLatlng = new BMap.Point(allLocation[index - 1].baiduLng, allLocation[index - 1].baiduLat);
                    var ArrayPolyPoints = new Array();
                    ArrayPolyPoints.push(lastLatlng);
                    ArrayPolyPoints.push(latlng);
                    var mypoly = new BMap.Polyline(ArrayPolyPoints, { strokeColor: "#FF0000", strokeWeight: 6, strokeOpacity: 1 });
                    map.addOverlay(mypoly);
                    allPolyline.push(mypoly);
                }
            }

        } else {
            if (isFirstShowHistory) {
                var html = GetMarkerInfo(d);
                var icon = "../images/icons/green.gif";
                var icon2 = "images/null.gif";
                lastMarker = new PopupMarker({ position: latlng, map: map, icon: icon, text: html, showpop: true });
                allMarker.push(lastMarker);
            }
            var stIcon = "../images/icons/start.png";
            var startIcon = new BMap.Icon(stIcon, new BMap.Size(20, 38));
            var firstMarker = new BMap.Marker(latlng, { icon: startIcon, title: d.deviceUtcDate });
            firstMarker.setOffset(new BMap.Size(0, -10))
            map.addOverlay(firstMarker);
            addBaiduClkListener(firstMarker, d);
            allMarker.push(firstMarker)
        }
        isDrawStart = false;

        index++;
        isFirstShowHistory = false;
        if (index >= allLocation.length) {
            clearInterval(forTime);
            //判断是否读取完数据
            if (queryStartDate != "") {
                var diff = DateDiff(queryStartDate, queryEndDate);
                if (diff > 2) {

                    WebGetHistory();
                } else {
                    document.getElementById("btnPause").disabled = true;
                    document.getElementById("btnNext").disabled = true;
                    document.getElementById("btnPlay").disabled = false;
                    alert(playbackPage.playOver);
                }
            } else {
                document.getElementById("btnPause").disabled = true;
                document.getElementById("btnNext").disabled = true;
                document.getElementById("btnPlay").disabled = false;
                alert(playbackPage.playOver);
            }
        } else {

        }
    } else {
        if (forTime) {
            clearInterval(forTime);
        }
    }

}

var inforwindowArr = [];
function addBaiduClkListener(marker, d) {
    try {

        marker.addEventListener("click", function () {
        	 var html = GetClkInfo(d, d.baiduLat, d.baiduLng);
        	var devGroupCode = $("#devGroupCode").val();
        	$.ajax({
    			url:"../../mobile/searchMarkerDataList.action",
    			type: "post",          //使用post方法访问后台  
    			dataType: "json",      //返回json格式的数据  
    			data:{
        			"devGroupCode" : devGroupCode,
        			"txtStartDate" : d.deviceUtcDate
        		},
    			success: function(data){
        			//html.push(data.markerData);
        			html = html + data.markerData;
        			var infoWindow = new BMap.InfoWindow(html);
                	marker.openInfoWindow(infoWindow);
        		}	
        	});
    	  	
        });
    } catch (ex) { }

}
function closeWindow() {
    for (var i = 0; i < inforwindowArr.length; i++) {
        inforwindowArr[i].close();
    }
    inforwindowArr.length = 0;
}

function GetMarkerInfo(d) {
    return GetPopupHtml(d, d.baiduLat, d.baiduLng);
}
