/**   
 * @Package: com.ycnet.frms.vo.mobile 
 * @author: FL   
 * @date: 2018年10月15日 上午9:46:21 
 */
package com.ycnet.frms.vo.mobile.ocii;

/** 
* @ClassName: OcWellInfo 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月15日 上午9:46:21  
*/
public class OcWellInfo {
	
	private Long wellId;
	
	private String baiduX;

	private String baiduY;

	private String name;//设施名称或者井名称

	public Long getWellId() {
		return wellId;
	}

	public void setWellId(Long wellId) {
		this.wellId = wellId;
	}

	public String getBaiduX() {
		return baiduX;
	}

	public void setBaiduX(String baiduX) {
		this.baiduX = baiduX;
	}

	public String getBaiduY() {
		return baiduY;
	}

	public void setBaiduY(String baiduY) {
		this.baiduY = baiduY;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
