package com.ycnet.frms.mapper;

import com.ycnet.frms.bean.WorkorderUsers;

public interface WorkorderUsersMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(WorkorderUsers record);

    int insertSelective(WorkorderUsers record);

    WorkorderUsers selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(WorkorderUsers record);

    int updateByPrimaryKey(WorkorderUsers record);

    /**
     * 
     * @Title: selectByUserCode
     * @Description: 验证userCode是否重复
     * @param @param userCode
     * @param @return 
     * @return int 入参
     * @return String    返回类型
     * @author DZY 
     * @throws
     * @date 2018年4月4日 下午4:52:03
     * @version V1.0
     */
	int selectByUserCode(String userCode);

	/**
	 * 
	 * @Title: getUserId
	 * @Description: 获取最新userId
	 * @param @return 
	 * @return Long 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月9日 下午6:02:37
	 * @version V1.0
	 */
	Long getUserId();

	/**
	 * 
	 * @Title: queryByUserCode
	 * @Description: 根据userCode查询光调用户
	 * @param @param userCode
	 * @param @return 
	 * @return WorkorderUsers 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月10日 下午1:29:00
	 * @version V1.0
	 */
	WorkorderUsers queryByUserCode(String userCode);
}