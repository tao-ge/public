package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.OcCableSectionImage;

public interface OcCableSectionImageMapper {
    int deleteByPrimaryKey(Long sectImageId);

    int insert(OcCableSectionImage record);

    int insertSelective(OcCableSectionImage record);

    OcCableSectionImage selectByPrimaryKey(Long sectImageId);

    int updateByPrimaryKeySelective(OcCableSectionImage record);

    int updateByPrimaryKey(OcCableSectionImage record);

	/** 
	 * 根据sectId查询光缆图片
	* @Title: queryImagesBySectId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param sectId
	* @param @return    入参
	* @return List<OcCableSectionImage>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月15日 下午1:18:47 
	* @version V1.0   
	*/
	List<OcCableSectionImage> queryImagesBySectId(Long sectId);

	/** 
	 * 删除光物理缆图片
	* @Title: deleteBySectIdAndUrl 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param map
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月15日 下午2:50:38 
	* @version V1.0   
	*/
	int deleteBySectIdAndUrl(Map<String, Object> map);
}