/**   
 * @Package: com.ycnet.frms.mapper.impl 
 * @author: FL   
 * @date: 2018年10月15日 上午11:56:03 
 */
package com.ycnet.frms.mapper.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.OcPipingCable;
import com.ycnet.frms.mapper.OcPipingCableMapper;

/** 
* @ClassName: OcPipingCableMapperImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月15日 上午11:56:03  
*/
@Repository("ocPipingCableMapper")
public class OcPipingCableMapperImpl extends BaseSqlSupport implements OcPipingCableMapper{
	
	@Override
	public int deleteByPrimaryKey(Long subtubeRefSectId) {
		return this.delete("com.ycnet.frms.mapper.OcPipingCableMapper.deleteByPrimaryKey",subtubeRefSectId);
	}

	@Override 
	public int insert(OcPipingCable record) {
		return this.insert("com.ycnet.frms.mapper.OcPipingCableMapper.insert",record);
	}

	@Override
	public int insertSelective(OcPipingCable record) {
		return this.insert("com.ycnet.frms.mapper.OcPipingCableMapper.insertSelective",record); 
	}

	@Override
	public OcPipingCable selectByPrimaryKey(Long subtubeRefSectId) {
		return this.selectOne("com.ycnet.frms.mapper.OcPipingCableMapper.selectByPrimaryKey",subtubeRefSectId);
	}

	@Override
	public int updateByPrimaryKeySelective(OcPipingCable record) {
		return this.update("com.ycnet.frms.mapper.OcPipingCableMapper.updateByPrimaryKeySelective",record);  
	}

	@Override
	public int updateByPrimaryKey(OcPipingCable record) {
		return this.update("com.ycnet.frms.mapper.OcPipingCableMapper.updateByPrimaryKey",record);
	}

	
	/** 
	 * 查询光缆段与管道段大孔关系表
	* @Title: selectBySectId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param sectId
	* @param @return    入参
	* @return List<OcPipingCable>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月15日 下午1:07:00 
	* @version V1.0   
	*/
	/* (non-Javadoc)
	 * @see com.ycnet.frms.mapper.OcPipingCableMapper#selectBySectId(java.lang.Long)
	 */
	@Override
	public List<OcPipingCable> selectBySectId(Long sectId) {
		return this.selectList("com.ycnet.frms.mapper.OcPipingCableMapper.selectBySectId",sectId);
	}

}
