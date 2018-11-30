package com.ycnet.frms.mapper.impl;

import org.springframework.stereotype.Repository;
import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.WxUsers;
import com.ycnet.frms.mapper.WxUsersMapper;

@Repository
public class WxUsersMapperImpl extends BaseSqlSupport implements WxUsersMapper {

	@Override
	public Long queryUserByOpenId(String openId) {
		return this.selectOne("com.ycnet.frms.mapper.WxUsersMapper.queryUserByOpenId", openId);
	}
	
	@Override
	public int updateWxUsersOpenId(WxUsers user) {
		return this.update("com.ycnet.frms.mapper.WxUsersMapper.updateWxUsersOpenId", user);
	}

}