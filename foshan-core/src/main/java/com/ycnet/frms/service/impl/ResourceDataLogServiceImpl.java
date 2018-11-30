package com.ycnet.frms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.ResourceDataLog;
import com.ycnet.frms.mapper.ResourceDataLogMapper;
import com.ycnet.frms.service.ResourceDataLogService;

@Service("resourceDataLogService")
public class ResourceDataLogServiceImpl implements ResourceDataLogService{

	@Resource(name="resourceDataLogMapper")
	private ResourceDataLogMapper resourceDataLogMapper;
	
	/**
	 * 资管校准日志列表
	 */
	@Override
	public PageBean queryByConditionBeans(ResourceDataLog resourceDataLog, PageBean pb) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rdl", resourceDataLog);
		map.put("pb", pb);
		pb.setRows(resourceDataLogMapper.queryCountByConditionMap(map));
		pb.setList(resourceDataLogMapper.queryByConditionBeans(map));
		return pb;
	}

	/**
	 * 删除资管校准日志
	 */
	@Override
	public int saveResourceDataLog(ResourceDataLog rdl) {
		return resourceDataLogMapper.insertSelective(rdl);
	}

}
  
    