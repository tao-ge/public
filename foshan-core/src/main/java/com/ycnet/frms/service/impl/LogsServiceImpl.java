package com.ycnet.frms.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Logs;
import com.ycnet.frms.mapper.LogsMapper;
import com.ycnet.frms.service.LogsService;
import com.ycnet.frms.vo.LogsConditionBean;

@Service("logsService")
public class LogsServiceImpl  implements LogsService{

	@Resource(name="logsMapper")
	private LogsMapper logsMapper;
	

	
	@Override
	public PageBean queryByConditionBeans(LogsConditionBean bean,PageBean pb,Long orgId) {
		// TODO Auto-generated method stub
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("LogsCondition", bean);
		conditionMap.put("pb", pb);
		conditionMap.put("orgId", orgId);
		pb.setRows(logsMapper.queryCountByConditionMap(conditionMap));//;
		pb.setList(logsMapper.queryByConditionBeans(conditionMap));		
				
		return pb;
	}



	@Override
	@Transactional
	public int addLogs(Logs logs) {
		return logsMapper.insertSelective(logs);
	}

	
}
