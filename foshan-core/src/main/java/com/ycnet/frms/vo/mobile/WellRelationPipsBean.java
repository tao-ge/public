/**   
 * @Package: com.ycnet.frms.vo.mobile 
 * @author: FL   
 * @date: 2018年10月15日 上午9:30:36 
 */
package com.ycnet.frms.vo.mobile;

import java.util.List;

import com.ycnet.frms.bean.OcWell;
import com.ycnet.frms.vo.mobile.ocii.OcWellInfo;

/** 
* @ClassName: WellRelationPipsBean 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月15日 上午9:30:36  
*/
public class WellRelationPipsBean {
	
	private Long pipingSectId;
	
	private String pipingName;
	
	private String pipingSectType;
	
	private List<OcWellInfo> wellList;//井信息集合

	public Long getPipingSectId() {
		return pipingSectId;
	}

	public void setPipingSectId(Long pipingSectId) {
		this.pipingSectId = pipingSectId;
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

	public void setPipingSectType(String pipingSectType) {
		this.pipingSectType = pipingSectType;
	}

	public List<OcWellInfo> getWellList() {
		return wellList;
	}

	public void setWellList(List<OcWellInfo> wellList) {
		this.wellList = wellList;
	}
	
}
