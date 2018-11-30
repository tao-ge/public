package com.ycnet.frms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.FacilityAccess;
import com.ycnet.frms.bean.Users;

import com.ycnet.frms.mapper.TaskRecordrzMapper;
import com.ycnet.frms.service.TaskRecordrzService;
import com.ycnet.frms.vo.SectRouteBean;
import com.ycnet.frms.vo.TaskRecordBean;
@Service("taskRecordrzService")
public class TaskRecordrzServiceImpl implements TaskRecordrzService  {
	@Resource(name="taskRecordrzMapper")
	private TaskRecordrzMapper taskRecordrzMapper;
	@Override
	////查询日志类型 佟盛玮
	public List<Users> selectlogtype() {
		
		List<Users>	 a=new ArrayList<Users>();
		return taskRecordrzMapper.selectlogtype();
	}
	
////查询任务记录类型 佟盛玮
	public List<Users> selecttasktype() {
		
		List<Users>	 a=new ArrayList<Users>();
		return taskRecordrzMapper.selecttasktype();
	}
	//查询除了超级管理员外得所有人并且在一个组织机构下 佟盛玮 2017.10.25
	@Override
public List<Users> selectuserlogtype(Users user) {
		
		List<Users>	 a=new ArrayList<Users>();
		return taskRecordrzMapper.selectuserlogtype( user);
	}
	//查询是否是超级管理管理员 佟盛玮 2017.10.25
	@Override
	public List<Users> selectuserloggl(Users user) {
			
			
			return taskRecordrzMapper.selectuserloggl( user);
		}
	////查询任务日志条数和数据 佟盛玮
	@Override
	public PageBean queryTaskRecordgrzList(TaskRecordBean record, PageBean pb,Users user) {
		 
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("record", record);
		conditionMap.put("user", user);
		conditionMap.put("pb", pb);
		pb.setRows(taskRecordrzMapper.queryCountByConditionrzMap(conditionMap));
	      
		pb.setList(taskRecordrzMapper.queryTaskRecordrzList(conditionMap));
		return pb;
	}
	//任务记录查询
	@Override
	public PageBean queryTaskRecordlogList(TaskRecordBean record, PageBean pb,Users user) {
		 
		Map<String,Object> conditionMap=new HashMap<String,Object>();

		conditionMap.put("record", record);
		conditionMap.put("user", user);
		conditionMap.put("pb", pb);
		
		pb.setRows(taskRecordrzMapper.queryCountByConditionlogMap(conditionMap));
	      
		pb.setList(taskRecordrzMapper.queryTaskRecordlogList(conditionMap));
		
		return pb;
	}
	//修改原有的资管版本号佟盛玮 2017.10.25
	@Override
	public int updatversion(TaskRecordBean record,Users user) {
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("record", record);
		conditionMap.put("user", user);
		return taskRecordrzMapper.updateversion(conditionMap);
	}
	//管理员录入数据 佟盛玮 2017.10.25
		@Override
		public int updatesh(TaskRecordBean record,Users user) {
			Map<String,Object> conditionMap=new HashMap<String,Object>();
			conditionMap.put("record", record);
			conditionMap.put("user", user);
			return taskRecordrzMapper.updatsh(conditionMap);
		}
		//添加功能 查询分配任务人员 佟盛玮 2017.10.25
		@Override
		public List<Users> queryUserListByOrgId(Long orgId) {
			return taskRecordrzMapper.queryUserListByOrgId(orgId);
		}
		//查询原有的版本号 佟盛玮 2017.10.25
		@Override
		public List<TaskRecordBean> selectbbh(Long orgId) {
			return taskRecordrzMapper.selectbbh(orgId);
		}
		//查询设施列表 佟盛玮 2017.10.25
		@Override
		public List<Facility> queryListlogContent(String content,Long orgId) {
			// TODO Auto-generated method stub
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("content",content);
			map.put("orgId", orgId);
			return taskRecordrzMapper.queryListlogContent(map);
		}
		//保存数据 佟盛玮 2017.10.25
		@Override
		public int saveverson(TaskRecordBean record,Users user) {
			Map<String,Object> conditionMap=new HashMap<String,Object>();
			conditionMap.put("record", record);
			conditionMap.put("user", user);
			long version = 0;
			String version1=record.getDevIds();
			String[] version2=version1.split(",");
			for(int i=0;i<version2.length;i++) {
				version=Long.parseLong(version2[i].toString());
				record.setVersion(version);
				//修改设施表中的版本号 佟盛玮 2017.10.25
				taskRecordrzMapper.updatessversion(record);
			}
				return taskRecordrzMapper.insertversion(conditionMap);
		}
		//查询设施列表并显示  佟盛玮  2017.10.25
		public List<TaskRecordBean> queryListsslb(String ids) {
			return taskRecordrzMapper.queryListssslb(ids.split(","));

		}
}
