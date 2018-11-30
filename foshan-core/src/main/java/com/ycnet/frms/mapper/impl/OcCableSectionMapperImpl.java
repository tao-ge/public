/**   
 * @Package: com.ycnet.frms.mapper.impl 
 * @author: FL   
 * @date: 2018年10月11日 下午4:03:53 
 */
package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.OcCableSection;
import com.ycnet.frms.mapper.OcCableSectionMapper;
import com.ycnet.frms.vo.mobile.ocii.IndexInBean;
import com.ycnet.frms.vo.mobile.ocii.IndexLine;
import com.ycnet.frms.vo.mobile.ocii.OcSectBean;

/** 
* @ClassName: OcCableSectionMapperImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月11日 下午4:03:53  
*/
@Repository("ocCableSectionMapper")
public class OcCableSectionMapperImpl extends BaseSqlSupport implements OcCableSectionMapper{

	@Override
	public int deleteByPrimaryKey(Long sectId) {
		return this.delete("com.ycnet.frms.mapper.OcCableSectionMapper.deleteByPrimaryKey",sectId);
	}

	@Override
	public int insert(OcCableSection record) {
		return this.insert("com.ycnet.frms.mapper.OcCableSectionMapper.insert",record);
	}

	@Override
	public int insertSelective(OcCableSection record) {
		return this.insert("com.ycnet.frms.mapper.OcCableSectionMapper.insertSelective",record); 
	}

	@Override
	public OcCableSection selectByPrimaryKey(Long sectId) {
		return this.selectOne("com.ycnet.frms.mapper.OcCableSectionMapper.selectByPrimaryKey",sectId);
	}

	@Override
	public int updateByPrimaryKeySelective(OcCableSection record) {
		return this.update("com.ycnet.frms.mapper.OcCableSectionMapper.updateByPrimaryKeySelective",record);  
	}

	@Override
	public int updateByPrimaryKey(OcCableSection record) {
		return this.update("com.ycnet.frms.mapper.OcCableSectionMapper.updateByPrimaryKey",record);
	}

	/* (non-Javadoc)
	 * @see com.ycnet.frms.mapper.OcCableSectionMapper#queryOciiFacilityByPdevId(java.lang.Long)
	 */
	/** 
	 * 查询光交箱下的关联光缆段
	* @Title: queryOciiFacilityByPdevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return List<OcSectBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月11日 下午4:03:02 
	* @version V1.0   
	*/
	@Override
	public List<OcSectBean> queryOciiFacilityByPdevId(Long devId,Long orgId) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("devId", devId);
		map.put("orgId", orgId);
		return this.selectList("com.ycnet.frms.mapper.OcCableSectionMapper.queryOciiFacilityByPdevId",map);
	}

	/**
     * 根据条件查询管道段两端
    * @Title: OcIndexPipByType 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param iib
    * @param @return    入参
    * @return List<IndexLine>    返回类型
    * @author YHT（作者） 
    * @throws
    * @date 2018年10月17日 上午9:06:44 
    * @version V1.0
     */
	@Override
	public List<IndexLine> OcIndexSectByType(IndexInBean iib) {
		// TODO Auto-generated method stub
		return this.selectList("com.ycnet.frms.mapper.OcCableSectionMapper.OcIndexSectByType",iib);
	}
}
