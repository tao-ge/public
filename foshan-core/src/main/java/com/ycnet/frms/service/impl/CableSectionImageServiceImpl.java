package com.ycnet.frms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.frms.bean.CableSectionImage;
import com.ycnet.frms.mapper.CableSectionImageMapper;
import com.ycnet.frms.service.CableSectionImageService;

@Service("cableSectionImageService")
@Transactional
public class CableSectionImageServiceImpl implements CableSectionImageService{
	
	@Resource(name="cableSectionImageMapper")
	private CableSectionImageMapper cableSectionImageMapper;

	/**
	 * 
	 * @Title: queryBySectId
	 * @Description: 根据光缆段ID查询图片数据
	 * @param @param sectId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月2日 下午5:07:40
	 * @version V1.0
	 */
	@Override
	public List<CableSectionImage> queryBySectId(Long sectId) {
		return cableSectionImageMapper.queryBySectId(sectId);
	}

	/**
	 * 
	 * @Title: deleteImageBySectId
	 * @Description: 根据sectId删除图片数据
	 * @param @param sectId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月2日 下午5:25:04
	 * @version V1.0
	 */
	@Override
	public int deleteImageBySectId(Long sectId) {
		return cableSectionImageMapper.deleteImageBySectId(sectId);
	}

	/**
	 * 
	 * @Title: insert
	 * @Description: 保存光缆段图片信息
	 * @param @param cableSectionImage
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月2日 下午5:42:21
	 * @version V1.0
	 */
	@Override
	public int insert(CableSectionImage cableSectionImage) {
		return cableSectionImageMapper.insertSelective(cableSectionImage);
	}
}
  
    