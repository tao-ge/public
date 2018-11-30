package com.ycnet.frms.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.RemoteUnlockEntity;
import com.ycnet.frms.mapper.RemoteUnlockEntityMapper;
import com.ycnet.frms.vo.RemoteUnlockEntityVo;

@Repository("remoteUnlockEntityMapper")
public class RemoteUnlockEntityMapperImpl extends BaseSqlSupport implements RemoteUnlockEntityMapper{

	@Override
	public int deleteByPrimaryKey(Long applyId) {
		return this.delete("com.ycnet.frms.mapper.RemoteUnlockEntityMapper.deleteByPrimaryKey",applyId);
	}

	@Override
	public int insert(RemoteUnlockEntity record) {
		return this.insert("com.ycnet.frms.mapper.RemoteUnlockEntityMapper.insert",record);
	}

	@Override
	public int insertSelective(RemoteUnlockEntity record) {
		return this.insert("com.ycnet.frms.mapper.RemoteUnlockEntityMapper.insertSelective",record);
	}

	@Override
	public RemoteUnlockEntity selectByPrimaryKey(Long applyId) {
		return this.selectOne("com.ycnet.frms.mapper.RemoteUnlockEntityMapper.selectByPrimaryKey",applyId);
	}

	@Override
	public int updateByPrimaryKeySelective(RemoteUnlockEntity record) {
		return this.update("com.ycnet.frms.mapper.RemoteUnlockEntityMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(RemoteUnlockEntity record) {
		return this.update("com.ycnet.frms.mapper.RemoteUnlockEntityMapper.updateByPrimaryKey",record);
	}

	/**
	 * 
	 * @Title: queryRemoteUnlockList
	 * @Description: 装维远程开锁申请列表
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月15日 下午2:41:29
	 * @version V1.0
	 */
	@Override
	public List<RemoteUnlockEntityVo> queryRemoteUnlockList(Map<String, Object> map) {
		return this.selectList("com.ycnet.frms.mapper.RemoteUnlockEntityMapper.queryRemoteUnlockList",map);
	}

	/**
	 * 
	 * @Title: queryCountRemoteUnlockList
	 * @Description: 装维远程开锁申请列表数量
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月15日 下午2:41:44
	 * @version V1.0
	 */
	@Override
	public Integer queryCountRemoteUnlockList(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.RemoteUnlockEntityMapper.queryCountRemoteUnlockList",map);
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
	 * @date 2018年4月15日 下午4:21:16
	 * @version V1.0
	 */
	@Override
	public RemoteUnlockEntity queryLastByDevId(Long devId) {
		return this.selectOne("com.ycnet.frms.mapper.RemoteUnlockEntityMapper.queryLastByDevId", devId);
	}

}
  
    