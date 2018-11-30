package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.InspectWork;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.InspectWorkConditionBean;
import com.ycnet.frms.vo.InspectWorkDev;

public interface InspectWorkService {
	
	InspectWork selectById(Long id);
	
	/**
	 * 
	 * @Title: save
	 * @Description: 巡检任务管理编辑
	 * @param @param access
	 * @param @param user
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY （添加注释）
	 * @throws
	 * @date 2018年2月24日 上午9:35:36
	 * @version V1.0
	 */
	int save(InspectWork access, Users user);
	
	int delete(Long id);
	
	List<InspectWork> queryList(InspectWork fa,PageBean pb,Users user);
	
	int queryCountByList(InspectWork fa,Users user);
	
	List<InspectWork> queryListByCondition(InspectWorkConditionBean cb,PageBean pb,Users user);
	
	int queryCountByCondition(InspectWorkConditionBean cb,Users user);
	
	List<InspectWorkDev> queryDevList(Long workId);

	/**
	 * 
	 * @Title: queryNotCompletedCount
	 * @Description: 查询未完成巡检任务的数量
	 * @param @param user
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年2月13日 上午9:37:28
	 * @version V1.0
	 */
	int queryNotCompletedCount(Users user);
}
