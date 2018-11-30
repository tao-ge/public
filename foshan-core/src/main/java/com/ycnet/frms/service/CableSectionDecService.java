package com.ycnet.frms.service;


import java.util.HashMap;
import java.util.List;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.CableSectionDec;

public interface CableSectionDecService {

	
	/**
	* @Title: 查询光缆断详细信息
	* @Description: TODO 
	* @param @return
	* @return String
	* @author fl
	* @date 2017年11月27日 下午2:36:50
	* @version V1.0
	 * @param fiberNo 
	 * @param pageNo 
	 * @param pb 
	 */
	List<CableSectionDec> queryCabledetails(Long id, Long fiberNo, String sectDec, PageBean pageBean);



	/**
	 * 查询成端类型
	* 
	* @Title: CableSectionDecService.java 
	* @Description: TODO 
	* @param @param map
	* @param @return
	* @return String
	* @author fl
	* @date 2017年11月28日 下午12:35:02
	* @version V1.0
	 */
	CableSectionDec queryIsOccup(HashMap<String, Long> map);

	
	

}
