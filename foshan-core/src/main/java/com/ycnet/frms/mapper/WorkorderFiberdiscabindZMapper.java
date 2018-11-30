package com.ycnet.frms.mapper;

import java.util.Map;

import com.ycnet.frms.bean.WorkorderFiberdiscabindZ;
import com.ycnet.frms.vo.WorkorderFiberdiscabindBean;

public interface WorkorderFiberdiscabindZMapper {

	int deleteByPrimaryKey(Long id);

    int insert(WorkorderFiberdiscabindZ record);

    int insertSelective(WorkorderFiberdiscabindZ record);

    WorkorderFiberdiscabindZ selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkorderFiberdiscabindZ record);

    int updateByPrimaryKey(WorkorderFiberdiscabindZ record);
    
	/**
	 * 
	* @Title: queryByIds 
	* @Description: 查询Z端绑定端子 
	* @param @param designId
	* @param @param adevId
	* @param @return    
	* @return WorkorderFiberdiscabindZ
	* @author liucanghai 
	* @throws
	* @date 2018年4月20日 下午2:42:19 
	* @version V1.0
	 */
	WorkorderFiberdiscabindZ queryByRoutsIds(Long designroutesId, Long zdevId);

	/**
	 * 
	 * @Title: selectByDevIdAndFiberDiscId
	 * @Description: 根据devId和fiberDiscId查询一条
	 * @param @param map
	 * @param @return 
	 * @return WorkorderFiberdiscabindZ 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月6日 上午9:06:12
	 * @version V1.0
	 */
	WorkorderFiberdiscabindZ selectByDevIdAndFiberDiscId(Map<String, Object> map);

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
	WorkorderFiberdiscabindBean queryWorkorderFiberdiscabindZByDesignroutesId(Long designroutesId);

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
	WorkorderFiberdiscabindZ selectById(Long designroutesId);

	/**
	 * 
	 * @Title: queryWorkorderFiberdiscabindAByZfiberDiscId
	 * @Description: 根据zfiberDiscId查询
	 * @param @param zfiberDiscId
	 * @param @return 
	 * @return WorkorderFiberdiscabindBean 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月12日 上午9:42:52
	 * @version V1.0
	 */
	WorkorderFiberdiscabindBean queryWorkorderFiberdiscabindAByZfiberDiscId(Long zfiberDiscId);

}
