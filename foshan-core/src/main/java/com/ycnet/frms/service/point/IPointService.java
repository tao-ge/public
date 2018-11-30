package com.ycnet.frms.service.point;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ycnet.frms.bean.TnExport;

/**
 * 点位接口
 * @author yxt
 * @since 2017年4月20日17:23:22
 *
 */
public interface IPointService {

	/**
	 * 导入点位
	 * @param request
	 * @param response
	 * @param multiRequest
	 * @return
	 */
	String importPointByExcel(HttpServletRequest request,HttpServletResponse response,MultipartHttpServletRequest multiRequest);

	/**
	 * 转成百度经纬度
	 * @return
	 */
	String changePoints();

	/**
	 * 
	* @Title: queryPoint 
	* @Description: TODO(导入管道站点) 
	* @param @return    入参
	* @return String    返回类型
	* @author （刘沧海） 
	* @throws
	* @date 2017年11月8日 下午4:09:48 
	* @version V1.0
	 */
	int queryPoint();

}
