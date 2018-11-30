package com.ycnet.core.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * 分页辅助类
 * 
 * @author 
 * @since
 * @version 1.0 这里加了一个注释
 */
@Component("pageBean")
public class PageBean {

    /**
     * 重写无参构造函数：获取设置的默认分页行数
     * 
     * 
     */
    public PageBean() {
    }

    // 当前页列表数据
    private List<?> list = new ArrayList<Object>();
    // 符合条件的记录总数
    private int rows;
    // 总页数
    private int totalPages;
    // 每页显示多少条
    private int pageSize = FIFTEEN;
    // 当前页码
    private int pageNo = 1;
    // 上一页码
    private int prePageNo;
    // 下一页码
    private int nextPageNo;
    // 第一页码
    private int firstPageNo = 1;
    // 最后一页码
    private int lastPageNo;
    // 开始页码
    private int startNo = 1;
    // 结束页码
    private int endNo;
    // 分页开始的条数
    private int startRowNum;
    // 分页结束的条数
    private int endRowNum;
    private Object objectBean;

    private String url;

    public Object getObjectBean() {
        return objectBean;
    }

    public void setObjectBean(Object objectBean) {
        this.objectBean = objectBean;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setPrePageNo(int prePageNo) {
        this.prePageNo = prePageNo;
    }

    public void setNextPageNo(int nextPageNo) {
        this.nextPageNo = nextPageNo;
    }

    /**
     * 计算总页数
     * 
     * @return 计算出的最大页数 {@link java.lang.Integer}
     */
    public int getTotalPages() {
        totalPages = getRows() % pageSize == 0 ? rows / pageSize : rows / pageSize + 1;
        return totalPages;
    }

    /**
     * 计算上一页
     * 
     * @return 计算出的上一页 如果是第一页就返回第一页 {@link java.lang.Integer}
     */
    public int getPrePageNo() {
        this.prePageNo = this.pageNo - 1;
        if (this.prePageNo < this.getFirstPageNo()) {
            this.prePageNo = this.getFirstPageNo();
        }
        return prePageNo;
    }

    /**
     * 计算下一页
     * 
     * @return 如果是总页数就返回总页数 否则页数+1 {@link java.lang.Integer}
     */
    public int getNextPageNo() {
        this.nextPageNo = this.pageNo + 1;
        if (this.nextPageNo > this.getLastPageNo()) {
            this.nextPageNo = this.getLastPageNo();
        }
        return nextPageNo;
    }

    public int getFirstPageNo() {
        return firstPageNo;
    }

    public void setFirstPageNo(int firstPageNo) {
        this.firstPageNo = firstPageNo;
    }

    /**
     * 获取最后一页
     * 
     * @return {@link java.lang.Integer}
     */
    public int getLastPageNo() {
        this.lastPageNo = this.getTotalPages();
        return lastPageNo;
    }

    public void setLastPageNo(int lastPageNo) {
        this.lastPageNo = lastPageNo;
    }

    /**
     * 获取显示的开始页码数与 @see {@link com.ningpai.util.PageBean#getEnd()} 相对存在
     * 
     * @return 页码显示10个 {@link java.lang.Integer}
     */
    public int getStart() {
        if (this.pageNo / TEN < 1) {
            return 1;
        } else {
            if (pageNo == totalPages) {
                return pageNo - NINE;
            } else {
                if (this.getTotalPages() - this.pageNo < TEN) {
                    return this.getTotalPages() - NINE;
                } else {
                    return pageNo - FOUR;
                }
            }

        }
    }

    /**
     * 获取显示的结束页码数 {@link com.ningpai.util.PageBean#getStart()}
     * 
     * @return 结束的页码数
     */
    public int getEnd() {
        if (this.pageNo / TEN < 1) {
            if (this.getTotalPages() > TEN) {
                return TEN;
            } else {
                return this.getTotalPages();
            }
        } else {
            if (pageNo == this.getTotalPages()) {
                return this.getTotalPages();
            } else {
                if (this.getTotalPages() - this.pageNo < TEN) {
                    return this.getTotalPages();
                } else {
                    return pageNo + FIVE;
                }
            }
        }
    }

    /**
     * 去掉变量为引用的标记
     */
    public void deltip() {
        endNo = endNo + 1 - 1;
        startRowNum = startRowNum + 1 - 1;
        endRowNum = endRowNum + 1 - 1;
        startNo = startNo + 1 - 1;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getStartRowNum() {
        return (pageNo - 1) * pageSize;
    }

    public void setStartRowNum(int startRowNum) {
        this.startRowNum = startRowNum;
    }

    public int getEndRowNum() {
        return pageSize;
    }

    public void setEndRowNum(int endRowNum) {
        this.endRowNum = endRowNum;
    }

    public int getStartNo() {
        return getStart();
    }

    public void setStartNo(int startNo) {
        this.startNo = startNo;
    }

    public int getEndNo() {
        return getEnd();
    }

    public void setEndNo(int endNo) {
        this.endNo = endNo;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows == null ? 0 : rows;
    }

    // 数字 15
    private static final int FIFTEEN = 15;
    // 数字 10
    private static final int TEN = 10;
    // 数字 9
    private static final int NINE = 9;
    // 数字 5
    private static final int FIVE = 5;
    // 数字 4
    private static final int FOUR = 4;
}

