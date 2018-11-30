package com.ycnet.frms.vo;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Project;

public class ProjectConditionBean {

	private Project project;
	
	private PageBean pageBean;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	
	
}
