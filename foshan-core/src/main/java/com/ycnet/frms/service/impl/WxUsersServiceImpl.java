package com.ycnet.frms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.frms.bean.WxUsers;
import com.ycnet.frms.mapper.WxUsersMapper;
import com.ycnet.frms.service.WxUsersService;

@Service
public class WxUsersServiceImpl implements WxUsersService {

	@Resource
	private WxUsersMapper wxUsersMapper;

	@Override
	public Long queryUserByOpenId(String openId) {
		return wxUsersMapper.queryUserByOpenId(openId);
	}
	
	@Override
	public boolean updateWxUsersOpenId(WxUsers user) {
		return wxUsersMapper.updateWxUsersOpenId(user) > 0 ? true : false;
	}

}
