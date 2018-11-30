package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.TaskRecordBean;

public interface TaskRecordrzMapper {
	//查询日志类型 佟盛玮
	public List<Users> selectlogtype();

	
	//查询任务日志的数据 佟盛玮
	List<TaskRecordBean> queryTaskRecordrzList(Map<String, Object> map);


	//查询任务日志的条数 佟盛玮
	int queryCountByConditionrzMap(Map<String, Object> map);


	//查询任务日志的数据佟盛玮
	List<TaskRecordBean> queryTaskRecordlogList(Map<String, Object> map);


	//查询任务日志的条数 佟盛玮
	int queryCountByConditionlogMap(Map<String, Object> map);


	//查询除了超级管理员外得所有人并且在一个组织机构下 佟盛玮 2017.10.25
	 List<Users> selectuserlogtype(Users user);


	//查询是否是超级管理员 佟盛玮 2017.10.25
	 List<Users> selectuserloggl(Users user);


		//修改原有的资管版本号佟盛玮 2017.10.25
	int updateversion(Map<String, Object> conditionMap);


	//管理员录入数据 佟盛玮 2017.10.25
	int updatsh(Map<String, Object> map);


	//添加功能 查询分配任务人员 佟盛玮 2017.10.25
	List<Users> queryUserListByOrgId(Long orgId);


	//查询设施列表 佟盛玮 2017.10.25
	List<Facility> queryListlogContent(Map<String, Object> map);


	//查询原有的版本号 佟盛玮 2017.10.25
	List<TaskRecordBean> selectbbh(Long orgId);

	//保存数据 佟盛玮 2017.10.25
	int insertversion(Map<String, Object> map);


	//修改设施表中的版本号 佟盛玮 2017.10.25
	int updatessversion(TaskRecordBean record);


	//查询设施列表并显示  佟盛玮  2017.10.25
	List<TaskRecordBean> queryListssslb(String[] ids);

	//查询任务类型 佟盛玮
	public List<Users> selecttasktype();





}
