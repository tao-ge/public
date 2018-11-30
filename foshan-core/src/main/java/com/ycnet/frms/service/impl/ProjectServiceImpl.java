package com.ycnet.frms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Project;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.ProjectMapper;
import com.ycnet.frms.service.ProjectService;
import com.ycnet.frms.vo.ProjectConditionBean;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService{

	@Resource(name="projectMapper")
	private ProjectMapper projectMapper;
	
	@Override
	public List<Project> selectByParam(ProjectConditionBean searchBean) {
		return projectMapper.selectByParam(searchBean);
	}

	@Override
	public Project selectById(Long proId) {
		// TODO Auto-generated method stub
		return projectMapper.selectByPrimaryKey(proId);
	}

	@Override
	public PageBean queryByProjectBean(Project bean, PageBean pb,Users user) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bean", bean);
		map.put("pb", pb);
		map.put("user", user);
		pb.setRows(projectMapper.queryCountByProjectBean(map));
		pb.setList(projectMapper.queryByProjectBean(map));
		return pb;
	}

	@Override
	public int save(Project param) {
			int ret = 0;
			if(param.getProId()!=null)
			{
				//param.setProCode(null);
				ret =  projectMapper.updateByPrimaryKeySelective(param);
			}
			else
			{
				ret = projectMapper.insertSelective(param);
				
			}
			return ret;
	}

	@Override
	public int delete(Long proId) {
		return projectMapper.deleteByPrimaryKey(proId);
	}

	//导出数据库 刘沧海 2017/10/13
	@Override
	public List<Project> queryList(Long orgId) {
		return projectMapper.queryList(orgId);
	}

}
