package com.ycnet.frms.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.InspectImg;
import com.ycnet.frms.mapper.InspectImgMapper;

@Repository("inspectImgMapper")
public class InspectImgMapperImpl extends  BaseSqlSupport 
		implements InspectImgMapper{
	
		@Override
		public int insert(InspectImg record)
		{
			return this.insert("com.ycnet.frms.mapper.InspectImgMapper.insert",record);
		}
	
		@Override
		public InspectImg selectByPrimaryKey(Long id)
		{
			return this.selectOne("com.ycnet.frms.mapper.InspectImgMapper.selectByPrimaryKey",id);
		}
	
		@Override
		public int deleteByPrimaryKey(Long id)
		{
			return this.delete("com.ycnet.frms.mapper.InspectImgMapper.deleteByPrimaryKey",id);
		}
	
		@Override
		public int insertSelective(InspectImg record)
		{
			return this.insert("com.ycnet.frms.mapper.InspectImgMapper.insertSelective",record);
		}
	
		@Override
		public int updateByPrimaryKeySelective(InspectImg record)
		{
			return this.update("com.ycnet.frms.mapper.InspectImgMapper.updateByPrimaryKeySelective",record);
		}
	
		@Override
		public int updateByPrimaryKey(InspectImg record)
		{
			return this.update("com.ycnet.frms.mapper.InspectImgMapper.updateByPrimaryKey",record);
		}

		@Override
		public List<InspectImg> selectImg(InspectImg param) {

			return this.selectList("com.ycnet.frms.mapper.InspectImgMapper.selectImg",param);
		}

		@Override
		public InspectImg selectByWorkId(Map<String, Object> map) {
			// TODO Auto-generated method stub
			return this.selectOne("com.ycnet.frms.mapper.InspectImgMapper.selectByWorkId",map);
		}

		@Override
		public List<InspectImg> selectByWorkIds(Long workId) {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.InspectImgMapper.selectByWorkIds",workId);
		}

		/*
		 * 根据workId查询巡检图片
		 * @see com.ycnet.frms.mapper.InspectImgMapper#queryListByWorkId(java.lang.Long)
		 */
		@Override
		public List<InspectImg> queryListByWorkId(Long workId) {
			return this.selectList("com.ycnet.frms.mapper.InspectImgMapper.queryListByWorkId",workId);
		}
		
		/*
		 * 查询数据列表按map
		 * @see com.ycnet.frms.mapper.FacilityMapper#queryByConditionMap(java.util.Map)
		 */
//		@Override
//		public List<InspectImg> queryByConditionMap(Map<String, Object> map) {
//			// TODO Auto-generated method stub
//			//System.out.println(this.count("com.ycnet.frms.mapper.FacilityMapper.queryByConditionMap", map));
//			return this.selectList("com.ycnet.frms.mapper.InspectImgMapper.queryByConditionMap",map);
//		}
//
//		@Override
//		public int queryCountByConditionMap(Map<String, Object> map) {			
//			return this.selectOne("com.ycnet.frms.mapper.InspectImgMapper.queryCountByConditionMap", map);
//		}
}