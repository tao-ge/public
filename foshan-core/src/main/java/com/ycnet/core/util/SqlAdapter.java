package com.ycnet.core.util;

public class SqlAdapter {

	private String sql;

	public SqlAdapter(String sql)
	{
		this.sql = sql;
	}
	
	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	
	
}
