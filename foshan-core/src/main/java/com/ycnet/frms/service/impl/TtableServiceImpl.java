package com.ycnet.frms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.frms.bean.Tdisc;
import com.ycnet.frms.bean.Tjumper;
import com.ycnet.frms.mapper.TdiscMapper;
import com.ycnet.frms.mapper.TjumperMapper;
import com.ycnet.frms.service.TtableService;

@Service("ttableServcie")
@Transactional
public class TtableServiceImpl implements TtableService{

	@Resource
	private TdiscMapper tdiscMapper;
	
	@Resource
	private TjumperMapper tjumperMapper;
	
	@Override
	public int saveDisc(List<Tdisc> list) {
		int ret = 0;
		for(Tdisc disc :list)
		{
			ret +=tdiscMapper.insertSelective(disc);
		}
		return ret;
	}

	@Override
	public int saveJumper(List<Tjumper> list) {
		int ret = 0;
		for(Tjumper jumper :list)
		{
			ret +=tjumperMapper.insertSelective(jumper);
		}
		return ret;
	}

	@Override
	public void deleteByUser(Long userId) {
		tdiscMapper.deleteByUser(userId);
		tjumperMapper.deleteByUser(userId);
	}

	@Override
	public void deleteByDev(String devCode) {
		tdiscMapper.deleteByDev(devCode);
		tjumperMapper.deleteByDev(devCode);
	}

}
