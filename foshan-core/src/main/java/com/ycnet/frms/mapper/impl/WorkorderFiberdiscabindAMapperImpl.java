package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.WorkorderFiberdiscabindA;
import com.ycnet.frms.mapper.WorkorderFiberdiscabindAMapper;
import com.ycnet.frms.vo.WorkorderFiberdiscabindBean;
@Repository("workorderFiberdiscabindAMapper")
public class WorkorderFiberdiscabindAMapperImpl extends BaseSqlSupport implements WorkorderFiberdiscabindAMapper {

	@Override
	public int deleteByPrimaryKey(Long id) {
		return this.delete("com.ycnet.frms.mapper.WorkorderFiberdiscabindAMapper.deleteByPrimaryKey",id);
	}

	@Override
	public int insert(WorkorderFiberdiscabindA record) {
		return this.insert("com.ycnet.frms.mapper.WorkorderFiberdiscabindAMapper.insert",record);
	}

	@Override
	public int insertSelective(WorkorderFiberdiscabindA record) {
		return this.insert("com.ycnet.frms.mapper.WorkorderFiberdiscabindAMapper.insertSelective",record);
	}

	@Override
	public WorkorderFiberdiscabindA selectByPrimaryKey(Long id) {
		return this.selectOne("com.ycnet.frms.mapper.WorkorderFiberdiscabindAMapper.selectByPrimaryKey",id);
	}

	@Override
	public int updateByPrimaryKeySelective(WorkorderFiberdiscabindA record) {
		return this.update("com.ycnet.frms.mapper.WorkorderFiberdiscabindAMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(WorkorderFiberdiscabindA record) {
		return this.update("com.ycnet.frms.mapper.WorkorderFiberdiscabindAMapper.updateByPrimaryKey",record);
	}

	
	/**
	 * 
	 * @Title: queryByIds 
	 * @Description: 查询A端绑定端子 
	 * @param @param designId
	 * @param @param adevId
	 * @param @return    
	 * @return WorkorderFiberdiscabindA
	 * @author liucanghai 
	 * @throws
	 * @date 2018年4月20日 下午2:42:05 
	 * @version V1.0
	 */
	@Override
	public WorkorderFiberdiscabindA queryByRoutsIds(Long designroutesId, Long adevId) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("designroutesId", designroutesId);
		map.put("adevId", adevId);
		return this.selectOne("com.ycnet.frms.mapper.WorkorderFiberdiscabindAMapper.queryByRoutsIds", map);
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
	 * @date 2018年6月5日 下午5:58:44
	 * @version V1.0
	 */
	@Override
	public WorkorderFiberdiscabindA selectByDevIdAndFiberDiscId(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.WorkorderFiberdiscabindAMapper.selectByDevIdAndFiberDiscId", map);
	}
	/**
	 * 根据调度工单路由Id查询端子,设施,盘信息A
	* @Title: queryWorkorderFiberdiscabindAByDesignroutesId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param designroutesId
	* @param @return    入参
	* @return WorkorderFiberdiscabindBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月5日 下午5:15:13 
	* @version V1.0
	 */
	@Override
	public WorkorderFiberdiscabindBean queryWorkorderFiberdiscabindAByDesignroutesId(Long designroutesId) {
		return this.selectOne("com.ycnet.frms.mapper.WorkorderFiberdiscabindAMapper.queryWorkorderFiberdiscabindAByDesignroutesId", designroutesId);
	}

	/**
	 * 根据主键查询WorkorderFiberdiscabindA
	* @Title: selectById 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param designroutesId
	* @param @return    入参
	* @return WorkorderFiberdiscabindA    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月6日 上午10:30:19 
	* @version V1.0
	 */
	@Override
	public WorkorderFiberdiscabindA selectById(Long designroutesId) {
		return this.selectOne("com.ycnet.frms.mapper.WorkorderFiberdiscabindAMapper.selectById", designroutesId);
	}

	/**
	 * 
	 * @Title: queryWorkorderFiberdiscabindAByAfiberDiscId
	 * @Description: 根据afiberDiscId查询
	 * @param @param afiberDiscId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月12日 上午9:39:28
	 * @version V1.0
	 */
	@Override
	public WorkorderFiberdiscabindBean queryWorkorderFiberdiscabindAByAfiberDiscId(Long afiberDiscId) {
		return this.selectOne("com.ycnet.frms.mapper.WorkorderFiberdiscabindAMapper.queryWorkorderFiberdiscabindAByAfiberDiscId", afiberDiscId);
	}

}
