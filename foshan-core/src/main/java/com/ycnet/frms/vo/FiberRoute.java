package com.ycnet.frms.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class FiberRoute implements Serializable{

	private String acode;
	
	private String aotherName ;
	
	private ArrayList<LinkedList<FiberRouteNode>> halfRoutes;
	

	public String getAcode() {
		return acode;
	}

	public void setAcode(String acode) {
		this.acode = acode;
	}

	public ArrayList<LinkedList<FiberRouteNode>> getHalfRoutes() {
		return halfRoutes;
	}

	public void setHalfRoutes(ArrayList<LinkedList<FiberRouteNode>> halfRoutes) {
		this.halfRoutes = halfRoutes;
	}

	public String getAotherName() {
		return aotherName;
	}

	public void setAotherName(String aotherName) {
		this.aotherName = aotherName;
	}


	
	
}
