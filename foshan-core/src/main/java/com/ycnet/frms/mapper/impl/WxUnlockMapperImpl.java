package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.WxUnlock;
import com.ycnet.frms.mapper.WxUnlockMapper;

@Repository
public class WxUnlockMapperImpl extends BaseSqlSupport implements WxUnlockMapper{

	@Override
	public int insertWxUnlock(WxUnlock wxUnlock) {
		return this.insert("com.ycnet.frms.mapper.WxUnlockMapper.insert", wxUnlock);
	}
	
	@Override
	public List<Map<String, Object>> queryUnlockRecordByUser(long applyUser, int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applyUser", applyUser);
		map.put("startRowNum", (pageNo-1)*pageSize);
		map.put("endRowNum", pageSize);
		return this.selectList("com.ycnet.frms.mapper.WxUnlockMapper.queryUnlockRecordByUser", map);
	}
}
