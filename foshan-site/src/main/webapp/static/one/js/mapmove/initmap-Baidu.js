var oLat = 29.502;
var oLng = 118.932;
var oZoom = 6;

function initBaiduMap(lat, lng, zoom) {
    //初始化地图
    if (lat == "" || lat == 0) {
        lat = oLat;
        lng = oLng;
    }
    if (zoom == 0) {
        zoom = oZoom;
    }

    map = new BMap.Map("map_canvas");           // 创建Map实例
    var point = new BMap.Point(lng, lat);       // 创建点坐标
    map.centerAndZoom(point, zoom);             // 初始化地图,设置中心点坐标和地图级别。

    map.addControl(new BMap.NavigationControl());               // 添加平移缩放控件
    map.addControl(new BMap.ScaleControl());                    // 添加比例尺控件
    map.addControl(new BMap.OverviewMapControl());              //添加缩略地图控件
    map.enableScrollWheelZoom();    //激活滚轮
    map.enableContinuousZoom();     //连续平滑
    map.enableInertialDragging();   //开启惯性拖拽效果
    map.setMinZoom(5); 
    //map.addControl(new BMap.MapTypeControl()); //地图,卫星,三维
    map.addControl(new BMap.MapTypeControl({ mapTypes: [BMAP_NORMAL_MAP, BMAP_HYBRID_MAP] }));
}
