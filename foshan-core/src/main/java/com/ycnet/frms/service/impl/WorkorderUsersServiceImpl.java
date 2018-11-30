package com.ycnet.frms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.frms.bean.Users;
import com.ycnet.frms.bean.WorkorderUsers;
import com.ycnet.frms.mapper.WorkorderUsersMapper;
import com.ycnet.frms.service.WorkorderUsersService;

@Service("workorderUsersService")
@Transactional
public class WorkorderUsersServiceImpl implements WorkorderUsersService{
	
	@Resource(name="workorderUsersMapper")
	private WorkorderUsersMapper workorderUsersMapper;
	
	/**
	 * 
	 * @Title: selectByUserCode
	 * @Description: 验证userCode是否重复
	 * @param @param userCode
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月4日 下午4:51:10
	 * @version V1.0
	 */
	@Override
	public int selectByUserCode(String userCode) {
		return workorderUsersMapper.selectByUserCode(userCode);
	}

	/**
	 * 
	 * @Title: updateByPrimaryKeySelective
	 * @Description: 修改光调用户
	 * @param @param workOrderUser
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月8日 上午9:33:58
	 * @version V1.0
	 */
	@Override
	public int updateByPrimaryKeySelective(WorkorderUsers workOrderUser) {
		return workorderUsersMapper.updateByPrimaryKeySelective(workOrderUser);
	}

	/**
	 * 
	 * @Title: insertSelective
	 * @Description: 添加光调用户
	 * @param @param wUser
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月9日 下午4:45:26
	 * @version V1.0
	 */
	@Override
	public int insertSelective(WorkorderUsers wUser) {
		return workorderUsersMapper.insertSelective(wUser);
	}

	/**
	 * 
	 * @Title: getUserId
	 * @Description: 获取最新userId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月9日 下午6:02:09
	 * @version V1.0
	 */
	@Override
	public Long getUserId() {
		return workorderUsersMapper.getUserId();
	}

	/**
	 * 
	 * @Title: queryByUserCode
	 * @Description: 根据userCode查询光调用户
	 * @param @param userCode
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月10日 下午1:28:25
	 * @version V1.0
	 */
	@Override
	public WorkorderUsers queryByUserCode(String userCode) {
		return workorderUsersMapper.queryByUserCode(userCode);
	}

}
  
    