package com.ycnet.frms.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.ResourceData;
import com.ycnet.frms.bean.Users;

public interface ResourceDataService {

	//查询资管数据管理数据
	PageBean queryResourceDataList(ResourceData rd, Users user, PageBean pb);

	//重新生成修改保存
	int reGeneratorSave(ResourceData rd);

	//根据ID查询一条
	ResourceData queryById(Long resId);

	//生成.txt文件
	String reGeneratorSave(HttpSession session, HttpServletRequest request, String areaCode);

	//生成数据的添加保存
	int saveGenerData(ResourceData rdi);

	//根据areaCode查询数据
	List<ResourceData> queryByAreaCode(String areaCode);

	//生成数据的修改保存
	int updateGenerData(ResourceData rdi);

	//导入资管数据文件
	int importResourceDatasFile(HttpSession session, HttpServletRequest request, String areaCode, MultipartFile importFile);
	
}
