package com.ycnet.frms.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.FacilityAccess;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.FacilityAccessMapper;
import com.ycnet.frms.mapper.FacilityMapper;
import com.ycnet.frms.mapper.UsersMapper;
import com.ycnet.frms.service.FacilityAccessService;
import com.ycnet.frms.vo.AccessConditionBean;
import com.ycnet.frms.vo.FacilityAccessBean;

@Service("facilityAccessService")
public class FacilityAccessServiceImpl implements FacilityAccessService{
	@Resource(name="facilityAccessMapper")
	private FacilityAccessMapper facilityAccessMapper;
	@Resource(name="usersMapper")
	private UsersMapper usersMapper;
	
	@Resource(name="facilityMapper")
	private FacilityMapper facilityMapper;
	
	@Override
	public int save(FacilityAccess access) {
		if(access.getAccessId()!=null){
			return facilityAccessMapper.updateByPrimaryKeySelective(access);
		}else
			return facilityAccessMapper.insertSelective(access);
	}

	@Override
	public FacilityAccess selectById(Long id) {
		// TODO Auto-generated method stub
		return facilityAccessMapper.selectByPrimaryKey(id);
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return facilityAccessMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<FacilityAccess> queryList(FacilityAccess fa, PageBean pb,Users user) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("fa", fa);
		map.put("pb", pb);
		map.put("user", user);
		return facilityAccessMapper.queryListByMap(map);
	}

	@Override
	public int queryCountByList(FacilityAccess fa,Users user) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("fa", fa);
		map.put("user", user);
		return facilityAccessMapper.queryCountByMap(map);
	}
	
	@Override
	public List<FacilityAccessBean> queryListByCondition(AccessConditionBean cb,
			PageBean pb,Users user) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cb", cb);
		map.put("pb", pb);
		map.put("user", user);
		List<FacilityAccessBean> faccessList=facilityAccessMapper.queryListByCondition(map);
		if(faccessList!=null && faccessList.size()>0) {
			for(FacilityAccessBean fa:faccessList) {
				if(fa.getAccessUserId()!=null) {
					Users users=usersMapper.selectByPrimaryKey(fa.getAccessUserId());
					if(users!=null) {
						fa.setAccessUserName(users.getUserName());
					}
				}
			}
		}
		return faccessList;
	}
	
	@Override
	public int queryCountByCondition(AccessConditionBean cb,Users user) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cb", cb);
		map.put("user", user);
		return facilityAccessMapper.queryCountByCondition(map);
	}

	/**
	 * 
	* @Title: queryListByConditionExport 
	* @Description: 导出查询 
	* @param @param cb
	* @param @param pb
	* @param @param user
	* @param @return    
	* @return List<FacilityAccessBean>
	* @author liucanghai 
	* @throws
	* @date 2018年2月22日 上午10:01:32 
	* @version V1.0
	 */
	@Override
	public List<FacilityAccessBean> queryListByConditionExport(AccessConditionBean cb, PageBean pb, Users user) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cb", cb);
		pb = null;
		map.put("pb", pb);
		map.put("user", user);
		List<FacilityAccessBean> faccessList=facilityAccessMapper.queryListByCondition(map);
		if(faccessList!=null && faccessList.size()>0) {
			for(FacilityAccessBean fa:faccessList) {
				if(fa.getAccessUserId()!=null) {
					Users users=usersMapper.selectByPrimaryKey(fa.getAccessUserId());
					if(users!=null) {
						fa.setAccessUserName(users.getUserName());
					}
				}
				String devNames="";
				if(!"null".equals(fa.getDevIds())) {
					String[] devIds = fa.getDevIds().split(",");
					for (int i = 0; i < devIds.length; i++) {
						Facility f = facilityMapper.selectByPrimaryKey(Long.valueOf(devIds[i]));
						devNames+=f.getDevName()+"（"+f.getDevCode()+"）"+",";
					}
					if(!"".equals(devNames)) {
						fa.setDevIds(devNames.substring(0,devNames.length()-1));//设施名称
					}
				}else {
					fa.setDevIds("");
				}
				if(fa.getAccessTime()!=null) {//授权时间
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
					 String dateString = formatter.format(fa.getAccessTime());
					 fa.setAccTime(dateString);
				}
				
				if(fa.getValidateTime()!=null) {//授权结束时间
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
					 String dateString = formatter.format(fa.getValidateTime());
					 fa.setAccTimeEnd(dateString);
				}
				if(fa.getValidateStatus()!=null) {
					fa.setValidateStatus("0".equals(fa.getValidateStatus())?"未开启":"已开启");
				}
			}
		}
		return faccessList;
	}

	/**
	 * 
	 * @Title: queryAccessCountByMac
	 * @Description: 根据条件查询是否设施是否授权
	 * @param @param accessConditionBean
	 * @param @param user
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年5月2日 上午10:33:30
	 * @version V1.0
	 */
	@Override
	public int queryAccessCountByMac(AccessConditionBean ac, Users user) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ac", ac);
		map.put("user", user);
		return facilityAccessMapper.queryAccessCountByMac(map);
	}

	/**
	 * 
	 * @Title: queryWhetherAccess
	 * @Description: 查询设施是否授权
	 * @param @param devId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月24日 下午3:26:40
	 * @version V1.0
	 */
	@Override
	public FacilityAccess queryWhetherAccess(String devId) {
		return facilityAccessMapper.queryWhetherAccess(devId);
	}

	/**
	 * 根据imei,查询授权时间
	* @Title: queryFacilityAccessByImei 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param imei    入参
	* @return void    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月28日 下午6:06:57 
	* @version V1.0
	 */
	@Override
	public FacilityAccess queryFacilityAccessByImei(String imei) {
		return facilityAccessMapper.queryFacilityAccessByImei(imei);
	}
}
