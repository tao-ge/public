package com.ycnet.frms.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.CableSection;
import com.ycnet.frms.bean.Discinfo;
import com.ycnet.frms.bean.Fiberdisc;
import com.ycnet.frms.bean.FiberdiscZF;
import com.ycnet.frms.vo.CableSectionChart;
import com.ycnet.frms.vo.DifferentPortsBean;
import com.ycnet.frms.vo.Disc;
import com.ycnet.frms.vo.DiscGroup;
import com.ycnet.frms.vo.DiscWX;
import com.ycnet.frms.vo.FiberdiscBean;
import com.ycnet.frms.vo.FiberdiscConditionBean;
import com.ycnet.frms.vo.FiberdiscInfo;
import com.ycnet.frms.vo.Group;
import com.ycnet.frms.vo.Group1;
import com.ycnet.frms.vo.mobile.DiscinfoMobile;

public interface FiberdiscMapper {

	int deleteByPrimaryKey(Long fiberDiscId);

    int insert(Fiberdisc record);

    int insertSelective(Fiberdisc record);
    
    int insertSelectiveZG(Fiberdisc record);

    Fiberdisc selectByPrimaryKey(Long fiberDiscId);

    int updateByPrimaryKeySelective(Fiberdisc record);

    int updateByPrimaryKey(Fiberdisc record);
    
    List<Fiberdisc> selectByDevId(Long devId);
    
    /**
     * 通过设施id查询熔纤�?
     * @param param
     * @return
     */
    List<Fiberdisc> queryBydev(FiberdiscConditionBean param);
    /**
     * 获取设施熔纤�?插板编码
     * @param devId 设施ID
     * @return
     */
    List<FiberdiscInfo> queryDiscCodes(FiberdiscConditionBean param);
    
    /**
     * 熔纤盘解成端
     * @param devId
     * @param sectId
     * @param fromFiberNum
     * @param endFiberNum
     * @return
     */
    int unlocate(Long devId,Long sectId,Integer fromFiberNum,Integer endFiberNum,Long userId);
    
    int unlocate(Long devId,Long sectId,Long userId) ;
    /**
     * 按纤芯序号查询已成端的熔纤盘信息
     * @param devId
     * @param sectId
     * @param fromFiberNum
     * @param endFiberNum
     * @return
     */
    List<Fiberdisc> queryByFiberNum(Long devId,long sectId,Integer fromFiberNum,Integer endFiberNum);
    
    List<Group> selectGroup(Long devId);
    
    List<Group> selectGroup1(Long devId);
    
    Fiberdisc selectFiberdiscByCode(Long devId,String port01);
    
    /**
     * 盘占用情况  -- 添加尾纤悬空状态
     * @author: YHT
     * @date: 2017年9月26日 上午11:12:16 
     * @Title: selectFiberdiscWX  
     * @param @param devId
     * @param @param side
     * @param @param discId
     * @param @return     
     * @return DiscWX   
     * @throws
     */
    DiscWX selectFiberdiscWX(Long devId,String side,Long discId);
    
    Disc selectFiberdisc(Long devId,String side,Long discId);
    
    Fiberdisc selectByPort(String port01);
    
    
    List<Fiberdisc> checkOccupyFiberdisc(Long devId,String side,String direction);

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

	int updateBySectId(Long sectId);


	int deleteFiberDisc(Map<String, Object> map);

	List<Fiberdisc> queryFiberDisc(Map<String, Object> map);

	List<?> queryDiscSection(Long discId); 
	
	Disc selectFiberdiscBysectId(Long devId,String side,Long discId, Long sectId);

	//用于删除设备
	int deleteByDevId(Long devId);

	//导出数据库 刘沧海 2017/10/13
	List<FiberdiscZF> queryList(Long orgId, String areaCode);

	/**
	 * 资管导入专用
	 * @author: YHT
	 * @date: 2017年10月24日 上午11:24:24 
	 * @Title: queryBydevZG  
	 * @param @param param
	 * @param @return     
	 * @return List<Fiberdisc>   
	 * @throws
	 */
	List<Fiberdisc> queryBydevZG(FiberdiscConditionBean param);

	/**
	 * 根据编码查询AZ编码查询占用状态
	* 
	* @Title: FiberdiscMapper.java 
	* @Description: TODO 
	* @param @param acode
	* @param @return
	* @return Fiberdisc
	* @author fl
	* @date 2017年11月30日 下午8:55:14
	* @version V1.0
	 */
	Fiberdisc queryOccupy(String code);
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
	 */
	int updateIsOccurByDevid(Long adevId,String isOccur);

	/**
	 * 
	 * @Title: queryByPort
	 * @Description: 根据code查端子
	 * @param @param code
	 * @param @return 
	 * @return Fiberdisc 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月1日 下午2:05:53
	 * @version V1.0
	 */
	Fiberdisc queryByPort(String code);


	/**
	 * 根据端子编码查询主键
	* 
	* @Title: FiberdiscMapper.java 
	* @Description: TODO 
	* @param @param port01
	* @param @return
	* @return Fiberdisc
	* @author fl
	* @date 2017年12月1日 下午3:31:06
	* @version V1.0
	 */
	Fiberdisc queryId(String port01);

	/**
	 * 
	* @Title: updateByIsOccup 
	* @Description: 根据端子编码修改状态 
	* @param @param fd
	* @param @return    
	* @return int
	* @author liucanghai 
	* @throws
	* @date 2017年12月2日 上午11:41:33 
	* @version V1.0
	 */
	int updateByIsOccup(Fiberdisc fd);

	/**
	 * 根据熔纤盘编码,和设施ID,查询端子状态
	* 
	* @Title: FiberdiscMapper.java 
	* @Description: TODO 
	* @param @param map
	* @param @return
	* @return List<Fiberdisc>
	* @author fl
	* @date 2017年12月17日 下午5:06:37
	* @version V1.0
	 */
	List<Fiberdisc> queryFiberdiscByCodeId(String discCode,Long devId);

	/**
	 * 
	* @Title: queryFiberdiscForCode 
	* @Description: 根据盘编码和序号查询 
	* @param @param discCode
	* @param @param i
	* @param @return    
	* @return Fiberdisc
	* @author liucanghai 
	* @throws
	* @date 2017年12月17日 下午3:28:37 
	* @version V1.0
	 */
	Fiberdisc queryFiberdiscForCode(String discCode, long i);

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
	 * @date 2017年12月17日 下午7:55:54
	 * @version V1.0
	 */
	int deleteByDiscCode(String discCode);

	/**
	 * 
	* @Title: deleteByDevCodeMohu 
	* @Description: 根据接头拼接编码删除端子数据 
	* @param @param devCode
	* @param @return    
	* @return int
	* @author liucanghai 
	* @throws
	* @date 2017年12月20日 下午6:16:26 
	* @version V1.0
	 */
	int deleteByDevCodeMohu(String devCode);

	/**
	 * 查询表中的光路、光衰数据
	* @Title: queryByTowDates 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    入参
	* @return List<Fiberdisc>    返回类型
	* @author fl
	* @throws
	* @date 2017年12月26日 下午2:59:45 
	* @version V1.0
	 */
	List<Fiberdisc> queryByTowDates();
	
    /**
     * 刷新熔纤盘（新）
    * @Title: selectFiberdiscByDiscCode 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param discCode
    * @param @return    入参
    * @return DiscWX    返回类型
    * @author YHT（作者） 
    * @throws
    * @date 2017年12月24日 下午1:55:34 
    * @version V1.0
     */
    DiscWX selectFiberdiscByDiscCode(String discCode);

	/**
	 * 查询端子表数据
	* @Title: queryByThings 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @param side
	* @param @return    入参
	* @return List<Fiberdisc>    返回类型
	* @author 魏俊康（作者） 
	* @throws
	* @date 2017年12月26日 下午5:28:13 
	* @version V1.0
	 */
	List<Fiberdisc> queryByThings(Long devId, String side);

	/**
	 * 清空端子数据
	* 
	* @Title: FiberdiscMapper.java 
	* @Description: TODO 
	* @param @param discCode
	* @param @return
	* @return int
	* @author fl
	* @date 2017年12月29日 上午10:45:37
	* @version V1.0
	 */
	int updateByDiscCode(String discCode);

	/**
	 * 查询端子数据
	* 
	* @Title: FiberdiscMapper.java 
	* @Description: TODO 
	* @param @param port
	* @param @return
	* @return FiberdiscConditionBean
	* @author fl
	* @date 2018年1月11日 下午2:18:05
	* @version V1.0
	 */
	FiberdiscBean queryfiberdiscByPort(String port);

	/**
	 * 根据设施Id查盘分组
	* 
	* @Title: FiberdiscMapper.java 
	* @Description: TODO 
	* @param @param devId
	* @param @return
	* @return List<Group>
	* @author fl
	* @date 2018年1月16日 上午9:17:53
	* @version V1.0
	 */
	List<Group> selectGroupXG(Long devId);

	/**
	 * 刷新熔纤盘
	* 
	* @Title: FiberdiscMapper.java 
	* @Description: TODO 
	* @param @param discCode
	* @param @return
	* @return Disc
	* @author fl
	* @date 2018年1月16日 上午9:37:29
	* @version V1.0
	 */
	Disc pushFiberdiscByDiscCode(String discCode);

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
	 * @date 2018年1月23日 下午2:59:20
	 * @version V1.0
	 */
	List<Fiberdisc> queryByDiscCode(String discCode);

	/**
	 * 
	* @Title: queryByPort01 
	* @Description: 模糊查询端子编码 
	* @param @param discCode
	* @param @return    
	* @return List<Fiberdisc>
	* @author liucanghai 
	* @throws
	* @date 2018年2月1日 下午2:13:10 
	* @version V1.0
	 * @param zdiscCode 
	 */
	List<Fiberdisc> queryByPort01(String discCode);

	/**
	 * 
	* @Title: queryByZhiPort01 
	* @Description: 查询直熔 
	* @param @param adiscCode
	* @param @param zdiscCode
	* @param @return    
	* @return List<Fiberdisc>
	* @author liucanghai 
	* @throws
	* @date 2018年2月2日 上午11:34:49 
	* @version V1.0
	 */
	List<FiberdiscBean> queryByZhiPort01(String adiscCode, String zdiscCode);

	/**
	 * 
	* @Title: cableSectionChart 
	* @Description: 光缆段成端详情 
	* @param @param model
	* @param @param devId
	* @param @param sectId
	* @param @return    
	* @return String
	* @author liucanghai 
	* @throws
	* @date 2018年4月18日 下午3:20:21 
	* @version V1.0
	 */
	CableSectionChart cableSectionChart(Long devId, Long sectId);

	/**
	 * 查询端子情况
	* @Title: queryDiscGroupDiscPorts 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return CableSectionChart    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月19日 下午2:44:23 
	* @version V1.0
	 */
	List<Group> queryDiscGroupDiscPorts(Long devId);

	/**
	 * 
	* @Title: queryByGroupDesc 
	* @Description: 根据端子ID查询端口信息 
	* @param @param fiberDiscId
	* @param @return    
	* @return Group1
	* @author liucanghai 
	* @throws
	* @date 2018年4月23日 上午9:38:42 
	* @version V1.0
	 */
	Group1 queryByGroupDesc(Long fiberDiscId);

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
	 * @date 2018年5月10日 下午1:53:52
	 * @version V1.0
	 */
	FiberdiscBean queryFiberdiscOccupCount(Long devId);

	/**
	 * 根据机构ID,查询端子占用情况
	* @Title: queryOccupyConditionByOrgId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @return    入参
	* @return List<DifferentPortsBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月24日 下午3:44:04 
	* @version V1.0
	 */
	List<DifferentPortsBean> queryOccupyConditionByOrgId(Long orgId);

	/**
	 * 根据机构ID,查询端子占用情况总条数
	* @Title: queryOccupyCountsByOrgId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    入参
	* @return Integer    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月25日 上午9:36:24 
	* @version V1.0
	 * @param pb 
	 * @param orgId 
	 */
	Integer queryOccupyCountsByOrgId(Long orgId, PageBean pb);

	/**
	 * 根据区域编码查询端子数据统计
	* @Title: queryOccupyConditionByArea 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @param pb
	* @param @return    入参
	* @return List<DifferentPortsBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月25日 上午10:26:44 
	* @version V1.0
	 * @param differentPortsBean 
	 */
	List<DifferentPortsBean> queryOccupyConditionByArea(Long orgId);

	/**
	 * 根据区域编码查询端子数据统计总条数
	* @Title: queryOccupyCountsByArea 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @param pb
	* @param @param differentPortsBean
	* @param @return    入参
	* @return Integer    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月25日 上午11:00:05 
	* @version V1.0
	 */
	Integer queryOccupyCountsByArea(Long orgId, PageBean pb, DifferentPortsBean differentPortsBean);

	/**
	 * 查询端子占用情况
	* @Title: queryPortOccupyCodition 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return List<Group>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月25日 下午4:21:00 
	* @version V1.0
	 */
	List<Group> queryPortOccupyCodition(Long devId);

	/**
	 * 查询本端端子设施名称
	* @Title: queryDevNameByDiscId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param discId
	* @param @return    入参
	* @return String    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月22日 下午3:24:22 
	* @version V1.0
	 */
	String queryDevNameByDiscId(Long fiberDiscId);

	/**
	 * 查询端子情况,分组信息等
	* @Title: queryByConditions 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @param clist
	* @param @return    入参
	* @return List<DiscinfoMobile>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年8月27日 下午4:19:19 
	* @version V1.0
	 */
	List<DiscinfoMobile> queryByConditions(Long devId, List<Long> slist);

}