package com.ycnet.frms.service.impl;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.frms.bean.UrlImg;
import com.ycnet.frms.mapper.UrlImgMapper;
import com.ycnet.frms.service.UrlImgService;

@Service("urlImgService")
public class UrlImgServiceImpl implements UrlImgService {

	@Resource(name="urlImgMapper")
	private UrlImgMapper urlImgMapper;
	
	
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
	@Override
	public List<UrlImg> queryDownApkUrl(String ip, String proName) {
		return urlImgMapper.queryDownApkUrl(ip,proName);
	}

	
	
	
	
}
