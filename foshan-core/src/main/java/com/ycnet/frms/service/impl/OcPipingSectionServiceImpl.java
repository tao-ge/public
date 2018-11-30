package com.ycnet.frms.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.core.FrmsException;
import com.ycnet.frms.bean.OcPiping;
import com.ycnet.frms.bean.OcPipingSectWell;
import com.ycnet.frms.bean.OcPipingSection;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.OcPipingMapper;
import com.ycnet.frms.mapper.OcPipingSectWellMapper;
import com.ycnet.frms.mapper.OcPipingSectionMapper;
import com.ycnet.frms.mapper.OcSpaceMapper;
import com.ycnet.frms.service.OcPipingSectionService;
import com.ycnet.frms.util.GoogleToGPSUtil;
import com.ycnet.frms.vo.mobile.ocii.OcPipingResult;
import com.ycnet.frms.vo.mobile.ocii.OcPipingSectWellUtil;
import com.ycnet.frms.vo.mobile.ocii.OcPipingSectionResult;
import com.ycnet.frms.vo.mobile.ocii.OcPipingSectionUtil;
import com.ycnet.frms.vo.mobile.ocii.PipSectionBean;

/**
 * 管道段逻辑层
* @ClassName: OcPipingSectionServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author YHT(作者)  
* @date 2018年10月19日 下午1:28:04 
* @version V1.0
 */
@Service("ocPipingSectionService")
@Transactional
public class OcPipingSectionServiceImpl implements OcPipingSectionService{
	
	@Resource(name ="ocPipingSectionMapper")
	private OcPipingSectionMapper ocPipingSectionMapper;
	
	@Resource(name ="ocPipingMapper")
	private OcPipingMapper ocPipingMapper;
	
	@Resource(name="ocSpaceMapper")
	private OcSpaceMapper ocSpaceMapper;
	
	@Resource(name="ocPipingSectWellMapper")
	private OcPipingSectWellMapper ocPipingSectWellMapper;

	@Override
	public int saveOcPiping(OcPipingSection ocPipingSection, Users user) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 根据管道段ID删除管道段
	* @Title: delOcPipSectionById 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param pipingSectId
	* @param @return    入参
	* @return int    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年10月19日 下午1:33:45 
	* @version V1.0
	 */
	@Override
	public int delOcPipSectionById(Long pipingSectId) {
		// TODO Auto-generated method stub
		List<OcPipingResult> list = ocPipingMapper.queryOcPipingByPipingSectId(pipingSectId);
		if(list!=null && list.size()>0) {
			throw new FrmsException("管段段下存在管孔，请先删除管孔！");
		}
		return ocPipingSectionMapper.deleteByPrimaryKey(pipingSectId);
	}

	/**
	 * 根据管道段ID查询管道段信息
	* @Title: queryOcPipSectionById 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param pipingSectId
	* @param @return    入参
	* @return OcPipingSectionResult    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年10月22日 上午9:31:16 
	* @version V1.0
	 */
	@Override
	public OcPipingSectionResult queryOcPipSectionById(Long pipingSectId) {
		// TODO Auto-generated method stub
		OcPipingSectionResult apsr = new OcPipingSectionResult();
		
		OcPipingSectionUtil opsu = ocPipingSectionMapper.selectPipSectById(pipingSectId);
		if(opsu != null) {
			apsr.setPhyLen(opsu.getPhyLen());
			apsr.setPipingName(opsu.getPipingName());
			apsr.setPipingSectType(opsu.getPipingSectType());
			apsr.setPipingSectId(opsu.getPipingSectId());
			apsr.setIsImport(opsu.getIsImport());
			if(opsu.getPipSectWellList()!=null) {
				OcPipingSectWellUtil op = null;
				for(int i=0;i<opsu.getPipSectWellList().size();i++) {
					op = opsu.getPipSectWellList().get(i);
					if(i==0) {
						apsr.setAwellId(op.getWellId());
						apsr.setAwellName(op.getDevName());
						apsr.setTypeA(op.getIsWell());
						apsr.setAspaceId(op.getSpaceId());
						if("1".equals(apsr.getTypeA())) {
							apsr.setAspaceList(ocSpaceMapper.selectSpaceByWellId(op.getWellId()));
						}
					}
					if(i==1) {
						apsr.setZwellId(op.getWellId());
						apsr.setZwellName(op.getDevName());
						apsr.setTypeZ(op.getIsWell());
						apsr.setZspaceId(op.getSpaceId());
						if("1".equals(apsr.getTypeZ())) {
							apsr.setZspaceList(ocSpaceMapper.selectSpaceByWellId(op.getWellId()));
						}
					}
				}
			}
			apsr.setPipingBeanList(ocPipingMapper.queryOcPipingByPipingSectId(pipingSectId));
		}else {
			apsr=null;
		}
		return apsr;
	}

	
	/** 
	 * 新建管道段
	* @Title: addOciiPipSection 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param pipSectionBean
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月19日 下午4:38:42 
	* @version V1.0   
	*/
	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.OcPipingSectionService#addOciiPipSection(com.ycnet.frms.vo.mobile.ocii.PipSectionBean)
	 */
	@Override
	public int addOciiPipSection(PipSectionBean pipSectionBean, Users user) {
		//存管道段
		OcPipingSection ocPipingSection = new OcPipingSection();
		ocPipingSection.setCreateTime(new Date());
		ocPipingSection.setCreateUser(user.getUserId());
		ocPipingSection.setIsImport("0");

		if (pipSectionBean.getAbaiduX() != null && !"".equals(pipSectionBean.getAbaiduX())
				&& pipSectionBean.getZbaiduX() != null && !"".equals(pipSectionBean.getZbaiduX())
				&& pipSectionBean.getAbaiduY() != null && !"".equals(pipSectionBean.getAbaiduY())
				&& pipSectionBean.getZbaiduY() != null && !"".equals(pipSectionBean.getZbaiduY())) {
			double[] Axy = GoogleToGPSUtil.bd09_To_Gcj02(Double.valueOf(pipSectionBean.getAbaiduX()),
					Double.valueOf(pipSectionBean.getAbaiduY()));
			double[] Zxy = GoogleToGPSUtil.bd09_To_Gcj02(Double.valueOf(pipSectionBean.getZbaiduX()),
					Double.valueOf(pipSectionBean.getZbaiduY()));
			double mm = GoogleToGPSUtil.distance(Axy[0], Axy[1], Zxy[0], Zxy[1]);// 两点距离
			ocPipingSection.setMapLen(String.format("%.2f", mm));// 地图长度
		}
		
		ocPipingSection.setOrgId(user.getOrgId());
		ocPipingSection.setPhyLen(pipSectionBean.getPhyLen());
		ocPipingSection.setPipingName(pipSectionBean.getPipingName());
		ocPipingSection.setPipingSectId(pipSectionBean.getPipingSectId());
		ocPipingSection.setPipingSectType("2");
		ocPipingSection.setRemark(pipSectionBean.getRemark());
		int reg = ocPipingSectionMapper.insertSelective(ocPipingSection);
		if (reg > 0) {
			// 井和管道段
			OcPipingSectWell ocPipingSectWell = new OcPipingSectWell();
			ocPipingSectWell.setWellId(pipSectionBean.getAwellId());
			ocPipingSectWell.setOrgId(user.getOrgId());
			ocPipingSectWell.setPipingSectId(ocPipingSection.getPipingSectId());
			ocPipingSectWell.setSpaceId(Long.valueOf(pipSectionBean.getSurfaceA()));
			if (pipSectionBean.getTypeA() != null && !"".equals(pipSectionBean.getTypeA())) {
				ocPipingSectWell.setIsWell(pipSectionBean.getTypeA().equals("01") ? "1" : "0");
			}
			reg += ocPipingSectWellMapper.insertSelective(ocPipingSectWell);// 1
			ocPipingSectWell.setWellId(pipSectionBean.getZwellId());
			ocPipingSectWell.setSpaceId(Long.valueOf(pipSectionBean.getSurfaceZ()));
			if (pipSectionBean.getTypeZ() != null && !"".equals(pipSectionBean.getTypeZ())) {
				ocPipingSectWell.setIsWell(pipSectionBean.getTypeZ().equals("01") ? "1" : "0");
			}
			reg += ocPipingSectWellMapper.insertSelective(ocPipingSectWell);// 2
			// 大孔表
			OcPiping ocPiping = new OcPiping();
			ocPiping.setCreateTime(new Date());
			ocPiping.setCreateUser(user.getUserId());
			ocPiping.setDiameter(Short.valueOf(pipSectionBean.getDiameter() + ""));
			ocPiping.setIsImport("0");
			ocPiping.setIsOccupy(pipSectionBean.getIsOccupy());
			ocPiping.setOrgId(user.getOrgId());
			ocPiping.setPipingSectId(ocPipingSection.getPipingSectId());
			ocPiping.setPipingState("2");
			ocPiping.setSubtubeCount(Short.valueOf(pipSectionBean.getSubtubeCount() + ""));
			ocPiping.setUnusedsubtubeCount(Short.valueOf(pipSectionBean.getUnusedsubtubeCount() + ""));
			ocPiping.setUsedsubtubeCount(Short.valueOf(pipSectionBean.getUsedsubtubeCount() + ""));
			reg += ocPipingMapper.insertSelective(ocPiping);
		}
		return reg;
	}

	
	/** 
	 * 修改管道段
	* @Title: updateOciiPipSection 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param pipSectionBean
	* @param @param user
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月23日 上午10:25:25 
	* @version V1.0   
	*/
	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.OcPipingSectionService#updateOciiPipSection(com.ycnet.frms.vo.mobile.ocii.PipSectionBean, com.ycnet.frms.bean.Users)
	 */
	@Override
	public int updateOciiPipSection(PipSectionBean pipSectionBean, Users user) {
		//修改管道段
		OcPipingSection ocPipingSection = new OcPipingSection();
		ocPipingSection.setLastModifyTime(new Date());
		ocPipingSection.setLastModifyUser(user.getUserId());
		ocPipingSection.setPipingSectType(pipSectionBean.getPipingSectType());
		ocPipingSection.setPipingName(pipSectionBean.getPipingName());
		ocPipingSection.setPipingSectId(pipSectionBean.getPipingSectId());
		ocPipingSection.setPhyLen(pipSectionBean.getPhyLen());
		int reg = ocPipingSectionMapper.updateByPrimaryKeySelective(ocPipingSection);
		if (reg>0) {
			//修改中间表
			OcPipingSectWell ocPipingSectWell = new OcPipingSectWell();
			ocPipingSectWell.setPipingWellId(pipSectionBean.getPipingWellIdA());
			ocPipingSectWell.setSpaceId(Long.valueOf(pipSectionBean.getSurfaceA()));
			ocPipingSectWell.setIsWell(pipSectionBean.getTypeA().equals("01") ? "1" : "0");
			ocPipingSectWell.setWellId(pipSectionBean.getAwellId());
			reg += ocPipingSectWellMapper.updateByPrimaryKeySelective(ocPipingSectWell);// 1
			ocPipingSectWell.setPipingWellId(pipSectionBean.getPipingWellIdZ());
			ocPipingSectWell.setSpaceId(Long.valueOf(pipSectionBean.getSurfaceZ()));
			ocPipingSectWell.setIsWell(pipSectionBean.getTypeZ().equals("01") ? "1" : "0");
			ocPipingSectWell.setWellId(pipSectionBean.getZwellId());
			reg += ocPipingSectWellMapper.updateByPrimaryKeySelective(ocPipingSectWell);// 2
		}
		return reg;
	}

	
}
