package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.Project;
import com.ycnet.frms.vo.ProjectConditionBean;

public interface ProjectMapper {
    int deleteByPrimaryKey(Long proId);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Long proId);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);
    
    List<Project> selectByParam(ProjectConditionBean project);

	List<Project> queryByProjectBean(Map<String, Object>  map);

	int queryCountByProjectBean(Map<String, Object>  map);

	//导出数据库 刘沧海 2017/10/13
	List<Project> queryList(Long orgId);
	
	/**
	 * 根据工程编码查询工程
	* @Title: selectByProCode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param proCode
	* @param @return    入参
	* @return Project    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2017年12月4日 上午11:04:51 
	* @version V1.0
	 */
	Project selectByProCode(String proCode);
}