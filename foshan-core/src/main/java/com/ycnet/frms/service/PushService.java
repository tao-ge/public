package com.ycnet.frms.service;

import com.ycnet.frms.bean.InspectWork;
import com.ycnet.frms.bean.Users;

public interface PushService {

	/**
	 * 
	 * @Title: insertInspectWork
	 * @Description: 推送巡检任务
	 * @param @param work
	 * @param @param user 
	 * @return void 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年2月24日 上午9:37:27
	 * @version V1.0
	 */
	void pushInspectWork(InspectWork work, Users user);

}
  
    