package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.OcSpace;
import com.ycnet.frms.vo.mobile.OcSpaceBean;
import com.ycnet.frms.vo.mobile.ocii.OcSpaceResult;

public interface OcSpaceMapper {
    int deleteByPrimaryKey(Integer spaceId);

    int insert(OcSpace record);

    int insertSelective(OcSpace record);

    OcSpace selectByPrimaryKey(Integer spaceId);

    int updateByPrimaryKeySelective(OcSpace record);

    int updateByPrimaryKey(OcSpace record);

	/** 
	 * 查询分面及下属大孔信息
	* @Title: querySpaceAndPips 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wellId
	* @param @return    入参
	* @return List<OcSpaceBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月11日 上午10:16:14 
	* @version V1.0   
	*/
	List<OcSpaceBean> querySpaceAndPips(Long wellId);

	/** 
	 * 根据井ID,删除分面
	* @Title: deleteByWellId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wellId
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月15日 下午4:11:02 
	* @version V1.0   
	*/
	int deleteByWellId(Long wellId);

	/**
	 * 根据井ID查询分面信息
	* @Title: selectSpaceByWellId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wellId
	* @param @return    入参
	* @return List<OcSpaceResult>    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年10月22日 下午1:25:35 
	* @version V1.0
	 */
	List<OcSpaceResult> selectSpaceByWellId(Long wellId);
}