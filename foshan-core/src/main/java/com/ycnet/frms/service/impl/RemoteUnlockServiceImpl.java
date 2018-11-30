package com.ycnet.frms.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonObject;
import com.ycnet.core.HttpUtil;
import com.ycnet.core.HttpsUtil;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.DeviceReg;
import com.ycnet.frms.bean.Organizition;
import com.ycnet.frms.bean.RemoteUnlock;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.RemoteUnlockMapper;
import com.ycnet.frms.mapper.UsersMapper;
import com.ycnet.frms.service.DeviceRegService;
import com.ycnet.frms.service.OrganizitionService;
import com.ycnet.frms.service.RemoteUnlockService;
import com.ycnet.frms.vo.RemoteUnlockBean;

import net.sf.json.JSONObject;

@Service("remoteUnlockService")
@Transactional
public class RemoteUnlockServiceImpl implements RemoteUnlockService{
	
	@Resource(name="remoteUnlockMapper")
	private  RemoteUnlockMapper remoteUnlockMapper;
	
	@Resource(name="usersMapper")
	private UsersMapper usersMapper;
	
	@Resource(name="organizitionService")
	private OrganizitionService organizitionService;
	
	@Resource(name="deviceRegService")
	private DeviceRegService deviceRegService;

	/**
	 * 
	 * @Title: queryRemoteUnlockApplyList
	 * @Description: 远程开锁授权列表
	 * @param @param ru
	 * @param @param pb
	 * @param @param user
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年2月5日 下午2:05:34
	 * @version V1.0
	 */
	@Override
	public PageBean queryRemoteUnlockApplyList(RemoteUnlock ru, PageBean pb, Users user) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ru", ru);
		map.put("user", user);
		map.put("pb", pb);
		pb.setRows(remoteUnlockMapper.queryCountRemoteUnlockApplyList(map));
		List<RemoteUnlock> ruList = remoteUnlockMapper.queryRemoteUnlockApplyList(map);
		pb.setList(ruList);
		return pb;
	}

	/**
	 * 
	 * @Title: save
	 * @Description: 添加远程开锁申请
	 * @param @param ru
	 * @param @param user
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年2月6日 下午2:19:57
	 * @version V1.0
	 */
	@Override
	public int save(RemoteUnlock ru, Users user) {
		int req = 0;
		Organizition org = organizitionService.selectById(user.getOrgId());
		ru.setOperatTime(new Date());
		ru.setOperatUser(user.getUserId());
		ru.setOrgId(user.getOrgId());
		req = remoteUnlockMapper.insertSelective(ru);
		req=0;//初始化
		//根据devId查询设备
		String did = "";
		String result = "";
		String url = "";
		//调用华为平台接口，进行远程开锁
		HttpsUtil https = new HttpsUtil();
		List<DeviceReg> drList = deviceRegService.queryByDevId(ru.getDevId(),user.getOrgId());
		RemoteUnlockBean condition = new RemoteUnlockBean();
		if(drList!=null && drList.size()>0) {
			for (int i = 0; i < drList.size(); i++) {
				//拼接did：mid+pid+imei
				did=org.getMid()+org.getPid()+drList.get(i).getDevImei();
				try {
					//如果为移创转发平台
					if("01".equals(drList.get(i).getDevPlatform())) {
						//http
						url = "http://"+org.getIp()+":8080/device/unlockApply.htm";
					}else {
						//https
						url = "https://116.62.37.111:8888/device/remotecommand";
//						url = "http://116.62.37.111:9999/device/remotecommand";
						https.initSSLConfigForTwoWay();//验证证书
					}
					condition.setDid(did);
//					condition.setExpire_time(ru.getApplyTime().multiply(new BigDecimal(60)).toString());//BigDecimal乘法，转换成秒
					condition.setExpire_time(String.valueOf(ru.getApplyTime()*60));//转换成秒
					condition.setPlatform(drList.get(i).getDevPlatform());
					condition.setUnlockingType("1");//远程开锁
					condition.setUserId(user.getUserId().toString());
					JSONObject json = JSONObject.fromObject(condition);//封装为json
//			System.out.println("~~~~~~~"+json.toString());
					result = https.doPostJsonForString(url, json.toString());
					JSONObject jsonObj = JSONObject.fromObject(result);
					if(!jsonObj.containsKey("result")) {
						req = 0;
					}else {
						if("SUCCESS".equals(jsonObj.getString("result"))) {
							req = 1;
//					System.out.println("~~~~SUCCESS");
						}
					}
				} catch (Exception e) {
					//TODOAuto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return req;
	}
	
	/**
	 * 
	 * @Title: listToString
	 * @Description: List转为String
	 * @param @param list
	 * @param @return 
	 * @return String 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年2月7日 下午2:43:13
	 * @version V1.0
	 */
	public static String listToString(List<String> list){
	   StringBuilder result = new StringBuilder();
	   boolean first = true;
	   //第一个前面不拼接","
	   for(String string :list) {
	      if(first) {
	         first=false;
	      }else{
	         result.append(",");
	      }
	      result.append(string);
	   }
	   return result.toString();
	}

	/**
	 * 
	 * @Title: validateApply
	 * @Description: 验证该设施是否已经申请并在其申请时间内
	 * @param @param devId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年3月19日 上午11:43:21
	 * @version V1.0
	 */
	@Override
	public int validateApply(Long devId) {
		int req = 0;
		RemoteUnlock ru = remoteUnlockMapper.queryLastByDevId(devId);
		if(ru!=null) {
			int applyTime = ru.getApplyTime();
			//如果该设施已经申请远程开锁，并在其申请时间内
			long cha = (System.currentTimeMillis()-ru.getOperatTime().getTime())/(1000*60);
			if(cha<applyTime) {
				req = 0;
			}else {
				req = 1;
			}
		}else {
			req = 1;
		}
		return req;
	}
}
  
    