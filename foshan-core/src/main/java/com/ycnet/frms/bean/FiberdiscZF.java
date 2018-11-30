package com.ycnet.frms.bean;

/**
 * 
* @ClassName: FiberdiscZF 
* @Description: 导出数据库新建类,数据生成用
* @author DZY  
* @date 2017年10月20日 下午2:31:11 
* @version V1.0
 */
public class FiberdiscZF {
	private Long fiberDiscId;

	private Long devId;

	private String side;

	private Long discRowNo;

	private String discCode;

	private Long discColNo;

	private String port01;

	private Long sectId;

	private Long fiberNum;

	private String remark;

    private Long createUser;
    
    private Long lastModifyUser;

    private String detailsPort;

	public Long getFiberDiscId() {
		return fiberDiscId;
	}

	public void setFiberDiscId(Long fiberDiscId) {
		this.fiberDiscId = fiberDiscId;
	}

	public Long getDevId() {
		return devId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public Long getDiscRowNo() {
		return discRowNo;
	}

	public void setDiscRowNo(Long discRowNo) {
		this.discRowNo = discRowNo;
	}

	public String getDiscCode() {
		return discCode;
	}

	public void setDiscCode(String discCode) {
		this.discCode = discCode;
	}

	public Long getDiscColNo() {
		return discColNo;
	}

	public void setDiscColNo(Long discColNo) {
		this.discColNo = discColNo;
	}

	public String getPort01() {
		return port01;
	}

	public void setPort01(String port01) {
		this.port01 = port01;
	}

	public Long getSectId() {
		return sectId;
	}

	public void setSectId(Long sectId) {
		this.sectId = sectId;
	}

	public Long getFiberNum() {
		return fiberNum;
	}

	public void setFiberNum(Long fiberNum) {
		this.fiberNum = fiberNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	public Long getLastModifyUser() {
		return lastModifyUser;
	}

	public void setLastModifyUser(Long lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}

	public String getDetailsPort() {
		return detailsPort;
	}

	public void setDetailsPort(String detailsPort) {
		this.detailsPort = detailsPort;
	}
}
