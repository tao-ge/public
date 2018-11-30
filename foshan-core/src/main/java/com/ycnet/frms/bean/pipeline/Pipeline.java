package com.ycnet.frms.bean.pipeline;

import java.math.BigDecimal;

import com.ycnet.frms.bean.point.Point;

public class Pipeline {
    private Integer id;

    private String pipelineName;

    private Integer frontPointId;

    private Integer backPointId;

    private String pipelineType;

    private Integer pore;

    private String pipe;

    private String trunkName;

    private String pepelineClassify;
    
    private BigDecimal abaiduX;

    private BigDecimal abaiduY;
    
    private BigDecimal zbaiduX;

    private BigDecimal zbaiduY;
    
    private Point frontPoint;
    private Point backPoint;
    

    

	public BigDecimal getAbaiduX() {
		return abaiduX;
	}

	public void setAbaiduX(BigDecimal abaiduX) {
		this.abaiduX = abaiduX;
	}

	public BigDecimal getAbaiduY() {
		return abaiduY;
	}

	public void setAbaiduY(BigDecimal abaiduY) {
		this.abaiduY = abaiduY;
	}

	public BigDecimal getZbaiduX() {
		return zbaiduX;
	}

	public void setZbaiduX(BigDecimal zbaiduX) {
		this.zbaiduX = zbaiduX;
	}

	public BigDecimal getZbaiduY() {
		return zbaiduY;
	}

	public void setZbaiduY(BigDecimal zbaiduY) {
		this.zbaiduY = zbaiduY;
	}

	public Point getFrontPoint() {
		return frontPoint;
	}

	public void setFrontPoint(Point frontPoint) {
		this.frontPoint = frontPoint;
	}

	public Point getBackPoint() {
		return backPoint;
	}

	public void setBackPoint(Point backPoint) {
		this.backPoint = backPoint;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPipelineName() {
        return pipelineName;
    }

    public void setPipelineName(String pipelineName) {
        this.pipelineName = pipelineName == null ? null : pipelineName.trim();
    }

    public Integer getFrontPointId() {
        return frontPointId;
    }

    public void setFrontPointId(Integer frontPointId) {
        this.frontPointId = frontPointId;
    }

    public Integer getBackPointId() {
        return backPointId;
    }

    public void setBackPointId(Integer backPointId) {
        this.backPointId = backPointId;
    }

    public String getPipelineType() {
        return pipelineType;
    }

    public void setPipelineType(String pipelineType) {
        this.pipelineType = pipelineType == null ? null : pipelineType.trim();
    }

    public Integer getPore() {
        return pore;
    }

    public void setPore(Integer pore) {
        this.pore = pore;
    }

    public String getPipe() {
        return pipe;
    }

    public void setPipe(String pipe) {
        this.pipe = pipe == null ? null : pipe.trim();
    }

    public String getTrunkName() {
        return trunkName;
    }

    public void setTrunkName(String trunkName) {
        this.trunkName = trunkName == null ? null : trunkName.trim();
    }

    public String getPepelineClassify() {
        return pepelineClassify;
    }

    public void setPepelineClassify(String pepelineClassify) {
        this.pepelineClassify = pepelineClassify == null ? null : pepelineClassify.trim();
    }
}