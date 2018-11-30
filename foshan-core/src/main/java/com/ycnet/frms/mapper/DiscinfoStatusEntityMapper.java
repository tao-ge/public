package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.DiscinfoStatusEntity;
import com.ycnet.frms.vo.DeviceDiscinfoEntityBean;

public interface DiscinfoStatusEntityMapper {
    int insert(DiscinfoStatusEntity record);

    int insertSelective(DiscinfoStatusEntity record);

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
	List<DeviceDiscinfoEntityBean> queryhistoryDetialByDiscId(Long discId);

	/**
	 * 
	 * @Title: deleteByCodIdAndDiscId
	 * @Description: 根据CodId和DiscId删除端子控制器状态记录
	 * @param @param codId
	 * @param @param discId
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月14日 上午10:05:37
	 * @version V1.0
	 */
	int deleteByCodIdAndDiscId(Long codId, Long discId);

	/** 
	* @Title: updateByPrimaryKeySelective 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param record    入参
	* @return void    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月12日 下午5:16:07 
	* @version V1.0   
	*/
	int updateByPrimaryKeySelective(DiscinfoStatusEntity record);
}