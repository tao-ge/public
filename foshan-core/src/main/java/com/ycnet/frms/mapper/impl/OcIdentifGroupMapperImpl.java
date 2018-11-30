/**   
 * @Package: com.ycnet.frms.mapper.impl 
 * @author: FL   
 * @date: 2018年10月19日 上午8:57:15 
 */
package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.OcIdentifGroup;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.OcIdentifGroupMapper;

/** 
* @ClassName: OcIdentifGroupMapperImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月19日 上午8:57:15  
*/
@Repository("ocIdentifGroupMapper")
public class OcIdentifGroupMapperImpl extends BaseSqlSupport implements OcIdentifGroupMapper{
	
	@Override
	public int deleteByPrimaryKey(Long groupId) {
		return this.delete("com.ycnet.frms.mapper.OcIdentifGroupMapper.deleteByPrimaryKey",groupId);
	}

	@Override
	public int insert(OcIdentifGroup record) {
		return this.insert("com.ycnet.frms.mapper.OcIdentifGroupMapper.insert",record);
	}

	@Override
	public int insertSelective(OcIdentifGroup record) {
		return this.insert("com.ycnet.frms.mapper.OcIdentifGroupMapper.insertSelective",record); 
	}

	@Override
	public OcIdentifGroup selectByPrimaryKey(Long groupId) {
		return this.selectOne("com.ycnet.frms.mapper.OcIdentifGroupMapper.selectByPrimaryKey",groupId);
	}

	@Override
	public int updateByPrimaryKeySelective(OcIdentifGroup record) {
		return this.update("com.ycnet.frms.mapper.OcIdentifGroupMapper.updateByPrimaryKeySelective",record);  
	}

	@Override
	public int updateByPrimaryKey(OcIdentifGroup record) {
		return this.update("com.ycnet.frms.mapper.OcIdentifGroupMapper.updateByPrimaryKey",record);
	}

	/** 
	 * 查询识别仪分组列表
	* @Title: queryIdentifGroup 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param ocIdentifGroup
	* @param @param user
	* @param @return    入参
	* @return List<OcIdentifGroup>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月19日 上午10:10:57 
	* @version V1.0   
	*/
	/* (non-Javadoc)
	 * @see com.ycnet.frms.mapper.OcIdentifGroupMapper#queryIdentifGroup(com.ycnet.frms.bean.OcIdentifGroup, com.ycnet.frms.bean.Users)
	 */
	@Override
	public List<OcIdentifGroup> queryIdentifGroup(OcIdentifGroup ocIdentifGroup, Users user) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("ocIdentifGroup",ocIdentifGroup);
		map.put("orgId",user.getOrgId());
		return this.selectList("com.ycnet.frms.mapper.OcIdentifGroupMapper.queryIdentifGroup",map);
	}

}
