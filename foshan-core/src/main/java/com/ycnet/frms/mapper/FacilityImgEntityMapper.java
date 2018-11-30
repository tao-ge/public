package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.FacilityImgEntity;

public interface FacilityImgEntityMapper {
    int deleteByPrimaryKey(Long devImgId);

    int insert(FacilityImgEntity record);

    int insertSelective(FacilityImgEntity record);

    FacilityImgEntity selectByPrimaryKey(Long devImgId);

    int updateByPrimaryKeySelective(FacilityImgEntity record);

    int updateByPrimaryKey(FacilityImgEntity record);

    /**
     * 根据设施ID,查询设施图片
    * @Title: selectByDevId 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param devId
    * @param @return    入参
    * @return List<FacilityImgEntity>    返回类型
    * @author FL（作者） 
    * @throws
    * @date 2018年6月14日 上午10:02:49 
    * @version V1.0
     */
	List<FacilityImgEntity> selectByDevId(Long devId);
}