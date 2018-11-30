package com.ycnet.frms.mapper.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.TaskRecord;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.TaskRecordMapper;
import com.ycnet.frms.vo.TaskRecordBean;

@Repository("taskRecordMapper")
public class TaskRecordMapperImpl extends BaseSqlSupport implements TaskRecordMapper{

	@Override
	public int deleteByPrimaryKey(Long taskId) {
		
		return this.delete("com.ycnet.frms.mapper.TaskRecordMapper.deleteByPrimaryKey",taskId);
	}

	@Override
	public int insert(TaskRecord record) {
		// TODO Auto-generated method stub
		return this.insert("com.ycnet.frms.mapper.TaskRecordMapper.insert",record);
	}
//鍏夌紗浠诲姟娣诲姞
	@Override
	public int insertSelectiveglan(TaskRecord record) {
		// TODO Auto-generated method stub
		return this.insert("com.ycnet.frms.mapper.TaskRecordMapper.insertSelectiveglan",record);
	}
	@Override
	public int insertSelective(TaskRecord record) {
		// TODO Auto-generated method stub
		return this.insert("com.ycnet.frms.mapper.TaskRecordMapper.insertSelective",record);
	}

	@Override
	public TaskRecordBean selectByPrimaryKey(Long taskId) {
		// TODO Auto-generated method stub
		return this.selectOne("com.ycnet.frms.mapper.TaskRecordMapper.selectByPrimaryKey",taskId);
	}
	@Override
	public TaskRecordBean selectByPrimaryKeyglan(Long cableId) {
		// TODO Auto-generated method stub
		return this.selectOne("com.ycnet.frms.mapper.TaskRecordMapper.selectByPrimaryKeyglan",cableId);
	}
	@Override
	public TaskRecordBean selectRoutename(String terminalinformation) {
		// TODO Auto-generated method stub
		return this.selectOne("com.ycnet.frms.mapper.TaskRecordMapper.selectRoutename",terminalinformation);
	}
	@Override
	public int updateByPrimaryKeySelective(TaskRecord record) {
		// TODO Auto-generated method stub
		String c=record.getFlag();
		return this.update("com.ycnet.frms.mapper.TaskRecordMapper.updateByPrimaryKeySelective",record);
	}
	@Override
	public int updateByPrimaryKeySelectiveglan(TaskRecord record) {
		// TODO Auto-generated method stub
		String c=record.getFlag();
		return this.update("com.ycnet.frms.mapper.TaskRecordMapper.updateByPrimaryKeySelectiveglan",record);
	}

	@Override
	public int updateByPrimaryKey(TaskRecord record) {
		// TODO Auto-generated method stub
		return this.update("com.ycnet.frms.mapper.TaskRecordMapper.updateByPrimaryKey",record);
	}
	
	@Override
	public List<TaskRecordBean> queryTaskRecordList(Map<String, Object> map) {
		return this.selectList("com.ycnet.frms.mapper.TaskRecordMapper.queryTaskRecordList",map);
	}
	@Override
	public List<TaskRecordBean> queryTaskRecordglanList(Map<String, Object> map) {
		return this.selectList("com.ycnet.frms.mapper.TaskRecordMapper.queryTaskRecordglanList",map);
	}

	@Override
	public int queryCountByConditionMap(Map<String, Object>  map) {
		
		return this.selectOne("com.ycnet.frms.mapper.TaskRecordMapper.queryCountByConditionMap", map);
	}
	@Override
	public int queryCountByConditionglanMap(Map<String, Object>  map) {
		
		return this.selectOne("com.ycnet.frms.mapper.TaskRecordMapper.queryCountByConditionglanMap", map);
	}

	@Override
	public int updateFlagByPrimaryKey(TaskRecord record) {
		
		return this.update("com.ycnet.frms.mapper.TaskRecordMapper.updateFlagByPrimaryKey",record);
	}
	@Override
	public int updateFlagglanByPrimaryKey(TaskRecord record) {
		
		return this.update("com.ycnet.frms.mapper.TaskRecordMapper.updateFlagglanByPrimaryKey",record);
	}

	@Override
	public List<Users> selectTaskUser() {
		// TODO Auto-generated method stub
		return this.selectList("com.ycnet.frms.mapper.TaskRecordMapper.selectTaskUser");
	}

}
