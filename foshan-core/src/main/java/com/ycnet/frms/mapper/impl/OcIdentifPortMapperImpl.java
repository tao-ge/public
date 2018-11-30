/**   
 * @Package: com.ycnet.frms.mapper.impl 
 * @author: FL   
 * @date: 2018年10月19日 下午12:12:24 
 */
package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.OcIdentifPort;
import com.ycnet.frms.mapper.OcIdentifPortMapper;
import com.ycnet.frms.vo.mobile.ocii.OcIdentifPortResult;

/** 
* @ClassName: OcIdentifPortMapperImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月19日 下午12:12:24  
*/
@Repository("ocIdentifPortMapper")
public class OcIdentifPortMapperImpl extends BaseSqlSupport implements OcIdentifPortMapper{
	
	@Override
	public int deleteByPrimaryKey(Long portId) {
		return this.delete("com.ycnet.frms.mapper.OcIdentifPortMapper.deleteByPrimaryKey",portId);
	}

	@Override
	public int insert(OcIdentifPort record) {
		return this.insert("com.ycnet.frms.mapper.OcIdentifPortMapper.insert",record);
	}

	@Override
	public int insertSelective(OcIdentifPort record) {
		return this.insert("com.ycnet.frms.mapper.OcIdentifPortMapper.insertSelective",record); 
	}

	@Override
	public OcIdentifPort selectByPrimaryKey(Long portId) {
		return this.selectOne("com.ycnet.frms.mapper.OcIdentifPortMapper.selectByPrimaryKey",portId);
	}

	@Override
	public int updateByPrimaryKeySelective(OcIdentifPort record) {
		return this.update("com.ycnet.frms.mapper.OcIdentifPortMapper.updateByPrimaryKeySelective",record);  
	}

	@Override
	public int updateByPrimaryKey(OcIdentifPort record) {
		return this.update("com.ycnet.frms.mapper.OcIdentifPortMapper.updateByPrimaryKey",record);
	}

	/**
     * 根据ID查询端口信息
    * @Title: selectPortByIdentifId 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param identId
    * @param @return    入参
    * @return List<OcIdentifPort>    返回类型
    * @author YHT（作者） 
    * @throws
    * @date 2018年10月24日 下午2:39:11 
    * @version V1.0
     */
	@Override
	public List<OcIdentifPort> selectPortByIdentifId(Long identId) {
		// TODO Auto-generated method stub
		return this.selectList("com.ycnet.frms.mapper.OcIdentifPortMapper.selectPortByIdentifId",identId);
	}

	/**
	 * 接口 - 查询端口详情
	* @Title: selectPortByIdentIdAndWellId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wellDevId
	* @param @param type
	* @param @param identId
	* @param @return    入参
	* @return List<OcIdentifPortResult>    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年10月25日 下午1:52:29 
	* @version V1.0
	 */
	@Override
	public List<OcIdentifPortResult> selectPortByIdentIdAndWellId(Long wellDevId, String type, Long identId) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("wellDevId", wellDevId);
		map.put("type", type);
		map.put("identId", identId);
		return this.selectList("com.ycnet.frms.mapper.OcIdentifPortMapper.selectPortByIdentIdAndWellId",map);
	}
}
