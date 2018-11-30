package com.ycnet.frms.mapper;

import com.ycnet.frms.bean.WorkorderImplePlans;
import com.ycnet.frms.vo.WorkorderFiberdiscabindBean;
import com.ycnet.frms.vo.WorkorderFiberdiscabindImgBean;

public interface WorkorderImplePlansMapper {
    int deleteByPrimaryKey(Long plansId);

    int insert(WorkorderImplePlans record);

    int insertSelective(WorkorderImplePlans record);

    WorkorderImplePlans selectByPrimaryKey(Long plansId);

    int updateByPrimaryKeySelective(WorkorderImplePlans record);

    int updateByPrimaryKey(WorkorderImplePlans record);

    /**
     * 
    * @Title: deleteByDesignroutesId 
    * @Description: 删除实施施工方案 
    * @param @param designroutesId
    * @param @return    
    * @return int
    * @author liucanghai 
    * @throws
    * @date 2018年4月24日 下午1:45:55 
    * @version V1.0
     */
	int deleteByDesignroutesId(Long designroutesId);

	/**
	 * 
	* @Title: queryByDesignroutesId 
	* @Description: 查询施工反馈信息 
	* @param @param designroutesId
	* @param @return    
	* @return WorkorderImplePlans
	* @author liucanghai 
	* @throws
	* @date 2018年4月25日 下午1:50:52 
	* @version V1.0
	 */
	WorkorderImplePlans queryByDesignroutesId(Long designroutesId);

	/**
	 * 
	* @Title: queryByFiberDataCount 
	* @Description: 根据端子查询实施方案有没有数据 
	* @param @param orgId
	* @param @param code
	* @param @return    
	* @return int
	* @author liucanghai 
	* @throws
	* @date 2018年5月29日 上午11:45:57 
	* @version V1.0
	 */
	int queryByFiberDataCount(Long orgId, String code);

	/**
	 * 
	 * @Title: queryImplAByDesignroutesId
	 * @Description: 根据designroutesId,查询A端实际完成方案信息
	 * @param @param designroutesId
	 * @param @return 
	 * @return WorkorderFiberdiscabindImgBean 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月11日 下午1:37:44
	 * @version V1.0
	 */
	WorkorderFiberdiscabindImgBean queryImplAByDesignroutesId(Long designroutesId);

	
	/**
	 * 
	 * @Title: queryImplZByDesignroutesId
	 * @Description: 根据designroutesId,查询Z端实际完成方案信息
	 * @param @param designroutesId
	 * @param @return 
	 * @return WorkorderFiberdiscabindImgBean 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月11日 下午2:16:58
	 * @version V1.0
	 */
	WorkorderFiberdiscabindImgBean queryImplZByDesignroutesId(Long designroutesId);

	/**
	 * 根据端子查询盘等信息
	* @Title: queryImplAByFiberDiscId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param afiberDiscId
	* @param @return    入参
	* @return WorkorderFiberdiscabindBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年9月6日 下午3:30:29 
	* @version V1.0
	 */
	WorkorderFiberdiscabindBean queryImplAByFiberDiscId(Long afiberDiscId);
}