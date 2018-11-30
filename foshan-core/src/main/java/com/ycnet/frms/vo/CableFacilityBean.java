package com.ycnet.frms.vo;

import com.ycnet.frms.bean.Facility;

public class CableFacilityBean {

	private CablesBean cablesBean;
	
	private Facility afacility;
	
	private Facility zfacility;

	public CablesBean getCablesBean() {
		return cablesBean;
	}

	public void setCablesBean(CablesBean cablesBean) {
		this.cablesBean = cablesBean;
	}

	public Facility getAfacility() {
		return afacility;
	}

	public void setAfacility(Facility afacility) {
		this.afacility = afacility;
	}

	public Facility getZfacility() {
		return zfacility;
	}

	public void setZfacility(Facility zfacility) {
		this.zfacility = zfacility;
	}
	
}
