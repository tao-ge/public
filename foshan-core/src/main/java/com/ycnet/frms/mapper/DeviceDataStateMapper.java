package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;
import com.ycnet.frms.bean.DeviceDataState;
import com.ycnet.frms.vo.DifferentPortsBean;
import com.ycnet.frms.vo.DifferentPortsVo;

public interface DeviceDataStateMapper {
    int deleteByPrimaryKey(Long dataId);

    int insert(DeviceDataState record);

    int insertSelective(DeviceDataState record);

    DeviceDataState selectByPrimaryKey(Long dataId);

    int updateByPrimaryKeySelective(DeviceDataState record);

    int updateByPrimaryKey(DeviceDataState record);

    /**
     * 
     * @Title: insertList
     * @Description: 批量添加区域数据
     * @param @param listByArea
     * @param @return 
     * @return int 入参
     * @return String    返回类型
     * @author DZY 
     * @throws
     * @date 2018年6月26日 下午2:24:12
     * @version V1.0
     */
	int insertList(List<DeviceDataState> list);

    /**
     * 端子差异数据页面查询数据区域
    * @Title: queryListByConditions 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param map
    * @param @return    入参
    * @return List<DeviceDataState>    返回类型
    * @author FL（作者） 
    * @throws
    * @date 2018年6月26日 上午9:34:30 
    * @version V1.0
     */
	List<DifferentPortsVo> queryListByConditions(Map<String, Object> map);

	/**
	 * 端子差异数据页面查询数据区域总条数
	* @Title: queryCounts 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param map
	* @param @return    入参
	* @return Integer    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月26日 上午10:32:01 
	* @version V1.0
	 */
	Integer queryCounts(Map<String, Object> map);

	/**
	 * 端子差异数据页面查询数据业务点
	* @Title: queryDifferentTotals 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param map
	* @param @return    入参
	* @return List<?>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月26日 上午11:45:30 
	* @version V1.0
	 */
	List<DifferentPortsVo> queryDifferentTotals(Map<String, Object> map);

	/**
	 * 端子差异数据页面查询数据业务点总条数
	* @Title: queryDifferentTotalsCounts 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param map
	* @param @return    入参
	* @return Integer    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月26日 上午11:46:03 
	* @version V1.0
	 */
	int queryDifferentTotalsCounts(Map<String, Object> map);

	/**
	 * 
	 * @Title: deleteByDataType
	 * @Description: 根据数据类型删除
	 * @param @param string
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月26日 下午3:07:02
	 * @version V1.0
	 * @param orgId 
	 */
	int deleteByDataType(String dataType, Long orgId);

}