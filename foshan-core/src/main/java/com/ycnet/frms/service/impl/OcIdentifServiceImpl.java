/**   
 * @Package: com.ycnet.frms.service.impl 
 * @author: FL   
 * @date: 2018年10月19日 上午9:38:53 
 */
package com.ycnet.frms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.core.FrmsException;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.OcCableSection;
import com.ycnet.frms.bean.OcCableWell;
import com.ycnet.frms.bean.OcIdentif;
import com.ycnet.frms.bean.OcIdentifPort;
import com.ycnet.frms.bean.OcWell;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.FacilityMapper;
import com.ycnet.frms.mapper.OcCableSectionMapper;
import com.ycnet.frms.mapper.OcCableWellMapper;
import com.ycnet.frms.mapper.OcIdentifMapper;
import com.ycnet.frms.mapper.OcIdentifPortMapper;
import com.ycnet.frms.mapper.OcWellMapper;
import com.ycnet.frms.service.OcIdentifService;
import com.ycnet.frms.vo.mobile.ocii.OcCableSectionInput;
import com.ycnet.frms.vo.mobile.ocii.OcCableSectionResult;
import com.ycnet.frms.vo.mobile.ocii.OcIdentifInput;
import com.ycnet.frms.vo.mobile.ocii.OcIdentifPortResult;
import com.ycnet.frms.vo.mobile.ocii.OcIdentifResult;

/** 
* @ClassName: OcIdentifServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月19日 上午9:38:53  
*/
@Service("ocIdentifService")
public class OcIdentifServiceImpl implements OcIdentifService{
	
	@Resource(name="ocIdentifMapper")
	private OcIdentifMapper ocIdentifMapper;
	
	@Resource(name="ocIdentifPortMapper")
	private OcIdentifPortMapper ocIdentifPortMapper;
	
	@Resource(name="ocCableWellMapper")
	private OcCableWellMapper ocCableWellMapper;
	
	@Resource(name="ocWellMapper")
	private OcWellMapper ocWellMapper;
	
	@Resource(name="facilityMapper")
	private FacilityMapper facilityMapper;
	
	@Resource(name ="ocCableSectionMapper")
	private OcCableSectionMapper ocCableSectionMapper;
	
	/** 
	 * 添加/修改光缆识别仪
	* @Title: saveOciiIdentif 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param identif
	* @param @param user
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月19日 上午9:37:08 
	* @version V1.0   
	*/
	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.OcIdentifService#saveOciiIdentif(com.ycnet.frms.bean.OcIdentif, com.ycnet.frms.bean.Users)
	 */
	@Override
	public int saveOciiIdentif(OcIdentif identif, Users user) {
		int reg = 0;
		// 1.校验编码是否已存在，存在则提示
		List<OcIdentif> list = ocIdentifMapper.queryByIdentCode(identif.getIdentCode(), user.getOrgId());
		if (list != null && list.size() > 0) {
			throw new FrmsException("(" + identif.getIdentCode() + ")该编码已存在!");
		}
		if (identif.getIdentId() == null) {// 添加
			identif.setIdentCode(UUID.randomUUID().toString());
			identif.setCreateTime(new Date());
			identif.setCreateUser(user.getUserId());
			identif.setOrgId(user.getOrgId());
			reg = ocIdentifMapper.insertSelective(identif);
			if (reg>0) {
				if (identif.getPortNum()>0) {
					for (int i = 0; i < identif.getPortNum(); i++) {
						OcIdentifPort ocIdentifPort = new OcIdentifPort();
						ocIdentifPort.setCreateTime(new Date());
						ocIdentifPort.setCreateUser(user.getUserId());
						ocIdentifPort.setPortSeq(i+1);
						ocIdentifPort.setGroupId(identif.getGroupId());
						ocIdentifPort.setOrgId(user.getOrgId());
						ocIdentifPort.setPorttCode(UUID.randomUUID().toString());
						ocIdentifPortMapper.insertSelective(ocIdentifPort);
					}
				}
			}
			
		} else {// 修改
			identif.setLastModifyTime(new Date());
			identif.setLastModifyUser(user.getUserId());
			identif.setPortNum(ocIdentifMapper.selectByPrimaryKey(identif.getIdentId()).getPortNum());
			reg = ocIdentifMapper.updateByPrimaryKeySelective(identif);
		}
		return reg;
	}

	
	/** 
	 * 删除光缆识别仪
	* @Title: delOciiIdentif 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param identId
	* @param @param user
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月19日 上午9:57:41 
	* @version V1.0   
	*/
	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.OcIdentifService#delOciiIdentif(java.lang.Long, com.ycnet.frms.bean.Users)
	 */
	@Override
	public int delOciiIdentif(Long identId, Users user) {
		return ocIdentifMapper.deleteByPrimaryKey(identId);
	}


	/**
	 * 根据识别仪ID查询识别仪端口详情
	* @Title: queryOcIdentifPortByIdentifId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param oi
	* @param @param user
	* @param @return    入参
	* @return List<OcIdentifResult>    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年10月24日 上午9:25:57 
	* @version V1.0
	 */
	@Transactional
	@Override
	public OcIdentifResult queryOcIdentifPortByIdentifId(OcIdentifInput oi, Users user) {
		// TODO Auto-generated method stub
		OcIdentifResult result = ocIdentifMapper.selectIdentifById(oi.getIdentId());
		if(result == null) {
			throw new FrmsException("传入识别仪ID数据错误！");
		}
		List<OcIdentifPortResult> resultList = new ArrayList<OcIdentifPortResult>();
		//查询最大光缆点数
		List<OcCableWell> cableList = ocCableWellMapper.selectCableByWellId(oi.getWellDevId(),oi.getType());
		int maxSectNum = 0;
		if(cableList!=null && cableList.size()>0) {
			maxSectNum = cableList.size();
		}
		//处理光缆点
		if(oi.getSectNum()>maxSectNum) {
			//新光缆点数大于原光缆点数 -- 直接在原有基础上插入
			OcCableWell cw = null;
			OcCableSection ocs = null;
			String SecName = "";
			if("1".equals(oi.getType())) {
				OcWell ow = ocWellMapper.selectByPrimaryKey(oi.getWellDevId());
				if(ow!=null) {
					SecName = ow.getWellName();
				}
			}else {
				Facility f = facilityMapper.selectByPrimaryKey(oi.getWellDevId());
				if(f!=null) {
					SecName = f.getDevName();
				}
			}
			for(int i=maxSectNum;i<oi.getSectNum();i++) {
				cw = new OcCableWell();
				cw.setCreateUser(user.getUserId());
				cw.setCreateTime(new Date());
				cw.setOrgId(user.getOrgId());
				cw.setIsWell(oi.getType());
				cw.setWellDevId(oi.getWellDevId());
				cw.setIsRecogn("0");
				cw.setSectPointNo(oi.getSectNum());
				cw.setSectPointSeq(i+1);
				//发射端生成光缆段
				if("1".equals(result.getIdentType())) {
					ocs = new OcCableSection();
					ocs.setCreateTime(new Date());
					ocs.setCreateUser(user.getUserId());
					ocs.setDigitalSigns(i+1L);
					ocs.setIsEndfibercable("0");
					ocs.setIsRecogn("0");
					ocs.setSecName(SecName);
					ocs.setOrgId(user.getOrgId());
					ocs.setSectState("2");
					ocs.setFiberNum(0L);
					int acc = ocCableSectionMapper.insertSelective(ocs);
					if(acc < 0) {
						throw new FrmsException("新增光缆段失败！");
					}
					cw.setSectId(ocs.getSectId());
					cw.setSectNum(1000);
					acc = ocCableWellMapper.insertSelective(cw);
					if(acc < 0) {
						throw new FrmsException("新增光缆点失败！");
					}
					ocs=null;
				}
				cw=null;		
			}
			ocCableWellMapper.updateByDevWellId(oi.getWellDevId(),oi.getType(),oi.getSectNum());
		}else if(oi.getSectNum()<maxSectNum){
			//新光缆点数小于原光缆点数 -- 先验证大于maxSectNum的光缆 点是否已识别完成,未识别完成则则直接删除，已识别则提示
			OcCableWell cw = null;
			for(int l=oi.getSectNum();l<maxSectNum;l++) {
				cw = cableList.get(l);
				//判断是否已识别
				if("1".equals(cw.getIsRecogn())) {
					throw new FrmsException("光缆序号【"+cw.getSectPointSeq()+"】已识别,不能删除！");
				}
				cw=null;		
			}
			for(int j=oi.getSectNum();j<maxSectNum;j++) {
				cw = cableList.get(j);
				if(cw.getSectId()!=null){
					ocCableSectionMapper.deleteByPrimaryKey(cw.getSectId());
				}
				int accdel = ocCableWellMapper.deleteByPrimaryKey(cw.getCableWellId());
				if(accdel < 0) {
					throw new FrmsException("删除光缆点失败！");
				}
				cw=null;		
			}
			ocCableWellMapper.updateByDevWellId(oi.getWellDevId(),oi.getType(),oi.getSectNum());
		}
		
		//验证之前光缆点是否挂光缆段
		if("1".equals(result.getIdentType())) {
			//验证之前光缆点是否挂光缆段
			checkSectBypoint(oi.getSectNum(),oi.getType(),oi.getWellDevId(),cableList,user,maxSectNum);
		}
		
		
		//查询识别仪所有端口数据信息 -- 未绑定前
		List<OcIdentifPort> portListBefore = ocIdentifPortMapper.selectPortByIdentifId(oi.getIdentId());
		int selectPortNum = 0;
		if(portListBefore==null || portListBefore.size()==0) {
			throw new FrmsException("该识别仪未绑定端口！");
		}else {
			selectPortNum = portListBefore.size();
		}
		
		//查询该设施所有光缆点详情 
		List<OcIdentifPortResult> sectList = ocCableWellMapper.selectCableByWellIdResult(oi.getWellDevId(),oi.getType());
		int sectNum = 0;
		if(sectList==null || sectList.size()==0) {
			throw new FrmsException("该识别仪未绑定端口！");
		}else {
			sectNum = sectList.size();
		}
		
		int popNum = 0;
		if(selectPortNum<((sectNum+1)-oi.getStartSeq()) || selectPortNum==((sectNum+1)-oi.getStartSeq())) {
			popNum = selectPortNum;
		}else if(selectPortNum>((sectNum+1)-oi.getStartSeq())) {
			popNum = (sectNum+1)-oi.getStartSeq();
		}
		if(popNum > 0) {
			for(int n = 0;n<popNum;n++) {
				OcIdentifPort op = portListBefore.get(n);
				OcIdentifPortResult opr = sectList.get(oi.getStartSeq()+n-1);
				//绑定识别仪端口
				op.setCableWellId(opr.getCableWellId());
				int resu = ocIdentifPortMapper.updateByPrimaryKeySelective(op);
				if(resu < 1) {
					throw new FrmsException("该识别仪端口绑定失败！");
				}
				opr.setPortId(op.getPortId());
				opr.setPortSeq(op.getPortSeq());
				opr.setPorttCode(op.getPorttCode());
				resultList.add(opr);
				
				opr = null;
				op = null;
			}
		}
		
		
		result.setList(resultList);
		return result;
	}


	/** 
	 * 设备-根据设施ID查询光缆段列表
	* @Title: querySectByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param oi
	* @param @param user
	* @param @return    入参
	* @return OcIdentifResult    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月25日 下午1:18:02 
	* @version V1.0   
	*/
	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.OcIdentifService#querySectByDevId(com.ycnet.frms.vo.mobile.ocii.OcCableSectionInput, com.ycnet.frms.bean.Users)
	 */
	@Override
	public List<Map<String, Object>> querySectByDevId(OcCableSectionInput oi, Users user) {
		List<OcCableWell> cableList = ocCableWellMapper.selectCableByWellId(oi.getWellDevId(),oi.getType());
		int maxSectNum = 0;
		if(cableList!=null && cableList.size()>0) {
			maxSectNum = cableList.size();
		}
		//处理光缆点
		if(oi.getSectNum()>maxSectNum) {
			//新光缆点数大于原光缆点数 -- 直接在原有基础上插入
			OcCableWell cw = null;
			OcCableSection ocs = null;
			String SecName = "";
			if("1".equals(oi.getType())) {
				OcWell ow = ocWellMapper.selectByPrimaryKey(oi.getWellDevId());
				if(ow!=null) {
					SecName = ow.getWellName();
				}
			}else {
				Facility f = facilityMapper.selectByPrimaryKey(oi.getWellDevId());
				if(f!=null) {
					SecName = f.getDevName();
				}
			}
			for(int i=maxSectNum;i<oi.getSectNum();i++) {
				cw = new OcCableWell();
				cw.setCreateUser(user.getUserId());
				cw.setCreateTime(new Date());
				cw.setOrgId(user.getOrgId());
				cw.setIsWell(oi.getType());
				cw.setWellDevId(oi.getWellDevId());
				cw.setIsRecogn("0");
				cw.setSectPointNo(oi.getSectNum());
				cw.setSectPointSeq(i+1);
				//发射端生成光缆段
				ocs = new OcCableSection();
				ocs.setCreateTime(new Date());
				ocs.setCreateUser(user.getUserId());
				ocs.setDigitalSigns(i+1L);
				ocs.setIsEndfibercable("0");
				ocs.setIsRecogn("0");
				ocs.setSecName(SecName);
				ocs.setOrgId(user.getOrgId());
				ocs.setSectState("2");
				ocs.setFiberNum(0L);
				int acc = ocCableSectionMapper.insertSelective(ocs);
				if(acc < 0) {
					throw new FrmsException("新增光缆段失败！");
				}
				cw.setSectId(ocs.getSectId());
				cw.setSectNum(1000);
				acc = ocCableWellMapper.insertSelective(cw);
				if(acc < 0) {
					throw new FrmsException("新增光缆点失败！");
				}
				ocs=null;
				cw=null;		
			}
			ocCableWellMapper.updateByDevWellId(oi.getWellDevId(),oi.getType(),oi.getSectNum());
		}else if(oi.getSectNum()<maxSectNum){
			//新光缆点数小于原光缆点数 -- 先验证大于maxSectNum的光缆 点是否已识别完成,未识别完成则则直接删除，已识别则提示
			OcCableWell cw = null;
			for(int l=oi.getSectNum();l<maxSectNum;l++) {
				cw = cableList.get(l);
				//判断是否已识别
				if("1".equals(cw.getIsRecogn())) {
					throw new FrmsException("光缆序号【"+cw.getSectPointSeq()+"】已识别,不能删除！");
				}
				cw=null;		
			}
			for(int j=oi.getSectNum();j<maxSectNum;j++) {
				cw = cableList.get(j);
				if(cw.getSectId()!=null){
					ocCableSectionMapper.deleteByPrimaryKey(cw.getSectId());
				}
				int accdel = ocCableWellMapper.deleteByPrimaryKey(cw.getCableWellId());
				if(accdel < 0) {
					throw new FrmsException("删除光缆点失败！");
				}
				cw=null;		
			}
			ocCableWellMapper.updateByDevWellId(oi.getWellDevId(),oi.getType(),oi.getSectNum());
		}
		
		//验证之前光缆点是否挂光缆段
		checkSectBypoint(oi.getSectNum(),oi.getType(),oi.getWellDevId(),cableList,user,maxSectNum);
		
		return ocCableWellMapper.querySectByDevId(oi,user);
	}
	
	/**
	 * 验证之前光缆点是否挂光缆段
	* @Title: checkSectBypoint 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param sectNum
	* @param @param type
	* @param @param wellDevId
	* @param @param cableList
	* @param @param user
	* @param @param maxSectNum
	* @param @return    入参
	* @return int    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年10月25日 下午3:19:48 
	* @version V1.0
	 */
	public int checkSectBypoint(int sectNum,String type,Long wellDevId, List<OcCableWell> cableList,Users user,int maxSectNum) {
		int sectNo = 0;
		if(sectNum>maxSectNum) {
			sectNo = maxSectNum;
		}else {
			sectNo = sectNum;
		}
		if(sectNo > 0) {
			return 1;
		}
		OcCableWell cw = null;
		OcCableSection ocs = null;
		String SecName = "";
		if("1".equals(type)) {
			OcWell ow = ocWellMapper.selectByPrimaryKey(wellDevId);
			if(ow!=null) {
				SecName = ow.getWellName();
			}
		}else {
			Facility f = facilityMapper.selectByPrimaryKey(wellDevId);
			if(f!=null) {
				SecName = f.getDevName();
			}
		}
		for(int k=0;k<sectNo;k++) {
			cw = cableList.get(k);
			if(cw.getSectId() != null) {
				continue;
			}
			//发射端生成光缆段
			ocs = new OcCableSection();
			ocs.setCreateTime(new Date());
			ocs.setCreateUser(user.getUserId());
			ocs.setDigitalSigns(k+1L);
			ocs.setIsEndfibercable("0");
			ocs.setIsRecogn("0");
			ocs.setSecName(SecName);
			ocs.setOrgId(user.getOrgId());
			ocs.setSectState("2");
			ocs.setFiberNum(0L);
			int acc = ocCableSectionMapper.insertSelective(ocs);
			if(acc < 0) {
				throw new FrmsException("新增光缆段失败！");
			}
			cw.setSectId(ocs.getSectId());
			cw.setSectNum(1000);
			acc = ocCableWellMapper.updateByPrimaryKeySelective(cw);
			if(acc < 0) {
				throw new FrmsException("更新光缆点失败！");
			}
			cw=null;		
			ocs=null;
		}
		return 1;
	}

	/**
	 * 根据识别仪ID查询识别仪端口详情  - 手动
	* @Title: queryIdentifPortBySeqId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param oi
	* @param @param user
	* @param @return    入参
	* @return OcIdentifResult    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年10月25日 下午1:35:58 
	* @version V1.0
	 */
	@Override
	public OcIdentifResult queryIdentifPortBySeqId(OcIdentifInput oi, Users user) {
		// TODO Auto-generated method stub
		OcIdentifResult result = ocIdentifMapper.selectIdentifById(oi.getIdentId());
		if(result == null) {
			throw new FrmsException("传入识别仪ID数据错误！");
		}
		
		List<OcIdentifPortResult> portList = ocIdentifPortMapper.selectPortByIdentIdAndWellId(oi.getWellDevId(),oi.getType(),oi.getIdentId());
		
		result.setList(portList);
		return result;
	}

}
