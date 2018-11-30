package com.ycnet.mobile.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class UploadUtil {

	private static String prexImagUrl ="/uploadImage";
	
	public static String uploadFile(MultipartFile file,MultipartHttpServletRequest request, String state)
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
		}else {
			imgUrl=prexImagUrl;
		}
		String path = request.getSession().getServletContext().getRealPath("/") + imgUrl;
		Date date = new Date();
		String fileName = date.getTime() +file.getOriginalFilename();
		String FileURL = imgUrl +"/" +fileName;
		File parent = new File(path);
		parent.mkdirs();
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
