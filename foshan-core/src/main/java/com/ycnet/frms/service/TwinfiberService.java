package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.frms.bean.Twinfiber;
import com.ycnet.frms.vo.TwinfiberBean;
import com.ycnet.frms.vo.TwinfiberVo;

public interface TwinfiberService {

	int add(TwinfiberVo vo);
	
	boolean exists(String portCode);
	
	int delete(Long id);
	
	List<TwinfiberBean> findByDiscId(Long discId);

	//导出数据库  刘沧海 2017/10/13
	List<Twinfiber> queryList(Long orgId, String areaCode);
}
