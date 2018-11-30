package com.ycnet.frms.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.FacilityImg;
import com.ycnet.frms.mapper.FacilityImgMapper;

@Repository("facilityImgMapper")
public class FacilityImgMapperImpl extends  BaseSqlSupport 
		implements FacilityImgMapper{
	
		@Override
		public int insert(FacilityImg record)
		{
			return this.insert("com.ycnet.frms.mapper.FacilityImgMapper.insert",record);
		}
	
		@Override
		public FacilityImg selectByPrimaryKey(Long id)
		{
			return this.selectOne("com.ycnet.frms.mapper.FacilityImgMapper.selectByPrimaryKey",id);
		}
	
		@Override
		public int deleteByPrimaryKey(Long id)
		{
			return this.delete("com.ycnet.frms.mapper.FacilityImgMapper.deleteByPrimaryKey",id);
		}
	
		@Override
		public int insertSelective(FacilityImg record)
		{
			return this.insert("com.ycnet.frms.mapper.FacilityImgMapper.insertSelective",record);
		}
	
		@Override
		public int updateByPrimaryKeySelective(FacilityImg record)
		{
			return this.update("com.ycnet.frms.mapper.FacilityImgMapper.updateByPrimaryKeySelective",record);
		}
	
		@Override
		public int updateByPrimaryKey(FacilityImg record)
		{
			return this.update("com.ycnet.frms.mapper.FacilityImgMapper.updateByPrimaryKey",record);
		}

		@Override
		public List<FacilityImg> selectImg(FacilityImg param) {

			return this.selectList("com.ycnet.frms.mapper.FacilityImgMapper.selectImg",param);
		}

		@Override
		public List<FacilityImg> selectByInspectedId(Long inspectedId) {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.FacilityImgMapper.selectByInspectedId",inspectedId);
		}
		
		/*
		 * 查询数据列表按map
		 * @see com.ycnet.frms.mapper.FacilityMapper#queryByConditionMap(java.util.Map)
		 */
		@Override
		public List<FacilityImg> queryByConditionMap(Map<String, Object> map) {
			// TODO Auto-generated method stub
			//System.out.println(this.count("com.ycnet.frms.mapper.FacilityMapper.queryByConditionMap", map));
			return this.selectList("com.ycnet.frms.mapper.FacilityImgMapper.queryByConditionMap",map);
		}

		@Override
		public int queryCountByConditionMap(Map<String, Object> map) {			
			return this.selectOne("com.ycnet.frms.mapper.FacilityImgMapper.queryCountByConditionMap", map);
		}

		@Override
		public List<FacilityImg> selectByDevId(Long devId) {
			return this.selectList("com.ycnet.frms.mapper.FacilityImgMapper.selectByDevId",devId);
		}

		@Override
		public FacilityImg selectByDevUrl(String paths) {
			return this.selectOne("com.ycnet.frms.mapper.FacilityImgMapper.selectByDevUrl",paths);
		}

		@Override
		public int saveUrl(FacilityImg facilityImg) {
			return this.insert("com.ycnet.frms.mapper.FacilityImgMapper.saveUrl",facilityImg);
		}

		@Override
		public int deleteUrl(String paths) {
			return this.delete("com.ycnet.frms.mapper.FacilityImgMapper.deleteUrl",paths);
		}
		
		@Override
		public int deleteByDevId(Long devId) {
			return this.delete("com.ycnet.frms.mapper.FacilityImgMapper.deleteByDevId",devId);
		}

		//导出数据库  刘沧海 2017/10/13
		@Override
		public List<FacilityImg> queryList() {
			return this.selectList("com.ycnet.frms.mapper.FacilityImgMapper.queryList");
		}

		/**
		 * 
		 * @Title: selectByInspectedIdAndDevId
		 * @Description: 根据巡检ID和设施ID查询巡检图片
		 * @param @param inspectId
		 * @param @param devId
		 * @param @return
		 * @return String    返回类型
		 * @author DZY 
		 * @throws
		 * @date 2018年3月13日 下午2:35:58
		 * @version V1.0
		 */
		@Override
		public FacilityImg selectByInspectedIdAndDevId(Map<String,Object> map) {
			return this.selectOne("com.ycnet.frms.mapper.FacilityImgMapper.selectByInspectedIdAndDevId", map);
		}

		/**
		 * @Title: deleteByDevIdAndUrl
		 * @Description: 根据devId和imgUrl删除
		 * @param @param map
		 * @param @return
		 * @return String    返回类型
		 * @author DZY 
		 * @throws
		 * @date 2018年7月16日 下午3:15:45
		 * @version V1.0
		 */
		@Override
		public int deleteByDevIdAndUrl(Map<String, Object> map) {
			return this.delete("com.ycnet.frms.mapper.FacilityImgMapper.deleteByDevIdAndUrl", map);
		}
}