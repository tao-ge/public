package com.ycnet.frms.vo;

import java.util.List;

import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.PipingSection;
import com.ycnet.frms.bean.Space;
import com.ycnet.frms.bean.Well;
import com.ycnet.frms.bean.WellImg;

public class WellBean extends Well{
	
	private List<PipingSectionBean> pipingSectionList;//管道段
	
	private String isState;//核查状态,精准查询0123456
	
	private String wellStateEnd;
	private String times1;//开始时间
	private String times2;//结束时间
	
	private String devCode;//局前井设施编码
	
	private String devName;//局前井设施名称
	
	private Integer wellIden=0;//是不是当前井标识  1是，0不是
	
	private List<WellImg> wellImageList;//井图片路径集合
	
	public Integer getWellIden() {
		return wellIden;
	}

	public void setWellIden(Integer wellIden) {
		this.wellIden = wellIden;
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

	public String getTimes1() {
		return times1;
	}

	public void setTimes1(String times1) {
		this.times1 = times1;
	}

	public String getTimes2() {
		return times2;
	}

	public void setTimes2(String times2) {
		this.times2 = times2;
	}

	private List<PipingSectionBean> pipingSectionListBean;//管道段 加分面信息
	
	private String codeBaiduX;
	private String codeBaiduY;
	

	public List<PipingSectionBean> getPipingSectionListBean() {
		return pipingSectionListBean;
	}

	public void setPipingSectionListBean(List<PipingSectionBean> pipingSectionListBean) {
		this.pipingSectionListBean = pipingSectionListBean;
	}

	
	
	public List<PipingSectionBean> getPipingSectionList() {
		return pipingSectionList;
	}

	public void setPipingSectionList(List<PipingSectionBean> pipingSectionList) {
		this.pipingSectionList = pipingSectionList;
	}

	public String getWellStateEnd() {
		return wellStateEnd;
	}

	public void setWellStateEnd(String wellStateEnd) {
		this.wellStateEnd = wellStateEnd;
	}

	public String getCodeBaiduX() {
		return codeBaiduX;
	}

	public void setCodeBaiduX(String codeBaiduX) {
		this.codeBaiduX = codeBaiduX;
	}

	public String getCodeBaiduY() {
		return codeBaiduY;
	}

	public void setCodeBaiduY(String codeBaiduY) {
		this.codeBaiduY = codeBaiduY;
	}

	public String getIsState() {
		return isState;
	}

	public void setIsState(String isState) {
		this.isState = isState;
	}

	public List<WellImg> getWellImageList() {
		return wellImageList;
	}

	public void setWellImageList(List<WellImg> wellImageList) {
		this.wellImageList = wellImageList;
	}



	
}
