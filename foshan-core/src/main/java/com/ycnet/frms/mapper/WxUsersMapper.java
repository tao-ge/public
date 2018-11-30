package com.ycnet.frms.mapper;

import com.ycnet.frms.bean.WxUsers;

public interface WxUsersMapper {
    Long queryUserByOpenId(String openId);
    
    int updateWxUsersOpenId(WxUsers user);
}