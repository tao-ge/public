/**   
 * @Package: com.ycnet.frms.vo.mobile 
 * @author: FL   
 * @date: 2018年10月10日 下午3:48:26 
 */
package com.ycnet.frms.vo.mobile;

import java.util.List;

import com.ycnet.frms.bean.OcPipingSection;
import com.ycnet.frms.vo.PipingBean;

/** 
* @ClassName: OcPipingSectionBean 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月10日 下午3:48:26  
*/
public class OcPipingSectionBean extends  OcPipingSection{
	
	private Long awellId;//A端井ID
	
	private String aWellName;//A端井设施名称
	
	private String zWellName;//Z端井设施名称
	
	private Long zwellId;//Z端井ID
	
	private String aSurface;//A端面
	
	private String zSurface;//Z端面
	
	private Long aspaceId;//A端面ID
	
	private Long zspaceId;//Z端面ID
	
	private List<PipingBean> pipingBeanList;//管孔集合

}
