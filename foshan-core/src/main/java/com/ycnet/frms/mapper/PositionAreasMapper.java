package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.PositionAreas;
import com.ycnet.frms.vo.CityBean;
import com.ycnet.frms.vo.CountyBean;
import com.ycnet.frms.vo.ProvinceBean;
import com.ycnet.frms.vo.TownBean;

public interface PositionAreasMapper {
    int deleteByPrimaryKey(String areaCode);

    int insert(PositionAreas record);

    int insertSelective(PositionAreas record);

    PositionAreas selectByPrimaryKey(String areaCode);

    int updateByPrimaryKeySelective(PositionAreas record);

    int updateByPrimaryKey(PositionAreas record);
    
    /**
     * 
     * @Title: queryProvinceById
     * @Description: 根据ID查询省
     * @param @param provinceId
     * @param @return 
     * @return List<ProvinceBean> 入参
     * @return String    返回类型
     * @author DZY 
     * @throws
     * @date 2018年6月15日 下午3:11:47
     * @version V1.0
     */
	List<ProvinceBean> queryProvinceById(Long provinceId);

	/**
	 * 
	 * @Title: queryCityById
	 * @Description: 根据ID查询市
	 * @param @param cityId
	 * @param @return 
	 * @return List<CityBean> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月15日 下午3:28:55
	 * @version V1.0
	 */
	List<CityBean> queryCityById(String provinceId);

	/**
	 * 
	 * @Title: queryAllProvince
	 * @Description: 查询所有省
	 * @param @return 
	 * @return List<ProvinceBean> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月15日 下午3:38:37
	 * @version V1.0
	 */
	List<ProvinceBean> queryAllProvince();

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
	List<CountyBean> queryByCityId(String cityId);

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
	List<TownBean> queryTownById(String countyId);
}