/**   
 * @Package: com.ycnet.frms.mapper.impl 
 * @author: FL   
 * @date: 2018年10月10日 下午2:03:08 
 */
package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.OcPiping;
import com.ycnet.frms.mapper.OcPipingMapper;
import com.ycnet.frms.vo.mobile.ocii.OcPipingResult;

/** 
* @ClassName: OcPipingMapperImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月10日 下午2:03:08  
*/
@Repository("ocPipingMapper")
public class OcPipingMapperImpl extends BaseSqlSupport implements OcPipingMapper{
	@Override
	public int deleteByPrimaryKey(Long pipingId) {
		return this.delete("com.ycnet.frms.mapper.OcPipingMapper.deleteByPrimaryKey",pipingId);
	}

	@Override
	public int insert(OcPiping record) {
		return this.insert("com.ycnet.frms.mapper.OcPipingMapper.insert",record);
	}

	@Override
	public int insertSelective(OcPiping record) {
		return this.insert("com.ycnet.frms.mapper.OcPipingMapper.insertSelective",record); 
	}

	@Override
	public OcPiping selectByPrimaryKey(Long pipingId) {
		return this.selectOne("com.ycnet.frms.mapper.OcPipingMapper.selectByPrimaryKey",pipingId);
	}

	@Override
	public int updateByPrimaryKeySelective(OcPiping record) {
		return this.update("com.ycnet.frms.mapper.OcPipingMapper.updateByPrimaryKeySelective",record);  
	}

	@Override
	public int updateByPrimaryKey(OcPiping record) {
		return this.update("com.ycnet.frms.mapper.OcPipingMapper.updateByPrimaryKey",record);
	}

	/* (non-Javadoc)
	 * @see com.ycnet.frms.mapper.OcPipingMapper#queryOciiPipingSections(java.lang.Long, java.lang.Long)
	 */
	/** 
	 * 查询光交箱下的管道段
	* @Title: queryOciiPipingSections 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @param orgId
	* @param @return    入参
	* @return List<Map<String,Object>>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月11日 下午4:42:02 
	* @version V1.0   
	*/
	@Override
	public List<Map<String, Object>> queryOciiPipingSections(Long devId, Long orgId) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("devId", devId);
		map.put("orgId", orgId);
		return this.selectList("com.ycnet.frms.mapper.OcPipingMapper.queryOciiPipingSections",map);
	}

	/* (non-Javadoc)
	 * @see com.ycnet.frms.mapper.OcPipingMapper#queryOcPipingById(java.lang.Long)
	 */
	/** 
	 * 查询大孔信息
	* @Title: queryOcPipingById 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param pipingId
	* @param @return    入参
	* @return Map<String,Object>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月12日 下午2:22:57 
	* @version V1.0   
	*/
	@Override
	public Map<String, Object> queryOcPipingById(Long pipingId) {
		return this.selectOne("com.ycnet.frms.mapper.OcPipingMapper.queryOcPipingById",pipingId);
	}

	
	/**
	 * 根据管道段ID删除管道段
	* @Title: queryOcPipingByPipingSectId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param pipingId
	* @param @return    入参
	* @return List<OcPiping>    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年10月19日 下午1:45:34 
	* @version V1.0
	 */
	@Override
	public List<OcPipingResult> queryOcPipingByPipingSectId(Long pipingSectId) {
		// TODO Auto-generated method stub
		return this.selectList("com.ycnet.frms.mapper.OcPipingMapper.queryOcPipingByPipingSectId",pipingSectId);
	}
}
