package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.Roles;

public interface RolesMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(Roles record);

    int insertSelective(Roles record);

    Roles selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(Roles record);

    int updateByPrimaryKey(Roles record);
    
    List<Roles> queryRolesList();
    
    long getRoleId();

    List<Roles> queryRoles();

	List<Roles> checkRoleName(Map<String, Object> map);

	List<Roles> queryRolesAndUser(String roleName);

	//导出数据库  刘沧海 2017/10/13
	List<Roles> queryList();
	/**
	 * 
	* @Title: queryRolesByOrgId 
	* @Description: 根据组织机构查询角色列表
	* @param @param orgId
	* @param @return    入参
	* @return List<Roles>    返回类型
	* @author周宇 
	* @throws
	* @date 2018年2月11日 上午10:56:11 
	* @version V1.0
	 */
	List<Roles> queryRolesByOrgId(Long orgId);

	/**
	 * 
	 * @Title: queryIsAdmin
	 * @Description: 查询该用户是否为管理员角色
	 * @param @param userId
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年3月17日 下午12:34:41
	 * @version V1.0
	 */
	int queryIsAdmin(Long userId);
}