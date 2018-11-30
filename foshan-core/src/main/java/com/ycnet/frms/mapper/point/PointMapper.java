package com.ycnet.frms.mapper.point;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ycnet.frms.bean.point.Point;
import com.ycnet.frms.bean.point.PointExample;

/**
 * 点位接口
 * @author yxt
 * @since 2017年4月19日16:01:16
 *
 */
public interface PointMapper {
    int countByExample(PointExample example);

    int deleteByExample(PointExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Point record);

    int insertSelective(Point record);

    List<Point> selectByExample(PointExample example);

    Point selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Point record, @Param("example") PointExample example);

    int updateByExample(@Param("record") Point record, @Param("example") PointExample example);

    int updateByPrimaryKeySelective(Point record);

    int updateByPrimaryKey(Point record);
    
	/**
	 * 查询点位是否已经存在
	 * @param pointName
	 * @return
	 */
	List<Point> queryByPointName(Point point);
	/**
	 * 查询点位是否已经存在
	 * @param pointName
	 * @return
	 */
	int findPointName(String pointName);

	/**
	 * 通过点位名称查询 点位信息
	 * @param pointName
	 * @return
	 */
	Point findPointByName(String pointName);
	
	/**
	 * 查询所有转化过的点位信息
	 * @return
	 */
	List<Point> findPointList();
	
	/**
	 * 查询所有没有转化过的点位信息
	 * @param pageSize 
	 * @param startNum 
	 * @return
	 */
	List<Point> findNoChangePointList(Map<String, Integer> map);

	/**
	 * 查询数据库第一个点位作为固定点
	 * @return
	 */
	Point findFirstPoint();


}