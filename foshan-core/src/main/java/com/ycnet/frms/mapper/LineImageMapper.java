package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.LineImage;

public interface LineImageMapper {
    int deleteByPrimaryKey(Long lineImageId);

    int insert(LineImage record);

    int insertSelective(LineImage record);

    LineImage selectByPrimaryKey(Long lineImageId);

    int updateByPrimaryKeySelective(LineImage record);

    int updateByPrimaryKey(LineImage record);

    

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
     * @date 2017年12月23日 下午2:17:54
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
	 * @date 2017年12月23日 下午2:29:48
	 * @version V1.0
	 */
	List<LineImage> queryByLineId(Long lineId);

	/**
	 * 
	 * @Title: deleteByDevIdAndUrl
	 * @Description: 根据lineId和imgUrl删除端子图片信息
	 * @param @param map
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月17日 下午3:50:27
	 * @version V1.0
	 */
	int deleteByLineIdAndUrl(Map<String, Object> map);
}