package com.ycnet.frms.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.InspectImg;
import com.ycnet.frms.bean.InspectWork;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.FacilityMapper;
import com.ycnet.frms.mapper.InspectImgMapper;
import com.ycnet.frms.mapper.InspectWorkMapper;
import com.ycnet.frms.service.InspectWorkService;
import com.ycnet.frms.service.PushService;
import com.ycnet.frms.vo.InspectWorkConditionBean;
import com.ycnet.frms.vo.InspectWorkDev;

@Service("inspectWorkService")
public class InspectWorkServiceImpl implements InspectWorkService{
	
	@Resource(name="inspectWorkMapper")
	private InspectWorkMapper inspectWorkMapper;
	
	@Resource(name="facilityMapper")
	private FacilityMapper facilityMapper;

	@Resource(name="inspectImgMapper")
	private InspectImgMapper inspectImgMapper;
	
	@Resource(name="pushService")
	private PushService pushService;
	
	/**
	 * 
	 * @Title: save
	 * @Description: 巡检任务编辑
	 * @param @param work
	 * @param @return
	 * @return String    返回类型
	 * @author DZY （添加注释）
	 * @throws
	 * @date 2018年2月24日 上午9:04:39
	 * @version V1.0
	 */
	@Override
	public int save(InspectWork work, Users user) {
		if(work.getWorkId()!=null){
			return inspectWorkMapper.updateByPrimaryKeySelective(work);
		}else {
			work.setWorkType("0");
			int iw = inspectWorkMapper.insertSelective(work);
			if(iw>0) {
				//推送巡检任务
				pushService.pushInspectWork(work,user);
			}
			return iw;
		}
	}

	@Override
	public InspectWork selectById(Long id) {
		// TODO Auto-generated method stub
		return inspectWorkMapper.selectByPrimaryKey(id);
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return inspectWorkMapper.deleteByPrimaryKey(id);
	}

	@SuppressWarnings("static-access")
	@Override
	public List<InspectWork> queryList(InspectWork fa, PageBean pb,Users user) {
		Map<String,Object> map = new HashMap<String,Object>();
		Date selectDate = null;
		if(fa.getUserNameWork() == null){
			if(fa!=null && fa.getCreateTime()!=null){
				selectDate = fa.getCreateTime();
			}else{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
				try {
					selectDate = sdf.parse(sdf.format(new Date()));
					fa.setCreateTime(selectDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Calendar   calendar   =   new   GregorianCalendar(); 
		    calendar.setTime(selectDate); 
		    calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动 
			fa.setEndTime(calendar.getTime());
			map.put("fa", fa);
			map.put("pb", pb);
			map.put("user", user);
			return inspectWorkMapper.queryListByMap(map);
		}
		map.put("fa", fa);
		map.put("pb", pb);
		map.put("user", user);
		return inspectWorkMapper.queryListByMapByWeb(map);
	}

	@Override
	public int queryCountByList(InspectWork fa,Users user) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("fa", fa);
		map.put("user", user);
		if(fa.getUserNameWork() == null){
			return inspectWorkMapper.queryCountByMap(map);
		}
		return inspectWorkMapper.queryCountByMapByWeb(map);
	}
	
	@Override
	public List<InspectWork> queryListByCondition(InspectWorkConditionBean cb,
			PageBean pb,Users user) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cb", cb);
		map.put("pb", pb);
		map.put("user", user);
		return inspectWorkMapper.queryListByCondition(map);
	}
	
	@Override
	public int queryCountByCondition(InspectWorkConditionBean cb,Users user) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cb", cb);
		map.put("user", user);
		return inspectWorkMapper.queryCountByCondition(map);
	}

	@Override
	public List<InspectWorkDev> queryDevList(Long workId) {
		// TODO Auto-generated method stub
		List<InspectWorkDev> list = new ArrayList<InspectWorkDev>();
		InspectWork iw = inspectWorkMapper.selectByPrimaryKey(workId);
		if(iw!=null && iw.getDevIds()!=null ){
			String[] devIds = iw.getDevIds().split(",");
			InspectWorkDev inspectWorkDev = null;
			Map<String, Object> map = null;
			for(String devId : devIds){
				Facility f = facilityMapper.selectByPrimaryKey(Long.parseLong(devId));
				if(f != null && !"04".equals(f.getDevType()) && !"30".equals(f.getDevType())
						&& !"31".equals(f.getDevType()) && !"32".equals(f.getDevType())
						&& !"33".equals(f.getDevType())){
					inspectWorkDev = new InspectWorkDev();
					map = new HashMap<String, Object>();
					map.put("devId", devId);
					map.put("workId", workId);
					inspectWorkDev.setDevId(Long.parseLong(devId));
					
					inspectWorkDev.setDevCode(f.getDevCode());
					inspectWorkDev.setDevName(f.getDevName());
					inspectWorkDev.setLatitude(f.getLatitude());
					inspectWorkDev.setLongitude(f.getLongitude());
				
					InspectImg inspectImg = inspectImgMapper.selectByWorkId(map);
					if(inspectImg != null){
						inspectWorkDev.setWorkType("1");
						inspectWorkDev.setImgDesc(inspectImg.getImgDesc());
						inspectWorkDev.setImgUrl(inspectImg.getImgUrl());
					}else{
						inspectWorkDev.setWorkType("0");
					}
					list.add(inspectWorkDev);
				}
				map = null;
				inspectWorkDev = null;
			}
			return list;
		}
		return null;
	}

	/**
	 * 
	 * @Title: queryNotCompletedCount
	 * @Description: 查询未完成巡检任务的数量
	 * @param @param user
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年2月13日 上午9:38:04
	 * @version V1.0
	 */
	@Override
	public int queryNotCompletedCount(Users user) {
		Map<String,Object> map = new HashMap<String,Object>();
		InspectWork work = new InspectWork();
		PageBean pb = new PageBean();
		Date selectDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
		try {
			selectDate = sdf.parse(sdf.format(new Date()));
			work.setCreateTime(selectDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar   calendar   =   new   GregorianCalendar(); 
	    calendar.setTime(selectDate); 
	    calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动 
	    work.setEndTime(calendar.getTime());
	    work.setWorkType("0");
		map.put("fa", work);
		map.put("pb", pb);
		map.put("user", user);
		return inspectWorkMapper.queryCountByMapByWeb(map);
	}
}
