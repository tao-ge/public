package com.ycnet.frms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.core.Constants;
import com.ycnet.core.util.BeanUtils;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.DeviceStatus;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.FacilityImg;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.FacilityImgMapper;
import com.ycnet.frms.mapper.FacilityMapper;
import com.ycnet.frms.service.FacilityImgService;
import com.ycnet.frms.vo.DeviceStatusBean;
import com.ycnet.frms.vo.DeviceStatusConditionBean;
import com.ycnet.frms.vo.FacilityImgConditionBean;

@Service("facilityImgService")
@Transactional
public class FacilityImgServiceImpl implements FacilityImgService{

	@Resource(name="facilityImgMapper")
	private FacilityImgMapper facilityImgMapper;
	
	@Resource(name="facilityMapper")
	private FacilityMapper facilityMapper;

	@Override
	public int save(FacilityImg facilityImg) {
		if(facilityImg!=null)
		{
			Long devId = facilityImg.getDevId();
			Facility facility =facilityMapper.selectByPrimaryKey(devId);
			if(facility!=null)
			{
				facilityImg.setDevCode(facility.getDevCode());
			}
			return facilityImgMapper.insertSelective(facilityImg);
		}
		return 0;
	}

	@Override
	public List<FacilityImg> selectImg(FacilityImg param) {
		
		return facilityImgMapper.selectImg(param);
	}
	
		
	public PageBean queryByConditionBean(FacilityImgConditionBean bean,
			Users user, PageBean pb) {
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("facilityImgCon", bean);
		conditionMap.put("user", user);
		conditionMap.put("pb", pb);
		
		pb.setRows(facilityImgMapper.queryCountByConditionMap(conditionMap));//;
		pb.setList(this.Convert(facilityImgMapper.queryByConditionMap(conditionMap)));		
				
		return pb;
	}
	
	/**
	 * 转换实体bean为业务bean
	 * @param deviceStatusList
	 * @return
	 */
	private List<FacilityImgConditionBean> Convert(List<FacilityImg> facilityImgList)
	{
		List<FacilityImgConditionBean> ficList=new ArrayList<FacilityImgConditionBean>();
		for(FacilityImg fi:facilityImgList)
		{
			FacilityImgConditionBean fic=new FacilityImgConditionBean();
			BeanUtils.copy(fi, fic);			
			
			try
			{
				
			}
			catch(Exception ex)
			{
				
			}
			ficList.add(fic);
		}
		
		return ficList;
	}

	@Override
	public List<FacilityImg> selectByDevId(Long devId) {
		return facilityImgMapper.selectByDevId(devId);
	}

	@Override
	public FacilityImg selectByDevUrl(String paths) {
		return facilityImgMapper.selectByDevUrl(paths);
	}

	@Override
	public int saveUrl(FacilityImg facilityImg) {
		return facilityImgMapper.saveUrl(facilityImg);
	}

	@Override
	public int deleteUrl(String paths) {
		return facilityImgMapper.deleteUrl(paths);
	}

	//导出数据库  刘沧海 2017/10/13
	@Override
	public List<FacilityImg> queryList() {
		return facilityImgMapper.queryList();
	}

	
	
}
