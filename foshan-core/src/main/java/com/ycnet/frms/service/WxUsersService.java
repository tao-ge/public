package com.ycnet.frms.service;

import com.ycnet.frms.bean.WxUsers;

public interface WxUsersService {
	
	Long queryUserByOpenId(String openId);
	
	boolean updateWxUsersOpenId(WxUsers user);
}
