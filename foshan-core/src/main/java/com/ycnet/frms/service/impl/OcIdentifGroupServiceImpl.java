/**   
 * @Package: com.ycnet.frms.service.impl 
 * @author: FL   
 * @date: 2018年10月18日 下午4:46:49 
 */
package com.ycnet.frms.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.core.FrmsException;
import com.ycnet.frms.bean.OcIdentif;
import com.ycnet.frms.bean.OcIdentifGroup;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.OcIdentifGroupMapper;
import com.ycnet.frms.mapper.OcIdentifMapper;
import com.ycnet.frms.service.OcIdentifGroupService;

/** 
* @ClassName: OcIdentifGroupServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月18日 下午4:46:49  
*/
@Service("ocIdentifGroupService")
public class OcIdentifGroupServiceImpl implements OcIdentifGroupService{

	@Resource(name ="ocIdentifGroupMapper")
	private OcIdentifGroupMapper ocIdentifGroupMapper;
	
	@Resource(name ="ocIdentifMapper")
	private OcIdentifMapper ocIdentifMapper;
	
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
	*/
	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.OcIdentifGroupService#saveIdentifGroup(com.ycnet.frms.bean.OcIdentifGroup)
	 */
	@Override
	public int saveIdentifGroup(OcIdentifGroup identifGroup, Users user) {
		int reg = 0;
		if (identifGroup.getGroupId() == null) {// 添加
			identifGroup.setCreateTime(new Date());
			identifGroup.setCreateUser(user.getUserId());
			identifGroup.setOrgId(user.getOrgId());
			identifGroup.setGroupCode(UUID.randomUUID().toString());
			reg = ocIdentifGroupMapper.insertSelective(identifGroup);
		} else {// 修改
			identifGroup.setLastModifyTime(new Date());
			identifGroup.setLastModifyUser(user.getUserId());
			reg = ocIdentifGroupMapper.updateByPrimaryKeySelective(identifGroup);
		}
		return reg;
	}

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
	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.OcIdentifGroupService#delIdentifGroup(java.lang.Long, com.ycnet.frms.bean.Users)
	 */
	@Override
	public int delIdentifGroup(Long groupId, Users user) {
		//分组下有识别仪不能删除
		List<OcIdentif>list=ocIdentifMapper.queryByGroupId(groupId);
		if (list!=null && list.size()>0) {
			throw new FrmsException("("+groupId+")该分组下有识别仪不能删除!");
		}
		return ocIdentifGroupMapper.deleteByPrimaryKey(groupId);
	}

	
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
	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.OcIdentifGroupService#queryIdentifGroup(com.ycnet.frms.bean.OcIdentifGroup, com.ycnet.frms.bean.Users)
	 */
	@Override
	public List<OcIdentifGroup> queryIdentifGroup(OcIdentifGroup ocIdentifGroup, Users user) {
		return ocIdentifGroupMapper.queryIdentifGroup(ocIdentifGroup,user);
	}

	
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
	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.OcIdentifGroupService#queryIdentifListByGroupId(java.lang.Long, com.ycnet.frms.bean.Users)
	 */
	@Override
	public List<OcIdentif> queryByGroupId(Long groupId, Users user) {
		return ocIdentifMapper.queryByGroupId(groupId);
	}

}
