package com.ycnet.frms.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.core.util.KVBean;
import com.ycnet.frms.bean.Basecode;
import com.ycnet.frms.mapper.BasecodeMapper;

@Repository("basecodeMapper")
public class BasecodeMapperImpl extends  BaseSqlSupport 
		implements BasecodeMapper{
	
		@Override
		public int insert(Basecode record)
		{
			return this.insert("com.ycnet.frms.mapper.BasecodeMapper.insert",record);
		}
	
		@Override
		public Basecode selectByPrimaryKey(Long id)
		{
			return this.selectOne("com.ycnet.frms.mapper.BasecodeMapper.selectByPrimaryKey",id);
		}
	
		@Override
		public int deleteByPrimaryKey(Long id)
		{
			return this.delete("com.ycnet.frms.mapper.BasecodeMapper.deleteByPrimaryKey",id);
		}
	
		@Override
		public int insertSelective(Basecode record)
		{
			return this.insert("com.ycnet.frms.mapper.BasecodeMapper.insertSelective",record);
		}
	
		@Override
		public int updateByPrimaryKeySelective(Basecode record)
		{
			return this.update("com.ycnet.frms.mapper.BasecodeMapper.updateByPrimaryKeySelective",record);
		}
	
		@Override
		public int updateByPrimaryKey(Basecode record)
		{
			return this.update("com.ycnet.frms.mapper.BasecodeMapper.updateByPrimaryKey",record);
		}

		@Override
		public List<Basecode> selectSimilar(String classCode) {
			return this.selectList("com.ycnet.frms.mapper.BasecodeMapper.selectSimilar", classCode);
		}

		@Override
		public List<Basecode> getBaseCodeList(Basecode basecode) {
			
			return this.selectList("com.ycnet.frms.mapper.BasecodeMapper.getBaseCodeList", basecode);
		}

		@Override
		public List<Basecode> getClasscode() {
			
			return this.selectList("com.ycnet.frms.mapper.BasecodeMapper.getClasscode");
		}
		
		/** 数据字典 代码值唯一校验  **/
		@Override
		public Basecode basecodeValueCodeVerify(Basecode basecode) {
			
			return this.selectOne("com.ycnet.frms.mapper.BasecodeMapper.basecodeValueCodeVerify",basecode);
		}

		/**
		 * 查询分页  2017-9-26 刘沧海
		 */
		@Override
		public List<Basecode> queryBaseCodeList(Map<String, Object> map) {
			return this.selectList("com.ycnet.frms.mapper.BasecodeMapper.queryBaseCodeList", map);
		}

		/**
		 * 查询总数  2017-9-26 刘沧海
		 */
		@Override
		public int queryBaseCodeCount(Map<String, Object> map) {
			return this.selectOne("com.ycnet.frms.mapper.BasecodeMapper.queryBaseCodeCount", map);
		}

		//导出数据库  刘沧海  2017/10/13
		@Override
		public List<Basecode> queryList() {
			return this.selectList("com.ycnet.frms.mapper.BasecodeMapper.queryList");
		}

		/**
		 * 产权性质查询
		* 
		* @Title: BasecodeMapper.java 
		* @Description: TODO 
		* @param @return
		* @return List<Basecode>
		* @author fl
		* @date 2018年1月30日 下午4:35:55
		* @version V1.0
		 */
		@Override
		public List<Basecode> querynopTypes() {
			return selectList("com.ycnet.frms.mapper.BasecodeMapper.querynopTypes");
		}
		/**
		 * 
		* @Title: getplatformList 
		* @Description: 查询所有平台类型
		* @param @return    入参
		* @return List<String>    返回类型
		* @author zhouyu 
		* @throws
		* @date 2018年2月7日 下午4:01:40 
		* @version V1.0
		 */
		@Override
		public List<Basecode> getplatformList() {
			return selectList("com.ycnet.frms.mapper.BasecodeMapper.getplatformList");
		}
		/**
		 * 
		* @Title: getplatformListByValueVode 
		* @Description: 根据code查询平台类型
		* @param @return    入参
		* @return Basecode    返回类型
		* @author 周宇（作者） 
		* @throws
		* @date 2018年2月11日 下午2:19:52 
		* @version V1.0
		 */
		@Override
		public Basecode getplatformListByValueVode(String valueCode) {
			return this.selectOne("com.ycnet.frms.mapper.BasecodeMapper.getplatformListByValueVode", valueCode);
		}
		
		
}