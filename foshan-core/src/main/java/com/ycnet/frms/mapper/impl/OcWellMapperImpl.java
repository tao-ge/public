/**   
 * @Package: com.ycnet.frms.mapper.impl 
 * @author: FL   
 * @date: 2018年10月10日 下午1:11:30 
 */
package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.OcWell;
import com.ycnet.frms.mapper.OcWellMapper;
import com.ycnet.frms.vo.mobile.WellRelationPipsBean;
import com.ycnet.frms.vo.mobile.WellSpacesBean;
import com.ycnet.frms.vo.mobile.ocii.IndexInBean;
import com.ycnet.frms.vo.mobile.ocii.IndexPoint;
import com.ycnet.frms.vo.mobile.ocii.OcSectBean;
import com.ycnet.frms.vo.mobile.ocii.OcWellBean;

/** 
* @ClassName: OcWellMapperImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月10日 下午1:11:30  
*/
@Repository("ocWellMapper")
public class OcWellMapperImpl extends BaseSqlSupport implements OcWellMapper{

	@Override
	public int deleteByPrimaryKey(Long wellId) {
		return this.delete("com.ycnet.frms.mapper.OcWellMapper.deleteByPrimaryKey",wellId);
	}

	@Override
	public int insert(OcWell record) {
		return this.insert("com.ycnet.frms.mapper.OcWellMapper.insert",record);
	}

	@Override
	public int insertSelective(OcWell record) {
		return this.insert("com.ycnet.frms.mapper.OcWellMapper.insertSelective",record); 
	}

	@Override
	public OcWell selectByPrimaryKey(Long wellId) {
		return this.selectOne("com.ycnet.frms.mapper.OcWellMapper.selectByPrimaryKey",wellId);
	}

	@Override
	public int updateByPrimaryKeySelective(OcWell record) {
		return this.update("com.ycnet.frms.mapper.OcWellMapper.updateByPrimaryKeySelective",record);  
	}

	@Override
	public int updateByPrimaryKey(OcWell record) {
		return this.update("com.ycnet.frms.mapper.OcWellMapper.updateByPrimaryKey",record);
	}

	/* (non-Javadoc)
	 * @see com.ycnet.frms.mapper.OcWellMapper#updateWellBean(com.ycnet.frms.bean.OcWell)
	 */
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
	@Override
	public int updateWellBean(OcWell well) {
		return this.update("com.ycnet.frms.mapper.OcWellMapper.updateWellBean",well);
	}

	/* (non-Javadoc)
	 * @see com.ycnet.frms.mapper.OcWellMapper#queryqueryOcciWellAndImg(java.lang.Long)
	 */
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
	@Override
	public OcWellBean queryqueryOcciWellAndImg(Long wellId) {
		return this.selectOne("com.ycnet.frms.mapper.OcWellMapper.queryqueryOcciWellAndImg",wellId);
	}

	
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
	/* (non-Javadoc)
	 * @see com.ycnet.frms.mapper.OcWellMapper#queryWellRelationPips(java.lang.Long, java.lang.String, java.lang.Long)
	 */
	@Override
	public List<WellRelationPipsBean> queryWellRelationPips(Long wellId, String isWell, Long orgId) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("wellId", wellId);
		map.put("isWell", isWell);
		map.put("orgId", orgId);
		return this.selectList("com.ycnet.frms.mapper.OcWellMapper.queryWellRelationPips",map);
	}

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
	/* (non-Javadoc)
	 * @see com.ycnet.frms.mapper.OcWellMapper#queryWellRelationSections(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<OcSectBean> queryWellRelationSections(Long wellId, Long orgId) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("wellId", wellId);
		map.put("orgId", orgId);
		return  this.selectList("com.ycnet.frms.mapper.OcWellMapper.queryWellRelationSections",map);
	}
	
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
	@Override
	public List<IndexPoint> OcIndexWellByType(IndexInBean iib) {
		return this.selectList("com.ycnet.frms.mapper.OcWellMapper.OcIndexWellByType",iib);
	}

	
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
	/* (non-Javadoc)
	 * @see com.ycnet.frms.mapper.OcWellMapper#queryOciiWellAndSpaces(java.lang.Long)
	 */
	@Override
	public WellSpacesBean queryOciiWellAndSpaces(Long wellId) {
		return this.selectOne("com.ycnet.frms.mapper.OcWellMapper.queryOciiWellAndSpaces", wellId);
	}

}
