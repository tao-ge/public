package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.RemoteUnlockEntity;
import com.ycnet.frms.vo.RemoteUnlockEntityVo;

public interface RemoteUnlockEntityMapper {
    int deleteByPrimaryKey(Long applyId);

    int insert(RemoteUnlockEntity record);

    int insertSelective(RemoteUnlockEntity record);

    RemoteUnlockEntity selectByPrimaryKey(Long applyId);

    int updateByPrimaryKeySelective(RemoteUnlockEntity record);

    int updateByPrimaryKey(RemoteUnlockEntity record);

    /**
     * 
     * @Title: queryRemoteUnlockList
     * @Description: 装维远程开锁申请列表
     * @param @return 
     * @return List<RemoteUnlockEntity> 入参
     * @return String    返回类型
     * @author DZY 
     * @throws
     * @date 2018年4月15日 下午2:40:09
     * @version V1.0
     * @param map 
     */
	List<RemoteUnlockEntityVo> queryRemoteUnlockList(Map<String, Object> map);

	/**
	 * 
	 * @Title: queryCountRemoteUnlockList
	 * @Description: 装维远程开锁申请列表数量
	 * @param @return 
	 * @return Integer 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月15日 下午2:40:51
	 * @version V1.0
	 * @param map 
	 */
	Integer queryCountRemoteUnlockList(Map<String, Object> map);

	/**
	 * 
	 * @Title: queryLastByDevId
	 * @Description: 根据devId查询最新的一条数据
	 * @param @param devId
	 * @param @return 
	 * @return RemoteUnlockEntity 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月15日 下午4:20:41
	 * @version V1.0
	 */
	RemoteUnlockEntity queryLastByDevId(Long devId);
}