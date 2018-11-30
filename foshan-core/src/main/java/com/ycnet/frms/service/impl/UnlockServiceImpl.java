package com.ycnet.frms.service.impl;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.frms.bean.DeviceReg;
import com.ycnet.frms.bean.MobileSwitch;
import com.ycnet.frms.bean.Organizition;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.service.DeviceRegService;
import com.ycnet.frms.service.MobileSwitchService;
import com.ycnet.frms.service.OrganizitionService;
import com.ycnet.frms.service.UnlockService;

@Service("unlockService")
@Transactional
public class UnlockServiceImpl implements UnlockService{

	@Resource(name="organizitionService")
	private OrganizitionService organizitionService;
	
	@Resource(name="deviceRegService")
	private DeviceRegService deviceRegService;
	
	@Resource(name="mobileSwitchService")
	private MobileSwitchService mobileSwitchService;
	
	@Override
	public String unlockInstruct(String did,Users user) {
		Organizition org = organizitionService.selectByPrimaryKey(user.getOrgId());
		DeviceReg dr = deviceRegService.queryByImei(did);
		BufferedReader in = null;
		BufferedOutputStream out = null;
		Socket socket = null;
		boolean bConnected = false;
		try {
			/*ip = PropertiesUtil.getValue("/socket.properties", "socket.ip");
			port = Integer.parseInt(PropertiesUtil.getValue("/socket.properties", "socket.port"));
			socket = new Socket(ip,port);*/
			//socket = new Socket("58.42.249.170",8887);
//			socket = new Socket("39.108.6.91",8887);
			socket = new Socket(org.getIp(),org.getPort().intValue());
			socket.setSoTimeout(1000*60);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
			out = new BufferedOutputStream(socket.getOutputStream());
			//out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"));
			System.out.println("~~~~~~~~连接成功~~~~~~~~!");
			String outDate="";
			outDate = "{\"header\":{\"version\":\"0.4\",\"msgType\":\"down\",\"msgId\":\"1\",\"did\":\""+did+"\"},\"body\":{\"raw\":{\"platform\":"+dr.getDevPlatform()+",\"msgType\":\"unlocking\",\"userId\":"+user.getUserId()+",\"lockType\":\"01\"}}}";
			out.write(outDate.getBytes());
			bConnected = true;
			
			out.write("\r\n".getBytes());
			out.flush();
			while(bConnected){

				String inStr = in.readLine();
				//System.out.println("开锁接收到的返回数据" + inStr);
				if(inStr != null ){
					//保存远程开锁记录
					MobileSwitch  mobile = new MobileSwitch();
					mobile.setDevId(dr.getDevId());
					mobile.setDevCode(dr.getDevCode());
					mobile.setDevName(dr.getDevName());
					mobile.setUserId(user.getUserId());
					mobile.setUserName(user.getUserName());
					mobile.setMobilePhone(user.getMobilePhone());
					mobile.setMobileImei(user.getMobileImei());
					mobile.setColTime(new Date());
					mobile.setRptTime(new Date());
					mobile.setSwitchType("1");
					mobile.setOprTypes("1");
					mobile.setDevImei(did);
					mobile.setDevMac(dr.getDevMac());
					mobile.setOrgId(user.getOrgId());
					
					if("ok".equals(inStr)){
						mobile.setResultStatus("1");
						mobileSwitchService.save(mobile);
						return "开锁完成！";
					}else{
						mobile.setResultStatus("0");
						mobileSwitchService.save(mobile);
						return inStr;
					}
				}
			}
		} catch (SocketTimeoutException e) {
			try {
				Socket socketc = new Socket(org.getIp(),org.getPort().intValue());
				in = new BufferedReader(new InputStreamReader(socketc.getInputStream(),"utf-8"));
				out = new BufferedOutputStream(socketc.getOutputStream());
				String outDate=""; 
				outDate = "{\"header\":{\"version\":\"0.4\",\"msgType\":\"timeOut\",\"msgId\":\"1\",\"did\":\""+did+"\"}}";
				out.write(outDate.getBytes());
				out.write("\r\n".getBytes());
				out.flush();
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				out.close();
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "开锁失败！";
	}
	
	

	
}
