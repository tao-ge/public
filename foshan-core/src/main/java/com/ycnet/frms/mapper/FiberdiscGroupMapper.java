package com.ycnet.frms.mapper;

import java.util.HashMap;
import java.util.List;

import com.ycnet.frms.bean.FiberdiscGroup;
import com.ycnet.frms.bean.FiberdiscGroupZF;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.FiberdiscGroupBean;
import com.ycnet.frms.vo.GroupConditionBean;

public interface FiberdiscGroupMapper {
    int deleteByPrimaryKey(Long groupId);

    int insert(FiberdiscGroup record);

    int insertSelective(FiberdiscGroup record);

    FiberdiscGroup selectByPrimaryKey(Long groupId);

    int updateByPrimaryKeySelective(FiberdiscGroup record);
    
    int updateByPrimaryKeySelectiveZG(FiberdiscGroup record);

    int updateByPrimaryKey(FiberdiscGroup record);
    
    List<FiberdiscGroup> queryGroupByDev(GroupConditionBean bean);
    
    List<FiberdiscGroupBean> selectDiscinfoByGroup(Long devId,Long groupId);
    
    FiberdiscGroupBean selectDiscinfoByGroup(Long groupId);

	int updateDiscGroup(Long groupId, String side);

	int updateDiscGroupName(Long groupId, String groupName);

	//用于删除设备
	List<FiberdiscGroup> selectByDevId(Long devId);
	//用于删除设备
	int deleteByDevId(Long devId);

	//设施分组信息  --刘沧海--2017-9-26
	List<FiberdiscGroup> queryByDevId(Long devId);

	//导出数据库 刘沧海 2017/10/13
	List<FiberdiscGroupZF> queryList(Long orgId, String areaCode);
	
	/**
	 * 资管导入（专用）
	 * @author: YHT
	 * @date: 2017年10月24日 上午10:27:35 
	 * @Title: insertSelective  
	 * @param @param record
	 * @param @return     
	 * @return int   
	 * @throws
	 */
	int insertSelectiveZG(FiberdiscGroup record);
	/**
	 * 
	* @Title: saveGroupDescByGroupId 
	* @Description: 根据熔纤盘groupId修改熔纤盘描述 
	* @param @param groupId
	* @param @param groupDesc
	* @param @param groupName
	* @param @return    入参
	* @return int    返回类型
	* @author zhouyu 
	* @throws
	* @date 2017年12月6日 下午4:12:15 
	* @version V1.0
	 * @param user 
	 */
	int saveGroupDescByGroupId(Long groupId, String groupDesc, String groupName, Users user);

	/**
	 * 新建的熔纤盘列表展示,以及每个盘的纤芯
	* 
	* @Title: FiberdiscGroupMapper.java 
	* @Description: TODO 
	* @param @param fiberdiscGroup
	* @param @return
	* @return List<FiberdiscGroup>
	* @author fl
	* @date 2017年12月17日 下午2:53:22
	* @version V1.0
	 */
	List<FiberdiscGroup> queryisInvest(Long devId);

	/**
	 * 
	 * @Title: selectBySide
	 * @Description: 根据分组编码查询最大分组编码[直熔盘专用]
	 * @param @param string
	 * @param @return 
	 * @return String 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月17日 下午9:58:06
	 * @version V1.0
	 * @param devId 
	 */
	String selectByMaxSide(String side, Long devId);

	/**
	 * 
	 * @Title: selectByDiscId
	 * @Description: 根据盘ID查询分组
	 * @param @param discId
	 * @param @return 
	 * @return FiberdiscGroup 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月26日 下午5:15:20
	 * @version V1.0
	 */
	FiberdiscGroup selectByDiscId(Long discId);

	/**
	 * 
	* @Title: querySideByZO 
	* @Description: 根据设施编号和编码查询 
	* @param @param devId
	* @param @param side
	* @param @param sideout
	* @param @return    
	* @return List<FiberdiscGroup>
	* @author liucanghai 
	* @throws
	* @date 2017年12月26日 下午7:40:10 
	* @version V1.0
	 */
	List<FiberdiscGroup> querySideByZO(Long devId, String side, String sideout);

	/**
	 * 
	 * @Title: queryByDevIdAndSide
	 * @Description: 根据devId和side查询分组数据
	 * @param @param devId
	 * @param @param side
	 * @param @return 
	 * @return List<FiberdiscGroup> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月23日 下午2:25:19
	 * @version V1.0
	 */
	List<FiberdiscGroup> queryByDevIdAndSide(Long devId, String side);

	/**
	 * 
	* @Title: queryForDevIdGroup 
	* @Description: 查询设施分组 
	* @param @param devId
	* @param @return    
	* @return List<FiberdiscGroupBean>
	* @author liucanghai 
	* @throws
	* @date 2018年2月1日 下午1:36:39 
	* @version V1.0
	 */
	List<FiberdiscGroupZF> queryForDevIdGroup(Long devId);

	/**
	 * 
	 * @Title: queryGroupByPort01
	 * @Description: 根据端子编码查询所属分组
	 * @param @param code
	 * @param @return 
	 * @return FiberdiscGroup 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月25日 上午11:22:04
	 * @version V1.0
	 */
	FiberdiscGroup queryGroupByPort01(String port01);

}