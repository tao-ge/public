package com.ycnet.frms.vo.mobile.ocii;

/**
 * 设备-查询光缆段入参
* @ClassName: OcCableSectionInput 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author YHT(作者)  
* @date 2018年10月25日 上午11:03:11 
* @version V1.0
 */
public class OcCableSectionInput{

    private String type;

    private Integer sectNum;

    private Long wellDevId;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSectNum() {
		return sectNum;
	}

	public void setSectNum(Integer sectNum) {
		this.sectNum = sectNum;
	}

	public Long getWellDevId() {
		return wellDevId;
	}

	public void setWellDevId(Long wellDevId) {
		this.wellDevId = wellDevId;
	}

    
}
