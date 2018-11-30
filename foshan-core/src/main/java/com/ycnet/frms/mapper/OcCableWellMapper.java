package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.OcCableWell;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.mobile.ocii.OcCableSectionInput;
import com.ycnet.frms.vo.mobile.ocii.OcIdentifPortResult;

public interface OcCableWellMapper {
    int deleteByPrimaryKey(Long cableWellId);

    int insert(OcCableWell record);

    int insertSelective(OcCableWell record);

    OcCableWell selectByPrimaryKey(Long cableWellId);

    int updateByPrimaryKeySelective(OcCableWell record);

    int updateByPrimaryKey(OcCableWell record);

	/** 
	 * 根据ID查询物理光缆段信息
	* @Title: queryOcciCableWell 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param sectId
	* @param @return    入参
	* @return List<Map<String,Object>>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月12日 下午3:17:43 
	* @version V1.0   
	*/
	List<Map<String, Object>> queryOcciCableWell(Long sectId);

	/** 
	 * 根据井ID,查询是否有光缆
	* @Title: queryOcCableWellByWellId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wellId
	* @param @return    入参
	* @return List<OcCableWell>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月15日 下午4:49:24 
	* @version V1.0   
	 * @param orgId 
	*/
	List<OcCableWell> queryOcCableWellByWellId(Long wellId, Long orgId);

	/** 
	 * 根据设施ID,查询识别光缆数
	* @Title: queryOcCableWellByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return OcCableWell    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月16日 上午9:59:14 
	* @version V1.0   
	 * @param type 
	*/
	Map<String, Object> queryOcCableWellByDevId(Long devId, String type);

	/**
	 * 根据设施或者井ID查询光缆点信息
	* @Title: selectCableByWellId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param identId
	* @param @param type
	* @param @return    入参
	* @return List<OcCableWell>    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年10月24日 下午1:31:39 
	* @version V1.0
	 */
	List<OcCableWell> selectCableByWellId(Long wellDevId, String type);

	/**
	 * 修改光缆点总数 
	* @Title: updateByDevWellId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wellDevId
	* @param @param type
	* @param @param sectNum
	* @param @return    入参
	* @return int    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年10月24日 下午2:02:52 
	* @version V1.0
	 */
	int updateByDevWellId(Long wellDevId, String type, Integer sectPointNo);

	/**
	 * 根据设施或者井ID查询端口信息 - 接口专用
	* @Title: selectCableByWellIdResult 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wellDevId
	* @param @param type
	* @param @return    入参
	* @return List<OcIdentifPortResult>    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年10月24日 下午2:52:36 
	* @version V1.0
	 */
	List<OcIdentifPortResult> selectCableByWellIdResult(Long wellDevId, String type);

	/** 
	 * 设备-根据设施ID查询光缆段列表
	* @Title: querySectByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param oi
	* @param @param user
	* @param @return    入参
	* @return Map<String,Object>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月25日 下午2:00:36 
	* @version V1.0   
	*/
	List<Map<String, Object>> querySectByDevId(OcCableSectionInput oi, Users user);
}