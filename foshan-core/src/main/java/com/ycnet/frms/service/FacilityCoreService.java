package com.ycnet.frms.service;

import com.ycnet.frms.bean.Discinfo;
import com.ycnet.frms.bean.Fiberdisc;
import com.ycnet.frms.bean.FiberdiscGroup;
import com.ycnet.frms.vo.FiberdiscGroupVo;

public interface FacilityCoreService {

	/**
	 * 添加分组
	 * @param group
	 * @return
	 */
	int  addGroup(FiberdiscGroup group);
	
	int  addGroup1(FiberdiscGroupVo group);
	
	/**
	 * 添加熔纤盘信息
	 * @param discinfo
	 * @return
	 */
	int  addDiscInfo(Discinfo discinfo,int index);
	
	/**
	 * 添加/修改熔纤盘点
	 * @param fiberdisc
	 * @return
	 */
	int  saveFiberdisc(Fiberdisc fiberdisc);
	
	/**
	 * 添加分组（资管专用）
	 * @author: YHT
	 * @date: 2017年10月24日 上午10:12:13 
	 * @Title: addGroup  
	 * @param @param group
	 * @param @return     
	 * @return int   
	 * @throws
	 */
	int  addGroupZG(FiberdiscGroup group);
}
