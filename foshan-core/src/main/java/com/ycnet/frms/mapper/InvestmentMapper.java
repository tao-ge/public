package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.Investment;

public interface InvestmentMapper {
    int deleteByPrimaryKey(Long investId);

    int insert(Investment record);

    int insertSelective(Investment record);

    Investment selectByPrimaryKey(Long investId);

    int updateByPrimaryKeySelective(Investment record);

    int updateByPrimaryKey(Investment record);

    /**
     *  查询直熔盘信息
    * 
    * @Title: InvestmentMapper.java 
    * @Description: TODO 
    * @param @param devId
    * @param @return
    * @return List<Investment>
    * @author fl
    * @date 2017年12月16日 下午2:07:56
    * @version V1.0
     */
	List<Investment> queryByDevId(Long devId);
}