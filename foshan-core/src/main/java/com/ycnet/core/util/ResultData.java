package com.ycnet.core.util;

import java.io.Serializable;

/**
 * 查询结果类
 * @author YHT
 * @time   2016年7月7日 下午3:01:22
 */

public class ResultData implements Serializable{
	//0 没有数据     >=1有数据 数据记录条数
	private String r;
	//具体描述，如没有则为“null”
	private String r_content;
	//巡检信息，如没有则为null
	private Object dt;
	//无则为空
	private Object dtpic;
	public String getR() {
		return r;
	}
	public void setR(String r) {
		this.r = r;
	}
	public String getR_content() {
		return r_content;
	}
	public void setR_content(String r_content) {
		this.r_content = r_content;
	}
	public Object getDt() {
		return dt;
	}
	public void setDt(Object dt) {
		this.dt = dt;
	}
	public Object getDtpic() {
		return dtpic;
	}
	public void setDtpic(Object dtpic) {
		this.dtpic = dtpic;
	}
	@Override
	public String toString() {
		return "ResultData [r=" + r + ", r_content=" + r_content + ", dt=" + dt + ", dtpic=" + dtpic + "]";
	} 
	
	
}
