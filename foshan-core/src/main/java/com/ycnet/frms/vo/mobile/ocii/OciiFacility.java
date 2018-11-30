/**   
 * @Package: com.ycnet.frms.vo.mobile.ocii 
 * @author: FL   
 * @date: 2018年10月11日 下午1:34:39 
 */
package com.ycnet.frms.vo.mobile.ocii;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/** 
* @ClassName: OciiFacility 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月11日 下午1:34:39  
*/
public class OciiFacility {
	
	private Long devId;
	
	private String devCode;
	
	private String devName;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date time;
	
	private	String devType;
	
	private	String devState;
	
	private BigDecimal baiduX;
    
    private BigDecimal baiduY;
	
	private String devAddr;

	public Long getDevId() {
		return devId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public String getDevCode() {
		return devCode;
	}

	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getDevType() {
		return devType;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public String getDevState() {
		return devState;
	}

	public void setDevState(String devState) {
		this.devState = devState;
	}


	public String getDevAddr() {
		return devAddr;
	}

	public void setDevAddr(String devAddr) {
		this.devAddr = devAddr;
	}

	public BigDecimal getBaiduX() {
		return baiduX;
	}

	public void setBaiduX(BigDecimal baiduX) {
		this.baiduX = baiduX;
	}

	public BigDecimal getBaiduY() {
		return baiduY;
	}

	public void setBaiduY(BigDecimal baiduY) {
		this.baiduY = baiduY;
	}

}
