package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.TransfLogEntity;
import com.ycnet.frms.vo.TransfLogEntityBean;

public interface TransfLogEntityMapper {
    int deleteByPrimaryKey(Long orgId);

    int insert(TransfLogEntity record);

    int insertSelective(TransfLogEntity record);

    TransfLogEntity selectByPrimaryKey(Long orgId);

    int updateByPrimaryKeySelective(TransfLogEntity record);

    int updateByPrimaryKey(TransfLogEntity record);

    /**
     * 设备上报日志查询
    * @Title: queryAll 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param map
    * @param @return    入参
    * @return List<TransfLogEntity>    返回类型
    * @author FL（作者） 
    * @throws
    * @date 2018年5月24日 上午11:09:31 
    * @version V1.0
     */
	List<TransfLogEntity> queryAll(Map<String, Object> map);

	/**
	 * 设备上报日志记录总条数
	* @Title: queryAllCount 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param map
	* @param @return    入参
	* @return Integer    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月24日 上午11:09:44 
	* @version V1.0
	 */
	Integer queryAllCount(Map<String, Object> map);

}