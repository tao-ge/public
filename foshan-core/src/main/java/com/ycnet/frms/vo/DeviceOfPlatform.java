package com.ycnet.frms.vo;

public class DeviceOfPlatform {
	private String sn; //设备产品编号（在云平台创建）
	private String productId; //设备产品编号（在云平台创建）
	private String didkey;//设备初始密钥，随机产生
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getDidkey() {
		return didkey;
	}
	public void setDidkey(String didkey) {
		this.didkey = didkey;
	}
	@Override
	public String toString() {
		return "{\"devices\":[{\"sn\":\"" + sn + "\", \"didKey\":\"" + didkey
				+ "\", \"productId\":\"" + productId + "\"}]}";
	}
}
