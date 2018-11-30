package com.ycnet.frms.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.CableSectionDec;
import com.ycnet.frms.bean.Lines;
import com.ycnet.frms.vo.CableSectionConditionBean;
import com.ycnet.frms.vo.CableSectionDecBean;

public interface CableSectionDecMapper {
	int deleteByPrimaryKey(Long sectDecId);

	int insert(CableSectionDec record);

	int insertSelective(CableSectionDec record);

	CableSectionDec selectByPrimaryKey(Long sectDecId);

	int updateByPrimaryKeySelective(CableSectionDec record);

	int updateByPrimaryKey(CableSectionDec record);



	/**
	 * 
	* @Title: queryZgSectionDev 
	* @Description: TODO(根据端口编码查询资管光缆段) 
	* @param @param port01
	* @param @return    入参
	* @return CableSectionDec    返回类型
	* @author liucanghai（作者） 
	* @throws
	* @date 2017年11月27日 下午3:29:58 
	* @version V1.0
	 */
	CableSectionDec queryZgSectionDev(String port01);
   
   



   
   
	/**
	 * 查询所有数据
	* 
	* @Title: CableSectionDecMapper.java 
	* @Description: TODO 
	* @param @param map
	* @param @return
	* @return List<?>
	* @author fl
	* @date 2017年11月27日 下午3:53:57
	* @version V1.0
	 */

	

	List<CableSectionDec> queryCabledetailsList(Map<String, Object> map);

	/**
	 * 查询总条数
	* 
	* @Title: CableSectionDecMapper.java 
	* @Description: TODO 
	* @param @param map
	* @param @return
	* @return Integer
	* @author fl
	* @date 2017年11月27日 下午4:59:02
	* @version V1.0
	 */
	Integer selectCountAll(Map<String, Object> map);

	/**
	 * 查询成端对应端状态
	* 
	* @Title: CableSectionDecMapper.java 
	* @Description: TODO 
	* @param @param map
	* @param @return
	* @return String
	* @author fl
	* @date 2017年11月28日 下午12:43:50
	* @version V1.0
	 */
	CableSectionDec queryIsOccup(HashMap<String, Long> map);

	/**
	 * 
	 * @Title: querySectionDecBySectIdAndFiberNo
	 * @Description: 根据SectId和FiberNo查询一条
	 * @param @param sectId
	 * @param @param long1
	 * @param @return 
	 * @return CableSectionDec 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年11月28日 下午5:36:56
	 * @version V1.0
	 */
	CableSectionDec querySectionDecBySectIdAndFiberNo(Long sectId, Long long1);

	/**
	 * 
	 * @Title: queryBySectIdAndFiberNo
	 * @Description: 根据SectId和FiberNo查询一条
	 * @param @param map
	 * @param @return 
	 * @return Lines 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年11月28日 下午5:42:59
	 * @version V1.0
	 */
	Lines queryBySectIdAndFiberNo(Map<String, Object> map);

	/**
	 * 
	* @Title: queryZgSectionDevZG 
	* @Description: TODO(根据端子查询资管详情) 
	* @param @param code
	* @param @return    入参
	* @return CableSectionDec    返回类型
	* @author liucanghai（作者） 
	* @throws
	* @date 2017年11月28日 上午9:20:42 
	* @version V1.0
	 */
	CableSectionDecBean queryZgSectionDevZG(String code);

	/**
	 * 
	 * 
	 * @Title: modifyBySectIdAndState
	 * @Description: 修改纤芯状态
	 * @param @param sectId
	 * @param @param string
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月4日 下午3:49:01
	 * @version V1.0
	 * @param fiberState1 
	 */
	int modifyBySectIdAndState(Long sectId, String fiberState, String fiberState1);
	
	
	CableSectionDec queryBySectNameAndFiberNo(String sectName,Long fiberNo);

	/**
	 * 根据光缆ID和光缆芯数查询大于光缆芯数的所有光缆描述
	* @Title: selectBySectIdAndGreatFiberNum 
	* @Description: TODO 
	* @param @param sectId
	* @param @param fiberNum
	* @param @return    
	* @return List<CableSectionDec>
	* @author liucanghai 
	* @throws
	* @date 2017年12月13日 上午9:23:19 
	* @version V1.0
	 */
	List<CableSectionDec> selectBySectIdAndGreatFiberNum(Long sectId, Long oldFiberNum,Long newFiberNum);
	
	/**
	 * 根据光缆ID和纤芯删除光缆描述
	* @Title: deleteByPrimaryKey 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param sectDecId
	* @param @param fiberNo
	* @param @return    入参
	* @return int    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2017年12月13日 下午4:34:05 
	* @version V1.0
	 */
	int deleteBySectIdAndFiberNo(Long sectId,Long fiberNo);

	/**
	 * 根据光缆ID查询光缆详情
	* @Title: selectBySectId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param sectId
	* @param @return    入参
	* @return List<CableSectionDec>    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2017年12月14日 下午4:03:19 
	* @version V1.0
	 */
	List<CableSectionDec> selectBySectId(Long sectId);

}