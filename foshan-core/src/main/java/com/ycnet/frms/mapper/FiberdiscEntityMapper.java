package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.FiberdiscEntity;


public interface FiberdiscEntityMapper {
    int deleteByPrimaryKey(Long fiberDiscId);

    int insert(FiberdiscEntity record);

    int insertSelective(FiberdiscEntity record);

    FiberdiscEntity selectByPrimaryKey(Long fiberDiscId);

    int updateByPrimaryKeySelective(FiberdiscEntity record);

    int updateByPrimaryKeyWithBLOBs(FiberdiscEntity record);

    int updateByPrimaryKey(FiberdiscEntity record);

    /**
     * 
     * @Title: queryBydev
     * @Description: 根据条件查询端子
     * @param @param param
     * @param @return 
     * @return List<FiberdiscEntity> 入参
     * @return String    返回类型
     * @author DZY 
     * @throws
     * @date 2018年4月13日 上午10:00:43
     * @version V1.0
     */
	List<FiberdiscEntity> queryBydev(FiberdiscEntity param);
}