package com.ycnet.frms.bean;

import java.util.List;

/**
 * 
* @ClassName: CutData 
* @Description: 生成数据库.txt文件时,切分文件过大数据,把原本大数据的.txt分为多个
* @author DZY  
* @date 2017年10月24日 上午10:13:02 
* @version V1.0
 */
public class CutData {
	private String cutName;//切分的表名
	
	private int cutNum;//把数据切分为的数量
	
	private String baseUrl;//文件下载路径
	
	private List<String> fileNameList;//拆分后的文件名

	public String getCutName() {
		return cutName;
	}

	public void setCutName(String cutName) {
		this.cutName = cutName;
	}

	public int getCutNum() {
		return cutNum;
	}

	public void setCutNum(int cutNum) {
		this.cutNum = cutNum;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public List<String> getFileNameList() {
		return fileNameList;
	}

	public void setFileNameList(List<String> fileNameList) {
		this.fileNameList = fileNameList;
	}
}
