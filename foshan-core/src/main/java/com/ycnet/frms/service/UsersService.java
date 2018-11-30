package com.ycnet.frms.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ycnet.core.util.AppResponseEntity;
import com.ycnet.core.util.PageBean;
import com.ycnet.core.util.ResultData;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.UsersVo;

public interface UsersService {

	Users  getLoginUsers(String userCode,String userPwd1,String mobileImei);
	
	/**
	 * 修改账户信息
	 * @author YHT
	 * @time   2016年7月14日 上午11:15:34
	 * @param session
	 * @param users
	 * @return
	 */
	ResultData  userUpdate(HttpSession session ,Users users);

	/**
	 * 修改密码信息
	 * @author YHT
	 * @time   2016年7月14日 下午1:13:34
	 * @param session
	 * @param users
	 * @return
	 */
	ResultData  userUpdateForuserPwd(HttpSession session ,Users users,String oldUserPwd);
	AppResponseEntity userUpdateForuserPwd(HttpSession session, String newUserPwd, String oldUserPwd, Users user);
	
	/**
	 * 查询个人信息
	 * @author YHT
	 * @time   2016年7月15日 上午11:30:15
	 * @param session
	 * @return
	 */
	ResultData  userInfo(HttpSession session);
	
	/**
	 * 查询用户列表
	 * @author QLF
	 * @time   2016年7月27日 下午15:00:15
	 * @param users
	 * @return
	 */
	PageBean queryUserList(Users users,PageBean pb);
	
	/**
	 * 
	* @Title: queryUserListByOrgId 
	* @Description: TODO(查询相同机构下的用户列表) 
	* @param @param users
	* @param @param pb
	* @param @return    入参
	* @return PageBean    返回类型
	* @author （DZY） 
	* @throws
	* @date 2017年8月31日 下午1:28:28 
	* @version V1.0
	 */
	List<Users> queryUserListByOrgId(Long orgId);
	
	/**
	 * 插入用户
	 * @author QLF
	 * @time   2016年7月29日 上午9:30:15
	 * @param record
	 * @return
	 */
	int insert(Users record);
	/**
	 * 根据主键查询用户信息
	 * @author QLF
	 * @time   2016年7月29日 上午9:30:15
	 * @param userId
	 * @return
	 */
	Users selectByPrimaryKey(Long userId);
	/**
	 * 更新指定字段数据
	 * @author QLF
	 * @time   2016年7月29日 上午9:30:15
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(Users record);
	/**
	 * 根据主键更新数据
	 * @author QLF
	 * @time   2016年7月29日 上午9:30:15
	 * @param record
	 * @return
	 */
    int updateByPrimaryKey(Users record);
    
    Long getUserId();
    
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
     * @date 2018年4月28日 上午9:22:33
     * @version V1.0
     */
    int selectByUserCode(String userCode);

    /**
     * 
    * @Title: queryList 
    * @Description: 生成数据
    * @param @param orgId
    * @param @return    入参
    * @return List<Users>    返回类型
    * @author DZY 
    * @throws
    * @date 2017年10月23日 下午5:01:25 
    * @version V1.0
     */
	List<Users> queryList(Long orgId);

	/**
	 * 修改成密码为MD5加密
	* 
	* @Title: UsersService.java 
	* @Description: TODO 
	* @param 
	* @return void
	* @author fl
	* @date 2018年1月8日 下午12:59:35
	* @version V1.0
	 */
	int updateMD5Password();

	/**
	 * 密码确认查询
	* 
	* @Title: UsersService.java 
	* @Description: TODO 
	* @param @param userId
	* @param @return
	* @return Users
	* @author fl
	* @date 2018年1月8日 下午3:17:08
	* @version V1.0
	 */
	Users queryqrPassword(Long userId);
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
	List<Users> queryAllUserList(String devPlatform, Long orgId);

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
	 * @Description: 光路探测登录接口
	 * @param @param userCode
	 * @param @param userPwd
	 * @param @param mobileImei
	 * @param @return 
	 * @return UsersVo 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月25日 下午2:45:47
	 * @version V1.0
	 */
	UsersVo getLoginUsersOpd(String userCode, String userPwd, String mobileImei);
}
