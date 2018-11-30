package com.ycnet.frms.mapper.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.HardwareUpgradEntity;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.HardwareUpgradEntityMapper;

@Repository("hardwareUpgradEntityMapper")
public class HardwareUpgradEntityMapperImpl extends BaseSqlSupport implements HardwareUpgradEntityMapper{

	@Override
	public int deleteByPrimaryKey(Long hardId) {
		return this.delete("com.ycnet.frms.mapper.HardwareUpgradEntityMapper.deleteByPrimaryKey",hardId);
	}

	@Override
	public int insert(HardwareUpgradEntity record) {
		return this.insert("com.ycnet.frms.mapper.HardwareUpgradEntityMapper.insert",record);
	}

	@Override
	public int insertSelective(HardwareUpgradEntity record) {
		return this.insert("com.ycnet.frms.mapper.HardwareUpgradEntityMapper.insertSelective",record); 
	}

	@Override
	public HardwareUpgradEntity selectByPrimaryKey(Long hardId) {
		return this.selectOne("com.ycnet.frms.mapper.HardwareUpgradEntityMapper.selectByPrimaryKey",hardId);
	}

	@Override
	public int updateByPrimaryKeySelective(HardwareUpgradEntity record) {
		return this.update("com.ycnet.frms.mapper.HardwareUpgradEntityMapper.updateByPrimaryKeySelective",record);  
	}

	@Override
	public int updateByPrimaryKey(HardwareUpgradEntity record) {
		return this.update("com.ycnet.frms.mapper.HardwareUpgradEntityMapper.updateByPrimaryKey",record);
	}

	/**
	 * 
	 * @Title: queryRemoteUnlockList
	 * @Description: 设备远程更新列表查询
	 * @param @param map
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月15日 下午5:23:11
	 * @version V1.0
	 */
	@Override
	public List<HardwareUpgradEntity> queryRemoteUnlockList(Map<String, Object> map) {
		return this.selectList("com.ycnet.frms.mapper.HardwareUpgradEntityMapper.queryRemoteUnlockList",map);
	}

	/**
	 * 
	 * @Title: queryCountqueryRemoteUnlock
	 * @Description: 设备远程更新列表数量查询
	 * @param @param map
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月15日 下午5:24:06
	 * @version V1.0
	 */
	@Override
	public Integer queryCountqueryRemoteUnlock(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.HardwareUpgradEntityMapper.queryCountqueryRemoteUnlock",map);
	}

	/**
	 * 
	 * @Title: queryByHardVersion
	 * @Description: 根据硬件版本号查看是否重复
	 * @param @param hardVersion
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月16日 上午10:40:49
	 * @version V1.0
	 */
	@Override
	public int queryByHardVersion(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.HardwareUpgradEntityMapper.queryByHardVersion",map);
	}

	/**
	 * 
	 * @Title: queryMaxVersion
	 * @Description: 查询组织机构下最大版本号
	 * @param @param user
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月16日 下午1:41:42
	 * @version V1.0
	 */
	@Override
	public String queryMaxVersion(Users user) {
		return this.selectOne("com.ycnet.frms.mapper.HardwareUpgradEntityMapper.queryMaxVersion",user);
	}

	/**
	 * 
	 * @Title: queryByFileName
	 * @Description: 根据文件名称模糊查询硬件版本数量
	 * @param @param filename
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月21日 上午9:53:51
	 * @version V1.0
	 */
	@Override
	public int queryByFileName(String filename) {
		return this.selectOne("com.ycnet.frms.mapper.HardwareUpgradEntityMapper.queryByFileName",filename);
	}

	/**
	 * 
	 * @Title: queryByHardVersionAndOrgId
	 * @Description: 根据版本号和orgId查询
	 * @param @param map
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月5日 下午3:08:26
	 * @version V1.0
	 */
	@Override
	public HardwareUpgradEntity queryByHardVersionAndOrgId(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.HardwareUpgradEntityMapper.queryByHardVersionAndOrgId",map);
	}

	/**
	 * 查询最大版本号
	* @Title: queryMaxHardVersion 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @param lockCode
	* @param @return    入参
	* @return BigDecimal    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年8月9日 上午10:18:45 
	* @version V1.0
	 */
	@Override
	public BigDecimal queryMaxHardVersion(Long orgId, String codCode) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("orgId", orgId);
		map.put("codCode", codCode);
		return this.selectOne("com.ycnet.frms.mapper.HardwareUpgradEntityMapper.queryMaxHardVersion",map);
	}

}
  
    