package com.ycnet.frms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.frms.bean.DevicRegImg;
import com.ycnet.frms.mapper.DevicRegImgMapper;
import com.ycnet.frms.service.DevicRegImgService;

@Service("devicRegImgService")
public class DevicRegImgServiceImpl implements DevicRegImgService{

	@Resource(name="devicRegImgMapper")
	private DevicRegImgMapper devicRegImgMapper;
	
	/**
	 * 保存光交箱摄像头拍摄照片
	 */
	@Override
	public int savedevicRegImg(DevicRegImg devicRegImg) {
		return devicRegImgMapper.insertSelective(devicRegImg);
	}

}
  
    