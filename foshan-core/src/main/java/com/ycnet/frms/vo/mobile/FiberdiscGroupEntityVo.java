package com.ycnet.frms.vo.mobile;

import java.util.Date;
import java.util.List;

import com.ycnet.frms.vo.DeviceDiscinfoEntityVo;


/** 
* @ClassName: FiberdiscGroupEntityVo 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author (作者)  
* @date 2018年4月13日 下午9:34:05 
* @version V1.0 
*/
public class FiberdiscGroupEntityVo {

	private Long groupId;

    private Long devId;

    private Integer discNum;

    private String discCode;//熔纤盘编码
    
    private List<DeviceDiscinfoEntityVo> DeviceDiscinfoEntityVoList;//

	public Long getGroupId() {
		return groupId;
	}

	public Long getDevId() {
		return devId;
	}

	public Integer getDiscNum() {
		return discNum;
	}

	public String getDiscCode() {
		return discCode;
	}

	public List<DeviceDiscinfoEntityVo> getDeviceDiscinfoEntityVoList() {
		return DeviceDiscinfoEntityVoList;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public void setDiscNum(Integer discNum) {
		this.discNum = discNum;
	}

	public void setDiscCode(String discCode) {
		this.discCode = discCode;
	}

	public void setDeviceDiscinfoEntityVoList(List<DeviceDiscinfoEntityVo> deviceDiscinfoEntityVoList) {
		DeviceDiscinfoEntityVoList = deviceDiscinfoEntityVoList;
	}
    
}
