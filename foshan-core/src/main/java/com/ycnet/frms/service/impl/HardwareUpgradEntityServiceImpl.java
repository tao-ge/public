package com.ycnet.frms.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.HardwareUpgradEntity;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.HardwareUpgradEntityMapper;
import com.ycnet.frms.service.HardwareUpgradEntityService;
import com.ycnet.frms.util.UploadUtilNoSign;

@Service("hardwareUpgradEntityService")
public class HardwareUpgradEntityServiceImpl implements HardwareUpgradEntityService{

	@Resource(name="hardwareUpgradEntityMapper")
	private HardwareUpgradEntityMapper hardwareUpgradEntityMapper;

	/**
	 * 
	 * @Title: queryRemoteUnlockList
	 * @Description: 设备远程更新列表
	 * @param @param hue
	 * @param @param pb
	 * @param @param user
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月15日 下午5:19:50
	 * @version V1.0
	 */
	@Override
	public PageBean queryRemoteUnlockList(HardwareUpgradEntity hue, PageBean pb, Users user) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("hue", hue);
		map.put("pb", pb);
		map.put("user", user);
		pb.setRows(hardwareUpgradEntityMapper.queryCountqueryRemoteUnlock(map));
		pb.setList(hardwareUpgradEntityMapper.queryRemoteUnlockList(map));
		return pb;
	}

	/**
	 * 
	 * @Title: hardwareUpgradUploadFile
	 * @Description: 上传文件
	 * @param @param request
	 * @param @param importFile
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月16日 上午9:25:21
	 * @version V1.0
	 */
	@Override
	public int hardwareUpgradUploadFile(HttpServletRequest request, MultipartFile importFile, Users user, HardwareUpgradEntity hue) {
		int num = 0;
		MultipartHttpServletRequest req = (MultipartHttpServletRequest)request;
		String fileName = user.getOrgId() + "-" +hue.getHardType() +"v"+hue.getHardVersion();
		//升级文件需按照原名称保存,所以用UploadUtilNoSign类来处理
		String path = UploadUtilNoSign.uploadFile(importFile, req, "07;" + fileName);//"07"硬件远程升级文件
		hue.setHardSize(importFile.getSize());
		hue.setUploadTime(new Date());
		hue.setHardUrl(path);
		hue.setUserId(user.getUserId());
		hue.setOrgId(user.getOrgId());
		hue.setHardType("01");
		num = hardwareUpgradEntityMapper.insertSelective(hue);
		hue.setHardType("02");
		hue.setHardId(null);
		num = hardwareUpgradEntityMapper.insertSelective(hue);
		return num;
	}

	/**
	 * 
	 * @Title: queryByHardVersion
	 * @Description: 根据硬件版本号查看是否重复
	 * @param @param hardVersion
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月16日 上午10:39:49
	 * @version V1.0
	 */
	@Override
	public int queryByHardVersion(String hardVersion, Long orgId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("hardVersion", hardVersion);
		map.put("orgId", orgId);
		return hardwareUpgradEntityMapper.queryByHardVersion(map);
	}

	/**
	 * 
	 * @Title: queryMaxVersion
	 * @Description: 查询组织机构下最大版本号
	 * @param @param user
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月16日 下午1:40:29
	 * @version V1.0
	 */
	@Override
	public String queryMaxVersion(Users user) {
		return hardwareUpgradEntityMapper.queryMaxVersion(user);
	}

	/**
	 * 
	 * @Title: selectByPrimaryKey
	 * @Description: 根据ID查询
	 * @param @param hardId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月17日 下午5:14:10
	 * @version V1.0
	 */
	@Override
	public HardwareUpgradEntity selectByPrimaryKey(Long hardId) {
		return hardwareUpgradEntityMapper.selectByPrimaryKey(hardId);
	}

	/**
	 * 
	 * @Title: queryByFileName
	 * @Description: 根据文件名称模糊查询硬件版本数量
	 * @param @param filename
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月21日 上午9:52:52
	 * @version V1.0
	 */
	@Override
	public int queryByFileName(String filename) {
		return hardwareUpgradEntityMapper.queryByFileName(filename);
	}
}
  
    