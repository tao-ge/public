/**   
 * @Package: com.ycnet.frms.vo.mobile 
 * @author: FL   
 * @date: 2018年10月10日 下午1:31:06 
 */
package com.ycnet.frms.vo.mobile.ocii;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ycnet.frms.vo.mobile.OcSpaceBean;


/**
 * 
* @ClassName: OcWellImgs 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月16日 下午2:12:27
 */
public class OcCableSectionImgs{

    private Long sectId;

    private String secName;

    private Long digitalSigns;

    private BigDecimal cableLen;

    private Long fiberNum;

    private Long devIdA;

    private Long devIdZ;

    private Long orgId;

    private Date createTime;

    private Long createUser;

    private Date lastModifyTime;

    private Long lastModifyUser;

    private String isEndfibercable;

    private String sectState;

    private String remark;
    
    private String isRecogn;
    
    private List<String> imgList;

    public Long getSectId() {
        return sectId;
    }

    public void setSectId(Long sectId) {
        this.sectId = sectId;
    }

    public String getSecName() {
        return secName;
    }

    public void setSecName(String secName) {
        this.secName = secName == null ? null : secName.trim();
    }

    public Long getDigitalSigns() {
        return digitalSigns;
    }

    public void setDigitalSigns(Long digitalSigns) {
        this.digitalSigns = digitalSigns;
    }

    public BigDecimal getCableLen() {
        return cableLen;
    }

    public void setCableLen(BigDecimal cableLen) {
        this.cableLen = cableLen;
    }

    public Long getFiberNum() {
        return fiberNum;
    }

    public void setFiberNum(Long fiberNum) {
        this.fiberNum = fiberNum;
    }

    public Long getDevIdA() {
        return devIdA;
    }

    public void setDevIdA(Long devIdA) {
        this.devIdA = devIdA;
    }

    public Long getDevIdZ() {
        return devIdZ;
    }

    public void setDevIdZ(Long devIdZ) {
        this.devIdZ = devIdZ;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Long getLastModifyUser() {
        return lastModifyUser;
    }

    public void setLastModifyUser(Long lastModifyUser) {
        this.lastModifyUser = lastModifyUser;
    }

    public String getIsEndfibercable() {
        return isEndfibercable;
    }

    public void setIsEndfibercable(String isEndfibercable) {
        this.isEndfibercable = isEndfibercable == null ? null : isEndfibercable.trim();
    }

    public String getSectState() {
        return sectState;
    }

    public void setSectState(String sectState) {
        this.sectState = sectState == null ? null : sectState.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getIsRecogn() {
		return isRecogn;
	}

	public void setIsRecogn(String isRecogn) {
		this.isRecogn = isRecogn;
	}

	public List<String> getImgList() {
		return imgList;
	}

	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
	}

}
