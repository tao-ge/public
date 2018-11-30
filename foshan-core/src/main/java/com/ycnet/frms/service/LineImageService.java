package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.frms.bean.LineImage;

public interface LineImageService {

	/**
	 * 
	 * @Title: deleteImage
	 * @Description: 根据lineId删除图片
	 * @param @param lineId
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月23日 下午2:17:08
	 * @version V1.0
	 */
	int deleteImageByLineId(Long lineId);

	/**
	 * 
	 * @Title: queryByLineId
	 * @Description: 根据lineId查询图片数据
	 * @param @param lineId
	 * @param @return 
	 * @return List<LineImage> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月23日 下午2:29:01
	 * @version V1.0
	 */
	List<LineImage> queryByLineId(Long lineId);

	/**
	 * 
	 * @Title: insertSelective
	 * @Description: 保存图片
	 * @param @param lineImage
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月23日 下午2:47:11
	 * @version V1.0
	 */
	int insertSelective(LineImage lineImage);

}
  
    