package com.ycnet.frms.bean;

public class Basecode {
    private Long codeId;

    private String classCode;

    private String className;

    
    private String valueCode;

    private String valueName;

    private String managerSign;
    
    private String directFlg;

    public Long getCodeId() {
        return codeId;
    }

    public void setCodeId(Long codeId) {
        this.codeId = codeId;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getValueCode() {
        return valueCode;
    }

    public void setValueCode(String valueCode) {
        this.valueCode = valueCode;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public String getManagerSign() {
        return managerSign;
    }

    public void setManagerSign(String managerSign) {
        this.managerSign = managerSign;
    }

	public String getDirectFlg() {
		return directFlg;
	}

	public void setDirectFlg(String directFlg) {
		this.directFlg = directFlg;
	}
}