package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.CableSectionImage;

public interface CableSectionImageMapper {
    int deleteByPrimaryKey(Long sectImageId);

    int insert(CableSectionImage record);

    int insertSelective(CableSectionImage record);

    CableSectionImage selectByPrimaryKey(Long sectImageId);

    int updateByPrimaryKeySelective(CableSectionImage record);

    int updateByPrimaryKey(CableSectionImage record);

    /**
     * 
     * @Title: queryBySectId
     * @Description: 根据光缆段ID查询图片数据
     * @param @param sectId
     * @param @return 
     * @return List<CableSectionImage> 入参
     * @return String    返回类型
     * @author DZY 
     * @throws
     * @date 2018年1月2日 下午5:08:16
     * @version V1.0
     */
	List<CableSectionImage> queryBySectId(Long sectId);

	/**
	 * 
	 * @Title: deleteImageBySectId
	 * @Description: 根据sectId删除图片数据
	 * @param @param sectId
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月2日 下午5:25:30
	 * @version V1.0
	 */
	int deleteImageBySectId(Long sectId);
}