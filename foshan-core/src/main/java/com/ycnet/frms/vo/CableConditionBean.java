package com.ycnet.frms.vo;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Cable;

public class CableConditionBean {

	private Cable cable;
	
	private PageBean pageBean;

	public Cable getCable() {
		return cable;
	}

	public void setCable(Cable cable) {
		this.cable = cable;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	
	
}
