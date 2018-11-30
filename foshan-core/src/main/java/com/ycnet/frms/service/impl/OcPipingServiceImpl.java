/**   
 * @Package: com.ycnet.frms.service.impl 
 * @author: FL   
 * @date: 2018年10月10日 下午1:47:44 
 */
package com.ycnet.frms.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.frms.bean.OcPiping;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.OcPipingMapper;
import com.ycnet.frms.mapper.PortImgMapper;
import com.ycnet.frms.service.OcPipingService;

/** 
* @ClassName: OcPipingServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月10日 下午1:47:44  
*/
@Service("ocPipingService")
@Transactional
public class OcPipingServiceImpl implements OcPipingService{
	
	@Resource(name ="ocPipingMapper")
	private OcPipingMapper ocPipingMapper;

	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.OcPipingService#saveOcPiping(com.ycnet.frms.bean.OcPiping)
	 */
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
	*/
	@Override
	public int saveOcPiping(OcPiping ocPiping, Users user) {
		int reg=0;
		if (ocPiping.getPipingId()==null) {//添加
			ocPiping.setCreateTime(new Date());
			ocPiping.setCreateUser(user.getUserId());
			ocPiping.setOrgId(user.getOrgId());
			ocPiping.setIsImport("0");
			ocPiping.setPipingState("2");
			reg=ocPipingMapper.insertSelective(ocPiping);
		}else {
			ocPiping.setLastModifyTime(new Date());
			ocPiping.setLastModifyUser(user.getUserId());
			reg=ocPipingMapper.updateByPrimaryKeySelective(ocPiping);
		}
		return reg;
	}

	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.OcPipingService#delOcPiping(java.lang.Long)
	 */
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
	@Override
	public int delOcPiping(Long pipingId) {
		return ocPipingMapper.deleteByPrimaryKey(pipingId);
	}

	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.OcPipingService#queryOcPipingById(java.lang.Long)
	 */
	/** 
	 * 根据主键查询大孔信息
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
	@Override
	public Map<String, Object> queryOcPipingById(Long pipingId) {
		return ocPipingMapper.queryOcPipingById(pipingId);
	}

	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.OcPipingService#queryOciiPipingSections(java.lang.Long, java.lang.Long)
	 */
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
	@Override
	public List<Map<String, Object>> queryOciiPipingSections(Long devId, Long orgId) {
		return ocPipingMapper.queryOciiPipingSections(devId,orgId);
	}

}
