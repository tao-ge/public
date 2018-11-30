package com.ycnet.frms.mapper;

import java.util.Map;

import com.ycnet.frms.bean.WorkorderFiberdiscabindA;
import com.ycnet.frms.vo.WorkorderFiberdiscabindBean;

public interface WorkorderFiberdiscabindAMapper {

	int deleteByPrimaryKey(Long designId);

    int insert(WorkorderFiberdiscabindA record);

    int insertSelective(WorkorderFiberdiscabindA record);

    WorkorderFiberdiscabindA selectByPrimaryKey(Long designId);

    int updateByPrimaryKeySelective(WorkorderFiberdiscabindA record);

    int updateByPrimaryKey(WorkorderFiberdiscabindA record);
    
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
	WorkorderFiberdiscabindA queryByRoutsIds(Long designroutesId, Long adevId);

	/**
	 * 
	 * 
	 * @Title: selectByDevIdAndFiberDiscId
	 * @Description: 根据devId和fiberDiscId查询一条
	 * @param @param devId
	 * @param @param fiberDiscId
	 * @param @return 
	 * @return WorkorderFiberdiscabindA 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月5日 下午5:55:23
	 * @version V1.0
	 */
	WorkorderFiberdiscabindA selectByDevIdAndFiberDiscId(Map<String, Object> map);

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
	WorkorderFiberdiscabindBean queryWorkorderFiberdiscabindAByDesignroutesId(Long designroutesId);

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
	WorkorderFiberdiscabindA selectById(Long designroutesId);

	/**
	 * 
	 * @Title: queryWorkorderFiberdiscabindAByAfiberDiscId
	 * @Description: 根据afiberDiscId查询
	 * @param @param afiberDiscId
	 * @param @return 
	 * @return WorkorderFiberdiscabindBean 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月12日 上午9:38:58
	 * @version V1.0
	 */
	WorkorderFiberdiscabindBean queryWorkorderFiberdiscabindAByAfiberDiscId(Long afiberDiscId);

}
