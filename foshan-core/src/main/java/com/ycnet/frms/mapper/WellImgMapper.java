package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.WellImg;

public interface WellImgMapper {
    int deleteByPrimaryKey(Long wellImgId);

    int insert(WellImg record);

    int insertSelective(WellImg record);

    WellImg selectByPrimaryKey(Long wellImgId);

    int updateByPrimaryKeySelective(WellImg record);

    int updateByPrimaryKey(WellImg record);

    /**
     * 根据设施ID,查询WellImg列表
    * @Title: selectByWellId 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param wellId
    * @param @return    入参
    * @return List<WellImg>    返回类型
    * @author FL（作者） 
    * @throws
    * @date 2018年3月20日 下午2:04:19 
    * @version V1.0
     */
	List<WellImg> selectByWellId(Long wellId);

	/**
	 * 根据井ID,删除WellImg列表
	* @Title: deleteByWellId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wellId
	* @param @return    入参
	* @return long    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年3月20日 下午3:17:32 
	* @version V1.0
	 */
	long deleteByWellId(Long wellId);
}