package com.ycnet.frms.vo.mobile.ocii;

/**
 * 首页查询接收类 - 点
* @ClassName: IndexBean 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author YHT(作者)  
* @date 2018年10月16日 下午1:11:56 
* @version V1.0
 */
public class IndexPoint{
	
	private Long id;
	
    private String type;

    private String devName;

    private String devState;
    
    private Double curLng;

    private Double curLat;

    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getDevState() {
		return devState;
	}

	public void setDevState(String devState) {
		this.devState = devState;
	}

	public Double getCurLng() {
		return curLng;
	}

	public void setCurLng(Double curLng) {
		this.curLng = curLng;
	}

	public Double getCurLat() {
		return curLat;
	}

	public void setCurLat(Double curLat) {
		this.curLat = curLat;
	}

}
