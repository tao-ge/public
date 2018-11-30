package com.ycnet.frms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.frms.bean.WellPileline;
import com.ycnet.frms.mapper.WellPilelineMapper;
import com.ycnet.frms.service.WellPilelineService;

@Service("wellPilelineService")
@Transactional
public class WellPilelineServiceImpl implements WellPilelineService {
	@Resource(name="wellPilelineMapper")
	private WellPilelineMapper wellPilelineMapper;

	//导出数据库 刘沧海 2017/10/13
	@Override
	public List<WellPileline> queryList() {
		return wellPilelineMapper.queryList();
	}
}
