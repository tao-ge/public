package com.ycnet.frms.vo;

import java.util.List;

public class ResultOfPlatFormAddDevies {
	
	private List<DeviceResult> devices;
	private String result;
	private String desc;

	

	public List<DeviceResult> getDevices() {
		return devices;
	}

	public void setDevices(List<DeviceResult> devices) {
		this.devices = devices;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public static class DeviceResult
	{
		private String sn;
		private String did;
		public String getSn() {
			return sn;
		}
		public void setSn(String sn) {
			this.sn = sn;
		}
		public String getDid() {
			return did;
		}
		public void setDid(String did) {
			this.did = did;
		}
	}

}
