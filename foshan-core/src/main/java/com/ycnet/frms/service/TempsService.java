package com.ycnet.frms.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ycnet.core.util.PageBean;
import com.ycnet.core.util.ResultData;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.FacilityImg;
import com.ycnet.frms.bean.FacilityImgs;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.FacilityBean;
import com.ycnet.frms.vo.FacilityConditionBean;
import com.ycnet.frms.vo.JumperInfo;
import com.ycnet.frms.vo.LocateInfo;
import com.ycnet.frms.vo.LockKey;

public interface TempsService {

	Facility selectByCode(String devCode);
	
	Long save(Facility facility);
	
	Long save(Facility facility,List<FacilityImg> images);
	
	/**
	 * 添加分光器
	 * @param facility
	 * @return
	 */
	int addObd(Facility facility);
	
	Facility selectById(Long devId);
	
	int delete(Long devId);
	
	List<Facility> queryByConditionBean(FacilityConditionBean bean);
	
	
	List<Facility> selectAround(Double curLng, Double curLat,Double distance,String devType,String surveyResult,String areaCode1, Users user);
	
	List<Facility> selectAround1(Double curLng, Double curLat,Double distance,String devType,String surveyResult,String areaCode1, Users user);
	//坐标转换
	List<FacilityBean> selectAroundConvert(Double curLng, Double curLat,Double distance,String devType,String surveyResult, String areaCode1, Users user);
	
	FacilityBean convert(Facility facility);
	
	List<FacilityBean> convert(List<Facility> list);
	
	/**
	 * 依据给定的设施ID，盘面，和熔纤盘数量自动生成设施熔纤盘
	 * @param devId ：所属设施id
	 * @param side    : A,B 面
	 * @param fiberDiscNum 熔纤盘数量
	 * @param portNum 单个熔纤盘端子数
	 * @return
	 */
	int genFiberdisc(Long devId,String side,Integer fiberDiscNum ,Integer portNum,Integer fromIndex);
	
	/**
	 * 依据给定的设施ID，盘面，和制定的熔纤盘序号添加一个熔纤盘
	 * @param devId
	 * @param side
	 * @param discRowNo
	 * @return
	 */
	int addFiberdisc(Long devId,String side,Integer discRowNo);
	
	/**
	 * 光纤成端(单熔纤盘）
	 * @param devId  设施ID
	 * @param sectId  光缆段ID
	 * @param discCode 熔纤盘/插板编号
	 * @param fromFiberNum 起始纤芯
	 * @param endFiberNum	终止纤芯
	 * @param fromPortNum	起始端子序号
	 * @return
	 */
	int fiberLocate(Long devId,Long sectId,String discCode,Integer fromFiberNum,Integer endFiberNum,Integer fromPortNum);
	
	/**
	 * 光纤成端(多熔纤盘连续成端）
	 * @return
	 */
	int fiberLocate(Long devId,Long sectId,String startDiscCode,Integer fromFiberNum,Integer endFiberNum,Integer fromPortNum,String side,String direction);
	
	int fiberLocate(LocateInfo info);
	/**
	 * 解成端
	 * @param devId
	 * @param sectId
	 * @param fromFiberNum
	 * @param endFiberNum
	 * @return
	 */
	int unFiberLocate(Long devId,Long sectId,Integer fromFiberNum,Integer endFiberNum,Long userId);
	
	int unFiberLocate(Long devId,Long sectId,Long userId);
	/**
	 * 添加跳纤
	 * @param adevId
	 * @param acode
	 * @param zdevId
	 * @param zcode
	 * @param srvName
	 * @return
	 */
	int addJumper(Long adevId,String acode,Long zdevId,String zcode,String srvName,Long orgId);
	/**
	 * 跳纤维护
	 * @param jumperInfo
	 * @return
	 */
	int saveJumper(JumperInfo jumperInfo);
	
    List<LockKey> queryKeyList(Long uid,Facility fa);
    
    /**
     * 根据mac地址获取设施信息
     * @author YHT
     * @time   2016年7月14日 下午1:35:41
     * @param session
     * @param fa
     * @return
     */
    ResultData facilityFromdevMac(HttpSession session,Facility fa);
    
    PageBean queryByConditionBean(FacilityConditionBean bean,Users user,PageBean pb);
    
    
    PageBean queryFacilityByPosition(FacilityConditionBean bean,PageBean pb);
    
	/**
	 * 根据地区code获取设施坐标并根据坐标获取半径内的所有设施
	 */
    PageBean queryDistanceFacilityByCondition(FacilityConditionBean bean,
			Users user, PageBean pb);

    /**
     * 查询设施详情
     * @param devId
     * @return
     */
    FacilityBean selectDetailById(Long devId);
    
    List<Facility> queryListByAccessIds(String ids);
    
    /**
     * 手机端 查询设施总数
     * @author YHT
     * @time   2016年8月7日 下午3:07:34
     * @param bean
     * @return
     */
    Integer queryCountByConditionBean(FacilityConditionBean bean);

	void exportFacilityTemp(HttpServletResponse response,HttpServletRequest request);

	String importFacilitiesByExcel(HttpServletRequest request,
			HttpServletResponse response,
			MultipartHttpServletRequest multiRequest);
	//zhouyu 1/4修改返回值类型facility-facilityBean
	List<FacilityBean> queryListByContent(String content);
	
	/**
	 * 简单查询  不转换bean
	 * @param bean
	 * @param user
	 * @param pb
	 * @return
	 */
	PageBean simpleQueryByConditionBean(FacilityConditionBean bean,
			Users user, PageBean pb);
	
	/**
	 * 根据devId获取设施信息
	 * @author  YHT 
	 * @date 创建时间：2017年2月27日 下午2:47:52 
	 * @version 1.0
	 * @param devId
	 * @return
	 */
	List<Facility> selectByDevIds(String workId);

	List<Facility> findFacilities(Long orgId);
	/**
	 * 转成百度经纬度
	 * @return
	 */

	String changeFacility();

	Facility selectByCodeMap(String devCode);

	Facility selectByCode1(String devCode, Long orgId);

	FacilityImgs selectImgById(Long devId);

	Long edit(Facility facility, List<FacilityImg> images);


	

	int saveOLT(String vender);
	
	int buildAlarmLog();
}
