package com.ycnet.frms.mapper.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.WorkorderImplePlansImg;
import com.ycnet.frms.mapper.WorkorderImplePlansImgMapper;
import com.ycnet.frms.vo.WorkorderImplePlansImgBean;
@Repository("workorderImplePlansImgMapper")
public class WorkorderImplePlansImgMapperImpl extends BaseSqlSupport implements WorkorderImplePlansImgMapper {

	@Override
	public int deleteByPrimaryKey(Long planImgId) {
		return this.delete("com.ycnet.frms.mapper.WorkorderImplePlansImgMapper.deleteByPrimaryKey", planImgId);
	}

	@Override
	public int insert(WorkorderImplePlansImg record) {
		return this.insert("com.ycnet.frms.mapper.WorkorderImplePlansImgMapper.insert", record);
	}

	@Override
	public int insertSelective(WorkorderImplePlansImg record) {
		return this.insert("com.ycnet.frms.mapper.WorkorderImplePlansImgMapper.insertSelective", record);
	}

	@Override
	public WorkorderImplePlansImg selectByPrimaryKey(Long planImgId) {
		return this.selectOne("com.ycnet.frms.mapper.WorkorderImplePlansImgMapper.selectByPrimaryKey", planImgId);
	}

	@Override
	public int updateByPrimaryKeySelective(WorkorderImplePlansImg record) {
		return this.update("com.ycnet.frms.mapper.WorkorderImplePlansImgMapper.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(WorkorderImplePlansImg record) {
		return this.update("com.ycnet.frms.mapper.WorkorderImplePlansImgMapper.updateByPrimaryKey", record);
	}

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
	@Override
	public int deleteImplePlansImg(Long plansId) {
		return this.delete("com.ycnet.frms.mapper.WorkorderImplePlansImgMapper.deleteImplePlansImg", plansId);
	}

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
	@Override
	public List<WorkorderImplePlansImgBean> queryByDesignroutesId(Long designroutesId) {
		return this.selectList("com.ycnet.frms.mapper.WorkorderImplePlansImgMapper.queryByDesignroutesId", designroutesId);
	}

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
	@Override
	public List<WorkorderImplePlansImgBean> queryByPlansId(Long plansId) {
		// TODO Auto-generated method stub
		return this.selectList("com.ycnet.frms.mapper.WorkorderImplePlansImgMapper.queryByPlansId", plansId);
	}

}
