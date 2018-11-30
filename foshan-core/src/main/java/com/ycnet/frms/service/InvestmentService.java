package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.frms.bean.Investment;

import com.ycnet.frms.bean.Investment;
import com.ycnet.frms.bean.Users;

public interface InvestmentService {

	/**
	 * 根据DevId 查询直熔盘信息
	* 
	* @Title: InvestmentService.java 
	* @Description: TODO 
	* @param @param devId
	* @param @return
	* @return List<Investment>
	* @author fl
	* @date 2017年12月16日 下午2:06:30
	* @version V1.0
	 */
	List<Investment> queryByDevId(Long devId);

	/**
	 * 
	 * @Title: save
	 * @Description: 保存直熔盘(修改和添加)
	 * @param @param investment
	 * @param @param user
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月16日 下午2:18:17
	 * @version V1.0
	 */
	int save(Investment investment, Users user);
	/**
	 * 
	* @Title: deleteInvestByInvestId 
	* @Description: 删除直熔盘
	* @param @param investId
	* @param @return    入参
	* @return int    返回类型
	* @author 周宇 
	* @throws
	* @date 2017年12月17日 下午12:57:25 
	* @version V1.0
	 */
	int deleteInvestByInvestId(Long investId);

}
  
    