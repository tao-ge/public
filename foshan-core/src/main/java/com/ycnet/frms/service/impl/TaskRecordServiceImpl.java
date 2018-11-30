package com.ycnet.frms.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.TaskRecord;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.TaskRecordMapper;
import com.ycnet.frms.service.TaskRecordService;
import com.ycnet.frms.vo.TaskRecordBean;

import freemarker.template.SimpleDate;

@Service("taskRecordService")
public class TaskRecordServiceImpl implements TaskRecordService{
	
	@Resource(name="taskRecordMapper")
	private TaskRecordMapper taskRecordMapper;

	@Override
	public int deleteByPrimaryKey(Long taskId) {
		return taskRecordMapper.deleteByPrimaryKey(taskId);
	}

	@Override
	public int insert(TaskRecord record) {
		return taskRecordMapper.insert(record);
	}

	@Override
	public int insertSelective(TaskRecord record) {
		return taskRecordMapper.insertSelective(record);
	}
	//光缆添加
	@Override
	public int insertSelectiveglan(TaskRecord record) {
		
		return taskRecordMapper.insertSelectiveglan(record);
	}

	@Override
	public TaskRecordBean selectByPrimaryKey(Long taskId) {
		return taskRecordMapper.selectByPrimaryKey(taskId);
	}
	@Override
	public TaskRecordBean selectByPrimaryKeyglan(Long cableId) {
		return taskRecordMapper.selectByPrimaryKeyglan(cableId);
	}
	@Override
	public TaskRecordBean selectRoutename(String  terminalinformation) {
		return taskRecordMapper.selectRoutename(terminalinformation);
	}

	@Override
	public int updateByPrimaryKeySelective(TaskRecord record) {
	
		return taskRecordMapper.updateByPrimaryKeySelective(record);
	}
	@Override
	public int updateByPrimaryKeySelectiveglan(TaskRecord record) {
	
		return taskRecordMapper.updateByPrimaryKeySelectiveglan(record);
	}

	@Override
	public int updateByPrimaryKey(TaskRecord record) {
		return taskRecordMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageBean queryTaskRecordList(TaskRecordBean record, PageBean pb,Users user) throws ParseException {
		//处理endTime,对其进行加1
		if (record !=null && record.getEndTime()!=null && !"".equals(record.getEndTime())) {
			String endtime = record.getEndTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cd = Calendar.getInstance();   
			cd.setTime(sdf.parse(endtime));
			cd.add(Calendar.DATE, 1);//增加一天
			record.setEndTime(sdf.format(cd.getTime()));
		}
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("record", record);
		conditionMap.put("user", user);
		conditionMap.put("pb", pb);
		pb.setRows(taskRecordMapper.queryCountByConditionMap(conditionMap));
	
		pb.setList(taskRecordMapper.queryTaskRecordList(conditionMap));
		return pb;
	}
	@Override
	public PageBean queryTaskRecordglanList(TaskRecordBean record, PageBean pb,Users user) {
		 
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("record", record);
		conditionMap.put("user", user);
		conditionMap.put("pb", pb);
		pb.setRows(taskRecordMapper.queryCountByConditionglanMap(conditionMap));
	
		pb.setList(taskRecordMapper.queryTaskRecordglanList(conditionMap));
		return pb;
	}
	@Override
	public int updateFlagByPrimaryKey(TaskRecord record) {
		return taskRecordMapper.updateFlagByPrimaryKey(record);
	}
	@Override
	public int updateFlagglanByPrimaryKey(TaskRecord record) {
		return taskRecordMapper.updateFlagglanByPrimaryKey(record);
	}
	
	@Override
	public List<Users> selectTaskUser() {
		
		return taskRecordMapper.selectTaskUser();
	}

}
