package com.ycnet.mobile.controller.iam;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ycnet.core.config.SessionName;
import com.ycnet.core.util.AppRequestEntity;
import com.ycnet.core.util.AppResponseEntity;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.service.AlarmEntityService;
import com.ycnet.frms.vo.mobile.AlarmEntityMobile;
import com.ycnet.frms.vo.mobile.FacilityAlarmListMobile;
/**
 * 
* @ClassName: AlarmController 
* @Description: 智能锁告警记录 
* @author lch  
* @date 2018年4月12日 下午7:53:00 
* @version V1.0
 */
@RestController
@RequestMapping("/iam")
public class AlarmEntityController {

	@Resource
	private AlarmEntityService alarmService;
	/**
	 * 
	* @Title: queryAlarmList 
	* @Description: 告警信息列表-- 非干城 
	* @param @param session
	* @param @param userId
	* @param @param pb
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年4月12日 下午7:56:08 
	* @version V1.0
	 */
	@RequestMapping(value="/queryAlarmList.htm")
	public Object queryAlarmList(HttpSession session,@RequestBody AppRequestEntity entity) {
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("queryAlarmList");
		AlarmEntityMobile aem = new AlarmEntityMobile();
		try {
			Integer pageNo = Integer.parseInt(entity.getDto().getOrDefault("pageNo", 0).toString());
			Integer pageSize = Integer.parseInt(entity.getDto().getOrDefault("pageSize", 0).toString());
			PageBean pb = new PageBean();
			pb.setPageNo(pageNo);
			if (pageSize.intValue()!=0) {
				pb.setPageSize(pageSize);
			}
			aem.setDevCode(entity.getDto().getOrDefault("devCode", "").toString());
			aem.setDevName(entity.getDto().getOrDefault("devName", "").toString());
			if(pageNo==0) {
				pb=null;
			}
			Users users=(Users)session.getAttribute(SessionName.mobile);
			if(users!=null) {
				List<AlarmEntityMobile> alarmList=alarmService.queryAlarmList(pb,users.getOrgId(),aem);
				if(alarmList!=null && alarmList.size()>0) {
					r.setCode("1");
					r.setMessage("查询成功！");
					r.setDto(alarmList);
				}else {
					r.setCode("0");
					r.setMessage("未查询到数据！");
				}
			}else {
				r.setCode("0");
				r.setMessage("请重新登录！");
			}
		} catch (Exception e) {
			r.setCode("0");
			r.setMessage("操作失败！");
			e.printStackTrace();
		}
		return r;
	}
	/**
	 * 
	* @Title: modifAlarm 
	* @Description: 批量处理告警信息 
	* @param @param session
	* @param @param userId
	* @param @param alarmIds
	* @param @param dealSituation
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年4月13日 上午10:27:26 
	* @version V1.0
	 */
	@RequestMapping("/modifAlarm.htm")
	public Object modifAlarm(HttpSession session,@RequestBody AppRequestEntity entity) {
		String alarmIds=entity.getDto().getOrDefault("alarmIds", "").toString();
		String dealSituation=entity.getDto().getOrDefault("dealSituation", "").toString();
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("modifAlarm");
		try {
			if(alarmIds==null || "".equals(alarmIds)) {
				r.setCode("0");
				r.setMessage("请选中要处理的告警信息！");
				return r;
			}
			if(dealSituation==null||"".equals(dealSituation) ){
				r.setCode("0");
				r.setMessage("请填写处理信息！");
				return r;
			}
			Users users=(Users)session.getAttribute(SessionName.mobile);
			if(users!=null) {
				int reg=alarmService.modifAlarm(users,alarmIds,dealSituation);
				if(reg>0) {
					r.setCode("1");
					r.setMessage("处理成功！");
				}else {
					r.setCode("0");
					r.setMessage("处理失败！");
				}
			}else {
				r.setCode("0");
				r.setMessage("请重新登录！");
			}
		} catch (Exception e) {
			r.setCode("0");
			r.setMessage("操作失败！");
			e.printStackTrace();
		}
		return r;
	}
	/**
	 * 告警接口列表(分页)
	* @Title: queryAlarmInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param session
	* @param @param entity
	* @param @return    入参
	* @return Object    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年8月8日 上午11:18:29 
	* @version V1.0
	 */
	@RequestMapping("/queryAlarmInfo.htm")
	public Object queryAlarmInfo(HttpSession session,@RequestBody AppRequestEntity entity) {
		Long devId=Long.parseLong(entity.getDto().getOrDefault("devId", "0L").toString());
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("queryAlarmInfo");
		try {
			int pageNo = Integer.parseInt(entity.getDto().getOrDefault("pageNo", 1).toString());
			int pageSize = Integer.parseInt(entity.getDto().getOrDefault("pageSize", 0).toString());
			PageBean pb = new PageBean();
			pb.setPageNo(pageNo);
			if (pageSize!=0) {
				pb.setPageSize(pageSize);
			}
//			if (pageNo == -999) {
//				r.setCode("0");
//				r.setMessage("pageNo必传!");
//				return r;
//			}
			if(pageNo==0) {//不分页
				pb=null;
			}
			if(devId==null || devId==0L) {
				r.setCode("0");
				r.setMessage("请选取告警信息！");
				return r;
			}
			Users users=(Users)session.getAttribute(SessionName.mobile);
			if(users!=null) {
				FacilityAlarmListMobile  alarmList=alarmService.queryAlarmInfo(devId,pb);
				if(alarmList!=null) {
					r.setCode("1");
					r.setMessage("查询成功！");
					r.setDto(alarmList);
				}else {
					r.setCode("0");
					r.setMessage("未查询到数据！");
				}
			}else {
				r.setCode("0");
				r.setMessage("请重新登录！");
			}
		} catch (Exception e) {
			r.setCode("0");
			r.setMessage("操作失败！");
			e.printStackTrace();
		}
		return r;
	}
}
