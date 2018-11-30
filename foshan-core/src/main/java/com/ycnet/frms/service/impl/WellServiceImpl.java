package com.ycnet.frms.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ycnet.core.DateState;
import com.ycnet.core.FrmsException;
import com.ycnet.core.util.LatLng;
import com.ycnet.core.util.Map_Util;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.CableSection;
import com.ycnet.frms.bean.CableSectionImage;
import com.ycnet.frms.bean.Colors;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.Piping;
import com.ycnet.frms.bean.PipingCable;
import com.ycnet.frms.bean.PipingSectWell;
import com.ycnet.frms.bean.PipingSection;
import com.ycnet.frms.bean.Space;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.bean.Well;
import com.ycnet.frms.bean.WellImg;
import com.ycnet.frms.mapper.CableSectionMapper;
import com.ycnet.frms.mapper.ColorsMapper;
import com.ycnet.frms.mapper.FacilityMapper;
import com.ycnet.frms.mapper.PipingCableMapper;
import com.ycnet.frms.mapper.PipingMapper;
import com.ycnet.frms.mapper.PipingSectWellMapper;
import com.ycnet.frms.mapper.PipingSectionMapper;
import com.ycnet.frms.mapper.SpaceMapper;
import com.ycnet.frms.mapper.WellImgMapper;
import com.ycnet.frms.mapper.WellMapper;
import com.ycnet.frms.service.CableSectionImageService;
import com.ycnet.frms.service.WellService;
import com.ycnet.frms.util.ChineseCharToEn;
import com.ycnet.frms.util.GoogleToGPSUtil;
import com.ycnet.frms.util.UploadUtil;
import com.ycnet.frms.vo.PipingBean;
import com.ycnet.frms.vo.PipingSectionBean;
import com.ycnet.frms.vo.WellBean;

/**
 * 
* @ClassName: WellServiceImpl 
* @Description: TODO(井，管道专用实现类) 
* @author (刘沧海)  
* @date 2017年12月21日 下午3:04:13 
* @version V1.0
 */
@Service("wellService")
@Transactional
public class WellServiceImpl implements WellService {
	
	@Resource(name="wellMapper")
	private WellMapper wellMapper;
	
	@Resource(name="wellService")
	private WellService wellService;
	
	@Resource(name="pipingSectWellMapper")
	private PipingSectWellMapper pipingSectWellMapper;
	
	@Resource(name="pipingSectionMapper")
	private PipingSectionMapper pipingSectionMapper;
	
	@Resource(name="pipingMapper")
	private PipingMapper pipingMapper;
	
	@Resource(name="pipingCableMapper")
	private PipingCableMapper pipingCableMapper;
	
	@Resource(name="cableSectionMapper")
	private CableSectionMapper cableSectionMapper;
	
	@Resource(name="cableSectionImageService")
	private CableSectionImageService cableSectionImageService;
	
	@Resource(name="spaceMapper")
	private SpaceMapper spaceMapper;
	
	@Resource(name="colorsMapper")
	private ColorsMapper colorsMapper;
	
	@Resource(name="facilityMapper")
	private FacilityMapper facilityMapper;
	
	@Resource(name="wellImgMapper")
	private WellImgMapper wellImgMapper;
	
	/**
	 * 井信息（关联每个井对应的管道段）
	 */
	@Override
	public List<WellBean> queryWells(WellBean well,Long distance,PageBean pb,Users users) {
		//获取首字母  必须大于三个汉字,才获取首字母
		StringBuffer sb = new StringBuffer();  
		if(well.getWellName() !=null && well.getWellName().length()>3) {
			ChineseCharToEn cte = new ChineseCharToEn(); 
			String spell =  cte.getAllFirstLetter(well.getWellName());
			//变成大写  
			for(int i=0;i<spell.length();i++){  
				char c = spell.charAt(i);  
				sb.append(Character.toUpperCase(c));   
			}
		}
		String Upper = sb.toString();
		List<WellBean> list = wellMapper.queryWells(well,Upper,pb,distance,users.getOrgId());
		if (list !=null && list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getDevId()!=null) {
					Facility fa = facilityMapper.selectByPrimaryKey(list.get(i).getDevId());
					list.get(i).setDevCode(fa.getDevCode());//局前井设施编号
					list.get(i).setDevName(fa.getDevName());//局前井设施名称
				}
				//查询管道段
//				List<PipingSection> pipingSectionlist=pipingSectionMapper.queryByWellId(list.get(i).getWellId());
				List<PipingSectionBean> pipingSectionlist= pipingSectionMapper.queryAllByWellId(list.get(i).getWellId());
				 if (pipingSectionlist !=null && pipingSectionlist.size()>0) {
					 list.get(i).setPipingSectionListBean(pipingSectionlist);
				 }
			}
		}
		return list;
	}
	/**
	 * 
	* @Title: saveWellFacility 
	* @Description: 保存和修改井设施 
	* @param @param well
	* @param @param users
	* @param @return    
	* @return int
	* @author liucanghai 
	* @throws
	* @date 2017年12月22日 上午10:45:45 
	* @version V1.0
	 */
	@Override
	public int saveWellFacility(Well well, Users users,HttpServletRequest request) {
		int reg=0;
		well.setOrgId(users.getOrgId());
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
			
			reg=wellMapper.insertSelective(well);
			if(reg>0) {
//				reg=well.getWellId();
				
				//添加图片 fl 20180320
				if(request instanceof MultipartHttpServletRequest)
				{
					MultipartHttpServletRequest req = (MultipartHttpServletRequest)request;
					List<MultipartFile> listfile = req.getFiles("images");
					for (int j = 0; j < listfile.size(); j++) {
						if (listfile!=null&&!listfile.isEmpty()) {
							WellImg wellImg = new WellImg();
							String state="06";
							String FileURL = UploadUtil.uploadFile(listfile.get(j),req,users.getOrgId(),state);
							wellImg.setFlag("1");
							wellImg.setImgDesc(listfile.get(j).getOriginalFilename());
							wellImg.setImgTypes("01");
							wellImg.setImgUrl(FileURL);
							wellImg.setPhotoTime(new Date());
							wellImg.setWellId(well.getWellId());
							//保存井图片
							 reg += wellImgMapper.insertSelective(wellImg);
						}
					}
					
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
			if("0".equals(well.getIsFormerbureau())) {
				well.setIsFormerbureauState(0);
			}
			reg=wellMapper.updateWellBean(well);//修改
//			reg=well.getWellId();
			//添加图片 fl 20180320
			if (reg>0) {
				if(request instanceof MultipartHttpServletRequest)
				{
					List<WellImg> wellImgList = wellImgMapper.selectByWellId(well.getWellId());
					if(wellImgList.size()>0 && wellImgList!=null) {//如果存在数据
						for (int i = 0; i < wellImgList.size(); i++) {
							File file=new File(request.getSession().getServletContext().getRealPath("/")+wellImgList.get(i).getImgUrl());
							if(file.exists() && file.isFile())
								file.delete();
						}
						//删除表中数据
					 reg += wellImgMapper.deleteByWellId(well.getWellId());
					}
					MultipartHttpServletRequest req = (MultipartHttpServletRequest)request;
					List<MultipartFile> listfile = req.getFiles("images");
					for (int j = 0; j < listfile.size(); j++) {
						if (listfile!=null&&!listfile.isEmpty()) {
							WellImg wellImg = new WellImg();
							
							String state="06";
							String FileURL = UploadUtil.uploadFile(listfile.get(j),req,users.getOrgId(),state);
							
							wellImg.setFlag("1");
							wellImg.setImgDesc(listfile.get(j).getOriginalFilename());
							wellImg.setImgTypes("01");
							wellImg.setImgUrl(FileURL);
							wellImg.setPhotoTime(new Date());
							wellImg.setWellId(well.getWellId());
							//保存井图片
							 reg += wellImgMapper.insertSelective(wellImg);
						}
					}
					
				}else {
					List<WellImg> wellImgList = wellImgMapper.selectByWellId(well.getWellId());
					if(wellImgList.size()>0 && wellImgList!=null) {//如果存在数据
						for (int i = 0; i < wellImgList.size(); i++) {
							File file=new File(request.getSession().getServletContext().getRealPath("/")+wellImgList.get(i).getImgUrl());
							if(file.exists() && file.isFile())
								file.delete();
						}
						//删除表中数据
					 reg += wellImgMapper.deleteByWellId(well.getWellId());
					}
				}
			}
		}
		
		return reg;
	}
	/**
	 * 查询井信息 fl 加管道段信息
	 */
	@Override
	public WellBean queryWellByWellId(Long wellId,Users users) {
		WellBean well = wellMapper.queryWellByWellId(wellId,users.getOrgId());
		if (well !=null) {
			//查询管道段
			List<PipingSectionBean> pipingSectionlistBean=pipingSectionMapper.queryAllByWellId(well.getWellId());
			if (pipingSectionlistBean !=null && pipingSectionlistBean.size()>0 ) {
				well.setPipingSectionListBean(pipingSectionlistBean);
			}
		}
		return well;
	}
	
	/**
	 * 
	* @Title: savePiping 
	* @Description:添加修改管孔信息 
	* @param @param piping
	* @param @param users
	* @param @return    
	* @return int
	* @author liucanghai 
	* @throws
	* @date 2017年12月22日 上午11:02:11 
	* @version V1.0
	 */
	@Override
	public int savePiping(Piping piping, Users users) {
		int reg=0;
		if(piping.getPipingId()==null) {
			if(piping.getWellId()!=null && piping.getPipingSectId()!=null) {
				PipingSectWell ppsw=pipingSectWellMapper.querySpaceIdForPipingCableWell(piping.getWellId(),piping.getPipingSectId());
				if(ppsw!=null) {
					if(ppsw.getSpaceId()!=null) {
						piping.setSpaceId(ppsw.getSpaceId());//面ID
					}
				}
			}
			piping.setOrgId(users.getOrgId());
			piping.setIsImport("0");
			piping.setPipingState("2");
			piping.setCreateTime(new Date());
			piping.setCreateUser(users.getUserId());
			if(piping.getUnusedsubtubeCount()!=null && piping.getUsedsubtubeCount()!=null 
					&& piping.getSubtubeCount()!=null) {
				if((piping.getUnusedsubtubeCount()+piping.getUsedsubtubeCount())>piping.getSubtubeCount()) {
					throw new FrmsException("占用子孔数+未占用子孔数不能大于子孔数量【"+piping.getSubtubeCount()+"】");
				}
			}
			reg=pipingMapper.insertSelective(piping);
		}else {
			if(piping.getUnusedsubtubeCount()!=null && piping.getUsedsubtubeCount()!=null 
					&& piping.getSubtubeCount()!=null) {
				if((piping.getUnusedsubtubeCount()+piping.getUsedsubtubeCount())>piping.getSubtubeCount()) {
					throw new FrmsException("占用子孔数+未占用子孔数不能大于子孔数量【"+piping.getSubtubeCount()+"】");
				}
			}
			piping.setLastModifyTime(new Date());
			piping.setLastModifyUser(users.getUserId());
			reg=pipingMapper.updateByPrimaryKeySelective(piping);
		}
		return reg;
	}
	
	/**
	 * 根据入参,删除光缆段
	 */
	@Override
	public int deletepipingCableByThings(PipingCable pipingCable,Users users) {
		int num = 0;
		List<PipingCable> pipingCableList=pipingCableMapper.selectpipingCableByThings(pipingCable);
		if (pipingCableList !=null && pipingCableList.size()>0) {
			for (int i = 0; i < pipingCableList.size(); i++) {
				PipingCable pipingCable1 =pipingCableList.get(0);
				pipingCable1.setSectState(pipingCable.getSectState());
				pipingCable1.setLastModifyTime(new Date());
				pipingCable1.setLastModifyUser(users.getUserId());
				num= pipingCableMapper.updateByPrimaryKeySelective(pipingCable1);
			}
		}
		return num;
	}
	/**
	 * 根据入参,恢复光缆段
	 */
	@Override
	public int recoverpipingCable(PipingCable pipingCable,Users users) {
		int num = 0;
		List<PipingCable> pipingCableList=pipingCableMapper.selectpipingCableByThreeIds(pipingCable);
		if (pipingCableList !=null && pipingCableList.size()>0) {
			for (int i = 0; i < pipingCableList.size(); i++) {
				PipingCable pipingCable1 =pipingCableList.get(0);
				if (pipingCable1 !=null) {
					if (DateState.ZGDEL.toString().equals(pipingCable1.getSectState())) {
						pipingCable1.setSectState("3");
						pipingCable1.setLastModifyTime(new Date());
						pipingCable1.setLastModifyUser(users.getUserId());
					}else if (DateState.NEWDEL.toString().equals(pipingCable1.getSectState())) {
						pipingCable1.setSectState("2");
						pipingCable1.setLastModifyTime(new Date());
						pipingCable1.setLastModifyUser(users.getUserId());
					}
					num+= pipingCableMapper.updateByPrimaryKeySelective(pipingCable1);
				}
			}
		}
		return num;
	}
	/**
	 * 
	* @Title: savePipingSection 
	* @Description: 添加和修改管孔信息 
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2017年12月22日 下午12:13:58 
	* @version V1.0
	 */
	@Override
	public int savePipingSection(PipingSectionBean bean, Users users) {
		int reg=0;
		
		if(bean.getPipingSectId()==null) {
			if(bean.getAwellId() != null && bean.getZwellId() != null) {
				if(bean.getAwellId().longValue()==bean.getZwellId().longValue()) {
					throw new FrmsException("管道段两端不能相同！");
				}
				Well awell = wellMapper.selectByPrimaryKey(bean.getAwellId());
				Well zwell = wellMapper.selectByPrimaryKey(bean.getZwellId());
				if(awell!=null && zwell!=null) {
					if(awell.getLatitude()!=null && !"".equals(awell.getLatitude()) && 
						awell.getLongitude()!=null && !"".equals(awell.getLongitude())) {
						if(zwell.getLatitude()!=null && !"".equals(zwell.getLatitude()) &&
							zwell.getLongitude()!=null && !"".equals(zwell.getLongitude())) {
							
							double mm = GoogleToGPSUtil.distance(//两点距离
										Double.valueOf(awell.getLatitude()).doubleValue(),
										Double.valueOf(awell.getLongitude()).doubleValue(),
										Double.valueOf(zwell.getLatitude()).doubleValue(),
										Double.valueOf(zwell.getLongitude()).doubleValue()
										);
							bean.setMapLen(String.format("%.2f", mm));//地图长度 	
						}
					}
				}
				bean.setPipingSectType("2");
				bean.setIsImport("0");
				bean.setOrgId(users.getOrgId());
				bean.setCreateUser(users.getUserId());
				bean.setCreateTime(new Date());
				reg=pipingSectionMapper.insertSelective(bean);//添加管道段
//				reg=bean.getPipingSectId();
				
				PipingSectWell pswell=null;
				if(bean.getAwellId()!=null ) {
					pswell=new PipingSectWell();
					pswell.setWellId(bean.getAwellId());
					pswell.setSpaceId(getSpaceId(bean.getAwellId(),bean.getaSurface(),users));
					pswell.setPipingSectId(bean.getPipingSectId());
					pipingSectWellMapper.insertSelective(pswell);//添加井，面，管道段关系表
					pswell=null;
				}
				if(bean.getZwellId()!=null) {
					pswell=new PipingSectWell();
					pswell.setWellId(bean.getZwellId());
					pswell.setSpaceId(getSpaceId(bean.getZwellId(),bean.getzSurface(),users));
					pswell.setPipingSectId(bean.getPipingSectId());
					pipingSectWellMapper.insertSelective(pswell);
					pswell=null;
				}
			}
		}else {
			if(bean.getAwellId() != null && bean.getZwellId() != null) {
				if(bean.getAwellId().longValue()==bean.getZwellId().longValue()) {
					throw new FrmsException("管道段两端不能相同！");
				}
				Well awell = wellMapper.selectByPrimaryKey(bean.getAwellId());
				Well zwell = wellMapper.selectByPrimaryKey(bean.getZwellId());
				if(awell.getLatitude()!=null && !"".equals(awell.getLatitude()) && 
					awell.getLongitude()!=null && !"".equals(awell.getLongitude())) {
					if(zwell.getLatitude()!=null && !"".equals(zwell.getLatitude()) &&
						zwell.getLongitude()!=null && !"".equals(zwell.getLongitude())) {
						
						double mm = GoogleToGPSUtil.distance(//两点距离
									Double.valueOf(awell.getLatitude()).doubleValue(),
									Double.valueOf(awell.getLongitude()).doubleValue(),
									Double.valueOf(zwell.getLatitude()).doubleValue(),
									Double.valueOf(zwell.getLongitude()).doubleValue()
									);
						bean.setMapLen(String.format("%.2f", mm));//地图长度 	
					}
				}
				bean.setLastModifyTime(new Date());
				bean.setLastModifyUser(users.getUserId());
				reg=pipingSectionMapper.updateByPrimaryKeySelective(bean);//添加管道段
//				reg=bean.getPipingSectId();
				
				PipingSectWell pswell=null;
				List<PipingSectWell> ppswList = pipingSectWellMapper.queryByPipingSectId(bean.getPipingSectId());
				if(ppswList!=null) {
					if(ppswList.size()==2) {
						pswell=new PipingSectWell();
						pswell.setPipingWellId(ppswList.get(0).getPipingWellId());
						pswell.setWellId(bean.getAwellId());
						pswell.setSpaceId(getSpaceId(bean.getAwellId(),bean.getaSurface(),users));
						pswell.setPipingSectId(bean.getPipingSectId());
						pipingSectWellMapper.updateByPrimaryKeySelective(pswell);//添加井，面，管道段关系表
						pswell=null;
						pswell=new PipingSectWell();
						pswell.setPipingWellId(ppswList.get(1).getPipingWellId());
						pswell.setWellId(bean.getZwellId());
						pswell.setSpaceId(getSpaceId(bean.getZwellId(),bean.getzSurface(),users));
						pswell.setPipingSectId(bean.getPipingSectId());
						pipingSectWellMapper.updateByPrimaryKeySelective(pswell);
						pswell=null;
					}
				}
			}
		}
		return reg;
	}
	/**
	 * 
	* @Title: getSpaceId 
	* @Description: 根据井ID和面，查询面ID 
	* @param @param wellId
	* @param @param surface
	* @param @return    
	* @return Long
	* @author liucanghai 
	* @throws
	* @date 2018年1月17日 下午3:28:31 
	* @version V1.0
	 */
	public Long getSpaceId(Long wellId,String surface,Users users) {
		Long spaceId=null;
		Space space=null;
		if(surface!=null && !"".equals(surface)) {
			List<Space> spaceList=spaceMapper.queryByWellId(wellId);
			if(spaceList!=null && spaceList.size()>0) {
				for (int i = 0; i < spaceList.size(); i++) {
					if(spaceList.get(i).getSurface().equals(surface)) {
						spaceId=spaceList.get(i).getSpaceId();//A面ID
					}else {
						space=new Space();
						space.setCreateTime(new Date());
						space.setCreateUser(users.getUserId());
						space.setPipingCount(0L);
						space.setWellId(wellId);
						space.setSurface(surface);
						space.setOrgId(users.getOrgId());
						int reg=spaceMapper.insertSelective(space);
						if(reg>0) {
							spaceId=space.getSpaceId();
						}else {
							throw new FrmsException("创建【"+surface+"】面失败！");
						}
						space=null;
					}
				}
			}else {
				space=new Space();
				space.setCreateTime(new Date());
				space.setCreateUser(users.getUserId());
				space.setPipingCount(0L);
				space.setWellId(wellId);
				space.setSurface(surface);
				space.setOrgId(users.getOrgId());
				int reg=spaceMapper.insertSelective(space);
				if(reg>0) {
					spaceId=space.getSpaceId();
				}else {
					throw new FrmsException("创建【"+surface+"】面失败！");
				}
				space=null;
			}
		}else {
			return null;
		}
		return spaceId;
	}
	
	public static void main(String[] args) {
		String straa="113.00251613666502";
		String strao="23.00630111463651";
		
		String strbb="113.06977317176462";
		String strb="22.996135958146912";
		
		
		double mm = GoogleToGPSUtil.distance(Double.valueOf(straa).doubleValue(),Double.valueOf(strao).doubleValue(),
				Double.valueOf(strbb).doubleValue(),Double.valueOf(strb).doubleValue());
		System.out.println(String.format("%.2f", mm));
	}
	
	/**
	 * 
	* @Title: modifWell 
	* @Description: 删除井设施 
	* @param @param session
	* @param @param well
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2017年12月22日 下午4:53:23 
	* @version V1.0
	 */
	@Override
	public int modifWell(WellBean bean, Users users) {
		int reg=0;
		List<Piping> pipingList=pipingMapper.queryByWellId(bean.getWellId());
		if(pipingList!=null&&pipingList.size()>0) {
			for (int i = 0; i < pipingList.size(); i++) {
				reg=wellService.deletePipingSectionByPipingId(pipingList.get(i).getPipingId(),bean.getWellStateEnd(),users);
			}
		}
		
		List<PipingSection> ppSectList = pipingSectionMapper.queryByWellId(bean.getWellId());
		if(ppSectList!=null && ppSectList.size()>0) {
			for (int i = 0; i < ppSectList.size(); i++) {
				if(!DateState.ZGDEL.toString().equals(ppSectList.get(i).getPipingSectType()) &&
					!DateState.NEWDEL.toString().equals(ppSectList.get(i).getPipingSectType())
					) {//判断所属于井的管道段有没有不是删除的
					throw new FrmsException("请先删除管道段【"+ppSectList.get(i).getPipingName()+"】,再删除井设施！");
				}
			}
		}
		PipingCable pc=new PipingCable();
		pc.setWellId(bean.getWellId());
		List<PipingCable> pcList=pipingCableMapper.queryByPipingCableBean(pc);//查询光缆关系表
		if(pcList!=null && pcList.size()>0) {
			for (int i = 0; i < pcList.size(); i++) {
				if(pcList.get(i).getSectId()!=null) {
					CableSection cs = cableSectionMapper.selectByPrimaryKey(pcList.get(i).getSectId());
					if(!DateState.ZGDEL.toString().equals(cs.getSectState()) &&
						!DateState.NEWDEL.toString().equals(cs.getSectState())) {//判断属于井的光缆段是不是删除状态
						throw new FrmsException("请先删除光缆段【"+cs.getSecName()+"】,再删除井设施！");
					}
				}
			}
		}
		Well well=new Well();
		well.setWellId(bean.getWellId());
		well.setWellState(bean.getWellStateEnd());
		well.setLastModifyTime(new Date());
		well.setLastModifyUser(users.getUserId());
		reg = wellMapper.updateByPrimaryKeySelective(well);//删除井
		return reg;
	}
	/**
	 * 
	* @Title: recoverWell 
	* @Description: 恢复井设施 
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2017年12月22日 下午5:57:06 
	* @version V1.0
	 */
	@Override
	public int recoverWell(Long wellId, Users users) {
		
		int reg=0;
		List<Piping> pipingList=pipingMapper.queryByWellId(wellId);
		if(pipingList!=null &&pipingList.size()>0) {
			for (int i = 0; i < pipingList.size(); i++) {
				reg=wellService.recorePipingSectionByPipingId(pipingList.get(i).getPipingId(),users);
			}
		}
		
		Well well= wellMapper.selectByPrimaryKey(wellId);
		if(DateState.ZGDEL.toString().equals(well.getWellState())) {
			well.setWellState("3");
			well.setLastModifyTime(new Date());
			well.setLastModifyUser(users.getUserId());
			reg=wellMapper.updateByPrimaryKeySelective(well);
		}
		if(DateState.NEWDEL.toString().equals(well.getWellState())) {
			well.setWellState("2");
			well.setLastModifyTime(new Date());
			well.setLastModifyUser(users.getUserId());
			reg=wellMapper.updateByPrimaryKeySelective(well);
		}
		
		
		return reg;
	}

	
	/**
	 * 
	* @Title: queryPipingByPipingSectId 
	* @Description: 根据管道段id查询管孔信息列表
	* @param @return    入参
	* @return Object    返回类型
	* @author zhouyu 
	* @throws
	* @date 2017年1月17日 上午11:10:30 改
	* @version V1.0
	 */
	@Override
	public PipingSectionBean queryPipingSectByPipingSectId(Long pipingSectId,Long orgId,Long wellId) {
		PipingSectionBean pspp =pipingCableMapper.queryPipingSectionPing(pipingSectId,wellId,orgId);
		if(pspp!=null) {
			List<PipingSectWell> psppList=pipingSectWellMapper.queryByPipingSectId(pipingSectId);
			if(psppList!=null ) {
				if(psppList.size()==2) {
					pspp.setAwellId(psppList.get(0).getWellId());
					pspp.setZwellId(psppList.get(1).getWellId());
					Space aspace = spaceMapper.selectByPrimaryKey(psppList.get(0).getSpaceId());
					if(aspace!=null) {
						pspp.setaSurface(aspace.getSurface());
					}
					Space zspace = spaceMapper.selectByPrimaryKey(psppList.get(1).getSpaceId());
					if(zspace!=null) {
						pspp.setzSurface(zspace.getSurface());
					}
					Well aWell = wellMapper.selectByPrimaryKey(psppList.get(0).getWellId());
					Well zWell = wellMapper.selectByPrimaryKey(psppList.get(1).getWellId());
					pspp.setaWellName(aWell.getWellName());
					pspp.setzWellName(zWell.getWellName());
				}else {
					throw new FrmsException("数据错误，请联系管理员！");
				}
			}
		}else {
			PipingSectionBean ps = pipingSectionMapper.queryByPrimaryKey(pipingSectId);
			if(ps!=null) {
				List<PipingSectWell> psppList=pipingSectWellMapper.queryByPipingSectId(pipingSectId);
				if(psppList!=null ) {
					if(psppList.size()==2) {
						ps.setAwellId(psppList.get(0).getWellId());
						ps.setZwellId(psppList.get(1).getWellId());
						Space aspace = spaceMapper.selectByPrimaryKey(psppList.get(0).getSpaceId());
						if(aspace!=null) {
							ps.setaSurface(aspace.getSurface());
						}
						Space zspace = spaceMapper.selectByPrimaryKey(psppList.get(1).getSpaceId());
						if(zspace!=null) {
							ps.setzSurface(zspace.getSurface());
						}
						Well aWell = wellMapper.selectByPrimaryKey(psppList.get(0).getWellId());
						Well zWell = wellMapper.selectByPrimaryKey(psppList.get(1).getWellId());
						ps.setaWellName(aWell.getWellName());
						ps.setzWellName(zWell.getWellName());
					}else {
						throw new FrmsException("数据错误，请联系管理员！");
					}
				}
			}
			return ps;
		}
		return pspp;
	}
	/**
	 * 
	* @Title: deletePipingSectionByPipingId 
	* @Description: 删除管孔信息 
	* @param @param pipingId pipingState
	* @param @return    入参
	* @return Object    返回类型
	* @author 周宇
	* @throws
	* @date 2017年12月22日 下午5:40:41 
	* @version V1.0
	 */
	@Override
	public int deletePipingSectionByPipingId(Long pipingId,String pipingState,Users users) {
		if(Objects.equals(pipingState, "2")) {
			pipingState="5";
		}else {
			pipingState="4";
		}
		Piping piping = new Piping();
		piping.setPipingId(pipingId);
		piping.setPipingState(pipingState);
		piping.setLastModifyTime(new Date());
		piping.setLastModifyUser(users.getUserId());
		int key = pipingMapper.updateByPrimaryKeySelective(piping);
		return key;
	}
	/**
	 * 
	* @Title: deletePipingSectionByPipingId 
	* @Description: 恢复管孔信息 
	* @param @param pipingId
	* @param @return    入参
	* @return Object    返回类型
	* @author 周宇
	* @throws
	* @date 2017年12月22日 下午5:40:41 
	* @version V1.0
	 */
	@Override
	public int recorePipingSectionByPipingId(Long pipingId,Users users) {
		int i =0;
		Piping piping = pipingMapper.selectByPrimaryKey(pipingId);
		String state = piping.getPipingState();
		piping.setLastModifyTime(new Date());
		piping.setLastModifyUser(users.getUserId());
		if(Objects.equals(state, "4")) {
			piping.setPipingState("3");
			 i = pipingMapper.updateByPrimaryKeySelective(piping);
		}else if(Objects.equals(state, "5")) {
			piping.setPipingState("2");
			 i = pipingMapper.updateByPrimaryKeySelective(piping);
		}else {
			i=-1;
		}
		
		return i;
	}
	/**
	 * 
	* @Title: deletePipingSect 
	* @Description: 删除管道段 
	* @param @param pipings
	* @param @param users
	* @param @return    
	* @return int
	* @author liucanghai 
	* @throws
	* @date 2017年12月27日 上午10:21:40 
	* @version V1.0
	 */
	@Override
	public int deletePipingSect(PipingSectionBean bean, Users users) {
		int reg=0;
		PipingSection ps=new PipingSection();
		ps.setPipingSectId(bean.getPipingSectId());
		ps.setLastModifyTime(new Date());
		ps.setLastModifyUser(users.getUserId());
		ps.setPipingSectType(bean.getPipingSectTypeDelete());
		reg=pipingSectionMapper.updateByPrimaryKeySelective(ps);
		return reg;
	}
	/**
	 * 
	* @Title: recorePipingSect 
	* @Description: 恢复管道段 
	* @param @param session
	* @param @param bean
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2017年12月27日 上午10:43:26 
	* @version V1.0
	 */
	@Override
	public int recorePipingSect(PipingSectionBean bean, Users users) {
		int reg=0;
		PipingSection ps = pipingSectionMapper.selectByPrimaryKey(bean.getPipingSectId());
		if(DateState.ZGDEL.toString().equals(ps.getPipingSectType())) {
			ps.setPipingSectType(DateState.MODIF.toString());
			ps.setLastModifyTime(new Date());
			ps.setLastModifyUser(users.getUserId());
			reg=pipingSectionMapper.updateByPrimaryKeySelective(ps);
		}
		if(DateState.NEWDEL.toString().equals(ps.getPipingSectType())) {
			ps.setPipingSectType(DateState.NEWLY.toString());
			ps.setLastModifyTime(new Date());
			ps.setLastModifyUser(users.getUserId());
			reg=pipingSectionMapper.updateByPrimaryKeySelective(ps);
		}
		return reg;
	}
	/**
	 * 
	* @Title: pipingSectionList 
	* @Description: 查询井设施详细信息 
	* @param @param wellId
	* @param @return    
	* @return String
	* @author liucanghai 
	* @throws
	* @date 2018年1月3日 下午3:30:32 
	* @version V1.0
	 */
	@Override
	public List<PipingSectionBean> querypipingSectionList(Long wellId) {
		return pipingCableMapper.queryByWellId(wellId);
	}
	/**
	 * 根据井ID查询
	 * 刘沧海
	 * 2018/1/4
	 */
	@Override
	public Well selectByPrimaryKey(Long wellId) {
		return wellMapper.selectByPrimaryKey(wellId);
	}
	@Override
	public List<WellBean> change(List<WellBean> wells) {
		if(wells.size()>0) {
			for (WellBean wellBean : wells) {
				if(wellBean.getWellKind()==null||wellBean.getWellKind()=="") {
					
				}else if(wellBean.getWellKind().equals("1")) {
					wellBean.setWellKind("孤立");
				}else if(wellBean.getWellKind().equals("2")) {
					wellBean.setWellKind("直通");
				}else if(wellBean.getWellKind().equals("3")) {
					wellBean.setWellKind("三通");
				}else if(wellBean.getWellKind().equals("4")) {
					wellBean.setWellKind("四通");
				}else if(wellBean.getWellKind().equals("5")) {
					wellBean.setWellKind("五通");
				}else if(wellBean.getWellKind().equals("6")) {
					wellBean.setWellKind("六通");
				}else if(wellBean.getWellKind().equals("7")) {
					wellBean.setWellKind("七通");
				}else if(wellBean.getWellKind().equals("8")) {
					wellBean.setWellKind("七通以上");
				}
				
				if(wellBean.getWellState()==null||wellBean.getWellState()=="") {
					
				}else if(wellBean.getWellState().equals("0")) {
					wellBean.setWellState("未核查");
				}else if(wellBean.getWellState().equals("1")) {
					wellBean.setWellState("与现场一致");
				}else if(wellBean.getWellState().equals("2")) {
					wellBean.setWellState(" 新增");
				}else if(wellBean.getWellState().equals("3")) {
					wellBean.setWellState("修改");
				}else if(wellBean.getWellState().equals("4")) {
					wellBean.setWellState("未找到");
				}else if(wellBean.getWellState().equals("5")) {
					wellBean.setWellState("新增删除");
				}else if(wellBean.getWellState().equals("6")) {
					wellBean.setWellState("存疑");
				}
				
				if(wellBean.getWellType()==null||wellBean.getWellType()=="") {
					
				}else if(wellBean.getWellType().equals("01")) {
					wellBean.setWellType(" 井");
				}else if(wellBean.getWellType().equals("02")) {
					wellBean.setWellType(" 挂墙");
				}else if(wellBean.getWellType().equals("03")) {
					wellBean.setWellType(" 杆");
				}else if(wellBean.getWellType().equals("04")) {
					wellBean.setWellType(" 标石");
				}
				
				if(wellBean.getIsFormerbureau()==null||wellBean.getIsFormerbureau()=="") {
					
				}else if(wellBean.getIsFormerbureau().equals("0")) {
					wellBean.setIsFormerbureau("否");
				}else if(wellBean.getIsFormerbureau().equals("1")) {
					wellBean.setIsFormerbureau("是");
				}
			}
			
		}
		return wells;
	}
	
	/**
	 * 
	 * @Title: bindCablePipingAndSubtube
	 * @Description: 绑定光缆段与子管(或者管孔)
	 * @param @param sectId
	 * @param @param pipingId
	 * @param @param subtubeId
	 * @param @param pipingSectId
	 * @param @param user
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月2日 下午4:16:51
	 * @version V1.0
	 */
	@Override
	public int bindCablePipingAndSubtube(PipingCable pipingCable, Long colorId, Users user,HttpServletRequest request) {
		int num = 0;
		Piping piping = pipingMapper.selectByPrimaryKey(pipingCable.getPipingId());
		if(pipingCable.getSubtubeRefSectId() == null) {
			pipingCable.setWellId(piping.getWellId());
			pipingCable.setSpaceId(piping.getSpaceId());
			pipingCable.setSubtubeId(colorId);//把颜色ID添加到子管ID
			pipingCable.setIsImport("0");
			pipingCable.setCreateTime(new Date());
			pipingCable.setCreateUser(user.getUserId());
			pipingCable.setOrgId(user.getOrgId());
			num = pipingCableMapper.insertSelective(pipingCable);
		}else {
			pipingCable.setWellId(piping.getWellId());
			pipingCable.setSpaceId(piping.getSpaceId());
			pipingCable.setSubtubeId(colorId);//把颜色ID添加到子管ID
			pipingCable.setLastModifyTime(new Date());
			pipingCable.setLastModifyUser(user.getUserId());
			pipingCable.setOrgId(user.getOrgId());
			num = pipingCableMapper.updateByPrimaryKeySelective(pipingCable);
		}
		if(num>0) {
			int saveImage = 0;
			List<CableSectionImage> cableSectionImageList = cableSectionImageService.queryBySectId(pipingCable.getSectId());
			if(cableSectionImageList.size()>0) {//如果存在数据
				for (int i = 0; i < cableSectionImageList.size(); i++) {
					File file=new File(request.getSession().getServletContext().getRealPath("/")+cableSectionImageList.get(i).getImgUrl());
					if(file.exists()&&file.isFile())
						file.delete();
				}
				//根据sectId删除图片
				saveImage = cableSectionImageService.deleteImageBySectId(pipingCable.getSectId());
			}
			if(request instanceof MultipartHttpServletRequest)
			{
				MultipartHttpServletRequest req = (MultipartHttpServletRequest)request;
				List<MultipartFile> listfile = req.getFiles("images");
				for (int j = 0; j < listfile.size(); j++) {
					if (listfile!=null&&!listfile.isEmpty()) {
						CableSectionImage cableSectionImage = new CableSectionImage();
						String image = UploadUtil.uploadFile(listfile.get(j), req,user.getOrgId(), "04");//"04"添加光缆段图片
						cableSectionImage.setImgUrl(image);
						cableSectionImage.setSectId(pipingCable.getSectId());
						cableSectionImage.setCreateTime(new Date());;
						cableSectionImage.setCreateUser(user.getUserId());
						saveImage = cableSectionImageService.insert(cableSectionImage);
					}
				}
			}
		}
		return num;
	}
	/**
	 * 
	* @Title: queryCableSectionImage 
	* @Description: 根据管孔ID查询光缆段信心和图片 
	* @param @param piping
	* @param @param session
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年1月22日 上午10:40:13 
	* @version V1.0
	 */
	@Override
	public PipingBean queryCableSectionImage(Long pipingId, Users user) {
		return pipingCableMapper.queryCableSectionImage(pipingId,user.getOrgId());
	}
	
	@Override
	public List<Colors> queryColor() {
		return colorsMapper.queryColor();
	}
	/**
	 * 根据井id更改井信息
	 */
	@Override
	public int updateWellById(Long wellId) {
		WellBean wellBean = new WellBean();
		wellBean.setWellId(wellId);
		wellMapper.updateByPrimaryKeySelective(wellBean);
		return 0;
	}
	
	/**
	 * 
	 * @Title: queryPipingCableBySectId
	 * @Description: 根据sectId查询光缆段绑定子管表
	 * @param @param sectId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年2月7日 下午3:48:18
	 * @version V1.0
	 */
	@Override
	public PipingCable queryPipingCableBySectId(Long sectId, Long pipingId) {
		return pipingCableMapper.queryPipingCableBySectId(sectId,pipingId);
	}
	/**
	 * 
	* @Title: queryAllPipelineByWellId 
	* @Description: 根据井ID查询整个管道 
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年3月1日 上午9:54:53 
	* @version V1.0
	 */
	@Override
	public List<WellBean> queryAllPipelineByWellId(Long wellId, Users user) {
		List<PipingSectWell> ppswList=pipingSectWellMapper.queryByWellId(wellId);
		List<WellBean> wellList=new ArrayList<WellBean>();
		
		WellBean atWell=wellMapper.queryByWellId(wellId);
		if(atWell==null) {
			throw new FrmsException("当前井不存在！");
		}
		atWell.setWellIden(1);//当前井
		if(ppswList!=null && ppswList.size()>0) {
			for(PipingSectWell psw:ppswList) {
				if(psw.getWellId().longValue()!=wellId.longValue()) {
					WellBean well=wellMapper.queryByWellId(psw.getWellId());
					if(well!=null) {
						if(well.getBaiduX()!=null && !"".equals(well.getBaiduX())&&
								well.getBaiduY()!=null && !"".equals(well.getBaiduY())) {
							wellList.add(well);
						}
					}
				}
			}
		}
		wellList.add(atWell);
		if(wellList!=null && wellList.size()>0) {
			for(WellBean wb :wellList) {
				List<PipingSectionBean> ppsList = pipingSectionMapper.queryAllByWellId(wb.getWellId());
				if(ppsList!=null && ppsList.size()>0) {
					wb.setPipingSectionListBean(ppsList);//查询井的管道段
				}
			}
		}
		return wellList;
	}
	/**
	 * 
	* @Title: queryWellExport 
	* @Description: 条件查询井设施 
	* @param @param well
	* @param @return    
	* @return List<WellBean>
	* @author liucanghai 
	* @throws
	* @date 2018年3月28日 下午3:39:28 
	* @version V1.0
	 */
	@Override
	public List<WellBean> queryWellExport(WellBean well) {
		return wellMapper.queryWellExport(well);
	}
}
