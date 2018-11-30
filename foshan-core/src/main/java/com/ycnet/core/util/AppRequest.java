package com.ycnet.core.util;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 移动端请求消息体
 */
public class AppRequest{

	
	private String imei;		// 设备标识，安卓端可用imei，ios可用uuid,web端待定
	private String timestamp; 	// 时间戳
	private String format;		// 报文格式，目前固定为json
	private String devType;		// 终端类型 1: Android 2: iOS 3：前端
	private String tranCode;	// 业务代码，接口名称,用来做基准验证，后续这个部分可以作为验签字段使用
	private String version;		// 接口版本号，用于app升级时，新老版本的接口兼容性
	
	private Object dto;	// 接口对应的参数实体

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getDevType() {
		return devType;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public String getTranCode() {
		return tranCode;
	}

	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Object getDto() {
		Object result = null;
		if(null != dto) {
			ObjectMapper mapper = new ObjectMapper(); //转换器  
//			Map m = mapper.readValue(json, Map.class); //json转换成map  
			 try {
				 String json=mapper.writeValueAsString((Map)dto);
				 result=mapper.readValue(json, Object.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public void setDto(Object dto) {
		this.dto = dto;
	}

	@Override
	public String toString() {
		return "AppRequestEntity [imei=" + imei + ", timestamp=" + timestamp + ", format=" + format + ", devType="
				+ devType + ", tranCode=" + tranCode + ", version=" + version + ", dto=" + dto + "]";
	}
	
}
