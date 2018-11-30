/**   
 * @Package: com.ycnet.frms.service.impl 
 * @author: FL   
 * @date: 2018年10月12日 下午3:09:51 
 */
package com.ycnet.frms.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.frms.mapper.OcCableSectionMapper;
import com.ycnet.frms.mapper.OcCableWellMapper;
import com.ycnet.frms.service.OcCableWellService;

/** 
* @ClassName: OcCableWellServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月12日 下午3:09:51  
*/
@Service("ocCableWellService")
@Transactional
public class OcCableWellServiceImpl implements OcCableWellService{
	
	
	@Resource(name ="ocCableWellMapper")
	private OcCableWellMapper ocCableWellMapper;

	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.OcCableWellService#queryOcciCableWell(java.lang.Long)
	 */
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
	@Override
	public List<Map<String, Object>> queryOcciCableWell(Long sectId) {
		return ocCableWellMapper.queryOcciCableWell(sectId);
	}

}
