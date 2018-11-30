package com.ycnet.frms.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.frms.bean.FiberdiscEntity;
import com.ycnet.frms.mapper.FiberdiscEntityMapper;
import com.ycnet.frms.service.FiberdiscEntityService;

@Service("fiberdiscEntityService")
public class FiberdiscEntityServiceImpl implements FiberdiscEntityService{
	
	@Resource(name="fiberdiscEntityMapper")
	private FiberdiscEntityMapper fiberdiscEntityMapper;

	/**
	 * 
	 * @Title: selectFiberdisc
	 * @Description: 根据条件查询端子
	 * @param @param param
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 上午10:00:35
	 * @version V1.0
	 */
	@Override
	public List<FiberdiscEntity> selectFiberdisc(FiberdiscEntity param) {
		return fiberdiscEntityMapper.queryBydev(param);
	}

}
  
    