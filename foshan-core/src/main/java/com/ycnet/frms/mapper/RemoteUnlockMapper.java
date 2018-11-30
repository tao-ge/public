package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.RemoteUnlock;

public interface RemoteUnlockMapper {
    int deleteByPrimaryKey(Long applyId);

    int insert(RemoteUnlock record);

    int insertSelective(RemoteUnlock record);

    RemoteUnlock selectByPrimaryKey(Long applyId);

    int updateByPrimaryKeySelective(RemoteUnlock record);

    int updateByPrimaryKey(RemoteUnlock record);

    /**
     * 
     * @Title: queryRemoteUnlockApplyList
     * @Description: 远程开锁授权列表
     * @param @param map
     * @param @return 
     * @return List<RemoteUnlock> 入参
     * @return String    返回类型
     * @author DZY 
     * @throws
     * @date 2018年2月5日 下午2:06:00
     * @version V1.0
     */
	List<RemoteUnlock> queryRemoteUnlockApplyList(Map<String, Object> map);

	/**
	 * 
	 * @Title: queryCountRemoteUnlockApplyList
	 * @Description: 查询远程开锁授权列表数量
	 * @param @param map
	 * @param @return 
	 * @return Integer 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年2月5日 下午2:06:28
	 * @version V1.0
	 */
	Integer queryCountRemoteUnlockApplyList(Map<String, Object> map);

	/**
	 * 
	 * @Title: queryLastByDevId
	 * @Description: 根据devId查询最新的一条数据
	 * @param @param devId
	 * @param @return 
	 * @return RemoteUnlock 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年3月19日 上午11:47:57
	 * @version V1.0
	 */
	RemoteUnlock queryLastByDevId(Long devId);
}