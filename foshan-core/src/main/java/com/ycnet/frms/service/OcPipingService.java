/**   
 * @Package: com.ycnet.frms.service 
 * @author: FL   
 * @date: 2018年10月10日 下午1:45:50 
 */
package com.ycnet.frms.service;

import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.OcPiping;
import com.ycnet.frms.bean.Users;

/** 
* @ClassName: OcPipingService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月10日 下午1:45:50  
*/
public interface OcPipingService {

	/** 
	 * 大孔修改保存
	* @Title: saveOcPiping 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param ocPiping
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月10日 下午1:47:18 
	* @version V1.0   
	 * @param user 
	*/
	int saveOcPiping(OcPiping ocPiping, Users user);

	/** 
	 * 根据主键删除大孔
	* @Title: delOcPiping 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param pipingId
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月10日 下午2:10:06 
	* @version V1.0   
	*/
	int delOcPiping(Long pipingId);

	/** 
	* @Title: queryOcPipingById 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param pipingId
	* @param @return    入参
	* @return OcPiping    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月10日 下午2:21:43 
	* @version V1.0   
	*/
	Map<String, Object> queryOcPipingById(Long pipingId);

	/** 
	 * 查询光交箱下的管道段
	* @Title: queryOciiPipingSections 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @param orgId
	* @param @return    入参
	* @return List<Map<String,Object>>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月11日 下午4:37:47 
	* @version V1.0   
	*/
	List<Map<String, Object>> queryOciiPipingSections(Long devId, Long orgId);

}
