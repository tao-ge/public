package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.OcPipingSection;
import com.ycnet.frms.vo.mobile.ocii.IndexInBean;
import com.ycnet.frms.vo.mobile.ocii.IndexLine;
import com.ycnet.frms.vo.mobile.ocii.OcPipingSectionUtil;

public interface OcPipingSectionMapper {
    int deleteByPrimaryKey(Long pipingSectId);

    int insert(OcPipingSection record);

    int insertSelective(OcPipingSection record);

    OcPipingSection selectByPrimaryKey(Long pipingSectId);

    int updateByPrimaryKeySelective(OcPipingSection record);

    int updateByPrimaryKey(OcPipingSection record);

    /**
     * 根据条件查询管道段两端
    * @Title: OcIndexPipByType 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param iib
    * @param @return    入参
    * @return List<IndexLine>    返回类型
    * @author YHT（作者） 
    * @throws
    * @date 2018年10月17日 上午9:06:44 
    * @version V1.0
     */
	List<IndexLine> OcIndexPipByType(IndexInBean iib);

	/**
	 * 根据管道段ID查询管道段
	* @Title: selectPipSectById 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param pipingSectId
	* @param @return    入参
	* @return OcPipingSectionUtil    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年10月22日 上午9:50:16 
	* @version V1.0
	 */
	OcPipingSectionUtil selectPipSectById(Long pipingSectId);
}