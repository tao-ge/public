/**   
 * @Package: com.ycnet.frms.mapper.impl 
 * @author: FL   
 * @date: 2018年10月12日 下午3:19:24 
 */
package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.OcCableWell;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.OcCableWellMapper;
import com.ycnet.frms.vo.mobile.ocii.OcCableSectionInput;
import com.ycnet.frms.vo.mobile.ocii.OcIdentifPortResult;

/** 
* @ClassName: OcCableWellMapperImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月12日 下午3:19:24  
*/
@Repository("ocCableWellMapper")
public class OcCableWellMapperImpl extends BaseSqlSupport implements OcCableWellMapper{

	@Override
	public int deleteByPrimaryKey(Long cableWellId) {
		return this.delete("com.ycnet.frms.mapper.OcCableWellMapper.deleteByPrimaryKey",cableWellId);
	}

	@Override
	public int insert(OcCableWell record) {
		return this.insert("com.ycnet.frms.mapper.OcCableWellMapper.insert",record);
	}

	@Override
	public int insertSelective(OcCableWell record) {
		return this.insert("com.ycnet.frms.mapper.OcCableWellMapper.insertSelective",record); 
	}

	@Override
	public OcCableWell selectByPrimaryKey(Long cableWellId) {
		return this.selectOne("com.ycnet.frms.mapper.OcCableWellMapper.selectByPrimaryKey",cableWellId);
	}

	@Override
	public int updateByPrimaryKeySelective(OcCableWell record) {
		return this.update("com.ycnet.frms.mapper.OcCableWellMapper.updateByPrimaryKeySelective",record);  
	}

	@Override
	public int updateByPrimaryKey(OcCableWell record) {
		return this.update("com.ycnet.frms.mapper.OcCableWellMapper.updateByPrimaryKey",record);
	}

	/* (non-Javadoc)
	 * @see com.ycnet.frms.mapper.OcCableWellMapper#queryOcciCableWell(java.lang.Long)
	 */
	/** 
	 * 根据ID查询物理光缆段信息
	* @Title: queryOcciCableWell 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param sectId
	* @param @return    入参
	* @return List<Map<String,Object>>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月12日 下午3:17:43 
	* @version V1.0   
	*/
	@Override
	public List<Map<String, Object>> queryOcciCableWell(Long sectId) {
		return this.selectList("com.ycnet.frms.mapper.OcCableWellMapper.queryOcciCableWell",sectId);
	}

	
	/** 
	 * 根据井ID,查询是否有光缆
	* @Title: queryOcCableWellByWellId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wellId
	* @param @return    入参
	* @return List<OcCableWell>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月15日 下午4:49:24 
	* @version V1.0   
	*/
	/* (non-Javadoc)
	 * @see com.ycnet.frms.mapper.OcCableWellMapper#queryOcCableWellByWellId(java.lang.Long)
	 */
	@Override
	public List<OcCableWell> queryOcCableWellByWellId(Long wellId, Long orgId) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("wellId", wellId);
		map.put("orgId", orgId);
		return this.selectList("com.ycnet.frms.mapper.OcCableWellMapper.queryOcCableWellByWellId",map);
	}

	/** 
	 * 根据设施ID,查询识别光缆数
	* @Title: queryOcCableWellByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return OcCableWell    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月16日 上午9:59:14 
	* @version V1.0   
	*/
	/* (non-Javadoc)
	 * @see com.ycnet.frms.mapper.OcCableWellMapper#queryOcCableWellByDevId(java.lang.Long)
	 */
	@Override
	public Map<String, Object> queryOcCableWellByDevId(Long devId,String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("devId", devId);
		map.put("type", type);
		return this.selectOne("com.ycnet.frms.mapper.OcCableWellMapper.queryOcCableWellByDevId",map);
	}

	/**
	 * 根据设施或者井ID查询光缆点信息
	* @Title: selectCableByWellId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param identId
	* @param @param type
	* @param @return    入参
	* @return List<OcCableWell>    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年10月24日 下午1:31:39 
	* @version V1.0
	 */
	@Override
	public List<OcCableWell> selectCableByWellId(Long wellDevId, String type) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("wellDevId", wellDevId);
		map.put("type", type);
		return this.selectList("com.ycnet.frms.mapper.OcCableWellMapper.selectCableByWellId",map);
	}

	/**
	 * 修改光缆点总数 
	* @Title: updateByDevWellId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wellDevId
	* @param @param type
	* @param @param sectNum
	* @param @return    入参
	* @return int    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年10月24日 下午2:02:52 
	* @version V1.0
	 */
	@Override
	public int updateByDevWellId(Long wellDevId, String type, Integer sectPointNo) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("wellDevId", wellDevId);
		map.put("type", type);
		map.put("sectPointNo", sectPointNo);
		return this.update("com.ycnet.frms.mapper.OcCableWellMapper.updateByDevWellId",map);  
	}

	/**
	 * 根据设施或者井ID查询端口信息 - 接口专用
	* @Title: selectCableByWellIdResult 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wellDevId
	* @param @param type
	* @param @return    入参
	* @return List<OcIdentifPortResult>    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年10月24日 下午2:52:36 
	* @version V1.0
	 */
	@Override
	public List<OcIdentifPortResult> selectCableByWellIdResult(Long wellDevId, String type) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("wellDevId", wellDevId);
		map.put("type", type);
		return this.selectList("com.ycnet.frms.mapper.OcCableWellMapper.selectCableByWellIdResult",map);
	}

	/** 
	 * 设备-根据设施ID查询光缆段列表
	* @Title: querySectByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param oi
	* @param @param user
	* @param @return    入参
	* @return Map<String,Object>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月25日 下午2:00:36 
	* @version V1.0   
	*/
	/* (non-Javadoc)
	 * @see com.ycnet.frms.mapper.OcCableWellMapper#querySectByDevId(com.ycnet.frms.vo.mobile.ocii.OcCableSectionInput, com.ycnet.frms.bean.Users)
	 */
	@Override
	public List<Map<String, Object>> querySectByDevId(OcCableSectionInput oi, Users user) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("oi", oi);
		map.put("user", user);
		return this.selectList("com.ycnet.frms.mapper.OcCableWellMapper.querySectByDevId",map);
	}

}
