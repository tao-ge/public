package com.ycnet.frms.vo;

import java.util.List;

import com.ycnet.frms.bean.Lines;

public  class PortWX
{
	private Integer col;
	
	private String code;
	
	private List<Lines> lines;
	//占用情况
	private String occupy = "0";
	
	//蓝牙占用情况
	private String bluetooethOccupy;//占用情况 0 否 1 是
	
	private String remark;
	
	private String isCd;
	//硬件检测的占用情况与系统普查数据对比   0 不一致  1 一致
	private String hardOccupy = "1";
	
	
	public String getHardOccupy() {
		return hardOccupy;
	}

	public void setHardOccupy(String hardOccupy) {
		this.hardOccupy = hardOccupy;
	}

	public String getOccupy() {
		return occupy;
	}

	public void setOccupy(String occupy) {
		this.occupy = occupy;
	}

	public List<Lines> getLines() {
		return lines;
	}

	public String getIsCd() {
		return isCd;
	}

	public void setIsCd(String isCd) {
		this.isCd = isCd;
	}

	public Integer getCol() {
		return col;
	}

	public void setCol(Integer col) {
		this.col = col;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

//	// 0 未使用 	 1 成端 或 跳纤      2 占用
//	public String getStatus()
//	{
//		if(lines==null||lines.size()==0) {
//			return "0"; //未使用
//		}else if(lines.size()==1) {
//
//			return "1"; //成端
//			
//		}else {
//
//			return "2";//占用
//		}
//	}
	
	public String getStatus()
	{
		if(lines==null||lines.size()==0) {
			return "0"; //未使用
		}else if(lines.size()==1) {
			if("02".equals(lines.get(0).getLineType()) && (lines.get(0).getAcode()==null || lines.get(0).getZcode()==null)) {
				return "3"; //空闲 -尾纤悬空
			}
			if("02".equals(lines.get(0).getLineType())) {
				return "5"; //跳纤
			}else {
				return "1"; //成端
			}
		}else {
			int count = 0;
			for(Lines lines1 : lines) {
				if("02".equals(lines1.getLineType())) {
					++count;
				}
			}
			if(count > 1) {
				return "6";
			}
			for(Lines lines2 : lines) {
				System.out.println(lines2.getLineType()+"=="+lines2.getAcode()+"=="+lines2.getZcode());
				if("02".equals(lines2.getLineType()) && (lines2.getAcode()==null || lines2.getZcode()==null)) {
					return "4"; //占用 -尾纤悬空
				}
			}
			return "2";//占用
		}
	}
	

	public void setLines(List<Lines> lines) {
		this.lines = lines;
	}

	public String getRemark() {
		remark = "";
		if(lines==null) return remark;
		for(Lines l:lines)
		{
			String srvName=l.getSrvName();
			remark +=srvName==null?"":srvName;
		}
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBluetooethOccupy() {
		return bluetooethOccupy;
	}

	public void setBluetooethOccupy(String bluetooethOccupy) {
		this.bluetooethOccupy = bluetooethOccupy;
	}
	
}