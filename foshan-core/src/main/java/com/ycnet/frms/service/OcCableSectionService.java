/**   
 * @Package: com.ycnet.frms.service 
 * @author: FL   
 * @date: 2018年10月11日 下午3:52:02 
 */
package com.ycnet.frms.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ycnet.frms.bean.OcCableSection;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.mobile.ocii.OcCableSectionImgs;
import com.ycnet.frms.vo.mobile.ocii.OcSectBean;

/** 
* @ClassName: OcCableSectionService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月11日 下午3:52:02  
*/
public interface OcCableSectionService {

	/** 
	 * 查询光交箱下的关联光缆段
	* @Title: queryOciiFacilityByPdevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return List<OcSectBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月11日 下午3:53:50 
	* @version V1.0   
	 * @param orgId 
	*/
	List<OcSectBean> queryOciiFacilityByPdevId(Long devId, Long orgId);

	/** 
	 * 删除物理光缆段
	* @Title: delOciiCableSection 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param sectId
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月12日 下午4:00:39 
	* @version V1.0   
	*/
	int delOciiCableSection(Long sectId);

	/** 
	 * 添加修改物理光缆段
	* @Title: saveOciiCableSection 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param ocCableSection
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月15日 下午1:42:30 
	* @version V1.0   
	 * @param user 
	 * @param imgList 
	*/
	int saveOciiCableSection(OcCableSectionImgs ocCableSection, Users user, List<String> imgList);

	/** 
	 * 保存物理光缆图片
	* @Title: saveOciiFacilityImages 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param user
	* @param @return    入参
	* @return List<String>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月15日 下午3:19:54 
	* @version V1.0   
	*/
	List<String> saveOciiCableImages(HttpServletRequest request, Users user);

	/** 
	 * 根据设施/井ID,查询光缆详情(识别详情)
	* @Title: queryOciiSectByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @param type
	* @param @param user
	* @param @return    入参
	* @return Map<String,Object>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月16日 上午9:55:37 
	* @version V1.0   
	*/
	Map<String, Object> queryOciiSectByDevId(Long devId, String type, Users user);
}
