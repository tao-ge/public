package com.ycnet.core.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 用于手机接口的返回实体
 */
public class AppResponseEntity {

	private String code;		// 返回数据标识码
	private String message;		// 返回数据信息
	private String tranCode;	// 业务代码，接口名称,用来做基准验证，后续这个部分可以作为验签字段使用
	
	private Object dto;
	
	public AppResponseEntity() {
	}
	
	public AppResponseEntity(String code, String message, String tranCode){
		this.code = code;
		this.message = message;
		this.tranCode = tranCode;
	}
	
	public AppResponseEntity(String code, String message, String tranCode, Object dto){
		this.code = code;
		this.message = message;
		this.tranCode = tranCode;
		this.setDto(dto);
	}

	public void setDto(Object dto) {
		if(null == dto) {
			Map<String, Object> map = new HashMap<String, Object>();
			this.dto = map;
		}else {
			if(dto instanceof Collection) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("results", dto);
				this.dto = map;
			}else {
				this.dto = dto;
			}
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTranCode() {
		return tranCode;
	}

	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}

	public Object getDto() {
		return dto;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		if (StringUtils.isNotBlank(code)) {
			sb.append("\"code\":\"" + code + "\",");
		}
		if (StringUtils.isNotBlank(message)) {
			sb.append("\"message\":\"" + message + "\",");
		}
		if (StringUtils.isNotBlank(tranCode)) {
			sb.append("\"tranCode\":\"" + tranCode + "\",");
		}
		if(null == dto) {
			sb.append("\"dto\":{}");
		}else {
			sb.append("\"dto\":" + dto + "");
		}
		sb.append("}");
		return sb.toString();
	}

}
