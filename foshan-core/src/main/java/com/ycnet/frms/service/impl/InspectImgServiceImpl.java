package com.ycnet.frms.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.FacilityImg;
import com.ycnet.frms.bean.FacilityInspect;
import com.ycnet.frms.bean.InspectImg;
import com.ycnet.frms.bean.InspectWork;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.FacilityInspectMapper;
import com.ycnet.frms.mapper.FacilityMapper;
import com.ycnet.frms.mapper.InspectImgMapper;
import com.ycnet.frms.mapper.InspectWorkMapper;
import com.ycnet.frms.mapper.UsersMapper;
import com.ycnet.frms.service.FacilityImgService;
import com.ycnet.frms.service.InspectImgService;

@Service("inspectImgService")
@Transactional
public class InspectImgServiceImpl implements InspectImgService{

	@Resource(name="inspectImgMapper")
	private InspectImgMapper inspectImgMapper;
	
	@Resource(name="facilityMapper")
	private FacilityMapper facilityMapper;
	
	@Resource(name="usersMapper")
	private UsersMapper usersMapper;
	
	@Resource(name="inspectWorkMapper")
	private InspectWorkMapper inspectWorkMapper;

	@Resource(name="facilityInspectMapper")
	private FacilityInspectMapper facilityInspectMapper;
	
	@Resource(name="facilityImgService")
	private FacilityImgService facilityImgService;
	
	@Override
	public int save(InspectImg inspectImg,Long userId) {
		if(inspectImg!=null)
		{
			Map<String,Object> map = new HashMap<String,Object>();
			Long devId = inspectImg.getDevId();
			Facility facility =facilityMapper.selectByPrimaryKey(devId);
			if(facility!=null)
			{
				inspectImg.setDevCode(facility.getDevCode());
			}
			int result = inspectImgMapper.insertSelective(inspectImg);
			if(result>0){
				InspectWork inspectWork = inspectWorkMapper.selectByPrimaryKey(inspectImg.getWorkId());
				String[] devIds= null;
				if(inspectWork.getDevIds() != null){
					devIds = inspectWork.getDevIds().split(",");
				}
				List<InspectImg> Ilist = inspectImgMapper.selectByWorkIds(inspectImg.getWorkId());
				if(inspectWork.getDevIds().contains(inspectImg.getDevId().toString())){
					InspectWork work = new InspectWork();
					work.setWorkId(inspectImg.getWorkId());
					
					//判断巡检任务是否已经巡检完成，如果巡检完成，把巡检任务状态改为已巡检
					int req = 0;
					for (int i = 0; i < devIds.length; i++) {
						String id = devIds[i];
						map.put("devId", id);
						map.put("workId", inspectImg.getWorkId());
						InspectImg img= inspectImgMapper.selectByWorkId(map);
						if(img!=null) {
							req+=1;
						}
					}
					if(req==devIds.length) {
						work.setWorkType("1");
					}
					
					work.setEndTime(new Date());
					int reg=inspectWorkMapper.updateByPrimaryKeySelective(work);
					if(reg>0) {
						FacilityInspect fin=new FacilityInspect();
						fin.setInspectInfo(inspectImg.getImgDesc());
						fin.setDevId(inspectImg.getDevId());
						Date now = new Date();
						fin.setInspectTime(now);
						fin.setUserId(userId);
						fin.setInspectStatus(inspectImg.getInspectStatus());
						Users user = usersMapper.selectByPrimaryKey(userId);
						fin.setUserName(user.getUserName());
						int num = facilityInspectMapper.insertSelective(fin);
						if(num>0) {
							FacilityInspect finIt = facilityInspectMapper.queryByCondition(fin);
							FacilityImg img = new FacilityImg();
							img.setDevId(inspectImg.getDevId());
							img.setDevCode(inspectImg.getDevCode());
							img.setImgTypes("03");
							img.setFlag(inspectImg.getInspectStatus());
							img.setImgDesc(inspectImg.getImgDesc());
							img.setPhotoTime(new Date());
							img.setImgUrl(inspectImg.getImgUrl());
							img.setInspectId(finIt.getInspectId());
							facilityImgService.save(img);
						}
					}
					return reg;
				}
				return result;
			}
		} 
		return 0;
	}

	@Override
	public List<InspectImg> selectImg(InspectImg param) {
		
		return inspectImgMapper.selectImg(param);
	}

	//根据workId查询巡检图片
	@Override
	public List<InspectImg> queryListByWorkId(Long workId) {
		return inspectImgMapper.queryListByWorkId(workId);
	}
	
		
//	public PageBean queryByConditionBean(FacilityImgConditionBean bean,
//			Users user, PageBean pb) {
//		Map<String,Object> conditionMap=new HashMap<String,Object>();
//		conditionMap.put("facilityImgCon", bean);
//		conditionMap.put("user", user);
//		conditionMap.put("pb", pb);
//		
//		pb.setRows(facilityImgMapper.queryCountByConditionMap(conditionMap));//;
//		pb.setList(this.Convert(facilityImgMapper.queryByConditionMap(conditionMap)));		
//				
//		return pb;
//	}
//	
//	/**
//	 * 转换实体bean为业务bean
//	 * @param deviceStatusList
//	 * @return
//	 */
//	private List<FacilityImgConditionBean> Convert(List<FacilityImg> facilityImgList)
//	{
//		List<FacilityImgConditionBean> ficList=new ArrayList<FacilityImgConditionBean>();
//		for(FacilityImg fi:facilityImgList)
//		{
//			FacilityImgConditionBean fic=new FacilityImgConditionBean();
//			BeanUtils.copy(fi, fic);			
//			
//			try
//			{
//				
//			}
//			catch(Exception ex)
//			{
//				
//			}
//			ficList.add(fic);
//		}
//		
//		return ficList;
//	}
	
}
