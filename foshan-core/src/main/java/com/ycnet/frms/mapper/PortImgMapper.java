package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.PortImg;

public interface PortImgMapper {
    int deleteByPrimaryKey(Long portImgId);

    int insert(PortImg record);

    int insertSelective(PortImg record);

    PortImg selectByPrimaryKey(Long portImgId);

    int updateByPrimaryKeySelective(PortImg record);

    int updateByPrimaryKey(PortImg record);

    /**
     * 
    * @Title: queryImgPaths 
    * @Description: 根据端子查询图片路径 
    * @param @param devId
    * @param @param port01
    * @param @return    
    * @return List<PortImg>
    * @author liucanghai 
    * @throws
    * @date 2017年12月4日 下午4:44:42 
    * @version V1.0
     */
	List<PortImg> queryImgPaths(Long devId, String port01);

	int deleteByUrl(String path);

}