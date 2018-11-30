package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.FiberdiscGroup;
import com.ycnet.frms.bean.FiberdiscGroupEntity;
import com.ycnet.frms.vo.mobile.DiscinfoGroupMobile;


public interface FiberdiscGroupEntityMapper {
    int deleteByPrimaryKey(Long groupId);

    int insert(FiberdiscGroupEntity record);

    int insertSelective(FiberdiscGroupEntity record);

    FiberdiscGroupEntity selectByPrimaryKey(Long groupId);

    int updateByPrimaryKeySelective(FiberdiscGroupEntity record);

    int updateByPrimaryKey(FiberdiscGroupEntity record);

    /**
     * 
     * @Title: queryGroupByDev
     * @Description: 根据devId和side查询
     * @param @param bean
     * @param @return 
     * @return List<?> 入参
     * @return String    返回类型
     * @author DZY 
     * @throws
     * @date 2018年4月13日 上午11:31:50
     * @version V1.0
     */
	List<?> queryGroupByDev(FiberdiscGroupEntity bean);

	/**
	 * 
	* @Title: selectByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return FiberdiscGroup    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月31日 下午2:41:54 
	* @version V1.0
	 */
	List<DiscinfoGroupMobile> selectByDevId(Long devId);
}