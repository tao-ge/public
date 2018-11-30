package com.ycnet.frms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.frms.mapper.PositionAreasMapper;
import com.ycnet.frms.service.PositionAreasService;
import com.ycnet.frms.vo.CityBean;
import com.ycnet.frms.vo.CountyBean;
import com.ycnet.frms.vo.ProvinceBean;
import com.ycnet.frms.vo.TownBean;

@Service("positionAreasService")
public class PositionServiceImpl implements PositionAreasService{
	
	@Resource(name="positionAreasMapper")
	private PositionAreasMapper positionAreasMapper;

	/**
	 * 
	 * @Title: queryProvinceById
	 * @Description: 根据ID查询省
	 * @param @param provinceId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月15日 下午3:11:13
	 * @version V1.0
	 */
	@Override
	public List<ProvinceBean> queryProvinceById(Long provinceId) {
		return positionAreasMapper.queryProvinceById(provinceId);
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
	 * @date 2018年6月15日 下午3:28:34
	 * @version V1.0
	 */
	@Override
	public List<CityBean> queryCityById(String provinceId) {
		return positionAreasMapper.queryCityById(provinceId);
	}

	/**
	 * 
	 * @Title: queryAllProvince
	 * @Description: 查询所有省
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月15日 下午3:38:08
	 * @version V1.0
	 */
	@Override
	public List<ProvinceBean> queryAllProvince() {
		return positionAreasMapper.queryAllProvince();
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
	* @date 2018年6月15日 下午3:09:58 
	* @version V1.0
	 */
	@Override
	public List<CountyBean> queryByCityId(String cityId) {
		return positionAreasMapper.queryByCityId(cityId);
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
	* @date 2018年6月15日 下午3:21:04 
	* @version V1.0
	 */
	@Override
	public List<TownBean> queryTownById(String countyId) {
		return positionAreasMapper.queryTownById(countyId);
	}
	
}
