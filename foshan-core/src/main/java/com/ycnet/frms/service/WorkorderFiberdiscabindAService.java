package com.ycnet.frms.service;

import javax.servlet.http.HttpServletRequest;

import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.JumperInfo;

public interface WorkorderFiberdiscabindAService {

	/**
	 * 
	 * @Title: saveJumper
	 * @Description: 跳纤,并添加工单信息
	 * @param @param jumperInfo
	 * @param @param request
	 * @param @param user
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月5日 下午5:30:14
	 * @version V1.0
	 */
	int saveJumper(JumperInfo jumperInfo, HttpServletRequest request, Users user);

	/**
	 * 
	 * @Title: updateJumper
	 * @Description: 解跳纤,重新跳纤,修改调度工单设计方案
	 * @param @param jumperInfo
	 * @param @param request
	 * @param @param user
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月6日 上午10:49:20
	 * @version V1.0
	 */
	int updateJumper(JumperInfo jumperInfo, HttpServletRequest request, Users user);

}
  
    