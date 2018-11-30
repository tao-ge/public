/**   
 * @Package: com.ycnet.frms.service 
 * @author: FL   
 * @date: 2018年10月10日 下午1:00:17 
 */
package com.ycnet.frms.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ycnet.frms.bean.OcWell;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.mobile.WellRelationPipsBean;
import com.ycnet.frms.vo.mobile.WellSpacesBean;
import com.ycnet.frms.vo.mobile.ocii.IndexInBean;
import com.ycnet.frms.vo.mobile.ocii.OcSectBean;
import com.ycnet.frms.vo.mobile.ocii.OcWellBean;
import com.ycnet.frms.vo.mobile.ocii.OcWellImgs;

/** 
* @ClassName: OcWellService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月10日 下午1:00:17  
*/
public interface OcWellService {

	/** 
	 * 光路识别井设施保存
	* @Title: saveOcciWellFacility 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param well
	* @param @param users
	* @param @param imgList
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月10日 下午1:01:01 
	* @version V1.0   
	*/
	int saveOcciWellFacility(OcWellImgs well, Users users, List<String> imgList);

	/** 
	 * 查询井基本信息
	* @Title: queryWellByWellId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wellId
	* @param @param users
	* @param @return    入参
	* @return OcWellBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月10日 下午1:32:48 
	* @version V1.0   
	*/
	OcWellBean queryOcciWellByWellId(Long wellId, Users users);

	/** 
	 * 根据路径和Id删除数据,和文件
	* @Title: deleteOciiImage 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param imageUrlList
	* @param @param wellId
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月10日 下午2:33:35 
	* @version V1.0   
	 * @param imgType 
	*/
	int deleteOciiImage(HttpServletRequest request, List<String> imageUrlList, Long imgId, String imgType);

	/** 
	 * 保存文件返回路径
	* @Title: saveOciiFacilityImages 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param user
	* @param @return    入参
	* @return List<String>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月10日 下午2:45:17 
	* @version V1.0   
	*/
	List<String> saveOciiFacilityImages(HttpServletRequest request, Users user);

	/** 
	 * 根据主键删除井
	* @Title: delOcWellById 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wellId
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月11日 上午8:46:02 
	* @version V1.0   
	 * @param user 
	*/
	int delOcWellById(Long wellId, Users user);

	/** 
	 * 查询井关联的管道段
	* @Title: queryWellRelationPips 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wellId
	* @param @param isWell
	* @param @param orgId
	* @param @return    入参
	* @return List<WellRelationPipsBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月15日 上午9:31:22 
	* @version V1.0   
	*/
	List<WellRelationPipsBean> queryWellRelationPips(Long wellId, String isWell, Long orgId);

	/** 
	 * 井所关联的光缆段信息
	* @Title: queryWellRelationSections 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wellId
	* @param @param orgId
	* @param @return    入参
	* @return List<WellRelationPipsBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月15日 上午10:39:41 
	* @version V1.0   
	*/
	List<OcSectBean> queryWellRelationSections(Long wellId, Long orgId);

	
	/**
	 * 根据条件查询首页信息
	* @Title: queryIndexByType 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param iib
	* @param @return    入参
	* @return IndexInBean    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年10月16日 下午1:41:10 
	* @version V1.0
	 */
	IndexInBean queryIndexByType(IndexInBean iib);

	/** 
	 * 根据井ID查询井和其面信息
	* @Title: queryOciiWellAndSpaces 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wellId
	* @param @param user
	* @param @return    入参
	* @return WellSpacesBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月19日 下午3:39:49 
	* @version V1.0   
	*/
	WellSpacesBean queryOciiWellAndSpaces(Long wellId, Users user);
}
