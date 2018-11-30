package com.ycnet.frms.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.HardwareUpgradEntity;
import com.ycnet.frms.bean.Users;

public interface HardwareUpgradEntityMapper {
    int deleteByPrimaryKey(Long hardId);

    int insert(HardwareUpgradEntity record);

    int insertSelective(HardwareUpgradEntity record);

    HardwareUpgradEntity selectByPrimaryKey(Long hardId);

    int updateByPrimaryKeySelective(HardwareUpgradEntity record);

    int updateByPrimaryKey(HardwareUpgradEntity record);

    /**
     * 
     * @Title: queryRemoteUnlockList
     * @Description: 设备远程更新列表查询
     * @param @param map
     * @param @return 
     * @return List<HardwareUpgradEntity> 入参
     * @return String    返回类型
     * @author DZY 
     * @throws
     * @date 2018年4月15日 下午5:22:21
     * @version V1.0
     */
	List<HardwareUpgradEntity> queryRemoteUnlockList(Map<String, Object> map);

	/**
	 * 
	 * @Title: queryCountqueryRemoteUnlock
	 * @Description: 设备远程更新列表数量查询
	 * @param @param map
	 * @param @return 
	 * @return Integer 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月15日 下午5:22:47
	 * @version V1.0
	 */
	Integer queryCountqueryRemoteUnlock(Map<String, Object> map);

	/**
	 * 
	 * @Title: queryByHardVersion
	 * @Description: 根据硬件版本号查看是否重复
	 * @param @param hardVersion
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月16日 上午10:40:32
	 * @version V1.0
	 */
	int queryByHardVersion(Map<String, Object> map);

	/**
	 * 
	 * @Title: queryMaxVersion
	 * @Description: 查询组织机构下最大版本号
	 * @param @param user
	 * @param @return 
	 * @return String 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月16日 下午1:41:21
	 * @version V1.0
	 */
	String queryMaxVersion(Users user);

	/**
	 * 
	 * @Title: queryByFileName
	 * @Description: 根据文件名称模糊查询硬件版本数量
	 * @param @param filename
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月21日 上午9:53:28
	 * @version V1.0
	 */
	int queryByFileName(String filename);

	/**
	 * @Title: queryByHardVersionAndOrgId
	 * @Description: 根据版本号和orgId查询
	 * @param @param map
	 * @param @return 
	 * @return HardwareUpgradEntity 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月5日 下午3:07:57
	 * @version V1.0
	 */
	HardwareUpgradEntity queryByHardVersionAndOrgId(Map<String, Object> map);

	/**
	 * 查询最大版本号
	* @Title: queryMaxHardVersion 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @param lockCode
	* @param @return    入参
	* @return BigDecimal    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年8月9日 上午10:18:45 
	* @version V1.0
	 */
	BigDecimal queryMaxHardVersion(Long orgId, String codCode);
}