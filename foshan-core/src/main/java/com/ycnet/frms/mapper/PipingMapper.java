package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.Piping;
import com.ycnet.frms.vo.PipingBean;

public interface PipingMapper {
    int deleteByPrimaryKey(Long pipingId);

    int insert(Piping record);

    int insertSelective(Piping record);

    Piping selectByPrimaryKey(Long pipingId);

    int updateByPrimaryKeySelective(Piping record);

    int updateByPrimaryKey(Piping record);
    
    Piping queryPipingbeanByPrimaryKey(Long pipingId);

	List<PipingBean> queryBypipingSectId(Long pipingSectId, Long orgId, Long wellId);

	/**
	 * 
	* @Title: queryByWellId 
	* @Description: 根据井ID查询管控信息 
	* @param @param wellId
	* @param @return    
	* @return List<Piping>
	* @author liucanghai 
	* @throws
	* @date 2017年12月22日 下午8:00:53 
	* @version V1.0
	 */
	List<Piping> queryByWellId(Long wellId);
}