package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.TaskRecordBean;

public interface TaskRecordrzService {
////查询日志类型 佟盛玮
	List<Users> selectlogtype();
////查询任务日志条数和数据 佟盛玮
	PageBean queryTaskRecordgrzList(TaskRecordBean record, PageBean pb, Users user);
	//任务记录查询
	PageBean queryTaskRecordlogList(TaskRecordBean record, PageBean pb, Users user);
	//查询除了超级管理员外得所有人并且在一个组织机构下 佟盛玮 2017.10.25
	List<Users> selectuserlogtype(Users user);
	//查询是否是超级管理管理员 佟盛玮 2017.10.25
	List<Users> selectuserloggl(Users user);
	//修改原有的资管版本号佟盛玮 2017.10.25
	int updatversion(TaskRecordBean record, Users user);
	//管理员录入数据 佟盛玮 2017.10.25
	int updatesh(TaskRecordBean record, Users user);
	//添加功能 查询分配任务人员 佟盛玮 2017.10.25
	List<Users> queryUserListByOrgId(Long orgId);
	//查询设施列表 佟盛玮 2017.10.25
	List<Facility> queryListlogContent(String content, Long orgId);
	//查询原有的版本号 佟盛玮 2017.10.25
	List<TaskRecordBean> selectbbh(Long orgId);
	//保存数据 佟盛玮 2017.10.25
	int saveverson(TaskRecordBean record, Users user);
	//查询设施列表并显示  佟盛玮  2017.10.25
	List<TaskRecordBean> queryListsslb(String ids);
	//查询任务类型 佟盛玮
	List<Users> selecttasktype();

}
