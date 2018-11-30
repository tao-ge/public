package com.ycnet.frms.service;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.ResourceDataLog;

public interface ResourceDataLogService {

	/**
	 * 
	 * 资管校准日志列表
	* @author: DZY  
	* @createTime: 2017年11月8日 上午9:01:17  
	* @history:  
	* @param resourceDataLog
	* @param pb
	* @return PageBean
	 */
	PageBean queryByConditionBeans(ResourceDataLog resourceDataLog, PageBean pb);

	/**
	 * 
	 * @Title: saveResourceDataLog
	 * @Description: 资管校准日志添加
	 * @param @param rdl
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年11月8日 下午2:07:43
	 * @version V1.0
	 */
	int saveResourceDataLog(ResourceDataLog rdl);

}
  
    