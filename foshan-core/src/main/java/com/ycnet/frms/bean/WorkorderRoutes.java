package com.ycnet.frms.bean;

public class WorkorderRoutes {
    private Long designroutesId;

    private Long designplanId;

    private Integer routesSort;

    private String acode;

    private Long adevId;

    private String zcode;

    private Long zdevId;
    
    private String adevName;
    
    private String zdevName;
    
    private String rouState;//光路状态 0未完成，1完成
    
    private String isImple;
    
    


	public String getIsImple() {
		return isImple;
	}

	public void setIsImple(String isImple) {
		this.isImple = isImple;
	}

	public String getRouState() {
		return rouState;
	}

	public void setRouState(String rouState) {
		this.rouState = rouState;
	}

	public String getAdevName() {
		return adevName;
	}

	public void setAdevName(String adevName) {
		this.adevName = adevName;
	}

	public String getZdevName() {
		return zdevName;
	}

	public void setZdevName(String zdevName) {
		this.zdevName = zdevName;
	}

	public Long getDesignroutesId() {
        return designroutesId;
    }

    public void setDesignroutesId(Long designroutesId) {
        this.designroutesId = designroutesId;
    }

    public Long getDesignplanId() {
        return designplanId;
    }

    public void setDesignplanId(Long designplanId) {
        this.designplanId = designplanId;
    }

    public Integer getRoutesSort() {
        return routesSort;
    }

    public void setRoutesSort(Integer routesSort) {
        this.routesSort = routesSort;
    }

    public String getAcode() {
        return acode;
    }

    public void setAcode(String acode) {
        this.acode = acode == null ? null : acode.trim();
    }

    public Long getAdevId() {
        return adevId;
    }

    public void setAdevId(Long adevId) {
        this.adevId = adevId;
    }

    public String getZcode() {
        return zcode;
    }

    public void setZcode(String zcode) {
        this.zcode = zcode == null ? null : zcode.trim();
    }

    public Long getZdevId() {
        return zdevId;
    }

    public void setZdevId(Long zdevId) {
        this.zdevId = zdevId;
    }
}