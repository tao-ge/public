package com.ycnet.frms.vo.mobile.ocii;

/**
 * 首页查询接收类
* @ClassName: IndexBean 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author YHT(作者)  
* @date 2018年10月16日 下午1:11:56 
* @version V1.0
 */
public class IndexLine{
	
	private Long id;
	
    private String type;

    private String devState;
    

    private Long devIdA;
    
    private String devNameA;
    
    private Double curLngA;

    private Double curLatA;
    
    
    private Long devIdZ;
    
    private String devNameZ;
    
    private Double curLngZ;

    private Double curLatZ;

    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDevState() {
		return devState;
	}

	public void setDevState(String devState) {
		this.devState = devState;
	}

	public String getDevNameA() {
		return devNameA;
	}

	public void setDevNameA(String devNameA) {
		this.devNameA = devNameA;
	}

	public Double getCurLngA() {
		return curLngA;
	}

	public void setCurLngA(Double curLngA) {
		this.curLngA = curLngA;
	}

	public Double getCurLatA() {
		return curLatA;
	}

	public void setCurLatA(Double curLatA) {
		this.curLatA = curLatA;
	}

	public Long getDevIdA() {
		return devIdA;
	}

	public void setDevIdA(Long devIdA) {
		this.devIdA = devIdA;
	}

	public Long getDevIdZ() {
		return devIdZ;
	}

	public void setDevIdZ(Long devIdZ) {
		this.devIdZ = devIdZ;
	}

	public String getDevNameZ() {
		return devNameZ;
	}

	public void setDevNameZ(String devNameZ) {
		this.devNameZ = devNameZ;
	}

	public Double getCurLngZ() {
		return curLngZ;
	}

	public void setCurLngZ(Double curLngZ) {
		this.curLngZ = curLngZ;
	}

	public Double getCurLatZ() {
		return curLatZ;
	}

	public void setCurLatZ(Double curLatZ) {
		this.curLatZ = curLatZ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((curLatA == null) ? 0 : curLatA.hashCode());
		result = prime * result + ((curLatZ == null) ? 0 : curLatZ.hashCode());
		result = prime * result + ((curLngA == null) ? 0 : curLngA.hashCode());
		result = prime * result + ((curLngZ == null) ? 0 : curLngZ.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IndexLine other = (IndexLine) obj;
		if (curLatA == null) {
			if (other.curLatA != null)
				return false;
		} else if (!curLatA.equals(other.curLatA))
			return false;
		if (curLatZ == null) {
			if (other.curLatZ != null)
				return false;
		} else if (!curLatZ.equals(other.curLatZ))
			return false;
		if (curLngA == null) {
			if (other.curLngA != null)
				return false;
		} else if (!curLngA.equals(other.curLngA))
			return false;
		if (curLngZ == null) {
			if (other.curLngZ != null)
				return false;
		} else if (!curLngZ.equals(other.curLngZ))
			return false;
		return true;
	}

	
}
