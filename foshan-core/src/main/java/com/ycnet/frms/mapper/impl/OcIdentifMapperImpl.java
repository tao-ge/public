/**   
 * @Package: com.ycnet.frms.mapper.impl 
 * @author: FL   
 * @date: 2018年10月19日 上午9:30:53 
 */
package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.OcIdentif;
import com.ycnet.frms.mapper.OcIdentifMapper;
import com.ycnet.frms.vo.mobile.ocii.OcIdentifResult;

/** 
* @ClassName: OcIdentifMapperImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月19日 上午9:30:53  
*/
@Repository("ocIdentifMapper")
public class OcIdentifMapperImpl extends BaseSqlSupport implements OcIdentifMapper {
	
	@Override
	public int deleteByPrimaryKey(Long identId) {
		return this.delete("com.ycnet.frms.mapper.OcIdentifMapper.deleteByPrimaryKey",identId);
	}

	@Override
	public int insert(OcIdentif record) {
		return this.insert("com.ycnet.frms.mapper.OcIdentifMapper.insert",record);
	}

	@Override
	public int insertSelective(OcIdentif record) {
		return this.insert("com.ycnet.frms.mapper.OcIdentifMapper.insertSelective",record); 
	}

	@Override
	public OcIdentif selectByPrimaryKey(Long identId) {
		return this.selectOne("com.ycnet.frms.mapper.OcIdentifMapper.selectByPrimaryKey",identId);
	}

	@Override
	public int updateByPrimaryKeySelective(OcIdentif record) {
		return this.update("com.ycnet.frms.mapper.OcIdentifMapper.updateByPrimaryKeySelective",record);  
	}

	@Override
	public int updateByPrimaryKey(OcIdentif record) {
		return this.update("com.ycnet.frms.mapper.OcIdentifMapper.updateByPrimaryKey",record);
	}

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
	/* (non-Javadoc)
	 * @see com.ycnet.frms.mapper.OcIdentifMapper#queryByGroupId(java.lang.Long)
	 */
	@Override
	public List<OcIdentif> queryByGroupId(Long groupId) {
		return this.selectList("com.ycnet.frms.mapper.OcIdentifMapper.queryByGroupId",groupId);
	}

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
	/* (non-Javadoc)
	 * @see com.ycnet.frms.mapper.OcIdentifMapper#queryByIdentCode(java.lang.String, java.lang.Long)
	 */
	@Override
	public List<OcIdentif> queryByIdentCode(String identCode, Long orgId) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("identCode", identCode);
		map.put("orgId", orgId);
		return this.selectList("com.ycnet.frms.mapper.OcIdentifMapper.queryByIdentCode",map);
	}

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
	@Override
	public OcIdentifResult selectIdentifById(Long identId) {
		// TODO Auto-generated method stub
		return this.selectOne("com.ycnet.frms.mapper.OcIdentifMapper.selectIdentifById",identId);
	}


}
