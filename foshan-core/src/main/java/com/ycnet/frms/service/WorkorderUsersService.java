package com.ycnet.frms.service;

import com.ycnet.frms.bean.Users;
import com.ycnet.frms.bean.WorkorderUsers;

public interface WorkorderUsersService {

	/**
	 * 
	 * @Title: selectByUserCode
	 * @Description: 验证userCode是否重复
	 * @param @param userCode
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月4日 下午4:48:08
	 * @version V1.0
	 */
	int selectByUserCode(String userCode);

	/**
	 * 
	 * @Title: updateByPrimaryKeySelective
	 * @Description: 修改光调用户
	 * @param @param workOrderUser
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月8日 上午9:33:09
	 * @version V1.0
	 */
	int updateByPrimaryKeySelective(WorkorderUsers workOrderUser);

	/**
	 * 
	 * @Title: insertSelective
	 * @Description: 添加光调用户
	 * @param @param wUser
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月9日 下午4:44:46
	 * @version V1.0
	 */
	int insertSelective(WorkorderUsers wUser);

	/**
	 * 
	 * @Title: getUserId
	 * @Description: 获取最新userId
	 * @param @return 
	 * @return Long 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月9日 下午6:01:37
	 * @version V1.0
	 */
	Long getUserId();

	/**
	 * 
	 * @Title: queryByUserCode
	 * @Description: 根据userCode查询光调用户
	 * @param @param userCode
	 * @param @return 
	 * @return WorkorderUsers 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月10日 下午1:27:11
	 * @version V1.0
	 */
	WorkorderUsers queryByUserCode(String userCode);

}
  
    