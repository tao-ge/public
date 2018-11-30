package com.ycnet.frms.mapper.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.FiberdiscGroup;
import com.ycnet.frms.bean.FiberdiscGroupZF;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.FiberdiscGroupMapper;
import com.ycnet.frms.vo.FiberdiscGroupBean;
import com.ycnet.frms.vo.GroupConditionBean;

@Repository("fiberdiscGroupMapper")
public class FiberdiscGroupMapperImpl extends BaseSqlSupport implements FiberdiscGroupMapper {

	@Override
	public int deleteByPrimaryKey(Long groupId) {
		return this.delete("com.ycnet.frms.mapper.FiberdiscGroupMapper.deleteByPrimaryKey",groupId);
	}

	@Override
	public int insert(FiberdiscGroup record) {
		return this.insert("com.ycnet.frms.mapper.FiberdiscGroupMapper.insert",record);
	}

	@Override
	public int insertSelective(FiberdiscGroup record) {
		return this.insert("com.ycnet.frms.mapper.FiberdiscGroupMapper.insertSelective",record);
	}

	@Override
	public FiberdiscGroup selectByPrimaryKey(Long groupId) {
		return this.selectOne("com.ycnet.frms.mapper.FiberdiscGroupMapper.selectByPrimaryKey",groupId);
	}

	@Override
	public int updateByPrimaryKeySelective(FiberdiscGroup record) {
		return this.update("com.ycnet.frms.mapper.FiberdiscGroupMapper.updateByPrimaryKeySelective",record);
	}
	
	@Override
	public int updateByPrimaryKeySelectiveZG(FiberdiscGroup record) {
		return this.update("com.ycnet.frms.mapper.FiberdiscGroupMapper.updateByPrimaryKeySelectiveZG",record);
	}

	@Override
	public int updateByPrimaryKey(FiberdiscGroup record) {
		return this.update("com.ycnet.frms.mapper.FiberdiscGroupMapper.updateByPrimaryKey",record);
	}

	@Override
	public List<FiberdiscGroup> queryGroupByDev(GroupConditionBean bean) {
		return this.selectList("com.ycnet.frms.mapper.FiberdiscGroupMapper.queryGroupByDev",bean);
	}

	@Override
	public List<FiberdiscGroupBean> selectDiscinfoByGroup(Long devId,
			Long groupId) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("devId",devId);
		param.put("groupId", groupId);
		return this.selectList("com.ycnet.frms.mapper.FiberdiscGroupMapper.selectDiscinfoByGroup",param);
	}

	@Override
	public FiberdiscGroupBean selectDiscinfoByGroup(Long groupId) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("devId",null);
		param.put("groupId", groupId);
		return this.selectOne("com.ycnet.frms.mapper.FiberdiscGroupMapper.selectDiscinfoByGroup",param);
	}

	@Override
	public int updateDiscGroup(Long groupId, String side) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("groupId", groupId);
		map.put("side", side);
		return this.update("com.ycnet.frms.mapper.FiberdiscGroupMapper.updateDiscGroup", map);
	}

	@Override
	public int updateDiscGroupName(Long groupId, String groupName) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("groupId", groupId);
		map.put("groupName", groupName);
		return this.update("com.ycnet.frms.mapper.FiberdiscGroupMapper.updateDiscGroupName", map);
	}

	/**
	 * 用于删除设备
	 */
	@Override
	public List<FiberdiscGroup> selectByDevId(Long devId) {
		return this.selectList("com.ycnet.frms.mapper.FiberdiscGroupMapper.selectByDevId", devId);
	}
	/**
	 * 用于删除设备
	 */
	@Override
	public int deleteByDevId(Long devId) {
		return this.delete("com.ycnet.frms.mapper.FiberdiscGroupMapper.deleteByDevId", devId);
	}

	/**
	 * 设施分组信息 --刘沧海--2017-9-26
	 */
	@Override
	public List<FiberdiscGroup> queryByDevId(Long devId) {
		return this.selectList("com.ycnet.frms.mapper.FiberdiscGroupMapper.queryByDevId", devId);
	}

	//导出数据库 刘沧海 2017/10/13
	@Override
	public List<FiberdiscGroupZF> queryList(Long orgId, String areaCode) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("orgId", orgId);
		map.put("areaCode", areaCode);
		return this.selectList("com.ycnet.frms.mapper.FiberdiscGroupMapper.queryList",map);
	}

	@Override
	public int insertSelectiveZG(FiberdiscGroup record) {
		// TODO Auto-generated method stub
		return this.insert("com.ycnet.frms.mapper.FiberdiscGroupMapper.insertSelectiveZG",record);
	}
	/**
	 * 
	* @Title: saveGroupDescByGroupId 
	* @Description: 根据熔纤盘groupId修改熔纤盘描述 
	* @param @param groupId
	* @param @param groupDesc
	* @param @param groupName
	* @param @return    入参
	* @return int    返回类型
	* @author zhouyu 
	* @throws
	* @date 2017年12月6日 下午4:12:15 
	* @version V1.0
	 */
	@Override
	public int saveGroupDescByGroupId(Long groupId, String groupDesc, String groupName,Users user) {
		FiberdiscGroup fiberdiscGroup = new FiberdiscGroup();
		fiberdiscGroup.setGroupId(groupId);
		fiberdiscGroup.setGroupDesc(groupDesc);
		fiberdiscGroup.setGroupName(groupName);
		fiberdiscGroup.setLastModifyTime(new Date());
		fiberdiscGroup.setLastModifyUser(user.getOrgId());
		return this.update("com.ycnet.frms.mapper.FiberdiscGroupMapper.updateByPrimaryKeySelective", fiberdiscGroup);
	}

	/**
	 * 新建的熔纤盘列表展示
	* 
	* @Title: FiberdiscGroupMapper.java 
	* @Description: TODO 
	* @param @param fiberdiscGroup
	* @param @return
	* @return List<FiberdiscGroup>
	* @author fl
	* @date 2017年12月17日 下午2:53:22
	* @version V1.0
	 */
	@Override
	public List<FiberdiscGroup> queryisInvest(Long devId) {
		return this.selectList("com.ycnet.frms.mapper.FiberdiscGroupMapper.queryisInvest", devId);
	}

	/**
	 * 
	 * @Title: selectBySide
	 * @Description: 根据分组编码查询最大分组编码[直熔盘专用]
	 * @param @param string
	 * @param @return 
	 * @return String 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月17日 下午9:58:06
	 * @version V1.0
	 */
	@Override
	public String selectByMaxSide(String side, Long devId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("side", side);
		map.put("devId", devId);
		return this.selectOne("com.ycnet.frms.mapper.FiberdiscGroupMapper.selectByMaxSide",map);
	}

	/**
	 * 根据分组编码查询
	 * 刘沧海
	 */
	@Override
	public List<FiberdiscGroup> querySideByZO(Long devId, String side, String sideOut) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("devId", devId);
		map.put("side", side);
		map.put("sideOut", sideOut);
		return this.selectList("com.ycnet.frms.mapper.FiberdiscGroupMapper.querySideByZO", map);
	}
	/**
	 * 
	 * @Title: selectByDiscId
	 * @Description: 根据盘ID查询分组
	 * @param @param discId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月26日 下午5:15:33
	 * @version V1.0
	 */
	@Override
	public FiberdiscGroup selectByDiscId(Long discId) {
		return this.selectOne("com.ycnet.frms.mapper.FiberdiscGroupMapper.selectByDiscId",discId);
	}

	/**
	 * 
	 * @Title: queryByDevIdAndSide
	 * @Description: 根据devId和side查询分组数据
	 * @param @param devId
	 * @param @param side
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月23日 下午2:25:41
	 * @version V1.0
	 */
	@Override
	public List<FiberdiscGroup> queryByDevIdAndSide(Long devId, String side) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("devId", devId);
		map.put("side", side);
		return this.selectList("com.ycnet.frms.mapper.FiberdiscGroupMapper.queryByDevIdAndSide", map);
	}

	/**
	 * 
	* @Title: queryForDevIdGroup 
	* @Description: 查询设施分组 
	* @param @param devId
	* @param @return    
	* @return List<FiberdiscGroupBean>
	* @author liucanghai 
	* @throws
	* @date 2018年2月1日 下午1:36:39 
	* @version V1.0
	 */
	@Override
	public List<FiberdiscGroupZF> queryForDevIdGroup(Long devId) {
		return this.selectList("com.ycnet.frms.mapper.FiberdiscGroupMapper.queryForDevIdGroup", devId);
	}

	/**
	 * 
	 * @Title: queryGroupByPort01
	 * @Description: 根据端子编码查询所属分组
	 * @param @param code
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月25日 上午11:22:28
	 * @version V1.0
	 */
	@Override
	public FiberdiscGroup queryGroupByPort01(String port01) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("port01", port01);
		return this.selectOne("com.ycnet.frms.mapper.FiberdiscGroupMapper.queryGroupByPort01",map);
	}
}
