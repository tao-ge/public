package com.ycnet.frms.vo;

import java.util.List;

public class GroupTemplate {

	private String model;
	
	private String side;
	
	private String row;
	
	private String col;
	
	private List<String> discType;

	private String startIndex;
	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public String getCol() {
		return col;
	}

	public void setCol(String col) {
		this.col = col;
	}

	public List<String> getDiscType() {
		return discType;
	}

	public void setDiscType(List<String> discType) {
		this.discType = discType;
	}

	public String getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}
	
	
}
