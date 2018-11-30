package com.ycnet.frms.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ycnet.core.FrmsException;
import com.ycnet.core.util.ASCIIUtil;
import com.ycnet.core.util.HexUtil;

public class hzgcRequsetInfo {

	//设备imei
	private String imei;
	
	//平台标记 默认04
	private String platform = "07";
	
	//所属下游平台标记 固定01
	private String platform_from = "01";
	
	//app远程开锁90   其他配置信息90000
	private Long expired_time;
	
	//下行数据
	private String value;
	
	//用户ID
	private String userId;

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getPlatform_from() {
		return platform_from;
	}

	public void setPlatform_from(String platform_from) {
		this.platform_from = platform_from;
	}

	public Long getExpired_time() {
		return expired_time;
	}

	public void setExpired_time(Long expired_time) {
		this.expired_time = expired_time;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * 控制参数设置  -- 01平台开锁 02APP开锁 03布防 04撤防
	* @Title: setContrlValue 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param imei
	* @param @param controlInfo  控制信息
	* @param @param req    总参数长度
	* @param @return    入参
	* @return String    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年7月26日 上午10:25:33 
	* @version V1.0
	 */
	public String setContrlValue(String imei,String controlInfo,Integer req) {
		String fireProtoVersion = "10011002";
		//01(01平台开锁 02APP开锁 03布防 04撤防)其余位数补0
		if(imei==null || "".equals(imei)) {
			new FrmsException("IMEI值不能为空！");
		}else if(controlInfo==null || "".equals(controlInfo)) {
			new FrmsException("控制类型不能为空！");
		}else if(req==null || req==0) {
			new FrmsException("控制信息的总长度不能为空！");
		}
		String result = HexUtil.encode(imei)
				+ fireProtoVersion
				+ controlInfo;
		if(result.length()<req) {
			result = result + getCover(req,result.length()+12)
					+ new SimpleDateFormat("yyMMddHHmmss").format(new Date());
		}else if((result.length()+12)<req) {
			new FrmsException("控制参数设置出现异常！");
		}
		 return result;
	} 
	public static void main(String[] args) {
		String result = HexUtil.encode("865352031796724");
		System.out.println(result);
	}
	/**
	 * 得到补位0数
	* @Title: getCover 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param num
	* @param @return    入参
	* @return String    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年7月26日 上午10:10:26 
	* @version V1.0
	 */
	private static String getCover(Integer numAll,Integer req) {
		if (numAll.equals(req)) {
			return "";
		}else {
			return String.format("%0"+(numAll-req)+"d", 1).replace("1", "0");
		}
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
