package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.DiscinfoStatusEntity;
import com.ycnet.frms.mapper.DiscinfoStatusEntityMapper;
import com.ycnet.frms.vo.DeviceDiscinfoEntityBean;

@Repository("discinfoStatusEntityMapper")
public class DiscinfoStatusEntityMapperImpl extends BaseSqlSupport implements DiscinfoStatusEntityMapper{

	@Override
	public int insert(DiscinfoStatusEntity record) {
		return this.insert("com.ycnet.frms.mapper.DiscinfoStatusEntityMapper.insert",record);
	}

	@Override
	public int insertSelective(DiscinfoStatusEntity record) {
		return this.insert("com.ycnet.frms.mapper.DiscinfoStatusEntityMapper.insertSelective",record); 
	}

	 /**
     * 查询历史上报记录
    * @Title: queryhistoryDetialByDiscId 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param discId
    * @param @return    入参
    * @return List<DeviceDiscinfoEntityBean>    返回类型
    * @author FL（作者） 
    * @throws
    * @date 2018年6月8日 下午1:50:34 
    * @version V1.0
     */
	@Override
	public List<DeviceDiscinfoEntityBean> queryhistoryDetialByDiscId(Long discId) {
		return this.selectList("com.ycnet.frms.mapper.DiscinfoStatusEntityMapper.queryhistoryDetialByDiscId",discId);
	}

	/**
	 * 
	 * @Title: deleteByCodIdAndDiscId
	 * @Description: 根据CodId和DiscId删除端子控制器状态记录
	 * @param @param codId
	 * @param @param discId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月14日 上午10:06:21
	 * @version V1.0
	 */
	@Override
	public int deleteByCodIdAndDiscId(Long codId, Long discId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("codId", codId);
		map.put("discId", discId);
		return this.delete("com.ycnet.frms.mapper.DiscinfoStatusEntityMapper.deleteByCodIdAndDiscId",map);
	}

	/* (non-Javadoc)
	 * @see com.ycnet.frms.mapper.DiscinfoStatusEntityMapper#updateByPrimaryKeySelective(com.ycnet.frms.bean.DiscinfoStatusEntity)
	 */
	@Override
	public int updateByPrimaryKeySelective(DiscinfoStatusEntity record) {
		return this.update("com.ycnet.frms.mapper.DiscinfoStatusEntityMapper.updateByPrimaryKeySelective",record);
	}

}
  
    