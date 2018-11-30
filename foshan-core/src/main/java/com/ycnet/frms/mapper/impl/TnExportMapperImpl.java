package com.ycnet.frms.mapper.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.Fiberdisc;
import com.ycnet.frms.bean.FiberdiscGroup;
import com.ycnet.frms.bean.TnExport;
import com.ycnet.frms.mapper.TnExportMapper;
import com.ycnet.frms.vo.FacilityAll;
import com.ycnet.frms.vo.Group;


@Repository("exportMapper")
public class TnExportMapperImpl extends  BaseSqlSupport 
		implements TnExportMapper{
	
		@Override
		public int insert(TnExport record)
		{
			return this.insert("com.ycnet.frms.mapper.TnExportMapper.insert",record);
		}
	
		@Override
		public TnExport selectByPrimaryKey(Long id)
		{
			return this.selectOne("com.ycnet.frms.mapper.TnExportMapper.selectByPrimaryKey",id);
		}
	
		@Override
		public int deleteByPrimaryKey(Long id)
		{
			return this.delete("com.ycnet.frms.mapper.TnExportMapper.deleteByPrimaryKey",id);
		}
	
		@Override
		public int insertSelective(TnExport record)
		{
			return this.insert("com.ycnet.frms.mapper.TnExportMapper.insertSelective",record);
		}
		
		@Override
		public int insertSelective01(TnExport record)
		{
			return this.insert("com.ycnet.frms.mapper.TnExportMapper.insertSelective01",record);
		}
	
		@Override
		public int updateByPrimaryKeySelective(TnExport record)
		{
			return this.update("com.ycnet.frms.mapper.TnExportMapper.updateByPrimaryKeySelective",record);
		}
		
		@Override
		public int updateByPrimaryKeySelective2(TnExport record)
		{
			return this.update("com.ycnet.frms.mapper.TnExportMapper.updateByPrimaryKeySelective2",record);
		}
	
		@Override
		public int updateByPrimaryKey(TnExport record)
		{
			return this.update("com.ycnet.frms.mapper.TnExportMapper.updateByPrimaryKey",record);
		}

		@Override
		public List<TnExport> selectByparam(String routeType) {
			return this.selectList("com.ycnet.frms.mapper.TnExportMapper.selectByparam",routeType);
		}

		@Override
		public int deleteByParam(String routeType) {
			return this.delete("com.ycnet.frms.mapper.TnExportMapper.deleteByParam",routeType);
		}

		@Override
		public List<TnExport> selectByparams(String routeType) {
			return this.selectList("com.ycnet.frms.mapper.TnExportMapper.selectByparams",routeType);
		}

		@Override
		public TnExport selectByRouteName(String routeName) {
			return this.selectOne("com.ycnet.frms.mapper.TnExportMapper.selectByRouteName",routeName);
		}
		
		@Override
		public List<TnExport> selectByparamByType(String routeType) {
			return this.selectList("com.ycnet.frms.mapper.TnExportMapper.selectByparamByType",routeType);
		}

		@Override
		public List<FacilityAll> labExport() {
			return this.selectList("com.ycnet.frms.mapper.TnExportMapper.labExport");
		}

		@Override
		public List<FiberdiscGroup> FGlist(Long devId, String side) {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("devId", devId);
			params.put("side", side);
			return this.selectList("com.ycnet.frms.mapper.TnExportMapper.FGlist",params);
		}

		@Override
		public List<Fiberdisc> selectFiber(String code) {
			return this.selectList("com.ycnet.frms.mapper.TnExportMapper.selectFiber",code);
		}

		@Override
		public List<TnExport> selectByTnExport(TnExport export) {
			return this.selectList("com.ycnet.frms.mapper.TnExportMapper.selectByTnExport",export);
		}

		@Override
		public List<TnExport> selectTnExport01() {
			return this.selectList("com.ycnet.frms.mapper.TnExportMapper.selectTnExport01");
		}

		@Override
		public int deleteTnExport01() {
			return this.delete("com.ycnet.frms.mapper.TnExportMapper.deleteTnExport01");
		}

		@Override
		public List<TnExport> selectJT() {
			List<TnExport> list = this.selectList("com.ycnet.frms.mapper.TnExportMapper.selectJTn");
			List<TnExport> list2 = this.selectList("com.ycnet.frms.mapper.TnExportMapper.selectJTp");
			list.addAll(list2);
			return list;
		}
		/**
	     * 
	    * @Title: queryList 
	    * @Description: TODO(查询A端数据表) 
	    * @param @return    入参
	    * @return List<TnExport>    返回类型
	    * @author （刘沧海） 
	    * @throws
	    * @date 2017年11月8日 下午4:16:29 
	    * @version V1.0
	     */
		@Override
		public List<TnExport> queryList() {
			return this.selectList("com.ycnet.frms.mapper.TnExportMapper.queryList");
		}

		

		@Override
		public List<TnExport> selectExport03() {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.TnExportMapper.selectExport03");
		}

		@Override
		public TnExport selectByRouteId(String routeId) {
			// TODO Auto-generated method stub
			return this.selectOne("com.ycnet.frms.mapper.TnExportMapper.selectByRouteId",routeId);
		}

		@Override
		public List<TnExport> selectExport19() {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.TnExportMapper.selectExport19");
		}

		@Override
		public List<TnExport> selectExport20() {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.TnExportMapper.selectExport20");
		}

		@Override
		public List<TnExport> selectExport01() {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.TnExportMapper.selectExport01");
		}
		
		@Override
		public List<TnExport> selectExport11() {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.TnExportMapper.selectExport11");
		}

		@Override
		public List<TnExport> selectExport00() {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.TnExportMapper.selectExport00");
		}

		@Override
		public List<TnExport> selectExportgl() {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.TnExportMapper.selectExportgl");
		}
		
		@Override
		public List<TnExport> selectExport04() {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.TnExportMapper.selectExport04");
		}
		
		@Override
		public List<TnExport> selectExportWell() {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.TnExportMapper.selectExportWell");
		}
		
		@Override
		public List<TnExport> selectExportPiping() {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.TnExportMapper.selectExportPiping");
		}
		
		@Override
		public List<TnExport> selectExportSection() {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.TnExportMapper.selectExportSection");
		}
		
		@Override
		public int deleteBy00(Long id)
		{
			return this.delete("com.ycnet.frms.mapper.TnExportMapper.deleteBy00",id);
		}
		
		@Override
		public int deleteByGl(Long id)
		{
			return this.delete("com.ycnet.frms.mapper.TnExportMapper.deleteByGl",id);
		}

		@Override
		public List<TnExport> selectExportguanglu() {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.TnExportMapper.selectExportguanglu");
		}
		
		@Override
		public List<TnExport> selectExport() {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.TnExportMapper.selectExport");
		}

		@Override
		public List<TnExport> queryRouteText() {
			return this.selectList("com.ycnet.frms.mapper.TnExportMapper.queryRouteText");
		}

		@Override
		public List<TnExport> pdhcopy() {
			return this.selectList("com.ycnet.frms.mapper.TnExportMapper.pdhcopy");
		}

		@Override
		public List<TnExport> selectExportAreaCode() {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.TnExportMapper.selectExportAreaCode");
		}
}