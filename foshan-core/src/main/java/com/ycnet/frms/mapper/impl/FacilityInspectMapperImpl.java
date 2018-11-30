package com.ycnet.frms.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.FacilityInspect;
import com.ycnet.frms.mapper.FacilityInspectMapper;
import com.ycnet.frms.vo.FacilityInspectConditionBean;

@Repository("facilityInspectMapper")
public class FacilityInspectMapperImpl extends  BaseSqlSupport 
		implements FacilityInspectMapper{
	
		@Override
		public int insert(FacilityInspect record)
		{
			
			
			int i=this.insert("com.ycnet.frms.mapper.FacilityInspectMapper.insert",record);
			return i; 
		}
	
		@Override
		public FacilityInspect selectByPrimaryKey(Long id)
		{
			return this.selectOne("com.ycnet.frms.mapper.FacilityInspectMapper.selectByPrimaryKey",id);
		}
	
		@Override
		public int deleteByPrimaryKey(Long id)
		{
			return this.delete("com.ycnet.frms.mapper.FacilityInspectMapper.deleteByPrimaryKey",id);
		}
	
		@Override
		public int insertSelective(FacilityInspect record)
		{
			return this.insert("com.ycnet.frms.mapper.FacilityInspectMapper.insertSelective",record);
		}
	
		@Override
		public int updateByPrimaryKeySelective(FacilityInspect record)
		{
			return this.update("com.ycnet.frms.mapper.FacilityInspectMapper.updateByPrimaryKeySelective",record);
		}
	
		@Override
		public int updateByPrimaryKey(FacilityInspect record)
		{
			return this.update("com.ycnet.frms.mapper.FacilityInspectMapper.updateByPrimaryKey",record);
		}
		
		@Override
		public List<FacilityInspect> queryByConditionBean(FacilityInspectConditionBean bean) {
			return this.selectList("com.ycnet.frms.mapper.FacilityInspectMapper.queryByConditionBean", bean);
		}

		@Override
		public List<FacilityInspect> queryByConditionBeans(Map<String,Object> map) {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.FacilityInspectMapper.queryByConditionBeans", map);
		}
		
		@Override
		public int queryCountByConditionMap(Map<String, Object> map) {
			// TODO Auto-generated method stub
			return this.selectOne("com.ycnet.frms.mapper.FacilityInspectMapper.queryCountByConditionMap", map);
		}

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
		@Override
		public List<FacilityInspect> queryFacilityInspectByDevId(Facility facility) {
			return this.selectList("com.ycnet.frms.mapper.FacilityInspectMapper.queryFacilityInspectByDevId", facility);
		}

		/**
		 * 
		 * @Title: queryByCondition
		 * @Description: 查询刚刚添加完的那条数据
		 * @param @param fin
		 * @param @return
		 * @return String    返回类型
		 * @author DZY 
		 * @throws
		 * @date 2018年3月13日 下午4:45:08
		 * @version V1.0
		 */
		@Override
		public FacilityInspect queryByCondition(FacilityInspect fin) {
			return this.selectOne("com.ycnet.frms.mapper.FacilityInspectMapper.queryByCondition", fin);
		}
}