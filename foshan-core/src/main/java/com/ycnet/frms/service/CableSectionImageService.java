package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.frms.bean.CableSectionImage;

public interface CableSectionImageService {

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
	 * @date 2018年1月2日 下午5:07:20
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
	 * @date 2018年1月2日 下午5:24:53
	 * @version V1.0
	 */
	int deleteImageBySectId(Long sectId);

	/**
	 * 
	 * @Title: insert
	 * @Description: 保存光缆段图片信息
	 * @param @param cableSectionImage
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月2日 下午5:41:53
	 * @version V1.0
	 */
	int insert(CableSectionImage cableSectionImage);

}
  
    