package com.ycnet.frms.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.core.FrmsException;
import com.ycnet.core.util.HexUtil;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.DeviceDiscinfoEntity;
import com.ycnet.frms.bean.DeviceEntity;
import com.ycnet.frms.bean.DiscinfoEntity;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.AreasMapper;
import com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper;
import com.ycnet.frms.mapper.DeviceEntityMapper;
import com.ycnet.frms.mapper.DiscinfoEntityMapper;
import com.ycnet.frms.mapper.DiscinfoMapper;
import com.ycnet.frms.mapper.FiberdiscMapper;
import com.ycnet.frms.service.DeviceDiscinfoService;
import com.ycnet.frms.service.FiberdiscService;
import com.ycnet.frms.vo.AreasParent;
import com.ycnet.frms.vo.DeviceDiscinfoEntityBean;
import com.ycnet.frms.vo.DifferentPortsBean;
import com.ycnet.frms.vo.FiberdiscBean;
import com.ycnet.frms.vo.FiberdiscGroupEntityVo;
import com.ycnet.frms.vo.mobile.BindDiscNumMobile;
import com.ycnet.frms.vo.mobile.DeviceDiscinfoEntityMobile;
import com.ycnet.frms.vo.mobile.DeviceDiscinfosMobile;
import com.ycnet.frms.vo.mobile.FacilityCabinetsInfo;
import com.ycnet.frms.vo.mobile.FiberdiscGroupOpdVo;

@Service("deviceDiscinfoService")
@Transactional
public class DeviceDiscinfoServiceImpl implements DeviceDiscinfoService {
	
	@Resource(name="deviceDiscinfoEntityMapper")
	private DeviceDiscinfoEntityMapper  deviceDiscinfoEntityMapper;
	
	@Resource(name="discinfoEntityMapper")
	private DiscinfoEntityMapper discinfoEntityMapper;
	
	@Resource(name="deviceDiscinfoService")
	private DeviceDiscinfoService deviceDiscinfoService;
	
	@Resource(name="areasMapper")
	private AreasMapper areasMapper;
	
	@Resource(name="fiberdiscService")
	private FiberdiscService fiberdiscService;
	
	@Resource(name="fiberdiscMapper")
	private FiberdiscMapper fiberdiscMapper;
	
	@Resource(name="deviceEntityMapper")
	private DeviceEntityMapper deviceEntityMapper;
	
	@Resource(name="discinfoMapper")
	private DiscinfoMapper discinfoMapper;
	
	/**
	 * 根据设施查询熔纤盘分组信息
	* @Title: queryDeviceDisinfoByDdevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return List<FiberdiscGroupVo>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月13日 下午1:08:40 
	* @version V1.0
	 */
	@Override
	public List<FiberdiscGroupOpdVo> queryDeviceDisinfoByDdevId(Long devId) {
		return deviceDiscinfoEntityMapper.queryDeviceDisinfoByDdevId(devId);
	}

	/**
	 * 
	 * @Title: insertSelective
	 * @Description: 端子控制器绑定熔纤盘
	 * @param @param dd
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 下午1:41:49
	 * @version V1.0
	 */
	@Override
	public int insertSelective(DeviceDiscinfoEntity dd) {
		return deviceDiscinfoEntityMapper.insertSelective(dd);
	}
	
	/**
	 * 
	 * @Title: insertSelective
	 * @Description: 端子控制器绑定熔纤盘
	 * @param @param dd
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 下午1:41:49
	 * @version V1.0
	 */
	@Override
	public int band(DeviceDiscinfoEntity dd, Users user, long bandType) {
		
		DeviceEntity dev = deviceEntityMapper.selectByPrimaryKey(dd.getCodId());
		if(dev == null) {
			throw new FrmsException("中控器不存在");
		}
		DiscinfoEntity disc1 = discinfoEntityMapper.selectByPrimaryKey(dd.getDiscId());
		if(disc1 == null) {
			throw new FrmsException("所属熔纤盘不存在");
		}
//		else {
//			dd.setDevId(disc.getDevId());
//			dd.setGroupId(disc.getGroupId());
//		}
		int req = deviceDiscinfoService.selectByCodIdAndDiscContrCode(dd.getCodId(),dd.getDiscContrCode());
		int num = deviceDiscinfoEntityMapper.selectByDiscId(dd.getDiscId());
		DeviceDiscinfoEntity disc = deviceDiscinfoEntityMapper.queryByDiscIdAndStateAndContrCode(0L,"0",dd.getDiscContrCode(),dd.getCodId());
		if(disc == null) {
			throw new FrmsException("该检测板未绑定中控器");
		}
		
		if(bandType==0) {
			if(req>0) {
				throw new FrmsException("端子控制器编码已绑定");
			}
			if(num>0) {
				throw new FrmsException("该熔纤盘已绑定检测板");
			}
			DeviceDiscinfoEntity delDisc = deviceDiscinfoService.qeuryByDiscIdAndState(dd.getDiscId(),"2");
			if(delDisc != null) {
				int dbdasac = deviceDiscinfoEntityMapper.deleteByPrimaryKey(dd.getDiscId());
				if(dbdasac < 1) {
					throw new FrmsException("绑定失败！");
				}
			}
			disc.setDiscId(dd.getDiscId());
			disc.setLastModifyTime(new Date());
			disc.setLastModifyUser(user.getUserId());
			disc.setGroupId(disc1.getGroupId());
			disc.setDiscContrState("0");
			return	deviceDiscinfoEntityMapper.updateByDiscIdAndStateAndContrCode(disc);
		}
		if(bandType==1) {
			if(req>0) {
				throw new FrmsException("端子控制器编码已绑定");
			}
			if(num<1) {
				throw new FrmsException("该熔纤盘未绑定检测板,无法重新绑定");
			}
			DeviceDiscinfoEntity ddebk = new DeviceDiscinfoEntity();
			DeviceDiscinfoEntity dde = deviceDiscinfoEntityMapper.selectByPrimaryKey(dd.getDiscId());
			
			disc.setDiscSeq(disc.getDiscContrCode());
			disc.setGroupId(disc.getCodId());
			
			disc.setDiscContrCode(dde.getDiscContrCode());
			disc.setDiscContrId(dde.getDiscContrId());
			disc.setCodId(dde.getCodId());
			disc.setDevId(dde.getDevId());
			disc.setLastModifyTime(new Date());
			disc.setLastModifyUser(user.getUserId());
			
			deviceDiscinfoEntityMapper.updateByCodeAndId(disc);
			
			dde.setLastModifyTime(new Date());
			dde.setLastModifyUser(user.getUserId());
			dde.setDiscContrCode(dd.getDiscContrCode());
			dde.setDiscContrId(dd.getDiscContrId());
			dde.setDiscContrState("0");
			dde.setCodId(dd.getCodId());
			return deviceDiscinfoEntityMapper.updateByPrimaryKeySelective(dde);
			

		}
//		dd.setOrgId(user.getOrgId());
//		dd.setDiscContrState("0");
//		//查询熔纤盘下是否绑定了已删除的端子控制器
//		DeviceDiscinfoEntity delDisc = deviceDiscinfoService.qeuryByDiscIdAndState(dd.getDiscId(),"2");
//		if(bandType==0 && delDisc != null) {
//			delDisc.setDiscContrState("0");
//			delDisc.setDiscContrCode(dd.getDiscContrCode());
//			delDisc.setLastModifyTime(new Date());
//			delDisc.setLastModifyUser(user.getUserId());
//			return deviceDiscinfoEntityMapper.updateByPrimaryKeySelective(delDisc);
//		}else if(bandType==0 && delDisc == null) {//绑定端子控制器
//			dd.setCreateTime(new Date());
//			dd.setCreateUser(user.getUserId());
//			return deviceDiscinfoEntityMapper.insertSelective(dd);
//		}else if(bandType==1){//重新绑定端子控制器
//			dd.setLastModifyTime(new Date());
//			dd.setLastModifyUser(user.getUserId());
//			return deviceDiscinfoEntityMapper.updateByPrimaryKeySelective(dd);
//		}else {
//			return 0;
//		}
		return 1;
	}
	
	/**
	 * 
	 * @Title: selectByCodIdAndDiscContrCode
	 * @Description: 查询同个中控器下，端子控制器编码是否已经绑定
	 * @param @param codId
	 * @param @param discContrCode
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 下午5:29:57
	 * @version V1.0
	 */
	@Override
	public int selectByCodIdAndDiscContrCode(Long codId, String discContrCode) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("codId", codId);
		map.put("discContrCode", discContrCode);
		return deviceDiscinfoEntityMapper.selectByCodIdAndDiscContrCode(map);
	}

	/**
	 * 设备上报日志记录
	* @Title: queryDeviceDisinfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param pb
	* @param @param deviceDiscinfoEntityBean
	* @param @param orgId
	* @param @return    入参
	* @return PageBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月16日 下午4:48:11 
	* @version V1.0
	 */
	@Override
	public PageBean queryDeviceDisinfo(PageBean pb, DeviceDiscinfoEntityBean deviceDiscinfoEntityBean, Long orgId) {
		Map<String,Object> map = new HashMap<String,Object>();
		pb.setPageSize(1000);
		map.put("orgId", orgId);
		map.put("pb", pb);
		map.put("deviceDiscinfoEntityBean", deviceDiscinfoEntityBean);
		pb.setList(deviceDiscinfoEntityMapper.queryDeviceDisinfoList(map));
		pb.setRows(deviceDiscinfoEntityMapper.queryDeviceDisinfoCount(map));
		if (pb.getList().size()>0 && pb.getList() !=null) {
			List<DeviceDiscinfoEntityBean> list = (List<DeviceDiscinfoEntityBean>) pb.getList();
			for (int i = 0; i < list.size(); i++) {
				List<String> lastReportDataList = new ArrayList<String>();
				DeviceDiscinfoEntityBean deviceDiscinfoEntity=list.get(i);
				char[] lastReportDatas=null;
				if (deviceDiscinfoEntity!=null && deviceDiscinfoEntity.getLastReportData()!=null&&!"".equals( deviceDiscinfoEntity.getLastReportData())) {
					 lastReportDatas = deviceDiscinfoEntity.getLastReportData().toCharArray();
				}
				if (lastReportDatas !=null &&lastReportDatas.length>0) {
					deviceDiscinfoEntity.setLastReportDataSize(lastReportDatas.length/2);
					for (int j = 0; j < lastReportDatas.length; j++) {
						char s = lastReportDatas[j];
						lastReportDataList.add(String.valueOf(s));
					}
				}
				deviceDiscinfoEntity.setLastReportDataList(lastReportDataList);
			}
		}
		return pb;
	}

	/**
	 * 所属设施名称查询
	* @Title: querydevNames 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param codId
	* @param @return    入参
	* @return List<String>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月17日 下午2:56:00 
	* @version V1.0
	 */
	@Override
	public List<DeviceDiscinfoEntityBean> querydevNames(Long codId) {
		return deviceDiscinfoEntityMapper.querydevNames(codId);
	}

	
	/**
	 * 端口占用情况
	* @Title: queryDeviceDisinfoFiberdisc 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param deviceDiscinfoEntityBean
	* @param @return    入参
	* @return List<DeviceDiscinfoEntityBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月18日 下午4:10:17 
	* @version V1.0
	 */
	@Override
	public List<DeviceDiscinfoEntityBean> queryDeviceDisinfoFiberdisc(DeviceDiscinfoEntityBean deviceDiscinfoEntityBean) {
		List<DeviceDiscinfoEntityBean> list=deviceDiscinfoEntityMapper.queryDeviceDisinfoFiberdisc(deviceDiscinfoEntityBean);
		if (list.size()>0 && list!=null) {
			for (int i = 0; i < list.size(); i++) {
				List<String> lastReportDataList = new ArrayList<String>();
				DeviceDiscinfoEntityBean deviceDiscinfoEntity=list.get(i);
				if (deviceDiscinfoEntity.getLastReportData() !=null &&!"".equals( deviceDiscinfoEntity.getLastReportData()) && deviceDiscinfoEntity!=null) {
					char[] lastReportDatas = deviceDiscinfoEntity.getLastReportData().toCharArray();
					deviceDiscinfoEntity.setLastReportDataSize(lastReportDatas.length/2);
					for (int j = 0; j < lastReportDatas.length; j++) {
						char s = lastReportDatas[j];
						lastReportDataList.add(String.valueOf(s));
					}
				}
				deviceDiscinfoEntity.setLastReportDataList(lastReportDataList);
			}
		}
		return list;
	}

	/**
	 * 端子占用情况查询
	* @Title: queryDeviceDisinfoByDisId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param discId
	* @param @return    入参
	* @return DeviceDiscinfoEntity    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月19日 下午4:23:03 
	* @version V1.0
	 */
	@Override
	public DeviceDiscinfoEntity queryDeviceDisinfoByDisId(Long discId) {
		return deviceDiscinfoEntityMapper.selectByDeviceDiscinfo(discId);
	}

	/**
	 *熔纤盘总数
	* @Title: queryByAreaCode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param areaCode1
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月8日 下午3:07:21 
	* @version V1.0
	 */
	@Override
	public int queryByAreaCode(String areaCode1,String areaCode2,Users user) {
			Map<String, Object> map = new HashMap<String,Object>();
			if (areaCode2==null ||"".equals(areaCode2)) {
				map.put("areaCode1", areaCode1);
			}else {
				map.put("areaCode1", areaCode2);
			}
			map.put("orgId", user.getOrgId());
		return deviceDiscinfoEntityMapper.queryByAreaCode(map);
	}

	/**
	 * 已安装熔纤盘数量
	* @Title: queryByAreaCodeY 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param areaCode1
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月8日 下午3:16:20 
	* @version V1.0
	 */
	@Override
	public int queryByAreaCodeY(String areaCode1,String areaCode2,Users user) {
			Map<String, Object> map = new HashMap<String,Object>();
			if (areaCode2==null ||"".equals(areaCode2)) {
				map.put("areaCode1", areaCode1);
			}else {
				map.put("areaCode1", areaCode2);
			}
			map.put("orgId", user.getOrgId());
		return deviceDiscinfoEntityMapper.queryByAreaCodeY(map);
	}

	/**
	 * 
	 * @Title: queryDifferentDats
	 * @Description: 差异数据统计列表查询
	 * @param @param pb
	 * @param @param ddeBean
	 * @param @param user
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年5月10日 上午11:02:13
	 * @version V1.0
	 */
	@Override
	public PageBean queryDifferentDats(PageBean pb, DeviceDiscinfoEntityBean ddeBean, Users user) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pb", pb);
		map.put("ddeBean", ddeBean);
		map.put("user", user);
		pb.setRows(deviceDiscinfoEntityMapper.queryDifferentDatsCount(map));
		List<DeviceDiscinfoEntityBean> ddeList = deviceDiscinfoEntityMapper.queryDifferentDats(map);
		if(ddeList!=null) {
			for (DeviceDiscinfoEntityBean deviceDiscinfo : ddeList) {
				if(deviceDiscinfo.getDevId() != null) {
					FiberdiscBean fb = fiberdiscService.queryFiberdiscOccupCount(deviceDiscinfo.getDevId());
					deviceDiscinfo.setFibPortNum(fb.getFibPortNum());
					deviceDiscinfo.setFibPortOccupyNum(fb.getFibPortOccupyNum());
					deviceDiscinfo.setFibPortFreeNum(fb.getFibPortFreeNum());
				}
			}
		}
		pb.setList(ddeList);
		return pb;
	}

	/**
	 * 查询端子差异数据统计
	* @Title: queryByOrgId 
	* @Description: 废弃	DZY改	2018年6月26日11:28:44
	* @param @param orgId
	* @param @return    入参
	* @return PageBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月24日 下午3:35:32 
	* @version V1.0
	 */
	@Override
	public PageBean queryByOrgId(Long orgId,PageBean pb) {
		return pb;
//		List<DifferentPortsBean> list1=fiberdiscMapper.queryOccupyConditionByOrgId(orgId,pb);
//		List<DifferentPortsBean> list2=deviceDiscinfoEntityMapper.queryReportOccupyByOrgId(orgId);
//		//未知,null的list集合
//		List<DifferentPortsBean> ilist = new ArrayList<DifferentPortsBean>();
//		//端口占用计算
//		if (list1.size()>0 && list1!=null) {
//			Iterator<DifferentPortsBean> itList = list1.iterator();
//			while(itList.hasNext()){
//				DifferentPortsBean differentPortsBean=itList.next();
//				if (differentPortsBean.getIsNotOccup()+differentPortsBean.getIsOccup()!=0) {
//					differentPortsBean.setOccupyBat(Double.valueOf(differentPortsBean.getIsOccup())/Double.valueOf((differentPortsBean.getIsNotOccup()+differentPortsBean.getIsOccup())));
//				}else {
//					differentPortsBean.setOccupyBat(0.0);
//				}
//				if (differentPortsBean.getAreaCode1()==null || "".equals(differentPortsBean.getAreaCode1())) {//未知的,归为一组计算
//					ilist.add(differentPortsBean);
//					itList.remove();
//				}
//			}
//		}
//		//计算未知的端口占用
//		if (ilist.size()>0 && ilist!=null) {
//			DifferentPortsBean differentPortsBean1 = new DifferentPortsBean();
//			differentPortsBean1.setAreaName("未知");
//			for (int j = 0; j < ilist.size(); j++) {
//				int isOccup =+ ilist.get(j).getIsOccup();
//				differentPortsBean1.setIsOccup(isOccup);
//				int isNotOccup =+ ilist.get(j).getIsNotOccup();
//				differentPortsBean1.setIsNotOccup(isNotOccup);
//				differentPortsBean1.setOccupyBat(Double.valueOf(differentPortsBean1.getIsOccup())/Double.valueOf((differentPortsBean1.getIsNotOccup()+differentPortsBean1.getIsOccup())));
//			}
//			list1.add(differentPortsBean1);
//		}
//		//端口差异数据计算
//		for (int i = 0; i < list1.size(); i++) {
//			DifferentPortsBean differentPortsBean1=list1.get(i);
//			if (list2.size()>0 && list2 !=null) {
//				for (int j = 0; j < list2.size(); j++) {
//					DifferentPortsBean differentPortsBean2=list2.get(j);
//					if (differentPortsBean1.getAreaCode1()!=null && !"".equals(differentPortsBean1.getAreaCode1()) && differentPortsBean1.getAreaCode1().equals(differentPortsBean2.getAreaCode1()) && differentPortsBean2.getAreaCode1()!=null && !"".equals(differentPortsBean2.getAreaCode1())) {
//						differentPortsBean1.setPortFreeNum(differentPortsBean2.getPortFreeNum());
//						differentPortsBean1.setPortOccupyNum(differentPortsBean2.getPortOccupyNum());
//						differentPortsBean1.setPortErrorNum(differentPortsBean2.getPortErrorNum());
//						if (differentPortsBean2.getPortFreeNum()+differentPortsBean2.getPortOccupyNum()!=0) {
//							differentPortsBean1.setErrorNumBat(Double.valueOf(differentPortsBean2.getPortErrorNum())/Double.valueOf(differentPortsBean2.getPortFreeNum()+differentPortsBean2.getPortOccupyNum()));
//						}else {
//							differentPortsBean1.setErrorNumBat(0.0);
//						}
//					}
//					if (differentPortsBean1.getAreaName().equals("未知")) {
//						DifferentPortsBean nullBean = deviceDiscinfoEntityMapper.queryAreaCodeIsNull(orgId);
//						if (nullBean !=null) {
//							differentPortsBean1.setPortFreeNum(nullBean.getPortFreeNum());
//							differentPortsBean1.setPortOccupyNum(nullBean.getPortOccupyNum());
//							differentPortsBean1.setPortErrorNum(nullBean.getPortErrorNum());
//							if (nullBean.getPortFreeNum()+nullBean.getPortOccupyNum()!=0) {
//								differentPortsBean1.setErrorNumBat(Double.valueOf(nullBean.getPortErrorNum())/Double.valueOf(nullBean.getPortFreeNum()+nullBean.getPortOccupyNum()));
//							}else {
//								differentPortsBean1.setErrorNumBat(0.0);
//							}
//						}
//					}
//				}
//			}
//		}
//		pb.setList(list1);
////		pb.setRows(fiberdiscMapper.queryOccupyCountsByOrgId(orgId,pb));
//		return pb;
	}

	/**
	 * 根据区域编码查询端子数据统计
	* @Title: queryDifferentTotalsByArea 
	* @Description: 废弃		DZY改	2018年6月26日13:21:35
	* @param @param orgId
	* @param @param pb
	* @param @param differentPortsBean
	* @param @return    入参
	* @return PageBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月25日 上午10:24:27 
	* @version V1.0
	 */
	@Override
	public PageBean queryDifferentTotalsByArea(Long orgId, PageBean pb, DifferentPortsBean differentPortsBean) {
//		List<DifferentPortsBean> list1=fiberdiscMapper.queryOccupyConditionByArea(orgId);
//		List<DifferentPortsBean> list2=deviceDiscinfoEntityMapper.queryReportOccupyByArea(orgId);
//		if (list1.size()>0 && list1!=null) {
//			for (int i = 0; i < list1.size(); i++) {
//				DifferentPortsBean differentPortsBean1 = list1.get(i);
//				if (differentPortsBean1.getIsNotOccup()+differentPortsBean1.getIsOccup()!=0) {
//					differentPortsBean1.setOccupyBat(Double.valueOf(differentPortsBean1.getIsOccup())/Double.valueOf((differentPortsBean1.getIsNotOccup()+differentPortsBean1.getIsOccup())));
//				}
//				if (list2.size()>0 && list2 !=null) {
//					for (int j = 0; j < list2.size(); j++) {
//						DifferentPortsBean differentPortsBean2=list2.get(j);
//						if (differentPortsBean1.getAreaCode1()!=null && !"".equals(differentPortsBean1.getAreaCode1()) && differentPortsBean2.getAreaCode1()!=null && !"".equals(differentPortsBean2.getAreaCode1()) && differentPortsBean1.getAreaCode1().equals(differentPortsBean2.getAreaCode1())) {
//							differentPortsBean1.setPortFreeNum(differentPortsBean2.getPortFreeNum());
//							differentPortsBean1.setPortOccupyNum(differentPortsBean2.getPortOccupyNum());
//							differentPortsBean1.setPortErrorNum(differentPortsBean2.getPortErrorNum());
//							if (differentPortsBean2.getPortFreeNum()+differentPortsBean2.getPortOccupyNum()!=0) {
//								differentPortsBean1.setErrorNumBat(Double.valueOf(differentPortsBean2.getPortErrorNum())/Double.valueOf(differentPortsBean2.getPortFreeNum()+differentPortsBean2.getPortOccupyNum()));
//							}
//						}
//					}
//				}
//			}
//		}
//		pb.setList(list1);
//		pb.setRows(fiberdiscMapper.queryOccupyCountsByArea(orgId,pb,differentPortsBean));
		return pb;
	}

	/**
	 * 根据盘ID.查询端子占用状态
	* @Title: queryDeviceDisinfoEntityByDisId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param discId
	* @param @return    入参
	* @return DeviceDiscinfoEntityMobile    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月31日 上午11:26:36 
	* @version V1.0
	 */
	@Override
	public List<DeviceDiscinfoEntityMobile> queryDeviceDisinfoEntityByDisId(Long discId) {
		List<DeviceDiscinfoEntityMobile> list =discinfoMapper.queryByDiscId(discId);
		char[] devicOccupys = null;
		if (list !=null && list.size()>0) {
			for (DeviceDiscinfoEntityMobile deviceDiscinfoEntityMobile : list) {
				DeviceDiscinfoEntity deviceDiscinfoEntity=deviceDiscinfoEntityMapper.selectByPrimaryKey(deviceDiscinfoEntityMobile.getDiscId());
				if (deviceDiscinfoEntity!= null && deviceDiscinfoEntity.getDiscContrCode() !=null) {
					deviceDiscinfoEntityMobile.setDiscContrCode(deviceDiscinfoEntity.getDiscContrState());
					deviceDiscinfoEntityMobile.setCodId(deviceDiscinfoEntity.getCodId());
					if (deviceDiscinfoEntity.getLastReportData()!=null && !"".equals(deviceDiscinfoEntity.getLastReportData())) {
						devicOccupys= deviceDiscinfoEntity.getLastReportData().toCharArray();
					}
				}
			}
			for (int i = 0; i < list.size(); i++) {
				DeviceDiscinfoEntityMobile deviceDiscinfoEntityMobile=list.get(i);
				if (devicOccupys!=null && devicOccupys.length>0) {
					for (int j = 0; j < devicOccupys.length; j++) {
						if (j==i) {
							deviceDiscinfoEntityMobile.setDevicOccupy(devicOccupys[j]+"");
						}
					}
				}
			}
			
		}
		
		return list;
	}

	/**
	 * 根据中控器ID查询机柜列表
	* @Title: queryCabinetsInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return List<FacilityCabinetsInfo>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月12日 下午5:13:57 
	* @version V1.0
	 */
	@Override
	public List<FacilityCabinetsInfo> queryCabinetsInfo(Long codId) {
		return deviceDiscinfoEntityMapper.queryCabinetsInfo(codId);
	}
	
	/**
	 * 根据盘ID.查询端子占用状态
	* @Title: queryopdDeviceDisinfoEntityByDisId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param discId
	* @param @return    入参
	* @return List<DeviceDiscinfoEntityMobile>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月22日 下午1:22:51 
	* @version V1.0
	 */
	@Override
	public List<DeviceDiscinfoEntityMobile> queryopdDeviceDisinfoEntityByDisId(Long discId) {
		List<DeviceDiscinfoEntityMobile> list =discinfoMapper.queryByDiscId(discId);
		char[] devicOccupys = null;
		if (list !=null && list.size()>0) {
			for (DeviceDiscinfoEntityMobile deviceDiscinfoEntityMobile : list) {
				DeviceDiscinfoEntity deviceDiscinfoEntity=deviceDiscinfoEntityMapper.selectByPrimaryKey(deviceDiscinfoEntityMobile.getDiscId());
				if (deviceDiscinfoEntity!= null && deviceDiscinfoEntity.getDiscContrCode() !=null) {
					deviceDiscinfoEntityMobile.setDiscContrCode(deviceDiscinfoEntity.getDiscContrState());
					deviceDiscinfoEntityMobile.setCodId(deviceDiscinfoEntity.getCodId());
					if (deviceDiscinfoEntity.getLastReportData()!=null && !"".equals(deviceDiscinfoEntity.getLastReportData())) {
						devicOccupys= deviceDiscinfoEntity.getLastReportData().toCharArray();
					}
				}
			}
			for (int i = 0; i < list.size(); i++) {
				DeviceDiscinfoEntityMobile deviceDiscinfoEntityMobile=list.get(i);
				if (devicOccupys!=null && devicOccupys.length>0) {
					for (int j = 0; j < devicOccupys.length; j++) {
						if (j==i) {
							deviceDiscinfoEntityMobile.setDevicOccupy(devicOccupys[j]+"");
						}
					}
				}
			}
			
		}
		
		return list;
	}

	/**
	 * 
	 * @Title: queryReportOccupyByOrgId
	 * @Description: 查询上报端子占用情况
	 * @param @param orgId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月26日 上午10:49:27
	 * @version V1.0
	 */
	@Override
	public List<DifferentPortsBean> queryReportOccupyByOrgId(Long orgId) {
		return deviceDiscinfoEntityMapper.queryReportOccupyByOrgId(orgId);
	}

	/**
	 * 
	 * @Title: queryAreaCodeIsNull
	 * @Description: 根据orgId查询区域编码为空的数据
	 * @param @param orgId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月26日 上午11:21:03
	 * @version V1.0
	 */
	@Override
	public DifferentPortsBean queryAreaCodeIsNull(Long orgId) {
		return deviceDiscinfoEntityMapper.queryAreaCodeIsNull(orgId);
	}

	/**
	 * 
	 * @Title: queryReportOccupyByArea
	 * @Description: 查询上报端子占用情况
	 * @param @param orgId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月26日 下午1:19:16
	 * @version V1.0
	 */
	@Override
	public List<DifferentPortsBean> queryReportOccupyByArea(Long orgId) {
		return deviceDiscinfoEntityMapper.queryReportOccupyByArea(orgId);
	}

	/**
	 * 
	 * @Title: deleteDeviceDiscinfo
	 * @Description: 删除端子控制器
	 * @param @param discId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月9日 下午4:01:37
	 * @version V1.0
	 */
	@Override
	public int deleteDeviceDiscinfo(Long discId, Users user) {
		int num = deviceDiscinfoEntityMapper.selectByDiscId(discId);
		if (num<1) {
			throw new FrmsException("该熔纤盘未绑定检测板,无法解绑");
		}
		DeviceDiscinfoEntity ddd = deviceDiscinfoEntityMapper.selectByPrimaryKey(discId);
		ddd.setLastModifyTime(new Date());
		ddd.setLastModifyUser(user.getUserId());
		ddd.setDiscContrState("2");
		deviceDiscinfoEntityMapper.updateByPrimaryKeySelective(ddd);
		
		ddd.setDiscId(0L);
		ddd.setDiscContrState("0");
		ddd.setGroupId(null);
		ddd.setDiscSeq(null);
		ddd.setPortErrorNum(null);
		ddd.setPortFreeNum(null);
		ddd.setPortNum(null);
		ddd.setPortOccupyNum(null);
		ddd.setLastReportData(null);
		return deviceDiscinfoEntityMapper.insert(ddd);
	}

	/**
	 * 
	 * @Title: qeuryByGroupIdAndState
	 * @Description: 根据groupId查询删除的端子控制器
	 * @param @param groupId
	 * @param @param string
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月10日 下午3:29:44
	 * @version V1.0
	 */
	@Override
	public DeviceDiscinfoEntity qeuryByGroupIdAndState(Long groupId, String discContrState) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("groupId", groupId);
		map.put("discContrState", discContrState);
		return deviceDiscinfoEntityMapper.qeuryByGroupIdAndState(map);
	}

	/**
	 * 
	 * @Title: qeuryByDiscIdAndState
	 * @Description: 根据discId查询已删除的端子控制器
	 * @param @param discId
	 * @param @param discContrState
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月11日 上午11:19:54
	 * @version V1.0
	 */
	@Override
	public DeviceDiscinfoEntity qeuryByDiscIdAndState(Long discId, String discContrState) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("discId", discId);
		map.put("discContrState", discContrState);
		return deviceDiscinfoEntityMapper.qeuryByDiscIdAndState(map);
	}


	/**
	 * 查询已绑定的设备列表
	* @Title: queryBindDiscNum 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param codId
	* @param @param devId
	* @param @return    入参
	* @return List<BindDiscNumMobile>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年9月1日 下午1:26:52 
	* @version V1.0
	 */
	@Override
	public List<BindDiscNumMobile> queryBindDiscNum(Long devId, Long orgId) {
		return deviceDiscinfoEntityMapper.queryBindDiscNum(devId,orgId);
	}
	
	/**
	 * 查询检测板的详细信息
	* @Title: queryDeviceDiscinfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param codId
	* @param @param devId
	* @param @param discContrId
	* @param @param orgId
	* @param @return    入参
	* @return List<DeviceDiscinfosMobile>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年9月1日 下午2:30:20 
	* @version V1.0
	 */
	@Override
	public DeviceDiscinfosMobile queryDeviceDiscinfo(Long codId, Long devId, String discContrId, Long orgId) {
		return deviceDiscinfoEntityMapper.queryDeviceDiscinfo(codId,devId,discContrId,orgId);
	}
	
	
	@Override
	public int bandContr(DeviceDiscinfoEntity dd, Users user, Long bandType) {
		int num=0;
		DeviceEntity dev = deviceEntityMapper.selectByPrimaryKey(dd.getCodId());
		if(dev == null) {
			throw new FrmsException("中控器不存在");
		}
		String contrCode = "";
		if(bandType==0) {
			contrCode = dd.getDiscContrId();
		}else {
			contrCode = HexUtil.encode(dd.getDiscContrId());
		}
		List<DeviceDiscinfoEntity> discList = deviceDiscinfoEntityMapper.selectByContrId(contrCode,user.getOrgId());
		
		//拆除
		if(bandType==0) {
			if(discList == null|| discList.size()==0) {
				throw new FrmsException("所属检测板不存在");
			}
			if(discList.size()>0) {
				for(DeviceDiscinfoEntity disc : discList) {
					if(disc.getDiscId()==0) {
						deviceDiscinfoEntityMapper.deleteByByDiscIdAndStateAndContrCode(disc);
					}else {
						disc.setLastModifyTime(new Date());
						disc.setLastModifyUser(user.getUserId());
						disc.setDiscContrState("2");
						deviceDiscinfoEntityMapper.updateByPrimaryKeySelective(disc);
					}
					
				}
			}
			return 2;
		}
		//绑定
		if(bandType==1) {
			if(discList != null && discList.size()>0) {
				throw new FrmsException("所属检测板已绑定！");
			}
			DeviceDiscinfoEntity dde = new DeviceDiscinfoEntity();
			dde.setDiscId(0L);
			dde.setCodId(dd.getCodId());
			dde.setDiscContrState("0");
			dde.setDevId(dd.getDevId());
			dde.setDiscContrId(contrCode);
			dde.setCreateTime(new Date());
			dde.setCreateUser(user.getUserId());
			dde.setOrgId(user.getOrgId());
			for(int i=1;i<5;i++) {
				dde.setDiscContrCode(contrCode+HexUtil.encode(""+i));
				deviceDiscinfoEntityMapper.insertSelective(dde);
			}
			return 1;
		}
		return num;
	}
}
