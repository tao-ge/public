package com.ycnet.frms.service;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.vo.TransfLogEntityBean;

public interface TransfLogService {

	/**
	 * 设备上报日志查询
	* @Title: queryAll 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param pb
	* @param @param transfLogEntity
	* @param @return    入参
	* @return PageBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月24日 上午11:06:35 
	* @version V1.0
	 */
	PageBean queryAll(PageBean pb, TransfLogEntityBean transfLogEntity);

}
