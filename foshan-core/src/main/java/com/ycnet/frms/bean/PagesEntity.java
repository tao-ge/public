package com.ycnet.frms.bean;

public class PagesEntity {
    private Long pageId;

    private String pageName;

    private String pageUrl;

    private String icon;

    private Long pageRank;

    private Long pageOrders;

    private String pageDesc;

    private Long parentPageId;

    private String flag;

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName == null ? null : pageName.trim();
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl == null ? null : pageUrl.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Long getPageRank() {
        return pageRank;
    }

    public void setPageRank(Long pageRank) {
        this.pageRank = pageRank;
    }

    public Long getPageOrders() {
        return pageOrders;
    }

    public void setPageOrders(Long pageOrders) {
        this.pageOrders = pageOrders;
    }

    public String getPageDesc() {
        return pageDesc;
    }

    public void setPageDesc(String pageDesc) {
        this.pageDesc = pageDesc == null ? null : pageDesc.trim();
    }

    public Long getParentPageId() {
        return parentPageId;
    }

    public void setParentPageId(Long parentPageId) {
        this.parentPageId = parentPageId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }
}