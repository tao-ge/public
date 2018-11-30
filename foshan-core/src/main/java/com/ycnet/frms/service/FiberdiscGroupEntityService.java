package com.ycnet.frms.service;

import com.ycnet.frms.bean.FiberdiscGroupEntity;

public interface FiberdiscGroupEntityService {

	/**
	 * 
	 * @Title: addGroup
	 * @Description: 添加分组
	 * @param @param group
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 上午10:37:54
	 * @version V1.0
	 */
	int addGroup(FiberdiscGroupEntity group);

	/**
	 * 
	 * @Title: insert
	 * @Description: 保存分组
	 * @param @param group
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 上午10:45:53
	 * @version V1.0
	 */
	int insert(FiberdiscGroupEntity group);

}
  
    