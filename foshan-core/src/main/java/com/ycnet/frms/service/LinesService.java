package com.ycnet.frms.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ycnet.core.Constants;
import com.ycnet.frms.bean.CableSection;
import com.ycnet.frms.bean.Fiberdisc;
import com.ycnet.frms.bean.LineImage;
import com.ycnet.frms.bean.Lines;
import com.ycnet.frms.bean.LinesZF;
import com.ycnet.frms.bean.PortImg;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.CableSectionDecBean;
import com.ycnet.frms.vo.ConnectTwoCableSectionBean;
import com.ycnet.frms.vo.JumperInfo;
import com.ycnet.frms.vo.JumperInfos;
import com.ycnet.frms.vo.LinesBean;
import com.ycnet.frms.vo.LinesConditionBean;
import com.ycnet.frms.vo.WorkorderJumperInfo;

public interface LinesService {

	
	int deleteById(Long lineId);
	
	int deleteAllFibers(Long sectId);
	
	int update(Lines lines);
	
	int updateAllColoumn(Lines lines);
	/**
	 * 查询光纤
	 * @param param
	 * @return
	 */
	List<Lines> queryFiber(LinesConditionBean param);
	
	/**
	 * 通过光缆断id和纤芯范围查询光纤
	 * @param sectId
	 * @param fromFiberNum
	 * @param endFiberNum
	 * @return
	 */
	List<Lines> queryFiberByFiberNo(Long sectId,Integer fromFiberNum,Integer endFiberNum);
	
	Lines selectById(Long lineId);
	
	/**
	 * 添加光纤
	 * @param sectId：光缆段ID
	 * @param count：纤芯数量
	 * @param devIdA: A端设施ID
	 * @param devIdZ: Z端设施ID
	 * @return
	 */
	int addFiber(Long sectId,Long count,Long devIdA,Long devIdZ,Long orgId);
	
	/**
	 * 光缆段A/Z变化后修改纤芯信息
	 * @param sectId
	 * @param oldDevIdA
	 * @param oldDevIdZ
	 * @param newDevIdA
	 * @param newDevIdZ
	 * @return
	 */
	int updateFiber(Long sectId,Long oldDevIdA,Long oldDevIdZ,Long newDevIdA,Long newDevIdZ);
	
	/**
	 * 生成分光器内部虚拟线
	 * @param devId 设施ID
	 * @return
	 */
	int addVirtual(Long devId,Constants.FACILITTYPE devType);
	
	/**
	 * 资管导入数据专用
	 * @author: YHT
	 * @date: 2017年10月24日 上午11:15:32 
	 * @Title: addVirtualZG  
	 * @param @param devId
	 * @param @param devType
	 * @param @return     
	 * @return int   
	 * @throws
	 */
	int addVirtualZG(Long devId,Constants.FACILITTYPE devType);
	
	/**
	 * 生成跳纤
	 * @param adevId
	 * @param acode
	 * @param zdevId
	 * @param zcode
	 * @param srvName
	 * @return
	 */
	int addJumper(Long adevId,String acode,Long zdevId,String zcode,String srvName,Long orgId);
	/**
	 * 
	* @Title: addJumper 
	* @Description: TODO(跳纤添加到line) 
	* 修改:跳纤时添加创建人和时间
	* @param @param adevId
	* @param @param acode
	* @param @param zdevId
	* @param @param zcode
	* @param @param srvName
	* @param @param orgId
	* @param @param unknownPointName
	* @param @param userId
	* @param @return    入参
	* @return int    返回类型
	* @author （刘沧海） 
	* @throws
	* @date 2017年11月2日 下午4:18:46 
	* @version V1.0
	 */
	int addJumper(Long adevId,String acode,Long zdevId,String zcode,String srvName,Long orgId,String unknownPointName,Long userId);
	/**
	 * 按光缆段查询光纤
	 * @param sectId
	 * @return
	 */
	List<Lines> selectBySect(Long sectId,Long devId,String srvName);
	

	int unFiberlocate(Long devId,Long sectId,Integer fromFiberNo,Integer endFiberNo);
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
	int unFiberlocate(Long devId,Long sectId, Long userId);
	
	int update(List<Lines> list);
	
	List<Lines> queryByPoint(String code);
	
	Lines selectLinesByTwoPoint(String acode,String zcode);
	
	List<LinesBean> selectLineBySect(Long sectId);
	
	/**
	 * @param user 
	 * @param request 
	 * 双芯跳纤添加
	 * @author: YHT
	 * @date: 2017年9月6日 下午3:12:11 
	 * @Title: saveJumper  
	 * @param @param jumperInfos
	 * @param @return     
	 * @return int   
	 * @throws
	 */
	int saveTwinsJumper(JumperInfos jumperInfos, HttpServletRequest request, Users user);

	//导出数据库  刘沧海 2017/10/13
	List<LinesZF> queryList(Long orgId, String areaCode);

	/**
	 * 
	* @Title: isExistJumper 
	* @Description: TODO(判断是否为尾纤悬空) 
	* @param @param code
	* @param @return    入参
	* @return int    返回类型
	* @author （刘沧海） 
	* @throws
	* @date 2017年11月14日 下午3:10:36 
	* @version V1.0
	 */
	int isExistJumper(String code);

	/**
	 * 
	* @Title: insertCableSectionDev 
	* @Description: 导入资管数据 
	* @param @param userId
	* @param @return    
	* @return int
	* @author liucanghai 
	* @throws
	* @date 2017年12月3日 下午6:44:02 
	* @version V1.0
	 */
	int insertCableSectionDev(Long userId);

	/**
	 * 
	* @Title: queryCodeByZg 
	* @Description: TODO(根据端子查询资管详情) 
	* @param @param code
	* @param @return    入参
	* @return CableSectionDec    返回类型
	* @author liucanghai（作者） 
	* @throws
	* @date 2017年11月28日 上午9:20:42 
	* @version V1.0
	 */
	CableSectionDecBean queryCodeByZg(String code);

	/**
	 * 
	 * @Title: queryBySectIdAndFiberNo
	 * @Description: 根据SectId和FiberNo查询一条
	 * @param @param sectId
	 * @param @param long1
	 * @param @return 
	 * @return Lines 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年11月28日 下午5:33:33
	 * @version V1.0
	 */
	Lines queryBySectIdAndFiberNo(Long sectId, Long fiberNo);

	/**
	 * 
	 * @Title: changeJumpper
	 * @Description: 更改跳纤接口,包括(解跳纤,更改跳纤)
	 * @param @param user
	 * @param @param jumperInfo
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年11月29日 上午10:08:37
	 * @version V1.0
	 * @param request 
	 */
	int changeJumpper(Users user, LinesBean linesBean, HttpServletRequest request);

	/**
	 * 
	 * @Title: queryLineType
	 * @Description: 根据code查询A端是否成端
	 * @param @param acode
	 * @param @return 
	 * @return LinesBean 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年11月29日 上午10:31:11
	 * @version V1.0
	 * @param string 
	 */
	List<LinesBean> queryLineType(String lineType,String acode);
	/**
	 * 
	* @Title: addJumper 
	* @Description: 跳纤 并且验证
	* @param @param jumperInfo
	* @param @return    
	* @return int
	* @author liucanghai 
	* @throws
	* @date 2017年12月3日 下午6:28:00 
	* @version V1.0
	 */
	int addJumper(JumperInfo jumperInfo,HttpServletRequest request,Users user);

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
	 * @date 2017年12月3日 下午3:49:05
	 * @version V1.0
	 */
	List<LinesBean> queryBySectId(Long sectId);
	
	/**
	 * 根据修改光缆信息。修改纤芯信息
	* @Title: updateLine 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param cableSection
	* @param @return    入参
	* @return int    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2017年12月3日 上午11:54:34 
	* @version V1.0
	 */
	int updateLine(CableSection cableSection,CableSection old);

	List<Lines> queryFiberNumFor();

	/**
	 * 
	* @Title: saveConnectTwoCableSection 
	* @Description: 保存直熔光缆段 
	* @param @param bean
	* @param @return    
	* @return int
	* @author liucanghai 
	* @throws
	* @date 2017年12月17日 下午2:46:47 
	* @version V1.0
	 * @param user 
	 */
	int saveConnectTwoCableSection(Users user, ConnectTwoCableSectionBean bean);

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
	 * @date 2017年12月17日 下午8:15:52
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
	 * @date 2017年12月17日 下午8:23:43
	 * @version V1.0
	 */
	List<Lines> queryByDiscCode(String discCode);
	
	

	/**
	 * 保存跳纤图片
	* 
	* @Description: TODO 
	* @param @param LinesBean
	* @param @param images
	* @param @return
	* @return int
	* @author 周宇
	* @date 2017年12月3日 下午3:23:57
	* @version V1.0
	 */
	int insertLineImg(List<LineImage> list,Long lineId);

	/**
	 * 
	 * @Title: saveImages
	 * @Description: 保存更改跳纤时图片
	 * @param @param lineId
	 * @param @param request
	 * @param @param user
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月23日 下午9:38:20
	 * @version V1.0
	 */
	int saveImages(Long lineId, HttpServletRequest request, Users user);

	/**
	 * 
	 * @Title: selectByDiscCode
	 * @Description: 根据discCode查看当前盘是否存在成端或跳纤,如果存在,则不可以删除
	 * @param @param string
	 * @param @return 
	 * @return List<Lines> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月5日 上午9:52:24
	 * @version V1.0
	 */
	List<Lines> selectByDiscCode(String discCode);

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
	 */
	int unbindAsaside(Long devId, Long sectId, Integer aorz, Long userId);

	/**
	 * 
	* @Title: workorderAddJumper 
	* @Description: 光路调度跳纤 
	* @param @param jumperInfo
	* @param @param request
	* @param @param user
	* @param @return    
	* @return int
	* @author liucanghai 
	* @throws
	* @date 2018年4月24日 上午9:48:29 
	* @version V1.0
	 */
	int workorderAddJumper(WorkorderJumperInfo jumperInfo, HttpServletRequest request, Users user);

	/**
	 * 
	* @Title: workorderChangeJumpper 
	* @Description: 光路调度解跳纤 
	* @param @param user
	* @param @param linesBean
	* @param @param request
	* @param @return    
	* @return int
	* @author liucanghai 
	* @throws
	* @date 2018年4月24日 下午1:38:58 
	* @version V1.0
	 * @param designroutesId 
	 */
	int workorderChangeJumpper(Users user, LinesBean linesBean, HttpServletRequest request, Long designroutesId);

	/**
	 * 
	 * @Title: queryByAcodeAndZcode
	 * @Description: 根据acode和zcode查询跳纤
	 * @param @param code
	 * @param @param zcode
	 * @param @return 
	 * @return LinesBean 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月6日 上午11:45:40
	 * @version V1.0
	 * @param zcode2 
	 */
	LinesBean queryByAcodeAndZcode(String lineType, String code, String zcode);

	/**
	 * 
	 * @Title: saveJumpperImagesOpd
	 * @Description: 保存跳纤端子图片
	 * @param @param request
	 * @param @param user
	 * @param @return 
	 * @return List<String> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月21日 下午4:31:20
	 * @version V1.0
	 */
	List<String> saveJumpperImagesOpd(HttpServletRequest request, Users user);

	/**
	 * 
	 * @Title: selectLinesByTwoPointZY
	 * @Description: 查询跳纤
	 * @param @param code
	 * @param @param zcode
	 * @param @return 
	 * @return Lines 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月22日 下午2:40:31
	 * @version V1.0
	 */
	Lines selectLinesByTwoPointZY(String code, String zcode);

	/**
	 * 
	 * @Title: deleteImage
	 * @Description: 删除端子图片
	 * @param @param request
	 * @param @param imageUrlList
	 * @param @param lineId
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月17日 下午3:47:57
	 * @version V1.0
	 */
	int deleteImage(HttpServletRequest request, List<String> imageUrlList, Long lineId);

}
