package com.ycnet.frms.bean;

import java.util.List;
//权限有判断相等，添加字段改euqals和hashcode方法
public class Pages {
    private Long pageId;

    private String pageName;

    private String pageUrl;

    private String icon;

    private Long pageRank;

    private Long pageOrders;

    private String pageDesc;

    private Long parentPageId;
    
    private String parentPageName;

    private String flag;
    
    private String directFlg;
    
    private List<Pages> childPages;
    
    private String chooseFlag;

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
        this.pageName = pageName;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
        this.pageDesc = pageDesc;
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
        this.flag = flag;
    }

	public String getDirectFlg() {
		return directFlg;
	}

	public void setDirectFlg(String directFlg) {
		this.directFlg = directFlg;
	}

	public String getParentPageName() {
		return parentPageName;
	}

	public void setParentPageName(String parentPageName) {
		this.parentPageName = parentPageName;
	}

	public List<Pages> getChildPages() {
		return childPages;
	}

	public void setChildPages(List<Pages> childPages) {
		this.childPages = childPages;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((childPages == null) ? 0 : childPages.hashCode());
		result = prime * result + ((chooseFlag == null) ? 0 : chooseFlag.hashCode());
		result = prime * result + ((directFlg == null) ? 0 : directFlg.hashCode());
		result = prime * result + ((flag == null) ? 0 : flag.hashCode());
		result = prime * result + ((icon == null) ? 0 : icon.hashCode());
		result = prime * result + ((pageDesc == null) ? 0 : pageDesc.hashCode());
		result = prime * result + ((pageId == null) ? 0 : pageId.hashCode());
		result = prime * result + ((pageName == null) ? 0 : pageName.hashCode());
		result = prime * result + ((pageOrders == null) ? 0 : pageOrders.hashCode());
		result = prime * result + ((pageRank == null) ? 0 : pageRank.hashCode());
		result = prime * result + ((pageUrl == null) ? 0 : pageUrl.hashCode());
		result = prime * result + ((parentPageId == null) ? 0 : parentPageId.hashCode());
		result = prime * result + ((parentPageName == null) ? 0 : parentPageName.hashCode());
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
		Pages other = (Pages) obj;
		if (childPages == null) {
			if (other.childPages != null)
				return false;
		} else if (!childPages.equals(other.childPages))
			return false;
		if (chooseFlag == null) {
			if (other.chooseFlag != null)
				return false;
		} else if (!chooseFlag.equals(other.chooseFlag))
			return false;
		if (directFlg == null) {
			if (other.directFlg != null)
				return false;
		} else if (!directFlg.equals(other.directFlg))
			return false;
		if (flag == null) {
			if (other.flag != null)
				return false;
		} else if (!flag.equals(other.flag))
			return false;
		if (icon == null) {
			if (other.icon != null)
				return false;
		} else if (!icon.equals(other.icon))
			return false;
		if (pageDesc == null) {
			if (other.pageDesc != null)
				return false;
		} else if (!pageDesc.equals(other.pageDesc))
			return false;
		if (pageId == null) {
			if (other.pageId != null)
				return false;
		} else if (!pageId.equals(other.pageId))
			return false;
		if (pageName == null) {
			if (other.pageName != null)
				return false;
		} else if (!pageName.equals(other.pageName))
			return false;
		if (pageOrders == null) {
			if (other.pageOrders != null)
				return false;
		} else if (!pageOrders.equals(other.pageOrders))
			return false;
		if (pageRank == null) {
			if (other.pageRank != null)
				return false;
		} else if (!pageRank.equals(other.pageRank))
			return false;
		if (pageUrl == null) {
			if (other.pageUrl != null)
				return false;
		} else if (!pageUrl.equals(other.pageUrl))
			return false;
		if (parentPageId == null) {
			if (other.parentPageId != null)
				return false;
		} else if (!parentPageId.equals(other.parentPageId))
			return false;
		if (parentPageName == null) {
			if (other.parentPageName != null)
				return false;
		} else if (!parentPageName.equals(other.parentPageName))
			return false;
		return true;
	}

	public String getChooseFlag() {
		return chooseFlag;
	}

	public void setChooseFlag(String chooseFlag) {
		this.chooseFlag = chooseFlag;
	}
}