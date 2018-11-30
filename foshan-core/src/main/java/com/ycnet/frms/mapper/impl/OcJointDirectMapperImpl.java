/**   
 * @Package: com.ycnet.frms.mapper.impl 
 * @author: FL   
 * @date: 2018年10月17日 上午9:08:49 
 */
package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.OcJointDirect;
import com.ycnet.frms.mapper.OcJointDirectMapper;

/** 
* @ClassName: OcJointDirectMapperImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月17日 上午9:08:49  
*/
@Repository("ocJointDirectMapper")
public class OcJointDirectMapperImpl extends BaseSqlSupport implements OcJointDirectMapper{

	@Override
	public int deleteByPrimaryKey(Long jointId) {
		return this.delete("com.ycnet.frms.mapper.OcJointDirectMapper.deleteByPrimaryKey",jointId);
	}

	@Override
	public int insert(OcJointDirect record) {
		return this.insert("com.ycnet.frms.mapper.OcJointDirectMapper.insert",record);
	}

	@Override
	public int insertSelective(OcJointDirect record) {
		return this.insert("com.ycnet.frms.mapper.OcJointDirectMapper.insertSelective",record); 
	}

	@Override
	public OcJointDirect selectByPrimaryKey(Long jointId) {
		return this.selectOne("com.ycnet.frms.mapper.OcJointDirectMapper.selectByPrimaryKey",jointId);
	}

	@Override
	public int updateByPrimaryKeySelective(OcJointDirect record) {
		return this.update("com.ycnet.frms.mapper.OcJointDirectMapper.updateByPrimaryKeySelective",record);  
	}

	@Override
	public int updateByPrimaryKey(OcJointDirect record) {
		return this.update("com.ycnet.frms.mapper.OcJointDirectMapper.updateByPrimaryKey",record);
	}

	
	/** 
	 * 查询是否有直熔数据
	* @Title: queryByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return List<OcJointDirect>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月17日 上午9:12:06 
	* @version V1.0   
	*/
	/* (non-Javadoc)
	 * @see com.ycnet.frms.mapper.OcJointDirectMapper#queryByDevId(java.lang.Long)
	 */
	@Override
	public List<OcJointDirect> queryByDevId(Long devId) {
		return this.selectList("com.ycnet.frms.mapper.OcJointDirectMapper.queryByDevId",devId);
	}

	/** 
	 * 查询所选纤芯是否存在直熔的数据
	* @Title: queryBySectId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param sectIdA
	* @param @return    入参
	* @return List<OcJointDirect>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月17日 上午11:02:20 
	* @version V1.0   
	*/
	/* (non-Javadoc)
	 * @see com.ycnet.frms.mapper.OcJointDirectMapper#queryBySectId(java.lang.Long)
	 */
	@Override
	public Integer queryOcJointDirectByCondition(Long sectId, Integer start, Integer end, Long devId, Long jointId) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("sectId", sectId);
		map.put("start", start);
		map.put("end", end);
		map.put("devId", devId);
		map.put("jointId", jointId);
		return this.selectOne("com.ycnet.frms.mapper.OcJointDirectMapper.queryOcJointDirectByCondition",map);
	}
}
