/**   
 * @Package: com.ycnet.frms.vo.mobile.ocii 
 * @author: FL   
 * @date: 2018年10月11日 下午3:50:44 
 */
package com.ycnet.frms.vo.mobile.ocii;

import java.util.List;

/** 
* @ClassName: OcSectBean 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月11日 下午3:50:44  
*/
public class OcSectBean {
	
	private Long sectId;

    private String secName;
	
    private List<wellName> wellList;

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

	public List<wellName> getWellList() {
		return wellList;
	}

	public void setWellList(List<wellName> wellList) {
		this.wellList = wellList;
	}

    
    
}
