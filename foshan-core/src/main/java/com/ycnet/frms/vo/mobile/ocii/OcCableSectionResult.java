package com.ycnet.frms.vo.mobile.ocii;

/**
 * 设备-查询光缆段入参
* @ClassName: OcCableSectionInput 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author YHT(作者)  
* @date 2018年10月25日 上午11:03:11 
* @version V1.0
 */
public class OcCableSectionResult{
	
	private Long sectId;

	private String secName;
	
    private Long fiberNum;
    
    private Long cableWellId;

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
		this.secName = secName;
	}

	public Long getFiberNum() {
		return fiberNum;
	}

	public void setFiberNum(Long fiberNum) {
		this.fiberNum = fiberNum;
	}

	public Long getCableWellId() {
		return cableWellId;
	}

	public void setCableWellId(Long cableWellId) {
		this.cableWellId = cableWellId;
	}



    
}
