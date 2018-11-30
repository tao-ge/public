package com.ycnet.core.util;
/**
 * 分页类
 * @author YHT
 * @time   2016年7月7日 下午3:01:00
 */

public class Page {

	private String name;
	private int page;
	private int size;
	private Integer id;
	private Integer catid;
	private String sort;
	
	
	@Override
	public String toString() {
		return "Page [name=" + name + ", page=" + page + ", size=" + size + ", id=" + id + ", catid=" + catid
				+ ", sort=" + sort + "]";
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public Integer getCatid() {
		return catid;
	}
	public void setCatid(Integer catid) {
		this.catid = catid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
}
