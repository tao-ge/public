package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.WorkorderFiberdiscabindZ;
import com.ycnet.frms.mapper.WorkorderFiberdiscabindZMapper;
import com.ycnet.frms.vo.WorkorderFiberdiscabindBean;
@Repository("workorderFiberdiscabindZMapper")
public class WorkorderFiberdiscabindZMapperImpl extends BaseSqlSupport implements WorkorderFiberdiscabindZMapper {

	@Override
	public int deleteByPrimaryKey(Long id) {
		return this.delete("com.ycnet.frms.mapper.WorkorderFiberdiscabindZMapper.deleteByPrimaryKey",id);
	}

	@Override
	public int insert(WorkorderFiberdiscabindZ record) {
		return this.insert("com.ycnet.frms.mapper.WorkorderFiberdiscabindZMapper.insert",record);
	}

	@Override
	public int insertSelective(WorkorderFiberdiscabindZ record) {
		return this.insert("com.ycnet.frms.mapper.WorkorderFiberdiscabindZMapper.insertSelective",record);
	}

	@Override
	public WorkorderFiberdiscabindZ selectByPrimaryKey(Long id) {
		return this.selectOne("com.ycnet.frms.mapper.WorkorderFiberdiscabindZMapper.selectByPrimaryKey",id);
	}

	@Override
	public int updateByPrimaryKeySelective(WorkorderFiberdiscabindZ record) {
		return this.update("com.ycnet.frms.mapper.WorkorderFiberdiscabindZMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(WorkorderFiberdiscabindZ record) {
		return this.update("com.ycnet.frms.mapper.WorkorderFiberdiscabindZMapper.updateByPrimaryKey",record);
	}
	
	@Override
	public WorkorderFiberdiscabindZ queryByRoutsIds(Long designroutesId, Long zdevId) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("designroutesId", designroutesId);
		map.put("zdevId", zdevId);
		return this.selectOne("com.ycnet.frms.mapper.WorkorderFiberdiscabindZMapper.queryByRoutsIds", map);
	}
	
	/**
	 * 
	 * @Title: selectByDevIdAndFiberDiscId
	 * @Description: 根据devId和fiberDiscId查询一条
	 * @param @param map
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月6日 上午9:06:38
	 * @version V1.0
	 */
	@Override
	public WorkorderFiberdiscabindZ selectByDevIdAndFiberDiscId(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.WorkorderFiberdiscabindZMapper.selectByDevIdAndFiberDiscId", map);
	}
	/**
	 * 根据调度工单路由Id查询端子,设施,盘信息Z
	* @Title: queryWorkorderFiberdiscabindZByDesignroutesId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param designroutesId
	* @param @return    入参
	* @return WorkorderFiberdiscabindBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月5日 下午5:15:54 
	* @version V1.0
	 */
	@Override
	public WorkorderFiberdiscabindBean queryWorkorderFiberdiscabindZByDesignroutesId(Long designroutesId) {
		return this.selectOne("com.ycnet.frms.mapper.WorkorderFiberdiscabindZMapper.queryWorkorderFiberdiscabindZByDesignroutesId", designroutesId);
	}

	/**
	 * 根据主键查询WorkorderFiberdiscabindZ
	* @Title: selectById 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param designroutesId
	* @param @return    入参
	* @return WorkorderFiberdiscabindZ    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月6日 上午11:54:26 
	* @version V1.0
	 */
	@Override
	public WorkorderFiberdiscabindZ selectById(Long designroutesId) {
		return this.selectOne("com.ycnet.frms.mapper.WorkorderFiberdiscabindZMapper.selectById", designroutesId);
	}

	/**
	 * 
	 * @Title: queryWorkorderFiberdiscabindAByZfiberDiscId
	 * @Description: 根据zfiberDiscId查询
	 * @param @param zfiberDiscId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月12日 上午9:43:17
	 * @version V1.0
	 */
	@Override
	public WorkorderFiberdiscabindBean queryWorkorderFiberdiscabindAByZfiberDiscId(Long zfiberDiscId) {
		return this.selectOne("com.ycnet.frms.mapper.WorkorderFiberdiscabindZMapper.queryWorkorderFiberdiscabindAByZfiberDiscId", zfiberDiscId);
	}

}
