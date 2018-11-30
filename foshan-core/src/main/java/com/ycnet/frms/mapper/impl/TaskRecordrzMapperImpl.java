package com.ycnet.frms.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.FacilityAccess;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.TaskRecordrzMapper;
import com.ycnet.frms.vo.SectRouteBean;
import com.ycnet.frms.vo.TaskRecordBean;
@Repository("taskRecordrzMapper")
public class TaskRecordrzMapperImpl extends BaseSqlSupport implements TaskRecordrzMapper{
	
	//查询日志类型 佟盛玮
	public List<Users> selectlogtype() {
		// TODO Auto-generated method stub
		return this.selectList("com.ycnet.frms.mapper.TaskRecordrzMapper.selectlogtype");
	}
	//查询任务类型 佟盛玮
		public List<Users> selecttasktype() {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.TaskRecordrzMapper.selecttasktype");
		}
	//查询除了超级管理员外得所有人并且在一个组织机构下 佟盛玮 2017.10.25
	public List<Users> selectuserlogtype(Users user) {
		// TODO Auto-generated method stub
		return this.selectList("com.ycnet.frms.mapper.TaskRecordrzMapper.selectuserlogtype",user);
	}
	//查询是否是超级管理员 佟盛玮 2017.10.25
		public List<Users> selectuserloggl(Users user) {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.TaskRecordrzMapper.selectuserloggl",user);
		}
	@Override
	//查询任务日志的条数 佟盛玮
	public int queryCountByConditionrzMap(Map<String, Object>  map) {
		
		return this.selectOne("com.ycnet.frms.mapper.TaskRecordrzMapper.queryCountByConditionrzMap", map);
	}
	//查询任务日志的数据 佟盛玮
	@Override
	public List<TaskRecordBean> queryTaskRecordrzList(Map<String, Object> map) {
		return this.selectList("com.ycnet.frms.mapper.TaskRecordrzMapper.queryTaskRecordrzList",map);
	}
	@Override
	//查询任务日志的条数 佟盛玮
	public int queryCountByConditionlogMap(Map<String, Object>  map) {
		
		return this.selectOne("com.ycnet.frms.mapper.TaskRecordrzMapper.queryCountByConditionlogMap", map);
	}

	@Override
	public List<TaskRecordBean> queryTaskRecordlogList(Map<String, Object> map) {
		return this.selectList("com.ycnet.frms.mapper.TaskRecordrzMapper.queryTaskRecordlogList",map);
	}
	//修改原有的资管版本号佟盛玮 2017.10.25
	@Override
	public int updateversion(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.update("com.ycnet.frms.mapper.TaskRecordrzMapper.updateversion",map);
	}
	//管理员录入数据 佟盛玮 2017.10.25
		@Override
		public int updatsh(Map<String, Object> map) {
			// TODO Auto-generated method stub
			return this.update("com.ycnet.frms.mapper.TaskRecordrzMapper.updatsh",map);
		}
		//添加功能 查询分配任务人员 佟盛玮 2017.10.25
		@Override
		public List<Users> queryUserListByOrgId(Long orgId) {
			return this.selectList("com.ycnet.frms.mapper.TaskRecordrzMapper.queryUserListByOrgId", orgId);
		}
		//查询原有的版本号 佟盛玮 2017.10.25
		@Override
		public List<TaskRecordBean>selectbbh(Long orgId) {
			return this.selectList("com.ycnet.frms.mapper.TaskRecordrzMapper.selectbbh", orgId);
		}
		//查询设施列表 佟盛玮 2017.10.25
		@Override
		public List<Facility> queryListlogContent(Map<String, Object> map) {
			return this.selectList("com.ycnet.frms.mapper.TaskRecordrzMapper.queryListlogContent",map);
		}
		//保存数据 佟盛玮 2017.10.25
		@Override
		public int insertversion(Map<String, Object> map)
		{
			return this.insert("com.ycnet.frms.mapper.TaskRecordrzMapper.insertversion",map);
		}
		//修改设施表中的版本号 佟盛玮 2017.10.25
		@Override
		public int updatessversion(TaskRecordBean record) {
			// TODO Auto-generated method stub
			return this.update("com.ycnet.frms.mapper.TaskRecordrzMapper.updatessversion",record);
		}
		//查询设施列表并显示  佟盛玮  2017.10.25
		@Override
		public List<TaskRecordBean> queryListssslb(String[] ids) {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.TaskRecordrzMapper.queryListssslb",ids);
		}
}
