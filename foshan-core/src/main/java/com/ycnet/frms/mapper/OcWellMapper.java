package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.OcWell;
import com.ycnet.frms.vo.mobile.WellRelationPipsBean;
import com.ycnet.frms.vo.mobile.WellSpacesBean;
import com.ycnet.frms.vo.mobile.ocii.IndexInBean;
import com.ycnet.frms.vo.mobile.ocii.IndexPoint;
import com.ycnet.frms.vo.mobile.ocii.OcSectBean;
import com.ycnet.frms.vo.mobile.ocii.OcWellBean;

public interface OcWellMapper {
    int deleteByPrimaryKey(Long wellId);

    int insert(OcWell record);

    int insertSelective(OcWell record);

    OcWell selectByPrimaryKey(Long wellId);

    int updateByPrimaryKeySelective(OcWell record);

    int updateByPrimaryKey(OcWell record);

	/** 
	 * 修改井
	* @Title: updateWellBean 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param well
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月10日 下午1:18:59 
	* @version V1.0   
	*/
	int updateWellBean(OcWell well);

	/** 
	 * 查询井信息和图片集合
	* @Title: queryqueryOcciWellAndImg 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wellId
	* @param @return    入参
	* @return OcWellBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月10日 下午4:36:02 
	* @version V1.0   
	*/
	OcWellBean queryqueryOcciWellAndImg(Long wellId);

	/** 
	 * 查询井关联的管道段
	* @Title: queryWellRelationPips 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wellId
	* @param @param isWell
	* @param @param orgId
	* @param @return    入参
	* @return List<WellRelationPipsBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月15日 上午9:32:50 
	* @version V1.0   
	*/
	List<WellRelationPipsBean> queryWellRelationPips(Long wellId, String isWell, Long orgId);

	/** 
	 * 井所关联的光缆段信息
	* @Title: queryWellRelationSections 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wellId
	* @param @param orgId
	* @param @return    入参
	* @return List<OcSectBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月15日 上午10:46:21 
	* @version V1.0   
	*/
	List<OcSectBean> queryWellRelationSections(Long wellId, Long orgId);

	/**
	 * 通过坐标查询首页井信息
	* @Title: OcIndexWellByType 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param iib
	* @param @return    入参
	* @return List<IndexPoint>    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年10月16日 下午3:01:54 
	* @version V1.0
	 */
	List<IndexPoint> OcIndexWellByType(IndexInBean iib);

	/** 
	 * 根据井ID查询井和其面信息
	* @Title: queryOciiWellAndSpaces 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wellId
	* @param @return    入参
	* @return WellSpacesBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月19日 下午3:41:14 
	* @version V1.0   
	*/
	WellSpacesBean queryOciiWellAndSpaces(Long wellId);
}