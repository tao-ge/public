package com.ycnet.frms.service;

import com.ycnet.frms.bean.ActIdUser;

public interface ActIdUserService {

	/**
	 * 
	 * @Title: selectByPrimaryKey
	 * @Description: 根据ID查询一条
	 * @param @param userCode
	 * @param @return 
	 * @return ActIdUser 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年5月15日 上午10:00:21
	 * @version V1.0
	 */
	ActIdUser selectByPrimaryKey(String userCode);

	/**
	 * 
	 * @Title: insertSelective
	 * @Description: 添加流程用户
	 * @param @param actUser
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年5月15日 上午10:07:14
	 * @version V1.0
	 */
	int insertSelective(ActIdUser actUser);

	/**
	 * 
	 * @Title: deleteByPrimaryKey
	 * @Description: 删除流程用户
	 * @param @param userCode
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年5月15日 上午11:25:02
	 * @version V1.0
	 */
	int deleteByPrimaryKey(String userCode);

}
  
    