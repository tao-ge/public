package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.OcCableSection;
import com.ycnet.frms.vo.mobile.ocii.IndexInBean;
import com.ycnet.frms.vo.mobile.ocii.IndexLine;
import com.ycnet.frms.vo.mobile.ocii.OcSectBean;

public interface OcCableSectionMapper {
    int deleteByPrimaryKey(Long sectId);

    int insert(OcCableSection record);

    int insertSelective(OcCableSection record);

    OcCableSection selectByPrimaryKey(Long sectId);

    int updateByPrimaryKeySelective(OcCableSection record);

    int updateByPrimaryKey(OcCableSection record);

	/** 
	 * 查询光交箱下的关联光缆段
	* @Title: queryOciiFacilityByPdevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return List<OcSectBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月11日 下午4:03:02 
	* @version V1.0   
	 * @param orgId 
	*/
	List<OcSectBean> queryOciiFacilityByPdevId(Long devId, Long orgId);

	/**
	 * 根据条件查询光缆段两端
	* @Title: OcIndexSectByType 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param iib
	* @param @return    入参
	* @return List<IndexLine>    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年10月17日 上午9:44:06 
	* @version V1.0
	 */
	List<IndexLine> OcIndexSectByType(IndexInBean iib);
}