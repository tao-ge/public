package com.ycnet.frms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.frms.bean.ActIdUser;
import com.ycnet.frms.mapper.ActIdUserMapper;
import com.ycnet.frms.service.ActIdUserService;

@Service("actIdUserService")
public class ActIdUserServiceImpl implements ActIdUserService{

	@Resource(name="actIdUserMapper")
	private ActIdUserMapper actIdUserMapper;
	
	/**
	 * 
	 * @Title: selectByPrimaryKey
	 * @Description: 根据ID查询一条
	 * @param @param userCode
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年5月15日 上午10:01:55
	 * @version V1.0
	 */
	@Override
	public ActIdUser selectByPrimaryKey(String userCode) {
		return actIdUserMapper.selectByPrimaryKey(userCode);
	}

	/**
	 * 
	 * @Title: insertSelective
	 * @Description: 添加流程用户
	 * @param @param actUser
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年5月15日 上午10:07:37
	 * @version V1.0
	 */
	@Override
	public int insertSelective(ActIdUser actUser) {
		return actIdUserMapper.insertSelective(actUser);
	}

	/**
	 * 
	 * @Title: deleteByPrimaryKey
	 * @Description: 删除流程用户
	 * @param @param userCode
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年5月15日 上午11:25:21
	 * @version V1.0
	 */
	@Override
	public int deleteByPrimaryKey(String userCode) {
		return actIdUserMapper.deleteByPrimaryKey(userCode);
	}

}
  
    