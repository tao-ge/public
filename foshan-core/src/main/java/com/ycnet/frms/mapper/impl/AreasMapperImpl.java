package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.Areas;
import com.ycnet.frms.mapper.AreasMapper;
import com.ycnet.frms.vo.AreasBean;
import com.ycnet.frms.vo.AreasParent;
import com.ycnet.frms.vo.AreasVo;
import com.ycnet.frms.vo.Position;

@Repository("areasMapper")
public class AreasMapperImpl extends  BaseSqlSupport 
		implements AreasMapper{
	
		@Override
		public int insert(Areas record)
		{
			return this.insert("com.ycnet.frms.mapper.AreasMapper.insert",record);
		}
	
		@Override
		public Areas selectByPrimaryKey(String record)
		{
			return this.selectOne("com.ycnet.frms.mapper.AreasMapper.selectByPrimaryKey",record);
		}
	
		@Override
		public int deleteByPrimaryKey(String record)
		{
			return this.delete("com.ycnet.frms.mapper.AreasMapper.deleteByPrimaryKey",record);
		}
	
		@Override
		public int insertSelective(Areas record)
		{
			return this.insert("com.ycnet.frms.mapper.AreasMapper.insertSelective",record);
		}
	
		@Override
		public int updateByPrimaryKeySelective(Areas record)
		{
			return this.update("com.ycnet.frms.mapper.AreasMapper.updateByPrimaryKeySelective",record);
		}
	
		@Override
		public int updateByPrimaryKey(Areas record)
		{
			return this.update("com.ycnet.frms.mapper.AreasMapper.updateByPrimaryKey",record);
		}

		@Override
		public List<Areas> selectByParent(@Param("parentAreaCode")String parentAreaCode) {
        
            //Map<String,String> map=new HashMap<String,String>();
           // map.put("parentAreaCode", parentAreaCode);
            
			return this.selectList("com.ycnet.frms.mapper.AreasMapper.selectByParent",parentAreaCode);
		}

		@Override
		public List<Areas> selectByAreaRank(Areas record) {
		  //18/1/16zhouyu 改 根据区域查询
			return this.selectList("com.ycnet.frms.mapper.AreasMapper.selectByAreaRanks",record);
		}

		//导出数据库用  刘沧海 2017/10/12
		@Override
		public List<Areas> queryList() {
			return this.selectList("com.ycnet.frms.mapper.AreasMapper.queryList");
		}

		/**
		 * 
		* @Title: selectByParentJQ 
		* @Description: TODO(根据父类局域代码查询子类汇聚区) 
		* @param @param code
		* @param @return    入参
		* @return List<Areas>    返回类型
		* @author （刘沧海） 
		* @throws
		* @date 2017年10月26日 上午11:22:24 
		* @version V1.0
		 */
		@Override
		public List<Areas> selectByParentJQ(String code) {
			return this.selectList("com.ycnet.frms.mapper.AreasMapper.selectByParentJQ",code);
		}

		/**
		 * 
		* @Title: selectByParentJQ 
		* @Description: TODO(根据局域代码查询汇聚区) 
		* @param @param code
		* @param @return    入参
		* @return List<Areas>    返回类型
		* @author （刘沧海） 
		* @throws
		* @date 2017年10月26日 上午11:22:24 
		* @version V1.0
		 */
		@Override
		public List<Areas> selectByChildrenJQ(String code) {
			return this.selectList("com.ycnet.frms.mapper.AreasMapper.selectByChildrenJQ",code);
		}

		/**
		 * 	
		* @Title: selectByParentChild 
		* @Description: 根据父类区域编码查询子类 
		* @param @param code1
		* @param @return    
		* @return List<Areas>
		* @author liucanghai 
		* @throws
		* @date 2018年1月17日 下午4:45:41 
		* @version V1.0
		 */
		@Override
		public List<AreasParent> selectByParentChild(String code) {
			return this.selectList("com.ycnet.frms.mapper.AreasMapper.selectByParentChild",code);
		}

		/**
		 * fl 根据Code 查询本汇聚去编码
		 * @param areaCode1
		 * @date 2018年2月212日 上午11:22:24 
		 * @return
		 */
		@Override
		public AreasParent selectByCode(String areaCode1) {
			
			return this.selectOne("com.ycnet.frms.mapper.AreasMapper.selectByCode",areaCode1);
		}

		
		/**
		 * 查询省市区
		* @Title: queryThreeAreas 
		* @Description: TODO(这里用一句话描述这个方法的作用) 
		* @param @return    入参
		* @return List<AreasBean>    返回类型
		* @author FL（作者） 
		* @throws
		* @date 2018年6月14日 下午3:11:03 
		* @version V1.0
		 */
		@Override
		public List<AreasBean> queryThreeAreas() {
			return this.selectList("com.ycnet.frms.mapper.AreasMapper.queryThreeAreas");
		}

		/**
		 * 查询省市区镇
		* @Title: queryPosition 
		* @Description: TODO(这里用一句话描述这个方法的作用) 
		* @param @return    入参
		* @return List<Position>    返回类型
		* @author FL（作者） 
		* @throws
		* @date 2018年6月15日 上午10:40:26 
		* @version V1.0
		 */
		@Override
		public List<Position> queryPosition() {
			return this.selectList("com.ycnet.frms.mapper.AreasMapper.queryPosition");
		}
		
	
		
		
}
