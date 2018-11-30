package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.Space;

public interface SpaceMapper {
    int deleteByPrimaryKey(Long spaceId);

    int insert(Space record);

    int insertSelective(Space record);

    Space selectByPrimaryKey(Long spaceId);

    int updateByPrimaryKeySelective(Space record);

    int updateByPrimaryKey(Space record);

    /**
     * 
    * @Title: queryByWellId 
    * @Description: 根据井设施ID查询面 
    * @param @param wellId
    * @param @return    
    * @return List<Space>
    * @author liucanghai 
    * @throws
    * @date 2018年1月17日 下午5:38:54 
    * @version V1.0
     */
	List<Space> queryByWellId(Long wellId);
}