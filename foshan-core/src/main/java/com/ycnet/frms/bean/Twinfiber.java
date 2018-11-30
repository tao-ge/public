package com.ycnet.frms.bean;

public class Twinfiber {
    private Long id;

    private Long devId;

    private String side;

    private String discCode;

    private String portCode1;

    private String portCode2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDiscCode() {
        return discCode;
    }

    public void setDiscCode(String discCode) {
        this.discCode = discCode;
    }

    public String getPortCode1() {
        return portCode1;
    }

    public void setPortCode1(String portCode1) {
        this.portCode1 = portCode1;
    }

    public String getPortCode2() {
        return portCode2;
    }

    public void setPortCode2(String portCode2) {
        this.portCode2 = portCode2;
    }
}