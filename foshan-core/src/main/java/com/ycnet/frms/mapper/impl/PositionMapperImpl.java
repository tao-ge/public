package com.ycnet.frms.mapper.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.Position;
import com.ycnet.frms.mapper.PositionMapper;
import com.ycnet.frms.vo.CityBean;
import com.ycnet.frms.vo.CountyBean;
import com.ycnet.frms.vo.ProvinceBean;
import com.ycnet.frms.vo.TownBean;

@Repository("positionMapper")
public class PositionMapperImpl extends BaseSqlSupport implements PositionMapper{

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.delete("com.ycnet.frms.mapper.PositionMapper.deleteByPrimaryKey", id);
	}

	@Override
	public int insert(Position record) {
		return this.insert("com.ycnet.frms.mapper.PositionMapper.insert",record);
	}

	@Override
	public int insertSelective(Position record) {
		return this.insert("com.ycnet.frms.mapper.PositionMapper.insertSelective",record);
	}

	@Override
	public Position selectByPrimaryKey(Integer id) {
		return this.selectOne("com.ycnet.frms.mapper.PositionMapper.selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(Position record) {
		return this.update("com.ycnet.frms.mapper.PositionMapper.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(Position record) {
		return this.update("com.ycnet.frms.mapper.PositionMapper.updateByPrimaryKey", record);
	}

	/**
	 * 
	 * @Title: queryProvinceById
	 * @Description: 根据ID查询省
	 * @param @param provinceId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月15日 下午3:12:12
	 * @version V1.0
	 */
	@Override
	public List<ProvinceBean> queryProvinceById(Long provinceId) {
		return this.selectList("com.ycnet.frms.mapper.PositionMapper.queryProvinceById", provinceId);
	}

	/**
	 * 
	 * @Title: queryCityById
	 * @Description: 根据ID查询市
	 * @param @param cityId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月15日 下午3:29:13
	 * @version V1.0
	 */
	@Override
	public List<CityBean> queryCityById(Long provinceId) {
		return this.selectList("com.ycnet.frms.mapper.PositionMapper.queryCityById", provinceId);
	}

	/**
	 * 
	 * @Title: queryAllProvince
	 * @Description: 查询所有省
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月15日 下午3:38:50
	 * @version V1.0
	 */
	@Override
	public List<ProvinceBean> queryAllProvince() {
		return this.selectList("com.ycnet.frms.mapper.PositionMapper.queryAllProvince");
	}

	/**
     * 根据市ID,查询区域
    * @Title: queryByCityId 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param cityId
    * @param @return    入参
    * @return List<CountyBean>    返回类型
    * @author FL（作者） 
    * @throws
    * @date 2018年6月15日 下午3:12:14 
    * @version V1.0
     */
	@Override
	public List<CountyBean> queryByCityId(Long cityId) {
		return this.selectList("com.ycnet.frms.mapper.PositionMapper.queryByCityId", cityId);
	}

	/**
	 * 根据区ID,查询镇
	* @Title: queryTownById 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param countyId
	* @param @return    入参
	* @return List<TownBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月15日 下午3:22:20 
	* @version V1.0
	 */
	@Override
	public List<TownBean> queryTownById(Long countyId) {
		return this.selectList("com.ycnet.frms.mapper.PositionMapper.queryTownById", countyId);
	}
}
  
    