package com.ycnet.frms.vo.mobile;

public class UDPsendEntityBean {

    private String msgType;
    
    private String version;
    
    private String settingUID;//16进制buffer
    
    private String result;
    
    private String cltID1;
    
    private String cltID2;

	public String getMsgType() {
		return msgType;
	}

	public String getVersion() {
		return version;
	}

	public String getSettingUID() {
		return settingUID;
	}

	public String getResult() {
		return result;
	}

	public String getCltID1() {
		return cltID1;
	}

	public String getCltID2() {
		return cltID2;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setSettingUID(String settingUID) {
		this.settingUID = settingUID;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setCltID1(String cltID1) {
		this.cltID1 = cltID1;
	}

	public void setCltID2(String cltID2) {
		this.cltID2 = cltID2;
	}
}
