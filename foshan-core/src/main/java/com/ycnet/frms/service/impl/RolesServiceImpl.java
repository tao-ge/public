package com.ycnet.frms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.frms.bean.Roles;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.RolesMapper;
import com.ycnet.frms.service.RolesService;

@Service("rolesService")
public class RolesServiceImpl implements RolesService{
	
	@Resource(name="rolesMapper")
	private RolesMapper rolesMapper;

	@Override
	public List<Roles> queryRolesList() {
		
		return rolesMapper.queryRolesList();
	}

	@Override
	public int deleteByPrimaryKey(Long roleId) {
		if(roleId != null){
			return rolesMapper.deleteByPrimaryKey(roleId);
		}
		return 0;
	}

	@Override
	public int insert(Roles record) {
		if(record != null){
			return rolesMapper.insert(record);
		}
		return 0;
	}

	@Override
	public int insertSelective(Roles record) {
		if(record != null){
			return rolesMapper.insertSelective(record);
		}
		return 0;
	}

	@Override
	public Roles selectByPrimaryKey(Long roleId) {
		if(roleId != null){
			return rolesMapper.selectByPrimaryKey(roleId);
		}
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Roles record) {
		if(record != null){
			return rolesMapper.updateByPrimaryKeySelective(record);
		}
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Roles record) {
		if(record != null){
			return rolesMapper.updateByPrimaryKey(record);
		}
		return 0;
	}

	@Override
	public long getRoleId() {
		
		return rolesMapper.getRoleId();
	}

	@Override
	public List<Roles> queryRoles() {
		return rolesMapper.queryRoles();
	}

	@Override
	public List<Roles> checkRoleName(String roleName, Users user) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("roleName", roleName);
		map.put("user", user);
		return rolesMapper.checkRoleName(map);
	}

	@Override
	public List<Roles> queryRolesAndUser(String roleName) {
		return rolesMapper.queryRolesAndUser(roleName);
	}

	//导出数据库  刘沧海 2017/10/13
	@Override
	public List<Roles> queryList() {
		return rolesMapper.queryList();
	}
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
	@Override
	public List<Roles> queryRolesByOrgId(Long orgId) {
		return rolesMapper.queryRolesByOrgId(orgId);
	}
	
	/**
	 * 
	 * @Title: queryIsAdmin
	 * @Description: 查询该用户是否为管理员角色
	 * @param @param userId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年3月17日 下午12:34:06
	 * @version V1.0
	 */
	@Override
	public int queryIsAdmin(Long userId) {
		return rolesMapper.queryIsAdmin(userId);
	}

}
