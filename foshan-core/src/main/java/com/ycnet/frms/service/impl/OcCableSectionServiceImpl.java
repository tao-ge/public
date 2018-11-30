/**   
 * @Package: com.ycnet.frms.service.impl 
 * @author: FL   
 * @date: 2018年10月11日 下午3:54:36 
 */
package com.ycnet.frms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ycnet.core.FrmsException;
import com.ycnet.frms.bean.OcCableSection;
import com.ycnet.frms.bean.OcCableSectionImage;
import com.ycnet.frms.bean.OcPipingCable;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.FacilityMapper;
import com.ycnet.frms.mapper.OcCableSectionImageMapper;
import com.ycnet.frms.mapper.OcCableSectionMapper;
import com.ycnet.frms.mapper.OcCableWellMapper;
import com.ycnet.frms.mapper.OcPipingCableMapper;
import com.ycnet.frms.mapper.OcWellMapper;
import com.ycnet.frms.service.OcCableSectionService;
import com.ycnet.frms.util.UploadUtil;
import com.ycnet.frms.vo.mobile.ocii.OcCableSectionImgs;
import com.ycnet.frms.vo.mobile.ocii.OcSectBean;
import com.ycnet.frms.vo.mobile.ocii.OcWellBean;
import com.ycnet.frms.vo.mobile.ocii.OciiFacilityBean;

/** 
* @ClassName: OcCableSectionServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月11日 下午3:54:36  
*/
@Service("ocCableSectionService")
@Transactional
public class OcCableSectionServiceImpl implements OcCableSectionService{

	@Resource(name ="ocCableSectionMapper")
	private OcCableSectionMapper ocCableSectionMapper;
	
	@Resource(name ="ocPipingCableMapper")
	private OcPipingCableMapper ocPipingCableMapper;
	
	@Resource(name ="ocCableSectionImageMapper")
	private OcCableSectionImageMapper  ocCableSectionImageMapper;
	
	@Resource(name ="ocCableWellMapper")
	private OcCableWellMapper  ocCableWellMapper;
	
	@Resource(name ="ocWellMapper")
	private OcWellMapper  ocWellMapper;
	
	@Resource(name ="facilityMapper")
	private FacilityMapper  facilityMapper;
	
	
	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.OcCableSectionService#queryOciiFacilityByPdevId(java.lang.Long)
	 */
	@Override
	/** 
	 * 查询光交箱下的关联光缆段
	* @Title: queryOciiFacilityByPdevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return List<OcSectBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月11日 下午3:53:50 
	* @version V1.0   
	*/
	public List<OcSectBean> queryOciiFacilityByPdevId(Long devId, Long orgId) {
		return ocCableSectionMapper.queryOciiFacilityByPdevId(devId,orgId);
	}

	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.OcCableSectionService#delOciiCableSection(java.lang.Long)
	 */
	/** 
	 * 删除物理光缆段
	* @Title: delOciiCableSection 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param sectId
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月12日 下午4:00:39 
	* @version V1.0   
	*/
	@Override
	public int delOciiCableSection(Long sectId) {
		int reg = ocCableSectionMapper.deleteByPrimaryKey(sectId);
		if (reg > 0) {
			// 删除光缆段与管道段大孔关系表
			List<OcPipingCable> ocPipingCableList = ocPipingCableMapper.selectBySectId(sectId);
			if (ocPipingCableList != null && ocPipingCableList.size() > 0) {
				for (int i = 0; i < ocPipingCableList.size(); i++) {
					reg += ocPipingCableMapper.deleteByPrimaryKey(ocPipingCableList.get(i).getSubtubeId());
				}
			}
			// 删除图片表
			List<OcCableSectionImage> imgList = ocCableSectionImageMapper.queryImagesBySectId(sectId);
			if (imgList != null && imgList.size() > 0) {
				for (int i = 0; i < imgList.size(); i++) {
					reg += ocCableSectionImageMapper.deleteByPrimaryKey(imgList.get(i).getSectImageId());
				}
			}
		}
		return reg;
	}

	/** 
	 * 添加修改物理光缆段
	* @Title: saveOciiCableSection 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param ocCableSection
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月15日 下午1:42:30 
	* @version V1.0   
	*/
	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.OcCableSectionService#saveOciiCableSection(com.ycnet.frms.bean.OcCableSection)
	 */
	@Override
	public int saveOciiCableSection(OcCableSectionImgs ocCableSection, Users user, List<String> imgList) {
		int reg = 0;
		if (ocCableSection.getSectId() == null) {// 添加
			ocCableSection.setOrgId(user.getOrgId());
			ocCableSection.setCreateTime(new Date());
			ocCableSection.setCreateUser(user.getUserId());
			ocCableSection.setSectState("2");
			OcCableSection ocCable = conver(ocCableSection);
			reg = ocCableSectionMapper.insertSelective(ocCable);
		} else {// 修改
			ocCableSection.setLastModifyTime(new Date());
			ocCableSection.setLastModifyUser(user.getUserId());
			OcCableSection ocCable = conver(ocCableSection);
			reg += ocCableSectionMapper.updateByPrimaryKeySelective(ocCable);
		}
		if (reg>0) {
			if (imgList!=null && imgList.size()>0) {
				OcCableSection ocCable = conver(ocCableSection);
				for (int i = 0; i < imgList.size(); i++) {
					OcCableSectionImage ocCableSectionImage = new OcCableSectionImage();
					ocCableSectionImage.setCreateTime(new Date());
					ocCableSectionImage.setCreateUser(user.getUserId());
					ocCableSectionImage.setImgUrl(imgList.get(i));
					ocCableSectionImage.setSectId(ocCable.getSectId());
					ocCableSectionImageMapper.insertSelective(ocCableSectionImage);
					ocCableSectionImage=null;
				}
			}
		}
		return reg;
	}

	/** 
	 * 实体转换
	* @Title: conver 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param ocCableSection
	* @param @return    入参
	* @return OcCableSection    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月16日 下午3:04:20 
	* @version V1.0   
	*/
	private OcCableSection conver(OcCableSectionImgs ocCable) {
		OcCableSection cableSection = new OcCableSection();
		
		cableSection.setCableLen(ocCable.getCableLen());
		cableSection.setCreateTime(ocCable.getCreateTime());
		cableSection.setCreateUser(ocCable.getCreateUser());
		cableSection.setDevIdA(ocCable.getDevIdA());
		cableSection.setDevIdZ(ocCable.getDevIdZ());
		
		cableSection.setDigitalSigns(ocCable.getDigitalSigns());
		cableSection.setFiberNum(ocCable.getFiberNum());
		cableSection.setIsEndfibercable(ocCable.getIsEndfibercable());
		cableSection.setIsRecogn(ocCable.getIsRecogn());
		cableSection.setLastModifyTime(ocCable.getLastModifyTime());
		
		cableSection.setLastModifyUser(ocCable.getLastModifyUser());
		cableSection.setOrgId(ocCable.getOrgId());
		cableSection.setRemark(ocCable.getRemark());
		cableSection.setSecName(ocCable.getSecName());
		cableSection.setSectId(ocCable.getSectId());
		cableSection.setSectState(ocCable.getSectState());
		
		return cableSection;
	}

	/** 
	 * 保存物理光缆图片
	* @Title: saveOciiCableImages 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param user
	* @param @return    入参
	* @return List<String>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月15日 下午3:19:54 
	* @version V1.0   
	*/
	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.OcCableSectionService#saveOciiCableImages(javax.servlet.http.HttpServletRequest, com.ycnet.frms.bean.Users)
	 */
	@Override
	public List<String> saveOciiCableImages(HttpServletRequest request, Users user) {
		List<String> list = new ArrayList<String>();
		if(request instanceof MultipartHttpServletRequest)
		{
			MultipartHttpServletRequest req = (MultipartHttpServletRequest)request;
			List<MultipartFile> files = req.getFiles("imgList");
			if (files!=null&&files.size()>0) {
				for(int index = 0;index<files.size();index++)
				{
					 String image = UploadUtil.uploadFile(files.get(index), req,user.getOrgId(), "09");//添加井图片
					 list.add(image);
				}
			 }else {
				   throw new FrmsException("文集合不能为空!");
			 }
		}
		return list;
	}


	/** 
	 * 根据设施/井ID,查询光缆详情(识别详情)
	* @Title: queryOciiSectByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @param type
	* @param @param user
	* @param @return    入参
	* @return Map<String,Object>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月16日 上午9:55:37 
	* @version V1.0   
	*/
	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.OcCableSectionService#queryOciiSectByDevId(java.lang.Long, java.lang.String, com.ycnet.frms.bean.Users)
	 */
	@Override
	public Map<String, Object> queryOciiSectByDevId(Long devId, String type, Users user) {
		Map<String, Object> map = new HashMap<String,Object>();
		//根据设施ID,查询识别光缆数
		Map<String, Object> map1=ocCableWellMapper.queryOcCableWellByDevId(devId,type);
		if (map1!=null) {
			map.putAll(map1);
		}else {
			map.put("isRecogn", 0);
			map.put("noRecogn", 0);
		}
		map.put("areaName", "");
		map.put("name", "");
		//查询设施详情
		if (type.equals("0")) {//设施
			OciiFacilityBean f = facilityMapper.queryOciiFacilityByDevId(devId);
			if (f!=null) {
				if (f.getDevAddr()!=null && !"".equals(f.getDevAddr())) {
					map.put("areaName", f.getDevAddr());
				}
				if (f.getDevName()!=null && !"".equals(f.getDevName())) {
					map.put("name", f.getDevName());
				}
			}
		}else if(type.equals("1")) {//井
			OcWellBean ocWell = ocWellMapper.queryqueryOcciWellAndImg(devId);
			if (ocWell!=null) {
				if (ocWell.getWellAddr()!=null && !"".equals(ocWell.getWellAddr())) {
					map.put("areaName", ocWell.getWellAddr());
				}
				if (ocWell.getWellName()!=null && !"".equals(ocWell.getWellName())) {
					map.put("name", ocWell.getWellName());
				}
			}
		}
		
		return map;
	}



}
