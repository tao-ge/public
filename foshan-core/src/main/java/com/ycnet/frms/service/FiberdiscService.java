package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.frms.bean.Fiberdisc;
import com.ycnet.frms.bean.FiberdiscZF;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.CableSectionChart;
import com.ycnet.frms.vo.DifferentPortsBean;
import com.ycnet.frms.vo.DiscGroup;
import com.ycnet.frms.vo.FiberdiscBean;
import com.ycnet.frms.vo.FiberdiscBean;
import com.ycnet.frms.vo.FiberdiscConditionBean;
import com.ycnet.frms.vo.FiberdiscInfo;
import com.ycnet.frms.vo.FiberdiscOpdBean;
import com.ycnet.frms.vo.Group;
import com.ycnet.frms.vo.PortJumper;

public interface FiberdiscService {

	int save(Fiberdisc fiberdisc);
	
	Fiberdisc selectById(Long fiberDiscId);
	
	int delete(Long fiberDiscId);
	
	List<Fiberdisc> selectByDevId(Long devId);
	
	
	
	/**
	 * 查询熔纤盘
	 * @param param ：devId 所属设施id；side A/B面;pb 分页 
	 * @return
	 */
	List<Fiberdisc> selectFiberdisc(FiberdiscConditionBean param);
	
	
	List<FiberdiscInfo> queryDiscCodes(FiberdiscConditionBean param);
	
	List<Group> selectGroup(Long devId);
	List<Group> selectGroup1(Long devId);
	
	List<Group> selectGroupWithLines(Long devId);
	
	PortJumper selectJumpers(Long devId,String port01);
	
	Fiberdisc selectByPort(String port01);

	/**
	 * 
	* @Title: fiberdiscGroup 
	* @Description: 根据光缆段ID和设施ID查询熔纤盘详细信息 
	* @param @param sectId
	* @param @param devId
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年5月2日 上午11:53:56 
	* @version V1.0
	 */
	List<DiscGroup> queryDiscGroup(Long sectId, Long devId);

	List<?> queryDiscSection(Long discId);
	
	List<Group> selectSectGroupByDevId(Long devId);

	//导出数据库 刘沧海 2017/10/13
	List<FiberdiscZF> queryList(Long orgId, String areaCode);

	int updateByPrimarykey(long devId);
	/**
	 * 
	* @Title: updateIsOccurByDevid 
	* @Description: 更新fiberdisc中的isoccur 
	* @param @param adevId
	* @param @return    入参
	* @return int    返回类型
	* @author zhouyu 
	* @throws
	* @date 2017年12月1日 下午4:16:35 
	* @version V1.0
	 * @param isOccur 
	 */
	int updateIsOccurByDevid(Long adevId, String isOccur);


	/**
	 * 保存端子信息
	* 
	* @Title: FiberdiscService.java 
	* @Description: TODO 
	* @param @param port01
	* @param @param isSheath
	* @param @param isGlazed1
	* @param @param isGlazed2
	* @param @return
	* @return int
	* @author fl
	* @date 2017年12月1日 下午3:15:40
	* @version V1.0
	 */
	int insertFiberdisc(Fiberdisc fiberdisc);

	/**
	 * 
	 * @Title: genFiberdisc
	 * @Description: 生成直熔盘端子数据-直熔盘专用
	 * @param @param devId
	 * @param @param side
	 * @param @param discNum
	 * @param @param portNum
	 * @param @param object
	 * @param @param startIndex
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月17日 下午5:15:56
	 * @version V1.0
	 * @param user 
	 */
	int genFiberdisc(Users user, Long devId,String side,Integer fiberDiscNum ,Integer portNum,Integer fromRowIndex,Integer fromColIndex,String model);

	/**
	 * 
	 * @Title: genFiberdiscOut
	 * @Description: 生成直熔盘端子数据-直熔盘专用
	 * @param @param user
	 * @param @param devId
	 * @param @param side
	 * @param @param discNum
	 * @param @param portNum
	 * @param @param object
	 * @param @param startIndex
	 * @param @param object2
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月17日 下午6:30:04
	 * @version V1.0
	 */
	int genFiberdiscOut(Users user, Long devId,String side,Integer fiberDiscNum ,Integer portNum,Integer fromRowIndex,Integer fromColIndex,String model);

	/**
	 * 
	 * @Title: deleteByDiscCode
	 * @Description: 根据discCode删除端子数据
	 * @param @param discCode
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月17日 下午7:54:21
	 * @version V1.0
	 */
	int deleteByDiscCode(String discCode);

	/**
	 * 更改端子表中的光路、光衰数据，只保留数字
	* @Title: updateTowDates 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    入参
	* @return int    返回类型
	* @author fl
	* @throws
	* @date 2017年12月26日 下午2:57:22 
	* @version V1.0
	 */
	int updateTowDates();

	/**
	 * 替换端子表中的数据
	* @Title: updateThreeDates 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    入参
	* @return int    返回类型
	* @author 魏俊康（作者） 
	* @throws
	* @date 2017年12月26日 下午5:25:01 
	* @version V1.0
	 * @param newCode 
	 * @param side 
	 * @param devId 
	 */
	int updateThreeDates(Long devId, String side, String newCode);

	/**
	 *  只删除直熔的纤芯
	* 
	* @Title: FiberdiscService.java 
	* @Description: TODO 
	* @param @param discCode
	* @param @param users
	* @param @return
	* @return int
	* @author fl
	* @date 2017年12月29日 上午10:04:37
	* @version V1.0
	 */
	int deleteZRFiberdiscs(String discCode, Users user);

	/**
	 * 查询端子数据
	* 
	* @Title: FiberdiscService.java 
	* @Description: TODO 
	* @param @param port
	* @param @return
	* @return FiberdiscConditionBean
	* @author fl
	* @date 2018年1月11日 下午2:16:34
	* @version V1.0
	 */
	FiberdiscBean queryfiberdiscByPort(String port);

	/**
	 * 
	 * @Title: queryByDiscCode
	 * @Description: 根据discCode查询端子数据
	 * @param @param discCode
	 * @param @return 
	 * @return List<Fiberdisc> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月23日 下午2:57:53
	 * @version V1.0
	 */
	List<Fiberdisc> queryByDiscCode(String discCode);

	/**
	 * 
	* @Title: cableSectionChart 
	* @Description: 光缆段成端详情 
	* @param @param model
	* @param @param devId
	* @param @param devIdZ
	* @param @param sectId
	* @param @return    
	* @return String
	* @author liucanghai 
	* @throws
	* @date 2018年4月18日 下午3:20:21 
	* @version V1.0
	 * @param sectId 
	 * @param state 
	 * @param long1 
	 */
	CableSectionChart cableSectionChart(Long  devId, Long sectId, Integer state);

	/**
	 * 查询端子情况
	* @Title: queryDiscGroupDiscPorts 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return CableSectionChart    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月19日 下午2:43:21 
	* @version V1.0
	 */
	List<Group> queryDiscGroupDiscPorts(Long devId);

	/**
	 * 
	* @Title: queryFiberdiscGroupWithSection 
	* @Description: 根据光缆段ID和设施ID查询熔纤盘详细信息  
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年5月3日 下午4:53:07 
	* @version V1.0
	 */
	List<DiscGroup> queryFiberdiscGroupWithSection(Long sectId, Long devId);

	/**
	 * 
	 * @Title: queryFiberdiscOccupCount
	 * @Description: 根据devId查询设施下端子占用情况
	 * @param @param devId
	 * @param @return 
	 * @return FiberdiscBean 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年5月10日 下午1:47:12
	 * @version V1.0
	 */
	FiberdiscBean queryFiberdiscOccupCount(Long devId);

	/**
	 * 端子差异情况
	* @Title: queryPortOccupyCodition 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return List<Group>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月25日 下午4:19:38 
	* @version V1.0
	 */
	List<Group> queryPortOccupyCodition(Long devId);

	/**
	 * 查询端子详情
	* @Title: queryfiberdisc 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param discId
	* @param @param port01
	* @param @return    入参
	* @return FiberdiscOpdBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月22日 下午3:10:53 
	* @version V1.0
	 */
	FiberdiscOpdBean queryfiberdisc(Long fiberDiscId, String port01);

	/**
	 * 
	 * @Title: queryOccupyConditionByOrgId
	 * @Description: 根据orgId,查询端子占用
	 * @param @param orgId
	 * @param @return 
	 * @return List<DifferentPortsBean> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月26日 上午10:42:42
	 * @version V1.0
	 */
	List<DifferentPortsBean> queryOccupyConditionByOrgId(Long orgId);

	/**
	 * 
	 * @Title: queryOccupyConditionByArea
	 * @Description: 查询端子占用
	 * @param @param orgId
	 * @param @return 
	 * @return List<DifferentPortsBean> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月26日 上午11:56:51
	 * @version V1.0
	 */
	List<DifferentPortsBean> queryOccupyConditionByArea(Long orgId);
	
}
