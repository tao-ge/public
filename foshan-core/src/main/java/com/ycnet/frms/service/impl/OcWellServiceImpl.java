/**   
 * @Package: com.ycnet.frms.service.impl 
 * @author: FL   
 * @date: 2018年10月10日 下午1:06:31 
 */
package com.ycnet.frms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ycnet.core.FrmsException;
import com.ycnet.core.util.LatLng;
import com.ycnet.core.util.Map_Util;
import com.ycnet.frms.bean.OcCableWell;
import com.ycnet.frms.bean.OcSpace;
import com.ycnet.frms.bean.OcWell;
import com.ycnet.frms.bean.OcWellImg;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.FacilityMapper;
import com.ycnet.frms.mapper.OcCableSectionImageMapper;
import com.ycnet.frms.mapper.OcCableSectionMapper;
import com.ycnet.frms.mapper.OcCableWellMapper;
import com.ycnet.frms.mapper.OcPipingSectionMapper;
import com.ycnet.frms.mapper.OcSpaceMapper;
import com.ycnet.frms.mapper.OcWellImgMapper;
import com.ycnet.frms.mapper.OcWellMapper;
import com.ycnet.frms.service.OcWellService;
import com.ycnet.frms.util.UploadUtil;
import com.ycnet.frms.vo.mobile.OcSpaceBean;
import com.ycnet.frms.vo.mobile.WellRelationPipsBean;
import com.ycnet.frms.vo.mobile.WellSpacesBean;
import com.ycnet.frms.vo.mobile.ocii.IndexInBean;
import com.ycnet.frms.vo.mobile.ocii.IndexLine;
import com.ycnet.frms.vo.mobile.ocii.IndexPoint;
import com.ycnet.frms.vo.mobile.ocii.OcSectBean;
import com.ycnet.frms.vo.mobile.ocii.OcWellBean;
import com.ycnet.frms.vo.mobile.ocii.OcWellImgs;

/** 
* @ClassName: OcWellServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月10日 下午1:06:31  
*/
@Service("ocWellService")
@Transactional
public class OcWellServiceImpl implements OcWellService{
	
	@Resource(name="ocWellMapper")
	private OcWellMapper ocWellMapper;
	
	@Resource(name="ocSpaceMapper")
	private OcSpaceMapper ocSpaceMapper;
	
	@Resource(name="ocWellImgMapper")
	private OcWellImgMapper ocWellImgMapper;
	
	@Resource(name="ocCableWellMapper")
	private OcCableWellMapper ocCableWellMapper;
	
	@Resource(name ="ocCableSectionImageMapper")
	private OcCableSectionImageMapper  ocCableSectionImageMapper;
	
	@Resource(name="facilityMapper")
	private FacilityMapper facilityMapper;
	
	@Resource(name ="ocCableSectionMapper")
	private OcCableSectionMapper  ocCableSectionMapper;

	
	@Resource(name ="ocPipingSectionMapper")
	private OcPipingSectionMapper  ocPipingSectionMapper;
	
	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.OcWellService#saveOcciWellFacility(com.ycnet.frms.bean.OcWell, com.ycnet.frms.bean.Users, java.util.List)
	 */
	@Override
	/** 
	 * 光路识别井设施保存
	* @Title: saveOcciWellFacility 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param well
	* @param @param users
	* @param @param imgList
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月10日 下午1:01:01 
	* @version V1.0   
	*/
	public int saveOcciWellFacility(OcWellImgs well, Users users, List<String> imgList) {
		int reg=0;
		well.setOrgId(users.getOrgId());
		well.setWellState("2");
		if(well.getWellId()==null) {
			well.setCreateTime(new Date());
			well.setCreateUser(users.getUserId());//保存
			well.setIsImport("0");
			//fl 修改增加百度 X Y 坐标
			if (well.getLatitude() !=null && !"".equals(well.getLatitude()) && well.getLongitude() !=null && !"".equals(well.getLongitude())) {
				if (well.getBaiduX()==null || well.getBaiduY()==null || "".equals(well.getBaiduX()) || "".equals(well.getBaiduY())) {
					LatLng LatLng = Map_Util.Gps84_To_bd09(Double.valueOf(well.getLatitude()), Double.valueOf(well.getLongitude()));
					well.setBaiduX(String.valueOf(LatLng.getLongitude()));
					well.setBaiduY(String.valueOf(LatLng.getLatitude()));
				}
			}
			OcWell ocWell = cover(well);
			reg=ocWellMapper.insertSelective(ocWell);
			if(reg>0) {
				reg=ocWell.getWellId().intValue();
				if (imgList!=null && imgList.size()>0) {
					for (int j = 0; j < imgList.size(); j++) {
						OcWellImg wellImg = new OcWellImg();
						wellImg.setFlag("1");
						wellImg.setImgTypes("01");
						wellImg.setImgUrl(imgList.get(j));
						wellImg.setPhotoTime(new Date());
						wellImg.setWellId(ocWell.getWellId());
						//保存井图片
						reg += ocWellImgMapper.insertSelective(wellImg);
					}
				}
				for (int i = 0; i < 8; i++) {
					OcSpace ocSpace =new OcSpace();
					ocSpace.setCreateTime(new Date());
					ocSpace.setCreateUser(users.getUserId());
					ocSpace.setOrgId(users.getOrgId());
					ocSpace.setWellId(ocWell.getWellId());
					ocSpace.setSurface(getSurface(i));
					ocSpaceMapper.insertSelective(ocSpace);
					ocSpace=null;
				}	
				
			}
		}else {//修改
			well.setLastModifyTime(new Date());
			well.setLastModifyUser(users.getUserId());
			//fl 修改增加百度 X Y 坐标
			if (well.getLatitude() !=null && !"".equals(well.getLatitude()) && well.getLongitude() !=null && !"".equals(well.getLongitude())) {
				if (well.getBaiduX()==null || well.getBaiduY()==null || "".equals(well.getBaiduX()) || "".equals(well.getBaiduY())) {
					LatLng LatLng = Map_Util.Gps84_To_bd09(Double.valueOf(well.getLatitude()), Double.valueOf(well.getLongitude()));
					well.setBaiduX(String.valueOf(LatLng.getLongitude()));
					well.setBaiduY(String.valueOf(LatLng.getLatitude()));
				}
			}
//			if("0".equals(well.getIsFormerbureau())) {
//				well.setIsFormerbureauState(0);
//			}
			OcWell ocWell = cover(well);
			reg=ocWellMapper.updateWellBean(ocWell);//修改
			if (reg>0) {
				reg=ocWell.getWellId().intValue();
				if (imgList!=null && imgList.size()>0) {
					for (int j = 0; j < imgList.size(); j++) {
						OcWellImg wellImg = new OcWellImg();
						wellImg.setFlag("1");
						wellImg.setImgTypes("01");
						wellImg.setImgUrl(imgList.get(j));
						wellImg.setPhotoTime(new Date());
						wellImg.setWellId(ocWell.getWellId());
						//保存井图片
						reg += ocWellImgMapper.insertSelective(wellImg);
					}
				}
			}
		}
		
		return reg;
	}

	/** 
	 * 实体转换
	* @Title: cover 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param well
	* @param @return    入参
	* @return OcWell    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月16日 下午2:20:22 
	* @version V1.0   
	*/
	private OcWell cover(OcWellImgs well) {
		OcWell ocWell = new OcWell();
		ocWell.setAreaCode(well.getAreaCode());
		ocWell.setBaiduX(well.getBaiduX());
		ocWell.setBaiduY(well.getBaiduY());
		ocWell.setCreateTime(well.getCreateTime());
		ocWell.setCreateUser(well.getCreateUser());
		ocWell.setDevId(well.getDevId());
		ocWell.setIsBranch(well.getIsBranch());
		ocWell.setIsFormerbureau(well.getIsFormerbureau());
		ocWell.setIsImport(well.getIsImport());
		ocWell.setLastModifyTime(well.getLastModifyTime());
		ocWell.setLastModifyUser(well.getLastModifyUser());
		ocWell.setLatitude(well.getLatitude());
		ocWell.setLongitude(well.getLongitude());
		ocWell.setOrgId(well.getOrgId());
		ocWell.setProrightType(well.getProrightType());
		ocWell.setRemark(well.getRemark());
		ocWell.setWellAddr(well.getWellAddr());
		ocWell.setWellId(well.getWellId());
		ocWell.setWellKind(well.getWellKind());		
		ocWell.setWellName(well.getWellName());
		ocWell.setWellNumber(well.getWellNumber());
		ocWell.setWellState(well.getWellState());
		ocWell.setWellType(well.getWellType());
		ocWell.setZgWellName(well.getZgWellName());
		ocWell.setZgWellNumber(well.getZgWellNumber());
		return ocWell;
	}

	/** 
	* @Title: getSurface 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param i
	* @param @return    入参
	* @return String    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月11日 上午11:42:26 
	* @version V1.0   
	*/
	public  String getSurface(int i) {
		String[] surfaces= {"A","B","C","D","E","F","G","H"};
		return surfaces[i];
	}
	
	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.OcWellService#queryWellByWellId(java.lang.Long, com.ycnet.frms.bean.Users)
	 */
	/** 
	 * 查询井基本信息
	* @Title: queryWellByWellId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wellId
	* @param @param users
	* @param @return    入参
	* @return OcWellBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月10日 下午1:32:48 
	* @version V1.0   
	*/
	@Override
	public OcWellBean queryOcciWellByWellId(Long wellId, Users users) {
		//查询井及图片信息
		OcWellBean OcWellBean =ocWellMapper.queryqueryOcciWellAndImg(wellId);
		//查询九宫格:分面\大孔
		if (OcWellBean!=null) {
			List<OcSpaceBean> list=ocSpaceMapper.querySpaceAndPips(wellId);
			if (list!=null &&list.size()>0) {
				OcWellBean.setList(list);
			}
		}
		return OcWellBean;
	}

	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.OcWellService#deleteOciiImage(javax.servlet.http.HttpServletRequest, java.util.List, java.lang.Long)
	 */
	/** 
	 * 根据路径和Id删除数据,和文件
	* @Title: deleteOciiImage 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param imageUrlList
	* @param @param wellId
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月10日 下午2:33:35 
	* @version V1.0   
	*/
	@Override
	public int deleteOciiImage(HttpServletRequest request, List<String> imageUrlList, Long imgId, String imgType) {
		int req = 0;
		Map<String,Object> map = null;
		if(imageUrlList != null && imageUrlList.size()>0) {
			for (String imageUrl : imageUrlList) {
				if(imageUrl != null && !"".equals(imageUrl.trim())) {
					map = new HashMap<String,Object>();
					map.put("imgUrl", imageUrl);
					if (imgType.equals("06")) {
						map.put("wellId", imgId);
						req = ocWellImgMapper.deleteByDevIdAndUrl(map);//根据devId和imgUrl删除井图片
					}
					if (imgType.equals("09")) {
						map.put("sectId", imgId);
						req = ocCableSectionImageMapper.deleteBySectIdAndUrl(map);//根据sectId和imgUrl删除井图片
					}
					req+= UploadUtil.deleteImage(imageUrl, request,imgType);//删除文件
				}
			}
		}
		return req;
	}

	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.OcWellService#saveOciiFacilityImages(javax.servlet.http.HttpServletRequest, com.ycnet.frms.bean.Users)
	 */
	/** 
	 * 保存文件返回路径
	* @Title: saveOciiFacilityImages 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param user
	* @param @return    入参
	* @return List<String>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月10日 下午2:45:17 
	* @version V1.0   
	 * @throws Exception 
	*/
	@Override
	public List<String> saveOciiFacilityImages(HttpServletRequest request, Users user){
		List<String> list = new ArrayList<String>();
		if(request instanceof MultipartHttpServletRequest)
		{
			MultipartHttpServletRequest req = (MultipartHttpServletRequest)request;
			List<MultipartFile> files = req.getFiles("imgList");
			if (files!=null&&files.size()>0) {
				for(int index = 0;index<files.size();index++)
				{
					 String image = UploadUtil.uploadFile(files.get(index), req,user.getOrgId(), "06");//添加井图片
					 list.add(image);
				}
			 }else {
				   throw new FrmsException("文集合不能为空!");
			 }
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.OcWellService#delOcWellById(java.lang.Long)
	 */
	/** 
	 * 根据主键删除井
	* @Title: delOcWellById 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wellId
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月11日 上午8:46:02 
	* @version V1.0   
	*/
	@Override
	public int delOcWellById(Long wellId, Users user) {
		// 有管道段不能删除
		List<WellRelationPipsBean> wellRelationPipsBeanList = ocWellMapper.queryWellRelationPips(wellId, "1",user.getOrgId());
		if (wellRelationPipsBeanList != null && wellRelationPipsBeanList.size() > 0) {
			throw new FrmsException("(" + wellId + ")该井下有管道段不能删除!");
		}
		// 有光缆段不能删除
		List<OcCableWell>ocCableWellList=ocCableWellMapper.queryOcCableWellByWellId(wellId,user.getOrgId());
		if (ocCableWellList != null && ocCableWellList.size() > 0) {
			throw new FrmsException("(" + wellId + ")该井下有光缆段不能删除!");
		}
		int reg = ocWellMapper.deleteByPrimaryKey(wellId);
		if (reg > 0) {// 删除分面
			reg += ocSpaceMapper.deleteByWellId(wellId);
		}
		return reg;
	}

	
	/** 
	 * 查询井关联的管道段
	* @Title: queryWellRelationPips 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wellId
	* @param @param isWell
	* @param @param orgId
	* @param @return    入参
	* @return List<WellRelationPipsBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月15日 上午9:31:22 
	* @version V1.0   
	*/
	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.OcWellService#queryWellRelationPips(java.lang.Long, java.lang.String, java.lang.Long)
	 */
	@Override
	public List<WellRelationPipsBean> queryWellRelationPips(Long wellId, String isWell, Long orgId) {
		return ocWellMapper.queryWellRelationPips(wellId,isWell,orgId);
	}

	
	/** 
	 * 井所关联的光缆段信息
	* @Title: queryWellRelationSections 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wellId
	* @param @param orgId
	* @param @return    入参
	* @return List<WellRelationPipsBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月15日 上午10:39:41 
	* @version V1.0   
	*/
	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.OcWellService#queryWellRelationSections(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<OcSectBean> queryWellRelationSections(Long wellId, Long orgId) {
		return ocWellMapper.queryWellRelationSections(wellId,orgId);
	}


	/**
	 * 根据条件查询首页信息
	* @Title: queryIndexByType 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param iib
	* @param @return    入参
	* @return IndexInBean    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年10月16日 下午1:41:10 
	* @version V1.0
	 */
	@Override
	public IndexInBean queryIndexByType(IndexInBean iib) {
		
		List<IndexPoint> pointList = new ArrayList<IndexPoint>();
		Set<IndexLine> lineSet = new HashSet<IndexLine>();
		//查询设施点
		List<IndexPoint> devList = null;
		//查询井点
		List<IndexPoint> wellList = null;
		//查询管道段 
		List<IndexLine> pipList = null;
		//查询光缆段
		List<IndexLine> sectList = null;
		if("0".equals(iib.getType())) {
			devList = facilityMapper.OcIndexFacByType(iib);
			wellList = ocWellMapper.OcIndexWellByType(iib);
			pointList.addAll(wellList);
			pointList.addAll(devList);
			//处理光缆段去重
			sectList = ocCableSectionMapper.OcIndexSectByType(iib);
			if(sectList!=null && sectList.size()>0) {
				for(IndexLine il : sectList) {
					IndexLine il_bak = null;
					if(il.getDevIdA() > il.getDevIdZ()) {
						il_bak = new IndexLine();
						il_bak.setType(il.getType());
						il_bak.setDevState(il.getDevState());
						il_bak.setDevIdA(il.getDevIdZ());
						il_bak.setDevNameA(il.getDevNameZ());
						il_bak.setCurLatA(il.getCurLatZ());
						il_bak.setCurLngA(il.getCurLngZ());
						il_bak.setDevIdZ(il.getDevIdA());
						il_bak.setDevNameZ(il.getDevNameA());
						il_bak.setCurLatZ(il.getCurLatA());
						il_bak.setCurLngZ(il.getCurLngA());
						lineSet.add(il_bak);
					}else {
						lineSet.add(il);
					}
				}
			}
			//处理管道段去重
			pipList = ocPipingSectionMapper.OcIndexPipByType(iib);
			if(pipList!=null && pipList.size()>0) {
				for(IndexLine il : pipList) {
					IndexLine il_bak = null;
					if(il.getDevIdA() > il.getDevIdZ()) {
						il_bak = new IndexLine();
						il_bak.setType(il.getType());
						il_bak.setDevState(il.getDevState());
						il_bak.setDevIdA(il.getDevIdZ());
						il_bak.setDevNameA(il.getDevNameZ());
						il_bak.setCurLatA(il.getCurLatZ());
						il_bak.setCurLngA(il.getCurLngZ());
						il_bak.setDevIdZ(il.getDevIdA());
						il_bak.setDevNameZ(il.getDevNameA());
						il_bak.setCurLatZ(il.getCurLatA());
						il_bak.setCurLngZ(il.getCurLngA());
						lineSet.add(il_bak);
					}else {
						lineSet.add(il);
					}
				}
			}

		}else {
			//处理多选情况
			String[] typeArr = iib.getType().split(",");
			//初始类型
			String typeInitial = iib.getType();
			if(typeArr!=null && typeArr.length>0) {
				for(String type : typeArr) {
					if("20".equals(type)) {
						iib.setType("20");
						devList = facilityMapper.OcIndexFacByType(iib);
						pointList.addAll(devList);
						devList = null;
					}else if("01".equals(type)) {
						iib.setType("01");
						devList = facilityMapper.OcIndexFacByType(iib);
						pointList.addAll(devList);
						devList = null;
					}else if("3".equals(type)) {
						wellList = ocWellMapper.OcIndexWellByType(iib);
						pointList.addAll(wellList);
						wellList = null;
					}else if("4".equals(type)) {
						pipList = ocPipingSectionMapper.OcIndexPipByType(iib);
						lineSet.addAll(pipList);
					}else if("5".equals(type)) {
						sectList = ocCableSectionMapper.OcIndexSectByType(iib);
						lineSet.addAll(sectList);
					}
				}
				iib.setType(typeInitial);
			}
		}
		iib.setPointList(pointList);
		iib.setLineSet(lineSet);
		
		return iib;
	}

	/** 
	 * 根据井ID查询井和其面信息
	* @Title: queryOciiWellAndSpaces 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wellId
	* @param @param user
	* @param @return    入参
	* @return WellSpacesBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月19日 下午3:39:49 
	* @version V1.0   
	*/
	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.OcWellService#queryOciiWellAndSpaces(java.lang.Long, com.ycnet.frms.bean.Users)
	 */
	@Override
	public WellSpacesBean queryOciiWellAndSpaces(Long wellId, Users user) {
		return ocWellMapper.queryOciiWellAndSpaces(wellId);
	}
	
}
