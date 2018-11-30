package com.ycnet.frms.bean;

public class RouteNode {
    private Long nodeId;

    private Long routeId;

    private Long devId;

    private String code;

    private Long sectId;

    private Integer fiberNo;

    private Integer nodeNo;

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public Long getDevId() {
        return devId;
    }

    public void setDevId(Long devId) {
        this.devId = devId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getSectId() {
        return sectId;
    }

    public void setSectId(Long sectId) {
        this.sectId = sectId;
    }

    public Integer getFiberNo() {
        return fiberNo;
    }

    public void setFiberNo(Integer fiberNo) {
        this.fiberNo = fiberNo;
    }

    public Integer getNodeNo() {
        return nodeNo;
    }

    public void setNodeNo(Integer nodeNo) {
        this.nodeNo = nodeNo;
    }
}