package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.DeviceDataState;
import com.ycnet.frms.mapper.DeviceDataStateMapper;
import com.ycnet.frms.vo.DifferentPortsBean;
import com.ycnet.frms.vo.DifferentPortsVo;

@Repository("deviceDataStateMapper")
public class DeviceDataStateMapperImpl extends BaseSqlSupport implements DeviceDataStateMapper{

	@Override
	public int deleteByPrimaryKey(Long dataId) {
		return this.delete("com.ycnet.frms.mapper.DeviceDataStateMapper.deleteByPrimaryKey",dataId);
	}

	@Override
	public int insert(DeviceDataState record) {
		return this.insert("com.ycnet.frms.mapper.DeviceDataStateMapper.insert",record);
	}

	@Override
	public int insertSelective(DeviceDataState record) {
		return this.insert("com.ycnet.frms.mapper.DeviceDataStateMapper.insertSelective",record); 
	}

	@Override
	public DeviceDataState selectByPrimaryKey(Long dataId) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceDataStateMapper.selectByPrimaryKey",dataId);
	}

	@Override
	public int updateByPrimaryKeySelective(DeviceDataState record) {
		return this.update("com.ycnet.frms.mapper.DeviceDataStateMapper.updateByPrimaryKeySelective",record);  
	}

	@Override
	public int updateByPrimaryKey(DeviceDataState record) {
		return this.update("com.ycnet.frms.mapper.DeviceDataStateMapper.updateByPrimaryKey",record);
	}

	/**
	 * 
	 * @Title: insertList
	 * @Description: 批量添加区域数据
	 * @param @param listByArea
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月26日 下午2:24:37
	 * @version V1.0
	 */
	@Override
	@Transactional
	public int insertList(List<DeviceDataState> list) {
		return this.insert("com.ycnet.frms.mapper.DeviceDataStateMapper.insertList",list);
	}

	/**
     * 端子差异数据页面查询数据
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
	@Override
	public List<DifferentPortsVo> queryListByConditions(Map<String, Object> map) {
		return this.selectList("com.ycnet.frms.mapper.DeviceDataStateMapper.queryListByConditions",map);
	}

	
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
	@Override
	public Integer queryCounts(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceDataStateMapper.queryCounts",map);
	}

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
	@Override
	public List<DifferentPortsVo> queryDifferentTotals(Map<String, Object> map) {
		return this.selectList("com.ycnet.frms.mapper.DeviceDataStateMapper.queryDifferentTotals",map);
	}

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
	@Override
	public int queryDifferentTotalsCounts(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceDataStateMapper.queryDifferentTotalsCounts",map);
	}

	/**
	 * 
	 * @Title: deleteByDataType
	 * @Description: 根据数据类型删除
	 * @param @param dataType
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月26日 下午3:07:34
	 * @version V1.0
	 */
	@Override
	public int deleteByDataType(String dataType, Long orgId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("dataType", dataType);
		map.put("orgId", orgId);
		return this.delete("com.ycnet.frms.mapper.DeviceDataStateMapper.deleteByDataType",map);
	}

}
  
    