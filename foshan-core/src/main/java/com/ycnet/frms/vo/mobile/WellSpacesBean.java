/**   
 * @Package: com.ycnet.frms.vo.mobile 
 * @author: FL   
 * @date: 2018年10月19日 下午3:39:40 
 */
package com.ycnet.frms.vo.mobile;

import java.util.List;

/** 
* @ClassName: WellSpacesBean 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月19日 下午3:39:40  
*/
public class WellSpacesBean {
	
	private Long wellId;
	
	private String wellName;
	
	private List<SpaceBean> spaceList;

	public Long getWellId() {
		return wellId;
	}

	public void setWellId(Long wellId) {
		this.wellId = wellId;
	}

	public String getWellName() {
		return wellName;
	}

	public void setWellName(String wellName) {
		this.wellName = wellName;
	}

	public List<SpaceBean> getSpaceList() {
		return spaceList;
	}

	public void setSpaceList(List<SpaceBean> spaceList) {
		this.spaceList = spaceList;
	}
	

}
