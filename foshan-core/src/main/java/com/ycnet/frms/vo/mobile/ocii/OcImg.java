/**   
 * @Package: com.ycnet.frms.vo.mobile.ocii 
 * @author: FL   
 * @date: 2018年10月11日 上午9:25:31 
 */
package com.ycnet.frms.vo.mobile.ocii;

import java.util.Date;

/** 
* @ClassName: OcImg 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月11日 上午9:25:31  
*/
public class OcImg {
	
    private Long wellId;

    private String imgTypes;

    private String imgDesc;

    public Long getWellId() {
		return wellId;
	}

	public void setWellId(Long wellId) {
		this.wellId = wellId;
	}

	public String getImgTypes() {
		return imgTypes;
	}

	public void setImgTypes(String imgTypes) {
		this.imgTypes = imgTypes;
	}

	public String getImgDesc() {
		return imgDesc;
	}

	public void setImgDesc(String imgDesc) {
		this.imgDesc = imgDesc;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	private String imgUrl;

	
}
