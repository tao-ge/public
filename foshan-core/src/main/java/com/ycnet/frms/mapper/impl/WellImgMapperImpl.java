package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.WellImg;
import com.ycnet.frms.mapper.WellImgMapper;

@Repository("wellImgMapper")
public class WellImgMapperImpl extends  BaseSqlSupport 
		implements WellImgMapper{
	
		@Override
		public int insert(WellImg record)
		{
			return this.insert("com.ycnet.frms.mapper.WellImgMapper.insert",record);
		}
	
		@Override
		public int insertSelective(WellImg record)
		{
			return this.insert("com.ycnet.frms.mapper.WellImgMapper.insertSelective",record);
		}
	
		@Override
		public int updateByPrimaryKeySelective(WellImg record)
		{
			return this.update("com.ycnet.frms.mapper.WellImgMapper.updateByPrimaryKeySelective",record);
		}
	
		@Override
		public int updateByPrimaryKey(WellImg record)
		{
			return this.update("com.ycnet.frms.mapper.WellImgMapper.updateByPrimaryKey",record);
		}

		@Override
		public int deleteByPrimaryKey(Long wellImgId) {
			return this.delete("com.ycnet.frms.mapper.WellImgMapper.deleteByPrimaryKey",wellImgId);
		}

		@Override
		public WellImg selectByPrimaryKey(Long wellImgId) {
			return this.selectOne("com.ycnet.frms.mapper.WellImgMapper.selectByPrimaryKey",wellImgId);
		}

		/**
	     * 根据设施ID,查询WellImg列表
	    * @Title: selectByWellId 
	    * @Description: TODO(这里用一句话描述这个方法的作用) 
	    * @param @param wellId
	    * @param @return    入参
	    * @return List<WellImg>    返回类型
	    * @author FL（作者） 
	    * @throws
	    * @date 2018年3月20日 下午2:04:19 
	    * @version V1.0
	     */
		@Override
		public List<WellImg> selectByWellId(Long wellId) {
			return this.selectList("com.ycnet.frms.mapper.WellImgMapper.selectByWellId",wellId);
		}

		/**
		 * 根据井ID,删除WellImg列表
		* @Title: deleteByWellId 
		* @Description: TODO(这里用一句话描述这个方法的作用) 
		* @param @param wellId
		* @param @return    入参
		* @return long    返回类型
		* @author FL（作者） 
		* @throws
		* @date 2018年3月20日 下午3:17:32 
		* @version V1.0
		 */
		@Override
		public long deleteByWellId(Long wellId) {
			return this.delete("com.ycnet.frms.mapper.WellImgMapper.deleteByWellId",wellId);
		}
		
}
