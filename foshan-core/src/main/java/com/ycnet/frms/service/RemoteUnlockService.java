package com.ycnet.frms.service;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.RemoteUnlock;
import com.ycnet.frms.bean.Users;

public interface RemoteUnlockService {

	/**
	 * 
	 * @Title: queryRemoteUnlockApplyList
	 * @Description: 远程开锁授权列表
	 * @param @param ru
	 * @param @param pb
	 * @param @param user
	 * @param @return 
	 * @return PageBean 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年2月5日 下午1:54:57
	 * @version V1.0
	 */
	PageBean queryRemoteUnlockApplyList(RemoteUnlock ru, PageBean pb, Users user);

	/**
	 * 
	 * @Title: save
	 * @Description: 添加远程开锁申请
	 * @param @param ru
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年2月6日 下午2:19:28
	 * @version V1.0
	 * @param user 
	 */
	int save(RemoteUnlock ru, Users user);

	/**
	 * 
	 * @Title: validateApply
	 * @Description: 验证该设施是否已经申请并在其申请时间内
	 * @param @param devId
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年3月19日 上午11:42:41
	 * @version V1.0
	 */
	int validateApply(Long devId);

}
  
    