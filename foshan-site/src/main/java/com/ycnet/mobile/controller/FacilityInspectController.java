package com.ycnet.mobile.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ycnet.core.util.ResultData;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.FacilityInspect;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.service.FacilityInspectService;
import com.ycnet.frms.vo.FacilityInspectConditionBean;
import com.ycnet.mobile.util.Result;


@RestController
public class FacilityInspectController {

	@Resource(name="facilityInspectService")
	private FacilityInspectService facilityInspectService;
	 
	
	/**
	 * 我的巡检记录 
	 * @author YHT
	 * @time   2016年7月7日 下午5:54:47
	 * @param bean
	 * @return
	 */
	@RequestMapping("/m/facilityInspectList.htm")
	public Object FacilityInspectList(FacilityInspectConditionBean bean,String page,String pagesize,HttpSession session) {
		ResultData result = new ResultData();
		try {
			Users user=(Users)session.getAttribute("users");
			bean.setUserId(user.getUserId());
			result = facilityInspectService.queryByConditionBean(bean,page,pagesize);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setR("0");
			result.setR_content("查询我的巡检记录出现异常！");
	    	return result;
		}
		
		return result;
	}
	
	/**
	 * 查询巡检详细信息
	 * @author YHT
	 * @time   2016年7月8日 上午9:52:36
	 * @param inspectedId
	 * @return
	 */
	@RequestMapping("/m/facilityInspectInfo.htm")
	public ResultData findFacility(String inspectedId) {
		ResultData result = new ResultData();
		try {
			if(inspectedId != null){
				result = facilityInspectService.selectById(inspectedId);
			}else{
				result.setR("0");
				result.setR_content("巡检id不能为空！");
				return result;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
		
	}
	
	/**
	 * 上报巡检信息
	 * @author YHT
	 * @time   2016年7月7日 下午2:57:35
	 * @param facilityInspect
	 * @return
	 */
	@RequestMapping("/m/saveFacilityInspect.htm")
	public ResultData saveFacility(FacilityInspect facilityInspect,HttpSession session){
		ResultData result = new ResultData();
		try {
			Users user=(Users)session.getAttribute("users");
			facilityInspect.setUserId(user.getUserId());
			facilityInspect.setUserName(user.getUserName());
			result = facilityInspectService.save(facilityInspect);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setR("0");
			result.setR_content("上报巡检信息出现异常！");
	    	return result;
		}
		
		return result;
		
		
	}
	
	/*
	 * 巡检历史记录
	 */
	@RequestMapping("/m/facilityInspectHisoryList.htm")
	public Object facilityInspectHisoryList(FacilityInspectConditionBean bean,String page,String pagesize,HttpSession session) {
		ResultData result = new ResultData();
		try {
			Users user=(Users)session.getAttribute("users");
			bean.setUserId(user.getUserId());
			result = facilityInspectService.queryByConditionBean(bean,page,pagesize);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setR("0");
			result.setR_content("查询巡检记录出现异常！");
	    	return result;
		}
		
		return result;
	}
	/**
	 * 
	* @Title: queryFacilityInspectByDevId 
	* @Description: 根据设施ID查询巡检历史任务 
	* @param @param devId
	* @param @param session
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年2月9日 下午2:07:35 
	* @version V1.0
	 */
	@RequestMapping("/m/queryFacilityInspectByDevId.htm")
	public Object queryFacilityInspectByDevId(Facility facility,HttpSession session) {
		Result r = Result.get();
		try {
			Users user=(Users)session.getAttribute("users");
			if(facility.getDevId()==null) {
				r.setR(0);
				r.setR_content("设施ID为空，请联系管理员！");
				return r;
			}
			List<FacilityInspect> faInsList=facilityInspectService.queryFacilityInspectByDevId(facility);
			if(faInsList != null && faInsList.size()>0) {
				r.setR(1);
				r.setDtList(faInsList);
				r.setR_content("查询成功！");
			}else {
				r.setR(0);
				r.setR_content("未查询到数据！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			r.setR(0);
			r.setR_content("查询数据有误！");
		}
		return r;
	}
	
}
