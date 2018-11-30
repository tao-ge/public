package com.ycnet.frms.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.mapper.TransfLogEntityMapper;
import com.ycnet.frms.service.TransfLogService;
import com.ycnet.frms.vo.TransfLogEntityBean;

@Service("transfLogService")
public class TransfLogServiceImpl implements TransfLogService{

	@Resource(name="transfLogEntityMapper")
	private TransfLogEntityMapper transfLogEntityMapper;

	/**
	 * 设备上报日志查询
	* @Title: queryAll 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param pb
	* @param @param transfLogEntity
	* @param @return    入参
	* @return PageBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月24日 上午11:06:35 
	* @version V1.0
	 */
	@Override
	public PageBean queryAll(PageBean pb, TransfLogEntityBean transfLogEntity) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("pb", pb);
		map.put("transfLogEntity", transfLogEntity);
		pb.setList(transfLogEntityMapper.queryAll(map));
		pb.setRows(transfLogEntityMapper.queryAllCount(map));
		return pb;
	}

}
