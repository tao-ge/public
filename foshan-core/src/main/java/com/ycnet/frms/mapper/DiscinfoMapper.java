package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.Discinfo;
import com.ycnet.frms.bean.DiscinfoZF;
import com.ycnet.frms.vo.mobile.DeviceDiscinfoEntityMobile;

public interface DiscinfoMapper {
    int deleteByPrimaryKey(Long discId);

    int insert(Discinfo record);

    int insertSelective(Discinfo record);
    
    int insertSelectiveZG(Discinfo record);

    Discinfo selectByPrimaryKey(Long discId);

    int updateByPrimaryKeySelective(Discinfo record);
    
    int updateByPrimaryKeySelectiveZG(Discinfo record);

    int updateByPrimaryKey(Discinfo record);
    
    List<Discinfo> selectByGroup(Long groupId);
    
    Discinfo selectByDiscCode(String discCode);

	int deleteDiscInfo(Long groupId);

	//用于删除设备
	List<Discinfo> selectByDevId(Long devId);
	//用于删除设备
	int deleteByDevId(Long devId);

	//导出数据库 刘沧海 2017/10/13
	List<DiscinfoZF> queryList(Long orgId, String areaCode);

	/**
	 * 
	* @Title: deleteByDevCodeMohu 
	* @Description: 根据接头拼接编码删除盘数据  
	* @param @param devCode
	* @param @return    
	* @return int
	* @author liucanghai 
	* @throws
	* @date 2017年12月20日 下午6:20:26 
	* @version V1.0
	 */
	int deleteByDevCodeMohu(String devCode);

	/**
	 * 根据盘编码删除熔纤盘
	* @Title: deleteByDiscCode 
	* @Description: TODO 
	* @param @param replace
	* @param @return    
	* @return int
	* @author liucanghai 
	* @throws
	* @date 2017年12月24日 上午12:18:46 
	* @version V1.0
	 */
	int deleteByDiscCode(String discCode);

	/**
	 * 
	* @Title: queryByDevIdForcode 
	* @Description: 根据设施ID和编码查询 
	* @param @param devId
	* @param @param codeOut
	* @param @return    
	* @return Discinfo
	* @author liucanghai 
	* @throws
	* @date 2017年12月26日 下午4:38:14 
	* @version V1.0
	 * @param codeIn 
	 */
	List<Discinfo> queryByDevIdForcode(Long devId,String codeIn, String codeOut );

	/**
	 * 根据设施Id和分面查询熔纤盘
	* @Title: queryByDevIdAndSide 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @param side
	* @param @return    入参
	* @return List<Discinfo>    返回类型
	* @author 魏俊康（作者） 
	* @throws
	* @date 2017年12月26日 下午6:02:13 
	* @version V1.0
	 */
	List<Discinfo> queryByDevIdAndSide(Long devId, String side);

	/**
	 * 根据盘ID.查询端子占用状态
	* @Title: queryByDiscId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param discId
	* @param @return    入参
	* @return List<DeviceDiscinfoEntityMobile>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月31日 上午11:29:42 
	* @version V1.0
	 */
	List<DeviceDiscinfoEntityMobile> queryByDiscId(Long discId);

}