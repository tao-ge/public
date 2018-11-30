package com.ycnet.frms.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ycnet.core.FrmsException;
import com.ycnet.core.util.ASCIIUtil;
import com.ycnet.core.util.HexUtil;

public class HzgcRequsetParameter {

	//平台标记 默认04
	private String platform = "07";
	
	//所属下游平台标记 固定01
	private String platform_from = "01";
	
	//app远程开锁90   其他配置信息90000
	private Long expired_time;
	
	//下行数据
	private List<valueList> value_list;
	

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
	
	public List<valueList> getValue_list() {
		return value_list;
	}

	public void setValue_list(List<valueList> value_list) {
		this.value_list = value_list;
	}
	
	/**
	 * 配置参数设置   基础参数设置
	* @Title: setConfigureValue 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param imei
	* @param @param baseInfoArr  基础参数信息
	* @param @param req  参数个数
	* @param @return    入参
	* @return String    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年7月26日 上午10:25:01 
	* @version V1.0
	 */
	public String setConfigureValue(String imei,Integer[] baseInfoArr,Integer req) {
		String fireProtoVersion = "10011002";
		//心跳规则+电池阀值+电池告警间隔时间+门未关时间+门超时告警间隔+非法开门告警间隔+温度阀值+
		//温度告警间隔+撞击阀值+撞击告警间隔+锁未关时间+锁未关告警间隔+非法撬锁告警间隔
		String baseInfo = "";
		//01(01平台开锁 02APP开锁 03布防 04撤防)其余位数补0
		if(imei==null || "".equals(imei)) {
			new FrmsException("IMEI值不能为空！");
		}else if(req==null || req>0) {
			new FrmsException("设置个数不对！");
		}else if(baseInfoArr==null || baseInfoArr.length!=req) {
			new FrmsException("设置参数不能为空！");
		}
		for(int i=1;i<=req;i++) {
			String base = Integer.toHexString(baseInfoArr[i-1]);
			if(base.length() > 4) {
				new FrmsException("设置参数过长！");
			}
			if(i==1) {//心跳规则 4
				baseInfo = baseInfo + getCover(4,base.length())+base;
			}else if(i==2) {//电池阀值 2
				baseInfo = baseInfo + getCover(2,base.length())+base;
			}else if(i==3) {//电池告警间隔时间 4
				baseInfo = baseInfo + getCover(4,base.length())+base;
			}else if(i==4) {//门未关时间 4
				baseInfo = baseInfo + getCover(4,base.length())+base;
			}else if(i==5) {//门超时告警间隔 4
				baseInfo = baseInfo + getCover(4,base.length())+base;
			}else if(i==6) {//非法开门告警间隔 4
				baseInfo = baseInfo + getCover(4,base.length())+base;
			}else if(i==7) {//温度阀值 2
				baseInfo = baseInfo + getCover(2,base.length())+base;
			}else if(i==8) {//温度告警间隔 4
				baseInfo = baseInfo + getCover(4,base.length())+base;
			}else if(i==9) {//撞击阀值 2
				baseInfo = baseInfo + getCover(2,base.length())+base;
			}else if(i==10) {//撞击告警间隔 4
				baseInfo = baseInfo + getCover(4,base.length())+base;
			}else if(i==11) {//锁未关时间 4
				baseInfo = baseInfo + getCover(4,base.length())+base;
			}else if(i==12) {//锁未关告警间隔 4
				baseInfo = baseInfo + getCover(4,base.length())+base;
			}else if(i==13) {//非法撬锁告警间隔 4
				baseInfo = baseInfo + getCover(4,base.length())+base;
			}
		}
		String result = HexUtil.encode(imei)
				+ fireProtoVersion
				+ baseInfo
				+ "0000"
				+ new SimpleDateFormat("yyMMddHHmmss").format(new Date());
//		if(result.length()<req) {
//			result = result + getCover(req,result.length());
//		}else if(result.length()<req) {
//			new FrmsException("控制参数设置出现异常！");
//		}
		 return result;
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
}
