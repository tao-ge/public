/**   
 * @Package: com.ycnet.frms.service 
 * @author: FL   
 * @date: 2018年10月18日 上午11:28:07 
 */
package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.frms.bean.OcIdentif;
import com.ycnet.frms.bean.OcIdentifGroup;
import com.ycnet.frms.bean.Users;

/** 
* @ClassName: OcIdentifGroupService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月18日 上午11:28:07  
*/
public interface OcIdentifGroupService {

	/** 
	 * 添加/修改识别仪分组
	* @Title: saveIdentifGroup 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param identifGroup
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月18日 上午11:29:43 
	* @version V1.0   
	 * @param user 
	*/
	int saveIdentifGroup(OcIdentifGroup identifGroup, Users user);

	/** 
	 * 删除识别仪分组
	* @Title: delIdentifGroup 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param groupId
	* @param @param user
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月19日 上午8:43:53 
	* @version V1.0   
	*/
	int delIdentifGroup(Long groupId, Users user);

	/** 
	 * 查询识别仪分组列表
	* @Title: queryIdentifGroup 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param ocIdentifGroup
	* @param @param user
	* @param @return    入参
	* @return List<OcIdentifGroup>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月19日 上午10:09:38 
	* @version V1.0   
	*/
	List<OcIdentifGroup> queryIdentifGroup(OcIdentifGroup ocIdentifGroup, Users user);

	/** 
	 *  根据分组ID,查询识别仪列表
	* @Title: queryIdentifListByGroupId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param groupId
	* @param @param user
	* @param @return    入参
	* @return List<OcIdentif>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月19日 上午10:28:04 
	* @version V1.0   
	*/
	List<OcIdentif> queryByGroupId(Long groupId, Users user);


}
