/**   
 * @Package: com.ycnet.frms.vo.mobile 
 * @author: FL   
 * @date: 2018年10月10日 下午1:31:06 
 */
package com.ycnet.frms.vo.mobile.ocii;

import java.util.List;

import com.ycnet.frms.vo.mobile.OcSpaceBean;

/** 
* @ClassName: OcWellBean 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月10日 下午1:31:06  
*/
public class OcWellBean{
	
	private Long wellId;

    private Long devId;

    private String wellName;

    private String wellNumber;

    private String isFormerbureau;
    
    private String remark;

    private String isBranch;

    private String prorightType;
    
    private String wellState;
    
    private String valueName;
    
//    private String areaName;
    
    private String wellAddr;
    
    private List<OcImg> imgList;//图片集合
    
    private List<OcSpaceBean> list;//分面大孔

    public Long getWellId() {
		return wellId;
	}

	public void setWellId(Long wellId) {
		this.wellId = wellId;
	}

	public Long getDevId() {
		return devId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public String getWellName() {
		return wellName;
	}

	public void setWellName(String wellName) {
		this.wellName = wellName;
	}

	public String getWellNumber() {
		return wellNumber;
	}

	public void setWellNumber(String wellNumber) {
		this.wellNumber = wellNumber;
	}

	public String getIsFormerbureau() {
		return isFormerbureau;
	}

	public void setIsFormerbureau(String isFormerbureau) {
		this.isFormerbureau = isFormerbureau;
	}

	public String getIsBranch() {
		return isBranch;
	}

	public void setIsBranch(String isBranch) {
		this.isBranch = isBranch;
	}

	public String getProrightType() {
		return prorightType;
	}

	public void setProrightType(String prorightType) {
		this.prorightType = prorightType;
	}

	public String getWellState() {
		return wellState;
	}

	public void setWellState(String wellState) {
		this.wellState = wellState;
	}

	public List<OcImg> getImgList() {
		return imgList;
	}

	public void setImgList(List<OcImg> imgList) {
		this.imgList = imgList;
	}

	public List<OcSpaceBean> getList() {
		return list;
	}

	public void setList(List<OcSpaceBean> list) {
		this.list = list;
	}

	public String getValueName() {
		return valueName;
	}

	public void setValueName(String valueName) {
		this.valueName = valueName;
	}

//	public String getAreaName() {
//		return areaName;
//	}
//
//	public void setAreaName(String areaName) {
//		this.areaName = areaName;
//	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getWellAddr() {
		return wellAddr;
	}

	public void setWellAddr(String wellAddr) {
		this.wellAddr = wellAddr;
	}


}
