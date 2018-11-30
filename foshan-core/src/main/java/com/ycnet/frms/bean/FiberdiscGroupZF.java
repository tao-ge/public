package com.ycnet.frms.bean;

import java.util.List;

import com.ycnet.frms.vo.CableSectionBean;

/**
 * 
* @ClassName: FiberdiscGroupZF 
* @Description: 导出数据库新建类,数据生成用
* @author DZY 
* @date 2017年10月23日 下午1:48:08 
* @version V1.0
 */
public class FiberdiscGroupZF {
	private Long groupId;

    private Long devId;

    private String side;

    private Integer discNum;

    private Integer portNum;
    
    private String groupName;
    
    private String groupDesc;
    
    private Long createUser;
    
    private Long lastModifyUser;
    
    private List<CableSectionBean> cableList;

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
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

	public Integer getDiscNum() {
		return discNum;
	}

	public void setDiscNum(Integer discNum) {
		this.discNum = discNum;
	}

	public Integer getPortNum() {
		return portNum;
	}

	public void setPortNum(Integer portNum) {
		this.portNum = portNum;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
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

	public List<CableSectionBean> getCableList() {
		return cableList;
	}

	public void setCableList(List<CableSectionBean> cableList) {
		this.cableList = cableList;
	}

	
}
