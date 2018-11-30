/**   
 * @Package: com.ycnet.frms.service 
 * @author: FL   
 * @date: 2018年10月19日 上午9:24:23 
 */
package com.ycnet.frms.service;

import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.OcIdentif;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.mobile.ocii.OcCableSectionInput;
import com.ycnet.frms.vo.mobile.ocii.OcCableSectionResult;
import com.ycnet.frms.vo.mobile.ocii.OcIdentifInput;
import com.ycnet.frms.vo.mobile.ocii.OcIdentifResult;

/** 
* @ClassName: OcIdentifService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月19日 上午9:24:23  
*/
public interface OcIdentifService {

	/** 
	 * 添加/修改光缆识别仪
	* @Title: saveOciiIdentif 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param identif
	* @param @param user
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月19日 上午9:37:08 
	* @version V1.0   
	*/
	int saveOciiIdentif(OcIdentif identif, Users user);

	/** 
	 * 删除光缆识别仪
	* @Title: delOciiIdentif 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param identId
	* @param @param user
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月19日 上午9:57:41 
	* @version V1.0   
	*/
	int delOciiIdentif(Long identId, Users user);

	/**
	 * 根据识别仪ID查询识别仪端口详情 - 自动
	* @Title: queryOcIdentifPortByIdentifId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param oi
	* @param @param user
	* @param @return    入参
	* @return OcIdentifResult    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年10月24日 上午9:25:57 
	* @version V1.0
	 */
	OcIdentifResult queryOcIdentifPortByIdentifId(OcIdentifInput oi, Users user);

	/** 
	 * 设备-根据设施ID查询光缆段列表
	* @Title: querySectByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param oi
	* @param @param user
	* @param @return    入参
	* @return OcIdentifResult    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月25日 下午1:18:02 
	* @version V1.0   
	*/
	List<Map<String, Object>> querySectByDevId(OcCableSectionInput oi, Users user);

	/**
	 * 根据识别仪ID查询识别仪端口详情  - 手动
	* @Title: queryIdentifPortBySeqId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param oi
	* @param @param user
	* @param @return    入参
	* @return OcIdentifResult    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年10月25日 下午1:35:58 
	* @version V1.0
	 */
	OcIdentifResult queryIdentifPortBySeqId(OcIdentifInput oi, Users user);
}
