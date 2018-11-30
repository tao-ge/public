package com.ycnet.frms.mapper;

import com.ycnet.frms.bean.DiscinfoEntity;

public interface DiscinfoEntityMapper {
    int deleteByPrimaryKey(Long discId);

    int insert(DiscinfoEntity record);

    int insertSelective(DiscinfoEntity record);

    DiscinfoEntity selectByPrimaryKey(Long discId);

    int updateByPrimaryKeySelective(DiscinfoEntity record);

    int updateByPrimaryKey(DiscinfoEntity record);

    /**
     * 
     * @Title: selectByDiscCode
     * @Description: 根据discCode查询
     * @param @param discCode
     * @param @return 
     * @return DiscinfoEntity 入参
     * @return String    返回类型
     * @author DZY 
     * @throws
     * @date 2018年4月13日 上午9:20:26
     * @version V1.0
     */
	DiscinfoEntity selectByDiscCode(String discCode);
}