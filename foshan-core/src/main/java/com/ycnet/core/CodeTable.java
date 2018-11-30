package com.ycnet.core;

public enum CodeTable {
	/*分光器类型*/
	OBDTYPES("OBD_TYPES"),
	/*设施类型*/
	DEVTYPE("DEV_TYPE"),
	/*图片类型*/
	IMGTYPE("IMG_TYPE"),
	PTNTYPE("PTN_TYPES"), //ptn型号
	OLTTYPE("OLT_TYPES"),
	SDHTYPE("SDH_TYPES"),
	BBUTYPE("BBU_TYPES"),
	PIPETYPE("PIPE_TYPE"),
	;
	
	
	private String name;
	
	public String getName() {
		return name;
	}

	private CodeTable(String name)
	{
		this.name=name;
	}
	
	public static CodeTable toType(String name)
	{
		for(CodeTable t: CodeTable.values())
		{
			if(t.name.equals(name))
				return t;
		}
		return null;
	}
}
