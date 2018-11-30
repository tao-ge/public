package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.OcIdentifGroup;
import com.ycnet.frms.bean.Users;

public interface OcIdentifGroupMapper {
    int deleteByPrimaryKey(Long groupId);

    int insert(OcIdentifGroup record);

    int insertSelective(OcIdentifGroup record);

    OcIdentifGroup selectByPrimaryKey(Long groupId);

    int updateByPrimaryKeySelective(OcIdentifGroup record);

    int updateByPrimaryKey(OcIdentifGroup record);

	/** 
	 * 查询识别仪分组列表
	* @Title: queryIdentifGroup 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param ocIdentifGroup
	* @param @param user
	* @param @return    入参
	* @return List<OcIdentifGroup>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月19日 上午10:10:57 
	* @version V1.0   
	*/
	List<OcIdentifGroup> queryIdentifGroup(OcIdentifGroup ocIdentifGroup, Users user);
}