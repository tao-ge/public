package com.ycnet.frms.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.RemoteUnlockEntity;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.RemoteUnlockEntityMapper;
import com.ycnet.frms.service.RemoteUnlockEntityService;
import com.ycnet.frms.vo.RemoteUnlockEntityVo;

@Transactional
@Service("remoteUnlockEntityService")
public class RemoteUnlockEntityServiceImpl implements RemoteUnlockEntityService{
	
	@Resource(name="remoteUnlockEntityMapper")
	private RemoteUnlockEntityMapper remoteUnlockEntityMapper;

	/**
	 * 
	 * @Title: queryRemoteUnlockList
	 * @Description: 装维远程开锁申请列表
	 * @param @param ru
	 * @param @param pb
	 * @param @param user
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月15日 下午2:36:39
	 * @version V1.0
	 */
	@Override
	public PageBean queryRemoteUnlockList(RemoteUnlockEntityVo ru, PageBean pb, Users user) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ru", ru);
		map.put("user", user);
		map.put("pb", pb);
		pb.setRows(remoteUnlockEntityMapper.queryCountRemoteUnlockList(map));
		pb.setList(remoteUnlockEntityMapper.queryRemoteUnlockList(map));
		return pb;
	}

	@Override
	public int validateRemoteUnlock(Long devId) {
		int req = 0;
		RemoteUnlockEntity ru = remoteUnlockEntityMapper.queryLastByDevId(devId);
		if(ru!=null) {
			if(System.currentTimeMillis()<ru.getEndTime().getTime()) {//如果实际时间小于申请结束时间，说明还在申请时间内
				req = 0;
			}else {
				req = 1;
			}
		}else {
			req = 1;
		}
		return req;
	}

	/**
	 * 
	 * @Title: insertSelective
	 * @Description: 添加远程开锁申请
	 * @param @param ru
	 * @param @param user
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月15日 下午4:46:57
	 * @version V1.0
	 */
	@Override
	public int insertSelective(RemoteUnlockEntity ru, Users user) {
		ru.setOperatTime(new Date());
		ru.setOperatUser(user.getUserId());
		ru.setOrgId(user.getOrgId());
		return remoteUnlockEntityMapper.insertSelective(ru);
	}

	/**
	 * 删除远程开锁权限
	* @Title: remoteUnlockDelete 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param appId
	* @param @return    入参
	* @return int    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年7月12日 下午2:08:20 
	* @version V1.0
	 */
	@Override
	public int remoteUnlockDelete(Long appId) {
		// TODO Auto-generated method stub
		return remoteUnlockEntityMapper.deleteByPrimaryKey(appId);
	}
}
  
    