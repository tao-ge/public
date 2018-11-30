package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.Cable;
import com.ycnet.frms.bean.CableZF;
import com.ycnet.frms.vo.CableConditionBean;
import com.ycnet.frms.vo.CableSectionBean;
import com.ycnet.frms.vo.CableStat;
import com.ycnet.frms.vo.CablesBean;

public interface CableMapper {
    int deleteByPrimaryKey(Long cableId);

    int insert(Cable record);

    int insertSelective(Cable record);

    Cable selectByPrimaryKey(Long cableId);

    int updateByPrimaryKeySelective(Cable record);

    int updateByPrimaryKey(Cable record);
    
    List<Cable> selectByParam(CableConditionBean searchBean);
    
    List<Cable> queryByConditionMap(Map<String,Object> map);
    
    int queryCountByConditionMap(Map<String,Object> map);

	Cable queryCableEnd(Long sectId);

	Cable queryCablesInfo(Long sectId);

	//导出纤芯占用详细(光交箱)
	List<Cable> queryPortInfo(Long orgId, String areaCode1);
	//机柜端口占用详细
	List<Cable> queryPortInfoCablin(Long orgId, String areaCode1);

	List<CableStat> queryPortStat(Long orgId, String areaCode1);

	CableStat queryPortStat1(Long orgId, Long sectId);


	List<Cable> queryCableInfo(Long orgId, String areaCode1);

	List<Cable> queryCableDevId(Long orgId, Long devId);

	List<Cable> queryCableInfoCablin(Long orgId, String areaCode1);
	
	Cable queryCablesInfos(Long sectId);

	//导出数据库 刘沧海 2017/10/13
	List<CableZF> queryList(Long orgId);
	
	/**
	 * 根据编码查询光缆
	* @Title: selectByCableCode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param code
	* @param @return    入参
	* @return Cable    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2017年12月4日 下午3:07:24 
	* @version V1.0
	 */
	Cable selectByCableCode(String code);

	/**
	 * web每第一次进入,只查未成端光缆 数据
	* 
	* @Title: CableMapper.java 
	* @Description: TODO 
	* @param @param conditionMap
	* @param @return
	* @return Cable
	* @author fl
	* @date 2018年1月16日 下午2:13:53
	* @version V1.0
	 */
	List<Cable> queryCablesByBean(Map<String, Object> map);

	/**
	 * web每第一次进入,只查未成端光缆 总数
	* 
	* @Title: CableMapper.java 
	* @Description: TODO 
	* @param @param conditionMap
	* @param @return
	* @return Integer
	* @author fl
	* @date 2018年1月16日 下午2:33:41
	* @version V1.0
	 */
	Integer queryCountCablesByBean(Map<String, Object> map);

	/**
	 * 修改web页面光缆列表查询,优化sql,查询数据
	* 
	* @Title: CableMapper.java 
	* @Description: TODO 
	* @param @param conditionMap
	* @param @return
	* @return Cable
	* @author fl
	* @date 2018年1月16日 下午3:57:36
	* @version V1.0
	 */
	List<Cable> queryByConditionsMap(Map<String, Object> map);

	/**
	 * 修改web页面光缆列表查询,优化sql 查询总条数
	* 
	* @Title: CableMapper.java 
	* @Description: TODO 
	* @param @param conditionMap
	* @param @return
	* @return Integer
	* @author fl
	* @date 2018年1月16日 下午4:03:01
	* @version V1.0
	 */
	Integer queryCountByConditionsMap(Map<String, Object> map);

	/**
	 * 光缆状态为3的查询总条数
	* 
	* @Title: CableMapper.java 
	* @Description: TODO 
	* @param @param conditionMap
	* @param @return
	* @return Integer
	* @author fl
	* @date 2018年1月22日 下午4:29:29
	* @version V1.0
	 */
	Integer queryCountByConditionsMapFour(Map<String, Object> map);

	/**
	 * 光缆状态为3的查询总数据
	* 
	* @Title: CableMapper.java 
	* @Description: TODO 
	* @param @param conditionMap
	* @param @return
	* @return Cable
	* @author fl
	* @date 2018年1月22日 下午4:29:49
	* @version V1.0
	 */
	List<Cable> queryByConditionsMapFour(Map<String, Object> map);

	/**
	 * 光缆状态为0总条数
	* 
	* @Title: CableMapper.java 
	* @Description: TODO 
	* @param @param conditionMap
	* @param @return
	* @return Integer
	* @author fl
	* @date 2018年1月22日 下午5:33:56
	* @version V1.0
	 */
	Integer queryCountByConditionsMapZero(Map<String, Object> conditionMap);

	/**
	 * 光缆状态为0总数据
	* 
	* @Title: CableMapper.java 
	* @Description: TODO 
	* @param @param conditionMap
	* @param @return
	* @return Cable
	* @author fl
	* @date 2018年1月22日 下午5:34:11
	* @version V1.0
	 */
	List<Cable> queryByConditionsMapZero(Map<String, Object> conditionMap);
	/**
	 * 
	* @Title: queryErrorRecCableListByCablesBean 
	* @Description: 查询纠错光缆段 
	* @param @param conditionMap
	* @param @return    入参
	* @return List<Cable>    返回类型
	* @author zhouyu 
	* @throws
	* @date 2018年1月23日 下午2:39:32 
	* @version V1.0
	 */
	List<CablesBean> queryErrorRecCableListByCablesBean(Map<String, Object> conditionMap);
	/**
	 * 
	* @Title: queryErrorRecCableListByCablesBean 
	* @Description: 查询纠错光缆段数目
	* @param @param conditionMap
	* @param @return    入参
	* @return List<Cable>    返回类型
	* @author zhouyu 
	* @throws
	* @date 2018年1月23日 下午2:39:32 
	* @version V1.0
	 */
	Integer queryErrorRecCableListCountByCablesBean(Map<String, Object> conditionMap);

	
}