package com.ycnet.frms.service;

import com.ycnet.frms.bean.OcPipingSection;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.mobile.ocii.PipSectionBean;
import com.ycnet.frms.vo.mobile.ocii.OcPipingSectionResult;

/**
 * 管道段
* @ClassName: OcPipingSectionService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author YHT(作者)  
* @date 2018年10月19日 下午1:25:19 
* @version V1.0
 */
public interface OcPipingSectionService {

	
	int saveOcPiping(OcPipingSection ocPipingSection, Users user);

	
	/**
	 * 根据管道段ID删除管道段
	* @Title: delOcPipSectionById 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param pipingSectId
	* @param @return    入参
	* @return int    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年10月19日 下午1:33:45 
	* @version V1.0
	 */
	int delOcPipSectionById(Long pipingSectId);


	/** 
	 * 新建管道段
	* @Title: addOciiPipSection 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param pipSectionBean
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月19日 下午4:38:42 
	* @version V1.0   
	 * @param user 
	*/
	int addOciiPipSection(PipSectionBean pipSectionBean, Users user);


	/**
	 * 根据管道段ID查询管道段信息
	* @Title: queryOcPipSectionById 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param pipingSectId
	* @param @return    入参
	* @return OcPipingSectionResult    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年10月22日 上午9:31:16 
	* @version V1.0
	 */
	OcPipingSectionResult queryOcPipSectionById(Long pipingSectId);


	/** 
	 * 修改管道段
	* @Title: updateOciiPipSection 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param pipSectionBean
	* @param @param user
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月23日 上午10:25:25 
	* @version V1.0   
	*/
	int updateOciiPipSection(PipSectionBean pipSectionBean, Users user);

}
