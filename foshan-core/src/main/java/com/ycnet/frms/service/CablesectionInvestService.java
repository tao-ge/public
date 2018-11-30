package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.frms.bean.CablesectionInvest;
import com.ycnet.frms.bean.Users;

public interface CablesectionInvestService {
	/**
	 * 
	 * @Title: save
	 * @Description: 光缆直熔信息保存(添加和修改)
	 * @param @param cablesectionInvestBean
	 * @param @param user
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月16日 上午11:49:42
	 * @version V1.0
	 */
	int save(CablesectionInvest cablesectionInvest, Users user);
	

	/**
	 * 查询所属直熔盘列表
	* 
	* @Title: CablesectionInvestService.java 
	* @Description: TODO 
	* @param @param devId
	* @param @return
	* @return List<CablesectionInvest>
	* @author fl
	* @date 2017年12月16日 上午11:19:04
	* @version V1.0
	 */
	List<CablesectionInvest> queryInvestByInversId(Long investId);
	
}
    