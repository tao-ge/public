package com.ycnet.frms.service;

import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.WxUnlock;

public interface WxUnlockService {

	boolean insertWxUnlock(WxUnlock wxUnlock);
	
    List<Map<String, Object>> queryUnlockRecordByUser(long applyUser, int pageNo, int pageSize);
}
