package com.ycnet.frms.mapper.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.CableSectionImage;
import com.ycnet.frms.mapper.CableSectionImageMapper;

@Repository("cableSectionImageMapper")
public class CableSectionImageMapperImpl extends BaseSqlSupport implements CableSectionImageMapper{

	@Override
	public int deleteByPrimaryKey(Long sectImageId) {
		return this.delete("com.ycnet.frms.mapper.CableSectionImageMapper.deleteByPrimaryKey",sectImageId);
	}

	@Override
	public int insert(CableSectionImage record) {
		return this.insert("com.ycnet.frms.mapper.CableSectionImageMapper.insert",record);
	}

	@Override
	public int insertSelective(CableSectionImage record) {
		return this.insert("com.ycnet.frms.mapper.CableSectionImageMapper.insertSelective",record);
	}

	@Override
	public CableSectionImage selectByPrimaryKey(Long sectImageId) {
		return this.selectOne("com.ycnet.frms.mapper.CableSectionImageMapper.selectByPrimaryKey",sectImageId);
	}

	@Override
	public int updateByPrimaryKeySelective(CableSectionImage record) {
		return this.update("com.ycnet.frms.mapper.CableSectionImageMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(CableSectionImage record) {
		return this.update("com.ycnet.frms.mapper.CableSectionImageMapper.updateByPrimaryKey",record);
	}

	/**
	 * 
	 * @Title: queryBySectId
	 * @Description: 根据光缆段ID查询图片数据
	 * @param @param sectId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月2日 下午5:12:32
	 * @version V1.0
	 */
	@Override
	public List<CableSectionImage> queryBySectId(Long sectId) {
		return this.selectList("com.ycnet.frms.mapper.CableSectionImageMapper.queryBySectId",sectId);
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
	 * @date 2018年1月2日 下午5:25:46
	 * @version V1.0
	 */
	@Override
	public int deleteImageBySectId(Long sectId) {
		return this.delete("com.ycnet.frms.mapper.CableSectionImageMapper.deleteImageBySectId",sectId);
	}

}
  
    