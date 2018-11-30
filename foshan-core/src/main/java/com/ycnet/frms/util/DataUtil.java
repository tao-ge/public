package com.ycnet.frms.util;

import com.ycnet.core.DateState;

public class DataUtil { 
	
	/**
	 * 经度检查
	* @Title: CheckLongitude 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param Longitude
	* @param @return    入参
	* @return String    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2017年12月4日 下午1:27:27 
	* @version V1.0
	 */
	public static Double CheckLongitude(String Longitude) {
		Double longitude = null;
		if(Longitude==null || "".equals(Longitude.trim())) {
			return null;
		}else {
			longitude = Double.parseDouble(Longitude);
			if(longitude > 130 || longitude < 100) {
				return null;
			}
		}
		return longitude;
	}
	
	/**
	 * 纬度检查
	* @Title: CheckLatitude 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param Latitude
	* @param @return    入参
	* @return String    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2017年12月4日 下午1:24:58 
	* @version V1.0
	 */
	public static Double CheckLatitude(String Latitude) {
		Double latitude = null;
		if(Latitude==null || "".equals(Latitude.trim())) {
			return null;
		}else {
			latitude = Double.parseDouble(Latitude);
			if(latitude < 20 || latitude > 30) {
				return null;
			}
		}
		return latitude;
	}
	
//
//	public static String Googl_to_GPS(String Longitude) {
//		if(Longitude==null || "".equals(Longitude.trim())) {
//			return "";
//		}else {
//			Double longitude = Double.parseDouble(Longitude);
//			if(longitude > 130 || longitude < 100) {
//				return "";
//			}
//		}
//		return Longitude;
//	}
//	
//
//	public static String Googl_to_BD(String Latitude) {
//		if(Latitude==null || "".equals(Latitude.trim())) {
//			return "";
//		}else {
//			Double latitude = Double.parseDouble(Latitude);
//			if(latitude < 20 || latitude > 30) {
//				return "";
//			}else {
//				
//			}
//		}
//		return Latitude;
//	}
	
	/**
	 * 校验纤芯序号
	* @Title: CheckFiberNum 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param fiberNum
	* @param @return    入参
	* @return Long    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2017年12月4日 下午2:49:15 
	* @version V1.0
	 */
	public static Long CheckFiberNum(String fiberNum) {
		if(fiberNum==null || "".equals(fiberNum.trim())) {
			return 0L;
		}
		if(fiberNum.contains(".")) {
			fiberNum = fiberNum.substring(0, fiberNum.indexOf("."));
		}
		return Long.parseLong(fiberNum);
	}

	/**
	 * 设施光缆状态修改工具类
	* @Title: DevStateByCode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param code
	* @param @return    入参
	* @return String    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2017年12月1日 上午11:33:24 
	* @version V1.0
	 */
	public static String DevStateByCode(String code) {
		if(DateState.NEWLY.toString().equals(code)) {
			return "5";
		}else {
			return "4";
		}
	}
	
	/**
	 * 导入数据判断
	* @Title: WellByCode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param code
	* @param @return    入参
	* @return String    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2017年12月1日 上午11:31:15 
	* @version V1.0
	 */
	public static String WellByCode(String code) {
		if("孤立".equals(code)) {
			return "1";
		}else if("直通".equals(code)) {
			return "2";
		}else if("三通".equals(code)) {
			return "3";
		}else if("四通".equals(code)) {
			return "4";
		}else if("五通".equals(code)) {
			return "5";
		}else if("六通".equals(code)) {
			return "6";
		}else if("六通以上".equals(code)) {
			return "7";
		}else {
			return "7";
		}
	}
	
	/**
	 * 产权编码
	* @Title: wellProrightType 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param code
	* @param @return    入参
	* @return String    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年1月30日 下午4:51:28 
	* @version V1.0
	 */
	public static String wellProrightType(String code) {
		if("自建".equals(code)) {
			return "01";
		}else if("共建".equals(code)) {
			return "02";
		}else if("租用".equals(code)) {
			return "03";
		}else {
			return "04";
		}
	}
	
	
	/**
	 * 佛山 根据区域名称 返回 区域编码
	* @Title: AreaCodeByAreaName 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param areaName
	* @param @return    入参
	* @return String    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2017年11月30日 下午3:27:04 
	* @version V1.0
	 */
	public static String AreaCodeByAreaName(String areaName) {
		if("".equals(areaName) || areaName==null) {
			return "440600";
		}
		if(areaName.indexOf("佛山三水")!=-1) {
			return "440607";
		}else if(areaName.indexOf("佛山南海")!=-1) {
			return "440605";
		}else if(areaName.indexOf("佛山禅城")!=-1) {
			return "440604";
		}else if(areaName.indexOf("佛山顺德")!=-1) {
			return "440606";
		}else if(areaName.indexOf("佛山高明")!=-1) {
			return "440608";
		}else {
			return "440601";
		}
	}
	

	/**
	 * 博罗汇聚区对照
	* @Title: AreaCodeByAreaName_bl 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param areaName
	* @param @return    入参
	* @return String    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年9月7日 下午3:12:30 
	* @version V1.0
	 */
	public static String AreaCodeByAreaName_bl(String areaName) {
		if("".equals(areaName) || areaName==null) {
			return "441322";
		}
		if(areaName.indexOf("博罗保利山水城汇聚区")!=-1) {
			return "44132210";
		}else if(areaName.indexOf("博罗十二岭汇聚区")!=-1) {
			return "44132204";
		}else if(areaName.indexOf("博罗移动汇聚区")!=-1) {
			return "44132206";
		}else if(areaName.indexOf("福田汇聚区")!=-1) {
			return "44132201";
		}else if(areaName.indexOf("湖镇汇聚区")!=-1) {
			return "44132202";
		}else if(areaName.indexOf("龙溪汇聚区")!=-1) {
			return "44132203";
		}else if(areaName.indexOf("石湾汇聚区")!=-1) {
			return "44132205";
		}else if(areaName.indexOf("泰美汇聚区")!=-1) {
			return "44132211";
		}else if(areaName.indexOf("园洲汇聚区")!=-1) {
			return "44132207";
		}else {
			return "441322";
		}
	}

	/**
	 * 光缆类型返回编码
	* @Title: devTypeConvert 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param type
	* @param @return    入参
	* @return String    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2017年11月30日 下午3:29:25 
	* @version V1.0
	 */
	public static String devTypeConvert(String type) {
		if("ODF".equals(type)) {
			return "05";
		}else if("ONU(GPON)".equals(type)){
			return "13";
		}else if("光交接箱".equals(type)){
			return "01";
		}else if("光交".equals(type)){
			return "01";
		}else if("光交箱".equals(type)){
			return "01";
		}else if("光分纤盒".equals(type)){
			return "03";
		}else if("光终端盒".equals(type)){
			return "02";
		}else if("光缆接头".equals(type)){
			return "04";
		}else if("机房".equals(type)){
			return "20";
		}else {
			return "03";
		}
	}
	
	/**
	 * 筛选光路类型工具类
	 * @param lightName
	 * @return
	 */
	public static String LightPathType(String lightName) {
		String lightType = "";
		String Adev = "";
		String Zdev = "";
		String Mdev = "";
		
		if(lightName == null) {
			return "";
		}else {
			Adev = lightName.substring(0,lightName.indexOf("<==>"));
			Zdev = lightName.substring(lightName.lastIndexOf("<==>"));
			Mdev = lightName.substring(lightName.indexOf("<==>"),lightName.lastIndexOf("<==>")+1);
		}

		//光路两端是设备
		if(Adev.indexOf("PTN")!=-1 && Zdev.indexOf("PTN")!=-1) {
			lightType = "PTN<==>PTN";
		}else if(Adev.indexOf("SDH")!=-1 && Zdev.indexOf("SDH")!=-1) {
			lightType =  "SDH<==>SDH";
		//OLT分光器/末端站点
		}else if(((Adev.indexOf("OLT")!=-1 || Adev.indexOf("PON")!=-1 || Adev.indexOf("5680T")!=-1 || Adev.indexOf("5683T")!=-1 || Adev.indexOf("ZX")!=-1) && Zdev.indexOf("FG")!=-1)
 					|| ((Zdev.indexOf("OLT")!=-1 || Zdev.indexOf("PON")!=-1 || Zdev.indexOf("5680T")!=-1 || Zdev.indexOf("5683T")!=-1 || Zdev.indexOf("ZX")!=-1) && Adev.indexOf("FG")!=-1)) {
			lightType =  "OLT<==>分光器";
		//BBUODF
		}else if((Adev.indexOf("BBU")!=-1 && Zdev.indexOf("ODF")!=-1) 
				|| (Zdev.indexOf("BBU")!=-1 && Adev.indexOf("ODF")!=-1) ) {
			lightType =  "BBU<==>ODF";
		}else if((Adev.indexOf("BBU")!=-1 && Zdev.indexOf("RRU")!=-1) 
				|| (Zdev.indexOf("BBU")!=-1 && Adev.indexOf("RRU")!=-1)) {
			lightType =  "BBU<==>RRU";
		}else if(((Adev.indexOf("OLT")!=-1 || Adev.indexOf("PON")!=-1 || Adev.indexOf("5680T")!=-1 || Adev.indexOf("5683T")!=-1 || Adev.indexOf("ZX")!=-1 || Adev.indexOf("PTN")!=-1 || Adev.indexOf("SDH")!=-1) && Zdev.indexOf("ODF")!=-1)
				|| ((Zdev.indexOf("OLT")!=-1 || Zdev.indexOf("PON")!=-1 || Zdev.indexOf("5680T")!=-1 || Zdev.indexOf("5683T")!=-1 || Zdev.indexOf("ZX")!=-1 || Zdev.indexOf("PTN")!=-1 || Zdev.indexOf("SDH")!=-1) && Adev.indexOf("ODF")!=-1)) {
			lightType =  "(PTN/SDH/OLT)<==>ODF";
		}else if(Adev.indexOf("ODF")!=-1 && Zdev.indexOf("ODF")!=-1) {
			lightType =  "ODF<==>ODF";
		}else if(lightName.indexOf("虚拟")!=-1) {
			lightType =  "包含虚拟信息";
		//光路两端任意一端没有明确的端口编号
		}else if(StringUtil.qurByChar('-',Adev)<3 || StringUtil.qurByChar('-',Zdev)<3) {
			lightType =  "光路两端任意一端没有明确的端口编号";
		}else if((Adev.indexOf("ODF")!=-1 && Zdev.indexOf("FG")!=-1) 
				|| (Zdev.indexOf("ODF")!=-1 && Adev.indexOf("FG")!=-1)) {
			lightType =  "ODF<==>分光器";
		//中间是设备端口
		}else if(Mdev.indexOf("PTN")!=-1 || Mdev.indexOf("SDH")!=-1 || Mdev.indexOf("BBU")!=-1 || Mdev.indexOf("RRU")!=-1 || Mdev.indexOf("PON")!=-1 || Mdev.indexOf("OLT")!=-1 || Mdev.indexOf("5680T")!=-1 || Mdev.indexOf("5683T")!=-1 || Mdev.indexOf("ZX")!=-1) {
			lightType =  "中间是设备端口";
		}else if(((Adev.indexOf("#")!=-1 || Adev.indexOf("井")!=-1) && Zdev.indexOf("ODF")!=-1) 
				|| ((Zdev.indexOf("#")!=-1 || Zdev.indexOf("井")!=-1) && Adev.indexOf("ODF")!=-1) ) {
			lightType =  "一端是井，一端无设备";
		}else {
			lightType =  "其他";
		}
		return lightType;
	}
}   
