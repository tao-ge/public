package com.ycnet.frms.mapper.impl;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.WorkorderUsers;
import com.ycnet.frms.mapper.WorkorderUsersMapper;

@Repository("workorderUsersMapper")
public class WorkorderUsersMapperImpl extends BaseSqlSupport implements WorkorderUsersMapper{

	@Override
	public int deleteByPrimaryKey(Long userId) {
		return this.delete("com.ycnet.frms.mapper.WorkorderUsersMapper.deleteByPrimaryKey",userId);
	}

	@Override
	public int insert(WorkorderUsers record) {
		return this.insert("com.ycnet.frms.mapper.WorkorderUsersMapper.insert",record);
	}

	@Override
	public int insertSelective(WorkorderUsers record) {
		return this.insert("com.ycnet.frms.mapper.WorkorderUsersMapper.insertSelective",record);
	}

	@Override
	public WorkorderUsers selectByPrimaryKey(Long userId) {
		return this.selectOne("com.ycnet.frms.mapper.WorkorderUsersMapper.selectByPrimaryKey",userId);
	}

	@Override
	public int updateByPrimaryKeySelective(WorkorderUsers record) {
		return this.update("com.ycnet.frms.mapper.WorkorderUsersMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(WorkorderUsers record) {
		return this.update("com.ycnet.frms.mapper.WorkorderUsersMapper.updateByPrimaryKey",record);
	}

	/**
	 * 
	 * @Title: selectByUserCode
	 * @Description: 验证userCode是否重复
	 * @param @param userCode
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月4日 下午4:51:50
	 * @version V1.0
	 */
	@Override
	public int selectByUserCode(String userCode) {
		return this.selectOne("com.ycnet.frms.mapper.WorkorderUsersMapper.selectByUserCode",userCode);
	}

	/**
	 * 
	 * @Title: getUserId
	 * @Description: 获取最新userId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月9日 下午6:03:03
	 * @version V1.0
	 */
	@Override
	public Long getUserId() {
		return this.selectOne("com.ycnet.frms.mapper.WorkorderUsersMapper.getUserId");
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
	 * @date 2018年4月10日 下午1:29:19
	 * @version V1.0
	 */
	@Override
	public WorkorderUsers queryByUserCode(String userCode) {
		return this.selectOne("com.ycnet.frms.mapper.WorkorderUsersMapper.queryByUserCode",userCode);
	}

}
  
    