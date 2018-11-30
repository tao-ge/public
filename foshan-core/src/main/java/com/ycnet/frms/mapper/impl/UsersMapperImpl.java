package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.UsersMapper;
import com.ycnet.frms.vo.UsersConditionBean;
import com.ycnet.frms.vo.UsersVo;

@Repository("usersMapper")
public class UsersMapperImpl extends  BaseSqlSupport 
		implements UsersMapper{
	
		@Override
		public int insert(Users record)
		{
			return this.insert("com.ycnet.frms.mapper.UsersMapper.insert",record);
		}
	
		@Override
		public Users selectByPrimaryKey(Long id)
		{
			return this.selectOne("com.ycnet.frms.mapper.UsersMapper.selectByPrimaryKey",id);
		}
	
		@Override
		public int deleteByPrimaryKey(Long id)
		{
			return this.delete("com.ycnet.frms.mapper.UsersMapper.deleteByPrimaryKey",id);
		}
	
		@Override
		public int insertSelective(Users record)
		{
			return this.insert("com.ycnet.frms.mapper.UsersMapper.insertSelective",record);
		}
	
		@Override
		public int updateByPrimaryKeySelective(Users record)
		{
			return this.update("com.ycnet.frms.mapper.UsersMapper.updateByPrimaryKeySelective",record);
		}
	
		@Override
		public int updateByPrimaryKey(Users record)
		{
			return this.update("com.ycnet.frms.mapper.UsersMapper.updateByPrimaryKey",record);
		}

		@Override
		public Users getLoginUsers(UsersConditionBean param) {
			
			return this.selectOne("com.ycnet.frms.mapper.UsersMapper.getLoginUsers",param);
			
		}

		@Override
		public List<Users> queryUserList(Map<String,Object> map) {
			
			return this.selectList("com.ycnet.frms.mapper.UsersMapper.queryUserList", map);
		}
		
		@Override
		public List<Users> queryUserListByOrgId(Long orgId) {
			return this.selectList("com.ycnet.frms.mapper.UsersMapper.queryUserListByOrgId", orgId);
		}

		@Override
		public Long getUserId() {
			
			return this.selectOne("com.ycnet.frms.mapper.UsersMapper.getUserId");
		}

		@Override
		public int queryCountByConditionMap(Map<String, Object> map) {
			
			return this.selectOne("com.ycnet.frms.mapper.UsersMapper.queryCountByConditionMap", map);
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
		 * @date 2018年4月28日 上午9:29:56
		 * @version V1.0
		 */
		@Override
		public int selectByUserCode(String userCode) {
			
			return this.selectOne("com.ycnet.frms.mapper.UsersMapper.selectByUserCode", userCode);
		}
		
		@Override
		public List<Users> selectByOrgId(Long orgId) {
			
			return this.selectList("com.ycnet.frms.mapper.UsersMapper.selectByOrgId", orgId);
		}

		@Override
		public List<Users> queryList(Long orgId) {
			return this.selectList("com.ycnet.frms.mapper.UsersMapper.queryList", orgId);
		}

		/**
		 * 查询所有用户
		 */
		@Override
		public List<Users> queryAll() {
			return this.selectList("com.ycnet.frms.mapper.UsersMapper.queryAll");
		}
		/**
		 * 
		* @Title: queryAllUserList 
		* @Description: 查询所属平台是否关联用户或者设备(DZY改)
		* @param @param users
		* @param @return    入参
		* @return List<Users>    返回类型
		* @author 周宇 
		* @throws
		* @date 2018年2月9日 下午3:36:22 
		* @version V1.0
		 */
		@Override
		public List<Users> queryAllUserList(Map<String,Object> map) {
			return this.selectList("com.ycnet.frms.mapper.UsersMapper.queryAllUserList",map);		
		}
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
		@Override
		public List<Users> queryUserListRolesZero(Map<String, Object> conditionMap) {
			return this.selectList("com.ycnet.frms.mapper.UsersMapper.queryUserListRolesZero",conditionMap);		
		}

		/**
		 * 
		 * @Title: queryCountByUserListRolesZero
		 * @Description: 查询角色orgid为0的用户数据数量
		 * @param @param conditionMap
		 * @param @return
		 * @return String    返回类型
		 * @author DZY 
		 * @throws
		 * @date 2018年3月12日 上午11:06:57
		 * @version V1.0
		 */
		@Override
		public Integer queryCountByUserListRolesZero(Map<String, Object> conditionMap) {
			return this.selectOne("com.ycnet.frms.mapper.UsersMapper.queryCountByUserListRolesZero", conditionMap);
		}

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
		@Override
		public Long queryByUserRoleForOrgId(Long userId) {
			return this.selectOne("com.ycnet.frms.mapper.UsersMapper.queryByUserRoleForOrgId", userId);
		}

		/**
		 * 
		 * @Title: getLoginUsersOpd
		 * @Description: 根据条件查询
		 * @param @param param
		 * @param @return
		 * @return String    返回类型
		 * @author DZY 
		 * @throws
		 * @date 2018年6月22日 上午11:25:16
		 * @version V1.0
		 */
		@Override
		public UsersVo getLoginUsersOpd(UsersConditionBean param) {
			return this.selectOne("com.ycnet.frms.mapper.UsersMapper.getLoginUsersOpd", param);
		}
}