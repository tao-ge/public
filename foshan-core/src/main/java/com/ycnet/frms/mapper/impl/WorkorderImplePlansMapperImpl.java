package com.ycnet.frms.mapper.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.WorkorderImplePlans;
import com.ycnet.frms.mapper.WorkorderImplePlansMapper;
import com.ycnet.frms.vo.WorkorderFiberdiscabindBean;
import com.ycnet.frms.vo.WorkorderFiberdiscabindImgBean;
@Repository("workorderImplePlansMapper")
public class WorkorderImplePlansMapperImpl extends BaseSqlSupport implements WorkorderImplePlansMapper {

	@Override
	public int deleteByPrimaryKey(Long plansId) {
		return this.delete("com.ycnet.frms.mapper.WorkorderImplePlansMapper.deleteByPrimaryKey", plansId);
	}

	@Override
	public int insert(WorkorderImplePlans record) {
		return this.insert("com.ycnet.frms.mapper.WorkorderImplePlansMapper.insert", record);
	}

	@Override
	public int insertSelective(WorkorderImplePlans record) {
		return this.insert("com.ycnet.frms.mapper.WorkorderImplePlansMapper.insertSelective", record);
	}

	@Override
	public WorkorderImplePlans selectByPrimaryKey(Long plansId) {
		return this.selectOne("com.ycnet.frms.mapper.WorkorderImplePlansMapper.selectByPrimaryKey", plansId);
	}

	@Override
	public int updateByPrimaryKeySelective(WorkorderImplePlans record) {
		return this.update("com.ycnet.frms.mapper.WorkorderImplePlansMapper.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(WorkorderImplePlans record) {
		return this.update("com.ycnet.frms.mapper.WorkorderImplePlansMapper.updateByPrimaryKey", record);
	}

	/**
     * 
    * @Title: deleteByDesignroutesId 
    * @Description: 删除实施施工方案 
    * @param @param designroutesId
    * @param @return    
    * @return int
    * @author liucanghai 
    * @throws
    * @date 2018年4月24日 下午1:45:55 
    * @version V1.0
     */
	@Override
	public int deleteByDesignroutesId(Long designroutesId) {
		return this.delete("com.ycnet.frms.mapper.WorkorderImplePlansMapper.deleteByDesignroutesId", designroutesId);
	}

	/**
	 * 
	* @Title: queryByDesignroutesId 
	* @Description: 查询施工反馈信息 
	* @param @param designroutesId
	* @param @return    
	* @return WorkorderImplePlans
	* @author liucanghai 
	* @throws
	* @date 2018年4月25日 下午1:50:52 
	* @version V1.0
	 */
	@Override
	public WorkorderImplePlans queryByDesignroutesId(Long designroutesId) {
		return this.selectOne("com.ycnet.frms.mapper.WorkorderImplePlansMapper.queryByDesignroutesId", designroutesId);
	}

	/**
	 * 
	* @Title: queryByFiberDataCount 
	* @Description: 根据端子查询实施方案有没有数据 
	* @param @param orgId
	* @param @param code
	* @param @return    
	* @return int
	* @author liucanghai 
	* @throws
	* @date 2018年5月29日 上午11:45:57 
	* @version V1.0
	 */
	@Override
	public int queryByFiberDataCount(Long orgId, String code) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("orgId", orgId);
		map.put("code", code);
		return this.selectOne("com.ycnet.frms.mapper.WorkorderImplePlansMapper.queryByFiberDataCount",map);
	}

	/**
	 * 
	 * @Title: queryImplAByDesignroutesId
	 * @Description: 根据designroutesId,查询A端实际完成方案信息
	 * @param @param designroutesId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月11日 下午1:38:41
	 * @version V1.0
	 */
	@Override
	public WorkorderFiberdiscabindImgBean queryImplAByDesignroutesId(Long designroutesId) {
		return this.selectOne("com.ycnet.frms.mapper.WorkorderImplePlansMapper.queryImplAByDesignroutesId",designroutesId);
	}

	/**
	 * 
	 * @Title: queryImplZByDesignroutesId
	 * @Description: 根据designroutesId,查询Z端实际完成方案信息
	 * @param @param designroutesId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月11日 下午2:17:43
	 * @version V1.0
	 */
	@Override
	public WorkorderFiberdiscabindImgBean queryImplZByDesignroutesId(Long designroutesId) {
		return this.selectOne("com.ycnet.frms.mapper.WorkorderImplePlansMapper.queryImplZByDesignroutesId",designroutesId);
	}

	/**
	 * 根据端子查询盘等信息
	* @Title: queryImplAByFiberDiscId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param afiberDiscId
	* @param @return    入参
	* @return WorkorderFiberdiscabindBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年9月6日 下午3:30:29 
	* @version V1.0
	 */
	@Override
	public WorkorderFiberdiscabindBean queryImplAByFiberDiscId(Long afiberDiscId) {
		return this.selectOne("com.ycnet.frms.mapper.WorkorderImplePlansMapper.queryImplAByFiberDiscId",afiberDiscId);
	}

}
