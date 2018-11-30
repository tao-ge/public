package com.ycnet.mobile.util;

import java.util.ArrayList;
import java.util.List;

public class Result {


	private Long r;
	
	private String r_content;
	
	private Object dt;
	
	private List<?> dtList;
	private Result()
	{
		this.r = 0L;
		this.r_content="初始化未知错误";
		dtList = new ArrayList<Object>();
	}

	public Long getR() {
		return r;
	}

	
	public void setR(long r){
		this.r = r;
	}

	public String getR_content() {
		return r_content;
	}

	public void setR_content(String r_content) {
		this.r_content = r_content;
	}

	public Object getDt() {
		return dt;
	}

	public void setDt(Object dt) {
		this.dt = dt;
	}

	public List<?> getDtList() {
		return dtList;
	}

	public void setDtList(List<?> dtList) {
		this.dtList = dtList;
	}
	
	public static Result get()
	{
		return new Result();
	}
	
	public Result putDT(Object o)
	{
		this.setDt(o);
		if(o!=null)
		{
			this.setR(1);
			this.setR_content("");
		}
		return this;
	}
	
	public Result putDtList(List<?> dtList)
	{
		this.setDtList(dtList);
		if(dtList !=null)
		{
			this.setR(dtList.size());
			this.setR_content("");
		}
		return this;
	}
	
	public Result putR(int r)
	{
		this.setR(r);
		return this;
	}
	
	public Result putR(long r)
	{
		this.setR(r);
		return this;
	}
	
	public Result putRContent(String r_content)
	{
		this.setR_content(r_content);
		return this;
	}
	
	public Result initSaveMessage()
	{
		if(this.r >=1)
			this.setR_content("保存成功！");
		else
			this.setR_content("保存失败！");
		return this;
	}
	
	public Result initQueryMessage()
	{
		if(this.r<=0)
			this.setR_content("未查询到数据！");
		else
			this.setR_content("获取到"+this.r+"条数据");
		return this;
	}
}
