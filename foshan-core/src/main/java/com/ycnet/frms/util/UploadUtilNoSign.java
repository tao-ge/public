package com.ycnet.frms.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 按原名称保存文件
* @desc: foshan-core  
* @author: DZY  
* @createTime: 2018年6月21日 上午10:18:02  
* @history:  
* @version: v1.0
 */
public class UploadUtilNoSign {

	private static String prexImagUrl ="/uploadImage";
	
	public static String uploadFile(MultipartFile file,MultipartHttpServletRequest request, String state)
	{
		String fileName = "";
		if(state!=null && state.contains(";")) {
			String[] strArr = state.split(";");
			fileName = strArr[1];
			state = strArr[0];
		}else{
			fileName = file.getOriginalFilename();
		}
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
		}
		else {
			imgUrl=prexImagUrl;
		}
		String path = request.getSession().getServletContext().getRealPath("/") + imgUrl;
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

}
