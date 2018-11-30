/**   
 * @Package: com.ycnet.frms.vo.mobile 
 * @author: FL   
 * @date: 2018年10月11日 上午10:31:05 
 */
package com.ycnet.frms.vo.mobile;

/** 
* @ClassName: OcpipBean 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月11日 上午10:31:05  
*/
public class OcpipBean {

	private Long pipingId;

    private Short subtubeCount;

    private Short usedsubtubeCount;

    private Short unusedsubtubeCount;

	public Long getPipingId() {
		return pipingId;
	}

	public void setPipingId(Long pipingId) {
		this.pipingId = pipingId;
	}

	public Short getSubtubeCount() {
		return subtubeCount;
	}

	public void setSubtubeCount(Short subtubeCount) {
		this.subtubeCount = subtubeCount;
	}

	public Short getUsedsubtubeCount() {
		return usedsubtubeCount;
	}

	public void setUsedsubtubeCount(Short usedsubtubeCount) {
		this.usedsubtubeCount = usedsubtubeCount;
	}

	public Short getUnusedsubtubeCount() {
		return unusedsubtubeCount;
	}

	public void setUnusedsubtubeCount(Short unusedsubtubeCount) {
		this.unusedsubtubeCount = unusedsubtubeCount;
	}
}
