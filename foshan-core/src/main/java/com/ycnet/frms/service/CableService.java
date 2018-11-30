package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Cable;
import com.ycnet.frms.bean.CableSection;
import com.ycnet.frms.bean.CableZF;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.CableBean;
import com.ycnet.frms.vo.CableConditionBean;
import com.ycnet.frms.vo.CableFacilityBean;
import com.ycnet.frms.vo.CableSectionConditionBean;
import com.ycnet.frms.vo.CableSectionResultVo;
import com.ycnet.frms.vo.CableStat;
import com.ycnet.frms.vo.CablesBean;

public interface CableService {

	int save(Cable cable);
	
	Cable selectById(Long cableId);
	
	List<Cable> selectByParam(CableConditionBean searchBean);
	
	CableBean convert(Cable cable);
	
//	CableSectionConditionBean convert(List<CableSection> cableSection);
	
	List<CableBean> convert(List<Cable> list);
	
	/**
	 * web查询光缆列表
	 * @author YHT
	 * @time   2016年7月24日 上午10:32:06
	 * @param param
	 * @return
	 */
	PageBean queryByCablesBean(CablesBean bean,PageBean pb,Long orgId);
	
	int delete(Long sectId); 

	Cable queryCableEnd(Long sectId);

	Cable queryCablesInfo(Long sectId);

	//导出纤芯占用详细(光交箱)
	List<Cable> queryPortInfo(Long orgId, String areaCode1);

	List<CableStat> queryPortStat(Long orgId, String areaCode1);

	List<Cable> queryCableInfo(Long orgId, String areaCode1);

	List<Cable> queryCableDevId(Long orgId, Long devId);

	List<Cable> queryCableInfoCablin(Long orgId, String areaCode1);
	
	List<Facility> queryBySectId(Long sectId);

	//导出纤芯占用详细机柜
	List<Cable> queryPortInfoCablin(Long orgId, String areaCode1);

	//导出数据库 刘沧海 2017/10/13
	List<CableZF> queryList(Long orgId);

	/**
	 * web每第一次进入,只查未成端光缆
	* 
	* @Title: CableService.java 
	* @Description: TODO 
	* @param @param bean
	* @param @param pb
	* @param @param orgId
	* @param @return
	* @return Object
	* @author fl
	* @date 2018年1月16日 下午2:11:13
	* @version V1.0
	 * @param bean 
	 * @param pb 
	 */
	PageBean queryCablesByBean(CablesBean bean, PageBean pb, Long orgId);
	/**
	 * 
	* @Title: queryErrorRecCableListByCablesBean 
	* @Description: 查询纠错光缆段 
	* @param @param bean
	* @param @param pb
	* @param @param orgId
	* @param @return    入参
	* @return PageBean    返回类型
	* @author zhouy 
	* @throws
	* @date 2018年1月23日 下午2:32:23 
	* @version V1.0
	 */
	PageBean queryErrorRecCableListByCablesBean(CablesBean bean, PageBean pb, Long orgId);

	/**
	 * 
	* @Title: queryAllCableSection 
	* @Description: 查询光缆段(用于导出) 
	* @param @param areaCode1
	* @param @return    
	* @return List<Cable>
	* @author liucanghai 
	* @throws
	* @date 2018年1月23日 下午4:47:01 
	* @version V1.0
	 */
	List<Cable> queryAllCableSection(CablesBean bean,PageBean pb, Long orgId);

	/**
	 * 
	* @Title: moDuanSectionExport 
	* @Description: 导出末端光缆成端信息 
	* @param @param bean
	* @param @param pb
	* @param @param orgId
	* @param @return    
	* @return List<Cable>
	* @author liucanghai 
	* @throws
	* @date 2018年1月23日 下午5:14:26 
	* @version V1.0
	 */
	List<Cable> moDuanSectionExport(CablesBean bean, PageBean pb, Long orgId);
	
	/**
	 * 
	* @Title: yiDuanSectionExport 
	* @Description: 导出一端成端光缆段信息 
	* @param @param bean
	* @param @param pb
	* @param @param orgId
	* @param @return    
	* @return List<Cable>
	* @author liucanghai 
	* @throws
	* @date 2018年1月23日 下午5:20:23 
	* @version V1.0
	 */
	List<Cable> yiDuanSectionExport(CablesBean bean, PageBean pb, Long orgId);

	/**
	 * 
	* @Title: yiDuanSectionExport 
	* @Description: 导出已成端光缆段信息 
	* @param @param bean
	* @param @param pb
	* @param @param orgId
	* @param @return    
	* @return List<Cable>
	* @author liucanghai 
	* @throws
	* @date 2018年1月23日 下午5:20:23 
	* @version V1.0
	 */
	List<Cable> yiChengDuanSectionExport(CablesBean bean, PageBean pb, Long orgId);

	/**
	 * 
	* @Title: chongFuSectionExport 
	* @Description: 导出重复光缆成端数据 
	* @param @param bean
	* @param @param pb
	* @param @param orgId
	* @param @return    
	* @return List<Cable>
	* @author liucanghai 
	* @throws
	* @date 2018年1月23日 下午5:24:59 
	* @version V1.0
	 */
	List<Cable> chongFuSectionExport(CablesBean bean, PageBean pb, Long orgId);

	/**
	 * 
	 * @Title: exporterrorRecCable
	 * @Description: 导出纠错光缆段
	 * @param @param bean
	 * @param @param pb
	 * @param @param orgId
	 * @param @return 
	 * @return List<Cable> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月24日 下午5:04:04
	 * @version V1.0
	 */
	List<CablesBean> exporterrorRecCable(CablesBean bean, PageBean pb, Long orgId);


}
