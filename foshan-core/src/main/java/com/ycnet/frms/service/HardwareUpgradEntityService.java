package com.ycnet.frms.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.HardwareUpgradEntity;
import com.ycnet.frms.bean.Users;

public interface HardwareUpgradEntityService {

	/**
	 * 
	 * @Title: queryRemoteUnlockList
	 * @Description: 设备远程更新列表
	 * @param @param hue
	 * @param @param pb
	 * @param @param user
	 * @param @return 
	 * @return PageBean 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月15日 下午5:19:15
	 * @version V1.0
	 */
	PageBean queryRemoteUnlockList(HardwareUpgradEntity hue, PageBean pb, Users user);

	/**
	 * 
	 * @Title: hardwareUpgradUploadFile
	 * @Description: 上传文件
	 * @param @param request
	 * @param @param importFile
	 * @param @return 
	 * @return String 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月16日 上午9:24:53
	 * @version V1.0
	 * @param user 
	 * @param hue 
	 */
	int hardwareUpgradUploadFile(HttpServletRequest request, MultipartFile importFile, Users user, HardwareUpgradEntity hue);

	/**
	 * 
	 * @Title: queryByHardVersion
	 * @Description: 根据硬件版本号查看是否重复
	 * @param @param hardVersion
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月16日 上午10:39:13
	 * @version V1.0
	 * @param orgId 
	 */
	int queryByHardVersion(String hardVersion, Long orgId);

	/**
	 * 
	 * @Title: queryMaxVersion
	 * @Description: 查询组织机构下最大版本号
	 * @param @param user
	 * @param @return 
	 * @return String 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月16日 下午1:39:57
	 * @version V1.0
	 */
	String queryMaxVersion(Users user);

	/**
	 * 
	 * @Title: selectByPrimaryKey
	 * @Description: 根据ID查询
	 * @param @param hardId
	 * @param @return 
	 * @return HardwareUpgradEntity 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月17日 下午5:13:46
	 * @version V1.0
	 */
	HardwareUpgradEntity selectByPrimaryKey(Long hardId);

	/**
	 * 
	 * @Title: queryByFileName
	 * @Description: 根据文件名称模糊查询硬件版本数量
	 * @param @param filename
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月21日 上午9:36:25
	 * @version V1.0
	 */
	int queryByFileName(String filename);

}
  
    