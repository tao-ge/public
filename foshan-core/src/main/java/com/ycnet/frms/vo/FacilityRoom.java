package com.ycnet.frms.vo;

import java.util.List;

import com.ycnet.frms.bean.Facility;

public class FacilityRoom {
	private Long devIdRoom;
	
	private String devCodeRoom;
	
	private String devNameRoom;
	
	private String devTypeRoom;
	
	private List<Facility> faList;

	public Long getDevIdRoom() {
		return devIdRoom;
	}

	public void setDevIdRoom(Long devIdRoom) {
		this.devIdRoom = devIdRoom;
	}

	public String getDevCodeRoom() {
		return devCodeRoom;
	}

	public void setDevCodeRoom(String devCodeRoom) {
		this.devCodeRoom = devCodeRoom;
	}

	public String getDevNameRoom() {
		return devNameRoom;
	}

	public void setDevNameRoom(String devNameRoom) {
		this.devNameRoom = devNameRoom;
	}

	public String getDevTypeRoom() {
		return devTypeRoom;
	}

	public void setDevTypeRoom(String devTypeRoom) {
		this.devTypeRoom = devTypeRoom;
	}

	public List<Facility> getFaList() {
		return faList;
	}

	public void setFaList(List<Facility> faList) {
		this.faList = faList;
	}
	
	
}
