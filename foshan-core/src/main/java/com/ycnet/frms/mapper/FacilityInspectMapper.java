package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.FacilityInspect;
import com.ycnet.frms.vo.FacilityInspectConditionBean;

public interface FacilityInspectMapper {
    int deleteByPrimaryKey(Long inspectId);

    int insert(FacilityInspect record);

    int insertSelective(FacilityInspect record);

    FacilityInspect selectByPrimaryKey(Long inspectId);

    int updateByPrimaryKeySelective(FacilityInspect record);

    int updateByPrimaryKey(FacilityInspect record);
    
    /**
     * 查询我的巡检记录
     * @author YHT
     * @time   2016年7月8日 上午8:23:50
     * @param bean
     * @return
     */
    List<FacilityInspect> queryByConditionBean(FacilityInspectConditionBean bean);
    
    /**
     * web查询巡检记录
     * @author YHT
     * @time   2016年7月28日 下午7:53:18
     * @param bean
     * @return
     */
    List<FacilityInspect> queryByConditionBeans(Map<String,Object> map);
    
    int queryCountByConditionMap(Map<String,Object> map);

    /**
	 * 
	* @Title: queryFacilityInspectByDevId 
	* @Description: 根据设施ID查询巡检历史任务 
	* @param @param devId
	* @param @param session
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年2月9日 下午2:07:35 
	* @version V1.0
	 */
	List<FacilityInspect> queryFacilityInspectByDevId(Facility facility);

	/**
	 * 
	 * @Title: queryByCondition
	 * @Description: 查询刚刚添加完的那条数据
	 * @param @param fin
	 * @param @return 
	 * @return FacilityInspect 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年3月13日 下午4:43:15
	 * @version V1.0
	 */
	FacilityInspect queryByCondition(FacilityInspect fin);
	
}