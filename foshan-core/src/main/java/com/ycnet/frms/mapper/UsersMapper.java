package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.UsersConditionBean;
import com.ycnet.frms.vo.UsersVo;

public interface UsersMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
    
    Users getLoginUsers(UsersConditionBean param);
    
    //List<Users> queryUserList(Users users);
    List<Users> queryUserList(Map<String,Object> map);
    
    List<Users> queryUserListByOrgId(Long orgId);
    
    Long getUserId();
    
    int queryCountByConditionMap(Map<String,Object> map);
    
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
     * @date 2018年4月28日 上午9:28:34
     * @version V1.0
     */
    int selectByUserCode(String userCode);
    
    List<Users> selectByOrgId(Long orgId);

    /**
     * 
    * @Title: queryList 
    * @Description: 生成数据
    * @param @param orgId
    * @param @return    入参
    * @return List<Users>    返回类型
    * @author DZY 
    * @throws
    * @date 2017年10月23日 下午5:02:57 
    * @version V1.0
     */
	List<Users> queryList(Long orgId);

	/**
	 * 查询所有用户
	* 
	* @Title: UsersMapper.java 
	* @Description: TODO 
	* @param @return
	* @return List<Users>
	* @author fl
	* @date 2018年1月8日 下午1:05:59
	* @version V1.0
	 */
	List<Users> queryAll();
	/**
	 * 
	* @Title: queryAllUserList 
	* @Description: 查询所属平台是否关联用户或者设备(DZY改)
	* @param @param map
	* @param @return    入参
	* @return List<Users>    返回类型
	* @author 周宇 
	* @throws
	* @date 2018年2月9日 下午3:36:22 
	* @version V1.0
	 */
	List<Users> queryAllUserList(Map<String, Object> map);
	/**
	 * 
	* @Title: queryUserListRolesZero 
	* @Description: 查询角色orgid为0的用户数据
	* @param @param map
	* @param @return    入参
	* @return List<Users>    返回类型
	* @author 周宇 
	* @throws
	* @date 2018年2月9日 下午3:36:22 
	* @version V1.0
	 */
	List<Users> queryUserListRolesZero(Map<String, Object> conditionMap);

	/**
	 * 
	 * @Title: queryCountByUserListRolesZero
	 * @Description: 查询角色orgid为0的用户数据数量
	 * @param @param conditionMap
	 * @param @return 
	 * @return Integer 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年3月12日 上午11:05:33
	 * @version V1.0
	 */
	Integer queryCountByUserListRolesZero(Map<String, Object> conditionMap);

	/**
	 * 
	* @Title: queryByUserRoleForOrgId 
	* @Description: 根据用户ID查询角色机构ID 
	* @param @param userId
	* @param @return    
	* @return Long
	* @author liucanghai 
	* @throws
	* @date 2018年4月3日 上午10:48:27 
	* @version V1.0
	 */
	Long queryByUserRoleForOrgId(Long userId);

	/**
	 * 
	 * @Title: getLoginUsersOpd
	 * @Description: 根据条件查询
	 * @param @param param
	 * @param @return 
	 * @return UsersVo 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月22日 上午11:24:21
	 * @version V1.0
	 */
	UsersVo getLoginUsersOpd(UsersConditionBean param);
}