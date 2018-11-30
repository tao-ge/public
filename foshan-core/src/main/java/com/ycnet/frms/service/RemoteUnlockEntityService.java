package com.ycnet.frms.service;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.RemoteUnlock;
import com.ycnet.frms.bean.RemoteUnlockEntity;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.RemoteUnlockEntityVo;

public interface RemoteUnlockEntityService {

	/**
	 * 
	 * @Title: queryRemoteUnlockList
	 * @Description: 装维远程开锁申请列表
	 * @param @param ru
	 * @param @param pb
	 * @param @param user
	 * @param @return 
	 * @return PageBean 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月15日 下午2:36:12
	 * @version V1.0
	 */
	PageBean queryRemoteUnlockList(RemoteUnlockEntityVo ru, PageBean pb, Users user);

	/**
	 * 
	 * @Title: validateRemoteUnlock
	 * @Description: 验证该设施是否已经申请并在其申请时间内
	 * @param @param devId
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月15日 下午4:08:36
	 * @version V1.0
	 */
	int validateRemoteUnlock(Long devId);

	/**
	 * 
	 * @Title: insertSelective
	 * @Description: 添加远程开锁申请
	 * @param @param ru
	 * @param @param user
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月15日 下午4:46:19
	 * @version V1.0
	 */
	int insertSelective(RemoteUnlockEntity ru, Users user);
	
	
	/**
	 * 删除远程开锁权限
	* @Title: remoteUnlockDelete 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param appId
	* @param @return    入参
	* @return int    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年7月12日 下午2:08:20 
	* @version V1.0
	 */
	int remoteUnlockDelete(Long appId);

}
  
    