/**   
 * @Package: com.ycnet.frms.service 
 * @author: FL   
 * @date: 2018年10月12日 下午3:08:03 
 */
package com.ycnet.frms.service;

import java.util.List;
import java.util.Map;

/** 
* @ClassName: OcCableWellService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月12日 下午3:08:03  
*/
public interface OcCableWellService {

	/** 
	 * 根据ID查询物理光缆段信息
	* @Title: queryOcciCableWell 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param sectId
	* @param @return    入参
	* @return List<Map<String,Object>>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月12日 下午3:15:22 
	* @version V1.0   
	*/
	List<Map<String, Object>> queryOcciCableWell(Long sectId);
	
}
