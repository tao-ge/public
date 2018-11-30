package com.ycnet.frms.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.ss.formula.functions.Dec2Bin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ycnet.core.UDPClient;
import com.ycnet.core.UDPServer;
import com.ycnet.core.util.HexUtil;
import com.ycnet.core.util.IPUtil;
import com.ycnet.frms.bean.DeviceAlarmEntity;
import com.ycnet.frms.bean.DeviceLockEntity;
import com.ycnet.frms.bean.DeviceLockStatusEntity;
import com.ycnet.frms.bean.DeviceLockSwitch;
import com.ycnet.frms.bean.DeviceReg;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.DeviceAlarmEntityMapper;
import com.ycnet.frms.mapper.DeviceAlarmMapper;
import com.ycnet.frms.mapper.DeviceEntityMapper;
import com.ycnet.frms.mapper.DeviceLockEntityMapper;
import com.ycnet.frms.mapper.DeviceLockStatusEntityMapper;
import com.ycnet.frms.mapper.DeviceLockSwitchMapper;
import com.ycnet.frms.mapper.HardwareUpgradEntityMapper;
import com.ycnet.frms.service.DeviceLockEntityService;
import com.ycnet.frms.vo.DeviceEntityVo;
import com.ycnet.frms.vo.mobile.DeviceLockInfoMobile;
import com.ycnet.frms.vo.mobile.UDPsendEntityBean;

import freemarker.template.SimpleDate;
import net.sf.json.JSONObject;

@Service("deviceLockEntityService")
public class DeviceLockEntityServiceImpl implements DeviceLockEntityService{

	@Resource(name="deviceLockEntityMapper")
	private DeviceLockEntityMapper deviceLockEntityMapper;
	
	@Resource(name="deviceLockSwitchMapper")
	private DeviceLockSwitchMapper deviceLockSwitchMapper;
	
	
	@Resource(name="hardwareUpgradEntityMapper")
	private HardwareUpgradEntityMapper hardwareUpgradEntityMapper;
	
	@Resource(name="deviceEntityMapper")
	private DeviceEntityMapper deviceEntityMapper;
	
	@Resource(name="deviceAlarmEntityMapper")
	private DeviceAlarmEntityMapper deviceAlarmEntityMapper;
	
	@Resource(name="deviceLockStatusEntityMapper")
	private DeviceLockStatusEntityMapper deviceLockStatusEntityMapper;
	
	/**
	 * 
	 * @Title: insertSelective
	 * @Description: 添加
	 * @param @param deviceLock
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月18日 下午6:41:46
	 * @version V1.0
	 */
	@Override
	public int insertSelective(DeviceLockEntity deviceLock) {
		return deviceLockEntityMapper.insertSelective(deviceLock);
	}

	/**
	 * 
	 * @Title: queryByLockCodeAndDevId
	 * @Description: 根据lockCode和devId查询
	 * @param @param lock
	 * @param @param devId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月18日 下午7:26:12
	 * @version V1.0
	 */
	@Override
	public int queryByLockCodeAndDevId(String lockCode, Long devId, Long codId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("lockCode", lockCode);
		map.put("devId", devId);
		map.put("codId", codId);
		return deviceLockEntityMapper.queryByLockCodeAndDevId(map);
	}

	/**
	 * 查询锁,中控器,设施信息
	* @Title: queryDeviceLockInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @param devCode
	* @param @param devName
	* @param @return    入参
	* @return List<DeviceLockInfoMobile>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月5日 上午9:48:56 
	* @version V1.0
	 */
	@Override
	public List<DeviceLockInfoMobile> queryDeviceLockInfo(Long orgId, String devCode, String devName) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orgId", orgId);
		map.put("devCode", devCode);
		map.put("devName", devName);
		return deviceLockEntityMapper.queryDeviceLockInfo(map);
	}

	/**
	 * 
	 * @Title: queryByCodIdAndDevId
	 * @Description: 根据devId和codId查询锁
	 * @param @param devId
	 * @param @param codId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月9日 下午2:53:24
	 * @version V1.0
	 */
	@Override
	public List<DeviceLockEntity> queryByCodIdAndDevId(Long devId, Long codId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("devId", devId);
		map.put("codId", codId);
		return deviceLockEntityMapper.queryByCodIdAndDevId(map);
	}

	/**
	 * 手机远程开锁,点击开锁
	* @Title: unlocking 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param deviceReg
	* @param @param user
	* @param @return    入参
	* @return DeviceLockSwitch    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年8月7日 上午11:25:01 
	* @version V1.0
	 */
	@Override
	public String unlocking(DeviceLockEntity deviceLock, Users user, String ip, String port1) {
		String res="";
		int count=0;
		SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long nowDate = System.currentTimeMillis();
		System.out.println(spf.format(nowDate));
		//1修改不应该在线的锁状态 im_device_lock 
		int min=2;//几分钟前的
		deviceLockEntityMapper.updateByCondetions(spf.format(nowDate-min*60*1000),user);
		//查询是否有在线的锁
		List<DeviceLockEntity> deviceLockEntityList  =null; 
		boolean flag=true;
		while(flag) {
			try {
				Thread.sleep(2 * 1000); //设置暂停的时间 2 秒
				++count;
				//是否存在成功的远程开锁记录
				deviceLockEntityList=deviceLockEntityMapper.selectBycodId(deviceLock.getCodId(),user.getOrgId());//根据条件查询锁ID (codId)
				if (deviceLockEntityList!=null&&deviceLockEntityList.size()>0) {
					flag = false;
					break;
				}
				if (count>24) {
					return res="锁未激活!";
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}  
		}	
		
		if (deviceLockEntityList!=null && deviceLockEntityList.size()==2) {//在线锁
				DeviceLockEntity deviceLockEntity1=deviceLockEntityList.get(1);
				DeviceLockEntity deviceLockEntity0=deviceLockEntityList.get(0);
				res=sendUDPconnections(deviceLockEntity0,deviceLockEntity1,user,ip,port1,nowDate,count);//udp通信
			}else if(deviceLockEntityList!=null && deviceLockEntityList.size()==1){
				DeviceLockEntity deviceLockEntity=deviceLockEntityList.get(0);
				res=sendUDPconnection(deviceLockEntity,user,ip,port1,nowDate,count);//udp通信
			}if(deviceLockEntityList==null || deviceLockEntityList.size()==0) {
				res="锁未激活!";
			}
		return res;
	}
	

	/**
	 * 两把锁,一起通讯
	* @Title: sendUDPconnections 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param deviceLockEntity0
	* @param @param deviceLockEntity1
	* @param @param user
	* @param @param ip
	* @param @param port1
	* @param @param nowDate
	* @param @return    入参
	* @return String    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年8月17日 上午10:42:53 
	* @version V1.0
	 * @param count 
	 */
	private String sendUDPconnections(DeviceLockEntity deviceLockEntity0, DeviceLockEntity deviceLockEntity1,
			Users user, String ip, String port1, long nowDate, int count) {
		String res="";
		SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long lockId0=deviceLockEntity0.getLockId();
		Long lockId1=deviceLockEntity1.getLockId();
		//2存在开始走协议//结果
		Integer port= Integer.valueOf(port1);
		//发送的json 实体
		
		UDPsendEntityBean UDPsendEntity0=new UDPsendEntityBean();
		UDPsendEntityBean UDPsendEntity1=new UDPsendEntityBean();
		UDPsendEntity0.setMsgType("9");
		UDPsendEntity1.setMsgType("9");
		//查询最大版本号:
		BigDecimal version1=hardwareUpgradEntityMapper.queryMaxHardVersion(user.getOrgId(),deviceEntityMapper.selectByPrimaryKey(deviceLockEntity1.getCodId()).getCodCode());
		BigDecimal version0=hardwareUpgradEntityMapper.queryMaxHardVersion(user.getOrgId(),deviceEntityMapper.selectByPrimaryKey(deviceLockEntity0.getCodId()).getCodCode());
		
		if (version1!=null) {
			UDPsendEntity1.setVersion((int)(version1.floatValue()*100)+"");
		}
		UDPsendEntity1.setResult("0");
		
		if (version0!=null) {
			UDPsendEntity0.setVersion((int)(version0.floatValue()*100)+"");
		}
		UDPsendEntity0.setResult("0");
		UDPsendEntity1.setResult("0");
		
		if (deviceLockEntity1.getUploadCode()!=null && !"".equals(deviceLockEntity1.getUploadCode())) {
			UDPsendEntity1.setCltID1(deviceLockEntity1.getUploadCode().split(",")[0]);
			UDPsendEntity1.setCltID2(deviceLockEntity1.getUploadCode().split(",")[1]);
		}
		if (deviceLockEntity0.getUploadCode()!=null && !"".equals(deviceLockEntity0.getUploadCode())) {
			UDPsendEntity0.setCltID1(deviceLockEntity0.getUploadCode().split(",")[0]);
			UDPsendEntity0.setCltID2(deviceLockEntity0.getUploadCode().split(",")[1]);
		}
		String userId= complementUserid(user.getUserId());//补全的userId
		UDPsendEntity0.setSettingUID(userId);
		UDPsendEntity1.setSettingUID(userId);
		JSONObject js0 = JSONObject.fromObject(UDPsendEntity0);
		JSONObject js1 = JSONObject.fromObject(UDPsendEntity1);
		System.out.println(js0.toString()+"--------------------------------------");
		System.out.println(js1.toString()+"--------------------------------------");
		String data0=js0.toString();
		String data1=js1.toString();
		//UDP发送数据
		String result0 = UDPClient.sendServer(ip, port, data0);
		String result1 = UDPClient.sendServer(ip, port, data1);
		if (result0!=null && !"".equals(result0 )|| result1!=null && !"".equals(result1)) {
			JSONObject resJson0 = JSONObject.fromObject(result0);
			JSONObject resJson1 = JSONObject.fromObject(result1);
			String str0 = resJson0.getString("result");
			String str1 = resJson1.getString("result");
			if (str0!=null && !"".equals(str0) || str1!=null && !"".equals(str1) ) {
				if (str0.equals("0") || str1.equals("0")) {
					//3开始轮询  im_device_lock_switch
					//DeviceLockSwitch deviceLockSwitch = new DeviceLockSwitch();
					DeviceLockStatusEntity deviceLockStatus = new DeviceLockStatusEntity();
					Boolean kg = true;
					while(kg) {
						try {
							Thread.sleep(2 * 1000); //设置暂停的时间 2 秒
							++count;
							//是否存在成功的远程开锁记录
							//deviceLockSwitch = deviceLockSwitchMapper.queryByLockIds(lockId0,lockId1,spf.format(nowDate-30*1000),user);
							deviceLockStatus=deviceLockStatusEntityMapper.queryByConditions(deviceLockEntity0.getDevId(),spf.format(nowDate-30*1000),user);
							if (deviceLockStatus!=null) {
								break;
							}
							if (count > 24) {
								kg = false;
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}  
					}
					if (deviceLockStatus!=null) {
						//修改当前锁为下线状态
						DeviceLockEntity record0 = new DeviceLockEntity();
						record0.setLockId(lockId0);
						record0.setIsUpload("0");
						int num=deviceLockEntityMapper.updateByPrimaryKeySelective(record0);
						DeviceLockEntity record1 = new DeviceLockEntity();
						record1.setLockId(lockId1);
						record1.setIsUpload("0");
						num+=deviceLockEntityMapper.updateByPrimaryKeySelective(record1);
						if (num>0) {
							res="开锁成功!";
						}else {
							res="开锁失败!";
						}
					}else {
						res="开锁失败!";
					}
				}
			}
		}
		return res;
	}

	/**
	 * 发送udp
	* @Title: sendUDPconnection 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param deviceLockEntity
	* @param @param user
	* @param @param ip
	* @param @param port1
	* @param @param nowDate
	* @param @return    入参
	* @return String    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年8月17日 上午8:22:54 
	* @version V1.0
	 * @param count 
	 */
	private String sendUDPconnection(DeviceLockEntity deviceLockEntity, Users user, String ip, String port1,Long nowDate, int count) {
		String res="";
		SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long lockId=deviceLockEntity.getLockId();
		//2存在开始走协议//结果
		Integer port= Integer.valueOf(port1);
		//发送的json 实体
		UDPsendEntityBean UDPsendEntity=new UDPsendEntityBean();
		UDPsendEntity.setMsgType("9");
		//查询最大版本号:
		BigDecimal version=hardwareUpgradEntityMapper.queryMaxHardVersion(user.getOrgId(),deviceEntityMapper.selectByPrimaryKey(deviceLockEntity.getCodId()).getCodCode());
		
		if (version!=null) {
			UDPsendEntity.setVersion((int)(version.floatValue()*100)+"");
		}
		UDPsendEntity.setResult("0");
		
		if (deviceLockEntity.getUploadCode()!=null && !"".equals(deviceLockEntity.getUploadCode())) {
			UDPsendEntity.setCltID1(deviceLockEntity.getUploadCode().split(",")[0]);
			UDPsendEntity.setCltID2(deviceLockEntity.getUploadCode().split(",")[1]);
		}
		String userId= complementUserid(user.getUserId());//补全的userId
		UDPsendEntity.setSettingUID(userId);
		JSONObject js = JSONObject.fromObject(UDPsendEntity);
		System.out.println(js.toString()+"--------------------------------------");
		String data=js.toString();
		//UDP发送数据
		String result = UDPClient.sendServer(ip, port, data);
		if (result!=null && !"".equals(result)) {
			JSONObject resJson = JSONObject.fromObject(result);
			String str = resJson.getString("result");
			if (str!=null && !"".equals(str)) {
				if (str.equals("0")) {
					//3开始轮询  im_device_lock_switch
//					DeviceLockSwitch deviceLockSwitch = new DeviceLockSwitch();
//					DeviceAlarmEntity deviceAlarmEntity= new DeviceAlarmEntity();
					DeviceLockStatusEntity deviceLockStatus= new DeviceLockStatusEntity();
					Boolean kg = true;
					while(kg) {
						try {
							Thread.sleep(2 * 1000); //设置暂停的时间 2 秒
							++count;
							//是否存在成功的远程开锁记录
//							deviceLockSwitch = deviceLockSwitchMapper.queryByLockId(lockId,spf.format(nowDate-30*1000),user);
//							deviceAlarmEntity=deviceAlarmEntityMapper.queryByConditions(deviceLockEntity.getDevId(),spf.format(nowDate-30*1000),user);
							deviceLockStatus=deviceLockStatusEntityMapper.queryByConditions(deviceLockEntity.getDevId(),spf.format(nowDate-30*1000),user);
							if (deviceLockStatus!=null) {
								break;
							}
							if (count > 24) {
								kg = false;
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}  
					}
					if (deviceLockStatus!=null) {
						//修改当前锁为下线状态
						DeviceLockEntity record = new DeviceLockEntity();
						record.setLockId(lockId);
						record.setIsUpload("0");
						int num=deviceLockEntityMapper.updateByPrimaryKeySelective(record);
						if (num>0) {
							res="开锁成功!";
						}else {
							res="开锁失败!";
						}
					}else {
						res="开锁失败!";
					}
				}
			}
		}else {
			res="通讯失败!";
		}
		return res;
	}

	/**
	 * 补全userId
	* @Title: complementUserid 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param userId
	* @param @return    入参
	* @return String    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年8月7日 下午4:44:23 
	* @version V1.0
	 */
	private static String complementUserid(Long userId) {
        String hexEncode = HexUtil.encode(userId+"");
        while (hexEncode.length() < 16) {
        	hexEncode = hexEncode + "0";
		}
		return  hexEncode;
	}

	/**
	 * 转为16进制
	* @Title: toChange16 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param string
	* @param @return    入参
	* @return String    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年8月7日 下午2:48:34 
	* @version V1.0
	 */
	private String toChange16(String strPart) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < strPart.length(); i++) {
			int ch = (int) strPart.charAt(i);
			String strHex = Integer.toHexString(ch);
			hexString.append(strHex);
		}
		return hexString.toString();
	}

//	public static void main(String[] args) {
//		String strPart = "多久啊会计分录看";
//		StringBuffer hexString = new StringBuffer();
//		for (int i = 0; i < strPart.length(); i++) {
//			int ch = (int) strPart.charAt(i);
//			String strHex = Integer.toHexString(ch);
//			hexString.append(strHex);
//		}
//		System.out.println();
//
//	}
	public static void main(String[] args) {
//		String ip= "localhost";
//		Integer port=8800;
//		//发送的json 实体
//		UDPsendEntityBean UDPsendEntity=new UDPsendEntityBean();
//		UDPsendEntity.setMsgType("9");
//		UDPsendEntity.setVersion("123");
//		UDPsendEntity.setResult("0");
//		String userId= complementUserid(89l);//补全的userId
//		System.out.println(userId);
//		UDPsendEntity.setSettingUID(userId);
//		JSONObject js = JSONObject.fromObject(UDPsendEntity);
//		System.out.println(js.toString()+"--------------------------------------");
//		String data=js.toString();
//		//UDP发送数据
//		String result = UDPClient.sendServer(ip, port, data);
		
		System.out.println(complementUserid(34L));
	}
}
  
    