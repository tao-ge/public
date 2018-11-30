package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.OcIdentif;
import com.ycnet.frms.vo.mobile.ocii.OcIdentifResult;

public interface OcIdentifMapper {
    int deleteByPrimaryKey(Long identId);

    int insert(OcIdentif record);

    int insertSelective(OcIdentif record);

    OcIdentif selectByPrimaryKey(Long identId);

    int updateByPrimaryKeySelective(OcIdentif record);

    int updateByPrimaryKey(OcIdentif record);

	/** 
	 * 根据分组ID,查询光缆识别仪
	* @Title: queryByGroupId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param groupId
	* @param @return    入参
	* @return List<OcIdentif>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月19日 上午9:30:11 
	* @version V1.0   
	*/
	List<OcIdentif> queryByGroupId(Long groupId);

	/** 
	 * 查询数据库该编码是否存在
	* @Title: queryByIdentCode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param identCode
	* @param @param orgId
	* @param @return    入参
	* @return List<OcIdentif>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月19日 上午9:47:42 
	* @version V1.0   
	*/
	List<OcIdentif> queryByIdentCode(String identCode, Long orgId);

	/**
	 * 根据ID查询讯识别仪信息
	* @Title: selectIdentifById 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param identId
	* @param @return    入参
	* @return OcIdentifResult    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年10月24日 上午11:34:30 
	* @version V1.0
	 */
	OcIdentifResult selectIdentifById(Long identId);
}