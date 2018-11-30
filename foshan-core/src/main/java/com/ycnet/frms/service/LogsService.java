package com.ycnet.frms.service;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Logs;
import com.ycnet.frms.vo.LogsConditionBean;

public interface LogsService {

	/**
	 * 查询设施监控数据
	 * fl 增加机构区分
	* 
	* @Title: LogsService.java 
	* @Description: TODO 
	* @param @param bean
	* @param @param pb
	* @param @param orgId
	* @param @return
	* @return PageBean
	* @author fl
	* @date 2018年1月31日 上午8:37:39
	* @version V1.0
	 */
	PageBean queryByConditionBeans(LogsConditionBean bean,PageBean pb, Long orgId);
	
	int addLogs(Logs logs);
}
