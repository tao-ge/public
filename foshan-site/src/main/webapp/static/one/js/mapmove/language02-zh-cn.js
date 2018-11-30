function writePage(msg) {
    document.write(msg);
}

var allPage = { tab1: "运行总览", tab2: "报警总览", tab3: "运行统计", tab4: "报警统计", startTime: "开始时间", endTime: "结束时间", search: "查 询", num: "序号", deviceName: "设备名",
    time: "时间", distance: "里程(公里)", overspeed: "超速", noData: "没有查询到数据", lat: "纬度", lng: "经度", speed: "速度", address: "地址", speedKM: "公里/小时", day: "天", hour: "小时",
    minute: "分钟", pleSel: "请选择", date: "日期", plsDeviceMsg: "请选择设备", address: "地点", moreDevice: "更多设备", msg: "消息提醒", myAccount: "我的账号", changePass: "修改密码",
    tracking: "实时跟踪", playback: "历史轨迹", monitor: "监控", home: "主页", customer: "客户", report: "统计", more: "更多", all: "全部", no: "序号", name: "名称", carNum: "车牌号",
    imeiNo: "IMEI号", activeTime: "激活时间", hireExpireTime: "到期时间", operation: "操作", noData: "没有查询到数据", edit: "修改", divicesInfo: "设备信息", cellName: "联系人",
    phone: "电话", timezone: "时区", save: "保存", confirm: "确定", updateUserSuccess: "修改资料成功!", updateUserFailed: "修改资料失败!", modelName: "型号", state: "状态", position: "定位",
    drection: "方向", baidu: "百度", google: "谷歌", day: "天", hour: "小时", minute: "分钟", stopTime: "停留时间", desc: "备注", cancel: "取消", del: "删除", delSuccess: "删除成功!",
    delFaild: "删除失败!", accStr: "ACC状态", acc0: "关", acc1: "开", positionType: "定位方式", manDevice: "设备管理", type: "类型", acc2: "未接", resolve: "解析", startStopTime: "停留开始",
    endStopTime: "停留结束", status1: "未启用", moving: "行驶", stopCar: "静止", offline: "离线", arrears: "欠费", primaryEmail: "联系邮箱", positionTime: "定位时间", clear: "清除", targetName: "设备名",
    obd: "OBD诊断", photo: "拍照", toExcel: "导出Excel",pageShowSize:"每页显示",pageUnit:"条", powerStr: "电量"
};

var courseName = { dueNorth: "正北", northeast: "东北", dueEast: "正东", southeast: "东南", dueSouth: "正南", southwest: "西南", dueWest: "正西", northwest: "西北" };

var reportPage = { title: "运行统计总览", warnCount: "报警", stopCount: "停留" };

var alarmSumPage = { title: "报警统计总览", lowCount: "低电报警", cutPowerCount: "断电报警", vibCount: "震动报警", sosCount: "求救报警" }

var overSpeedPage = { continueTime: "持续时间", speedlimit: "超速值", distancePage: "里程统计", overspeedDetail: "超速详单", stopDetail: "停留详单" };

var alarmIndexPage = { geofenceIn: "进电子栅栏", geofenceOut: "出电子栅栏", moved: "位移报警", lowBattery: "低电报警", sos: "求救报警", cutPower: "断电报警", vibration: "震动报警",
    overSpeed: "超速报警", inputPort1: "输入报警1", inputPort2: "输入报警2", inputPort3: "输入报警3"
};

var runindexPage = { statistics: "统计方式", statistics2: "按天统计", oilCoefficient: "百公里油耗系数", L: "升", oil: "油耗" };

var alarmDetailPage = { alarmTime: "报警时间", alarmType: "报警类型", alarmCount: "报警统计", alarmDetail: "报警详单" };

var userPage = { warnTitle: "系统报警信息提醒", warnSound: "开启报警声音", day7Exp: "7天内过期设备", day60Exp: "60天内过期设备", alreadyExp: "已过期设备",
    username: "客户名/账号", hello: "您好", searchDevice: "搜设备", searchUser: "搜客户", exit: "退出", message: "消息", allDeivce: "全部设备",
    help: "帮助"
};

var userInfoPage = { myAccount: "我的账号", changePassword: "修改密码", userMsg: "请完善以下信息，比如联系电话、联系人（若包含该项）", customerName: "客户名称",
    account: "登陆账号", oldPass: "旧密码", newPass: "新密码", confirmPass: "确认密码", passLengthMsg: "密码长度不得大于20个字符", passNull: "密码输入不能为空!",
    passError: "2次密码输入不一致!", changePassSuccess: "修改密码成功!", changePassError: "修改密码失败!", oldPassError: "旧密码不对!", warnSendMsg: "报警附加通知方式",
    sendEmail: "邮件通知", service: "服务商"
};

var warnMessagePage = { warnMsg: "报警消息", handle1: "未处理", handle2: "已处理", alarmType: "报警类型", alarmTime: "报警时间" };

var trackingPage = { secondMsg: "秒后刷新!" };

var playbackPage = { from: "时间从:", to: "到:", play: "播 放", pause: "暂 停", next: "继 续", fast: "快", slow: "慢", timeMsg: "结束时间大于开始时间!", nowLoading: "正在加载数据!",
    playOver: "播放完毕!", searchNull: "没搜索到数据!", playbackDesc: "回放说明"
};

var geofencesPage = { geofence: "电子栅栏", addGeofence: "新增电子栅栏", geoNameNull: "电子栅栏名称不能为空!", radius: "半径(米)", delGeoConfirm: "确认删除", delGeoConfirm2: "这个电子栅栏吗?", 
    devicesGeofence: "电子栅栏", addDevicesGeofence: "新增设备电子栅栏", geoExists: "只能对设备设置一个电子栅栏!" };

var iframeMapPage = { baiduMap: "百度地图", googleMap: "谷歌地图", deviceName: "设备名称",fullScreenMap:"全屏地图" };

var userUpdatePage = { account: "登录名" };

var ObdPage = { num: "序号", travelRecords: "行程记录", carStatus: "车辆状态", faultReport: "故障报告", fireSnapshot: "点火快照", travelTitle: "出行列表", carStatus: "车辆状态",
    faultReport: "故障记录", fireSnapshot: "点火快照", begintime: "开始时间", endtime: "结束时间", travelTime: "行程时间", distance: "行驶里程[公里]", totalDistance: "总里程(Km)",
    oil: "耗油[升]", oilCoefficient: "平均油耗(L)", hundredFuelLv: "平均油耗[升/百公里]", avgSpeed: "平均速度[千米/小时]", control: "操作",
    fuelCosts: "油费[元]", playback: "轨迹回放", time: "时间", vss: "车速", rpm: "转速", ect: "水温", fuellv: "燃油量", runtime: "发动机启动时间", vpwr: "电池电压", loading: "发动机负荷",
    fuel: "耗油量", distance: "里程"
};

var PhotoPage = { num: "序号", deviceName: "设备", status: "状态", time: "时间", cameraNo: "编号", picture: "图片", sendPhoto: "下发拍照", cameraName: "摄像头名称", Operating: "操作",
    selecePicture: "查看照片", camera1: "摄像头一", camera2: "摄像头二", camera3: "摄像头三", camera4: "摄像头四", photoNow: "拍照", cancelNow: "取消", requerCamera: "未选择摄像头", status: "状态",
    status0: "摄像头不存在", status1: "待机", status2: "拍照成功", status3: "正在取得相片数据", status4: "正在传送照片", isOffline: "定时拍照"
};

//map.aspx
var mapPage = { divicesInfo: "设备信息", cutOffPetrol: "远程断油电", restorePetrol: "远程恢复油电", checkLocation: "查询定位", checkCommand: "查询指令记录",
    sendMsg1: "正在发送指令,请稍后...", sendSuccess: "命令已经成功下发,等待设备响应...",
    sendMsg2: "命令无效", sendMsg3: "设备不存在", sendMsg4: "设备与服务器已经断开连接", sendMsg5: "下发不成功", sendMsg6: "定时指令已下发",
    responseSuccess: "设备成功返回!", responseNull: "还没有获取到设备返回数据!"
};

var downloadLocation = { download: "下 载", help: "帮助", step: "步骤", step1: "1.选择需要下载的指定设备。", step2: "2.输入需要下载的具体日期。", step3: "3.单击“下载KML轨迹文件”按钮。",
    msg1: "注意：如果单击“下载”按钮， 出现“没有找到有效的轨迹数据!”提示信息，表示当前没有你需要下载的数据。", msg2: "下载的轨迹文件的格式是Google KML格式，如:“文件名.kml”。",
    msg3: "安装 Google Earth后。双击KML文件，会通过Google Earth工具打开。", msg4: "KML轨迹文件会将设备的移动痕迹以红线描绘在Google地图上。", msg5: "»:下载 Google Earth 请点击", here: "这里",
    msg6: "您可以通过“下载轨迹数据”功能，下载指定设备某一日期内的移动痕迹。"
};

var deviceStatusPage = { deviceStatusTotal: "设备状态总览", tab5: "设备状态", lastUpdateTime: "最后更新时间", update: "同步", num: "序号", devicesModel: "设备型号",
    onLine: "在线(台)", offLine: "离线(台)", total: "合计", exportExcel: "导出"
};
//2012-11-23 贾恒 下载历史轨迹
var downloadPage = { selectDevice: "选择设备", select: "--选择--", dateTimeMax: "轨迹日期不能大于今天！", selectTarget: "请选择设备!", htDateTime: "轨迹日期", downloadBotton: "下 载",
 downMsg: '<div id="downloadkml"><h1>帮助</h1><div class="info">您可以通过\“下载轨迹”功能，下载指定设备某一日期内的移动轨迹数据。</div><div class="notice">注意：如果单击“下载”按钮，'+
'出现\“没有找到有效的轨迹数据!\”提示信息，<br />表示当前没有你需要下载的数据。</div><div class="google">下载的轨迹文件的格式是Google KML格式，如:\“文件名.kml\”。<br />安装 Google Earth后。'+
'双击KML文件，会通过Google Earth工具打开。<br />所下载的"KML轨迹文件"会将设备的移动痕迹以红线动态的描绘在Google地图上。'+
'<em><a href=\"http://dl.google.com/earth/client/ge4/release_4_3/googleearth-win-plus-4.3.7284.3916.exe\">&#187;下载 Google Earth</a></em></div></div>',
noData:"没有找到有效的轨迹数据!",downLoad:"下载轨迹"};
