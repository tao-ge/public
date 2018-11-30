package com.ycnet.frms.vo;

import java.util.List;

public class JumperInfo {
	
	private Long devId;
	
	private String code;
	
	private Long adevId;
	
	private String acode ;
	
	private Long alinesId;
	
	private String aunknownPointName;
	
	private Long zdevId ;
	
	private String zcode;
	
	private Long zlinesId;
	
	private String zunknownPointName;
	
	private String unknownPointName;
	
	private String sign;//跳纤标志  0 添加 ;1 修改
	
	private Long designroutesId;//调度工单路由ID
	
	private String adevData;//A端设备检测数据
	
	private String zdevData;//Z端设备检测数据
	
	private String impleBack;//实施反馈信息
	
    private List<String> picList;//图片路径集合
	
	public String getUnknownPointName() {
		return unknownPointName;
	}

	public void setUnknownPointName(String unknownPointName) {
		this.unknownPointName = unknownPointName;
	}

	private String srvName;
	
	private Long orgId;
	
	private Long userId;
	
	private String adevType;//3,4点类型
	private String zdevType;//3,4点类型
	
	
	

	public String getAdevType() {
		return adevType;
	}

	public void setAdevType(String adevType) {
		this.adevType = adevType;
	}

	public String getZdevType() {
		return zdevType;
	}

	public void setZdevType(String zdevType) {
		this.zdevType = zdevType;
	}

	public Long getDevId() {
		return devId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getAdevId() {
		return adevId;
	}

	public void setAdevId(Long adevId) {
		this.adevId = adevId;
	}

	public String getAcode() {
		return acode;
	}

	public void setAcode(String acode) {
		this.acode = acode;
	}


	public Long getZdevId() {
		return zdevId;
	}

	public void setZdevId(Long zdevId) {
		this.zdevId = zdevId;
	}

	public String getZcode() {
		return zcode;
	}

	public void setZcode(String zcode) {
		this.zcode = zcode;
	}


	public Long getAlinesId() {
		return alinesId;
	}

	public void setAlinesId(Long alinesId) {
		this.alinesId = alinesId;
	}

	public Long getZlinesId() {
		return zlinesId;
	}

	public void setZlinesId(Long zlinesId) {
		this.zlinesId = zlinesId;
	}

	public String getSrvName() {
		return srvName;
	}

	public void setSrvName(String srvName) {
		this.srvName = srvName;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getAunknownPointName() {
		return aunknownPointName;
	}

	public void setAunknownPointName(String aunknownPointName) {
		this.aunknownPointName = aunknownPointName;
	}

	public String getZunknownPointName() {
		return zunknownPointName;
	}

	public void setZunknownPointName(String zunknownPointName) {
		this.zunknownPointName = zunknownPointName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Long getDesignroutesId() {
		return designroutesId;
	}

	public void setDesignroutesId(Long designroutesId) {
		this.designroutesId = designroutesId;
	}

	public String getAdevData() {
		return adevData;
	}

	public void setAdevData(String adevData) {
		this.adevData = adevData;
	}

	public String getZdevData() {
		return zdevData;
	}

	public void setZdevData(String zdevData) {
		this.zdevData = zdevData;
	}

	public String getImpleBack() {
		return impleBack;
	}

	public void setImpleBack(String impleBack) {
		this.impleBack = impleBack;
	}

	public List<String> getPicList() {
		return picList;
	}

	public void setPicList(List<String> picList) {
		this.picList = picList;
	}

	
}
