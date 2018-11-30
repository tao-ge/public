package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.WorkorderImplePlansImg;
import com.ycnet.frms.vo.WorkorderImplePlansImgBean;

public interface WorkorderImplePlansImgMapper {
    int deleteByPrimaryKey(Long planImgId);

    int insert(WorkorderImplePlansImg record);

    int insertSelective(WorkorderImplePlansImg record);

    WorkorderImplePlansImg selectByPrimaryKey(Long planImgId);

    int updateByPrimaryKeySelective(WorkorderImplePlansImg record);

    int updateByPrimaryKey(WorkorderImplePlansImg record);

    /**
     * 
    * @Title: deleteImplePlansImg 
    * @Description: 根据光路ID删除施工反馈图片 
    * @param @param designroutesId
    * @param @return    
    * @return int
    * @author liucanghai 
    * @throws
    * @date 2018年4月25日 下午1:47:11 
    * @version V1.0
     */
	int deleteImplePlansImg(Long plansId);

	/**
	 * 查询图片及反馈信息
	* @Title: queryByDesignroutesId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param designroutesId
	* @param @return    入参
	* @return List<WorkorderImplePlansImgBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月6日 上午11:34:09 
	* @version V1.0
	 */
	List<WorkorderImplePlansImgBean> queryByDesignroutesId(Long designroutesId);

	/**
	 * 更具计划ID查询图片信息
	* @Title: queryByPlansId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param plansId
	* @param @return    入参
	* @return List<WorkorderImplePlansImgBean>    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年9月13日 上午11:26:45 
	* @version V1.0
	 */
	List<WorkorderImplePlansImgBean> queryByPlansId(Long plansId);
}