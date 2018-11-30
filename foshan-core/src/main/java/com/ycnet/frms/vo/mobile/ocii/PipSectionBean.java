/**   
 * @Package: com.ycnet.frms.vo.mobile.ocii 
 * @author: FL   
 * @date: 2018年10月19日 下午4:25:57 
 */
package com.ycnet.frms.vo.mobile.ocii;

/** 
* @ClassName: PipSectionBean 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月19日 下午4:25:57  
*/
public class PipSectionBean {
	
	private Long pipingSectId;
	
	private String pipingName;
	
	private String phyLen;// 物理长度
	
	private Long awellId;// A端井或设施ID
	
	private Long zwellId;// Z端井或设施ID
	
	private String surfaceA;// A端面
	
	private String surfaceZ;// Z端面
	
	private String typeA;// A类型 01 井 02 设施
	
	private String typeZ; // Z类型 01 井 02 设施
	
	private String abaiduX;// A经度
	
	private String abaiduY;// A纬度
	
	private String zbaiduX;// Z经度
	
	private String zbaiduY;// Z纬度
	
	private int diameter;// 直径
	
	private int subtubeCount;// 子孔数
	
	private int usedsubtubeCount;// 占用子孔数
	
	private int unusedsubtubeCount;// 未占用子孔数
	
	private String isOccupy;// 是否占用
	
	private String pipingSectType;
	
	private String remark;
	
	private Long pipingWellIdA;
	
	private Long pipingWellIdZ;
	

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

	public String getPhyLen() {
		return phyLen;
	}

	public void setPhyLen(String phyLen) {
		this.phyLen = phyLen;
	}

	public Long getAwellId() {
		return awellId;
	}

	public void setAwellId(Long awellId) {
		this.awellId = awellId;
	}

	public Long getZwellId() {
		return zwellId;
	}

	public void setZwellId(Long zwellId) {
		this.zwellId = zwellId;
	}


	public String getAbaiduX() {
		return abaiduX;
	}

	public void setAbaiduX(String abaiduX) {
		this.abaiduX = abaiduX;
	}

	public String getAbaiduY() {
		return abaiduY;
	}

	public void setAbaiduY(String abaiduY) {
		this.abaiduY = abaiduY;
	}

	public String getZbaiduX() {
		return zbaiduX;
	}

	public void setZbaiduX(String zbaiduX) {
		this.zbaiduX = zbaiduX;
	}

	public String getZbaiduY() {
		return zbaiduY;
	}

	public void setZbaiduY(String zbaiduY) {
		this.zbaiduY = zbaiduY;
	}

	public int getDiameter() {
		return diameter;
	}

	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}

	public int getSubtubeCount() {
		return subtubeCount;
	}

	public void setSubtubeCount(int subtubeCount) {
		this.subtubeCount = subtubeCount;
	}

	public int getUsedsubtubeCount() {
		return usedsubtubeCount;
	}

	public void setUsedsubtubeCount(int usedsubtubeCount) {
		this.usedsubtubeCount = usedsubtubeCount;
	}

	public int getUnusedsubtubeCount() {
		return unusedsubtubeCount;
	}

	public void setUnusedsubtubeCount(int unusedsubtubeCount) {
		this.unusedsubtubeCount = unusedsubtubeCount;
	}

	public String getIsOccupy() {
		return isOccupy;
	}

	public void setIsOccupy(String isOccupy) {
		this.isOccupy = isOccupy;
	}

	public String getPipingSectType() {
		return pipingSectType;
	}

	public void setPipingSectType(String pipingSectType) {
		this.pipingSectType = pipingSectType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSurfaceA() {
		return surfaceA;
	}

	public void setSurfaceA(String surfaceA) {
		this.surfaceA = surfaceA;
	}

	public String getSurfaceZ() {
		return surfaceZ;
	}

	public void setSurfaceZ(String surfaceZ) {
		this.surfaceZ = surfaceZ;
	}

	public String getTypeA() {
		return typeA;
	}

	public void setTypeA(String typeA) {
		this.typeA = typeA;
	}

	public String getTypeZ() {
		return typeZ;
	}

	public void setTypeZ(String typeZ) {
		this.typeZ = typeZ;
	}

	public Long getPipingWellIdA() {
		return pipingWellIdA;
	}

	public void setPipingWellIdA(Long pipingWellIdA) {
		this.pipingWellIdA = pipingWellIdA;
	}

	public Long getPipingWellIdZ() {
		return pipingWellIdZ;
	}

	public void setPipingWellIdZ(Long pipingWellIdZ) {
		this.pipingWellIdZ = pipingWellIdZ;
	}

}
