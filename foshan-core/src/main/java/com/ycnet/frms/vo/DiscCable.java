package com.ycnet.frms.vo;

import java.util.List;

public class DiscCable {
	private Long sectId;
	
	private String secName;
	
	private List<Disc1> discs;

	public Long getSectId() {
		return sectId;
	}

	public void setSectId(Long sectId) {
		this.sectId = sectId;
	}

	public String getSecName() {
		return secName;
	}

	public void setSecName(String secName) {
		this.secName = secName;
	}

	public List<Disc1> getDiscs() {
		return discs;
	}

	public void setDiscs(List<Disc1> discs) {
		this.discs = discs;
	}
}
