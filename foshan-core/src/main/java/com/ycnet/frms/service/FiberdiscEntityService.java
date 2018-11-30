package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.frms.bean.FiberdiscEntity;


public interface FiberdiscEntityService {

	/**
	 * 
	 * @Title: selectFiberdisc
	 * @Description: 根据条件查询端子
	 * @param @param param
	 * @param @return 
	 * @return List<FiberdiscEntity> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 上午9:57:21
	 * @version V1.0
	 */
	List<FiberdiscEntity> selectFiberdisc(FiberdiscEntity param);

}
  
    