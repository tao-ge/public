package com.ycnet.frms.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.core.MD5Utils;
import com.ycnet.core.util.AppResponseEntity;
import com.ycnet.core.util.PageBean;
import com.ycnet.core.util.ResultData;
import com.ycnet.frms.bean.DiscinfoStatusEntity;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.bean.WorkorderUsers;
import com.ycnet.frms.mapper.DiscinfoStatusEntityMapper;
import com.ycnet.frms.mapper.UsersMapper;
import com.ycnet.frms.service.RolesService;
import com.ycnet.frms.service.UsersService;
import com.ycnet.frms.service.WorkorderUsersService;
import com.ycnet.frms.vo.UsersConditionBean;
import com.ycnet.frms.vo.UsersVo;

@Service("usersService")
public class UsersServiceImpl implements UsersService{

	@Resource(name="usersMapper")
	private UsersMapper usersMapper;
	
	@Resource(name="rolesService")
	private RolesService rolesService;
	
	@Resource(name="workorderUsersService")
	private WorkorderUsersService workorderUsersService;
	
	@Resource(name="discinfoStatusEntityMapper")
	private DiscinfoStatusEntityMapper discinfoStatusEntityMapper;

	@Override
	@Transactional
	public Users getLoginUsers(String userCode, String userPwd1, String mobileImei) {
		String userPwd="";
		if (userPwd1 !=null && !"".equals(userPwd1)) {
			 userPwd=MD5Utils.md5Password(userPwd1);
		}
		UsersConditionBean param =  new UsersConditionBean(userCode,userPwd,mobileImei);//fl修改
		
		Users user = usersMapper.getLoginUsers(param);
//		UsersVo userOpd = usersMapper.getLoginUsersOpd(param);
		if(user!=null)
		{
			Users userNew=new Users();
//			BeanUtils.copy(user, userNew);
			userNew.setLastLoginTime(new Date());
			userNew.setUserId(user.getUserId());
			
			usersMapper.updateByPrimaryKeySelective(userNew);
		}
		
		return user;
	}

	@Override
	public ResultData userUpdate(HttpSession session, Users users) {
		// TODO Auto-generated method stub
		Users u = (Users) session.getAttribute("users");
		ResultData result = new ResultData();
		int ResultNum = 0;
		
		users.setUserId(u.getUserId());
		ResultNum = usersMapper.updateByPrimaryKeySelective(users);
		if(ResultNum == 1){
			result.setR("1");
			result.setR_content("修改账户信息成功！");
			return result;
		}
		result.setR("0");
		result.setR_content("修改账户信息败！");
		return result;
	}

	@Override
	public ResultData userUpdateForuserPwd(HttpSession session, Users users,String oldUserPwd) {
		// TODO Auto-generated method stub
		Users u = (Users) session.getAttribute("users");
		ResultData result = new ResultData();
		int ResultNum = 0;
				
		users.setUserId(u.getUserId());
		
		Users userPwd = usersMapper.selectByPrimaryKey(users.getUserId());
		//System.out.println(userPwd.getUserPwd() + "============"+ users.getUserPwd());
		if(!MD5Utils.md5Password(oldUserPwd).equals(userPwd.getUserPwd())){//fl修改MD5加密
			result.setR("4");
			result.setR_content("原密码输入错误！");
			return result;
		}
		if(userPwd.getUserPwd().equals(users.getUserPwd())){//原密码与作用域密码是否相同
			result.setR("2");
			result.setR_content("修改密码与原密码相同！");
			return result;
		}
		users.setUserPwd(MD5Utils.md5Password(users.getUserPwd()));//fl修改MD5加密
		ResultNum = usersMapper.updateByPrimaryKeySelective(users);
		
		//如果用户为同步光调项目用户
		if(u.getIsSynchOpss()!=null && "1".equals(u.getIsSynchOpss())) {
			//修改光调用户
			WorkorderUsers wUser = workorderUsersService.queryByUserCode(u.getUserCode());
			wUser.setUserPwd(users.getUserPwd());
			workorderUsersService.updateByPrimaryKeySelective(wUser);
		}
			
		if(ResultNum == 1){
			result.setR("1");
			result.setR_content("修改密码成功！");
			return result;
		}
		result.setR("0");
		result.setR_content("修改密码失败！");
		return result;
	}

	@Override
	public ResultData userInfo(HttpSession session) {
		// TODO Auto-generated method stub
		Users u = (Users) session.getAttribute("users");
		ResultData result = new ResultData();
		Users userPwd = null;
		userPwd = usersMapper.selectByPrimaryKey(u.getUserId());
		
		if(userPwd != null){
			result.setDt(userPwd);
			result.setR("1");
			result.setR_content("查询个人信息成功！");
			return result;
		}
		result.setR("0");
		result.setR_content("查询个人信息失败！");
		return result;
	}

	@Override
	public PageBean queryUserList(Users users,PageBean pb) {
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("user", users);
		conditionMap.put("pb", pb);
		conditionMap.put("orgId", users.getOrgId());
		List<Users> list=null;
		int isAdmin = 0;
		Integer rows;
		if(users.getOrgId()==0) {
			rows = usersMapper.queryCountByUserListRolesZero(conditionMap);
			list = usersMapper.queryUserListRolesZero(conditionMap);
		}else {
			rows = usersMapper.queryCountByConditionMap(conditionMap);
			list = usersMapper.queryUserList(conditionMap);
		}
		pb.setRows(rows);
		pb.setList(list);
		return pb;
	}
	
	@Override
	public List<Users> queryUserListByOrgId(Long orgId) {
		return usersMapper.queryUserListByOrgId(orgId);
	}


	@Override
	@Transactional
	public int insert(Users record) {
		if(record != null){
			return usersMapper.insertSelective(record);
		}
		return 0;
	}

	@Override
	public Users selectByPrimaryKey(Long userId) {
		if(userId != null){
			return usersMapper.selectByPrimaryKey(userId);
		}
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Users record) {
		if(record != null){
			return usersMapper.updateByPrimaryKeySelective(record);
		}
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Users record) {
		if(record != null){
			return usersMapper.updateByPrimaryKey(record);
		}
		return 0;
	}

	@Override
	public Long getUserId() {
		
		return usersMapper.getUserId();
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
	 * @date 2018年4月28日 上午11:41:45
	 * @version V1.0
	 */
	@Override
	public int selectByUserCode(String userCode) {
		int req = 0;
		req = usersMapper.selectByUserCode(userCode);
		req += workorderUsersService.selectByUserCode(userCode);
		return req;
	}

	@Override
	public List<Users> queryList(Long orgId) {
		return usersMapper.queryList(orgId);
	}

	/**
	 * fl  修改成密码为MD5加密
	 */
	@Override
	public int updateMD5Password() {
		int num=0;
		List<Users> userList=usersMapper.queryAll();
		if (userList !=null && userList.size()>0) {
			for (int i = 0; i < userList.size(); i++) {
				Users user = userList.get(i);
				if (user !=null && user.getUserPwd()!=null &&!"".equals(user.getUserPwd())) {
					user.setUserPwd(MD5Utils.md5Password(user.getUserPwd()));
				}
				 num=usersMapper.updateByPrimaryKeySelective(user);
			}
		}
		return num;
	}

	/**
	 * 密码确认查询
	 */
	@Override
	public Users queryqrPassword(Long userId) {
		return usersMapper.selectByPrimaryKey(userId);
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
	public List<Users> queryAllUserList(String devPlatform, Long orgId) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("devPlatform", devPlatform);
		map.put("orgId", orgId);
		return usersMapper.queryAllUserList(map);
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
		return usersMapper.queryByUserRoleForOrgId(userId);
	}
	
	/**
	 * fl 修改 添加user 传参
	 */
	@Override
	public AppResponseEntity userUpdateForuserPwd(HttpSession session, String newUserPwd, String oldUserPwd,Users user) {
		AppResponseEntity r = new AppResponseEntity();
		r.setDto(null);
		int ResultNum = 0;
				
		//users.setUserId(u.getUserId());
		
		Users userPwd = usersMapper.selectByPrimaryKey(user.getUserId());
		//System.out.println(userPwd.getUserPwd() + "============"+ users.getUserPwd());
		if(!MD5Utils.md5Password(oldUserPwd).equals(userPwd.getUserPwd())){//fl修改MD5加密
			r.setCode("4");
			r.setMessage("原密码输入错误！");
			return r;
		}
		
		Users updateUser = new Users();
		updateUser.setUserId(userPwd.getUserId());
		updateUser.setUserPwd(MD5Utils.md5Password(newUserPwd));//fl修改MD5加密
		ResultNum = usersMapper.updateByPrimaryKeySelective(updateUser);
		
		//如果用户为同步光调项目用户
		if(user.getIsSynchOpss()!=null && "1".equals(user.getIsSynchOpss())) {
			//修改光调用户
			WorkorderUsers wUser = workorderUsersService.queryByUserCode(user.getUserCode());
			wUser.setUserPwd(updateUser.getUserPwd());
			workorderUsersService.updateByPrimaryKeySelective(wUser);
		}
			
		if(ResultNum == 1){
			r.setCode("1");
			r.setMessage("修改密码成功！");
			return r;
		}
		r.setCode("0");
		r.setMessage("修改密码失败！");
		return r;
	}
	
	/**
	 * 
	 * @Title: getLoginUsersOpd
	 * @Description: 光路探测登陆接口
	 * @param @param userCode
	 * @param @param userPwd1
	 * @param @param mobileImei
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月25日 下午2:46:39
	 * @version V1.0
	 */
	@Override
	@Transactional
	public UsersVo getLoginUsersOpd(String userCode, String userPwd1, String mobileImei) {
		String userPwd="";
		if (userPwd1 !=null && !"".equals(userPwd1)) {
			 userPwd=MD5Utils.md5Password(userPwd1);
		}
		UsersConditionBean param =  new UsersConditionBean(userCode,userPwd,mobileImei);//fl修改
		
//		Users user = usersMapper.getLoginUsers(param);
		UsersVo userOpd = usersMapper.getLoginUsersOpd(param);
		if(userOpd!=null)
		{
			Users userNew=new Users();
//			BeanUtils.copy(user, userNew);
			userNew.setLastLoginTime(new Date());
			userNew.setUserId(userOpd.getUserId());
			usersMapper.updateByPrimaryKeySelective(userNew);
		}
		
		return userOpd;
	}

//	/* (non-Javadoc)
//	 * @see com.ycnet.frms.service.UsersService#usersService()
//	* 上报数据反转
//	 */
//	@Override
//	public void usersService() {
//		List<Map<String,Object>> list1 = usersMapper.selectAl();
//		for (int i = 0; i < list1.size(); i++) {
//			String value = (String) list1.get(i).get("up_data");
//			Long discStatusId =  Long.valueOf(list1.get(i).get("disc_status_id").toString());
//			String data="";
//			if (value!=null && !"".equals(value)) {
//				List<String> list = new ArrayList<String>();
//				if (value!=null && !"".equals(value)) {
//					char[] datas = value.toCharArray();
//					for (char c : datas) {
//						list.add(c+"");
//					}
//					Collections.reverse(list);
//					for (String c : list) {
//						data+=c;
//					}
//				}
//			}
//			DiscinfoStatusEntity record = new DiscinfoStatusEntity();
//			record.setUpData(data);
//			record.setDiscStatusId(discStatusId);
//			discinfoStatusEntityMapper.updateByPrimaryKeySelective(record);
//			record=null;
//		}
//		
//	}
}
