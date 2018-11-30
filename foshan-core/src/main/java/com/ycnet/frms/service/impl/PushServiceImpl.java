package com.ycnet.frms.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.org.apache.regexp.internal.recompile;
import com.ycnet.core.Constants;
import com.ycnet.core.util.BeanUtils;
import com.ycnet.core.util.JsonDateValueProcessor;
import com.ycnet.core.util.Sms;
import com.ycnet.core.util.SmsPost;
import com.ycnet.frms.bean.AlarmPushlog;
import com.ycnet.frms.bean.DeviceAlarm;
import com.ycnet.frms.bean.DeviceReg;
import com.ycnet.frms.bean.InspectWork;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.DeviceAlarmMapper;
import com.ycnet.frms.mapper.UsersMapper;
import com.ycnet.frms.service.DeviceRegService;
import com.ycnet.frms.service.PushService;
import com.ycnet.frms.vo.DeviceAlarmBean;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.Notification;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


@Service("pushService")
@Transactional
public class PushServiceImpl implements PushService{

	@Resource(name="deviceAlarmMapper")
	private DeviceAlarmMapper deviceAlarmMapper;
	
	@Resource(name="usersMapper")
	private UsersMapper usersMapper;
	
	@Resource(name="deviceRegService")
	private DeviceRegService deviceRegService;
	
	//182 	服务器(废弃)
//	public static final String masterSecret ="c614cbbe0db9f819a8e79453";
//	public static final String appKey ="054d0b8943f7e5fc0e5a29e6";
//	//128 	服务器(废弃)
//	public static final String masterSecret ="63a7128e75d98cd5d9f81198";
//	public static final String appKey ="9d3da3fd7955a8c6fcb37a35";
	
	//短信参数
	public static final String Url ="http://utf8.sms.webchinese.cn/";
	public static final String Uid ="JSM40940";
	public static final String Key ="ba5c7f4038eb0e64645f";
	
	
	//推送参数
	public static final String masterSecret ="cd634fa650f0b710909e6919";
	public static final String appKey ="8c0259a9a055c7579491a6da";
	
//	//正式服务器	40 foshan
	public static final String pushTo ="http://120.77.183.40/foshan";
	
//	//正式服务器	40 frms
//	public static final String pushTo ="http://120.77.183.40/frms";
	
	//测试服务器	128
//	public static final String pushTo ="http://120.79.29.128/frms";
	
//	//本地测试
//	public static final String pushTo =".1.8";//如果测试把本地ip写在上面
	
	
	
	/**
	 * 
	 * @Title: push
	 * @Description: 推送设备报警记录
	 * @param  
	 * @return void 入参
	 * @return String    返回类型
	 * @author DZY （添加注释）
	 * @throws
	 * @date 2018年2月24日 上午9:39:42
	 * @version V1.0
	 */
//	@Scheduled(fixedDelay=10000)
	public void push()
	{
		List<DeviceAlarmBean> alarmList = Convert(deviceAlarmMapper.getAlarmList());
		String orgId = "";
		for(DeviceAlarmBean alarm : alarmList){
			orgId = deviceAlarmMapper.getOrgIdByDevId(alarm.getDevId());
			alarm.setOrgId(orgId);
			alarm.setPushTo(pushTo);
			JPushServer(alarm,orgId);
			//sendPost(alarm,orgId);
		}
/*		
 * 测试代码	
 * 
 * 
		DeviceAlarmBean alarm = new DeviceAlarmBean();
		alarm.setAlarmProcessId(1L);
		alarm.setAlarmContent("推送成功");
		alarm.setUserId(6L);
		for(int i=0;i<3;i++){
			JSONObject json = JSONObject.fromObject(alarm);
	         
			PushResult result = JPush_send("6",json.toString());
			
		}
	*/	
		//System.out.println("==========="+result.getOriginalContent());
	}
	
	/**
	 发送短信报警  返回值意义
	 	-1	没有该用户账户
		-2	接口密钥不正确 [查看密钥]
				不是账户登陆密码
		-21	MD5接口密钥加密不正确
		-3	短信数量不足
		-11	该用户被禁用
		-14	短信内容出现非法字符
		-4	手机号格式不正确
		-41	手机号码为空
		-42	短信内容为空
		-51	短信签名格式不正确
				接口签名格式为：【签名内容】
		-6	IP限制
		大于0	短信发送数量
	 */
	public boolean sendPost(DeviceAlarmBean alarm,String orgId) {

		String phones = getPhones(alarm,orgId);
		if(phones == null){
			return false;
		}
		HttpClient client = null;
		PostMethod post = null;
		try {	
			client = new HttpClient();
			post = new PostMethod(Url);
			post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");// 在头文件中设置转码
			NameValuePair[] data = { 
					//本站用户名
					new NameValuePair("Uid", Uid), 
					//注册时填写的接口秘钥
					new NameValuePair("Key", Key),
					//目的手机号码
					new NameValuePair("smsMob", phones), 
					//短信内容，最多支持400个字
					new NameValuePair("smsText",alarm.getDevCode()+alarm.getDevName()+alarm.getAlarmContent()) };
			post.setRequestBody(data);
	
			client.executeMethod(post);
			Header[] headers = post.getResponseHeaders();
			int statusCode = post.getStatusCode();
			System.out.println("statusCode:" + statusCode); 
			for (Header h : headers) {
				System.out.println(h.toString());
			}
			String result = new String(post.getResponseBodyAsString().getBytes("utf-8"));
			System.out.println("发送短信返回值="+result); // 打印返回消息状态
			
			
			post.releaseConnection();
			if (Integer.parseInt(result)>0) {
				//因推送和短信都发送一次，是否成功都不在发送
				AlarmPushlog log= deviceAlarmMapper.getIsExitByLogDX(alarm.getAlarmProcessId());
				if(log == null){
					AlarmPushlog logs = new AlarmPushlog();
					logs.setAlarmProcessId(alarm.getAlarmProcessId());
					logs.setDevId(alarm.getDevId());
					logs.setPushTime(new Date());
					logs.setPushSign("0");
					logs.setOrgId(Long.parseLong(orgId));
					deviceAlarmMapper.AlarmPushlogAdd(logs);
				}
				return true;
			}
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private String getPhones(DeviceAlarmBean alarm,String orgId){
		List<Users> list = usersMapper.selectByOrgId(Long.parseLong(orgId));
		String phones = null;
		for (int i=0;i<list.size();i++){
			String phone = list.get(i).getMobilePhone();
			if( phone!=null && !"".equals(phone)){
				if(phones == null){
					phones = phone;
				}else{
					if(phones.indexOf(phone)==-1){
						phones = phones + "," + phone;
					}
				}
			}
		}
		return phones;
	} 
	
	private  void JPushServer(DeviceAlarmBean alarm,String orgId){

		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
		
		JSONObject json = JSONObject.fromObject(alarm, jsonConfig);
		
		PushResult result = JPush_send(orgId,json.toString());
		if(result != null){
			AlarmPushlog log= deviceAlarmMapper.getIsExitByLogTS(alarm.getAlarmProcessId());
			if(log == null){
				AlarmPushlog logs = new AlarmPushlog();
				logs.setAlarmProcessId(alarm.getAlarmProcessId());
				logs.setDevId(alarm.getDevId());
				logs.setPushTime(new Date());
				logs.setPushSign("0");
				logs.setOrgId(Long.parseLong(orgId));
				deviceAlarmMapper.AlarmPushlogAdd(logs);
			}else{
				log.setPushTime(new Date());
				log.setPushSign("1");
				deviceAlarmMapper.updateByPrimaryKeySelectives(log);
			}
		}else{
			AlarmPushlog log= deviceAlarmMapper.getIsExitByLogTS(alarm.getAlarmProcessId());
			if(log == null){ 
				AlarmPushlog logs = new AlarmPushlog();
				logs.setAlarmProcessId(alarm.getAlarmProcessId());
				logs.setDevId(alarm.getDevId());
				logs.setPushTime(new Date());
				logs.setPushSign("1");
				logs.setOrgId(Long.parseLong(orgId));
				deviceAlarmMapper.AlarmPushlogAdd(logs);
			}else{
				log.setPushTime(new Date());
				deviceAlarmMapper.updateByPrimaryKeySelectives(log);
			}
			
		}
	}
	
	
	private  static PushResult JPush_send(String orgId,String alarm) {
		 //// 对android和ios设备发送,同时指定离线消息保存时间
		 /**
		  * masterSecret	String	必须	Portal上注册应用时生成的 masterSecret
			appKey			String	必须	Portal上注册应用时生成的 appKey
				
			timeToLive		long	可选	
									保存离线消息的时长。秒为单位。最多支持10天（864000秒）。
									0 表示该消息不保存离线。即：用户在线马上发出，当前不在线用户将不会收到此消息。
									此参数不设置则表示默认，默认为保存1天的离线消息（86400秒）。
		  */
		 JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3);
		 //DeviceAlarmBean send = new DeviceAlarmBean();

	     // For push, all you need do is to build PushPayload object.
	     PushPayload payload = buildPushObject_all_tag_alert(orgId,alarm);
	     PushResult result = null;
	     try { 
	         result = jpushClient.sendPush(payload);
	        System.out.println("Got result - " + result); 

	     } catch (APIConnectionException e) {
	         // Connection error, should retry later
	         e.printStackTrace();

	     } catch (APIRequestException e) { 
	         // Should review the error, and fix the request
	         e.getStatus();
	         e.getErrorCode();
	         e.getErrorMessage();
	     }
		return result;
	
	}

	/*
	 * 构建推送对象：平台是 Andorid 与 iOS，推送目标是 （"tag1" 与 "tag2" 的并集）且（"alias1" 与 "alias2" 的并集），推送内容是 - 内容为 MSG_CONTENT 的消息，并且附加字段 from = JPush。
	 */
	public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras() {
       return PushPayload.newBuilder()
               .setPlatform(Platform.android_ios())
               .setAudience(Audience.newBuilder()
                       //.addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))
                       .addAudienceTarget(AudienceTarget.alias("16"))
                       .build())
               .setMessage(Message.newBuilder()
                       .setMsgContent("消息内容222")
                       .addExtra("from", "JPush")
                       .build())
               .build();
   }

	/**
	 * 构建推送对象：所有平台，推送目标是别名为 "alias1"，通知内容为 ALERT。
	 * setMessage  自定义推送
	 * setNotification  通知推送
	 */
	public static PushPayload buildPushObject_all_alias_alert(String orgId,String send) {
       return PushPayload.newBuilder()
               .setPlatform(Platform.all())
               .setAudience(Audience.alias(orgId))
//               .setNotification(Notification.alert(send))
//               	.setNotification(Notification.android(send, "标题", null))//增加标题
               .setMessage(Message.newBuilder()
                       .setMsgContent(send)
                       .addExtra("from", "JPush")
                       .build())
               .build();
   }
	
	/**
	 * 构建推送对象：所有平台，推送目标是别名为 "tag"，通知内容为 ALERT。
	 * setMessage  自定义推送
	 * setNotification  通知推送
	 */
	public static PushPayload buildPushObject_all_tag_alert(String orgId,String send) {
       return PushPayload.newBuilder()
               .setPlatform(Platform.android())
               .setAudience(Audience.tag(orgId))
				//Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
				// sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
				// [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
				.setMessage(Message.newBuilder()
				        .setMsgContent(send)
				        .build()
				        )
               .build();
   }
	
	private List<DeviceAlarmBean> Convert(List<DeviceAlarm> deviceAlarmList)
	{
		List<DeviceAlarmBean> dabList=new ArrayList<DeviceAlarmBean>();
		for(DeviceAlarm da:deviceAlarmList)
		{
			DeviceAlarmBean dab=new DeviceAlarmBean();
//			BeanUtils.copy(da, dab);
			dab.setAlarmProcessId(da.getAlarmProcessId());
			dab.setAlarmId(da.getAlarmId());
			dab.setDevId(da.getDevId());
			dab.setDevCode(da.getDevCode());
			dab.setDevName(da.getDevName());
			dab.setDevImei(da.getDevImei());
			dab.setDevMac(da.getDevMac());
			dab.setAlarmTime(da.getAlarmTime());
			dab.setRptTime(da.getRptTime());
			dab.setAlarmTypes(da.getAlarmTypes());
			dab.setAlarmContent(da.getAlarmContent());
			dab.setDealSign(da.getDealSign());
			dab.setDealSituation(da.getDealSituation());
			dab.setUserId(da.getUserId());
			dab.setUserName(da.getUserName());
			
			//所属平台
			DeviceReg dev = deviceRegService.queryByImei(da.getDevImei());
			dab.setDevPlatform(dev.getDevPlatform());
			
			dab.setPushType("alarm");//设置推送类型
			
			if(Constants.DealSignMap.containsKey(dab.getDealSign()))
			{
				dab.setDealSignName(Constants.DealSignMap.get(dab.getDealSign()));
			}
			dabList.add(dab);
		}
		
		return dabList;
	}

	public static void main(String[] args) throws Exception{

		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://utf8.sms.webchinese.cn/");
		post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");// 在头文件中设置转码
		NameValuePair[] data = { new NameValuePair("Uid", "JSM40940"), new NameValuePair("Key", "ba5c7f4038eb0e64645f"),
					new NameValuePair("smsMob", "18345171178"), new NameValuePair("smsText", "光交箱报警") };
		post.setRequestBody(data);

		client.executeMethod(post);
		Header[] headers = post.getResponseHeaders();
		int statusCode = post.getStatusCode();
		System.out.println("statusCode:" + statusCode);
		for (Header h : headers) {
			System.out.println(h.toString());
		}
		String result = new String(post.getResponseBodyAsString().getBytes("utf-8"));
		System.out.println(result); // 打印返回消息状态

		post.releaseConnection();
	}

	/**
	 * 
	 * @Title: pushInspectWork
	 * @Description: 推送巡检任务
	 * @param @param work
	 * @param @param user
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年2月24日 上午9:40:22
	 * @version V1.0
	 */
	@Override
	public void pushInspectWork(InspectWork work, Users user) {
		work.setPushType("inspect");//设置推送类型
		work.setOrgId(user.getOrgId().toString());
		work.setUserCode(user.getUserCode());
		work.setPushTo(pushTo);
		JPushServerInspectWork(work,user.getOrgId());
	}
	
	/**
	 * 
	 * @Title: JPushServerInspectWork
	 * @Description: 推送方法
	 * @param @param work
	 * @param @param orgId 
	 * @return void 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年2月24日 上午9:43:29
	 * @version V1.0
	 */
	private void JPushServerInspectWork(InspectWork work, Long orgId) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
		JSONObject json = JSONObject.fromObject(work, jsonConfig);
		PushResult result = JPush_send(orgId.toString(),json.toString());
	}
	
}
