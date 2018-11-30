package com.ycnet.frms.vo.mobile.ocii;

import java.util.List;


public class OcPipingSectionUtil {
	
	private String pipingName;

	private String pipingSectType;
	
	private String isImport;

	private String phyLen;
	
	private Long awellId;//A端井ID
	
	private String awellName;//A端井设施名称
	
	private String aType;//A端类型
	
	private Long zwellId;//Z端井ID
	
	private String zwellName;//Z端井设施名称
	
	private String zType;//Z端类型
	
	private Long aspaceId;//A端面ID
	
	private Long zspaceId;//Z端面ID
	
	private List<OcPipingSectWellUtil> pipSectWellList;//管道段两端集合
	
	private Long pipingSectId;

	

	public String getIsImport() {
		return isImport;
	}

	public void setIsImport(String isImport) {
		this.isImport = isImport;
	}

	/**
	 * 设置管道段两端信息
	* @Title: setAwellName 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param awellName    入参
	* @return void    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年10月22日 上午11:34:52 
	* @version V1.0
	 */
	public void setAwellName(String awellName) {
		
		this.awellName = awellName;
	}
	
	public String getAwellName()
	{
		return awellName;
	}


	public Long getPipingSectId() {
		return pipingSectId;
	}





	public String getPipingName() {
		return pipingName;
	}


	public void setPipingName(String pipingName) {
		this.pipingName = pipingName;
	}


	public String getPipingSectType() {
		return pipingSectType;
	}


	

	public String getPhyLen() {
		return phyLen;
	}


	public void setPhyLen(String phyLen) {
		this.phyLen = phyLen;
	}


	public Long getAwellId() {
		return awellId;
	}


	public void setAwellId(Long awellId) {
		this.awellId = awellId;
	}

	public String getaType() {
		return aType;
	}


	public void setaType(String aType) {
		this.aType = aType;
	}


	public Long getZwellId() {
		return zwellId;
	}


	public void setZwellId(Long zwellId) {
		this.zwellId = zwellId;
	}


	public String getZwellName() {
		return zwellName;
	}


	public void setZwellName(String zwellName) {
		this.zwellName = zwellName;
	}


	public String getzType() {
		return zType;
	}


	public void setzType(String zType) {
		this.zType = zType;
	}


	public Long getAspaceId() {
		return aspaceId;
	}


	public void setAspaceId(Long aspaceId) {
		this.aspaceId = aspaceId;
	}


	public Long getZspaceId() {
		return zspaceId;
	}


	public void setZspaceId(Long zspaceId) {
		this.zspaceId = zspaceId;
	}


	public List<OcPipingSectWellUtil> getPipSectWellList() {
		return pipSectWellList;
	}


	public void setPipSectWellList(List<OcPipingSectWellUtil> pipSectWellList) {
		this.pipSectWellList = pipSectWellList;
	}
	
	public void setPipingSectId(Long pipingSectId) {
		this.pipingSectId = pipingSectId;
	}
	
	public void setPipingSectType(String pipingSectType) {
		this.pipingSectType = pipingSectType;
	}

	
	public String getStatus()
	{
		if(pipSectWellList != null && pipSectWellList.size()>0) {
			OcPipingSectWellUtil op = null;
			for(int i=0;i<pipSectWellList.size();i++) {
				op = pipSectWellList.get(i);
				if(i==0) {
					awellId = op.getWellId();
					awellName = op.getDevName();
					aType = op.getIsWell();
					aspaceId = op.getSpaceId();
				}
				if(i==1) {
					zwellId = op.getWellId();
					zwellName = op.getDevName();
					zType = op.getIsWell();
					zspaceId = op.getSpaceId();
				}
			}
		}
		return "1";
	}
}
