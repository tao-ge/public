package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.Discinfo;
import com.ycnet.frms.bean.Lines;
import com.ycnet.frms.bean.LinesZF;
import com.ycnet.frms.vo.LinesBean;
import com.ycnet.frms.vo.LinesConditionBean;

public interface LinesMapper {
	
    int deleteByPrimaryKey(Long lineId);
    
    int delete1(String lineType,Long sectId);

    int insert(Lines record);

    int insertSelective(Lines record);
    
    int insertSelectiveZG(Lines record);

    Lines selectByPrimaryKey(Long lineId);

    int updateByPrimaryKeySelective(Lines record);
    
    int updateByPrimaryKeySelectiveZG(Lines record);

    int updateByPrimaryKey(Lines record);
    
    /**
     * 查询光纤
     * @param param
     * @return
     */
    List<Lines> queryFiber(LinesConditionBean param);

    /**
     * 通过给定的光缆段ID和纤芯范围查询光纤
     * @param param
     * @return
     */
    List<Lines> queryFiberByFiberNo(Map<String,Object> param);
    
    /**
     * 查询跳纤
     * @param param
     * @return
     */
    List<Lines> queryJumper(LinesConditionBean param);
    
    
    /**
     *  根据两点查询唯一的跳纤
     * @param param
     * @return
     */
    Lines selectJumper(LinesConditionBean param);
    
    Lines selectJumperWithNullPoint(LinesConditionBean param);
    
    /**
     * 查询虚拟线
     * @param param
     * @return
     */
    List<Lines> queryVirtual(LinesConditionBean param);
    
    /**
     * 解除光纤成端信息
     * @param lineType
     * @param devId
     * @param sectId
     * @param fromFiberNo
     * @param endFiberNo
     * @return
     */
    int unFiberlocate(String lineType,Long devId,Long sectId,Integer fromFiberNo,Integer endFiberNo);
    /**
	 * 
	* @Title: unLocateDisc 
	* @Description: TODO(修改:解成端时添加最后修改人和时间) 
	* @param @param devId
	* @param @param sectId
	* @param @param session
	* @param @return    入参
	* @return Object    返回类型
	* @author （刘沧海） 
	* @throws
	* @date 2017年11月2日 下午4:07:06 
	* @version V1.0
	 */
    int unFiberlocate(String lineType, Long devId, Long sectId, Long userId);
    /**
     * 通过端子编码查询端子使用情况
     * @param code
     * @return
     */
    List<Lines> queryByPoint(String code);
    
    Lines selectLinesByTwoPoint(String acode,String zcode);
    
    List<String> selectAlonePort();
    
    List<String> selectAlonePort(Long orgId,String areaCode,String linesIds,Long devId);
    
    List<Lines> selectRelationLines(String code);
    
    List<Lines> selectJumperByGroup(Long devId,String side);
    
    Lines selectAllLineWithOneNull(String code);

	int deleteLines(String str);

	List<Lines> queryLinesSideAcode(String str);

	List<Lines> queryLinesSideZcode(String str);
	
	/**
	 * 检查是否成端
	 * @param str
	 * @return
	 */
	List<Lines> selectIsRBySectId(Long sectId);
	
	int selectTerminatByPort(String acode,String zcode);
	
	/**
	 * 查询光缆段对应端子详细信息
	 * @param sectId
	 * @return
	 */
	List<LinesBean> selectLineBySect(Long sectId);

	//用于删除设备
	List<Lines> selectByDevId(Long devId);
	//用于删除设备
	int deleteByDevId(Long devId);

	
	int isExistJumperByCode(String code);

	//导出数据库  刘沧海 2017/10/13
	List<LinesZF> queryList(Long orgId, String areaCode);
	
	/**
	 * 根据光缆信息查询成端信息
	 * @author: YHT
	 * @date: 2017年10月19日 下午2:03:20 
	 * @Title: selectBySectOrFib  
	 * @param @param sectId
	 * @param @param fiberNo
	 * @param @return     
	 * @return Lines   
	 * @throws
	 */
	Lines selectBySectOrFib(Long sectId,Long fiberNo);
	
	/**
	 * 根据光缆查询list
	 * @author: YHT
	 * @date: 2017年11月10日 下午3:52:34 
	 * @Title: selectBySectId  
	 * @param @param sectId
	 * @param @return     
	 * @return List<Lines>   
	 * @throws
	 */
	List<Lines> selectBySectId(Long sectId);

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
	 * @date 2017年11月28日 下午4:59:05
	 * @version V1.0
	 */
	Lines queryBySectIdAndFiberNo(Map<String, Object> map);

	List<Lines> queryLinsFor01();

	Lines queryLinsForAcode(String code);

	/**
	 * 
	 * @Title: queryLineType
	 * @Description: 根据code查询是否成端
	 * @param @param map
	 * @param @return 
	 * @return LinesBean 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年11月29日 上午10:40:40
	 * @version V1.0
	 */
	List<LinesBean> queryLineType(Map<String, Object> map);

	/**
	 * 
	* @Title: queryByCode01 
	* @Description: 根据端子编码查询成端信息 
	* @param @param acode
	* @param @return    
	* @return List<Lines>
	* @author liucanghai 
	* @throws
	* @date 2017年12月3日 下午3:37:49 
	* @version V1.0
	 */
	Lines queryByCode01(String code);

	/**
	 * 
	 * @Title: queryBySectId
	 * @Description: 根据sectId查询数据
	 * @param @param sectId
	 * @param @return 
	 * @return List<LinesBean> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月3日 下午3:50:48
	 * @version V1.0
	 */
	List<LinesBean> queryBySectId(Long sectId);

	List<Lines> queryFiberNumFor();

	/**
	 * 删除多余成端信息
	* @Title: deleteBySectIdAndFiberNum 
	* @Description: TODO 
	* @param @param sectId
	* @param @param fiberNum
	* @param @return    
	* @return int
	* @author liucanghai 
	* @throws
	* @date 2017年12月13日 上午12:23:18 
	* @version V1.0
	 */
	int deleteBySectIdAndFiberNum(Long sectId, Long fiberNum);

	/**
	 * 
	* @Title: queryDevCodeByCode 
	* @Description: 根据设施编号模糊查询有没有成端 
	* @param @param devCode
	* @param @return    
	* @return List<Lines>
	* @author liucanghai 
	* @throws
	* @date 2017年12月16日 下午4:11:44 
	* @version V1.0
	 */
	List<Lines> queryDevCodeByCode(String devCode);

	/**
	 * 
	* @Title: queryForSectId 
	* @Description: 根据光缆段ID查询成端 
	* @param @param sectId
	* @param @param lineType
	* @param @return    
	* @return List<Lines>
	* @author liucanghai 
	* @throws
	* @date 2017年12月16日 下午5:36:12 
	* @version V1.0
	 */
	List<Lines> queryForSectId(Long sectId, String lineType);

	/**
	 * 
	* @Title: queryFutureTwoCableSection 
	* @Description: 根据光缆段ID和纤芯序号查询 
	* @param @param sectIdA
	* @param @param i
	* @param @return    
	* @return Lines
	* @author liucanghai 
	* @throws
	* @date 2017年12月17日 下午3:03:34 
	* @version V1.0
	 */
	Lines queryFutureTwoCableSection(Long sectIdA, long i);

	/**
	 * 
	 * @Title: deleteLinesByLineTypeAndCode
	 * @Description: 根据discCode删除跳纤
	 * @param @param discCode
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月17日 下午8:16:43
	 * @version V1.0
	 */
	int deleteLinesByLineTypeAndCode(String discCode);

	/**
	 * 
	 * @Title: queryByDiscCode
	 * @Description: 根据discCode查成端
	 * @param @param discCode
	 * @param @return 
	 * @return List<Lines> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月17日 下午8:24:36
	 * @version V1.0
	 */
	List<Lines> queryByDiscCode(String discCode);

	Lines selectLinesByTwoPointZY(String code, String zcode);

	/**
	 * 
	* @Title: queryBySectIdLineType 
	* @Description: 根据line类型和sectId查询 
	* @param @param sectIdA
	* @param @param string
	* @param @return    
	* @return List<Lines>
	* @author liucanghai 
	* @throws
	* @date 2017年12月26日 下午12:05:08 
	* @version V1.0
	 */
	List<Lines> queryBySectIdLineType(Long sectId, String lineType);

	/**
	 * 
	 * @Title: selectByDiscCode
	 * @Description: 根据discCode查看当前盘是否存在成端或跳纤,如果存在,则不可以删除 
	 * @param @param discCode
	 * @param @return 
	 * @return List<Lines> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月5日 上午9:54:15
	 * @version V1.0
	 */
	List<Lines> selectByDiscCode(String discCode);
	
	/**
	 * 
	* @Title: queryForDiscCodeByOPS 
	* @Description: 根据盘编码模糊查询 
	* @param @param discCode
	* @param @return    
	* @return List<Discinfo>
	* @author liucanghai 
	* @throws
	* @date 2018年1月4日 下午3:38:43 
	* @version V1.0
	 * @param devCode 
	 */
	List<Lines> queryForDiscCodeByOPS(String lineType, String devCode);

	/**
	 * 
	* @Title: unbindAsaside 
	* @Description: 解成端处理lines表 
	* @param @param devId
	* @param @param sectId
	* @param @param aorz
	* @param @param userId
	* @param @return    
	* @return int
	* @author liucanghai 
	* @throws
	* @date 2018年1月12日 上午10:58:12 
	* @version V1.0
	 * @param aorz 
	 */
	int unbindAsaside(String lintType, Long devId, Long sectId, Integer aorz, Long userId);

	/**
	 * 根据devId 查设施
	* 
	* @Title: LinesMapper.java 
	* @Description: TODO 
	* @param @param code
	* @param @return
	* @return List<Lines>
	* @author fl
	* @date 2018年1月16日 上午8:55:00
	* @version V1.0
	 */
	List<Lines> selectJumperByCode(String code);

	/**
	 * 
	* @Title: queryNotoEnd 
	* @Description: 查询A端未成端的 
	* @param @param devId
	* @param @param sectId
	* @param @return    
	* @return List<Lines>
	* @author liucanghai 
	* @throws
	* @date 2018年4月19日 下午1:51:28 
	* @version V1.0
	 * @param state 
	 */
	List<Lines> queryNotoEndA(Long devId, Long sectId);

	/**
	 * 
	* @Title: queryNotoEnd 
	* @Description: 查询Z端未成端的 
	* @param @param devId
	* @param @param sectId
	* @param @return    
	* @return List<Lines>
	* @author liucanghai 
	* @throws
	* @date 2018年4月19日 下午1:51:28 
	* @version V1.0
	 * @param state 
	 */
	List<Lines> queryNotoEndZ(Long devId, Long sectId);

	/**
	 * 
	* @Title: queryLineType02 
	* @Description: 查询跳纤 
	* @param @param port01
	* @param @return    
	* @return Lines
	* @author liucanghai 
	* @throws
	* @date 2018年4月10日 上午10:35:35 
	* @version V1.0
	 */
	Lines queryLineType02(String port01);

	/**
	 * 
	 * @Title: queryByAcodeAndZcode
	 * @Description: 根据acode和zcode查询跳纤
	 * @param @param map
	 * @param @return 
	 * @return LinesBean 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月6日 上午11:48:01
	 * @version V1.0
	 */
	LinesBean queryByAcodeAndZcode(Map<String, Object> map);

	/**
	 * 查询对端跳纤的端子
	* @Title: queryOppositeByPort01 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param port01
	* @param @return    入参
	* @return Lines    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月22日 下午3:31:26 
	* @version V1.0
	 */
	Lines queryOppositeByPort01(String port01);







}