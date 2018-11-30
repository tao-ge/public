/**   
 * @Package: com.ycnet.frms.service.impl 
 * @author: FL   
 * @date: 2018年10月25日 下午2:46:19 
 */
package com.ycnet.frms.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.frms.bean.OcIdentifPort;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.LogsMapper;
import com.ycnet.frms.mapper.OcIdentifPortMapper;
import com.ycnet.frms.service.OcIdentifPortService;

/** 
* @ClassName: OcIdentifPortServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月25日 下午2:46:19  
*/
@Service("ocIdentifPortService")
public class OcIdentifPortServiceImpl implements OcIdentifPortService{

	
	@Resource(name="ocIdentifPortMapper")
	private OcIdentifPortMapper ocIdentifPortMapper;
	
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
	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.OcIdentifPortService#bindIdentifPort(java.lang.Long, java.lang.Long, com.ycnet.frms.bean.Users)
	 */
	@Override
	public int bindIdentifPort(Long portId, Long cableWellId, Users user) {
		OcIdentifPort record = new OcIdentifPort();
		record.setLastModifyTime(new Date());
		record.setLastModifyUser(user.getUserId());
		record.setPortId(portId);
		record.setCableWellId(cableWellId);
		return ocIdentifPortMapper.updateByPrimaryKeySelective(record);
	}

}
