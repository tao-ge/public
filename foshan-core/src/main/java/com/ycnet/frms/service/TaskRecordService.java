package com.ycnet.frms.service;

import java.text.ParseException;
import java.util.List;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.TaskRecord;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.TaskRecordBean;

public interface TaskRecordService {
	
    int deleteByPrimaryKey(Long taskId);

    int insert(TaskRecord record);

    int insertSelective(TaskRecord record);

    TaskRecordBean selectByPrimaryKey(Long taskId);

    int updateByPrimaryKeySelective(TaskRecord record);

    int updateByPrimaryKey(TaskRecord record);
    
    /**分页查询 可带条件
     * @param user 
     * @throws ParseException */
    PageBean queryTaskRecordList(TaskRecordBean record,PageBean pb, Users user) throws ParseException;
    
    /**删除 一条任务记录*/
	int updateFlagByPrimaryKey(TaskRecord record);
	
	/**查询 所有有任务记录的用户*/
	List<Users> selectTaskUser();

	TaskRecordBean selectRoutename(String terminalinformation);

	int insertSelectiveglan(TaskRecord record);

	PageBean queryTaskRecordglanList(TaskRecordBean record, PageBean pb, Users user);

	TaskRecordBean selectByPrimaryKeyglan(Long taskId);

	int updateByPrimaryKeySelectiveglan(TaskRecord record);

	int updateFlagglanByPrimaryKey(TaskRecord record);

}
