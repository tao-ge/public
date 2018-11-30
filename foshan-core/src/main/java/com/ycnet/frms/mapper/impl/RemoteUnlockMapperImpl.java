package com.ycnet.frms.mapper.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.RemoteUnlock;
import com.ycnet.frms.mapper.RemoteUnlockMapper;

@Repository("remoteUnlockMapper")
public class RemoteUnlockMapperImpl extends BaseSqlSupport implements RemoteUnlockMapper{

	@Override
	public int deleteByPrimaryKey(Long applyId) {
		return this.delete("com.ycnet.frms.mapper.RemoteUnlockMapper.deleteByPrimaryKey",applyId);
	}

	@Override
	public int insert(RemoteUnlock record) {
		return this.insert("com.ycnet.frms.mapper.RemoteUnlockMapper.insert",record);
	}

	@Override
	public int insertSelective(RemoteUnlock record) {
		return this.insert("com.ycnet.frms.mapper.RemoteUnlockMapper.insertSelective",record);
	}

	@Override
	public RemoteUnlock selectByPrimaryKey(Long applyId) {
		return this.selectOne("com.ycnet.frms.mapper.RemoteUnlockMapper.selectByPrimaryKey",applyId);
	}

	@Override
	public int updateByPrimaryKeySelective(RemoteUnlock record) {
		return this.update("com.ycnet.frms.mapper.RemoteUnlockMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(RemoteUnlock record) {
		return this.update("com.ycnet.frms.mapper.RemoteUnlockMapper.updateByPrimaryKey",record);
	}

	/**
	 * 
	 * @Title: queryRemoteUnlockApplyList
	 * @Description: 远程开锁授权列表
	 * @param @param map
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年2月5日 下午2:06:57
	 * @version V1.0
	 */
	@Override
	public List<RemoteUnlock> queryRemoteUnlockApplyList(Map<String, Object> map) {
		return this.selectList("com.ycnet.frms.mapper.RemoteUnlockMapper.queryRemoteUnlockApplyList", map);
	}

	/**
	 * 
	 * @Title: queryCountRemoteUnlockApplyList
	 * @Description: 查询远程开锁授权列表数量
	 * @param @param map
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年2月5日 下午2:07:11
	 * @version V1.0
	 */
	@Override
	public Integer queryCountRemoteUnlockApplyList(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.RemoteUnlockMapper.queryCountRemoteUnlockApplyList", map);
	}

	/**
	 * 
	 * @Title: queryLastByDevId
	 * @Description: 根据devId查询最新的一条数据
	 * @param @param devId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年3月19日 上午11:48:36
	 * @version V1.0
	 */
	@Override
	public RemoteUnlock queryLastByDevId(Long devId) {
		return this.selectOne("com.ycnet.frms.mapper.RemoteUnlockMapper.queryLastByDevId", devId);
	}

}
  
    