package com.ycnet.core.util;

import java.math.BigDecimal;

import com.ycnet.frms.util.GoogleToGPSUtil;

public class Map_Util {
	
	/** 
	 * 各地图API坐标系统比较与转换; 
	 * WGS84坐标系：即地球坐标系，国际上通用的坐标系。设备一般包含GPS芯片或者北斗芯片获取的经纬度为WGS84地理坐标系, 
	 * 谷歌地图采用的是WGS84地理坐标系（中国范围除外）; 
	 * GCJ02坐标系：即火星坐标系，是由中国国家测绘局制订的地理信息系统的坐标系统。由WGS84坐标系经加密后的坐标系。 
	 * 谷歌中国地图和搜搜中国地图采用的是GCJ02地理坐标系; BD09坐标系：即百度坐标系，GCJ02坐标系经加密后的坐标系; 
	 * 搜狗坐标系、图吧坐标系等，估计也是在GCJ02基础上加密而成的。  
	 */ 
	
	 public static final String BAIDU_LBS_TYPE = "bd09ll";  
     
	    public static double pi = 3.1415926535897932384626;  
	    public static double a = 6378245.0;  
	    public static double ee = 0.00669342162296594323; 
	    
	    public static LatLng gps84_To_Gcj02(double lat, double lon) {  
	        if (outOfChina(lat, lon)) {  
	            return null;  
	        }  
	        double dLat = transformLat(lon - 105.0, lat - 35.0);  
	        double dLon = transformLon(lon - 105.0, lat - 35.0);  
	        double radLat = lat / 180.0 * pi;  
	        double magic = Math.sin(radLat);  
	        magic = 1 - ee * magic * magic;  
	        double sqrtMagic = Math.sqrt(magic);  
	        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);  
	        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);  
	        double mgLat = lat + dLat;  
	        double mgLon = lon + dLon;  
	        return new LatLng(mgLat, mgLon);  
	    }  
	  
	    /** 
	     * * 火星坐标系 (GCJ-02) to 84 * * @param lon * @param lat * @return 
	     * */  
	    public static LatLng gcj_To_Gps84(double lat, double lon) {  
	    	LatLng gps = transform(lat, lon);  
	        double lontitude = lon * 2 - gps.getLongitude();  
	        double latitude = lat * 2 - gps.getLatitude();  
	        return new LatLng(latitude, lontitude);  
	    }  
	  
	    /** 
	     * 火星坐标系 (GCJ-02) 与百度坐标系 (BD-09) 的转换算法 将 GCJ-02 坐标转换成 BD-09 坐标 
	     *  
	     * @param gg_lat 
	     * @param gg_lon 
	     */  
	    public static LatLng gcj02_To_Bd09(double gg_lat, double gg_lon) {  
	        double x = gg_lon, y = gg_lat;  
	        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * pi);  
	        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * pi);  
	        double bd_lon = z * Math.cos(theta) + 0.0065;  
	        double bd_lat = z * Math.sin(theta) + 0.006;  
	        return new LatLng(bd_lat, bd_lon);  
	    }  
	  
	    /** 
	     * * 火星坐标系 (GCJ-02) 与百度坐标系 (BD-09) 的转换算法 * * 将 BD-09 坐标转换成GCJ-02 坐标 * * @param 
	     * bd_lat * @param bd_lon * @return 
	     */  
	    public static LatLng bd09_To_Gcj02(double bd_lat, double bd_lon) {  
	        double x = bd_lon - 0.0065, y = bd_lat - 0.006;  
	        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * pi);  
	        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * pi);  
	        double gg_lon = z * Math.cos(theta);  
	        double gg_lat = z * Math.sin(theta);  
	        return new LatLng(gg_lat, gg_lon);  
	    }  
	  
	    /** 
	     * (BD-09)-->84 
	     * @param bd_lat 
	     * @param bd_lon 
	     * @return 
	     */  
	    public static LatLng bd09_To_Gps84(double bd_lat, double bd_lon) {  
	  
	    	LatLng gcj02 = Map_Util.bd09_To_Gcj02(bd_lat, bd_lon);  
	    	LatLng map84 = Map_Util.gcj_To_Gps84(gcj02.getLatitude(),  
	                gcj02.getLongitude());  
	        return map84;  
	  
	    }  
	    
	    public static LatLng Gps84_To_bd09(double bd_lat, double bd_lon) {  
	  	  
	    	LatLng gcj02 = Map_Util.gps84_To_Gcj02(bd_lat, bd_lon);  
	    	LatLng bd09 = Map_Util.gcj02_To_Bd09(gcj02.getLatitude(),  
	                gcj02.getLongitude());  
	        return bd09;  
	  
	    }  
	    
	    
	  
	    public static boolean outOfChina(double lat, double lon) {  
	        if (lon < 72.004 || lon > 137.8347)  
	            return true;  
	        if (lat < 0.8293 || lat > 55.8271)  
	            return true;  
	        return false;  
	    }  
	  
	    public static LatLng transform(double lat, double lon) {  
	        if (outOfChina(lat, lon)) {  
	            return new LatLng(lat, lon);  
	        }  
	        double dLat = transformLat(lon - 105.0, lat - 35.0);  
	        double dLon = transformLon(lon - 105.0, lat - 35.0);  
	        double radLat = lat / 180.0 * pi;  
	        double magic = Math.sin(radLat);  
	        magic = 1 - ee * magic * magic;  
	        double sqrtMagic = Math.sqrt(magic);  
	        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);  
	        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);  
	        double mgLat = lat + dLat;  
	        double mgLon = lon + dLon;  
	        return new LatLng(mgLat, mgLon);  
	    }  
	  
	    public static double transformLat(double x, double y) {  
	        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y  
	                + 0.2 * Math.sqrt(Math.abs(x));  
	        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;  
	        ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;  
	        ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;  
	        return ret;  
	    }  
	  
	    public static double transformLon(double x, double y) {  
	        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1  
	                * Math.sqrt(Math.abs(x));  
	        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;  
	        ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;  
	        ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0  
	                * pi)) * 2.0 / 3.0;  
	        return ret;  
	    }  
	    
	    public static void main(String[] args) {
	    	//126.687244481,45.7331040310
//	    	LatLng gc = gps84_To_Gcj02(45.7331040310,126.687244481);
//	    	LatLng bd = gcj02_To_Bd09(gc.getLatitude(),gc.getLongitude());
	    	String la="45.7331073744";
	    	String lon="126.687206355";
	    	LatLng LatLng = Map_Util.Gps84_To_bd09(Double.valueOf(la), Double.valueOf(lon));
//	    	System.out.println(Double.valueOf(LatLng.getLatitude())+","+Double.valueOf(lon));
	    	System.out.println(BigDecimal.valueOf(LatLng.getLatitude())+","+BigDecimal.valueOf(LatLng.getLongitude()));
	    	
	    	System.out.println(LatLng.getLatitude()+","+LatLng.getLongitude());
//	    	LatLng bd = Gps84_To_bd09(45.7331040310,126.687244481);
//	    	System.out.println(bd.getLatitude()+","+bd.getLongitude());
	    	
//	    	double s = GoogleToGPSUtil.distance(23.1170966906,114.416732977, bd.getLatitude(), bd.getLongitude());
	    	
//	    	System.out.println(s);
		}

}
