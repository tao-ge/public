package com.ycnet.frms.vo;

import java.math.BigDecimal;

import com.ycnet.frms.bean.Cable;

public class CablesBean extends Cable{

    private String cableCode;

    private String cableName;
    
    private String devCode;//条件查询设施编码

    private String devName;//条件查询设施名称

    private Long proId;
    
    //
    private String acode;
    
    private String zcode;
    
    private String acodeE;
    
    private String zcodeE;
    
    private String secName;
    
    private String secCode;
    
    private Long sectId;
    
    private Long fiberNo;
    
    private String devCodeA;
    
    private String devCodeZ;
    
    private String zdevName;

	private String adevName;
	
	private BigDecimal cableLen;
	
	private String areaCode1;
	
	private String areaCode2;
	
	private String  isTerminat;//光缆状态
	
	private String check;//是否选中单选按钮
	
	private String sectState;//光缆状态
	
	//光缆段下光纤为直熔
	private String investment;
	
	private String areadevCode;//区
	
	public String getAreadevCode() {
		return areadevCode;
	}

	public void setAreadevCode(String areadevCode) {
		this.areadevCode = areadevCode;
	}

	public String getInvestment() {
		return investment;
	}

	public void setInvestment(String investment) {
		this.investment = investment;
	}

	private String investmentA;
	private String investmentZ;
	//光缆段下光纤a端成端
	private String inusedA;
	//光缆段下光纤z端成端
	private String inusedZ;
	//光缆段下光纤未成端
	private String notInused;
	
	private String inused;
	

	public String getInused() {
		return inused;
	}

	public void setInused(String inused) {
		this.inused = inused;
	}

	public String getInusedA() {
		return inusedA;
	}

	public void setInusedA(String inusedA) {
		this.inusedA = inusedA;
	}

	public String getInusedZ() {
		return inusedZ;
	}

	public void setInusedZ(String inusedZ) {
		this.inusedZ = inusedZ;
	}

	

	

	public String getInvestmentA() {
		return investmentA;
	}

	public void setInvestmentA(String investmentA) {
		this.investmentA = investmentA;
	}

	public String getInvestmentZ() {
		return investmentZ;
	}

	public void setInvestmentZ(String investmentZ) {
		this.investmentZ = investmentZ;
	}

	public String getNotInused() {
		return notInused;
	}

	public void setNotInused(String notInused) {
		this.notInused = notInused;
	}

	public String getIsTerminat() {
		return isTerminat;
	}

	public void setIsTerminat(String isTerminat) {
		this.isTerminat = isTerminat;
	}
   

	
    

	public String getZdevName() {
		return zdevName;
	}

	public String getAreaCode1() {
		return areaCode1;
	}

	public void setAreaCode1(String areaCode1) {
		this.areaCode1 = areaCode1;
	}

	public BigDecimal getCableLen() {
		return cableLen;
	}

	public void setCableLen(BigDecimal cableLen) {
		this.cableLen = cableLen;
	}

	public String getAcodeE() {
		return acodeE;
	}

	public void setAcodeE(String acodeE) {
		this.acodeE = acodeE;
	}

	public String getZcodeE() {
		return zcodeE;
	}

	public void setZcodeE(String zcodeE) {
		this.zcodeE = zcodeE;
	}

	public String getSecCode() {
		return secCode;
	}

	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}

	public void setZdevName(String zdevName) {
		this.zdevName = zdevName;
	}

	public String getAdevName() {
		return adevName;
	}

	public void setAdevName(String adevName) {
		this.adevName = adevName;
	}

	private int pageno;

	

	public String getAcode() {
		return acode;
	}

	public void setAcode(String acode) {
		this.acode = acode;
	}

	public String getZcode() {
		return zcode;
	}

	public void setZcode(String zcode) {
		this.zcode = zcode;
	}

	public String getSecName() {
		return secName;
	}

	public void setSecName(String secName) {
		this.secName = secName;
	}

	public Long getSectId() {
		return sectId;
	}

	public void setSectId(Long sectId) {
		this.sectId = sectId;
	}

	public Long getFiberNo() {
		return fiberNo;
	}

	public void setFiberNo(Long fiberNo) {
		this.fiberNo = fiberNo;
	}

	public String getDevCodeA() {
		return devCodeA;
	}

	public void setDevCodeA(String devCodeA) {
		this.devCodeA = devCodeA;
	}

	public String getDevCodeZ() {
		return devCodeZ;
	}

	public void setDevCodeZ(String devCodeZ) {
		this.devCodeZ = devCodeZ;
	}

	

	public String getCableCode() {
		return cableCode;
	}

	public void setCableCode(String cableCode) {
		this.cableCode = cableCode;
	}

	public String getCableName() {
		return cableName;
	}

	public void setCableName(String cableName) {
		this.cableName = cableName;
	}

	public Long getProId() {
		return proId;
	}

	public void setProId(Long proId) {
		this.proId = proId;
	}

	public int getPageno() {
		return pageno;
	}

	public void setPageno(int pageno) {
		this.pageno = pageno;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public String getDevCode() {
		return devCode;
	}

	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getAreaCode2() {
		return areaCode2;
	}

	public void setAreaCode2(String areaCode2) {
		this.areaCode2 = areaCode2;
	}

	public String getSectState() {
		return sectState;
	}

	public void setSectState(String sectState) {
		this.sectState = sectState;
	}  


}