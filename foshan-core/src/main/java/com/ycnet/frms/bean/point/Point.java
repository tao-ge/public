package com.ycnet.frms.bean.point;

import java.math.BigDecimal;

public class Point {
    private Integer id;

    private String pointName;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private String area;

    private BigDecimal baiduX;

    private BigDecimal baiduY;

    private String isTranslated;


	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName == null ? null : pointName.trim();
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public BigDecimal getBaiduX() {
        return baiduX;
    }

    public void setBaiduX(BigDecimal baiduX) {
        this.baiduX = baiduX;
    }

    public BigDecimal getBaiduY() {
        return baiduY;
    }

    public void setBaiduY(BigDecimal baiduY) {
        this.baiduY = baiduY;
    }

    public String getIsTranslated() {
        return isTranslated;
    }

    public void setIsTranslated(String isTranslated) {
        this.isTranslated = isTranslated == null ? null : isTranslated.trim();
    }
}