package com.ycnet.frms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Tmps;
import com.ycnet.frms.mapper.TmpsMapper;
import com.ycnet.frms.service.TmpsService;
import com.ycnet.frms.vo.TmpsConditionBean;

@Service("tmpsService")
public class TmpsServiceImpl implements TmpsService{

	@Resource(name="tmpsMapper")
	private TmpsMapper tmpsMapper;
	
	@Override
	public PageBean queryByConditionBeans(TmpsConditionBean bean,PageBean pb) {
		// TODO Auto-generated method stub
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("TmpsCondition", bean);
		conditionMap.put("pb", pb);
		pb.setRows(tmpsMapper.queryCountByConditionMap(conditionMap));//;
		pb.setList(tmpsMapper.queryByConditionBeans(conditionMap));		
				
		return pb;
	}

}
