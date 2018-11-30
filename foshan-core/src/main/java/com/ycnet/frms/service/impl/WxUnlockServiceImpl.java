package com.ycnet.frms.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.ycnet.frms.bean.WxUnlock;
import com.ycnet.frms.mapper.WxUnlockMapper;
import com.ycnet.frms.service.WxUnlockService;

@Service
public class WxUnlockServiceImpl implements WxUnlockService{

	@Resource
	private WxUnlockMapper wxUnlockMapper;
	
	@Override
	public boolean insertWxUnlock(WxUnlock wxUnlock) {
		return wxUnlockMapper.insertWxUnlock(wxUnlock) > 0;
	}
	
	@Override
	public List<Map<String, Object>> queryUnlockRecordByUser(long applyUser, int pageNo, int pageSize) {
		return wxUnlockMapper.queryUnlockRecordByUser(applyUser, pageNo, pageSize);
	}
}
