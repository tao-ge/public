package com.ycnet.frms.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.core.Constants;
import com.ycnet.core.FrmsException;
import com.ycnet.core.util.LatLng;
import com.ycnet.core.util.Map_Util;
import com.ycnet.core.util.PageBean;
import com.ycnet.core.util.StringUtils;
import com.ycnet.frms.bean.FacilityEntity;
import com.ycnet.frms.bean.FacilityImgEntity;
import com.ycnet.frms.bean.FiberdiscEntity;
import com.ycnet.frms.bean.PositionAreas;
import com.ycnet.frms.mapper.FacilityEntityMapper;
import com.ycnet.frms.mapper.FacilityImgEntityMapper;
import com.ycnet.frms.mapper.FiberdiscEntityMapper;
import com.ycnet.frms.mapper.PositionAreasMapper;
import com.ycnet.frms.service.FacilityEntityService;
import com.ycnet.frms.service.SiteCodeService;
import com.ycnet.frms.vo.FacilityConditionBean;
import com.ycnet.frms.vo.FacilityVoBeanXY;
import com.ycnet.frms.vo.mobile.FacilityListOpd;
import com.ycnet.frms.vo.mobile.FaciltyDeviceDtoBean;



@Service("facilityEntityService")
public class FacilityEntityServiceImpl implements FacilityEntityService{
	
	@Resource(name="facilityEntityMapper")
	private FacilityEntityMapper facilityEntityMapper;
	
	@Resource(name="facilityImgEntityMapper")
	private FacilityImgEntityMapper facilityImgEntityMapper;
	
	@Resource(name="siteCodeService")
	private SiteCodeService siteCodeService;
	
	@Resource(name="fiberdiscEntityMapper")
	private FiberdiscEntityMapper fiberdiscEntityMapper;
	
	@Resource(name="positionAreasMapper")
	private PositionAreasMapper positionAreasMapper;

	/**
	 * 
	 * @Title: saveFacsility
	 * @Description: 保存设施(添加/修改)
	 * @param @param fa
	 * @param @param list
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月12日 下午3:42:29
	 * @version V1.0
	 */
	@Override
	@Transactional
	public Long saveFacsility(FacilityVoBeanXY fvb, List<FacilityImgEntity> images) {
		FacilityEntity facility =  new FacilityEntity();
		facility.setLastModifyUser(fvb.getLastModifyUser());
		facility.setLastModifyTime(fvb.getLastModifyTime());
		facility.setDevAddr(fvb.getDevAddr());
		facility.setAreaCode1(fvb.getAreaCode1());
		facility.setAreaCode2(fvb.getAreaCode2());
		facility.setCodeA(fvb.getCodeA());
		facility.setDevStateA(fvb.getDevStateA());
		facility.setCodeZ(fvb.getCodeZ());
		facility.setDevStateZ(fvb.getDevStateZ());
		facility.setCheckType(fvb.getCheckType());
		facility.setCheckUserId(fvb.getCheckUserId());
		facility.setCompleteDate(fvb.getCompleteDate());
		facility.setCreateTime(fvb.getCreateTime());
		facility.setCreateUser(fvb.getCreateUser());
		facility.setDevDesc(fvb.getDevDesc());
		facility.setDevId(fvb.getDevId());
		facility.setDevImei(fvb.getDevImei());
		facility.setDevKind(fvb.getDevKind());
		facility.setDevMac(fvb.getDevMac());
		facility.setDevMarking(fvb.getDevMarking());
		facility.setDevModel(fvb.getDevModel());
		facility.setDevName(fvb.getDevName());
		facility.setDevType(fvb.getDevType());
		facility.setFiberDiscNum(fvb.getFiberDiscNum());
		facility.setFlag(fvb.getFlag());
		facility.setIsbefwell(fvb.getIsbefwell());
		facility.setIsTranslated(fvb.getIsTranslated());
		facility.setZgDevName(fvb.getZgDevName());
		facility.setProId(fvb.getProId());
		facility.setPdevId(fvb.getPdevId());
		facility.setOrgId(fvb.getOrgId());
		facility.setImgType(fvb.getImgType());
		facility.setSite(fvb.getSite());
		facility.setSurveyResult(fvb.getSurveyResult());
		facility.setRoom(fvb.getRoom());
		facility.setDevCode(fvb.getDevCode());
		facility.setDevState(fvb.getDevState());
		facility.setLatitude(fvb.getLatitude());
		facility.setLongitude(fvb.getLongitude());
		if(fvb.getBaiduX()!=null && !"".equals(fvb.getBaiduX())) {
			facility.setBaiduX(BigDecimal.valueOf(Double.parseDouble(fvb.getBaiduX())));
		}
		if(fvb.getBaiduY()!=null && !"".equals(fvb.getBaiduY())) {
			facility.setBaiduY(BigDecimal.valueOf(Double.parseDouble(fvb.getBaiduY())));
		}
		
		Long ret = 0L;
		if((facility.getAreaCode1()==null||"".equals(facility.getAreaCode1()))&&facility.getPdevId()!=null){
			
			FacilityEntity parent = facilityEntityMapper.selectByPrimaryKey(facility.getPdevId());
			if(parent!=null)
				facility.setAreaCode1(parent.getAreaCode1());
		}
		if(facility.getDevId()==null){
			FacilityEntity faDevNames=facilityEntityMapper.queryRepetitionDevName(facility);
			if(faDevNames !=null ) {
				throw new FrmsException("设施名称【"+facility.getDevName()+"】已存在！");
			}
			
			if(Constants.FACILITTYPE.OBD.toString().equals(facility.getDevType()))
			{
				if ((facility.getLatitude() !=null && !"".equals(facility.getLatitude())) && ("".equals(fvb.getBaiduX()) || fvb.getBaiduX()==null)) {
					LatLng LatLng = Map_Util.Gps84_To_bd09(Double.valueOf(facility.getLatitude()), Double.valueOf(facility.getLongitude()));
					facility.setBaiduX(BigDecimal.valueOf(LatLng.getLongitude()));
					facility.setBaiduY(BigDecimal.valueOf(LatLng.getLatitude()));
				}
//				addObd(facility);
			}
			else
			{
				facility.setDevCode(siteCodeService.getNewDevCode());
				facility.setDevState("2");//2 新增,dzy修改
				//fl 修改增加百度 X Y 坐标
				if ((facility.getLatitude() !=null && !"".equals(facility.getLatitude())) && ("".equals(fvb.getBaiduX()) || fvb.getBaiduX()==null)) {
					LatLng LatLng = Map_Util.Gps84_To_bd09(Double.valueOf(facility.getLatitude()), Double.valueOf(facility.getLongitude()));
					facility.setBaiduX(BigDecimal.valueOf(LatLng.getLongitude()));
					facility.setBaiduY(BigDecimal.valueOf(LatLng.getLatitude()));
				}
				
				facilityEntityMapper.insertSelective(facility);
			}
			ret =facility.getDevId();
			for(FacilityImgEntity img:images){
				img.setDevCode(facility.getDevCode());
				img.setDevId(facility.getDevId());
				facilityImgEntityMapper.insertSelective(img);
			}
//			addConnecter(facility);
		}
		else{
			//fl 修改增加百度 X Y 坐标
			if (facility.getLatitude() !=null && !"".equals(facility.getLatitude()) && facility.getLongitude() !=null && !"".equals(facility.getLongitude())) {
				LatLng LatLng = Map_Util.Gps84_To_bd09(Double.valueOf(facility.getLatitude()), Double.valueOf(facility.getLongitude()));
				facility.setBaiduX(BigDecimal.valueOf(LatLng.getLongitude()));
				facility.setBaiduY(BigDecimal.valueOf(LatLng.getLatitude()));
			}
			
			//验证设施名并修改光缆段名称
			validateDevName(facility);
			
			facilityEntityMapper.updateByPrimaryKeySelective(facility);
			FacilityEntity f = facilityEntityMapper.selectByPrimaryKey(facility.getDevId());
			for(FacilityImgEntity img:images){
				img.setDevCode(f.getDevCode());
				img.setDevId(f.getDevId());
				facilityImgEntityMapper.insertSelective(img);
			}
			ret = facility.getDevId();
		}
		return ret;
	}

	/**
	 * 
	 * @Title: validateDevName
	 * @Description: 验证修改设施名
	 * @param @param facility 
	 * @return void 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月12日 下午5:14:41
	 * @version V1.0
	 */
	private void validateDevName(FacilityEntity facility) {
		if(facility==null || facility.getDevId() ==null ){
			return;
		}
		if ("".equals(facility.getDevName())) {
			facility.setDevName(null);
		}
		if(facility.getDevName()!=null &&!"".equals(facility.getDevName())) {
			FacilityEntity fa = facilityEntityMapper.selectByPrimaryKey(facility.getDevId());
			if(!facility.getDevName().equals(fa.getDevName())) {
				FacilityEntity faDevNames=facilityEntityMapper.queryRepetitionDevName(facility);
				if(faDevNames !=null) {
					throw new FrmsException("设施名称【"+facility.getDevName()+"】已存在！");
				}
			}
		}
	}

	/**
	 * 设施查询
	* @Title: queryFacilityByConditions 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param facility
	* @param @param pb
	* @param @param orgId
	* @param @return    入参
	* @return List<FacilityEntity>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月12日 下午3:52:48 
	* @version V1.0
	 */
	@Override
	public List<FaciltyDeviceDtoBean> queryFacilityByConditions(FacilityConditionBean facility, PageBean pb, Long orgId) {
		if (facility.getDevType()==null || "".equals(facility.getDevType())) {
			facility.setDevTypes(new String[0]);
		}else {
			String [] devTypes=facility.getDevType().split(",");
			facility.setDevTypes(devTypes);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("facility", facility);
		map.put("pb", pb);
		map.put("orgId", orgId);
		return facilityEntityMapper.queryFacilityByConditions(map);
	}

	/**
	 * 根据设施ID,查询设施信息
	* @Title: queryFacilityByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return FacilityEntity    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月12日 下午4:30:50 
	* @version V1.0
	 */
	@Override
	public FaciltyDeviceDtoBean queryFacilityByDevId(Long devId) {
		FaciltyDeviceDtoBean faciltyDeviceDtoBean= facilityEntityMapper.queryFacilityByDevId(devId);
		//查询返回自己及所属的父级区域
		if(faciltyDeviceDtoBean != null) {
			if (faciltyDeviceDtoBean.getAreaCode2() != null && !"".equals(faciltyDeviceDtoBean.getAreaCode2().trim())) {
				String positionAreas = "";
				String positionAreaNames = "";
				boolean flag = true;
				String areaCode2 = faciltyDeviceDtoBean.getAreaCode2();
				while (flag) {
					//查询区域
					PositionAreas position = positionAreasMapper.selectByPrimaryKey(areaCode2);
					if(position != null) {
						//拼接:  "省,市,区,镇";如果该设施所属的区域为市,则返回结果为"省,市"
						positionAreas = position.getAreaCode() + "," + positionAreas;
						positionAreaNames = position.getAreaName() + "," + positionAreaNames;
						if(position.getParentAreaCode() != null && !"".equals(position.getParentAreaCode().trim())) {
							areaCode2 = position.getParentAreaCode();
						}else {
							flag = false;
						}
					}else {
						flag = false;
					}
				}
				faciltyDeviceDtoBean.setPositionAreas(positionAreas);
				faciltyDeviceDtoBean.setAreaName(positionAreaNames);
			}
			List<FacilityImgEntity> list=facilityImgEntityMapper.selectByDevId(devId);
			if (list !=null && list.size()>0) {
				faciltyDeviceDtoBean.setImgList(list);
			}
		}
		return faciltyDeviceDtoBean;
	}
	
	@Override
	public Map<String, Object> findFacilityByDevId(Long devId) {
		return facilityEntityMapper.findFacilityByDevId(devId);
	}

	
	@Override
	public int genFiberdisc(Long devId, String side, Integer fiberDiscNum,Integer portNum,Integer fromIndex)
	{
		return genFiberdisc( devId,  side,  fiberDiscNum, portNum, fromIndex,null);
	}
	
	@Override
	public int genFiberdisc(Long devId, String side, Integer fiberDiscNum,Integer portNum,Integer fromRowIndex,Integer fromColIndex){
		return genFiberdisc(devId,side,fiberDiscNum,portNum,fromRowIndex,fromColIndex,null);
	}
	
	@Override
	public int genFiberdisc(Long devId, String side, Integer fiberDiscNum,Integer portNum,Integer fromRowIndex,Integer fromColIndex,String  discModel){
		int ret = 0;
		discModel=discModel==null||"".equals(discModel.replaceAll(" ",""))?null:discModel.replaceAll(" ","").toUpperCase();
		fromRowIndex = fromRowIndex==null?1:fromRowIndex;
		fromColIndex =fromColIndex==null?1:fromColIndex;
		if(fromColIndex.intValue()!=0&&fromColIndex.intValue()!=1)
		{
			throw new FrmsException("端口编码起始位置不正确！");
		}
		
		if(fiberDiscNum==null||(fiberDiscNum<=0))
			return ret;
		FacilityEntity f = facilityEntityMapper.selectByPrimaryKey(devId);
		if(f==null)
			return ret;
		String code =f.getDevCode();
		for(long row =fromRowIndex;row <=fiberDiscNum +(fromRowIndex -1);row ++)
		{
			for(long col = 1;col<=portNum;col++)
			{
				FiberdiscEntity disc = new FiberdiscEntity();
				disc.setDevId(devId);
				disc.setDiscRowNo(row);
				disc.setDiscColNo(col);
				disc.setSide(side);
				disc.setDiscCode(StringUtils.genPoint(code,side,discModel,row));
				disc.setPort01(StringUtils.genPoint(code,side,discModel,row,fromColIndex==1?col:(col - 1)));
				disc.setCreateTime(new Date());
				ret = fiberdiscEntityMapper.insertSelective(disc);
			}
		}
		return ret;
		
	}

	/**
	 * 
	 * @Title: selectByPrimaryKey
	 * @Description: 根据ID查询
	 * @param @param devId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 上午10:42:01
	 * @version V1.0
	 */
	@Override
	public FacilityEntity selectByPrimaryKey(Long devId) {
		return facilityEntityMapper.selectByPrimaryKey(devId);
	}

	/**
	 * 
	 * @Title: selectById
	 * @Description: 根据ID查询
	 * @param @param devId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 上午10:49:48
	 * @version V1.0
	 */
	@Override
	public FacilityEntity selectById(Long devId) {
		return facilityEntityMapper.selectByPrimaryKey(devId);
	}

	/**
	 * 根据条件查询设施列表
	* @Title: queryopdFacilityDeviceByConditions 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param facility
	* @param @param pb
	* @param @param orgId
	* @param @return    入参
	* @return List<FaciltyDeviceDtoBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月20日 下午4:35:28 
	* @version V1.0
	 */
	@Override
	public List<FacilityListOpd> queryopdFacilityDeviceByConditions(FacilityConditionBean facility, PageBean pb,
			Long orgId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("facility", facility);
		map.put("pb", pb);
		map.put("orgId", orgId);
		return facilityEntityMapper.queryopdFacilityDeviceByConditions(map);
	}

	/**
	 * 根据设施ID,查询设施详情
	* @Title: queryopdFacilityDeviceByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return FaciltyDeviceDtoBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月20日 下午4:50:46 
	* @version V1.0
	 */
	@Override
	public FaciltyDeviceDtoBean queryopdFacilityDeviceByDevId(Long devId) {

		FaciltyDeviceDtoBean faciltyDeviceDtoBean= facilityEntityMapper.queryFacilityByDevId(devId);
		//查询返回自己及所属的父级区域
		if(faciltyDeviceDtoBean != null && faciltyDeviceDtoBean.getAreaCode2() != null && !"".equals(faciltyDeviceDtoBean.getAreaCode2().trim())) {
			String positionAreas = "";
			boolean flag = true;
			String areaCode2 = faciltyDeviceDtoBean.getAreaCode2();
			while (flag) {
				//查询区域
				PositionAreas position = positionAreasMapper.selectByPrimaryKey(areaCode2);
				if(position != null) {
					//拼接:  "省,市,区,镇";如果该设施所属的区域为市,则返回结果为"省,市"
					positionAreas = position.getAreaCode() + "," + positionAreas;
					if(position.getParentAreaCode() != null && !"".equals(position.getParentAreaCode().trim())) {
						areaCode2 = position.getParentAreaCode();
					}else {
						flag = false;
					}
				}else {
					flag = false;
				}
			}
			faciltyDeviceDtoBean.setPositionAreas(positionAreas);
		}
		List<FacilityImgEntity> list=facilityImgEntityMapper.selectByDevId(devId);
		if (list !=null && list.size()>0) {
			faciltyDeviceDtoBean.setImgList(list);
		}
		return faciltyDeviceDtoBean;
	}
}
  
    