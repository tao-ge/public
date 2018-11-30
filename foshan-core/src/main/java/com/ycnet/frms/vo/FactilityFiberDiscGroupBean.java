package com.ycnet.frms.vo;

import java.util.List;

public class FactilityFiberDiscGroupBean {
	private String devName;
	
	private String devCode;
	
	private List<String> imgUrls;
	
	private String impleBack;
	
	private String isBend = "0";//是否为始末端 0 否 1 是
	
	private WorkorderFiberdiscabindBean workorderFiberdiscabindBeanA;//
	
	private WorkorderFiberdiscabindBean workorderFiberdiscabindBeanZ;//
	
	private WorkorderFiberdiscabindImgBean WorkorderFiberdiscabindImgBeanA;
	
	private WorkorderFiberdiscabindImgBean WorkorderFiberdiscabindImgBeanZ;

	public WorkorderFiberdiscabindBean getWorkorderFiberdiscabindBeanA() {
		return workorderFiberdiscabindBeanA;
	}

	public WorkorderFiberdiscabindBean getWorkorderFiberdiscabindBeanZ() {
		return workorderFiberdiscabindBeanZ;
	}

	public void setWorkorderFiberdiscabindBeanA(WorkorderFiberdiscabindBean workorderFiberdiscabindBeanA) {
		this.workorderFiberdiscabindBeanA = workorderFiberdiscabindBeanA;
	}

	public void setWorkorderFiberdiscabindBeanZ(WorkorderFiberdiscabindBean workorderFiberdiscabindBeanZ) {
		this.workorderFiberdiscabindBeanZ = workorderFiberdiscabindBeanZ;
	}

	public WorkorderFiberdiscabindImgBean getWorkorderFiberdiscabindImgBeanA() {
		return WorkorderFiberdiscabindImgBeanA;
	}

	public WorkorderFiberdiscabindImgBean getWorkorderFiberdiscabindImgBeanZ() {
		return WorkorderFiberdiscabindImgBeanZ;
	}

	public void setWorkorderFiberdiscabindImgBeanA(WorkorderFiberdiscabindImgBean workorderFiberdiscabindImgBeanA) {
		WorkorderFiberdiscabindImgBeanA = workorderFiberdiscabindImgBeanA;
	}

	public void setWorkorderFiberdiscabindImgBeanZ(WorkorderFiberdiscabindImgBean workorderFiberdiscabindImgBeanZ) {
		WorkorderFiberdiscabindImgBeanZ = workorderFiberdiscabindImgBeanZ;
	}

	public String getDevName() {
		return devName;
	}

	public String getDevCode() {
		return devCode;
	}

	public List<String> getImgUrls() {
		return imgUrls;
	}

	public String getImpleBack() {
		return impleBack;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

	public void setImgUrls(List<String> imgUrls) {
		this.imgUrls = imgUrls;
	}

	public void setImpleBack(String impleBack) {
		this.impleBack = impleBack;
	}

	public String getIsBend() {
		return isBend;
	}

	public void setIsBend(String isBend) {
		this.isBend = isBend;
	}
}
