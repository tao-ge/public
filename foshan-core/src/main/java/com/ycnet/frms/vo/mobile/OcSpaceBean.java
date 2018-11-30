/**   
 * @Package: com.ycnet.frms.vo.mobile 
 * @author: FL   
 * @date: 2018年10月11日 上午10:10:51 
 */
package com.ycnet.frms.vo.mobile;

import java.util.List;

/** 
* @ClassName: OcSpaceBean 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月11日 上午10:10:51  
*/
public class OcSpaceBean {
	
	private Integer spaceId;

    private Short pipingCount;

    private String surface;

    private List<OcpipBean> pipList;

	public Integer getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(Integer spaceId) {
		this.spaceId = spaceId;
	}

	public Short getPipingCount() {
		return pipingCount;
	}

	public void setPipingCount(Short pipingCount) {
		this.pipingCount = pipingCount;
	}

	public String getSurface() {
		return surface;
	}

	public void setSurface(String surface) {
		this.surface = surface;
	}

	public List<OcpipBean> getPipList() {
		return pipList;
	}

	public void setPipList(List<OcpipBean> pipList) {
		this.pipList = pipList;
	}
    
}
