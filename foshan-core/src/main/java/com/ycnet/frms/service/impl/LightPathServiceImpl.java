package com.ycnet.frms.service.impl;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.frms.bean.Organizition;
import com.ycnet.frms.bean.TempLightPath;
import com.ycnet.frms.mapper.OrganizitionMapper;
import com.ycnet.frms.mapper.RouteMapper;
import com.ycnet.frms.mapper.TempLightPathMapper;
import com.ycnet.frms.service.LightPathService;


@Service("lightPathService")
@Transactional
public class LightPathServiceImpl implements LightPathService{

	@Resource(name ="routeMapper")
	private RouteMapper routeMapper;
	
	@Resource(name ="tempLightPathMapper")
	private TempLightPathMapper tempLightPathMapper;
	
	@Resource(name ="organizitionMapper")
	private OrganizitionMapper organizitionMapper;
	
	//@Scheduled(cron = "0 30 1 ? * *")
	//@Scheduled(cron = "0 29 14 ? * *")
	@SuppressWarnings("deprecation")
	public void exportBoxToLightPath()
	{
		try {
			int ret = 0;
			int hour = 0;//当前时间
			List<Organizition> orgList = organizitionMapper.OrgIdList();        
			if(orgList != null) {
				
				List<TempLightPath> tempList = tempLightPathMapper.selectMaxTime(null);
				Long orgId = 0L;
				if(tempList != null) {
					orgId = tempList.get(0).getOrgId();
				}
				for(Organizition org : orgList) {
					if(orgId > org.getOrgId()) {
						continue;
					}
					Calendar rightNow = Calendar.getInstance();
					hour = rightNow.get(Calendar.HOUR_OF_DAY);
//					if(hour<20 && hour>4) {
//						return;
//					}
					List<TempLightPath> list = routeMapper.queryAllLightPathInfo(org.getOrgId());
					if(list != null) {
						ret = tempLightPathMapper.deleteByPrimaryKey(org.getOrgId());
						for(TempLightPath lightPath : list) {
							ret = tempLightPathMapper.insertSelective(lightPath);
							if(ret<1) {
								throw new Exception("保存失败");
							}
						}
					}
					rightNow = null;
				}
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		
	}
	
	
	
	public static void main(String[] args) {
		Calendar rightNow = Calendar.getInstance();
		System.err.println(rightNow.get(Calendar.HOUR_OF_DAY));
	}



	@Override
	public int save(List<TempLightPath> lpList,String devType,Long orgId,String areaCode1) {
		// TODO Auto-generated method stub
		int ret = 0;
		try {
			tempLightPathMapper.deleteByParam(devType,orgId,areaCode1);
			for(TempLightPath lightPath : lpList) {
				ret = tempLightPathMapper.insertSelective(lightPath);
				if(ret<1) {
					throw new Exception("保存失败");
				}	
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ret;
	}



	@Override
	public String selectMinTimeByParam(String devType, Long orgId, String areaCode1) {
		// TODO Auto-generated method stub
		return tempLightPathMapper.selectMinTimeByParam(devType,orgId,areaCode1);
	}
}
