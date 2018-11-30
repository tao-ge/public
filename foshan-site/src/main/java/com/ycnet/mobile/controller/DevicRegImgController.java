package com.ycnet.mobile.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ycnet.frms.bean.DevicRegImg;
import com.ycnet.frms.service.DevicRegImgService;
import com.ycnet.mobile.util.Result;
import com.ycnet.mobile.util.UploadUtil;

@RestController
public class DevicRegImgController {
	
	@Resource(name="devicRegImgService")
	private DevicRegImgService devicRegImgService;
	
	/**
	 * 
	 * @Title: saveDevicRegImg
	 * @Description: 保存光交箱摄像头拍摄照片
	 * @param @param request
	 * @param @param devicRegImg
	 * @param @return 
	 * @return Object 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年11月13日 上午9:18:17
	 * @version V1.0
	 */
	@RequestMapping("/m/saveDevicRegImg.htm")
	public Object saveDevicRegImg(HttpServletRequest request,DevicRegImg devicRegImg) {
		Result r = Result.get();
		if(request instanceof MultipartHttpServletRequest)
		{
			MultipartHttpServletRequest req = (MultipartHttpServletRequest)request;
			MultipartFile file = req.getFile("imgFiles");//修改图片类型
			if (file!=null&&!file.isEmpty()) {
				 String image = UploadUtil.uploadFile(file, req,"05");//"05"摄像头上传图片
				 devicRegImg.setRegImgUrl(image);
			 }
			devicRegImg.setCreateTime(new Date());
			int ret = devicRegImgService.savedevicRegImg(devicRegImg);
			r.putR(ret);
			r.putRContent(ret==1?"成功":"失败");
		}
		return r;
	}
	
}
  
    