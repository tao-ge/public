package com.ycnet.frms.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ycnet.core.LineType;


public class PortJumper {

	private  Long devId;
	
	private String devName;
	
	private String routeText;

	private String code;
	
	private String srvName;
	
	private String zgSectDec;
	
	private String zgRouteName;
	
	private String zgRouteText;
	
    private String isOccup;//占用情况 0未占用 1已占用
    
    private String isSheath;//是否铠装尾纤 0否 1是 2未核查
    
    private String isGlazed1;//是否有光1 0否 1是 2未核查
    
    private String isGlazed2;//是否有光1 0否 1是 2未核查

	private List<Jumper> jumpers = new ArrayList<Jumper>();
	
	private List<String> imgPaths; //图片路径
	
	private List<String> lineImgPaths;
	/*zhouyu 1/4 该类型*/
	private String lightLen;//光路长度
	/*zhouyu 1/4 该类型*/
    private String lightWane;//光衰
    
    private String remark;//备注
    
    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}



	private String unknownPointName;
    
    
	
	public String getUnknownPointName() {
		return unknownPointName;
	}

	public void setUnknownPointName(String unknownPointName) {
		this.unknownPointName = unknownPointName;
	}

	public List<String> getImgPaths() {
		return imgPaths;
	}

	public void setImgPaths(List<String> imgPaths) {
		this.imgPaths = imgPaths;
	}

	public String getIsOccup() {
		return isOccup;
	}

	public void setIsOccup(String isOccup) {
		this.isOccup = isOccup;
	}

	public String getIsSheath() {
		return isSheath;
	}

	public void setIsSheath(String isSheath) {
		this.isSheath = isSheath;
	}

	public String getIsGlazed1() {
		return isGlazed1;
	}

	public void setIsGlazed1(String isGlazed1) {
		this.isGlazed1 = isGlazed1;
	}

	public String getIsGlazed2() {
		return isGlazed2;
	}

	public void setIsGlazed2(String isGlazed2) {
		this.isGlazed2 = isGlazed2;
	}

	public String getZgSectDec() {
		return zgSectDec;
	}

	public void setZgSectDec(String zgSectDec) {
		this.zgSectDec = zgSectDec;
	}

	public String getZgRouteName() {
		return zgRouteName;
	}

	public void setZgRouteName(String zgRouteName) {
		this.zgRouteName = zgRouteName;
	}

	public String getZgRouteText() {
		return zgRouteText;
	}

	public void setZgRouteText(String zgRouteText) {
		this.zgRouteText = zgRouteText;
	}
	
	public String getRouteText() {
		return routeText;
	}

	public void setRouteText(String routeText) {
		this.routeText = routeText;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}
	
	public List<Jumper> getJumpers() {
		return jumpers;
	}

	public void setJumpers(List<Jumper> jumpers) {
		this.jumpers = jumpers;
	}

	public Long getDevId() {
		return devId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	
	public String getSrvName() {
		return srvName;
	}

	public void setSrvName(String srvName) {
		this.srvName = srvName;
	}

	

	public String getLightLen() {
		return lightLen;
	}

	public void setLightLen(String lightLen) {
		this.lightLen = lightLen;
	}

	public String getLightWane() {
		return lightWane;
	}

	public void setLightWane(String lightWane) {
		this.lightWane = lightWane;
	}

	public List<String> getLineImgPaths() {
		return lineImgPaths;
	}

	public void setLineImgPaths(List<String> lineImgPaths) {
		this.lineImgPaths = lineImgPaths;
	}



	public static class Jumper  implements Comparable<Jumper>
	{
		private Long lineId;
		
		private Long devId;
		
		private String devName;
		
		private String unknownPointName;
		
		public String getDevName() {
			return devName;
		}

		public void setDevName(String devName) {
			this.devName = devName;
		}

		public String getSectName() {
			return sectName;
		}

		public void setSectName(String sectName) {
			this.sectName = sectName;
		}

		private Long sectId;
		
		private String sectName;
		
		private Integer fiberNo;
		
		private String code;
		
		private String side;
		
		private Integer row;
		
		private Integer col;
		
		private String type;

		public Long getLineId() {
			return lineId;
		}

		public void setLineId(Long lineId) {
			this.lineId = lineId;
		}


		public Long getDevId() {
			return devId;
		}

		public void setDevId(Long devId) {
			this.devId = devId;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getSide() {
			return side;
		}

		public void setSide(String side) {
			this.side = side;
		}

		public Integer getRow() {
			return row;
		}

		public void setRow(Integer row) {
			this.row = row;
		}

		public Integer getCol() {
			return col;
		}

		public void setCol(Integer col) {
			this.col = col;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		@Override
		public int compareTo(Jumper o) {
			if(o!=null)
				if(LineType.toType(this.getType())==LineType.FIBER)
					return -1;
			return 0;
		}

		public Long getSectId() {
			return sectId;
		}

		public void setSectId(Long sectId) {
			this.sectId = sectId;
		}

		public Integer getFiberNo() {
			return fiberNo;
		}

		public void setFiberNo(Integer fiberNo) {
			this.fiberNo = fiberNo;
		}

		public String getUnknownPointName() {
			return unknownPointName;
		}

		public void setUnknownPointName(String unknownPointName) {
			this.unknownPointName = unknownPointName;
		}
		
		
	}
	
}
