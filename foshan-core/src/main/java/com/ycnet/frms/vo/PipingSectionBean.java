package com.ycnet.frms.vo;

import java.util.List;

import com.ycnet.frms.bean.Piping;
import com.ycnet.frms.bean.PipingSection;
import com.ycnet.frms.bean.Space;

public class PipingSectionBean extends PipingSection{

	private String userName;
	
	private Long awellId;//A端井ID
	
	private String wellId;
	
	private String aWellName;//A端井设施名称
	
	private String zWellName;//Z端井设施名称
	
	private String wellName;
	
	private Long zwellId;//Z端井ID
	
	private String aSurface;//A端面
	
	private String zSurface;//Z端面
	
	private Long aspaceId;//A端面ID
	private Long spaceId;//分面ID
	
	private Long zspaceId;//Z端面ID
	private String surface; //分面名称
	
//	private List<Space> sufaceList; //面集合
	
	private Long subtubeRefSectId;
	
	private List<PipingBean> pipingBeanList;//管孔集合
	
	private String pipingSectTypeDelete;

	private List<Space> spaceList;
	

	
	
	public String getWellName() {
		return wellName;
	}

	public void setWellName(String wellName) {
		this.wellName = wellName;
	}

	public String getaWellName() {
		return aWellName;
	}

	public void setaWellName(String aWellName) {
		this.aWellName = aWellName;
	}

	
	public String getzWellName() {
		return zWellName;
	}

	public void setzWellName(String zWellName) {
		this.zWellName = zWellName;
	}

	public String getWellId() {
		return wellId;
	}

	public String getaSurface() {
		return aSurface;
	}

	public void setaSurface(String aSurface) {
		this.aSurface = aSurface;
	}

	public String getzSurface() {
		return zSurface;
	}

	public void setzSurface(String zSurface) {
		this.zSurface = zSurface;
	}

	public void setWellId(String wellId) {
		this.wellId = wellId;
	}
	
	public Long getAwellId() {
		return awellId;
	}

	public void setAwellId(Long awellId) {
		this.awellId = awellId;
	}

	public Long getZwellId() {
		return zwellId;
	}

	public void setZwellId(Long zwellId) {
		this.zwellId = zwellId;
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
	private List<Piping> pipingList; //管孔集合
	public List<Piping> getPipingList() {
		return pipingList;
	}

	public void setPipingList(List<Piping> pipingList) {
		this.pipingList = pipingList;
	}

	public Long getSubtubeRefSectId() {
		return subtubeRefSectId;
	}

	public void setSubtubeRefSectId(Long subtubeRefSectId) {
		this.subtubeRefSectId = subtubeRefSectId;
	}

	
	
	public List<PipingBean> getPipingBeanList() {
		return pipingBeanList;
	}

	public void setPipingBeanList(List<PipingBean> pipingBeanList) {
		this.pipingBeanList = pipingBeanList;
	}

	public String getPipingSectTypeDelete() {
		return pipingSectTypeDelete;
	}

	public void setPipingSectTypeDelete(String pipingSectTypeDelete) {
		this.pipingSectTypeDelete = pipingSectTypeDelete;
	}

	public List<Space> getSpaceList() {
		return spaceList;
	}

	public void setSpaceList(List<Space> spaceList) {
		this.spaceList = spaceList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getSurface() {
		return surface;
	}

	public void setSurface(String surface) {
		this.surface = surface;
	}

	public Long getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(Long spaceId) {
		this.spaceId = spaceId;
	}


	
	
	
	
}
