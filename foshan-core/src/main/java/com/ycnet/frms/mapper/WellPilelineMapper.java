package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.WellPileline;

public interface WellPilelineMapper {
    int deleteByPrimaryKey(Long relevId);

    int insert(WellPileline record);

    int insertSelective(WellPileline record);

    WellPileline selectByPrimaryKey(Long relevId);

    int updateByPrimaryKeySelective(WellPileline record);

    int updateByPrimaryKey(WellPileline record);
    
    /**
     * 查询是否已经添加关联
     * @param spaceId
     * @param sectId
     * @return
     */
    int selectCount(Long devId,Long sectId);
    
    List<WellPileline> selectByDevId(Long devId);
    
    int deleteByDevId(Long devId);
    
    List<WellPileline> selectByFDevId(Long fdevId);

	List<WellPileline> selectByDevIdOr(Long devId);

	int deleteByDevIdOr(Long devId);

	//导出数据库  刘沧海 2017/10/13
	List<WellPileline> queryList();
}