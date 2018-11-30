package com.ycnet.frms.mapper.impl.point;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.core.util.KVBean;
import com.ycnet.frms.bean.point.Point;
import com.ycnet.frms.bean.point.PointExample;
import com.ycnet.frms.mapper.point.PointMapper;

@Repository("PointMapper")
public class PointMapperImpl extends  BaseSqlSupport implements PointMapper{
	
		@Override
		public int insert(Point record)
		{
			return this.insert("com.ycnet.frms.mapper.point.PointMapper.insert",record);
		}
	
		@Override
		public Point selectByPrimaryKey(Integer id)
		{
			return this.selectOne("com.ycnet.frms.mapper.point.PointMapper.selectByPrimaryKey",id);
		}
	
		@Override
		public int deleteByPrimaryKey(Integer id)
		{
			return this.delete("com.ycnet.frms.mapper.point.PointMapper.deleteByPrimaryKey",id);
		}
	
		@Override
		public int insertSelective(Point record)
		{
			return this.insert("com.ycnet.frms.mapper.point.PointMapper.insertSelective",record);
		}
	
		@Override
		public int updateByPrimaryKeySelective(Point record)
		{
			return this.update("com.ycnet.frms.mapper.point.PointMapper.updateByPrimaryKeySelective",record);
		}
	
		@Override
		public int updateByPrimaryKey(Point record)
		{
			return this.update("com.ycnet.frms.mapper.point.PointMapper.updateByPrimaryKey",record);
		}

		@Override
		public int countByExample(PointExample example) {
			return this.selectOne("com.ycnet.frms.mapper.point.PointMapper.countByExample",example);
		}

		@Override
		public int deleteByExample(PointExample example) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public List<Point> selectByExample(PointExample example) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int updateByExampleSelective(Point record, PointExample example) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int updateByExample(Point record, PointExample example) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public List<Point> queryByPointName(Point point) {
			return this.selectList("com.ycnet.frms.mapper.point.PointMapper.queryByPointName",point);
		}

		@Override
		public int findPointName(String pointName) {
			return this.selectOne("com.ycnet.frms.mapper.point.PointMapper.findPointName",pointName);

		}

		@Override
		public Point findPointByName(String pointName) {
			return this.selectOne("com.ycnet.frms.mapper.point.PointMapper.findPointByName",pointName);
		}

		@Override
		public List<Point> findPointList() {
			return this.selectList("com.ycnet.frms.mapper.point.PointMapper.findPointList");
		}
/*		@Override
		public List<Point> findNoChangePointList() {
			return this.selectList("com.ycnet.frms.mapper.point.PointMapper.findNoChangePointList");
		}*/

		@Override
		public Point findFirstPoint() {
			return this.selectOne("com.ycnet.frms.mapper.point.PointMapper.findFirstPoint");
		}

		@Override
		public List<Point> findNoChangePointList(Map<String, Integer> map) {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.point.PointMapper.findNoChangePointList",map);
		}
		
	
}



		
		
