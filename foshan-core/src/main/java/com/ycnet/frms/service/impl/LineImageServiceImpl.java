package com.ycnet.frms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.frms.bean.LineImage;
import com.ycnet.frms.mapper.LineImageMapper;
import com.ycnet.frms.service.LineImageService;

@Service("lineImageService")
@Transactional
public class LineImageServiceImpl implements LineImageService{

	@Resource(name="lineImageMapper")
	private LineImageMapper lineImageMapper;

	/**
	 * 
	 * @Title: deleteImageByLineId
	 * @Description: 根据lineId删除图片
	 * @param @param lineId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月23日 下午3:25:50
	 * @version V1.0
	 */
	@Override
	public int deleteImageByLineId(Long lineId) {
		return lineImageMapper.deleteImageByLineId(lineId);
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
	 * @date 2017年12月23日 下午3:25:59
	 * @version V1.0
	 */
	@Override
	public List<LineImage> queryByLineId(Long lineId) {
		return lineImageMapper.queryByLineId(lineId);
	}

	/**
	 * 
	 * @Title: insertSelective
	 * @Description: 保存图片
	 * @param @param lineImage
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月23日 下午3:26:19
	 * @version V1.0
	 */
	@Override
	public int insertSelective(LineImage lineImage) {
		return lineImageMapper.insertSelective(lineImage);
	}
}
  
    