package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.FacilityImg;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.FacilityImgConditionBean;

public interface FacilityImgService {

	int save(FacilityImg facilityImg);
	
	List<FacilityImg> selectImg(FacilityImg param);
	
	public PageBean queryByConditionBean(FacilityImgConditionBean bean, Users user, PageBean pb);

	List<FacilityImg> selectByDevId(Long devId);

	FacilityImg selectByDevUrl(String paths);

	int saveUrl(FacilityImg facilityImg);

	int deleteUrl(String paths);

	//导出数据库 刘沧海 2017/10/13
	List<FacilityImg> queryList();
}
