package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.TaskRecord;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.TaskRecordBean;

public interface TaskRecordMapper {
    int deleteByPrimaryKey(Long taskId);

    int insert(TaskRecord record);

    int insertSelective(TaskRecord record);

    TaskRecordBean selectByPrimaryKey(Long taskId);

    int updateByPrimaryKeySelective(TaskRecord record);

    int updateByPrimaryKey(TaskRecord record);
    
    List<TaskRecordBean> queryTaskRecordList(Map<String, Object> map);

	int queryCountByConditionMap(Map<String, Object> map);

	int updateFlagByPrimaryKey(TaskRecord record);

	List<Users> selectTaskUser();

	TaskRecordBean selectRoutename(String terminalinformation);

	int insertSelectiveglan(TaskRecord record);

	int queryCountByConditionglanMap(Map<String, Object> map);

	List<TaskRecordBean> queryTaskRecordglanList(Map<String, Object> map);

	TaskRecordBean selectByPrimaryKeyglan(Long taskId);

	int updateByPrimaryKeySelectiveglan(TaskRecord record);

	int updateFlagglanByPrimaryKey(TaskRecord record);


}