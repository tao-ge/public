package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.frms.bean.UrlImg;

public interface UrlImgService {

	/**
	 * 
	* @Title: queryDownApkUrl 
	* @Description: 根据项目名称和IP查询 tn_url_img
	* @param @param ip
	* @param @param proName
	* @param @return    
	* @return List<UrlImg>
	* @author liucanghai 
	* @throws
	* @date 2018年3月22日 上午11:24:43 
	* @version V1.0
	 */
	List<UrlImg> queryDownApkUrl(String ip, String proName);

}
