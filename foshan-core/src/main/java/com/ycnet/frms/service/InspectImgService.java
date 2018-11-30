package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.frms.bean.InspectImg;

public interface InspectImgService {

	int save(InspectImg inspectImg, Long userId);
	
	List<InspectImg> selectImg(InspectImg param);

	//根据workId查询巡检图片
	List<InspectImg> queryListByWorkId(Long workId);
	
}
