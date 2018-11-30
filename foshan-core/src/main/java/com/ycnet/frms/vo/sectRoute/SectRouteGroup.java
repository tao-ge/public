package com.ycnet.frms.vo.sectRoute;

import java.util.List;

public class SectRouteGroup {

	private String side;
	
	private Integer discNum;
	
	private List<SectRouteDisc> srdList;


	public Integer getDiscNum() {
		return discNum;
	}

	public void setDiscNum(Integer discNum) {
		this.discNum = discNum;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public List<SectRouteDisc> getSrdList() {
		return srdList;
	}

	public void setSrdList(List<SectRouteDisc> srdList) {
		this.srdList = srdList;
	}
	
	
}
