package com.ycnet.frms.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 保存文件,名称格式:orgId_System.currentTimeMillis()+文件名
* @desc: foshan-core  
* @author: DZY  
* @createTime: 2018年6月21日 上午10:18:21  
* @history:  
* @version: v1.0
 */
public class UploadUtil {

	private static String prexImagUrl ="/uploadImage";
	
	public static String uploadFile(MultipartFile file,MultipartHttpServletRequest request, Long orgId, String state)
	{
		String imgUrl="";
		if("01".equals(state)) {
			imgUrl=prexImagUrl+"/facility";//设施添加图片
		}else if("02".equals(state)) {
			imgUrl=prexImagUrl+"/terminal";//端子添加图片
		}else if("03".equals(state)) {
			imgUrl=prexImagUrl+"/deviceInspect";//巡检添加图片
		}else if("04".equals(state)) {
			imgUrl=prexImagUrl+"/cableSection";//光缆段添加图片
		}else if("05".equals(state)) {
			imgUrl=prexImagUrl+"/monitoring";//摄像头上传图片(实时监控)
		}else if("06".equals(state)) {
			imgUrl=prexImagUrl+"/wellImg";//摄像头上传图片(实时监控)
		}else if("07".equals(state)) {
			imgUrl=prexImagUrl+"/hardwareUpgradFile";//硬件升级版本文件
		}else if("08".equals(state)) {
			imgUrl=prexImagUrl+"/implePlansimg";//施工反馈图片
		}else if("09".equals(state)) {
			imgUrl=prexImagUrl+"/cableSectionImage";//物理光缆图片
		}
		else {
			imgUrl=prexImagUrl;
		}
		String path = request.getSession().getServletContext().getRealPath("/") + imgUrl;
		Date date = new Date();
		String fileName = orgId+"_"+date.getTime() +file.getOriginalFilename();
		String FileURL = imgUrl +"/" +fileName;
		File parent = new File(path);
		if (!parent.exists()) {
			parent.mkdirs();
		}
		File imageFile = new File(parent , fileName );
		
		OutputStream out = null;
		
		try {
			out= new FileOutputStream(imageFile);
			out.write(file.getBytes());
			out.flush();
			return FileURL;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if (out!=null)
			{
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "";
	}
	
	/**
	 * 删除图片文件
	* @Function: UploadUtil.java
	* @return：返回结果描述
	* @throws：异常描述
	* @version: v1.0.0
	* @author: FL
	 * @param type 
	* @date: 2018年9月29日 下午5:06:01
	 */
	public static int deleteImage(String imgUrl,HttpServletRequest request, String state) {
		if("01".equals(state)) {
			imgUrl=prexImagUrl+"/facility";//设施添加图片
		}else if("02".equals(state)) {
			imgUrl=prexImagUrl+"/terminal";//端子添加图片
		}else if("03".equals(state)) {
			imgUrl=prexImagUrl+"/deviceInspect";//巡检添加图片
		}else if("04".equals(state)) {
			imgUrl=prexImagUrl+"/cableSection";//光缆段添加图片
		}else if("05".equals(state)) {
			imgUrl=prexImagUrl+"/monitoring";//摄像头上传图片(实时监控)
		}else if("06".equals(state)) {
			imgUrl=prexImagUrl+"/wellImg";//摄像头上传图片(实时监控)
		}else if("07".equals(state)) {
			imgUrl=prexImagUrl+"/hardwareUpgradFile";//硬件升级版本文件
		}else if("08".equals(state)) {
			imgUrl=prexImagUrl+"/implePlansimg";//施工反馈图片
		}else if("09".equals(state)) {
			imgUrl=prexImagUrl+"/cableSectionImage";//物理光缆图片
		}
		else {
			imgUrl=prexImagUrl;
		}
		int del=0;
		try {
			if (imgUrl != null && !"".equals(imgUrl)) {
				File file = new File(request.getSession().getServletContext().getRealPath("/") + imgUrl);
				if (file.exists() && file.isFile()) {
					file.delete();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return del;
	}

}
