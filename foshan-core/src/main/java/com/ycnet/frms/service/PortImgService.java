package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.frms.bean.Fiberdisc;
import com.ycnet.frms.bean.PortImg;

public interface PortImgService {


	/**
	 * 保存端子图片
	* 
	* @Title: PortImgService.java 
	* @Description: TODO 
	* @param @param fiberdisc
	* @param @param images
	* @param @return
	* @return int
	* @author fl
	* @date 2017年12月3日 下午3:23:57
	* @version V1.0
	 */
	int insertPortImg(Fiberdisc fiberdisc, List<PortImg> list);

	int deleteByUrl(String path);


}
