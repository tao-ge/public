package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.ResourceDataLog;

public interface ResourceDataLogMapper {
    int deleteByPrimaryKey(Long resLogId);

    int insert(ResourceDataLog record);

    int insertSelective(ResourceDataLog record);

    ResourceDataLog selectByPrimaryKey(Long resLogId);

    int updateByPrimaryKeySelective(ResourceDataLog record);

    int updateByPrimaryKey(ResourceDataLog record);

    /**
     * 
     * @Title: queryByConditionBeans
     * @Description: 查询资管校准日志列表 
     * @param @param map
     * @param @return 
     * @return List<ResourceDataLog> 入参
     * @return String    返回类型
     * @author DZY 
     * @throws
     * @date 2017年11月8日 上午9:22:48
     * @version V1.0
     */
	List<ResourceDataLog> queryByConditionBeans(Map<String, Object> map);

	/**
	 * 
	 * @Title: queryCountByConditionMap
	 * @Description: 查询资管校准日志列表数量
	 * @param @param map
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年11月8日 上午9:23:39
	 * @version V1.0
	 */
	int queryCountByConditionMap(Map<String, Object> map);
}