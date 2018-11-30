package com.ycnet.frms.vo;

import com.ycnet.frms.bean.Fiberdisc;

public class FiberdiscBean extends Fiberdisc{
	private Integer portNum;
	
	private String devName;
	
	private String routeText;
	
	private Long aSectId;
	private Long zSectId;
	
	private Long fibPortNum;//普查端子总数
	
	private Long fibPortFreeNum;//普查端子空闲数
	
	private Long fibPortOccupyNum;//普查端子占用数

	public String getRouteText() {
		return routeText;
	}

	public void setRouteText(String routeText) {
		this.routeText = routeText;
	}

	public Integer getPortNum() {
		return portNum;
	}

	public void setPortNum(Integer portNum) {
		this.portNum = portNum;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public Long getaSectId() {
		return aSectId;
	}

	public void setaSectId(Long aSectId) {
		this.aSectId = aSectId;
	}

	public Long getzSectId() {
		return zSectId;
	}

	public void setzSectId(Long zSectId) {
		this.zSectId = zSectId;
	}

	public Long getFibPortNum() {
		return fibPortNum;
	}

	public void setFibPortNum(Long fibPortNum) {
		this.fibPortNum = fibPortNum;
	}

	public Long getFibPortFreeNum() {
		return fibPortFreeNum;
	}

	public void setFibPortFreeNum(Long fibPortFreeNum) {
		this.fibPortFreeNum = fibPortFreeNum;
	}

	public Long getFibPortOccupyNum() {
		return fibPortOccupyNum;
	}

	public void setFibPortOccupyNum(Long fibPortOccupyNum) {
		this.fibPortOccupyNum = fibPortOccupyNum;
	}
	
	
}
