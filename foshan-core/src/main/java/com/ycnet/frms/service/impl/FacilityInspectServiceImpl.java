package com.ycnet.frms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.core.util.PageBean;
import com.ycnet.core.util.ResultData;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.FacilityImg;
import com.ycnet.frms.bean.FacilityInspect;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.FacilityImgMapper;
import com.ycnet.frms.mapper.FacilityInspectMapper;
import com.ycnet.frms.mapper.FacilityMapper;
import com.ycnet.frms.service.FacilityInspectService;
import com.ycnet.frms.vo.FacilityInspectConditionBean;

@Service("facilityInspectService")
public class FacilityInspectServiceImpl implements FacilityInspectService{
	
	
	@Resource(name="facilityInspectMapper")
	private FacilityInspectMapper facilityInspectMapper;

	@Resource(name="facilityImgMapper")
	private FacilityImgMapper facilityImgMapper;
	
	@Resource(name="facilityMapper")
	private FacilityMapper facilityMapper;
	
	@Override
	public ResultData save(FacilityInspect facilityInspect) {
		// TODO Auto-generated method stub
		ResultData result = new ResultData();
		int ResultNum = 0;
		
		ResultNum = facilityInspectMapper.insert(facilityInspect);
	
		if(ResultNum == 1){
			result.setR(facilityInspect.getInspectId() + "");
			result.setR_content("上报巡检信息成功！");
			return result;
		}
		result.setR("0");
		result.setR_content("上报巡检信息失败！");
		return result;
	}


	@Override
	public ResultData queryByConditionBean(FacilityInspectConditionBean bean,String page,String pagesize) {
		// TODO Auto-generated method stub
		ResultData result = new ResultData();
		List<FacilityInspect> list = new ArrayList<FacilityInspect>();
		int p,ps;
		if(page == null){
			p = 1;
		}else{
			p = Integer.parseInt(page);
		}
		if(pagesize == null){
			ps = 20;
		}else{
			ps = Integer.parseInt(pagesize);
		}
		
		bean.setPg((p-1)*ps);
		bean.setPs(ps);
		
		list = facilityInspectMapper.queryByConditionBean(bean);
		if(list.size() > 0){
			result.setDt(list);
			result.setR(list.size() +"");
			result.setR_content("查询我的巡检记录成功！");
			return result;
		}
		result.setR("0");
		result.setR_content("无数据");
		return result;
	}


	@Override
	public ResultData selectById(String inspectedId) {
		// TODO Auto-generated method stub
		ResultData result = new ResultData();
		FacilityInspect facilityInspect = new FacilityInspect();
		List<FacilityImg> list = new ArrayList<FacilityImg>();
		facilityInspect = facilityInspectMapper.selectByPrimaryKey(Long.parseLong(inspectedId));
		
		if(facilityInspect != null){
			//查询巡检所对应的设施图片信息
			list = facilityImgMapper.selectByInspectedId(Long.parseLong(inspectedId));
			if(list.size() > 0){
				result.setDt(facilityInspect);
				result.setDtpic(list);
				result.setR(list.size() +"");
				result.setR_content("查询巡检详细信息成功！");
				return result;
			}
			
		}
		result.setR("0");
		result.setR_content("无数据");
		return result;
	}


	@Override
	public FacilityInspect selectByIds(Long inspectedId) {
		// TODO Auto-generated method stub
		return facilityInspectMapper.selectByPrimaryKey(inspectedId);
	}


	@Override
	public PageBean queryByConditionBeans(FacilityInspectConditionBean bean,PageBean pb,Users user) {
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("InspectCondition", bean);
		conditionMap.put("pb", pb);
		conditionMap.put("user", user);
		pb.setRows(facilityInspectMapper.queryCountByConditionMap(conditionMap));//;
		List<FacilityInspect> finList=facilityInspectMapper.queryByConditionBeans(conditionMap);
		if(finList!=null && finList.size()>0) {
			for(FacilityInspect fin:finList) {
				if(fin.getDevId()!=null && !"".equals(fin.getDevId().toString())) {
					Facility fa = facilityMapper.selectByPrimaryKey(fin.getDevId());
					if(fa!=null) {
						if(fa.getBaiduX()!=null && !"".equals(fa.getBaiduX().toString()) && 
								fa.getBaiduY()!=null && !"".equals(fa.getBaiduY().toString())) {
							fin.setBaiduX(fa.getBaiduX().toString());
							fin.setBaiduY(fa.getBaiduY().toString());
						}
						fin.setDevCode(fa.getDevCode());
						fin.setDevName(fa.getDevName());
					}
				}
			}
		}
		pb.setList(finList);		
				
		return pb;
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
	@Override
	public List<FacilityInspect> queryFacilityInspectByDevId(Facility facility) {
		List<FacilityInspect> faiList=facilityInspectMapper.queryFacilityInspectByDevId(facility);
		if(faiList !=null && faiList.size()>0) {
			for(FacilityInspect faIn:faiList) {
				Facility fa = facilityMapper.selectByPrimaryKey(facility.getDevId());
				faIn.setDevCode(fa.getDevCode());
				faIn.setDevName(fa.getDevName());
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("inspectId", faIn.getInspectId());
				map.put("devId", facility.getDevId());
				FacilityImg img = facilityImgMapper.selectByInspectedIdAndDevId(map);
				if(img!= null) {
					faIn.setImgUrl(img.getImgUrl());
				}
				
			}
		}
		return faiList;
	}
	
	
}
