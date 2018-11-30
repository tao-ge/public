package com.ycnet.frms.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.LineImage;
import com.ycnet.frms.mapper.LineImageMapper;
@Repository("lineImageMapper")
public class LineImageMapperImpl extends BaseSqlSupport implements LineImageMapper{

	@Override
	public int deleteByPrimaryKey(Long lineImageId) {
		return this.delete("com.ycnet.frms.mapper.LineImageMapper.deleteByPrimaryKey",lineImageId);
	}

	@Override
	public int insert(LineImage record) {
		return this.insert("com.ycnet.frms.mapper.LineImageMapper.insert",record);
	}

	@Override
	public int insertSelective(LineImage record) {
		return this.insert("com.ycnet.frms.mapper.LineImageMapper.insertSelective",record);
	}

	@Override
	public LineImage selectByPrimaryKey(Long lineImageId) {
		return this.selectOne("com.ycnet.frms.mapper.LineImageMapper.selectByPrimaryKey",lineImageId);
	}

	@Override
	public int updateByPrimaryKeySelective(LineImage record) {
		return this.update("com.ycnet.frms.mapper.LineImageMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(LineImage record) {
		return this.update("com.ycnet.frms.mapper.LineImageMapper.updateByPrimaryKey",record);
	}


	/**
	 * 
	 * @Title: deleteImageByLineId
	 * @Description: 根据lineId删除图片
	 * @param @param lineId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月23日 下午3:26:37
	 * @version V1.0
	 */
	@Override
	public int deleteImageByLineId(Long lineId) {
		return this.delete("com.ycnet.frms.mapper.LineImageMapper.deleteImageByLineId",lineId);
	}

	/**
	 * 
	 * @Title: queryByLineId
	 * @Description: 根据lineId查询图片数据
	 * @param @param lineId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月23日 下午3:26:45
	 * @version V1.0
	 */
	@Override
	public List<LineImage> queryByLineId(Long lineId) {
		return this.selectList("com.ycnet.frms.mapper.LineImageMapper.queryByLineId",lineId);
	}

	/**
	 * 
	 * @Title: deleteByDevIdAndUrl
	 * @Description: 根据lineId和imgUrl删除端子图片信息
	 * @param @param map
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月17日 下午3:50:48
	 * @version V1.0
	 */
	@Override
	public int deleteByLineIdAndUrl(Map<String, Object> map) {
		return this.delete("com.ycnet.frms.mapper.LineImageMapper.deleteByLineIdAndUrl", map);
	}

}
