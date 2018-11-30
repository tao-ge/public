package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.DeviceEntity;
import com.ycnet.frms.bean.DeviceLockSwitch;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.DeviceLockSwitchMapper;
import com.ycnet.frms.vo.DeviceLockSwitchVo;
@Repository("deviceLockSwitchMapper")
public class DeviceLockSwitchMapperImpl extends BaseSqlSupport implements DeviceLockSwitchMapper {

	@Override
	public int deleteByPrimaryKey(Long switchId) {
		return this.delete("com.ycnet.frms.mapper.DeviceLockSwitchMapper.deleteByPrimaryKey",switchId);
	}

	@Override
	public int insert(DeviceLockSwitch record) {
		return this.insert("com.ycnet.frms.mapper.DeviceLockSwitchMapper.insert",record);
	}

	@Override
	public int insertSelective(DeviceLockSwitch record) {
		return this.insert("com.ycnet.frms.mapper.DeviceLockSwitchMapper.insertSelective",record); 
	}

	@Override
	public DeviceLockSwitch selectByPrimaryKey(Long switchId) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceLockSwitchMapper.selectByPrimaryKey",switchId);
	}

	@Override
	public int updateByPrimaryKeySelective(DeviceLockSwitch record) {
		return this.update("com.ycnet.frms.mapper.DeviceLockSwitchMapper.updateByPrimaryKeySelective",record);  
	}

	@Override
	public int updateByPrimaryKey(DeviceLockSwitch record) {
		return this.update("com.ycnet.frms.mapper.DeviceLockSwitchMapper.updateByPrimaryKey",record);
	}

	/**
	 * 
	* @Title: queryLockSwitchList 
	* @Description: 开关锁记录查询 
	* @param @param pb
	* @param @param switchVo
	* @param @return    
	* @return PageBean
	* @author liucanghai 
	* @throws
	* @date 2018年4月17日 下午3:16:57 
	* @version V1.0
	 */
	@Override
	public List<DeviceLockSwitchVo> queryLockSwitchList(DeviceLockSwitchVo switchVo, PageBean pb) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("switchVo",switchVo);
		map.put("pb",pb);
		return this.selectList("com.ycnet.frms.mapper.DeviceLockSwitchMapper.queryLockSwitchList", map);
	}

	/**
	 * 
	* @Title: queryLockSwitchCount 
	* @Description: 开关锁记录查询 
	* @param @param pb
	* @param @param switchVo
	* @param @return    
	* @return PageBean
	* @author liucanghai 
	* @throws
	* @date 2018年4月17日 下午3:16:57 
	* @version V1.0
	 */
	@Override
	public int queryLockSwitchCount(DeviceLockSwitchVo switchVo, PageBean pb) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("switchVo",switchVo);
		map.put("pb",pb);
		return this.selectOne("com.ycnet.frms.mapper.DeviceLockSwitchMapper.queryLockSwitchCount", map);
	}

	/**
	 * 开关锁记录查询数据
	* @Title: queryimDeviceLockSwitchList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param map
	* @param @return    入参
	* @return List<?>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月21日 下午3:06:43 
	* @version V1.0
	 */
	@Override
	public List<DeviceLockSwitchVo> queryimDeviceLockSwitchList(Map<String, Object> map) {
		return this.selectList("com.ycnet.frms.mapper.DeviceLockSwitchMapper.queryimDeviceLockSwitchList", map);
	}

	/**
	 * 开关锁记录查询总条数
	* @Title: queryimDeviceLockSwitchCount 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param map
	* @param @return    入参
	* @return Integer    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月21日 下午3:06:48 
	* @version V1.0
	 */
	@Override
	public Integer queryimDeviceLockSwitchCount(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceLockSwitchMapper.queryimDeviceLockSwitchCount", map);
	}

	/**
	 * 
	 * @Title: deleteByLockId
	 * @Description: 根据lockId删除开关锁记录
	 * @param @param lockId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月5日 上午10:38:58
	 * @version V1.0
	 */
	@Override
	public int deleteByLockId(Map<String, Object> dsMap) {
		return this.delete("com.ycnet.frms.mapper.DeviceLockSwitchMapper.deleteByLockId",dsMap);
	}

	/**
	 * 根据锁ID,查询是否有开锁成功记录
	* @Title: queryByLockId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param lockId
	* @param @return    入参
	* @return DeviceLockSwitch    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年8月7日 下午1:10:08 
	* @version V1.0
	 */
	@Override
	public DeviceLockSwitch queryByLockId(Long lockId, String date, Users user) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("lockId",lockId);
		map.put("date",date);
		map.put("user",user);
		return this.selectOne("com.ycnet.frms.mapper.DeviceLockSwitchMapper.queryByLockId",map);
	}

	/**
	 * 根据锁IDs,查询是否有开锁成功记录
	* @Title: queryByLockIds 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param lockId0
	* @param @param lockId1
	* @param @param format
	* @param @param user
	* @param @return    入参
	* @return DeviceLockSwitch    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年8月17日 上午10:56:25 
	* @version V1.0
	 */
	@Override
	public DeviceLockSwitch queryByLockIds(Long lockId0, Long lockId1, String date, Users user) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("lockId0",lockId0);
		map.put("lockId1",lockId1);
		map.put("date",date);
		map.put("user",user);
		return this.selectOne("com.ycnet.frms.mapper.DeviceLockSwitchMapper.queryByLockIds",map);
	}

}
