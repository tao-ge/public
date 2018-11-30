package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.PipingSectWell;

public interface PipingSectWellMapper {
    int deleteByPrimaryKey(Long pipingWellId);

    int insert(PipingSectWell record);

    int insertSelective(PipingSectWell record);

    PipingSectWell selectByPrimaryKey(Long pipingWellId);

    int updateByPrimaryKeySelective(PipingSectWell record);

    int updateByPrimaryKey(PipingSectWell record);

    /**
     * 
    * @Title: queryByPipingSectId 
    * @Description: 根据管道段ID查询井ID 
    * @param @param pipingSectId
    * @param @return    
    * @return List<PipingSectWell>
    * @author liucanghai 
    * @throws
    * @date 2018年1月18日 上午10:50:07 
    * @version V1.0
     */
	List<PipingSectWell> queryByPipingSectId(Long pipingSectId);

	/**
	 * 
	* @Title: querySpaceIdForPipingCableWell 
	* @Description: 根据井ID和管道段ID查询面ID 
	* @param @param wellId
	* @param @param pipingSectId
	* @param @return    
	* @return PipingSectWell
	* @author liucanghai 
	* @throws
	* @date 2018年1月22日 上午9:21:20 
	* @version V1.0
	 */
	PipingSectWell querySpaceIdForPipingCableWell(Long wellId, Long pipingSectId);

	/**
	 * 
	* @Title: queryByWellId 
	* @Description: 根据井ID查询关联管道段 
	* @param @param wellId
	* @param @return    
	* @return List<PipingSectWell>
	* @author liucanghai 
	* @throws
	* @date 2018年3月1日 上午11:29:58 
	* @version V1.0
	 */
	List<PipingSectWell> queryByWellId(Long wellId);
}