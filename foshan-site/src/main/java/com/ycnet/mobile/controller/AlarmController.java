package com.ycnet.mobile.controller;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.DeviceAlarm;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.service.DeviceAlarmService;
import com.ycnet.frms.service.InspectWorkService;
import com.ycnet.frms.vo.DeviceAlarmConditionBean;
import com.ycnet.mobile.util.CountResult;
import com.ycnet.mobile.util.Result;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlarmController
{

  @Resource(name="deviceAlarmService")
  private DeviceAlarmService deviceAlarmService;
  
  @Resource(name = "inspectWorkService")
  private InspectWorkService inspectWorkService;

  /**
   * 
   * @Title: findAlarmList
   * @Description: 设备报警列表
   * @param @param deviceAlarmCon
   * @param @param session
   * @param @return 
   * @return Object 入参
   * @return String    返回类型
   * @author DZY (添加注释)
   * @throws
   * @date 2018年2月23日 上午11:47:43
   * @version V1.0
   */
  @RequestMapping({"/m/AlarmList.htm"})
  public Object findAlarmList(DeviceAlarmConditionBean deviceAlarmCon, HttpSession session)
  {
    Result r = Result.get();
    try
    {
      Users user = (Users)session.getAttribute("users");
      PageBean pb = new PageBean();
      if(deviceAlarmCon.getPageno()!=null) {
    	  if(0==deviceAlarmCon.getPageno()) {
    		  pb.setPageNo(1);
    		  pb.setPageSize(10000);
    	  }else {
    		  pb.setPageNo(deviceAlarmCon.getPageno().intValue());
    	  }
      }
      pb = this.deviceAlarmService.queryByConditionBean(deviceAlarmCon, user, pb);
      if (pb.getRows() > 0)
      {
        r.putDtList(pb.getList());
        r.setR(pb.getRows());
      }
      else
      {
        r.setR_content("没有查询到报警数据！");
        r.setR(0);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    return r;
  }

  /**
   * 
   * @Title: alarmSave
   * @Description: 报警处理
   * @param @param deviceAlarm
   * @param @param session
   * @param @param model
   * @param @return 
   * @return Object 入参
   * @return String    返回类型
   * @author DZY 
   * @throws
   * @date 2018年2月23日 上午11:48:14
   * @version V1.0
   */
  @ResponseBody
  @RequestMapping({"/m/saveAlarm.htm"})
  public Object alarmSave(DeviceAlarm deviceAlarm, HttpSession session, Model model)
  {
		Result r = Result.get();
		try {
			Users user = (Users) session.getAttribute("users");
//			DeviceAlarm alarm = this.deviceAlarmService.selectById(deviceAlarm.getAlarmProcessId());
//			if ("0".equals(alarm.getDealSign())) {
				int status = this.deviceAlarmService.deviceAlarmCtrlSave(deviceAlarm, user);
				if (status > 0) {
					r.setR(1);
					r.putRContent("处理成功");
				} else {
					r.setR(0);
					r.putRContent("处理失败");
				}
//			} else {
//				r.setR(0);
//				r.putRContent("报警已处理！");
//			}
		} catch (Exception e) {
			r.setR(0);
			r.putRContent("处理失败");
			e.printStackTrace();
		}

    return r;
  }
  
	/**
	 * 
	 * @Title: queryNotCompletedCount
	 * @Description: 查询未完成巡检任务及未处理报警的数量
	 * @param @param session
	 * @param @return 
	 * @return Object 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年2月12日 下午5:03:39
	 * @version V1.0
	 */
	@RequestMapping("/m/queryNotCompletedCount.htm")
	public Object queryNotCompletedCount(HttpSession session) {
		Result r = Result.get();
		Users user = (Users)session.getAttribute("users");
		try {
			int alarmCount = deviceAlarmService.queryNotCompletedCount(user);
			int workCount = inspectWorkService.queryNotCompletedCount(user);
			CountResult count = new CountResult();
			count.setAlarmCount(alarmCount);
			count.setWorkCount(workCount);
			r.putR(1);
			r.putDT(count);
		} catch (Exception e) {
			r.putR(0);
			r.putRContent("未处理报警数量或未巡检数量查询失败");
			e.printStackTrace();
		}
		return r;
	}
  
}