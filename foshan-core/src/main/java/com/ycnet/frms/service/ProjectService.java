package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Project;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.ProjectConditionBean;

public interface ProjectService {

	List<Project> selectByParam(ProjectConditionBean project);
	
	Project selectById(Long proId);

	PageBean queryByProjectBean(Project bean, PageBean pb,Users user);

	int save(Project param);

	int delete(Long proId);

	//导出数据库 刘沧海 2017/10/13
	List<Project> queryList(Long orgId);
}
