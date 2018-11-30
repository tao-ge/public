/**   
 * @Package: com.ycnet.frms.service 
 * @author: FL   
 * @date: 2018年10月25日 下午2:41:59 
 */
package com.ycnet.frms.service;

import com.ycnet.frms.bean.Users;

/** 
* @ClassName: OcIdentifPortService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月25日 下午2:41:59  
*/
public interface OcIdentifPortService {

	/** 
	 * 探头绑定光缆点
	* @Title: bindIdentifPort 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param portId
	* @param @param cableWellId
	* @param @param user
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月25日 下午2:45:47 
	* @version V1.0   
	*/
	int bindIdentifPort(Long portId, Long cableWellId, Users user);
}
