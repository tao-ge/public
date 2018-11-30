package com.ycnet.frms.service.pipeline;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ycnet.frms.bean.PipelineSection;
import com.ycnet.frms.bean.pipeline.Pipeline;


/**
 * 管道接口
 * @author yxt
 * @since 2017年4月20日17:08:01
 *
 */
public interface IPipeLineService {

	/**
	 * excel导入管道信息
	 * @param request
	 * @param response
	 * @param multiRequest
	 * @return
	 */
	String importPipeLineByExcel(HttpServletRequest request,
			HttpServletResponse response,
			MultipartHttpServletRequest multiRequest);

	/**
	 * 查询管道
	 * @return
	 */
	List<Pipeline> findPipeline();

	/**
	 * 
	* @Title: queryPipelineSectionByDevId 
	* @Description: 根据devid查询管道段信息
	* @param @param devId
	* @param @return    入参
	* @return List<PipelineSection>    返回类型
	* @author 周宇 
	* @throws
	* @date 2017年11月30日 上午9:24:29
	* @version V1.0
	 */
	List<PipelineSection> queryPipelineSectionByDevId(Long devId);

}
